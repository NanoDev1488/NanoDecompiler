// исходный (обфусцированный) внутренний класс: com.kenai.jffi.LastError.SingletonHolder
package com.kenai.jffi;

import com.kenai.jffi.LastError;

final class LastError_SingletonHolder {

    // ---- поля ----
  static final LastError INSTANCE;

    static {
        INSTANCE = new LastError(null);
    }

  private LastError_SingletonHolder() { // было: <init>
        super();
    }

}