// исходный (обфусцированный) внутренний класс: jnr.posix.JavaLibCHelper.ReflectiveAccess
package jnr.posix;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

class JavaLibCHelper_ReflectiveAccess {

    // ---- поля ----
  private static final Class SEL_CH_IMPL;
  private static final Method SEL_CH_IMPL_GET_FD;
  private static final Class FILE_CHANNEL_IMPL;
  private static final Field FILE_CHANNEL_IMPL_FD;
  private static final Field FILE_DESCRIPTOR_FD;
  private static final Field FILE_DESCRIPTOR_HANDLE;

    static {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: ldc  #5 // 'sun.nio.ch.SelChImpl'
        //      2: invokestatic  #22 // java.lang.Class.forName:(Ljava/lang/String;)Ljava/lang/Class;
        //      5: astore_1
        //      6: aload_1
        //      7: ldc  #2 // 'getFD'
        //      9: iconst_0
        //     10: anewarray  #7 // java.lang.Class
        //     13: invokevirtual  #24 // java.lang.Class.getMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //     16: astore_0
        //     17: aload_0
        //     18: iconst_1
        //     19: invokevirtual  #27 // java.lang.reflect.Method.setAccessible:(Z)V
        //     22: goto  28 (offset +6)
        //     25: astore_2
        //     26: aconst_null
        //     27: astore_0
        //     28: goto  36 (offset +8)
        //     31: astore_2
        //     32: aconst_null
        //     33: astore_1
        //     34: aconst_null
        //     35: astore_0
        //     36: aload_1
        //     37: putstatic  #19 // jnr.posix.JavaLibCHelper$ReflectiveAccess.SEL_CH_IMPL:Ljava/lang/Class;
        //     40: aload_0
        //     41: putstatic  #20 // jnr.posix.JavaLibCHelper$ReflectiveAccess.SEL_CH_IMPL_GET_FD:Ljava/lang/reflect/Method;
        //     44: ldc  #4 // 'sun.nio.ch.FileChannelImpl'
        //     46: invokestatic  #22 // java.lang.Class.forName:(Ljava/lang/String;)Ljava/lang/Class;
        //     49: astore_3
        //     50: aload_3
        //     51: ldc  #1 // 'fd'
        //     53: invokevirtual  #23 // java.lang.Class.getDeclaredField:(Ljava/lang/String;)Ljava/lang/reflect/Field;
        //     56: astore_2
        //     57: aload_2
        //     58: iconst_1
        //     59: invokevirtual  #26 // java.lang.reflect.Field.setAccessible:(Z)V
        //     62: goto  69 (offset +7)
        //     65: astore  4
        //     67: aconst_null
        //     68: astore_2
        //     69: goto  78 (offset +9)
        //     72: astore  4
        //     74: aconst_null
        //     75: astore_3
        //     76: aconst_null
        //     77: astore_2
        //     78: aload_3
        //     79: putstatic  #15 // jnr.posix.JavaLibCHelper$ReflectiveAccess.FILE_CHANNEL_IMPL:Ljava/lang/Class;
        //     82: aload_2
        //     83: putstatic  #16 // jnr.posix.JavaLibCHelper$ReflectiveAccess.FILE_CHANNEL_IMPL_FD:Ljava/lang/reflect/Field;
        //     86: ldc  #6 // java.io.FileDescriptor
        //     88: ldc  #1 // 'fd'
        //     90: invokevirtual  #23 // java.lang.Class.getDeclaredField:(Ljava/lang/String;)Ljava/lang/reflect/Field;
        //     93: astore  4
        //     95: aload  4
        //     97: iconst_1
        //     98: invokevirtual  #26 // java.lang.reflect.Field.setAccessible:(Z)V
        //    101: goto  109 (offset +8)
        //    104: astore  5
        //    106: aconst_null
        //    107: astore  4
        //    109: aload  4
        //    111: putstatic  #17 // jnr.posix.JavaLibCHelper$ReflectiveAccess.FILE_DESCRIPTOR_FD:Ljava/lang/reflect/Field;
        //    114: getstatic  #21 // jnr.posix.util.Platform.IS_WINDOWS:Z
        //    117: ifeq  151 (offset +34)
        //    120: ldc  #6 // java.io.FileDescriptor
        //    122: ldc  #3 // 'handle'
        //    124: invokevirtual  #23 // java.lang.Class.getDeclaredField:(Ljava/lang/String;)Ljava/lang/reflect/Field;
        //    127: astore  5
        //    129: aload  5
        //    131: iconst_1
        //    132: invokevirtual  #26 // java.lang.reflect.Field.setAccessible:(Z)V
        //    135: goto  143 (offset +8)
        //    138: astore  6
        //    140: aconst_null
        //    141: astore  5
        //    143: aload  5
        //    145: putstatic  #18 // jnr.posix.JavaLibCHelper$ReflectiveAccess.FILE_DESCRIPTOR_HANDLE:Ljava/lang/reflect/Field;
        //    148: goto  155 (offset +7)
        //    151: aconst_null
        //    152: putstatic  #18 // jnr.posix.JavaLibCHelper$ReflectiveAccess.FILE_DESCRIPTOR_HANDLE:Ljava/lang/reflect/Field;
        //    155: return
        //       Exception table:
        //         from 6 to 22 target 25 type java.lang.Exception
        //         from 0 to 28 target 31 type java.lang.Exception
        //         from 50 to 62 target 65 type java.lang.Exception
        //         from 44 to 69 target 72 type java.lang.Exception
        //         from 86 to 101 target 104 type java.lang.Exception
        //         from 120 to 135 target 138 type java.lang.Exception
    }

  private JavaLibCHelper_ReflectiveAccess() { // было: <init>
        super();
    }

  static Method access$000() {
        return SEL_CH_IMPL_GET_FD;
    }

  static Class access$100() {
        return SEL_CH_IMPL;
    }

  static Field access$200() {
        return FILE_CHANNEL_IMPL_FD;
    }

  static Class access$300() {
        return FILE_CHANNEL_IMPL;
    }

  static Field access$400() {
        return FILE_DESCRIPTOR_FD;
    }

  static Field access$500() {
        return FILE_DESCRIPTOR_HANDLE;
    }

}