// исходный (обфусцированный) внутренний класс: jnr.ffi.Struct.UnsignedLong
package jnr.ffi;

import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.Struct;
import jnr.ffi.Struct_NumberField;
import jnr.ffi.Struct_Offset;
import jnr.ffi.Type;

public class Struct_UnsignedLong extends Struct_NumberField {

    // ---- поля ----
  final Struct this$0;

  public Struct_UnsignedLong(Struct arg0) { // было: <init>
        super(arg0, NativeType.ULONG);
        this$0 = arg0;
    }

  public Struct_UnsignedLong(Struct arg0, Struct_Offset arg1) { // было: <init>
        super(arg0, NativeType.ULONG, arg1);
        this$0 = arg0;
    }

  public final long get() {
        long __stk1;
        long var1 = getMemory().getNativeLong(offset());
        __stk1 = this$0.getRuntime().findType(NativeType.SLONG).size() != 32 ? -1L : 4294967295L;
        long var3 = __stk1;
        return var1 >= 0L ? var1 : (var1 & var3) + var3 + 1L;
    }

  public final void set(long arg0) {
        getMemory().putNativeLong(offset(), arg0);
    }

  public void set(Number arg0) {
        getMemory().putNativeLong(offset(), arg0.longValue());
    }

  public final int intValue() {
        return ((int) get());
    }

  public final long longValue() {
        return get();
    }

  public final String toString() {
        return Long.toString(get());
    }

}