// исходный (обфусцированный) внутренний класс: jnr.posix.util.FieldAccess
package jnr.posix.util;

import java.lang.reflect.Field;

public class FieldAccess {

  public FieldAccess() { // было: <init>
        super();
    }

  public static Field getProtectedField(Class arg0, String arg1) {
        Object var2 = null;
        try {
            var2 = arg0.getDeclaredField(arg1);
            var2.setAccessible(true);
        } catch (Exception var3) {
        }
        return ((Field) var2);
    }

  public static Object getProtectedFieldValue(Class arg0, String arg1, Object arg2) {
        Object __stk1;
        try {
            Field var3 = getProtectedField(arg0, arg1);
            __stk1 = var3.get(arg2);
        } catch (Exception e1) {
            Throwable var3 = e1;
            throw new IllegalArgumentException(var3);
        }
    }

}