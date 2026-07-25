// исходный (обфусцированный) внутренний класс: com.kenai.jffi.ObjectParameterStrategy
package com.kenai.jffi;

import com.kenai.jffi.ObjectParameterInfo;
import com.kenai.jffi.ObjectParameterStrategy_StrategyType;
import com.kenai.jffi.ObjectParameterType;

public abstract class ObjectParameterStrategy {

    // ---- поля ----
  private final boolean isDirect;
  final int typeInfo;
  protected static final ObjectParameterStrategy_StrategyType DIRECT;
  protected static final ObjectParameterStrategy_StrategyType HEAP;

    static {
        DIRECT = ObjectParameterStrategy_StrategyType.DIRECT;
        HEAP = ObjectParameterStrategy_StrategyType.HEAP;
    }

  public ObjectParameterStrategy(boolean arg0) { // было: <init>
        this(arg0, ObjectParameterType.INVALID);
    }

  public ObjectParameterStrategy(boolean arg0, ObjectParameterType arg1) { // было: <init>
        super();
        isDirect = arg0;
        typeInfo = arg1.typeInfo;
    }

  public ObjectParameterStrategy(ObjectParameterStrategy_StrategyType arg0) { // было: <init>
        this(arg0, ObjectParameterType.INVALID);
    }

  public ObjectParameterStrategy(ObjectParameterStrategy_StrategyType arg0, ObjectParameterType arg1) { // было: <init>
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokespecial  #18 // java.lang.Object.<init>:()V
        //      4: aload_0
        //      5: aload_1
        //      6: getstatic  #7 // com.kenai.jffi.ObjectParameterStrategy.DIRECT:Lcom/kenai/jffi/ObjectParameterStrategy$StrategyType;
        //      9: if_acmpne  16 (offset +7)
        //     12: iconst_1
        //     13: goto  17 (offset +4)
        //     16: iconst_0
        //     17: putfield  #9 // com.kenai.jffi.ObjectParameterStrategy.isDirect:Z
        //     20: aload_0
        //     21: aload_2
        //     22: getfield  #14 // com.kenai.jffi.ObjectParameterType.typeInfo:I
        //     25: putfield  #10 // com.kenai.jffi.ObjectParameterStrategy.typeInfo:I
        //     28: return
    }

  public final boolean isDirect() {
        return isDirect;
    }

  final int objectInfo(ObjectParameterInfo arg0) {
        int var2 = arg0.asObjectInfo();
        if (typeInfo == 0) {
            return var2;
        } else {
            return var2 & 16777215 | typeInfo;
        }
    }

  public abstract long address(Object arg0);

  public abstract Object object(Object arg0);

  public abstract int offset(Object arg0);

  public abstract int length(Object arg0);

}