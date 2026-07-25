// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.AsmRuntime
package jnr.ffi.provider.jffi;

import com.kenai.jffi.CallContext;
import com.kenai.jffi.Function;
import com.kenai.jffi.HeapInvocationBuffer;
import com.kenai.jffi.MemoryIO;
import com.kenai.jffi.ObjectParameterType;
import com.kenai.jffi.ObjectParameterType_ComponentType;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import jnr.ffi.Address;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter_PostInvocation;
import jnr.ffi.provider.jffi.BufferParameterStrategy;
import jnr.ffi.provider.jffi.DirectMemoryIO;
import jnr.ffi.provider.jffi.NullObjectParameterStrategy;
import jnr.ffi.provider.jffi.ParameterStrategy;
import jnr.ffi.provider.jffi.PointerParameterStrategy;
import jnr.ffi.provider.jffi.PrimitiveArrayParameterStrategy;

public final class AsmRuntime {

    // ---- поля ----
  public static final MemoryIO IO;

    static {
        IO = MemoryIO.getInstance();
    }

  private AsmRuntime() { // было: <init>
        super();
    }

  public static UnsatisfiedLinkError newUnsatisifiedLinkError(String arg0) {
        return new UnsatisfiedLinkError(arg0);
    }

  public static HeapInvocationBuffer newHeapInvocationBuffer(Function arg0) {
        return new HeapInvocationBuffer(arg0);
    }

  public static HeapInvocationBuffer newHeapInvocationBuffer(CallContext arg0) {
        return new HeapInvocationBuffer(arg0);
    }

  public static HeapInvocationBuffer newHeapInvocationBuffer(CallContext arg0, int arg1) {
        return new HeapInvocationBuffer(arg0, arg1);
    }

  public static Pointer pointerValue(long arg0, Runtime arg1) {
        return arg0 == 0L ? null : new DirectMemoryIO(arg1, arg0);
    }

  public static Pointer pointerValue(int arg0, Runtime arg1) {
        return arg0 == 0 ? null : new DirectMemoryIO(arg1, arg0);
    }

  public static boolean isDirect(Pointer arg0) {
        return arg0 == null ? 1 : arg0.isDirect();
    }

  public static int intValue(Pointer arg0) {
        return arg0 == null ? 0 : ((int) arg0.address());
    }

  public static long longValue(Pointer arg0) {
        return arg0 == null ? 0L : arg0.address();
    }

  public static long longValue(Address arg0) {
        return arg0 == null ? 0L : arg0.longValue();
    }

  public static int intValue(Address arg0) {
        return arg0 == null ? 0 : arg0.intValue();
    }

  public static ParameterStrategy nullParameterStrategy() {
        return NullObjectParameterStrategy.NULL;
    }

  public static PointerParameterStrategy directPointerParameterStrategy() {
        return PointerParameterStrategy.DIRECT;
    }

  public static PointerParameterStrategy pointerParameterStrategy(Pointer arg0) {
        if (arg0 == null) {
            return PointerParameterStrategy.DIRECT;
        } else {
            if (!arg0.isDirect()) {
                return otherPointerParameterStrategy(arg0);
            } else {
                return PointerParameterStrategy.DIRECT;
            }
        }
    }

  private static PointerParameterStrategy otherPointerParameterStrategy(Pointer arg0) {
        if (!arg0.hasArray()) {
            throw new RuntimeException(new StringBuilder().append("cannot convert ").append(arg0.getClass()).append(" to native").toString());
        } else {
            return PointerParameterStrategy.HEAP;
        }
    }

  public static BufferParameterStrategy bufferParameterStrategy(Buffer arg0, ObjectParameterType_ComponentType arg1) {
        if (arg0 == null) {
            return BufferParameterStrategy.direct(arg1);
        } else {
            if (!arg0.isDirect()) {
                if (!arg0.hasArray()) {
                    throw new IllegalArgumentException("cannot marshal non-direct, non-array Buffer");
                } else {
                    return BufferParameterStrategy.heap(arg1);
                }
            } else {
                return BufferParameterStrategy.direct(arg1);
            }
        }
    }

