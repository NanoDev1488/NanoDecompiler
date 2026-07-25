// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.bp
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.a.cQ;
import dev.angelvisuals.a.cs;
import java.awt.Color;
import java.nio.ByteBuffer;
import java.util.Objects;
import lombok.Generated;
import net.minecraft.class_3532;

public class bp {

    // ---- поля ----
  public static final bp field909; // было: c
  public static final bp field910; // было: d
  public static final bp field911; // было: e
  public static final bp field912; // было: f
  public static final bp field913; // было: g
  public static final bp field914; // было: h
  public static final bp field915; // было: i
  public static final bp field916; // было: j
  private transient float[] field917; // было: c
  private final int hz;
  private final int hA;
  private final int hB;
  private final int hC;
  private static final ByteBuffer field918; // было: a
  private static final String mP = "// every class watermarked, every string encrypted, every number xored";
  private static final String mQ = "// number obfuscation: ENABLED (XOR masking)";
  private static final String mR = "// this jar protected by JoinerObfuscator";
  private static final String mS = "Protected by t.me/JoinerClient";
  private static final String mT = "// flow obfuscation: ENABLED";
  private static final int hD = 1068399111;
  private static final int hE = 2036652527;
  private static final int hF = 963522198;
  private static final byte[] bD;

    static {
        bD = "D+R=IMv;crq:}aAJsK!}F(26S2Fc;.r@&wKguli<$tw#B4'd|[?gw\"]#u>j\\4;3!/|.:PZc3UES}/;Wn<q Mhp_54,Hr&`Bn\"3&@t CV^_5x\\{}NKtY=*a2_qP:RNl[=f/CHyj@,BZ\"5;lE(tnV]{48T01*f!4{(Dy1:B8&8@$PgiuY|_TmCb%{;+I=xoq_/# E<T=L)PZy3D(a|{P*#EKz@RwRE0s>EcRPI\\%X)@y]/[hvPhbY6Q#rsQd)7Q83J".getBytes("ISO-8859-1");
        field909 = new bp(1874772201 ^ 1874771990, -1394832161 ^ -1394832352, 547709429 ^ 547709194);
        field910 = new bp(1279663064 ^ 1279663064, 1230675792 ^ 1230675792, -607043869 ^ -607043869);
        field911 = new bp(49947148 ^ 49947148, 1594763567 ^ 1594763728, 709182183 ^ 709182183);
        field912 = new bp(1580832135 ^ 1580832120, -2142607667 ^ -2142607667, 1850546315 ^ 1850546315);
        field913 = new bp(1165722381 ^ 1165722381, -1300303068 ^ -1300303068, -2105915870 ^ -2105915683);
        field914 = new bp(902613671 ^ 902613592, -567269472 ^ -567269537, 1212267165 ^ 1212267165);
        field915 = new bp(54484189 ^ 54484101, -229092576 ^ -229092489, 1974978508 ^ 1974978449);
        field916 = new bp(1221282780 ^ 1221282780, -1507438584 ^ -1507438584, 1273454100 ^ 1273454100, 952078922 ^ 952078922);
        field918 = ByteBuffer.allocateDirect(1614908210 ^ 1614908214);
    }

  public bp(int arg0) { // было: <init>
        this(cQ.method1699(arg0), cQ.method1700(arg0), cQ.method1701(arg0), cQ.method1702(arg0));
    }

  public bp(Color arg0) { // было: <init>
        this(arg0.getRed(), arg0.getGreen(), arg0.getBlue(), arg0.getAlpha());
    }

  public bp(int arg0, int arg1, int arg2) { // было: <init>
        this(arg0, arg1, arg2, -904534839 ^ -904534986);
    }

  public bp(int arg0, int arg1, int arg2, int arg3) { // было: <init>
        super();
        arg0 = class_3532.method_15340(arg0, 1266453331 ^ 1266453331, -738436821 ^ -738436652);
        arg1 = class_3532.method_15340(arg1, 11767201 ^ 11767201, -1636517985 ^ -1636518048);
        arg2 = class_3532.method_15340(arg2, -660222447 ^ -660222447, -1209727619 ^ -1209727614);
        arg3 = class_3532.method_15340(arg3, -952174583 ^ -952174583, 1886377048 ^ 1886377127);
        hz = arg0;
        hA = arg1;
        hB = arg2;
        hC = arg3;
    }

