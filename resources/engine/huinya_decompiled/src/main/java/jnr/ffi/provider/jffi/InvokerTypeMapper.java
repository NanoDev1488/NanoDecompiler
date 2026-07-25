// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.InvokerTypeMapper
package jnr.ffi.provider.jffi;

import java.lang.reflect.Method;
import java.util.EnumSet;
import java.util.Set;
import jnr.ffi.NativeLong;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.Struct;
import jnr.ffi.Type;
import jnr.ffi.annotations.Delegate;
import jnr.ffi.byref.ByReference;
import jnr.ffi.mapper.AbstractSignatureTypeMapper;
import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.mapper.FromNativeConverter;
import jnr.ffi.mapper.FromNativeType;
import jnr.ffi.mapper.FromNativeTypes;
import jnr.ffi.mapper.SignatureType;
import jnr.ffi.mapper.SignatureTypeMapper;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter;
import jnr.ffi.mapper.ToNativeType;
import jnr.ffi.mapper.ToNativeTypes;
import jnr.ffi.provider.ParameterFlags;
import jnr.ffi.provider.converters.BoxedBooleanArrayParameterConverter;
import jnr.ffi.provider.converters.BoxedByteArrayParameterConverter;
import jnr.ffi.provider.converters.BoxedDoubleArrayParameterConverter;
import jnr.ffi.provider.converters.BoxedFloatArrayParameterConverter;
import jnr.ffi.provider.converters.BoxedIntegerArrayParameterConverter;
import jnr.ffi.provider.converters.BoxedLong32ArrayParameterConverter;
import jnr.ffi.provider.converters.BoxedLong64ArrayParameterConverter;
import jnr.ffi.provider.converters.BoxedShortArrayParameterConverter;
import jnr.ffi.provider.converters.ByReferenceParameterConverter;
import jnr.ffi.provider.converters.CharSequenceArrayParameterConverter;
import jnr.ffi.provider.converters.CharSequenceParameterConverter;
import jnr.ffi.provider.converters.EnumConverter;
import jnr.ffi.provider.converters.EnumSetConverter;
import jnr.ffi.provider.converters.Long32ArrayParameterConverter;
import jnr.ffi.provider.converters.NativeLong32ArrayParameterConverter;
import jnr.ffi.provider.converters.NativeLong64ArrayParameterConverter;
import jnr.ffi.provider.converters.NativeLongConverter;
import jnr.ffi.provider.converters.Pointer32ArrayParameterConverter;
import jnr.ffi.provider.converters.Pointer64ArrayParameterConverter;
import jnr.ffi.provider.converters.StringBufferParameterConverter;
import jnr.ffi.provider.converters.StringBuilderParameterConverter;
import jnr.ffi.provider.converters.StringResultConverter;
import jnr.ffi.provider.converters.StructArrayParameterConverter;
import jnr.ffi.provider.converters.StructByReferenceToNativeConverter;
import jnr.ffi.provider.jffi.AsmClassLoader;
import jnr.ffi.provider.jffi.ClosureFromNativeConverter;
import jnr.ffi.provider.jffi.NativeClosureManager;
import jnr.ffi.provider.jffi.StructByReferenceResultConverterFactory;
import jnr.ffi.provider.jffi.Types;

final class InvokerTypeMapper extends AbstractSignatureTypeMapper implements SignatureTypeMapper {

    // ---- поля ----
  private final NativeClosureManager closureManager;
  private final AsmClassLoader classLoader;
  private final StructByReferenceResultConverterFactory structResultConverterFactory;

  public InvokerTypeMapper(NativeClosureManager arg0, AsmClassLoader arg1, boolean arg2) { // было: <init>
        super();
        closureManager = arg0;
        classLoader = arg1;
        structResultConverterFactory = new StructByReferenceResultConverterFactory(arg1, arg2);
    }

