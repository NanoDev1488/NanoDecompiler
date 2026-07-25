// исходный (обфусцированный) внутренний класс: jnr.posix.WString
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.mapper.ToNativeConverter;
import jnr.posix.WString_Anon1;
import jnr.posix.util.WindowsHelpers;

public final class WString {

    // ---- поля ----
  static final Runtime runtime;
  private final byte[] bytes;
  public static final ToNativeConverter Converter;

    static {
        runtime = Runtime.getSystemRuntime();
        Converter = new WString_Anon1();
    }

   WString(String arg0) { // было: <init>
        super();
        bytes = WindowsHelpers.toWString(arg0);
    }

  private WString(byte[] arg0) { // было: <init>
        super();
        bytes = arg0;
    }

  public static WString path(String arg0) {
        return new WString(path(arg0, false));
    }

  public static byte[] path(String arg0, boolean arg1) {
        if (arg1) {
            if (arg0.length() > 240) {
                if (!arg0.startsWith("//")) {
                    if (!arg0.startsWith("\\\\")) {
                        if (WindowsHelpers.isDriveLetterPath(arg0)) {
                            arg0 = !arg0.contains("/") ? new StringBuilder().append("\\\\?\\").append(arg0).toString() : new StringBuilder().append("//?/").append(arg0).toString();
                        }
                    } else {
                        arg0 = new StringBuilder().append("\\\\?\\UNC\\").append(arg0.substring(2)).toString();
                    }
                } else {
                    arg0 = new StringBuilder().append("//?/UNC/").append(arg0.substring(2)).toString();
                }
            }
        }
        return WindowsHelpers.toWPath(arg0);
    }

  static byte[] access$000(WString arg0) {
        return arg0.bytes;
    }

}