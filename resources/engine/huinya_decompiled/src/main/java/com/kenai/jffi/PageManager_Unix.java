// исходный (обфусцированный) внутренний класс: com.kenai.jffi.PageManager.Unix
package com.kenai.jffi;

import com.kenai.jffi.Foreign;
import com.kenai.jffi.PageManager;

final class PageManager_Unix extends PageManager {

   PageManager_Unix() { // было: <init>
        super();
    }

  public long allocatePages(int arg0, int arg1) {
        long var3 = ((long) arg0) * pageSize();
        long var5 = Foreign.mmap(0L, var3, arg1, 258, -1, 0L);
        return var5 == -1L ? 0L : var5;
    }

  public void freePages(long arg0, int arg1) {
        Foreign.munmap(arg0, ((long) arg1) * pageSize());
    }

  public void protectPages(long arg0, int arg1, int arg2) {
        Foreign.mprotect(arg0, ((long) arg1) * pageSize(), arg2);
    }

}