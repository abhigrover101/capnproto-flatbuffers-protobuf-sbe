// automatically generated, do not modify

package CompanyNamespaceWhatever;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class Sword extends Table {
  public static Sword getRootAsSword(ByteBuffer _bb) { return getRootAsSword(_bb, new Sword()); }
  public static Sword getRootAsSword(ByteBuffer _bb, Sword obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__init(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public Sword __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public int damage() { int o = __offset(4); return o != 0 ? bb.getInt(o + bb_pos) : 10; }
  public short distance() { int o = __offset(6); return o != 0 ? bb.getShort(o + bb_pos) : 5; }

  public static int createSword(FlatBufferBuilder builder,
      int damage,
      short distance) {
    builder.startObject(2);
    Sword.addDamage(builder, damage);
    Sword.addDistance(builder, distance);
    return Sword.endSword(builder);
  }

  public static void startSword(FlatBufferBuilder builder) { builder.startObject(2); }
  public static void addDamage(FlatBufferBuilder builder, int damage) { builder.addInt(0, damage, 10); }
  public static void addDistance(FlatBufferBuilder builder, short distance) { builder.addShort(1, distance, 5); }
  public static int endSword(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
};

