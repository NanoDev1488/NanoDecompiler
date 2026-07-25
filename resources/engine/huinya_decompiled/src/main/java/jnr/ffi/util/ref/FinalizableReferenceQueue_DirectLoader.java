// исходный (обфусцированный) внутренний класс: jnr.ffi.util.ref.FinalizableReferenceQueue.DirectLoader
package jnr.ffi.util.ref;

import jnr.ffi.util.ref.FinalizableReferenceQueue_FinalizerLoader;

class FinalizableReferenceQueue_DirectLoader implements FinalizableReferenceQueue_FinalizerLoader {

   FinalizableReferenceQueue_DirectLoader() { // было: <init>
        super();
    }

  public Class loadFinalizer() {
        Class __stk1;
        try {
            __stk1 = Class.forName("jnr.ffi.util.ref.internal.Finalizer");
        } catch (ClassNotFoundException var1) {
            throw new AssertionError(var1);
        }
    }

}