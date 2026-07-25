// исходный (обфусцированный) внутренний класс: jnr.x86asm.OperandFlags
package jnr.x86asm;

public class OperandFlags {

    // ---- поля ----
  public static final int O_G8 = 1;
  public static final int O_G16 = 2;
  public static final int O_G32 = 4;
  public static final int O_G64 = 8;
  public static final int O_MEM = 64;
  public static final int O_IMM = 128;
  public static final int O_G8_16_32_64 = 15;
  public static final int O_G16_32_64 = 14;
  public static final int O_G32_64 = 12;
  public static final int O_FM_1 = 1;
  public static final int O_FM_2 = 2;
  public static final int O_FM_4 = 4;
  public static final int O_FM_8 = 8;
  public static final int O_FM_10 = 16;
  public static final int O_FM_2_4 = 6;
  public static final int O_FM_2_4_8 = 14;
  public static final int O_FM_4_8 = 12;
  public static final int O_FM_4_8_10 = 28;
  public static final int O_NOREX = 1;
  public static final int O_MM = 16;
  public static final int O_XMM = 32;
  public static final int O_MM_MEM = 80;
  public static final int O_XMM_MEM = 96;
  public static final int O_MM_XMM = 48;
  public static final int O_MM_XMM_MEM = 112;

  public OperandFlags() { // было: <init>
        super();
    }

}