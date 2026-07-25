// исходный (обфусцированный) внутренний класс: jnr.ffi.Struct.IntegerAlias
package jnr.ffi;

import jnr.ffi.Pointer;
import jnr.ffi.Struct;
import jnr.ffi.Struct_NumberField;
import jnr.ffi.Struct_Offset;
import jnr.ffi.TypeAlias;

public abstract class Struct_IntegerAlias extends Struct_NumberField {

    // ---- поля ----
  final Struct this$0;

   Struct_IntegerAlias(Struct arg0, TypeAlias arg1) { // было: <init>
        super(arg0, arg1);
        this$0 = arg0;
    }

   Struct_IntegerAlias(Struct arg0, TypeAlias arg1, Struct_Offset arg2) { // было: <init>
        super(arg0, arg1, arg2);
        this$0 = arg0;
    }

  public void set(Number arg0) {
        getMemory().putInt(type, offset(), arg0.longValue());
    }

  public void set(long arg0) {
        getMemory().putInt(type, offset(), arg0);
    }

  public final long get() {
        return getMemory().getInt(type, offset());
    }

  public int intValue() {
        return ((int) get());
    }

  public long longValue() {
        return get();
    }

  public final String toString() {
        return Long.toString(get());
    }

}