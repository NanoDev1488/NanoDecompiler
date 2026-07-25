// исходный (обфусцированный) внутренний класс: jnr.posix.windows.WindowsFileTime
package jnr.posix.windows;

import jnr.ffi.Runtime;
import jnr.ffi.Struct;
import jnr.ffi.Struct_Unsigned32;

public class WindowsFileTime extends Struct {

    // ---- поля ----
  final Struct_Unsigned32 lowDateTime;
  final Struct_Unsigned32 highDateTime;

  public WindowsFileTime(Runtime arg0) { // было: <init>
        super(arg0);
        lowDateTime = new Struct_Unsigned32(this);
        highDateTime = new Struct_Unsigned32(this);
    }

  public int getLowDateTime() {
        return lowDateTime.intValue();
    }

  public int getHighDateTime() {
        return highDateTime.intValue();
    }

  public long getLongValue() {
        return ((long) (getHighDateTime() << 32 + getLowDateTime()));
    }

}