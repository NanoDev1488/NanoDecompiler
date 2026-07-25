// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.BufferMethodGenerator.Operation
package jnr.ffi.provider.jffi;

import jnr.ffi.provider.jffi.BufferMethodGenerator_Anon1;

abstract class BufferMethodGenerator_Operation {

    // ---- поля ----
  final String methodName;
  final Class primitiveClass;

  private BufferMethodGenerator_Operation(String arg0, Class arg1) { // было: <init>
        super();
        methodName = arg0;
        primitiveClass = arg1;
    }

   BufferMethodGenerator_Operation(String arg0, Class arg1, BufferMethodGenerator_Anon1 arg2) { // было: <init>
        this(arg0, arg1);
    }

}