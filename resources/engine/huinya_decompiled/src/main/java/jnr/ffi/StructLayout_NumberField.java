// исходный (обфусцированный) внутренний класс: jnr.ffi.StructLayout.NumberField
package jnr.ffi;

import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_Field;
import jnr.ffi.StructLayout_Offset;
import jnr.ffi.Type;

public abstract class StructLayout_NumberField extends StructLayout_Field {

    // ---- поля ----
  protected final Type type;
  final StructLayout this$0;

  protected StructLayout_NumberField(StructLayout arg0, NativeType arg1) { // было: <init>
        this(arg0, arg0.getRuntime().findType(arg1));
    }

  protected StructLayout_NumberField(StructLayout arg0, Type arg1) { // было: <init>
        super(arg0, arg0.addField(arg1));
        this$0 = arg0;
        type = arg1;
    }

  protected StructLayout_NumberField(StructLayout arg0, NativeType arg1, StructLayout_Offset arg2) { // было: <init>
        this(arg0, arg0.getRuntime().findType(arg1), arg2);
    }

  protected StructLayout_NumberField(StructLayout arg0, Type arg1, StructLayout_Offset arg2) { // было: <init>
        super(arg0, arg0.addField(arg1, arg2));
        this$0 = arg0;
        type = arg1;
    }

  public abstract void set(Pointer arg0, Number arg1);

  public double doubleValue(Pointer arg0) {
        return ((double) longValue(arg0));
    }

  public float floatValue(Pointer arg0) {
        return ((float) intValue(arg0));
    }

  public byte byteValue(Pointer arg0) {
        return ((byte) intValue(arg0));
    }

  public short shortValue(Pointer arg0) {
        return ((short) intValue(arg0));
    }

  public abstract int intValue(Pointer arg0);

  public long longValue(Pointer arg0) {
        return ((long) intValue(arg0));
    }

  public String toString(Pointer arg0) {
        return Integer.toString(intValue(arg0), 10);
    }

}