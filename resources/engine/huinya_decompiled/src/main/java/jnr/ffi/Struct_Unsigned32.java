// исходный (обфусцированный) внутренний класс: jnr.ffi.Struct.Unsigned32
package jnr.ffi;

import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.Struct;
import jnr.ffi.Struct_NumberField;
import jnr.ffi.Struct_Offset;

public class Struct_Unsigned32 extends Struct_NumberField {

    // ---- поля ----
  final Struct this$0;

  public Struct_Unsigned32(Struct arg0) { // было: <init>
        super(arg0, NativeType.UINT);
        this$0 = arg0;
    }

  public Struct_Unsigned32(Struct arg0, Struct_Offset arg1) { // было: <init>
        super(arg0, NativeType.UINT, arg1);
        this$0 = arg0;
    }

  public final long get() {
        long var1 = ((long) getMemory().getInt(offset()));
        return var1 >= 0L ? var1 : (var1 & 2147483647L) + 2147483648L;
    }

  public final void set(long arg0) {
        getMemory().putInt(offset(), ((int) arg0));
    }

  public void set(Number arg0) {
        getMemory().putInt(offset(), arg0.intValue());
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