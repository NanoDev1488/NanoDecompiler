// исходный (обфусцированный) внутренний класс: com.kenai.jffi.Foreign.InstanceHolder
package com.kenai.jffi;

import com.kenai.jffi.Foreign;
import com.kenai.jffi.Foreign_Anon1;
import com.kenai.jffi.Foreign_InValidInstanceHolder;
import com.kenai.jffi.Foreign_ValidInstanceHolder;
import com.kenai.jffi.Init;

abstract class Foreign_InstanceHolder {

    // ---- поля ----
  static final Foreign_InstanceHolder INSTANCE;

    static {
        INSTANCE = getInstanceHolder();
    }

  private Foreign_InstanceHolder() { // было: <init>
        super();
    }

  private static Foreign_InstanceHolder getInstanceHolder() {
        Foreign_InValidInstanceHolder __stk2;
        Foreign_ValidInstanceHolder __stk3;
        try {
            Init.load();
            Foreign var0 = new Foreign(null);
            if ((var0.getVersion() & 16776960) != (Foreign.VERSION_MAJOR << 16 | Foreign.VERSION_MINOR << 8)) {
                String var1 = String.format("incorrect native library version %d.%d, expected %d.%d", new Object[]{Integer.valueOf(var0.getVersion() >> 16 & 255), Integer.valueOf(var0.getVersion() >> 8 & 255), Integer.valueOf(Foreign.VERSION_MAJOR), Integer.valueOf(Foreign.VERSION_MINOR)});
                __stk2 = new Foreign_InValidInstanceHolder(new UnsatisfiedLinkError(var1));
            }
            try {
                Foreign.access$100(var0);
                __stk3 = new Foreign_ValidInstanceHolder(var0);
            } catch (Throwable e1) {
                var0 = e1;
                return new Foreign_InValidInstanceHolder(var0);
            }
        } catch (Throwable e2) {
            Throwable var0 = e1;
            return new Foreign_InValidInstanceHolder(var0);
        }
    }

  abstract Foreign getForeign();

   Foreign_InstanceHolder(Foreign_Anon1 arg0) { // было: <init>
        this();
    }

}