// исходный (обфусцированный) внутренний класс: com.kenai.jffi.MemoryIO.CheckedMemorySingletonHolder
package com.kenai.jffi;

import com.kenai.jffi.MemoryIO;

final class MemoryIO_CheckedMemorySingletonHolder {

    // ---- поля ----
  private static final MemoryIO INSTANCE;

    static {
        INSTANCE = MemoryIO.access$100();
    }

  private MemoryIO_CheckedMemorySingletonHolder() { // было: <init>
        super();
    }

  static MemoryIO access$300() {
        return INSTANCE;
    }

}