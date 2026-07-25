// исходный (обфусцированный) внутренний класс: jnr.ffi.Pointer
package jnr.ffi;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import jnr.ffi.Address;
import jnr.ffi.Runtime;
import jnr.ffi.Type;
import jnr.ffi.provider.MemoryManager;

public abstract class Pointer {

    // ---- поля ----
  private final Runtime runtime;
  private final long address;
  private final boolean isDirect;

  public static Pointer wrap(Runtime arg0, long arg1) {
        return arg0.getMemoryManager().newPointer(arg1);
    }

  public static Pointer wrap(Runtime arg0, long arg1, long arg2) {
        return arg0.getMemoryManager().newPointer(arg1, arg2);
    }

  public static Pointer wrap(Runtime arg0, ByteBuffer arg1) {
        return arg0.getMemoryManager().newPointer(arg1);
    }

  public static Pointer newIntPointer(Runtime arg0, long arg1) {
        return arg0.getMemoryManager().newOpaquePointer(arg1);
    }

  protected Pointer(Runtime arg0, long arg1, boolean arg2) { // было: <init>
        super();
        runtime = arg0;
        address = arg1;
        isDirect = arg2;
    }

  public final boolean isDirect() {
        return isDirect;
    }

  public final long address() {
        return address;
    }

  public final Runtime getRuntime() {
        return runtime;
    }

  public String toString() {
        StringBuilder var1 = new StringBuilder();
        var1.append(getClass().getName());
        var1.append(String.format("[address=%#x", new Object[]{Long.valueOf(address())}));
        if (size() != 9223372036854775807L) {
            var1.append(String.format(" size=%d", new Object[]{Long.valueOf(size())}));
        }
        var1.append(']');
        return var1.toString();
    }

  public abstract long size();

  public abstract boolean hasArray();

  public abstract Object array();

  public abstract int arrayOffset();

  public abstract int arrayLength();

  public abstract byte getByte(long arg0);

  public abstract short getShort(long arg0);

  public abstract int getInt(long arg0);

  public abstract long getLong(long arg0);

  public abstract long getLongLong(long arg0);

  public abstract float getFloat(long arg0);

  public abstract double getDouble(long arg0);

  public abstract long getNativeLong(long arg0);

  public abstract long getInt(Type arg0, long arg1);

  public abstract void putByte(long arg0, byte arg1);

  public abstract void putShort(long arg0, short arg1);

  public abstract void putInt(long arg0, int arg1);

  public abstract void putLong(long arg0, long arg1);

  public abstract void putLongLong(long arg0, long arg1);

  public abstract void putFloat(long arg0, float arg1);

  public abstract void putDouble(long arg0, double arg1);

  public abstract void putNativeLong(long arg0, long arg1);

  public abstract void putInt(Type arg0, long arg1, long arg2);

  public abstract long getAddress(long arg0);

  public abstract void putAddress(long arg0, long arg1);

  public abstract void putAddress(long arg0, Address arg1);

  public abstract void get(long arg0, byte[] arg1, int arg2, int arg3);

  public abstract void put(long arg0, byte[] arg1, int arg2, int arg3);

  public abstract void get(long arg0, short[] arg1, int arg2, int arg3);

  public abstract void put(long arg0, short[] arg1, int arg2, int arg3);

  public abstract void get(long arg0, int[] arg1, int arg2, int arg3);

  public abstract void put(long arg0, int[] arg1, int arg2, int arg3);

  public abstract void get(long arg0, long[] arg1, int arg2, int arg3);

  public abstract void put(long arg0, long[] arg1, int arg2, int arg3);

  public abstract void get(long arg0, float[] arg1, int arg2, int arg3);

  public abstract void put(long arg0, float[] arg1, int arg2, int arg3);

  public abstract void get(long arg0, double[] arg1, int arg2, int arg3);

  public abstract void put(long arg0, double[] arg1, int arg2, int arg3);

  public abstract Pointer getPointer(long arg0);

  public abstract Pointer getPointer(long arg0, long arg1);

  public abstract void putPointer(long arg0, Pointer arg1);

  public abstract String getString(long arg0);

  public abstract String getString(long arg0, int arg1, Charset arg2);

  public abstract void putString(long arg0, String arg1, int arg2, Charset arg3);

  public abstract Pointer slice(long arg0);

  public abstract Pointer slice(long arg0, long arg1);

  public abstract void transferTo(long arg0, Pointer arg1, long arg2, long arg3);

  public abstract void transferFrom(long arg0, Pointer arg1, long arg2, long arg3);

  public abstract void checkBounds(long arg0, long arg1);

  public abstract void setMemory(long arg0, long arg1, byte arg2);

  public abstract int indexOf(long arg0, byte arg1);

  public abstract int indexOf(long arg0, byte arg1, int arg2);

  public void get(long arg0, Pointer[] arg1, int arg2, int arg3) {
        int var6 = getRuntime().addressSize();
        int var7 = 0;
        while (var7 < arg3) {
            arg1[arg2 + var7] = getPointer(arg0 + ((long) (var7 * var6)));
            ++var7;
            continue;
        }
    }

  public void put(long arg0, Pointer[] arg1, int arg2, int arg3) {
        int var6 = getRuntime().addressSize();
        int var7 = 0;
        while (var7 < arg3) {
            putPointer(arg0 + ((long) (var7 * var6)), ((Pointer) arg1[arg2 + var7]));
            ++var7;
            continue;
        }
    }

  public String[] getNullTerminatedStringArray(long arg0) {
        Pointer var3 = getPointer(arg0);
        ArrayList var5;
        if (var3 != null) {
            int var4 = getRuntime().addressSize();
            var5 = new ArrayList();
            var5.add(var3.getString(0L));
            int var6 = var4;
        } else {
            return new String[0];
        }
        while (true) {
            var3 = getPointer(arg0 + ((long) var6));
            if (var3 == null) {
                break;
            }
            var5.add(var3.getString(0L));
            int var6 = var6 + var4;
            continue;
        }
        return ((String[]) var5.toArray(new String[var5.size()]));
    }

  public Pointer[] getNullTerminatedPointerArray(long arg0) {
        Pointer var3 = getPointer(arg0);
        ArrayList var5;
        if (var3 != null) {
            int var4 = getRuntime().addressSize();
            var5 = new ArrayList();
            var5.add(var3);
            int var6 = var4;
        } else {
            return new Pointer[0];
        }
        while (true) {
            var3 = getPointer(arg0 + ((long) var6));
            if (var3 == null) {
                break;
            }
            var5.add(var3);
            int var6 = var6 + var4;
            continue;
        }
        return ((Pointer[]) var5.toArray(new Pointer[var5.size()]));
    }

}