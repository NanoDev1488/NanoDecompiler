// исходный (обфусцированный) внутренний класс: com.kenai.jffi.InvokeDynamicSupport.JSR292
package com.kenai.jffi;

import java.lang.reflect.Method;

final class InvokeDynamicSupport_JSR292 {

    // ---- поля ----
  static final InvokeDynamicSupport_JSR292 INSTANCE;
  private final Object lookup;
  private final Method unreflect;
  private final Class methodHandles;
  private final Method insertArguments;

    static {
        INSTANCE = getInstance();
    }

  static boolean isAvailable() {
        return INSTANCE != null;
    }

  private static InvokeDynamicSupport_JSR292 getInstance() {
        InvokeDynamicSupport_JSR292 __stk3;
        try {
            Class var0 = Class.forName("java.lang.invoke.MethodHandles$Lookup");
            Class var1 = Class.forName("java.lang.invoke.MethodHandles");
            Class var2 = Class.forName("java.lang.invoke.MethodHandle");
            Method var3 = var1.getDeclaredMethod("lookup", new Class[0]);
            Method var4 = var0.getDeclaredMethod("unreflect", new Class[]{Method.class});
            Class[] __obj2 = new Class[3];
            __obj2[0] = var2;
            __obj2[1] = Integer.TYPE;
            __obj2[2] = Object[].class;
            Method var5 = var1.getDeclaredMethod("insertArguments", __obj2);
            Object var6 = var3.invoke(var1, new Object[0]);
            __stk3 = new InvokeDynamicSupport_JSR292(var6, var4, var1, var5);
        } catch (Throwable e1) {
            Throwable var0 = e1;
            return null;
        }
    }

   InvokeDynamicSupport_JSR292(Object arg0, Method arg1, Class arg2, Method arg3) { // было: <init>
        super();
        lookup = arg0;
        unreflect = arg1;
        methodHandles = arg2;
        insertArguments = arg3;
    }

  public Object unreflect(Method arg0) {
        return unreflect.invoke(lookup, new Object[]{arg0});
    }

  public Object insertArguments(Object arg0, int arg1, Object[] arg2) {
        return insertArguments.invoke(methodHandles, new Object[]{arg0, Integer.valueOf(arg1), arg2});
    }

}