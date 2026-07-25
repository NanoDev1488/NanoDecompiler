// исходный (обфусцированный) внутренний класс: jnr.posix.NativePasswd
package jnr.posix;

import jnr.ffi.Pointer;
import jnr.posix.Passwd;

public abstract class NativePasswd implements Passwd {

    // ---- поля ----
  protected final Pointer memory;

   NativePasswd(Pointer arg0) { // было: <init>
        super();
        memory = arg0;
    }

}