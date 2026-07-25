// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.V
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.angelvisuals.a.ClassA148_ClassA149;
import dev.angelvisuals.a.ClassA150_ClassA151;
import dev.angelvisuals.a.ClassA154_ClassA155;
import dev.angelvisuals.a.ClassA161;
import dev.angelvisuals.a.aE;
import dev.angelvisuals.a.ab;
import dev.angelvisuals.a.cF;
import dev.angelvisuals.a.cQ;
import dev.angelvisuals.a.dp;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.imageio.ImageIO;
import lombok.Generated;
import net.minecraft.class_1011;
import net.minecraft.class_10142;
import net.minecraft.class_1043;
import net.minecraft.class_1060;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_293.class_5596;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_4587.class_4665;
import net.minecraft.class_4588;
import net.minecraft.class_9801;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

public final class ClassA158 implements cF {

    // ---- поля ----
  public static HashMap field870; // было: a
  public static HashMap field871; // было: b
  static final Stack field872; // было: a
  private static final List field873; // было: h
  private static final ExecutorService field874; // было: a
  private static final String fX = "// number obfuscation: ENABLED (XOR masking)";
  private static final String fY = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String fZ = "// every class watermarked, every string encrypted, every number xored";
  private static final String ga = "// Joiner sees you";
  private static final String gb = "// stop. seriously. go play minecraft instead";
  private static final int ds = 1315356461;
  private static final int dt = -484087612;
  private static final int du = -71437477;
  private static final byte[] ap;

    static {
        ap = "+,utW9iEj^39]SByDJ!4v3)d]rs7D2bP`],u-m+M>+\"'Zm%cvB}+7j>4rID]n]V5\\GcQB3`vLW'FvP9|,CT+Za\"\"psk3luh|+WYkbw<&h6rf*6Nbk3I^ERnPg&>4wnnJO@#*x(TVby6p]*]}9^Tsx)nBk:Qd:l<z4iBSvaQl7!!d7JkXLH%}#S?\\BhW1v4$C!|qXKFOt21[DXa 8Z@ui3LDqGi.ZMoBTmy3ml^z\\J]9gIP6kh#\"^+-HGR]bqNMf%".getBytes("ISO-8859-1");
        field870 = new HashMap();
        field871 = new HashMap();
        field872 = new Stack();
        field873 = new ArrayList();
        field874 = Executors.newSingleThreadExecutor();
    }

  public static void method1489() { // было: v
        RenderSystem.disableScissor();
    }

  public static void method1490(class_287 arg0, Matrix4f arg1, float arg2, float arg3, float arg4, float arg5, Color arg6, Color arg7, Color arg8, Color arg9) { // было: a
        arg0.method_22918(arg1, arg2, arg5, 0.0f).method_39415(arg6.getRGB());
        arg0.method_22918(arg1, arg4, arg5, 0.0f).method_39415(arg7.getRGB());
        arg0.method_22918(arg1, arg4, arg3, 0.0f).method_39415(arg8.getRGB());
        arg0.method_22918(arg1, arg2, arg3, 0.0f).method_39415(arg9.getRGB());
    }

  public static boolean method1491(double arg0, double arg1, double arg2, double arg3, double arg4, double arg5) { // было: a
        return arg0 < arg2 ? -1075386167 ^ -1075386167 : arg0 - arg4 > arg2 ? -1075386167 ^ -1075386167 : arg1 < arg3 ? -1075386167 ^ -1075386167 : arg1 - arg5 > arg3 ? -1075386167 ^ -1075386167 : -1891181025 ^ -1891181026;
    }

  public static void method1492(class_4587 arg0, float arg1, float arg2, float arg3, float arg4, int arg5, Color arg6) { // было: a
        // (пустое тело)
    }

