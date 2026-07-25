// исходный (обфусцированный) внутренний класс: jnr.ffi.Struct.AsciiStringRef
package jnr.ffi;

import jnr.ffi.Struct;
import jnr.ffi.Struct_UTFStringRef;

public class Struct_AsciiStringRef extends Struct_UTFStringRef {

    // ---- поля ----
  final Struct this$0;

  public Struct_AsciiStringRef(Struct arg0, int arg1) { // было: <init>
        super(arg0, arg1, Struct.ASCII);
        this$0 = arg0;
    }

  public Struct_AsciiStringRef(Struct arg0) { // было: <init>
        super(arg0, 2147483647, Struct.ASCII);
        this$0 = arg0;
    }

}