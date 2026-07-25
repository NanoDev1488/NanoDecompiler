// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.aH.d
package dev.angelvisuals.a;

import dev.angelvisuals.a.aH;
import lombok.Generated;

public abstract class aH_ClassA7 implements aH {

    // ---- поля ----
  private float cV;
  private float cW;
  private static final String xN = "// === DO NOT TOUCH ===";
  private static final String xO = "// every class watermarked, every string encrypted, every number xored";
  private static final String xP = "// flow obfuscation: ENABLED";
  private static final String xQ = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String xR = "// reverse-engineering this jar is a waste of time, friend";
  private static final int oc = 917302216;
  private static final int od = 1242158031;
  private static final int oe = -1906792848;
  private static final byte[] dz;

    static {
        dz = "y'u]_`{'Q8z2OE5y5{o=U|T?D0I'AQ(>/PM(nasM67VY#TmjSqr^*qKH!7\"}{E4WlhEbfpI!G{lAq^j6#Whm&&Y>jvm;G`,/Z_]#-PA+Jv\"\\koU@,[R>9G;?=-czGm0* 3u8wQ83MDSd4WkyO+|z$#:Xm[^ve`n]%z!Z1EiPi^#|?DZH0|has&]s|,i%\\w%BXlnGf=Bvmmrn JF- THj~QJ|?w~hS%ncqYs1TG#T0|7L{6Ukr_3#dJLi3JX+%5?$".getBytes("ISO-8859-1");
    }

  public aH_ClassA7(float arg0, float arg1) { // было: <init>
        super();
        cV = arg0;
        cW = arg1;
    }

  public aH_ClassA7() { // было: <init>
        this(-1.0f, 0.0f);
    }

    @Generated
  public void method33(float arg0) { // было: Z
        cV = arg0;
    }

    @Generated
  public void aa(float arg0) {
        cW = arg0;
    }

    @Generated
  public float bc() {
        return cV;
    }

    @Generated
  public float bd() {
        return cW;
    }

  private static int mu(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int mv(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int mw(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}