// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.AbstractX86StubCompiler.PageHolder
package jnr.ffi.provider.jffi;

import com.kenai.jffi.PageManager;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.logging.Level;
import java.util.logging.Logger;
import jnr.ffi.provider.jffi.AbstractX86StubCompiler;

final class AbstractX86StubCompiler_PageHolder {

    // ---- поля ----
  final PageManager pm;
  final long memory;
  final long pageCount;
  volatile int disposed;

  public AbstractX86StubCompiler_PageHolder(PageManager arg0, long arg1, long arg2) { // было: <init>
        super();
        pm = arg0;
        memory = arg1;
        pageCount = arg2;
    }

  protected void finalize() {
        try {
            int var1 = AbstractX86StubCompiler.PAGE_HOLDER_UPDATER.getAndSet(this, 1);
            if (var1 == 0) {
                pm.freePages(memory, ((int) pageCount));
            }
        } catch (Throwable e2) {
            try {
                Throwable var1 = e2;
                Logger.getLogger(getClass().getName()).log(Level.WARNING, "Exception when freeing native pages: %s", var1.getLocalizedMessage());
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