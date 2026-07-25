// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.TransientNativeMemory.Magazine
package jnr.ffi.provider.jffi;

import com.kenai.jffi.PageManager;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Map;
import jnr.ffi.provider.jffi.NativeFinalizer;
import jnr.ffi.provider.jffi.TransientNativeMemory;
import jnr.ffi.provider.jffi.TransientNativeMemory_Sentinel;
import jnr.ffi.util.ref.FinalizablePhantomReference;

final class TransientNativeMemory_Magazine extends FinalizablePhantomReference {

    // ---- поля ----
  private final Reference sentinelReference;
  private final PageManager pm;
  private final long page;
  private final long end;
  private final int pageCount;
  private long memory;

   TransientNativeMemory_Magazine(TransientNativeMemory_Sentinel arg0, PageManager arg1, long arg2, int arg3) { // было: <init>
        super(arg0, NativeFinalizer.getInstance().getFinalizerQueue());
        sentinelReference = new WeakReference(arg0);
        pm = arg1;
        page = arg2;
        memory = arg2;
        pageCount = arg3;
        end = memory + ((long) arg3) * arg1.pageSize();
    }

   TransientNativeMemory_Sentinel sentinel() {
        return ((TransientNativeMemory_Sentinel) sentinelReference.get());
    }

   long allocate(long arg0, int arg1) {
        long var4 = TransientNativeMemory.access$100(memory, ((long) arg1));
        if (var4 + arg0 > end) {
            return 0L;
        } else {
            memory = var4 + arg0;
            return var4;
        }
    }

  public final void finalizeReferent() {
        pm.freePages(page, pageCount);
        TransientNativeMemory.access$200().remove(this);
    }

}