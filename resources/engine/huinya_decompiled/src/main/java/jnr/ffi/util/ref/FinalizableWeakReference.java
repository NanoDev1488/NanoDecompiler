// исходный (обфусцированный) внутренний класс: jnr.ffi.util.ref.FinalizableWeakReference
package jnr.ffi.util.ref;

import java.lang.ref.WeakReference;
import jnr.ffi.util.ref.FinalizableReference;
import jnr.ffi.util.ref.FinalizableReferenceQueue;

public abstract class FinalizableWeakReference extends WeakReference implements FinalizableReference {

  protected FinalizableWeakReference(Object arg0, FinalizableReferenceQueue arg1) { // было: <init>
        super(arg0, arg1.queue);
        arg1.cleanUp();
    }

}