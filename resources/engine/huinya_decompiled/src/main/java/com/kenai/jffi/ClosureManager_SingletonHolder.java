// исходный (обфусцированный) внутренний класс: com.kenai.jffi.ClosureManager.SingletonHolder
package com.kenai.jffi;

import com.kenai.jffi.ClosureManager;

final class ClosureManager_SingletonHolder {

    // ---- поля ----
  static final ClosureManager INSTANCE;

    static {
        INSTANCE = new ClosureManager(null);
    }

  private ClosureManager_SingletonHolder() { // было: <init>
        super();
    }

}