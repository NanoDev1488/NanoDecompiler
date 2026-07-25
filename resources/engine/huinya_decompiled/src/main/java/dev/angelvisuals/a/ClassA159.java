// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.y
package dev.angelvisuals.a;

import dev.angelvisuals.a.cF;
import lombok.Generated;

public class ClassA159 implements cF {

    // ---- поля ----
  private double field875; // было: a
  private double field876; // было: b
  private double field877; // было: c
  private double field878; // было: d
  private static final double field879 = 0.4; // было: e
  public static final double field880 = 1.0; // было: f
  private static final String by = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final String bz = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String bA = "// every class watermarked, every string encrypted, every number xored";
  private static final String bB = "// reverse-engineering this jar is a waste of time, friend";
  private static final String bC = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final int aE = -470233863;
  private static final int aF = -223669871;
  private static final int aG = -1583462697;
  private static final byte[] field881; // было: z

    static {
        field881 = "~B3z,.!vK[0Hfh.y4Boeff>z-?*yLhz*ypqYMYLapi'gpFTyu<:1n`1;xHHF+t(5o~c_'ymCi+fj0gZ!5 7_aPz$|07*6me|HPczls\\0UG^3z[7*EJ-ufJCX[FhlN&=@eLP2%%WLoP[\"$Gt&9bi_clTtZ@u?5Tfs9#di[@L~VpHQ5%R*bK9b(M(vdv_vivdw2qUC<~e}~A9x*BHr2W&{ B5L'tL#Ug6JkLf*NYYeOo\"G^*}u<vlQ#1WKEB=TIb7,".getBytes("ISO-8859-1");
    }

  public ClassA159() { // было: <init>
        super();
        field876 = 0.0;
        field877 = 0.0;
        field878 = 8.0;
    }

  public void method1544() { // было: f
        field877 = Math.max(Math.min(field877, 0.0), -field875);
        double var1 = field877 - field876;
        field876 = field876 + var1 * 0.4;
        if (Math.abs(var1) < 0.1) {
            field876 = field877;
        }
    }

  public double method1545() { // было: a
        return -field876;
    }

  public void method1546(double arg0) { // было: a
        field877 = field877 + arg0 * field878;
    }

    @Generated
  public double method1547() { // было: b
        return field875;
    }

    @Generated
  public double method1548() { // было: c
        return field877;
    }

    @Generated
  public double method1549() { // было: d
        return field878;
    }

    @Generated
  public void method1550(double arg0) { // было: b
        field875 = arg0;
    }

    @Generated
  public void method1551(double arg0) { // было: c
        field876 = arg0;
    }

    @Generated
  public void method1552(double arg0) { // было: d
        field877 = arg0;
    }

    @Generated
  public void method1553(double arg0) { // было: e
        field878 = arg0;
    }

  private static int ax(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int ay(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int az(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}