// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.dp
package dev.angelvisuals.a;

import dev.angelvisuals.a.bp;
import java.util.List;
import lombok.Generated;

public class dp {

    // ---- поля ----
  protected final bp field905; // было: k
  protected final bp field906; // было: l
  protected final bp field907; // было: m
  protected final bp field908; // было: n
  private static final String CL = "// every class watermarked, every string encrypted, every number xored";
  private static final String CM = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final String CN = "// every class watermarked, every string encrypted, every number xored";
  private static final String CO = "// === DO NOT TOUCH ===";
  private static final String CP = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final int qY = 1461516186;
  private static final int qZ = 1884180928;
  private static final int ra = 1581430388;
  private static final byte[] ev;

    static {
        ev = "cXq |I3?c@,|,'$*8W0R{CPw8#~}68kDunFD[e4%bRKw} M[1Vdc6pzk$(sekuaD&E=p HC&lu|DLu/`@vK_vtj?wKicwx!~hvZtBL&+{bi0>a)Y?^qA6Q|#fYAdD_Hhchr-\\A2D'cNGe4]yuUq?bHOrS^*  <{.Nn'RTPjY!qHN^E9ni ?HU\"$VgKgU5=1}T3)7'%ZA Z7b)*1K+4:g9oi\\Wh:fyH3^Ar{u5V,ob&xYne]|[):d4^8u,Gab,dlh".getBytes("ISO-8859-1");
    }

  protected dp(bp arg0, bp arg1, bp arg2, bp arg3) { // было: <init>
        super();
        field905 = arg0;
        field906 = arg1;
        field907 = arg2;
        field908 = arg3;
    }

  public static dp method1657(bp arg0, bp arg1, bp arg2, bp arg3) { // было: a
        return new dp(arg0, arg1, arg2, arg3);
    }

  public static dp method1658(List arg0) { // было: a
        return new dp(((bp) arg0.get(1529996192 ^ 1529996192)), ((bp) arg0.get(-330039726 ^ -330039725)), ((bp) arg0.get(700783672 ^ 700783674)), ((bp) arg0.get(963600632 ^ 963600635)));
    }

  public dp method1659() { // было: a
        return this;
    }

  public dp method1660(float arg0) { // было: a
        return new dp(field905.method1687(arg0), field906.method1687(arg0), field907.method1687(arg0), field908.method1687(arg0));
    }

    @Generated
  public bp method1661() { // было: l
        return field905;
    }

    @Generated
  public bp method1662() { // было: m
        return field906;
    }

    @Generated
  public bp method1663() { // было: n
        return field907;
    }

    @Generated
  public bp method1664() { // было: o
        return field908;
    }

  private static int pj(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int pk(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int pl(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}