// исходный (обфусцированный) внутренний класс: com.kenai.jffi.Platform.CPU
package com.kenai.jffi;

import com.kenai.jffi.Platform;

public enum Platform_CPU {

    I386(32),
    X86_64(64),
    PPC(32),
    PPC64(64),
    PPC64LE(64),
    SPARC(32),
    SPARCV9(64),
    S390X(64),
    ARM(32),
    AARCH64(64),
    LOONGARCH64(64),
    MIPSEL(32),
    MIPS64EL(64),
    RISCV64(64),
    UNKNOWN(64);

    // ---- поля ----
  public final int dataModel;
  public final long addressMask;

  private Platform_CPU(int arg2) { // было: <init>
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: aload_1
        //      2: iload_2
        //      3: invokespecial  #48 // java.lang.Enum.<init>:(Ljava/lang/String;I)V
        //      6: aload_0
        //      7: iload_3
        //      8: putfield  #42 // com.kenai.jffi.Platform$CPU.dataModel:I
        //     11: aload_0
        //     12: iload_3
        //     13: bipush  32
        //     15: if_icmpne  24 (offset +9)
        //     18: ldc2_w  #23 // 4294967295L
        //     21: goto  27 (offset +6)
        //     24: ldc2_w  #21 // -1L
        //     27: putfield  #41 // com.kenai.jffi.Platform$CPU.addressMask:J
        //     30: return
    }

  public String toString() {
        return name().toLowerCase(Platform.access$000());
    }

}