// automatically generated, do not modify

package uk.co.real_logic.protobuf.examples;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

public final class Car extends Table {
  public static Car getRootAsCar(ByteBuffer _bb) { return getRootAsCar(_bb, new Car()); }
  public static Car getRootAsCar(ByteBuffer _bb, Car obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__init(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public Car __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public long serialNumber() { int o = __offset(4); return o != 0 ? (long)bb.getInt(o + bb_pos) & 0xFFFFFFFFL : 0; }
  public long modelYear() { int o = __offset(6); return o != 0 ? (long)bb.getInt(o + bb_pos) & 0xFFFFFFFFL : 0; }
  public boolean available() { int o = __offset(8); return o != 0 ? 0!=bb.get(o + bb_pos) : false; }
  public short code() { int o = __offset(10); return o != 0 ? bb.getShort(o + bb_pos) : 0; }
  public int someNumbers(int j) { int o = __offset(12); return o != 0 ? bb.getInt(__vector(o) + j * 4) : 0; }
  public int someNumbersLength() { int o = __offset(12); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer someNumbersAsByteBuffer() { return __vector_as_bytebuffer(12, 4); }
  public String vehicleCode() { int o = __offset(14); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer vehicleCodeAsByteBuffer() { return __vector_as_bytebuffer(14, 1); }
  public short optionalExtras(int j) { int o = __offset(16); return o != 0 ? bb.getShort(__vector(o) + j * 2) : 0; }
  public int optionalExtrasLength() { int o = __offset(16); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer optionalExtrasAsByteBuffer() { return __vector_as_bytebuffer(16, 2); }
  public Engine engine() { return engine(new Engine()); }
  public Engine engine(Engine obj) { int o = __offset(18); return o != 0 ? obj.__init(__indirect(o + bb_pos), bb) : null; }
  public FuelFigures fuelFigures(int j) { return fuelFigures(new FuelFigures(), j); }
  public FuelFigures fuelFigures(FuelFigures obj, int j) { int o = __offset(20); return o != 0 ? obj.__init(__indirect(__vector(o) + j * 4), bb) : null; }
  public int fuelFiguresLength() { int o = __offset(20); return o != 0 ? __vector_len(o) : 0; }
  public PerformanceFigures performance(int j) { return performance(new PerformanceFigures(), j); }
  public PerformanceFigures performance(PerformanceFigures obj, int j) { int o = __offset(22); return o != 0 ? obj.__init(__indirect(__vector(o) + j * 4), bb) : null; }
  public int performanceLength() { int o = __offset(22); return o != 0 ? __vector_len(o) : 0; }
  public String make() { int o = __offset(24); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer makeAsByteBuffer() { return __vector_as_bytebuffer(24, 1); }
  public String model() { int o = __offset(26); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer modelAsByteBuffer() { return __vector_as_bytebuffer(26, 1); }

  public static int createCar(FlatBufferBuilder builder,
      long serialNumber,
      long modelYear,
      boolean available,
      short code,
      int someNumbers,
      int vehicleCode,
      int optionalExtras,
      int engine,
      int fuelFigures,
      int performance,
      int make,
      int model) {
    builder.startObject(12);
    Car.addModel(builder, model);
    Car.addMake(builder, make);
    Car.addPerformance(builder, performance);
    Car.addFuelFigures(builder, fuelFigures);
    Car.addEngine(builder, engine);
    Car.addOptionalExtras(builder, optionalExtras);
    Car.addVehicleCode(builder, vehicleCode);
    Car.addSomeNumbers(builder, someNumbers);
    Car.addModelYear(builder, modelYear);
    Car.addSerialNumber(builder, serialNumber);
    Car.addCode(builder, code);
    Car.addAvailable(builder, available);
    return Car.endCar(builder);
  }

  public static void startCar(FlatBufferBuilder builder) { builder.startObject(12); }
  public static void addSerialNumber(FlatBufferBuilder builder, long serialNumber) { builder.addInt(0, (int)(serialNumber & 0xFFFFFFFFL), 0); }
  public static void addModelYear(FlatBufferBuilder builder, long modelYear) { builder.addInt(1, (int)(modelYear & 0xFFFFFFFFL), 0); }
  public static void addAvailable(FlatBufferBuilder builder, boolean available) { builder.addBoolean(2, available, false); }
  public static void addCode(FlatBufferBuilder builder, short code) { builder.addShort(3, code, 0); }
  public static void addSomeNumbers(FlatBufferBuilder builder, int someNumbersOffset) { builder.addOffset(4, someNumbersOffset, 0); }
  public static int createSomeNumbersVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addInt(data[i]); return builder.endVector(); }
  public static void startSomeNumbersVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addVehicleCode(FlatBufferBuilder builder, int vehicleCodeOffset) { builder.addOffset(5, vehicleCodeOffset, 0); }
  public static void addOptionalExtras(FlatBufferBuilder builder, int optionalExtrasOffset) { builder.addOffset(6, optionalExtrasOffset, 0); }
  public static int createOptionalExtrasVector(FlatBufferBuilder builder, short[] data) { builder.startVector(2, data.length, 2); for (int i = data.length - 1; i >= 0; i--) builder.addShort(data[i]); return builder.endVector(); }
  public static void startOptionalExtrasVector(FlatBufferBuilder builder, int numElems) { builder.startVector(2, numElems, 2); }
  public static void addEngine(FlatBufferBuilder builder, int engineOffset) { builder.addOffset(7, engineOffset, 0); }
  public static void addFuelFigures(FlatBufferBuilder builder, int fuelFiguresOffset) { builder.addOffset(8, fuelFiguresOffset, 0); }
  public static int createFuelFiguresVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startFuelFiguresVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addPerformance(FlatBufferBuilder builder, int performanceOffset) { builder.addOffset(9, performanceOffset, 0); }
  public static int createPerformanceVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startPerformanceVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addMake(FlatBufferBuilder builder, int makeOffset) { builder.addOffset(10, makeOffset, 0); }
  public static void addModel(FlatBufferBuilder builder, int modelOffset) { builder.addOffset(11, modelOffset, 0); }
  public static int endCar(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
};

