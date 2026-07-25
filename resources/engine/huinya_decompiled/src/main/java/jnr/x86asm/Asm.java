// исходный (обфусцированный) внутренний класс: jnr.x86asm.Asm
package jnr.x86asm;

import jnr.x86asm.CPU;
import jnr.x86asm.Immediate;
import jnr.x86asm.Label;
import jnr.x86asm.MMRegister;
import jnr.x86asm.Mem;
import jnr.x86asm.Register;
import jnr.x86asm.SEGMENT;
import jnr.x86asm.XMMRegister;

public final class Asm {

    // ---- поля ----
    @Deprecated
  public static final CPU I386;
  public static final CPU X86_32;
  public static final CPU X86_64;
  public static final Register no_reg;
  public static final Register al;
  public static final Register cl;
  public static final Register dl;
  public static final Register bl;
  public static final Register ah;
  public static final Register ch;
  public static final Register dh;
  public static final Register bh;
  public static final Register r8b;
  public static final Register r9b;
  public static final Register r10b;
  public static final Register r11b;
  public static final Register r12b;
  public static final Register r13b;
  public static final Register r14b;
  public static final Register r15b;
  public static final Register ax;
  public static final Register cx;
  public static final Register dx;
  public static final Register bx;
  public static final Register sp;
  public static final Register bp;
  public static final Register si;
  public static final Register di;
  public static final Register r8w;
  public static final Register r9w;
  public static final Register r10w;
  public static final Register r11w;
  public static final Register r12w;
  public static final Register r13w;
  public static final Register r14w;
  public static final Register r15w;
  public static final Register eax;
  public static final Register ecx;
  public static final Register edx;
  public static final Register ebx;
  public static final Register esp;
  public static final Register ebp;
  public static final Register esi;
  public static final Register edi;
  public static final Register rax;
  public static final Register rcx;
  public static final Register rdx;
  public static final Register rbx;
  public static final Register rsp;
  public static final Register rbp;
  public static final Register rsi;
  public static final Register rdi;
  public static final Register r8;
  public static final Register r9;
  public static final Register r10;
  public static final Register r11;
  public static final Register r12;
  public static final Register r13;
  public static final Register r14;
  public static final Register r15;
  public static final MMRegister mm0;
  public static final MMRegister mm1;
  public static final MMRegister mm2;
  public static final MMRegister mm3;
  public static final MMRegister mm4;
  public static final MMRegister mm5;
  public static final MMRegister mm6;
  public static final MMRegister mm7;
  public static final XMMRegister xmm0;
  public static final XMMRegister xmm1;
  public static final XMMRegister xmm2;
  public static final XMMRegister xmm3;
  public static final XMMRegister xmm4;
  public static final XMMRegister xmm5;
  public static final XMMRegister xmm6;
  public static final XMMRegister xmm7;
  public static final XMMRegister xmm8;
  public static final XMMRegister xmm9;
  public static final XMMRegister xmm10;
  public static final XMMRegister xmm11;
  public static final XMMRegister xmm12;
  public static final XMMRegister xmm13;
  public static final XMMRegister xmm14;
  public static final XMMRegister xmm15;

