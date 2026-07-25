// исходный (обфусцированный) внутренний класс: com.kenai.jffi.Platform.Windows
package com.kenai.jffi;

import com.kenai.jffi.Platform;
import com.kenai.jffi.Platform_OS;

final class Platform_Windows extends Platform {

  public Platform_Windows() { // было: <init>
        super(Platform_OS.WINDOWS, null);
    }

  public String getLibraryNamePattern() {
        return ".*\\.dll$";
    }

  public final int longSize() {
        return 32;
    }

}