// исходный (обфусцированный) внутренний класс: com.kenai.jffi.FaultException
package com.kenai.jffi;

import com.kenai.jffi.Foreign;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class FaultException extends RuntimeException {

    // ---- поля ----
  private final int signal;

   FaultException(int arg0, long[] arg1, long[] arg2, long[] arg3) { // было: <init>
        super(String.format("Received signal %d", new Object[]{Integer.valueOf(arg0)}));
        setStackTrace(createStackTrace(arg1, arg2, arg3, fillInStackTrace().getStackTrace()));
        signal = arg0;
    }

  private static StackTraceElement[] createStackTrace(long[] arg0, long[] arg1, long[] arg2, StackTraceElement[] arg3) {
        ArrayList var4 = new ArrayList();
        int var5 = 0;
        while (var5 < arg0.length) {
            String var6 = new String(Foreign.getZeroTerminatedByteArray(arg1[var5]));
            String var7 = new String(Foreign.getZeroTerminatedByteArray(arg2[var5]));
            var4.add(new StackTraceElement("native", var6, var7, -1));
            ++var5;
            continue;
        }
        var4.addAll(Arrays.asList(arg3));
        return ((StackTraceElement[]) var4.toArray(new StackTraceElement[var4.size()]));
    }

  public int getSignal() {
        return signal;
    }

}