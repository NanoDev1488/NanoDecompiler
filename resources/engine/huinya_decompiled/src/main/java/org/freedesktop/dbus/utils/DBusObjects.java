// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.utils.DBusObjects
package org.freedesktop.dbus.utils;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.freedesktop.dbus.exceptions.InvalidBusNameException;

public final class DBusObjects {

    // ---- поля ----
  private static final int MAX_NAME_LENGTH = 255;
  private static final Pattern OBJECT_REGEX_PATTERN;
  private static final Pattern BUSNAME_REGEX;
  private static final Pattern CONNID_REGEX;

    static {
        OBJECT_REGEX_PATTERN = Pattern.compile("^/([-_a-zA-Z0-9]+(/[-_a-zA-Z0-9]+)*)?$");
        BUSNAME_REGEX = Pattern.compile("^[-_a-zA-Z][-_a-zA-Z0-9]*(\\.[-_a-zA-Z][-_a-zA-Z0-9]*)*$");
        CONNID_REGEX = Pattern.compile("^:[0-9]*\\.[0-9]*$");
    }

  private DBusObjects() { // было: <init>
        super();
    }

  private static Object requireBase(Object arg0, Predicate arg1, Function arg2, String arg3) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: ifnonnull  23 (offset +22)
        //      4: aload_2
        //      5: aload_3
        //      6: ifnull  13 (offset +7)
        //      9: aload_3
        //     10: goto  14 (offset +4)
        //     13: aconst_null
        //     14: invokeinterface  #43 // java.util.function.Function.apply:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //     19: checkcast  #18 // org.freedesktop.dbus.exceptions.DBusException
        //     22: athrow
        //     23: aload_0
        //     24: instanceof  #9 // java.lang.String
        //     27: ifeq  64 (offset +37)
        //     30: aload_0
        //     31: checkcast  #9 // java.lang.String
        //     34: astore  4
        //     36: aload  4
        //     38: invokevirtual  #26 // java.lang.String.isBlank:()Z
        //     41: ifeq  64 (offset +23)
        //     44: aload_2
        //     45: aload_3
        //     46: ifnull  53 (offset +7)
        //     49: aload_3
        //     50: goto  55 (offset +5)
        //     53: ldc  #3 // '<Empty String>'
        //     55: invokeinterface  #43 // java.util.function.Function.apply:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //     60: checkcast  #18 // org.freedesktop.dbus.exceptions.DBusException
        //     63: athrow
        //     64: aload_1
        //     65: aload_0
        //     66: invokeinterface  #44 // java.util.function.Predicate.test:(Ljava/lang/Object;)Z, count 2
        //     71: ifne  96 (offset +25)
        //     74: aload_2
        //     75: aload_3
        //     76: ifnull  83 (offset +7)
        //     79: aload_3
        //     80: goto  87 (offset +7)
        //     83: aload_0
        //     84: invokestatic  #29 // java.lang.String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //     87: invokeinterface  #43 // java.util.function.Function.apply:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //     92: checkcast  #18 // org.freedesktop.dbus.exceptions.DBusException
        //     95: athrow
        //     96: aload_0
        //     97: areturn
    }

  public static String requireObjectPath(String arg0) {
        return ((String) requireBase(arg0, lp0 -> validateObjectPath(((String) lp0)), lp0 -> new InvalidObjectPathException(((String) lp0)), null));
    }

  public static String requireBusName(String arg0) {
        return requireBusName(arg0, null);
    }

  public static String requireBusName(String arg0, String arg1) {
        return ((String) requireBase(arg0, lp0 -> validateBusName(((String) lp0)), lp0 -> new InvalidBusNameException(((String) lp0)), arg1));
    }

  public static String requireNotBusName(String arg0, String arg1) {
        return ((String) requireBase(arg0, lp0 -> validateNotBusName(((String) lp0)), lp0 -> new InvalidBusNameException(((String) lp0)), arg1));
    }

  public static String requireConnectionId(String arg0) {
        return ((String) requireBase(arg0, lp0 -> validateConnectionId(((String) lp0)), lp0 -> new InvalidBusNameException(((String) lp0)), null));
    }

  public static String requireBusNameOrConnectionId(String arg0) {
        if (!validateBusName(arg0)) {
            if (!validateConnectionId(arg0)) {
                throw new InvalidBusNameException(arg0);
            } else {
                return arg0;
            }
        } else {
            return arg0;
        }
    }

  public static boolean validateBusName(String arg0) {
        return arg0 == null ? 0 : arg0.length() >= 255 ? 0 : BUSNAME_REGEX.matcher(arg0).matches();
    }

  public static boolean validateNotBusName(String arg0) {
        return !validateBusName(arg0);
    }

  public static boolean validateObjectPath(String arg0) {
        return !validateNotObjectPath(arg0);
    }

  public static boolean validateNotObjectPath(String arg0) {
        return arg0 == null ? 1 : arg0.length() > 255 ? 1 : !arg0.startsWith("/") ? 1 : !OBJECT_REGEX_PATTERN.matcher(arg0).matches();
    }

  public static boolean validateNotConnectionId(String arg0) {
        return !validateConnectionId(arg0);
    }

  public static boolean validateConnectionId(String arg0) {
        return arg0 == null ? 0 : CONNID_REGEX.matcher(arg0).matches();
    }

  public static Object requireNotNull(Object arg0, Supplier arg1) {
        if (arg0 != null) {
            return arg0;
        } else {
            throw ((Exception) arg1.get());
        }
    }

}