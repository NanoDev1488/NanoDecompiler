// исходный (обфусцированный) внутренний класс: jnr.ffi.Struct.UTF8StringRef
package jnr.ffi;

import jnr.ffi.Struct;
import jnr.ffi.Struct_UTFStringRef;

public class Struct_UTF8StringRef extends Struct_UTFStringRef {

    // ---- поля ----
  final Struct this$0;

  public Struct_UTF8StringRef(Struct arg0, int arg1) { // было: <init>
        super(arg0, arg1, Struct.UTF8);
        this$0 = arg0;
    }

  public Struct_UTF8StringRef(Struct arg0) { // было: <init>
        super(arg0, 2147483647, Struct.UTF8);
        this$0 = arg0;
    }

}