  public bp(int arg0, int arg1, int arg2, float arg3) { // было: <init>
        super();
        arg0 = class_3532.method_15340(arg0, -1816924730 ^ -1816924730, 1052151530 ^ 1052151317);
        arg1 = class_3532.method_15340(arg1, -743103801 ^ -743103801, -72116088 ^ -72116105);
        arg2 = class_3532.method_15340(arg2, -840334525 ^ -840334525, 66887494 ^ 66887609);
        arg3 = class_3532.method_15363(arg3, 0.0f, 255.0f);
        hz = arg0;
        hA = arg1;
        hB = arg2;
        hC = ((int) arg3);
    }

  public int method1680() { // было: E
        int var1 = Math.round(((float) method1681(((float) hC))));
        int var2 = Math.round(((float) method1681(((float) hz))));
        int var3 = Math.round(((float) method1681(((float) hA))));
        int var4 = Math.round(((float) method1681(((float) hB))));
        return (var1 & (1569611370 ^ 1569611413)) << (1467799157 ^ 1467799149) | (var2 & (-633179273 ^ -633179256)) << (209950466 ^ 209950482) | (var3 & (-1045092322 ^ -1045092127)) << (126711293 ^ 126711285) | var4 & (-1450837662 ^ -1450837603);
    }

  private int method1681(float arg0) { // было: a
        return ((int) Math.max(0.0f, Math.min(255.0f, arg0)));
    }

  public static bp method1682(String arg0) { // было: a
        String __stk1;
        int __stk2;
        __stk1 = !arg0.startsWith(Decryptor.method1945(XorDecoder.method1946("\u000fe\u0010\u0014\\+$\u001c'+yN&c(@?\u0001y3$\u0002oJ", 555318254 ^ 1447766144))) ? arg0 : arg0.substring(-103908415 ^ -103908416);
        String var1 = __stk1;
        if (var1.length() == (180073778 ^ 180073780)) {
            int var2 = Integer.parseInt(var1.substring(-2019383006 ^ -2019383006, 2004366890 ^ 2004366888), 1264447797 ^ 1264447781);
            int var3 = Integer.parseInt(var1.substring(49750817 ^ 49750819, -648268477 ^ -648268473), -1648018914 ^ -1648018930);
            int var4 = Integer.parseInt(var1.substring(-655812425 ^ -655812429, 1164839084 ^ 1164839082), -1128962975 ^ -1128962959);
            __stk2 = var1.length() != (-1193562357 ^ -1193562365) ? -1889471727 ^ -1889471506 : Integer.parseInt(var1.substring(1708427959 ^ 1708427953, 1899939129 ^ 1899939121), -209424437 ^ -209424421);
            int var5 = __stk2;
            return new bp(var2, var3, var4, ((Integer) var5));
        } else {
            if (var1.length() == (361691563 ^ 361691555)) {
                int var2 = Integer.parseInt(var1.substring(-2019383006 ^ -2019383006, 2004366890 ^ 2004366888), 1264447797 ^ 1264447781);
                int var3 = Integer.parseInt(var1.substring(49750817 ^ 49750819, -648268477 ^ -648268473), -1648018914 ^ -1648018930);
                int var4 = Integer.parseInt(var1.substring(-655812425 ^ -655812429, 1164839084 ^ 1164839082), -1128962975 ^ -1128962959);
                __stk2 = var1.length() != (-1193562357 ^ -1193562365) ? -1889471727 ^ -1889471506 : Integer.parseInt(var1.substring(1708427959 ^ 1708427953, 1899939129 ^ 1899939121), -209424437 ^ -209424421);
                int var5 = __stk2;
                return new bp(var2, var3, var4, ((Integer) var5));
            } else {
                throw new IllegalArgumentException(Decryptor.method1945(XorDecoder.method1946("Ó+ÿ°&ØÒ\u0006÷§Ü\u0001ç¸×1¿3Ù±\u001dø§'ÝÓ\u0010ü´7â5í¦Ñ>¤µQêÑ,¾PÂ¤7ê¬©*ä£,·Ü\u0002Ì\u0001ö·)¤5²é", 468374336 ^ -815417435)));
            }
        }
    }

