// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.messages.DBusSignal.CachedConstructor
package org.freedesktop.dbus.messages;

import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class DBusSignal_CachedConstructor {

    // ---- поля ----
  private final Constructor constructor;
  private final List parameterTypes;
  private final Type[] types;

   DBusSignal_CachedConstructor(Constructor arg0) { // было: <init>
        super();
        constructor = arg0;
        parameterTypes = ((List) Arrays.stream(constructor.getParameterTypes()).skip(1L).map(lp0 -> lambda$new$0(((Class) lp0))).collect(Collectors.toList()));
        types = createTypes(constructor);
    }

  public boolean matchesParameters(List arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #23 // org.freedesktop.dbus.messages.DBusSignal$CachedConstructor.parameterTypes:Ljava/util/List;
        //      4: ifnull  11 (offset +7)
        //      7: aload_1
        //      8: ifnonnull  13 (offset +5)
        //     11: iconst_0
        //     12: ireturn
        //     13: aload_0
        //     14: getfield  #23 // org.freedesktop.dbus.messages.DBusSignal$CachedConstructor.parameterTypes:Ljava/util/List;
        //     17: invokeinterface  #42 // java.util.List.size:()I, count 1
        //     22: aload_1
        //     23: invokeinterface  #42 // java.util.List.size:()I, count 1
        //     28: if_icmpeq  33 (offset +5)
        //     31: iconst_0
        //     32: ireturn
        //     33: iconst_0
        //     34: istore_2
        //     35: iload_2
        //     36: aload_0
        //     37: getfield  #23 // org.freedesktop.dbus.messages.DBusSignal$CachedConstructor.parameterTypes:Ljava/util/List;
        //     40: invokeinterface  #42 // java.util.List.size:()I, count 1
        //     45: if_icmpge  171 (offset +126)
        //     48: aload_0
        //     49: getfield  #23 // org.freedesktop.dbus.messages.DBusSignal$CachedConstructor.parameterTypes:Ljava/util/List;
        //     52: iload_2
        //     53: invokeinterface  #41 // java.util.List.get:(I)Ljava/lang/Object;, count 2
        //     58: checkcast  #2 // java.lang.Class
        //     61: astore_3
        //     62: ldc  #3 // java.lang.Enum
        //     64: aload_3
        //     65: invokevirtual  #25 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //     68: ifeq  89 (offset +21)
        //     71: ldc  #5 // java.lang.String
        //     73: aload_1
        //     74: iload_2
        //     75: invokeinterface  #41 // java.util.List.get:(I)Ljava/lang/Object;, count 2
        //     80: invokevirtual  #28 // java.lang.Object.equals:(Ljava/lang/Object;)Z
        //     83: ifeq  89 (offset +6)
        //     86: goto  165 (offset +79)
        //     89: ldc  #19 // org.freedesktop.dbus.interfaces.DBusInterface
        //     91: aload_3
        //     92: invokevirtual  #25 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //     95: ifeq  116 (offset +21)
        //     98: ldc  #17 // org.freedesktop.dbus.ObjectPath
        //    100: aload_1
        //    101: iload_2
        //    102: invokeinterface  #41 // java.util.List.get:(I)Ljava/lang/Object;, count 2
        //    107: invokevirtual  #28 // java.lang.Object.equals:(Ljava/lang/Object;)Z
        //    110: ifeq  116 (offset +6)
        //    113: goto  165 (offset +52)
        //    116: ldc  #18 // org.freedesktop.dbus.Struct
        //    118: aload_3
        //    119: invokevirtual  #25 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    122: ifeq  143 (offset +21)
        //    125: ldc  #1 // [Ljava.lang.Object;
        //    127: aload_1
        //    128: iload_2
        //    129: invokeinterface  #41 // java.util.List.get:(I)Ljava/lang/Object;, count 2
        //    134: invokevirtual  #28 // java.lang.Object.equals:(Ljava/lang/Object;)Z
        //    137: ifeq  143 (offset +6)
        //    140: goto  165 (offset +25)
        //    143: aload_3
        //    144: aload_1
        //    145: iload_2
        //    146: invokeinterface  #41 // java.util.List.get:(I)Ljava/lang/Object;, count 2
        //    151: checkcast  #2 // java.lang.Class
        //    154: invokevirtual  #25 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    157: ifeq  163 (offset +6)
        //    160: goto  165 (offset +5)
        //    163: iconst_0
        //    164: ireturn
        //    165: iinc  2, 1
        //    168: goto  35 (offset -133)
        //    171: iconst_1
        //    172: ireturn
    }

  private static Type[] createTypes(Constructor arg0) {
        Type[] var1 = arg0.getGenericParameterTypes();
        Type[] var2 = new Type[var1.length - 1];
        int var3 = 1;
        while (var3 <= var2.length) {
            if (!(var1[var3] instanceof TypeVariable)) {
                var2[var3 - 1] = var1[var3];
            } else {
                var2[var3 - 1] = (((TypeVariable) var1[var3])).getBounds()[0];
            }
            ++var3;
            continue;
        }
        return var2;
    }

  private static Class wrap(Class arg0) {
        return MethodType.methodType(arg0).wrap().returnType();
    }

  private static Class lambda$new$0(Class arg0) {
        if (!arg0.isPrimitive()) {
            return arg0;
        } else {
            return wrap(arg0);
        }
    }

}