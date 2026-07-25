// исходный (обфусцированный) внутренний класс: jnr.a64asm.Immediate
package jnr.a64asm;

import jnr.a64asm.Operand;
import jnr.a64asm.RELOC_MODE;

public final class Immediate extends Operand {

    // ---- поля ----
  private final long value;
  private final boolean isUnsigned;
  private final RELOC_MODE relocMode;

  public Immediate(long arg0, boolean arg1) { // было: <init>
        super(3, 0);
        value = arg0;
        isUnsigned = arg1;
        relocMode = RELOC_MODE.RELOC_NONE;
    }

  public long value() {
        return value;
    }

  public final byte byteValue() {
        return ((byte) ((int) value));
    }

  public final short shortValue() {
        return ((short) ((int) value));
    }

  public final int intValue() {
        return ((int) value);
    }

  public final long longValue() {
        return value;
    }

  public final boolean isUnsigned() {
        return isUnsigned;
    }

   RELOC_MODE relocMode() {
        return relocMode;
    }

  public static final Immediate imm(long arg0) {
        return new Immediate(arg0, false);
    }

  public static final Immediate uimm(long arg0) {
        return new Immediate(arg0, true);
    }

}