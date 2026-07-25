// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.aH.f
package dev.angelvisuals.a;

import dev.angelvisuals.a.aH;
import lombok.Generated;

public abstract class aH_ClassA3 implements aH {

    // ---- поля ----
  public static final float dB = 1.7015800476074219f;
  private float dC;
  private static final String CQ = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String CR = "// number obfuscation: ENABLED (XOR masking)";
  private static final String CS = "// good luck with the next 9999 classes";
  private static final String CT = "// stop. seriously. go play minecraft instead";
  private static final String CU = "// this jar protected by JoinerObfuscator";
  private static final int rb = 1262357506;
  private static final int rc = -1511369237;
  private static final int rd = -36577255;
  private static final byte[] ew;

    static {
        ew = "9$K76(rxkh74#F2]p?E~B69ix9^FD2\"+Vh#T$W7AwUJ:EUiyPR2++zC^ldk}Ij^uzbkw8Hd/</1cGPG]',|GY>6+<`a'*yj~&pI:.N4DXOLXZjO.ljAA36]6J^u#E(%\"sV3)PEfgquEAr:h(hFl!$^h3M{;n/vtV?_2?DDx8W-iTwMHhM%A5$!(VY@veMT8!T7YMbh%'jjltzZa:1Nz6R,K)VlI$tK>o=V(!BH!0:J?u66'}`2y_lOkwCC@:?Se)".getBytes("ISO-8859-1");
    }

  public aH_ClassA3() { // было: <init>
        this(1.7015800476074219f);
    }

  public aH_ClassA3(float arg0) { // было: <init>
        super();
        dC = arg0;
    }

    @Generated
  public void ad(float arg0) {
        dC = arg0;
    }

    @Generated
  public float bn() {
        return dC;
    }

  private static int pm(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int pn(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int po(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}