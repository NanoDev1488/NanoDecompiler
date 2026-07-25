// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.utils.LoggingHelper
package org.freedesktop.dbus.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public final class LoggingHelper {

  private LoggingHelper() { // было: <init>
        super();
    }

  public static String arraysVeryDeepString(Object[] arg0) {
        if (arg0 != null) {
            return String.join(", ", arraysVeryDeepStringRecursive(arg0));
        } else {
            return null;
        }
    }

  private static List arraysVeryDeepStringRecursive(Object[] arg0) {
        ArrayList var1;
        int var3;
        int var4;
        if (arg0 != null) {
            var1 = new ArrayList();
            Object[] var2 = arg0;
            var3 = var2.length;
            var4 = 0;
        } else {
            return null;
        }
        while (var4 < var3) {
            Object var5 = var2[var4];
            if (var5 != null) {
                if (!var5.getClass().isArray()) {
                    if (!(var5 instanceof Collection)) {
                        var1.add(convertToString(var5));
                    } else {
                        Collection var6 = ((Collection) var5);
                        var1.add(convertToString(arraysVeryDeepStringRecursive(var6.toArray())));
                    }
                } else {
                    if (!var5.getClass().getComponentType().isPrimitive()) {
                        var1.add(convertToString(arraysVeryDeepStringRecursive(((Object[]) var5))));
                    } else {
                        var1.add(convertToString(var5));
                    }
                }
            } else {
                var1.add("(null)");
            }
            ++var4;
            continue;
        }
        return var1;
    }

  static String convertToString(Object arg0) {
        if (arg0 != null) {
            if (!arg0.getClass().isArray()) {
                return Objects.toString(arg0);
            } else {
                if (!arg0.getClass().getComponentType().isPrimitive()) {
                    return Objects.toString(arg0);
                } else {
                    if (arg0.getClass().getComponentType() != Boolean.TYPE) {
                        if (arg0.getClass().getComponentType() != Character.TYPE) {
                            if (arg0.getClass().getComponentType() != Integer.TYPE) {
                                if (arg0.getClass().getComponentType() != Float.TYPE) {
                                    if (arg0.getClass().getComponentType() != Double.TYPE) {
                                        if (arg0.getClass().getComponentType() != Byte.TYPE) {
                                            if (arg0.getClass().getComponentType() != Long.TYPE) {
                                                return Objects.toString(arg0);
                                            } else {
                                                return Arrays.toString(((long[]) arg0));
                                            }
                                        } else {
                                            return Arrays.toString(((byte[]) arg0));
                                        }
                                    } else {
                                        return Arrays.toString(((double[]) arg0));
                                    }
                                } else {
                                    return Arrays.toString(((float[]) arg0));
                                }
                            } else {
                                return Arrays.toString(((int[]) arg0));
                            }
                        } else {
                            return Arrays.toString(((char[]) arg0));
                        }
                    } else {
                        return Arrays.toString(((boolean[]) arg0));
                    }
                }
            }
        } else {
            return null;
        }
    }

  public static void logIf(boolean arg0, Runnable arg1) {
        if (arg0) {
            arg1.run();
        }
    }

}