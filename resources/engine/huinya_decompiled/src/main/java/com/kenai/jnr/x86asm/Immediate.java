// исходный (обфусцированный) внутренний класс: com.kenai.jnr.x86asm.Immediate
package com.kenai.jnr.x86asm;

import com.kenai.jnr.x86asm.Immediate_Cache;
import com.kenai.jnr.x86asm.Operand;
import com.kenai.jnr.x86asm.RELOC_MODE;

@Deprecated
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
        Immediate __stk1;
        __stk1 = arg0 < -128L ? new Immediate(arg0, false) : arg0 > 127L ? new Immediate(arg0, false) : Immediate_Cache.cache[128 + ((int) arg0)];
        return ((Immediate) __stk1);
    }

  public static final Immediate uimm(long arg0) {
        return new Immediate(arg0, true);
    }

}