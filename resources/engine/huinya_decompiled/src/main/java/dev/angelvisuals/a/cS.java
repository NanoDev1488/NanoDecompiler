// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.cs
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.a.cF;
import java.util.concurrent.ThreadLocalRandom;
import lombok.Generated;
import net.minecraft.class_1297;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_9779;
import org.joml.Vector3d;

public final class cs implements cF {

    // ---- поля ----
  public static double field791; // было: r
  private static final int lV = 65536;
  private static final double field792 = 6.283185307179586; // было: s
  private static final double[] field793; // было: a
  private static final String um = "// stop. seriously. go play minecraft instead";
  private static final String un = "// flow obfuscation: ENABLED";
  private static final String uo = "// this jar protected by JoinerObfuscator";
  private static final String up = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final String uq = "// you are reading machine-generated garbage";
  private static final int lW = 509306542;
  private static final int lX = -1084877498;
  private static final int lY = -1964269654;
  private static final byte[] cR;

    static {
        cR = "/c\"f8ot^L+9RLIo&Vd/mE:#|0O'%^q>c?{Z\\wGS~3Nq.&!SaHQ_oLJ8s$VXxQ\"i[?Dr4v4G]INh]EVz%e$i@?m|YSfYq_h+f(ZKzm'hKS9R/4,DLr:74*r4j]TIkudv7:\"ax\\540\"jEaDbi^R*d[`^#$}1LW7<(zX4L&'`zl):ux@<G:K6J72@,g#sb!di|k@qZ5\"XnM7&d3U|zxh^_u@#(sA9!=5=Sm)*4*TlKYs\\6h2n)U}$d}`:CR@oHLVMY2".getBytes("ISO-8859-1");
        field791 = 6.283185307179586;
        field793 = new double[-1276308394 ^ -1276373930];
        int var0 = -1679711829 ^ -1679711829;
        while (var0 < (-1132672843 ^ -1132607307)) {
            field793[var0] = Math.sin(((double) var0) * 6.283185307179586 / 65536.0);
            ++var0;
            continue;
        }
    }

  public static double method1411(double arg0) { // было: a
        int var2 = ((int) (arg0 * 10430.378350470453)) & (-1726331547 ^ -1726301542);
        return field793[var2];
    }

  public static double method1412(double arg0) { // было: b
        int var2 = ((int) (arg0 * 10430.378350470453 + 16384.0)) & (232287974 ^ 232296729);
        return field793[var2];
    }

  public static float method1413(double arg0, double arg1) { // было: a
        return ((float) (arg0 + (arg1 - arg0) * Math.random()));
    }

  public static double method1414(double arg0, double arg1, double arg2, double arg3, double arg4) { // было: a
        return Math.pow(1.0 - arg0, 3.0) * arg1 + 3.0 * arg0 * Math.pow(1.0 - arg0, 2.0) * arg2 + 3.0 * Math.pow(arg0, 2.0) * (1.0 - arg0) * arg3 + Math.pow(arg0, 3.0) * arg4;
    }

  public static int method1415(String arg0, String arg1) { // было: a
        int __stk1;
        int var2 = arg0.length();
        int var3 = arg1.length();
        int[] var4 = new int[var3 + (-827110270 ^ -827110269)];
        int var5 = 1328038168 ^ 1328038168;
        while (var5 <= var3) {
            var4[var5] = var5++;
            continue;
        }
        var5 = -627920976 ^ -627920975;
        while (var5 <= var2) {
            int var6 = var4[-927415577 ^ -927415577];
            var4[1477753149 ^ 1477753149] = var5;
            int var7 = 187972054 ^ 187972055;
            while (var7 <= var3) {
                int var8 = var4[var7];
                __stk1 = arg0.charAt(var5 - (914293476 ^ 914293477)) != arg1.charAt(var7 - (1805695434 ^ 1805695435)) ? 2114794185 ^ 2114794184 : -522543683 ^ -522543683;
                int var9 = __stk1;
                var4[var7] = Math.min(Math.min(var4[var7] + (1193646948 ^ 1193646949), var4[var7 - (-1758606756 ^ -1758606755)] + (1925979953 ^ 1925979952)), var6 + var9);
                var6 = var8;
                ++var7;
                continue;
            }
            ++var5;
            continue;
        }
        return var4[var3];
    }

  public static float method1416(float arg0, float arg1) { // было: a
        float var2 = (arg0 - arg1) % 360.0f;
        if (var2 >= -180.0f) {
            if (var2 > 180.0f) {
                var2 = var2 - 360.0f;
            }
        } else {
            var2 = var2 + 360.0f;
        }
        return var2;
    }

  public static boolean method1417(double arg0, double arg1, double arg2, double arg3, double arg4, double arg5) { // было: c
        return arg0 < arg2 ? -374859753 ^ -374859753 : arg0 > arg2 + arg4 ? -374859753 ^ -374859753 : arg1 < arg3 ? -374859753 ^ -374859753 : arg1 > arg3 + arg5 ? -374859753 ^ -374859753 : -1563006530 ^ -1563006529;
    }

