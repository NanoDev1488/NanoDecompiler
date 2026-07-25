// исходный (обфусцированный) внутренний класс: com.kenai.jffi.MemoryIO
package com.kenai.jffi;

import com.kenai.jffi.Foreign;
import com.kenai.jffi.MemoryIO_CheckedMemorySingletonHolder;
import com.kenai.jffi.MemoryIO_CheckedNativeImpl;
import com.kenai.jffi.MemoryIO_NativeImpl32;
import com.kenai.jffi.MemoryIO_NativeImpl64;
import com.kenai.jffi.MemoryIO_SingletonHolder;
import com.kenai.jffi.Platform;
import com.kenai.jffi.UnsafeMemoryIO_UnsafeMemoryIO32;
import com.kenai.jffi.UnsafeMemoryIO_UnsafeMemoryIO64;
import java.lang.reflect.Method;
import java.nio.Buffer;
import java.nio.ByteBuffer;

public abstract class MemoryIO {

    // ---- поля ----
  final Foreign foreign;
  static final long ADDRESS_MASK;

    static {
        ADDRESS_MASK = Platform.getPlatform().addressMask();
    }

  public static MemoryIO getInstance() {
        return MemoryIO_SingletonHolder.access$200();
    }

  public static MemoryIO getCheckedInstance() {
        return MemoryIO_CheckedMemorySingletonHolder.access$300();
    }

   MemoryIO() { // было: <init>
        super();
        foreign = Foreign.getInstance();
    }

  private static MemoryIO newMemoryIO() {
        MemoryIO __stk1;
        MemoryIO __stk2;
        try {
            if (Boolean.getBoolean("jffi.memory.checked")) {
                __stk1 = newNativeCheckedImpl();
            }
            try {
                if (Boolean.getBoolean("jffi.unsafe.disabled")) {
                    __stk2 = newNativeImpl();
                }
                __stk2 = !isUnsafeAvailable() ? newNativeImpl() : newUnsafeImpl();
            } catch (Throwable var0) {
                return newNativeImpl();
            }
        } catch (Throwable var0) {
            return newNativeImpl();
        }
    }

  private static MemoryIO newNativeImpl() {
        return Platform.getPlatform().addressSize() != 32 ? newNativeImpl64() : newNativeImpl32();
    }

  private static MemoryIO newNativeCheckedImpl() {
        return !Foreign.isMemoryProtectionEnabled() ? newNativeImpl() : new MemoryIO_CheckedNativeImpl(null);
    }

  private static MemoryIO newNativeImpl32() {
        return new MemoryIO_NativeImpl32(null);
    }

  private static MemoryIO newNativeImpl64() {
        return new MemoryIO_NativeImpl64(null);
    }

  private static MemoryIO newUnsafeImpl() {
        return Platform.getPlatform().addressSize() != 32 ? newUnsafeImpl64() : newUnsafeImpl32();
    }

  private static MemoryIO newUnsafeImpl32() {
        return new UnsafeMemoryIO_UnsafeMemoryIO32();
    }

  private static MemoryIO newUnsafeImpl64() {
        return new UnsafeMemoryIO_UnsafeMemoryIO64();
    }

  public abstract byte getByte(long arg0);

  public abstract short getShort(long arg0);

  public abstract int getInt(long arg0);

  public abstract long getLong(long arg0);

  public abstract float getFloat(long arg0);

  public abstract double getDouble(long arg0);

  public abstract long getAddress(long arg0);

  public abstract void putByte(long arg0, byte arg1);

  public abstract void putShort(long arg0, short arg1);

  public abstract void putInt(long arg0, int arg1);

  public abstract void putLong(long arg0, long arg1);

  public abstract void putFloat(long arg0, float arg1);

  public abstract void putDouble(long arg0, double arg1);

  public abstract void putAddress(long arg0, long arg1);

  public final void copyMemory(long arg0, long arg1, long arg2) {
        if (arg1 + arg2 <= arg0) {
            _copyMemory(arg0, arg1, arg2);
        } else {
            if (arg0 + arg2 > arg1) {
                memmove(arg1, arg0, arg2);
            } else {
                _copyMemory(arg0, arg1, arg2);
            }
        }
    }

  abstract void _copyMemory(long arg0, long arg1, long arg2);

  public abstract void setMemory(long arg0, long arg1, byte arg2);

  public abstract void memcpy(long arg0, long arg1, long arg2);

  public abstract void memmove(long arg0, long arg1, long arg2);

  public final void memset(long arg0, int arg1, long arg2) {
        setMemory(arg0, arg2, ((byte) arg1));
    }

  public abstract long memchr(long arg0, int arg1, long arg2);

  public abstract void putByteArray(long arg0, byte[] arg1, int arg2, int arg3);

