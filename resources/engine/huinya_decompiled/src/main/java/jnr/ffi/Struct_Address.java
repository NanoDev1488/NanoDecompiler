// исходный (обфусцированный) внутренний класс: jnr.ffi.Struct.Address
package jnr.ffi;

import jnr.ffi.Address;
import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.Struct;
import jnr.ffi.Struct_NumberField;
import jnr.ffi.Struct_Offset;

public class Struct_Address extends Struct_NumberField {

    // ---- поля ----
  final Struct this$0;

  public Struct_Address(Struct arg0) { // было: <init>
        super(arg0, NativeType.ADDRESS);
        this$0 = arg0;
    }

  public Struct_Address(Struct arg0, Struct_Offset arg1) { // было: <init>
        super(arg0, NativeType.ADDRESS, arg1);
        this$0 = arg0;
    }

  public final Address get() {
        return Address.valueOf(getMemory().getAddress(offset()));
    }

  public final void set(Address arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokevirtual  #20 // jnr.ffi.Struct$Address.getMemory:()Ljnr/ffi/Pointer;
        //      4: aload_0
        //      5: invokevirtual  #21 // jnr.ffi.Struct$Address.offset:()J
        //      8: aload_1
        //      9: ifnull  19 (offset +10)
        //     12: aload_1
        //     13: invokevirtual  #14 // jnr.ffi.Address.nativeAddress:()J
        //     16: goto  20 (offset +4)
        //     19: lconst_0
        //     20: invokevirtual  #18 // jnr.ffi.Pointer.putAddress:(JJ)V
        //     23: return
    }

  public void set(Number arg0) {
        getMemory().putAddress(offset(), arg0.longValue());
    }

  public final int intValue() {
        return get().intValue();
    }

  public final long longValue() {
        return get().longValue();
    }

  public final String toString() {
        return get().toString();
    }

}