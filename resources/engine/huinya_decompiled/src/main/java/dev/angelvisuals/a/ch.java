// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.cH
package dev.angelvisuals.a;

import dev.angelvisuals.a.ba;
import lombok.Generated;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_315;
import net.minecraft.class_3532;
import net.minecraft.class_7172;
import net.minecraft.class_746;

public class cH {

    // ---- поля ----
  private final float cO;
  private final float cP;
  private boolean ae;
  public static final cH field758; // было: c
  private static final String wC = "// class hierarchy hashing: ENABLED";
  private static final String wD = "// reverse-engineering this jar is a waste of time, friend";
  private static final String wE = "// every class watermarked, every string encrypted, every number xored";
  private static final String wF = "// flow obfuscation: ENABLED";
  private static final String wG = "// nice try. closed source for a reason.";
  private static final int nm = 83474105;
  private static final int nn = -108392960;
  private static final int no = -778095235;
  private static final byte[] dn;

    static {
        dn = "V70Um#'u2)ePWp/'C<,<m?I-B<lexrn=7>UI<;&VUM24AgOo-2g7o<yx.oh`*^X:I{~X5tRmSxm1M@D#Lu?#.3yzBze[x\\NSLd<zQ:zWZhy3]I{2XtdnY\"\\wR;Yv;%}X{7y +7yvk8&!C qt-d5:ylL z,H~1[b;'3+Tx0/pSA(-=!Ff4*L^F%N+~lC aQO5hiCpi9f-Kw2Vx)q$csfyoZC?4228o a9eJPtZ#-pn([o3?%x`KF\"=l%4y8@UL /g".getBytes("ISO-8859-1");
        field758 = new cH(0.0f, 0.0f);
    }

  public cH(float arg0, float arg1) { // было: <init>
        this(arg0, arg1, -104505298 ^ -104505298);
    }

  public cH(float arg0, float arg1, boolean arg2) { // было: <init>
        super();
        cO = arg0;
        cP = arg1;
        ae = arg2;
    }

  public static cH method1346(class_243 arg0, class_243 arg1) { // было: a
        return method1347(arg0.method_1020(arg1));
    }

  public static cH method1347(class_243 arg0) { // было: c
        double var1 = arg0.field_1352;
        double var3 = arg0.field_1351;
        double var5 = arg0.field_1350;
        return new cH(((float) class_3532.method_15338(Math.toDegrees(Math.atan2(var5, var1)) - 90.0)), ((float) class_3532.method_15338(-Math.toDegrees(Math.atan2(var3, Math.sqrt(var1 * var1 + var5 * var5))))));
    }

  public static cH method1348(class_243 arg0) { // было: d
        double var1 = arg0.field_1352 - class_310.method_1551().field_1724.method_23317();
        double var3 = arg0.field_1351 - class_310.method_1551().field_1724.method_23320();
        double var5 = arg0.field_1350 - class_310.method_1551().field_1724.method_23321();
        double var7 = ((double) class_3532.method_15355(((float) (var1 * var1 + var5 * var5))));
        float var9 = ((float) (class_3532.method_15349(var5, var1) * 57.29577951308232 - 90.0));
        float var10 = ((float) (-class_3532.method_15349(var3, var7) * 57.29577951308232));
        return new cH(var9, var10);
    }

  public float method1349(cH arg0) { // было: a
        return Math.min(method1350(arg0).ac(), 180.0f);
    }

  public ba method1350(cH arg0) { // было: a
        return new ba(method1352(arg0.cO, cO), method1352(arg0.cP, cP));
    }

  public float method1351(cH arg0) { // было: b
        float var2 = class_3532.method_15393(arg0.aX() - cO);
        float var3 = arg0.aY() - cP;
        return ((float) Math.hypot(((double) Math.abs(var2)), ((double) Math.abs(var3))));
    }

  private float method1352(float arg0, float arg1) { // было: d
        return class_3532.method_15393(arg0 - arg1);
    }

  public boolean method1353(cH arg0, float arg1) { // было: a
        return method1349(arg0) > arg1 ? 951729295 ^ 951729295 : 17314877 ^ 17314876;
    }

  public boolean ad() {
        return ae;
    }

  public class_243 method1354() { // было: j
        return class_243.method_1030(cP, cO);
    }

  public final class_243 method1355() { // было: k
        float var1 = cP * 0.01745329238474369f;
        float var2 = -cO * 0.01745329238474369f;
        float var3 = class_3532.method_15362(var2);
        float var4 = class_3532.method_15374(var2);
        float var5 = class_3532.method_15362(var1);
        float var6 = class_3532.method_15374(var1);
        return new class_243(((double) (var4 * var5)), ((double) -var6), ((double) (var3 * var5)));
    }

  public cH method1356(cH arg0, float arg1, float arg2) { // было: a
        ba var4 = method1350(arg0);
        float var5 = var4.ac();
        float var6 = Math.abs(var4.ad() / var5) * arg1;
        float var7 = Math.abs(var4.ae() / var5) * arg2;
        float var8 = class_3532.method_15363(var4.ad(), -var6, var6);
        float var9 = class_3532.method_15363(var4.ae(), -var7, var7);
        return new cH(cO + var8, cP + var9);
    }

  public boolean ae() {
        return Float.isInfinite(cO) ? 448592143 ^ 448592142 : Float.isNaN(cO) ? 448592143 ^ 448592142 : Float.isInfinite(cP) ? 448592143 ^ 448592142 : !Float.isNaN(cP) ? 2111246569 ^ 2111246569 : 448592143 ^ 448592142;
    }

  public static float aW() {
        double var0 = (((Double) class_310.method_1551().field_1690.method_42495().method_41753())).doubleValue() * 0.6000000238418579 + 0.20000000298023224;
        return ((float) (var0 * var0 * var0 * 8.0 * 0.15000000596046448));
    }

  public cH method1357(cH arg0) { // было: a
        if (ae) {
            return this;
        } else {
            if (equals(arg0)) {
                return this;
            } else {
                ba var2 = arg0.method1350(this);
                double var3 = ((double) aW());
                int var5 = ((int) (((double) var2.ad()) / var3));
                int var6 = ((int) (((double) var2.ae()) / var3));
                return new cH(((float) (((double) arg0.aX()) + ((double) var5) * var3)), ((float) (((double) arg0.aY()) + ((double) var6) * var3)), 411469890 ^ 411469891);
            }
        }
    }

  public cH method1358(ba arg0) { // было: a
        return new cH(cO + arg0.ad(), cP + arg0.ae());
    }

  public boolean equals(Object arg0) {
        if (arg0 instanceof cH) {
            cH var2 = ((cH) arg0);
            return var2.cO != cO ? 156435353 ^ 156435353 : var2.cP != cP ? 156435353 ^ 156435353 : 1553714807 ^ 1553714806;
        } else {
            return 856341701 ^ 856341701;
        }
    }

    @Generated
  public float aX() {
        return cO;
    }

    @Generated
  public float aY() {
        return cP;
    }

  private static int lK(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int lL(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int lM(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}