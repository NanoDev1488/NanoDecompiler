// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.NativeClosurePointer
package jnr.ffi.provider.jffi;

import com.kenai.jffi.Closure_Handle;
import jnr.ffi.Runtime;
import jnr.ffi.provider.InAccessibleMemoryIO;
import jnr.ffi.provider.jffi.NativeClosureProxy;

class NativeClosurePointer extends InAccessibleMemoryIO {

    // ---- поля ----
  private final Closure_Handle handle;
  final NativeClosureProxy proxy;

  public NativeClosurePointer(Runtime arg0, Closure_Handle arg1, NativeClosureProxy arg2) { // было: <init>
        super(arg0, arg1.getAddress(), true);
        handle = arg1;
        proxy = arg2;
    }

  public long size() {
        return 0L;
    }

}