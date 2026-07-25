// исходный (обфусцированный) внутренний класс: jnr.posix.JavaPOSIX.IDHelper
package jnr.posix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import jnr.posix.util.Platform;

final class JavaPOSIX_IDHelper {

    // ---- поля ----
  private static final String ID_CMD;
  private static final int NOBODY;

    static {
        String __stk1;
        int __stk2;
        __stk1 = !Platform.IS_SOLARIS ? "/usr/bin/id" : "/usr/xpg4/bin/id";
        ID_CMD = __stk1;
        __stk2 = !Platform.IS_WINDOWS ? 32767 : 0;
        NOBODY = __stk2;
    }

  private JavaPOSIX_IDHelper() { // было: <init>
        super();
    }

  public static int getInt(String arg0) {
        int __stk2;
        try {
            Process var1 = Runtime.getRuntime().exec(new String[]{ID_CMD, arg0});
            BufferedReader var2 = new BufferedReader(new InputStreamReader(var1.getInputStream()));
            __stk2 = Integer.parseInt(var2.readLine());
        } catch (IOException e1) {
            Throwable var1 = e1;
            return NOBODY;
        } catch (NumberFormatException e2) {
            Throwable var1 = e2;
            return NOBODY;
        } catch (SecurityException e3) {
            Throwable var1 = e3;
            return NOBODY;
        }
    }

  public static String getString(String arg0) {
        String __stk2;
        try {
            Process var1 = Runtime.getRuntime().exec(new String[]{ID_CMD, arg0});
            BufferedReader var2 = new BufferedReader(new InputStreamReader(var1.getInputStream()));
            __stk2 = var2.readLine();
        } catch (IOException e1) {
            Throwable var1 = e1;
            return null;
        }
    }

}