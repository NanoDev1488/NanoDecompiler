// исходный (обфусцированный) внутренний класс: com.kenai.jffi.Foreign.InValidInstanceHolder
package com.kenai.jffi;

import com.kenai.jffi.Foreign;
import com.kenai.jffi.Foreign_InstanceHolder;

final class Foreign_InValidInstanceHolder extends Foreign_InstanceHolder {

    // ---- поля ----
  private final Throwable cause;

  public Foreign_InValidInstanceHolder(Throwable arg0) { // было: <init>
        super(null);
        cause = arg0;
    }

  final Foreign getForeign() {
        throw Foreign.access$300(cause);
    }

}