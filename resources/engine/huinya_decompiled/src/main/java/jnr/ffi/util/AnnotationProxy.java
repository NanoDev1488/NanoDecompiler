// исходный (обфусцированный) внутренний класс: jnr.ffi.util.AnnotationProxy
package jnr.ffi.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.security.AccessController;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import jnr.ffi.util.AnnotationProperty;
import jnr.ffi.util.AnnotationProxy_Anon1;

public final class AnnotationProxy implements Annotation, InvocationHandler {

    // ---- поля ----
  private static final int MEMBER_NAME_MULTIPLICATOR = 127;
  private final Class annotationType;
  private final Map properties;
  private final Annotation proxedAnnotation;

  public static AnnotationProxy newProxy(Class arg0) {
        if (arg0 != null) {
            return new AnnotationProxy(arg0);
        } else {
            throw new IllegalArgumentException("Parameter 'annotationType' must be not null");
        }
    }

  private static AnnotationProxy getAnnotationProxy(Object arg0) {
        if (!Proxy.isProxyClass(arg0.getClass())) {
            return null;
        } else {
            InvocationHandler var1 = Proxy.getInvocationHandler(arg0);
            if (!(var1 instanceof AnnotationProxy)) {
                return null;
            } else {
                return ((AnnotationProxy) var1);
            }
        }
    }

  private static Method[] getDeclaredMethods(Class arg0) {
        return ((Method[]) AccessController.doPrivileged(new AnnotationProxy_Anon1(arg0)));
    }

  private AnnotationProxy(Class arg0) { // было: <init>
        super();
        properties = new LinkedHashMap();
        annotationType = arg0;
        Method[] var5 = getDeclaredMethods(arg0);
        int var6 = var5.length;
        int var7 = 0;
        while (var7 < var6) {
            Object var8 = var5[var7];
            String var2 = var8.getName();
            Class var3 = var8.getReturnType();
            Object var4 = var8.getDefaultValue();
            AnnotationProperty var9 = new AnnotationProperty(var2, var3);
            var9.setValue(var4);
            properties.put(var2, var9);
            ++var7;
            continue;
        }
        proxedAnnotation = ((Annotation) arg0.cast(Proxy.newProxyInstance(arg0.getClassLoader(), new Class[]{arg0}, this)));
    }

  public void setProperty(String arg0, Object arg1) {
        if (arg0 != null) {
            if (arg1 != null) {
                if (properties.containsKey(arg0)) {
                    (((AnnotationProperty) properties.get(arg0))).setValue(arg1);
                    return;
                } else {
                    throw new IllegalArgumentException(new StringBuilder().append("Annotation '").append(annotationType.getName()).append("' does not contain a property named '").append(arg0).append("'").toString());
                }
            } else {
                throw new IllegalArgumentException("Parameter 'value' must be not null");
            }
        } else {
            throw new IllegalArgumentException("Parameter 'name' must be not null");
        }
    }

  public Object getProperty(String arg0) {
        if (arg0 != null) {
            return (((AnnotationProperty) properties.get(arg0))).getValue();
        } else {
            throw new IllegalArgumentException("Parameter 'name' must be not null");
        }
    }

  public Object invoke(Object arg0, Method arg1, Object[] arg2) {
        String var4 = arg1.getName();
        if (!properties.containsKey(var4)) {
            return arg1.invoke(this, arg2);
        } else {
            return (((AnnotationProperty) properties.get(var4))).getValue();
        }
    }

  public Class annotationType() {
        return annotationType;
    }

  public Annotation getProxedAnnotation() {
        return proxedAnnotation;
    }

