// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.BufferParameterStrategy
package jnr.ffi.provider.jffi;

import com.kenai.jffi.MemoryIO;
import com.kenai.jffi.ObjectParameterStrategy_StrategyType;
import com.kenai.jffi.ObjectParameterType;
import com.kenai.jffi.ObjectParameterType_ComponentType;
import com.kenai.jffi.ObjectParameterType_ObjectType;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import java.util.EnumSet;
import java.util.Iterator;
import jnr.ffi.provider.jffi.ParameterStrategy;

public final class BufferParameterStrategy extends ParameterStrategy {

    // ---- поля ----
  private static final int BYTE_POSITION_SHIFT = 0;
  private static final int SHORT_POSITION_SHIFT = 1;
  private static final int CHAR_POSITION_SHIFT = 1;
  private static final int BOOLEAN_POSITION_SHIFT = 2;
  private static final int INT_POSITION_SHIFT = 2;
  private static final int FLOAT_POSITION_SHIFT = 2;
  private static final int LONG_POSITION_SHIFT = 3;
  private static final int DOUBLE_POSITION_SHIFT = 3;
  private final int shift;
  private static final BufferParameterStrategy[] DIRECT_BUFFER_PARAMETER_STRATEGIES;
  private static final BufferParameterStrategy[] HEAP_BUFFER_PARAMETER_STRATEGIES;

    static {
        EnumSet var0 = EnumSet.allOf(ObjectParameterType_ComponentType.class);
        DIRECT_BUFFER_PARAMETER_STRATEGIES = new BufferParameterStrategy[var0.size()];
        HEAP_BUFFER_PARAMETER_STRATEGIES = new BufferParameterStrategy[var0.size()];
        Iterator var1 = var0.iterator();
        while (var1.hasNext()) {
            ObjectParameterType_ComponentType var2 = ((ObjectParameterType_ComponentType) var1.next());
            DIRECT_BUFFER_PARAMETER_STRATEGIES[var2.ordinal()] = new BufferParameterStrategy(DIRECT, var2);
            HEAP_BUFFER_PARAMETER_STRATEGIES[var2.ordinal()] = new BufferParameterStrategy(HEAP, var2);
            continue;
        }
    }

  private BufferParameterStrategy(ObjectParameterStrategy_StrategyType arg0, ObjectParameterType_ComponentType arg1) { // было: <init>
        super(arg0, ObjectParameterType.create(ObjectParameterType_ObjectType.ARRAY, arg1));
        shift = calculateShift(arg1);
    }

  public static long address(ByteBuffer arg0) {
        return address(arg0, 0);
    }

  public static long address(ShortBuffer arg0) {
        return address(arg0, 1);
    }

  public static long address(CharBuffer arg0) {
        return address(arg0, 1);
    }

  public static long address(IntBuffer arg0) {
        return address(arg0, 2);
    }

  public static long address(FloatBuffer arg0) {
        return address(arg0, 2);
    }

  public static long address(LongBuffer arg0) {
        return address(arg0, 3);
    }

  public static long address(DoubleBuffer arg0) {
        return address(arg0, 3);
    }

  public static long address(Buffer arg0) {
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
                                        return address(arg0, 0);
                                    }
                                } else {
                                    return address(arg0, 3);
                                }
                            } else {
                                return address(arg0, 2);
                            }
                        } else {
                            return address(arg0, 3);
                        }
                    } else {
                        return address(arg0, 2);
                    }
                } else {
                    return address(arg0, 1);
                }
            } else {
                return address(arg0, 1);
            }
        } else {
            return address(arg0, 0);
        }
    }

  private static long address(Buffer arg0, int arg1) {
        return arg0 == null ? 0L : !arg0.isDirect() ? 0L : MemoryIO.getInstance().getDirectBufferAddress(arg0) + ((long) (arg0.position() << arg1));
    }

  public long address(Object arg0) {
        return address(((Buffer) arg0), shift);
    }

  public Object object(Object arg0) {
        return (((Buffer) arg0)).array();
    }

  public int offset(Object arg0) {
        Buffer var2 = ((Buffer) arg0);
        return var2.arrayOffset() + var2.position();
    }

  public int length(Object arg0) {
        return (((Buffer) arg0)).remaining();
    }

  static int calculateShift(ObjectParameterType_ComponentType arg0) {
        switch (arg0) {
            case BYTE:
                return 0;
            case SHORT:
                return 1;
            case CHAR:
                return 1;
            case INT:
                return 2;
            case BOOLEAN:
                return 2;
            case FLOAT:
                return 2;
            case LONG:
                return 3;
            case DOUBLE:
                return 3;
            default:
                throw new IllegalArgumentException(new StringBuilder().append("unsupported component type: ").append(arg0).toString());
        }
    }

  static BufferParameterStrategy direct(ObjectParameterType_ComponentType arg0) {
        return ((BufferParameterStrategy) DIRECT_BUFFER_PARAMETER_STRATEGIES[arg0.ordinal()]);
    }

  static BufferParameterStrategy heap(ObjectParameterType_ComponentType arg0) {
        return ((BufferParameterStrategy) HEAP_BUFFER_PARAMETER_STRATEGIES[arg0.ordinal()]);
    }

}