// исходный (обфусцированный) внутренний класс: com.kenai.jffi.NativeMethods.ResourceHolder
package com.kenai.jffi;

import com.kenai.jffi.MemoryIO;
import java.util.logging.Level;
import java.util.logging.Logger;

final class NativeMethods_ResourceHolder {

    // ---- поля ----
  private final MemoryIO mm;
  private final long memory;

  public NativeMethods_ResourceHolder(MemoryIO arg0, long arg1) { // было: <init>
        super();
        mm = arg0;
        memory = arg1;
    }

  protected void finalize() {
        try {
            mm.freeMemory(memory);
        } catch (Throwable e2) {
            try {
                Throwable var1 = e2;
                Logger.getLogger(getClass().getName()).log(Level.WARNING, "Exception when freeing native method struct array: %s", var1.getLocalizedMessage());
            } catch (Throwable var2) {
                super.finalize();
                throw var2;
            }
        } catch (Throwable var2) {
            super.finalize();
            throw var2;
        }
    }

}