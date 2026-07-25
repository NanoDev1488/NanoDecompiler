// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.ah
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import lombok.Generated;
import net.minecraft.class_1294;
import net.minecraft.class_1309;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public final class ah {

    // ---- поля ----
  private static final String fx = "// every class watermarked, every string encrypted, every number xored";
  private static final String fy = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final String fz = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final String fA = "// class hierarchy hashing: ENABLED";
  private static final String fB = "// flow obfuscation: ENABLED";
  private static final int df = -1990177248;
  private static final int dg = 1260765700;
  private static final int dh = 1683794091;
  private static final byte[] al;

    static {
        al = "g~0r3Apz0$e>*\",PK_V>HcH(N:/_&@RV\"X&Bj>Wo~>r1v?S%eC#?w^#y+B@HU($mW`)s,aAP^[^L)H[]4U^h~Ss\\D%5.N/yV:d{18H^(<[]u-n%fD{y|#:>^_yd'XG66\\U]\"\",'!Ikvobu]\"0aQ/ew@QqC_][L5c^qG(y9 ;T.dju-KbG!&\"+,@N$&%j6<mCM~aFtStoM<q2|m4M+6wJ2S{ UbOi\\rcqP#fx/OzCDYQI]U!74]w2c6J4h,GWqB(V".getBytes("ISO-8859-1");
    }

  public static class_243 method1453(class_1309 arg0, class_243 arg1, float arg2) { // было: a
        int __stk1;
        double __stk2;
        if (Math.hypot(arg0.method_23317() - arg0.field_6014, arg0.method_23321() - arg0.field_5969) * 20.0 > 5.0) {
            float var3 = (arg0.method_36455() + arg0.field_6004 - arg0.method_36455()) * 0.01745329238474369f;
            float var4 = -(arg0.method_36454() + arg0.field_6259 - arg0.method_36454()) * 0.01745329238474369f;
            float var5 = class_3532.method_15362(var4);
            float var6 = class_3532.method_15374(var4);
            float var7 = class_3532.method_15362(var3);
            float var8 = class_3532.method_15374(var3);
            class_243 var9 = arg0.method_18798();
            class_243 var10 = new class_243(((double) (var6 * var7)), ((double) -var8), ((double) (var5 * var7)));
            float var11 = ((float) (((double) arg0.method_36455()) * 0.017453293005625408));
            double var12 = Math.sqrt(var10.field_1352 * var10.field_1352 + var10.field_1350 * var10.field_1350);
            double var14 = var9.method_37267();
            __stk1 = arg0.method_18798().field_1351 > 0.0 ? 621152854 ^ 621152854 : 1829990482 ^ 1829990483;
            int var16 = __stk1;
            __stk2 = var16 == 0 ? arg0.method_56989() : !arg0.method_6059(class_1294.field_5906) ? arg0.method_56989() : Math.min(arg0.method_56989(), 0.01);
            double var17 = __stk2;
            double var19 = class_3532.method_33723(Math.cos(((double) var11)));
            var9 = var9.method_1031(0.0, var17 * (-1.0 + var19 * 0.75), 0.0);
            if (var9.field_1351 < 0.0) {
                if (var12 > 0.0) {
                    double var21 = var9.field_1351 * -0.1 * var19;
                    var9 = var9.method_1031(var10.field_1352 * var21 / var12, var21, var10.field_1350 * var21 / var12);
                }
            }
            if (var11 < 0.0f) {
                if (var12 > 0.0) {
                    double var21 = var14 * ((double) -class_3532.method_15374(var11)) * 0.04;
                    var9 = var9.method_1031(-var10.field_1352 * var21 / var12, var21 * 3.2, -var10.field_1350 * var21 / var12);
                }
            }
            if (var12 > 0.0) {
                var9 = var9.method_1031((var10.field_1352 / var12 * var14 - var9.field_1352) * 0.1, 0.0, (var10.field_1350 / var12 * var14 - var9.field_1350) * 0.1);
            }
            class_243 var23 = var9.method_18805(0.9900000095367432, 0.9800000190734863, 0.9900000095367432);
            return arg1.method_1019(var23.method_1021(((double) arg2))).method_1031(0.0, 0.3499999940395355, 0.0);
        } else {
            if ((arg0.method_23318() - arg0.field_6036) * 20.0 > 5.0) {
                float var3 = (arg0.method_36455() + arg0.field_6004 - arg0.method_36455()) * 0.01745329238474369f;
                float var4 = -(arg0.method_36454() + arg0.field_6259 - arg0.method_36454()) * 0.01745329238474369f;
                float var5 = class_3532.method_15362(var4);
                float var6 = class_3532.method_15374(var4);
                float var7 = class_3532.method_15362(var3);
                float var8 = class_3532.method_15374(var3);
                class_243 var9 = arg0.method_18798();
                class_243 var10 = new class_243(((double) (var6 * var7)), ((double) -var8), ((double) (var5 * var7)));
                float var11 = ((float) (((double) arg0.method_36455()) * 0.017453293005625408));
                double var12 = Math.sqrt(var10.field_1352 * var10.field_1352 + var10.field_1350 * var10.field_1350);
                double var14 = var9.method_37267();
                __stk1 = arg0.method_18798().field_1351 > 0.0 ? 621152854 ^ 621152854 : 1829990482 ^ 1829990483;
                int var16 = __stk1;
                __stk2 = var16 == 0 ? arg0.method_56989() : !arg0.method_6059(class_1294.field_5906) ? arg0.method_56989() : Math.min(arg0.method_56989(), 0.01);
                double var17 = __stk2;
                double var19 = class_3532.method_33723(Math.cos(((double) var11)));
                var9 = var9.method_1031(0.0, var17 * (-1.0 + var19 * 0.75), 0.0);
                if (var9.field_1351 < 0.0) {
                    if (var12 > 0.0) {
                        double var21 = var9.field_1351 * -0.1 * var19;
                        var9 = var9.method_1031(var10.field_1352 * var21 / var12, var21, var10.field_1350 * var21 / var12);
                    }
                }
                if (var11 < 0.0f) {
                    if (var12 > 0.0) {
                        double var21 = var14 * ((double) -class_3532.method_15374(var11)) * 0.04;
                        var9 = var9.method_1031(-var10.field_1352 * var21 / var12, var21 * 3.2, -var10.field_1350 * var21 / var12);
                    }
                }
                if (var12 > 0.0) {
                    var9 = var9.method_1031((var10.field_1352 / var12 * var14 - var9.field_1352) * 0.1, 0.0, (var10.field_1350 / var12 * var14 - var9.field_1350) * 0.1);
                }
                class_243 var23 = var9.method_18805(0.9900000095367432, 0.9800000190734863, 0.9900000095367432);
                return arg1.method_1019(var23.method_1021(((double) arg2))).method_1031(0.0, 0.3499999940395355, 0.0);
            } else {
                return arg1;
            }
        }
    }

    @Generated
  private ah() { // было: <init>
        super();
        throw new UnsupportedOperationException(Decryptor.method1945(XorDecoder.method1946("¥Ø\u0007ò¿²\u0010ÎÒ\t£ï\u0018ñÖÖ8××­\u0004ÂÖã/ïÁ:àá{÷Ó¨\u001aà#Óª\u0003Î¿ß\u000cë¶í\rôË\tÔñ+¼ò+Ô¾Ñv÷×ý/­é\nÔí4áÕØ\u007f", 1362436932 ^ -143148382)));
    }

  public static class_243 method1454(class_1309 arg0, float arg1) { // было: a
        return method1453(arg0, arg0.method_19538(), arg1);
    }

  private static int cH(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int cI(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int cJ(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}