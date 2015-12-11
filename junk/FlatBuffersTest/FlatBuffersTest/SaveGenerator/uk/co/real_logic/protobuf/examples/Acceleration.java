// automatically generated, do not modify

package uk.co.real_logic.protobuf.examples;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

public final class Acceleration extends Table {
  public static Acceleration getRootAsAcceleration(ByteBuffer _bb) { return getRootAsAcceleration(_bb, new Acceleration()); }
  public static Acceleration getRootAsAcceleration(ByteBuffer _bb, Acceleration obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__init(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public Acceleration __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public long mph() { int o = __offset(4); return o != 0 ? (long)bb.getInt(o + bb_pos) & 0xFFFFFFFFL : 0; }
  public float seconds() { int o = __offset(6); return o != 0 ? bb.getFloat(o + bb_pos) : 0; }

  public static int createAcceleration(FlatBufferBuilder builder,
      long mph,
      float seconds) {
    builder.startObject(2);
    Acceleration.addSeconds(builder, seconds);
    Acceleration.addMph(builder, mph);
    return Acceleration.endAcceleration(builder);
  }

  public static void startAcceleration(FlatBufferBuilder builder) { builder.startObject(2); }
  public static void addMph(FlatBufferBuilder builder, long mph) { builder.addInt(0, (int)(mph & 0xFFFFFFFFL), 0); }
  public static void addSeconds(FlatBufferBuilder builder, float seconds) { builder.addFloat(1, seconds, 0); }
  public static int endAcceleration(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
};

