// исходный (обфусцированный) внутренний класс: com.kenai.jffi.Platform.Default
package com.kenai.jffi;

import com.kenai.jffi.Platform;
import com.kenai.jffi.Platform_OS;

final class Platform_Default extends Platform {

  public Platform_Default(Platform_OS arg0) { // было: <init>
        super(arg0, null);
    }

  public final int longSize() {
        return getCPU().dataModel;
    }

}