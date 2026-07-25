// исходный (обфусцированный) внутренний класс: jnr.ffi.Struct.UTFString
package jnr.ffi;

import java.nio.charset.Charset;
import jnr.ffi.Pointer;
import jnr.ffi.Struct;
import jnr.ffi.Struct_String;

public class Struct_UTFString extends Struct_String {

    // ---- поля ----
  final Struct this$0;

  public Struct_UTFString(Struct arg0, int arg1, Charset arg2) { // было: <init>
        super(arg0, arg1 * 8, 8, arg1, arg2);
        this$0 = arg0;
    }

  protected Pointer getStringMemory() {
        return getMemory().slice(offset(), ((long) length()));
    }

  public final String get() {
        return getStringMemory().getString(0L, length, charset);
    }

  public final void set(String arg0) {
        getStringMemory().putString(0L, arg0, length, charset);
    }

}