  public static BufferParameterStrategy pointerParameterStrategy(Buffer arg0) {
        if (!(arg0 instanceof ByteBuffer)) {
            if (!(arg0 instanceof ShortBuffer)) {
                if (!(arg0 instanceof CharBuffer)) {
                    if (!(arg0 instanceof IntBuffer)) {
                        if (!(arg0 instanceof LongBuffer)) {
                            if (!(arg0 instanceof FloatBuffer)) {
                                if (!(arg0 instanceof DoubleBuffer)) {
                                    if (arg0 != null) {
                                        throw new IllegalArgumentException(new StringBuilder().append("unsupported java.nio.Buffer subclass: ").append(arg0.getClass()).toString());
                                    } else {
                                        return BufferParameterStrategy.direct(ObjectParameterType.BYTE);
                                    }
                                } else {
                                    return bufferParameterStrategy(arg0, ObjectParameterType.DOUBLE);
                                }
                            } else {
                                return bufferParameterStrategy(arg0, ObjectParameterType.FLOAT);
                            }
                        } else {
                            return bufferParameterStrategy(arg0, ObjectParameterType.LONG);
                        }
                    } else {
                        return bufferParameterStrategy(arg0, ObjectParameterType.INT);
                    }
                } else {
                    return bufferParameterStrategy(arg0, ObjectParameterType.CHAR);
                }
            } else {
                return bufferParameterStrategy(arg0, ObjectParameterType.SHORT);
            }
        } else {
            return bufferParameterStrategy(arg0, ObjectParameterType.BYTE);
        }
    }

  public static BufferParameterStrategy pointerParameterStrategy(ByteBuffer arg0) {
        return bufferParameterStrategy(arg0, ObjectParameterType.BYTE);
    }

  public static BufferParameterStrategy pointerParameterStrategy(ShortBuffer arg0) {
        return bufferParameterStrategy(arg0, ObjectParameterType.SHORT);
    }

  public static BufferParameterStrategy pointerParameterStrategy(CharBuffer arg0) {
        return bufferParameterStrategy(arg0, ObjectParameterType.CHAR);
    }

  public static BufferParameterStrategy pointerParameterStrategy(IntBuffer arg0) {
        return bufferParameterStrategy(arg0, ObjectParameterType.INT);
    }

  public static BufferParameterStrategy pointerParameterStrategy(LongBuffer arg0) {
        return bufferParameterStrategy(arg0, ObjectParameterType.LONG);
    }

  public static BufferParameterStrategy pointerParameterStrategy(FloatBuffer arg0) {
        return bufferParameterStrategy(arg0, ObjectParameterType.FLOAT);
    }

  public static BufferParameterStrategy pointerParameterStrategy(DoubleBuffer arg0) {
        return bufferParameterStrategy(arg0, ObjectParameterType.DOUBLE);
    }

  public static ParameterStrategy pointerParameterStrategy(byte[] arg0) {
        return arg0 == null ? NullObjectParameterStrategy.NULL : PrimitiveArrayParameterStrategy.BYTE;
    }

  public static ParameterStrategy pointerParameterStrategy(short[] arg0) {
        return arg0 == null ? NullObjectParameterStrategy.NULL : PrimitiveArrayParameterStrategy.SHORT;
    }

  public static ParameterStrategy pointerParameterStrategy(char[] arg0) {
        return arg0 == null ? NullObjectParameterStrategy.NULL : PrimitiveArrayParameterStrategy.CHAR;
    }

  public static ParameterStrategy pointerParameterStrategy(int[] arg0) {
        return arg0 == null ? NullObjectParameterStrategy.NULL : PrimitiveArrayParameterStrategy.INT;
    }

  public static ParameterStrategy pointerParameterStrategy(long[] arg0) {
        return arg0 == null ? NullObjectParameterStrategy.NULL : PrimitiveArrayParameterStrategy.LONG;
    }

  public static ParameterStrategy pointerParameterStrategy(float[] arg0) {
        return arg0 == null ? NullObjectParameterStrategy.NULL : PrimitiveArrayParameterStrategy.FLOAT;
    }

  public static ParameterStrategy pointerParameterStrategy(double[] arg0) {
        return arg0 == null ? NullObjectParameterStrategy.NULL : PrimitiveArrayParameterStrategy.DOUBLE;
    }

  public static ParameterStrategy pointerParameterStrategy(boolean[] arg0) {
        return arg0 == null ? NullObjectParameterStrategy.NULL : PrimitiveArrayParameterStrategy.BOOLEAN;
    }

  public static void postInvoke(ToNativeConverter_PostInvocation arg0, Object arg1, Object arg2, ToNativeContext arg3) {
        try {
            arg0.postInvoke(arg1, arg2, arg3);
        } catch (Throwable var4) {
        }
    }

}