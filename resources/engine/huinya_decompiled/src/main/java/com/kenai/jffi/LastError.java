// исходный (обфусцированный) внутренний класс: com.kenai.jffi.LastError
package com.kenai.jffi;

import com.kenai.jffi.Foreign;
import com.kenai.jffi.LastError_Anon1;
import com.kenai.jffi.LastError_SingletonHolder;

public final class LastError {

    // ---- поля ----
  private final Foreign foreign;

  private LastError() { // было: <init>
        super();
        foreign = Foreign.getInstance();
    }

  public static final LastError getInstance() {
        return LastError_SingletonHolder.INSTANCE;
    }

    @Deprecated
  public final int getError() {
        return Foreign.getLastError();
    }

  public final int get() {
        return Foreign.getLastError();
    }

  public final void set(int arg0) {
        Foreign.setLastError(arg0);
    }

   LastError(LastError_Anon1 arg0) { // было: <init>
        this();
    }

}