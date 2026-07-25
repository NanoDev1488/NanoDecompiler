// исходный (обфусцированный) внутренний класс: jnr.x86asm.CONDITION
package jnr.x86asm;

public enum CONDITION {

    C_NO_CONDITION(-1),
    C_A(7),
    C_AE(3),
    C_B(2),
    C_BE(6),
    C_C(2),
    C_E(4),
    C_G(15),
    C_GE(13),
    C_L(12),
    C_LE(14),
    C_NA(6),
    C_NAE(2),
    C_NB(3),
    C_NBE(7),
    C_NC(3),
    C_NE(5),
    C_NG(14),
    C_NGE(12),
    C_NL(13),
    C_NLE(15),
    C_NO(1),
    C_NP(11),
    C_NS(9),
    C_NZ(5),
    C_O(0),
    C_P(10),
    C_PE(10),
    C_PO(11),
    C_S(8),
    C_Z(4),
    C_OVERFLOW(0),
    C_NO_OVERFLOW(1),
    C_BELOW(2),
    C_ABOVE_EQUAL(3),
    C_EQUAL(4),
    C_NOT_EQUAL(5),
    C_BELOW_EQUAL(6),
    C_ABOVE(7),
    C_SIGN(8),
    C_NOT_SIGN(9),
    C_PARITY_EVEN(10),
    C_PARITY_ODD(11),
    C_LESS(12),
    C_GREATER_EQUAL(13),
    C_LESS_EQUAL(14),
    C_GREATER(15),
    C_ZERO(4),
    C_NOT_ZERO(5),
    C_NEGATIVE(8),
    C_POSITIVE(9),
    C_FP_UNORDERED(16),
    C_FP_NOT_UNORDERED(17);

    // ---- поля ----
  private final int value;

  private CONDITION(int arg2) { // было: <init>
        value = arg2;
    }

  public final int value() {
        return value;
    }

}