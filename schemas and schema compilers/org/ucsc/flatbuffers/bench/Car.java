// automatically generated, do not modify

package org.ucsc.flatbuffers.bench;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

public final class Car extends Table {
  public static Car getRootAsCar(ByteBuffer _bb) { return getRootAsCar(_bb, new Car()); }
  public static Car getRootAsCar(ByteBuffer _bb, Car obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__init(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public static boolean CarBufferHasIdentifier(ByteBuffer _bb) { return __has_identifier(_bb, "MONS"); }
  public Car __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public long serialNumber() { int o = __offset(4); return o != 0 ? (long)bb.getInt(o + bb_pos) & 0xFFFFFFFFL : 0; }
  public boolean mutateSerialNumber(long serialNumber) { int o = __offset(4); if (o != 0) { bb.putInt(o + bb_pos, (int)serialNumber); return true; } else { return false; } }
  public int available() { int o = __offset(6); return o != 0 ? bb.get(o + bb_pos) & 0xFF : 0; }
  public boolean mutateAvailable(int available) { int o = __offset(6); if (o != 0) { bb.put(o + bb_pos, (byte)available); return true; } else { return false; } }
  public int modelYear() { int o = __offset(8); return o != 0 ? bb.getShort(o + bb_pos) & 0xFFFF : 0; }
  public boolean mutateModelYear(int modelYear) { int o = __offset(8); if (o != 0) { bb.putShort(o + bb_pos, (short)modelYear); return true; } else { return false; } }
  public String model() { int o = __offset(10); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer modelAsByteBuffer() { return __vector_as_bytebuffer(10, 1); }
  public String code() { int o = __offset(12); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer codeAsByteBuffer() { return __vector_as_bytebuffer(12, 1); }
  public int someNumbers(int j) { int o = __offset(14); return o != 0 ? bb.getInt(__vector(o) + j * 4) : 0; }
  public int someNumbersLength() { int o = __offset(14); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer someNumbersAsByteBuffer() { return __vector_as_bytebuffer(14, 4); }
  public boolean mutateSomeNumbers(int j, int someNumbers) { int o = __offset(14); if (o != 0) { bb.putInt(__vector(o) + j * 4, someNumbers); return true; } else { return false; } }
  public String vehicleCode() { int o = __offset(16); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer vehicleCodeAsByteBuffer() { return __vector_as_bytebuffer(16, 1); }
  public short extras(int j) { int o = __offset(18); return o != 0 ? bb.getShort(__vector(o) + j * 2) : 0; }
  public int extrasLength() { int o = __offset(18); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer extrasAsByteBuffer() { return __vector_as_bytebuffer(18, 2); }
  public boolean mutateExtras(int j, short extras) { int o = __offset(18); if (o != 0) { bb.putShort(__vector(o) + j * 2, extras); return true; } else { return false; } }
  public Engine engine() { return engine(new Engine()); }
  public Engine engine(Engine obj) { int o = __offset(20); return o != 0 ? obj.__init(__indirect(o + bb_pos), bb) : null; }
  public String make() { int o = __offset(22); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer makeAsByteBuffer() { return __vector_as_bytebuffer(22, 1); }
  public FuelFigures fuelfigure(int j) { return fuelfigure(new FuelFigures(), j); }
  public FuelFigures fuelfigure(FuelFigures obj, int j) { int o = __offset(24); return o != 0 ? obj.__init(__vector(o) + j * 8, bb) : null; }
  public int fuelfigureLength() { int o = __offset(24); return o != 0 ? __vector_len(o) : 0; }
  public PerformanceFigures performance(int j) { return performance(new PerformanceFigures(), j); }
  public PerformanceFigures performance(PerformanceFigures obj, int j) { int o = __offset(26); return o != 0 ? obj.__init(__indirect(__vector(o) + j * 4), bb) : null; }
  public int performanceLength() { int o = __offset(26); return o != 0 ? __vector_len(o) : 0; }

  public static int createCar(FlatBufferBuilder builder,
      long serialNumber,
      int available,
      int modelYear,
      int model,
      int code,
      int someNumbers,
      int vehicleCode,
      int extras,
      int engine,
      int make,
      int fuelfigure,
      int performance) {
    builder.startObject(12);
    Car.addPerformance(builder, performance);
    Car.addFuelfigure(builder, fuelfigure);
    Car.addMake(builder, make);
    Car.addEngine(builder, engine);
    Car.addExtras(builder, extras);
    Car.addVehicleCode(builder, vehicleCode);
    Car.addSomeNumbers(builder, someNumbers);
    Car.addCode(builder, code);
    Car.addModel(builder, model);
    Car.addSerialNumber(builder, serialNumber);
    Car.addModelYear(builder, modelYear);
    Car.addAvailable(builder, available);
    return Car.endCar(builder);
  }

  public static void startCar(FlatBufferBuilder builder) { builder.startObject(12); }
  public static void addSerialNumber(FlatBufferBuilder builder, long serialNumber) { builder.addInt(0, (int)(serialNumber & 0xFFFFFFFFL), 0); }
  public static void addAvailable(FlatBufferBuilder builder, int available) { builder.addByte(1, (byte)(available & 0xFF), 0); }
  public static void addModelYear(FlatBufferBuilder builder, int modelYear) { builder.addShort(2, (short)(modelYear & 0xFFFF), 0); }
  public static void addModel(FlatBufferBuilder builder, int modelOffset) { builder.addOffset(3, modelOffset, 0); }
  public static void addCode(FlatBufferBuilder builder, int codeOffset) { builder.addOffset(4, codeOffset, 0); }
  public static void addSomeNumbers(FlatBufferBuilder builder, int someNumbersOffset) { builder.addOffset(5, someNumbersOffset, 0); }
  public static int createSomeNumbersVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addInt(data[i]); return builder.endVector(); }
  public static void startSomeNumbersVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addVehicleCode(FlatBufferBuilder builder, int vehicleCodeOffset) { builder.addOffset(6, vehicleCodeOffset, 0); }
  public static void addExtras(FlatBufferBuilder builder, int extrasOffset) { builder.addOffset(7, extrasOffset, 0); }
  public static int createExtrasVector(FlatBufferBuilder builder, short[] data) { builder.startVector(2, data.length, 2); for (int i = data.length - 1; i >= 0; i--) builder.addShort(data[i]); return builder.endVector(); }
  public static void startExtrasVector(FlatBufferBuilder builder, int numElems) { builder.startVector(2, numElems, 2); }
  public static void addEngine(FlatBufferBuilder builder, int engineOffset) { builder.addOffset(8, engineOffset, 0); }
  public static void addMake(FlatBufferBuilder builder, int makeOffset) { builder.addOffset(9, makeOffset, 0); }
  public static void addFuelfigure(FlatBufferBuilder builder, int fuelfigureOffset) { builder.addOffset(10, fuelfigureOffset, 0); }
  public static void startFuelfigureVector(FlatBufferBuilder builder, int numElems) { builder.startVector(8, numElems, 4); }
  public static void addPerformance(FlatBufferBuilder builder, int performanceOffset) { builder.addOffset(11, performanceOffset, 0); }
  public static int createPerformanceVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startPerformanceVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static int endCar(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
  public static void finishCarBuffer(FlatBufferBuilder builder, int offset) { builder.finish(offset, "MONS"); }
};

