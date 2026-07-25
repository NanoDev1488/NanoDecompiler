// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.NativeLibraryLoader
package jnr.ffi.provider.jffi;

import java.util.Collection;
import java.util.Map;
import jnr.ffi.LibraryLoader;
import jnr.ffi.provider.jffi.AsmLibraryLoader;
import jnr.ffi.provider.jffi.NativeLibrary;
import jnr.ffi.provider.jffi.ReflectionLibraryLoader;
import jnr.ffi.provider.jffi.Util;

class NativeLibraryLoader extends LibraryLoader {

    // ---- поля ----
  static final boolean ASM_ENABLED;

    static {
        ASM_ENABLED = Util.getBooleanProperty("jnr.ffi.asm.enabled", true);
    }

   NativeLibraryLoader(Class arg0) { // было: <init>
        super(arg0);
    }

  public Object loadLibrary(Class arg0, Collection arg1, Collection arg2, Map arg3, boolean arg4) {
        Object __stk1;
        NativeLibrary var6 = new NativeLibrary(arg1, arg2, arg3);
        try {
            __stk1 = !ASM_ENABLED ? new ReflectionLibraryLoader().loadLibrary(var6, arg0, arg3, arg4) : new AsmLibraryLoader().loadLibrary(var6, arg0, arg3, arg4);
        } catch (RuntimeException var7) {
            throw var7;
        } catch (Exception e2) {
            Throwable var7 = e2;
            throw new RuntimeException(var7);
        }
    }

}