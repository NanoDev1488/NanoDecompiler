// исходный (обфусцированный) внутренний класс: jnr.ffi.StructLayout.UTFStringRef
package jnr.ffi;

import java.nio.charset.Charset;
import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_Offset;
import jnr.ffi.StructLayout_String;
import jnr.ffi.Type;
import jnr.ffi.provider.MemoryManager;

public class StructLayout_UTFStringRef extends StructLayout_String {

    // ---- поля ----
  private Pointer valueHolder;
  final StructLayout this$0;

  public StructLayout_UTFStringRef(StructLayout arg0, int arg1, Charset arg2) { // было: <init>
        super(arg0, arg0.getRuntime().findType(NativeType.ADDRESS).size(), arg0.getRuntime().findType(NativeType.ADDRESS).alignment(), arg1, arg2);
        this$0 = arg0;
    }

  public StructLayout_UTFStringRef(StructLayout arg0, int arg1, Charset arg2, StructLayout_Offset arg3) { // было: <init>
        super(arg0, arg0.getRuntime().findType(NativeType.ADDRESS).size(), arg0.getRuntime().findType(NativeType.ADDRESS).alignment(), arg3, arg1, arg2);
        this$0 = arg0;
    }

  public StructLayout_UTFStringRef(StructLayout arg0, Charset arg1) { // было: <init>
        this(arg0, 2147483647, arg1);
    }

  protected Pointer getStringMemory(Pointer arg0) {
        return arg0.getPointer(offset(), ((long) length()));
    }

  public final String get(Pointer arg0) {
        Pointer var2 = getStringMemory(arg0);
        return var2 == null ? null : var2.getString(0L, length, charset);
    }

  public final void set(Pointer arg0, String arg1) {
        if (arg1 == null) {
            valueHolder = null;
            arg0.putAddress(offset(), 0L);
        } else {
            valueHolder = this$0.getRuntime().getMemoryManager().allocateDirect(length() * 4);
            valueHolder.putString(0L, arg1, length() * 4, charset);
            arg0.putPointer(offset(), valueHolder);
        }
    }

}