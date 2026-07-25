// исходный (обфусцированный) внутренний класс: com.kenai.jnr.x86asm.SerializerCore
package com.kenai.jnr.x86asm;

import com.kenai.jnr.x86asm.CONDITION;
import com.kenai.jnr.x86asm.HINT;
import com.kenai.jnr.x86asm.INST_CODE;
import com.kenai.jnr.x86asm.Immediate;
import com.kenai.jnr.x86asm.Label;
import com.kenai.jnr.x86asm.Operand;
import com.kenai.jnr.x86asm.SerializerCore_Anon1;

@Deprecated
public abstract class SerializerCore {

    // ---- поля ----
  static final Operand _none;
  static INST_CODE[] _jcctable;
  static INST_CODE[] _cmovcctable;
  static final INST_CODE[] _setcctable;
  static final boolean $assertionsDisabled;

    static {
        boolean __stk4;
        __stk4 = !SerializerCore.class.desiredAssertionStatus();
        $assertionsDisabled = __stk4;
        _none = new SerializerCore_Anon1(0, 0);
        _jcctable = new INST_CODE[]{INST_CODE.INST_JO, INST_CODE.INST_JNO, INST_CODE.INST_JB, INST_CODE.INST_JAE, INST_CODE.INST_JE, INST_CODE.INST_JNE, INST_CODE.INST_JBE, INST_CODE.INST_JA, INST_CODE.INST_JS, INST_CODE.INST_JNS, INST_CODE.INST_JPE, INST_CODE.INST_JPO, INST_CODE.INST_JL, INST_CODE.INST_JGE, INST_CODE.INST_JLE, INST_CODE.INST_JG};
        INST_CODE[] __obj6 = new INST_CODE[16];
        __obj6[0] = INST_CODE.INST_CMOVO;
        __obj6[1] = INST_CODE.INST_CMOVNO;
        __obj6[2] = INST_CODE.INST_CMOVB;
        __obj6[3] = INST_CODE.INST_CMOVAE;
        __obj6[4] = INST_CODE.INST_CMOVE;
        __obj6[5] = INST_CODE.INST_CMOVNE;
        __obj6[6] = INST_CODE.INST_CMOVBE;
        __obj6[7] = INST_CODE.INST_CMOVA;
        __obj6[8] = INST_CODE.INST_CMOVS;
        __obj6[9] = INST_CODE.INST_CMOVNS;
        __obj6[10] = INST_CODE.INST_CMOVPE;
        __obj6[11] = INST_CODE.INST_CMOVPO;
        __obj6[12] = INST_CODE.INST_CMOVL;
        __obj6[13] = INST_CODE.INST_CMOVGE;
        __obj6[14] = INST_CODE.INST_CMOVLE;
        __obj6[15] = INST_CODE.INST_CMOVG;
        _cmovcctable = __obj6;
        INST_CODE[] __obj7 = new INST_CODE[16];
        __obj7[0] = INST_CODE.INST_SETO;
        __obj7[1] = INST_CODE.INST_SETNO;
        __obj7[2] = INST_CODE.INST_SETB;
        __obj7[3] = INST_CODE.INST_SETAE;
        __obj7[4] = INST_CODE.INST_SETE;
        __obj7[5] = INST_CODE.INST_SETNE;
        __obj7[6] = INST_CODE.INST_SETBE;
        __obj7[7] = INST_CODE.INST_SETA;
        __obj7[8] = INST_CODE.INST_SETS;
        __obj7[9] = INST_CODE.INST_SETNS;
        __obj7[10] = INST_CODE.INST_SETPE;
        __obj7[11] = INST_CODE.INST_SETPO;
        __obj7[12] = INST_CODE.INST_SETL;
        __obj7[13] = INST_CODE.INST_SETGE;
        __obj7[14] = INST_CODE.INST_SETLE;
        __obj7[15] = INST_CODE.INST_SETG;
        _setcctable = __obj7;
    }

  public SerializerCore() { // было: <init>
        super();
    }

  abstract void _emitX86(INST_CODE arg0, Operand arg1, Operand arg2, Operand arg3);

   void emitX86(INST_CODE arg0) {
        _emitX86(arg0, _none, _none, _none);
    }

   void emitX86(INST_CODE arg0, Operand arg1) {
        _emitX86(arg0, arg1, _none, _none);
    }

   void emitX86(INST_CODE arg0, Operand arg1, Operand arg2) {
        _emitX86(arg0, arg1, arg2, _none);
    }

   void emitX86(INST_CODE arg0, Operand arg1, Operand arg2, Operand arg3) {
        _emitX86(arg0, arg1, arg2, arg3);
    }

   void _emitJcc(INST_CODE arg0, Label arg1, int arg2) {
        if (arg2 != 0) {
            emitX86(arg0, arg1, Immediate.imm(((long) arg2)));
        } else {
            emitX86(arg0, arg1);
        }
    }

   void _emitJcc(INST_CODE arg0, Label arg1, HINT arg2) {
        if (arg2 != HINT.HINT_NONE) {
            emitX86(arg0, arg1, Immediate.imm(((long) arg2.value())));
        } else {
            emitX86(arg0, arg1);
        }
    }

  abstract boolean is64();

  static INST_CODE conditionToJCC(CONDITION arg0) {
        if ($assertionsDisabled) {
            return ((INST_CODE) _jcctable[arg0.value()]);
        } else {
            if (arg0.value() <= 15) {
                return ((INST_CODE) _jcctable[arg0.value()]);
            } else {
                throw new AssertionError();
            }
        }
    }

  static INST_CODE conditionToCMovCC(CONDITION arg0) {
        if ($assertionsDisabled) {
            return ((INST_CODE) _cmovcctable[arg0.value()]);
        } else {
            if (arg0.value() <= 15) {
                return ((INST_CODE) _cmovcctable[arg0.value()]);
            } else {
                throw new AssertionError();
            }
        }
    }

  static INST_CODE conditionToSetCC(CONDITION arg0) {
        if ($assertionsDisabled) {
            return ((INST_CODE) _setcctable[arg0.value()]);
        } else {
            if (arg0.value() <= 15) {
                return ((INST_CODE) _setcctable[arg0.value()]);
            } else {
                throw new AssertionError();
            }
        }
    }

}