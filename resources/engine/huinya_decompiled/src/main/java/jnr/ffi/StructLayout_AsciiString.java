// исходный (обфусцированный) внутренний класс: jnr.ffi.StructLayout.AsciiString
package jnr.ffi;

import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_Offset;
import jnr.ffi.StructLayout_UTFString;

public class StructLayout_AsciiString extends StructLayout_UTFString {

    // ---- поля ----
  final StructLayout this$0;

  public StructLayout_AsciiString(StructLayout arg0, int arg1) { // было: <init>
        super(arg0, arg1, StructLayout.ASCII);
        this$0 = arg0;
    }

  public StructLayout_AsciiString(StructLayout arg0, int arg1, StructLayout_Offset arg2) { // было: <init>
        super(arg0, arg1, StructLayout.ASCII, arg2);
        this$0 = arg0;
    }

}