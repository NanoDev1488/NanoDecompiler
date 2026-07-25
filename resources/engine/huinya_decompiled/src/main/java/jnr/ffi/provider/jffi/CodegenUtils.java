// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.CodegenUtils
package jnr.ffi.provider.jffi;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Type;

public class CodegenUtils {

  public CodegenUtils() { // было: <init>
        super();
    }

  public static String method1941(String arg0) { // было: c
        return arg0.replace('/', '.');
    }

  public static String method1942(Class arg0) { // было: p
        return arg0.getName().replace('.', '/');
    }

  public static String method1943(String arg0) { // было: p
        return arg0.replace('.', '/');
    }

  public static String ci(Class arg0) {
        if (!arg0.isArray()) {
            if (!arg0.isPrimitive()) {
                return new StringBuilder().append("L").append(method1942(arg0)).append(";").toString();
            } else {
                if (arg0 != Byte.TYPE) {
                    if (arg0 != Boolean.TYPE) {
                        if (arg0 != Short.TYPE) {
                            if (arg0 != Character.TYPE) {
                                if (arg0 != Integer.TYPE) {
                                    if (arg0 != Float.TYPE) {
                                        if (arg0 != Double.TYPE) {
                                            if (arg0 != Long.TYPE) {
                                                if (arg0 != Void.TYPE) {
                                                    throw new RuntimeException(new StringBuilder().append("Unrecognized type in compiler: ").append(arg0.getName()).toString());
                                                } else {
                                                    return "V";
                                                }
                                            } else {
                                                return "J";
                                            }
                                        } else {
                                            return "D";
                                        }
                                    } else {
                                        return "F";
                                    }
                                } else {
                                    return "I";
                                }
                            } else {
                                return "C";
                            }
                        } else {
                            return "S";
                        }
                    } else {
                        return "Z";
                    }
                } else {
                    return "B";
                }
            }
        } else {
            arg0 = arg0.getComponentType();
            if (!arg0.isPrimitive()) {
                return new StringBuilder().append("[").append(ci(arg0)).toString();
            } else {
                if (arg0 != Byte.TYPE) {
                    if (arg0 != Boolean.TYPE) {
                        if (arg0 != Short.TYPE) {
                            if (arg0 != Character.TYPE) {
                                if (arg0 != Integer.TYPE) {
                                    if (arg0 != Float.TYPE) {
                                        if (arg0 != Double.TYPE) {
                                            if (arg0 != Long.TYPE) {
                                                throw new RuntimeException(new StringBuilder().append("Unrecognized type in compiler: ").append(arg0.getName()).toString());
                                            } else {
                                                return "[J";
                                            }
                                        } else {
                                            return "[D";
                                        }
                                    } else {
                                        return "[F";
                                    }
                                } else {
                                    return "[I";
                                }
                            } else {
                                return "[C";
                            }
                        } else {
                            return "[S";
                        }
                    } else {
                        return "[Z";
                    }
                } else {
                    return "[B";
                }
            }
        }
    }

  public static String human(Class arg0) {
        return arg0.getCanonicalName();
    }

  public static String sig(Class arg0, Class[] arg1) {
        return new StringBuilder().append(sigParams(arg1)).append(ci(arg0)).toString();
    }

  public static String sig(Class arg0, String arg1, Class[] arg2) {
        return new StringBuilder().append(sigParams(arg1, arg2)).append(ci(arg0)).toString();
    }

  public static String sigParams(Class[] arg0) {
        StringBuilder var1 = new StringBuilder("(");
        int var2 = 0;
        while (var2 < arg0.length) {
            var1.append(ci(((Class) arg0[var2])));
            ++var2;
            continue;
        }
        var1.append(")");
        return var1.toString();
    }

  public static String sigParams(String arg0, Class[] arg1) {
        StringBuilder var2 = new StringBuilder("(");
        var2.append(arg0);
        int var3 = 0;
        while (var3 < arg1.length) {
            var2.append(ci(((Class) arg1[var3])));
            ++var3;
            continue;
        }
        var2.append(")");
        return var2.toString();
    }

  public static String pretty(Class arg0, Class[] arg1) {
        return new StringBuilder().append(prettyParams(arg1)).append(human(arg0)).toString();
    }

