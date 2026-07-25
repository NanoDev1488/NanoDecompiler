// исходный (обфусцированный) внутренний класс: jnr.a64asm.SerializerIntrinsics_a64
package jnr.a64asm;

import jnr.a64asm.Conditions;
import jnr.a64asm.Ext;
import jnr.a64asm.INST_CODE;
import jnr.a64asm.Immediate;
import jnr.a64asm.Label;
import jnr.a64asm.Mem;
import jnr.a64asm.Offset;
import jnr.a64asm.PRFOP_ENUM;
import jnr.a64asm.Post_index;
import jnr.a64asm.Pre_index;
import jnr.a64asm.Register;
import jnr.a64asm.SerializerCore;
import jnr.a64asm.Shift;
import jnr.a64asm.SysRegister;

public abstract class SerializerIntrinsics_a64 extends SerializerCore {

  public SerializerIntrinsics_a64() { // было: <init>
        super();
    }

  public final void adc(Register arg0, Register arg1, Register arg2) {
        emitA64(INST_CODE.INST_ADC_ADDSUB_CARRY, arg0, arg1, arg2);
    }

  public final void adcs(Register arg0, Register arg1, Register arg2) {
        emitA64(INST_CODE.INST_ADCS_ADDSUB_CARRY, arg0, arg1, arg2);
    }

  public final void add(Register arg0, Register arg1, Immediate arg2, Shift arg3) {
        emitA64(INST_CODE.INST_ADD_ADDSUB_IMM, arg0, arg1, arg2, arg3);
    }

  public final void add(Register arg0, Register arg1, Register arg2, Shift arg3) {
        emitA64(INST_CODE.INST_ADD_ADDSUB_SHIFT, arg0, arg1, arg2, arg3);
    }

  public final void add(Register arg0, Register arg1, Register arg2, Ext arg3) {
        emitA64(INST_CODE.INST_ADD_EXT_ADDSUB_EXT, arg0, arg1, arg2, arg3);
    }

  public final void adds(Register arg0, Register arg1, Register arg2, Ext arg3) {
        emitA64(INST_CODE.INST_ADDS_ADDSUB_EXT, arg0, arg1, arg2, arg3);
    }

  public final void adds(Register arg0, Register arg1, Immediate arg2, Shift arg3) {
        emitA64(INST_CODE.INST_ADDS_ADDSUB_IMM, arg0, arg1, arg2, arg3);
    }

  public final void adds(Register arg0, Register arg1, Register arg2, Shift arg3) {
        emitA64(INST_CODE.INST_ADDS_ADDSUB_SHIFT, arg0, arg1, arg2, arg3);
    }

  public final void adr(Register arg0, Label arg1) {
        emitA64(INST_CODE.INST_ADR_PCRELADDR, arg0, arg1);
    }

  public final void adrp(Register arg0, Label arg1) {
        emitA64(INST_CODE.INST_ADRP_PCRELADDR, arg0, arg1);
    }

  public final void and(Register arg0, Register arg1, Immediate arg2) {
        emitA64(INST_CODE.INST_AND_LOG_IMM, arg0, arg1, arg2);
    }

  public final void and(Register arg0, Register arg1, Register arg2, Shift arg3) {
        emitA64(INST_CODE.INST_AND_LOG_SHIFT, arg0, arg1, arg2, arg3);
    }

  public final void ands(Register arg0, Register arg1, Immediate arg2) {
        emitA64(INST_CODE.INST_ANDS_LOG_IMM, arg0, arg1, arg2);
    }

  public final void ands(Register arg0, Register arg1, Register arg2, Shift arg3) {
        emitA64(INST_CODE.INST_ANDS_LOG_SHIFT, arg0, arg1, arg2, arg3);
    }

  public final void asr(Register arg0, Register arg1, Immediate arg2) {
        emitA64(INST_CODE.INST_ASR_BITFIELD, arg0, arg1, arg2);
    }

  public final void asr(Register arg0, Register arg1, Register arg2) {
        emitA64(INST_CODE.INST_ASR_DP_2SRC, arg0, arg1, arg2);
    }

  public final void asrv(Register arg0, Register arg1, Register arg2) {
        emitA64(INST_CODE.INST_ASRV_DP_2SRC, arg0, arg1, arg2);
    }

  public final void method1940(Immediate arg0) { // было: b
        emitA64(INST_CODE.INST_B_BRANCH_IMM, arg0);
    }

  public final void bcc(Immediate arg0) {
        emitA64(INST_CODE.INST_BCC_CONDBRANCH, arg0);
    }

  public final void bcs(Immediate arg0) {
        emitA64(INST_CODE.INST_BCS_CONDBRANCH, arg0);
    }

  public final void beq(Immediate arg0) {
        emitA64(INST_CODE.INST_BEQ_CONDBRANCH, arg0);
    }

  public final void bfi(Register arg0, Register arg1, Immediate arg2, Immediate arg3) {
        emitA64(INST_CODE.INST_BFI_BITFIELD, arg0, arg1, arg2, arg3);
    }

  public final void bfm(Register arg0, Register arg1, Immediate arg2, Immediate arg3) {
        emitA64(INST_CODE.INST_BFM_BITFIELD, arg0, arg1, arg2, arg3);
    }

  public final void bfxil(Register arg0, Register arg1, Immediate arg2, Immediate arg3) {
        emitA64(INST_CODE.INST_BFXIL_BITFIELD, arg0, arg1, arg2, arg3);
    }

  public final void bge(Immediate arg0) {
        emitA64(INST_CODE.INST_BGE_CONDBRANCH, arg0);
    }

  public final void bgt(Immediate arg0) {
        emitA64(INST_CODE.INST_BGT_CONDBRANCH, arg0);
    }

  public final void bhi(Immediate arg0) {
        emitA64(INST_CODE.INST_BHI_CONDBRANCH, arg0);
    }