    static {
        I386 = CPU.I386;
        X86_32 = CPU.X86_32;
        X86_64 = CPU.X86_64;
        no_reg = new Register(255, 0);
        al = Register.gpr(0);
        cl = Register.gpr(1);
        dl = Register.gpr(2);
        bl = Register.gpr(3);
        ah = Register.gpr(4);
        ch = Register.gpr(5);
        dh = Register.gpr(6);
        bh = Register.gpr(7);
        r8b = Register.gpr(8);
        r9b = Register.gpr(9);
        r10b = Register.gpr(10);
        r11b = Register.gpr(11);
        r12b = Register.gpr(12);
        r13b = Register.gpr(13);
        r14b = Register.gpr(14);
        r15b = Register.gpr(15);
        ax = Register.gpr(16);
        cx = Register.gpr(17);
        dx = Register.gpr(18);
        bx = Register.gpr(19);
        sp = Register.gpr(20);
        bp = Register.gpr(21);
        si = Register.gpr(22);
        di = Register.gpr(23);
        r8w = Register.gpr(24);
        r9w = Register.gpr(25);
        r10w = Register.gpr(26);
        r11w = Register.gpr(27);
        r12w = Register.gpr(28);
        r13w = Register.gpr(29);
        r14w = Register.gpr(30);
        r15w = Register.gpr(31);
        eax = Register.gpr(32);
        ecx = Register.gpr(33);
        edx = Register.gpr(34);
        ebx = Register.gpr(35);
        esp = Register.gpr(36);
        ebp = Register.gpr(37);
        esi = Register.gpr(38);
        edi = Register.gpr(39);
        rax = Register.gpr(48);
        rcx = Register.gpr(49);
        rdx = Register.gpr(50);
        rbx = Register.gpr(51);
        rsp = Register.gpr(52);
        rbp = Register.gpr(53);
        rsi = Register.gpr(54);
        rdi = Register.gpr(55);
        r8 = Register.gpr(56);
        r9 = Register.gpr(57);
        r10 = Register.gpr(58);
        r11 = Register.gpr(59);
        r12 = Register.gpr(60);
        r13 = Register.gpr(61);
        r14 = Register.gpr(62);
        r15 = Register.gpr(63);
        mm0 = MMRegister.mm(0);
        mm1 = MMRegister.mm(1);
        mm2 = MMRegister.mm(2);
        mm3 = MMRegister.mm(3);
        mm4 = MMRegister.mm(4);
        mm5 = MMRegister.mm(5);
        mm6 = MMRegister.mm(6);
        mm7 = MMRegister.mm(7);
        xmm0 = XMMRegister.xmm(0);
        xmm1 = XMMRegister.xmm(1);
        xmm2 = XMMRegister.xmm(2);
        xmm3 = XMMRegister.xmm(3);
        xmm4 = XMMRegister.xmm(4);
        xmm5 = XMMRegister.xmm(5);
        xmm6 = XMMRegister.xmm(6);
        xmm7 = XMMRegister.xmm(7);
        xmm8 = XMMRegister.xmm(8);
        xmm9 = XMMRegister.xmm(9);
        xmm10 = XMMRegister.xmm(10);
        xmm11 = XMMRegister.xmm(11);
        xmm12 = XMMRegister.xmm(12);
        xmm13 = XMMRegister.xmm(13);
        xmm14 = XMMRegister.xmm(14);
        xmm15 = XMMRegister.xmm(15);
    }

  private Asm() { // было: <init>
        super();
    }

  static final Mem _ptr_build(Label arg0, long arg1, int arg2) {
        return new Mem(arg0, arg1, arg2);
    }

  static final Mem _ptr_build(Label arg0, Register arg1, int arg2, long arg3, int arg4) {
        return new Mem(arg0, arg1, arg2, arg3, arg4);
    }

  static final Mem _ptr_build_abs(long arg0, long arg1, SEGMENT arg2, int arg3) {
        return new Mem(arg0, arg1, arg2, arg3);
    }

  static final Mem _ptr_build_abs(long arg0, Register arg1, int arg2, long arg3, SEGMENT arg4, int arg5) {
        return new Mem(arg0, arg1, arg2, arg4, arg3, arg5);
    }

  static final Mem _ptr_build(Register arg0, long arg1, int arg2) {
        return new Mem(arg0, arg1, arg2);
    }

  static final Mem _ptr_build(Register arg0, Register arg1, int arg2, long arg3, int arg4) {
        return new Mem(arg0, arg1, arg2, arg3, arg4);
    }

  public static final Mem ptr(Label arg0, long arg1) {
        return _ptr_build(arg0, arg1, 0);
    }

  public static final Mem ptr(Label arg0) {
        return _ptr_build(arg0, 0L, 0);
    }

  public static final Mem byte_ptr(Label arg0, long arg1) {
        return _ptr_build(arg0, arg1, 1);
    }

  public static final Mem byte_ptr(Label arg0) {
        return _ptr_build(arg0, 0L, 1);
    }

  public static final Mem word_ptr(Label arg0, long arg1) {
        return _ptr_build(arg0, arg1, 2);
    }

  public static final Mem word_ptr(Label arg0) {
        return _ptr_build(arg0, 0L, 2);
    }

  public static final Mem dword_ptr(Label arg0, long arg1) {
        return _ptr_build(arg0, arg1, 4);
    }

  public static final Mem dword_ptr(Label arg0) {
        return _ptr_build(arg0, 0L, 4);
    }

  public static final Mem qword_ptr(Label arg0, long arg1) {
        return _ptr_build(arg0, arg1, 8);
    }

  public static final Mem qword_ptr(Label arg0) {
        return _ptr_build(arg0, 0L, 8);
    }

  public static final Mem tword_ptr(Label arg0, long arg1) {
        return _ptr_build(arg0, arg1, 10);
    }

