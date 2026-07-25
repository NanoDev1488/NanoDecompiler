// исходный (обфусцированный) внутренний класс: jnr.ffi.Struct.Padding
package jnr.ffi;

import jnr.ffi.NativeType;
import jnr.ffi.Runtime;
import jnr.ffi.Struct;
import jnr.ffi.Struct_AbstractMember;
import jnr.ffi.Type;

public final class Struct_Padding extends Struct_AbstractMember {

    // ---- поля ----
  final Struct this$0;

  public Struct_Padding(Struct arg0, Type arg1, int arg2) { // было: <init>
        super(arg0, arg1.size() * 8 * arg2, arg1.alignment() * 8);
        this$0 = arg0;
    }

  public Struct_Padding(Struct arg0, NativeType arg1, int arg2) { // было: <init>
        super(arg0, arg0.getRuntime().findType(arg1).size() * 8 * arg2, arg0.getRuntime().findType(arg1).alignment() * 8);
        this$0 = arg0;
    }

}