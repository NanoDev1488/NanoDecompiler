// исходный (обфусцированный) внутренний класс: jnr.ffi.Struct.Signed64
package jnr.ffi;

import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.Struct;
import jnr.ffi.Struct_NumberField;
import jnr.ffi.Struct_Offset;

public class Struct_Signed64 extends Struct_NumberField {

    // ---- поля ----
  final Struct this$0;

  public Struct_Signed64(Struct arg0) { // было: <init>
        super(arg0, NativeType.SLONGLONG);
        this$0 = arg0;
    }

  public Struct_Signed64(Struct arg0, Struct_Offset arg1) { // было: <init>
        super(arg0, NativeType.SLONGLONG, arg1);
        this$0 = arg0;
    }

  public final long get() {
        return getMemory().getLongLong(offset());
    }

  public final void set(long arg0) {
        getMemory().putLongLong(offset(), arg0);
    }

  public void set(Number arg0) {
        getMemory().putLongLong(offset(), arg0.longValue());
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