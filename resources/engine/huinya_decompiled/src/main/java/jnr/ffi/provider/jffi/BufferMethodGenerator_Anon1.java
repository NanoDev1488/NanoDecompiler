// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.BufferMethodGenerator$1
package jnr.ffi.provider.jffi;

import jnr.ffi.provider.InvocationSession;
import jnr.ffi.provider.jffi.BufferMethodGenerator;
import jnr.ffi.provider.jffi.CodegenUtils;
import jnr.ffi.provider.jffi.LocalVariable;
import jnr.ffi.provider.jffi.SkinnyMethodAdapter;

class BufferMethodGenerator_Anon1 implements Runnable {

    // ---- поля ----
  final SkinnyMethodAdapter val$mv;
  final LocalVariable val$session;
  final BufferMethodGenerator this$0;

   BufferMethodGenerator_Anon1(BufferMethodGenerator arg0, SkinnyMethodAdapter arg1, LocalVariable arg2) { // было: <init>
        super();
        this$0 = arg0;
        val$mv = arg1;
        val$session = arg2;
    }

  public void run() {
        val$mv.aload(val$session);
        val$mv.invokevirtual(CodegenUtils.method1942(InvocationSession.class), "finish", "()V");
    }

}