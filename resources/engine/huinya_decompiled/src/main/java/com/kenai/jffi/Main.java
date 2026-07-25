// исходный (обфусцированный) внутренний класс: com.kenai.jffi.Main
package com.kenai.jffi;

import com.kenai.jffi.Foreign;
import java.io.PrintStream;

public class Main {

  public Main() { // было: <init>
        super();
    }

  public static void main(String[] arg0) {
        try {
            System.out.printf("jffi jar version=%d.%d.%d\n", new Object[]{Integer.valueOf(Foreign.VERSION_MAJOR), Integer.valueOf(Foreign.VERSION_MINOR), Integer.valueOf(Foreign.VERSION_MICRO)});
            Foreign var1 = Foreign.getInstance();
            Object[] __obj2 = new Object[3];
            __obj2[0] = Integer.valueOf(method1(var1, 16));
            __obj2[1] = Integer.valueOf(method1(var1, 8));
            __obj2[2] = Integer.valueOf(method1(var1, 0));
            System.out.printf("jffi stub version=%d.%d.%d\n", __obj2);
            System.out.println(new StringBuilder().append("memory fault protection enabled=").append(Foreign.isMemoryProtectionEnabled()).toString());
            System.out.println(new StringBuilder().append("stub arch=").append(var1.getArch()).toString());
            Object[] __obj3 = new Object[1];
            __obj3[0] = Integer.valueOf(var1.getJNIVersion());
            System.out.printf("JNI version=%#x\n", __obj3);
        } catch (Throwable e1) {
            Throwable var1 = e1;
            System.err.println(new StringBuilder().append("Error: ").append(var1).toString());
        }
    }

  private static int method1(Foreign arg0, int arg1) { // было: v
        return arg0.getVersion() >> arg1 & 255;
    }

}