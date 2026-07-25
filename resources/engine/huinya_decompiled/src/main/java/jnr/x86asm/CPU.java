// исходный (обфусцированный) внутренний класс: jnr.x86asm.CPU
package jnr.x86asm;

public enum CPU {

    X86_32,
    X86_64;

    // ---- поля ----
  public static final CPU I386;

    static {
        I386 = X86_32;
    }

  private CPU() { // было: <init>
        // (пустое тело)
    }

}