  public final void bhs(Immediate arg0) {
        emitA64(INST_CODE.INST_BHS_CONDBRANCH, arg0);
    }

  public final void bic(Register arg0, Register arg1, Register arg2, Shift arg3) {
        emitA64(INST_CODE.INST_BIC_LOG_SHIFT, arg0, arg1, arg2, arg3);
    }

  public final void bics(Register arg0, Register arg1, Register arg2, Shift arg3) {
        emitA64(INST_CODE.INST_BICS_LOG_SHIFT, arg0, arg1, arg2, arg3);
    }

  public final void bl(Immediate arg0) {
        emitA64(INST_CODE.INST_BL_BRANCH_IMM, arg0);
    }

  public final void ble(Immediate arg0) {
        emitA64(INST_CODE.INST_BLE_CONDBRANCH, arg0);
    }

  public final void blo(Immediate arg0) {
        emitA64(INST_CODE.INST_BLO_CONDBRANCH, arg0);
    }

  public final void blr(Register arg0) {
        emitA64(INST_CODE.INST_BLR_BRANCH_REG, arg0);
    }

  public final void bls(Immediate arg0) {
        emitA64(INST_CODE.INST_BLS_CONDBRANCH, arg0);
    }

  public final void blt(Immediate arg0) {
        emitA64(INST_CODE.INST_BLT_CONDBRANCH, arg0);
    }

  public final void bmi(Immediate arg0) {
        emitA64(INST_CODE.INST_BMI_CONDBRANCH, arg0);
    }

  public final void bne(Immediate arg0) {
        emitA64(INST_CODE.INST_BNE_CONDBRANCH, arg0);
    }

  public final void bpl(Immediate arg0) {
        emitA64(INST_CODE.INST_BPL_CONDBRANCH, arg0);
    }

  public final void br(Register arg0) {
        emitA64(INST_CODE.INST_BR_BRANCH_REG, arg0);
    }

  public final void brk(Immediate arg0) {
        emitA64(INST_CODE.INST_BRK_EXCEPTION, arg0);
    }

  public final void bvc(Immediate arg0) {
        emitA64(INST_CODE.INST_BVC_CONDBRANCH, arg0);
    }

  public final void bvs(Immediate arg0) {
        emitA64(INST_CODE.INST_BVS_CONDBRANCH, arg0);
    }

  public final void cbnz(Register arg0, Label arg1) {
        emitA64(INST_CODE.INST_CBNZ_COMPBRANCH, arg0, arg1);
    }

  public final void cbz(Register arg0, Label arg1) {
        emitA64(INST_CODE.INST_CBZ_COMPBRANCH, arg0, arg1);
    }

  public final void ccmn(Register arg0, Immediate arg1, Immediate arg2, Conditions arg3) {
        emitA64(INST_CODE.INST_CCMN_CONDCMP_IMM, arg0, arg1, arg2, arg3);
    }

  public final void ccmn(Register arg0, Register arg1, Immediate arg2, Conditions arg3) {
        emitA64(INST_CODE.INST_CCMN_CONDCMP_REG, arg0, arg1, arg2, arg3);
    }

  public final void ccmp(Register arg0, Immediate arg1, Immediate arg2, Conditions arg3) {
        emitA64(INST_CODE.INST_CCMP_CONDCMP_IMM, arg0, arg1, arg2, arg3);
    }

  public final void ccmp(Register arg0, Register arg1, Immediate arg2, Conditions arg3) {
        emitA64(INST_CODE.INST_CCMP_CONDCMP_REG, arg0, arg1, arg2, arg3);
    }

  public final void cinc(Register arg0, Register arg1, Conditions arg2) {
        emitA64(INST_CODE.INST_CINC_CONDSEL, arg0, arg1, arg2);
    }

  public final void cinv(Register arg0, Register arg1, Conditions arg2) {
        emitA64(INST_CODE.INST_CINV_CONDSEL, arg0, arg1, arg2);
    }

  public final void clrex(Immediate arg0) {
        emitA64(INST_CODE.INST_CLREX_IC_SYSTEM, arg0);
    }

  public final void cls(Register arg0, Register arg1) {
        emitA64(INST_CODE.INST_CLS_DP_1SRC, arg0, arg1);
    }

  public final void clz(Register arg0, Register arg1) {
        emitA64(INST_CODE.INST_CLZ_DP_1SRC, arg0, arg1);
    }

  public final void cmn(Register arg0, Register arg1, Ext arg2) {
        emitA64(INST_CODE.INST_CMN_ADDSUB_EXT, arg0, arg1, arg2);
    }

  public final void cmn(Register arg0, Immediate arg1, Shift arg2) {
        emitA64(INST_CODE.INST_CMN_ADDSUB_IMM, arg0, arg1, arg2);
    }

  public final void cmn(Register arg0, Register arg1, Shift arg2) {
        emitA64(INST_CODE.INST_CMN_ADDSUB_SHIFT, arg0, arg1, arg2);
    }

  public final void cmp(Register arg0, Register arg1, Ext arg2) {
        emitA64(INST_CODE.INST_CMP_ADDSUB_EXT, arg0, arg1, arg2);
    }

  public final void cmp(Register arg0, Immediate arg1, Shift arg2) {
        emitA64(INST_CODE.INST_CMP_ADDSUB_IMM, arg0, arg1, arg2);
    }

  public final void cmp(Register arg0, Register arg1, Shift arg2) {
        emitA64(INST_CODE.INST_CMP_ADDSUB_SHIFT, arg0, arg1, arg2);
    }

  public final void cneg(Register arg0, Register arg1, Conditions arg2) {
        emitA64(INST_CODE.INST_CNEG_CONDSEL, arg0, arg1, arg2);
    }

