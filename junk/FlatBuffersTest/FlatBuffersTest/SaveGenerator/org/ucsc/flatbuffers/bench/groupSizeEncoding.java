// automatically generated, do not modify

package org.ucsc.flatbuffers.bench;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

public final class groupSizeEncoding extends Struct {
  public groupSizeEncoding __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public int blockLength() { return bb.getShort(bb_pos + 0) & 0xFFFF; }
  public void mutateBlockLength(int blockLength) { bb.putShort(bb_pos + 0, (short)blockLength); }
  public int numInGroup() { return bb.get(bb_pos + 2) & 0xFF; }
  public void mutateNumInGroup(int numInGroup) { bb.put(bb_pos + 2, (byte)numInGroup); }

  public static int creategroupSizeEncoding(FlatBufferBuilder builder, int blockLength, int numInGroup) {
    builder.prep(2, 4);
    builder.pad(1);
    builder.putByte((byte)(numInGroup & 0xFF));
    builder.putShort((short)(blockLength & 0xFFFF));
    return builder.offset();
  }
};

