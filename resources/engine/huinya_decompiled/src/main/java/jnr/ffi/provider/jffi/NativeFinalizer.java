// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.NativeFinalizer
package jnr.ffi.provider.jffi;

import jnr.ffi.provider.jffi.NativeFinalizer_SingletonHolder;
import jnr.ffi.util.ref.FinalizableReferenceQueue;

class NativeFinalizer {

    // ---- поля ----
  private final FinalizableReferenceQueue finalizerQueue;

   NativeFinalizer() { // было: <init>
        super();
        finalizerQueue = new FinalizableReferenceQueue();
    }

  public static NativeFinalizer getInstance() {
        return NativeFinalizer_SingletonHolder.access$000();
    }

  public FinalizableReferenceQueue getFinalizerQueue() {
        return finalizerQueue;
    }

}