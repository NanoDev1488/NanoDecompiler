// исходный (обфусцированный) внутренний класс: com.kenai.jffi.Type
package com.kenai.jffi;

import com.kenai.jffi.NativeType;
import com.kenai.jffi.Type_Builtin;
import com.kenai.jffi.Type_TypeInfo;
import java.util.List;

public abstract class Type {

    // ---- поля ----
  public static final Type VOID;
  public static final Type FLOAT;
  public static final Type DOUBLE;
  public static final Type LONGDOUBLE;
  public static final Type UINT8;
  public static final Type SINT8;
  public static final Type UINT16;
  public static final Type SINT16;
  public static final Type UINT32;
  public static final Type SINT32;
  public static final Type UINT64;
  public static final Type SINT64;
  public static final Type POINTER;
  public static final Type UCHAR;
  public static final Type SCHAR;
  public static final Type USHORT;
  public static final Type SSHORT;
  public static final Type UINT;
  public static final Type SINT;
  public static final Type ULONG;
  public static final Type SLONG;
  public static final Type ULONG_LONG;
  public static final Type SLONG_LONG;
  private int type;
  private int size;
  private int alignment;
  private volatile long handle;

    static {
        VOID = builtin(NativeType.VOID);
        FLOAT = builtin(NativeType.FLOAT);
        DOUBLE = builtin(NativeType.DOUBLE);
        LONGDOUBLE = builtin(NativeType.LONGDOUBLE);
        UINT8 = builtin(NativeType.UINT8);
        SINT8 = builtin(NativeType.SINT8);
        UINT16 = builtin(NativeType.UINT16);
        SINT16 = builtin(NativeType.SINT16);
        UINT32 = builtin(NativeType.UINT32);
        SINT32 = builtin(NativeType.SINT32);
        UINT64 = builtin(NativeType.UINT64);
        SINT64 = builtin(NativeType.SINT64);
        POINTER = builtin(NativeType.POINTER);
        UCHAR = UINT8;
        SCHAR = SINT8;
        USHORT = UINT16;
        SSHORT = SINT16;
        UINT = UINT32;
        SINT = SINT32;
        ULONG = builtin(NativeType.ULONG);
        SLONG = builtin(NativeType.SLONG);
        ULONG_LONG = UINT64;
        SLONG_LONG = SINT64;
    }

  public Type() { // было: <init>
        super();
        type = 0;
        size = 0;
        alignment = 0;
        handle = 0L;
    }

  public final int type() {
        return type == 0 ? resolveType() : type;
    }

  final long handle() {
        return handle == 0L ? resolveHandle() : handle;
    }

  public final int size() {
        return size == 0 ? resolveSize() : size;
    }

  public final int alignment() {
        return alignment == 0 ? resolveAlignment() : alignment;
    }

  private int resolveType() {
        type = getTypeInfo().type;
        return getTypeInfo().type;
    }

  private int resolveSize() {
        size = getTypeInfo().size;
        return getTypeInfo().size;
    }

  private int resolveAlignment() {
        alignment = getTypeInfo().alignment;
        return getTypeInfo().alignment;
    }

  private long resolveHandle() {
        handle = getTypeInfo().handle;
        return getTypeInfo().handle;
    }

  abstract Type_TypeInfo getTypeInfo();

  public boolean equals(Object arg0) {
        return !(arg0 instanceof Type) ? 0 : (((Type) arg0)).handle() == handle();
    }

  public int hashCode() {
        int var1 = 3;
        var1 = 67 * var1 + ((int) (handle() ^ handle() >>> 32));
        return var1;
    }

  static long[] nativeHandles(Type[] arg0) {
        long[] var1 = new long[arg0.length];
        int var2 = 0;
        while (var2 < arg0.length) {
            var1[var2] = arg0[var2].handle();
            ++var2;
            continue;
        }
        return var1;
    }

  static long[] nativeHandles(List arg0) {
        long[] var1 = new long[arg0.size()];
        int var2 = 0;
        while (var2 < var1.length) {
            var1[var2] = (((Type) arg0.get(var2))).handle();
            ++var2;
            continue;
        }
        return var1;
    }

  private static Type builtin(NativeType arg0) {
        return new Type_Builtin(arg0, null);
    }

}