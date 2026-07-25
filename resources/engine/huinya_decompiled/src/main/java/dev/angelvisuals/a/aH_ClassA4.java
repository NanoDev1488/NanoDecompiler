// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.aH.c
package dev.angelvisuals.a;

import dev.angelvisuals.a.aH_ClassA3;

public class aH_ClassA4 extends aH_ClassA3 {

    // ---- поля ----
  private static final String ra = "// flow obfuscation: ENABLED";
  private static final String rb = "// every class watermarked, every string encrypted, every number xored";
  private static final String rc = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String rd = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final String re = "// nice try. closed source for a reason.";
  private static final int ka = 152758782;
  private static final int kb = -1424552267;
  private static final int kc = 450067277;
  private static final byte[] cn;

    static {
        cn = "Zcp\\g7Wb8-P*XV5+6:P)%[rn\\Ho*,~,R%K*Gwt>dJc=r\\7AOFP`B.BG.me@%RupfqSO/FFZ;EJ^qYI /{zt=\"-/~yDQ>?4XKJq! I-L/&R$p<<oV\\P^>#j~/:V{IG[n46*ZrkhQV6[Vm{ ZyXcR+/Fy+OIdpEl7r&c:_z{jj8\\-.m\"{1koIcI}j5#lh!E0BHlU[95wTIxD l!w_x<`u&^IVP7L$66z4J(\"h:4N[ M+MO&mDyf(_zJw 0+|s]$PG$".getBytes("ISO-8859-1");
    }

  public aH_ClassA4() { // было: <init>
        super();
    }

  public aH_ClassA4(float arg0) { // было: <init>
        super(arg0);
    }

  public float method30(float arg0, float arg1, float arg2, float arg3) { // было: D
        float var5 = bn();
        arg0 = arg0 / arg3;
        return arg2 * arg0 * arg0 * ((var5 + 1.0f) * arg0 - var5) + arg1;
    }

  private static int iK(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int iL(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int iM(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}