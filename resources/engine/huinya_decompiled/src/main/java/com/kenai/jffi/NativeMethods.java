// исходный (обфусцированный) внутренний класс: com.kenai.jffi.NativeMethods
package com.kenai.jffi;

import com.kenai.jffi.Foreign;
import com.kenai.jffi.MemoryIO;
import com.kenai.jffi.NativeMethod;
import com.kenai.jffi.NativeMethods_ResourceHolder;
import com.kenai.jffi.Platform;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public final class NativeMethods {

    // ---- поля ----
  private static final Map registeredMethods;
  private final NativeMethods_ResourceHolder memory;

    static {
        registeredMethods = new WeakHashMap();
    }

  private NativeMethods(NativeMethods_ResourceHolder arg0) { // было: <init>
        super();
        memory = arg0;
    }

  public static final synchronized void register(Class arg0, List arg1) {
        int var2 = 0;
        Iterator var3 = arg1.iterator();
        NativeMethod var4;
        while (var3.hasNext()) {
            var4 = ((NativeMethod) var3.next());
            var2 = var2 + var4.name.getBytes().length + 1;
            var2 = var2 + var4.signature.getBytes().length + 1;
            continue;
        }
        var3 = Platform.getPlatform().addressSize() / 8;
        MemoryIO var4 = MemoryIO.getInstance();
        int var5 = arg1.size() * 3 * var3;
        long var6 = var4.allocateMemory(((long) (var5 + var2)), true);
        Iterator var11;
        if (var6 != 0L) {
            NativeMethods var8 = new NativeMethods(new NativeMethods_ResourceHolder(var4, var6));
            int var9 = 0;
            int var10 = var5;
            var11 = arg1.iterator();
        } else {
            throw new OutOfMemoryError("could not allocate native memory");
        }
        while (var11.hasNext()) {
            NativeMethod var12 = ((NativeMethod) var11.next());
            byte[] var13 = var12.name.getBytes();
            long var14 = var6 + ((long) var10);
            int var10 = var10 + var13.length + 1;
            var4.putZeroTerminatedByteArray(var14, var13, 0, var13.length);
            byte[] var16 = var12.signature.getBytes();
            long var17 = var6 + ((long) var10);
            var10 = var10 + var16.length + 1;
            var4.putZeroTerminatedByteArray(var17, var16, 0, var16.length);
            var4.putAddress(var6 + ((long) var9), var14);
            int var9 = var9 + var3;
            var4.putAddress(var6 + ((long) var9), var17);
            var9 = var9 + var3;
            var4.putAddress(var6 + ((long) var9), var12.function);
            var9 = var9 + var3;
            continue;
        }
        if (Foreign.getInstance().registerNatives(arg0, var6, arg1.size()) == 0) {
            registeredMethods.put(arg0, var8);
            return;
        } else {
            throw new RuntimeException("failed to register native methods");
        }
    }

  public static final synchronized void unregister(Class arg0) {
        if (registeredMethods.containsKey(arg0)) {
            if (Foreign.getInstance().unregisterNatives(arg0) == 0) {
                registeredMethods.remove(arg0);
                return;
            } else {
                throw new RuntimeException("failed to unregister native methods");
            }
        } else {
            throw new IllegalArgumentException("methods were not registered on class via NativeMethods.register");
        }
    }

}