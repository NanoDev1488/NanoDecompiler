// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.BaseMethodGenerator$1
package jnr.ffi.provider.jffi;

import jnr.ffi.provider.ResultType;
import jnr.ffi.provider.jffi.AsmBuilder;
import jnr.ffi.provider.jffi.AsmUtil;
import jnr.ffi.provider.jffi.SkinnyMethodAdapter;

class BaseMethodGenerator_Anon1 implements Runnable {

    // ---- поля ----
  final AsmBuilder val$builder;
  final SkinnyMethodAdapter val$mv;
  final ResultType val$resultType;
  final Class val$unboxedResultType;

   BaseMethodGenerator_Anon1(AsmBuilder arg0, SkinnyMethodAdapter arg1, ResultType arg2, Class arg3) { // было: <init>
        super();
        val$builder = arg0;
        val$mv = arg1;
        val$resultType = arg2;
        val$unboxedResultType = arg3;
    }

  public void run() {
        AsmUtil.emitFromNativeConversion(val$builder, val$mv, val$resultType, val$unboxedResultType);
        val$mv.nop();
    }

}