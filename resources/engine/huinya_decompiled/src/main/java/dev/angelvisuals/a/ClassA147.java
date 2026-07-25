// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.l
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import java.io.InputStream;
import java.net.URL;
import net.minecraft.class_1011;
import net.minecraft.class_1043;
import net.minecraft.class_1060;
import net.minecraft.class_2960;
import net.minecraft.class_310;

public class ClassA147 {

    // ---- поля ----
  private static final String ae = "// nice try. closed source for a reason.";
  private static final String af = "// good luck with the next 9999 classes";
  private static final String ag = "// === DO NOT TOUCH ===";
  private static final String ah = "// class hierarchy hashing: ENABLED";
  private static final String ai = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final int field808 = -1436975870; // было: O
  private static final int field809 = 368862996; // было: P
  private static final int field810 = 861135376; // было: Q
  private static final byte[] field811; // было: l

    static {
        field811 = "e6<P@Z@ )O*`J}9?z9kwXyb<]g@mJD5G7_Kg&+$:B%i0s{vs:\\h4`|>s}]SN?:DDz)7wUo$ca6F_QXkcV[Rk?=P4$\\s}KI9+ZIa=1T$6g!0nTTY\\;ta$q_/liQ^kK}DAD]=Bm`nU9vL_Z;A25:bjEo2W<V1\\xjLJo(U^6[%SXP`Pp` \"hD6{h;?l|]nD1=DsgPr0j\"?p.KRWo\"^6GH_k)*C6zG|gQE)XlFL-AZ)j%7gzfO%g4C.}oJZ_7giDgi\")".getBytes("ISO-8859-1");
    }

  public ClassA147() { // было: <init>
        super();
    }

  public static class_2960 method1455(String arg0, class_1011 arg1) { // было: a
        if (arg1 != null) {
            class_2960 var2 = class_2960.method_60655(Decryptor.method1945(XorDecoder.method1946("WìÂkÜÓêBÛûérÌÈRìÀ3Ï«", -1802990603 ^ 891171829)), arg0 + System.currentTimeMillis());
            class_310 var3 = class_310.method_1551();
            var3.execute(() -> method1457(arg1, var3, var2));
            return var2;
        } else {
            return null;
        }
    }

  public static class_1011 method1456(String arg0) { // было: a
        class_1011 __stk1;
        try {
            URL var1 = new URL(arg0);
            InputStream var2 = var1.openStream();
            class_1011 var3 = class_1011.method_4309(var2);
            var2.close();
            __stk1 = var3;
        } catch (Exception e1) {
            Throwable var1 = e1;
            return null;
        }
    }

  private static void method1457(class_1011 arg0, class_310 arg1, class_2960 arg2) { // было: a
        try {
            class_1043 var3 = new class_1043(arg0);
            arg1.method_1531().method_4616(arg2, var3);
        } catch (Exception e1) {
            Throwable var3 = e1;
        }
    }

  private static int method1458(int arg0, int arg1) { // было: H
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int method1459(int arg0, int arg1) { // было: I
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int method1460(int arg0, int arg1) { // было: J
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}