  public static boolean method1418(double arg0, double arg1, int arg2, int arg3, int arg4, int arg5) { // было: a
        return arg0 < ((double) arg2) ? -692853391 ^ -692853391 : arg0 > ((double) arg4) ? -692853391 ^ -692853391 : arg1 < ((double) arg3) ? -692853391 ^ -692853391 : arg1 > ((double) arg5) ? -692853391 ^ -692853391 : 1359488448 ^ 1359488449;
    }

  public static float method1419(double arg0, double arg1, double arg2) { // было: a
        return ((float) (arg0 + (arg1 - arg0) * arg2));
    }

  public static float method1420(float arg0, float arg1) { // было: b
        return Math.abs(arg0 - arg1);
    }

  public static double method1421(double arg0, double arg1) { // было: a
        if (arg0 != arg1) {
            if (arg0 > arg1) {
                double var4 = arg0;
                arg0 = arg1;
                arg1 = var4;
            }
            return ThreadLocalRandom.current().nextDouble() * (arg1 - arg0) + arg0;
        } else {
            return arg0;
        }
    }

  public static float method1422(float arg0) { // было: n
        return ((float) Math.round(arg0 * 10.0f)) / 10.0f;
    }

  public static double method1423(double arg0, double arg1) { // было: b
        double var4 = ((double) Math.round(arg0 / arg1)) * arg1;
        return ((double) Math.round(var4 * 100.0)) / 100.0;
    }

  public static class_243 method1424(int arg0, int arg1, double arg2) { // было: a
        int var4 = Math.min(arg0, arg1);
        float var5 = ((float) (Math.cos(((double) var4) * field791 / ((double) arg1)) * arg2));
        float var6 = ((float) (-Math.sin(((double) var4) * field791 / ((double) arg1)) * arg2));
        return new class_243(((double) var5), 0.0, ((double) var6));
    }

  public static Vector3d method1425(Vector3d arg0, Vector3d arg1) { // было: a
        return new Vector3d(method1429(arg0.x, arg1.x), method1429(arg0.y, arg1.y), method1429(arg0.z, arg1.z));
    }

  public static class_243 method1426(class_243 arg0, class_243 arg1) { // было: a
        return new class_243(method1429(arg0.field_1352, arg1.field_1352), method1429(arg0.field_1351, arg1.field_1351), method1429(arg0.field_1350, arg1.field_1350));
    }

  public static class_243 method1427(class_1297 arg0) { // было: a
        return arg0 != null ? new class_243(method1429(arg0.field_6014, arg0.method_23317()), method1429(arg0.field_6036, arg0.method_23318()), method1429(arg0.field_5969, arg0.method_23321())) : class_243.field_1353;
    }

  public static float method1428(float arg0, float arg1) { // было: c
        return class_3532.method_16439(mc.method_61966().method_60637(1780272674 ^ 1780272674), arg0, arg1);
    }

  public static double method1429(double arg0, double arg1) { // было: c
        return class_3532.method_16436(((double) mc.method_61966().method_60637(1999326156 ^ 1999326156)), arg0, arg1);
    }

  public static int method1430(double arg0, int arg1, int arg2) { // было: a
        return ((int) class_3532.method_16436(((double) mc.method_61966().method_60638()) / arg0, ((double) arg1), ((double) arg2)));
    }

  public static float method1431(double arg0, float arg1, float arg2) { // было: a
        return ((float) class_3532.method_16436(((double) mc.method_61966().method_60638()) / arg0, ((double) arg1), ((double) arg2)));
    }

  public static double method1432(double arg0, double arg1, double arg2) { // было: b
        return class_3532.method_16436(((double) mc.method_61966().method_60638()) / arg0, arg1, arg2);
    }

  public static double method1433(class_243 arg0, class_243 arg1) { // было: c
        double var2 = arg0.method_10216() - arg1.method_10216();
        double var4 = arg0.method_10214() - arg1.method_10214();
        double var6 = arg0.method_10215() - arg1.method_10215();
        return ((double) class_3532.method_15355(((float) (var2 * var2 + var4 * var4 + var6 * var6))));
    }

    @Generated
  private cs() { // было: <init>
        super();
        throw new UnsupportedOperationException(Decryptor.method1945(XorDecoder.method1946("T\u0018NwNrYKg\u0012@\u000cR/Qt'\u0016qR&mMG'#fjz\u0001sem!2r\"hS\u0013q jV`jJKN\u001fEnG-Dqs\u000b@\u0016%1b\u0016M2bQO\u0011?r&=f\u0008\\)C\u0010%-}d$\u00186\u001e", 1211247319 ^ 1798927296)));
    }

  private static int kw(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int kx(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int ky(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}