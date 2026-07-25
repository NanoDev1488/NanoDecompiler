// исходный (обфусцированный) внутренний класс: jnr.ffi.Platform.OS
package jnr.ffi;

import jnr.ffi.Platform;

public enum Platform_OS {

    DARWIN,
    FREEBSD,
    NETBSD,
    OPENBSD,
    DRAGONFLY,
    LINUX,
    SOLARIS,
    WINDOWS,
    AIX,
    IBMI,
    ZLINUX,
    MIDNIGHTBSD,
    UNKNOWN;

  private Platform_OS() { // было: <init>
        // (пустое тело)
    }

  public String toString() {
        return name().toLowerCase(Platform.access$100());
    }

}