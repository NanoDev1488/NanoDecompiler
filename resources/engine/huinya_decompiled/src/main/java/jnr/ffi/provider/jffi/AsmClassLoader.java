// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.AsmClassLoader
package jnr.ffi.provider.jffi;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class AsmClassLoader extends ClassLoader {

    // ---- поля ----
  private final ConcurrentMap definedClasses;

  public AsmClassLoader() { // было: <init>
        super();
        definedClasses = new ConcurrentHashMap();
    }

  public AsmClassLoader(ClassLoader arg0) { // было: <init>
        super(arg0);
        definedClasses = new ConcurrentHashMap();
    }

  public Class defineClass(String arg0, byte[] arg1) {
        Class var3 = defineClass(arg0, arg1, 0, arg1.length);
        definedClasses.putIfAbsent(arg0, var3);
        resolveClass(var3);
        return var3;
    }

  protected Class findClass(String arg0) {
        Class var2 = ((Class) definedClasses.get(arg0));
        if (var2 == null) {
            return super.findClass(arg0);
        } else {
            return var2;
        }
    }

}