// исходный (обфусцированный) внутренний класс: com.kenai.jffi.PageManager
package com.kenai.jffi;

import com.kenai.jffi.Foreign;
import com.kenai.jffi.PageManager_SingletonHolder;

public abstract class PageManager {

    // ---- поля ----
  public static final int PROT_EXEC = 4;
  public static final int PROT_READ = 1;
  public static final int PROT_WRITE = 2;
  private final Foreign foreign;
  private int pageSize;

  public PageManager() { // было: <init>
        super();
        foreign = Foreign.getInstance();
    }

  public static PageManager getInstance() {
        return PageManager_SingletonHolder.INSTANCE;
    }

  public final long pageSize() {
        return pageSize == 0 ? calculatePageSize() : ((long) pageSize);
    }

  private long calculatePageSize() {
        long __stk1;
        long var1 = Foreign.pageSize();
        if (var1 >= 2147483647L) {
            __stk1 = var1;
        } else {
            pageSize = ((int) var1);
            __stk1 = ((long) ((int) var1));
        }
        return __stk1;
    }

  public abstract long allocatePages(int arg0, int arg1);

  public abstract void freePages(long arg0, int arg1);

  public abstract void protectPages(long arg0, int arg1, int arg2);

}