  public static final Mem tword_ptr(Label arg0) {
        return _ptr_build(arg0, 0L, 10);
    }

  public static final Mem dqword_ptr(Label arg0, long arg1) {
        return _ptr_build(arg0, arg1, 16);
    }

  public static final Mem dqword_ptr(Label arg0) {
        return _ptr_build(arg0, 0L, 16);
    }

  public static final Mem mmword_ptr(Label arg0, long arg1) {
        return _ptr_build(arg0, arg1, 8);
    }

  public static final Mem mmword_ptr(Label arg0) {
        return _ptr_build(arg0, 0L, 8);
    }

  public static final Mem xmmword_ptr(Label arg0, long arg1) {
        return _ptr_build(arg0, arg1, 16);
    }

  public static final Mem xmmword_ptr(Label arg0) {
        return _ptr_build(arg0, 0L, 16);
    }

  public static final Mem ptr(Label arg0, Register arg1, int arg2, long arg3) {
        return _ptr_build(arg0, arg1, arg2, arg3, 0);
    }

  public static final Mem byte_ptr(Label arg0, Register arg1, int arg2, long arg3) {
        return _ptr_build(arg0, arg1, arg2, arg3, 1);
    }

  public static final Mem word_ptr(Label arg0, Register arg1, int arg2, long arg3) {
        return _ptr_build(arg0, arg1, arg2, arg3, 2);
    }

  public static final Mem dword_ptr(Label arg0, Register arg1, int arg2, long arg3) {
        return _ptr_build(arg0, arg1, arg2, arg3, 4);
    }

  public static final Mem qword_ptr(Label arg0, Register arg1, int arg2, long arg3) {
        return _ptr_build(arg0, arg1, arg2, arg3, 8);
    }

  public static final Mem tword_ptr(Label arg0, Register arg1, int arg2, long arg3) {
        return _ptr_build(arg0, arg1, arg2, arg3, 10);
    }

  public static final Mem dqword_ptr(Label arg0, Register arg1, int arg2, long arg3) {
        return _ptr_build(arg0, arg1, arg2, arg3, 16);
    }

  public static final Mem mmword_ptr(Label arg0, Register arg1, int arg2, long arg3) {
        return _ptr_build(arg0, arg1, arg2, arg3, 8);
    }

  public static final Mem xmmword_ptr(Label arg0, Register arg1, int arg2, long arg3) {
        return _ptr_build(arg0, arg1, arg2, arg3, 16);
    }

  public static final Mem ptr_abs(long arg0, long arg1, SEGMENT arg2) {
        return _ptr_build_abs(arg0, arg1, arg2, 0);
    }

  public static final Mem byte_ptr_abs(long arg0, long arg1, SEGMENT arg2) {
        return _ptr_build_abs(arg0, arg1, arg2, 1);
    }

  public static final Mem word_ptr_abs(long arg0, long arg1, SEGMENT arg2) {
        return _ptr_build_abs(arg0, arg1, arg2, 2);
    }

  public static final Mem dword_ptr_abs(long arg0, long arg1, SEGMENT arg2) {
        return _ptr_build_abs(arg0, arg1, arg2, 4);
    }

  public static final Mem qword_ptr_abs(long arg0, long arg1, SEGMENT arg2) {
        return _ptr_build_abs(arg0, arg1, arg2, 8);
    }

  public static final Mem tword_ptr_abs(long arg0, long arg1, SEGMENT arg2) {
        return _ptr_build_abs(arg0, arg1, arg2, 10);
    }

  public static final Mem dqword_ptr_abs(long arg0, long arg1, SEGMENT arg2) {
        return _ptr_build_abs(arg0, arg1, arg2, 16);
    }

  public static final Mem mmword_ptr_abs(long arg0, long arg1, SEGMENT arg2) {
        return _ptr_build_abs(arg0, arg1, arg2, 8);
    }

  public static final Mem xmmword_ptr_abs(long arg0, long arg1, SEGMENT arg2) {
        return _ptr_build_abs(arg0, arg1, arg2, 16);
    }

  public static final Mem ptr_abs(long arg0, Register arg1, int arg2, long arg3, SEGMENT arg4) {
        return _ptr_build_abs(arg0, arg1, arg2, arg3, arg4, 0);
    }

  public static final Mem byte_ptr_abs(long arg0, Register arg1, int arg2, long arg3, SEGMENT arg4) {
        return _ptr_build_abs(arg0, arg1, arg2, arg3, arg4, 1);
    }

