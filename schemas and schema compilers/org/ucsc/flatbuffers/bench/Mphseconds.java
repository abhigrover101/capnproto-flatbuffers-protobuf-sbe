// automatically generated, do not modify

package org.ucsc.flatbuffers.bench;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

public final class Mphseconds extends Struct {
  public Mphseconds __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public int mph() { return bb.getShort(bb_pos + 0) & 0xFFFF; }
  public void mutateMph(int mph) { bb.putShort(bb_pos + 0, (short)mph); }
  public float seconds() { return bb.getFloat(bb_pos + 4); }
  public void mutateSeconds(float seconds) { bb.putFloat(bb_pos + 4, seconds); }

  public static int createMphseconds(FlatBufferBuilder builder, int mph, float seconds) {
    builder.prep(4, 8);
    builder.putFloat(seconds);
    builder.pad(2);
    builder.putShort((short)(mph & 0xFFFF));
    return builder.offset();
  }
};