  public static bp method1683(bp arg0, bp arg1, float arg2) { // было: a
        float var3 = Math.max(0.0f, Math.min(1.0f, arg2));
        int var4 = ((int) (((float) arg0.method1695()) + ((float) (arg1.method1695() - arg0.method1695())) * var3));
        int var5 = ((int) (((float) arg0.method1696()) + ((float) (arg1.method1696() - arg0.method1696())) * var3));
        int var6 = ((int) (((float) arg0.method1697()) + ((float) (arg1.method1697() - arg0.method1697())) * var3));
        int var7 = ((int) (((float) arg0.method1698()) + ((float) (arg1.method1698() - arg0.method1698())) * var3));
        return new bp(var4, var5, var6, var7);
    }

  public static bp method1684(int arg0) { // было: a
        int var1 = arg0 >> (-2038070412 ^ -2038070420) & (2081928573 ^ 2081928578);
        int var2 = arg0 >> (-1633577479 ^ -1633577495) & (-1311082909 ^ -1311082852);
        int var3 = arg0 >> (882970213 ^ 882970221) & (905607683 ^ 905607932);
        int var4 = arg0 & (-1407092385 ^ -1407092320);
        return new bp(var2, var3, var4, var1);
    }

  public bp method1685(float arg0) { // было: b
        return new bp(hz, hA, hB, ((int) arg0));
    }

  public bp method1686(int arg0) { // было: b
        return new bp(hz, hA, hB, arg0);
    }

  public bp method1687(float arg0) { // было: c
        return method1686(((int) (((float) hC) * arg0)));
    }

  public bp method1688(bp arg0, float arg1) { // было: a
        arg1 = Math.min(1.0f, Math.max(0.0f, arg1));
        return new bp(((int) cs.method1419(((double) method1695()), ((double) arg0.method1695()), ((double) arg1))), ((int) cs.method1419(((double) method1696()), ((double) arg0.method1696()), ((double) arg1))), ((int) cs.method1419(((double) method1697()), ((double) arg0.method1697()), ((double) arg1))), ((int) cs.method1419(((double) method1698()), ((double) arg0.method1698()), ((double) arg1))));
    }

  public bp method1689(float arg0) { // было: d
        arg0 = class_3532.method_15363(arg0, 0.0f, 1.0f);
        return new bp(((int) (((float) hz) * (1.0f - arg0))), ((int) (((float) hA) * (1.0f - arg0))), ((int) (((float) hB) * (1.0f - arg0))), hC);
    }

  public static bp method1690(float arg0, float arg1, float arg2) { // было: a
        float var10;
        float var8;
        float var9;
        if (arg1 != 0.0f) {
            float var3 = (arg0 - ((float) Math.floor(((double) arg0)))) * 6.0f;
            float var4 = var3 - ((float) Math.floor(((double) var3)));
            float var5 = arg2 * (1.0f - arg1);
            float var6 = arg2 * (1.0f - arg1 * var4);
            float var7 = arg2 * (1.0f - arg1 * (1.0f - var4));
            var8 = 0.0f;
            var9 = 0.0f;
            var10 = 0.0f;
            switch (((int) var3)) {
                case 0:
                    var8 = arg2;
                    var9 = var7;
                    var10 = var5;
                    break;
                case 1:
                    var8 = var6;
                    var9 = arg2;
                    var10 = var5;
                    break;
                case 2:
                    var8 = var5;
                    var9 = arg2;
                    var10 = var7;
                    break;
                case 3:
                    var8 = var5;
                    var9 = var6;
                    var10 = arg2;
                    break;
                case 4:
                    var8 = var7;
                    var9 = var5;
                    var10 = arg2;
                    break;
                case 5:
                    var8 = arg2;
                    var9 = var5;
                    var10 = var6;
                default:
            }
        } else {
            int var3 = ((int) (arg2 * 255.0f + 0.5f));
            return new bp(var3, var3, var3);
        }
        return new bp(((int) (var8 * 255.0f)), ((int) (var9 * 255.0f)), ((int) (var10 * 255.0f)));
    }

