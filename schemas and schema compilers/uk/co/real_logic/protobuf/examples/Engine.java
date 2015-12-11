// automatically generated, do not modify

package uk.co.real_logic.protobuf.examples;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

public final class Engine extends Table {
  public static Engine getRootAsEngine(ByteBuffer _bb) { return getRootAsEngine(_bb, new Engine()); }
  public static Engine getRootAsEngine(ByteBuffer _bb, Engine obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__init(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public Engine __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public long capacity() { int o = __offset(4); return o != 0 ? (long)bb.getInt(o + bb_pos) & 0xFFFFFFFFL : 0; }
  public long numCylinders() { int o = __offset(6); return o != 0 ? (long)bb.getInt(o + bb_pos) & 0xFFFFFFFFL : 0; }
  public long maxRpm() { int o = __offset(8); return o != 0 ? (long)bb.getInt(o + bb_pos) & 0xFFFFFFFFL : 9000; }
  public String manufacturerCode() { int o = __offset(10); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer manufacturerCodeAsByteBuffer() { return __vector_as_bytebuffer(10, 1); }
  public String fuel() { int o = __offset(12); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer fuelAsByteBuffer() { return __vector_as_bytebuffer(12, 1); }

  public static int createEngine(FlatBufferBuilder builder,
      long capacity,
      long numCylinders,
      long maxRpm,
      int manufacturerCode,
      int fuel) {
    builder.startObject(5);
    Engine.addFuel(builder, fuel);
    Engine.addManufacturerCode(builder, manufacturerCode);
    Engine.addMaxRpm(builder, maxRpm);
    Engine.addNumCylinders(builder, numCylinders);
    Engine.addCapacity(builder, capacity);
    return Engine.endEngine(builder);
  }

  public static void startEngine(FlatBufferBuilder builder) { builder.startObject(5); }
  public static void addCapacity(FlatBufferBuilder builder, long capacity) { builder.addInt(0, (int)(capacity & 0xFFFFFFFFL), 0); }
  public static void addNumCylinders(FlatBufferBuilder builder, long numCylinders) { builder.addInt(1, (int)(numCylinders & 0xFFFFFFFFL), 0); }
  public static void addMaxRpm(FlatBufferBuilder builder, long maxRpm) { builder.addInt(2, (int)(maxRpm & 0xFFFFFFFFL), 9000); }
  public static void addManufacturerCode(FlatBufferBuilder builder, int manufacturerCodeOffset) { builder.addOffset(3, manufacturerCodeOffset, 0); }
  public static void addFuel(FlatBufferBuilder builder, int fuelOffset) { builder.addOffset(4, fuelOffset, 0); }
  public static int endEngine(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
};

