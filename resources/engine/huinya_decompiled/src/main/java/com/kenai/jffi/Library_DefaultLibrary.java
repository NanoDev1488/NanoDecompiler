// исходный (обфусцированный) внутренний класс: com.kenai.jffi.Library.DefaultLibrary
package com.kenai.jffi;

import com.kenai.jffi.Library;

final class Library_DefaultLibrary {

    // ---- поля ----
  private static final Library INSTANCE;

    static {
        INSTANCE = Library.openLibrary(null, 9);
    }

  private Library_DefaultLibrary() { // было: <init>
        super();
    }

  static Library access$000() {
        return INSTANCE;
    }

}