  public abstract void getByteArray(long arg0, byte[] arg1, int arg2, int arg3);

  public abstract void putCharArray(long arg0, char[] arg1, int arg2, int arg3);

  public abstract void getCharArray(long arg0, char[] arg1, int arg2, int arg3);

  public abstract void putShortArray(long arg0, short[] arg1, int arg2, int arg3);

  public abstract void getShortArray(long arg0, short[] arg1, int arg2, int arg3);

  public abstract void putIntArray(long arg0, int[] arg1, int arg2, int arg3);

  public abstract void getIntArray(long arg0, int[] arg1, int arg2, int arg3);

  public abstract void putLongArray(long arg0, long[] arg1, int arg2, int arg3);

  public abstract void getLongArray(long arg0, long[] arg1, int arg2, int arg3);

  public abstract void putFloatArray(long arg0, float[] arg1, int arg2, int arg3);

  public abstract void getFloatArray(long arg0, float[] arg1, int arg2, int arg3);

  public abstract void putDoubleArray(long arg0, double[] arg1, int arg2, int arg3);

  public abstract void getDoubleArray(long arg0, double[] arg1, int arg2, int arg3);

  public final long allocateMemory(long arg0, boolean arg1) {
        return Foreign.allocateMemory(arg0, arg1) & ADDRESS_MASK;
    }

  public final void freeMemory(long arg0) {
        Foreign.freeMemory(arg0);
    }

  public abstract long getStringLength(long arg0);

  public abstract byte[] getZeroTerminatedByteArray(long arg0);

  public abstract byte[] getZeroTerminatedByteArray(long arg0, int arg1);

    @Deprecated
  public final byte[] getZeroTerminatedByteArray(long arg0, long arg1) {
        return getZeroTerminatedByteArray(arg0, ((int) arg1));
    }

  public abstract void putZeroTerminatedByteArray(long arg0, byte[] arg1, int arg2, int arg3);

  public final long indexOf(long arg0, byte arg1) {
        long var4 = memchr(arg0, arg1, 2147483647L);
        return var4 == 0L ? -1L : var4 - arg0;
    }

  public final long indexOf(long arg0, byte arg1, int arg2) {
        long var5 = memchr(arg0, arg1, ((long) arg2));
        return var5 == 0L ? -1L : var5 - arg0;
    }

  public final ByteBuffer newDirectByteBuffer(long arg0, int arg1) {
        return foreign.newDirectByteBuffer(arg0, arg1);
    }

  public final long getDirectBufferAddress(Buffer arg0) {
        return foreign.getDirectBufferAddress(arg0);
    }

  private static void verifyAccessor(Class arg0, Class arg1) {
        String var2 = arg1.getSimpleName();
        String var3 = new StringBuilder().append(var2.substring(0, 1).toUpperCase()).append(var2.substring(1)).toString();
        Method var4 = arg0.getDeclaredMethod(new StringBuilder().append("get").append(var3).toString(), new Class[]{Long.TYPE});
        if (var4.getReturnType().equals(arg1)) {
            arg0.getDeclaredMethod(new StringBuilder().append("put").append(var3).toString(), new Class[]{Long.TYPE, arg1});
            return;
        } else {
            throw new NoSuchMethodException(new StringBuilder().append("Incorrect return type for ").append(var4.getName()).toString());
        }
    }

  static boolean isUnsafeAvailable() {
        int __stk6;
        try {
            Class var0 = Class.forName("sun.misc.Unsafe");
            Class[] var1 = new Class[]{Byte.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE};
            Class[] var2 = var1;
            int var3 = var2.length;
            int var4 = 0;
            while (var4 < var3) {
                Object var5 = var2[var4];
                verifyAccessor(var0, ((Class) var5));
                ++var4;
                continue;
            }
            Class[] __obj2 = new Class[1];
            __obj2[0] = Long.TYPE;
            var0.getDeclaredMethod("getAddress", __obj2);
            Class[] __obj3 = new Class[2];
            __obj3[0] = Long.TYPE;
            __obj3[1] = Long.TYPE;
            var0.getDeclaredMethod("putAddress", __obj3);
            Class[] __obj4 = new Class[1];
            __obj4[0] = Long.TYPE;
            var0.getDeclaredMethod("allocateMemory", __obj4);
            Class[] __obj5 = new Class[1];
            __obj5[0] = Long.TYPE;
            var0.getDeclaredMethod("freeMemory", __obj5);
            __stk6 = 1;
        } catch (Throwable e1) {
            Throwable var0 = e1;
            return false;
        }
    }

  static MemoryIO access$000() {
        return newMemoryIO();
    }

  static MemoryIO access$100() {
        return newNativeCheckedImpl();
    }

}