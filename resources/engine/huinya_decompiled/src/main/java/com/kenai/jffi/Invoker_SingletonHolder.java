// исходный (обфусцированный) внутренний класс: com.kenai.jffi.Invoker.SingletonHolder
package com.kenai.jffi;

import com.kenai.jffi.Invoker;
import com.kenai.jffi.Invoker_ILP32;
import com.kenai.jffi.Invoker_LP64;
import com.kenai.jffi.Platform;

final class Invoker_SingletonHolder {

    // ---- поля ----
  private static final Invoker INSTANCE;

    static {
        Invoker __stk1;
        __stk1 = Platform.getPlatform().addressSize() != 64 ? Invoker_ILP32.access$100() : Invoker_LP64.access$000();
        INSTANCE = __stk1;
    }

  private Invoker_SingletonHolder() { // было: <init>
        super();
    }

  static Invoker access$200() {
        return INSTANCE;
    }

}