  public FromNativeConverter getFromNativeConverter(SignatureType arg0, FromNativeContext arg1) {
        if (!Enum.class.isAssignableFrom(arg0.getDeclaredType())) {
            if (!Struct.class.isAssignableFrom(arg0.getDeclaredType())) {
                if (closureManager == null) {
                    if (NativeLong.class != arg0.getDeclaredType()) {
                        if (String.class == arg0.getDeclaredType()) {
                            return StringResultConverter.getInstance(arg1);
                        } else {
                            if (CharSequence.class != arg0.getDeclaredType()) {
                                if (Set.class == arg0.getDeclaredType()) {
                                    FromNativeConverter var3 = EnumSetConverter.getFromNativeConverter(arg0, arg1);
                                    if (var3 == null) {
                                        return null;
                                    } else {
                                        return var3;
                                    }
                                } else {
                                    if (EnumSet.class != arg0.getDeclaredType()) {
                                        return null;
                                    } else {
                                        FromNativeConverter var3 = EnumSetConverter.getFromNativeConverter(arg0, arg1);
                                        if (var3 == null) {
                                            return null;
                                        } else {
                                            return var3;
                                        }
                                    }
                                }
                            } else {
                                return StringResultConverter.getInstance(arg1);
                            }
                        }
                    } else {
                        return NativeLongConverter.getInstance();
                    }
                } else {
                    if (!isDelegate(arg0.getDeclaredType())) {
                        if (NativeLong.class != arg0.getDeclaredType()) {
                            if (String.class == arg0.getDeclaredType()) {
                                return StringResultConverter.getInstance(arg1);
                            } else {
                                if (CharSequence.class != arg0.getDeclaredType()) {
                                    if (Set.class == arg0.getDeclaredType()) {
                                        FromNativeConverter var3 = EnumSetConverter.getFromNativeConverter(arg0, arg1);
                                        if (var3 == null) {
                                            return null;
                                        } else {
                                            return var3;
                                        }
                                    } else {
                                        if (EnumSet.class != arg0.getDeclaredType()) {
                                            return null;
                                        } else {
                                            FromNativeConverter var3 = EnumSetConverter.getFromNativeConverter(arg0, arg1);
                                            if (var3 == null) {
                                                return null;
                                            } else {
                                                return var3;
                                            }
                                        }
                                    }
                                } else {
                                    return StringResultConverter.getInstance(arg1);
                                }
                            }
                        } else {
                            return NativeLongConverter.getInstance();
                        }
                    } else {
                        return ClosureFromNativeConverter.getInstance(arg1.getRuntime(), arg0, classLoader, this);
                    }
                }
            } else {
                return structResultConverterFactory.get(arg0.getDeclaredType().asSubclass(Struct.class), arg1);
            }
        } else {
            return EnumConverter.getInstance(arg0.getDeclaredType().asSubclass(Enum.class));
        }
    }

