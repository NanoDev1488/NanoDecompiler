// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.cE
package dev.angelvisuals.a;

import com.google.common.collect.Lists;
import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.a.ClassA140;
import dev.angelvisuals.a.ClassA146;
import dev.angelvisuals.a.ai;
import dev.angelvisuals.a.cE_ClassA142;
import dev.angelvisuals.a.cQ;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import lombok.Generated;
import net.minecraft.class_1041;
import net.minecraft.class_1268;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1703;
import net.minecraft.class_1713;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2371;
import net.minecraft.class_238;
import net.minecraft.class_2535;
import net.minecraft.class_2596;
import net.minecraft.class_266;
import net.minecraft.class_2680;
import net.minecraft.class_269;
import net.minecraft.class_2813;
import net.minecraft.class_2848;
import net.minecraft.class_2848.class_2849;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_3675.class_306;
import net.minecraft.class_3675.class_307;
import net.minecraft.class_4048;
import net.minecraft.class_4050;
import net.minecraft.class_408;
import net.minecraft.class_437;
import net.minecraft.class_5250;
import net.minecraft.class_634;
import net.minecraft.class_636;
import net.minecraft.class_638;
import net.minecraft.class_6880;
import net.minecraft.class_7204;
import net.minecraft.class_746;
import net.minecraft.class_7648;
import net.minecraft.class_8646;
import net.minecraft.class_9013;
import net.minecraft.class_9025;
import org.lwjgl.glfw.GLFW;

public final class cE implements ClassA146 {

    // ---- поля ----
  private static final String vW = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String vX = "// good luck with the next 9999 classes";
  private static final String vY = "// good luck with the next 9999 classes";
  private static final String vZ = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String wa = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final int mX = -2029699698;
  private static final int mY = -400242557;
  private static final int mZ = -784497159;
  private static final byte[] di;

    static {
        di = "(x<;~,th?e5gt6ool`@aurDBRDL]!u?{1eG-{Vp1bm?@?x 85)1gO/:~)9(jQQ*TPb3Q[lZw5X\"\"Hrt9k+/Qu\\Ua2`06.TyDgE)OUl=#SLZF g7#5@2C'P@s[Fu0U8T-A6BVZ9/@Ia;]i6-0R)u<2(72N2U%cre@=;S6h;Tsz!.kW90<FH/-l+aJIojvtt[[29hGM\\8}q/JH9ZcD&1AR2\"8/A0U]jg6X) }*oI\"G3y:o0*KPHN&eKu#IFu9RCJ^m".getBytes("ISO-8859-1");
    }

  public static void method1223(int arg0, int arg1, int arg2, class_1713 arg3, class_1657 arg4) { // было: a
        class_1703 var5 = arg4.field_7512;
        if (arg0 == var5.field_7763) {
            class_2371 var6 = var5.field_7761;
            int var7 = var6.size();
            ArrayList var8 = Lists.newArrayListWithCapacity(var7);
            Iterator var9 = var6.iterator();
            class_1735 var10;
            while (var9.hasNext()) {
                var10 = ((class_1735) var9.next());
                var8.add(var10.method_7677().method_7972());
                continue;
            }
            var5.method_7593(arg1, arg2, arg3, arg4);
            Int2ObjectOpenHashMap var10 = new Int2ObjectOpenHashMap();
            int var11 = -1155913482 ^ -1155913482;
            while (var11 < var7) {
                class_1799 var12 = ((class_1799) var8.get(var11));
                class_1799 var13 = (((class_1735) var6.get(var11))).method_7677();
                if (!class_1799.method_7973(var12, var13)) {
                    var10.put(var11, var13.method_7972());
                }
                ++var11;
                continue;
            }
            ClassA140.method1179(new class_2813(arg0, var5.method_37421(), arg1, arg2, arg3, var5.method_34255().method_7972(), var10));
        }
    }

  public static void method1224(class_7204 arg0) { // было: a
        mc.field_1761.method_41931(mc.field_1687, arg0);
    }

  public static void az() {
        mc.field_1724.field_3944.method_52787(new class_2848(mc.field_1724, class_2849.field_12982));
        mc.field_1724.method_23669();
    }

  public static void method1225(class_2596 arg0) { // было: d
        mc.method_1562().method_48296().method_10752(arg0, ((class_7648) null));
    }

