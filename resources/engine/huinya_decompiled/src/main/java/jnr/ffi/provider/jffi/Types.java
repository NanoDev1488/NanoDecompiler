// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.Types
package jnr.ffi.provider.jffi;

import java.lang.annotation.Annotation;
import java.lang.ref.Reference;
import java.nio.Buffer;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import jnr.ffi.Address;
import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.Type;
import jnr.ffi.annotations.TypeDefinition;

class Types {

    // ---- поля ----
  private static Reference typeCacheReference;

   Types() { // было: <init>
        super();
    }

  static Type getType(Runtime arg0, Class arg1, Collection arg2) {
        Map __stk1;
        Map __stk2;
        Type __stk3;
        Type __stk4;
        __stk1 = typeCacheReference == null ? null : ((Map) typeCacheReference.get());
        Map var3 = __stk1;
        __stk2 = var3 == null ? null : ((Map) var3.get(arg1));
        Map var4 = __stk2;
        __stk3 = var4 == null ? null : ((Type) var4.get(arg2));
        Type var5 = __stk3;
        __stk4 = var5 == null ? lookupAndCacheType(arg0, arg1, arg2) : var5;
        return ((Type) __stk4);
    }

  private static synchronized Type lookupAndCacheType(Runtime arg0, Class arg1, Collection arg2) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: getstatic  #49 // jnr.ffi.provider.jffi.Types.typeCacheReference:Ljava/lang/ref/Reference;
        //      3: ifnull  18 (offset +15)
        //      6: getstatic  #49 // jnr.ffi.provider.jffi.Types.typeCacheReference:Ljava/lang/ref/Reference;
        //      9: invokevirtual  #59 // java.lang.ref.Reference.get:()Ljava/lang/Object;
        //     12: checkcast  #24 // java.util.Map
        //     15: goto  19 (offset +4)
        //     18: aconst_null
        //     19: astore_3
        //     20: aload_3
        //     21: ifnull  37 (offset +16)
        //     24: aload_3
        //     25: aload_1
        //     26: invokeinterface  #73 // java.util.Map.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //     31: checkcast  #24 // java.util.Map
        //     34: goto  38 (offset +4)
        //     37: aconst_null
        //     38: astore  4
        //     40: aload  4
        //     42: ifnull  59 (offset +17)
        //     45: aload  4
        //     47: aload_2
        //     48: invokeinterface  #73 // java.util.Map.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //     53: checkcast  #29 // jnr.ffi.Type
        //     56: goto  60 (offset +4)
        //     59: aconst_null
        //     60: astore  5
        //     62: aload  5
        //     64: ifnull  70 (offset +6)
        //     67: aload  5
        //     69: areturn
        //     70: new  #21 // java.util.HashMap
        //     73: dup
        //     74: aload_3
        //     75: ifnull  82 (offset +7)
        //     78: aload_3
        //     79: goto  85 (offset +6)
        //     82: getstatic  #40 // java.util.Collections.EMPTY_MAP:Ljava/util/Map;
        //     85: invokespecial  #62 // java.util.HashMap.<init>:(Ljava/util/Map;)V
        //     88: astore_3
        //     89: new  #21 // java.util.HashMap
        //     92: dup
        //     93: aload  4
        //     95: ifnull  103 (offset +8)
        //     98: aload  4
        //    100: goto  106 (offset +6)
        //    103: getstatic  #40 // java.util.Collections.EMPTY_MAP:Ljava/util/Map;
        //    106: invokespecial  #62 // java.util.HashMap.<init>:(Ljava/util/Map;)V
        //    109: astore  4
        //    111: aload  4
        //    113: aload_2
        //    114: aload_0
        //    115: aload_1
        //    116: aload_2
        //    117: invokestatic  #68 // jnr.ffi.provider.jffi.Types.lookupType:(Ljnr/ffi/Runtime;Ljava/lang/Class;Ljava/util/Collection;)Ljnr/ffi/Type;
        //    120: dup
        //    121: astore  5
        //    123: invokeinterface  #74 // java.util.Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;, count 3
        //    128: pop
        //    129: aload_3
        //    130: aload_1
        //    131: aload  4
        //    133: invokestatic  #61 // java.util.Collections.unmodifiableMap:(Ljava/util/Map;)Ljava/util/Map;
        //    136: invokeinterface  #74 // java.util.Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;, count 3
        //    141: pop
        //    142: new  #17 // java.lang.ref.SoftReference
        //    145: dup
        //    146: new  #22 // java.util.IdentityHashMap
        //    149: dup
        //    150: aload_3
        //    151: invokespecial  #63 // java.util.IdentityHashMap.<init>:(Ljava/util/Map;)V
        //    154: invokestatic  #61 // java.util.Collections.unmodifiableMap:(Ljava/util/Map;)Ljava/util/Map;
        //    157: invokespecial  #60 // java.lang.ref.SoftReference.<init>:(Ljava/lang/Object;)V
        //    160: putstatic  #49 // jnr.ffi.provider.jffi.Types.typeCacheReference:Ljava/lang/ref/Reference;
        //    163: aload  5
        //    165: areturn
    }

  private static Type lookupAliasedType(Runtime arg0, Collection arg1) {
        Iterator var2 = arg1.iterator();
        TypeDefinition var4;
        while (true) {
            if (!var2.hasNext()) {
                return null;
            }
            Annotation var3 = ((Annotation) var2.next());
            var4 = ((TypeDefinition) var3.annotationType().getAnnotation(TypeDefinition.class));
            if (var4 != null) {
                break;
            }
            continue;
        }
        return arg0.findType(var4.alias());
    }

  static Type lookupType(Runtime arg0, Class arg1, Collection arg2) {
        Type __stk1;
        __stk1 = !arg1.isArray() ? lookupAliasedType(arg0, arg2) : null;
        Object var3 = __stk1;
        if (var3 == null) {
            if (Void.class.isAssignableFrom(arg1)) {
                return arg0.findType(NativeType.VOID);
            } else {
                if (Void.TYPE != arg1) {
                    if (Boolean.class.isAssignableFrom(arg1)) {
                        return arg0.findType(NativeType.SINT);
                    } else {
                        if (Boolean.TYPE != arg1) {
                            if (Byte.class.isAssignableFrom(arg1)) {
                                return arg0.findType(NativeType.SCHAR);
                            } else {
                                if (Byte.TYPE != arg1) {
                                    if (Short.class.isAssignableFrom(arg1)) {
                                        return arg0.findType(NativeType.SSHORT);
                                    } else {
                                        if (Short.TYPE != arg1) {
                                            if (Integer.class.isAssignableFrom(arg1)) {
                                                return arg0.findType(NativeType.SINT);
                                            } else {
                                                if (Integer.TYPE != arg1) {
                                                    if (Long.class.isAssignableFrom(arg1)) {
                                                        return arg0.findType(NativeType.SLONG);
                                                    } else {
                                                        if (Long.TYPE != arg1) {
                                                            if (Float.class.isAssignableFrom(arg1)) {
                                                                return arg0.findType(NativeType.FLOAT);
                                                            } else {
                                                                if (Float.TYPE != arg1) {
                                                                    if (Double.class.isAssignableFrom(arg1)) {
                                                                        return arg0.findType(NativeType.DOUBLE);
                                                                    } else {
                                                                        if (Double.TYPE != arg1) {
                                                                            if (!Pointer.class.isAssignableFrom(arg1)) {
                                                                                if (!Address.class.isAssignableFrom(arg1)) {
                                                                                    if (!Buffer.class.isAssignableFrom(arg1)) {
                                                                                        if (!CharSequence.class.isAssignableFrom(arg1)) {
                                                                                            if (!arg1.isArray()) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("unsupported type: ").append(arg1).toString());
                                                                                            } else {
                                                                                                return arg0.findType(NativeType.ADDRESS);
                                                                                            }
                                                                                        } else {
                                                                                            return arg0.findType(NativeType.ADDRESS);
                                                                                        }
                                                                                    } else {
                                                                                        return arg0.findType(NativeType.ADDRESS);
                                                                                    }
                                                                                } else {
                                                                                    return arg0.findType(NativeType.ADDRESS);
                                                                                }
                                                                            } else {
                                                                                return arg0.findType(NativeType.ADDRESS);
                                                                            }
                                                                        } else {
                                                                            return arg0.findType(NativeType.DOUBLE);
                                                                        }
                                                                    }
                                                                } else {
                                                                    return arg0.findType(NativeType.FLOAT);
                                                                }
                                                            }
                                                        } else {
                                                            return arg0.findType(NativeType.SLONG);
                                                        }
                                                    }
                                                } else {
                                                    return arg0.findType(NativeType.SINT);
                                                }
                                            }
                                        } else {
                                            return arg0.findType(NativeType.SSHORT);
                                        }
                                    }
                                } else {
                                    return arg0.findType(NativeType.SCHAR);
                                }
                            }
                        } else {
                            return arg0.findType(NativeType.SINT);
                        }
                    }
                } else {
                    return arg0.findType(NativeType.VOID);
                }
            }
        } else {
            return ((Type) var3);
        }
    }

}