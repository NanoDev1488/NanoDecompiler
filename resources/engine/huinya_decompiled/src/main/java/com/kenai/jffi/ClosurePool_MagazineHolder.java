// исходный (обфусцированный) внутренний класс: com.kenai.jffi.ClosurePool.MagazineHolder
package com.kenai.jffi;

import com.kenai.jffi.ClosurePool;
import com.kenai.jffi.ClosurePool_Magazine;

final class ClosurePool_MagazineHolder {

    // ---- поля ----
  final ClosurePool pool;
  final ClosurePool_Magazine magazine;

  public ClosurePool_MagazineHolder(ClosurePool arg0, ClosurePool_Magazine arg1) { // было: <init>
        super();
        pool = arg0;
        magazine = arg1;
    }

  protected void finalize() {
        try {
            pool.recycle(magazine);
        } catch (Throwable var1) {
            super.finalize();
            throw var1;
        }
    }

}