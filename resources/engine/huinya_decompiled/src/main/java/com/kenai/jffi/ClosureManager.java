// исходный (обфусцированный) внутренний класс: com.kenai.jffi.ClosureManager
package com.kenai.jffi;

import com.kenai.jffi.CallContext;
import com.kenai.jffi.CallContextCache;
import com.kenai.jffi.CallingConvention;
import com.kenai.jffi.Closure;
import com.kenai.jffi.ClosureMagazine;
import com.kenai.jffi.ClosureManager_Anon1;
import com.kenai.jffi.ClosureManager_SingletonHolder;
import com.kenai.jffi.ClosurePool;
import com.kenai.jffi.Closure_Buffer;
import com.kenai.jffi.Closure_Handle;
import com.kenai.jffi.Foreign;
import com.kenai.jffi.Type;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public final class ClosureManager {

    // ---- поля ----
  private final Map poolMap;

  public static ClosureManager getInstance() {
        return ClosureManager_SingletonHolder.INSTANCE;
    }

  private ClosureManager() { // было: <init>
        super();
        poolMap = new WeakHashMap();
    }

  public final Closure_Handle newClosure(Closure arg0, Type arg1, Type[] arg2, CallingConvention arg3) {
        return newClosure(arg0, CallContextCache.getInstance().getCallContext(arg1, arg2, arg3));
    }

  public final Closure_Handle newClosure(Closure arg0, CallContext arg1) {
        ClosurePool var3 = getClosurePool(arg1);
        return var3.newClosureHandle(arg0);
    }

  public final synchronized ClosurePool getClosurePool(CallContext arg0) {
        Reference var2 = ((Reference) poolMap.get(arg0));
        if (var2 == null) {
            ClosurePool var3 = new ClosurePool(arg0);
            poolMap.put(arg0, new SoftReference(var3));
            return var3;
        } else {
            ClosurePool var3 = ((ClosurePool) var2.get());
            if (var3 == null) {
                var3 = new ClosurePool(arg0);
                poolMap.put(arg0, new SoftReference(var3));
                return var3;
            } else {
                return var3;
            }
        }
    }

  public ClosureMagazine newClosureMagazine(CallContext arg0, Method arg1) {
        int __stk1;
        Foreign var3 = Foreign.getInstance();
        Class[] var4 = arg1.getParameterTypes();
        __stk1 = var4.length < 1 ? 1 : !Closure_Buffer.class.isAssignableFrom(((Class) arg1.getParameterTypes()[0]));
        int var5 = __stk1;
        long var6 = var3.newClosureMagazine(arg0.getAddress(), arg1, ((Boolean) var5));
        if (var6 != 0L) {
            return new ClosureMagazine(var3, arg0, var6);
        } else {
            throw new RuntimeException("could not allocate new closure magazine");
        }
    }

   ClosureManager(ClosureManager_Anon1 arg0) { // было: <init>
        this();
    }

}