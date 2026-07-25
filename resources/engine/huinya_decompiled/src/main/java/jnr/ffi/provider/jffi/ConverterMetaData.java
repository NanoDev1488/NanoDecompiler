// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.ConverterMetaData
package jnr.ffi.provider.jffi;

import java.lang.ref.Reference;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import jnr.ffi.mapper.FromNativeConverter;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter;
import jnr.ffi.util.Annotations;

class ConverterMetaData {

    // ---- поля ----
  private static volatile Reference cacheReference;
  final Collection classAnnotations;
  final Collection toNativeMethodAnnotations;
  final Collection fromNativeMethodAnnotations;
  final Collection nativeTypeMethodAnnotations;
  final Collection toNativeAnnotations;
  final Collection fromNativeAnnotations;

   ConverterMetaData(Class arg0, Class arg1) { // было: <init>
        super();
        classAnnotations = Annotations.sortedAnnotationCollection(arg0.getAnnotations());
        nativeTypeMethodAnnotations = getConverterMethodAnnotations(arg0, "nativeType", new Class[0]);
        fromNativeMethodAnnotations = getConverterMethodAnnotations(arg0, "fromNative", new Class[]{arg1, FromNativeContext.class});
        Class[] __obj2 = new Class[2];
        __obj2[0] = arg1;
        __obj2[1] = ToNativeContext.class;
        toNativeMethodAnnotations = getConverterMethodAnnotations(arg0, "toNative", __obj2);
        Collection[] __obj3 = new Collection[3];
        __obj3[0] = classAnnotations;
        __obj3[1] = toNativeMethodAnnotations;
        __obj3[2] = nativeTypeMethodAnnotations;
        toNativeAnnotations = Annotations.mergeAnnotations(__obj3);
        Collection[] __obj4 = new Collection[3];
        __obj4[0] = classAnnotations;
        __obj4[1] = fromNativeMethodAnnotations;
        __obj4[2] = nativeTypeMethodAnnotations;
        fromNativeAnnotations = Annotations.mergeAnnotations(__obj4);
    }

  private static Collection getToNativeMethodAnnotations(Class arg0, Class arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: ldc  #3 // 'toNative'
        //      3: iconst_2
        //      4: anewarray  #4 // java.lang.Class
        //      7: dup
        //      8: iconst_0
        //      9: ldc  #6 // java.lang.Object
        //     11: aastore
        //     12: dup
        //     13: iconst_1
        //     14: ldc  #21 // jnr.ffi.mapper.ToNativeContext
        //     16: aastore
        //     17: invokevirtual  #35 // java.lang.Class.getMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //     20: astore_2
        //     21: aload_0
        //     22: invokevirtual  #36 // java.lang.Class.getMethods:()[Ljava/lang/reflect/Method;
        //     25: astore_3
        //     26: aload_3
        //     27: arraylength
        //     28: istore  4
        //     30: iconst_0
        //     31: istore  5
        //     33: iload  5
        //     35: iload  4
        //     37: if_icmpge  131 (offset +94)
        //     40: aload_3
        //     41: iload  5
        //     43: aaload
        //     44: astore  6
        //     46: aload  6
        //     48: invokevirtual  #45 // java.lang.reflect.Method.getName:()Ljava/lang/String;
        //     51: ldc  #3 // 'toNative'
        //     53: invokevirtual  #41 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //     56: ifne  62 (offset +6)
        //     59: goto  125 (offset +66)
        //     62: aload_1
        //     63: aload  6
        //     65: invokevirtual  #47 // java.lang.reflect.Method.getReturnType:()Ljava/lang/Class;
        //     68: invokevirtual  #37 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //     71: ifne  77 (offset +6)
        //     74: goto  125 (offset +51)
        //     77: aload  6
        //     79: invokevirtual  #46 // java.lang.reflect.Method.getParameterTypes:()[Ljava/lang/Class;
        //     82: astore  7
        //     84: aload  7
        //     86: arraylength
        //     87: iconst_2
        //     88: if_icmpne  125 (offset +37)
        //     91: aload  7
        //     93: iconst_1
        //     94: aaload
        //     95: ldc  #21 // jnr.ffi.mapper.ToNativeContext
        //     97: invokevirtual  #37 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    100: ifne  106 (offset +6)
        //    103: goto  125 (offset +22)
        //    106: aload  6
        //    108: invokevirtual  #44 // java.lang.reflect.Method.getAnnotations:()[Ljava/lang/annotation/Annotation;
        //    111: invokestatic  #56 // jnr.ffi.util.Annotations.sortedAnnotationCollection:([Ljava/lang/annotation/Annotation;)Ljava/util/Collection;
        //    114: aload_2
        //    115: invokevirtual  #44 // java.lang.reflect.Method.getAnnotations:()[Ljava/lang/annotation/Annotation;
        //    118: invokestatic  #56 // jnr.ffi.util.Annotations.sortedAnnotationCollection:([Ljava/lang/annotation/Annotation;)Ljava/util/Collection;
        //    121: invokestatic  #54 // jnr.ffi.util.Annotations.mergeAnnotations:(Ljava/util/Collection;Ljava/util/Collection;)Ljava/util/Collection;
        //    124: areturn
        //    125: iinc  5, 1
        //    128: goto  33 (offset -95)
        //    131: getstatic  #33 // jnr.ffi.util.Annotations.EMPTY_ANNOTATIONS:Ljava/util/Collection;
        //    134: areturn
        //    135: astore_2
        //    136: getstatic  #33 // jnr.ffi.util.Annotations.EMPTY_ANNOTATIONS:Ljava/util/Collection;
        //    139: areturn
        //    140: astore_2
        //    141: getstatic  #33 // jnr.ffi.util.Annotations.EMPTY_ANNOTATIONS:Ljava/util/Collection;
        //    144: areturn
        //       Exception table:
        //         from 0 to 124 target 135 type java.lang.SecurityException
        //         from 125 to 134 target 135 type java.lang.SecurityException
        //         from 0 to 124 target 140 type java.lang.NoSuchMethodException
        //         from 125 to 134 target 140 type java.lang.NoSuchMethodException
    }

