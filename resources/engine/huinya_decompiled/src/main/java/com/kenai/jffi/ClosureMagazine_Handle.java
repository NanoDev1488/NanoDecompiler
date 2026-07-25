// исходный (обфусцированный) внутренний класс: com.kenai.jffi.ClosureMagazine.Handle
package com.kenai.jffi;

import com.kenai.jffi.ClosureMagazine;
import com.kenai.jffi.ClosureMagazine_Anon1;
import com.kenai.jffi.Closure_Handle;

final class ClosureMagazine_Handle implements Closure_Handle {

    // ---- поля ----
  private final ClosureMagazine magazine;
  private final long closureAddress;
  private final long codeAddress;

  private ClosureMagazine_Handle(ClosureMagazine arg0, long arg1, long arg2) { // было: <init>
        super();
        magazine = arg0;
        closureAddress = arg1;
        codeAddress = arg2;
    }

  public long getAddress() {
        return codeAddress;
    }

  public void setAutoRelease(boolean arg0) {
        // (пустое тело)
    }

  public void dispose() {
        // (пустое тело)
    }

  public void free() {
        // (пустое тело)
    }

   ClosureMagazine_Handle(ClosureMagazine arg0, long arg1, long arg2, ClosureMagazine_Anon1 arg3) { // было: <init>
        this(arg0, arg1, arg2);
    }

}