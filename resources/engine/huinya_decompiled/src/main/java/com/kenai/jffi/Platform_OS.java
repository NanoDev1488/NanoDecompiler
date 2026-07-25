// исходный (обфусцированный) внутренний класс: com.kenai.jffi.Platform.OS
package com.kenai.jffi;

import com.kenai.jffi.Platform;

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
    UNKNOWN;

  private Platform_OS() { // было: <init>
        // (пустое тело)
    }

  public String toString() {
        return name().toLowerCase(Platform.access$000());
    }

}