// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.T
package dev.angelvisuals.a;

import dev.angelvisuals.AngelVisuals;
import net.minecraft.class_2960;

public class ClassA161 {

    // ---- поля ----
  final class_2960 field895; // было: c
  private static final String dO = "// class hierarchy hashing: ENABLED";
  private static final String dP = "// stop. seriously. go play minecraft instead";
  private static final String dQ = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String dR = "// reverse-engineering this jar is a waste of time, friend";
  private static final String dS = "// nice try. closed source for a reason.";
  private static final int cd = -1592583537;
  private static final int ce = 274388181;
  private static final int cf = -1891857615;
  private static final byte[] field896; // было: U

    static {
        field896 = "H#s5XjUDY}V}nBtd:g!VgMe;H=EsUpFO8fg\"Ldug 8t@{T@MH>\\ZzOW*>FTgIF3j3}VA>YvW6;Pj}fC4V`s;!^:!V1?F[m!r7;4l}.EI/J1<.TOnL0s(TwGu.`/.Li.A4g#}$m\\= <sGhF,|g \"&*`BDji*_]Ju]$m6r\\9T2-7=z>ic9O:K{SP+Ep5s&6BORDH-`Yo0BUSG,6)!*w3)'{&a6@U+9B>W::78Au*|sk#A%E3*Mt0QyM]kPH`QA8lm*".getBytes("ISO-8859-1");
    }

  public ClassA161(String arg0) { // было: <init>
        super();
        field895 = AngelVisuals.id(method1595(arg0));
    }

  public ClassA161(class_2960 arg0) { // было: <init>
        super();
        field895 = class_2960.method_60655(arg0.method_12836(), arg0.method_12832());
    }

   String method1595(String arg0) { // было: a
        StringBuilder var2;
        int var4;
        int var5;
        if (!class_2960.method_20208(arg0)) {
            var2 = new StringBuilder();
            char[] var3 = arg0.toLowerCase().toCharArray();
            var4 = var3.length;
            var5 = 1757959961 ^ 1757959961;
        } else {
            return arg0;
        }
        while (var5 < var4) {
            char var6 = var3[var5];
            if (class_2960.method_29184(var6)) {
                var2.append(var6);
            }
            ++var5;
            continue;
        }
        return var2.toString();
    }

  public class_2960 method1596() { // было: b
        return field895;
    }

  private static int bI(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int bJ(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int bK(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}