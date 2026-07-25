// исходный (обфусцированный) внутренний класс: jnr.a64asm.OperandFlags
package jnr.a64asm;

public class OperandFlags {

    // ---- поля ----
  public static final int O_G32 = 4;
  public static final int O_G64 = 8;
  public static final int O_MEM = 64;
  public static final int O_IMM = 128;
  public static final int O_G32_64 = 12;

  public OperandFlags() { // было: <init>
        super();
    }

}