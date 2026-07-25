// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.utils.Hexdump
package org.freedesktop.dbus.utils;

import java.io.PrintStream;

public final class Hexdump {

    // ---- поля ----
  private static final char[] HEX_CHARS;

    static {
        HEX_CHARS = new char[]{48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
    }

  private Hexdump() { // было: <init>
        super();
    }

  public static String toHex(byte[] arg0) {
        return toHex(arg0, true);
    }

  public static String toHex(byte[] arg0, boolean arg1) {
        return toHex(arg0, 0, arg0.length, arg1);
    }

  public static String toHex(byte[] arg0, int arg1, int arg2, boolean arg3) {
        StringBuilder var4 = new StringBuilder();
        int var5 = arg1 + arg2;
        int var6 = arg1;
        while (var6 < var5) {
            if (var6 >= arg0.length) {
                if (arg3) {
                    var4.append(' ');
                    var4.append(' ');
                    var4.append(' ');
                }
            } else {
                var4.append(HEX_CHARS[(arg0[var6] & 240) >> 4]);
                var4.append(HEX_CHARS[arg0[var6] & 15]);
                if (arg3) {
                    var4.append(' ');
                }
            }
            ++var6;
            continue;
        }
        return var4.toString();
    }

  public static String toAscii(byte[] arg0) {
        return toAscii(arg0, 0, arg0.length);
    }

  public static String toAscii(byte[] arg0, int arg1, int arg2) {
        StringBuilder var3 = new StringBuilder();
        int var4 = arg1 + arg2;
        int var5 = arg1;
        while (var5 < var4) {
            if (var5 >= arg0.length) {
                var3.append(' ');
            } else {
                if (20 > arg0[var5]) {
                    var3.append('.');
                } else {
                    if (126 < arg0[var5]) {
                        var3.append('.');
                    } else {
                        var3.append(((char) arg0[var5]));
                    }
                }
            }
            ++var5;
            continue;
        }
        return var3.toString();
    }

  public static String format(byte[] arg0) {
        return format(arg0, 80);
    }

  public static String format(byte[] arg0, int arg1) {
        int var2 = (arg1 - 8) / 4;
        int var3 = 0;
        StringBuilder var4 = new StringBuilder();
        do {
            int var5 = 0;
            while (var5 < 6) {
                var4.append(HEX_CHARS[(var3 << var5 * 4 & 15728640) >> 20]);
                ++var5;
                continue;
            }
            var4.append('\t');
            var4.append(toHex(arg0, var3, var2, true));
            var4.append(' ');
            var4.append(toAscii(arg0, var3, var2));
            var4.append('\n');
            var3 = var3 + var2;
        } while (var3 < arg0.length);
        var4.deleteCharAt(var4.length() - 1);
        return var4.toString();
    }

  public static void print(byte[] arg0) {
        print(arg0, System.err);
    }

  public static void print(byte[] arg0, int arg1) {
        print(arg0, arg1, System.err);
    }

  public static void print(byte[] arg0, int arg1, PrintStream arg2) {
        arg2.print(format(arg0, arg1));
    }

  public static void print(byte[] arg0, PrintStream arg1) {
        arg1.print(format(arg0));
    }

  public static String toByteArray(byte[] arg0) {
        return toByteArray(arg0, 0, arg0.length);
    }

  public static String toByteArray(byte[] arg0, int arg1, int arg2) {
        StringBuilder var3 = new StringBuilder();
        int var4 = arg1;
        while (var4 < arg2) {
            if (var4 >= arg0.length) {
                break;
            }
            var3.append('0');
            var3.append('x');
            var3.append(HEX_CHARS[(arg0[var4] & 240) >> 4]);
            var3.append(HEX_CHARS[arg0[var4] & 15]);
            if (var4 + 1 < arg2) {
                if (var4 + 1 < arg0.length) {
                    var3.append(',');
                }
            }
            ++var4;
            continue;
        }
        return var3.toString();
    }

}