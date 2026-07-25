// исходный (обфусцированный) внутренний класс: com.kenai.jffi.ClosurePool
package com.kenai.jffi;

import com.kenai.jffi.CallContext;
import com.kenai.jffi.Closure;
import com.kenai.jffi.ClosurePool_Anon1;
import com.kenai.jffi.ClosurePool_Handle;
import com.kenai.jffi.ClosurePool_Magazine;
import com.kenai.jffi.ClosurePool_MagazineHolder;
import com.kenai.jffi.ClosurePool_Magazine_Slot;
import com.kenai.jffi.Closure_Handle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

public final class ClosurePool {

    // ---- поля ----
  private final Set magazines;
  private final ConcurrentLinkedQueue freeQueue;
  private final ConcurrentLinkedQueue partialQueue;
  private final CallContext callContext;
  private static final Closure NULL_CLOSURE;

    static {
        NULL_CLOSURE = new ClosurePool_Anon1();
    }

   ClosurePool(CallContext arg0) { // было: <init>
        super();
        magazines = Collections.synchronizedSet(new HashSet());
        freeQueue = new ConcurrentLinkedQueue();
        partialQueue = new ConcurrentLinkedQueue();
        callContext = arg0;
    }

  synchronized void recycle(ClosurePool_Magazine arg0) {
        arg0.recycle();
        if (arg0.isEmpty()) {
            magazines.remove(arg0);
        } else {
            useMagazine(arg0);
        }
    }

   void recycle(ClosurePool_Magazine_Slot arg0, ClosurePool_MagazineHolder arg1) {
        partialQueue.add(new ClosurePool_Handle(arg0, arg1));
    }

  private void useMagazine(ClosurePool_Magazine arg0) {
        ConcurrentLinkedQueue __stk1;
        ClosurePool_MagazineHolder var2 = new ClosurePool_MagazineHolder(this, arg0);
        ArrayList var3 = new ArrayList();
        __stk1 = !arg0.isFull() ? partialQueue : freeQueue;
        ConcurrentLinkedQueue var5 = __stk1;
        while (true) {
            ClosurePool_Magazine_Slot var4 = arg0.get();
            if (var4 == null) {
                break;
            }
            var3.add(new ClosurePool_Handle(var4, var2));
            continue;
        }
        var5.addAll(var3);
    }

  public Closure_Handle newClosureHandle(Closure arg0) {
        ClosurePool_Handle var2 = ((ClosurePool_Handle) partialQueue.poll());
        if (var2 == null) {
            var2 = ((ClosurePool_Handle) freeQueue.poll());
        }
        if (var2 == null) {
            var2 = allocateNewHandle();
        }
        var2.slot.proxy.closure = arg0;
        return var2;
    }

  private ClosurePool_Handle allocateNewHandle() {
        ClosurePool_Handle var1;
        while (true) {
            var1 = ((ClosurePool_Handle) partialQueue.poll());
            if (var1 != null) {
                break;
            }
            var1 = ((ClosurePool_Handle) freeQueue.poll());
            if (var1 != null) {
                break;
            }
            ClosurePool_Magazine var2 = new ClosurePool_Magazine(callContext);
            useMagazine(var2);
            magazines.add(var2);
            continue;
        }
        return var1;
    }

  static Closure access$000() {
        return NULL_CLOSURE;
    }

}