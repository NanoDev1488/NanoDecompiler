// исходный (обфусцированный) внутренний класс: com.kenai.jffi.internal.StubLoader.OS
package com.kenai.jffi.internal;

import com.kenai.jffi.internal.StubLoader;

public enum StubLoader_OS {

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

  private StubLoader_OS() { // было: <init>
        // (пустое тело)
    }

  public String toString() {
        return name().toLowerCase(StubLoader.access$000());
    }

}