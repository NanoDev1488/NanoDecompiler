// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.InvokerUtil
package jnr.ffi.provider.jffi;

import com.kenai.jffi.CallContext;
import com.kenai.jffi.CallContextCache;
import com.kenai.jffi.Type;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;
import jnr.ffi.CallingConvention;
import jnr.ffi.NativeType;
import jnr.ffi.Runtime;
import jnr.ffi.annotations.StdCall;
import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.mapper.FromNativeConverter;
import jnr.ffi.mapper.FromNativeType;
import jnr.ffi.mapper.SignatureTypeMapper;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter;
import jnr.ffi.mapper.ToNativeType;
import jnr.ffi.provider.ParameterType;
import jnr.ffi.provider.ResultType;
import jnr.ffi.provider.SigType;
import jnr.ffi.provider.jffi.ConverterMetaData;
import jnr.ffi.provider.jffi.Types;
import jnr.ffi.util.Annotations;

final class InvokerUtil {

    // ---- поля ----
  static final Map jffiTypes;

    static {
        EnumMap var0 = new EnumMap(NativeType.class);
        var0.put(NativeType.VOID, Type.VOID);
        var0.put(NativeType.SCHAR, Type.SCHAR);
        var0.put(NativeType.UCHAR, Type.UCHAR);
        var0.put(NativeType.SSHORT, Type.SSHORT);
        var0.put(NativeType.USHORT, Type.USHORT);
        var0.put(NativeType.SINT, Type.SINT);
        var0.put(NativeType.UINT, Type.UINT);
        var0.put(NativeType.SLONG, Type.SLONG);
        var0.put(NativeType.ULONG, Type.ULONG);
        var0.put(NativeType.SLONGLONG, Type.SLONG_LONG);
        var0.put(NativeType.ULONGLONG, Type.ULONG_LONG);
        var0.put(NativeType.FLOAT, Type.FLOAT);
        var0.put(NativeType.DOUBLE, Type.DOUBLE);
        var0.put(NativeType.ADDRESS, Type.POINTER);
        jffiTypes = Collections.unmodifiableMap(var0);
    }

   InvokerUtil() { // было: <init>
        super();
    }

  public static CallingConvention getCallingConvention(Map arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getstatic  #57 // jnr.ffi.LibraryOption.CallingConvention:Ljnr/ffi/LibraryOption;
        //      4: invokeinterface  #116 // java.util.Map.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //      9: astore_1
        //     10: aload_1
        //     11: instanceof  #4 // com.kenai.jffi.CallingConvention
        //     14: ifeq  37 (offset +23)
        //     17: getstatic  #39 // com.kenai.jffi.CallingConvention.DEFAULT:Lcom/kenai/jffi/CallingConvention;
        //     20: aload_1
        //     21: invokevirtual  #77 // com.kenai.jffi.CallingConvention.equals:(Ljava/lang/Object;)Z
        //     24: ifeq  33 (offset +9)
        //     27: getstatic  #55 // jnr.ffi.CallingConvention.DEFAULT:Ljnr/ffi/CallingConvention;
        //     30: goto  36 (offset +6)
        //     33: getstatic  #56 // jnr.ffi.CallingConvention.STDCALL:Ljnr/ffi/CallingConvention;
        //     36: areturn
        //     37: aload_1
        //     38: instanceof  #17 // jnr.ffi.CallingConvention
        //     41: ifeq  91 (offset +50)
        //     44: getstatic  #73 // jnr.ffi.provider.jffi.InvokerUtil$1.$SwitchMap$jnr$ffi$CallingConvention:[I
        //     47: aload_1
        //     48: checkcast  #17 // jnr.ffi.CallingConvention
        //     51: invokevirtual  #92 // jnr.ffi.CallingConvention.ordinal:()I
        //     54: iaload
        //     55: lookupswitch  default->88, 1->80, 2->84
        //     80: getstatic  #55 // jnr.ffi.CallingConvention.DEFAULT:Ljnr/ffi/CallingConvention;
        //     83: areturn
        //     84: getstatic  #56 // jnr.ffi.CallingConvention.STDCALL:Ljnr/ffi/CallingConvention;
        //     87: areturn
        //     88: goto  122 (offset +34)
        //     91: aload_1
        //     92: ifnull  122 (offset +30)
        //     95: new  #7 // java.lang.IllegalArgumentException
        //     98: dup
        //     99: new  #9 // java.lang.StringBuilder
        //    102: dup
        //    103: invokespecial  #82 // java.lang.StringBuilder.<init>:()V
        //    106: ldc  #1 // 'unknown calling convention: '
        //    108: invokevirtual  #84 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    111: aload_1
        //    112: invokevirtual  #83 // java.lang.StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    115: invokevirtual  #85 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    118: invokespecial  #80 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    121: athrow
        //    122: getstatic  #55 // jnr.ffi.CallingConvention.DEFAULT:Ljnr/ffi/CallingConvention;
        //    125: areturn
    }

