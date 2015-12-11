/*
 * Copyright 2013 Real Logic Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.co.real_logic.sbe.ir;

import uk.co.real_logic.agrona.CloseHelper;
import uk.co.real_logic.agrona.MutableDirectBuffer;
import uk.co.real_logic.agrona.concurrent.UnsafeBuffer;
import uk.co.real_logic.sbe.PrimitiveType;
import uk.co.real_logic.sbe.ir.generated.FrameCodecDecoder;
import uk.co.real_logic.sbe.ir.generated.TokenCodecDecoder;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import static uk.co.real_logic.sbe.ir.IrUtil.*;

public class IrDecoder implements AutoCloseable
{
    private static final int CAPACITY = 4096;

    private final FileChannel channel;
    private final MutableDirectBuffer directBuffer;
    private final FrameCodecDecoder frameDecoder = new FrameCodecDecoder();
    private final TokenCodecDecoder tokenDecoder = new TokenCodecDecoder();
    private int offset;
    private final long length;
    private String irPackageName = null;
    private String irNamespaceName = null;
    private String semanticVersion = null;
    private List<Token> irHeader = null;
    private int irId;
    private int irVersion = 0;
    private final byte[] valArray = new byte[CAPACITY];
    private final MutableDirectBuffer valBuffer = new UnsafeBuffer(valArray);

    public IrDecoder(final String fileName)
    {
        try
        {
            channel = new RandomAccessFile(fileName, "r").getChannel();
            final MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
            directBuffer = new UnsafeBuffer(buffer);
            length = channel.size();
            offset = 0;
        }
        catch (final IOException ex)
        {
            throw new RuntimeException(ex);
        }
    }

    public IrDecoder(final ByteBuffer buffer)
    {
        channel = null;
        length = buffer.limit();
        directBuffer = new UnsafeBuffer(buffer);
        offset = 0;
    }

    public void close()
    {
        CloseHelper.quietClose(channel);
    }

    public Ir decode()
    {
        decodeFrame();

        final List<Token> tokens = new ArrayList<>();
        while (offset < length)
        {
            tokens.add(decodeToken());
        }

        int i = 0;

        if (tokens.get(0).signal() == Signal.BEGIN_COMPOSITE)
        {
            i = captureHeader(tokens, 0);
        }

        final Ir ir = new Ir(irPackageName, irNamespaceName, irId, irVersion, semanticVersion, irHeader);

        for (int size = tokens.size(); i < size; i++)
        {
            if (tokens.get(i).signal() == Signal.BEGIN_MESSAGE)
            {
                i = captureMessage(tokens, i, ir);
            }
        }

        return ir;
    }

    private int captureHeader(final List<Token> tokens, int index)
    {
        final List<Token> headerTokens = new ArrayList<>();

        Token token = tokens.get(index);
        headerTokens.add(token);
        do
        {
            token = tokens.get(++index);
            headerTokens.add(token);
        }
        while (Signal.END_COMPOSITE != token.signal());

        irHeader = headerTokens;

        return index;
    }

    private int captureMessage(final List<Token> tokens, int index, final Ir ir)
    {
        final List<Token> messageTokens = new ArrayList<>();

        Token token = tokens.get(index);
        messageTokens.add(token);
        do
        {
            token = tokens.get(++index);
            messageTokens.add(token);
        }
        while (Signal.END_MESSAGE != token.signal());

        ir.addMessage(tokens.get(index).id(), messageTokens);

        return index;
    }

    private void decodeFrame()
    {
        frameDecoder.wrap(directBuffer, offset, frameDecoder.sbeBlockLength(), 0);

        irId = frameDecoder.irId();

        if (frameDecoder.irVersion() != 0)
        {
            throw new IllegalStateException("Unknown SBE version: " + frameDecoder.irVersion());
        }

        irVersion = frameDecoder.schemaVersion();
        irPackageName = frameDecoder.packageName();

        irNamespaceName = frameDecoder.namespaceName();
        if (irNamespaceName.isEmpty())
        {
            irNamespaceName = null;
        }

        semanticVersion = frameDecoder.semanticVersion();
        if (semanticVersion.isEmpty())
        {
            semanticVersion = null;
        }

        offset += frameDecoder.encodedLength();
    }

    private Token decodeToken()
    {
        final Token.Builder tokenBuilder = new Token.Builder();
        final Encoding.Builder encBuilder = new Encoding.Builder();

        tokenDecoder.wrap(directBuffer, offset, tokenDecoder.sbeBlockLength(), 0);

        tokenBuilder
            .offset(tokenDecoder.tokenOffset())
            .size(tokenDecoder.tokenSize())
            .id(tokenDecoder.fieldId())
            .version(tokenDecoder.tokenVersion())
            .signal(mapSignal(tokenDecoder.signal()));

        final PrimitiveType type = mapPrimitiveType(tokenDecoder.primitiveType());

        encBuilder
            .primitiveType(mapPrimitiveType(tokenDecoder.primitiveType()))
            .byteOrder(mapByteOrder(tokenDecoder.byteOrder()))
            .presence(mapPresence(tokenDecoder.presence()));

        tokenBuilder.name(tokenDecoder.name());

        encBuilder.constValue(get(valBuffer, type, tokenDecoder.getConstValue(valArray, 0, valArray.length)));
        encBuilder.minValue(get(valBuffer, type, tokenDecoder.getMinValue(valArray, 0, valArray.length)));
        encBuilder.maxValue(get(valBuffer, type, tokenDecoder.getMaxValue(valArray, 0, valArray.length)));
        encBuilder.nullValue(get(valBuffer, type, tokenDecoder.getNullValue(valArray, 0, valArray.length)));

        final String characterEncoding = tokenDecoder.characterEncoding();
        encBuilder.characterEncoding(characterEncoding.isEmpty() ? null : characterEncoding);

        final String epoch = tokenDecoder.epoch();
        encBuilder.epoch(epoch.isEmpty() ? null : epoch);

        final String timeUnit = tokenDecoder.timeUnit();
        encBuilder.timeUnit(timeUnit.isEmpty() ? null : timeUnit);

        final String semanticType = tokenDecoder.semanticType();
        encBuilder.semanticType(semanticType.isEmpty() ? null : semanticType);

        offset += tokenDecoder.encodedLength();

        return tokenBuilder.encoding(encBuilder.build()).build();
    }
}
