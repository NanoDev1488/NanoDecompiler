// исходный (обфусцированный) внутренний класс: com.kenai.jffi.Platform.SingletonHolder
package com.kenai.jffi;

import com.kenai.jffi.Platform;

final class Platform_SingletonHolder {

    // ---- поля ----
  static final Platform PLATFORM;

    static {
        PLATFORM = Platform.access$200(Platform.access$100());
    }

  private Platform_SingletonHolder() { // было: <init>
        super();
    }

}