// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.aH.e
package dev.angelvisuals.a;

import dev.angelvisuals.a.aH_ClassA7;
import dev.angelvisuals.a.cs;

public class aH_ClassA8 extends aH_ClassA7 {

    // ---- поля ----
  private static final String zO = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String zP = "// good luck with the next 9999 classes";
  private static final String zQ = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final String zR = "// every class watermarked, every string encrypted, every number xored";
  private static final String zS = "// you are reading machine-generated garbage";
  private static final int pk = 1199282528;
  private static final int pl = -962646354;
  private static final int pm = 1554726779;
  private static final byte[] dR;

    static {
        dR = "y%5kN!3aUL&C?N.]LhK}( LAPrnAE3ar@yys<[m%`Kf8(!Q7)8rb~EDM(3M1c'4wYV}wICFs_0}~9\"Lh,NRO/Oh6r58zV'cp~qUUef V()u3^@;L,gmUL$0>3a(G`:PIcxNBkj@[(LR~fD/?ij:,bbR)/$={]i\\E)(i#=k}eX6'h\"3BlT%~\\[)59?6VDQk5'nh[m%:`v&AU)s5Jwr\"3kuF*S5/r_kMx% ?C9@}Wd8gq\"AA,YA 3W^.5y[+a^,o^~".getBytes("ISO-8859-1");
    }

  public aH_ClassA8(float arg0, float arg1) { // было: <init>
        super(arg0, arg1);
    }

  public aH_ClassA8() { // было: <init>
        super();
    }

  public float method34(float arg0, float arg1, float arg2, float arg3) { // было: E
        float var5 = bc();
        float var6 = bd();
        if (arg0 != 0.0f) {
            arg0 = arg0 / arg3;
            if (arg0 != 1.0f) {
                if (var6 == 0.0f) {
                    var6 = arg3 * 0.30000001192092896f;
                }
                float var7 = 0.0f;
                if (var5 >= Math.abs(arg2)) {
                    var7 = var6 / 6.2831854820251465f * ((float) Math.asin(((double) (arg2 / var5))));
                } else {
                    var5 = arg2;
                    var7 = var6 / 4.0f;
                }
                arg0 = arg0 - 1.0f;
                return -(var5 * ((float) Math.pow(2.0, ((double) (10.0f * arg0)))) * ((float) cs.method1411(((double) (arg0 * arg3 - var7)) * 6.283185307179586 / ((double) var6)))) + arg1;
            } else {
                return arg1 + arg2;
            }
        } else {
            return arg1;
        }
    }

  private static int nw(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int nx(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int ny(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}