// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.AllocatedDirectMemoryIO
package jnr.ffi.provider.jffi;

import com.kenai.jffi.MemoryIO;
import java.util.concurrent.atomic.AtomicBoolean;
import jnr.ffi.Runtime;
import jnr.ffi.provider.jffi.DirectMemoryIO;

class AllocatedDirectMemoryIO extends DirectMemoryIO {

    // ---- поля ----
  private final AtomicBoolean allocated;
  private final long size;

  public AllocatedDirectMemoryIO(Runtime arg0, long arg1, boolean arg2) { // было: <init>
        super(arg0, IO.allocateMemory(arg1, arg2));
        allocated = new AtomicBoolean(true);
        size = arg1;
        if (address() != 0L) {
            return;
        } else {
            throw new OutOfMemoryError(new StringBuilder().append("Failed to allocate ").append(arg1).append(" bytes").toString());
        }
    }

  public long size() {
        return size;
    }

  public int hashCode() {
        return super.hashCode();
    }

  public boolean equals(Object arg0) {
        if (!(arg0 instanceof AllocatedDirectMemoryIO)) {
            return super.equals(arg0);
        } else {
            AllocatedDirectMemoryIO var2 = ((AllocatedDirectMemoryIO) arg0);
            return var2.size != size ? 0 : var2.address() == address();
        }
    }

  public final void dispose() {
        if (allocated.getAndSet(false)) {
            IO.freeMemory(address());
        }
    }

  protected void finalize() {
        try {
            if (allocated.getAndSet(false)) {
                IO.freeMemory(address());
            }
        } catch (Throwable var1) {
            super.finalize();
            throw var1;
        }
    }

}