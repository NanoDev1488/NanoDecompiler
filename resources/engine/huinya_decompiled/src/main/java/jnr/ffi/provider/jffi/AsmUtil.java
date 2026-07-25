// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.AsmUtil
package jnr.ffi.provider.jffi;

import com.kenai.jffi.Platform;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import jnr.ffi.Address;
import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.mapper.ToNativeConverter;
import jnr.ffi.provider.FromNativeType;
import jnr.ffi.provider.ParameterFlags;
import jnr.ffi.provider.ParameterType;
import jnr.ffi.provider.SigType;
import jnr.ffi.provider.ToNativeType;
import jnr.ffi.provider.jffi.AsmBuilder;
import jnr.ffi.provider.jffi.AsmBuilder_ObjectField;
import jnr.ffi.provider.jffi.AsmClassLoader;
import jnr.ffi.provider.jffi.CodegenUtils;
import jnr.ffi.provider.jffi.LocalVariable;
import jnr.ffi.provider.jffi.NumberUtil;
import jnr.ffi.provider.jffi.SkinnyMethodAdapter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

final class AsmUtil {

  private AsmUtil() { // было: <init>
        super();
    }

  public static MethodVisitor newTraceMethodVisitor(MethodVisitor arg0) {
        MethodVisitor __stk3;
        try {
            Class var1 = Class.forName("org.objectweb.asm.util.TraceMethodVisitor").asSubclass(MethodVisitor.class);
            Constructor var2 = var1.getDeclaredConstructor(new Class[]{MethodVisitor.class});
            Object[] __obj2 = new Object[1];
            __obj2[0] = arg0;
            __stk3 = ((MethodVisitor) var2.newInstance(__obj2));
        } catch (Throwable e1) {
            Throwable var1 = e1;
            return arg0;
        }
    }

  public static ClassVisitor newTraceClassVisitor(ClassVisitor arg0, OutputStream arg1) {
        return newTraceClassVisitor(arg0, new PrintWriter(arg1, true));
    }

  public static ClassVisitor newTraceClassVisitor(ClassVisitor arg0, PrintWriter arg1) {
        ClassVisitor __stk3;
        try {
            Class var2 = Class.forName("org.objectweb.asm.util.TraceClassVisitor").asSubclass(ClassVisitor.class);
            Constructor var3 = var2.getDeclaredConstructor(new Class[]{ClassVisitor.class, PrintWriter.class});
            Object[] __obj2 = new Object[2];
            __obj2[0] = arg0;
            __obj2[1] = arg1;
            __stk3 = ((ClassVisitor) var3.newInstance(__obj2));
        } catch (Throwable e1) {
            Throwable var2 = e1;
            return arg0;
        }
    }

  public static ClassVisitor newTraceClassVisitor(PrintWriter arg0) {
        ClassVisitor __stk3;
        try {
            Class var1 = Class.forName("org.objectweb.asm.util.TraceClassVisitor").asSubclass(ClassVisitor.class);
            Constructor var2 = var1.getDeclaredConstructor(new Class[]{PrintWriter.class});
            Object[] __obj2 = new Object[1];
            __obj2[0] = arg0;
            __stk3 = ((ClassVisitor) var2.newInstance(__obj2));
        } catch (Throwable e1) {
            Throwable var1 = e1;
            throw new RuntimeException(var1);
        }
    }

  public static ClassVisitor newCheckClassAdapter(ClassVisitor arg0) {
        ClassVisitor __stk3;
        try {
            Class var1 = Class.forName("org.objectweb.asm.util.CheckClassAdapter").asSubclass(ClassVisitor.class);
            Constructor var2 = var1.getDeclaredConstructor(new Class[]{ClassVisitor.class});
            Object[] __obj2 = new Object[1];
            __obj2[0] = arg0;
            __stk3 = ((ClassVisitor) var2.newInstance(__obj2));
        } catch (Throwable e1) {
            Throwable var1 = e1;
            return arg0;
        }
    }

  public static Class unboxedReturnType(Class arg0) {
        return unboxedType(arg0);
    }

