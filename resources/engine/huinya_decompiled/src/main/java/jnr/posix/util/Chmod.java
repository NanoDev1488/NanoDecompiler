// исходный (обфусцированный) внутренний класс: jnr.posix.util.Chmod
package jnr.posix.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class Chmod {

    // ---- поля ----
  private static final boolean CHMOD_API_AVAILABLE;
  private static final Method setWritable;
  private static final Method setReadable;
  private static final Method setExecutable;

    static {
        int var0 = 0;
        Object var1 = null;
        Object var2 = null;
        Object var3 = null;
        try {
            var1 = File.class.getMethod("setWritable", new Class[]{Boolean.TYPE, Boolean.TYPE});
            Class[] __obj2 = new Class[2];
            __obj2[0] = Boolean.TYPE;
            __obj2[1] = Boolean.TYPE;
            var2 = File.class.getMethod("setReadable", __obj2);
            Class[] __obj3 = new Class[2];
            __obj3[0] = Boolean.TYPE;
            __obj3[1] = Boolean.TYPE;
            var3 = File.class.getMethod("setExecutable", __obj3);
            var0 = 1;
        } catch (Exception var4) {
        }
        setWritable = ((Method) var1);
        setReadable = ((Method) var2);
        setExecutable = ((Method) var3);
        CHMOD_API_AVAILABLE = var0;
    }

  public Chmod() { // было: <init>
        super();
    }

  public static int chmod(File arg0, String arg1) {
        int __stk1;
        int var2;
        if (CHMOD_API_AVAILABLE) {
            var2 = 48;
            if (arg1.length() >= 1) {
                var2 = arg1.charAt(arg1.length() - 1);
            }
            int var3 = 48;
            if (arg1.length() >= 3) {
                var3 = arg1.charAt(arg1.length() - 3);
            }
            if (setPermissions(arg0, var2, false)) {
                if (setPermissions(arg0, var3, true)) {
                    return 0;
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        }
        try {
            Process var2 = Runtime.getRuntime().exec(new StringBuilder().append("/bin/chmod ").append(arg1).append(" ").append(arg0.getAbsolutePath()).toString());
            var2.waitFor();
            __stk1 = var2.exitValue();
        } catch (IOException e1) {
            Throwable var2 = e1;
        } catch (InterruptedException e2) {
            Throwable var2 = e2;
            Thread.currentThread().interrupt();
        }
    }

  private static boolean setPermissions(File arg0, char arg1, boolean arg2) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: iload_1
        //      1: bipush  8
        //      3: invokestatic  #32 // java.lang.Character.digit:(CI)I
        //      6: istore_3
        //      7: iload_3
        //      8: iconst_1
        //      9: iand
        //     10: ifeq  41 (offset +31)
        //     13: getstatic  #27 // jnr.posix.util.Chmod.setExecutable:Ljava/lang/reflect/Method;
        //     16: aload_0
        //     17: iconst_2
        //     18: anewarray  #14 // java.lang.Object
        //     21: dup
        //     22: iconst_0
        //     23: getstatic  #24 // java.lang.Boolean.TRUE:Ljava/lang/Boolean;
        //     26: aastore
        //     27: dup
        //     28: iconst_1
        //     29: iload_2
        //     30: invokestatic  #31 // java.lang.Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //     33: aastore
        //     34: invokevirtual  #46 // java.lang.reflect.Method.invoke:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //     37: pop
        //     38: goto  66 (offset +28)
        //     41: getstatic  #27 // jnr.posix.util.Chmod.setExecutable:Ljava/lang/reflect/Method;
        //     44: aload_0
        //     45: iconst_2
        //     46: anewarray  #14 // java.lang.Object
        //     49: dup
        //     50: iconst_0
        //     51: getstatic  #23 // java.lang.Boolean.FALSE:Ljava/lang/Boolean;
        //     54: aastore
        //     55: dup
        //     56: iconst_1
        //     57: iload_2
        //     58: invokestatic  #31 // java.lang.Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //     61: aastore
        //     62: invokevirtual  #46 // java.lang.reflect.Method.invoke:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //     65: pop
        //     66: iload_3
        //     67: iconst_2
        //     68: iand
        //     69: ifeq  100 (offset +31)
        //     72: getstatic  #29 // jnr.posix.util.Chmod.setWritable:Ljava/lang/reflect/Method;
        //     75: aload_0
        //     76: iconst_2
        //     77: anewarray  #14 // java.lang.Object
        //     80: dup
        //     81: iconst_0
        //     82: getstatic  #24 // java.lang.Boolean.TRUE:Ljava/lang/Boolean;
        //     85: aastore
        //     86: dup
        //     87: iconst_1
        //     88: iload_2
        //     89: invokestatic  #31 // java.lang.Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //     92: aastore
        //     93: invokevirtual  #46 // java.lang.reflect.Method.invoke:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //     96: pop
        //     97: goto  125 (offset +28)
        //    100: getstatic  #29 // jnr.posix.util.Chmod.setWritable:Ljava/lang/reflect/Method;
        //    103: aload_0
        //    104: iconst_2
        //    105: anewarray  #14 // java.lang.Object
        //    108: dup
        //    109: iconst_0
        //    110: getstatic  #23 // java.lang.Boolean.FALSE:Ljava/lang/Boolean;
        //    113: aastore
        //    114: dup
        //    115: iconst_1
        //    116: iload_2
        //    117: invokestatic  #31 // java.lang.Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    120: aastore
        //    121: invokevirtual  #46 // java.lang.reflect.Method.invoke:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //    124: pop
        //    125: iload_3
        //    126: iconst_4
        //    127: iand
        //    128: ifeq  159 (offset +31)
        //    131: getstatic  #28 // jnr.posix.util.Chmod.setReadable:Ljava/lang/reflect/Method;
        //    134: aload_0
        //    135: iconst_2
        //    136: anewarray  #14 // java.lang.Object
        //    139: dup
        //    140: iconst_0
        //    141: getstatic  #24 // java.lang.Boolean.TRUE:Ljava/lang/Boolean;
        //    144: aastore
        //    145: dup
        //    146: iconst_1
        //    147: iload_2
        //    148: invokestatic  #31 // java.lang.Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    151: aastore
        //    152: invokevirtual  #46 // java.lang.reflect.Method.invoke:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //    155: pop
        //    156: goto  184 (offset +28)
        //    159: getstatic  #28 // jnr.posix.util.Chmod.setReadable:Ljava/lang/reflect/Method;
        //    162: aload_0
        //    163: iconst_2
        //    164: anewarray  #14 // java.lang.Object
        //    167: dup
        //    168: iconst_0
        //    169: getstatic  #23 // java.lang.Boolean.FALSE:Ljava/lang/Boolean;
        //    172: aastore
        //    173: dup
        //    174: iconst_1
        //    175: iload_2
        //    176: invokestatic  #31 // java.lang.Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    179: aastore
        //    180: invokevirtual  #46 // java.lang.reflect.Method.invoke:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //    183: pop
        //    184: iconst_1
        //    185: ireturn
        //    186: astore  4
        //    188: goto  193 (offset +5)
        //    191: astore  4
        //    193: iconst_0
        //    194: ireturn
        //       Exception table:
        //         from 7 to 185 target 186 type java.lang.IllegalAccessException
        //         from 7 to 185 target 191 type java.lang.reflect.InvocationTargetException
    }

}