// исходный (обфусцированный) внутренний класс: jnr.ffi.Struct.PointerField
package jnr.ffi;

import jnr.ffi.Memory;
import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.Struct;
import jnr.ffi.Struct_NumberField;
import jnr.ffi.Struct_Offset;
import jnr.ffi.Type;
import jnr.ffi.provider.jffi.ArrayMemoryIO;

public abstract class Struct_PointerField extends Struct_NumberField {

    // ---- поля ----
  private Pointer finalPointer;
  final Struct this$0;

  public Struct_PointerField(Struct arg0) { // было: <init>
        super(arg0, NativeType.ADDRESS);
        this$0 = arg0;
    }

  public Struct_PointerField(Struct arg0, Struct_Offset arg1) { // было: <init>
        super(arg0, NativeType.ADDRESS, arg1);
        this$0 = arg0;
    }

  protected final Pointer getPointer() {
        return getMemory().getPointer(offset());
    }

  public final int size() {
        return this$0.getRuntime().findType(NativeType.ADDRESS).size() * 8;
    }

  public final void set(Pointer arg0) {
        finalPointer = arg0;
        if (arg0 instanceof ArrayMemoryIO) {
            ArrayMemoryIO var2 = ((ArrayMemoryIO) arg0);
            byte[] var3 = var2.array();
            finalPointer = Memory.allocateDirect(this$0.getRuntime(), var3.length);
            finalPointer.put(0L, var3, 0, var3.length);
        }
        getMemory().putPointer(offset(), finalPointer);
    }

  public void set(Number arg0) {
        getMemory().putAddress(offset(), arg0.longValue());
    }

  public int intValue() {
        return ((int) getMemory().getAddress(offset()));
    }

  public long longValue() {
        return getMemory().getAddress(offset());
    }

  public String toString() {
        return getPointer().toString();
    }

}