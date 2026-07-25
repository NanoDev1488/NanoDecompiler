// исходный (обфусцированный) внутренний класс: jnr.x86asm.SerializerIntrinsics
package jnr.x86asm;

import jnr.x86asm.CONDITION;
import jnr.x86asm.INST_CODE;
import jnr.x86asm.Immediate;
import jnr.x86asm.Label;
import jnr.x86asm.MMRegister;
import jnr.x86asm.Mem;
import jnr.x86asm.Register;
import jnr.x86asm.SerializerCore;
import jnr.x86asm.X87Register;
import jnr.x86asm.XMMRegister;

public abstract class SerializerIntrinsics extends SerializerCore {

    // ---- поля ----
  static final boolean $assertionsDisabled;

    static {
        boolean __stk1;
        __stk1 = !SerializerIntrinsics.class.desiredAssertionStatus();
        $assertionsDisabled = __stk1;
    }

  public SerializerIntrinsics() { // было: <init>
        super();
    }

  public final void adc(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_ADC, arg0, arg1);
    }

  public final void adc(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_ADC, arg0, arg1);
    }

  public final void adc(Register arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_ADC, arg0, arg1);
    }

  public final void adc(Mem arg0, Register arg1) {
        emitX86(INST_CODE.INST_ADC, arg0, arg1);
    }

  public final void adc(Mem arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_ADC, arg0, arg1);
    }

  public final void add(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_ADD, arg0, arg1);
    }

  public final void add(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_ADD, arg0, arg1);
    }

  public final void add(Register arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_ADD, arg0, arg1);
    }

  public final void add(Mem arg0, Register arg1) {
        emitX86(INST_CODE.INST_ADD, arg0, arg1);
    }

  public final void add(Mem arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_ADD, arg0, arg1);
    }

  public final void and_(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_AND, arg0, arg1);
    }

  public final void and_(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_AND, arg0, arg1);
    }

  public final void and_(Register arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_AND, arg0, arg1);
    }

  public final void and_(Mem arg0, Register arg1) {
        emitX86(INST_CODE.INST_AND, arg0, arg1);
    }

  public final void and_(Mem arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_AND, arg0, arg1);
    }

  public final void bsf(Register arg0, Register arg1) {
        if ($assertionsDisabled) {
            emitX86(INST_CODE.INST_BSF, arg0, arg1);
            return;
        } else {
            if (!arg0.isRegType(0)) {
                emitX86(INST_CODE.INST_BSF, arg0, arg1);
                return;
            } else {
                throw new AssertionError();
            }
        }
    }

  public final void bsf(Register arg0, Mem arg1) {
        if ($assertionsDisabled) {
            emitX86(INST_CODE.INST_BSF, arg0, arg1);
            return;
        } else {
            if (!arg0.isRegType(0)) {
                emitX86(INST_CODE.INST_BSF, arg0, arg1);
                return;
            } else {
                throw new AssertionError();
            }
        }
    }

  public final void bsr(Register arg0, Register arg1) {
        if ($assertionsDisabled) {
            emitX86(INST_CODE.INST_BSR, arg0, arg1);
            return;
        } else {
            if (!arg0.isRegType(0)) {
                emitX86(INST_CODE.INST_BSR, arg0, arg1);
                return;
            } else {
                throw new AssertionError();
            }
        }
    }

  public final void bsr(Register arg0, Mem arg1) {
        if ($assertionsDisabled) {
            emitX86(INST_CODE.INST_BSR, arg0, arg1);
            return;
        } else {
            if (!arg0.isRegType(0)) {
                emitX86(INST_CODE.INST_BSR, arg0, arg1);
                return;
            } else {
                throw new AssertionError();
            }
        }
    }

  public final void bswap(Register arg0) {
        if ($assertionsDisabled) {
            emitX86(INST_CODE.INST_BSWAP, arg0);
            return;
        } else {
            if (arg0.type() == 32) {
                emitX86(INST_CODE.INST_BSWAP, arg0);
                return;
            } else {
                if (arg0.type() == 48) {
                    emitX86(INST_CODE.INST_BSWAP, arg0);
                    return;
                } else {
                    throw new AssertionError();
                }
            }
        }
    }

  public final void bt(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_BT, arg0, arg1);
    }

  public final void bt(Register arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_BT, arg0, arg1);
    }

  public final void bt(Mem arg0, Register arg1) {
        emitX86(INST_CODE.INST_BT, arg0, arg1);
    }

  public final void bt(Mem arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_BT, arg0, arg1);
    }

  public final void btc(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_BTC, arg0, arg1);
    }

  public final void btc(Register arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_BTC, arg0, arg1);
    }

  public final void btc(Mem arg0, Register arg1) {
        emitX86(INST_CODE.INST_BTC, arg0, arg1);
    }

  public final void btc(Mem arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_BTC, arg0, arg1);
    }

  public final void btr(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_BTR, arg0, arg1);
    }

  public final void btr(Register arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_BTR, arg0, arg1);
    }

  public final void btr(Mem arg0, Register arg1) {
        emitX86(INST_CODE.INST_BTR, arg0, arg1);
    }

  public final void btr(Mem arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_BTR, arg0, arg1);
    }

  public final void bts(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_BTS, arg0, arg1);
    }

  public final void bts(Register arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_BTS, arg0, arg1);
    }

  public final void bts(Mem arg0, Register arg1) {
        emitX86(INST_CODE.INST_BTS, arg0, arg1);
    }

  public final void bts(Mem arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_BTS, arg0, arg1);
    }

  public final void call(Register arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: getstatic  #589 // jnr.x86asm.SerializerIntrinsics.$assertionsDisabled:Z
        //      3: ifne  35 (offset +32)
        //      6: aload_1
        //      7: aload_0
        //      8: invokevirtual  #614 // jnr.x86asm.SerializerIntrinsics.is64:()Z
        //     11: ifeq  19 (offset +8)
        //     14: bipush  48
        //     16: goto  21 (offset +5)
        //     19: bipush  32
        //     21: invokevirtual  #598 // jnr.x86asm.Register.isRegType:(I)Z
        //     24: ifne  35 (offset +11)
        //     27: new  #1 // java.lang.AssertionError
        //     30: dup
        //     31: invokespecial  #590 // java.lang.AssertionError.<init>:()V
        //     34: athrow
        //     35: aload_0
        //     36: getstatic  #36 // jnr.x86asm.INST_CODE.INST_CALL:Ljnr/x86asm/INST_CODE;
        //     39: aload_1
        //     40: invokevirtual  #606 // jnr.x86asm.SerializerIntrinsics.emitX86:(Ljnr/x86asm/INST_CODE;Ljnr/x86asm/Operand;)V
        //     43: return
    }

  public final void call(Mem arg0) {
        emitX86(INST_CODE.INST_CALL, arg0);
    }

  public final void call(Immediate arg0) {
        emitX86(INST_CODE.INST_CALL, arg0);
    }

  public final void call(long arg0) {
        emitX86(INST_CODE.INST_CALL, Immediate.imm(arg0));
    }

  public final void call(Label arg0) {
        emitX86(INST_CODE.INST_CALL, arg0);
    }

  public final void cbw() {
        emitX86(INST_CODE.INST_CBW);
    }

  public final void cwde() {
        emitX86(INST_CODE.INST_CWDE);
    }

  public final void cdqe() {
        emitX86(INST_CODE.INST_CDQE);
    }

  public final void clc() {
        emitX86(INST_CODE.INST_CLC);
    }

  public final void cld() {
        emitX86(INST_CODE.INST_CLD);
    }

  public final void cmc() {
        emitX86(INST_CODE.INST_CMC);
    }

  public final void cmov(CONDITION arg0, Register arg1, Register arg2) {
        emitX86(conditionToCMovCC(arg0), arg1, arg2);
    }

  public final void cmov(CONDITION arg0, Register arg1, Mem arg2) {
        emitX86(conditionToCMovCC(arg0), arg1, arg2);
    }

  public final void cmova(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_CMOVA, arg0, arg1);
    }

  public final void cmova(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CMOVA, arg0, arg1);
    }

  public final void cmovae(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_CMOVAE, arg0, arg1);
    }

  public final void cmovae(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CMOVAE, arg0, arg1);
    }

  public final void cmovb(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_CMOVB, arg0, arg1);
    }

  public final void cmovb(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CMOVB, arg0, arg1);
    }

  public final void cmovbe(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_CMOVBE, arg0, arg1);
    }

  public final void cmovbe(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CMOVBE, arg0, arg1);
    }

  public final void cmovc(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_CMOVC, arg0, arg1);
    }

  public final void cmovc(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CMOVC, arg0, arg1);
    }

  public final void cmove(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_CMOVE, arg0, arg1);
    }

  public final void cmove(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CMOVE, arg0, arg1);
    }

  public final void cmovg(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_CMOVG, arg0, arg1);
    }

  public final void cmovg(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CMOVG, arg0, arg1);
    }

  public final void cmovge(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_CMOVGE, arg0, arg1);
    }

  public final void cmovge(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CMOVGE, arg0, arg1);
    }

  public final void cmovl(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_CMOVL, arg0, arg1);
    }

  public final void cmovl(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CMOVL, arg0, arg1);
    }

  public final void cmovle(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_CMOVLE, arg0, arg1);
    }

  public final void cmovle(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CMOVLE, arg0, arg1);
    }

  public final void cmovna(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_CMOVNA, arg0, arg1);
    }

  public final void cmovna(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CMOVNA, arg0, arg1);
    }

  public final void cmovnae(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_CMOVNAE, arg0, arg1);
    }

  public final void cmovnae(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CMOVNAE, arg0, arg1);
    }

  public final void cmovnb(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_CMOVNB, arg0, arg1);
    }

  public final void cmovnb(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CMOVNB, arg0, arg1);
    }

  public final void cmovnbe(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_CMOVNBE, arg0, arg1);
    }

  public final void cmovnbe(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CMOVNBE, arg0, arg1);
    }

  public final void cmovnc(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_CMOVNC, arg0, arg1);
    }

  public final void cmovnc(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CMOVNC, arg0, arg1);
    }

  public final void cmovne(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_CMOVNE, arg0, arg1);
    }

  public final void cmovne(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CMOVNE, arg0, arg1);
    }

  public final void cmovng(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_CMOVNG, arg0, arg1);
    }

  public final void cmovng(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CMOVNG, arg0, arg1);
    }

  public final void cmovnge(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_CMOVNGE, arg0, arg1);
    }

  public final void cmovnge(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CMOVNGE, arg0, arg1);
    }

  public final void cmovnl(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_CMOVNL, arg0, arg1);
    }

  public final void cmovnl(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CMOVNL, arg0, arg1);
    }

  public final void cmovnle(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_CMOVNLE, arg0, arg1);
    }

  public final void cmovnle(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CMOVNLE, arg0, arg1);
    }

  public final void cmovno(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_CMOVNO, arg0, arg1);
    }

  public final void cmovno(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CMOVNO, arg0, arg1);
    }

  public final void cmovnp(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_CMOVNP, arg0, arg1);
    }

  public final void cmovnp(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CMOVNP, arg0, arg1);
    }

  public final void cmovns(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_CMOVNS, arg0, arg1);
    }

  public final void cmovns(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CMOVNS, arg0, arg1);
    }

  public final void cmovnz(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_CMOVNZ, arg0, arg1);
    }

  public final void cmovnz(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CMOVNZ, arg0, arg1);
    }

  public final void cmovo(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_CMOVO, arg0, arg1);
    }

  public final void cmovo(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CMOVO, arg0, arg1);
    }

  public final void cmovp(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_CMOVP, arg0, arg1);
    }

  public final void cmovp(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CMOVP, arg0, arg1);
    }

  public final void cmovpe(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_CMOVPE, arg0, arg1);
    }

  public final void cmovpe(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CMOVPE, arg0, arg1);
    }

  public final void cmovpo(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_CMOVPO, arg0, arg1);
    }

  public final void cmovpo(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CMOVPO, arg0, arg1);
    }

  public final void cmovs(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_CMOVS, arg0, arg1);
    }

  public final void cmovs(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CMOVS, arg0, arg1);
    }

  public final void cmovz(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_CMOVZ, arg0, arg1);
    }

  public final void cmovz(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CMOVZ, arg0, arg1);
    }

  public final void cmp(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_CMP, arg0, arg1);
    }

  public final void cmp(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CMP, arg0, arg1);
    }

  public final void cmp(Register arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_CMP, arg0, arg1);
    }

  public final void cmp(Mem arg0, Register arg1) {
        emitX86(INST_CODE.INST_CMP, arg0, arg1);
    }

  public final void cmp(Mem arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_CMP, arg0, arg1);
    }

  public final void cmpxchg(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_CMPXCHG, arg0, arg1);
    }

  public final void cmpxchg(Mem arg0, Register arg1) {
        emitX86(INST_CODE.INST_CMPXCHG, arg0, arg1);
    }

  public final void cmpxchg8b(Mem arg0) {
        emitX86(INST_CODE.INST_CMPXCHG8B, arg0);
    }

  public final void cmpxchg16b(Mem arg0) {
        emitX86(INST_CODE.INST_CMPXCHG16B, arg0);
    }

  public final void cpuid() {
        emitX86(INST_CODE.INST_CPUID);
    }

  public final void daa() {
        emitX86(INST_CODE.INST_DAA);
    }

  public final void das() {
        emitX86(INST_CODE.INST_DAS);
    }

  public final void dec(Register arg0) {
        emitX86(INST_CODE.INST_DEC, arg0);
    }

  public final void dec(Mem arg0) {
        emitX86(INST_CODE.INST_DEC, arg0);
    }

  public final void div(Register arg0) {
        emitX86(INST_CODE.INST_DIV, arg0);
    }

  public final void div(Mem arg0) {
        emitX86(INST_CODE.INST_DIV, arg0);
    }

  public final void enter(Immediate arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_ENTER, arg0, arg1);
    }

  public final void idiv(Register arg0) {
        emitX86(INST_CODE.INST_IDIV, arg0);
    }

  public final void idiv(Mem arg0) {
        emitX86(INST_CODE.INST_IDIV, arg0);
    }

  public final void imul(Register arg0) {
        emitX86(INST_CODE.INST_IMUL, arg0);
    }

  public final void imul(Mem arg0) {
        emitX86(INST_CODE.INST_IMUL, arg0);
    }

  public final void imul(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_IMUL, arg0, arg1);
    }

  public final void imul(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_IMUL, arg0, arg1);
    }

  public final void imul(Register arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_IMUL, arg0, arg1);
    }

  public final void imul(Register arg0, Register arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_IMUL, arg0, arg1, arg2);
    }

  public final void imul(Register arg0, Mem arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_IMUL, arg0, arg1, arg2);
    }

  public final void inc(Register arg0) {
        emitX86(INST_CODE.INST_INC, arg0);
    }

  public final void inc(Mem arg0) {
        emitX86(INST_CODE.INST_INC, arg0);
    }

  public final void int3() {
        emitX86(INST_CODE.INST_INT3);
    }

  public final void method1944(CONDITION arg0, Label arg1, int arg2) { // было: j
        _emitJcc(conditionToJCC(arg0), arg1, arg2);
    }

  public final void ja(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JA, arg0, arg1);
    }

  public final void jae(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JAE, arg0, arg1);
    }

  public final void jb(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JB, arg0, arg1);
    }

  public final void jbe(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JBE, arg0, arg1);
    }

  public final void jc(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JC, arg0, arg1);
    }

  public final void je(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JE, arg0, arg1);
    }

  public final void jg(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JG, arg0, arg1);
    }

  public final void jge(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JGE, arg0, arg1);
    }

  public final void jl(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JL, arg0, arg1);
    }

  public final void jle(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JLE, arg0, arg1);
    }

  public final void jna(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JNA, arg0, arg1);
    }

  public final void jnae(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JNAE, arg0, arg1);
    }

  public final void jnb(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JNB, arg0, arg1);
    }

  public final void jnbe(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JNBE, arg0, arg1);
    }

  public final void jnc(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JNC, arg0, arg1);
    }

  public final void jne(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JNE, arg0, arg1);
    }

  public final void jng(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JNG, arg0, arg1);
    }

  public final void jnge(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JNGE, arg0, arg1);
    }

  public final void jnl(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JNL, arg0, arg1);
    }

  public final void jnle(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JNLE, arg0, arg1);
    }

  public final void jno(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JNO, arg0, arg1);
    }

  public final void jnp(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JNP, arg0, arg1);
    }

  public final void jns(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JNS, arg0, arg1);
    }

  public final void jnz(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JNZ, arg0, arg1);
    }

  public final void jo(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JO, arg0, arg1);
    }

  public final void jp(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JP, arg0, arg1);
    }

  public final void jpe(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JPE, arg0, arg1);
    }

  public final void jpo(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JPO, arg0, arg1);
    }

  public final void js(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JS, arg0, arg1);
    }

  public final void jz(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JZ, arg0, arg1);
    }

  public final void j_short(CONDITION arg0, Label arg1, int arg2) {
        _emitJcc(INST_CODE.valueOf(conditionToJCC(arg0).ordinal() + INST_CODE.INST_J_SHORT.ordinal() - INST_CODE.INST_J.ordinal()), arg1, arg2);
    }

  public final void ja_short(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JA_SHORT, arg0, arg1);
    }

  public final void jae_short(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JAE_SHORT, arg0, arg1);
    }

  public final void jb_short(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JB_SHORT, arg0, arg1);
    }

  public final void jbe_short(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JBE_SHORT, arg0, arg1);
    }

  public final void jc_short(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JC_SHORT, arg0, arg1);
    }

  public final void je_short(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JE_SHORT, arg0, arg1);
    }

  public final void jg_short(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JG_SHORT, arg0, arg1);
    }

  public final void jge_short(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JGE_SHORT, arg0, arg1);
    }

  public final void jl_short(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JL_SHORT, arg0, arg1);
    }

  public final void jle_short(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JLE_SHORT, arg0, arg1);
    }

  public final void jna_short(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JNA_SHORT, arg0, arg1);
    }

  public final void jnae_short(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JNAE_SHORT, arg0, arg1);
    }

  public final void jnb_short(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JNB_SHORT, arg0, arg1);
    }

  public final void jnbe_short(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JNBE_SHORT, arg0, arg1);
    }

  public final void jnc_short(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JNC_SHORT, arg0, arg1);
    }

  public final void jne_short(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JNE_SHORT, arg0, arg1);
    }

  public final void jng_short(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JNG_SHORT, arg0, arg1);
    }

  public final void jnge_short(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JNGE_SHORT, arg0, arg1);
    }

  public final void jnl_short(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JNL_SHORT, arg0, arg1);
    }

  public final void jnle_short(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JNLE_SHORT, arg0, arg1);
    }

  public final void jno_short(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JNO_SHORT, arg0, arg1);
    }

  public final void jnp_short(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JNP_SHORT, arg0, arg1);
    }

  public final void jns_short(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JNS_SHORT, arg0, arg1);
    }

  public final void jnz_short(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JNZ_SHORT, arg0, arg1);
    }

  public final void jo_short(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JO_SHORT, arg0, arg1);
    }

  public final void jp_short(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JP_SHORT, arg0, arg1);
    }

  public final void jpe_short(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JPE_SHORT, arg0, arg1);
    }

  public final void jpo_short(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JPO_SHORT, arg0, arg1);
    }

  public final void js_short(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JS_SHORT, arg0, arg1);
    }

  public final void jz_short(Label arg0, int arg1) {
        _emitJcc(INST_CODE.INST_JZ_SHORT, arg0, arg1);
    }

  public final void jmp(Register arg0) {
        emitX86(INST_CODE.INST_JMP, arg0);
    }

  public final void jmp(Mem arg0) {
        emitX86(INST_CODE.INST_JMP, arg0);
    }

  public final void jmp(Immediate arg0) {
        emitX86(INST_CODE.INST_JMP, arg0);
    }

  public final void jmp(long arg0) {
        emitX86(INST_CODE.INST_JMP, Immediate.imm(arg0));
    }

  public final void jmp(Label arg0) {
        emitX86(INST_CODE.INST_JMP, arg0);
    }

  public final void jmp_short(Label arg0) {
        emitX86(INST_CODE.INST_JMP_SHORT, arg0);
    }

  public final void lea(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_LEA, arg0, arg1);
    }

  public final void leave() {
        emitX86(INST_CODE.INST_LEAVE);
    }

  public final void lock() {
        emitX86(INST_CODE.INST_LOCK);
    }

  public final void mov(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_MOV, arg0, arg1);
    }

  public final void mov(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_MOV, arg0, arg1);
    }

  public final void mov(Register arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_MOV, arg0, arg1);
    }

  public final void mov(Mem arg0, Register arg1) {
        emitX86(INST_CODE.INST_MOV, arg0, arg1);
    }

  public final void mov(Mem arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_MOV, arg0, arg1);
    }

  public final void mov_ptr(Register arg0, long arg1) {
        if ($assertionsDisabled) {
            emitX86(INST_CODE.INST_MOV_PTR, arg0, Immediate.imm(arg1));
            return;
        } else {
            if (arg0.index() == 0) {
                emitX86(INST_CODE.INST_MOV_PTR, arg0, Immediate.imm(arg1));
                return;
            } else {
                throw new AssertionError();
            }
        }
    }

  public final void mov_ptr(long arg0, Register arg1) {
        if ($assertionsDisabled) {
            emitX86(INST_CODE.INST_MOV_PTR, Immediate.imm(arg0), arg1);
            return;
        } else {
            if (arg1.index() == 0) {
                emitX86(INST_CODE.INST_MOV_PTR, Immediate.imm(arg0), arg1);
                return;
            } else {
                throw new AssertionError();
            }
        }
    }

  public final void movsx(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_MOVSX, arg0, arg1);
    }

  public final void movsx(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_MOVSX, arg0, arg1);
    }

  public final void movsxd(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_MOVSXD, arg0, arg1);
    }

  public final void movsxd(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_MOVSXD, arg0, arg1);
    }

  public final void movzx(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_MOVZX, arg0, arg1);
    }

  public final void movzx(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_MOVZX, arg0, arg1);
    }

  public final void mul(Register arg0) {
        emitX86(INST_CODE.INST_MUL, arg0);
    }

  public final void mul(Mem arg0) {
        emitX86(INST_CODE.INST_MUL, arg0);
    }

  public final void neg(Register arg0) {
        emitX86(INST_CODE.INST_NEG, arg0);
    }

  public final void neg(Mem arg0) {
        emitX86(INST_CODE.INST_NEG, arg0);
    }

  public final void nop() {
        emitX86(INST_CODE.INST_NOP);
    }

  public final void not_(Register arg0) {
        emitX86(INST_CODE.INST_NOT, arg0);
    }

  public final void not_(Mem arg0) {
        emitX86(INST_CODE.INST_NOT, arg0);
    }

  public final void or_(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_OR, arg0, arg1);
    }

  public final void or_(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_OR, arg0, arg1);
    }

  public final void or_(Register arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_OR, arg0, arg1);
    }

  public final void or_(Mem arg0, Register arg1) {
        emitX86(INST_CODE.INST_OR, arg0, arg1);
    }

  public final void or_(Mem arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_OR, arg0, arg1);
    }

  public final void pop(Register arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: getstatic  #589 // jnr.x86asm.SerializerIntrinsics.$assertionsDisabled:Z
        //      3: ifne  44 (offset +41)
        //      6: aload_1
        //      7: bipush  16
        //      9: invokevirtual  #598 // jnr.x86asm.Register.isRegType:(I)Z
        //     12: ifne  44 (offset +32)
        //     15: aload_1
        //     16: aload_0
        //     17: invokevirtual  #614 // jnr.x86asm.SerializerIntrinsics.is64:()Z
        //     20: ifeq  28 (offset +8)
        //     23: bipush  48
        //     25: goto  30 (offset +5)
        //     28: bipush  32
        //     30: invokevirtual  #598 // jnr.x86asm.Register.isRegType:(I)Z
        //     33: ifne  44 (offset +11)
        //     36: new  #1 // java.lang.AssertionError
        //     39: dup
        //     40: invokespecial  #590 // java.lang.AssertionError.<init>:()V
        //     43: athrow
        //     44: aload_0
        //     45: getstatic  #460 // jnr.x86asm.INST_CODE.INST_POP:Ljnr/x86asm/INST_CODE;
        //     48: aload_1
        //     49: invokevirtual  #606 // jnr.x86asm.SerializerIntrinsics.emitX86:(Ljnr/x86asm/INST_CODE;Ljnr/x86asm/Operand;)V
        //     52: return
    }

  public final void pop(Mem arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: getstatic  #589 // jnr.x86asm.SerializerIntrinsics.$assertionsDisabled:Z
        //      3: ifne  42 (offset +39)
        //      6: aload_1
        //      7: invokevirtual  #595 // jnr.x86asm.Mem.size:()I
        //     10: iconst_2
        //     11: if_icmpeq  42 (offset +31)
        //     14: aload_1
        //     15: invokevirtual  #595 // jnr.x86asm.Mem.size:()I
        //     18: aload_0
        //     19: invokevirtual  #614 // jnr.x86asm.SerializerIntrinsics.is64:()Z
        //     22: ifeq  30 (offset +8)
        //     25: bipush  8
        //     27: goto  31 (offset +4)
        //     30: iconst_4
        //     31: if_icmpeq  42 (offset +11)
        //     34: new  #1 // java.lang.AssertionError
        //     37: dup
        //     38: invokespecial  #590 // java.lang.AssertionError.<init>:()V
        //     41: athrow
        //     42: aload_0
        //     43: getstatic  #460 // jnr.x86asm.INST_CODE.INST_POP:Ljnr/x86asm/INST_CODE;
        //     46: aload_1
        //     47: invokevirtual  #606 // jnr.x86asm.SerializerIntrinsics.emitX86:(Ljnr/x86asm/INST_CODE;Ljnr/x86asm/Operand;)V
        //     50: return
    }

  public final void popad() {
        emitX86(INST_CODE.INST_POPAD);
    }

  public final void popf() {
        if (is64()) {
            popfq();
        } else {
            popfd();
        }
    }

  public final void popfd() {
        emitX86(INST_CODE.INST_POPFD);
    }

  public final void popfq() {
        emitX86(INST_CODE.INST_POPFQ);
    }

  public final void push(Register arg0) {
        emitX86(INST_CODE.INST_PUSH, arg0);
    }

  public final void push(Mem arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: getstatic  #589 // jnr.x86asm.SerializerIntrinsics.$assertionsDisabled:Z
        //      3: ifne  42 (offset +39)
        //      6: aload_1
        //      7: invokevirtual  #595 // jnr.x86asm.Mem.size:()I
        //     10: iconst_2
        //     11: if_icmpeq  42 (offset +31)
        //     14: aload_1
        //     15: invokevirtual  #595 // jnr.x86asm.Mem.size:()I
        //     18: aload_0
        //     19: invokevirtual  #614 // jnr.x86asm.SerializerIntrinsics.is64:()Z
        //     22: ifeq  30 (offset +8)
        //     25: bipush  8
        //     27: goto  31 (offset +4)
        //     30: iconst_4
        //     31: if_icmpeq  42 (offset +11)
        //     34: new  #1 // java.lang.AssertionError
        //     37: dup
        //     38: invokespecial  #590 // java.lang.AssertionError.<init>:()V
        //     41: athrow
        //     42: aload_0
        //     43: getstatic  #504 // jnr.x86asm.INST_CODE.INST_PUSH:Ljnr/x86asm/INST_CODE;
        //     46: aload_1
        //     47: invokevirtual  #606 // jnr.x86asm.SerializerIntrinsics.emitX86:(Ljnr/x86asm/INST_CODE;Ljnr/x86asm/Operand;)V
        //     50: return
    }

  public final void push(Immediate arg0) {
        emitX86(INST_CODE.INST_PUSH, arg0);
    }

  public final void pushad() {
        emitX86(INST_CODE.INST_PUSHAD);
    }

  public final void pushf() {
        if (is64()) {
            pushfq();
        } else {
            pushfd();
        }
    }

  public final void pushfd() {
        emitX86(INST_CODE.INST_PUSHFD);
    }

  public final void pushfq() {
        emitX86(INST_CODE.INST_PUSHFQ);
    }

  public final void rcl(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_RCL, arg0, arg1);
    }

  public final void rcl(Register arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_RCL, arg0, arg1);
    }

  public final void rcl(Mem arg0, Register arg1) {
        emitX86(INST_CODE.INST_RCL, arg0, arg1);
    }

  public final void rcl(Mem arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_RCL, arg0, arg1);
    }

  public final void rcr(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_RCR, arg0, arg1);
    }

  public final void rcr(Register arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_RCR, arg0, arg1);
    }

  public final void rcr(Mem arg0, Register arg1) {
        emitX86(INST_CODE.INST_RCR, arg0, arg1);
    }

  public final void rcr(Mem arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_RCR, arg0, arg1);
    }

  public final void rdtsc() {
        emitX86(INST_CODE.INST_RDTSC);
    }

  public final void rdtscp() {
        emitX86(INST_CODE.INST_RDTSCP);
    }

  public final void ret() {
        emitX86(INST_CODE.INST_RET);
    }

  public final void ret(Immediate arg0) {
        emitX86(INST_CODE.INST_RET, arg0);
    }

  public final void rol(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_ROL, arg0, arg1);
    }

  public final void rol(Register arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_ROL, arg0, arg1);
    }

  public final void rol(Mem arg0, Register arg1) {
        emitX86(INST_CODE.INST_ROL, arg0, arg1);
    }

  public final void rol(Mem arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_ROL, arg0, arg1);
    }

  public final void ror(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_ROR, arg0, arg1);
    }

  public final void ror(Register arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_ROR, arg0, arg1);
    }

  public final void ror(Mem arg0, Register arg1) {
        emitX86(INST_CODE.INST_ROR, arg0, arg1);
    }

  public final void ror(Mem arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_ROR, arg0, arg1);
    }

  public final void sahf() {
        emitX86(INST_CODE.INST_SAHF);
    }

  public final void sbb(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_SBB, arg0, arg1);
    }

  public final void sbb(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_SBB, arg0, arg1);
    }

  public final void sbb(Register arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_SBB, arg0, arg1);
    }

  public final void sbb(Mem arg0, Register arg1) {
        emitX86(INST_CODE.INST_SBB, arg0, arg1);
    }

  public final void sbb(Mem arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_SBB, arg0, arg1);
    }

  public final void sal(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_SAL, arg0, arg1);
    }

  public final void sal(Register arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_SAL, arg0, arg1);
    }

  public final void sal(Mem arg0, Register arg1) {
        emitX86(INST_CODE.INST_SAL, arg0, arg1);
    }

  public final void sal(Mem arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_SAL, arg0, arg1);
    }

  public final void sar(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_SAR, arg0, arg1);
    }

  public final void sar(Register arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_SAR, arg0, arg1);
    }

  public final void sar(Mem arg0, Register arg1) {
        emitX86(INST_CODE.INST_SAR, arg0, arg1);
    }

  public final void sar(Mem arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_SAR, arg0, arg1);
    }

  public final void set(CONDITION arg0, Register arg1) {
        emitX86(conditionToSetCC(arg0), arg1);
    }

  public final void set(CONDITION arg0, Mem arg1) {
        emitX86(conditionToSetCC(arg0), arg1);
    }

  public final void seta(Register arg0) {
        emitX86(INST_CODE.INST_SETA, arg0);
    }

  public final void seta(Mem arg0) {
        emitX86(INST_CODE.INST_SETA, arg0);
    }

  public final void setae(Register arg0) {
        emitX86(INST_CODE.INST_SETAE, arg0);
    }

  public final void setae(Mem arg0) {
        emitX86(INST_CODE.INST_SETAE, arg0);
    }

  public final void setb(Register arg0) {
        emitX86(INST_CODE.INST_SETB, arg0);
    }

  public final void setb(Mem arg0) {
        emitX86(INST_CODE.INST_SETB, arg0);
    }

  public final void setbe(Register arg0) {
        emitX86(INST_CODE.INST_SETBE, arg0);
    }

  public final void setbe(Mem arg0) {
        emitX86(INST_CODE.INST_SETBE, arg0);
    }

  public final void setc(Register arg0) {
        emitX86(INST_CODE.INST_SETC, arg0);
    }

  public final void setc(Mem arg0) {
        emitX86(INST_CODE.INST_SETC, arg0);
    }

  public final void sete(Register arg0) {
        emitX86(INST_CODE.INST_SETE, arg0);
    }

  public final void sete(Mem arg0) {
        emitX86(INST_CODE.INST_SETE, arg0);
    }

  public final void setg(Register arg0) {
        emitX86(INST_CODE.INST_SETG, arg0);
    }

  public final void setg(Mem arg0) {
        emitX86(INST_CODE.INST_SETG, arg0);
    }

  public final void setge(Register arg0) {
        emitX86(INST_CODE.INST_SETGE, arg0);
    }

  public final void setge(Mem arg0) {
        emitX86(INST_CODE.INST_SETGE, arg0);
    }

  public final void setl(Register arg0) {
        emitX86(INST_CODE.INST_SETL, arg0);
    }

  public final void setl(Mem arg0) {
        emitX86(INST_CODE.INST_SETL, arg0);
    }

  public final void setle(Register arg0) {
        emitX86(INST_CODE.INST_SETLE, arg0);
    }

  public final void setle(Mem arg0) {
        emitX86(INST_CODE.INST_SETLE, arg0);
    }

  public final void setna(Register arg0) {
        emitX86(INST_CODE.INST_SETNA, arg0);
    }

  public final void setna(Mem arg0) {
        emitX86(INST_CODE.INST_SETNA, arg0);
    }

  public final void setnae(Register arg0) {
        emitX86(INST_CODE.INST_SETNAE, arg0);
    }

  public final void setnae(Mem arg0) {
        emitX86(INST_CODE.INST_SETNAE, arg0);
    }

  public final void setnb(Register arg0) {
        emitX86(INST_CODE.INST_SETNB, arg0);
    }

  public final void setnb(Mem arg0) {
        emitX86(INST_CODE.INST_SETNB, arg0);
    }

  public final void setnbe(Register arg0) {
        emitX86(INST_CODE.INST_SETNBE, arg0);
    }

  public final void setnbe(Mem arg0) {
        emitX86(INST_CODE.INST_SETNBE, arg0);
    }

  public final void setnc(Register arg0) {
        emitX86(INST_CODE.INST_SETNC, arg0);
    }

  public final void setnc(Mem arg0) {
        emitX86(INST_CODE.INST_SETNC, arg0);
    }

  public final void setne(Register arg0) {
        emitX86(INST_CODE.INST_SETNE, arg0);
    }

  public final void setne(Mem arg0) {
        emitX86(INST_CODE.INST_SETNE, arg0);
    }

  public final void setng(Register arg0) {
        emitX86(INST_CODE.INST_SETNG, arg0);
    }

  public final void setng(Mem arg0) {
        emitX86(INST_CODE.INST_SETNG, arg0);
    }

  public final void setnge(Register arg0) {
        emitX86(INST_CODE.INST_SETNGE, arg0);
    }

  public final void setnge(Mem arg0) {
        emitX86(INST_CODE.INST_SETNGE, arg0);
    }

  public final void setnl(Register arg0) {
        emitX86(INST_CODE.INST_SETNL, arg0);
    }

  public final void setnl(Mem arg0) {
        emitX86(INST_CODE.INST_SETNL, arg0);
    }

  public final void setnle(Register arg0) {
        emitX86(INST_CODE.INST_SETNLE, arg0);
    }

  public final void setnle(Mem arg0) {
        emitX86(INST_CODE.INST_SETNLE, arg0);
    }

  public final void setno(Register arg0) {
        emitX86(INST_CODE.INST_SETNO, arg0);
    }

  public final void setno(Mem arg0) {
        emitX86(INST_CODE.INST_SETNO, arg0);
    }

  public final void setnp(Register arg0) {
        emitX86(INST_CODE.INST_SETNP, arg0);
    }

  public final void setnp(Mem arg0) {
        emitX86(INST_CODE.INST_SETNP, arg0);
    }

  public final void setns(Register arg0) {
        emitX86(INST_CODE.INST_SETNS, arg0);
    }

  public final void setns(Mem arg0) {
        emitX86(INST_CODE.INST_SETNS, arg0);
    }

  public final void setnz(Register arg0) {
        emitX86(INST_CODE.INST_SETNZ, arg0);
    }

  public final void setnz(Mem arg0) {
        emitX86(INST_CODE.INST_SETNZ, arg0);
    }

  public final void seto(Register arg0) {
        emitX86(INST_CODE.INST_SETO, arg0);
    }

  public final void seto(Mem arg0) {
        emitX86(INST_CODE.INST_SETO, arg0);
    }

  public final void setp(Register arg0) {
        emitX86(INST_CODE.INST_SETP, arg0);
    }

  public final void setp(Mem arg0) {
        emitX86(INST_CODE.INST_SETP, arg0);
    }

  public final void setpe(Register arg0) {
        emitX86(INST_CODE.INST_SETPE, arg0);
    }

  public final void setpe(Mem arg0) {
        emitX86(INST_CODE.INST_SETPE, arg0);
    }

  public final void setpo(Register arg0) {
        emitX86(INST_CODE.INST_SETPO, arg0);
    }

  public final void setpo(Mem arg0) {
        emitX86(INST_CODE.INST_SETPO, arg0);
    }

  public final void sets(Register arg0) {
        emitX86(INST_CODE.INST_SETS, arg0);
    }

  public final void sets(Mem arg0) {
        emitX86(INST_CODE.INST_SETS, arg0);
    }

  public final void setz(Register arg0) {
        emitX86(INST_CODE.INST_SETZ, arg0);
    }

  public final void setz(Mem arg0) {
        emitX86(INST_CODE.INST_SETZ, arg0);
    }

  public final void shl(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_SHL, arg0, arg1);
    }

  public final void shl(Register arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_SHL, arg0, arg1);
    }

  public final void shl(Mem arg0, Register arg1) {
        emitX86(INST_CODE.INST_SHL, arg0, arg1);
    }

  public final void shl(Mem arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_SHL, arg0, arg1);
    }

  public final void shr(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_SHR, arg0, arg1);
    }

  public final void shr(Register arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_SHR, arg0, arg1);
    }

  public final void shr(Mem arg0, Register arg1) {
        emitX86(INST_CODE.INST_SHR, arg0, arg1);
    }

  public final void shr(Mem arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_SHR, arg0, arg1);
    }

  public final void shld(Register arg0, Register arg1, Register arg2) {
        emitX86(INST_CODE.INST_SHLD, arg0, arg1, arg2);
    }

  public final void shld(Register arg0, Register arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_SHLD, arg0, arg1, arg2);
    }

  public final void shld(Mem arg0, Register arg1, Register arg2) {
        emitX86(INST_CODE.INST_SHLD, arg0, arg1, arg2);
    }

  public final void shld(Mem arg0, Register arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_SHLD, arg0, arg1, arg2);
    }

  public final void shrd(Register arg0, Register arg1, Register arg2) {
        emitX86(INST_CODE.INST_SHRD, arg0, arg1, arg2);
    }

  public final void shrd(Register arg0, Register arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_SHRD, arg0, arg1, arg2);
    }

  public final void shrd(Mem arg0, Register arg1, Register arg2) {
        emitX86(INST_CODE.INST_SHRD, arg0, arg1, arg2);
    }

  public final void shrd(Mem arg0, Register arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_SHRD, arg0, arg1, arg2);
    }

  public final void stc() {
        emitX86(INST_CODE.INST_STC);
    }

  public final void std() {
        emitX86(INST_CODE.INST_STD);
    }

  public final void sub(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_SUB, arg0, arg1);
    }

  public final void sub(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_SUB, arg0, arg1);
    }

  public final void sub(Register arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_SUB, arg0, arg1);
    }

  public final void sub(Mem arg0, Register arg1) {
        emitX86(INST_CODE.INST_SUB, arg0, arg1);
    }

  public final void sub(Mem arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_SUB, arg0, arg1);
    }

  public final void test(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_TEST, arg0, arg1);
    }

  public final void test(Register arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_TEST, arg0, arg1);
    }

  public final void test(Mem arg0, Register arg1) {
        emitX86(INST_CODE.INST_TEST, arg0, arg1);
    }

  public final void test(Mem arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_TEST, arg0, arg1);
    }

  public final void ud2() {
        emitX86(INST_CODE.INST_UD2);
    }

  public final void xadd(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_XADD, arg0, arg1);
    }

  public final void xadd(Mem arg0, Register arg1) {
        emitX86(INST_CODE.INST_XADD, arg0, arg1);
    }

  public final void xchg(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_XCHG, arg0, arg1);
    }

  public final void xchg(Mem arg0, Register arg1) {
        emitX86(INST_CODE.INST_XCHG, arg0, arg1);
    }

  public final void xchg(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_XCHG, arg1, arg0);
    }

  public final void xor_(Register arg0, Register arg1) {
        emitX86(INST_CODE.INST_XOR, arg0, arg1);
    }

  public final void xor_(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_XOR, arg0, arg1);
    }

  public final void xor_(Register arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_XOR, arg0, arg1);
    }

  public final void xor_(Mem arg0, Register arg1) {
        emitX86(INST_CODE.INST_XOR, arg0, arg1);
    }

  public final void xor_(Mem arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_XOR, arg0, arg1);
    }

  public final void f2xm1() {
        emitX86(INST_CODE.INST_F2XM1);
    }

  public final void fabs() {
        emitX86(INST_CODE.INST_FABS);
    }

  public final void fadd(X87Register arg0, X87Register arg1) {
        if ($assertionsDisabled) {
            emitX86(INST_CODE.INST_FADD, arg0, arg1);
            return;
        } else {
            if (arg0.index() == 0) {
                emitX86(INST_CODE.INST_FADD, arg0, arg1);
                return;
            } else {
                if (arg1.index() == 0) {
                    emitX86(INST_CODE.INST_FADD, arg0, arg1);
                    return;
                } else {
                    throw new AssertionError();
                }
            }
        }
    }

  public final void fadd(Mem arg0) {
        emitX86(INST_CODE.INST_FADD, arg0);
    }

  public final void faddp(X87Register arg0) {
        emitX86(INST_CODE.INST_FADDP, arg0);
    }

  public final void faddp() {
        faddp(X87Register.st(1));
    }

  public final void fbld(Mem arg0) {
        emitX86(INST_CODE.INST_FBLD, arg0);
    }

  public final void fbstp(Mem arg0) {
        emitX86(INST_CODE.INST_FBSTP, arg0);
    }

  public final void fchs() {
        emitX86(INST_CODE.INST_FCHS);
    }

  public final void fclex() {
        emitX86(INST_CODE.INST_FCLEX);
    }

  public final void fcmovb(X87Register arg0) {
        emitX86(INST_CODE.INST_FCMOVB, arg0);
    }

  public final void fcmovbe(X87Register arg0) {
        emitX86(INST_CODE.INST_FCMOVBE, arg0);
    }

  public final void fcmove(X87Register arg0) {
        emitX86(INST_CODE.INST_FCMOVE, arg0);
    }

  public final void fcmovnb(X87Register arg0) {
        emitX86(INST_CODE.INST_FCMOVNB, arg0);
    }

  public final void fcmovnbe(X87Register arg0) {
        emitX86(INST_CODE.INST_FCMOVNBE, arg0);
    }

  public final void fcmovne(X87Register arg0) {
        emitX86(INST_CODE.INST_FCMOVNE, arg0);
    }

  public final void fcmovnu(X87Register arg0) {
        emitX86(INST_CODE.INST_FCMOVNU, arg0);
    }

  public final void fcmovu(X87Register arg0) {
        emitX86(INST_CODE.INST_FCMOVU, arg0);
    }

  public final void fcom(X87Register arg0) {
        emitX86(INST_CODE.INST_FCOM, arg0);
    }

  public final void fcom() {
        fcom(X87Register.st(1));
    }

  public final void fcom(Mem arg0) {
        emitX86(INST_CODE.INST_FCOM, arg0);
    }

  public final void fcomp(X87Register arg0) {
        emitX86(INST_CODE.INST_FCOMP, arg0);
    }

  public final void fcomp() {
        fcomp(X87Register.st(1));
    }

  public final void fcomp(Mem arg0) {
        emitX86(INST_CODE.INST_FCOMP, arg0);
    }

  public final void fcompp() {
        emitX86(INST_CODE.INST_FCOMPP);
    }

  public final void fcomi(X87Register arg0) {
        emitX86(INST_CODE.INST_FCOMI, arg0);
    }

  public final void fcomip(X87Register arg0) {
        emitX86(INST_CODE.INST_FCOMIP, arg0);
    }

  public final void fcos() {
        emitX86(INST_CODE.INST_FCOS);
    }

  public final void fdecstp() {
        emitX86(INST_CODE.INST_FDECSTP);
    }

  public final void fdiv(X87Register arg0, X87Register arg1) {
        if ($assertionsDisabled) {
            emitX86(INST_CODE.INST_FDIV, arg0, arg1);
            return;
        } else {
            if (arg0.index() == 0) {
                emitX86(INST_CODE.INST_FDIV, arg0, arg1);
                return;
            } else {
                if (arg1.index() == 0) {
                    emitX86(INST_CODE.INST_FDIV, arg0, arg1);
                    return;
                } else {
                    throw new AssertionError();
                }
            }
        }
    }

  public final void fdiv(Mem arg0) {
        emitX86(INST_CODE.INST_FDIV, arg0);
    }

  public final void fdivp(X87Register arg0) {
        emitX86(INST_CODE.INST_FDIVP, arg0);
    }

  public final void fdivp() {
        fdivp(X87Register.st(1));
    }

  public final void fdivr(X87Register arg0, X87Register arg1) {
        if ($assertionsDisabled) {
            emitX86(INST_CODE.INST_FDIVR, arg0, arg1);
            return;
        } else {
            if (arg0.index() == 0) {
                emitX86(INST_CODE.INST_FDIVR, arg0, arg1);
                return;
            } else {
                if (arg1.index() == 0) {
                    emitX86(INST_CODE.INST_FDIVR, arg0, arg1);
                    return;
                } else {
                    throw new AssertionError();
                }
            }
        }
    }

  public final void fdivr(Mem arg0) {
        emitX86(INST_CODE.INST_FDIVR, arg0);
    }

  public final void fdivrp(X87Register arg0) {
        emitX86(INST_CODE.INST_FDIVRP, arg0);
    }

  public final void fdivrp() {
        emitX86(INST_CODE.INST_FDIVRP, X87Register.st(1));
    }

  public final void ffree(X87Register arg0) {
        emitX86(INST_CODE.INST_FFREE, arg0);
    }

  public final void fiadd(Mem arg0) {
        if ($assertionsDisabled) {
            emitX86(INST_CODE.INST_FIADD, arg0);
            return;
        } else {
            if (arg0.size() == 2) {
                emitX86(INST_CODE.INST_FIADD, arg0);
                return;
            } else {
                if (arg0.size() == 4) {
                    emitX86(INST_CODE.INST_FIADD, arg0);
                    return;
                } else {
                    throw new AssertionError();
                }
            }
        }
    }

  public final void ficom(Mem arg0) {
        if ($assertionsDisabled) {
            emitX86(INST_CODE.INST_FICOM, arg0);
            return;
        } else {
            if (arg0.size() == 2) {
                emitX86(INST_CODE.INST_FICOM, arg0);
                return;
            } else {
                if (arg0.size() == 4) {
                    emitX86(INST_CODE.INST_FICOM, arg0);
                    return;
                } else {
                    throw new AssertionError();
                }
            }
        }
    }

  public final void ficomp(Mem arg0) {
        if ($assertionsDisabled) {
            emitX86(INST_CODE.INST_FICOMP, arg0);
            return;
        } else {
            if (arg0.size() == 2) {
                emitX86(INST_CODE.INST_FICOMP, arg0);
                return;
            } else {
                if (arg0.size() == 4) {
                    emitX86(INST_CODE.INST_FICOMP, arg0);
                    return;
                } else {
                    throw new AssertionError();
                }
            }
        }
    }

  public final void fidiv(Mem arg0) {
        if ($assertionsDisabled) {
            emitX86(INST_CODE.INST_FIDIV, arg0);
            return;
        } else {
            if (arg0.size() == 2) {
                emitX86(INST_CODE.INST_FIDIV, arg0);
                return;
            } else {
                if (arg0.size() == 4) {
                    emitX86(INST_CODE.INST_FIDIV, arg0);
                    return;
                } else {
                    throw new AssertionError();
                }
            }
        }
    }

  public final void fidivr(Mem arg0) {
        if ($assertionsDisabled) {
            emitX86(INST_CODE.INST_FIDIVR, arg0);
            return;
        } else {
            if (arg0.size() == 2) {
                emitX86(INST_CODE.INST_FIDIVR, arg0);
                return;
            } else {
                if (arg0.size() == 4) {
                    emitX86(INST_CODE.INST_FIDIVR, arg0);
                    return;
                } else {
                    throw new AssertionError();
                }
            }
        }
    }

  public final void fild(Mem arg0) {
        if ($assertionsDisabled) {
            emitX86(INST_CODE.INST_FILD, arg0);
            return;
        } else {
            if (arg0.size() == 2) {
                emitX86(INST_CODE.INST_FILD, arg0);
                return;
            } else {
                if (arg0.size() == 4) {
                    emitX86(INST_CODE.INST_FILD, arg0);
                    return;
                } else {
                    if (arg0.size() == 8) {
                        emitX86(INST_CODE.INST_FILD, arg0);
                        return;
                    } else {
                        throw new AssertionError();
                    }
                }
            }
        }
    }

  public final void fimul(Mem arg0) {
        if ($assertionsDisabled) {
            emitX86(INST_CODE.INST_FIMUL, arg0);
            return;
        } else {
            if (arg0.size() == 2) {
                emitX86(INST_CODE.INST_FIMUL, arg0);
                return;
            } else {
                if (arg0.size() == 4) {
                    emitX86(INST_CODE.INST_FIMUL, arg0);
                    return;
                } else {
                    throw new AssertionError();
                }
            }
        }
    }

  public final void fincstp() {
        emitX86(INST_CODE.INST_FINCSTP);
    }

  public final void finit() {
        emitX86(INST_CODE.INST_FINIT);
    }

  public final void fisub(Mem arg0) {
        if ($assertionsDisabled) {
            emitX86(INST_CODE.INST_FISUB, arg0);
            return;
        } else {
            if (arg0.size() == 2) {
                emitX86(INST_CODE.INST_FISUB, arg0);
                return;
            } else {
                if (arg0.size() == 4) {
                    emitX86(INST_CODE.INST_FISUB, arg0);
                    return;
                } else {
                    throw new AssertionError();
                }
            }
        }
    }

  public final void fisubr(Mem arg0) {
        if ($assertionsDisabled) {
            emitX86(INST_CODE.INST_FISUBR, arg0);
            return;
        } else {
            if (arg0.size() == 2) {
                emitX86(INST_CODE.INST_FISUBR, arg0);
                return;
            } else {
                if (arg0.size() == 4) {
                    emitX86(INST_CODE.INST_FISUBR, arg0);
                    return;
                } else {
                    throw new AssertionError();
                }
            }
        }
    }

  public final void fninit() {
        emitX86(INST_CODE.INST_FNINIT);
    }

  public final void fist(Mem arg0) {
        if ($assertionsDisabled) {
            emitX86(INST_CODE.INST_FIST, arg0);
            return;
        } else {
            if (arg0.size() == 2) {
                emitX86(INST_CODE.INST_FIST, arg0);
                return;
            } else {
                if (arg0.size() == 4) {
                    emitX86(INST_CODE.INST_FIST, arg0);
                    return;
                } else {
                    throw new AssertionError();
                }
            }
        }
    }

  public final void fistp(Mem arg0) {
        if ($assertionsDisabled) {
            emitX86(INST_CODE.INST_FISTP, arg0);
            return;
        } else {
            if (arg0.size() == 2) {
                emitX86(INST_CODE.INST_FISTP, arg0);
                return;
            } else {
                if (arg0.size() == 4) {
                    emitX86(INST_CODE.INST_FISTP, arg0);
                    return;
                } else {
                    if (arg0.size() == 8) {
                        emitX86(INST_CODE.INST_FISTP, arg0);
                        return;
                    } else {
                        throw new AssertionError();
                    }
                }
            }
        }
    }

  public final void fld(Mem arg0) {
        if ($assertionsDisabled) {
            emitX86(INST_CODE.INST_FLD, arg0);
            return;
        } else {
            if (arg0.size() == 4) {
                emitX86(INST_CODE.INST_FLD, arg0);
                return;
            } else {
                if (arg0.size() == 8) {
                    emitX86(INST_CODE.INST_FLD, arg0);
                    return;
                } else {
                    if (arg0.size() == 10) {
                        emitX86(INST_CODE.INST_FLD, arg0);
                        return;
                    } else {
                        throw new AssertionError();
                    }
                }
            }
        }
    }

  public final void fld(X87Register arg0) {
        emitX86(INST_CODE.INST_FLD, arg0);
    }

  public final void fld1() {
        emitX86(INST_CODE.INST_FLD1);
    }

  public final void fldl2t() {
        emitX86(INST_CODE.INST_FLDL2T);
    }

  public final void fldl2e() {
        emitX86(INST_CODE.INST_FLDL2E);
    }

  public final void fldpi() {
        emitX86(INST_CODE.INST_FLDPI);
    }

  public final void fldlg2() {
        emitX86(INST_CODE.INST_FLDLG2);
    }

  public final void fldln2() {
        emitX86(INST_CODE.INST_FLDLN2);
    }

  public final void fldz() {
        emitX86(INST_CODE.INST_FLDZ);
    }

  public final void fldcw(Mem arg0) {
        emitX86(INST_CODE.INST_FLDCW, arg0);
    }

  public final void fldenv(Mem arg0) {
        emitX86(INST_CODE.INST_FLDENV, arg0);
    }

  public final void fmul(X87Register arg0, X87Register arg1) {
        if ($assertionsDisabled) {
            emitX86(INST_CODE.INST_FMUL, arg0, arg1);
            return;
        } else {
            if (arg0.index() == 0) {
                emitX86(INST_CODE.INST_FMUL, arg0, arg1);
                return;
            } else {
                if (arg1.index() == 0) {
                    emitX86(INST_CODE.INST_FMUL, arg0, arg1);
                    return;
                } else {
                    throw new AssertionError();
                }
            }
        }
    }

  public final void fmul(Mem arg0) {
        emitX86(INST_CODE.INST_FMUL, arg0);
    }

  public final void fmulp(X87Register arg0) {
        emitX86(INST_CODE.INST_FMULP, arg0);
    }

  public final void fmulp() {
        fmulp(X87Register.st(1));
    }

  public final void fnclex() {
        emitX86(INST_CODE.INST_FNCLEX);
    }

  public final void fnop() {
        emitX86(INST_CODE.INST_FNOP);
    }

  public final void fnsave(Mem arg0) {
        emitX86(INST_CODE.INST_FNSAVE, arg0);
    }

  public final void fnstenv(Mem arg0) {
        emitX86(INST_CODE.INST_FNSTENV, arg0);
    }

  public final void fnstcw(Mem arg0) {
        emitX86(INST_CODE.INST_FNSTCW, arg0);
    }

  public final void fnstsw(Register arg0) {
        if ($assertionsDisabled) {
            emitX86(INST_CODE.INST_FNSTSW, arg0);
            return;
        } else {
            if (arg0.isRegCode(16)) {
                emitX86(INST_CODE.INST_FNSTSW, arg0);
                return;
            } else {
                throw new AssertionError();
            }
        }
    }

  public final void fnstsw(Mem arg0) {
        emitX86(INST_CODE.INST_FNSTSW, arg0);
    }

  public final void fpatan() {
        emitX86(INST_CODE.INST_FPATAN);
    }

  public final void fprem() {
        emitX86(INST_CODE.INST_FPREM);
    }

  public final void fprem1() {
        emitX86(INST_CODE.INST_FPREM1);
    }

  public final void fptan() {
        emitX86(INST_CODE.INST_FPTAN);
    }

  public final void frndint() {
        emitX86(INST_CODE.INST_FRNDINT);
    }

  public final void frstor(Mem arg0) {
        emitX86(INST_CODE.INST_FRSTOR, arg0);
    }

  public final void fsave(Mem arg0) {
        emitX86(INST_CODE.INST_FSAVE, arg0);
    }

  public final void fscale() {
        emitX86(INST_CODE.INST_FSCALE);
    }

  public final void fsin() {
        emitX86(INST_CODE.INST_FSIN);
    }

  public final void fsincos() {
        emitX86(INST_CODE.INST_FSINCOS);
    }

  public final void fsqrt() {
        emitX86(INST_CODE.INST_FSQRT);
    }

  public final void fst(Mem arg0) {
        if ($assertionsDisabled) {
            emitX86(INST_CODE.INST_FST, arg0);
            return;
        } else {
            if (arg0.size() == 4) {
                emitX86(INST_CODE.INST_FST, arg0);
                return;
            } else {
                if (arg0.size() == 8) {
                    emitX86(INST_CODE.INST_FST, arg0);
                    return;
                } else {
                    throw new AssertionError();
                }
            }
        }
    }

  public final void fst(X87Register arg0) {
        emitX86(INST_CODE.INST_FST, arg0);
    }

  public final void fstp(Mem arg0) {
        if ($assertionsDisabled) {
            emitX86(INST_CODE.INST_FSTP, arg0);
            return;
        } else {
            if (arg0.size() == 4) {
                emitX86(INST_CODE.INST_FSTP, arg0);
                return;
            } else {
                if (arg0.size() == 8) {
                    emitX86(INST_CODE.INST_FSTP, arg0);
                    return;
                } else {
                    if (arg0.size() == 10) {
                        emitX86(INST_CODE.INST_FSTP, arg0);
                        return;
                    } else {
                        throw new AssertionError();
                    }
                }
            }
        }
    }

  public final void fstp(X87Register arg0) {
        emitX86(INST_CODE.INST_FSTP, arg0);
    }

  public final void fstcw(Mem arg0) {
        emitX86(INST_CODE.INST_FSTCW, arg0);
    }

  public final void fstenv(Mem arg0) {
        emitX86(INST_CODE.INST_FSTENV, arg0);
    }

  public final void fstsw(Register arg0) {
        if ($assertionsDisabled) {
            emitX86(INST_CODE.INST_FSTSW, arg0);
            return;
        } else {
            if (arg0.isRegCode(16)) {
                emitX86(INST_CODE.INST_FSTSW, arg0);
                return;
            } else {
                throw new AssertionError();
            }
        }
    }

  public final void fstsw(Mem arg0) {
        emitX86(INST_CODE.INST_FSTSW, arg0);
    }

  public final void fsub(X87Register arg0, X87Register arg1) {
        if ($assertionsDisabled) {
            emitX86(INST_CODE.INST_FSUB, arg0, arg1);
            return;
        } else {
            if (arg0.index() == 0) {
                emitX86(INST_CODE.INST_FSUB, arg0, arg1);
                return;
            } else {
                if (arg1.index() == 0) {
                    emitX86(INST_CODE.INST_FSUB, arg0, arg1);
                    return;
                } else {
                    throw new AssertionError();
                }
            }
        }
    }

  public final void fsub(Mem arg0) {
        if ($assertionsDisabled) {
            emitX86(INST_CODE.INST_FSUB, arg0);
            return;
        } else {
            if (arg0.size() == 4) {
                emitX86(INST_CODE.INST_FSUB, arg0);
                return;
            } else {
                if (arg0.size() == 8) {
                    emitX86(INST_CODE.INST_FSUB, arg0);
                    return;
                } else {
                    throw new AssertionError();
                }
            }
        }
    }

  public final void fsubp(X87Register arg0) {
        emitX86(INST_CODE.INST_FSUBP, arg0);
    }

  public final void fsubp() {
        emitX86(INST_CODE.INST_FSUBP, X87Register.st(1));
    }

  public final void fsubr(X87Register arg0, X87Register arg1) {
        if ($assertionsDisabled) {
            emitX86(INST_CODE.INST_FSUBR, arg0, arg1);
            return;
        } else {
            if (arg0.index() == 0) {
                emitX86(INST_CODE.INST_FSUBR, arg0, arg1);
                return;
            } else {
                if (arg1.index() == 0) {
                    emitX86(INST_CODE.INST_FSUBR, arg0, arg1);
                    return;
                } else {
                    throw new AssertionError();
                }
            }
        }
    }

  public final void fsubr(Mem arg0) {
        if ($assertionsDisabled) {
            emitX86(INST_CODE.INST_FSUBR, arg0);
            return;
        } else {
            if (arg0.size() == 4) {
                emitX86(INST_CODE.INST_FSUBR, arg0);
                return;
            } else {
                if (arg0.size() == 8) {
                    emitX86(INST_CODE.INST_FSUBR, arg0);
                    return;
                } else {
                    throw new AssertionError();
                }
            }
        }
    }

  public final void fsubrp(X87Register arg0) {
        emitX86(INST_CODE.INST_FSUBRP, arg0);
    }

  public final void fsubrp() {
        emitX86(INST_CODE.INST_FSUBRP, X87Register.st(1));
    }

  public final void ftst() {
        emitX86(INST_CODE.INST_FTST);
    }

  public final void fucom(X87Register arg0) {
        emitX86(INST_CODE.INST_FUCOM, arg0);
    }

  public final void fucom() {
        emitX86(INST_CODE.INST_FUCOM, X87Register.st(1));
    }

  public final void fucomi(X87Register arg0) {
        emitX86(INST_CODE.INST_FUCOMI, arg0);
    }

  public final void fucomip(X87Register arg0) {
        emitX86(INST_CODE.INST_FUCOMIP, arg0);
    }

  public final void fucomip() {
        emitX86(INST_CODE.INST_FUCOMIP, X87Register.st(1));
    }

  public final void fucomp(X87Register arg0) {
        emitX86(INST_CODE.INST_FUCOMP, arg0);
    }

  public final void fucomp() {
        emitX86(INST_CODE.INST_FUCOMP, X87Register.st(1));
    }

  public final void fucompp() {
        emitX86(INST_CODE.INST_FUCOMPP);
    }

  public final void fwait() {
        emitX86(INST_CODE.INST_FWAIT);
    }

  public final void fxam() {
        emitX86(INST_CODE.INST_FXAM);
    }

  public final void fxch(X87Register arg0) {
        emitX86(INST_CODE.INST_FXCH, arg0);
    }

  public final void fxch() {
        emitX86(INST_CODE.INST_FXCH, X87Register.st(1));
    }

  public final void fxrstor(Mem arg0) {
        emitX86(INST_CODE.INST_FXRSTOR, arg0);
    }

  public final void fxsave(Mem arg0) {
        emitX86(INST_CODE.INST_FXSAVE, arg0);
    }

  public final void fxtract() {
        emitX86(INST_CODE.INST_FXTRACT);
    }

  public final void fyl2x() {
        emitX86(INST_CODE.INST_FYL2X);
    }

  public final void fyl2xp1() {
        emitX86(INST_CODE.INST_FYL2XP1);
    }

  public final void emms() {
        emitX86(INST_CODE.INST_EMMS);
    }

  public final void movd(Mem arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_MOVD, arg0, arg1);
    }

  public final void movd(Register arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_MOVD, arg0, arg1);
    }

  public final void movd(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_MOVD, arg0, arg1);
    }

  public final void movd(MMRegister arg0, Register arg1) {
        emitX86(INST_CODE.INST_MOVD, arg0, arg1);
    }

  public final void movq(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_MOVQ, arg0, arg1);
    }

  public final void movq(Mem arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_MOVQ, arg0, arg1);
    }

  public final void movq(Register arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_MOVQ, arg0, arg1);
    }

  public final void movq(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_MOVQ, arg0, arg1);
    }

  public final void movq(MMRegister arg0, Register arg1) {
        emitX86(INST_CODE.INST_MOVQ, arg0, arg1);
    }

  public final void packuswb(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PACKUSWB, arg0, arg1);
    }

  public final void packuswb(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PACKUSWB, arg0, arg1);
    }

  public final void paddb(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PADDB, arg0, arg1);
    }

  public final void paddb(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PADDB, arg0, arg1);
    }

  public final void paddw(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PADDW, arg0, arg1);
    }

  public final void paddw(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PADDW, arg0, arg1);
    }

  public final void paddd(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PADDD, arg0, arg1);
    }

  public final void paddd(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PADDD, arg0, arg1);
    }

  public final void paddsb(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PADDSB, arg0, arg1);
    }

  public final void paddsb(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PADDSB, arg0, arg1);
    }

  public final void paddsw(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PADDSW, arg0, arg1);
    }

  public final void paddsw(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PADDSW, arg0, arg1);
    }

  public final void paddusb(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PADDUSB, arg0, arg1);
    }

  public final void paddusb(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PADDUSB, arg0, arg1);
    }

  public final void paddusw(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PADDUSW, arg0, arg1);
    }

  public final void paddusw(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PADDUSW, arg0, arg1);
    }

  public final void pand(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PAND, arg0, arg1);
    }

  public final void pand(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PAND, arg0, arg1);
    }

  public final void pandn(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PANDN, arg0, arg1);
    }

  public final void pandn(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PANDN, arg0, arg1);
    }

  public final void pcmpeqb(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PCMPEQB, arg0, arg1);
    }

  public final void pcmpeqb(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PCMPEQB, arg0, arg1);
    }

  public final void pcmpeqw(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PCMPEQW, arg0, arg1);
    }

  public final void pcmpeqw(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PCMPEQW, arg0, arg1);
    }

  public final void pcmpeqd(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PCMPEQD, arg0, arg1);
    }

  public final void pcmpeqd(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PCMPEQD, arg0, arg1);
    }

  public final void pcmpgtb(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PCMPGTB, arg0, arg1);
    }

  public final void pcmpgtb(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PCMPGTB, arg0, arg1);
    }

  public final void pcmpgtw(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PCMPGTW, arg0, arg1);
    }

  public final void pcmpgtw(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PCMPGTW, arg0, arg1);
    }

  public final void pcmpgtd(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PCMPGTD, arg0, arg1);
    }

  public final void pcmpgtd(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PCMPGTD, arg0, arg1);
    }

  public final void pmulhw(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PMULHW, arg0, arg1);
    }

  public final void pmulhw(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMULHW, arg0, arg1);
    }

  public final void pmullw(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PMULLW, arg0, arg1);
    }

  public final void pmullw(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMULLW, arg0, arg1);
    }

  public final void por(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_POR, arg0, arg1);
    }

  public final void por(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_POR, arg0, arg1);
    }

  public final void pmaddwd(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PMADDWD, arg0, arg1);
    }

  public final void pmaddwd(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMADDWD, arg0, arg1);
    }

  public final void pslld(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PSLLD, arg0, arg1);
    }

  public final void pslld(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSLLD, arg0, arg1);
    }

  public final void pslld(MMRegister arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_PSLLD, arg0, arg1);
    }

  public final void psllq(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PSLLQ, arg0, arg1);
    }

  public final void psllq(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSLLQ, arg0, arg1);
    }

  public final void psllq(MMRegister arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_PSLLQ, arg0, arg1);
    }

  public final void psllw(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PSLLW, arg0, arg1);
    }

  public final void psllw(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSLLW, arg0, arg1);
    }

  public final void psllw(MMRegister arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_PSLLW, arg0, arg1);
    }

  public final void psrad(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PSRAD, arg0, arg1);
    }

  public final void psrad(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSRAD, arg0, arg1);
    }

  public final void psrad(MMRegister arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_PSRAD, arg0, arg1);
    }

  public final void psraw(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PSRAW, arg0, arg1);
    }

  public final void psraw(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSRAW, arg0, arg1);
    }

  public final void psraw(MMRegister arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_PSRAW, arg0, arg1);
    }

  public final void psrld(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PSRLD, arg0, arg1);
    }

  public final void psrld(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSRLD, arg0, arg1);
    }

  public final void psrld(MMRegister arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_PSRLD, arg0, arg1);
    }

  public final void psrlq(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PSRLQ, arg0, arg1);
    }

  public final void psrlq(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSRLQ, arg0, arg1);
    }

  public final void psrlq(MMRegister arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_PSRLQ, arg0, arg1);
    }

  public final void psrlw(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PSRLW, arg0, arg1);
    }

  public final void psrlw(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSRLW, arg0, arg1);
    }

  public final void psrlw(MMRegister arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_PSRLW, arg0, arg1);
    }

  public final void psubb(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PSUBB, arg0, arg1);
    }

  public final void psubb(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSUBB, arg0, arg1);
    }

  public final void psubw(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PSUBW, arg0, arg1);
    }

  public final void psubw(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSUBW, arg0, arg1);
    }

  public final void psubd(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PSUBD, arg0, arg1);
    }

  public final void psubd(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSUBD, arg0, arg1);
    }

  public final void psubsb(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PSUBSB, arg0, arg1);
    }

  public final void psubsb(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSUBSB, arg0, arg1);
    }

  public final void psubsw(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PSUBSW, arg0, arg1);
    }

  public final void psubsw(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSUBSW, arg0, arg1);
    }

  public final void psubusb(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PSUBUSB, arg0, arg1);
    }

  public final void psubusb(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSUBUSB, arg0, arg1);
    }

  public final void psubusw(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PSUBUSW, arg0, arg1);
    }

  public final void psubusw(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSUBUSW, arg0, arg1);
    }

  public final void punpckhbw(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PUNPCKHBW, arg0, arg1);
    }

  public final void punpckhbw(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PUNPCKHBW, arg0, arg1);
    }

  public final void punpckhwd(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PUNPCKHWD, arg0, arg1);
    }

  public final void punpckhwd(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PUNPCKHWD, arg0, arg1);
    }

  public final void punpckhdq(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PUNPCKHDQ, arg0, arg1);
    }

  public final void punpckhdq(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PUNPCKHDQ, arg0, arg1);
    }

  public final void punpcklbw(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PUNPCKLBW, arg0, arg1);
    }

  public final void punpcklbw(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PUNPCKLBW, arg0, arg1);
    }

  public final void punpcklwd(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PUNPCKLWD, arg0, arg1);
    }

  public final void punpcklwd(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PUNPCKLWD, arg0, arg1);
    }

  public final void punpckldq(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PUNPCKLDQ, arg0, arg1);
    }

  public final void punpckldq(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PUNPCKLDQ, arg0, arg1);
    }

  public final void pxor(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PXOR, arg0, arg1);
    }

  public final void pxor(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PXOR, arg0, arg1);
    }

  public final void femms() {
        emitX86(INST_CODE.INST_FEMMS);
    }

  public final void pf2id(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PF2ID, arg0, arg1);
    }

  public final void pf2id(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PF2ID, arg0, arg1);
    }

  public final void pf2iw(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PF2IW, arg0, arg1);
    }

  public final void pf2iw(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PF2IW, arg0, arg1);
    }

  public final void pfacc(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PFACC, arg0, arg1);
    }

  public final void pfacc(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PFACC, arg0, arg1);
    }

  public final void pfadd(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PFADD, arg0, arg1);
    }

  public final void pfadd(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PFADD, arg0, arg1);
    }

  public final void pfcmpeq(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PFCMPEQ, arg0, arg1);
    }

  public final void pfcmpeq(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PFCMPEQ, arg0, arg1);
    }

  public final void pfcmpge(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PFCMPGE, arg0, arg1);
    }

  public final void pfcmpge(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PFCMPGE, arg0, arg1);
    }

  public final void pfcmpgt(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PFCMPGT, arg0, arg1);
    }

  public final void pfcmpgt(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PFCMPGT, arg0, arg1);
    }

  public final void pfmax(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PFMAX, arg0, arg1);
    }

  public final void pfmax(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PFMAX, arg0, arg1);
    }

  public final void pfmin(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PFMIN, arg0, arg1);
    }

  public final void pfmin(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PFMIN, arg0, arg1);
    }

  public final void pfmul(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PFMUL, arg0, arg1);
    }

  public final void pfmul(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PFMUL, arg0, arg1);
    }

  public final void pfnacc(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PFNACC, arg0, arg1);
    }

  public final void pfnacc(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PFNACC, arg0, arg1);
    }

  public final void pfpnaxx(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PFPNACC, arg0, arg1);
    }

  public final void pfpnacc(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PFPNACC, arg0, arg1);
    }

  public final void pfrcp(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PFRCP, arg0, arg1);
    }

  public final void pfrcp(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PFRCP, arg0, arg1);
    }

  public final void pfrcpit1(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PFRCPIT1, arg0, arg1);
    }

  public final void pfrcpit1(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PFRCPIT1, arg0, arg1);
    }

  public final void pfrcpit2(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PFRCPIT2, arg0, arg1);
    }

  public final void pfrcpit2(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PFRCPIT2, arg0, arg1);
    }

  public final void pfrsqit1(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PFRSQIT1, arg0, arg1);
    }

  public final void pfrsqit1(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PFRSQIT1, arg0, arg1);
    }

  public final void pfrsqrt(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PFRSQRT, arg0, arg1);
    }

  public final void pfrsqrt(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PFRSQRT, arg0, arg1);
    }

  public final void pfsub(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PFSUB, arg0, arg1);
    }

  public final void pfsub(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PFSUB, arg0, arg1);
    }

  public final void pfsubr(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PFSUBR, arg0, arg1);
    }

  public final void pfsubr(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PFSUBR, arg0, arg1);
    }

  public final void pi2fd(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PI2FD, arg0, arg1);
    }

  public final void pi2fd(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PI2FD, arg0, arg1);
    }

  public final void pi2fw(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PI2FW, arg0, arg1);
    }

  public final void pi2fw(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PI2FW, arg0, arg1);
    }

  public final void pswapd(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PSWAPD, arg0, arg1);
    }

  public final void pswapd(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSWAPD, arg0, arg1);
    }

  public final void addps(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_ADDPS, arg0, arg1);
    }

  public final void addps(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_ADDPS, arg0, arg1);
    }

  public final void addss(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_ADDSS, arg0, arg1);
    }

  public final void addss(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_ADDSS, arg0, arg1);
    }

  public final void andnps(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_ANDNPS, arg0, arg1);
    }

  public final void andnps(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_ANDNPS, arg0, arg1);
    }

  public final void andps(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_ANDPS, arg0, arg1);
    }

  public final void andps(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_ANDPS, arg0, arg1);
    }

  public final void cmpps(XMMRegister arg0, XMMRegister arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_CMPPS, arg0, arg1, arg2);
    }

  public final void cmpps(XMMRegister arg0, Mem arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_CMPPS, arg0, arg1, arg2);
    }

  public final void cmpss(XMMRegister arg0, XMMRegister arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_CMPSS, arg0, arg1, arg2);
    }

  public final void cmpss(XMMRegister arg0, Mem arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_CMPSS, arg0, arg1, arg2);
    }

  public final void comiss(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_COMISS, arg0, arg1);
    }

  public final void comiss(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_COMISS, arg0, arg1);
    }

  public final void cvtpi2ps(XMMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_CVTPI2PS, arg0, arg1);
    }

  public final void cvtpi2ps(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CVTPI2PS, arg0, arg1);
    }

  public final void cvtps2pi(MMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_CVTPS2PI, arg0, arg1);
    }

  public final void cvtps2pi(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CVTPS2PI, arg0, arg1);
    }

  public final void cvtsi2ss(XMMRegister arg0, Register arg1) {
        emitX86(INST_CODE.INST_CVTSI2SS, arg0, arg1);
    }

  public final void cvtsi2ss(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CVTSI2SS, arg0, arg1);
    }

  public final void cvtss2si(Register arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_CVTSS2SI, arg0, arg1);
    }

  public final void cvtss2si(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CVTSS2SI, arg0, arg1);
    }

  public final void cvttps2pi(MMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_CVTTPS2PI, arg0, arg1);
    }

  public final void cvttps2pi(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CVTTPS2PI, arg0, arg1);
    }

  public final void cvttss2si(Register arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_CVTTSS2SI, arg0, arg1);
    }

  public final void cvttss2si(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CVTTSS2SI, arg0, arg1);
    }

  public final void divps(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_DIVPS, arg0, arg1);
    }

  public final void divps(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_DIVPS, arg0, arg1);
    }

  public final void divss(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_DIVSS, arg0, arg1);
    }

  public final void divss(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_DIVSS, arg0, arg1);
    }

  public final void ldmxcsr(Mem arg0) {
        emitX86(INST_CODE.INST_LDMXCSR, arg0);
    }

  public final void maskmovq(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_MASKMOVQ, arg0, arg1);
    }

  public final void maxps(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MAXPS, arg0, arg1);
    }

  public final void maxps(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_MAXPS, arg0, arg1);
    }

  public final void maxss(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MAXSS, arg0, arg1);
    }

  public final void maxss(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_MAXSS, arg0, arg1);
    }

  public final void minps(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MINPS, arg0, arg1);
    }

  public final void minps(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_MINPS, arg0, arg1);
    }

  public final void minss(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MINSS, arg0, arg1);
    }

  public final void minss(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_MINSS, arg0, arg1);
    }

  public final void movaps(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MOVAPS, arg0, arg1);
    }

  public final void movaps(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_MOVAPS, arg0, arg1);
    }

  public final void movaps(Mem arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MOVAPS, arg0, arg1);
    }

  public final void movd(Mem arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MOVD, arg0, arg1);
    }

  public final void movd(Register arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MOVD, arg0, arg1);
    }

  public final void movd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_MOVD, arg0, arg1);
    }

  public final void movd(XMMRegister arg0, Register arg1) {
        emitX86(INST_CODE.INST_MOVD, arg0, arg1);
    }

  public final void movq(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MOVQ, arg0, arg1);
    }

  public final void movq(Mem arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MOVQ, arg0, arg1);
    }

  public final void movq(Register arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MOVQ, arg0, arg1);
    }

  public final void movq(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_MOVQ, arg0, arg1);
    }

  public final void movq(XMMRegister arg0, Register arg1) {
        emitX86(INST_CODE.INST_MOVQ, arg0, arg1);
    }

  public final void movntq(Mem arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_MOVNTQ, arg0, arg1);
    }

  public final void movhlps(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MOVHLPS, arg0, arg1);
    }

  public final void movhps(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_MOVHPS, arg0, arg1);
    }

  public final void movhps(Mem arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MOVHPS, arg0, arg1);
    }

  public final void movlhps(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MOVLHPS, arg0, arg1);
    }

  public final void movlps(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_MOVLPS, arg0, arg1);
    }

  public final void movlps(Mem arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MOVLPS, arg0, arg1);
    }

  public final void movntps(Mem arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MOVNTPS, arg0, arg1);
    }

  public final void movss(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MOVSS, arg0, arg1);
    }

  public final void movss(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_MOVSS, arg0, arg1);
    }

  public final void movss(Mem arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MOVSS, arg0, arg1);
    }

  public final void movups(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MOVUPS, arg0, arg1);
    }

  public final void movups(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_MOVUPS, arg0, arg1);
    }

  public final void movups(Mem arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MOVUPS, arg0, arg1);
    }

  public final void mulps(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MULPS, arg0, arg1);
    }

  public final void mulps(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_MULPS, arg0, arg1);
    }

  public final void mulss(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MULSS, arg0, arg1);
    }

  public final void mulss(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_MULSS, arg0, arg1);
    }

  public final void orps(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_ORPS, arg0, arg1);
    }

  public final void orps(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_ORPS, arg0, arg1);
    }

  public final void pavgb(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PAVGB, arg0, arg1);
    }

  public final void pavgb(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PAVGB, arg0, arg1);
    }

  public final void pavgw(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PAVGW, arg0, arg1);
    }

  public final void pavgw(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PAVGW, arg0, arg1);
    }

  public final void pextrw(Register arg0, MMRegister arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_PEXTRW, arg0, arg1, arg2);
    }

  public final void pinsrw(MMRegister arg0, Register arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_PINSRW, arg0, arg1, arg2);
    }

  public final void pinsrw(MMRegister arg0, Mem arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_PINSRW, arg0, arg1, arg2);
    }

  public final void pmaxsw(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PMAXSW, arg0, arg1);
    }

  public final void pmaxsw(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMAXSW, arg0, arg1);
    }

  public final void pmaxub(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PMAXUB, arg0, arg1);
    }

  public final void pmaxub(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMAXUB, arg0, arg1);
    }

  public final void pminsw(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PMINSW, arg0, arg1);
    }

  public final void pminsw(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMINSW, arg0, arg1);
    }

  public final void pminub(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PMINUB, arg0, arg1);
    }

  public final void pminub(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMINUB, arg0, arg1);
    }

  public final void pmovmskb(Register arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PMOVMSKB, arg0, arg1);
    }

  public final void pmulhuw(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PMULHUW, arg0, arg1);
    }

  public final void pmulhuw(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMULHUW, arg0, arg1);
    }

  public final void psadbw(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PSADBW, arg0, arg1);
    }

  public final void psadbw(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSADBW, arg0, arg1);
    }

  public final void pshufw(MMRegister arg0, MMRegister arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_PSHUFW, arg0, arg1, arg2);
    }

  public final void pshufw(MMRegister arg0, Mem arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_PSHUFW, arg0, arg1, arg2);
    }

  public final void rcpps(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_RCPPS, arg0, arg1);
    }

  public final void rcpps(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_RCPPS, arg0, arg1);
    }

  public final void rcpss(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_RCPSS, arg0, arg1);
    }

  public final void rcpss(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_RCPSS, arg0, arg1);
    }

  public final void prefetch(Mem arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_PREFETCH, arg0, arg1);
    }

  public final void psadbw(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PSADBW, arg0, arg1);
    }

  public final void psadbw(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSADBW, arg0, arg1);
    }

  public final void rsqrtps(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_RSQRTPS, arg0, arg1);
    }

  public final void rsqrtps(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_RSQRTPS, arg0, arg1);
    }

  public final void rsqrtss(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_RSQRTSS, arg0, arg1);
    }

  public final void rsqrtss(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_RSQRTSS, arg0, arg1);
    }

  public final void sfence() {
        emitX86(INST_CODE.INST_SFENCE);
    }

  public final void shufps(XMMRegister arg0, XMMRegister arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_SHUFPS, arg0, arg1, arg2);
    }

  public final void shufps(XMMRegister arg0, Mem arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_SHUFPS, arg0, arg1, arg2);
    }

  public final void sqrtps(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_SQRTPS, arg0, arg1);
    }

  public final void sqrtps(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_SQRTPS, arg0, arg1);
    }

  public final void sqrtss(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_SQRTSS, arg0, arg1);
    }

  public final void sqrtss(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_SQRTSS, arg0, arg1);
    }

  public final void stmxcsr(Mem arg0) {
        emitX86(INST_CODE.INST_STMXCSR, arg0);
    }

  public final void subps(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_SUBPS, arg0, arg1);
    }

  public final void subps(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_SUBPS, arg0, arg1);
    }

  public final void subss(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_SUBSS, arg0, arg1);
    }

  public final void subss(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_SUBSS, arg0, arg1);
    }

  public final void ucomiss(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_UCOMISS, arg0, arg1);
    }

  public final void ucomiss(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_UCOMISS, arg0, arg1);
    }

  public final void unpckhps(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_UNPCKHPS, arg0, arg1);
    }

  public final void unpckhps(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_UNPCKHPS, arg0, arg1);
    }

  public final void unpcklps(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_UNPCKLPS, arg0, arg1);
    }

  public final void unpcklps(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_UNPCKLPS, arg0, arg1);
    }

  public final void xorps(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_XORPS, arg0, arg1);
    }

  public final void xorps(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_XORPS, arg0, arg1);
    }

  public final void addpd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_ADDPD, arg0, arg1);
    }

  public final void addpd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_ADDPD, arg0, arg1);
    }

  public final void addsd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_ADDSD, arg0, arg1);
    }

  public final void addsd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_ADDSD, arg0, arg1);
    }

  public final void andnpd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_ANDNPD, arg0, arg1);
    }

  public final void andnpd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_ANDNPD, arg0, arg1);
    }

  public final void andpd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_ANDPD, arg0, arg1);
    }

  public final void andpd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_ANDPD, arg0, arg1);
    }

  public final void clflush(Mem arg0) {
        emitX86(INST_CODE.INST_CLFLUSH, arg0);
    }

  public final void cmppd(XMMRegister arg0, XMMRegister arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_CMPPD, arg0, arg1, arg2);
    }

  public final void cmppd(XMMRegister arg0, Mem arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_CMPPD, arg0, arg1, arg2);
    }

  public final void cmpsd(XMMRegister arg0, XMMRegister arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_CMPSD, arg0, arg1, arg2);
    }

  public final void cmpsd(XMMRegister arg0, Mem arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_CMPSD, arg0, arg1, arg2);
    }

  public final void comisd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_COMISD, arg0, arg1);
    }

  public final void comisd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_COMISD, arg0, arg1);
    }

  public final void cvtdq2pd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_CVTDQ2PD, arg0, arg1);
    }

  public final void cvtdq2pd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CVTDQ2PD, arg0, arg1);
    }

  public final void cvtdq2ps(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_CVTDQ2PS, arg0, arg1);
    }

  public final void cvtdq2ps(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CVTDQ2PS, arg0, arg1);
    }

  public final void cvtpd2dq(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_CVTPD2DQ, arg0, arg1);
    }

  public final void cvtpd2dq(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CVTPD2DQ, arg0, arg1);
    }

  public final void cvtpd2pi(MMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_CVTPD2PI, arg0, arg1);
    }

  public final void cvtpd2pi(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CVTPD2PI, arg0, arg1);
    }

  public final void cvtpd2ps(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_CVTPD2PS, arg0, arg1);
    }

  public final void cvtpd2ps(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CVTPD2PS, arg0, arg1);
    }

  public final void cvtpi2pd(XMMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_CVTPI2PD, arg0, arg1);
    }

  public final void cvtpi2pd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CVTPI2PD, arg0, arg1);
    }

  public final void cvtps2dq(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_CVTPS2DQ, arg0, arg1);
    }

  public final void cvtps2dq(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CVTPS2DQ, arg0, arg1);
    }

  public final void cvtps2pd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_CVTPS2PD, arg0, arg1);
    }

  public final void cvtps2pd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CVTPS2PD, arg0, arg1);
    }

  public final void cvtsd2si(Register arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_CVTSD2SI, arg0, arg1);
    }

  public final void cvtsd2si(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CVTSD2SI, arg0, arg1);
    }

  public final void cvtsd2ss(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_CVTSD2SS, arg0, arg1);
    }

  public final void cvtsd2ss(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CVTSD2SS, arg0, arg1);
    }

  public final void cvtsi2sd(XMMRegister arg0, Register arg1) {
        emitX86(INST_CODE.INST_CVTSI2SD, arg0, arg1);
    }

  public final void cvtsi2sd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CVTSI2SD, arg0, arg1);
    }

  public final void cvtss2sd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_CVTSS2SD, arg0, arg1);
    }

  public final void cvtss2sd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CVTSS2SD, arg0, arg1);
    }

  public final void cvttpd2pi(MMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_CVTTPD2PI, arg0, arg1);
    }

  public final void cvttpd2pi(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CVTTPD2PI, arg0, arg1);
    }

  public final void cvttpd2dq(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_CVTTPD2DQ, arg0, arg1);
    }

  public final void cvttpd2dq(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CVTTPD2DQ, arg0, arg1);
    }

  public final void cvttps2dq(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_CVTTPS2DQ, arg0, arg1);
    }

  public final void cvttps2dq(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CVTTPS2DQ, arg0, arg1);
    }

  public final void cvttsd2si(Register arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_CVTTSD2SI, arg0, arg1);
    }

  public final void cvttsd2si(Register arg0, Mem arg1) {
        emitX86(INST_CODE.INST_CVTTSD2SI, arg0, arg1);
    }

  public final void divpd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_DIVPD, arg0, arg1);
    }

  public final void divpd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_DIVPD, arg0, arg1);
    }

  public final void divsd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_DIVSD, arg0, arg1);
    }

  public final void divsd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_DIVSD, arg0, arg1);
    }

  public final void lfence() {
        emitX86(INST_CODE.INST_LFENCE);
    }

  public final void maskmovdqu(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MASKMOVDQU, arg0, arg1);
    }

  public final void maxpd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MAXPD, arg0, arg1);
    }

  public final void maxpd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_MAXPD, arg0, arg1);
    }

  public final void maxsd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MAXSD, arg0, arg1);
    }

  public final void maxsd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_MAXSD, arg0, arg1);
    }

  public final void mfence() {
        emitX86(INST_CODE.INST_MFENCE);
    }

  public final void minpd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MINPD, arg0, arg1);
    }

  public final void minpd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_MINPD, arg0, arg1);
    }

  public final void minsd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MINSD, arg0, arg1);
    }

  public final void minsd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_MINSD, arg0, arg1);
    }

  public final void movdqa(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MOVDQA, arg0, arg1);
    }

  public final void movdqa(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_MOVDQA, arg0, arg1);
    }

  public final void movdqa(Mem arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MOVDQA, arg0, arg1);
    }

  public final void movdqu(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MOVDQU, arg0, arg1);
    }

  public final void movdqu(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_MOVDQU, arg0, arg1);
    }

  public final void movdqu(Mem arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MOVDQU, arg0, arg1);
    }

  public final void movmskps(Register arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MOVMSKPS, arg0, arg1);
    }

  public final void movmskpd(Register arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MOVMSKPD, arg0, arg1);
    }

  public final void movsd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MOVSD, arg0, arg1);
    }

  public final void movsd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_MOVSD, arg0, arg1);
    }

  public final void movsd(Mem arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MOVSD, arg0, arg1);
    }

  public final void movapd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MOVAPD, arg0, arg1);
    }

  public final void movapd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_MOVAPD, arg0, arg1);
    }

  public final void movapd(Mem arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MOVAPD, arg0, arg1);
    }

  public final void movdq2q(MMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MOVDQ2Q, arg0, arg1);
    }

  public final void movq2dq(XMMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_MOVQ2DQ, arg0, arg1);
    }

  public final void movhpd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_MOVHPD, arg0, arg1);
    }

  public final void movhpd(Mem arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MOVHPD, arg0, arg1);
    }

  public final void movlpd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_MOVLPD, arg0, arg1);
    }

  public final void movlpd(Mem arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MOVLPD, arg0, arg1);
    }

  public final void movntdq(Mem arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MOVNTDQ, arg0, arg1);
    }

  public final void movnti(Mem arg0, Register arg1) {
        emitX86(INST_CODE.INST_MOVNTI, arg0, arg1);
    }

  public final void movntpd(Mem arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MOVNTPD, arg0, arg1);
    }

  public final void movupd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_MOVUPD, arg0, arg1);
    }

  public final void movupd(Mem arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MOVUPD, arg0, arg1);
    }

  public final void mulpd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MULPD, arg0, arg1);
    }

  public final void mulpd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_MULPD, arg0, arg1);
    }

  public final void mulsd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MULSD, arg0, arg1);
    }

  public final void mulsd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_MULSD, arg0, arg1);
    }

  public final void orpd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_ORPD, arg0, arg1);
    }

  public final void orpd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_ORPD, arg0, arg1);
    }

  public final void packsswb(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PACKSSWB, arg0, arg1);
    }

  public final void packsswb(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PACKSSWB, arg0, arg1);
    }

  public final void packssdw(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PACKSSDW, arg0, arg1);
    }

  public final void packssdw(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PACKSSDW, arg0, arg1);
    }

  public final void packuswb(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PACKUSWB, arg0, arg1);
    }

  public final void packuswb(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PACKUSWB, arg0, arg1);
    }

  public final void paddb(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PADDB, arg0, arg1);
    }

  public final void paddb(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PADDB, arg0, arg1);
    }

  public final void paddw(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PADDW, arg0, arg1);
    }

  public final void paddw(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PADDW, arg0, arg1);
    }

  public final void paddd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PADDD, arg0, arg1);
    }

  public final void paddd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PADDD, arg0, arg1);
    }

  public final void paddq(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PADDQ, arg0, arg1);
    }

  public final void paddq(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PADDQ, arg0, arg1);
    }

  public final void paddq(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PADDQ, arg0, arg1);
    }

  public final void paddq(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PADDQ, arg0, arg1);
    }

  public final void paddsb(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PADDSB, arg0, arg1);
    }

  public final void paddsb(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PADDSB, arg0, arg1);
    }

  public final void paddsw(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PADDSW, arg0, arg1);
    }

  public final void paddsw(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PADDSW, arg0, arg1);
    }

  public final void paddusb(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PADDUSB, arg0, arg1);
    }

  public final void paddusb(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PADDUSB, arg0, arg1);
    }

  public final void paddusw(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PADDUSW, arg0, arg1);
    }

  public final void paddusw(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PADDUSW, arg0, arg1);
    }

  public final void pand(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PAND, arg0, arg1);
    }

  public final void pand(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PAND, arg0, arg1);
    }

  public final void pandn(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PANDN, arg0, arg1);
    }

  public final void pandn(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PANDN, arg0, arg1);
    }

  public final void pause() {
        emitX86(INST_CODE.INST_PAUSE);
    }

  public final void pavgb(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PAVGB, arg0, arg1);
    }

  public final void pavgb(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PAVGB, arg0, arg1);
    }

  public final void pavgw(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PAVGW, arg0, arg1);
    }

  public final void pavgw(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PAVGW, arg0, arg1);
    }

  public final void pcmpeqb(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PCMPEQB, arg0, arg1);
    }

  public final void pcmpeqb(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PCMPEQB, arg0, arg1);
    }

  public final void pcmpeqw(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PCMPEQW, arg0, arg1);
    }

  public final void pcmpeqw(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PCMPEQW, arg0, arg1);
    }

  public final void pcmpeqd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PCMPEQD, arg0, arg1);
    }

  public final void pcmpeqd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PCMPEQD, arg0, arg1);
    }

  public final void pcmpgtb(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PCMPGTB, arg0, arg1);
    }

  public final void pcmpgtb(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PCMPGTB, arg0, arg1);
    }

  public final void pcmpgtw(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PCMPGTW, arg0, arg1);
    }

  public final void pcmpgtw(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PCMPGTW, arg0, arg1);
    }

  public final void pcmpgtd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PCMPGTD, arg0, arg1);
    }

  public final void pcmpgtd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PCMPGTD, arg0, arg1);
    }

  public final void pmaxsw(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PMAXSW, arg0, arg1);
    }

  public final void pmaxsw(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMAXSW, arg0, arg1);
    }

  public final void pmaxub(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PMAXUB, arg0, arg1);
    }

  public final void pmaxub(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMAXUB, arg0, arg1);
    }

  public final void pminsw(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PMINSW, arg0, arg1);
    }

  public final void pminsw(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMINSW, arg0, arg1);
    }

  public final void pminub(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PMINUB, arg0, arg1);
    }

  public final void pminub(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMINUB, arg0, arg1);
    }

  public final void pmovmskb(Register arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PMOVMSKB, arg0, arg1);
    }

  public final void pmulhw(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PMULHW, arg0, arg1);
    }

  public final void pmulhw(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMULHW, arg0, arg1);
    }

  public final void pmulhuw(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PMULHUW, arg0, arg1);
    }

  public final void pmulhuw(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMULHUW, arg0, arg1);
    }

  public final void pmullw(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PMULLW, arg0, arg1);
    }

  public final void pmullw(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMULLW, arg0, arg1);
    }

  public final void pmuludq(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PMULUDQ, arg0, arg1);
    }

  public final void pmuludq(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMULUDQ, arg0, arg1);
    }

  public final void pmuludq(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PMULUDQ, arg0, arg1);
    }

  public final void pmuludq(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMULUDQ, arg0, arg1);
    }

  public final void por(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_POR, arg0, arg1);
    }

  public final void por(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_POR, arg0, arg1);
    }

  public final void pslld(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PSLLD, arg0, arg1);
    }

  public final void pslld(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSLLD, arg0, arg1);
    }

  public final void pslld(XMMRegister arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_PSLLD, arg0, arg1);
    }

  public final void psllq(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PSLLQ, arg0, arg1);
    }

  public final void psllq(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSLLQ, arg0, arg1);
    }

  public final void psllq(XMMRegister arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_PSLLQ, arg0, arg1);
    }

  public final void psllw(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PSLLW, arg0, arg1);
    }

  public final void psllw(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSLLW, arg0, arg1);
    }

  public final void psllw(XMMRegister arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_PSLLW, arg0, arg1);
    }

  public final void pslldq(XMMRegister arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_PSLLDQ, arg0, arg1);
    }

  public final void psrad(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PSRAD, arg0, arg1);
    }

  public final void psrad(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSRAD, arg0, arg1);
    }

  public final void psrad(XMMRegister arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_PSRAD, arg0, arg1);
    }

  public final void psraw(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PSRAW, arg0, arg1);
    }

  public final void psraw(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSRAW, arg0, arg1);
    }

  public final void psraw(XMMRegister arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_PSRAW, arg0, arg1);
    }

  public final void psubb(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PSUBB, arg0, arg1);
    }

  public final void psubb(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSUBB, arg0, arg1);
    }

  public final void psubw(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PSUBW, arg0, arg1);
    }

  public final void psubw(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSUBW, arg0, arg1);
    }

  public final void psubd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PSUBD, arg0, arg1);
    }

  public final void psubd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSUBD, arg0, arg1);
    }

  public final void psubq(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PSUBQ, arg0, arg1);
    }

  public final void psubq(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSUBQ, arg0, arg1);
    }

  public final void psubq(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PSUBQ, arg0, arg1);
    }

  public final void psubq(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSUBQ, arg0, arg1);
    }

  public final void pmaddwd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PMADDWD, arg0, arg1);
    }

  public final void pmaddwd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMADDWD, arg0, arg1);
    }

  public final void pshufd(XMMRegister arg0, XMMRegister arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_PSHUFD, arg0, arg1, arg2);
    }

  public final void pshufd(XMMRegister arg0, Mem arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_PSHUFD, arg0, arg1, arg2);
    }

  public final void pshufhw(XMMRegister arg0, XMMRegister arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_PSHUFHW, arg0, arg1, arg2);
    }

  public final void pshufhw(XMMRegister arg0, Mem arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_PSHUFHW, arg0, arg1, arg2);
    }

  public final void pshuflw(XMMRegister arg0, XMMRegister arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_PSHUFLW, arg0, arg1, arg2);
    }

  public final void pshuflw(XMMRegister arg0, Mem arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_PSHUFLW, arg0, arg1, arg2);
    }

  public final void psrld(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PSRLD, arg0, arg1);
    }

  public final void psrld(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSRLD, arg0, arg1);
    }

  public final void psrld(XMMRegister arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_PSRLD, arg0, arg1);
    }

  public final void psrlq(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PSRLQ, arg0, arg1);
    }

  public final void psrlq(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSRLQ, arg0, arg1);
    }

  public final void psrlq(XMMRegister arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_PSRLQ, arg0, arg1);
    }

  public final void psrldq(XMMRegister arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_PSRLDQ, arg0, arg1);
    }

  public final void psrlw(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PSRLW, arg0, arg1);
    }

  public final void psrlw(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSRLW, arg0, arg1);
    }

  public final void psrlw(XMMRegister arg0, Immediate arg1) {
        emitX86(INST_CODE.INST_PSRLW, arg0, arg1);
    }

  public final void psubsb(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PSUBSB, arg0, arg1);
    }

  public final void psubsb(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSUBSB, arg0, arg1);
    }

  public final void psubsw(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PSUBSW, arg0, arg1);
    }

  public final void psubsw(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSUBSW, arg0, arg1);
    }

  public final void psubusb(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PSUBUSB, arg0, arg1);
    }

  public final void psubusb(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSUBUSB, arg0, arg1);
    }

  public final void psubusw(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PSUBUSW, arg0, arg1);
    }

  public final void psubusw(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSUBUSW, arg0, arg1);
    }

  public final void punpckhbw(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PUNPCKHBW, arg0, arg1);
    }

  public final void punpckhbw(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PUNPCKHBW, arg0, arg1);
    }

  public final void punpckhwd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PUNPCKHWD, arg0, arg1);
    }

  public final void punpckhwd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PUNPCKHWD, arg0, arg1);
    }

  public final void punpckhdq(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PUNPCKHDQ, arg0, arg1);
    }

  public final void punpckhdq(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PUNPCKHDQ, arg0, arg1);
    }

  public final void punpckhqdq(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PUNPCKHQDQ, arg0, arg1);
    }

  public final void punpckhqdq(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PUNPCKHQDQ, arg0, arg1);
    }

  public final void punpcklbw(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PUNPCKLBW, arg0, arg1);
    }

  public final void punpcklbw(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PUNPCKLBW, arg0, arg1);
    }

  public final void punpcklwd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PUNPCKLWD, arg0, arg1);
    }

  public final void punpcklwd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PUNPCKLWD, arg0, arg1);
    }

  public final void punpckldq(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PUNPCKLDQ, arg0, arg1);
    }

  public final void punpckldq(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PUNPCKLDQ, arg0, arg1);
    }

  public final void punpcklqdq(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PUNPCKLQDQ, arg0, arg1);
    }

  public final void punpcklqdq(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PUNPCKLQDQ, arg0, arg1);
    }

  public final void pxor(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PXOR, arg0, arg1);
    }

  public final void pxor(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PXOR, arg0, arg1);
    }

  public final void sqrtpd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_SQRTPD, arg0, arg1);
    }

  public final void sqrtpd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_SQRTPD, arg0, arg1);
    }

  public final void sqrtsd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_SQRTSD, arg0, arg1);
    }

  public final void sqrtsd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_SQRTSD, arg0, arg1);
    }

  public final void subpd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_SUBPD, arg0, arg1);
    }

  public final void subpd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_SUBPD, arg0, arg1);
    }

  public final void subsd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_SUBSD, arg0, arg1);
    }

  public final void subsd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_SUBSD, arg0, arg1);
    }

  public final void ucomisd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_UCOMISD, arg0, arg1);
    }

  public final void ucomisd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_UCOMISD, arg0, arg1);
    }

  public final void unpckhpd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_UNPCKHPD, arg0, arg1);
    }

  public final void unpckhpd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_UNPCKHPD, arg0, arg1);
    }

  public final void unpcklpd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_UNPCKLPD, arg0, arg1);
    }

  public final void unpcklpd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_UNPCKLPD, arg0, arg1);
    }

  public final void xorpd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_XORPD, arg0, arg1);
    }

  public final void xorpd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_XORPD, arg0, arg1);
    }

  public final void addsubpd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_ADDSUBPD, arg0, arg1);
    }

  public final void addsubpd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_ADDSUBPD, arg0, arg1);
    }

  public final void addsubps(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_ADDSUBPS, arg0, arg1);
    }

  public final void addsubps(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_ADDSUBPS, arg0, arg1);
    }

  public final void fisttp(Mem arg0) {
        emitX86(INST_CODE.INST_FISTTP, arg0);
    }

  public final void haddpd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_HADDPD, arg0, arg1);
    }

  public final void haddpd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_HADDPD, arg0, arg1);
    }

  public final void haddps(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_HADDPS, arg0, arg1);
    }

  public final void haddps(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_HADDPS, arg0, arg1);
    }

  public final void hsubpd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_HSUBPD, arg0, arg1);
    }

  public final void hsubpd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_HSUBPD, arg0, arg1);
    }

  public final void hsubps(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_HSUBPS, arg0, arg1);
    }

  public final void hsubps(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_HSUBPS, arg0, arg1);
    }

  public final void lddqu(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_LDDQU, arg0, arg1);
    }

  public final void monitor() {
        emitX86(INST_CODE.INST_MONITOR);
    }

  public final void movddup(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MOVDDUP, arg0, arg1);
    }

  public final void movddup(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_MOVDDUP, arg0, arg1);
    }

  public final void movshdup(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MOVSHDUP, arg0, arg1);
    }

  public final void movshdup(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_MOVSHDUP, arg0, arg1);
    }

  public final void movsldup(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_MOVSLDUP, arg0, arg1);
    }

  public final void movsldup(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_MOVSLDUP, arg0, arg1);
    }

  public final void mwait() {
        emitX86(INST_CODE.INST_MWAIT);
    }

  public final void psignb(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PSIGNB, arg0, arg1);
    }

  public final void psignb(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSIGNB, arg0, arg1);
    }

  public final void psignb(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PSIGNB, arg0, arg1);
    }

  public final void psignb(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSIGNB, arg0, arg1);
    }

  public final void psignw(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PSIGNW, arg0, arg1);
    }

  public final void psignw(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSIGNW, arg0, arg1);
    }

  public final void psignw(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PSIGNW, arg0, arg1);
    }

  public final void psignw(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSIGNW, arg0, arg1);
    }

  public final void psignd(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PSIGND, arg0, arg1);
    }

  public final void psignd(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSIGND, arg0, arg1);
    }

  public final void psignd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PSIGND, arg0, arg1);
    }

  public final void psignd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSIGND, arg0, arg1);
    }

  public final void phaddw(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PHADDW, arg0, arg1);
    }

  public final void phaddw(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PHADDW, arg0, arg1);
    }

  public final void phaddw(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PHADDW, arg0, arg1);
    }

  public final void phaddw(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PHADDW, arg0, arg1);
    }

  public final void phaddd(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PHADDD, arg0, arg1);
    }

  public final void phaddd(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PHADDD, arg0, arg1);
    }

  public final void phaddd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PHADDD, arg0, arg1);
    }

  public final void phaddd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PHADDD, arg0, arg1);
    }

  public final void phaddsw(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PHADDSW, arg0, arg1);
    }

  public final void phaddsw(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PHADDSW, arg0, arg1);
    }

  public final void phaddsw(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PHADDSW, arg0, arg1);
    }

  public final void phaddsw(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PHADDSW, arg0, arg1);
    }

  public final void phsubw(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PHSUBW, arg0, arg1);
    }

  public final void phsubw(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PHSUBW, arg0, arg1);
    }

  public final void phsubw(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PHSUBW, arg0, arg1);
    }

  public final void phsubw(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PHSUBW, arg0, arg1);
    }

  public final void phsubd(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PHSUBD, arg0, arg1);
    }

  public final void phsubd(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PHSUBD, arg0, arg1);
    }

  public final void phsubd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PHSUBD, arg0, arg1);
    }

  public final void phsubd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PHSUBD, arg0, arg1);
    }

  public final void phsubsw(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PHSUBSW, arg0, arg1);
    }

  public final void phsubsw(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PHSUBSW, arg0, arg1);
    }

  public final void phsubsw(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PHSUBSW, arg0, arg1);
    }

  public final void phsubsw(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PHSUBSW, arg0, arg1);
    }

  public final void pmaddubsw(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PMADDUBSW, arg0, arg1);
    }

  public final void pmaddubsw(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMADDUBSW, arg0, arg1);
    }

  public final void pmaddubsw(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PMADDUBSW, arg0, arg1);
    }

  public final void pmaddubsw(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMADDUBSW, arg0, arg1);
    }

  public final void pabsb(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PABSB, arg0, arg1);
    }

  public final void pabsb(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PABSB, arg0, arg1);
    }

  public final void pabsb(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PABSB, arg0, arg1);
    }

  public final void pabsb(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PABSB, arg0, arg1);
    }

  public final void pabsw(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PABSW, arg0, arg1);
    }

  public final void pabsw(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PABSW, arg0, arg1);
    }

  public final void pabsw(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PABSW, arg0, arg1);
    }

  public final void pabsw(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PABSW, arg0, arg1);
    }

  public final void pabsd(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PABSD, arg0, arg1);
    }

  public final void pabsd(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PABSD, arg0, arg1);
    }

  public final void pabsd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PABSD, arg0, arg1);
    }

  public final void pabsd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PABSD, arg0, arg1);
    }

  public final void pmulhrsw(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PMULHRSW, arg0, arg1);
    }

  public final void pmulhrsw(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMULHRSW, arg0, arg1);
    }

  public final void pmulhrsw(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PMULHRSW, arg0, arg1);
    }

  public final void pmulhrsw(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMULHRSW, arg0, arg1);
    }

  public final void pshufb(MMRegister arg0, MMRegister arg1) {
        emitX86(INST_CODE.INST_PSHUFB, arg0, arg1);
    }

  public final void pshufb(MMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSHUFB, arg0, arg1);
    }

  public final void pshufb(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PSHUFB, arg0, arg1);
    }

  public final void pshufb(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PSHUFB, arg0, arg1);
    }

  public final void palignr(MMRegister arg0, MMRegister arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_PALIGNR, arg0, arg1, arg2);
    }

  public final void palignr(MMRegister arg0, Mem arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_PALIGNR, arg0, arg1, arg2);
    }

  public final void palignr(XMMRegister arg0, XMMRegister arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_PALIGNR, arg0, arg1, arg2);
    }

  public final void palignr(XMMRegister arg0, Mem arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_PALIGNR, arg0, arg1, arg2);
    }

  public final void blendpd(XMMRegister arg0, XMMRegister arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_BLENDPD, arg0, arg1, arg2);
    }

  public final void blendpd(XMMRegister arg0, Mem arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_BLENDPD, arg0, arg1, arg2);
    }

  public final void blendps(XMMRegister arg0, XMMRegister arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_BLENDPS, arg0, arg1, arg2);
    }

  public final void blendps(XMMRegister arg0, Mem arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_BLENDPS, arg0, arg1, arg2);
    }

  public final void blendvpd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_BLENDVPD, arg0, arg1);
    }

  public final void blendvpd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_BLENDVPD, arg0, arg1);
    }

  public final void blendvps(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_BLENDVPS, arg0, arg1);
    }

  public final void blendvps(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_BLENDVPS, arg0, arg1);
    }

  public final void dppd(XMMRegister arg0, XMMRegister arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_DPPD, arg0, arg1, arg2);
    }

  public final void dppd(XMMRegister arg0, Mem arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_DPPD, arg0, arg1, arg2);
    }

  public final void dpps(XMMRegister arg0, XMMRegister arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_DPPS, arg0, arg1, arg2);
    }

  public final void dpps(XMMRegister arg0, Mem arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_DPPS, arg0, arg1, arg2);
    }

  public final void extractps(XMMRegister arg0, XMMRegister arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_EXTRACTPS, arg0, arg1, arg2);
    }

  public final void extractps(XMMRegister arg0, Mem arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_EXTRACTPS, arg0, arg1, arg2);
    }

  public final void movntdqa(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_MOVNTDQA, arg0, arg1);
    }

  public final void mpsadbw(XMMRegister arg0, XMMRegister arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_MPSADBW, arg0, arg1, arg2);
    }

  public final void mpsadbw(XMMRegister arg0, Mem arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_MPSADBW, arg0, arg1, arg2);
    }

  public final void packusdw(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PACKUSDW, arg0, arg1);
    }

  public final void packusdw(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PACKUSDW, arg0, arg1);
    }

  public final void pblendvb(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PBLENDVB, arg0, arg1);
    }

  public final void pblendvb(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PBLENDVB, arg0, arg1);
    }

  public final void pblendw(XMMRegister arg0, XMMRegister arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_PBLENDW, arg0, arg1, arg2);
    }

  public final void pblendw(XMMRegister arg0, Mem arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_PBLENDW, arg0, arg1, arg2);
    }

  public final void pcmpeqq(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PCMPEQQ, arg0, arg1);
    }

  public final void pcmpeqq(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PCMPEQQ, arg0, arg1);
    }

  public final void pextrb(Register arg0, XMMRegister arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_PEXTRB, arg0, arg1, arg2);
    }

  public final void pextrb(Mem arg0, XMMRegister arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_PEXTRB, arg0, arg1, arg2);
    }

  public final void pextrd(Register arg0, XMMRegister arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_PEXTRD, arg0, arg1, arg2);
    }

  public final void pextrd(Mem arg0, XMMRegister arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_PEXTRD, arg0, arg1, arg2);
    }

  public final void pextrq(Register arg0, XMMRegister arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_PEXTRQ, arg0, arg1, arg2);
    }

  public final void pextrq(Mem arg0, XMMRegister arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_PEXTRQ, arg0, arg1, arg2);
    }

  public final void pextrw(Register arg0, XMMRegister arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_PEXTRW, arg0, arg1, arg2);
    }

  public final void pextrw(Mem arg0, XMMRegister arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_PEXTRW, arg0, arg1, arg2);
    }

  public final void phminposuw(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PHMINPOSUW, arg0, arg1);
    }

  public final void phminposuw(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PHMINPOSUW, arg0, arg1);
    }

  public final void pinsrb(XMMRegister arg0, Register arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_PINSRB, arg0, arg1, arg2);
    }

  public final void pinsrb(XMMRegister arg0, Mem arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_PINSRB, arg0, arg1, arg2);
    }

  public final void pinsrd(XMMRegister arg0, Register arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_PINSRD, arg0, arg1, arg2);
    }

  public final void pinsrd(XMMRegister arg0, Mem arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_PINSRD, arg0, arg1, arg2);
    }

  public final void pinsrq(XMMRegister arg0, Register arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_PINSRQ, arg0, arg1, arg2);
    }

  public final void pinsrq(XMMRegister arg0, Mem arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_PINSRQ, arg0, arg1, arg2);
    }

  public final void pinsrw(XMMRegister arg0, Register arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_PINSRW, arg0, arg1, arg2);
    }

  public final void pinsrw(XMMRegister arg0, Mem arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_PINSRW, arg0, arg1, arg2);
    }

  public final void pmaxuw(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PMAXUW, arg0, arg1);
    }

  public final void pmaxuw(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMAXUW, arg0, arg1);
    }

  public final void pmaxsb(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PMAXSB, arg0, arg1);
    }

  public final void pmaxsb(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMAXSB, arg0, arg1);
    }

  public final void pmaxsd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PMAXSD, arg0, arg1);
    }

  public final void pmaxsd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMAXSD, arg0, arg1);
    }

  public final void pmaxud(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PMAXUD, arg0, arg1);
    }

  public final void pmaxud(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMAXUD, arg0, arg1);
    }

  public final void pminsb(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PMINSB, arg0, arg1);
    }

  public final void pminsb(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMINSB, arg0, arg1);
    }

  public final void pminuw(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PMINUW, arg0, arg1);
    }

  public final void pminuw(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMINUW, arg0, arg1);
    }

  public final void pminud(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PMINUD, arg0, arg1);
    }

  public final void pminud(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMINUD, arg0, arg1);
    }

  public final void pminsd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PMINSD, arg0, arg1);
    }

  public final void pminsd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMINSD, arg0, arg1);
    }

  public final void pmovsxbw(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PMOVSXBW, arg0, arg1);
    }

  public final void pmovsxbw(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMOVSXBW, arg0, arg1);
    }

  public final void pmovsxbd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PMOVSXBD, arg0, arg1);
    }

  public final void pmovsxbd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMOVSXBD, arg0, arg1);
    }

  public final void pmovsxbq(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PMOVSXBQ, arg0, arg1);
    }

  public final void pmovsxbq(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMOVSXBQ, arg0, arg1);
    }

  public final void pmovsxwd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PMOVSXWD, arg0, arg1);
    }

  public final void pmovsxwd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMOVSXWD, arg0, arg1);
    }

  public final void pmovsxwq(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PMOVSXWQ, arg0, arg1);
    }

  public final void pmovsxwq(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMOVSXWQ, arg0, arg1);
    }

  public final void pmovsxdq(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PMOVSXDQ, arg0, arg1);
    }

  public final void pmovsxdq(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMOVSXDQ, arg0, arg1);
    }

  public final void pmovzxbw(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PMOVZXBW, arg0, arg1);
    }

  public final void pmovzxbw(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMOVZXBW, arg0, arg1);
    }

  public final void pmovzxbd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PMOVZXBD, arg0, arg1);
    }

  public final void pmovzxbd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMOVZXBD, arg0, arg1);
    }

  public final void pmovzxbq(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PMOVZXBQ, arg0, arg1);
    }

  public final void pmovzxbq(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMOVZXBQ, arg0, arg1);
    }

  public final void pmovzxwd(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PMOVZXWD, arg0, arg1);
    }

  public final void pmovzxwd(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMOVZXWD, arg0, arg1);
    }

  public final void pmovzxwq(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PMOVZXWQ, arg0, arg1);
    }

  public final void pmovzxwq(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMOVZXWQ, arg0, arg1);
    }

  public final void pmovzxdq(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PMOVZXDQ, arg0, arg1);
    }

  public final void pmovzxdq(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMOVZXDQ, arg0, arg1);
    }

  public final void pmuldq(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PMULDQ, arg0, arg1);
    }

  public final void pmuldq(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMULDQ, arg0, arg1);
    }

  public final void pmulld(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PMULLD, arg0, arg1);
    }

  public final void pmulld(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PMULLD, arg0, arg1);
    }

  public final void ptest(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PTEST, arg0, arg1);
    }

  public final void ptest(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PTEST, arg0, arg1);
    }

  public final void roundps(XMMRegister arg0, XMMRegister arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_ROUNDPS, arg0, arg1, arg2);
    }

  public final void roundps(XMMRegister arg0, Mem arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_ROUNDPS, arg0, arg1, arg2);
    }

  public final void roundss(XMMRegister arg0, XMMRegister arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_ROUNDSS, arg0, arg1, arg2);
    }

  public final void roundss(XMMRegister arg0, Mem arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_ROUNDSS, arg0, arg1, arg2);
    }

  public final void roundpd(XMMRegister arg0, XMMRegister arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_ROUNDPD, arg0, arg1, arg2);
    }

  public final void roundpd(XMMRegister arg0, Mem arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_ROUNDPD, arg0, arg1, arg2);
    }

  public final void roundsd(XMMRegister arg0, XMMRegister arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_ROUNDSD, arg0, arg1, arg2);
    }

  public final void roundsd(XMMRegister arg0, Mem arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_ROUNDSD, arg0, arg1, arg2);
    }

  public final void crc32(Register arg0, Register arg1) {
        if ($assertionsDisabled) {
            emitX86(INST_CODE.INST_CRC32, arg0, arg1);
            return;
        } else {
            if (arg0.isRegType(32)) {
                emitX86(INST_CODE.INST_CRC32, arg0, arg1);
                return;
            } else {
                if (arg0.isRegType(48)) {
                    emitX86(INST_CODE.INST_CRC32, arg0, arg1);
                    return;
                } else {
                    throw new AssertionError();
                }
            }
        }
    }

  public final void crc32(Register arg0, Mem arg1) {
        if ($assertionsDisabled) {
            emitX86(INST_CODE.INST_CRC32, arg0, arg1);
            return;
        } else {
            if (arg0.isRegType(32)) {
                emitX86(INST_CODE.INST_CRC32, arg0, arg1);
                return;
            } else {
                if (arg0.isRegType(48)) {
                    emitX86(INST_CODE.INST_CRC32, arg0, arg1);
                    return;
                } else {
                    throw new AssertionError();
                }
            }
        }
    }

  public final void pcmpestri(XMMRegister arg0, XMMRegister arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_PCMPESTRI, arg0, arg1, arg2);
    }

  public final void pcmpestri(XMMRegister arg0, Mem arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_PCMPESTRI, arg0, arg1, arg2);
    }

  public final void pcmpestrm(XMMRegister arg0, XMMRegister arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_PCMPESTRM, arg0, arg1, arg2);
    }

  public final void pcmpestrm(XMMRegister arg0, Mem arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_PCMPESTRM, arg0, arg1, arg2);
    }

  public final void pcmpistri(XMMRegister arg0, XMMRegister arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_PCMPISTRI, arg0, arg1, arg2);
    }

  public final void pcmpistri(XMMRegister arg0, Mem arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_PCMPISTRI, arg0, arg1, arg2);
    }

  public final void pcmpistrm(XMMRegister arg0, XMMRegister arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_PCMPISTRM, arg0, arg1, arg2);
    }

  public final void pcmpistrm(XMMRegister arg0, Mem arg1, Immediate arg2) {
        emitX86(INST_CODE.INST_PCMPISTRM, arg0, arg1, arg2);
    }

  public final void pcmpgtq(XMMRegister arg0, XMMRegister arg1) {
        emitX86(INST_CODE.INST_PCMPGTQ, arg0, arg1);
    }

  public final void pcmpgtq(XMMRegister arg0, Mem arg1) {
        emitX86(INST_CODE.INST_PCMPGTQ, arg0, arg1);
    }

  public final void popcnt(Register arg0, Register arg1) {
        if ($assertionsDisabled) {
            if ($assertionsDisabled) {
                emitX86(INST_CODE.INST_POPCNT, arg0, arg1);
                return;
            } else {
                if (arg1.type() == arg0.type()) {
                    emitX86(INST_CODE.INST_POPCNT, arg0, arg1);
                    return;
                } else {
                    throw new AssertionError();
                }
            }
        } else {
            if (!arg0.isRegType(0)) {
                if ($assertionsDisabled) {
                    emitX86(INST_CODE.INST_POPCNT, arg0, arg1);
                    return;
                } else {
                    if (arg1.type() == arg0.type()) {
                        emitX86(INST_CODE.INST_POPCNT, arg0, arg1);
                        return;
                    } else {
                        throw new AssertionError();
                    }
                }
            } else {
                throw new AssertionError();
            }
        }
    }

  public final void popcnt(Register arg0, Mem arg1) {
        if ($assertionsDisabled) {
            emitX86(INST_CODE.INST_POPCNT, arg0, arg1);
            return;
        } else {
            if (!arg0.isRegType(0)) {
                emitX86(INST_CODE.INST_POPCNT, arg0, arg1);
                return;
            } else {
                throw new AssertionError();
            }
        }
    }

  public final void amd_prefetch(Mem arg0) {
        emitX86(INST_CODE.INST_AMD_PREFETCH, arg0);
    }

  public final void amd_prefetchw(Mem arg0) {
        emitX86(INST_CODE.INST_AMD_PREFETCHW, arg0);
    }

  public final void movbe(Register arg0, Mem arg1) {
        if ($assertionsDisabled) {
            emitX86(INST_CODE.INST_MOVBE, arg0, arg1);
            return;
        } else {
            if (!arg0.isRegType(0)) {
                emitX86(INST_CODE.INST_MOVBE, arg0, arg1);
                return;
            } else {
                throw new AssertionError();
            }
        }
    }

  public final void movbe(Mem arg0, Register arg1) {
        if ($assertionsDisabled) {
            emitX86(INST_CODE.INST_MOVBE, arg0, arg1);
            return;
        } else {
            if (!arg1.isRegType(0)) {
                emitX86(INST_CODE.INST_MOVBE, arg0, arg1);
                return;
            } else {
                throw new AssertionError();
            }
        }
    }

}