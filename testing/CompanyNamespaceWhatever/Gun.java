// automatically generated, do not modify

package CompanyNamespaceWhatever;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class Gun extends Table {
  public static Gun getRootAsGun(ByteBuffer _bb) { return getRootAsGun(_bb, new Gun()); }
  public static Gun getRootAsGun(ByteBuffer _bb, Gun obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__init(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public Gun __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public int damage() { int o = __offset(4); return o != 0 ? bb.getInt(o + bb_pos) : 500; }
  public short reloadspeed() { int o = __offset(6); return o != 0 ? bb.getShort(o + bb_pos) : 2; }

  public static int createGun(FlatBufferBuilder builder,
      int damage,
      short reloadspeed) {
    builder.startObject(2);
    Gun.addDamage(builder, damage);
    Gun.addReloadspeed(builder, reloadspeed);
    return Gun.endGun(builder);
  }

  public static void startGun(FlatBufferBuilder builder) { builder.startObject(2); }
  public static void addDamage(FlatBufferBuilder builder, int damage) { builder.addInt(0, damage, 500); }
  public static void addReloadspeed(FlatBufferBuilder builder, short reloadspeed) { builder.addShort(1, reloadspeed, 2); }
  public static int endGun(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
};

