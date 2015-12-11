// automatically generated, do not modify

package CompanyNamespaceWhatever;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class GameDataWhatever extends Table {
  public static GameDataWhatever getRootAsGameDataWhatever(ByteBuffer _bb) { return getRootAsGameDataWhatever(_bb, new GameDataWhatever()); }
  public static GameDataWhatever getRootAsGameDataWhatever(ByteBuffer _bb, GameDataWhatever obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__init(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public static boolean GameDataWhateverBufferHasIdentifier(ByteBuffer _bb) { return __has_identifier(_bb, "WHAT"); }
  public GameDataWhatever __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public Vec3 pos() { return pos(new Vec3()); }
  public Vec3 pos(Vec3 obj) { int o = __offset(4); return o != 0 ? obj.__init(o + bb_pos, bb) : null; }
  public short mana() { int o = __offset(6); return o != 0 ? bb.getShort(o + bb_pos) : 150; }
  public short hp() { int o = __offset(8); return o != 0 ? bb.getShort(o + bb_pos) : 100; }
  public String name() { int o = __offset(10); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer nameAsByteBuffer() { return __vector_as_bytebuffer(10, 1); }
  public int inventory(int j) { int o = __offset(12); return o != 0 ? bb.get(__vector(o) + j * 1) & 0xFF : 0; }
  public int inventoryLength() { int o = __offset(12); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer inventoryAsByteBuffer() { return __vector_as_bytebuffer(12, 1); }
  public byte color() { int o = __offset(14); return o != 0 ? bb.get(o + bb_pos) : 3; }
  public byte weaponType() { int o = __offset(16); return o != 0 ? bb.get(o + bb_pos) : 0; }
  public Table weapon(Table obj) { int o = __offset(18); return o != 0 ? __union(obj, o) : null; }

  public static void startGameDataWhatever(FlatBufferBuilder builder) { builder.startObject(8); }
  public static void addPos(FlatBufferBuilder builder, int posOffset) { builder.addStruct(0, posOffset, 0); }
  public static void addMana(FlatBufferBuilder builder, short mana) { builder.addShort(1, mana, 150); }
  public static void addHp(FlatBufferBuilder builder, short hp) { builder.addShort(2, hp, 100); }
  public static void addName(FlatBufferBuilder builder, int nameOffset) { builder.addOffset(3, nameOffset, 0); }
  public static void addInventory(FlatBufferBuilder builder, int inventoryOffset) { builder.addOffset(4, inventoryOffset, 0); }
  public static int createInventoryVector(FlatBufferBuilder builder, byte[] data) { builder.startVector(1, data.length, 1); for (int i = data.length - 1; i >= 0; i--) builder.addByte(data[i]); return builder.endVector(); }
  public static void startInventoryVector(FlatBufferBuilder builder, int numElems) { builder.startVector(1, numElems, 1); }
  public static void addColor(FlatBufferBuilder builder, byte color) { builder.addByte(5, color, 3); }
  public static void addWeaponType(FlatBufferBuilder builder, byte weaponType) { builder.addByte(6, weaponType, 0); }
  public static void addWeapon(FlatBufferBuilder builder, int weaponOffset) { builder.addOffset(7, weaponOffset, 0); }
  public static int endGameDataWhatever(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
  public static void finishGameDataWhateverBuffer(FlatBufferBuilder builder, int offset) { builder.finish(offset, "WHAT"); }
};

