// исходный (обфусцированный) внутренний класс: com.kenai.jnr.x86asm.TrampolineWriter
package com.kenai.jnr.x86asm;

import java.nio.ByteBuffer;

@Deprecated
final class TrampolineWriter {

    // ---- поля ----
  public static final int TRAMPOLINE_JMP = 6;
  public static final int TRAMPOLINE_ADDR = 8;
  public static final int TRAMPOLINE_SIZE = 14;

   TrampolineWriter() { // было: <init>
        super();
    }

  static void writeTrampoline(ByteBuffer arg0, long arg1) {
        arg0.put(-1);
        arg0.put(37);
        arg0.putInt(0);
        arg0.putLong(arg1);
    }

}