// исходный (обфусцированный) внутренний класс: com.kenai.jffi.Invoker.ILP32
package com.kenai.jffi;

import com.kenai.jffi.CallContext;
import com.kenai.jffi.HeapInvocationBuffer;
import com.kenai.jffi.Invoker;

final class Invoker_ILP32 extends Invoker {

    // ---- поля ----
  private static final Invoker INSTANCE;
  private static final long ADDRESS_MASK = 4294967295L;

    static {
        INSTANCE = new Invoker_ILP32();
    }

  private Invoker_ILP32() { // было: <init>
        super(null);
    }

  public final long invokeAddress(CallContext arg0, long arg1, HeapInvocationBuffer arg2) {
        return ((long) invokeInt(arg0, arg1, arg2)) & 4294967295L;
    }

  static Invoker access$100() {
        return INSTANCE;
    }

}