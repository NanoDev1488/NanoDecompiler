// исходный (обфусцированный) внутренний класс: jnr.posix.SpawnAttribute.Sigdef
package jnr.posix;

import jnr.ffi.Pointer;
import jnr.posix.POSIX;
import jnr.posix.SpawnAttribute;

final class SpawnAttribute_Sigdef extends SpawnAttribute {

    // ---- поля ----
  final long sigdef;

  public SpawnAttribute_Sigdef(long arg0) { // было: <init>
        super();
        sigdef = arg0;
    }

  final boolean set(POSIX arg0, Pointer arg1) {
        throw new RuntimeException("sigdefault not yet supported");
    }

  public String toString() {
        return new StringBuilder().append("SpawnAttribute::Sigdef(def = ").append(Long.toHexString(sigdef)).append(")").toString();
    }

}