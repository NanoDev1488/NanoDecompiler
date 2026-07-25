// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.NumberUtil
package jnr.ffi.provider.jffi;

import com.kenai.jffi.Type;
import jnr.ffi.NativeType;
import jnr.ffi.provider.SigType;
import jnr.ffi.provider.jffi.SkinnyMethodAdapter;
import org.objectweb.asm.Label;

public final class NumberUtil {

  private NumberUtil() { // было: <init>
        super();
    }

  static Class getBoxedClass(Class arg0) {
        if (arg0.isPrimitive()) {
            if (Void.TYPE != arg0) {
                if (Byte.TYPE != arg0) {
                    if (Character.TYPE != arg0) {
                        if (Short.TYPE != arg0) {
                            if (Integer.TYPE != arg0) {
                                if (Long.TYPE != arg0) {
                                    if (Float.TYPE != arg0) {
                                        if (Double.TYPE != arg0) {
                                            if (Boolean.TYPE != arg0) {
                                                throw new IllegalArgumentException("unknown primitive class");
                                            } else {
                                                return Boolean.class;
                                            }
                                        } else {
                                            return Double.class;
                                        }
                                    } else {
                                        return Float.class;
                                    }
                                } else {
                                    return Long.class;
                                }
                            } else {
                                return Integer.class;
                            }
                        } else {
                            return Short.class;
                        }
                    } else {
                        return Character.class;
                    }
                } else {
                    return Byte.class;
                }
            } else {
                return Void.class;
            }
        } else {
            return arg0;
        }
    }

  static Class getPrimitiveClass(Class arg0) {
        if (Void.class != arg0) {
            if (Boolean.class != arg0) {
                if (Byte.class != arg0) {
                    if (Character.class != arg0) {
                        if (Short.class != arg0) {
                            if (Integer.class != arg0) {
                                if (Long.class != arg0) {
                                    if (Float.class != arg0) {
                                        if (Double.class != arg0) {
                                            if (!arg0.isPrimitive()) {
                                                throw new IllegalArgumentException("unsupported number class");
                                            } else {
                                                return arg0;
                                            }
                                        } else {
                                            return Double.TYPE;
                                        }
                                    } else {
                                        return Float.TYPE;
                                    }
                                } else {
                                    return Long.TYPE;
                                }
                            } else {
                                return Integer.TYPE;
                            }
                        } else {
                            return Short.TYPE;
                        }
                    } else {
                        return Character.TYPE;
                    }
                } else {
                    return Byte.TYPE;
                }
            } else {
                return Boolean.TYPE;
            }
        } else {
            return Void.TYPE;
        }
    }

  public static boolean isPrimitiveInt(Class arg0) {
        return Byte.TYPE == arg0 ? 1 : Character.TYPE == arg0 ? 1 : Short.TYPE == arg0 ? 1 : Integer.TYPE == arg0 ? 1 : Boolean.TYPE == arg0;
    }

  public static void widen(SkinnyMethodAdapter arg0, Class arg1, Class arg2) {
        if (Long.TYPE != arg2) {
            if (Boolean.TYPE == arg2) {
                if (Boolean.TYPE != arg1) {
                    if (isPrimitiveInt(arg1)) {
                        arg0.iconst_1();
                        arg0.iand();
                    }
                }
            }
        } else {
            if (Long.TYPE == arg1) {
                if (Boolean.TYPE == arg2) {
                    if (Boolean.TYPE != arg1) {
                        if (isPrimitiveInt(arg1)) {
                            arg0.iconst_1();
                            arg0.iand();
                        }
                    }
                }
            } else {
                if (!isPrimitiveInt(arg1)) {
                    if (Boolean.TYPE == arg2) {
                        if (Boolean.TYPE != arg1) {
                            if (isPrimitiveInt(arg1)) {
                                arg0.iconst_1();
                                arg0.iand();
                            }
                        }
                    }
                } else {
                    arg0.i2l();
                }
            }
        }
    }

  public static void widen(SkinnyMethodAdapter arg0, Class arg1, Class arg2, NativeType arg3) {
        if (isPrimitiveInt(arg1)) {
            if (arg3 != NativeType.UCHAR) {
                if (arg3 == NativeType.USHORT) {
                    arg0.pushInt(65535);
                    arg0.iand();
                }
            } else {
                arg0.pushInt(255);
                arg0.iand();
            }
            if (Long.TYPE == arg2) {
                arg0.i2l();
                switch (arg3) {
                    case UINT:
                    case ULONG:
                    case ADDRESS:
                        if (sizeof(arg3) >= 8) {
                            break;
                        }
                        arg0.ldc(Long.valueOf(4294967295L));
                        arg0.land();
                    default:
                }
            }
        }
    }

