// исходный (обфусцированный) внутренний класс: jnr.ffi.Struct.AsciiString
package jnr.ffi;

import jnr.ffi.Struct;
import jnr.ffi.Struct_UTFString;

public class Struct_AsciiString extends Struct_UTFString {

    // ---- поля ----
  final Struct this$0;

  public Struct_AsciiString(Struct arg0, int arg1) { // было: <init>
        super(arg0, arg1, Struct.ASCII);
        this$0 = arg0;
    }

}