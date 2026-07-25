// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.ArrayFrob
package org.freedesktop.dbus;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class ArrayFrob {

    // ---- поля ----
  private static final Map PRIMITIVE_TO_WRAPPER;
  private static final Map WRAPPER_TO_PRIMITIVE;

    static {
        PRIMITIVE_TO_WRAPPER = new ConcurrentHashMap();
        WRAPPER_TO_PRIMITIVE = new ConcurrentHashMap();
        PRIMITIVE_TO_WRAPPER.put(Boolean.TYPE, Boolean.class);
        PRIMITIVE_TO_WRAPPER.put(Byte.TYPE, Byte.class);
        PRIMITIVE_TO_WRAPPER.put(Short.TYPE, Short.class);
        PRIMITIVE_TO_WRAPPER.put(Character.TYPE, Character.class);
        PRIMITIVE_TO_WRAPPER.put(Integer.TYPE, Integer.class);
        PRIMITIVE_TO_WRAPPER.put(Long.TYPE, Long.class);
        PRIMITIVE_TO_WRAPPER.put(Float.TYPE, Float.class);
        PRIMITIVE_TO_WRAPPER.put(Double.TYPE, Double.class);
        WRAPPER_TO_PRIMITIVE.put(Boolean.class, Boolean.TYPE);
        WRAPPER_TO_PRIMITIVE.put(Byte.class, Byte.TYPE);
        WRAPPER_TO_PRIMITIVE.put(Short.class, Short.TYPE);
        WRAPPER_TO_PRIMITIVE.put(Character.class, Character.TYPE);
        WRAPPER_TO_PRIMITIVE.put(Integer.class, Integer.TYPE);
        WRAPPER_TO_PRIMITIVE.put(Long.class, Long.TYPE);
        WRAPPER_TO_PRIMITIVE.put(Float.class, Float.TYPE);
        WRAPPER_TO_PRIMITIVE.put(Double.class, Double.TYPE);
    }

  private ArrayFrob() { // было: <init>
        super();
    }

  public static Map getPrimitiveToWrapperTypes() {
        return Collections.unmodifiableMap(PRIMITIVE_TO_WRAPPER);
    }

  public static Map getWrapperToPrimitiveTypes() {
        return Collections.unmodifiableMap(WRAPPER_TO_PRIMITIVE);
    }

  public static Object[] wrap(Object arg0) {
        Class var1 = arg0.getClass();
        if (!var1.isArray()) {
            throw new IllegalArgumentException("Not an array");
        }
        Class var2 = var1.getComponentType();
        Class var3 = ((Class) PRIMITIVE_TO_WRAPPER.get(var2));
        Object[] var4;
        int var5;
        if (null != var3) {
            var4 = ((Object[]) Array.newInstance(var3, Array.getLength(arg0)));
            var5 = 0;
        } else {
            throw new IllegalArgumentException("Not a primitive type");
        }
        while (var5 < var4.length) {
            var4[var5] = Array.get(arg0, var5);
            ++var5;
            continue;
        }
        return var4;
    }

  public static Object unwrap(Object[] arg0) {
        Class var1 = arg0.getClass();
        Class var2 = var1.getComponentType();
        Class var3 = ((Class) WRAPPER_TO_PRIMITIVE.get(var2));
        Object var4;
        int var5;
        if (null != var3) {
            var4 = Array.newInstance(var3, arg0.length);
            var5 = 0;
        } else {
            throw new IllegalArgumentException("Not a wrapper type");
        }
        while (var5 < arg0.length) {
            Array.set(var4, var5, arg0[var5]);
            ++var5;
            continue;
        }
        return var4;
    }

  public static List listify(Object[] arg0) {
        return Arrays.asList(arg0);
    }

  public static List listify(Object arg0) {
        if (arg0 instanceof Object[]) {
            return listify(((Object[]) arg0));
        }
        ArrayList var1;
        int var2;
        if (arg0.getClass().isArray()) {
            var1 = new ArrayList(Array.getLength(arg0));
            var2 = 0;
        } else {
            throw new IllegalArgumentException("Not an array");
        }
        while (var2 < Array.getLength(arg0)) {
            var1.add(Array.get(arg0, var2));
            ++var2;
            continue;
        }
        return var1;
    }

  public static Object[] delist(List arg0, Class arg1) {
        return arg0.toArray(((Object[]) Array.newInstance(arg1, 0)));
    }

  public static Object delistprimitive(List arg0, Class arg1) {
        Object var2 = Array.newInstance(arg1, arg0.size());
        int var3 = 0;
        while (var3 < arg0.size()) {
            Array.set(var2, var3, arg0.get(var3));
            ++var3;
            continue;
        }
        return var2;
    }

  public static Object convert(Object arg0, Class arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: ldc  #25 // java.util.List
        //      2: aload_1
        //      3: invokevirtual  #47 // java.lang.Object.equals:(Ljava/lang/Object;)Z
        //      6: ifeq  18 (offset +12)
        //      9: aload_0
        //     10: instanceof  #25 // java.util.List
        //     13: ifeq  18 (offset +5)
        //     16: aload_0
        //     17: areturn
        //     18: ldc  #25 // java.util.List
        //     20: aload_1
        //     21: invokevirtual  #47 // java.lang.Object.equals:(Ljava/lang/Object;)Z
        //     24: ifeq  42 (offset +18)
        //     27: aload_0
        //     28: invokevirtual  #48 // java.lang.Object.getClass:()Ljava/lang/Class;
        //     31: invokevirtual  #42 // java.lang.Class.isArray:()Z
        //     34: ifeq  42 (offset +8)
        //     37: aload_0
        //     38: invokestatic  #61 // org.freedesktop.dbus.ArrayFrob.listify:(Ljava/lang/Object;)Ljava/util/List;
        //     41: areturn
        //     42: aload_0
        //     43: invokevirtual  #48 // java.lang.Object.getClass:()Ljava/lang/Class;
        //     46: invokevirtual  #42 // java.lang.Class.isArray:()Z
        //     49: ifeq  78 (offset +29)
        //     52: aload_1
        //     53: invokevirtual  #42 // java.lang.Class.isArray:()Z
        //     56: ifeq  78 (offset +22)
        //     59: aload_0
        //     60: invokevirtual  #48 // java.lang.Object.getClass:()Ljava/lang/Class;
        //     63: invokevirtual  #41 // java.lang.Class.getComponentType:()Ljava/lang/Class;
        //     66: aload_1
        //     67: invokevirtual  #41 // java.lang.Class.getComponentType:()Ljava/lang/Class;
        //     70: invokevirtual  #47 // java.lang.Object.equals:(Ljava/lang/Object;)Z
        //     73: ifeq  78 (offset +5)
        //     76: aload_0
        //     77: areturn
        //     78: aload_0
        //     79: invokevirtual  #48 // java.lang.Object.getClass:()Ljava/lang/Class;
        //     82: invokevirtual  #42 // java.lang.Class.isArray:()Z
        //     85: ifeq  113 (offset +28)
        //     88: aload_1
        //     89: invokevirtual  #42 // java.lang.Class.isArray:()Z
        //     92: ifeq  113 (offset +21)
        //     95: aload_0
        //     96: invokevirtual  #48 // java.lang.Object.getClass:()Ljava/lang/Class;
        //     99: invokevirtual  #41 // java.lang.Class.getComponentType:()Ljava/lang/Class;
        //    102: invokevirtual  #43 // java.lang.Class.isPrimitive:()Z
        //    105: ifeq  113 (offset +8)
        //    108: aload_0
        //    109: invokestatic  #65 // org.freedesktop.dbus.ArrayFrob.wrap:(Ljava/lang/Object;)[Ljava/lang/Object;
        //    112: areturn
        //    113: aload_0
        //    114: invokevirtual  #48 // java.lang.Object.getClass:()Ljava/lang/Class;
        //    117: invokevirtual  #42 // java.lang.Class.isArray:()Z
        //    120: ifeq  148 (offset +28)
        //    123: aload_1
        //    124: invokevirtual  #42 // java.lang.Class.isArray:()Z
        //    127: ifeq  148 (offset +21)
        //    130: aload_1
        //    131: invokevirtual  #41 // java.lang.Class.getComponentType:()Ljava/lang/Class;
        //    134: invokevirtual  #43 // java.lang.Class.isPrimitive:()Z
        //    137: ifeq  148 (offset +11)
        //    140: aload_0
        //    141: checkcast  #6 // [Ljava.lang.Object;
        //    144: invokestatic  #64 // org.freedesktop.dbus.ArrayFrob.unwrap:([Ljava/lang/Object;)Ljava/lang/Object;
        //    147: areturn
        //    148: aload_0
        //    149: instanceof  #25 // java.util.List
        //    152: ifeq  184 (offset +32)
        //    155: aload_1
        //    156: invokevirtual  #42 // java.lang.Class.isArray:()Z
        //    159: ifeq  184 (offset +25)
        //    162: aload_1
        //    163: invokevirtual  #41 // java.lang.Class.getComponentType:()Ljava/lang/Class;
        //    166: invokevirtual  #43 // java.lang.Class.isPrimitive:()Z
        //    169: ifeq  184 (offset +15)
        //    172: aload_0
        //    173: checkcast  #25 // java.util.List
        //    176: aload_1
        //    177: invokevirtual  #41 // java.lang.Class.getComponentType:()Ljava/lang/Class;
        //    180: invokestatic  #60 // org.freedesktop.dbus.ArrayFrob.delistprimitive:(Ljava/util/List;Ljava/lang/Class;)Ljava/lang/Object;
        //    183: areturn
        //    184: aload_0
        //    185: instanceof  #25 // java.util.List
        //    188: ifeq  210 (offset +22)
        //    191: aload_1
        //    192: invokevirtual  #42 // java.lang.Class.isArray:()Z
        //    195: ifeq  210 (offset +15)
        //    198: aload_0
        //    199: checkcast  #25 // java.util.List
        //    202: aload_1
        //    203: invokevirtual  #41 // java.lang.Class.getComponentType:()Ljava/lang/Class;
        //    206: invokestatic  #59 // org.freedesktop.dbus.ArrayFrob.delist:(Ljava/util/List;Ljava/lang/Class;)[Ljava/lang/Object;
        //    209: areturn
        //    210: aload_0
        //    211: invokevirtual  #48 // java.lang.Object.getClass:()Ljava/lang/Class;
        //    214: invokevirtual  #42 // java.lang.Class.isArray:()Z
        //    217: ifeq  239 (offset +22)
        //    220: aload_1
        //    221: invokevirtual  #42 // java.lang.Class.isArray:()Z
        //    224: ifeq  239 (offset +15)
        //    227: aload_0
        //    228: checkcast  #6 // [Ljava.lang.Object;
        //    231: aload_1
        //    232: invokevirtual  #41 // java.lang.Class.getComponentType:()Ljava/lang/Class;
        //    235: invokestatic  #63 // org.freedesktop.dbus.ArrayFrob.type:([Ljava/lang/Object;Ljava/lang/Class;)[Ljava/lang/Object;
        //    238: areturn
        //    239: goto  265 (offset +26)
        //    242: astore_2
        //    243: ldc  #28 // org.freedesktop.dbus.ArrayFrob
        //    245: invokestatic  #66 // org.slf4j.LoggerFactory.getLogger:(Ljava/lang/Class;)Lorg/slf4j/Logger;
        //    248: ldc  #1 // 'Cannot convert object.'
        //    250: aload_2
        //    251: invokeinterface  #73 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Throwable;)V, count 3
        //    256: new  #14 // java.lang.IllegalArgumentException
        //    259: dup
        //    260: aload_2
        //    261: invokespecial  #45 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/Throwable;)V
        //    264: athrow
        //    265: new  #14 // java.lang.IllegalArgumentException
        //    268: dup
        //    269: ldc  #2 // 'Not An Expected Convertion type from %s to %s'
        //    271: iconst_2
        //    272: anewarray  #17 // java.lang.Object
        //    275: dup
        //    276: iconst_0
        //    277: aload_0
        //    278: invokevirtual  #48 // java.lang.Object.getClass:()Ljava/lang/Class;
        //    281: aastore
        //    282: dup
        //    283: iconst_1
        //    284: aload_1
        //    285: aastore
        //    286: invokestatic  #49 // java.lang.String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    289: invokespecial  #44 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    292: athrow
        //       Exception table:
        //         from 0 to 17 target 242 type java.lang.Exception
        //         from 18 to 41 target 242 type java.lang.Exception
        //         from 42 to 77 target 242 type java.lang.Exception
        //         from 78 to 112 target 242 type java.lang.Exception
        //         from 113 to 147 target 242 type java.lang.Exception
        //         from 148 to 183 target 242 type java.lang.Exception
        //         from 184 to 209 target 242 type java.lang.Exception
        //         from 210 to 238 target 242 type java.lang.Exception
    }

  public static Object[] type(Object[] arg0, Class arg1) {
        Object[] var2 = ((Object[]) Array.newInstance(arg1, arg0.length));
        System.arraycopy(arg0, 0, var2, 0, var2.length);
        return var2;
    }

}