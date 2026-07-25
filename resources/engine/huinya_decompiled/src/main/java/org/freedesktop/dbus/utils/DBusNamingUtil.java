// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.utils.DBusNamingUtil
package org.freedesktop.dbus.utils;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.freedesktop.dbus.annotations.DBusBoundProperty;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.annotations.DBusMemberName;

public final class DBusNamingUtil {

    // ---- поля ----
  private static final Pattern DOLLAR_PATTERN;

    static {
        DOLLAR_PATTERN = Pattern.compile("[$]");
    }

  private DBusNamingUtil() { // было: <init>
        super();
    }

  public static String getInterfaceName(Class arg0) {
        Objects.requireNonNull(arg0, "Class must not be null");
        if (!arg0.isAnnotationPresent(DBusInterfaceName.class)) {
            return DOLLAR_PATTERN.matcher(arg0.getName()).replaceAll(".");
        } else {
            return (((DBusInterfaceName) arg0.getAnnotation(DBusInterfaceName.class))).value();
        }
    }

  public static String getMethodName(Method arg0) {
        Objects.requireNonNull(arg0, "method must not be null");
        if (!arg0.isAnnotationPresent(DBusMemberName.class)) {
            return arg0.getName();
        } else {
            return (((DBusMemberName) arg0.getAnnotation(DBusMemberName.class))).value();
        }
    }

  public static String getPropertyName(Method arg0) {
        Objects.requireNonNull(arg0, "method must not be null");
        if (!arg0.isAnnotationPresent(DBusBoundProperty.class)) {
            String var1 = arg0.getName();
            String var2 = var1.toLowerCase();
            if (!var2.startsWith("get")) {
                if (!var2.startsWith("set")) {
                    if (var2.startsWith("is")) {
                        if (!"is".equals(var2)) {
                            var1 = var1.substring(2);
                        }
                    }
                } else {
                    if ("set".equals(var2)) {
                        if (var2.startsWith("is")) {
                            if (!"is".equals(var2)) {
                                var1 = var1.substring(2);
                            }
                        }
                    } else {
                        var1 = var1.substring(3);
                    }
                }
            } else {
                if (!"get".equals(var2)) {
                    var1 = var1.substring(3);
                } else {
                    if (!var2.startsWith("set")) {
                        if (var2.startsWith("is")) {
                            if (!"is".equals(var2)) {
                                var1 = var1.substring(2);
                            }
                        }
                    } else {
                        if ("set".equals(var2)) {
                            if (var2.startsWith("is")) {
                                if (!"is".equals(var2)) {
                                    var1 = var1.substring(2);
                                }
                            }
                        } else {
                            var1 = var1.substring(3);
                        }
                    }
                }
            }
            return var1;
        } else {
            String var1 = (((DBusBoundProperty) arg0.getAnnotation(DBusBoundProperty.class))).name();
            if ("".equals(var1)) {
                var1 = arg0.getName();
                String var2 = var1.toLowerCase();
                if (!var2.startsWith("get")) {
                    if (!var2.startsWith("set")) {
                        if (var2.startsWith("is")) {
                            if (!"is".equals(var2)) {
                                var1 = var1.substring(2);
                            }
                        }
                    } else {
                        if ("set".equals(var2)) {
                            if (var2.startsWith("is")) {
                                if (!"is".equals(var2)) {
                                    var1 = var1.substring(2);
                                }
                            }
                        } else {
                            var1 = var1.substring(3);
                        }
                    }
                } else {
                    if (!"get".equals(var2)) {
                        var1 = var1.substring(3);
                    } else {
                        if (!var2.startsWith("set")) {
                            if (var2.startsWith("is")) {
                                if (!"is".equals(var2)) {
                                    var1 = var1.substring(2);
                                }
                            }
                        } else {
                            if ("set".equals(var2)) {
                                if (var2.startsWith("is")) {
                                    if (!"is".equals(var2)) {
                                        var1 = var1.substring(2);
                                    }
                                }
                            } else {
                                var1 = var1.substring(3);
                            }
                        }
                    }
                }
                return var1;
            } else {
                return var1;
            }
        }
    }

  public static String getSignalName(Class arg0) {
        Objects.requireNonNull(arg0, "Class must not be null");
        if (!arg0.isAnnotationPresent(DBusMemberName.class)) {
            return arg0.getSimpleName();
        } else {
            return (((DBusMemberName) arg0.getAnnotation(DBusMemberName.class))).value();
        }
    }

  public static String getAnnotationName(Class arg0) {
        Objects.requireNonNull(arg0, "Class must not be null");
        if (!arg0.isAnnotationPresent(DBusInterfaceName.class)) {
            return DOLLAR_PATTERN.matcher(arg0.getName()).replaceAll(".");
        } else {
            return (((DBusInterfaceName) arg0.getAnnotation(DBusInterfaceName.class))).value();
        }
    }

}