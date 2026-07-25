// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.dI
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.a.as;
import dev.angelvisuals.a.cF;
import dev.angelvisuals.a.cs;
import lombok.Generated;
import net.minecraft.class_1041;
import net.minecraft.class_1297;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import net.minecraft.class_4604;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4d;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;

public final class dI implements cF {

    // ---- поля ----
  private static final String FC = "// class hierarchy hashing: ENABLED";
  private static final String FD = "// Joiner sees you";
  private static final String FE = "// this jar protected by JoinerObfuscator";
  private static final String FF = "// reverse-engineering this jar is a waste of time, friend";
  private static final String FG = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final int sG = -1460009795;
  private static final int sH = 2048278011;
  private static final int sI = 1860373600;
  private static final byte[] eU;

    static {
        eU = "Dq&5'M+Z>%<P&9tzI!zm}!#wGnmSh/(bllaa/d=T1FRa/+<Gt:<zkaNDHf<O[QNV-vfE1!Va!6i>lf0^S-[fEaBd^1::]fw`B\\=O^}+Yua[+o~fO@H>>n5OW)s~CE=^7.iL?tW[\\=E]+gD-pMf}?bra()zC8;r6Mz\\#tX-0i;Ku;1@Kv}Er%p%OM0Skya[2k|,h~fV%+iHEg02GehDWLTNt%D8M*+NU|2>?Ks]h`f~bpO_~5k8;<Vr`(H$pSP-lP".getBytes("ISO-8859-1");
    }

    @NotNull
  public static class_243 method1436(class_243 arg0) { // было: d
        double var1 = mc.method_1561().field_4686.method_19326().field_1352;
        double var3 = mc.method_1561().field_4686.method_19326().field_1351;
        double var5 = mc.method_1561().field_4686.method_19326().field_1350;
        float var7 = ((float) (arg0.field_1352 - var1));
        float var8 = ((float) (arg0.field_1351 - var3));
        float var9 = ((float) (arg0.field_1350 - var5));
        int[] var10 = new int[500641554 ^ 500641558];
        GL11.glGetIntegerv(-394590456 ^ -394589014, var10);
        Vector4f var11 = new Vector4f(var7, var8, var9, 1.0f).mul(as.method1828());
        Matrix4f var12 = as.method1826();
        Vector3f var13 = new Vector3f();
        var12.project(var11.x(), var11.y(), var11.z(), var10, var13);
        double var14 = mc.method_22683().method_4495();
        return new class_243(((double) var13.x) / var14, ((double) (((float) mc.method_22683().method_4507()) - var13.y)) / var14, ((double) var13.z));
    }

  public static boolean method1437(class_243 arg0) { // было: b
        class_4184 var1 = mc.method_1561().field_4686;
        class_243 var2 = var1.method_19326();
        class_243 var3 = arg0.method_1020(var2);
        float var4 = var1.method_19329();
        float var5 = var1.method_19330();
        float var6 = class_3532.method_15362(-var5 * 0.01745329238474369f - 3.1415927410125732f);
        float var7 = class_3532.method_15374(-var5 * 0.01745329238474369f - 3.1415927410125732f);
        float var8 = -class_3532.method_15362(-var4 * 0.01745329238474369f);
        float var9 = class_3532.method_15374(-var4 * 0.01745329238474369f);
        class_243 var10 = new class_243(((double) (var7 * var8)), ((double) var9), ((double) (var6 * var8)));
        if (var3.method_1026(var10) >= 0.0) {
            class_4604 var11 = mc.field_1769.field_27740;
            return var11 == null ? 665924409 ^ 665924409 : !var11.method_23093(new class_238(arg0.field_1352 - 0.1, arg0.field_1351 - 0.1, arg0.field_1350 - 0.1, arg0.field_1352 + 0.1, arg0.field_1351 + 0.1, arg0.field_1350 + 0.1)) ? 665924409 ^ 665924409 : -1006585070 ^ -1006585069;
        } else {
            return -1210672113 ^ -1210672113;
        }
    }

  public static boolean method1438(class_238 arg0) { // было: b
        class_4604 var1 = mc.field_1769.field_27740;
        return arg0 == null ? -2010040842 ^ -2010040842 : var1 == null ? -2010040842 ^ -2010040842 : !var1.method_23093(arg0) ? -2010040842 ^ -2010040842 : 171402048 ^ 171402049;
    }

