// исходный (обфусцированный) внутренний класс: jnr.ffi.Struct.Pointer
package jnr.ffi;

import jnr.ffi.Pointer;
import jnr.ffi.Struct;
import jnr.ffi.Struct_Offset;
import jnr.ffi.Struct_PointerField;

public class Struct_Pointer extends Struct_PointerField {

    // ---- поля ----
  final Struct this$0;

  public Struct_Pointer(Struct arg0) { // было: <init>
        super(arg0);
        this$0 = arg0;
    }

  public Struct_Pointer(Struct arg0, Struct_Offset arg1) { // было: <init>
        super(arg0, arg1);
        this$0 = arg0;
    }

  public final Pointer get() {
        return getPointer();
    }

  public final int intValue() {
        return super.intValue();
    }

  public final long longValue() {
        return super.longValue();
    }

  public final String toString() {
        return super.toString();
    }

}