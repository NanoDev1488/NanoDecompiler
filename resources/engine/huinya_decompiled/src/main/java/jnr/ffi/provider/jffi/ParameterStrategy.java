// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.ParameterStrategy
package jnr.ffi.provider.jffi;

import com.kenai.jffi.ObjectParameterStrategy;
import com.kenai.jffi.ObjectParameterStrategy_StrategyType;
import com.kenai.jffi.ObjectParameterType;

public abstract class ParameterStrategy extends ObjectParameterStrategy {

    // ---- поля ----
  public final int objectCount;

  protected ParameterStrategy(ObjectParameterStrategy_StrategyType arg0) { // было: <init>
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: aload_1
        //      2: invokespecial  #6 // com.kenai.jffi.ObjectParameterStrategy.<init>:(Lcom/kenai/jffi/ObjectParameterStrategy$StrategyType;)V
        //      5: aload_0
        //      6: aload_1
        //      7: getstatic  #4 // jnr.ffi.provider.jffi.ParameterStrategy.HEAP:Lcom/kenai/jffi/ObjectParameterStrategy$StrategyType;
        //     10: if_acmpne  17 (offset +7)
        //     13: iconst_1
        //     14: goto  18 (offset +4)
        //     17: iconst_0
        //     18: putfield  #5 // jnr.ffi.provider.jffi.ParameterStrategy.objectCount:I
        //     21: return
    }

  protected ParameterStrategy(ObjectParameterStrategy_StrategyType arg0, ObjectParameterType arg1) { // было: <init>
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: aload_1
        //      2: aload_2
        //      3: invokespecial  #7 // com.kenai.jffi.ObjectParameterStrategy.<init>:(Lcom/kenai/jffi/ObjectParameterStrategy$StrategyType;Lcom/kenai/jffi/ObjectParameterType;)V
        //      6: aload_0
        //      7: aload_1
        //      8: getstatic  #4 // jnr.ffi.provider.jffi.ParameterStrategy.HEAP:Lcom/kenai/jffi/ObjectParameterStrategy$StrategyType;
        //     11: if_acmpne  18 (offset +7)
        //     14: iconst_1
        //     15: goto  19 (offset +4)
        //     18: iconst_0
        //     19: putfield  #5 // jnr.ffi.provider.jffi.ParameterStrategy.objectCount:I
        //     22: return
    }

}