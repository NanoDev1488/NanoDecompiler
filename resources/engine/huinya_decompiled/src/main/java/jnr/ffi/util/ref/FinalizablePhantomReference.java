// исходный (обфусцированный) внутренний класс: jnr.ffi.util.ref.FinalizablePhantomReference
package jnr.ffi.util.ref;

import java.lang.ref.PhantomReference;
import jnr.ffi.util.ref.FinalizableReference;
import jnr.ffi.util.ref.FinalizableReferenceQueue;

public abstract class FinalizablePhantomReference extends PhantomReference implements FinalizableReference {

  protected FinalizablePhantomReference(Object arg0, FinalizableReferenceQueue arg1) { // было: <init>
        super(arg0, arg1.queue);
        arg1.cleanUp();
    }

}