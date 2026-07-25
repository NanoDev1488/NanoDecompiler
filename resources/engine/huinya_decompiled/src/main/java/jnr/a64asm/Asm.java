// исходный (обфусцированный) внутренний класс: jnr.a64asm.Asm
package jnr.a64asm;

import jnr.a64asm.CPU_A64;
import jnr.a64asm.Immediate;
import jnr.a64asm.Label;
import jnr.a64asm.Mem;
import jnr.a64asm.Register;

public final class Asm {

    // ---- поля ----
  public static final CPU_A64 Aarch_64;
  public static final Register no_reg;
  public static final Register x0;
  public static final Register x1;
  public static final Register x2;
  public static final Register x3;
  public static final Register x4;
  public static final Register x5;
  public static final Register x6;
  public static final Register x7;
  public static final Register fp;
  public static final Register lr;
  public static final Register sp;
  public static final Register w0;
  public static final Register w1;
  public static final Register w2;
  public static final Register w3;
  public static final Register w4;
  public static final Register w5;
  public static final Register w6;
  public static final Register w7;
  public static final Register w8;
  public static final Register w9;
  public static final Register w10;
  public static final Register w11;
  public static final Register w12;
  public static final Register w13;
  public static final Register w14;
  public static final Register w15;

    static {
        Aarch_64 = CPU_A64.A64;
        no_reg = new Register(255, 0);
        x0 = Register.gpr(0);
        x1 = Register.gpr(1);
        x2 = Register.gpr(2);
        x3 = Register.gpr(3);
        x4 = Register.gpr(4);
        x5 = Register.gpr(5);
        x6 = Register.gpr(6);
        x7 = Register.gpr(7);
        fp = Register.gpr(29);
        lr = Register.gpr(30);
        sp = Register.gpr(31);
        w0 = Register.gpr(32);
        w1 = Register.gpr(33);
        w2 = Register.gpr(34);
        w3 = Register.gpr(35);
        w4 = Register.gpr(36);
        w5 = Register.gpr(37);
        w6 = Register.gpr(38);
        w7 = Register.gpr(39);
        w8 = Register.gpr(40);
        w9 = Register.gpr(41);
        w10 = Register.gpr(42);
        w11 = Register.gpr(43);
        w12 = Register.gpr(44);
        w13 = Register.gpr(45);
        w14 = Register.gpr(46);
        w15 = Register.gpr(47);
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

  static final Mem _ptr_build_abs(long arg0, long arg1, int arg2) {
        return new Mem(arg0, arg1, arg2);
    }

  static final Mem _ptr_build_abs(long arg0, Register arg1, int arg2, long arg3, int arg4) {
        return new Mem(arg0, arg1, arg2, arg3, arg4);
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

  public static final Mem word_ptr(Label arg0, long arg1) {
        return _ptr_build(arg0, arg1, 32);
    }

  public static final Mem word_ptr(Label arg0) {
        return _ptr_build(arg0, 0L, 32);
    }

  public static final Mem dword_ptr(Label arg0, long arg1) {
        return _ptr_build(arg0, arg1, 64);
    }

  public static final Mem dword_ptr(Label arg0) {
        return _ptr_build(arg0, 0L, 64);
    }

  public static final Mem ptr(Label arg0, Register arg1, int arg2, long arg3) {
        return _ptr_build(arg0, arg1, arg2, arg3, 0);
    }

  public static final Mem word_ptr(Label arg0, Register arg1, int arg2, long arg3) {
        return _ptr_build(arg0, arg1, arg2, arg3, 32);
    }

  public static final Mem dword_ptr(Label arg0, Register arg1, int arg2, long arg3) {
        return _ptr_build(arg0, arg1, arg2, arg3, 64);
    }

  public static final Mem word_ptr_abs(long arg0, Register arg1, int arg2, long arg3) {
        return _ptr_build_abs(arg0, arg1, arg2, arg3, 32);
    }

  public static final Mem dword_ptr_abs(long arg0, Register arg1, int arg2, long arg3) {
        return _ptr_build_abs(arg0, arg1, arg2, arg3, 64);
    }

  public static final Mem ptr(Register arg0, long arg1) {
        return _ptr_build(arg0, arg1, 0);
    }

  public static final Mem word_ptr(Register arg0, long arg1) {
        return _ptr_build(arg0, arg1, 32);
    }

  public static final Mem dword_ptr(Register arg0, long arg1) {
        return _ptr_build(arg0, arg1, 64);
    }

  public static final Mem ptr(Register arg0, Register arg1, int arg2, long arg3) {
        return _ptr_build(arg0, arg1, arg2, arg3, 0);
    }

  public static final Mem word_ptr(Register arg0, Register arg1, int arg2, long arg3) {
        return _ptr_build(arg0, arg1, arg2, arg3, 32);
    }

  public static final Mem dword_ptr(Register arg0, Register arg1, int arg2, long arg3) {
        return _ptr_build(arg0, arg1, arg2, arg3, 64);
    }

  public static final Immediate imm(long arg0) {
        return Immediate.imm(arg0);
    }

  public static final Immediate uimm(long arg0) {
        return Immediate.imm(arg0);
    }

}