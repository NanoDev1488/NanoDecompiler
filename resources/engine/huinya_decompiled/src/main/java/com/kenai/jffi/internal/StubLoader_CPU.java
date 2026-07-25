// исходный (обфусцированный) внутренний класс: com.kenai.jffi.internal.StubLoader.CPU
package com.kenai.jffi.internal;

import com.kenai.jffi.internal.StubLoader;

public enum StubLoader_CPU {

    I386,
    X86_64,
    PPC,
    PPC64,
    PPC64LE,
    SPARC,
    SPARCV9,
    S390X,
    ARM,
    AARCH64,
    LOONGARCH64,
    MIPSEL,
    MIPS64EL,
    RISCV64,
    UNKNOWN;

  private StubLoader_CPU() { // было: <init>
        // (пустое тело)
    }

  public String toString() {
        return name().toLowerCase(StubLoader.access$000());
    }

}