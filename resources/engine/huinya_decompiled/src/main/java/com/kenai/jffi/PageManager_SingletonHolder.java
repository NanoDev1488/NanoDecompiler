// исходный (обфусцированный) внутренний класс: com.kenai.jffi.PageManager.SingletonHolder
package com.kenai.jffi;

import com.kenai.jffi.PageManager;
import com.kenai.jffi.PageManager_Unix;
import com.kenai.jffi.PageManager_Windows;
import com.kenai.jffi.Platform;
import com.kenai.jffi.Platform_OS;

final class PageManager_SingletonHolder {

    // ---- поля ----
  public static final PageManager INSTANCE;

    static {
        PageManager_Unix __stk1;
        __stk1 = Platform.getPlatform().getOS() != Platform_OS.WINDOWS ? new PageManager_Unix() : new PageManager_Windows();
        INSTANCE = __stk1;
    }

  private PageManager_SingletonHolder() { // было: <init>
        super();
    }

}