  public static CallingConvention getCallingConvention(Class arg0, Map arg1) {
        if (!arg0.isAnnotationPresent(StdCall.class)) {
            return getCallingConvention(arg1);
        } else {
            return CallingConvention.STDCALL;
        }
    }

  public static boolean hasAnnotation(Collection arg0, Class arg1) {
        Iterator var2 = arg0.iterator();
        while (true) {
            if (!var2.hasNext()) {
                return false;
            }
            Annotation var3 = ((Annotation) var2.next());
            if (arg1.isInstance(var3)) {
                break;
            }
            continue;
        }
        return true;
    }

  static Type jffiType(NativeType arg0) {
        Type var1 = ((Type) jffiTypes.get(arg0));
        if (var1 == null) {
            throw new IllegalArgumentException(new StringBuilder().append("unsupported parameter type: ").append(arg0).toString());
        } else {
            return var1;
        }
    }

  static NativeType nativeType(jnr.ffi.Type arg0) {
        return arg0.getNativeType();
    }

  static Collection getAnnotations(FromNativeType arg0) {
        return arg0 == null ? Annotations.EMPTY_ANNOTATIONS : ConverterMetaData.getAnnotations(arg0.getFromNativeConverter());
    }

  static Collection getAnnotations(ToNativeType arg0) {
        return arg0 == null ? Annotations.EMPTY_ANNOTATIONS : ConverterMetaData.getAnnotations(arg0.getToNativeConverter());
    }