  public static Class unboxedType(Class arg0) {
        if (arg0 != Byte.class) {
            if (arg0 != Short.class) {
                if (arg0 != Integer.class) {
                    if (arg0 != Long.class) {
                        if (arg0 != Float.class) {
                            if (arg0 != Double.class) {
                                if (arg0 != Boolean.class) {
                                    if (!Pointer.class.isAssignableFrom(arg0)) {
                                        if (Address.class != arg0) {
                                            return arg0;
                                        } else {
                                            return Platform.getPlatform().addressSize() != 32 ? Long.TYPE : Integer.TYPE;
                                        }
                                    } else {
                                        return Platform.getPlatform().addressSize() != 32 ? Long.TYPE : Integer.TYPE;
                                    }
                                } else {
                                    return Boolean.TYPE;
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
            return Byte.TYPE;
        }
    }

  public static Class boxedType(Class arg0) {
        if (arg0 != Byte.TYPE) {
            if (arg0 != Short.TYPE) {
                if (arg0 != Integer.TYPE) {
                    if (arg0 != Long.TYPE) {
                        if (arg0 != Float.TYPE) {
                            if (arg0 != Double.TYPE) {
                                if (arg0 != Boolean.TYPE) {
                                    return arg0;
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
            return Byte.class;
        }
    }

  static void emitReturnOp(SkinnyMethodAdapter arg0, Class arg1) {
        if (arg1.isPrimitive()) {
            if (Long.TYPE != arg1) {
                if (Float.TYPE != arg1) {
                    if (Double.TYPE != arg1) {
                        if (Void.TYPE != arg1) {
                            arg0.ireturn();
                        } else {
                            arg0.voidreturn();
                        }
                    } else {
                        arg0.dreturn();
                    }
                } else {
                    arg0.freturn();
                }
            } else {
                arg0.lreturn();
            }
        } else {
            arg0.areturn();
        }
    }

  static int calculateLocalVariableSpace(Class arg0) {
        return Long.TYPE == arg0 ? 2 : Double.TYPE != arg0 ? 1 : 2;
    }

  static int calculateLocalVariableSpace(SigType arg0) {
        return calculateLocalVariableSpace(arg0.getDeclaredType());
    }

  static int calculateLocalVariableSpace(Class[] arg0) {
        int var1 = 0;
        int var2 = 0;
        while (var2 < arg0.length) {
            var1 = var1 + calculateLocalVariableSpace(((Class) arg0[var2]));
            ++var2;
            continue;
        }
        return var1;
    }

  static int calculateLocalVariableSpace(SigType[] arg0) {
        int var1 = 0;
        SigType[] var2 = arg0;
        int var3 = var2.length;
        int var4 = 0;
        while (var4 < var3) {
            Object var5 = var2[var4];
            var1 = var1 + calculateLocalVariableSpace(((SigType) var5));
            ++var4;
            continue;
        }
        return var1;
    }

  private static void unboxPointerOrStruct(SkinnyMethodAdapter arg0, Class arg1, Class arg2) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: ldc  #69 // jnr.ffi.provider.jffi.AsmRuntime
        //      3: invokestatic  #162 // jnr.ffi.provider.jffi.CodegenUtils.p:(Ljava/lang/Class;)Ljava/lang/String;
        //      6: getstatic  #84 // java.lang.Long.TYPE:Ljava/lang/Class;
        //      9: aload_2
        //     10: if_acmpne  18 (offset +8)
        //     13: ldc  #19 // 'longValue'
        //     15: goto  20 (offset +5)
        //     18: ldc  #17 // 'intValue'
        //     20: aload_2
        //     21: iconst_1
        //     22: anewarray  #33 // java.lang.Class
        //     25: dup
        //     26: iconst_0
        //     27: aload_1
        //     28: aastore
        //     29: invokestatic  #163 // jnr.ffi.provider.jffi.CodegenUtils.sig:(Ljava/lang/Class;[Ljava/lang/Class;)Ljava/lang/String;
        //     32: invokevirtual  #189 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokestatic:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //     35: return
    }

  static void unboxPointer(SkinnyMethodAdapter arg0, Class arg1) {
        unboxPointerOrStruct(arg0, Pointer.class, arg1);
    }

  static void unboxBoolean(SkinnyMethodAdapter arg0, Class arg1, Class arg2) {
        arg0.invokevirtual(CodegenUtils.method1942(arg1), "booleanValue", "()Z");
        NumberUtil.widen(arg0, Boolean.TYPE, arg2);
    }

  static void unboxBoolean(SkinnyMethodAdapter arg0, Class arg1) {
        unboxBoolean(arg0, Boolean.class, arg1);
    }

  static void unboxNumber(SkinnyMethodAdapter arg0, Class arg1, Class arg2, NativeType arg3) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: ldc  #42 // java.lang.Number
        //      2: aload_1
        //      3: invokevirtual  #101 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //      6: ifeq  241 (offset +235)
        //      9: getstatic  #89 // jnr.ffi.provider.jffi.AsmUtil$1.$SwitchMap$jnr$ffi$NativeType:[I
        //     12: aload_3
        //     13: invokevirtual  #123 // jnr.ffi.NativeType.ordinal:()I
        //     16: iaload
        //     17: tableswitch  default->238, 1->84, 2->84, 3->108, 4->108, 5->132, 6->132, 7->132, 8->132, 9->132, 10->188, 11->188, 12->211, 13->226
        //     84: aload_0
        //     85: aload_1
        //     86: invokestatic  #162 // jnr.ffi.provider.jffi.CodegenUtils.p:(Ljava/lang/Class;)Ljava/lang/String;
        //     89: ldc  #12 // 'byteValue'
        //     91: ldc  #4 // '()B'
        //     93: invokevirtual  #191 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokevirtual:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //     96: aload_0
        //     97: getstatic  #80 // java.lang.Byte.TYPE:Ljava/lang/Class;
        //    100: aload_2
        //    101: aload_3
        //    102: invokestatic  #165 // jnr.ffi.provider.jffi.NumberUtil.convertPrimitive:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljava/lang/Class;Ljava/lang/Class;Ljnr/ffi/NativeType;)V
        //    105: goto  238 (offset +133)
        //    108: aload_0
        //    109: aload_1
        //    110: invokestatic  #162 // jnr.ffi.provider.jffi.CodegenUtils.p:(Ljava/lang/Class;)Ljava/lang/String;
        //    113: ldc  #24 // 'shortValue'
        //    115: ldc  #9 // '()S'
        //    117: invokevirtual  #191 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokevirtual:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    120: aload_0
        //    121: getstatic  #85 // java.lang.Short.TYPE:Ljava/lang/Class;
        //    124: aload_2
        //    125: aload_3
        //    126: invokestatic  #165 // jnr.ffi.provider.jffi.NumberUtil.convertPrimitive:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljava/lang/Class;Ljava/lang/Class;Ljnr/ffi/NativeType;)V
        //    129: goto  238 (offset +109)
        //    132: aload_3
        //    133: invokestatic  #168 // jnr.ffi.provider.jffi.NumberUtil.sizeof:(Ljnr/ffi/NativeType;)I
        //    136: iconst_4
        //    137: if_icmpne  164 (offset +27)
        //    140: aload_0
        //    141: aload_1
        //    142: invokestatic  #162 // jnr.ffi.provider.jffi.CodegenUtils.p:(Ljava/lang/Class;)Ljava/lang/String;
        //    145: ldc  #17 // 'intValue'
        //    147: ldc  #7 // '()I'
        //    149: invokevirtual  #191 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokevirtual:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    152: aload_0
        //    153: getstatic  #83 // java.lang.Integer.TYPE:Ljava/lang/Class;
        //    156: aload_2
        //    157: aload_3
        //    158: invokestatic  #165 // jnr.ffi.provider.jffi.NumberUtil.convertPrimitive:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljava/lang/Class;Ljava/lang/Class;Ljnr/ffi/NativeType;)V
        //    161: goto  238 (offset +77)
        //    164: aload_0
        //    165: aload_1
        //    166: invokestatic  #162 // jnr.ffi.provider.jffi.CodegenUtils.p:(Ljava/lang/Class;)Ljava/lang/String;
        //    169: ldc  #19 // 'longValue'
        //    171: ldc  #8 // '()J'
        //    173: invokevirtual  #191 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokevirtual:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    176: aload_0
        //    177: getstatic  #84 // java.lang.Long.TYPE:Ljava/lang/Class;
        //    180: aload_2
        //    181: aload_3
        //    182: invokestatic  #165 // jnr.ffi.provider.jffi.NumberUtil.convertPrimitive:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljava/lang/Class;Ljava/lang/Class;Ljnr/ffi/NativeType;)V
        //    185: goto  238 (offset +53)
        //    188: aload_0
        //    189: aload_1
        //    190: invokestatic  #162 // jnr.ffi.provider.jffi.CodegenUtils.p:(Ljava/lang/Class;)Ljava/lang/String;
        //    193: ldc  #19 // 'longValue'
        //    195: ldc  #8 // '()J'
        //    197: invokevirtual  #191 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokevirtual:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    200: aload_0
        //    201: getstatic  #84 // java.lang.Long.TYPE:Ljava/lang/Class;
        //    204: aload_2
        //    205: invokestatic  #167 // jnr.ffi.provider.jffi.NumberUtil.narrow:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljava/lang/Class;Ljava/lang/Class;)V
        //    208: goto  238 (offset +30)
        //    211: aload_0
        //    212: aload_1
        //    213: invokestatic  #162 // jnr.ffi.provider.jffi.CodegenUtils.p:(Ljava/lang/Class;)Ljava/lang/String;
        //    216: ldc  #15 // 'floatValue'
        //    218: ldc  #6 // '()F'
        //    220: invokevirtual  #191 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokevirtual:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    223: goto  238 (offset +15)
        //    226: aload_0
        //    227: aload_1
        //    228: invokestatic  #162 // jnr.ffi.provider.jffi.CodegenUtils.p:(Ljava/lang/Class;)Ljava/lang/String;
        //    231: ldc  #14 // 'doubleValue'
        //    233: ldc  #5 // '()D'
        //    235: invokevirtual  #191 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokevirtual:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    238: goto  285 (offset +47)
        //    241: ldc  #31 // java.lang.Boolean
        //    243: aload_1
        //    244: invokevirtual  #101 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    247: ifeq  258 (offset +11)
        //    250: aload_0
        //    251: aload_2
        //    252: invokestatic  #156 // jnr.ffi.provider.jffi.AsmUtil.unboxBoolean:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljava/lang/Class;)V
        //    255: goto  285 (offset +30)
        //    258: new  #38 // java.lang.IllegalArgumentException
        //    261: dup
        //    262: new  #48 // java.lang.StringBuilder
        //    265: dup
        //    266: invokespecial  #112 // java.lang.StringBuilder.<init>:()V
        //    269: ldc  #27 // 'unsupported boxed type: '
        //    271: invokevirtual  #114 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    274: aload_1
        //    275: invokevirtual  #113 // java.lang.StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    278: invokevirtual  #115 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    281: invokespecial  #105 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    284: athrow
        //    285: return
    }

  static void unboxNumber(SkinnyMethodAdapter arg0, Class arg1, Class arg2) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: ldc  #42 // java.lang.Number
        //      2: aload_1
        //      3: invokevirtual  #101 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //      6: ifeq  168 (offset +162)
        //      9: getstatic  #80 // java.lang.Byte.TYPE:Ljava/lang/Class;
        //     12: aload_2
        //     13: if_acmpne  31 (offset +18)
        //     16: aload_0
        //     17: aload_1
        //     18: invokestatic  #162 // jnr.ffi.provider.jffi.CodegenUtils.p:(Ljava/lang/Class;)Ljava/lang/String;
        //     21: ldc  #12 // 'byteValue'
        //     23: ldc  #4 // '()B'
        //     25: invokevirtual  #191 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokevirtual:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //     28: goto  212 (offset +184)
        //     31: getstatic  #85 // java.lang.Short.TYPE:Ljava/lang/Class;
        //     34: aload_2
        //     35: if_acmpne  53 (offset +18)
        //     38: aload_0
        //     39: aload_1
        //     40: invokestatic  #162 // jnr.ffi.provider.jffi.CodegenUtils.p:(Ljava/lang/Class;)Ljava/lang/String;
        //     43: ldc  #24 // 'shortValue'
        //     45: ldc  #9 // '()S'
        //     47: invokevirtual  #191 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokevirtual:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //     50: goto  212 (offset +162)
        //     53: getstatic  #83 // java.lang.Integer.TYPE:Ljava/lang/Class;
        //     56: aload_2
        //     57: if_acmpne  75 (offset +18)
        //     60: aload_0
        //     61: aload_1
        //     62: invokestatic  #162 // jnr.ffi.provider.jffi.CodegenUtils.p:(Ljava/lang/Class;)Ljava/lang/String;
        //     65: ldc  #17 // 'intValue'
        //     67: ldc  #7 // '()I'
        //     69: invokevirtual  #191 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokevirtual:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //     72: goto  212 (offset +140)
        //     75: getstatic  #84 // java.lang.Long.TYPE:Ljava/lang/Class;
        //     78: aload_2
        //     79: if_acmpne  97 (offset +18)
        //     82: aload_0
        //     83: aload_1
        //     84: invokestatic  #162 // jnr.ffi.provider.jffi.CodegenUtils.p:(Ljava/lang/Class;)Ljava/lang/String;
        //     87: ldc  #19 // 'longValue'
        //     89: ldc  #8 // '()J'
        //     91: invokevirtual  #191 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokevirtual:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //     94: goto  212 (offset +118)
        //     97: getstatic  #82 // java.lang.Float.TYPE:Ljava/lang/Class;
        //    100: aload_2
        //    101: if_acmpne  119 (offset +18)
        //    104: aload_0
        //    105: aload_1
        //    106: invokestatic  #162 // jnr.ffi.provider.jffi.CodegenUtils.p:(Ljava/lang/Class;)Ljava/lang/String;
        //    109: ldc  #15 // 'floatValue'
        //    111: ldc  #6 // '()F'
        //    113: invokevirtual  #191 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokevirtual:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    116: goto  212 (offset +96)
        //    119: getstatic  #81 // java.lang.Double.TYPE:Ljava/lang/Class;
        //    122: aload_2
        //    123: if_acmpne  141 (offset +18)
        //    126: aload_0
        //    127: aload_1
        //    128: invokestatic  #162 // jnr.ffi.provider.jffi.CodegenUtils.p:(Ljava/lang/Class;)Ljava/lang/String;
        //    131: ldc  #14 // 'doubleValue'
        //    133: ldc  #5 // '()D'
        //    135: invokevirtual  #191 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokevirtual:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    138: goto  212 (offset +74)
        //    141: new  #38 // java.lang.IllegalArgumentException
        //    144: dup
        //    145: new  #48 // java.lang.StringBuilder
        //    148: dup
        //    149: invokespecial  #112 // java.lang.StringBuilder.<init>:()V
        //    152: ldc  #26 // 'unsupported Number subclass: '
        //    154: invokevirtual  #114 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    157: aload_1
        //    158: invokevirtual  #113 // java.lang.StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    161: invokevirtual  #115 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    164: invokespecial  #105 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    167: athrow
        //    168: ldc  #31 // java.lang.Boolean
        //    170: aload_1
        //    171: invokevirtual  #101 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    174: ifeq  185 (offset +11)
        //    177: aload_0
        //    178: aload_2
        //    179: invokestatic  #156 // jnr.ffi.provider.jffi.AsmUtil.unboxBoolean:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljava/lang/Class;)V
        //    182: goto  212 (offset +30)
        //    185: new  #38 // java.lang.IllegalArgumentException
        //    188: dup
        //    189: new  #48 // java.lang.StringBuilder
        //    192: dup
        //    193: invokespecial  #112 // java.lang.StringBuilder.<init>:()V
        //    196: ldc  #27 // 'unsupported boxed type: '
        //    198: invokevirtual  #114 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    201: aload_1
        //    202: invokevirtual  #113 // java.lang.StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    205: invokevirtual  #115 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    208: invokespecial  #105 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    211: athrow
        //    212: return
    }

  static void boxValue(AsmBuilder arg0, SkinnyMethodAdapter arg1, Class arg2, Class arg3) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_2
        //      1: aload_3
        //      2: if_acmpeq  196 (offset +194)
        //      5: aload_2
        //      6: invokevirtual  #103 // java.lang.Class.isPrimitive:()Z
        //      9: ifeq  15 (offset +6)
        //     12: goto  196 (offset +184)
        //     15: ldc  #31 // java.lang.Boolean
        //     17: aload_2
        //     18: invokevirtual  #101 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //     21: ifeq  55 (offset +34)
        //     24: aload_1
        //     25: aload_3
        //     26: getstatic  #79 // java.lang.Boolean.TYPE:Ljava/lang/Class;
        //     29: invokestatic  #167 // jnr.ffi.provider.jffi.NumberUtil.narrow:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljava/lang/Class;Ljava/lang/Class;)V
        //     32: aload_1
        //     33: ldc  #31 // java.lang.Boolean
        //     35: ldc  #28 // 'valueOf'
        //     37: ldc  #31 // java.lang.Boolean
        //     39: iconst_1
        //     40: anewarray  #33 // java.lang.Class
        //     43: dup
        //     44: iconst_0
        //     45: getstatic  #79 // java.lang.Boolean.TYPE:Ljava/lang/Class;
        //     48: aastore
        //     49: invokevirtual  #188 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokestatic:(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Class;[Ljava/lang/Class;)V
        //     52: goto  196 (offset +144)
        //     55: ldc  #56 // jnr.ffi.Pointer
        //     57: aload_2
        //     58: invokevirtual  #101 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //     61: ifeq  99 (offset +38)
        //     64: aload_1
        //     65: aload_0
        //     66: aload_0
        //     67: invokevirtual  #142 // jnr.ffi.provider.jffi.AsmBuilder.getRuntimeField:()Ljnr/ffi/provider/jffi/AsmBuilder$ObjectField;
        //     70: invokestatic  #153 // jnr.ffi.provider.jffi.AsmUtil.getfield:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljnr/ffi/provider/jffi/AsmBuilder;Ljnr/ffi/provider/jffi/AsmBuilder$ObjectField;)V
        //     73: aload_1
        //     74: ldc  #69 // jnr.ffi.provider.jffi.AsmRuntime
        //     76: ldc  #23 // 'pointerValue'
        //     78: ldc  #56 // jnr.ffi.Pointer
        //     80: iconst_2
        //     81: anewarray  #33 // java.lang.Class
        //     84: dup
        //     85: iconst_0
        //     86: aload_3
        //     87: aastore
        //     88: dup
        //     89: iconst_1
        //     90: ldc  #57 // jnr.ffi.Runtime
        //     92: aastore
        //     93: invokevirtual  #188 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokestatic:(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Class;[Ljava/lang/Class;)V
        //     96: goto  196 (offset +100)
        //     99: ldc  #54 // jnr.ffi.Address
        //    101: aload_2
        //    102: if_acmpne  124 (offset +22)
        //    105: aload_1
        //    106: aload_2
        //    107: ldc  #28 // 'valueOf'
        //    109: aload_2
        //    110: iconst_1
        //    111: anewarray  #33 // java.lang.Class
        //    114: dup
        //    115: iconst_0
        //    116: aload_3
        //    117: aastore
        //    118: invokevirtual  #188 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokestatic:(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Class;[Ljava/lang/Class;)V
        //    121: goto  196 (offset +75)
        //    124: ldc  #42 // java.lang.Number
        //    126: aload_2
        //    127: invokevirtual  #101 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    130: ifeq  160 (offset +30)
        //    133: aload_3
        //    134: invokestatic  #146 // jnr.ffi.provider.jffi.AsmUtil.boxedType:(Ljava/lang/Class;)Ljava/lang/Class;
        //    137: aload_2
        //    138: if_acmpne  160 (offset +22)
        //    141: aload_1
        //    142: aload_2
        //    143: ldc  #28 // 'valueOf'
        //    145: aload_2
        //    146: iconst_1
        //    147: anewarray  #33 // java.lang.Class
        //    150: dup
        //    151: iconst_0
        //    152: aload_3
        //    153: aastore
        //    154: invokevirtual  #188 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokestatic:(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Class;[Ljava/lang/Class;)V
        //    157: goto  196 (offset +39)
        //    160: new  #38 // java.lang.IllegalArgumentException
        //    163: dup
        //    164: new  #48 // java.lang.StringBuilder
        //    167: dup
        //    168: invokespecial  #112 // java.lang.StringBuilder.<init>:()V
        //    171: ldc  #13 // 'cannot box value of type '
        //    173: invokevirtual  #114 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    176: aload_3
        //    177: invokevirtual  #113 // java.lang.StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    180: ldc  #3 // ' to '
        //    182: invokevirtual  #114 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    185: aload_2
        //    186: invokevirtual  #113 // java.lang.StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    189: invokevirtual  #115 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    192: invokespecial  #105 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    195: athrow
        //    196: return
    }

  static int getNativeArrayFlags(int arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: iconst_0
        //      1: istore_1
        //      2: iload_1
        //      3: iload_0
        //      4: invokestatic  #128 // jnr.ffi.provider.ParameterFlags.isIn:(I)Z
        //      7: ifeq  14 (offset +7)
        //     10: iconst_1
        //     11: goto  15 (offset +4)
        //     14: iconst_0
        //     15: ior
        //     16: istore_1
        //     17: iload_1
        //     18: iload_0
        //     19: invokestatic  #130 // jnr.ffi.provider.ParameterFlags.isOut:(I)Z
        //     22: ifeq  29 (offset +7)
        //     25: iconst_2
        //     26: goto  30 (offset +4)
        //     29: iconst_0
        //     30: ior
        //     31: istore_1
        //     32: iload_1
        //     33: iload_0
        //     34: invokestatic  #131 // jnr.ffi.provider.ParameterFlags.isPinned:(I)Z
        //     37: ifeq  45 (offset +8)
        //     40: bipush  8
        //     42: goto  46 (offset +4)
        //     45: iconst_0
        //     46: ior
        //     47: istore_1
        //     48: iload_1
        //     49: iload_0
        //     50: invokestatic  #129 // jnr.ffi.provider.ParameterFlags.isNulTerminate:(I)Z
        //     53: ifne  63 (offset +10)
        //     56: iload_0
        //     57: invokestatic  #128 // jnr.ffi.provider.ParameterFlags.isIn:(I)Z
        //     60: ifeq  67 (offset +7)
        //     63: iconst_4
        //     64: goto  68 (offset +4)
        //     67: iconst_0
        //     68: ior
        //     69: istore_1
        //     70: iload_1
        //     71: ireturn
    }

  static int getNativeArrayFlags(Collection arg0) {
        return getNativeArrayFlags(ParameterFlags.parse(arg0));
    }

  static LocalVariable[] getParameterVariables(ParameterType[] arg0) {
        LocalVariable[] var1 = new LocalVariable[arg0.length];
        int var2 = 1;
        int var3 = 0;
        while (var3 < arg0.length) {
            var1[var3] = new LocalVariable(arg0[var3].getDeclaredType(), var2);
            var2 = var2 + calculateLocalVariableSpace(((SigType) arg0[var3]));
            ++var3;
            continue;
        }
        return var1;
    }

  static LocalVariable[] getParameterVariables(Class[] arg0) {
        LocalVariable[] var1 = new LocalVariable[arg0.length];
        int var2 = 1;
        int var3 = 0;
        while (var3 < arg0.length) {
            var1[var3] = new LocalVariable(((Class) arg0[var3]), var2);
            var2 = var2 + calculateLocalVariableSpace(((Class) arg0[var3]));
            ++var3;
            continue;
        }
        return var1;
    }

  static void load(SkinnyMethodAdapter arg0, Class arg1, LocalVariable arg2) {
        if (arg1.isPrimitive()) {
            if (Long.TYPE != arg1) {
                if (Float.TYPE != arg1) {
                    if (Double.TYPE != arg1) {
                        arg0.iload(arg2);
                    } else {
                        arg0.dload(arg2);
                    }
                } else {
                    arg0.fload(arg2);
                }
            } else {
                arg0.lload(new LocalVariable[]{arg2});
            }
        } else {
            arg0.aload(arg2);
        }
    }

  static void store(SkinnyMethodAdapter arg0, Class arg1, LocalVariable arg2) {
        if (arg1.isPrimitive()) {
            if (Long.TYPE != arg1) {
                if (Double.TYPE != arg1) {
                    if (Float.TYPE != arg1) {
                        arg0.istore(arg2);
                    } else {
                        arg0.fstore(arg2);
                    }
                } else {
                    arg0.dstore(arg2);
                }
            } else {
                arg0.lstore(arg2);
            }
        } else {
            arg0.astore(arg2);
        }
    }

  static void emitReturn(AsmBuilder arg0, SkinnyMethodAdapter arg1, Class arg2, Class arg3) {
        if (!arg2.isPrimitive()) {
            boxValue(arg0, arg1, arg2, arg3);
            arg1.areturn();
        } else {
            if (Long.TYPE != arg2) {
                if (Float.TYPE != arg2) {
                    if (Double.TYPE != arg2) {
                        if (Void.TYPE != arg2) {
                            arg1.ireturn();
                        } else {
                            arg1.voidreturn();
                        }
                    } else {
                        arg1.dreturn();
                    }
                } else {
                    arg1.freturn();
                }
            } else {
                arg1.lreturn();
            }
        }
    }

  static void getfield(SkinnyMethodAdapter arg0, AsmBuilder arg1, AsmBuilder_ObjectField arg2) {
        arg0.aload(0);
        arg0.getfield(arg1.getClassNamePath(), arg2.name, CodegenUtils.ci(arg2.klass));
    }

  static void tryfinally(SkinnyMethodAdapter arg0, Runnable arg1, Runnable arg2) {
        Label var3 = new Label();
        Label var4 = new Label();
        Label var5 = new Label();
        Label var6 = new Label();
        arg0.trycatch(var3, var4, var5, null);
        arg0.label(var3);
        arg1.run();
        arg0.label(var4);
        if (arg2 != null) {
            arg2.run();
        }
        arg0.go_to(var6);
        if (arg2 != null) {
            arg0.label(var5);
            arg2.run();
            arg0.athrow();
        }
        arg0.label(var6);
    }

  static void emitToNativeConversion(AsmBuilder arg0, SkinnyMethodAdapter arg1, ToNativeType arg2) {
        ToNativeConverter var3 = arg2.getToNativeConverter();
        if (var3 != null) {
            Method var4 = getToNativeMethod(arg2, arg0.getClassLoader());
            if (arg2.getDeclaredType().isPrimitive()) {
                boxValue(arg0, arg1, NumberUtil.getBoxedClass(arg2.getDeclaredType()), arg2.getDeclaredType());
            }
            if (!var4.getParameterTypes()[0].isAssignableFrom(NumberUtil.getBoxedClass(arg2.getDeclaredType()))) {
                arg1.checkcast(((Class) var4.getParameterTypes()[0]));
            }
            arg1.aload(0);
            AsmBuilder_ObjectField var5 = arg0.getToNativeConverterField(var3);
            arg1.getfield(arg0.getClassNamePath(), var5.name, CodegenUtils.ci(var5.klass));
            if (!var4.getDeclaringClass().equals(var5.klass)) {
                arg1.checkcast(var4.getDeclaringClass());
            }
            arg1.swap();
            if (arg2.getToNativeContext() == null) {
                arg1.aconst_null();
            } else {
                getfield(arg1, arg0, arg0.getToNativeContextField(arg2.getToNativeContext()));
            }
            if (!var4.getDeclaringClass().isInterface()) {
                arg1.invokevirtual(var4.getDeclaringClass(), var4.getName(), var4.getReturnType(), var4.getParameterTypes());
            } else {
                arg1.invokeinterface(var4.getDeclaringClass(), var4.getName(), var4.getReturnType(), var4.getParameterTypes());
            }
            if (!var3.nativeType().isAssignableFrom(var4.getReturnType())) {
                arg1.checkcast(CodegenUtils.method1942(var3.nativeType()));
            }
        }
    }

  static void emitFromNativeConversion(AsmBuilder arg0, SkinnyMethodAdapter arg1, FromNativeType arg2, Class arg3) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_2
        //      1: invokevirtual  #126 // jnr.ffi.provider.FromNativeType.getFromNativeConverter:()Ljnr/ffi/mapper/FromNativeConverter;
        //      4: astore  4
        //      6: aload  4
        //      8: ifnull  244 (offset +236)
        //     11: aload_1
        //     12: aload_3
        //     13: aload  4
        //     15: invokeinterface  #203 // jnr.ffi.mapper.FromNativeConverter.nativeType:()Ljava/lang/Class;, count 1
        //     20: invokestatic  #160 // jnr.ffi.provider.jffi.AsmUtil.unboxedType:(Ljava/lang/Class;)Ljava/lang/Class;
        //     23: aload_2
        //     24: invokevirtual  #127 // jnr.ffi.provider.FromNativeType.getNativeType:()Ljnr/ffi/NativeType;
        //     27: invokestatic  #165 // jnr.ffi.provider.jffi.NumberUtil.convertPrimitive:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljava/lang/Class;Ljava/lang/Class;Ljnr/ffi/NativeType;)V
        //     30: aload_0
        //     31: aload_1
        //     32: aload  4
        //     34: invokeinterface  #203 // jnr.ffi.mapper.FromNativeConverter.nativeType:()Ljava/lang/Class;, count 1
        //     39: aload_3
        //     40: invokestatic  #145 // jnr.ffi.provider.jffi.AsmUtil.boxValue:(Ljnr/ffi/provider/jffi/AsmBuilder;Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljava/lang/Class;Ljava/lang/Class;)V
        //     43: aload_2
        //     44: aload_0
        //     45: invokevirtual  #138 // jnr.ffi.provider.jffi.AsmBuilder.getClassLoader:()Ljnr/ffi/provider/jffi/AsmClassLoader;
        //     48: invokestatic  #150 // jnr.ffi.provider.jffi.AsmUtil.getFromNativeMethod:(Ljnr/ffi/provider/FromNativeType;Ljnr/ffi/provider/jffi/AsmClassLoader;)Ljava/lang/reflect/Method;
        //     51: astore  5
        //     53: aload_1
        //     54: aload_0
        //     55: aload_0
        //     56: aload  4
        //     58: invokevirtual  #141 // jnr.ffi.provider.jffi.AsmBuilder.getFromNativeConverterField:(Ljnr/ffi/mapper/FromNativeConverter;)Ljnr/ffi/provider/jffi/AsmBuilder$ObjectField;
        //     61: invokestatic  #153 // jnr.ffi.provider.jffi.AsmUtil.getfield:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljnr/ffi/provider/jffi/AsmBuilder;Ljnr/ffi/provider/jffi/AsmBuilder$ObjectField;)V
        //     64: aload_1
        //     65: invokevirtual  #198 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.swap:()V
        //     68: aload_2
        //     69: invokevirtual  #125 // jnr.ffi.provider.FromNativeType.getFromNativeContext:()Ljnr/ffi/mapper/FromNativeContext;
        //     72: ifnull  91 (offset +19)
        //     75: aload_1
        //     76: aload_0
        //     77: aload_0
        //     78: aload_2
        //     79: invokevirtual  #125 // jnr.ffi.provider.FromNativeType.getFromNativeContext:()Ljnr/ffi/mapper/FromNativeContext;
        //     82: invokevirtual  #140 // jnr.ffi.provider.jffi.AsmBuilder.getFromNativeContextField:(Ljnr/ffi/mapper/FromNativeContext;)Ljnr/ffi/provider/jffi/AsmBuilder$ObjectField;
        //     85: invokestatic  #153 // jnr.ffi.provider.jffi.AsmUtil.getfield:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljnr/ffi/provider/jffi/AsmBuilder;Ljnr/ffi/provider/jffi/AsmBuilder$ObjectField;)V
        //     88: goto  95 (offset +7)
        //     91: aload_1
        //     92: invokevirtual  #170 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.aconst_null:()V
        //     95: aload  5
        //     97: invokevirtual  #117 // java.lang.reflect.Method.getDeclaringClass:()Ljava/lang/Class;
        //    100: invokevirtual  #102 // java.lang.Class.isInterface:()Z
        //    103: ifeq  133 (offset +30)
        //    106: aload_1
        //    107: aload  5
        //    109: invokevirtual  #117 // java.lang.reflect.Method.getDeclaringClass:()Ljava/lang/Class;
        //    112: aload  5
        //    114: invokevirtual  #119 // java.lang.reflect.Method.getName:()Ljava/lang/String;
        //    117: aload  5
        //    119: invokevirtual  #121 // java.lang.reflect.Method.getReturnType:()Ljava/lang/Class;
        //    122: aload  5
        //    124: invokevirtual  #120 // java.lang.reflect.Method.getParameterTypes:()[Ljava/lang/Class;
        //    127: invokevirtual  #187 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokeinterface:(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Class;[Ljava/lang/Class;)V
        //    130: goto  157 (offset +27)
        //    133: aload_1
        //    134: aload  5
        //    136: invokevirtual  #117 // java.lang.reflect.Method.getDeclaringClass:()Ljava/lang/Class;
        //    139: aload  5
        //    141: invokevirtual  #119 // java.lang.reflect.Method.getName:()Ljava/lang/String;
        //    144: aload  5
        //    146: invokevirtual  #121 // java.lang.reflect.Method.getReturnType:()Ljava/lang/Class;
        //    149: aload  5
        //    151: invokevirtual  #120 // java.lang.reflect.Method.getParameterTypes:()[Ljava/lang/Class;
        //    154: invokevirtual  #190 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokevirtual:(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Class;[Ljava/lang/Class;)V
        //    157: aload_2
        //    158: invokevirtual  #124 // jnr.ffi.provider.FromNativeType.getDeclaredType:()Ljava/lang/Class;
        //    161: invokevirtual  #103 // java.lang.Class.isPrimitive:()Z
        //    164: ifeq  215 (offset +51)
        //    167: aload_2
        //    168: invokevirtual  #124 // jnr.ffi.provider.FromNativeType.getDeclaredType:()Ljava/lang/Class;
        //    171: invokestatic  #166 // jnr.ffi.provider.jffi.NumberUtil.getBoxedClass:(Ljava/lang/Class;)Ljava/lang/Class;
        //    174: astore  6
        //    176: aload  6
        //    178: aload  5
        //    180: invokevirtual  #121 // java.lang.reflect.Method.getReturnType:()Ljava/lang/Class;
        //    183: invokevirtual  #101 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    186: ifne  198 (offset +12)
        //    189: aload_1
        //    190: aload  6
        //    192: invokestatic  #162 // jnr.ffi.provider.jffi.CodegenUtils.p:(Ljava/lang/Class;)Ljava/lang/String;
        //    195: invokevirtual  #177 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.checkcast:(Ljava/lang/String;)V
        //    198: aload_1
        //    199: aload  6
        //    201: aload_2
        //    202: invokevirtual  #124 // jnr.ffi.provider.FromNativeType.getDeclaredType:()Ljava/lang/Class;
        //    205: aload_2
        //    206: invokevirtual  #127 // jnr.ffi.provider.FromNativeType.getNativeType:()Ljnr/ffi/NativeType;
        //    209: invokestatic  #158 // jnr.ffi.provider.jffi.AsmUtil.unboxNumber:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljava/lang/Class;Ljava/lang/Class;Ljnr/ffi/NativeType;)V
        //    212: goto  241 (offset +29)
        //    215: aload_2
        //    216: invokevirtual  #124 // jnr.ffi.provider.FromNativeType.getDeclaredType:()Ljava/lang/Class;
        //    219: aload  5
        //    221: invokevirtual  #121 // java.lang.reflect.Method.getReturnType:()Ljava/lang/Class;
        //    224: invokevirtual  #101 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    227: ifne  241 (offset +14)
        //    230: aload_1
        //    231: aload_2
        //    232: invokevirtual  #124 // jnr.ffi.provider.FromNativeType.getDeclaredType:()Ljava/lang/Class;
        //    235: invokestatic  #162 // jnr.ffi.provider.jffi.CodegenUtils.p:(Ljava/lang/Class;)Ljava/lang/String;
        //    238: invokevirtual  #177 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.checkcast:(Ljava/lang/String;)V
        //    241: goto  285 (offset +44)
        //    244: aload_2
        //    245: invokevirtual  #124 // jnr.ffi.provider.FromNativeType.getDeclaredType:()Ljava/lang/Class;
        //    248: invokevirtual  #103 // java.lang.Class.isPrimitive:()Z
        //    251: ifne  285 (offset +34)
        //    254: aload_2
        //    255: invokevirtual  #124 // jnr.ffi.provider.FromNativeType.getDeclaredType:()Ljava/lang/Class;
        //    258: invokestatic  #160 // jnr.ffi.provider.jffi.AsmUtil.unboxedType:(Ljava/lang/Class;)Ljava/lang/Class;
        //    261: astore  5
        //    263: aload_1
        //    264: aload_3
        //    265: aload  5
        //    267: aload_2
        //    268: invokevirtual  #127 // jnr.ffi.provider.FromNativeType.getNativeType:()Ljnr/ffi/NativeType;
        //    271: invokestatic  #165 // jnr.ffi.provider.jffi.NumberUtil.convertPrimitive:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljava/lang/Class;Ljava/lang/Class;Ljnr/ffi/NativeType;)V
        //    274: aload_0
        //    275: aload_1
        //    276: aload_2
        //    277: invokevirtual  #124 // jnr.ffi.provider.FromNativeType.getDeclaredType:()Ljava/lang/Class;
        //    280: aload  5
        //    282: invokestatic  #145 // jnr.ffi.provider.jffi.AsmUtil.boxValue:(Ljnr/ffi/provider/jffi/AsmBuilder;Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljava/lang/Class;Ljava/lang/Class;)V
        //    285: return
    }

  static Method getToNativeMethod(ToNativeType arg0, AsmClassLoader arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokevirtual  #137 // jnr.ffi.provider.ToNativeType.getToNativeConverter:()Ljnr/ffi/mapper/ToNativeConverter;
        //      4: astore_2
        //      5: aload_2
        //      6: ifnonnull  11 (offset +5)
        //      9: aconst_null
        //     10: areturn
        //     11: aload_2
        //     12: invokevirtual  #108 // java.lang.Object.getClass:()Ljava/lang/Class;
        //     15: astore_3
        //     16: aload_3
        //     17: invokevirtual  #99 // java.lang.Class.getModifiers:()I
        //     20: invokestatic  #122 // java.lang.reflect.Modifier.isPublic:(I)Z
        //     23: ifeq  153 (offset +130)
        //     26: aload_3
        //     27: invokevirtual  #98 // java.lang.Class.getMethods:()[Ljava/lang/reflect/Method;
        //     30: astore  4
        //     32: aload  4
        //     34: arraylength
        //     35: istore  5
        //     37: iconst_0
        //     38: istore  6
        //     40: iload  6
        //     42: iload  5
        //     44: if_icmpge  153 (offset +109)
        //     47: aload  4
        //     49: iload  6
        //     51: aaload
        //     52: astore  7
        //     54: aload  7
        //     56: invokevirtual  #119 // java.lang.reflect.Method.getName:()Ljava/lang/String;
        //     59: ldc  #25 // 'toNative'
        //     61: invokevirtual  #111 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //     64: ifne  70 (offset +6)
        //     67: goto  147 (offset +80)
        //     70: aload  7
        //     72: invokevirtual  #120 // java.lang.reflect.Method.getParameterTypes:()[Ljava/lang/Class;
        //     75: astore  8
        //     77: aload_2
        //     78: invokeinterface  #204 // jnr.ffi.mapper.ToNativeConverter.nativeType:()Ljava/lang/Class;, count 1
        //     83: aload  7
        //     85: invokevirtual  #121 // java.lang.reflect.Method.getReturnType:()Ljava/lang/Class;
        //     88: invokevirtual  #101 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //     91: ifeq  147 (offset +56)
        //     94: aload  8
        //     96: arraylength
        //     97: iconst_2
        //     98: if_icmpne  147 (offset +49)
        //    101: aload  8
        //    103: iconst_0
        //    104: aaload
        //    105: aload_0
        //    106: invokevirtual  #135 // jnr.ffi.provider.ToNativeType.getDeclaredType:()Ljava/lang/Class;
        //    109: invokevirtual  #101 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    112: ifeq  147 (offset +35)
        //    115: aload  8
        //    117: iconst_1
        //    118: aaload
        //    119: ldc  #60 // jnr.ffi.mapper.ToNativeContext
        //    121: if_acmpne  147 (offset +26)
        //    124: aload  7
        //    126: invokestatic  #154 // jnr.ffi.provider.jffi.AsmUtil.methodIsAccessible:(Ljava/lang/reflect/Method;)Z
        //    129: ifeq  147 (offset +18)
        //    132: aload_1
        //    133: aload  7
        //    135: invokevirtual  #117 // java.lang.reflect.Method.getDeclaringClass:()Ljava/lang/Class;
        //    138: invokestatic  #149 // jnr.ffi.provider.jffi.AsmUtil.classIsVisible:(Ljava/lang/ClassLoader;Ljava/lang/Class;)Z
        //    141: ifeq  147 (offset +6)
        //    144: aload  7
        //    146: areturn
        //    147: iinc  6, 1
        //    150: goto  40 (offset -110)
        //    153: aload_3
        //    154: ldc  #25 // 'toNative'
        //    156: iconst_2
        //    157: anewarray  #33 // java.lang.Class
        //    160: dup
        //    161: iconst_0
        //    162: ldc  #43 // java.lang.Object
        //    164: aastore
        //    165: dup
        //    166: iconst_1
        //    167: ldc  #60 // jnr.ffi.mapper.ToNativeContext
        //    169: aastore
        //    170: invokevirtual  #97 // java.lang.Class.getMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //    173: astore  4
        //    175: aload  4
        //    177: invokestatic  #154 // jnr.ffi.provider.jffi.AsmUtil.methodIsAccessible:(Ljava/lang/reflect/Method;)Z
        //    180: ifeq  200 (offset +20)
        //    183: aload_1
        //    184: aload  4
        //    186: invokevirtual  #117 // java.lang.reflect.Method.getDeclaringClass:()Ljava/lang/Class;
        //    189: invokestatic  #149 // jnr.ffi.provider.jffi.AsmUtil.classIsVisible:(Ljava/lang/ClassLoader;Ljava/lang/Class;)Z
        //    192: ifeq  200 (offset +8)
        //    195: aload  4
        //    197: goto  221 (offset +24)
        //    200: ldc  #61 // jnr.ffi.mapper.ToNativeConverter
        //    202: ldc  #25 // 'toNative'
        //    204: iconst_2
        //    205: anewarray  #33 // java.lang.Class
        //    208: dup
        //    209: iconst_0
        //    210: ldc  #43 // java.lang.Object
        //    212: aastore
        //    213: dup
        //    214: iconst_1
        //    215: ldc  #60 // jnr.ffi.mapper.ToNativeContext
        //    217: aastore
        //    218: invokevirtual  #96 // java.lang.Class.getDeclaredMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //    221: areturn
        //    222: astore_3
        //    223: ldc  #61 // jnr.ffi.mapper.ToNativeConverter
        //    225: ldc  #25 // 'toNative'
        //    227: iconst_2
        //    228: anewarray  #33 // java.lang.Class
        //    231: dup
        //    232: iconst_0
        //    233: ldc  #43 // java.lang.Object
        //    235: aastore
        //    236: dup
        //    237: iconst_1
        //    238: ldc  #60 // jnr.ffi.mapper.ToNativeContext
        //    240: aastore
        //    241: invokevirtual  #96 // java.lang.Class.getDeclaredMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //    244: areturn
        //    245: astore  4
        //    247: new  #45 // java.lang.RuntimeException
        //    250: dup
        //    251: new  #48 // java.lang.StringBuilder
        //    254: dup
        //    255: invokespecial  #112 // java.lang.StringBuilder.<init>:()V
        //    258: ldc  #18 // 'internal error. '
        //    260: invokevirtual  #114 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    263: ldc  #61 // jnr.ffi.mapper.ToNativeConverter
        //    265: invokevirtual  #113 // java.lang.StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    268: ldc  #2 // ' has no toNative() method'
        //    270: invokevirtual  #114 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    273: invokevirtual  #115 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    276: invokespecial  #109 // java.lang.RuntimeException.<init>:(Ljava/lang/String;)V
        //    279: athrow
        //       Exception table:
        //         from 11 to 146 target 222 type java.lang.NoSuchMethodException
        //         from 147 to 221 target 222 type java.lang.NoSuchMethodException
        //         from 223 to 244 target 245 type java.lang.NoSuchMethodException
    }

  static Method getFromNativeMethod(FromNativeType arg0, AsmClassLoader arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokevirtual  #126 // jnr.ffi.provider.FromNativeType.getFromNativeConverter:()Ljnr/ffi/mapper/FromNativeConverter;
        //      4: astore_2
        //      5: aload_2
        //      6: ifnonnull  11 (offset +5)
        //      9: aconst_null
        //     10: areturn
        //     11: aload_2
        //     12: invokevirtual  #108 // java.lang.Object.getClass:()Ljava/lang/Class;
        //     15: astore_3
        //     16: aload_3
        //     17: invokevirtual  #99 // java.lang.Class.getModifiers:()I
        //     20: invokestatic  #122 // java.lang.reflect.Modifier.isPublic:(I)Z
        //     23: ifeq  177 (offset +154)
        //     26: aload_3
        //     27: invokevirtual  #98 // java.lang.Class.getMethods:()[Ljava/lang/reflect/Method;
        //     30: astore  4
        //     32: aload  4
        //     34: arraylength
        //     35: istore  5
        //     37: iconst_0
        //     38: istore  6
        //     40: iload  6
        //     42: iload  5
        //     44: if_icmpge  177 (offset +133)
        //     47: aload  4
        //     49: iload  6
        //     51: aaload
        //     52: astore  7
        //     54: aload  7
        //     56: invokevirtual  #119 // java.lang.reflect.Method.getName:()Ljava/lang/String;
        //     59: ldc  #16 // 'fromNative'
        //     61: invokevirtual  #111 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //     64: ifne  70 (offset +6)
        //     67: goto  171 (offset +104)
        //     70: aload  7
        //     72: invokevirtual  #120 // java.lang.reflect.Method.getParameterTypes:()[Ljava/lang/Class;
        //     75: astore  8
        //     77: aload_0
        //     78: invokevirtual  #124 // jnr.ffi.provider.FromNativeType.getDeclaredType:()Ljava/lang/Class;
        //     81: invokevirtual  #103 // java.lang.Class.isPrimitive:()Z
        //     84: ifeq  97 (offset +13)
        //     87: aload_0
        //     88: invokevirtual  #124 // jnr.ffi.provider.FromNativeType.getDeclaredType:()Ljava/lang/Class;
        //     91: invokestatic  #146 // jnr.ffi.provider.jffi.AsmUtil.boxedType:(Ljava/lang/Class;)Ljava/lang/Class;
        //     94: goto  101 (offset +7)
        //     97: aload_0
        //     98: invokevirtual  #124 // jnr.ffi.provider.FromNativeType.getDeclaredType:()Ljava/lang/Class;
        //    101: astore  9
        //    103: aload  9
        //    105: aload  7
        //    107: invokevirtual  #121 // java.lang.reflect.Method.getReturnType:()Ljava/lang/Class;
        //    110: invokevirtual  #101 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    113: ifeq  171 (offset +58)
        //    116: aload  8
        //    118: arraylength
        //    119: iconst_2
        //    120: if_icmpne  171 (offset +51)
        //    123: aload  8
        //    125: iconst_0
        //    126: aaload
        //    127: aload_2
        //    128: invokeinterface  #203 // jnr.ffi.mapper.FromNativeConverter.nativeType:()Ljava/lang/Class;, count 1
        //    133: invokevirtual  #101 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    136: ifeq  171 (offset +35)
        //    139: aload  8
        //    141: iconst_1
        //    142: aaload
        //    143: ldc  #58 // jnr.ffi.mapper.FromNativeContext
        //    145: if_acmpne  171 (offset +26)
        //    148: aload  7
        //    150: invokestatic  #154 // jnr.ffi.provider.jffi.AsmUtil.methodIsAccessible:(Ljava/lang/reflect/Method;)Z
        //    153: ifeq  171 (offset +18)
        //    156: aload_1
        //    157: aload  7
        //    159: invokevirtual  #117 // java.lang.reflect.Method.getDeclaringClass:()Ljava/lang/Class;
        //    162: invokestatic  #149 // jnr.ffi.provider.jffi.AsmUtil.classIsVisible:(Ljava/lang/ClassLoader;Ljava/lang/Class;)Z
        //    165: ifeq  171 (offset +6)
        //    168: aload  7
        //    170: areturn
        //    171: iinc  6, 1
        //    174: goto  40 (offset -134)
        //    177: aload_3
        //    178: ldc  #16 // 'fromNative'
        //    180: iconst_2
        //    181: anewarray  #33 // java.lang.Class
        //    184: dup
        //    185: iconst_0
        //    186: ldc  #43 // java.lang.Object
        //    188: aastore
        //    189: dup
        //    190: iconst_1
        //    191: ldc  #58 // jnr.ffi.mapper.FromNativeContext
        //    193: aastore
        //    194: invokevirtual  #97 // java.lang.Class.getMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //    197: astore  4
        //    199: aload  4
        //    201: invokestatic  #154 // jnr.ffi.provider.jffi.AsmUtil.methodIsAccessible:(Ljava/lang/reflect/Method;)Z
        //    204: ifeq  224 (offset +20)
        //    207: aload_1
        //    208: aload  4
        //    210: invokevirtual  #117 // java.lang.reflect.Method.getDeclaringClass:()Ljava/lang/Class;
        //    213: invokestatic  #149 // jnr.ffi.provider.jffi.AsmUtil.classIsVisible:(Ljava/lang/ClassLoader;Ljava/lang/Class;)Z
        //    216: ifeq  224 (offset +8)
        //    219: aload  4
        //    221: goto  245 (offset +24)
        //    224: ldc  #59 // jnr.ffi.mapper.FromNativeConverter
        //    226: ldc  #16 // 'fromNative'
        //    228: iconst_2
        //    229: anewarray  #33 // java.lang.Class
        //    232: dup
        //    233: iconst_0
        //    234: ldc  #43 // java.lang.Object
        //    236: aastore
        //    237: dup
        //    238: iconst_1
        //    239: ldc  #58 // jnr.ffi.mapper.FromNativeContext
        //    241: aastore
        //    242: invokevirtual  #96 // java.lang.Class.getDeclaredMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //    245: areturn
        //    246: astore_3
        //    247: ldc  #59 // jnr.ffi.mapper.FromNativeConverter
        //    249: ldc  #16 // 'fromNative'
        //    251: iconst_2
        //    252: anewarray  #33 // java.lang.Class
        //    255: dup
        //    256: iconst_0
        //    257: ldc  #43 // java.lang.Object
        //    259: aastore
        //    260: dup
        //    261: iconst_1
        //    262: ldc  #58 // jnr.ffi.mapper.FromNativeContext
        //    264: aastore
        //    265: invokevirtual  #96 // java.lang.Class.getDeclaredMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //    268: areturn
        //    269: astore  4
        //    271: new  #45 // java.lang.RuntimeException
        //    274: dup
        //    275: new  #48 // java.lang.StringBuilder
        //    278: dup
        //    279: invokespecial  #112 // java.lang.StringBuilder.<init>:()V
        //    282: ldc  #18 // 'internal error. '
        //    284: invokevirtual  #114 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    287: ldc  #59 // jnr.ffi.mapper.FromNativeConverter
        //    289: invokevirtual  #113 // java.lang.StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    292: ldc  #1 // ' has no fromNative() method'
        //    294: invokevirtual  #114 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    297: invokevirtual  #115 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    300: invokespecial  #109 // java.lang.RuntimeException.<init>:(Ljava/lang/String;)V
        //    303: athrow
        //       Exception table:
        //         from 11 to 170 target 246 type java.lang.NoSuchMethodException
        //         from 171 to 245 target 246 type java.lang.NoSuchMethodException
        //         from 247 to 268 target 269 type java.lang.NoSuchMethodException
    }

  static boolean methodIsAccessible(Method arg0) {
        return !Modifier.isPublic(arg0.getModifiers()) ? 0 : Modifier.isPublic(arg0.getDeclaringClass().getModifiers());
    }

  private static boolean classIsVisible(ClassLoader arg0, Class arg1) {
        boolean __stk1;
        try {
            __stk1 = arg0.loadClass(arg1.getName()) == arg1;
        } catch (ClassNotFoundException var2) {
            return false;
        }
    }

}