  public static List method1226(class_2338 arg0, float arg1) { // было: a
        return method1228(arg0, arg1, arg1, 873281880 ^ 873281881);
    }

  public static List method1227(class_2338 arg0, float arg1, float arg2) { // было: a
        return method1228(arg0, arg1, arg2, 986342752 ^ 986342753);
    }

  public static List method1228(class_2338 arg0, float arg1, float arg2, boolean arg3) { // было: a
        int __stk1;
        ArrayList var4 = new ArrayList();
        int var5 = arg0.method_10263();
        int var6 = arg0.method_10264();
        int var7 = arg0.method_10260();
        __stk1 = !arg3 ? var6 : var6 - ((int) arg2);
        int var8 = __stk1;
        int var9 = var5 - ((int) arg1);
        while (((float) var9) <= ((float) var5) + arg1) {
            int var10 = var7 - ((int) arg1);
            while (((float) var10) <= ((float) var7) + arg1) {
                Object var11 = var8;
                while (((float) var11) <= ((float) var6) + arg2) {
                    var4.add(new class_2338(var9, ((Integer) var11), var10));
                    ++var11;
                    continue;
                }
                ++var10;
                continue;
            }
            ++var9;
            continue;
        }
        return var4;
    }

  public static List method1229(class_2338 arg0, class_2338 arg1) { // было: a
        ArrayList var2 = new ArrayList();
        int var3 = arg0.method_10263();
        while (var3 <= arg1.method_10263()) {
            int var4 = arg0.method_10260();
            while (var4 <= arg1.method_10260()) {
                int var5 = arg0.method_10264();
                while (var5 <= arg1.method_10264()) {
                    var2.add(new class_2338(var3, var5, var4));
                    ++var5;
                    continue;
                }
                ++var4;
                continue;
            }
            ++var3;
            continue;
        }
        return var2;
    }

  public static class_307 method1230(int arg0) { // было: a
        return arg0 >= (-4011599 ^ -4011591) ? class_307.field_1668 : class_307.field_1672;
    }

  public static Stream method1231() { // было: a
        return StreamSupport.stream(mc.field_1687.method_18112().spliterator(), -1999331678 ^ -1999331678);
    }

  public static boolean method1232(class_4050 arg0) { // было: a
        return mc.field_1724.method_37908().method_8587(mc.field_1724, mc.field_1724.method_18377(arg0).method_30757(mc.field_1724.method_19538()).method_1011(1e-07));
    }

  public static boolean method1233(class_6880 arg0) { // было: b
        return mc.field_1724.method_6088().containsKey(arg0);
    }

  public static boolean method1234(class_2248 arg0) { // было: a
        return method1235(mc.field_1724.method_5829().method_1014(-0.001), arg0);
    }

  public static boolean method1235(class_238 arg0, class_2248 arg1) { // было: a
        return method1237(arg0, lp0 -> method1249(arg1, ((class_2338) lp0)));
    }

  public static boolean method1236(class_238 arg0, List arg1) { // было: a
        return method1237(arg0, lp0 -> method1248(arg1, ((class_2338) lp0)));
    }

  public static boolean method1237(class_238 arg0, Predicate arg1) { // было: a
        return class_2338.method_29715(arg0).anyMatch(arg1);
    }

  public static boolean method1238(class_306 arg0) { // было: a
        return method1240(arg0.method_1442(), arg0.method_1444());
    }

  public static boolean method1239(ai arg0) { // было: a
        int var1 = arg0.method688();
        return mc.field_1755 != null ? -2093161470 ^ -2093161470 : !arg0.m() ? -2093161470 ^ -2093161470 : !method1240(method1230(var1), var1) ? -2093161470 ^ -2093161470 : 995819097 ^ 995819096;
    }

  public static boolean method1240(class_307 arg0, int arg1) { // было: a
        if (arg1 == (-435628067 ^ 435628066)) {
            return -1671325991 ^ -1671325991;
        }
        switch (cE_ClassA142.field728[arg0.ordinal()]) {
            case 1:
                return GLFW.glfwGetKey(mc.method_22683().method_4490(), arg1) != (-1195802563 ^ -1195802564) ? -971432519 ^ -971432519 : 1838554925 ^ 1838554924;
            case 2:
                return GLFW.glfwGetMouseButton(mc.method_22683().method_4490(), arg1) != (2086686371 ^ 2086686370) ? 1317787414 ^ 1317787414 : -885132781 ^ -885132782;
            default:
                return -1671325991 ^ -1671325991;
        }
    }

