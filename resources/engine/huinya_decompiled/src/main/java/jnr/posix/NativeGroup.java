// исходный (обфусцированный) внутренний класс: jnr.posix.NativeGroup
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.StructLayout;
import jnr.posix.Group;

public abstract class NativeGroup implements Group {

    // ---- поля ----
  protected final Runtime runtime;
  protected final StructLayout structLayout;

  protected NativeGroup(Runtime arg0, StructLayout arg1) { // было: <init>
        super();
        runtime = arg0;
        structLayout = arg1;
    }

}