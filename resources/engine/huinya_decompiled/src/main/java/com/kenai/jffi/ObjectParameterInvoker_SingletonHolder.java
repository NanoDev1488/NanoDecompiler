// исходный (обфусцированный) внутренний класс: com.kenai.jffi.ObjectParameterInvoker.SingletonHolder
package com.kenai.jffi;

import com.kenai.jffi.Foreign;
import com.kenai.jffi.ObjectParameterInvoker;

final class ObjectParameterInvoker_SingletonHolder {

    // ---- поля ----
  static final ObjectParameterInvoker INSTANCE;

    static {
        ObjectParameterInvoker __stk1;
        __stk1 = Foreign.getInstance().getVersion() < 65546 ? ObjectParameterInvoker.newHeapInvoker() : ObjectParameterInvoker.newNativeInvoker();
        INSTANCE = __stk1;
    }

  private ObjectParameterInvoker_SingletonHolder() { // было: <init>
        super();
    }

}