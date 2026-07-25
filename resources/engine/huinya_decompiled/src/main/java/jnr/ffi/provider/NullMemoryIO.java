// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.NullMemoryIO
package jnr.ffi.provider;

import jnr.ffi.Runtime;
import jnr.ffi.provider.InAccessibleMemoryIO;

public final class NullMemoryIO extends InAccessibleMemoryIO {

    // ---- поля ----
  private static final String msg = "attempted access to a NULL memory address";

  public NullMemoryIO(Runtime arg0) { // было: <init>
        super(arg0, 0L, true);
    }

  protected final NullPointerException error() {
        return new NullPointerException("attempted access to a NULL memory address");
    }

  public long size() {
        return 9223372036854775807L;
    }

  protected RuntimeException error() {
        return error();
    }

}