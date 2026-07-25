// исходный (обфусцированный) внутренний класс: jnr.ffi.util.ref.FinalizableSoftReference
package jnr.ffi.util.ref;

import java.lang.ref.SoftReference;
import jnr.ffi.util.ref.FinalizableReference;
import jnr.ffi.util.ref.FinalizableReferenceQueue;

public abstract class FinalizableSoftReference extends SoftReference implements FinalizableReference {

  protected FinalizableSoftReference(Object arg0, FinalizableReferenceQueue arg1) { // было: <init>
        super(arg0, arg1.queue);
        arg1.cleanUp();
    }

}