  public static void method1493(class_332 arg0) { // было: a
        class_4587 var1 = arg0.method_51448();
        Matrix4f var2 = var1.method_23760().method_23761();
        if (!field873.isEmpty()) {
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.setShader(class_10142.field_53876);
            class_287 var3 = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1576);
            field873.forEach(lp0 -> method1543(var2, var3, ((ClassA154_ClassA155) lp0)));
            class_286.method_43433(var3.method_60800());
            RenderSystem.disableBlend();
            field873.clear();
        }
    }

  public static void method1494(float arg0, float arg1, float arg2, float arg3, int arg4) { // было: a
        field873.add(new ClassA154_ClassA155(arg0, arg1, arg2, arg3, cQ.method1718(arg4, RenderSystem.getShaderColor()[-143799972 ^ -143799969])));
    }

  public static void method1495(Matrix4f arg0, class_287 arg1, float arg2, float arg3, float arg4, float arg5) { // было: a
        arg1.method_22918(arg0, arg2, arg3, 0.0f);
        arg1.method_22918(arg0, arg2, arg3 + arg5, 0.0f);
        arg1.method_22918(arg0, arg2 + arg4, arg3 + arg5, 0.0f);
        arg1.method_22918(arg0, arg2 + arg4, arg3, 0.0f);
    }

  public static void method1496(Matrix4f arg0, class_287 arg1, float arg2, float arg3, float arg4, float arg5, int arg6) { // было: a
        arg1.method_22918(arg0, arg2, arg3, 0.0f).method_39415(arg6);
        arg1.method_22918(arg0, arg2, arg3 + arg5, 0.0f).method_39415(arg6);
        arg1.method_22918(arg0, arg2 + arg4, arg3 + arg5, 0.0f).method_39415(arg6);
        arg1.method_22918(arg0, arg2 + arg4, arg3, 0.0f).method_39415(arg6);
    }

  public static void method1497(Matrix4f arg0, float arg1, float arg2, float arg3, float arg4, int arg5) { // было: a
        RenderSystem.setShader(class_10142.field_53880);
        class_287 var6 = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
        var6.method_22918(arg0, arg1, arg2 + arg4, 0.0f).method_22913(0.0f, 0.0f).method_39415(arg5);
        var6.method_22918(arg0, arg1 + arg3, arg2 + arg4, 0.0f).method_22913(0.0f, 1.0f).method_39415(arg5);
        var6.method_22918(arg0, arg1 + arg3, arg2, 0.0f).method_22913(1.0f, 1.0f).method_39415(arg5);
        var6.method_22918(arg0, arg1, arg2, 0.0f).method_22913(1.0f, 0.0f).method_39415(arg5);
        class_286.method_43433(var6.method_60800());
    }

  public static void method1498(Matrix4f arg0, class_287 arg1, float arg2, float arg3, float arg4, float arg5, int arg6) { // было: b
        arg1.method_22918(arg0, arg2, arg3 + arg5, 0.0f).method_22913(0.0f, 0.0f).method_39415(arg6);
        arg1.method_22918(arg0, arg2 + arg4, arg3 + arg5, 0.0f).method_22913(0.0f, 1.0f).method_39415(arg6);
        arg1.method_22918(arg0, arg2 + arg4, arg3, 0.0f).method_22913(1.0f, 1.0f).method_39415(arg6);
        arg1.method_22918(arg0, arg2, arg3, 0.0f).method_22913(1.0f, 0.0f).method_39415(arg6);
    }

  private static ClassA148_ClassA149 method1499(int arg0, int arg1, int arg2) { // было: a
        Object var3 = null;
        int var4 = -2128503255 ^ -18980394;
        Iterator var5 = field870.keySet().iterator();
        while (var5.hasNext()) {
            ClassA148_ClassA149 var6 = ((ClassA148_ClassA149) var5.next());
            int var7 = Math.abs(var6.ae() - arg0) + Math.abs(var6.af() - arg1) + Math.abs(var6.ag() - arg2);
            if (var7 < var4) {
                var4 = var7;
                var3 = var6;
            }
            continue;
        }
        return ((c) var3);
    }

  public static void method1500(class_4587 arg0, float arg1, float arg2, float arg3, float arg4, int arg5, dp arg6) { // было: a
        arg3 = arg3 + ((float) (arg5 * (55203506 ^ 55203504)));
        arg4 = arg4 + ((float) (arg5 * (388477178 ^ 388477176)));
        arg1 = arg1 - ((float) arg5);
        arg2 = arg2 - ((float) arg5);
        ClassA148_ClassA149 var7 = method1499(((int) arg3), ((int) arg4), arg5);
        ClassA148_ClassA149 var8;
        if (var7 == null) {
            var8 = new ClassA148_ClassA149(((int) arg3), ((int) arg4), arg5);
            mc.execute(() -> method1542(var8, arg5));
        } else {
            if (((int) (Math.abs(((float) var7.ae()) - arg3) + Math.abs(((float) var7.af()) - arg4) + ((float) Math.abs(var7.ag() - arg5)))) >= (102529308 ^ 102529305)) {
                var8 = new ClassA148_ClassA149(((int) arg3), ((int) arg4), arg5);
                mc.execute(() -> method1542(var8, arg5));
            }
        }
        ClassA150_ClassA151 var8 = ((ClassA150_ClassA151) field870.getOrDefault(var7, null));
        if (var8 != null) {
            var8.bh();
            aE.method1750(arg0, var8.field868.method1596(), arg1, arg2, arg3, arg4, arg6);
        }
    }

  public static void method1501(ClassA161 arg0, BufferedImage arg1) { // было: a
        try {
            ByteArrayOutputStream var2 = new ByteArrayOutputStream();
            ImageIO.write(arg1, Decryptor.method1945(XorDecoder.method1946("\u001c»å3\u0018©:0õ9;¶í\u0004\rÓÈ8(H", 1511851030 ^ 799139435)), var2);
            byte[] var3 = var2.toByteArray();
            method1502(arg0, var3);
        } catch (Exception e1) {
            Throwable var2 = e1;
        }
    }

  public static void method1502(ClassA161 arg0, byte[] arg1) { // было: a
        try {
            ByteBuffer var2 = BufferUtils.createByteBuffer(arg1.length).put(arg1);
            var2.flip();
            class_1043 var3 = new class_1043(class_1011.method_4324(var2));
            mc.execute(() -> method1541(arg0, var3));
        } catch (Exception e1) {
            Throwable var2 = e1;
        }
    }

  public static void method1503(class_4587 arg0, double arg1, double arg2, double arg3, double arg4, float arg5, float arg6, double arg7, double arg8, double arg9, double arg10) { // было: a
        double var19 = arg1 + arg3;
        double var21 = arg2 + arg4;
        double var23 = 0.0;
        Matrix4f var25 = arg0.method_23760().method_23761();
        RenderSystem.setShader(class_10142.field_53880);
        class_287 var26 = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1585);
        var26.method_22918(var25, ((float) arg1), ((float) var21), ((float) var23)).method_22913(arg5 / ((float) arg9), (arg6 + ((float) arg8)) / ((float) arg10));
        var26.method_22918(var25, ((float) var19), ((float) var21), ((float) var23)).method_22913((arg5 + ((float) arg7)) / ((float) arg9), (arg6 + ((float) arg8)) / ((float) arg10));
        var26.method_22918(var25, ((float) var19), ((float) arg2), ((float) var23)).method_22913((arg5 + ((float) arg7)) / ((float) arg9), arg6 / ((float) arg10));
        var26.method_22918(var25, ((float) arg1), ((float) arg2), ((float) var23)).method_22913(arg5 / ((float) arg9), (arg6 + 0.0f) / ((float) arg10));
        class_286.method_43433(var26.method_60800());
    }

  public static void method1504(class_4587 arg0, double arg1, double arg2, double arg3, double arg4, float arg5, float arg6, double arg7, double arg8, double arg9, double arg10, Color arg11, Color arg12, Color arg13, Color arg14) { // было: a
        RenderSystem.setShader(class_10142.field_53880);
        class_287 var23 = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
        method1505(var23, arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14);
        class_286.method_43433(var23.method_60800());
    }

  public static void method1505(class_287 arg0, class_4587 arg1, double arg2, double arg3, double arg4, double arg5, float arg6, float arg7, double arg8, double arg9, double arg10, double arg11, Color arg12, Color arg13, Color arg14, Color arg15) { // было: a
        double var24 = arg2 + arg4;
        double var26 = arg3 + arg5;
        double var28 = 0.0;
        Matrix4f var30 = arg1.method_23760().method_23761();
        arg0.method_22918(var30, ((float) arg2), ((float) var26), ((float) var28)).method_22913(arg6 / ((float) arg10), (arg7 + ((float) arg9)) / ((float) arg11)).method_39415(arg12.getRGB());
        arg0.method_22918(var30, ((float) var24), ((float) var26), ((float) var28)).method_22913((arg6 + ((float) arg8)) / ((float) arg10), (arg7 + ((float) arg9)) / ((float) arg11)).method_39415(arg13.getRGB());
        arg0.method_22918(var30, ((float) var24), ((float) arg3), ((float) var28)).method_22913((arg6 + ((float) arg8)) / ((float) arg10), arg7 / ((float) arg11)).method_39415(arg14.getRGB());
        arg0.method_22918(var30, ((float) arg2), ((float) arg3), ((float) var28)).method_22913(arg6 / ((float) arg10), (arg7 + 0.0f) / ((float) arg11)).method_39415(arg15.getRGB());
    }

  public static void method1506() { // было: w
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

  public static void method1507(class_4587 arg0, float arg1, float arg2, float arg3, float arg4, float arg5, boolean arg6, boolean arg7, int arg8) { // было: a
        // (пустое тело)
    }

  public static void method1508(class_4587 arg0, float arg1, float arg2, float arg3, Color arg4) { // было: a
        // (пустое тело)
    }

  public static void method1509(class_4587 arg0, float arg1, float arg2, float arg3, float arg4, float arg5, boolean arg6, boolean arg7, int arg8) { // было: b
        if (arg7) {
            method1492(arg0, arg1 - arg3 * arg4, arg2, arg1 + arg3 * arg4 - (arg1 - arg3 * arg4), arg3, 1758532344 ^ 1758532338, method1512(new Color(arg8), 1680482012 ^ 1680481872));
        }
        arg0.method_22903();
        method1506();
        Matrix4f var9 = arg0.method_23760().method_23761();
        RenderSystem.setShader(class_10142.field_53876);
        class_287 var10 = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1576);
        var10.method_22918(var9, arg1, arg2, 0.0f).method_39415(arg8);
        var10.method_22918(var9, arg1 - arg3 * arg4, arg2 + arg3, 0.0f).method_39415(arg8);
        var10.method_22918(var9, arg1, arg2 + arg3 - arg5, 0.0f).method_39415(arg8);
        var10.method_22918(var9, arg1, arg2, 0.0f).method_39415(arg8);
        arg8 = method1520(new Color(arg8), 0.800000011920929f).getRGB();
        var10.method_22918(var9, arg1, arg2, 0.0f).method_39415(arg8);
        var10.method_22918(var9, arg1, arg2 + arg3 - arg5, 0.0f).method_39415(arg8);
        var10.method_22918(var9, arg1 + arg3 * arg4, arg2 + arg3, 0.0f).method_39415(arg8);
        var10.method_22918(var9, arg1, arg2, 0.0f).method_39415(arg8);
        if (arg6) {
            arg8 = method1520(new Color(arg8), 0.6000000238418579f).getRGB();
            var10.method_22918(var9, arg1 - arg3 * arg4, arg2 + arg3, 0.0f).method_39415(arg8);
            var10.method_22918(var9, arg1 + arg3 * arg4, arg2 + arg3, 0.0f).method_39415(arg8);
            var10.method_22918(var9, arg1, arg2 + arg3 - arg5, 0.0f).method_39415(arg8);
            var10.method_22918(var9, arg1 - arg3 * arg4, arg2 + arg3, 0.0f).method_39415(arg8);
        }
        class_286.method_43433(var10.method_60800());
        method1510();
        arg0.method_22909();
    }

  public static void method1510() { // было: x
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableBlend();
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

  public static float method1511(float arg0, float arg1, float arg2) { // было: a
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: fload_0
        //      1: fload_1
        //      2: fcmpl
        //      3: ifle  14 (offset +11)
        //      6: ldc  #34 // -166338364
        //      8: ldc  #35 // -166338363
        //     10: ixor
        //     11: goto  19 (offset +8)
        //     14: ldc  #10 // -1576866903
        //     16: ldc  #10 // -1576866903
        //     18: ixor
        //     19: istore_3
        //     20: fload_2
        //     21: fconst_0
        //     22: fcmpg
        //     23: ifge  31 (offset +8)
        //     26: fconst_0
        //     27: fstore_2
        //     28: goto  39 (offset +11)
        //     31: fload_2
        //     32: fconst_1
        //     33: fcmpl
        //     34: ifle  39 (offset +5)
        //     37: fconst_1
        //     38: fstore_2
        //     39: fload_0
        //     40: fload_1
        //     41: invokestatic  #276 // java.lang.Math.max:(FF)F
        //     44: fload_0
        //     45: fload_1
        //     46: invokestatic  #278 // java.lang.Math.min:(FF)F
        //     49: fsub
        //     50: fstore  4
        //     52: fload  4
        //     54: fload_2
        //     55: fmul
        //     56: fstore  5
        //     58: fload_1
        //     59: iload_3
        //     60: ifeq  68 (offset +8)
        //     63: fload  5
        //     65: goto  71 (offset +6)
        //     68: fload  5
        //     70: fneg
        //     71: fadd
        //     72: freturn
    }

  public static Color method1512(Color arg0, int arg1) { // было: a
        return new Color(arg0.getRed(), arg0.getGreen(), arg0.getBlue(), class_3532.method_15340(arg1, 638421830 ^ 638421830, -140976629 ^ -140976396));
    }

  public static Color method1513(Color arg0, Color arg1, double arg2, double arg3) { // было: a
        int __stk1;
        int var6 = ((int) ((((double) System.currentTimeMillis()) / arg2 + arg3) % 360.0));
        __stk1 = var6 < (-1046823879 ^ -1046823795) ? var6 : (980145180 ^ 980145524) - var6;
        var6 = __stk1 * (1159468277 ^ 1159468279);
        return method1523(arg0, arg1, ((float) var6) / 360.0f);
    }

  public static Color method1514(boolean arg0, int arg1) { // было: a
        float __stk1;
        __stk1 = !arg0 ? 3000.0f : 3500.0f;
        float var2 = __stk1;
        float var3 = ((float) (System.currentTimeMillis() % ((long) ((int) var2)) + ((long) arg1)));
        if (var3 > var2) {
            var3 = var3 - var2;
        }
        var3 = var3 / var2;
        if (var3 > 0.5f) {
            var3 = 0.5f - (var3 - 0.5f);
        }
        var3 = var3 + 0.5f;
        return Color.getHSBColor(var3, 0.4000000059604645f, 1.0f);
    }

  public static Color method1515(int arg0, float arg1, float arg2) { // было: a
        double var3 = Math.ceil(((double) (((float) (System.currentTimeMillis() + ((long) arg0))) / 16.0f)));
        var3 = var3 % 360.0;
        return Color.getHSBColor(((float) (var3 / 360.0)), arg1, arg2);
    }

  public static Color method1516(int arg0, int arg1) { // было: a
        float __stk1;
        int var2 = ((int) ((System.currentTimeMillis() / ((long) arg0) + ((long) arg1)) % (6252501986194782005L ^ 6252501986194781789L)));
        var2 = var2 % (-418084466 ^ -418084634);
        __stk1 = ((double) ((float) (((double) var2) / 360.0))) >= 0.5 ? ((float) (((double) var2) / 360.0)) : -((float) (((double) var2) / 360.0));
        return Color.getHSBColor(__stk1, 0.5f, 1.0f);
    }

  public static Color method1517(Color arg0) { // было: a
        float[] var1 = Color.RGBtoHSB(arg0.getRed(), arg0.getGreen(), arg0.getBlue(), ((float[]) null));
        float var2 = 0.8399999737739563f;
        float var3 = var1[692445309 ^ 692445309] - var2;
        return new Color(Color.HSBtoRGB(var3, var1[-129038433 ^ -129038434], var1[2087327688 ^ 2087327690]));
    }

  public static Color method1518(Color arg0, float arg1) { // было: a
        arg1 = Math.min(1.0f, Math.max(0.0f, arg1));
        return new Color(arg0.getRed(), arg0.getGreen(), arg0.getBlue(), ((int) (((float) arg0.getAlpha()) * arg1)));
    }

  public static int method1519(int arg0, float arg1) { // было: b
        arg1 = Math.min(1.0f, Math.max(0.0f, arg1));
        Color var2 = new Color(arg0);
        return new Color(var2.getRed(), var2.getGreen(), var2.getBlue(), ((int) (((float) var2.getAlpha()) * arg1))).getRGB();
    }

  public static Color method1520(Color arg0, float arg1) { // было: b
        return new Color(Math.max(((int) (((float) arg0.getRed()) * arg1)), 901005922 ^ 901005922), Math.max(((int) (((float) arg0.getGreen()) * arg1)), -998303744 ^ -998303744), Math.max(((int) (((float) arg0.getBlue()) * arg1)), -202306322 ^ -202306322), arg0.getAlpha());
    }

  public static Color method1521(int arg0, int arg1, float arg2, float arg3, float arg4) { // было: a
        int var5 = ((int) ((System.currentTimeMillis() / ((long) arg0) + ((long) arg1)) % (7463579690720180609L ^ 7463579690720180457L)));
        float var6 = ((float) var5) / 360.0f;
        Color var7 = new Color(Color.HSBtoRGB(var6, arg2, arg3));
        return new Color(var7.getRed(), var7.getGreen(), var7.getBlue(), Math.max(-1932926438 ^ -1932926438, Math.min(-153715915 ^ -153715766, ((int) (arg4 * 255.0f)))));
    }

  public static Color method1522(int arg0, int arg1, Color arg2, Color arg3, boolean arg4) { // было: a
        int __stk1;
        int var5 = ((int) ((System.currentTimeMillis() / ((long) arg0) + ((long) arg1)) % (-6725144070954638397L ^ -6725144070954638677L)));
        __stk1 = var5 < (461392981 ^ 461393121) ? var5 : (-1628897497 ^ -1628897713) - var5;
        var5 = __stk1 * (1920908006 ^ 1920908004);
        return !arg4 ? method1523(arg2, arg3, ((float) var5) / 360.0f) : method1524(arg2, arg3, ((float) var5) / 360.0f);
    }

  public static Color method1523(Color arg0, Color arg1, float arg2) { // было: a
        arg2 = Math.min(1.0f, Math.max(0.0f, arg2));
        return new Color(method1527(arg0.getRed(), arg1.getRed(), ((double) arg2)), method1527(arg0.getGreen(), arg1.getGreen(), ((double) arg2)), method1527(arg0.getBlue(), arg1.getBlue(), ((double) arg2)), method1527(arg0.getAlpha(), arg1.getAlpha(), ((double) arg2)));
    }

  public static Color method1524(Color arg0, Color arg1, float arg2) { // было: b
        arg2 = Math.min(1.0f, Math.max(0.0f, arg2));
        float[] var3 = Color.RGBtoHSB(arg0.getRed(), arg0.getGreen(), arg0.getBlue(), ((float[]) null));
        float[] var4 = Color.RGBtoHSB(arg1.getRed(), arg1.getGreen(), arg1.getBlue(), ((float[]) null));
        Color var5 = Color.getHSBColor(method1526(var3[479391457 ^ 479391457], var4[1881367844 ^ 1881367844], ((double) arg2)), method1526(var3[1721030211 ^ 1721030210], var4[1801819044 ^ 1801819045], ((double) arg2)), method1526(var3[-469153573 ^ -469153575], var4[-714211855 ^ -714211853], ((double) arg2)));
        return new Color(var5.getRed(), var5.getGreen(), var5.getBlue(), method1527(arg0.getAlpha(), arg1.getAlpha(), ((double) arg2)));
    }

  public static double method1525(double arg0, double arg1, double arg2) { // было: a
        return arg0 + (arg1 - arg0) * arg2;
    }

  public static float method1526(float arg0, float arg1, double arg2) { // было: a
        return ((float) method1525(((double) arg0), ((double) arg1), ((double) ((float) arg2))));
    }

  public static int method1527(int arg0, int arg1, double arg2) { // было: a
        return ((int) method1525(((double) arg0), ((double) arg1), ((double) ((float) arg2))));
    }

  public static class_287 method1528(class_4587 arg0, float arg1, float arg2, float arg3, float arg4) { // было: a
        method1506();
        Matrix4f var5 = arg0.method_23760().method_23761();
        class_287 var6 = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1592);
        method1529(var6, var5, arg1, arg2, arg1 + arg3, arg2 + arg4);
        return var6;
    }

  public static void method1529(class_287 arg0, Matrix4f arg1, float arg2, float arg3, float arg4, float arg5) { // было: a
        arg0.method_22918(arg1, arg2, arg3, 0.0f);
        arg0.method_22918(arg1, arg2, arg5, 0.0f);
        arg0.method_22918(arg1, arg4, arg5, 0.0f);
        arg0.method_22918(arg1, arg4, arg3, 0.0f);
    }

  public static boolean method1530(Color arg0) { // было: a
        return method1531(((float) arg0.getRed()) / 255.0f, ((float) arg0.getGreen()) / 255.0f, ((float) arg0.getBlue()) / 255.0f);
    }

  public static boolean method1531(float arg0, float arg1, float arg2) { // было: a
        return method1532(arg0, arg1, arg2, 0.0f, 0.0f, 0.0f) >= method1532(arg0, arg1, arg2, 1.0f, 1.0f, 1.0f) ? -1019167158 ^ -1019167158 : 1924687938 ^ 1924687939;
    }

  public static float method1532(float arg0, float arg1, float arg2, float arg3, float arg4, float arg5) { // было: a
        float var6 = arg3 - arg0;
        float var7 = arg4 - arg1;
        float var8 = arg5 - arg2;
        return ((float) Math.sqrt(((double) (var6 * var6 + var7 * var7 + var8 * var8))));
    }

    @NotNull
  public static Color method1533(@NotNull Color arg0, @NotNull Color arg1, float arg2, boolean arg3) { // было: a
        if (arg3) {
            int var4 = arg1.getRed() - arg0.getRed();
            int var5 = arg1.getGreen() - arg0.getGreen();
            int var6 = arg1.getBlue() - arg0.getBlue();
            int var7 = arg1.getAlpha() - arg0.getAlpha();
            return new Color(method1534(arg0.getRed() + ((int) (((float) var4) * arg2))), method1534(arg0.getGreen() + ((int) (((float) var5) * arg2))), method1534(arg0.getBlue() + ((int) (((float) var6) * arg2))), method1534(arg0.getAlpha() + ((int) (((float) var7) * arg2))));
        } else {
            return ((double) arg2) < 0.95 ? arg0 : arg1;
        }
    }

  private static int method1534(int arg0) { // было: b
        return arg0 <= (1368963245 ^ 1368963154) ? Math.max(arg0, 1025820225 ^ 1025820225) : -1236407141 ^ -1236407196;
    }

  public static void method1535(class_287 arg0) { // было: a
        class_9801 var1 = arg0.method_60794();
        if (var1 != null) {
            class_286.method_43433(var1);
        }
    }

  public static void method1536(class_332 arg0, class_2960 arg1, float arg2, float arg3, float arg4, float arg5, int arg6, int arg7, int arg8, int arg9) { // было: a
        method1537(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, 1584861183 ^ -1584861184);
    }

  public static void method1537(class_332 arg0, class_2960 arg1, float arg2, float arg3, float arg4, float arg5, int arg6, int arg7, int arg8, int arg9, int arg10) { // было: a
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokevirtual  #307 // net.minecraft.class_332.method_51448:()Lnet/minecraft/class_4587;
        //      4: astore  11
        //      6: aload_1
        //      7: ifnull  269 (offset +262)
        //     10: fload  5
        //     12: fconst_0
        //     13: fcmpl
        //     14: ifle  68 (offset +54)
        //     17: aload  11
        //     19: aload_1
        //     20: fload_2
        //     21: fload_3
        //     22: fload  4
        //     24: fload  4
        //     26: fload  5
        //     28: invokestatic  #251 // dev.angelvisuals.a.aY.a:(F)Ldev/angelvisuals/a/aY;
        //     31: new  #127 // dev.angelvisuals.a.bp
        //     34: dup
        //     35: iload  10
        //     37: ldc  #26 // -504394506
        //     39: ldc  #59 // 504394505
        //     41: ixor
        //     42: if_icmpne  53 (offset +11)
        //     45: ldc  #78 // 1588675691
        //     47: ldc  #8 // -1588675692
        //     49: ixor
        //     50: goto  55 (offset +5)
        //     53: iload  10
        //     55: invokespecial  #254 // dev.angelvisuals.a.bp.<init>:(I)V
        //     58: fconst_0
        //     59: fconst_0
        //     60: fconst_1
        //     61: fconst_1
        //     62: invokestatic  #249 // dev.angelvisuals.a.aE.a:(Lnet/minecraft/class_4587;Lnet/minecraft/class_2960;FFFFLdev/angelvisuals/a/aY;Ldev/angelvisuals/a/bp;FFFF)V
        //     65: goto  269 (offset +204)
        //     68: aload  11
        //     70: invokevirtual  #309 // net.minecraft.class_4587.method_22903:()V
        //     73: invokestatic  #212 // com.mojang.blaze3d.systems.RenderSystem.enableBlend:()V
        //     76: invokestatic  #209 // com.mojang.blaze3d.systems.RenderSystem.defaultBlendFunc:()V
        //     79: ldc  #74 // 1383591216
        //     81: ldc  #74 // 1383591216
        //     83: ixor
        //     84: aload_1
        //     85: invokestatic  #216 // com.mojang.blaze3d.systems.RenderSystem.setShaderTexture:(ILnet/minecraft/class_2960;)V
        //     88: getstatic  #201 // net.minecraft.class_10142.field_53880:Lnet/minecraft/class_10156;
        //     91: invokestatic  #214 // com.mojang.blaze3d.systems.RenderSystem.setShader:(Lnet/minecraft/class_10156;)Lnet/minecraft/class_5944;
        //     94: pop
        //     95: invokestatic  #303 // net.minecraft.class_289.method_1348:()Lnet/minecraft/class_289;
        //     98: getstatic  #206 // net.minecraft.class_293$class_5596.field_27382:Lnet/minecraft/class_293$class_5596;
        //    101: getstatic  #202 // net.minecraft.class_290.field_1575:Lnet/minecraft/class_293;
        //    104: invokevirtual  #304 // net.minecraft.class_289.method_60827:(Lnet/minecraft/class_293$class_5596;Lnet/minecraft/class_293;)Lnet/minecraft/class_287;
        //    107: astore  12
        //    109: aload  11
        //    111: invokevirtual  #311 // net.minecraft.class_4587.method_23760:()Lnet/minecraft/class_4587$class_4665;
        //    114: invokevirtual  #312 // net.minecraft.class_4587$class_4665.method_23761:()Lorg/joml/Matrix4f;
        //    117: astore  13
        //    119: iload  10
        //    121: ldc  #45 // -43702741
        //    123: ldc  #47 // 43702740
        //    125: ixor
        //    126: if_icmpne  137 (offset +11)
        //    129: ldc  #25 // -598881172
        //    131: ldc  #60 // 598881171
        //    133: ixor
        //    134: goto  139 (offset +5)
        //    137: iload  10
        //    139: istore  14
        //    141: aload  12
        //    143: aload  13
        //    145: fload_2
        //    146: fload_3
        //    147: fload  4
        //    149: fadd
        //    150: fconst_0
        //    151: invokevirtual  #300 // net.minecraft.class_287.method_22918:(Lorg/joml/Matrix4f;FFF)Lnet/minecraft/class_4588;
        //    154: fconst_0
        //    155: fconst_1
        //    156: invokeinterface  #321 // net.minecraft.class_4588.method_22913:(FF)Lnet/minecraft/class_4588;, count 3
        //    161: iload  14
        //    163: invokeinterface  #322 // net.minecraft.class_4588.method_39415:(I)Lnet/minecraft/class_4588;, count 2
        //    168: pop
        //    169: aload  12
        //    171: aload  13
        //    173: fload_2
        //    174: fload  4
        //    176: fadd
        //    177: fload_3
        //    178: fload  4
        //    180: fadd
        //    181: fconst_0
        //    182: invokevirtual  #300 // net.minecraft.class_287.method_22918:(Lorg/joml/Matrix4f;FFF)Lnet/minecraft/class_4588;
        //    185: fconst_1
        //    186: fconst_1
        //    187: invokeinterface  #321 // net.minecraft.class_4588.method_22913:(FF)Lnet/minecraft/class_4588;, count 3
        //    192: iload  14
        //    194: invokeinterface  #322 // net.minecraft.class_4588.method_39415:(I)Lnet/minecraft/class_4588;, count 2
        //    199: pop
        //    200: aload  12
        //    202: aload  13
        //    204: fload_2
        //    205: fload  4
        //    207: fadd
        //    208: fload_3
        //    209: fconst_0
        //    210: invokevirtual  #300 // net.minecraft.class_287.method_22918:(Lorg/joml/Matrix4f;FFF)Lnet/minecraft/class_4588;
        //    213: fconst_1
        //    214: fconst_0
        //    215: invokeinterface  #321 // net.minecraft.class_4588.method_22913:(FF)Lnet/minecraft/class_4588;, count 3
        //    220: iload  14
        //    222: invokeinterface  #322 // net.minecraft.class_4588.method_39415:(I)Lnet/minecraft/class_4588;, count 2
        //    227: pop
        //    228: aload  12
        //    230: aload  13
        //    232: fload_2
        //    233: fload_3
        //    234: fconst_0
        //    235: invokevirtual  #300 // net.minecraft.class_287.method_22918:(Lorg/joml/Matrix4f;FFF)Lnet/minecraft/class_4588;
        //    238: fconst_0
        //    239: fconst_0
        //    240: invokeinterface  #321 // net.minecraft.class_4588.method_22913:(FF)Lnet/minecraft/class_4588;, count 3
        //    245: iload  14
        //    247: invokeinterface  #322 // net.minecraft.class_4588.method_39415:(I)Lnet/minecraft/class_4588;, count 2
        //    252: pop
        //    253: aload  12
        //    255: invokevirtual  #302 // net.minecraft.class_287.method_60800:()Lnet/minecraft/class_9801;
        //    258: invokestatic  #299 // net.minecraft.class_286.method_43433:(Lnet/minecraft/class_9801;)V
        //    261: invokestatic  #210 // com.mojang.blaze3d.systems.RenderSystem.disableBlend:()V
        //    264: aload  11
        //    266: invokevirtual  #310 // net.minecraft.class_4587.method_22909:()V
        //    269: return
    }

  private static void method1538(class_4587 arg0, class_2960 arg1, int arg2, int arg3, float arg4, float arg5, float arg6, float arg7, int arg8, int arg9, int arg10, int arg11, int arg12) { // было: a
        method1539(arg0, arg1, ((float) arg2), ((float) arg2) + arg4, ((float) arg3), ((float) arg3) + arg5, 0.0f, arg8, arg9, arg6, arg7, arg10, arg11, arg12);
    }

  private static void method1539(class_4587 arg0, class_2960 arg1, float arg2, float arg3, float arg4, float arg5, float arg6, int arg7, int arg8, float arg9, float arg10, int arg11, int arg12, int arg13) { // было: a
        method1540(arg0, arg1, arg2, arg3, arg4, arg5, (arg9 + 0.0f) / ((float) arg11), (arg9 + ((float) arg7)) / ((float) arg11), (arg10 + 0.0f) / ((float) arg12), (arg10 + ((float) arg8)) / ((float) arg12), arg13);
    }

  private static void method1540(class_4587 arg0, class_2960 arg1, float arg2, float arg3, float arg4, float arg5, float arg6, float arg7, float arg8, float arg9, int arg10) { // было: a
        RenderSystem.setShaderTexture(-399696660 ^ -399696660, arg1);
        RenderSystem.setShader(class_10142.field_53880);
        class_287 var11 = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
        Matrix4f var12 = arg0.method_23760().method_23761();
        var11.method_22918(var12, arg2, arg4, 0.0f).method_22913(arg6, arg8).method_39415(arg10);
        var11.method_22918(var12, arg2, arg5, 0.0f).method_22913(arg6, arg9).method_39415(arg10);
        var11.method_22918(var12, arg3, arg5, 0.0f).method_22913(arg7, arg9).method_39415(arg10);
        var11.method_22918(var12, arg3, arg4, 0.0f).method_22913(arg7, arg8).method_39415(arg10);
        class_286.method_43433(var11.method_60800());
    }

    @Generated
  private ClassA158() { // было: <init>
        super();
        throw new UnsupportedOperationException(Decryptor.method1945(XorDecoder.method1946("2T\u0014í(>\u0003Ñ\u0001^\u001a4c\u000bîAZ+È@!\u0017ÝAo<ð\u001cM)ÿ\u000bmhèD$\t\u0017l0Ì\u0006&\u0010Ñ(S\u001fô!a\u001eë\u0015G\u001aC}8+~8Ë)]eè@q<:e\u0019Ca'þBTl", -2011054968 ^ 829467129)));
    }

  private static void method1541(ClassA161 arg0, class_1043 arg1) { // было: a
        mc.method_1531().method_4616(arg0.method1596(), arg1);
    }

  private static void method1542(ClassA148_ClassA149 arg0, int arg1) { // было: a
        BufferedImage var2 = new BufferedImage(arg0.ae(), arg0.af(), -686629730 ^ -686629732);
        Graphics var3 = var2.getGraphics();
        var3.setColor(new Color(1442010770 ^ -1442010771));
        var3.fillRect(arg1, arg1, arg0.ae() - arg1 * (-688219993 ^ -688219995), arg0.af() - arg1 * (124213680 ^ 124213682));
        var3.dispose();
        ab var4 = new ab(((float) arg1));
        BufferedImage var5 = var4.method1465(var2, ((BufferedImage) null));
        field870.put(arg0, new ClassA150_ClassA151(var5));
    }

  private static void method1543(Matrix4f arg0, class_287 arg1, ClassA154_ClassA155 arg2) { // было: a
        method1496(arg0, arg1, arg2.ah, arg2.ai, arg2.aj, arg2.ak, arg2.cj);
    }

  private static int cT(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int cU(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int cV(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}