// automatically generated, do not modify

package org.ucsc.flatbuffers.bench;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

public final class varDataEncoding extends Struct {
  public varDataEncoding __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public int length() { return bb.get(bb_pos + 0) & 0xFF; }
  public void mutateLength(int length) { bb.put(bb_pos + 0, (byte)length); }
  public int varData() { return bb.get(bb_pos + 1) & 0xFF; }
  public void mutateVarData(int varData) { bb.put(bb_pos + 1, (byte)varData); }

  public static int createvarDataEncoding(FlatBufferBuilder builder, int length, int varData) {
    builder.prep(1, 2);
    builder.putByte((byte)(varData & 0xFF));
    builder.putByte((byte)(length & 0xFF));
    return builder.offset();
  }
};

