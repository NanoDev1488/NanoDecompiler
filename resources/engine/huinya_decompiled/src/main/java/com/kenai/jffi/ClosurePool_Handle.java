// исходный (обфусцированный) внутренний класс: com.kenai.jffi.ClosurePool.Handle
package com.kenai.jffi;

import com.kenai.jffi.ClosurePool;
import com.kenai.jffi.ClosurePool_MagazineHolder;
import com.kenai.jffi.ClosurePool_Magazine_Slot;
import com.kenai.jffi.Closure_Handle;

final class ClosurePool_Handle implements Closure_Handle {

    // ---- поля ----
  final ClosurePool_MagazineHolder holder;
  final ClosurePool_Magazine_Slot slot;
  private volatile boolean disposed;

   ClosurePool_Handle(ClosurePool_Magazine_Slot arg0, ClosurePool_MagazineHolder arg1) { // было: <init>
        super();
        slot = arg0;
        holder = arg1;
    }

  public long getAddress() {
        if (!disposed) {
            return slot.codeAddress;
        } else {
            throw new RuntimeException("trying to access disposed closure handle");
        }
    }

  public void setAutoRelease(boolean arg0) {
        if (!disposed) {
            slot.autorelease = arg0;
        }
    }

    @Deprecated
  public void free() {
        dispose();
    }

  public synchronized void dispose() {
        if (!disposed) {
            disposed = true;
            slot.autorelease = true;
            slot.proxy.closure = ClosurePool.access$000();
            holder.pool.recycle(slot, holder);
        }
    }

}