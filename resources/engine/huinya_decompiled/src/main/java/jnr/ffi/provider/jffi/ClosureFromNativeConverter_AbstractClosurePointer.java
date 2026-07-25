// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.ClosureFromNativeConverter.AbstractClosurePointer
package jnr.ffi.provider.jffi;

import com.kenai.jffi.Invoker;
import jnr.ffi.Runtime;
import jnr.ffi.provider.InAccessibleMemoryIO;

public abstract class ClosureFromNativeConverter_AbstractClosurePointer extends InAccessibleMemoryIO {

    // ---- поля ----
  public static final Invoker ffi;
  protected final long functionAddress;

    static {
        ffi = Invoker.getInstance();
    }

  protected ClosureFromNativeConverter_AbstractClosurePointer(Runtime arg0, long arg1) { // было: <init>
        super(arg0, arg1, true);
        functionAddress = arg1;
    }

  public final long size() {
        return 0L;
    }

}