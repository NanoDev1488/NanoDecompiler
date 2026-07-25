// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.ReflectionVariableAccessorGenerator
package jnr.ffi.provider.jffi;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.Type;
import jnr.ffi.Variable;
import jnr.ffi.mapper.DefaultSignatureType;
import jnr.ffi.mapper.FromNativeConverter;
import jnr.ffi.mapper.FromNativeType;
import jnr.ffi.mapper.SignatureTypeMapper;
import jnr.ffi.mapper.ToNativeConverter;
import jnr.ffi.mapper.ToNativeType;
import jnr.ffi.provider.jffi.DefaultInvokerFactory;
import jnr.ffi.provider.jffi.MemoryUtil;
import jnr.ffi.provider.jffi.NumberUtil;
import jnr.ffi.provider.jffi.ReflectionVariableAccessorGenerator_ConvertingVariable;
import jnr.ffi.provider.jffi.ReflectionVariableAccessorGenerator_DoublePointerOp;
import jnr.ffi.provider.jffi.ReflectionVariableAccessorGenerator_FloatPointerOp;
import jnr.ffi.provider.jffi.ReflectionVariableAccessorGenerator_Int16PointerOp;
import jnr.ffi.provider.jffi.ReflectionVariableAccessorGenerator_Int32PointerOp;
import jnr.ffi.provider.jffi.ReflectionVariableAccessorGenerator_Int64PointerOp;
import jnr.ffi.provider.jffi.ReflectionVariableAccessorGenerator_Int8PointerOp;
import jnr.ffi.provider.jffi.ReflectionVariableAccessorGenerator_NumberVariable;
import jnr.ffi.provider.jffi.ReflectionVariableAccessorGenerator_PointerOp;
import jnr.ffi.provider.jffi.ReflectionVariableAccessorGenerator_PointerVariable;
import jnr.ffi.provider.jffi.SimpleNativeContext;
import jnr.ffi.provider.jffi.Types;

class ReflectionVariableAccessorGenerator {

   ReflectionVariableAccessorGenerator() { // было: <init>
        super();
    }

  static Variable createVariableAccessor(Runtime arg0, Method arg1, long arg2, SignatureTypeMapper arg3, Collection arg4) {
        FromNativeConverter __stk1;
        ToNativeConverter __stk2;
        Class __stk3;
        Object var6 = (((ParameterizedType) arg1.getGenericReturnType())).getActualTypeArguments()[0];
        if (var6 instanceof Class) {
            Class var7 = ((Class) var6);
            SimpleNativeContext var8 = new SimpleNativeContext(arg0, arg4);
            DefaultSignatureType var9 = DefaultSignatureType.create(var7, var8);
            FromNativeType var10 = arg3.getFromNativeType(var9, var8);
            __stk1 = var10 == null ? null : var10.getFromNativeConverter();
            FromNativeConverter var11 = __stk1;
            ToNativeType var12 = arg3.getToNativeType(var9, var8);
            __stk2 = var12 == null ? null : var12.getToNativeConverter();
            ToNativeConverter var13 = __stk2;
            __stk3 = var13 == null ? var7 : var13.nativeType();
            Class var14 = __stk3;
            NativeType var15 = Types.getType(arg0, ((Class) var14), arg4).getNativeType();
            jnr.ffi.provider.ToNativeType var16 = new jnr.ffi.provider.ToNativeType(var7, var15, arg4, ((ToNativeConverter) var13), null);
            jnr.ffi.provider.FromNativeType var17 = new jnr.ffi.provider.FromNativeType(var7, var15, arg4, ((FromNativeConverter) var11), null);
            Pointer var19 = MemoryUtil.newPointer(arg0, arg2);
            Variable var18 = getNativeVariableAccessor(var19, var16, var17);
            return var16.getToNativeConverter() == null ? var18 : getConvertingVariable(var18, var16.getToNativeConverter(), var17.getFromNativeConverter());
        } else {
            throw new IllegalArgumentException(new StringBuilder().append("unsupported variable class: ").append(var6).toString());
        }
    }

  static Variable getConvertingVariable(Variable arg0, ToNativeConverter arg1, FromNativeConverter arg2) {
        if (arg1 == null) {
            if (arg1 != null) {
                return new ReflectionVariableAccessorGenerator_ConvertingVariable(arg0, arg1, arg2, null);
            } else {
                if (arg2 == null) {
                    return new ReflectionVariableAccessorGenerator_ConvertingVariable(arg0, arg1, arg2, null);
                } else {
                    throw new UnsupportedOperationException("convertible types must have both a ToNativeConverter and a FromNativeConverter");
                }
            }
        } else {
            if (arg2 == null) {
                throw new UnsupportedOperationException("convertible types must have both a ToNativeConverter and a FromNativeConverter");
            } else {
                if (arg1 != null) {
                    return new ReflectionVariableAccessorGenerator_ConvertingVariable(arg0, arg1, arg2, null);
                } else {
                    if (arg2 == null) {
                        return new ReflectionVariableAccessorGenerator_ConvertingVariable(arg0, arg1, arg2, null);
                    } else {
                        throw new UnsupportedOperationException("convertible types must have both a ToNativeConverter and a FromNativeConverter");
                    }
                }
            }
        }
    }

  static Variable getNativeVariableAccessor(Pointer arg0, jnr.ffi.provider.ToNativeType arg1, jnr.ffi.provider.FromNativeType arg2) {
        if (Pointer.class != arg1.effectiveJavaType()) {
            if (!Number.class.isAssignableFrom(arg1.effectiveJavaType())) {
                throw new UnsupportedOperationException(new StringBuilder().append("unsupported variable type: ").append(arg1.effectiveJavaType()).toString());
            } else {
                return new ReflectionVariableAccessorGenerator_NumberVariable(arg0, getPointerOp(arg1.getNativeType()), DefaultInvokerFactory.getNumberDataConverter(arg1.getNativeType()), DefaultInvokerFactory.getNumberResultConverter(arg2), null);
            }
        } else {
            return new ReflectionVariableAccessorGenerator_PointerVariable(arg0, null);
        }
    }

  private static ReflectionVariableAccessorGenerator_PointerOp getPointerOp(NativeType arg0) {
        switch (arg0) {
            case SCHAR:
            case UCHAR:
                return ReflectionVariableAccessorGenerator_Int8PointerOp.INSTANCE;
            case SSHORT:
            case USHORT:
                return ReflectionVariableAccessorGenerator_Int16PointerOp.INSTANCE;
            case SINT:
            case UINT:
                return ReflectionVariableAccessorGenerator_Int32PointerOp.INSTANCE;
            case SLONGLONG:
            case ULONGLONG:
                return ReflectionVariableAccessorGenerator_Int64PointerOp.INSTANCE;
            case SLONG:
            case ULONG:
            case ADDRESS:
                return NumberUtil.sizeof(arg0) != 4 ? ReflectionVariableAccessorGenerator_Int64PointerOp.INSTANCE : ReflectionVariableAccessorGenerator_Int32PointerOp.INSTANCE;
            case FLOAT:
                return ReflectionVariableAccessorGenerator_FloatPointerOp.INSTANCE;
            case DOUBLE:
                return ReflectionVariableAccessorGenerator_DoublePointerOp.INSTANCE;
            default:
                throw new UnsupportedOperationException(new StringBuilder().append("cannot convert ").append(arg0).toString());
        }
    }

}