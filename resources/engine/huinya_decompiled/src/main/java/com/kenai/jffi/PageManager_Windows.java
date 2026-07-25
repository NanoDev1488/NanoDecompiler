// исходный (обфусцированный) внутренний класс: com.kenai.jffi.PageManager.Windows
package com.kenai.jffi;

import com.kenai.jffi.Foreign;
import com.kenai.jffi.PageManager;

final class PageManager_Windows extends PageManager {

  public PageManager_Windows() { // было: <init>
        super();
    }

  public long allocatePages(int arg0, int arg1) {
        return Foreign.VirtualAlloc(0L, ((int) pageSize()) * arg0, 12288, w32prot(arg1));
    }

  public void freePages(long arg0, int arg1) {
        Foreign.VirtualFree(arg0, 0, 32768);
    }

  public void protectPages(long arg0, int arg1, int arg2) {
        Foreign.VirtualProtect(arg0, ((int) pageSize()) * arg1, w32prot(arg2));
    }

  private static int w32prot(int arg0) {
        int var1 = 1;
        if ((arg0 & 3) != 3) {
            if ((arg0 & 1) == 1) {
                var1 = 2;
            }
        } else {
            var1 = 4;
        }
        if ((arg0 & 4) == 4) {
            var1 = var1 << 4;
        }
        return var1;
    }

}