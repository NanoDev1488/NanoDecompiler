// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.platform.i386.windows.TypeAliases
package jnr.ffi.provider.jffi.platform.i386.windows;

import java.util.EnumMap;
import java.util.Map;
import jnr.ffi.NativeType;
import jnr.ffi.TypeAlias;

public final class TypeAliases {

    // ---- поля ----
  public static final Map ALIASES;

    static {
        ALIASES = buildTypeMap();
    }

  public TypeAliases() { // было: <init>
        super();
    }

  private static Map buildTypeMap() {
        EnumMap var0 = new EnumMap(TypeAlias.class);
        var0.put(TypeAlias.int8_t, NativeType.SCHAR);
        var0.put(TypeAlias.u_int8_t, NativeType.UCHAR);
        var0.put(TypeAlias.int16_t, NativeType.SSHORT);
        var0.put(TypeAlias.u_int16_t, NativeType.USHORT);
        var0.put(TypeAlias.int32_t, NativeType.SLONG);
        var0.put(TypeAlias.u_int32_t, NativeType.UINT);
        var0.put(TypeAlias.int64_t, NativeType.SLONGLONG);
        var0.put(TypeAlias.u_int64_t, NativeType.ULONGLONG);
        var0.put(TypeAlias.intptr_t, NativeType.SLONG);
        var0.put(TypeAlias.uintptr_t, NativeType.ULONG);
        var0.put(TypeAlias.caddr_t, NativeType.ADDRESS);
        var0.put(TypeAlias.dev_t, NativeType.ULONG);
        var0.put(TypeAlias.blkcnt_t, NativeType.SLONGLONG);
        var0.put(TypeAlias.blksize_t, NativeType.SLONG);
        var0.put(TypeAlias.gid_t, NativeType.ULONG);
        var0.put(TypeAlias.in_addr_t, NativeType.UINT);
        var0.put(TypeAlias.in_port_t, NativeType.USHORT);
        var0.put(TypeAlias.ino_t, NativeType.ULONGLONG);
        var0.put(TypeAlias.ino64_t, NativeType.ULONGLONG);
        var0.put(TypeAlias.key_t, NativeType.SLONGLONG);
        var0.put(TypeAlias.mode_t, NativeType.USHORT);
        var0.put(TypeAlias.nlink_t, NativeType.USHORT);
        var0.put(TypeAlias.id_t, NativeType.ULONG);
        var0.put(TypeAlias.pid_t, NativeType.SINT);
        var0.put(TypeAlias.off_t, NativeType.SLONGLONG);
        var0.put(TypeAlias.swblk_t, NativeType.SLONG);
        var0.put(TypeAlias.uid_t, NativeType.ULONG);
        var0.put(TypeAlias.clock_t, NativeType.ULONG);
        var0.put(TypeAlias.size_t, NativeType.UINT);
        var0.put(TypeAlias.ssize_t, NativeType.SINT);
        var0.put(TypeAlias.time_t, NativeType.SLONG);
        var0.put(TypeAlias.fsblkcnt_t, NativeType.ULONG);
        var0.put(TypeAlias.fsfilcnt_t, NativeType.ULONG);
        var0.put(TypeAlias.sa_family_t, NativeType.USHORT);
        var0.put(TypeAlias.socklen_t, NativeType.SINT);
        var0.put(TypeAlias.rlim_t, NativeType.ULONG);
        var0.put(TypeAlias.cc_t, NativeType.UCHAR);
        var0.put(TypeAlias.speed_t, NativeType.UINT);
        var0.put(TypeAlias.tcflag_t, NativeType.UINT);
        return var0;
    }

}