  public boolean equals(Object arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: aload_1
        //      2: if_acmpne  7 (offset +5)
        //      5: iconst_1
        //      6: ireturn
        //      7: aload_1
        //      8: ifnonnull  13 (offset +5)
        //     11: iconst_0
        //     12: ireturn
        //     13: aload_0
        //     14: getfield  #32 // jnr.ffi.util.AnnotationProxy.annotationType:Ljava/lang/Class;
        //     17: aload_1
        //     18: invokevirtual  #39 // java.lang.Class.isInstance:(Ljava/lang/Object;)Z
        //     21: ifne  26 (offset +5)
        //     24: iconst_0
        //     25: ireturn
        //     26: aload_0
        //     27: invokevirtual  #65 // jnr.ffi.util.AnnotationProxy.annotationType:()Ljava/lang/Class;
        //     30: invokestatic  #67 // jnr.ffi.util.AnnotationProxy.getDeclaredMethods:(Ljava/lang/Class;)[Ljava/lang/reflect/Method;
        //     33: astore  4
        //     35: aload  4
        //     37: arraylength
        //     38: istore  5
        //     40: iconst_0
        //     41: istore  6
        //     43: iload  6
        //     45: iload  5
        //     47: if_icmpge  187 (offset +140)
        //     50: aload  4
        //     52: iload  6
        //     54: aaload
        //     55: astore  7
        //     57: aload  7
        //     59: invokevirtual  #50 // java.lang.reflect.Method.getName:()Ljava/lang/String;
        //     62: astore_2
        //     63: aload_0
        //     64: getfield  #33 // jnr.ffi.util.AnnotationProxy.properties:Ljava/util/Map;
        //     67: aload_2
        //     68: invokeinterface  #72 // java.util.Map.containsKey:(Ljava/lang/Object;)Z, count 2
        //     73: ifne  78 (offset +5)
        //     76: iconst_0
        //     77: ireturn
        //     78: aload_0
        //     79: getfield  #33 // jnr.ffi.util.AnnotationProxy.properties:Ljava/util/Map;
        //     82: aload_2
        //     83: invokeinterface  #74 // java.util.Map.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //     88: checkcast  #29 // jnr.ffi.util.AnnotationProperty
        //     91: astore_3
        //     92: new  #29 // jnr.ffi.util.AnnotationProperty
        //     95: dup
        //     96: aload_2
        //     97: aload  7
        //     99: invokevirtual  #51 // java.lang.reflect.Method.getReturnType:()Ljava/lang/Class;
        //    102: invokespecial  #58 // jnr.ffi.util.AnnotationProperty.<init>:(Ljava/lang/String;Ljava/lang/Class;)V
        //    105: astore  8
        //    107: aload_1
        //    108: invokestatic  #66 // jnr.ffi.util.AnnotationProxy.getAnnotationProxy:(Ljava/lang/Object;)Ljnr/ffi/util/AnnotationProxy;
        //    111: astore  9
        //    113: aload  9
        //    115: ifnull  132 (offset +17)
        //    118: aload  8
        //    120: aload  9
        //    122: aload_2
        //    123: invokevirtual  #68 // jnr.ffi.util.AnnotationProxy.getProperty:(Ljava/lang/String;)Ljava/lang/Object;
        //    126: invokevirtual  #62 // jnr.ffi.util.AnnotationProperty.setValue:(Ljava/lang/Object;)V
        //    129: goto  170 (offset +41)
        //    132: aload  8
        //    134: aload  7
        //    136: aload_1
        //    137: iconst_0
        //    138: anewarray  #15 // java.lang.Object
        //    141: invokevirtual  #52 // java.lang.reflect.Method.invoke:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //    144: invokevirtual  #62 // jnr.ffi.util.AnnotationProperty.setValue:(Ljava/lang/Object;)V
        //    147: goto  170 (offset +23)
        //    150: astore  10
        //    152: iconst_0
        //    153: ireturn
        //    154: astore  10
        //    156: new  #11 // java.lang.AssertionError
        //    159: dup
        //    160: aload  10
        //    162: invokespecial  #35 // java.lang.AssertionError.<init>:(Ljava/lang/Object;)V
        //    165: athrow
        //    166: astore  10
        //    168: iconst_0
        //    169: ireturn
        //    170: aload_3
        //    171: aload  8
        //    173: invokevirtual  #59 // jnr.ffi.util.AnnotationProperty.equals:(Ljava/lang/Object;)Z
        //    176: ifne  181 (offset +5)
        //    179: iconst_0
        //    180: ireturn
        //    181: iinc  6, 1
        //    184: goto  43 (offset -141)
        //    187: iconst_1
        //    188: ireturn
        //       Exception table:
        //         from 132 to 147 target 150 type java.lang.IllegalArgumentException
        //         from 132 to 147 target 154 type java.lang.IllegalAccessException
        //         from 132 to 147 target 166 type java.lang.reflect.InvocationTargetException
    }

  public int hashCode() {
        int var1 = 0;
        Iterator var2 = properties.entrySet().iterator();
        while (var2.hasNext()) {
            Entry var3 = ((Entry) var2.next());
            var1 = var1 + (127 * (((String) var3.getKey())).hashCode() ^ (((AnnotationProperty) var3.getValue())).getValueHashCode());
            continue;
        }
        return var1;
    }

  public String toString() {
        StringBuilder var1 = new StringBuilder("@").append(annotationType.getName()).append('(');
        int var2 = 0;
        Iterator var3 = properties.entrySet().iterator();
        while (var3.hasNext()) {
            Entry var4 = ((Entry) var3.next());
            if (var2 > 0) {
                var1.append(", ");
            }
            var1.append(((String) var4.getKey())).append('=').append((((AnnotationProperty) var4.getValue())).valueToString());
            ++var2;
            continue;
        }
        return var1.append(')').toString();
    }

}