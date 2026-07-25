// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.aH.h
package dev.angelvisuals.a;

import dev.angelvisuals.a.aH_ClassA3;

public class aH_ClassA5 extends aH_ClassA3 {

    // ---- поля ----
  private static final String Gv = "// reverse-engineering this jar is a waste of time, friend";
  private static final String Gw = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String Gx = "Protected by t.me/JoinerClient";
  private static final String Gy = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final String Gz = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final int tk = 875250796;
  private static final int tl = 345648114;
  private static final int tm = 1451683490;
  private static final byte[] fd;

    static {
        fd = ">4,`!? T>&2*k:CxLjz!s~Mfed#LL--5G};gCB>y vV;r|=!$sL|P4ZDr&Wa$8EHT8m;;iu0pVP3VdwVt?s2X0S[Xp;+t;0qdjs|PJxj+=EB{C@Ekh)0f[:))54(v:-\"n+\\&#{'b+wS3WTt_ktSw3N~zrJ)jgKum/}Ki@zGs\"\"5OVk@mdYt1acR<(q{_f,i y'7%lB|pz*q01|omn$x`4S{q wDSv(mQ~oyEZyYe$&/+IMN)P>XA:1J)qXd\\\"hrX".getBytes("ISO-8859-1");
    }

  public aH_ClassA5() { // было: <init>
        super();
    }

  public aH_ClassA5(float arg0) { // было: <init>
        super(arg0);
    }

  public float method31(float arg0, float arg1, float arg2, float arg3) { // было: H
        float __stk1;
        float var5 = bn();
        arg0 = arg0 / (arg3 / 2.0f);
        if (arg0 >= 1.0f) {
            arg0 = arg0 - 2.0f;
            var5 = var5 * 1.524999976158142f;
            __stk1 = arg2 / 2.0f * (arg0 * arg0 * ((var5 + 1.0f) * arg0 + var5) + 2.0f) + arg1;
        } else {
            var5 = var5 * 1.524999976158142f;
            __stk1 = arg2 / 2.0f * arg0 * arg0 * ((var5 + 1.0f) * arg0 - var5) + arg1;
        }
        return __stk1;
    }

  private static int rh(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int ri(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int rj(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}