// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.X86Disassembler.UDis86
package jnr.ffi.provider.jffi;

import jnr.ffi.Pointer;
import jnr.ffi.provider.jffi.NoTrace;
import jnr.ffi.provider.jffi.NoX86;
import jnr.ffi.provider.jffi.X86Disassembler;
import jnr.ffi.types.intptr_t;
import jnr.ffi.types.size_t;
import jnr.ffi.types.u_int64_t;
import jnr.ffi.types.u_int8_t;

@NoX86
@NoTrace
public interface X86Disassembler_UDis86 {

  public abstract void ud_init(Pointer arg0);

  public abstract void ud_set_mode(X86Disassembler arg0, @u_int8_t int arg1);

  public abstract void ud_set_pc(X86Disassembler arg0, @u_int64_t int arg1);

  public abstract void ud_set_input_buffer(X86Disassembler arg0, Pointer arg1, @size_t long arg2);

  public abstract void ud_set_vendor(X86Disassembler arg0, int arg1);

  public abstract void ud_set_syntax(X86Disassembler arg0, @intptr_t long arg1);

  public abstract void ud_input_skip(X86Disassembler arg0, @size_t long arg1);

  public abstract int ud_input_end(X86Disassembler arg0);

  public abstract int ud_decode(X86Disassembler arg0);

  public abstract int ud_disassemble(X86Disassembler arg0);

  public abstract String ud_insn_asm(X86Disassembler arg0);

    @intptr_t
  public abstract long ud_insn_ptr(X86Disassembler arg0);

    @u_int64_t
  public abstract long ud_insn_off(X86Disassembler arg0);

  public abstract String ud_insn_hex(X86Disassembler arg0);

  public abstract int ud_insn_len(X86Disassembler arg0);

}