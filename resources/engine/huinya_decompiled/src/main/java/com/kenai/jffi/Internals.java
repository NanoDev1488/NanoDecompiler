// исходный (обфусцированный) внутренний класс: com.kenai.jffi.Internals
package com.kenai.jffi;

import com.kenai.jffi.Foreign;

public final class Internals {

  private Internals() { // было: <init>
        super();
    }

  public static final long getErrnoSaveFunction() {
        return Foreign.getInstance().getSaveErrnoFunction();
    }

}