  public static final Mem word_ptr_abs(long arg0, Register arg1, int arg2, long arg3, SEGMENT arg4) {
        return _ptr_build_abs(arg0, arg1, arg2, arg3, arg4, 2);
    }

  public static final Mem dword_ptr_abs(long arg0, Register arg1, int arg2, long arg3, SEGMENT arg4) {
        return _ptr_build_abs(arg0, arg1, arg2, arg3, arg4, 4);
    }

  public static final Mem qword_ptr_abs(long arg0, Register arg1, int arg2, long arg3, SEGMENT arg4) {
        return _ptr_build_abs(arg0, arg1, arg2, arg3, arg4, 8);
    }

  public static final Mem tword_ptr_abs(long arg0, Register arg1, int arg2, long arg3, SEGMENT arg4) {
        return _ptr_build_abs(arg0, arg1, arg2, arg3, arg4, 10);
    }

  public static final Mem dqword_ptr_abs(long arg0, Register arg1, int arg2, long arg3, SEGMENT arg4) {
        return _ptr_build_abs(arg0, arg1, arg2, arg3, arg4, 16);
    }

  public static final Mem mmword_ptr_abs(long arg0, Register arg1, int arg2, long arg3, SEGMENT arg4) {
        return _ptr_build_abs(arg0, arg1, arg2, arg3, arg4, 8);
    }

  public static final Mem xmmword_ptr_abs(long arg0, Register arg1, int arg2, long arg3, SEGMENT arg4) {
        return _ptr_build_abs(arg0, arg1, arg2, arg3, arg4, 16);
    }

  public static final Mem ptr(Register arg0, long arg1) {
        return _ptr_build(arg0, arg1, 0);
    }

  public static final Mem byte_ptr(Register arg0, long arg1) {
        return _ptr_build(arg0, arg1, 1);
    }

  public static final Mem word_ptr(Register arg0, long arg1) {
        return _ptr_build(arg0, arg1, 2);
    }

  public static final Mem dword_ptr(Register arg0, long arg1) {
        return _ptr_build(arg0, arg1, 4);
    }

  public static final Mem qword_ptr(Register arg0, long arg1) {
        return _ptr_build(arg0, arg1, 8);
    }

  public static final Mem tword_ptr(Register arg0, long arg1) {
        return _ptr_build(arg0, arg1, 10);
    }

  public static final Mem dqword_ptr(Register arg0, long arg1) {
        return _ptr_build(arg0, arg1, 16);
    }

  public static final Mem mmword_ptr(Register arg0, long arg1) {
        return _ptr_build(arg0, arg1, 8);
    }

  public static final Mem xmmword_ptr(Register arg0, long arg1) {
        return _ptr_build(arg0, arg1, 16);
    }

  public static final Mem ptr(Register arg0, Register arg1, int arg2, long arg3) {
        return _ptr_build(arg0, arg1, arg2, arg3, 0);
    }

  public static final Mem byte_ptr(Register arg0, Register arg1, int arg2, long arg3) {
        return _ptr_build(arg0, arg1, arg2, arg3, 1);
    }

  public static final Mem word_ptr(Register arg0, Register arg1, int arg2, long arg3) {
        return _ptr_build(arg0, arg1, arg2, arg3, 2);
    }

  public static final Mem dword_ptr(Register arg0, Register arg1, int arg2, long arg3) {
        return _ptr_build(arg0, arg1, arg2, arg3, 4);
    }

  public static final Mem qword_ptr(Register arg0, Register arg1, int arg2, long arg3) {
        return _ptr_build(arg0, arg1, arg2, arg3, 8);
    }

  public static final Mem tword_ptr(Register arg0, Register arg1, int arg2, long arg3) {
        return _ptr_build(arg0, arg1, arg2, arg3, 10);
    }

  public static final Mem dqword_ptr(Register arg0, Register arg1, int arg2, long arg3) {
        return _ptr_build(arg0, arg1, arg2, arg3, 16);
    }

  public static final Mem mmword_ptr(Register arg0, Register arg1, int arg2, long arg3) {
        return _ptr_build(arg0, arg1, arg2, arg3, 8);
    }

  public static final Mem xmmword_ptr(Register arg0, Register arg1, int arg2, long arg3) {
        return _ptr_build(arg0, arg1, arg2, arg3, 16);
    }

  public static final Immediate imm(long arg0) {
        return Immediate.imm(arg0);
    }

  public static final Immediate uimm(long arg0) {
        return Immediate.imm(arg0);
    }

}