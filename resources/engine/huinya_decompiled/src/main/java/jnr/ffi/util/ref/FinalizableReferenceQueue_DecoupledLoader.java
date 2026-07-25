// исходный (обфусцированный) внутренний класс: jnr.ffi.util.ref.FinalizableReferenceQueue.DecoupledLoader
package jnr.ffi.util.ref;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;
import jnr.ffi.util.ref.FinalizableReferenceQueue;
import jnr.ffi.util.ref.FinalizableReferenceQueue_FinalizerLoader;

class FinalizableReferenceQueue_DecoupledLoader implements FinalizableReferenceQueue_FinalizerLoader {

    // ---- поля ----
  private static final String LOADING_ERROR = "Could not load Finalizer in its own class loader.Loading Finalizer in the current class loader instead. As a result, you will not be ableto garbage collect this class loader. To support reclaiming this class loader, eitherresolve the underlying issue, or move Google Collections to your system class path.";

   FinalizableReferenceQueue_DecoupledLoader() { // было: <init>
        super();
    }

  public Class loadFinalizer() {
        Class __stk1;
        try {
            URLClassLoader var1 = newLoader(getBaseUrl());
            __stk1 = var1.loadClass("jnr.ffi.util.ref.internal.Finalizer");
        } catch (Exception e1) {
            Throwable var1 = e1;
            FinalizableReferenceQueue.access$000().log(Level.WARNING, "Could not load Finalizer in its own class loader.Loading Finalizer in the current class loader instead. As a result, you will not be ableto garbage collect this class loader. To support reclaiming this class loader, eitherresolve the underlying issue, or move Google Collections to your system class path.", var1);
            return null;
        }
    }

   URL getBaseUrl() {
        String var1 = new StringBuilder().append("jnr.ffi.util.ref.internal.Finalizer".replace('.', '/')).append(".class").toString();
        URL var2 = getClass().getClassLoader().getResource(var1);
        if (var2 != null) {
            String var3 = var2.toString();
            if (var3.endsWith(var1)) {
                var3 = var3.substring(0, var3.length() - var1.length());
                return new URL(var2, var3);
            } else {
                throw new IOException(new StringBuilder().append("Unsupported path style: ").append(var3).toString());
            }
        } else {
            throw new FileNotFoundException(var1);
        }
    }

   URLClassLoader newLoader(URL arg0) {
        return new URLClassLoader(new URL[]{arg0});
    }

}