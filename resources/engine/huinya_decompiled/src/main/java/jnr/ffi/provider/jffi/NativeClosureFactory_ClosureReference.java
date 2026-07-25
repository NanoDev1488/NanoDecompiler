// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.NativeClosureFactory.ClosureReference
package jnr.ffi.provider.jffi;

import jnr.ffi.Pointer;
import jnr.ffi.provider.jffi.NativeClosureFactory;
import jnr.ffi.provider.jffi.NativeClosureFactory_Anon1;
import jnr.ffi.provider.jffi.NativeClosurePointer;
import jnr.ffi.provider.jffi.NativeFinalizer;
import jnr.ffi.util.ref.FinalizableWeakReference;

final class NativeClosureFactory_ClosureReference extends FinalizableWeakReference {

    // ---- поля ----
  volatile NativeClosureFactory_ClosureReference next;
  private final NativeClosureFactory factory;
  private final NativeClosurePointer pointer;
  private final Integer key;
  final NativeClosureFactory this$0;

  private NativeClosureFactory_ClosureReference(NativeClosureFactory arg0, Object arg1, Integer arg2, NativeClosureFactory arg3, NativeClosurePointer arg4) { // было: <init>
        super(arg1, NativeFinalizer.getInstance().getFinalizerQueue());
        this$0 = arg0;
        factory = arg3;
        key = arg2;
        pointer = arg4;
    }

  public void finalizeReferent() {
        clear();
        NativeClosureFactory.access$000(factory, this, key);
        NativeClosureFactory.access$100(factory, pointer);
    }

   Object getCallable() {
        return get();
    }

   Pointer getPointer() {
        return pointer;
    }

  static NativeClosurePointer access$200(NativeClosureFactory_ClosureReference arg0) {
        return arg0.pointer;
    }

   NativeClosureFactory_ClosureReference(NativeClosureFactory arg0, Object arg1, Integer arg2, NativeClosureFactory arg3, NativeClosurePointer arg4, NativeClosureFactory_Anon1 arg5) { // было: <init>
        this(arg0, arg1, arg2, arg3, arg4);
    }

}