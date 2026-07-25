// исходный (обфусцированный) внутренний класс: jnr.ffi.util.ref.FinalizableReferenceQueue.SystemLoader
package jnr.ffi.util.ref;

import java.util.logging.Logger;
import jnr.ffi.util.ref.FinalizableReferenceQueue;
import jnr.ffi.util.ref.FinalizableReferenceQueue_FinalizerLoader;

class FinalizableReferenceQueue_SystemLoader implements FinalizableReferenceQueue_FinalizerLoader {

   FinalizableReferenceQueue_SystemLoader() { // было: <init>
        super();
    }

  public Class loadFinalizer() {
        try {
            ClassLoader var1 = ClassLoader.getSystemClassLoader();
        } catch (SecurityException var2) {
            FinalizableReferenceQueue.access$000().info("Not allowed to access system class loader.");
            return null;
        }
    }

}