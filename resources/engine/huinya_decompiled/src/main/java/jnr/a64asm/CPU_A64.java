// исходный (обфусцированный) внутренний класс: jnr.a64asm.CPU_A64
package jnr.a64asm;

public enum CPU_A64 {

    Aarch32,
    Aarch64,
    X86_32,
    X86_64;

    // ---- поля ----
  public static final CPU_A64 I386;
  public static final CPU_A64 A64;

    static {
        I386 = X86_32;
        A64 = Aarch64;
    }

  private CPU_A64() { // было: <init>
        // (пустое тело)
    }

}