  public static String prettyParams(Class[] arg0) {
        StringBuilder var1 = new StringBuilder("(");
        int var2 = 0;
        while (var2 < arg0.length) {
            var1.append(human(((Class) arg0[var2])));
            if (var2 < arg0.length - 1) {
                var1.append(',');
            }
            ++var2;
            continue;
        }
        var1.append(")");
        return var1.toString();
    }

  public static Class[] params(Class[] arg0) {
        return arg0;
    }

  public static Class[] params(Class arg0, int arg1) {
        Class[] var2 = new Class[arg1];
        Arrays.fill(var2, arg0);
        return var2;
    }

  public static Class[] params(Class arg0, Class arg1, int arg2) {
        Class[] var3 = new Class[arg2 + 1];
        Arrays.fill(var3, arg1);
        var3[0] = arg0;
        return var3;
    }

  public static String getAnnotatedBindingClassName(String arg0, String arg1, boolean arg2, int arg3, int arg4, boolean arg5, boolean arg6) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: iload  6
        //      2: ifeq  10 (offset +8)
        //      5: ldc  #1 // '$RUBYFRAMEDINVOKER$'
        //      7: goto  12 (offset +5)
        //     10: ldc  #2 // '$RUBYINVOKER$'
        //     12: astore  8
        //     14: iload  5
        //     16: ifeq  62 (offset +46)
        //     19: new  #44 // java.lang.StringBuilder
        //     22: dup
        //     23: invokespecial  #74 // java.lang.StringBuilder.<init>:()V
        //     26: iload_2
        //     27: ifeq  35 (offset +8)
        //     30: ldc  #4 // '$s'
        //     32: goto  37 (offset +5)
        //     35: ldc  #3 // '$i'
        //     37: invokevirtual  #78 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     40: ldc  #30 // '_method_multi'
        //     42: invokevirtual  #78 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     45: aload  8
        //     47: invokevirtual  #78 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     50: aload_0
        //     51: invokevirtual  #78 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     54: invokevirtual  #79 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //     57: astore  7
        //     59: goto  116 (offset +57)
        //     62: new  #44 // java.lang.StringBuilder
        //     65: dup
        //     66: invokespecial  #74 // java.lang.StringBuilder.<init>:()V
        //     69: iload_2
        //     70: ifeq  78 (offset +8)
        //     73: ldc  #4 // '$s'
        //     75: goto  80 (offset +5)
        //     78: ldc  #3 // '$i'
        //     80: invokevirtual  #78 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     83: ldc  #29 // '_method_'
        //     85: invokevirtual  #78 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     88: iload_3
        //     89: invokevirtual  #77 // java.lang.StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //     92: ldc  #28 // '_'
        //     94: invokevirtual  #78 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     97: iload  4
        //     99: invokevirtual  #77 // java.lang.StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    102: aload  8
        //    104: invokevirtual  #78 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    107: aload_0
        //    108: invokevirtual  #78 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    111: invokevirtual  #79 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    114: astore  7
        //    116: new  #44 // java.lang.StringBuilder
        //    119: dup
        //    120: invokespecial  #74 // java.lang.StringBuilder.<init>:()V
        //    123: aload_1
        //    124: invokevirtual  #78 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    127: aload  7
        //    129: invokevirtual  #78 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    132: invokevirtual  #79 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    135: areturn
    }

  public static void visitAnnotationFields(AnnotationVisitor arg0, Map arg1) {
        Iterator var2 = arg1.entrySet().iterator();
        while (var2.hasNext()) {
            Entry var3 = ((Entry) var2.next());
            Object var4 = var3.getValue();
            if (!var4.getClass().isArray()) {
                if (!var4.getClass().isEnum()) {
                    if (!(var4 instanceof Class)) {
                        arg0.visit(((String) var3.getKey()), var4);
                    } else {
                        arg0.visit(((String) var3.getKey()), Type.getType(((Class) var4)));
                    }
                } else {
                    arg0.visitEnum(((String) var3.getKey()), ci(var4.getClass()), var4.toString());
                }
            } else {
                Object[] var5 = ((Object[]) var4);
                AnnotationVisitor var6 = arg0.visitArray(((String) var3.getKey()));
                int var7 = 0;
                while (var7 < var5.length) {
                    var6.visit(null, var5[var7]);
                    ++var7;
                    continue;
                }
                var6.visitEnd();
            }
            continue;
        }
    }

}