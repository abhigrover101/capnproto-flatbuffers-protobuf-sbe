// automatically generated, do not modify

package org.ucsc.flatbuffers.bench;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

public final class PerformanceFigures extends Table {
  public static PerformanceFigures getRootAsPerformanceFigures(ByteBuffer _bb) { return getRootAsPerformanceFigures(_bb, new PerformanceFigures()); }
  public static PerformanceFigures getRootAsPerformanceFigures(ByteBuffer _bb, PerformanceFigures obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__init(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public PerformanceFigures __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public short octaneRating() { int o = __offset(4); return o != 0 ? bb.getShort(o + bb_pos) : 0; }
  public boolean mutateOctaneRating(short octaneRating) { int o = __offset(4); if (o != 0) { bb.putShort(o + bb_pos, octaneRating); return true; } else { return false; } }
  public Mphseconds acceleration(int j) { return acceleration(new Mphseconds(), j); }
  public Mphseconds acceleration(Mphseconds obj, int j) { int o = __offset(6); return o != 0 ? obj.__init(__vector(o) + j * 8, bb) : null; }
  public int accelerationLength() { int o = __offset(6); return o != 0 ? __vector_len(o) : 0; }

  public static int createPerformanceFigures(FlatBufferBuilder builder,
      short octaneRating,
      int acceleration) {
    builder.startObject(2);
    PerformanceFigures.addAcceleration(builder, acceleration);
    PerformanceFigures.addOctaneRating(builder, octaneRating);
    return PerformanceFigures.endPerformanceFigures(builder);
  }

  public static void startPerformanceFigures(FlatBufferBuilder builder) { builder.startObject(2); }
  public static void addOctaneRating(FlatBufferBuilder builder, short octaneRating) { builder.addShort(0, octaneRating, 0); }
  public static void addAcceleration(FlatBufferBuilder builder, int accelerationOffset) { builder.addOffset(1, accelerationOffset, 0); }
  public static void startAccelerationVector(FlatBufferBuilder builder, int numElems) { builder.startVector(8, numElems, 4); }
  public static int endPerformanceFigures(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
};

