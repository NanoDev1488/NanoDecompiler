// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.aH.a
package dev.angelvisuals.a;

import dev.angelvisuals.a.aH_ClassA7;
import dev.angelvisuals.a.cs;

public class aH_ClassA9 extends aH_ClassA7 {

    // ---- поля ----
  private static final String iA = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final String iB = "// good luck with the next 9999 classes";
  private static final String iC = "// good luck with the next 9999 classes";
  private static final String iD = "// stop. seriously. go play minecraft instead";
  private static final String iE = "// good luck with the next 9999 classes";
  private static final int eX = 1064685186;
  private static final int eY = 1277217894;
  private static final int eZ = -619997452;
  private static final byte[] aP;

    static {
        aP = "=ySS9qJ08T(FTH,VKb~;n'U`P6>9Hr|og0zFF @9Y7!->0myUTL!]Fi0=wPcxuKW]euTcLFcdz%|d9@lxme(JR4,R E}xF-wKlP!S*;$sp;vwB%seqT0%;XB-MyDA>\\8.]B+-mC}uYEi9[UgV?n=.FWa)X7.Dl/bpZglTK<Q?)ra3RPq}4eMnT2YD(ZeE40bOX,+`px$m\\]KvsMg_ 9a(Vv (@Uh+2~d*j'e3Bk:F=\\eh\"Pq}`<vyo`6ImokOlA$".getBytes("ISO-8859-1");
    }

  public aH_ClassA9(float arg0, float arg1) { // было: <init>
        super(arg0, arg1);
    }

  public aH_ClassA9() { // было: <init>
        super();
    }

  public float method35(float arg0, float arg1, float arg2, float arg3) { // было: a
        float __stk1;
        float var5 = bc();
        float var6 = bd();
        if (arg0 != 0.0f) {
            arg0 = arg0 / (arg3 / 2.0f);
            if (arg0 != 2.0f) {
                if (var6 == 0.0f) {
                    var6 = arg3 * 0.45000001788139343f;
                }
                float var7 = 0.0f;
                if (var5 >= Math.abs(arg2)) {
                    var7 = var6 / 6.2831854820251465f * ((float) Math.asin(((double) (arg2 / var5))));
                } else {
                    var5 = arg2;
                    var7 = var6 / 4.0f;
                }
                if (arg0 >= 1.0f) {
                    arg0 = arg0 - 1.0f;
                    __stk1 = var5 * ((float) Math.pow(2.0, ((double) (-10.0f * arg0)))) * ((float) cs.method1411(((double) (arg0 * arg3 - var7)) * 6.283185307179586 / ((double) var6))) * 0.5f + arg2 + arg1;
                } else {
                    arg0 = arg0 - 1.0f;
                    __stk1 = -0.5f * var5 * ((float) Math.pow(2.0, ((double) (10.0f * arg0)))) * ((float) cs.method1411(((double) (arg0 * arg3 - var7)) * 6.283185307179586 / ((double) var6))) + arg1;
                }
                return __stk1;
            } else {
                return arg1 + arg2;
            }
        } else {
            return arg1;
        }
    }

  private static int et(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int eu(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int ev(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}