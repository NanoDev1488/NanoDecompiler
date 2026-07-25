// исходный (обфусцированный) внутренний класс: jnr.ffi.util.AnnotationProxy$1
package jnr.ffi.util;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.security.PrivilegedAction;

class AnnotationProxy_Anon1 implements PrivilegedAction {

    // ---- поля ----
  final Class val$annotationType;

   AnnotationProxy_Anon1(Class arg0) { // было: <init>
        super();
        val$annotationType = arg0;
    }

  public Method[] run() {
        Method[] var1 = val$annotationType.getDeclaredMethods();
        AccessibleObject.setAccessible(var1, true);
        return var1;
    }

  public Object run() {
        return run();
    }

}