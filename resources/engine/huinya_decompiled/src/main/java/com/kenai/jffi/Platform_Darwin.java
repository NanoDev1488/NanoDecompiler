// исходный (обфусцированный) внутренний класс: com.kenai.jffi.Platform.Darwin
package com.kenai.jffi;

import com.kenai.jffi.Platform;
import com.kenai.jffi.Platform_OS;

final class Platform_Darwin extends Platform {

  public Platform_Darwin() { // было: <init>
        super(Platform_OS.DARWIN, null);
    }

  public String mapLibraryName(String arg0) {
        if (!arg0.matches(getLibraryNamePattern())) {
            return new StringBuilder().append("lib").append(arg0).append(".dylib").toString();
        } else {
            return arg0;
        }
    }

  public String getLibraryNamePattern() {
        return "lib.*\\.(dylib|jnilib)$";
    }

  public String getName() {
        return "Darwin";
    }

  public final int longSize() {
        return getCPU().dataModel;
    }

}