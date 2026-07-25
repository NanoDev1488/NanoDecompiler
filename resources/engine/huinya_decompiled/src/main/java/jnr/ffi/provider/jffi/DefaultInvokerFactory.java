// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.DefaultInvokerFactory
package jnr.ffi.provider.jffi;

import com.kenai.jffi.Function;
import com.kenai.jffi.ObjectParameterType_ComponentType;
import java.lang.reflect.Method;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import java.util.Collection;
import java.util.Map;
import jnr.ffi.Address;
import jnr.ffi.CallingConvention;
import jnr.ffi.LibraryLoader;
import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.annotations.StdCall;
import jnr.ffi.annotations.Synchronized;
import jnr.ffi.annotations.Variadic;
import jnr.ffi.mapper.DataConverter;
import jnr.ffi.mapper.DefaultSignatureType;
import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.mapper.FunctionMapper;
import jnr.ffi.mapper.MethodResultContext;
import jnr.ffi.mapper.SignatureTypeMapper;
import jnr.ffi.provider.FromNativeType;
import jnr.ffi.provider.Invoker;
import jnr.ffi.provider.NativeFunction;
import jnr.ffi.provider.ParameterType;
import jnr.ffi.provider.ResultType;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_AddressResultConverter;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_BooleanInvoker;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_BooleanMarshaller;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_BufferMarshaller;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_ByteResultConverter;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_ConvertingInvoker;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_DefaultInvoker;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_DoubleConverter;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_DoubleResultConverter;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_Float32Invoker;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_Float32Marshaller;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_Float64Invoker;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_Float64Marshaller;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_FloatConverter;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_FloatResultConverter;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_FunctionInvoker;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_FunctionNotFoundInvoker;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_Int16Marshaller;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_Int32Marshaller;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_Int64Marshaller;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_Int8Marshaller;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_IntInvoker;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_IntegerResultConverter;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_LongInvoker;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_LongLongConverter;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_LongResultConverter;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_Marshaller;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_PointerInvoker;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_PointerMarshaller;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_PrimitiveArrayMarshaller;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_ResultConverter;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_ShortResultConverter;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_Signed16Converter;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_Signed32Converter;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_Signed8Converter;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_SynchronizedInvoker;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_ToNativeConverterMarshaller;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_Unsigned16Converter;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_Unsigned32Converter;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_Unsigned8Converter;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_VariadicInvoker;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_VoidInvoker;
import jnr.ffi.provider.jffi.InvokerUtil;
import jnr.ffi.provider.jffi.NativeFunctionMapperContext;
import jnr.ffi.provider.jffi.NativeLibrary;
import jnr.ffi.provider.jffi.NativeRuntime;
import jnr.ffi.provider.jffi.NumberUtil;
import jnr.ffi.provider.jffi.PrimitiveArrayParameterStrategy;
import jnr.ffi.util.Annotations;

final class DefaultInvokerFactory {

    // ---- поля ----
  private final Runtime runtime;
  private final NativeLibrary library;
  private final SignatureTypeMapper typeMapper;
  private final FunctionMapper functionMapper;
  private final CallingConvention libraryCallingConvention;
  private final boolean libraryIsSynchronized;
  private final Map libraryOptions;

  public DefaultInvokerFactory(Runtime arg0, NativeLibrary arg1, SignatureTypeMapper arg2, FunctionMapper arg3, CallingConvention arg4, Map arg5, boolean arg6) { // было: <init>
        super();
        runtime = arg0;
        library = arg1;
        typeMapper = arg2;
        functionMapper = arg3;
        libraryCallingConvention = arg4;
        libraryIsSynchronized = arg6;
        libraryOptions = arg5;
    }

  public Invoker createInvoker(Method arg0) {
        CallingConvention __stk1;
        Collection var2 = Annotations.sortedAnnotationCollection(arg0.getAnnotations());
        String var3 = functionMapper.mapFunctionName(arg0.getName(), new NativeFunctionMapperContext(library, var2));
        long var4 = library.getSymbolAddress(var3);
        if (var4 != 0L) {
            MethodResultContext var6 = new MethodResultContext(NativeRuntime.getInstance(), arg0);
            DefaultSignatureType var7 = DefaultSignatureType.create(arg0.getReturnType(), var6);
            ResultType var8 = InvokerUtil.getResultType(runtime, arg0.getReturnType(), var6.getAnnotations(), typeMapper.getFromNativeType(var7, var6), var6);
            DefaultInvokerFactory_FunctionInvoker var9 = getFunctionInvoker(var8);
            if (var8.getFromNativeConverter() != null) {
                var9 = new DefaultInvokerFactory_ConvertingInvoker(var8.getFromNativeConverter(), var8.getFromNativeContext(), var9);
            }
        } else {
            return new DefaultInvokerFactory_FunctionNotFoundInvoker(arg0, var3, null);
        }
        ParameterType[] var10 = InvokerUtil.getParameterTypes(runtime, typeMapper, arg0);
        __stk1 = !arg0.isAnnotationPresent(StdCall.class) ? libraryCallingConvention : CallingConvention.STDCALL;
        CallingConvention var11 = __stk1;
        boolean var12 = LibraryLoader.saveError(libraryOptions, NativeFunction.hasSaveError(arg0), NativeFunction.hasIgnoreError(arg0));
        DefaultInvokerFactory_DefaultInvoker var13;
        if (!arg0.isVarArgs()) {
            Variadic var15 = ((Variadic) arg0.getAnnotation(Variadic.class));
            Function var14;
            if (var15 == null) {
                var14 = new Function(var4, InvokerUtil.getCallContext(var8, var10, ((CallingConvention) var11), var12));
            } else {
                var14 = new Function(var4, InvokerUtil.getCallContext(var8, var15.fixedCount(), var10, ((CallingConvention) var11), var12));
            }
            DefaultInvokerFactory_Marshaller[] var16 = new DefaultInvokerFactory_Marshaller[var10.length];
            int var17 = 0;
            while (var17 < var16.length) {
                var16[var17] = getMarshaller(((ParameterType) var10[var17]));
                ++var17;
                continue;
            }
            var13 = new DefaultInvokerFactory_DefaultInvoker(runtime, library, var14, var9, var16);
        } else {
            var13 = new DefaultInvokerFactory_VariadicInvoker(runtime, var9, typeMapper, var10, var4, var8, var12, ((CallingConvention) var11));
        }
        return libraryIsSynchronized ? new DefaultInvokerFactory_SynchronizedInvoker(var13) : !arg0.isAnnotationPresent(Synchronized.class) ? var13 : new DefaultInvokerFactory_SynchronizedInvoker(var13);
    }

