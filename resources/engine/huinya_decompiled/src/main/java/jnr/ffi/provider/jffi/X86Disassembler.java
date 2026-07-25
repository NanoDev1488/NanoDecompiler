// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.X86Disassembler
package jnr.ffi.provider.jffi;

import jnr.ffi.LibraryLoader;
import jnr.ffi.Memory;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.mapper.DefaultTypeMapper;
import jnr.ffi.provider.jffi.X86Disassembler_Mode;
import jnr.ffi.provider.jffi.X86Disassembler_SingletonHolder;
import jnr.ffi.provider.jffi.X86Disassembler_Syntax;
import jnr.ffi.provider.jffi.X86Disassembler_UDis86;
import jnr.ffi.provider.jffi.X86Disassembler_X86DisassemblerConverter;

class X86Disassembler {

    // ---- поля ----
  private final X86Disassembler_UDis86 udis86;
  final Pointer ud;

  static X86Disassembler_UDis86 loadUDis86() {
        DefaultTypeMapper var0 = new DefaultTypeMapper();
        var0.put(X86Disassembler.class, new X86Disassembler_X86DisassemblerConverter());
        return ((X86Disassembler_UDis86) LibraryLoader.create(X86Disassembler_UDis86.class).library("udis86").search("/usr/local/lib").search("/opt/local/lib").search("/usr/lib").mapper(var0).load());
    }

  static boolean isAvailable() {
        boolean __stk1;
        try {
            __stk1 = X86Disassembler_SingletonHolder.INSTANCE != null;
        } catch (Throwable var0) {
            return false;
        }
    }

  static X86Disassembler create() {
        return new X86Disassembler(X86Disassembler_SingletonHolder.INSTANCE);
    }

  private X86Disassembler(X86Disassembler_UDis86 arg0) { // было: <init>
        super();
        udis86 = arg0;
        ud = Memory.allocateDirect(Runtime.getRuntime(arg0), 1024, true);
        udis86.ud_init(ud);
    }

  public void setSyntax(X86Disassembler_Syntax arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #18 // jnr.ffi.provider.jffi.X86Disassembler.udis86:Ljnr/ffi/provider/jffi/X86Disassembler$UDis86;
        //      4: aload_0
        //      5: aload_1
        //      6: getstatic  #23 // jnr.ffi.provider.jffi.X86Disassembler$Syntax.INTEL:Ljnr/ffi/provider/jffi/X86Disassembler$Syntax;
        //      9: if_acmpne  18 (offset +9)
        //     12: getstatic  #22 // jnr.ffi.provider.jffi.X86Disassembler$SingletonHolder.intel:J
        //     15: goto  21 (offset +6)
        //     18: getstatic  #21 // jnr.ffi.provider.jffi.X86Disassembler$SingletonHolder.att:J
        //     21: invokeinterface  #43 // jnr.ffi.provider.jffi.X86Disassembler$UDis86.ud_set_syntax:(Ljnr/ffi/provider/jffi/X86Disassembler;J)V, count 4
        //     26: return
    }

  public void setMode(X86Disassembler_Mode arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #18 // jnr.ffi.provider.jffi.X86Disassembler.udis86:Ljnr/ffi/provider/jffi/X86Disassembler$UDis86;
        //      4: aload_0
        //      5: aload_1
        //      6: getstatic  #19 // jnr.ffi.provider.jffi.X86Disassembler$Mode.I386:Ljnr/ffi/provider/jffi/X86Disassembler$Mode;
        //      9: if_acmpne  17 (offset +8)
        //     12: bipush  32
        //     14: goto  19 (offset +5)
        //     17: bipush  64
        //     19: invokeinterface  #42 // jnr.ffi.provider.jffi.X86Disassembler$UDis86.ud_set_mode:(Ljnr/ffi/provider/jffi/X86Disassembler;I)V, count 3
        //     24: return
    }

  public void setInputBuffer(Pointer arg0, int arg1) {
        udis86.ud_set_input_buffer(this, arg0, ((long) arg1));
    }

  public boolean disassemble() {
        return udis86.ud_disassemble(this) != 0;
    }

  public String insn() {
        return udis86.ud_insn_asm(this);
    }

  public long offset() {
        return udis86.ud_insn_off(this);
    }

  public String hex() {
        return udis86.ud_insn_hex(this);
    }

}