// исходный (обфусцированный) внутренний класс: jnr.posix.HANDLE
package jnr.posix;

import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.mapper.DataConverter;
import jnr.ffi.provider.MemoryManager;
import jnr.posix.HANDLE_Anon1;

public final class HANDLE {

    // ---- поля ----
  public static final long INVALID_HANDLE_VALUE = -1L;
  private final Pointer pointer;
  public static final DataConverter Converter;

    static {
        Converter = new HANDLE_Anon1();
    }

  public HANDLE(Pointer arg0) { // было: <init>
        super();
        pointer = arg0;
    }

  public final Pointer toPointer() {
        return pointer;
    }

  public final boolean isValid() {
        return pointer.address() != (-1L & Runtime.getSystemRuntime().addressMask());
    }

  public static HANDLE valueOf(Pointer arg0) {
        return new HANDLE(arg0);
    }

  public static HANDLE valueOf(long arg0) {
        return new HANDLE(Runtime.getSystemRuntime().getMemoryManager().newPointer(arg0));
    }

  static Pointer access$000(HANDLE arg0) {
        return arg0.pointer;
    }

}