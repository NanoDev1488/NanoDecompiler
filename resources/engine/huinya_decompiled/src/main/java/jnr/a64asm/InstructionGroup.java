// исходный (обфусцированный) внутренний класс: jnr.a64asm.InstructionGroup
package jnr.a64asm;

public enum InstructionGroup {

    addsub_carry,
    addsub_ext,
    addsub_imm,
    addsub_shift,
    bitfield,
    branch_imm,
    branch_reg,
    compbranch,
    condbranch,
    condcmp_imm,
    condcmp_reg,
    condsel,
    dp_1src,
    dp_2src,
    dp_3src,
    exception,
    extract,
    ldst_imm9,
    ldst_pos,
    ldst_imm9_2reg,
    ldst_pos_2reg,
    ldst_regoff,
    ldst_unpriv,
    ldst_unscaled,
    ldstexcl,
    ldstexcl_op3,
    ldstexcl_op4,
    ldstnapair_offs,
    ldstpair_off,
    ldstpair_indexed,
    loadlit,
    log_imm,
    log_shift,
    movewide,
    pcreladdr,
    ic_system,
    testbranch;

  private InstructionGroup() { // было: <init>
        // (пустое тело)
    }

}