  static ResultType getResultType(Runtime arg0, Class arg1, Collection arg2, FromNativeConverter arg3, FromNativeContext arg4) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_3
        //      1: invokestatic  #99 // jnr.ffi.provider.jffi.ConverterMetaData.getAnnotations:(Ljnr/ffi/mapper/FromNativeConverter;)Ljava/util/Collection;
        //      4: astore  5
        //      6: aload_2
        //      7: aload  5
        //      9: invokestatic  #111 // jnr.ffi.util.Annotations.mergeAnnotations:(Ljava/util/Collection;Ljava/util/Collection;)Ljava/util/Collection;
        //     12: astore  6
        //     14: aload_0
        //     15: aload_3
        //     16: ifnull  28 (offset +12)
        //     19: aload_3
        //     20: invokeinterface  #118 // jnr.ffi.mapper.FromNativeConverter.nativeType:()Ljava/lang/Class;, count 1
        //     25: goto  29 (offset +4)
        //     28: aload_1
        //     29: aload  6
        //     31: invokestatic  #105 // jnr.ffi.provider.jffi.InvokerUtil.getMethodResultNativeType:(Ljnr/ffi/Runtime;Ljava/lang/Class;Ljava/util/Collection;)Ljnr/ffi/NativeType;
        //     34: astore  7
        //     36: aload_3
        //     37: ifnull  54 (offset +17)
        //     40: aload  5
        //     42: ldc  #24 // jnr.ffi.mapper.FromNativeConverter$NoContext
        //     44: invokestatic  #107 // jnr.ffi.provider.jffi.InvokerUtil.hasAnnotation:(Ljava/util/Collection;Ljava/lang/Class;)Z
        //     47: ifne  54 (offset +7)
        //     50: iconst_1
        //     51: goto  55 (offset +4)
        //     54: iconst_0
        //     55: istore  8
        //     57: new  #32 // jnr.ffi.provider.ResultType
        //     60: dup
        //     61: aload_1
        //     62: aload  7
        //     64: aload  6
        //     66: aload_3
        //     67: iload  8
        //     69: ifeq  77 (offset +8)
        //     72: aload  4
        //     74: goto  78 (offset +4)
        //     77: aconst_null
        //     78: invokespecial  #97 // jnr.ffi.provider.ResultType.<init>:(Ljava/lang/Class;Ljnr/ffi/NativeType;Ljava/util/Collection;Ljnr/ffi/mapper/FromNativeConverter;Ljnr/ffi/mapper/FromNativeContext;)V
        //     81: areturn
    }

  static ResultType getResultType(Runtime arg0, Class arg1, Collection arg2, FromNativeType arg3, FromNativeContext arg4) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_3
        //      1: invokestatic  #101 // jnr.ffi.provider.jffi.InvokerUtil.getAnnotations:(Ljnr/ffi/mapper/FromNativeType;)Ljava/util/Collection;
        //      4: astore  5
        //      6: aload_2
        //      7: aload  5
        //      9: invokestatic  #111 // jnr.ffi.util.Annotations.mergeAnnotations:(Ljava/util/Collection;Ljava/util/Collection;)Ljava/util/Collection;
        //     12: astore  6
        //     14: aload_3
        //     15: ifnull  27 (offset +12)
        //     18: aload_3
        //     19: invokeinterface  #119 // jnr.ffi.mapper.FromNativeType.getFromNativeConverter:()Ljnr/ffi/mapper/FromNativeConverter;, count 1
        //     24: goto  28 (offset +4)
        //     27: aconst_null
        //     28: astore  7
        //     30: aload_0
        //     31: aload  7
        //     33: ifnull  46 (offset +13)
        //     36: aload  7
        //     38: invokeinterface  #118 // jnr.ffi.mapper.FromNativeConverter.nativeType:()Ljava/lang/Class;, count 1
        //     43: goto  47 (offset +4)
        //     46: aload_1
        //     47: aload  6
        //     49: invokestatic  #105 // jnr.ffi.provider.jffi.InvokerUtil.getMethodResultNativeType:(Ljnr/ffi/Runtime;Ljava/lang/Class;Ljava/util/Collection;)Ljnr/ffi/NativeType;
        //     52: astore  8
        //     54: aload  7
        //     56: ifnull  73 (offset +17)
        //     59: aload  5
        //     61: ldc  #24 // jnr.ffi.mapper.FromNativeConverter$NoContext
        //     63: invokestatic  #107 // jnr.ffi.provider.jffi.InvokerUtil.hasAnnotation:(Ljava/util/Collection;Ljava/lang/Class;)Z
        //     66: ifne  73 (offset +7)
        //     69: iconst_1
        //     70: goto  74 (offset +4)
        //     73: iconst_0
        //     74: istore  9
        //     76: new  #32 // jnr.ffi.provider.ResultType
        //     79: dup
        //     80: aload_1
        //     81: aload  8
        //     83: aload  6
        //     85: aload  7
        //     87: iload  9
        //     89: ifeq  97 (offset +8)
        //     92: aload  4
        //     94: goto  98 (offset +4)
        //     97: aconst_null
        //     98: invokespecial  #97 // jnr.ffi.provider.ResultType.<init>:(Ljava/lang/Class;Ljnr/ffi/NativeType;Ljava/util/Collection;Ljnr/ffi/mapper/FromNativeConverter;Ljnr/ffi/mapper/FromNativeContext;)V
        //    101: areturn
    }

  private static ParameterType getParameterType(Runtime arg0, Class arg1, Collection arg2, ToNativeConverter arg3, ToNativeContext arg4) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: aload_3
        //      2: ifnull  14 (offset +12)
        //      5: aload_3
        //      6: invokeinterface  #121 // jnr.ffi.mapper.ToNativeConverter.nativeType:()Ljava/lang/Class;, count 1
        //     11: goto  15 (offset +4)
        //     14: aload_1
        //     15: aload_2
        //     16: invokestatic  #104 // jnr.ffi.provider.jffi.InvokerUtil.getMethodParameterNativeType:(Ljnr/ffi/Runtime;Ljava/lang/Class;Ljava/util/Collection;)Ljnr/ffi/NativeType;
        //     19: astore  5
        //     21: new  #31 // jnr.ffi.provider.ParameterType
        //     24: dup
        //     25: aload_1
        //     26: aload  5
        //     28: aload_2
        //     29: aload_3
        //     30: aload  4
        //     32: invokespecial  #96 // jnr.ffi.provider.ParameterType.<init>:(Ljava/lang/Class;Ljnr/ffi/NativeType;Ljava/util/Collection;Ljnr/ffi/mapper/ToNativeConverter;Ljnr/ffi/mapper/ToNativeContext;)V
        //     35: areturn
    }

  private static ParameterType getParameterType(Runtime arg0, Class arg1, Collection arg2, ToNativeType arg3, ToNativeContext arg4) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_3
        //      1: ifnull  13 (offset +12)
        //      4: aload_3
        //      5: invokeinterface  #122 // jnr.ffi.mapper.ToNativeType.getToNativeConverter:()Ljnr/ffi/mapper/ToNativeConverter;, count 1
        //     10: goto  14 (offset +4)
        //     13: aconst_null
        //     14: astore  5
        //     16: aload_0
        //     17: aload  5
        //     19: ifnull  32 (offset +13)
        //     22: aload  5
        //     24: invokeinterface  #121 // jnr.ffi.mapper.ToNativeConverter.nativeType:()Ljava/lang/Class;, count 1
        //     29: goto  33 (offset +4)
        //     32: aload_1
        //     33: aload_2
        //     34: invokestatic  #104 // jnr.ffi.provider.jffi.InvokerUtil.getMethodParameterNativeType:(Ljnr/ffi/Runtime;Ljava/lang/Class;Ljava/util/Collection;)Ljnr/ffi/NativeType;
        //     37: astore  6
        //     39: new  #31 // jnr.ffi.provider.ParameterType
        //     42: dup
        //     43: aload_1
        //     44: aload  6
        //     46: aload_2
        //     47: aload  5
        //     49: aload  4
        //     51: invokespecial  #96 // jnr.ffi.provider.ParameterType.<init>:(Ljava/lang/Class;Ljnr/ffi/NativeType;Ljava/util/Collection;Ljnr/ffi/mapper/ToNativeConverter;Ljnr/ffi/mapper/ToNativeContext;)V
        //     54: areturn
    }

  static ParameterType[] getParameterTypes(Runtime arg0, SignatureTypeMapper arg1, Method arg2) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_2
        //      1: invokevirtual  #88 // java.lang.reflect.Method.getParameterTypes:()[Ljava/lang/Class;
        //      4: astore_3
        //      5: aload_2
        //      6: invokevirtual  #87 // java.lang.reflect.Method.getParameterAnnotations:()[[Ljava/lang/annotation/Annotation;
        //      9: astore  4
        //     11: aload_3
        //     12: arraylength
        //     13: anewarray  #31 // jnr.ffi.provider.ParameterType
        //     16: astore  5
        //     18: iconst_0
        //     19: istore  6
        //     21: iload  6
        //     23: aload_3
        //     24: arraylength
        //     25: if_icmpge  166 (offset +141)
        //     28: aload  4
        //     30: iload  6
        //     32: aaload
        //     33: invokestatic  #112 // jnr.ffi.util.Annotations.sortedAnnotationCollection:([Ljava/lang/annotation/Annotation;)Ljava/util/Collection;
        //     36: astore  7
        //     38: new  #26 // jnr.ffi.mapper.MethodParameterContext
        //     41: dup
        //     42: aload_0
        //     43: aload_2
        //     44: iload  6
        //     46: aload  7
        //     48: invokespecial  #95 // jnr.ffi.mapper.MethodParameterContext.<init>:(Ljnr/ffi/Runtime;Ljava/lang/reflect/Method;ILjava/util/Collection;)V
        //     51: astore  8
        //     53: aload_3
        //     54: iload  6
        //     56: aaload
        //     57: aload  8
        //     59: invokestatic  #94 // jnr.ffi.mapper.DefaultSignatureType.create:(Ljava/lang/Class;Ljnr/ffi/mapper/ToNativeContext;)Ljnr/ffi/mapper/DefaultSignatureType;
        //     62: astore  9
        //     64: aload_1
        //     65: aload  9
        //     67: aload  8
        //     69: invokeinterface  #120 // jnr.ffi.mapper.SignatureTypeMapper.getToNativeType:(Ljnr/ffi/mapper/SignatureType;Ljnr/ffi/mapper/ToNativeContext;)Ljnr/ffi/mapper/ToNativeType;, count 3
        //     74: astore  10
        //     76: aload  10
        //     78: ifnull  91 (offset +13)
        //     81: aload  10
        //     83: invokeinterface  #122 // jnr.ffi.mapper.ToNativeType.getToNativeConverter:()Ljnr/ffi/mapper/ToNativeConverter;, count 1
        //     88: goto  92 (offset +4)
        //     91: aconst_null
        //     92: astore  11
        //     94: aload  11
        //     96: invokestatic  #100 // jnr.ffi.provider.jffi.ConverterMetaData.getAnnotations:(Ljnr/ffi/mapper/ToNativeConverter;)Ljava/util/Collection;
        //     99: astore  12
        //    101: aload  7
        //    103: aload  12
        //    105: invokestatic  #111 // jnr.ffi.util.Annotations.mergeAnnotations:(Ljava/util/Collection;Ljava/util/Collection;)Ljava/util/Collection;
        //    108: astore  13
        //    110: aload  11
        //    112: ifnull  129 (offset +17)
        //    115: aload  12
        //    117: ldc  #29 // jnr.ffi.mapper.ToNativeConverter$NoContext
        //    119: invokestatic  #107 // jnr.ffi.provider.jffi.InvokerUtil.hasAnnotation:(Ljava/util/Collection;Ljava/lang/Class;)Z
        //    122: ifne  129 (offset +7)
        //    125: iconst_1
        //    126: goto  130 (offset +4)
        //    129: iconst_0
        //    130: istore  14
        //    132: aload  5
        //    134: iload  6
        //    136: aload_0
        //    137: aload_3
        //    138: iload  6
        //    140: aaload
        //    141: aload  13
        //    143: aload  11
        //    145: iload  14
        //    147: ifeq  155 (offset +8)
        //    150: aload  8
        //    152: goto  156 (offset +4)
        //    155: aconst_null
        //    156: invokestatic  #106 // jnr.ffi.provider.jffi.InvokerUtil.getParameterType:(Ljnr/ffi/Runtime;Ljava/lang/Class;Ljava/util/Collection;Ljnr/ffi/mapper/ToNativeConverter;Ljnr/ffi/mapper/ToNativeContext;)Ljnr/ffi/provider/ParameterType;
        //    159: aastore
        //    160: iinc  6, 1
        //    163: goto  21 (offset -142)
        //    166: aload  5
        //    168: areturn
    }

  static CallContext getCallContext(SigType arg0, SigType[] arg1, CallingConvention arg2, boolean arg3) {
        return getCallContext(arg0, arg1.length, arg1, arg1.length, arg2, arg3);
    }

  static CallContext getCallContext(SigType arg0, int arg1, SigType[] arg2, CallingConvention arg3, boolean arg4) {
        return getCallContext(arg0, arg1, arg2, arg2.length, arg3, arg4);
    }

  static CallContext getCallContext(SigType arg0, int arg1, SigType[] arg2, int arg3, CallingConvention arg4, boolean arg5) {
        Type[] var6 = new Type[arg3];
        int var7 = 0;
        while (var7 < var6.length) {
            var6[var7] = jffiType(arg2[var7].getNativeType());
            ++var7;
            continue;
        }
        return CallContextCache.getInstance().getCallContext(jffiType(arg0.getNativeType()), arg1, var6, jffiConvention(arg4), arg5);
    }

  public static CallingConvention getNativeCallingConvention(Method arg0) {
        if (arg0.isAnnotationPresent(StdCall.class)) {
            return CallingConvention.STDCALL;
        } else {
            if (!arg0.getDeclaringClass().isAnnotationPresent(StdCall.class)) {
                return CallingConvention.DEFAULT;
            } else {
                return CallingConvention.STDCALL;
            }
        }
    }

  static NativeType getMethodParameterNativeType(Runtime arg0, Class arg1, Collection arg2) {
        return Types.getType(arg0, arg1, arg2).getNativeType();
    }

  static NativeType getMethodResultNativeType(Runtime arg0, Class arg1, Collection arg2) {
        return Types.getType(arg0, arg1, arg2).getNativeType();
    }

  public static final com.kenai.jffi.CallingConvention jffiConvention(CallingConvention arg0) {
        return arg0 != CallingConvention.DEFAULT ? com.kenai.jffi.CallingConvention.STDCALL : com.kenai.jffi.CallingConvention.DEFAULT;
    }

}