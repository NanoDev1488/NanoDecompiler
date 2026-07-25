// исходный (обфусцированный) внутренний класс: jnr.posix.FileTime
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.Struct;
import jnr.ffi.Struct_Unsigned32;

public class FileTime extends Struct {

    // ---- поля ----
  public final Struct_Unsigned32 dwLowDateTime;
  public final Struct_Unsigned32 dwHighDateTime;

   FileTime(Runtime arg0) { // было: <init>
        super(arg0);
        dwLowDateTime = new Struct_Unsigned32(this);
        dwHighDateTime = new Struct_Unsigned32(this);
    }

}