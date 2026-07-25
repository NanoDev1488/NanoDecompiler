// исходный (обфусцированный) внутренний класс: jnr.ffi.Struct.NumberField
package jnr.ffi;

import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.Struct;
import jnr.ffi.Struct_Info;
import jnr.ffi.Struct_Member;
import jnr.ffi.Struct_Offset;
import jnr.ffi.Type;
import jnr.ffi.TypeAlias;

public abstract class Struct_NumberField extends Struct_Member {

    // ---- поля ----
  private final int offset;
  protected final Type type;
  final Struct this$0;

  protected Struct_NumberField(Struct arg0, NativeType arg1) { // было: <init>
        super(arg0);
        this$0 = arg0;
        type = arg0.getRuntime().findType(arg1);
        Type var3 = arg0.getRuntime().findType(arg1);
        offset = arg0.__info.addField(var3.size() * 8, var3.alignment() * 8);
    }

  protected Struct_NumberField(Struct arg0, NativeType arg1, Struct_Offset arg2) { // было: <init>
        super(arg0);
        this$0 = arg0;
        type = arg0.getRuntime().findType(arg1);
        Type var4 = arg0.getRuntime().findType(arg1);
        offset = arg0.__info.addField(var4.size() * 8, var4.alignment() * 8, arg2);
    }

  protected Struct_NumberField(Struct arg0, TypeAlias arg1) { // было: <init>
        super(arg0);
        this$0 = arg0;
        type = arg0.getRuntime().findType(arg1);
        Type var3 = arg0.getRuntime().findType(arg1);
        offset = arg0.__info.addField(var3.size() * 8, var3.alignment() * 8);
    }

  protected Struct_NumberField(Struct arg0, TypeAlias arg1, Struct_Offset arg2) { // было: <init>
        super(arg0);
        this$0 = arg0;
        type = arg0.getRuntime().findType(arg1);
        Type var4 = arg0.getRuntime().findType(arg1);
        offset = arg0.__info.addField(var4.size() * 8, var4.alignment() * 8, arg2);
    }

  public final Pointer getMemory() {
        return this$0.__info.getMemory();
    }

  public final Struct struct() {
        return this$0;
    }

  public final long offset() {
        return ((long) (offset + this$0.__info.getOffset()));
    }

  public abstract void set(Number arg0);

  public double doubleValue() {
        return ((double) longValue());
    }

  public float floatValue() {
        return ((float) intValue());
    }

  public byte byteValue() {
        return ((byte) intValue());
    }

  public short shortValue() {
        return ((short) intValue());
    }

  public abstract int intValue();

  public long longValue() {
        return ((long) intValue());
    }

  public String toString() {
        return Integer.toString(intValue(), 10);
    }

}