  public final void csel(Register arg0, Register arg1, Register arg2, Conditions arg3) {
        emitA64(INST_CODE.INST_CSEL_CONDSEL, arg0, arg1, arg2, arg3);
    }

  public final void cset(Register arg0, Conditions arg1) {
        emitA64(INST_CODE.INST_CSET_CONDSEL, arg0, arg1);
    }

  public final void csetm(Register arg0, Conditions arg1) {
        emitA64(INST_CODE.INST_CSETM_CONDSEL, arg0, arg1);
    }

  public final void csinc(Register arg0, Register arg1, Register arg2, Conditions arg3) {
        emitA64(INST_CODE.INST_CSINC_CONDSEL, arg0, arg1, arg2, arg3);
    }

  public final void csinv(Register arg0, Register arg1, Register arg2, Conditions arg3) {
        emitA64(INST_CODE.INST_CSINV_CONDSEL, arg0, arg1, arg2, arg3);
    }

  public final void csneg(Register arg0, Register arg1, Register arg2, Conditions arg3) {
        emitA64(INST_CODE.INST_CSNEG_CONDSEL, arg0, arg1, arg2, arg3);
    }

  public final void dc(Register arg0, Register arg1) {
        emitA64(INST_CODE.INST_DC_IC_SYSTEM, arg0, arg1);
    }

  public final void dcps1(Immediate arg0) {
        emitA64(INST_CODE.INST_DCPS1_EXCEPTION, arg0);
    }

  public final void dcps2(Immediate arg0) {
        emitA64(INST_CODE.INST_DCPS2_EXCEPTION, arg0);
    }

  public final void dcps3(Immediate arg0) {
        emitA64(INST_CODE.INST_DCPS3_EXCEPTION, arg0);
    }

  public final void dmb(Immediate arg0) {
        emitA64(INST_CODE.INST_DMB_IC_SYSTEM, arg0);
    }

  public final void drps() {
        emitA64(INST_CODE.INST_DRPS_BRANCH_REG);
    }

  public final void dsb(Immediate arg0) {
        emitA64(INST_CODE.INST_DSB_IC_SYSTEM, arg0);
    }

  public final void eon(Register arg0, Register arg1, Register arg2, Shift arg3) {
        emitA64(INST_CODE.INST_EON_LOG_SHIFT, arg0, arg1, arg2, arg3);
    }

  public final void eor(Register arg0, Register arg1, Immediate arg2) {
        emitA64(INST_CODE.INST_EOR_LOG_IMM, arg0, arg1, arg2);
    }

  public final void eor(Register arg0, Register arg1, Register arg2, Shift arg3) {
        emitA64(INST_CODE.INST_EOR_LOG_SHIFT, arg0, arg1, arg2, arg3);
    }

  public final void eret() {
        emitA64(INST_CODE.INST_ERET_BRANCH_REG);
    }

  public final void extr(Register arg0, Register arg1, Register arg2, Immediate arg3) {
        emitA64(INST_CODE.INST_EXTR_EXTRACT, arg0, arg1, arg2, arg3);
    }

  public final void hint(Immediate arg0) {
        emitA64(INST_CODE.INST_HINT_IC_SYSTEM, arg0);
    }

  public final void hlt(Immediate arg0) {
        emitA64(INST_CODE.INST_HLT_EXCEPTION, arg0);
    }

  public final void hvc(Immediate arg0) {
        emitA64(INST_CODE.INST_HVC_EXCEPTION, arg0);
    }

  public final void ic(Register arg0, Register arg1) {
        emitA64(INST_CODE.INST_IC_IC_SYSTEM, arg0, arg1);
    }

  public final void isb(Immediate arg0) {
        emitA64(INST_CODE.INST_ISB_IC_SYSTEM, arg0);
    }

  public final void ldar(Register arg0, Mem arg1) {
        emitA64(INST_CODE.INST_LDAR_LDSTEXCL, arg0, arg1);
    }

  public final void ldarb(Register arg0, Mem arg1) {
        emitA64(INST_CODE.INST_LDARB_LDSTEXCL, arg0, arg1);
    }

  public final void ldarh(Register arg0, Mem arg1) {
        emitA64(INST_CODE.INST_LDARH_LDSTEXCL, arg0, arg1);
    }

  public final void ldaxp(Register arg0, Mem arg1) {
        emitA64(INST_CODE.INST_LDAXP_LDSTEXCL, arg0, arg1);
    }

  public final void ldaxr(Register arg0, Mem arg1) {
        emitA64(INST_CODE.INST_LDAXR_LDSTEXCL, arg0, arg1);
    }

  public final void ldaxrb(Register arg0, Mem arg1) {
        emitA64(INST_CODE.INST_LDAXRB_LDSTEXCL, arg0, arg1);
    }

  public final void ldaxrh(Register arg0, Mem arg1) {
        emitA64(INST_CODE.INST_LDAXRH_LDSTEXCL, arg0, arg1);
    }

  public final void ldnp(Register arg0, Register arg1, Register arg2, Immediate arg3) {
        emitA64(INST_CODE.INST_LDNP_LDSTNAPAIR_OFFS, arg0, arg1, arg2, arg3);
    }

  public final void ldp(Register arg0, Register arg1, Post_index arg2) {
        emitA64(INST_CODE.INST_LDP_POST_INDEXED_IDST_IMM9, arg0, arg1, arg2);
    }

  public final void ldp(Register arg0, Register arg1, Pre_index arg2) {
        emitA64(INST_CODE.INST_LDP_PRE_INDEXED_IDST_IMM9, arg0, arg1, arg2);
    }

  public final void ldp(Register arg0, Register arg1, Offset arg2) {
        emitA64(INST_CODE.INST_LDP_LDSTPAIR_OFF_LDST_POS, arg0, arg1, arg2);
    }

