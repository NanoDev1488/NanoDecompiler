// исходный (обфусцированный) внутренний класс: jnr.ffi.StructLayout.UTFString
package jnr.ffi;

import java.nio.charset.Charset;
import jnr.ffi.Pointer;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_Offset;
import jnr.ffi.StructLayout_String;

public class StructLayout_UTFString extends StructLayout_String {

    // ---- поля ----
  final StructLayout this$0;

  public StructLayout_UTFString(StructLayout arg0, int arg1, Charset arg2) { // было: <init>
        super(arg0, arg1, 1, arg1, arg2);
        this$0 = arg0;
    }

  public StructLayout_UTFString(StructLayout arg0, int arg1, Charset arg2, StructLayout_Offset arg3) { // было: <init>
        super(arg0, arg1, 1, arg3, arg1, arg2);
        this$0 = arg0;
    }

  protected Pointer getStringMemory(Pointer arg0) {
        return arg0.slice(offset(), ((long) length()));
    }

  public final String get(Pointer arg0) {
        return getStringMemory(arg0).getString(0L, length, charset);
    }

  public final void set(Pointer arg0, String arg1) {
        getStringMemory(arg0).putString(0L, arg1, length, charset);
    }

}