// исходный (обфусцированный) внутренний класс: jnr.ffi.Struct.UTFStringRef
package jnr.ffi;

import java.nio.charset.Charset;
import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.Struct;
import jnr.ffi.Struct_String;
import jnr.ffi.Type;
import jnr.ffi.provider.MemoryManager;

public class Struct_UTFStringRef extends Struct_String {

    // ---- поля ----
  private Pointer valueHolder;
  final Struct this$0;

  public Struct_UTFStringRef(Struct arg0, int arg1, Charset arg2) { // было: <init>
        super(arg0, arg0.getRuntime().findType(NativeType.ADDRESS).size() * 8, arg0.getRuntime().findType(NativeType.ADDRESS).alignment() * 8, arg1, arg2);
        this$0 = arg0;
    }

  public Struct_UTFStringRef(Struct arg0, Charset arg1) { // было: <init>
        this(arg0, 2147483647, arg1);
    }

  protected Pointer getStringMemory() {
        return getMemory().getPointer(offset(), ((long) length()));
    }

  public final String get() {
        Pointer var1 = getStringMemory();
        return var1 == null ? null : var1.getString(0L, length, charset);
    }

  public final void set(String arg0) {
        if (arg0 == null) {
            valueHolder = null;
            getMemory().putAddress(offset(), 0L);
        } else {
            int var2 = arg0.length() * 4 + 1;
            valueHolder = this$0.getRuntime().getMemoryManager().allocateDirect(var2);
            valueHolder.putString(0L, arg0, var2, charset);
            getMemory().putPointer(offset(), valueHolder);
        }
    }

}