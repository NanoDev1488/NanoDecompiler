// исходный (обфусцированный) внутренний класс: jnr.ffi.Struct.String
package jnr.ffi;

import java.nio.charset.Charset;
import jnr.ffi.Pointer;
import jnr.ffi.Struct;
import jnr.ffi.Struct_AbstractMember;
import jnr.ffi.Struct_Offset;

public abstract class Struct_String extends Struct_AbstractMember {

    // ---- поля ----
  protected final Charset charset;
  protected final int length;
  final Struct this$0;

  protected Struct_String(Struct arg0, int arg1, int arg2, int arg3, Charset arg4) { // было: <init>
        super(arg0, arg1, arg2);
        this$0 = arg0;
        length = arg3;
        charset = arg4;
    }

  protected Struct_String(Struct arg0, int arg1, int arg2, Struct_Offset arg3, int arg4, Charset arg5) { // было: <init>
        super(arg0, arg1, arg2, arg3);
        this$0 = arg0;
        length = arg4;
        charset = arg5;
    }

  public final int length() {
        return length;
    }

  protected abstract Pointer getStringMemory();

  public abstract String get();

  public abstract void set(String arg0);

  public final String toString() {
        return get();
    }

}