  public final void ldpsw(Register arg0, Register arg1, Mem arg2, Immediate arg3) {
        emitA64(INST_CODE.INST_LDPSW_POST_INDEXED, arg0, arg1, arg2, arg3);
    }

  public final void ldpsw(Register arg0, Register arg1, Pre_index arg2) {
        emitA64(INST_CODE.INST_LDPSW_PRE_INDEXED, arg0, arg1, arg2);
    }

  public final void ldpsw(Register arg0, Register arg1, Offset arg2) {
        emitA64(INST_CODE.INST_LDPSW_OFF, arg0, arg1, arg2);
    }

  public final void ldr(Register arg0, Post_index arg1) {
        emitA64(INST_CODE.INST_LDR_IMM_POST, arg0, arg1);
    }

  public final void ldr(Register arg0, Pre_index arg1) {
        emitA64(INST_CODE.INST_LDR_IMM_PRE, arg0, arg1);
    }

  public final void ldr(Register arg0, Offset arg1) {
        emitA64(INST_CODE.INST_LDR_IMM_OFF, arg0, arg1);
    }

  public final void ldr(Register arg0, Mem arg1) {
        emitA64(INST_CODE.INST_LDR_REG, arg0, arg1);
    }

  public final void ldr(Register arg0, Immediate arg1) {
        emitA64(INST_CODE.INST_LDR_LOADLIT, arg1);
    }

  public final void ldrb(Register arg0, Mem arg1, Immediate arg2) {
        emitA64(INST_CODE.INST_LDRB_IMM_POST, arg0, arg1, arg2);
    }

  public final void ldrb(Register arg0, Pre_index arg1) {
        emitA64(INST_CODE.INST_LDRB_IMM_PRE, arg0, arg1);
    }

  public final void ldrb(Register arg0, Offset arg1) {
        emitA64(INST_CODE.INST_LDRB_IMM_OFF, arg0, arg1);
    }

  public final void ldrb(Register arg0, Mem arg1) {
        emitA64(INST_CODE.INST_LDRB_REG, arg0, arg1);
    }

  public final void ldrh(Register arg0, Mem arg1, Immediate arg2) {
        emitA64(INST_CODE.INST_LDRH_IMM_POST, arg0, arg1, arg2);
    }

  public final void ldrh(Register arg0, Pre_index arg1) {
        emitA64(INST_CODE.INST_LDRH_IMM_PRE, arg0, arg1);
    }

  public final void ldrh(Register arg0, Offset arg1) {
        emitA64(INST_CODE.INST_LDRH_IMM_OFF, arg0, arg1);
    }

  public final void ldrh(Register arg0, Mem arg1) {
        emitA64(INST_CODE.INST_LDRH_REG, arg0, arg1);
    }

  public final void ldrsb(Register arg0, Mem arg1, Immediate arg2) {
        emitA64(INST_CODE.INST_LDRSB_IMM_POST, arg0, arg1, arg2);
    }

  public final void ldrsb(Register arg0, Pre_index arg1) {
        emitA64(INST_CODE.INST_LDRSB_IMM_PRE, arg0, arg1);
    }

  public final void ldrsb(Register arg0, Offset arg1) {
        emitA64(INST_CODE.INST_LDRSB_IMM_OFF, arg0, arg1);
    }

  public final void ldrsb(Register arg0, Mem arg1) {
        emitA64(INST_CODE.INST_LDRSB_REG, arg0, arg1);
    }

  public final void ldrsh(Register arg0, Mem arg1, Immediate arg2) {
        emitA64(INST_CODE.INST_LDRSH_IMM_POST, arg0, arg1, arg2);
    }

  public final void ldrsh(Register arg0, Pre_index arg1) {
        emitA64(INST_CODE.INST_LDRSH_IMM_PRE, arg0, arg1);
    }

  public final void ldrsh(Register arg0, Offset arg1) {
        emitA64(INST_CODE.INST_LDRSH_IMM_OFF, arg0, arg1);
    }

  public final void ldrsh(Register arg0, Mem arg1) {
        emitA64(INST_CODE.INST_LDRSH_REG, arg0, arg1);
    }

  public final void ldrsw(Register arg0, Mem arg1, Immediate arg2) {
        emitA64(INST_CODE.INST_LDRSW_IMM_POST, arg0, arg1, arg2);
    }

  public final void ldrsw(Register arg0, Pre_index arg1) {
        emitA64(INST_CODE.INST_LDRSW_IMM_PRE, arg0, arg1);
    }

  public final void ldrsw(Register arg0, Offset arg1) {
        emitA64(INST_CODE.INST_LDRSW_IMM_OFF, arg0, arg1);
    }

  public final void ldrsw(Register arg0, Mem arg1) {
        emitA64(INST_CODE.INST_LDRSW_REG, arg0, arg1);
    }

  public final void ldrsw(Register arg0, Label arg1) {
        emitA64(INST_CODE.INST_LDRSW_LOADLIT, arg1);
    }

  public final void ldtr(Register arg0, Mem arg1) {
        emitA64(INST_CODE.INST_LDTR_LDST_UNPRIV, arg0, arg1);
    }

  public final void ldtrb(Register arg0, Mem arg1) {
        emitA64(INST_CODE.INST_LDTRB_LDST_UNPRIV, arg0, arg1);
    }

  public final void ldtrh(Register arg0, Mem arg1) {
        emitA64(INST_CODE.INST_LDTRH_LDST_UNPRIV, arg0, arg1);
    }

  public final void ldtrsb(Register arg0, Mem arg1) {
        emitA64(INST_CODE.INST_LDTRSB_LDST_UNPRIV, arg0, arg1);
    }

  public final void ldtrsh(Register arg0, Mem arg1) {
        emitA64(INST_CODE.INST_LDTRSH_LDST_UNPRIV, arg0, arg1);
    }

  public final void ldtrsw(Register arg0, Mem arg1) {
        emitA64(INST_CODE.INST_LDTRSW_LDST_UNPRIV, arg0, arg1);
    }

