// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.aH.g
package dev.angelvisuals.a;

import dev.angelvisuals.a.aH_ClassA3;

public class aH_ClassA6 extends aH_ClassA3 {

    // ---- поля ----
  private static final String ET = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final String EU = "// class hierarchy hashing: ENABLED";
  private static final String EV = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String EW = "// === DO NOT TOUCH ===";
  private static final String EX = "// flow obfuscation: ENABLED";
  private static final int si = 1377474343;
  private static final int sj = -1585549700;
  private static final int sk = -295298927;
  private static final byte[] eN;

    static {
        eN = "zDRA#s#\\%1Rpxe1==1h'b*24[j,PWGB+o]`s\"Z}XRN&~=`j+/B<{V3*g \"+uP=:kiB(mo^Z)w)-XO/vy %ND}5)` ]v(Wu_9Z5<w1(k\"cSLNxBNBK~F'ZNI\"f6tb>LtxZ$@+ZFXt_'i3c+V0`W\\b3 u3\\|}4MZ?c4yQRT6`T,PA5cQXm=ahT[YBj880vx^gAqI~42G8lU[ w)9-fL3)~=@&cIv7}x4H(9Y{zG[RB!SZ=G<u$W<faz-y%vaXIa(WR".getBytes("ISO-8859-1");
    }

  public aH_ClassA6() { // было: <init>
        super();
    }

  public aH_ClassA6(float arg0) { // было: <init>
        super(arg0);
    }

  public float method32(float arg0, float arg1, float arg2, float arg3) { // было: F
        float var5 = bn();
        arg0 = arg0 / arg3 - 1.0f;
        return arg2 * (arg0 * arg0 * ((var5 + 1.0f) * arg0 + var5) + 1.0f) + arg1;
    }

  private static int ql(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int qm(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int qn(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}