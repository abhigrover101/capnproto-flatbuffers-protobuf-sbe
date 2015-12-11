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

package uk.co.real_logic.sbe;

import org.openjdk.jmh.annotations.*;

import extension.CarEncoder;
import uk.co.real_logic.agrona.concurrent.UnsafeBuffer;
import uk.co.real_logic.sbe.examples.*;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

public class CarBenchmark
{
    private static final byte[] MAKE;
    private static final byte[] MODEL;
    private static final byte[] ENG_MAN_CODE;
    private static final byte[] VEHICLE_CODE;

    static
    {
        try
        {
            MAKE = "MAKE".getBytes(CarEncoder.makeCharacterEncoding());
            MODEL = "MODEL".getBytes(CarEncoder.modelCharacterEncoding());
            ENG_MAN_CODE = "abc".getBytes(EngineEncoder.manufacturerCodeCharacterEncoding());
            VEHICLE_CODE = "abcdef".getBytes(CarEncoder.vehicleCodeCharacterEncoding());
        }
        catch (final UnsupportedEncodingException ex)
        {
            throw new RuntimeException(ex);
        }
    }

    @State(Scope.Benchmark)
    public static class MyState
    {
        final int bufferIndex = 0;

        final MessageHeaderEncoder messageHeaderEncoder = new MessageHeaderEncoder();
        final MessageHeaderDecoder messageHeaderDecoder = new MessageHeaderDecoder();

        final CarEncoder carEncoder = new CarEncoder();
        final CarDecoder carDecoder = new CarDecoder();

        final UnsafeBuffer encodeBuffer = new UnsafeBuffer(ByteBuffer.allocateDirect(1024));

        final byte[] tempBuffer = new byte[128];
        final UnsafeBuffer decodeBuffer = new UnsafeBuffer(ByteBuffer.allocateDirect(1024));

        {
            CarBenchmark.encode(messageHeaderEncoder, carEncoder, decodeBuffer, bufferIndex);
            //System.out.printf("length=============%d\n",decodeBuffer.byteBuffer().position());
        }

    }

    @Benchmark
    public int testEncode(final MyState state)
    {
        final MessageHeaderEncoder messageHeaderEncoder = state.messageHeaderEncoder;
        final CarEncoder carEncoder = state.carEncoder;
        final UnsafeBuffer buffer = state.encodeBuffer;
        final int bufferIndex = state.bufferIndex;

        encode(messageHeaderEncoder, carEncoder, buffer, bufferIndex);
        System.out.println(carEncoder.limit());
        System.out.println(buffer.byteBuffer().remaining());
        return carEncoder.encodedLength();
    }

    //@Benchmark
    public int testDecode(final MyState state)
    {
        final MessageHeaderDecoder messageHeaderDecoder = state.messageHeaderDecoder;
        final CarDecoder carDecoder = state.carDecoder;
        final UnsafeBuffer buffer = state.decodeBuffer;
        final int bufferIndex = state.bufferIndex;
        final byte[] tempBuffer = state.tempBuffer;

        decode(messageHeaderDecoder, carDecoder, buffer, bufferIndex, tempBuffer);

        return carDecoder.encodedLength();
    }

    public static CarEncoder encode(
        final MessageHeaderEncoder messageHeader, final CarEncoder car, final UnsafeBuffer buffer, final int bufferIndex)
    {
        messageHeader
            .wrap(buffer, bufferIndex)
            .blockLength(car.sbeBlockLength())
            .templateId(car.sbeTemplateId())
            .schemaId(car.sbeSchemaId())
            .version(car.sbeSchemaVersion());

        car
            .wrap(buffer, bufferIndex + messageHeader.encodedLength())
            .code(Model.A)
            .modelYear(2005)
            .serialNumber(12345)
            .available(BooleanType.TRUE)
            .putVehicleCode(VEHICLE_CODE, 0);
        //System.out.println(bufferIndex);

        for (int i = 0, size = CarEncoder.someNumbersLength(); i < size; i++)
        {
            car.someNumbers(i, i);
        }

        car.extras().clear()
           .sportsPack(true)
           .sunRoof(true);

        car.engine().capacity(4200)
           .numCylinders((short)8)
           .putManufacturerCode(ENG_MAN_CODE, 0);
        
        car.fuelFiguresCount(3).next().speed(30).mpg(35.9f)
           .next().speed(55).mpg(49.0f)
           .next().speed(75).mpg(40.0f);

        final CarEncoder.PerformanceFiguresEncoder perfFigures = car.performanceFiguresCount(2);
        perfFigures.next().octaneRating((short)95)
                   .accelerationCount(3).next().mph(30).seconds(4.0f)
                   .next().mph(60).seconds(7.5f)
                   .next().mph(100).seconds(12.2f);
        perfFigures.next().octaneRating((short)99)
                   .accelerationCount(3).next().mph(30).seconds(3.8f)
                   .next().mph(60).seconds(7.1f)
                   .next().mph(100).seconds(11.8f);

        car.putMake(MAKE, 0, MAKE.length);
        car.putModel(MODEL, 0, MODEL.length);
        //System.out.println(bufferIndex);
        return car;
    }