  public final void ldur(Register arg0, Mem arg1) {
        emitA64(INST_CODE.INST_LDUR_LDST_UNSCALED_X, arg0, arg1);
    }

  public final void ldurb(Register arg0, Mem arg1) {
        emitA64(INST_CODE.INST_LDURB_LDST_UNSCALED, arg0, arg1);
    }

  public final void ldurh(Register arg0, Mem arg1) {
        emitA64(INST_CODE.INST_LDURH_LDST_UNSCALED, arg0, arg1);
    }

  public final void ldursb(Register arg0, Mem arg1) {
        emitA64(INST_CODE.INST_LDURSB_LDST_UNSCALED, arg0, arg1);
    }

  public final void ldursh(Register arg0, Mem arg1) {
        emitA64(INST_CODE.INST_LDURSH_LDST_UNSCALED, arg0, arg1);
    }

  public final void ldursw(Register arg0, Mem arg1) {
        emitA64(INST_CODE.INST_LDURSW_LDST_UNSCALED, arg0, arg1);
    }

  public final void ldxp(Register arg0, Mem arg1) {
        emitA64(INST_CODE.INST_LDXP_LDSTEXCL, arg0, arg1);
    }

  public final void ldxr(Register arg0, Mem arg1) {
        emitA64(INST_CODE.INST_LDXR_LDSTEXCL, arg0, arg1);
    }

  public final void ldxrb(Register arg0, Mem arg1) {
        emitA64(INST_CODE.INST_LDXRB_LDSTEXCL, arg0, arg1);
    }

  public final void ldxrh(Register arg0, Mem arg1) {
        emitA64(INST_CODE.INST_LDXRH_LDSTEXCL, arg0, arg1);
    }

  public final void lsl(Register arg0, Register arg1, Immediate arg2) {
        emitA64(INST_CODE.INST_LSL_BITFIELD, arg0, arg1, arg2);
    }

  public final void lsl(Register arg0, Register arg1, Register arg2) {
        emitA64(INST_CODE.INST_LSL_DP_2SRC, arg0, arg1, arg2);
    }

  public final void lslv(Register arg0, Register arg1, Register arg2) {
        emitA64(INST_CODE.INST_LSLV_DP_2SRC, arg0, arg1, arg2);
    }

  public final void lsr(Register arg0, Register arg1, Immediate arg2) {
        emitA64(INST_CODE.INST_LSR_BITFIELD, arg0, arg1, arg2);
    }

  public final void lsr(Register arg0, Register arg1, Register arg2) {
        emitA64(INST_CODE.INST_LSR_DP_2SRC, arg0, arg1, arg2);
    }

  public final void lsrv(Register arg0, Register arg1, Register arg2) {
        emitA64(INST_CODE.INST_LSRV_DP_2SRC, arg0, arg1, arg2);
    }

  public final void madd(Register arg0, Register arg1, Register arg2, Register arg3) {
        emitA64(INST_CODE.INST_MADD_DP_3SRC, arg0, arg1, arg2, arg3);
    }

  public final void mneg(Register arg0, Register arg1, Register arg2) {
        emitA64(INST_CODE.INST_MNEG_DP_3SRC, arg0, arg1, arg2);
    }

  public final void mov(Register arg0, Register arg1) {
        if (arg0.code() >= 31) {
            emitA64(INST_CODE.INST_MOV_LOG_SHIFT, arg0, arg1);
        } else {
            if (arg1.code() >= 31) {
                emitA64(INST_CODE.INST_MOV_LOG_SHIFT, arg0, arg1);
            } else {
                emitA64(INST_CODE.INST_MOV_ADDSUB_IMM, arg0, arg1);
            }
        }
    }

  public final void mov(Register arg0, Shift arg1) {
        emitA64(INST_CODE.INST_MOV_LOG_SHIFT, arg0, arg1);
    }

  public final void mov(Register arg0, Immediate arg1) {
        if (arg0.code() >= 31) {
            emitA64(INST_CODE.INST_MOV_LOG_IMM, arg0, arg1);
        } else {
            emitA64(INST_CODE.INST_MOV_MOVEWIDE_X, arg0, arg1);
        }
    }

  public final void movk(Register arg0, Immediate arg1, Shift arg2) {
        emitA64(INST_CODE.INST_MOVK_MOVEWIDE, arg0, arg1, arg2);
    }

  public final void movn(Register arg0, Immediate arg1, Shift arg2) {
        emitA64(INST_CODE.INST_MOVN_MOVEWIDE, arg0, arg1, arg2);
    }

  public final void movz(Register arg0, Immediate arg1, Shift arg2) {
        emitA64(INST_CODE.INST_MOVZ_MOVEWIDE, arg0, arg1, arg2);
    }

  public final void mrs(Register arg0, Register arg1) {
        emitA64(INST_CODE.INST_MRS_IC_SYSTEM, arg0, arg1);
    }

  public final void msr(Register arg0, Immediate arg1) {
        emitA64(INST_CODE.INST_MSR_IC_SYSTEM, arg0, arg1);
    }

  public final void msr(SysRegister arg0, Register arg1) {
        emitA64(INST_CODE.INST_MSR_IC_SYSTEM_X, arg0, arg1);
    }

  public final void msub(Register arg0, Register arg1, Register arg2, Register arg3) {
        emitA64(INST_CODE.INST_MSUB_DP_3SRC, arg0, arg1, arg2, arg3);
    }

  public final void mul(Register arg0, Register arg1, Register arg2) {
        emitA64(INST_CODE.INST_MUL_DP_3SRC, arg0, arg1, arg2);
    }

  public final void mvn(Register arg0, Register arg1, Shift arg2) {
        emitA64(INST_CODE.INST_MVN_LOG_SHIFT, arg0, arg1, arg2);
    }

