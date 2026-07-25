// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.X86Disassembler.X86DisassemblerConverter
package jnr.ffi.provider.jffi;

import jnr.ffi.Pointer;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter;
import jnr.ffi.mapper.ToNativeConverter_NoContext;
import jnr.ffi.provider.jffi.X86Disassembler;

@ToNativeConverter_NoContext
public final class X86Disassembler_X86DisassemblerConverter implements ToNativeConverter {

  public X86Disassembler_X86DisassemblerConverter() { // было: <init>
        super();
    }

  public Pointer toNative(X86Disassembler arg0, ToNativeContext arg1) {
        return arg0.ud;
    }

  public Class nativeType() {
        return Pointer.class;
    }

  public Object toNative(Object arg0, ToNativeContext arg1) {
        return toNative(((X86Disassembler) arg0), arg1);
    }

}