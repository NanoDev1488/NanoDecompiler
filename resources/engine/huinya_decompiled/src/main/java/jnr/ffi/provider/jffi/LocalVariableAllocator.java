// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.LocalVariableAllocator
package jnr.ffi.provider.jffi;

import jnr.ffi.provider.SigType;
import jnr.ffi.provider.jffi.AsmUtil;
import jnr.ffi.provider.jffi.LocalVariable;

class LocalVariableAllocator {

    // ---- поля ----
  private int nextIndex;

   LocalVariableAllocator(SigType[] arg0) { // было: <init>
        super();
        nextIndex = AsmUtil.calculateLocalVariableSpace(arg0) + 1;
    }

   LocalVariableAllocator(Class[] arg0) { // было: <init>
        super();
        nextIndex = AsmUtil.calculateLocalVariableSpace(arg0) + 1;
    }

   LocalVariableAllocator(int arg0) { // было: <init>
        super();
        nextIndex = arg0;
    }

   LocalVariable allocate(Class arg0) {
        LocalVariable var2 = new LocalVariable(arg0, nextIndex);
        nextIndex = nextIndex + AsmUtil.calculateLocalVariableSpace(arg0);
        return var2;
    }

   int getSpaceUsed() {
        return nextIndex;
    }

}