  public static void narrow(SkinnyMethodAdapter arg0, Class arg1, Class arg2) {
        if (!arg1.equals(arg2)) {
            if (Byte.TYPE == arg2) {
                if (Long.TYPE == arg1) {
                    arg0.l2i();
                }
                if (Byte.TYPE != arg2) {
                    if (Short.TYPE != arg2) {
                        if (Character.TYPE == arg2) {
                            arg0.i2c();
                        }
                    } else {
                        arg0.i2s();
                    }
                } else {
                    arg0.i2b();
                }
            } else {
                if (Short.TYPE == arg2) {
                    if (Long.TYPE == arg1) {
                        arg0.l2i();
                    }
                    if (Byte.TYPE != arg2) {
                        if (Short.TYPE != arg2) {
                            if (Character.TYPE == arg2) {
                                arg0.i2c();
                            }
                        } else {
                            arg0.i2s();
                        }
                    } else {
                        arg0.i2b();
                    }
                } else {
                    if (Character.TYPE == arg2) {
                        if (Long.TYPE == arg1) {
                            arg0.l2i();
                        }
                        if (Byte.TYPE != arg2) {
                            if (Short.TYPE != arg2) {
                                if (Character.TYPE == arg2) {
                                    arg0.i2c();
                                }
                            } else {
                                arg0.i2s();
                            }
                        } else {
                            arg0.i2b();
                        }
                    } else {
                        if (Integer.TYPE != arg2) {
                            if (Boolean.TYPE == arg2) {
                                Label var3 = new Label();
                                Label var4 = new Label();
                                if (Long.TYPE != arg1) {
                                    arg0.ifeq(var3);
                                    arg0.iconst_1();
                                    arg0.go_to(var4);
                                    arg0.label(var3);
                                    arg0.iconst_0();
                                    arg0.label(var4);
                                } else {
                                    arg0.lconst_0();
                                    arg0.lcmp();
                                    arg0.ifeq(var3);
                                    arg0.iconst_1();
                                    arg0.go_to(var4);
                                    arg0.label(var3);
                                    arg0.iconst_0();
                                    arg0.label(var4);
                                }
                            }
                        } else {
                            if (Long.TYPE == arg1) {
                                arg0.l2i();
                            }
                            if (Byte.TYPE != arg2) {
                                if (Short.TYPE != arg2) {
                                    if (Character.TYPE == arg2) {
                                        arg0.i2c();
                                    }
                                } else {
                                    arg0.i2s();
                                }
                            } else {
                                arg0.i2b();
                            }
                        }
                    }
                }
            }
        }
    }

  public static void convertPrimitive(SkinnyMethodAdapter arg0, Class arg1, Class arg2) {
        narrow(arg0, arg1, arg2);
        widen(arg0, arg1, arg2);
    }

  public static void convertPrimitive(SkinnyMethodAdapter arg0, Class arg1, Class arg2, NativeType arg3) {
        if (Boolean.TYPE != arg2) {
            switch (arg3) {
                case SCHAR:
                    narrow(arg0, arg1, Byte.TYPE);
                    widen(arg0, Byte.TYPE, arg2);
                    break;
                case SSHORT:
                    narrow(arg0, arg1, Short.TYPE);
                    widen(arg0, Short.TYPE, arg2);
                    break;
                case SINT:
                    narrow(arg0, arg1, Integer.TYPE);
                    widen(arg0, Integer.TYPE, arg2);
                    break;
                case UCHAR:
                    narrow(arg0, arg1, Integer.TYPE);
                    arg0.pushInt(255);
                    arg0.iand();
                    widen(arg0, Integer.TYPE, arg2);
                    break;
                case USHORT:
                    narrow(arg0, arg1, Integer.TYPE);
                    arg0.pushInt(65535);
                    arg0.iand();
                    widen(arg0, Integer.TYPE, arg2);
                    break;
                case UINT:
                case ULONG:
                case ADDRESS:
                    if (sizeof(arg3) > 4) {
                        widen(arg0, arg1, arg2);
                        break;
                    } else {
                        narrow(arg0, arg1, Integer.TYPE);
                        if (Long.TYPE != arg2) {
                            break;
                        }
                        arg0.i2l();
                        arg0.ldc(Long.valueOf(4294967295L));
                        arg0.land();
                        break;
                    }
                case FLOAT:
                case DOUBLE:
                    break;
                default:
                    narrow(arg0, arg1, arg2);
                    widen(arg0, arg1, arg2);
            }
        } else {
            narrow(arg0, arg1, arg2);
            return;
        }
    }

  static int sizeof(SigType arg0) {
        return sizeof(arg0.getNativeType());
    }

  static int sizeof(NativeType arg0) {
        switch (arg0) {
            case SCHAR:
                return Type.SCHAR.size();
            case UCHAR:
                return Type.UCHAR.size();
            case SSHORT:
                return Type.SSHORT.size();
            case USHORT:
                return Type.USHORT.size();
            case SINT:
                return Type.SINT.size();
            case UINT:
                return Type.UINT.size();
            case SLONG:
                return Type.SLONG.size();
            case ULONG:
                return Type.ULONG.size();
            case SLONGLONG:
                return Type.SLONG_LONG.size();
            case ULONGLONG:
                return Type.ULONG_LONG.size();
            case FLOAT:
                return Type.FLOAT.size();
            case DOUBLE:
                return Type.DOUBLE.size();
            case ADDRESS:
                return Type.POINTER.size();
            case VOID:
                return 0;
            default:
                throw new UnsupportedOperationException(new StringBuilder().append("cannot determine size of ").append(arg0).toString());
        }
    }

}