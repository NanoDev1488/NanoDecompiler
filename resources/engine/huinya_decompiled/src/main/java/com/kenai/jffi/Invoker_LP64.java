// исходный (обфусцированный) внутренний класс: com.kenai.jffi.Invoker.LP64
package com.kenai.jffi;

import com.kenai.jffi.CallContext;
import com.kenai.jffi.HeapInvocationBuffer;
import com.kenai.jffi.Invoker;

final class Invoker_LP64 extends Invoker {

    // ---- поля ----
  private static final Invoker INSTANCE;

    static {
        INSTANCE = new Invoker_LP64();
    }

  private Invoker_LP64() { // было: <init>
        super(null);
    }

  public final long invokeAddress(CallContext arg0, long arg1, HeapInvocationBuffer arg2) {
        return invokeLong(arg0, arg1, arg2);
    }

  static Invoker access$000() {
        return INSTANCE;
    }

}