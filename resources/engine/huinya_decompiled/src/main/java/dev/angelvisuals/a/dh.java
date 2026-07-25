// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.dH
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.AngelVisuals;
import dev.angelvisuals.a.ClassA145;
import dev.angelvisuals.a.ClassA50_ClassA51;
import dev.angelvisuals.a.ClassA52;
import dev.angelvisuals.a.cE;
import dev.angelvisuals.a.cF;
import dev.angelvisuals.a.dL;
import dev.angelvisuals.a.dh;
import dev.angelvisuals.a.do;
import java.util.List;
import lombok.Generated;
import net.minecraft.class_1041;
import net.minecraft.class_2371;
import net.minecraft.class_304;
import net.minecraft.class_310;
import net.minecraft.class_3675;
import net.minecraft.class_3675.class_306;
import net.minecraft.class_463;
import net.minecraft.class_471;
import net.minecraft.class_497;
import net.minecraft.class_498;
import net.minecraft.class_746;

public final class dH implements cF {

    // ---- поля ----
  public static final List field729; // было: R
  public static ClassA50_ClassA51 field730; // было: a
  public static boolean aw;
  private static final String Fx = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final String Fy = "// class hierarchy hashing: ENABLED";
  private static final String Fz = "// stop. seriously. go play minecraft instead";
  private static final String FA = "Protected by t.me/JoinerClient";
  private static final String FB = "// stop. seriously. go play minecraft instead";
  private static final int sD = -739261789;
  private static final int sE = 2005746768;
  private static final int sF = -75285627;
  private static final byte[] eT;

    static {
        eT = "s5WRR~(}Hdv.FuYMzG.;8CS]qTI6;dCjV$IA$fdAn@M9OhgD#ouYXN%qj[P[A~WEtV&OpaWI{3h1Ro0X86KXUIo8=R{r]_b/0w;%uV\"=1#i$ws}2Me-=G$8IT}i)p\\Km0[[2bnP1/0[5V8D6bz,pm)BtxA&QA!iGpkSEXl~L`An^D2a{gYpq`NM-j8Uzc<)S-qGsd<zbS}9PH\\lvM=Q6Gzi+%<5T|4]+}IbSji g5 %^IH,,k#r]g!_<%\"+$mPK)".getBytes("ISO-8859-1");
        field729 = List.of(mc.field_1690.field_1894, mc.field_1690.field_1881, mc.field_1690.field_1913, mc.field_1690.field_1849, mc.field_1690.field_1903);
        field730 = new ClassA50_ClassA51();
        aw = -1774571332 ^ -1774571331;
    }

  public static void method1250(Runnable arg0) { // было: b
        int var2;
        if (!dh.an()) {
            arg0.run();
            return;
        } else {
            AngelVisuals.getInstance().getScriptManager().method446(field730);
            String var1 = AngelVisuals.getInstance().getServerHandler().method1385();
            var2 = 828702064 ^ -828702065;
            switch (var1.hashCode()) {
                case -495240450:
                    if (!var1.equals(Decryptor.method1945(XorDecoder.method1946("¨°6íl®íQóÙ2ÍØ¬\u007f¨Ø½8¥", -530143041 ^ 2019783254)))) {
                        break;
                    }
                    var2 = 69237367 ^ 69237366;
                    break;
                case -441313278:
                    if (!var1.equals(Decryptor.method1945(XorDecoder.method1946("_\u001ctQb,º!H\u0015i[8â]M\u0011·LH\u007fð", 218929743 ^ -1068583594)))) {
                        break;
                    }
                    var2 = -1510673541 ^ -1510673544;
                    break;
                case 1087265806:
                    if (!var1.equals(Decryptor.method1945(XorDecoder.method1946("«+}éæ\u000f`÷â:VÉË\u001cWïå\u0015vÊµ\u0008\u000e", 1698502576 ^ -586258384)))) {
                        break;
                    }
                    var2 = 2095694674 ^ 2095694672;
                    break;
                case 1154553036:
                    if (!var1.equals(Decryptor.method1945(XorDecoder.method1946("¼5´D\u0015Z¢\u0010·j¼\u00102\u0011°o&Ù?", 426959013 ^ 462853982)))) {
                        break;
                    }
                    var2 = -1255189403 ^ -1255189403;
                default:
            }
        }
        switch (var2) {
            case 0:
            case 1:
                field730.method440(do.class, lp0 -> method1261(((do) lp0)));
                field730.method440(do.class, lp0 -> method1260(arg0, ((do) lp0)));
                return;
            case 2:
                if (!mc.field_1724.method_24828()) {
                    arg0.run();
                    return;
                } else {
                    field730.method440(do.class, lp0 -> method1259(((do) lp0)));
                    field730.method440(do.class, lp0 -> method1258(((do) lp0)));
                    field730.method440(do.class, lp0 -> method1257(arg0, ((do) lp0)));
                    field730.method440(do.class, lp0 -> method1256(((do) lp0)));
                    return;
                }
            case 3:
                field730.method440(do.class, lp0 -> method1255(((do) lp0)));
                field730.method440(do.class, lp0 -> method1254(arg0, ((do) lp0)));
                field730.method440(do.class, lp0 -> method1253(((do) lp0)));
                return;
            default:
                arg0.run();
                return;
        }
    }