  public final void neg(Register arg0, Register arg1, Shift arg2) {
        emitA64(INST_CODE.INST_NEG_ADDSUB_SHIFT, arg0, arg1, arg2);
    }

  public final void negs(Register arg0, Register arg1, Shift arg2) {
        emitA64(INST_CODE.INST_NEGS_ADDSUB_SHIFT, arg0, arg1, arg2);
    }

  public final void ngc(Register arg0, Register arg1) {
        emitA64(INST_CODE.INST_NGC_ADDSUB_CARRY, arg0, arg1);
    }

  public final void ngcs(Register arg0, Register arg1) {
        emitA64(INST_CODE.INST_NGCS_ADDSUB_CARRY, arg0, arg1);
    }

  public final void nop() {
        emitA64(INST_CODE.INST_NOP_IC_SYSTEM);
    }

  public final void orn(Register arg0, Register arg1, Register arg2, Shift arg3) {
        emitA64(INST_CODE.INST_ORN_LOG_SHIFT, arg0, arg1, arg2, arg3);
    }

  public final void orr(Register arg0, Register arg1, Immediate arg2) {
        emitA64(INST_CODE.INST_ORR_LOG_IMM, arg0, arg1, arg2);
    }

  public final void orr(Register arg0, Register arg1, Register arg2, Shift arg3) {
        emitA64(INST_CODE.INST_ORR_LOG_SHIFT, arg0, arg1, arg2, arg3);
    }

  public final void prfm(PRFOP_ENUM arg0, Register arg1, Immediate arg2) {
        emitA64(INST_CODE.INST_PRFM_LDST_POS__IMMEDIATE, arg0, arg1, arg2);
    }

  public final void prfm(PRFOP_ENUM arg0, Immediate arg1) {
        emitA64(INST_CODE.INST_PRFM_LOADLIT__LITERAL, arg0, arg1);
    }

  public final void prfm(PRFOP_ENUM arg0, Register arg1, Register arg2, Ext arg3) {
        emitA64(INST_CODE.INST_PRFM_LDST_REGOFF__REGISTER, arg0, arg1, arg2, arg3);
    }

  public final void prfum(PRFOP_ENUM arg0, Register arg1, Immediate arg2) {
        emitA64(INST_CODE.INST_PRFUM_LDST_UNSCALED, arg0, arg1, arg2);
    }

  public final void rbit(Register arg0, Register arg1) {
        emitA64(INST_CODE.INST_RBIT_DP_1SRC, arg0, arg1);
    }

  public final void ret(Register arg0) {
        emitA64(INST_CODE.INST_RET_BRANCH_REG, arg0);
    }

  public final void rev(Register arg0, Register arg1) {
        emitA64(INST_CODE.INST_REV_DP_1SRC_X, arg0, arg1);
    }

  public final void rev16(Register arg0, Register arg1) {
        emitA64(INST_CODE.INST_REV16_DP_1SRC, arg0, arg1);
    }

  public final void rev32(Register arg0, Register arg1) {
        emitA64(INST_CODE.INST_REV32_DP_1SRC, arg0, arg1);
    }

  public final void ror(Register arg0, Register arg1, Register arg2) {
        emitA64(INST_CODE.INST_ROR_DP_2SRC, arg0, arg1, arg2);
    }

  public final void ror(Register arg0, Register arg1, Immediate arg2) {
        emitA64(INST_CODE.INST_ROR_EXTRACT, arg0, arg1, arg2);
    }

  public final void rorv(Register arg0, Register arg1, Register arg2) {
        emitA64(INST_CODE.INST_RORV_DP_2SRC, arg0, arg1, arg2);
    }

  public final void sbc(Register arg0, Register arg1, Register arg2) {
        emitA64(INST_CODE.INST_SBC_ADDSUB_CARRY, arg0, arg1, arg2);
    }

  public final void sbcs(Register arg0, Register arg1, Register arg2) {
        emitA64(INST_CODE.INST_SBCS_ADDSUB_CARRY, arg0, arg1, arg2);
    }

  public final void sbfiz(Register arg0, Register arg1, Immediate arg2, Immediate arg3) {
        emitA64(INST_CODE.INST_SBFIZ_BITFIELD, arg0, arg1, arg2, arg3);
    }

  public final void sbfm(Register arg0, Register arg1, Immediate arg2, Immediate arg3) {
        emitA64(INST_CODE.INST_SBFM_BITFIELD, arg0, arg1, arg2, arg3);
    }

  public final void sbfx(Register arg0, Register arg1, Immediate arg2, Immediate arg3) {
        emitA64(INST_CODE.INST_SBFX_BITFIELD, arg0, arg1, arg2, arg3);
    }

  public final void sdiv(Register arg0, Register arg1, Register arg2) {
        emitA64(INST_CODE.INST_SDIV_DP_2SRC, arg0, arg1, arg2);
    }

  public final void sev() {
        emitA64(INST_CODE.INST_SEV_IC_SYSTEM);
    }

  public final void sevl() {
        emitA64(INST_CODE.INST_SEVL_IC_SYSTEM);
    }

  public final void smaddl(Register arg0, Register arg1, Register arg2, Register arg3) {
        emitA64(INST_CODE.INST_SMADDL_DP_3SRC, arg0, arg1, arg2, arg3);
    }

  public final void smc(Immediate arg0) {
        emitA64(INST_CODE.INST_SMC_EXCEPTION, arg0);
    }

  public final void smnegl(Register arg0, Register arg1, Register arg2) {
        emitA64(INST_CODE.INST_SMNEGL_DP_3SRC, arg0, arg1, arg2);
    }

  public final void smsubl(Register arg0, Register arg1, Register arg2, Register arg3) {
        emitA64(INST_CODE.INST_SMSUBL_DP_3SRC, arg0, arg1, arg2, arg3);
    }

