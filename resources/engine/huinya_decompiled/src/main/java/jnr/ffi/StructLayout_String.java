// исходный (обфусцированный) внутренний класс: jnr.ffi.StructLayout.String
package jnr.ffi;

import java.nio.charset.Charset;
import jnr.ffi.Pointer;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_AbstractField;
import jnr.ffi.StructLayout_Offset;

public abstract class StructLayout_String extends StructLayout_AbstractField {

    // ---- поля ----
  protected final Charset charset;
  protected final int length;
  final StructLayout this$0;

  protected StructLayout_String(StructLayout arg0, int arg1, int arg2, int arg3, Charset arg4) { // было: <init>
        super(arg0, arg1, arg2);
        this$0 = arg0;
        length = arg3;
        charset = arg4;
    }

  protected StructLayout_String(StructLayout arg0, int arg1, int arg2, StructLayout_Offset arg3, int arg4, Charset arg5) { // было: <init>
        super(arg0, arg1, arg2, arg3);
        this$0 = arg0;
        length = arg4;
        charset = arg5;
    }

  public final int length() {
        return length;
    }

  protected abstract Pointer getStringMemory(Pointer arg0);

  public abstract String get(Pointer arg0);

  public abstract void set(Pointer arg0, String arg1);

  public final String toString(Pointer arg0) {
        return get(arg0);
    }

}