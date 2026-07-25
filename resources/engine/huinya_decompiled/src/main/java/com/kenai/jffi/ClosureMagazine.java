// исходный (обфусцированный) внутренний класс: com.kenai.jffi.ClosureMagazine
package com.kenai.jffi;

import com.kenai.jffi.CallContext;
import com.kenai.jffi.ClosureMagazine_Handle;
import com.kenai.jffi.Closure_Handle;
import com.kenai.jffi.Foreign;
import com.kenai.jffi.MemoryIO;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ClosureMagazine {

    // ---- поля ----
  private final Foreign foreign;
  private final CallContext callContext;
  private final long magazineAddress;
  private volatile int disposed;
  private static final AtomicIntegerFieldUpdater UPDATER;

    static {
        UPDATER = AtomicIntegerFieldUpdater.newUpdater(ClosureMagazine.class, "disposed");
    }

   ClosureMagazine(Foreign arg0, CallContext arg1, long arg2) { // было: <init>
        super();
        foreign = arg0;
        callContext = arg1;
        magazineAddress = arg2;
    }

  public Closure_Handle allocate(Object arg0) {
        long var2 = foreign.closureMagazineGet(magazineAddress, arg0);
        return var2 == 0L ? null : new ClosureMagazine_Handle(this, var2, MemoryIO.getInstance().getAddress(var2), null);
    }

  public void dispose() {
        int var1 = UPDATER.getAndSet(this, 1);
        if (magazineAddress != 0L) {
            if (var1 == 0) {
                foreign.freeClosureMagazine(magazineAddress);
            }
        }
    }

  protected void finalize() {
        try {
            int var1 = UPDATER.getAndSet(this, 1);
            if (magazineAddress == 0L) {
            }
            if (var1 == 0) {
                foreign.freeClosureMagazine(magazineAddress);
            }
        } catch (Throwable e2) {
            try {
                Throwable var1 = e2;
                Logger.getLogger(getClass().getName()).log(Level.WARNING, new StringBuilder().append("exception when freeing ").append(getClass()).append(": %s").toString(), var1.getLocalizedMessage());
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