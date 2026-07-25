// исходный (обфусцированный) внутренний класс: com.kenai.jffi.MemoryIO.SingletonHolder
package com.kenai.jffi;

import com.kenai.jffi.MemoryIO;

final class MemoryIO_SingletonHolder {

    // ---- поля ----
  private static final MemoryIO INSTANCE;

    static {
        INSTANCE = MemoryIO.access$000();
    }

  private MemoryIO_SingletonHolder() { // было: <init>
        super();
    }

  static MemoryIO access$200() {
        return INSTANCE;
    }

}