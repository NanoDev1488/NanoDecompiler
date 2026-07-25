// исходный (обфусцированный) внутренний класс: com.kenai.jffi.Foreign.ValidInstanceHolder
package com.kenai.jffi;

import com.kenai.jffi.Foreign;
import com.kenai.jffi.Foreign_InstanceHolder;

final class Foreign_ValidInstanceHolder extends Foreign_InstanceHolder {

    // ---- поля ----
  final Foreign foreign;

  public Foreign_ValidInstanceHolder(Foreign arg0) { // было: <init>
        super(null);
        foreign = arg0;
    }

  final Foreign getForeign() {
        return foreign;
    }

}