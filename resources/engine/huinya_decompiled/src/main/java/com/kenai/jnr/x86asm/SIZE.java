// исходный (обфусцированный) внутренний класс: com.kenai.jnr.x86asm.SIZE
package com.kenai.jnr.x86asm;

@Deprecated
public class SIZE {

    // ---- поля ----
  public static final int SIZE_BYTE = 1;
  public static final int SIZE_WORD = 2;
  public static final int SIZE_DWORD = 4;
  public static final int SIZE_QWORD = 8;
  public static final int SIZE_TWORD = 10;
  public static final int SIZE_DQWORD = 16;

  private SIZE() { // было: <init>
        super();
    }

}