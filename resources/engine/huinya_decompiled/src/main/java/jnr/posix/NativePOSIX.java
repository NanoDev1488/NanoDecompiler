// исходный (обфусцированный) внутренний класс: jnr.posix.NativePOSIX
package jnr.posix;

import jnr.ffi.Memory;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.posix.POSIX;
import jnr.posix.SocketMacros;

public abstract class NativePOSIX implements POSIX {

  public NativePOSIX() { // было: <init>
        super();
    }

   Runtime getRuntime() {
        return Runtime.getRuntime(libc());
    }

  public abstract SocketMacros socketMacros();

  public Pointer allocatePosixSpawnFileActions() {
        return Memory.allocateDirect(getRuntime(), 128);
    }

  public Pointer allocatePosixSpawnattr() {
        return Memory.allocateDirect(getRuntime(), 128);
    }

}