// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.NativeInvocationHandler
package jnr.ffi.provider;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import jnr.ffi.provider.Invoker;

public class NativeInvocationHandler implements InvocationHandler {

    // ---- поля ----
  private volatile Map fastLookupTable;
  private final Map invokerMap;

  public NativeInvocationHandler(Map arg0) { // было: <init>
        super();
        invokerMap = arg0;
        fastLookupTable = Collections.emptyMap();
    }

  public Object invoke(Object arg0, Method arg1, Object[] arg2) {
        Invoker var4 = ((Invoker) fastLookupTable.get(arg1));
        return var4 == null ? lookupAndCacheInvoker(arg1).invoke(arg0, arg2) : var4.invoke(arg0, arg2);
    }

  private synchronized Invoker lookupAndCacheInvoker(Method arg0) {
        Invoker var2 = ((Invoker) fastLookupTable.get(arg0));
        if (var2 == null) {
            IdentityHashMap var3 = new IdentityHashMap(fastLookupTable);
            var2 = ((Invoker) invokerMap.get(arg0));
            var3.put(arg0, var2);
            if (var2 != null) {
                fastLookupTable = var3;
                return var2;
            } else {
                throw new UnsatisfiedLinkError(new StringBuilder().append("no invoker for native method ").append(arg0.getName()).toString());
            }
        } else {
            return var2;
        }
    }

}