// исходный (обфусцированный) внутренний класс: com.kenai.jffi.Util
package com.kenai.jffi;

import java.util.Locale;

public final class Util {

  private Util() { // было: <init>
        super();
    }

  static int ffi_align(int arg0, int arg1) {
        return (arg0 - 1 | arg1 - 1) + 1;
    }

  public static boolean startsWithIgnoreCase(String arg0, String arg1, Locale arg2) {
        return arg0.startsWith(arg1) ? 1 : arg0.toUpperCase(arg2).startsWith(arg1.toUpperCase(arg2)) ? 1 : arg0.toLowerCase(arg2).startsWith(arg1.toLowerCase(arg2));
    }

  public static boolean equalsIgnoreCase(String arg0, String arg1, Locale arg2) {
        return arg0.equalsIgnoreCase(arg1) ? 1 : arg0.toUpperCase(arg2).equals(arg1.toUpperCase(arg2)) ? 1 : arg0.toLowerCase(arg2).equals(arg1.toLowerCase(arg2));
    }

}