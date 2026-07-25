// исходный (обфусцированный) внутренний класс: jnr.ffi.Platform.CPU
package jnr.ffi;

import jnr.ffi.Platform;

public enum Platform_CPU {

    I386,
    X86_64,
    PPC,
    PPC64,
    PPC64LE,
    SPARC,
    SPARCV9,
    S390X,
    MIPS32,
    ARM,
    AARCH64,
    MIPS64EL,
    LOONGARCH64,
    RISCV64,
    UNKNOWN;

  private Platform_CPU() { // было: <init>
        // (пустое тело)
    }

  public String toString() {
        return name().toLowerCase(Platform.access$100());
    }

}