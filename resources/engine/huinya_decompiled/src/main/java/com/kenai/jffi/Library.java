// исходный (обфусцированный) внутренний класс: com.kenai.jffi.Library
package com.kenai.jffi;

import com.kenai.jffi.Foreign;
import com.kenai.jffi.Library_DefaultLibrary;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public final class Library {

    // ---- поля ----
  private static final Map cache;
  private static final Object lock;
  private static final ThreadLocal lastError;
  public static final int LAZY = 1;
  public static final int NOW = 2;
  public static final int LOCAL = 4;
  public static final int GLOBAL = 8;
  private final long handle;
  private final String name;
  private final Foreign foreign;
  private volatile int disposed;
  private static final AtomicIntegerFieldUpdater UPDATER;

    static {
        cache = new ConcurrentHashMap();
        lock = new Object();
        lastError = new ThreadLocal();
        UPDATER = AtomicIntegerFieldUpdater.newUpdater(Library.class, "disposed");
    }

  private static long dlopen(Foreign arg0, String arg1, int arg2) {
        long __stk1;
        try {
            __stk1 = Foreign.dlopen(arg1, arg2);
        } catch (UnsatisfiedLinkError var3) {
            lastError.set(var3.getMessage());
            return 0L;
        }
    }

  public static final Library getDefault() {
        return Library_DefaultLibrary.access$000();
    }

  public static final Library getCachedInstance(String arg0, int arg1) {
        Library __stk1;
        if (arg0 != null) {
            WeakReference var2 = ((WeakReference) cache.get(arg0));
            __stk1 = var2 == null ? null : ((Library) var2.get());
            Library var3 = __stk1;
            if (var3 == null) {
                var3 = openLibrary(arg0, arg1);
                if (var3 != null) {
                    cache.put(arg0, new WeakReference(var3));
                    return ((Library) var3);
                } else {
                    return null;
                }
            } else {
                return ((Library) var3);
            }
        } else {
            return getDefault();
        }
    }

  public static final Library openLibrary(String arg0, int arg1) {
        if (arg1 == 0) {
            arg1 = 5;
        }
        Foreign var2 = Foreign.getInstance();
        long var3 = dlopen(var2, arg0, arg1);
        return var3 == 0L ? null : new Library(var2, arg0, var3);
    }

  private Library(Foreign arg0, String arg1, long arg2) { // было: <init>
        super();
        foreign = arg0;
        name = arg1;
        handle = arg2;
    }

  public final long getSymbolAddress(String arg0) {
        long __stk1;
        try {
            __stk1 = Foreign.dlsym(handle, arg0);
        } catch (UnsatisfiedLinkError var2) {
            lastError.set(Foreign.dlerror());
            return 0L;
        }
    }

  public static final String getLastError() {
        String var0 = ((String) lastError.get());
        return var0 == null ? "unknown" : var0;
    }

  protected void finalize() {
        try {
            int var1 = UPDATER.getAndSet(this, 1);
            if (var1 != 0) {
            }
            if (handle != 0L) {
                Foreign.dlclose(handle);
            }
        } catch (Throwable var2) {
            super.finalize();
            throw var2;
        }
    }

}