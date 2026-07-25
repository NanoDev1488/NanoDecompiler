// исходный (обфусцированный) внутренний класс: jnr.a64asm.CONDITION
package jnr.a64asm;

public enum CONDITION {

    C_NO_CONDITION(-1),
    C_EQ(0),
    C_NE(1),
    C_CS(2),
    C_CC(3),
    C_MI(4),
    C_PL(5),
    C_VS(6),
    C_VC(7),
    C_HI(8),
    C_LS(9),
    C_GE(16),
    C_LT(17),
    C_GT(18),
    C_LE(19),
    C_AL(20),
    C_NV(21),
    C_HS(2),
    C_LO(3),
    C_EQUAL(0),
    C_NOT_EQUAL(1),
    C_ABOVE_EQUAL(2),
    C_BELOW(3),
    C_SIGN(4),
    C_POSITIVE_ZERO(5),
    C_OVERFLOW(6),
    C_NO_OVERFLOW(7),
    C_ABOVE(8),
    C_BELOW_EQUAL(9),
    C_GREATER_EQUAL(10),
    C_LESS(11),
    C_GREATER(12),
    C_LESS_EQUAL(13),
    C_DEFAULT(14),
    C_ZERO(0),
    C_NOT_ZERO(1),
    C_NEGATIVE(4),
    C_POSITIVE(5);

    // ---- поля ----
  private final int value;

  private CONDITION(int arg2) { // было: <init>
        value = arg2;
    }

  public final int value() {
        return value;
    }

}