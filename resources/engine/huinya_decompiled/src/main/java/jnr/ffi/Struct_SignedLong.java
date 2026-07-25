// исходный (обфусцированный) внутренний класс: jnr.ffi.Struct.SignedLong
package jnr.ffi;

import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.Struct;
import jnr.ffi.Struct_NumberField;
import jnr.ffi.Struct_Offset;

public class Struct_SignedLong extends Struct_NumberField {

    // ---- поля ----
  final Struct this$0;

  public Struct_SignedLong(Struct arg0) { // было: <init>
        super(arg0, NativeType.SLONG);
        this$0 = arg0;
    }

  public Struct_SignedLong(Struct arg0, Struct_Offset arg1) { // было: <init>
        super(arg0, NativeType.SLONG, arg1);
        this$0 = arg0;
    }

  public final long get() {
        return getMemory().getNativeLong(offset());
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