  public float ak() {
        return method1691()[1916440620 ^ 1916440620];
    }

  public float al() {
        return method1691()[-1472744176 ^ -1472744174];
    }

  public float am() {
        return method1691()[1209944213 ^ 1209944212];
    }

  private float[] method1691() { // было: a
        if (field917 == null) {
            field917 = method1692();
        }
        return field917;
    }

  private float[] method1692() { // было: b
        float __stk2;
        float var1 = ((float) hz) / 255.0f;
        float var2 = ((float) hA) / 255.0f;
        float var3 = ((float) hB) / 255.0f;
        float var4 = Math.max(var1, Math.max(var2, var3));
        float var5 = Math.min(var1, Math.min(var2, var3));
        float var6 = var4 - var5;
        float var7 = 0.0f;
        if (var6 != 0.0f) {
            var7 = var4 != var1 ? var4 != var2 ? (var1 - var2) / var6 + 4.0f : (var3 - var1) / var6 + 2.0f : (var2 - var3) / var6;
            var7 = var7 / 6.0f;
            if (var7 < 0.0f) {
                var7 = var7 + 1.0f;
            }
        }
        __stk2 = var4 != 0.0f ? var6 / var4 : 0.0f;
        float var8 = __stk2;
        float[] __obj3 = new float[647071060 ^ 647071063];
        __obj3[-147569748 ^ -147569748] = var7;
        __obj3[-958070184 ^ -958070183] = var8;
        __obj3[893090846 ^ 893090844] = var4;
        return __obj3;
    }

  public bp method1693(float arg0) { // было: e
        arg0 = class_3532.method_15363(arg0, 0.0f, 1.0f);
        return new bp(((int) (((float) hz) + (255.0f - ((float) hz)) * arg0)), ((int) (((float) hA) + (255.0f - ((float) hA)) * arg0)), ((int) (((float) hB) + (255.0f - ((float) hB)) * arg0)), hC);
    }

  public boolean equals(Object arg0) {
        if (this != arg0) {
            if (arg0 == null) {
                return 1612777416 ^ 1612777416;
            } else {
                if (getClass() != arg0.getClass()) {
                    return 1612777416 ^ 1612777416;
                } else {
                    bp var2 = ((bp) arg0);
                    return Float.compare(((float) hz), ((float) var2.hz)) != 0 ? 1799548927 ^ 1799548927 : Float.compare(((float) hA), ((float) var2.hA)) != 0 ? 1799548927 ^ 1799548927 : Float.compare(((float) hB), ((float) var2.hB)) != 0 ? 1799548927 ^ 1799548927 : Float.compare(((float) hC), ((float) var2.hC)) != 0 ? 1799548927 ^ 1799548927 : -1732656379 ^ -1732656380;
                }
            }
        } else {
            return -2088024913 ^ -2088024914;
        }
    }

  public float method1694(bp arg0) { // было: a
        return Math.abs(ak() - arg0.ak()) + Math.abs(am() - arg0.am()) + Math.abs(al() - arg0.al());
    }

  public int hashCode() {
        Object[] __obj1 = new Object[1808722363 ^ 1808722367];
        __obj1[-891036142 ^ -891036142] = Integer.valueOf(hz);
        __obj1[75403464 ^ 75403465] = Integer.valueOf(hA);
        __obj1[1819832302 ^ 1819832300] = Integer.valueOf(hB);
        __obj1[-312184425 ^ -312184428] = Integer.valueOf(hC);
        return Objects.hash(__obj1);
    }

    @Generated
  public int method1695() { // было: F
        return hz;
    }

    @Generated
  public int method1696() { // было: G
        return hA;
    }

    @Generated
  public int method1697() { // было: H
        return hB;
    }

    @Generated
  public int method1698() { // было: I
        return hC;
    }

  private static int gJ(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int gK(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int gL(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}