  public final void smulh(Register arg0, Register arg1, Register arg2) {
        emitA64(INST_CODE.INST_SMULH_DP_3SRC, arg0, arg1, arg2);
    }

  public final void smull(Register arg0, Register arg1, Register arg2) {
        emitA64(INST_CODE.INST_SMULL_DP_3SRC, arg0, arg1, arg2);
    }

  public final void stlr(Register arg0, Mem arg1) {
        emitA64(INST_CODE.INST_STLR_LDSTEXCL, arg0, arg1);
    }

  public final void stlrb(Register arg0, Register arg1, Immediate arg2) {
        emitA64(INST_CODE.INST_STLRB_LDSTEXCL, arg0, arg1, arg2);
    }

  public final void stlrh(Register arg0, Mem arg1) {
        emitA64(INST_CODE.INST_STLRH_LDSTEXCL, arg0, arg1);
    }

  public final void stlxp(Register arg0, Register arg1, Register arg2, Mem arg3) {
        emitA64(INST_CODE.INST_STLXP_LDSTEXCL, arg0, arg1, arg2, arg3);
    }

  public final void stlxr(Register arg0, Register arg1, Mem arg2) {
        emitA64(INST_CODE.INST_STLXR_LDSTEXCL, arg0, arg1, arg2);
    }

  public final void stlxrb(Register arg0, Register arg1, Mem arg2) {
        emitA64(INST_CODE.INST_STLXRB_LDSTEXCL, arg0, arg1, arg2);
    }

  public final void stlxrh(Register arg0, Register arg1, Mem arg2) {
        emitA64(INST_CODE.INST_STLXRH_LDSTEXCL, arg0, arg1, arg2);
    }

  public final void stnp(Register arg0, Register arg1, Mem arg2) {
        emitA64(INST_CODE.INST_STNP_LDSTNAPAIR_OFFS, arg0, arg1, arg2);
    }

  public final void stp(Register arg0, Register arg1, Post_index arg2) {
        emitA64(INST_CODE.INST_STP_LDSTPAIR_INDEXED_POST, arg0, arg1, arg2);
    }

  public final void stp(Register arg0, Register arg1, Pre_index arg2) {
        emitA64(INST_CODE.INST_STP_LDSTPAIR_INDEXED_PRE, arg0, arg1, arg2);
    }

  public final void stp(Register arg0, Register arg1, Offset arg2) {
        emitA64(INST_CODE.INST_STP_LDSTPAIR_OFF, arg0, arg1, arg2);
    }

  public final void str(Register arg0, Post_index arg1) {
        emitA64(INST_CODE.INST_STR_LDST_IMM9_POST, arg0, arg1);
    }

  public final void str(Register arg0, Pre_index arg1) {
        emitA64(INST_CODE.INST_STR_LDST_IMM9_PRE, arg0, arg1);
    }

  public final void str(Register arg0, Offset arg1) {
        emitA64(INST_CODE.INST_STR_LDST_POS, arg0, arg1);
    }

  public final void str(Register arg0, Register arg1, Register arg2, Ext arg3) {
        emitA64(INST_CODE.INST_STR_LDST_REGOFF, arg0, arg1, arg2, arg3);
    }

  public final void strb(Register arg0, Post_index arg1) {
        emitA64(INST_CODE.INST_STRB_LDST_IMM9_POST, arg0, arg1);
    }

  public final void strb(Register arg0, Pre_index arg1) {
        emitA64(INST_CODE.INST_STRB_LDST_PRE, arg0, arg1);
    }

  public final void strb(Register arg0, Offset arg1) {
        emitA64(INST_CODE.INST_STRB_LDST_OFFSET, arg0, arg1);
    }

  public final void strb(Register arg0, Register arg1, Register arg2, Ext arg3) {
        emitA64(INST_CODE.INST_STRB_LDST_REGOFF, arg0, arg1, arg2, arg3);
    }

  public final void strh(Register arg0, Post_index arg1) {
        emitA64(INST_CODE.INST_STRH_LDST_IMM_POST, arg0, arg1);
    }

  public final void strh(Register arg0, Pre_index arg1) {
        emitA64(INST_CODE.INST_STRH_LDST_IMM_PRE, arg0, arg1);
    }

  public final void strh(Register arg0, Offset arg1) {
        emitA64(INST_CODE.INST_STRH_LDST_IMM_OFF, arg0, arg1);
    }

  public final void strh(Register arg0, Register arg1, Register arg2, Ext arg3) {
        emitA64(INST_CODE.INST_STRH_LDST_REGOFF, arg0, arg1, arg2, arg3);
    }

  public final void sttr(Register arg0, Offset arg1) {
        emitA64(INST_CODE.INST_STTR_LDST_UNPRIV, arg0, arg1);
    }

  public final void sttrb(Register arg0, Offset arg1) {
        emitA64(INST_CODE.INST_STTRB_LDST_UNPRIV, arg0, arg1);
    }

  public final void sttrh(Register arg0, Offset arg1) {
        emitA64(INST_CODE.INST_STTRH_LDST_UNPRIV, arg0, arg1);
    }

  public final void stur(Register arg0, Offset arg1) {
        emitA64(INST_CODE.INST_STUR_LDST_UNSCALED_X, arg0, arg1);
    }

  public final void sturb(Register arg0, Offset arg1) {
        emitA64(INST_CODE.INST_STURB_LDST_UNSCALED, arg0, arg1);
    }

  public final void sturh(Register arg0, Offset arg1) {
        emitA64(INST_CODE.INST_STURH_LDST_UNSCALED, arg0, arg1);
    }

  public final void stxp(Register arg0, Register arg1, Register arg2, Register arg3, Immediate arg4) {
        emitA64(INST_CODE.INST_STXP_LDSTEXCL, arg0, arg1, arg2, arg3, arg4);
    }