  private static DefaultInvokerFactory_FunctionInvoker getFunctionInvoker(ResultType arg0) {
        Class var1 = arg0.effectiveJavaType();
        if (Void.class.isAssignableFrom(var1)) {
            return DefaultInvokerFactory_VoidInvoker.INSTANCE;
        } else {
            if (Void.TYPE != var1) {
                if (Boolean.class.isAssignableFrom(var1)) {
                    return DefaultInvokerFactory_BooleanInvoker.INSTANCE;
                } else {
                    if (Boolean.TYPE != var1) {
                        if (Number.class.isAssignableFrom(var1)) {
                            return new DefaultInvokerFactory_ConvertingInvoker(getNumberResultConverter(arg0), null, new DefaultInvokerFactory_ConvertingInvoker(getNumberDataConverter(arg0.getNativeType()), null, getNumberFunctionInvoker(arg0.getNativeType())));
                        } else {
                            if (!var1.isPrimitive()) {
                                if (!Pointer.class.isAssignableFrom(var1)) {
                                    throw new IllegalArgumentException(new StringBuilder().append("Unknown return type: ").append(var1).toString());
                                } else {
                                    return DefaultInvokerFactory_PointerInvoker.INSTANCE;
                                }
                            } else {
                                return new DefaultInvokerFactory_ConvertingInvoker(getNumberResultConverter(arg0), null, new DefaultInvokerFactory_ConvertingInvoker(getNumberDataConverter(arg0.getNativeType()), null, getNumberFunctionInvoker(arg0.getNativeType())));
                            }
                        }
                    } else {
                        return DefaultInvokerFactory_BooleanInvoker.INSTANCE;
                    }
                }
            } else {
                return DefaultInvokerFactory_VoidInvoker.INSTANCE;
            }
        }
    }

  private static DefaultInvokerFactory_FunctionInvoker getNumberFunctionInvoker(NativeType arg0) {
        switch (arg0) {
            case SCHAR:
            case UCHAR:
            case SSHORT:
            case USHORT:
            case SINT:
            case UINT:
            case SLONG:
            case ULONG:
            case SLONGLONG:
            case ULONGLONG:
            case ADDRESS:
                return NumberUtil.sizeof(arg0) > 4 ? DefaultInvokerFactory_LongInvoker.INSTANCE : DefaultInvokerFactory_IntInvoker.INSTANCE;
            case FLOAT:
                return DefaultInvokerFactory_Float32Invoker.INSTANCE;
            case DOUBLE:
                return DefaultInvokerFactory_Float64Invoker.INSTANCE;
            default:
                throw new UnsupportedOperationException(new StringBuilder().append("unsupported numeric type: ").append(arg0).toString());
        }
    }

  static DefaultInvokerFactory_Marshaller getMarshaller(ParameterType arg0) {
        DefaultInvokerFactory_Marshaller var1 = getMarshaller(arg0.effectiveJavaType(), arg0.getNativeType(), arg0.getAnnotations());
        return arg0.getToNativeConverter() == null ? var1 : new DefaultInvokerFactory_ToNativeConverterMarshaller(arg0.getToNativeConverter(), arg0.getToNativeContext(), var1);
    }

