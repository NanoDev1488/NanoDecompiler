// исходный (обфусцированный) внутренний класс: jnr.posix.SpawnAttribute
package jnr.posix;

import jnr.ffi.Pointer;
import jnr.posix.POSIX;
import jnr.posix.SpawnAttribute_PGroup;
import jnr.posix.SpawnAttribute_SetFlags;

public abstract class SpawnAttribute {

    // ---- поля ----
  public static final int RESETIDS = 1;
  public static final int SETPGROUP = 2;
  public static final int SETSIGDEF = 4;
  public static final int SETSIGMASK = 8;

  public SpawnAttribute() { // было: <init>
        super();
    }

  abstract boolean set(POSIX arg0, Pointer arg1);

  public static SpawnAttribute pgroup(long arg0) {
        return new SpawnAttribute_PGroup(arg0);
    }

  public static SpawnAttribute flags(short arg0) {
        return new SpawnAttribute_SetFlags(arg0);
    }

  public static SpawnAttribute sigdef(long arg0) {
        throw new RuntimeException("sigdefault not yet supported");
    }

  public static SpawnAttribute sigmask(long arg0) {
        throw new RuntimeException("sigmask not yet supported");
    }

}