  public static void bb() {
        aw = -755673041 ^ -755673041;
        bd();
    }

  public static void bc() {
        dL.method1263(655737812 ^ 655737813);
        aw = -1779287902 ^ -1779287901;
        be();
    }

  public static void bd() {
        field729.forEach(lp0 -> method1252(((class_304) lp0)));
    }

  public static void be() {
        if (!AngelVisuals.getInstance().getMenuScreen().field609) {
            field729.forEach(lp0 -> method1251(((class_304) lp0)));
        }
    }

  public static boolean ay() {
        if (mc.field_1755 != null) {
            if (!cE.method1243(mc.field_1755)) {
                if (!(mc.field_1755 instanceof class_498)) {
                    if (!(mc.field_1755 instanceof class_471)) {
                        if (!(mc.field_1755 instanceof class_463)) {
                            if (!(mc.field_1755 instanceof class_497)) {
                                if (mc.field_1724 == null) {
                                    return -445178901 ^ -445178901;
                                } else {
                                    if (mc.field_1724.field_7512 == null) {
                                        return -445178901 ^ -445178901;
                                    } else {
                                        int var0 = mc.field_1724.field_7512.field_7761.size();
                                        return var0 < (1059509689 ^ 1059509666) ? 411571532 ^ 411571532 : 482032353 ^ 482032352;
                                    }
                                }
                            } else {
                                return -573464803 ^ -573464803;
                            }
                        } else {
                            return 1471891662 ^ 1471891662;
                        }
                    } else {
                        return -1522912086 ^ -1522912086;
                    }
                } else {
                    return 1116322756 ^ 1116322756;
                }
            } else {
                return 1673370293 ^ 1673370293;
            }
        } else {
            return -1135209564 ^ -1135209564;
        }
    }

    @Generated
  private dH() { // было: <init>
        super();
        throw new UnsupportedOperationException(Decryptor.method1945(XorDecoder.method1946("Î¼ïáÔÖøÝý¶áÈðâ½²ÐÄ¼ÉìÑ½Çüà¥Òó÷ä¸ÌòëËÀúÎëÝÔ»äøÝåçé¯á¿Ã×ÃÇÕµä¼ÇÆâ¿Üò¾¼", -808347309 ^ 2054907102)));
    }

  private static void method1251(class_304 arg0) { // было: a
        arg0.method_23481(class_3675.method_15987(mc.method_22683().method_4490(), arg0.method_1429().method_1444()));
    }

  private static void method1252(class_304 arg0) { // было: b
        arg0.method_23481(-1330521106 ^ -1330521106);
    }

  private static boolean method1253(do arg0) { // было: a
        bc();
        return -597915364 ^ -597915363;
    }

  private static boolean method1254(Runnable arg0, do arg1) { // было: a
        arg0.run();
        return 1209765445 ^ 1209765444;
    }

  private static boolean method1255(do arg0) { // было: b
        bb();
        return 147284532 ^ 147284533;
    }

  private static boolean method1256(do arg0) { // было: c
        bc();
        return -114993069 ^ -114993070;
    }

  private static boolean method1257(Runnable arg0, do arg1) { // было: b
        arg0.run();
        return 1652723668 ^ 1652723669;
    }

  private static boolean method1258(do arg0) { // было: d
        bb();
        return 1000651937 ^ 1000651936;
    }

  private static boolean method1259(do arg0) { // было: e
        bb();
        return -1784559015 ^ -1784559016;
    }

  private static boolean method1260(Runnable arg0, do arg1) { // было: c
        arg0.run();
        bc();
        return 579355690 ^ 579355691;
    }

  private static boolean method1261(do arg0) { // было: f
        bb();
        return -1292621335 ^ -1292621336;
    }

  private static int qD(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int qE(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int qF(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}