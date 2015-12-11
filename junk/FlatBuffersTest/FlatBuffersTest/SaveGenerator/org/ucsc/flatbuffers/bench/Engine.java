// automatically generated, do not modify

package org.ucsc.flatbuffers.bench;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

public final class Engine extends Table {
  public static Engine getRootAsEngine(ByteBuffer _bb) { return getRootAsEngine(_bb, new Engine()); }
  public static Engine getRootAsEngine(ByteBuffer _bb, Engine obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__init(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public Engine __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public int capacity() { int o = __offset(4); return o != 0 ? bb.getShort(o + bb_pos) & 0xFFFF : 0; }
  public boolean mutateCapacity(int capacity) { int o = __offset(4); if (o != 0) { bb.putShort(o + bb_pos, (short)capacity); return true; } else { return false; } }
  public int numCylinders() { int o = __offset(6); return o != 0 ? bb.get(o + bb_pos) & 0xFF : 0; }
  public boolean mutateNumCylinders(int numCylinders) { int o = __offset(6); if (o != 0) { bb.put(o + bb_pos, (byte)numCylinders); return true; } else { return false; } }
  public int maxRpm() { int o = __offset(8); return o != 0 ? bb.getShort(o + bb_pos) & 0xFFFF : 9000; }
  public boolean mutateMaxRpm(int maxRpm) { int o = __offset(8); if (o != 0) { bb.putShort(o + bb_pos, (short)maxRpm); return true; } else { return false; } }
  public String manufacturerCode() { int o = __offset(10); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer manufacturerCodeAsByteBuffer() { return __vector_as_bytebuffer(10, 1); }
  public String fuel() { int o = __offset(12); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer fuelAsByteBuffer() { return __vector_as_bytebuffer(12, 1); }

  public static int createEngine(FlatBufferBuilder builder,
      int capacity,
      int numCylinders,
      int maxRpm,
      int manufacturerCode,
      int fuel) {
    builder.startObject(5);
    Engine.addFuel(builder, fuel);
    Engine.addManufacturerCode(builder, manufacturerCode);
    Engine.addMaxRpm(builder, maxRpm);
    Engine.addCapacity(builder, capacity);
    Engine.addNumCylinders(builder, numCylinders);
    return Engine.endEngine(builder);
  }

  public static void startEngine(FlatBufferBuilder builder) { builder.startObject(5); }
  public static void addCapacity(FlatBufferBuilder builder, int capacity) { builder.addShort(0, (short)(capacity & 0xFFFF), 0); }
  public static void addNumCylinders(FlatBufferBuilder builder, int numCylinders) { builder.addByte(1, (byte)(numCylinders & 0xFF), 0); }
  public static void addMaxRpm(FlatBufferBuilder builder, int maxRpm) { builder.addShort(2, (short)(maxRpm & 0xFFFF), 9000); }
  public static void addManufacturerCode(FlatBufferBuilder builder, int manufacturerCodeOffset) { builder.addOffset(3, manufacturerCodeOffset, 0); }
  public static void addFuel(FlatBufferBuilder builder, int fuelOffset) { builder.addOffset(4, fuelOffset, 0); }
  public static int endEngine(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
};