  public static boolean method1241(class_2338 arg0) { // было: a
        return method1242(mc.field_1687.method_8320(arg0));
    }

  public static boolean method1242(class_2680 arg0) { // было: a
        return arg0.method_26215() ? -69097325 ^ -69097326 : arg0.method_26204().equals(class_2246.field_10543) ? -69097325 ^ -69097326 : !arg0.method_26204().equals(class_2246.field_10243) ? 617412864 ^ 617412864 : -69097325 ^ -69097326;
    }

  public static boolean method1243(class_437 arg0) { // было: a
        return arg0 instanceof class_408;
    }

  public static boolean ac() {
        return mc.field_1724 == null ? 1532368933 ^ 1532368932 : mc.field_1687 != null ? -688956077 ^ -688956077 : 1532368933 ^ 1532368932;
    }

  public static void method1244(class_1268 arg0) { // было: a
        mc.field_1761.method_2919(mc.field_1724, arg0);
    }

  public static float method1245(class_1309 arg0) { // было: b
        float var1 = arg0.method_6032() + arg0.method_6067();
        if (arg0 instanceof class_1657) {
            class_1657 var2 = ((class_1657) arg0);
            class_266 var3 = var2.method_7327().method_1189(class_8646.field_45158);
            if (var3 != null) {
                class_5250 var4 = class_9013.method_55398(var2.method_7327().method_55430(var2, var3), var3.method_55380(class_9025.field_47566));
                try {
                    var1 = Float.parseFloat(cQ.method1717(var4.getString()));
                } catch (NumberFormatException var5) {
                }
            }
        }
        return class_3532.method_15363(var1, 0.0f, arg0.method_6063());
    }

  public static String method1246(class_1309 arg0) { // было: a
        return method1247(method1245(arg0));
    }

  public static String method1247(float arg0) { // было: a
        Object[] __obj1 = new Object[-1641533471 ^ -1641533472];
        __obj1[1165952401 ^ 1165952401] = Float.valueOf(arg0);
        return String.format(Decryptor.method1945(XorDecoder.method1946("Þ¢v¦¢²\u0012Õt¨ÜuýÂ\u0008üÿ¦��õ", -1511229652 ^ 1842446525)), __obj1).replace(Decryptor.method1945(XorDecoder.method1946("D¬·4D¶¬f\u0018Âú5[Ý«B}È¯Y\\Öÿ>", 460356206 ^ 414383431)), Decryptor.method1945(XorDecoder.method1946("V\u00198¯U+\nðq%\np&7õpr`W\u0002dü", 1083662174 ^ -2117198746))).replace(Decryptor.method1945(XorDecoder.method1946("\u000eÿïi7ÂÛ^(Çì\u0012*Ýô\u000c;ÆÊ]!æ\u001a", 1034245912 ^ 437903424)), Decryptor.method1945(XorDecoder.method1946("Ôaé\u0018ÐPò\u0001ßPÍ$³BÛ8×\u007fÈ=w£i", -613874036 ^ -1879625621)));
    }

    @Generated
  private cE() { // было: <init>
        super();
        throw new UnsupportedOperationException(Decryptor.method1945(XorDecoder.method1946("ÕtþÏùcÂæzÓ¤ký¦KÛ§æwÎ¦¨\\ãûIììª\u0008û£ãið«PßáápÂÏ\u007fçÆ¦~øòz¤ºXÌ¹XØÎ\u0005û§¶\\Ý¢y¤¦Gí¥\u000c", -1358530488 ^ 87534302)));
    }

  private static boolean method1248(List arg0, class_2338 arg1) { // было: a
        return arg0.contains(mc.field_1687.method_8320(arg1).method_26204());
    }

  private static boolean method1249(class_2248 arg0, class_2338 arg1) { // было: a
        return mc.field_1687.method_8320(arg1).method_26204().equals(arg0);
    }

  private static int lv(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int lw(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int lx(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}