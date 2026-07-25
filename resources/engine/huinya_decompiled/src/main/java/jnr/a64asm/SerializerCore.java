// исходный (обфусцированный) внутренний класс: jnr.a64asm.SerializerCore
package jnr.a64asm;

import jnr.a64asm.INST_CODE;
import jnr.a64asm.Operand;
import jnr.a64asm.SerializerCore_Anon1;

public abstract class SerializerCore {

    // ---- поля ----
  static final Operand _none;

    static {
        _none = new SerializerCore_Anon1(0, 0);
    }

  public SerializerCore() { // было: <init>
        super();
    }

  abstract void _emita64(INST_CODE arg0, Operand arg1, Operand arg2, Operand arg3, Operand arg4, Operand arg5);

   void emitA64(INST_CODE arg0) {
        _emita64(arg0, _none, _none, _none, _none, _none);
    }

   void emitA64(INST_CODE arg0, Operand arg1) {
        _emita64(arg0, arg1, _none, _none, _none, _none);
    }

   void emitA64(INST_CODE arg0, Operand arg1, Operand arg2) {
        _emita64(arg0, arg1, arg2, _none, _none, _none);
    }

   void emitA64(INST_CODE arg0, Operand arg1, Operand arg2, Operand arg3) {
        _emita64(arg0, arg1, arg2, arg3, _none, _none);
    }

   void emitA64(INST_CODE arg0, Operand arg1, Operand arg2, Operand arg3, Operand arg4) {
        _emita64(arg0, arg1, arg2, arg3, arg4, _none);
    }

   void emitA64(INST_CODE arg0, Operand arg1, Operand arg2, Operand arg3, Operand arg4, Operand arg5) {
        _emita64(arg0, arg1, arg2, arg3, arg4, arg5);
    }

  abstract boolean is64();

}