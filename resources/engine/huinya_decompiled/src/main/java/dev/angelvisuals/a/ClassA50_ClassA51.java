// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.E.a
package dev.angelvisuals.a;

import dev.angelvisuals.a.ClassA44_ClassA45_ClassA46;
import dev.angelvisuals.a.ClassA47_ClassA48_ClassA49;
import java.util.LinkedList;
import java.util.Queue;

public class ClassA50_ClassA51 {

    // ---- поля ----
  private final Queue field220; // было: b
  private int la;
  private int lb;
  private static final String sE = "// good luck with the next 9999 classes";
  private static final String sF = "// this jar protected by JoinerObfuscator";
  private static final String sG = "// class hierarchy hashing: ENABLED";
  private static final String sH = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final String sI = "// class hierarchy hashing: ENABLED";
  private static final int lc = 1312961411;
  private static final int ld = -531925313;
  private static final int le = -310596971;
  private static final byte[] cC;

    static {
        cC = "M=^  cKVp#XdZZNb[*qLI2y+c&}>\"~!2(yt?-2/Wd97P^dP,hxk|;6hJ5l&p_mZYGgD5$kl>DwIP^_/Js'W2R>}`0ud*a=0%j2Eap.0_\\ Y!3Up4u?.[LyxhZH9%[9&Hd6A_I8>\"eK9%~uEJ#j#X)Gol!:`j'<72!yh<MsiFO&:XUxS^yj-rMq!C)]>zT>~`FF&4QL\"QyK|V8<`.Klm%2~`^:k/dSPxTryL}84DW4S!qDY;Q@jsAZH$>_gN/$E@/".getBytes("ISO-8859-1");
    }

  public ClassA50_ClassA51() { // было: <init>
        super();
        field220 = new LinkedList();
        la = -1868292784 ^ -1868292784;
        lb = -681445683 ^ -681445539;
    }

  public ClassA50_ClassA51 method439(int arg0) { // было: a
        lb = Math.max(-751867291 ^ -751867292, arg0);
        return this;
    }

  public ClassA50_ClassA51 method440(Class arg0, ClassA47_ClassA48_ClassA49 arg1) { // было: a
        field220.add(new ClassA44_ClassA45_ClassA46(arg0, arg1));
        return this;
    }

  public boolean method441(Object arg0) { // было: p
        ClassA44_ClassA45_ClassA46 var2 = ((ClassA44_ClassA45_ClassA46) field220.peek());
        if (var2 != null) {
            int var3 = 170741126 ^ 170741126;
            if (var2.field218.isInstance(arg0)) {
                boolean var4 = var2.method438(arg0);
                if (var4) {
                    field220.poll();
                    var3 = 254442632 ^ 254442633;
                }
            }
            if (var3 == 0) {
                la = la + (-706454618 ^ -706454617);
                if (la <= lb) {
                    return -1589818346 ^ -1589818346;
                } else {
                    field220.clear();
                    return 2032133504 ^ 2032133505;
                }
            } else {
                la = 1657054076 ^ 1657054076;
                return field220.isEmpty();
            }
        } else {
            return -2056029862 ^ -2056029861;
        }
    }

  public boolean method442() { // было: X
        return field220.isEmpty();
    }

  private static int jD(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int jE(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int jF(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}