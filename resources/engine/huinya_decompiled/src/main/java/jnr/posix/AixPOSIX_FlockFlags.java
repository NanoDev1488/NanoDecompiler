// исходный (обфусцированный) внутренний класс: jnr.posix.AixPOSIX.FlockFlags
package jnr.posix;

enum AixPOSIX_FlockFlags {

    LOCK_SH(1),
    LOCK_EX(2),
    LOCK_NB(4),
    LOCK_UN(8);

    // ---- поля ----
  private final int value;

  private AixPOSIX_FlockFlags(int arg2) { // было: <init>
        value = arg2;
    }

  public final int intValue() {
        return value;
    }

}