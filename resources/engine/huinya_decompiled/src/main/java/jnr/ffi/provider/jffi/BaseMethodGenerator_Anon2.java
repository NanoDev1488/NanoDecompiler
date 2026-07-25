// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.BaseMethodGenerator$2
package jnr.ffi.provider.jffi;

import jnr.ffi.provider.ParameterType;
import jnr.ffi.provider.jffi.AsmBuilder;
import jnr.ffi.provider.jffi.BaseMethodGenerator;
import jnr.ffi.provider.jffi.LocalVariable;
import jnr.ffi.provider.jffi.SkinnyMethodAdapter;

class BaseMethodGenerator_Anon2 implements Runnable {

    // ---- поля ----
  final AsmBuilder val$builder;
  final SkinnyMethodAdapter val$mv;
  final ParameterType[] val$parameterTypes;
  final LocalVariable[] val$parameters;
  final LocalVariable[] val$converted;
  final Runnable val$sessionCleanup;

   BaseMethodGenerator_Anon2(AsmBuilder arg0, SkinnyMethodAdapter arg1, ParameterType[] arg2, LocalVariable[] arg3, LocalVariable[] arg4, Runnable arg5) { // было: <init>
        super();
        val$builder = arg0;
        val$mv = arg1;
        val$parameterTypes = arg2;
        val$parameters = arg3;
        val$converted = arg4;
        val$sessionCleanup = arg5;
    }

  public void run() {
        BaseMethodGenerator.emitPostInvoke(val$builder, val$mv, val$parameterTypes, val$parameters, val$converted);
        if (val$sessionCleanup != null) {
            val$sessionCleanup.run();
        }
    }

}