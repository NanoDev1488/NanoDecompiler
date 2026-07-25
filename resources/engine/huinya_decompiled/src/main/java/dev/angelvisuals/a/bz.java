// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.bZ
package dev.angelvisuals.a;

import dev.angelvisuals.a.ap;
import lombok.Generated;
import net.minecraft.class_332;

public class bZ extends ap {

    // ---- поля ----
  private final int kA;
  private final int kB;
  private final float cr;
  private static final String rU = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final String rV = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final String rW = "Protected by t.me/JoinerClient";
  private static final String rX = "// this jar protected by JoinerObfuscator";
  private static final String rY = "// every class watermarked, every string encrypted, every number xored";
  private static final int kC = -649782211;
  private static final int kD = 194105491;
  private static final int kE = 1287811763;
  private static final byte[] cv;

    static {
        cv = "(]8j:hP26ctRlXJ)Sa!k+cgbmA\"lI?fT'H=$J/djt~)%zY't:sJ'&,jSs2^o1SHt$>0Usc9X9A@P^E.KD}\\07Z!WMaa\">-`n-_$K! ,i#qX+tp'bxs1:;?M&n!RYS_qo|wK91RzMvy7;04RiH@~trJ_b=wvKr`SG~_{bsO[6/GBWbr(fCV=epr0gLWbh4#_}*o;SI|q*paOnNS>hsTn;3:(3$%SY;ihW0oHqT#M{Mc8:O8U(&5oo-lc{\\A39c4y{".getBytes("ISO-8859-1");
    }

  protected bZ(class_332 arg0, int arg1, int arg2, float arg3) { // было: <init>
        super(arg0);
        kA = arg1;
        kB = arg2;
        cr = arg3;
    }

  public static bZ method1677(class_332 arg0, int arg1, int arg2, float arg3) { // было: a
        return new bZ(arg0, arg1, arg2, arg3);
    }

    @Generated
  public int method1678() { // было: Y
        return kA;
    }

    @Generated
  public int method1679() { // было: Z
        return kB;
    }

    @Generated
  public float aA() {
        return cr;
    }

  private static int ji(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int jj(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int jk(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}