// исходный (обфусцированный) внутренний класс: jnr.posix.WindowsSecurityAttributes
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.Struct;
import jnr.ffi.Struct_Pointer;
import jnr.ffi.Struct_Unsigned32;
import jnr.ffi.Struct_WBOOL;

public class WindowsSecurityAttributes extends Struct {

    // ---- поля ----
  public final Struct_Unsigned32 length;
  public final Struct_Pointer securityDescriptor;
  public final Struct_WBOOL inheritHandle;

  public WindowsSecurityAttributes(Runtime arg0) { // было: <init>
        super(arg0);
        length = new Struct_Unsigned32(this);
        securityDescriptor = new Struct_Pointer(this);
        inheritHandle = new Struct_WBOOL(this);
        length.set(((long) Struct.size(this)));
        inheritHandle.set(true);
    }

  public long getLength() {
        return length.get();
    }

  public boolean getInheritHandle() {
        return inheritHandle.get();
    }

}