  public final void stxr(Register arg0, Register arg1, Offset arg2) {
        emitA64(INST_CODE.INST_STXR_LDSTEXCL, arg0, arg1, arg2);
    }

  public final void stxrb(Register arg0, Register arg1, Offset arg2) {
        emitA64(INST_CODE.INST_STXRB_LDSTEXCL, arg0, arg1, arg2);
    }

  public final void stxrh(Register arg0, Register arg1, Offset arg2) {
        emitA64(INST_CODE.INST_STXRH_LDSTEXCL, arg0, arg1, arg2);
    }

  public final void sub(Register arg0, Register arg1, Register arg2, Ext arg3) {
        emitA64(INST_CODE.INST_SUB_ADDSUB_EXT, arg0, arg1, arg2, arg3);
    }

  public final void sub(Register arg0, Register arg1, Immediate arg2, Shift arg3) {
        emitA64(INST_CODE.INST_SUB_ADDSUB_IMM, arg0, arg1, arg2, arg3);
    }

  public final void sub(Register arg0, Register arg1, Register arg2, Shift arg3) {
        emitA64(INST_CODE.INST_SUB_ADDSUB_SHIFT, arg0, arg1, arg2, arg3);
    }

  public final void subs(Register arg0, Register arg1, Register arg2, Ext arg3) {
        emitA64(INST_CODE.INST_SUBS_ADDSUB_EXT, arg0, arg1, arg2, arg3);
    }

  public final void subs(Register arg0, Register arg1, Immediate arg2, Shift arg3) {
        emitA64(INST_CODE.INST_SUBS_ADDSUB_IMM, arg0, arg1, arg2, arg3);
    }

  public final void subs(Register arg0, Register arg1, Register arg2, Shift arg3) {
        emitA64(INST_CODE.INST_SUBS_ADDSUB_SHIFT, arg0, arg1, arg2, arg3);
    }

  public final void svc(Immediate arg0) {
        emitA64(INST_CODE.INST_SVC_EXCEPTION, arg0);
    }

  public final void sxtb(Register arg0, Register arg1) {
        emitA64(INST_CODE.INST_SXTB_BITFIELD, arg0, arg1);
    }

  public final void sxth(Register arg0, Register arg1) {
        emitA64(INST_CODE.INST_SXTH_BITFIELD, arg0, arg1);
    }

  public final void sxtw(Register arg0, Register arg1) {
        emitA64(INST_CODE.INST_SXTW_BITFIELD, arg0, arg1);
    }

  public final void tbnz(Register arg0, Immediate arg1, Label arg2) {
        emitA64(INST_CODE.INST_TBNZ_TESTBRANCH, arg0, arg1, arg2);
    }

  public final void tbz(Register arg0, Immediate arg1, Label arg2) {
        emitA64(INST_CODE.INST_TBZ_TESTBRANCH, arg0, arg1, arg2);
    }

  public final void tst(Register arg0, Immediate arg1) {
        emitA64(INST_CODE.INST_TST_LOG_IMM, arg0, arg1);
    }

  public final void tst(Register arg0, Register arg1, Shift arg2) {
        emitA64(INST_CODE.INST_TST_LOG_SHIFT, arg0, arg1, arg2);
    }

  public final void ubfiz(Register arg0, Register arg1, Immediate arg2, Immediate arg3) {
        emitA64(INST_CODE.INST_UBFIZ_BITFIELD, arg0, arg1, arg2, arg3);
    }

  public final void ubfm(Register arg0, Register arg1, Immediate arg2, Immediate arg3) {
        emitA64(INST_CODE.INST_UBFM_BITFIELD, arg0, arg1, arg2, arg3);
    }

  public final void ubfx(Register arg0, Register arg1, Immediate arg2, Immediate arg3) {
        emitA64(INST_CODE.INST_UBFX_BITFIELD, arg0, arg1, arg2, arg3);
    }

  public final void udiv(Register arg0, Register arg1, Register arg2) {
        emitA64(INST_CODE.INST_UDIV_DP_2SRC, arg0, arg1, arg2);
    }

  public final void umaddl(Register arg0, Register arg1, Register arg2, Register arg3) {
        emitA64(INST_CODE.INST_UMADDL_DP_3SRC, arg0, arg1, arg2, arg3);
    }

  public final void umnegl(Register arg0, Register arg1, Register arg2) {
        emitA64(INST_CODE.INST_UMNEGL_DP_3SRC, arg0, arg1, arg2);
    }

  public final void umsubl(Register arg0, Register arg1, Register arg2, Register arg3) {
        emitA64(INST_CODE.INST_UMSUBL_DP_3SRC, arg0, arg1, arg2, arg3);
    }

  public final void umulh(Register arg0, Register arg1, Register arg2) {
        emitA64(INST_CODE.INST_UMULH_DP_3SRC, arg0, arg1, arg2);
    }

  public final void umull(Register arg0, Register arg1, Register arg2) {
        emitA64(INST_CODE.INST_UMULL_DP_3SRC, arg0, arg1, arg2);
    }

  public final void uxtb(Register arg0, Register arg1) {
        emitA64(INST_CODE.INST_UXTB_BITFIELD, arg0, arg1);
    }

  public final void uxth(Register arg0, Register arg1) {
        emitA64(INST_CODE.INST_UXTH_BITFIELD, arg0, arg1);
    }

  public final void uxtw(Register arg0, Register arg1) {
        emitA64(INST_CODE.INST_UXTW_LOG_SHIFT, arg0, arg1);
    }

  public final void wfe() {
        emitA64(INST_CODE.INST_WFE_IC_SYSTEM);
    }

  public final void wfi() {
        emitA64(INST_CODE.INST_WFI_IC_SYSTEM);
    }

  public final void yield() {
        emitA64(INST_CODE.INST_YIELD_IC_SYSTEM);
    }

}