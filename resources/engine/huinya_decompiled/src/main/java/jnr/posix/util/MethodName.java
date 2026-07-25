// исходный (обфусцированный) внутренний класс: jnr.posix.util.MethodName
package jnr.posix.util;

public class MethodName {

    // ---- поля ----
  private static final int CLIENT_CODE_STACK_INDEX;

    static {
        int var0 = 0;
        StackTraceElement[] var1 = Thread.currentThread().getStackTrace();
        int var2 = var1.length;
        int var3 = 0;
        while (var3 < var2) {
            Object var4 = var1[var3];
            ++var0;
            if (!var4.getClassName().equals(MethodName.class.getName())) {
                ++var3;
                continue;
            } else {
                break;
            }
        }
        CLIENT_CODE_STACK_INDEX = var0;
    }

  public MethodName() { // было: <init>
        super();
    }

  public static String getMethodName() {
        return Thread.currentThread().getStackTrace()[CLIENT_CODE_STACK_INDEX].getMethodName();
    }

  public static String getCallerMethodName() {
        return Thread.currentThread().getStackTrace()[CLIENT_CODE_STACK_INDEX + 1].getMethodName();
    }

}