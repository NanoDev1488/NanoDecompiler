// исходный (обфусцированный) внутренний класс: com.kenai.jnr.x86asm.RID
package com.kenai.jnr.x86asm;

@Deprecated
public class RID {

    // ---- поля ----
  public static final int RID_EAX = 0;
  public static final int RID_ECX = 1;
  public static final int RID_EDX = 2;
  public static final int RID_EBX = 3;
  public static final int RID_ESP = 4;
  public static final int RID_EBP = 5;
  public static final int RID_ESI = 6;
  public static final int RID_EDI = 7;

  private RID() { // было: <init>
        super();
    }

}