  private static Collection getConverterMethodAnnotations(Class arg0, String arg1, Class[] arg2) {
        Collection __stk1;
        try {
            __stk1 = Annotations.sortedAnnotationCollection(arg0.getMethod(arg1, new Class[0]).getAnnotations());
        } catch (NoSuchMethodException var3) {
            return Annotations.EMPTY_ANNOTATIONS;
        } catch (Throwable e2) {
            Throwable var3 = e2;
            throw new RuntimeException(var3);
        }
    }

  private static ConverterMetaData getMetaData(Class arg0, Class arg1) {
        Map __stk1;
        __stk1 = cacheReference == null ? null : ((Map) cacheReference.get());
        Map var2 = __stk1;
        if (var2 == null) {
            return addMetaData(arg0, arg1);
        } else {
            ConverterMetaData var3 = ((ConverterMetaData) var2.get(arg0));
            if (var3 == null) {
                return addMetaData(arg0, arg1);
            } else {
                return var3;
            }
        }
    }

  private static synchronized ConverterMetaData addMetaData(Class arg0, Class arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: getstatic  #26 // jnr.ffi.provider.jffi.ConverterMetaData.cacheReference:Ljava/lang/ref/Reference;
        //      3: ifnull  18 (offset +15)
        //      6: getstatic  #26 // jnr.ffi.provider.jffi.ConverterMetaData.cacheReference:Ljava/lang/ref/Reference;
        //      9: invokevirtual  #42 // java.lang.ref.Reference.get:()Ljava/lang/Object;
        //     12: checkcast  #18 // java.util.Map
        //     15: goto  19 (offset +4)
        //     18: aconst_null
        //     19: astore_2
        //     20: aload_2
        //     21: ifnull  41 (offset +20)
        //     24: aload_2
        //     25: aload_0
        //     26: invokeinterface  #57 // java.util.Map.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //     31: checkcast  #23 // jnr.ffi.provider.jffi.ConverterMetaData
        //     34: dup
        //     35: astore_3
        //     36: ifnull  41 (offset +5)
        //     39: aload_3
        //     40: areturn
        //     41: new  #16 // java.util.HashMap
        //     44: dup
        //     45: aload_2
        //     46: ifnull  53 (offset +7)
        //     49: aload_2
        //     50: goto  56 (offset +6)
        //     53: getstatic  #25 // java.util.Collections.EMPTY_MAP:Ljava/util/Map;
        //     56: invokespecial  #48 // java.util.HashMap.<init>:(Ljava/util/Map;)V
        //     59: astore  4
        //     61: aload  4
        //     63: aload_0
        //     64: new  #23 // jnr.ffi.provider.jffi.ConverterMetaData
        //     67: dup
        //     68: aload_0
        //     69: aload_1
        //     70: invokespecial  #50 // jnr.ffi.provider.jffi.ConverterMetaData.<init>:(Ljava/lang/Class;Ljava/lang/Class;)V
        //     73: dup
        //     74: astore_3
        //     75: invokeinterface  #58 // java.util.Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;, count 3
        //     80: pop
        //     81: new  #12 // java.lang.ref.SoftReference
        //     84: dup
        //     85: new  #17 // java.util.IdentityHashMap
        //     88: dup
        //     89: aload  4
        //     91: invokespecial  #49 // java.util.IdentityHashMap.<init>:(Ljava/util/Map;)V
        //     94: dup
        //     95: astore_2
        //     96: invokespecial  #43 // java.lang.ref.SoftReference.<init>:(Ljava/lang/Object;)V
        //     99: putstatic  #26 // jnr.ffi.provider.jffi.ConverterMetaData.cacheReference:Ljava/lang/ref/Reference;
        //    102: aload_3
        //    103: areturn
    }

  static Collection getAnnotations(ToNativeConverter arg0) {
        return arg0 == null ? Annotations.EMPTY_ANNOTATIONS : getMetaData(arg0.getClass(), arg0.nativeType()).toNativeAnnotations;
    }

  static Collection getAnnotations(FromNativeConverter arg0) {
        return arg0 == null ? Annotations.EMPTY_ANNOTATIONS : getMetaData(arg0.getClass(), arg0.nativeType()).fromNativeAnnotations;
    }

}