  public static boolean method1439(Vector4d arg0) { // было: a
        return arg0 == null ? 202609262 ^ 202609263 : arg0.x >= 0.0 ? arg0.y >= 0.0 ? 676761252 ^ 676761252 : arg0.w >= 1.0 ? 676761252 ^ 676761252 : 202609262 ^ 202609263 : arg0.z < 1.0 ? 202609262 ^ 202609263 : arg0.y >= 0.0 ? 676761252 ^ 676761252 : arg0.w >= 1.0 ? 676761252 ^ 676761252 : 202609262 ^ 202609263;
    }

  public static double method1440(Vector4d arg0) { // было: a
        return arg0.x + (arg0.z - arg0.x) / 2.0;
    }

    @NotNull
  public static class_243[] method1441(class_1297 arg0, class_243 arg1) { // было: a
        class_238 var2 = arg0.method_5829();
        class_238 var3 = new class_238(var2.field_1323 - arg0.method_23317() + arg1.field_1352 - 0.10000000149011612, var2.field_1322 - arg0.method_23318() + arg1.field_1351 - 0.10000000149011612, var2.field_1321 - arg0.method_23321() + arg1.field_1350 - 0.10000000149011612, var2.field_1320 - arg0.method_23317() + arg1.field_1352 + 0.10000000149011612, var2.field_1325 - arg0.method_23318() + arg1.field_1351 + 0.10000000149011612, var2.field_1324 - arg0.method_23321() + arg1.field_1350 + 0.10000000149011612);
        class_243[] __obj1 = new class_243[148172752 ^ 148172760];
        __obj1[1752738319 ^ 1752738319] = new class_243(var3.field_1323, var3.field_1322, var3.field_1321);
        __obj1[643980083 ^ 643980082] = new class_243(var3.field_1323, var3.field_1325, var3.field_1321);
        __obj1[248729743 ^ 248729741] = new class_243(var3.field_1320, var3.field_1322, var3.field_1321);
        __obj1[637477483 ^ 637477480] = new class_243(var3.field_1320, var3.field_1325, var3.field_1321);
        __obj1[-1496134711 ^ -1496134707] = new class_243(var3.field_1323, var3.field_1322, var3.field_1324);
        __obj1[1087589117 ^ 1087589112] = new class_243(var3.field_1323, var3.field_1325, var3.field_1324);
        __obj1[649413003 ^ 649413005] = new class_243(var3.field_1320, var3.field_1322, var3.field_1324);
        __obj1[-869562966 ^ -869562963] = new class_243(var3.field_1320, var3.field_1325, var3.field_1324);
        return __obj1;
    }

  public static Vector4d method1442(class_1297 arg0) { // было: a
        Object var1 = null;
        class_243[] var2 = method1441(arg0, cs.method1427(arg0));
        int var3 = var2.length;
        int var4 = -502245156 ^ -502245156;
        while (var4 < var3) {
            Object var5 = var2[var4];
            var5 = method1436(new class_243(var5.field_1352, var5.field_1351, var5.field_1350));
            if (var5.field_1350 > 0.0) {
                if (var5.field_1350 < 1.0) {
                    if (var1 == null) {
                        var1 = new Vector4d(var5.field_1352, var5.field_1351, var5.field_1350, 0.0);
                    }
                    var1.x = Math.min(var5.field_1352, var1.x);
                    var1.y = Math.min(var5.field_1351, var1.y);
                    var1.z = Math.max(var5.field_1352, var1.z);
                    var1.w = Math.max(var5.field_1351, var1.w);
                }
            }
            ++var4;
            continue;
        }
        return ((Vector4d) var1);
    }

    @Generated
  private dI() { // было: <init>
        super();
        throw new UnsupportedOperationException(Decryptor.method1945(XorDecoder.method1946("[\u007fëËA\u0015ü÷huå°]HôÈ(qÔî)\nèû(DÃÖufÖÙbFÎ-\u000fö¯~GÏêo\rï÷AxàÒHJáÍ|låª*VÇªBUÇí@vÎ)ZÃ´SNæ¬*JØØ+\u007f¢", 1488656784 ^ -954914936)));
    }

  private static int qG(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int qH(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int qI(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}