  static DefaultInvokerFactory_Marshaller getMarshaller(Class arg0, NativeType arg1, Collection arg2) {
        if (Number.class.isAssignableFrom(arg0)) {
            switch (arg1) {
                case SCHAR:
                    return new DefaultInvokerFactory_Int8Marshaller(DefaultInvokerFactory_Signed8Converter.INSTANCE);
                case UCHAR:
                    return new DefaultInvokerFactory_Int8Marshaller(DefaultInvokerFactory_Unsigned8Converter.INSTANCE);
                case SSHORT:
                    return new DefaultInvokerFactory_Int16Marshaller(DefaultInvokerFactory_Signed16Converter.INSTANCE);
                case USHORT:
                    return new DefaultInvokerFactory_Int16Marshaller(DefaultInvokerFactory_Unsigned16Converter.INSTANCE);
                case SINT:
                    return new DefaultInvokerFactory_Int32Marshaller(DefaultInvokerFactory_Signed32Converter.INSTANCE);
                case UINT:
                    return new DefaultInvokerFactory_Int32Marshaller(DefaultInvokerFactory_Unsigned32Converter.INSTANCE);
                case SLONG:
                case ULONG:
                case ADDRESS:
                    return NumberUtil.sizeof(arg1) != 4 ? DefaultInvokerFactory_Int64Marshaller.INSTANCE : new DefaultInvokerFactory_Int32Marshaller(getNumberDataConverter(arg1));
                case SLONGLONG:
                case ULONGLONG:
                    return DefaultInvokerFactory_Int64Marshaller.INSTANCE;
                case FLOAT:
                    return DefaultInvokerFactory_Float32Marshaller.INSTANCE;
                case DOUBLE:
                    return DefaultInvokerFactory_Float64Marshaller.INSTANCE;
                default:
                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
            }
        } else {
            if (!arg0.isPrimitive()) {
                if (Boolean.class.isAssignableFrom(arg0)) {
                    return DefaultInvokerFactory_BooleanMarshaller.INSTANCE;
                } else {
                    if (Boolean.TYPE != arg0) {
                        if (!Pointer.class.isAssignableFrom(arg0)) {
                            if (!ByteBuffer.class.isAssignableFrom(arg0)) {
                                if (!ShortBuffer.class.isAssignableFrom(arg0)) {
                                    if (!IntBuffer.class.isAssignableFrom(arg0)) {
                                        if (!LongBuffer.class.isAssignableFrom(arg0)) {
                                            if (!FloatBuffer.class.isAssignableFrom(arg0)) {
                                                if (!DoubleBuffer.class.isAssignableFrom(arg0)) {
                                                    if (!Buffer.class.isAssignableFrom(arg0)) {
                                                        if (!arg0.isArray()) {
                                                            if (!arg0.isArray()) {
                                                                if (!arg0.isArray()) {
                                                                    if (!arg0.isArray()) {
                                                                        if (!arg0.isArray()) {
                                                                            if (!arg0.isArray()) {
                                                                                if (!arg0.isArray()) {
                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                } else {
                                                                                    if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                    } else {
                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                if (arg0.getComponentType() != Double.TYPE) {
                                                                                    if (!arg0.isArray()) {
                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                    } else {
                                                                                        if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                        } else {
                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                }
                                                                            }
                                                                        } else {
                                                                            if (arg0.getComponentType() != Float.TYPE) {
                                                                                if (!arg0.isArray()) {
                                                                                    if (!arg0.isArray()) {
                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                    } else {
                                                                                        if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                        } else {
                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    if (arg0.getComponentType() != Double.TYPE) {
                                                                                        if (!arg0.isArray()) {
                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.FLOAT, arg2);
                                                                            }
                                                                        }
                                                                    } else {
                                                                        if (arg0.getComponentType() != Long.TYPE) {
                                                                            if (!arg0.isArray()) {
                                                                                if (!arg0.isArray()) {
                                                                                    if (!arg0.isArray()) {
                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                    } else {
                                                                                        if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                        } else {
                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    if (arg0.getComponentType() != Double.TYPE) {
                                                                                        if (!arg0.isArray()) {
                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                if (arg0.getComponentType() != Float.TYPE) {
                                                                                    if (!arg0.isArray()) {
                                                                                        if (!arg0.isArray()) {
                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        if (arg0.getComponentType() != Double.TYPE) {
                                                                                            if (!arg0.isArray()) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.FLOAT, arg2);
                                                                                }
                                                                            }
                                                                        } else {
                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.LONG, arg2);
                                                                        }
                                                                    }
                                                                } else {
                                                                    if (arg0.getComponentType() != Integer.TYPE) {
                                                                        if (!arg0.isArray()) {
                                                                            if (!arg0.isArray()) {
                                                                                if (!arg0.isArray()) {
                                                                                    if (!arg0.isArray()) {
                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                    } else {
                                                                                        if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                        } else {
                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    if (arg0.getComponentType() != Double.TYPE) {
                                                                                        if (!arg0.isArray()) {
                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                if (arg0.getComponentType() != Float.TYPE) {
                                                                                    if (!arg0.isArray()) {
                                                                                        if (!arg0.isArray()) {
                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        if (arg0.getComponentType() != Double.TYPE) {
                                                                                            if (!arg0.isArray()) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.FLOAT, arg2);
                                                                                }
                                                                            }
                                                                        } else {
                                                                            if (arg0.getComponentType() != Long.TYPE) {
                                                                                if (!arg0.isArray()) {
                                                                                    if (!arg0.isArray()) {
                                                                                        if (!arg0.isArray()) {
                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        if (arg0.getComponentType() != Double.TYPE) {
                                                                                            if (!arg0.isArray()) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    if (arg0.getComponentType() != Float.TYPE) {
                                                                                        if (!arg0.isArray()) {
                                                                                            if (!arg0.isArray()) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Double.TYPE) {
                                                                                                if (!arg0.isArray()) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                    } else {
                                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.FLOAT, arg2);
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.LONG, arg2);
                                                                            }
                                                                        }
                                                                    } else {
                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.INT, arg2);
                                                                    }
                                                                }
                                                            } else {
                                                                if (arg0.getComponentType() != Short.TYPE) {
                                                                    if (!arg0.isArray()) {
                                                                        if (!arg0.isArray()) {
                                                                            if (!arg0.isArray()) {
                                                                                if (!arg0.isArray()) {
                                                                                    if (!arg0.isArray()) {
                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                    } else {
                                                                                        if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                        } else {
                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    if (arg0.getComponentType() != Double.TYPE) {
                                                                                        if (!arg0.isArray()) {
                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                if (arg0.getComponentType() != Float.TYPE) {
                                                                                    if (!arg0.isArray()) {
                                                                                        if (!arg0.isArray()) {
                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        if (arg0.getComponentType() != Double.TYPE) {
                                                                                            if (!arg0.isArray()) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.FLOAT, arg2);
                                                                                }
                                                                            }
                                                                        } else {
                                                                            if (arg0.getComponentType() != Long.TYPE) {
                                                                                if (!arg0.isArray()) {
                                                                                    if (!arg0.isArray()) {
                                                                                        if (!arg0.isArray()) {
                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        if (arg0.getComponentType() != Double.TYPE) {
                                                                                            if (!arg0.isArray()) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    if (arg0.getComponentType() != Float.TYPE) {
                                                                                        if (!arg0.isArray()) {
                                                                                            if (!arg0.isArray()) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Double.TYPE) {
                                                                                                if (!arg0.isArray()) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                    } else {
                                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.FLOAT, arg2);
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.LONG, arg2);
                                                                            }
                                                                        }
                                                                    } else {
                                                                        if (arg0.getComponentType() != Integer.TYPE) {
                                                                            if (!arg0.isArray()) {
                                                                                if (!arg0.isArray()) {
                                                                                    if (!arg0.isArray()) {
                                                                                        if (!arg0.isArray()) {
                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        if (arg0.getComponentType() != Double.TYPE) {
                                                                                            if (!arg0.isArray()) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    if (arg0.getComponentType() != Float.TYPE) {
                                                                                        if (!arg0.isArray()) {
                                                                                            if (!arg0.isArray()) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Double.TYPE) {
                                                                                                if (!arg0.isArray()) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                    } else {
                                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.FLOAT, arg2);
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                if (arg0.getComponentType() != Long.TYPE) {
                                                                                    if (!arg0.isArray()) {
                                                                                        if (!arg0.isArray()) {
                                                                                            if (!arg0.isArray()) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Double.TYPE) {
                                                                                                if (!arg0.isArray()) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                    } else {
                                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        if (arg0.getComponentType() != Float.TYPE) {
                                                                                            if (!arg0.isArray()) {
                                                                                                if (!arg0.isArray()) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                    } else {
                                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Double.TYPE) {
                                                                                                    if (!arg0.isArray()) {
                                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                    } else {
                                                                                                        if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                        } else {
                                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                        }
                                                                                                    }
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.FLOAT, arg2);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.LONG, arg2);
                                                                                }
                                                                            }
                                                                        } else {
                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.INT, arg2);
                                                                        }
                                                                    }
                                                                } else {
                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.SHORT, arg2);
                                                                }
                                                            }
                                                        } else {
                                                            if (arg0.getComponentType() != Byte.TYPE) {
                                                                if (!arg0.isArray()) {
                                                                    if (!arg0.isArray()) {
                                                                        if (!arg0.isArray()) {
                                                                            if (!arg0.isArray()) {
                                                                                if (!arg0.isArray()) {
                                                                                    if (!arg0.isArray()) {
                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                    } else {
                                                                                        if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                        } else {
                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    if (arg0.getComponentType() != Double.TYPE) {
                                                                                        if (!arg0.isArray()) {
                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                if (arg0.getComponentType() != Float.TYPE) {
                                                                                    if (!arg0.isArray()) {
                                                                                        if (!arg0.isArray()) {
                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        if (arg0.getComponentType() != Double.TYPE) {
                                                                                            if (!arg0.isArray()) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.FLOAT, arg2);
                                                                                }
                                                                            }
                                                                        } else {
                                                                            if (arg0.getComponentType() != Long.TYPE) {
                                                                                if (!arg0.isArray()) {
                                                                                    if (!arg0.isArray()) {
                                                                                        if (!arg0.isArray()) {
                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        if (arg0.getComponentType() != Double.TYPE) {
                                                                                            if (!arg0.isArray()) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    if (arg0.getComponentType() != Float.TYPE) {
                                                                                        if (!arg0.isArray()) {
                                                                                            if (!arg0.isArray()) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Double.TYPE) {
                                                                                                if (!arg0.isArray()) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                    } else {
                                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.FLOAT, arg2);
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.LONG, arg2);
                                                                            }
                                                                        }
                                                                    } else {
                                                                        if (arg0.getComponentType() != Integer.TYPE) {
                                                                            if (!arg0.isArray()) {
                                                                                if (!arg0.isArray()) {
                                                                                    if (!arg0.isArray()) {
                                                                                        if (!arg0.isArray()) {
                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        if (arg0.getComponentType() != Double.TYPE) {
                                                                                            if (!arg0.isArray()) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    if (arg0.getComponentType() != Float.TYPE) {
                                                                                        if (!arg0.isArray()) {
                                                                                            if (!arg0.isArray()) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Double.TYPE) {
                                                                                                if (!arg0.isArray()) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                    } else {
                                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.FLOAT, arg2);
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                if (arg0.getComponentType() != Long.TYPE) {
                                                                                    if (!arg0.isArray()) {
                                                                                        if (!arg0.isArray()) {
                                                                                            if (!arg0.isArray()) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Double.TYPE) {
                                                                                                if (!arg0.isArray()) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                    } else {
                                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        if (arg0.getComponentType() != Float.TYPE) {
                                                                                            if (!arg0.isArray()) {
                                                                                                if (!arg0.isArray()) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                    } else {
                                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Double.TYPE) {
                                                                                                    if (!arg0.isArray()) {
                                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                    } else {
                                                                                                        if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                        } else {
                                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                        }
                                                                                                    }
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.FLOAT, arg2);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.LONG, arg2);
                                                                                }
                                                                            }
                                                                        } else {
                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.INT, arg2);
                                                                        }
                                                                    }
                                                                } else {
                                                                    if (arg0.getComponentType() != Short.TYPE) {
                                                                        if (!arg0.isArray()) {
                                                                            if (!arg0.isArray()) {
                                                                                if (!arg0.isArray()) {
                                                                                    if (!arg0.isArray()) {
                                                                                        if (!arg0.isArray()) {
                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        if (arg0.getComponentType() != Double.TYPE) {
                                                                                            if (!arg0.isArray()) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    if (arg0.getComponentType() != Float.TYPE) {
                                                                                        if (!arg0.isArray()) {
                                                                                            if (!arg0.isArray()) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Double.TYPE) {
                                                                                                if (!arg0.isArray()) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                    } else {
                                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.FLOAT, arg2);
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                if (arg0.getComponentType() != Long.TYPE) {
                                                                                    if (!arg0.isArray()) {
                                                                                        if (!arg0.isArray()) {
                                                                                            if (!arg0.isArray()) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Double.TYPE) {
                                                                                                if (!arg0.isArray()) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                    } else {
                                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        if (arg0.getComponentType() != Float.TYPE) {
                                                                                            if (!arg0.isArray()) {
                                                                                                if (!arg0.isArray()) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                    } else {
                                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Double.TYPE) {
                                                                                                    if (!arg0.isArray()) {
                                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                    } else {
                                                                                                        if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                        } else {
                                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                        }
                                                                                                    }
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.FLOAT, arg2);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.LONG, arg2);
                                                                                }
                                                                            }
                                                                        } else {
                                                                            if (arg0.getComponentType() != Integer.TYPE) {
                                                                                if (!arg0.isArray()) {
                                                                                    if (!arg0.isArray()) {
                                                                                        if (!arg0.isArray()) {
                                                                                            if (!arg0.isArray()) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Double.TYPE) {
                                                                                                if (!arg0.isArray()) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                    } else {
                                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        if (arg0.getComponentType() != Float.TYPE) {
                                                                                            if (!arg0.isArray()) {
                                                                                                if (!arg0.isArray()) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                    } else {
                                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Double.TYPE) {
                                                                                                    if (!arg0.isArray()) {
                                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                    } else {
                                                                                                        if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                        } else {
                                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                        }
                                                                                                    }
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.FLOAT, arg2);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    if (arg0.getComponentType() != Long.TYPE) {
                                                                                        if (!arg0.isArray()) {
                                                                                            if (!arg0.isArray()) {
                                                                                                if (!arg0.isArray()) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                    } else {
                                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Double.TYPE) {
                                                                                                    if (!arg0.isArray()) {
                                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                    } else {
                                                                                                        if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                        } else {
                                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                        }
                                                                                                    }
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Float.TYPE) {
                                                                                                if (!arg0.isArray()) {
                                                                                                    if (!arg0.isArray()) {
                                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                    } else {
                                                                                                        if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                        } else {
                                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                        }
                                                                                                    }
                                                                                                } else {
                                                                                                    if (arg0.getComponentType() != Double.TYPE) {
                                                                                                        if (!arg0.isArray()) {
                                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                        } else {
                                                                                                            if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                            } else {
                                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                            }
                                                                                                        }
                                                                                                    } else {
                                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.FLOAT, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.LONG, arg2);
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.INT, arg2);
                                                                            }
                                                                        }
                                                                    } else {
                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.SHORT, arg2);
                                                                    }
                                                                }
                                                            } else {
                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BYTE, arg2);
                                                            }
                                                        }
                                                    } else {
                                                        return new DefaultInvokerFactory_BufferMarshaller(null, arg2);
                                                    }
                                                } else {
                                                    return new DefaultInvokerFactory_BufferMarshaller(ObjectParameterType_ComponentType.DOUBLE, arg2);
                                                }
                                            } else {
                                                return new DefaultInvokerFactory_BufferMarshaller(ObjectParameterType_ComponentType.FLOAT, arg2);
                                            }
                                        } else {
                                            return new DefaultInvokerFactory_BufferMarshaller(ObjectParameterType_ComponentType.LONG, arg2);
                                        }
                                    } else {
                                        return new DefaultInvokerFactory_BufferMarshaller(ObjectParameterType_ComponentType.INT, arg2);
                                    }
                                } else {
                                    return new DefaultInvokerFactory_BufferMarshaller(ObjectParameterType_ComponentType.SHORT, arg2);
                                }
                            } else {
                                return new DefaultInvokerFactory_BufferMarshaller(ObjectParameterType_ComponentType.BYTE, arg2);
                            }
                        } else {
                            return new DefaultInvokerFactory_PointerMarshaller(arg2);
                        }
                    } else {
                        return DefaultInvokerFactory_BooleanMarshaller.INSTANCE;
                    }
                }
            }
            if (!Number.class.isAssignableFrom(NumberUtil.getBoxedClass(arg0))) {
                if (Boolean.class.isAssignableFrom(arg0)) {
                    return DefaultInvokerFactory_BooleanMarshaller.INSTANCE;
                } else {
                    if (Boolean.TYPE != arg0) {
                        if (!Pointer.class.isAssignableFrom(arg0)) {
                            if (!ByteBuffer.class.isAssignableFrom(arg0)) {
                                if (!ShortBuffer.class.isAssignableFrom(arg0)) {
                                    if (!IntBuffer.class.isAssignableFrom(arg0)) {
                                        if (!LongBuffer.class.isAssignableFrom(arg0)) {
                                            if (!FloatBuffer.class.isAssignableFrom(arg0)) {
                                                if (!DoubleBuffer.class.isAssignableFrom(arg0)) {
                                                    if (!Buffer.class.isAssignableFrom(arg0)) {
                                                        if (!arg0.isArray()) {
                                                            if (!arg0.isArray()) {
                                                                if (!arg0.isArray()) {
                                                                    if (!arg0.isArray()) {
                                                                        if (!arg0.isArray()) {
                                                                            if (!arg0.isArray()) {
                                                                                if (!arg0.isArray()) {
                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                } else {
                                                                                    if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                    } else {
                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                if (arg0.getComponentType() != Double.TYPE) {
                                                                                    if (!arg0.isArray()) {
                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                    } else {
                                                                                        if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                        } else {
                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                }
                                                                            }
                                                                        } else {
                                                                            if (arg0.getComponentType() != Float.TYPE) {
                                                                                if (!arg0.isArray()) {
                                                                                    if (!arg0.isArray()) {
                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                    } else {
                                                                                        if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                        } else {
                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    if (arg0.getComponentType() != Double.TYPE) {
                                                                                        if (!arg0.isArray()) {
                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.FLOAT, arg2);
                                                                            }
                                                                        }
                                                                    } else {
                                                                        if (arg0.getComponentType() != Long.TYPE) {
                                                                            if (!arg0.isArray()) {
                                                                                if (!arg0.isArray()) {
                                                                                    if (!arg0.isArray()) {
                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                    } else {
                                                                                        if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                        } else {
                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    if (arg0.getComponentType() != Double.TYPE) {
                                                                                        if (!arg0.isArray()) {
                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                if (arg0.getComponentType() != Float.TYPE) {
                                                                                    if (!arg0.isArray()) {
                                                                                        if (!arg0.isArray()) {
                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        if (arg0.getComponentType() != Double.TYPE) {
                                                                                            if (!arg0.isArray()) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.FLOAT, arg2);
                                                                                }
                                                                            }
                                                                        } else {
                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.LONG, arg2);
                                                                        }
                                                                    }
                                                                } else {
                                                                    if (arg0.getComponentType() != Integer.TYPE) {
                                                                        if (!arg0.isArray()) {
                                                                            if (!arg0.isArray()) {
                                                                                if (!arg0.isArray()) {
                                                                                    if (!arg0.isArray()) {
                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                    } else {
                                                                                        if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                        } else {
                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    if (arg0.getComponentType() != Double.TYPE) {
                                                                                        if (!arg0.isArray()) {
                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                if (arg0.getComponentType() != Float.TYPE) {
                                                                                    if (!arg0.isArray()) {
                                                                                        if (!arg0.isArray()) {
                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        if (arg0.getComponentType() != Double.TYPE) {
                                                                                            if (!arg0.isArray()) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.FLOAT, arg2);
                                                                                }
                                                                            }
                                                                        } else {
                                                                            if (arg0.getComponentType() != Long.TYPE) {
                                                                                if (!arg0.isArray()) {
                                                                                    if (!arg0.isArray()) {
                                                                                        if (!arg0.isArray()) {
                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        if (arg0.getComponentType() != Double.TYPE) {
                                                                                            if (!arg0.isArray()) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    if (arg0.getComponentType() != Float.TYPE) {
                                                                                        if (!arg0.isArray()) {
                                                                                            if (!arg0.isArray()) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Double.TYPE) {
                                                                                                if (!arg0.isArray()) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                    } else {
                                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.FLOAT, arg2);
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.LONG, arg2);
                                                                            }
                                                                        }
                                                                    } else {
                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.INT, arg2);
                                                                    }
                                                                }
                                                            } else {
                                                                if (arg0.getComponentType() != Short.TYPE) {
                                                                    if (!arg0.isArray()) {
                                                                        if (!arg0.isArray()) {
                                                                            if (!arg0.isArray()) {
                                                                                if (!arg0.isArray()) {
                                                                                    if (!arg0.isArray()) {
                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                    } else {
                                                                                        if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                        } else {
                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    if (arg0.getComponentType() != Double.TYPE) {
                                                                                        if (!arg0.isArray()) {
                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                if (arg0.getComponentType() != Float.TYPE) {
                                                                                    if (!arg0.isArray()) {
                                                                                        if (!arg0.isArray()) {
                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        if (arg0.getComponentType() != Double.TYPE) {
                                                                                            if (!arg0.isArray()) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.FLOAT, arg2);
                                                                                }
                                                                            }
                                                                        } else {
                                                                            if (arg0.getComponentType() != Long.TYPE) {
                                                                                if (!arg0.isArray()) {
                                                                                    if (!arg0.isArray()) {
                                                                                        if (!arg0.isArray()) {
                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        if (arg0.getComponentType() != Double.TYPE) {
                                                                                            if (!arg0.isArray()) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    if (arg0.getComponentType() != Float.TYPE) {
                                                                                        if (!arg0.isArray()) {
                                                                                            if (!arg0.isArray()) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Double.TYPE) {
                                                                                                if (!arg0.isArray()) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                    } else {
                                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.FLOAT, arg2);
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.LONG, arg2);
                                                                            }
                                                                        }
                                                                    } else {
                                                                        if (arg0.getComponentType() != Integer.TYPE) {
                                                                            if (!arg0.isArray()) {
                                                                                if (!arg0.isArray()) {
                                                                                    if (!arg0.isArray()) {
                                                                                        if (!arg0.isArray()) {
                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        if (arg0.getComponentType() != Double.TYPE) {
                                                                                            if (!arg0.isArray()) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    if (arg0.getComponentType() != Float.TYPE) {
                                                                                        if (!arg0.isArray()) {
                                                                                            if (!arg0.isArray()) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Double.TYPE) {
                                                                                                if (!arg0.isArray()) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                    } else {
                                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.FLOAT, arg2);
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                if (arg0.getComponentType() != Long.TYPE) {
                                                                                    if (!arg0.isArray()) {
                                                                                        if (!arg0.isArray()) {
                                                                                            if (!arg0.isArray()) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Double.TYPE) {
                                                                                                if (!arg0.isArray()) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                    } else {
                                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        if (arg0.getComponentType() != Float.TYPE) {
                                                                                            if (!arg0.isArray()) {
                                                                                                if (!arg0.isArray()) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                    } else {
                                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Double.TYPE) {
                                                                                                    if (!arg0.isArray()) {
                                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                    } else {
                                                                                                        if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                        } else {
                                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                        }
                                                                                                    }
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.FLOAT, arg2);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.LONG, arg2);
                                                                                }
                                                                            }
                                                                        } else {
                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.INT, arg2);
                                                                        }
                                                                    }
                                                                } else {
                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.SHORT, arg2);
                                                                }
                                                            }
                                                        } else {
                                                            if (arg0.getComponentType() != Byte.TYPE) {
                                                                if (!arg0.isArray()) {
                                                                    if (!arg0.isArray()) {
                                                                        if (!arg0.isArray()) {
                                                                            if (!arg0.isArray()) {
                                                                                if (!arg0.isArray()) {
                                                                                    if (!arg0.isArray()) {
                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                    } else {
                                                                                        if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                        } else {
                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    if (arg0.getComponentType() != Double.TYPE) {
                                                                                        if (!arg0.isArray()) {
                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                if (arg0.getComponentType() != Float.TYPE) {
                                                                                    if (!arg0.isArray()) {
                                                                                        if (!arg0.isArray()) {
                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        if (arg0.getComponentType() != Double.TYPE) {
                                                                                            if (!arg0.isArray()) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.FLOAT, arg2);
                                                                                }
                                                                            }
                                                                        } else {
                                                                            if (arg0.getComponentType() != Long.TYPE) {
                                                                                if (!arg0.isArray()) {
                                                                                    if (!arg0.isArray()) {
                                                                                        if (!arg0.isArray()) {
                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        if (arg0.getComponentType() != Double.TYPE) {
                                                                                            if (!arg0.isArray()) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    if (arg0.getComponentType() != Float.TYPE) {
                                                                                        if (!arg0.isArray()) {
                                                                                            if (!arg0.isArray()) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Double.TYPE) {
                                                                                                if (!arg0.isArray()) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                    } else {
                                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.FLOAT, arg2);
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.LONG, arg2);
                                                                            }
                                                                        }
                                                                    } else {
                                                                        if (arg0.getComponentType() != Integer.TYPE) {
                                                                            if (!arg0.isArray()) {
                                                                                if (!arg0.isArray()) {
                                                                                    if (!arg0.isArray()) {
                                                                                        if (!arg0.isArray()) {
                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        if (arg0.getComponentType() != Double.TYPE) {
                                                                                            if (!arg0.isArray()) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    if (arg0.getComponentType() != Float.TYPE) {
                                                                                        if (!arg0.isArray()) {
                                                                                            if (!arg0.isArray()) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Double.TYPE) {
                                                                                                if (!arg0.isArray()) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                    } else {
                                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.FLOAT, arg2);
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                if (arg0.getComponentType() != Long.TYPE) {
                                                                                    if (!arg0.isArray()) {
                                                                                        if (!arg0.isArray()) {
                                                                                            if (!arg0.isArray()) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Double.TYPE) {
                                                                                                if (!arg0.isArray()) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                    } else {
                                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        if (arg0.getComponentType() != Float.TYPE) {
                                                                                            if (!arg0.isArray()) {
                                                                                                if (!arg0.isArray()) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                    } else {
                                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Double.TYPE) {
                                                                                                    if (!arg0.isArray()) {
                                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                    } else {
                                                                                                        if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                        } else {
                                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                        }
                                                                                                    }
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.FLOAT, arg2);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.LONG, arg2);
                                                                                }
                                                                            }
                                                                        } else {
                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.INT, arg2);
                                                                        }
                                                                    }
                                                                } else {
                                                                    if (arg0.getComponentType() != Short.TYPE) {
                                                                        if (!arg0.isArray()) {
                                                                            if (!arg0.isArray()) {
                                                                                if (!arg0.isArray()) {
                                                                                    if (!arg0.isArray()) {
                                                                                        if (!arg0.isArray()) {
                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        if (arg0.getComponentType() != Double.TYPE) {
                                                                                            if (!arg0.isArray()) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    if (arg0.getComponentType() != Float.TYPE) {
                                                                                        if (!arg0.isArray()) {
                                                                                            if (!arg0.isArray()) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Double.TYPE) {
                                                                                                if (!arg0.isArray()) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                    } else {
                                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.FLOAT, arg2);
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                if (arg0.getComponentType() != Long.TYPE) {
                                                                                    if (!arg0.isArray()) {
                                                                                        if (!arg0.isArray()) {
                                                                                            if (!arg0.isArray()) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Double.TYPE) {
                                                                                                if (!arg0.isArray()) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                    } else {
                                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        if (arg0.getComponentType() != Float.TYPE) {
                                                                                            if (!arg0.isArray()) {
                                                                                                if (!arg0.isArray()) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                    } else {
                                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Double.TYPE) {
                                                                                                    if (!arg0.isArray()) {
                                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                    } else {
                                                                                                        if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                        } else {
                                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                        }
                                                                                                    }
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.FLOAT, arg2);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.LONG, arg2);
                                                                                }
                                                                            }
                                                                        } else {
                                                                            if (arg0.getComponentType() != Integer.TYPE) {
                                                                                if (!arg0.isArray()) {
                                                                                    if (!arg0.isArray()) {
                                                                                        if (!arg0.isArray()) {
                                                                                            if (!arg0.isArray()) {
                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Double.TYPE) {
                                                                                                if (!arg0.isArray()) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                    } else {
                                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        if (arg0.getComponentType() != Float.TYPE) {
                                                                                            if (!arg0.isArray()) {
                                                                                                if (!arg0.isArray()) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                    } else {
                                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Double.TYPE) {
                                                                                                    if (!arg0.isArray()) {
                                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                    } else {
                                                                                                        if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                        } else {
                                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                        }
                                                                                                    }
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.FLOAT, arg2);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    if (arg0.getComponentType() != Long.TYPE) {
                                                                                        if (!arg0.isArray()) {
                                                                                            if (!arg0.isArray()) {
                                                                                                if (!arg0.isArray()) {
                                                                                                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                } else {
                                                                                                    if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                    } else {
                                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                if (arg0.getComponentType() != Double.TYPE) {
                                                                                                    if (!arg0.isArray()) {
                                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                    } else {
                                                                                                        if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                        } else {
                                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                        }
                                                                                                    }
                                                                                                } else {
                                                                                                    return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            if (arg0.getComponentType() != Float.TYPE) {
                                                                                                if (!arg0.isArray()) {
                                                                                                    if (!arg0.isArray()) {
                                                                                                        throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                    } else {
                                                                                                        if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                        } else {
                                                                                                            return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                        }
                                                                                                    }
                                                                                                } else {
                                                                                                    if (arg0.getComponentType() != Double.TYPE) {
                                                                                                        if (!arg0.isArray()) {
                                                                                                            throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                        } else {
                                                                                                            if (arg0.getComponentType() != Boolean.TYPE) {
                                                                                                                throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
                                                                                                            } else {
                                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BOOLEAN, arg2);
                                                                                                            }
                                                                                                        }
                                                                                                    } else {
                                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.DOUBLE, arg2);
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.FLOAT, arg2);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.LONG, arg2);
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.INT, arg2);
                                                                            }
                                                                        }
                                                                    } else {
                                                                        return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.SHORT, arg2);
                                                                    }
                                                                }
                                                            } else {
                                                                return new DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy.BYTE, arg2);
                                                            }
                                                        }
                                                    } else {
                                                        return new DefaultInvokerFactory_BufferMarshaller(null, arg2);
                                                    }
                                                } else {
                                                    return new DefaultInvokerFactory_BufferMarshaller(ObjectParameterType_ComponentType.DOUBLE, arg2);
                                                }
                                            } else {
                                                return new DefaultInvokerFactory_BufferMarshaller(ObjectParameterType_ComponentType.FLOAT, arg2);
                                            }
                                        } else {
                                            return new DefaultInvokerFactory_BufferMarshaller(ObjectParameterType_ComponentType.LONG, arg2);
                                        }
                                    } else {
                                        return new DefaultInvokerFactory_BufferMarshaller(ObjectParameterType_ComponentType.INT, arg2);
                                    }
                                } else {
                                    return new DefaultInvokerFactory_BufferMarshaller(ObjectParameterType_ComponentType.SHORT, arg2);
                                }
                            } else {
                                return new DefaultInvokerFactory_BufferMarshaller(ObjectParameterType_ComponentType.BYTE, arg2);
                            }
                        } else {
                            return new DefaultInvokerFactory_PointerMarshaller(arg2);
                        }
                    } else {
                        return DefaultInvokerFactory_BooleanMarshaller.INSTANCE;
                    }
                }
            }
            switch (arg1) {
                case SCHAR:
                    return new DefaultInvokerFactory_Int8Marshaller(DefaultInvokerFactory_Signed8Converter.INSTANCE);
                case UCHAR:
                    return new DefaultInvokerFactory_Int8Marshaller(DefaultInvokerFactory_Unsigned8Converter.INSTANCE);
                case SSHORT:
                    return new DefaultInvokerFactory_Int16Marshaller(DefaultInvokerFactory_Signed16Converter.INSTANCE);
                case USHORT:
                    return new DefaultInvokerFactory_Int16Marshaller(DefaultInvokerFactory_Unsigned16Converter.INSTANCE);
                case SINT:
                    return new DefaultInvokerFactory_Int32Marshaller(DefaultInvokerFactory_Signed32Converter.INSTANCE);
                case UINT:
                    return new DefaultInvokerFactory_Int32Marshaller(DefaultInvokerFactory_Unsigned32Converter.INSTANCE);
                case SLONG:
                case ULONG:
                case ADDRESS:
                    return NumberUtil.sizeof(arg1) != 4 ? DefaultInvokerFactory_Int64Marshaller.INSTANCE : new DefaultInvokerFactory_Int32Marshaller(getNumberDataConverter(arg1));
                case SLONGLONG:
                case ULONGLONG:
                    return DefaultInvokerFactory_Int64Marshaller.INSTANCE;
                case FLOAT:
                    return DefaultInvokerFactory_Float32Marshaller.INSTANCE;
                case DOUBLE:
                    return DefaultInvokerFactory_Float64Marshaller.INSTANCE;
                default:
                    throw new IllegalArgumentException(new StringBuilder().append("Unsupported parameter type: ").append(arg0).toString());
            }
        }
    }

  private static boolean isUnsigned(NativeType arg0) {
        switch (arg0) {
            case UCHAR:
            case USHORT:
            case UINT:
            case ULONG:
                return true;
            default:
            case 3:
            case 5:
            case 7:
                return false;
        }
    }

  static DataConverter getNumberDataConverter(NativeType arg0) {
        switch (arg0) {
            case SCHAR:
                return DefaultInvokerFactory_Signed8Converter.INSTANCE;
            case UCHAR:
                return DefaultInvokerFactory_Unsigned8Converter.INSTANCE;
            case SSHORT:
                return DefaultInvokerFactory_Signed16Converter.INSTANCE;
            case USHORT:
                return DefaultInvokerFactory_Unsigned16Converter.INSTANCE;
            case SINT:
                return DefaultInvokerFactory_Signed32Converter.INSTANCE;
            case UINT:
                return DefaultInvokerFactory_Unsigned32Converter.INSTANCE;
            case SLONG:
                return NumberUtil.sizeof(arg0) != 4 ? DefaultInvokerFactory_LongLongConverter.INSTANCE : DefaultInvokerFactory_Signed32Converter.INSTANCE;
            case ULONG:
            case ADDRESS:
                return NumberUtil.sizeof(arg0) != 4 ? DefaultInvokerFactory_LongLongConverter.INSTANCE : DefaultInvokerFactory_Unsigned32Converter.INSTANCE;
            case SLONGLONG:
            case ULONGLONG:
                return DefaultInvokerFactory_LongLongConverter.INSTANCE;
            case FLOAT:
                return DefaultInvokerFactory_FloatConverter.INSTANCE;
            case DOUBLE:
                return DefaultInvokerFactory_DoubleConverter.INSTANCE;
            default:
                throw new UnsupportedOperationException(new StringBuilder().append("cannot convert ").append(arg0).toString());
        }
    }

  static DefaultInvokerFactory_ResultConverter getNumberResultConverter(FromNativeType arg0) {
        if (Byte.class == arg0.effectiveJavaType()) {
            return DefaultInvokerFactory_ByteResultConverter.INSTANCE;
        } else {
            if (Byte.TYPE != arg0.effectiveJavaType()) {
                if (Short.class == arg0.effectiveJavaType()) {
                    return DefaultInvokerFactory_ShortResultConverter.INSTANCE;
                } else {
                    if (Short.TYPE != arg0.effectiveJavaType()) {
                        if (Integer.class == arg0.effectiveJavaType()) {
                            return DefaultInvokerFactory_IntegerResultConverter.INSTANCE;
                        } else {
                            if (Integer.TYPE != arg0.effectiveJavaType()) {
                                if (Long.class == arg0.effectiveJavaType()) {
                                    return DefaultInvokerFactory_LongResultConverter.INSTANCE;
                                } else {
                                    if (Long.TYPE != arg0.effectiveJavaType()) {
                                        if (Float.class == arg0.effectiveJavaType()) {
                                            return DefaultInvokerFactory_FloatResultConverter.INSTANCE;
                                        } else {
                                            if (Float.TYPE != arg0.effectiveJavaType()) {
                                                if (Double.class == arg0.effectiveJavaType()) {
                                                    return DefaultInvokerFactory_DoubleResultConverter.INSTANCE;
                                                } else {
                                                    if (Double.TYPE != arg0.effectiveJavaType()) {
                                                        if (Address.class != arg0.effectiveJavaType()) {
                                                            throw new UnsupportedOperationException(new StringBuilder().append("cannot convert to ").append(arg0.effectiveJavaType()).toString());
                                                        } else {
                                                            return DefaultInvokerFactory_AddressResultConverter.INSTANCE;
                                                        }
                                                    } else {
                                                        return DefaultInvokerFactory_DoubleResultConverter.INSTANCE;
                                                    }
                                                }
                                            } else {
                                                return DefaultInvokerFactory_FloatResultConverter.INSTANCE;
                                            }
                                        }
                                    } else {
                                        return DefaultInvokerFactory_LongResultConverter.INSTANCE;
                                    }
                                }
                            } else {
                                return DefaultInvokerFactory_IntegerResultConverter.INSTANCE;
                            }
                        }
                    } else {
                        return DefaultInvokerFactory_ShortResultConverter.INSTANCE;
                    }
                }
            } else {
                return DefaultInvokerFactory_ByteResultConverter.INSTANCE;
            }
        }
    }

}