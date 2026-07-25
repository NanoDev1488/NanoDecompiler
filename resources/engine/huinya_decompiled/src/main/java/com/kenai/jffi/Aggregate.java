// исходный (обфусцированный) внутренний класс: com.kenai.jffi.Aggregate
package com.kenai.jffi;

import com.kenai.jffi.Foreign;
import com.kenai.jffi.Type;
import com.kenai.jffi.Type_TypeInfo;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Aggregate extends Type {

    // ---- поля ----
  private final Type_TypeInfo typeInfo;
  private final long handle;
  private volatile int disposed;
  private static final AtomicIntegerFieldUpdater UPDATER;
  private final Foreign foreign;

    static {
        UPDATER = AtomicIntegerFieldUpdater.newUpdater(Aggregate.class, "disposed");
    }

   Aggregate(Foreign arg0, long arg1) { // было: <init>
        super();
        if (arg1 != 0L) {
            foreign = arg0;
            handle = arg1;
            typeInfo = new Type_TypeInfo(arg1, arg0.getTypeType(arg1), arg0.getTypeSize(arg1), arg0.getTypeAlign(arg1));
            return;
        } else {
            throw new NullPointerException("Invalid ffi_type handle");
        }
    }

  final Type_TypeInfo getTypeInfo() {
        return typeInfo;
    }

  public final synchronized void dispose() {
        // (пустое тело)
    }

  protected void finalize() {
        try {
            int var1 = UPDATER.getAndSet(this, 1);
            if (var1 == 0) {
                foreign.freeAggregate(typeInfo.handle);
            }
        } catch (Throwable e2) {
            try {
                Throwable var1 = e2;
                Logger.getLogger(getClass().getName()).log(Level.WARNING, "Exception when freeing FFI aggregate: %s", var1.getLocalizedMessage());
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