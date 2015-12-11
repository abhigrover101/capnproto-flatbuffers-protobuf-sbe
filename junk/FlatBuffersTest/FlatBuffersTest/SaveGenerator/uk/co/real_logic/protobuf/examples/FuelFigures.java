// automatically generated, do not modify

package uk.co.real_logic.protobuf.examples;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

public final class FuelFigures extends Table {
  public static FuelFigures getRootAsFuelFigures(ByteBuffer _bb) { return getRootAsFuelFigures(_bb, new FuelFigures()); }
  public static FuelFigures getRootAsFuelFigures(ByteBuffer _bb, FuelFigures obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__init(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public FuelFigures __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public long speed() { int o = __offset(4); return o != 0 ? (long)bb.getInt(o + bb_pos) & 0xFFFFFFFFL : 0; }
  public float mpg() { int o = __offset(6); return o != 0 ? bb.getFloat(o + bb_pos) : 0; }

  public static int createFuelFigures(FlatBufferBuilder builder,
      long speed,
      float mpg) {
    builder.startObject(2);
    FuelFigures.addMpg(builder, mpg);
    FuelFigures.addSpeed(builder, speed);
    return FuelFigures.endFuelFigures(builder);
  }

  public static void startFuelFigures(FlatBufferBuilder builder) { builder.startObject(2); }
  public static void addSpeed(FlatBufferBuilder builder, long speed) { builder.addInt(0, (int)(speed & 0xFFFFFFFFL), 0); }
  public static void addMpg(FlatBufferBuilder builder, float mpg) { builder.addFloat(1, mpg, 0); }
  public static int endFuelFigures(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
};

