// исходный (обфусцированный) внутренний класс: jnr.a64asm.SysRegister
package jnr.a64asm;

import jnr.a64asm.Operand;
import jnr.a64asm.SYSREG_CODE;

public class SysRegister extends Operand {

    // ---- поля ----
   SYSREG_CODE sysRegEnum;
  private static final SysRegister[] sys;

    static {
        sys = new SysRegister[305];
        SYSREG_CODE var0 = SYSREG_CODE.SPSR_EL1;
        while (var0.ordinal() < SYSREG_CODE.SYSREG_MAX.ordinal()) {
            sys[var0.ordinal()] = new SysRegister(var0);
            var0 = SYSREG_CODE.valueOf(var0.ordinal() + 1);
            continue;
        }
    }

  public SysRegister(SYSREG_CODE arg0) { // было: <init>
        super(9, 64);
        sysRegEnum = arg0;
    }

  public static final SysRegister sysReg(SYSREG_CODE arg0) {
        return ((SysRegister) sys[arg0.ordinal()]);
    }

  public SYSREG_CODE getEnum() {
        return sysRegEnum;
    }

}