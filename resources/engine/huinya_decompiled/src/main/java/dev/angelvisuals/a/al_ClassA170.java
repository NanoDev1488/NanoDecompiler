// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.al.a
package dev.angelvisuals.a;

import dev.angelvisuals.a.as;
import dev.angelvisuals.a.as_ClassA166;
import dev.angelvisuals.a.bp;
import java.util.List;
import net.minecraft.class_243;
import net.minecraft.class_4587;

public class al_ClassA170 {

    // ---- поля ----
  public class_243 field971; // было: c
  public class_243 field972; // было: d
  public bp field973; // было: a
  public float am;
  public long field974; // было: k
  public long field975; // было: l
  private static final String fS = "// this jar protected by JoinerObfuscator";
  private static final String fT = "// good luck with the next 9999 classes";
  private static final String fU = "// good luck with the next 9999 classes";
  private static final String fV = "// class hierarchy hashing: ENABLED";
  private static final String fW = "// flow obfuscation: ENABLED";
  private static final int dp = -424061633;
  private static final int dq = 785881235;
  private static final int dr = -243240770;
  private static final byte[] ao;

    static {
        ao = "32}f_DoKmD{\"wC}6M-]B|a*cd-4JgnXZmXOatLIjQpd0N(.QLdx4=uZyzfsxoU1vZsBdImtLp,cL;lTZ0Xd]cKoL@Ri~v+6\">^2e}]l?6GmJn$8uZ9(QRQW_5T+lK-3aFD:'84;Iu$E-e@6XO'{QxEb`@up7ilQE{D+@H_\"a6tIO?..J}i(OrNCqsSI^IV^9@^>Fky4C|2>18\"77,U!lM`ZL?_gI4I5f6&/=AFp<Bel*uxdgPAh|\\Hw{A{~2bC16".getBytes("ISO-8859-1");
    }

  public al_ClassA170(class_243 arg0, class_243 arg1, bp arg2, float arg3, long arg4) { // было: <init>
        super();
        field971 = arg0;
        field972 = arg1;
        field973 = arg2;
        am = arg3;
        field974 = System.currentTimeMillis();
        field975 = arg4;
    }

  public void method1839() { // было: u
        field971 = field971.method_1019(field972);
        field972 = field972.method_1021(0.95);
    }

  public boolean method1840() { // было: s
        return System.currentTimeMillis() - field974 <= field975 ? -143646249 ^ -143646249 : -390503734 ^ -390503733;
    }

  public void method1841(class_4587 arg0) { // было: a
        float var2 = 1.0f - ((float) (System.currentTimeMillis() - field974)) / ((float) field975);
        bp var3 = field973.method1686(((int) (255.0f * var2)));
        as.field964.add(new as_ClassA166(new class_243(field971.field_1352 - ((double) am), field971.field_1351, field971.field_1350 - ((double) am)), new class_243(field971.field_1352 + ((double) am), field971.field_1351, field971.field_1350 - ((double) am)), new class_243(field971.field_1352 + ((double) am), field971.field_1351, field971.field_1350 + ((double) am)), new class_243(field971.field_1352 - ((double) am), field971.field_1351, field971.field_1350 + ((double) am)), var3.method1680()));
    }

  private static int cQ(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int cR(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int cS(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}