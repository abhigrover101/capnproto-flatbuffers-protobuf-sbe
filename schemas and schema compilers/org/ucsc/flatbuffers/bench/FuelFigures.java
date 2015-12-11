// automatically generated, do not modify

package org.ucsc.flatbuffers.bench;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

public final class FuelFigures extends Struct {
  public FuelFigures __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public int speed() { return bb.getShort(bb_pos + 0) & 0xFFFF; }
  public void mutateSpeed(int speed) { bb.putShort(bb_pos + 0, (short)speed); }
  public float mpg() { return bb.getFloat(bb_pos + 4); }
  public void mutateMpg(float mpg) { bb.putFloat(bb_pos + 4, mpg); }

  public static int createFuelFigures(FlatBufferBuilder builder, int speed, float mpg) {
    builder.prep(4, 8);
    builder.putFloat(mpg);
    builder.pad(2);
    builder.putShort((short)(speed & 0xFFFF));
    return builder.offset();
  }
};

