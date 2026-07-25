// исходный (обфусцированный) внутренний класс: com.kenai.jnr.x86asm.InstructionGroup
package com.kenai.jnr.x86asm;

@Deprecated
public enum InstructionGroup {

    I_EMIT,
    I_ALU,
    I_BSWAP,
    I_BT,
    I_CALL,
    I_CRC32,
    I_ENTER,
    I_IMUL,
    I_INC_DEC,
    I_J,
    I_JMP,
    I_LEA,
    I_M,
    I_MOV,
    I_MOV_PTR,
    I_MOVSX_MOVZX,
    I_MOVSXD,
    I_PUSH,
    I_POP,
    I_R_RM,
    I_RM_B,
    I_RM,
    I_RM_R,
    I_RET,
    I_ROT,
    I_SHLD_SHRD,
    I_TEST,
    I_XCHG,
    I_X87_FPU,
    I_X87_STI,
    I_X87_MEM_STI,
    I_X87_MEM,
    I_X87_FSTSW,
    I_MOVBE,
    I_MMU_MOV,
    I_MMU_MOVD,
    I_MMU_MOVQ,
    I_MMU_PEXTR,
    I_MMU_PREFETCH,
    I_MMU_RMI,
    I_MMU_RM_IMM8,
    I_MMU_RM_3DNOW;

  private InstructionGroup() { // было: <init>
        // (пустое тело)
    }

}