    private static CarDecoder decode(
        final MessageHeaderDecoder messageHeader,
        final CarDecoder car,
        final UnsafeBuffer buffer,
        final int bufferIndex,
        final byte[] tempBuffer)
    {
        messageHeader.wrap(buffer, bufferIndex);

        final int actingVersion = messageHeader.version();
        final int actingBlockLength = messageHeader.blockLength();
        car.wrap(buffer, bufferIndex + messageHeader.encodedLength(), actingBlockLength, actingVersion);
//        car.serialNumber();
//        car.modelYear();
//        car.available();
//        car.code();
//
//        for (int i = 0, size = CarDecoder.someNumbersLength(); i < size; i++)
//        {
//            car.someNumbers(i);
//        }
//
//        for (int i = 0, size = CarDecoder.vehicleCodeLength(); i < size; i++)
//        {
//            car.vehicleCode(i);
//        }
//
//        final OptionalExtrasDecoder extras = car.extras();
//        extras.cruiseControl();
//        extras.sportsPack();
//        extras.sunRoof();
//
//        final EngineDecoder engine = car.engine();
//        engine.capacity();
//        engine.numCylinders();
//        engine.maxRpm();
//        for (int i = 0, size = EngineDecoder.manufacturerCodeLength(); i < size; i++)
//        {
//            engine.manufacturerCode(i);
//        }
//
//        engine.getFuel(tempBuffer, 0, tempBuffer.length);
//
//        for (final CarDecoder.FuelFiguresDecoder fuelFigures : car.fuelFigures())
//        {
//            fuelFigures.speed();
//            fuelFigures.mpg();
//        }
//
//        for (final CarDecoder.PerformanceFiguresDecoder performanceFigures : car.performanceFigures())
//        {
//            performanceFigures.octaneRating();
//
//            for (final CarDecoder.PerformanceFiguresDecoder.AccelerationDecoder acceleration : performanceFigures.acceleration())
//            {
//                acceleration.mph();
//                acceleration.seconds();
//            }
//        }
//
//        car.getMake(tempBuffer, 0, tempBuffer.length);
//        car.getModel(tempBuffer, 0, tempBuffer.length);
        car.model();
        return car;
    }

    /*
     * Benchmarks to allow execution outside of JMH.
     */

    public static void main(final String[] args)
    {
        for (int i = 0; i < 10; i++)
        {
            perfTestEncode(i);
            perfTestDecode(i);
        }
    }

    private static void perfTestEncode(final int runNumber)
    {
        final int reps = 10 * 1000 * 1000;
        final MyState state = new MyState();
        final CarBenchmark benchmark = new CarBenchmark();

        final long start = System.nanoTime();
        for (int i = 0; i < reps; i++)
        {
            benchmark.testEncode(state);
        }

        final long totalDuration = System.nanoTime() - start;

        System.out.printf(
            "%d - %d(ns) average duration for %s.testEncode() - message encodedLength %d\n",
            runNumber,
            (totalDuration / reps),
            benchmark.getClass().getName(),
            (state.carEncoder.encodedLength() + state.messageHeaderEncoder.encodedLength()));
    }

    private static void perfTestDecode(final int runNumber)
    {
        final int reps = 10 * 1000 * 1000;
        final MyState state = new MyState();
        final CarBenchmark benchmark = new CarBenchmark();

        final long start = System.nanoTime();
        for (int i = 0; i < reps; i++)
        {
            benchmark.testDecode(state);
        }

        final long totalDuration = System.nanoTime() - start;

        System.out.printf(
            "%d - %d(ns) average duration for %s.testDecode() - message encodedLength %d\n",
            runNumber,
            (totalDuration / reps),
            benchmark.getClass().getName(),
            (state.carDecoder.encodedLength() + state.messageHeaderDecoder.encodedLength()));
    }
}