  public ToNativeConverter getToNativeConverter(SignatureType arg0, ToNativeContext arg1) {
        Class var3 = arg0.getDeclaredType();
        if (!Enum.class.isAssignableFrom(var3)) {
            if (!Set.class.isAssignableFrom(var3)) {
                if (!isDelegate(var3)) {
                    if (!ByReference.class.isAssignableFrom(var3)) {
                        if (!Struct.class.isAssignableFrom(var3)) {
                            if (!NativeLong.class.isAssignableFrom(var3)) {
                                if (!StringBuilder.class.isAssignableFrom(var3)) {
                                    if (!StringBuffer.class.isAssignableFrom(var3)) {
                                        if (!CharSequence.class.isAssignableFrom(var3)) {
                                            if (!Byte[].class.isAssignableFrom(var3)) {
                                                if (!Short[].class.isAssignableFrom(var3)) {
                                                    if (!Integer[].class.isAssignableFrom(var3)) {
                                                        if (!Long[].class.isAssignableFrom(var3)) {
                                                            if (!NativeLong[].class.isAssignableFrom(var3)) {
                                                                if (!Float[].class.isAssignableFrom(var3)) {
                                                                    if (!Double[].class.isAssignableFrom(var3)) {
                                                                        if (!Boolean[].class.isAssignableFrom(var3)) {
                                                                            if (!var3.isArray()) {
                                                                                if (!long[].class.isAssignableFrom(var3)) {
                                                                                    if (!var3.isArray()) {
                                                                                        if (!var3.isArray()) {
                                                                                            return null;
                                                                                        } else {
                                                                                            if (!CharSequence.class.isAssignableFrom(var3.getComponentType())) {
                                                                                                return null;
                                                                                            } else {
                                                                                                return CharSequenceArrayParameterConverter.getInstance(arg1);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        if (!Struct.class.isAssignableFrom(var3.getComponentType())) {
                                                                                            if (!var3.isArray()) {
                                                                                                return null;
                                                                                            } else {
                                                                                                if (!CharSequence.class.isAssignableFrom(var3.getComponentType())) {
                                                                                                    return null;
                                                                                                } else {
                                                                                                    return CharSequenceArrayParameterConverter.getInstance(arg1);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            return StructArrayParameterConverter.getInstance(arg1, var3.getComponentType());
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    if (Types.getType(arg1.getRuntime(), var3.getComponentType(), arg1.getAnnotations()).size() != 4) {
                                                                                        if (!var3.isArray()) {
                                                                                            if (!var3.isArray()) {
                                                                                                return null;
                                                                                            } else {
                                                                                                if (!CharSequence.class.isAssignableFrom(var3.getComponentType())) {
                                                                                                    return null;
                                                                                                } else {
                                                                                                    return CharSequenceArrayParameterConverter.getInstance(arg1);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            if (!Struct.class.isAssignableFrom(var3.getComponentType())) {
                                                                                                if (!var3.isArray()) {
                                                                                                    return null;
                                                                                                } else {
                                                                                                    if (!CharSequence.class.isAssignableFrom(var3.getComponentType())) {
                                                                                                        return null;
                                                                                                    } else {
                                                                                                        return CharSequenceArrayParameterConverter.getInstance(arg1);
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                return StructArrayParameterConverter.getInstance(arg1, var3.getComponentType());
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        return Long32ArrayParameterConverter.getInstance(arg1);
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                if (!Pointer.class.isAssignableFrom(var3.getComponentType())) {
                                                                                    if (!long[].class.isAssignableFrom(var3)) {
                                                                                        if (!var3.isArray()) {
                                                                                            if (!var3.isArray()) {
                                                                                                return null;
                                                                                            } else {
                                                                                                if (!CharSequence.class.isAssignableFrom(var3.getComponentType())) {
                                                                                                    return null;
                                                                                                } else {
                                                                                                    return CharSequenceArrayParameterConverter.getInstance(arg1);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            if (!Struct.class.isAssignableFrom(var3.getComponentType())) {
                                                                                                if (!var3.isArray()) {
                                                                                                    return null;
                                                                                                } else {
                                                                                                    if (!CharSequence.class.isAssignableFrom(var3.getComponentType())) {
                                                                                                        return null;
                                                                                                    } else {
                                                                                                        return CharSequenceArrayParameterConverter.getInstance(arg1);
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                return StructArrayParameterConverter.getInstance(arg1, var3.getComponentType());
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        if (Types.getType(arg1.getRuntime(), var3.getComponentType(), arg1.getAnnotations()).size() != 4) {
                                                                                            if (!var3.isArray()) {
                                                                                                if (!var3.isArray()) {
                                                                                                    return null;
                                                                                                } else {
                                                                                                    if (!CharSequence.class.isAssignableFrom(var3.getComponentType())) {
                                                                                                        return null;
                                                                                                    } else {
                                                                                                        return CharSequenceArrayParameterConverter.getInstance(arg1);
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                if (!Struct.class.isAssignableFrom(var3.getComponentType())) {
                                                                                                    if (!var3.isArray()) {
                                                                                                        return null;
                                                                                                    } else {
                                                                                                        if (!CharSequence.class.isAssignableFrom(var3.getComponentType())) {
                                                                                                            return null;
                                                                                                        } else {
                                                                                                            return CharSequenceArrayParameterConverter.getInstance(arg1);
                                                                                                        }
                                                                                                    }
                                                                                                } else {
                                                                                                    return StructArrayParameterConverter.getInstance(arg1, var3.getComponentType());
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            return Long32ArrayParameterConverter.getInstance(arg1);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    return arg1.getRuntime().addressSize() != 4 ? Pointer64ArrayParameterConverter.getInstance(arg1) : Pointer32ArrayParameterConverter.getInstance(arg1);
                                                                                }
                                                                            }
                                                                        } else {
                                                                            return BoxedBooleanArrayParameterConverter.getInstance(arg1);
                                                                        }
                                                                    } else {
                                                                        return BoxedDoubleArrayParameterConverter.getInstance(arg1);
                                                                    }
                                                                } else {
                                                                    return BoxedFloatArrayParameterConverter.getInstance(arg1);
                                                                }
                                                            } else {
                                                                return Types.getType(arg1.getRuntime(), var3.getComponentType(), arg1.getAnnotations()).size() != 4 ? NativeLong64ArrayParameterConverter.getInstance(arg1) : NativeLong32ArrayParameterConverter.getInstance(arg1);
                                                            }
                                                        } else {
                                                            return Types.getType(arg1.getRuntime(), var3.getComponentType(), arg1.getAnnotations()).size() != 4 ? BoxedLong64ArrayParameterConverter.getInstance(arg1) : BoxedLong32ArrayParameterConverter.getInstance(arg1);
                                                        }
                                                    } else {
                                                        return BoxedIntegerArrayParameterConverter.getInstance(arg1);
                                                    }
                                                } else {
                                                    return BoxedShortArrayParameterConverter.getInstance(arg1);
                                                }
                                            } else {
                                                return BoxedByteArrayParameterConverter.getInstance(arg1);
                                            }
                                        } else {
                                            return CharSequenceParameterConverter.getInstance(arg1);
                                        }
                                    } else {
                                        return StringBufferParameterConverter.getInstance(ParameterFlags.parse(arg1.getAnnotations()), arg1);
                                    }
                                } else {
                                    return StringBuilderParameterConverter.getInstance(ParameterFlags.parse(arg1.getAnnotations()), arg1);
                                }
                            } else {
                                return NativeLongConverter.getInstance();
                            }
                        } else {
                            return StructByReferenceToNativeConverter.getInstance(arg1);
                        }
                    } else {
                        return ByReferenceParameterConverter.getInstance(arg1);
                    }
                } else {
                    return closureManager.newClosureSite(var3);
                }
            } else {
                ToNativeConverter var4 = EnumSetConverter.getToNativeConverter(arg0, arg1);
                if (var4 == null) {
                    if (!isDelegate(var3)) {
                        if (!ByReference.class.isAssignableFrom(var3)) {
                            if (!Struct.class.isAssignableFrom(var3)) {
                                if (!NativeLong.class.isAssignableFrom(var3)) {
                                    if (!StringBuilder.class.isAssignableFrom(var3)) {
                                        if (!StringBuffer.class.isAssignableFrom(var3)) {
                                            if (!CharSequence.class.isAssignableFrom(var3)) {
                                                if (!Byte[].class.isAssignableFrom(var3)) {
                                                    if (!Short[].class.isAssignableFrom(var3)) {
                                                        if (!Integer[].class.isAssignableFrom(var3)) {
                                                            if (!Long[].class.isAssignableFrom(var3)) {
                                                                if (!NativeLong[].class.isAssignableFrom(var3)) {
                                                                    if (!Float[].class.isAssignableFrom(var3)) {
                                                                        if (!Double[].class.isAssignableFrom(var3)) {
                                                                            if (!Boolean[].class.isAssignableFrom(var3)) {
                                                                                if (!var3.isArray()) {
                                                                                    if (!long[].class.isAssignableFrom(var3)) {
                                                                                        if (!var3.isArray()) {
                                                                                            if (!var3.isArray()) {
                                                                                                return null;
                                                                                            } else {
                                                                                                if (!CharSequence.class.isAssignableFrom(var3.getComponentType())) {
                                                                                                    return null;
                                                                                                } else {
                                                                                                    return CharSequenceArrayParameterConverter.getInstance(arg1);
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            if (!Struct.class.isAssignableFrom(var3.getComponentType())) {
                                                                                                if (!var3.isArray()) {
                                                                                                    return null;
                                                                                                } else {
                                                                                                    if (!CharSequence.class.isAssignableFrom(var3.getComponentType())) {
                                                                                                        return null;
                                                                                                    } else {
                                                                                                        return CharSequenceArrayParameterConverter.getInstance(arg1);
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                return StructArrayParameterConverter.getInstance(arg1, var3.getComponentType());
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        if (Types.getType(arg1.getRuntime(), var3.getComponentType(), arg1.getAnnotations()).size() != 4) {
                                                                                            if (!var3.isArray()) {
                                                                                                if (!var3.isArray()) {
                                                                                                    return null;
                                                                                                } else {
                                                                                                    if (!CharSequence.class.isAssignableFrom(var3.getComponentType())) {
                                                                                                        return null;
                                                                                                    } else {
                                                                                                        return CharSequenceArrayParameterConverter.getInstance(arg1);
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                if (!Struct.class.isAssignableFrom(var3.getComponentType())) {
                                                                                                    if (!var3.isArray()) {
                                                                                                        return null;
                                                                                                    } else {
                                                                                                        if (!CharSequence.class.isAssignableFrom(var3.getComponentType())) {
                                                                                                            return null;
                                                                                                        } else {
                                                                                                            return CharSequenceArrayParameterConverter.getInstance(arg1);
                                                                                                        }
                                                                                                    }
                                                                                                } else {
                                                                                                    return StructArrayParameterConverter.getInstance(arg1, var3.getComponentType());
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            return Long32ArrayParameterConverter.getInstance(arg1);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    if (!Pointer.class.isAssignableFrom(var3.getComponentType())) {
                                                                                        if (!long[].class.isAssignableFrom(var3)) {
                                                                                            if (!var3.isArray()) {
                                                                                                if (!var3.isArray()) {
                                                                                                    return null;
                                                                                                } else {
                                                                                                    if (!CharSequence.class.isAssignableFrom(var3.getComponentType())) {
                                                                                                        return null;
                                                                                                    } else {
                                                                                                        return CharSequenceArrayParameterConverter.getInstance(arg1);
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                if (!Struct.class.isAssignableFrom(var3.getComponentType())) {
                                                                                                    if (!var3.isArray()) {
                                                                                                        return null;
                                                                                                    } else {
                                                                                                        if (!CharSequence.class.isAssignableFrom(var3.getComponentType())) {
                                                                                                            return null;
                                                                                                        } else {
                                                                                                            return CharSequenceArrayParameterConverter.getInstance(arg1);
                                                                                                        }
                                                                                                    }
                                                                                                } else {
                                                                                                    return StructArrayParameterConverter.getInstance(arg1, var3.getComponentType());
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            if (Types.getType(arg1.getRuntime(), var3.getComponentType(), arg1.getAnnotations()).size() != 4) {
                                                                                                if (!var3.isArray()) {
                                                                                                    if (!var3.isArray()) {
                                                                                                        return null;
                                                                                                    } else {
                                                                                                        if (!CharSequence.class.isAssignableFrom(var3.getComponentType())) {
                                                                                                            return null;
                                                                                                        } else {
                                                                                                            return CharSequenceArrayParameterConverter.getInstance(arg1);
                                                                                                        }
                                                                                                    }
                                                                                                } else {
                                                                                                    if (!Struct.class.isAssignableFrom(var3.getComponentType())) {
                                                                                                        if (!var3.isArray()) {
                                                                                                            return null;
                                                                                                        } else {
                                                                                                            if (!CharSequence.class.isAssignableFrom(var3.getComponentType())) {
                                                                                                                return null;
                                                                                                            } else {
                                                                                                                return CharSequenceArrayParameterConverter.getInstance(arg1);
                                                                                                            }
                                                                                                        }
                                                                                                    } else {
                                                                                                        return StructArrayParameterConverter.getInstance(arg1, var3.getComponentType());
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                return Long32ArrayParameterConverter.getInstance(arg1);
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        return arg1.getRuntime().addressSize() != 4 ? Pointer64ArrayParameterConverter.getInstance(arg1) : Pointer32ArrayParameterConverter.getInstance(arg1);
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                return BoxedBooleanArrayParameterConverter.getInstance(arg1);
                                                                            }
                                                                        } else {
                                                                            return BoxedDoubleArrayParameterConverter.getInstance(arg1);
                                                                        }
                                                                    } else {
                                                                        return BoxedFloatArrayParameterConverter.getInstance(arg1);
                                                                    }
                                                                } else {
                                                                    return Types.getType(arg1.getRuntime(), var3.getComponentType(), arg1.getAnnotations()).size() != 4 ? NativeLong64ArrayParameterConverter.getInstance(arg1) : NativeLong32ArrayParameterConverter.getInstance(arg1);
                                                                }
                                                            } else {
                                                                return Types.getType(arg1.getRuntime(), var3.getComponentType(), arg1.getAnnotations()).size() != 4 ? BoxedLong64ArrayParameterConverter.getInstance(arg1) : BoxedLong32ArrayParameterConverter.getInstance(arg1);
                                                            }
                                                        } else {
                                                            return BoxedIntegerArrayParameterConverter.getInstance(arg1);
                                                        }
                                                    } else {
                                                        return BoxedShortArrayParameterConverter.getInstance(arg1);
                                                    }
                                                } else {
                                                    return BoxedByteArrayParameterConverter.getInstance(arg1);
                                                }
                                            } else {
                                                return CharSequenceParameterConverter.getInstance(arg1);
                                            }
                                        } else {
                                            return StringBufferParameterConverter.getInstance(ParameterFlags.parse(arg1.getAnnotations()), arg1);
                                        }
                                    } else {
                                        return StringBuilderParameterConverter.getInstance(ParameterFlags.parse(arg1.getAnnotations()), arg1);
                                    }
                                } else {
                                    return NativeLongConverter.getInstance();
                                }
                            } else {
                                return StructByReferenceToNativeConverter.getInstance(arg1);
                            }
                        } else {
                            return ByReferenceParameterConverter.getInstance(arg1);
                        }
                    } else {
                        return closureManager.newClosureSite(var3);
                    }
                } else {
                    return var4;
                }
            }
        } else {
            return EnumConverter.getInstance(var3.asSubclass(Enum.class));
        }
    }

  public FromNativeType getFromNativeType(SignatureType arg0, FromNativeContext arg1) {
        return FromNativeTypes.create(getFromNativeConverter(arg0, arg1));
    }

  public ToNativeType getToNativeType(SignatureType arg0, ToNativeContext arg1) {
        return ToNativeTypes.create(getToNativeConverter(arg0, arg1));
    }

  private static boolean isDelegate(Class arg0) {
        Method[] var1 = arg0.getMethods();
        int var2 = var1.length;
        int var3 = 0;
        while (true) {
            if (var3 >= var2) {
                return false;
            }
            Object var4 = var1[var3];
            if (var4.isAnnotationPresent(Delegate.class)) {
                break;
            }
            ++var3;
            continue;
        }
        return true;
    }

}