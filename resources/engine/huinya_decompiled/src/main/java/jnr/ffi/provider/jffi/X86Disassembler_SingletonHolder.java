// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.X86Disassembler.SingletonHolder
package jnr.ffi.provider.jffi;

import jnr.ffi.provider.jffi.AbstractAsmLibraryInterface;
import jnr.ffi.provider.jffi.NativeLibrary;
import jnr.ffi.provider.jffi.X86Disassembler;
import jnr.ffi.provider.jffi.X86Disassembler_UDis86;

final class X86Disassembler_SingletonHolder {

    // ---- поля ----
  static final X86Disassembler_UDis86 INSTANCE;
  static final long intel;
  static final long att;

    static {
        INSTANCE = X86Disassembler.loadUDis86();
        intel = (((AbstractAsmLibraryInterface) INSTANCE)).getLibrary().findSymbolAddress("ud_translate_intel");
        att = (((AbstractAsmLibraryInterface) INSTANCE)).getLibrary().findSymbolAddress("ud_translate_att");
    }

   X86Disassembler_SingletonHolder() { // было: <init>
        super();
    }

}