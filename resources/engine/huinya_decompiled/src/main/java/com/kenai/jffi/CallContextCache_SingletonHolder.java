// исходный (обфусцированный) внутренний класс: com.kenai.jffi.CallContextCache.SingletonHolder
package com.kenai.jffi;

import com.kenai.jffi.CallContextCache;

final class CallContextCache_SingletonHolder {

    // ---- поля ----
  static final CallContextCache INSTANCE;

    static {
        INSTANCE = new CallContextCache(null);
    }

  private CallContextCache_SingletonHolder() { // было: <init>
        super();
    }

}