// automatically generated, do not modify

package uk.co.real_logic.protobuf.examples;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

public final class PerformanceFigures extends Table {
  public static PerformanceFigures getRootAsPerformanceFigures(ByteBuffer _bb) { return getRootAsPerformanceFigures(_bb, new PerformanceFigures()); }
  public static PerformanceFigures getRootAsPerformanceFigures(ByteBuffer _bb, PerformanceFigures obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__init(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public PerformanceFigures __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public long octaneRating() { int o = __offset(4); return o != 0 ? (long)bb.getInt(o + bb_pos) & 0xFFFFFFFFL : 0; }
  public Acceleration acceleration(int j) { return acceleration(new Acceleration(), j); }
  public Acceleration acceleration(Acceleration obj, int j) { int o = __offset(6); return o != 0 ? obj.__init(__indirect(__vector(o) + j * 4), bb) : null; }
  public int accelerationLength() { int o = __offset(6); return o != 0 ? __vector_len(o) : 0; }

  public static int createPerformanceFigures(FlatBufferBuilder builder,
      long octaneRating,
      int acceleration) {
    builder.startObject(2);
    PerformanceFigures.addAcceleration(builder, acceleration);
    PerformanceFigures.addOctaneRating(builder, octaneRating);
    return PerformanceFigures.endPerformanceFigures(builder);
  }

  public static void startPerformanceFigures(FlatBufferBuilder builder) { builder.startObject(2); }
  public static void addOctaneRating(FlatBufferBuilder builder, long octaneRating) { builder.addInt(0, (int)(octaneRating & 0xFFFFFFFFL), 0); }
  public static void addAcceleration(FlatBufferBuilder builder, int accelerationOffset) { builder.addOffset(1, accelerationOffset, 0); }
  public static int createAccelerationVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startAccelerationVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static int endPerformanceFigures(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
};

