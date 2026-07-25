// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.aE
package dev.angelvisuals.a;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.angelvisuals.AngelVisuals;
import dev.angelvisuals.a.ClassA158;
import dev.angelvisuals.a.ClassA163;
import dev.angelvisuals.a.ClassA164;
import dev.angelvisuals.a.aY;
import dev.angelvisuals.a.be;
import dev.angelvisuals.a.bl;
import dev.angelvisuals.a.bp;
import dev.angelvisuals.a.ch;
import dev.angelvisuals.a.cs;
import dev.angelvisuals.a.dp;
import dev.angelvisuals.a.dq;
import lombok.Generated;
import net.minecraft.class_10142;
import net.minecraft.class_1041;
import net.minecraft.class_241;
import net.minecraft.class_276;
import net.minecraft.class_284;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_293.class_5596;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.minecraft.class_4587.class_4665;
import net.minecraft.class_4588;
import net.minecraft.class_6367;
import org.joml.Matrix4f;

public final class aE implements dq {

    // ---- поля ----
  public static final float dk = 0.800000011920929f;
  public static be field922; // было: o
  private static be field923; // было: p
  private static be field924; // было: q
  private static be field925; // было: r
  private static be field926; // было: s
  private static be field927; // было: t
  private static be field928; // было: u
  private static be field929; // было: v
  private static be field930; // было: w
  private static be field931; // было: x
  private static final ClassA164 field932; // было: g
  private static final Supplier field933; // было: a
  private static final class_276 field934; // было: a
  private static final String Bd = "// number obfuscation: ENABLED (XOR masking)";
  private static final String Be = "// flow obfuscation: ENABLED";
  private static final String Bf = "// class hierarchy hashing: ENABLED";
  private static final String Bg = "// === DO NOT TOUCH ===";
  private static final String Bh = "// you are reading machine-generated garbage";
  private static final int pX = -1678686297;
  private static final int pY = 921010471;
  private static final int pZ = -2121027030;
  private static final byte[] ee;

    static {
        ee = "_):Ft4k8\"y\":.03b`tO7]H!`)s\\|OZt94Mtcvb|m~9`|f;U4X%qZ)xRsFAUW?Sy_.~TfYU`7c-+?LT|#VDrE*/qv:bLYuKk.\\s2QR&+CD>}}:7;1jj_#[Gn,KYv@xv<?Crx+gO)O,san)^g6~8Cn#>!u8}:DM'lFd)$`2B[Obisr$T=Cvp.zW(qnh%`&,'R$c+tw<!%!Q-6~snWy~KN\"RyuR>~-^H[<[m\\?$5]`aL.0GqHZ1sG.kyx=3]{&&=<':".getBytes("ISO-8859-1");
        field932 = new ClassA164(189722081 ^ 189722081);
        field933 = Suppliers.memoize(() -> method1765());
        field934 = class_310.method_1551().method_1522();
    }

  public static void aW() {
        field922 = new be(AngelVisuals.id(Decryptor.method1945(XorDecoder.method1946("x\u000b9si��XwD\u000b)Xl\u001eG\u0010,��5vq8N\u0006", -1825693515 ^ -1470271573))), class_290.field_1576);
        field923 = new be(AngelVisuals.id(Decryptor.method1945(XorDecoder.method1946(">HD\u0014B\\3¶|>LýzK\u0003®w[K¢\u00124", 1403600506 ^ 1518795520))), class_290.field_1576);
        field925 = new be(AngelVisuals.id(Decryptor.method1945(XorDecoder.method1946("S\u0019NÎ­\u0014{ÊÍ.=µ¡\u0012@¡\"yfI§&r\u0015dbn·g>Íj", -1716062850 ^ -834464652))), class_290.field_1575);
        field924 = new be(AngelVisuals.id(Decryptor.method1945(XorDecoder.method1946("\u000cêÖé\u0016Ü·\n¤ÕUâ(ý½\u0004ï", -1363658371 ^ 476732067))), class_290.field_1575);
        field926 = new be(AngelVisuals.id(Decryptor.method1945(XorDecoder.method1946("¶sw\u001cM-~w(MµS{h½s\u0019\u001d@~\u0019", 1915089661 ^ 1449579781))), class_290.field_1576);
        field927 = new be(AngelVisuals.id(Decryptor.method1945(XorDecoder.method1946("î!º¹ßpäÉ%Ý¤j¢³ÓPÿ¡Ùdñ·", -1039575423 ^ 1220887836))), class_290.field_1576);
        field928 = new be(AngelVisuals.id(Decryptor.method1945(XorDecoder.method1946("pâÞ_÷ðýAöÛñxãbÅpÁªù", -248303860 ^ 899975429))), class_290.field_1576);
        field929 = new be(AngelVisuals.id(Decryptor.method1945(XorDecoder.method1946("[)üÿ\u0008\u0010ÓÜ\u0017=õû\u0010+Ü \u0003,ÌÕ$5ÞÇ\u00083Ýñ',É\u000c\u0017ÔÑ\u0001+ÊZ\u0006Ü­", 613985079 ^ -1272449963))), class_290.field_1576);
        field930 = new be(AngelVisuals.id(Decryptor.method1945(XorDecoder.method1946("ú\tÒºý^Ùµ\u0010Â¨ù\rÀ±ùR6Ý", 1689873779 ^ -2079716670))), class_290.field_1576);
        field931 = new be(AngelVisuals.id(Decryptor.method1945(XorDecoder.method1946("\u001a×Ýg\u0003Á»x\u001eÿ®#)à.*ó\u001aAÇËj", -115212006 ^ -1361803421))), class_290.field_1576);
    }

  public static void aX() {
        field932.method_1236(0.0f, 0.0f, 0.0f, 1.0f);
        field932.setup();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        mc.method_1522().method_35610();
        RenderSystem.setShader(class_10142.field_53880);
        RenderSystem.setShaderTexture(1082943648 ^ 1082943648, mc.method_1522().method_30277());
        method1735(0.0f, 0.0f, ((float) mw.method_4486()), ((float) mw.method_4502()), 658718489 ^ 658718488);
        mc.method_1522().method_1242();
        RenderSystem.disableBlend();
        mc.method_1522().method_1235(-551330179 ^ -551330180);
        field932.stop();
    }

  private static void method1735(float arg0, float arg1, float arg2, float arg3, boolean arg4) { // было: a
        float __stk1;
        float __stk2;
        class_287 var5 = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
        int var6 = 647424611 ^ 644421020;
        __stk1 = !arg4 ? 1.0f : 0.0f;
        float var7 = __stk1;
        __stk2 = !arg4 ? 0.0f : 1.0f;
        float var8 = __stk2;
        var5.method_22912(arg0, arg1, 0.0f).method_22913(0.0f, ((Float) var8)).method_39415(351793612 ^ -351793613);
        var5.method_22912(arg0, arg1 + arg3, 0.0f).method_22913(0.0f, ((Float) var7)).method_39415(-1512299793 ^ 1512299792);
        var5.method_22912(arg0 + arg2, arg1 + arg3, 0.0f).method_22913(1.0f, ((Float) var7)).method_39415(-742385327 ^ 742385326);
        var5.method_22912(arg0 + arg2, arg1, 0.0f).method_22913(1.0f, ((Float) var8)).method_39415(1389280117 ^ -1389280118);
        class_286.method_43433(var5.method_60800());
    }

  public static void method1736(class_4587 arg0, class_241 arg1, class_241 arg2, bp arg3) { // было: a
        arg0.method_22903();
        try {
            Matrix4f var4 = arg0.method_23760().method_23761();
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.setShader(class_10142.field_53876);
            RenderSystem.lineWidth(1.0f);
            aY();
            class_287 var5 = RenderSystem.renderThreadTesselator().method_60827(class_5596.field_29345, class_290.field_1576);
            var5.method_22918(var4, arg1.field_1343, arg1.field_1342, 0.0f).method_39415(arg3.method1680());
            var5.method_22918(var4, arg2.field_1343, arg2.field_1342, 0.0f).method_39415(arg3.method1680());
            class_286.method_43433(var5.method_60800());
            aZ();
        } catch (Throwable e2) {
            try {
                while (true) {
                    Throwable var6 = e2;
                }
            } catch (Throwable var6) {
            }
        }
    }

  public static void method1737(class_4587 arg0, class_241 arg1, class_241 arg2, class_241 arg3, class_241 arg4, bp arg5, int arg6) { // было: a
        arg0.method_22903();
        try {
            Matrix4f var7 = arg0.method_23760().method_23761();
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.setShader(class_10142.field_53876);
            RenderSystem.lineWidth(1.0f);
            aY();
            class_287 var8 = RenderSystem.renderThreadTesselator().method_60827(class_5596.field_29345, class_290.field_1576);
            int var9 = 1754790693 ^ 1754790693;
            while (var9 <= arg6) {
                float var10 = ((float) var9) / ((float) arg6);
                float var11 = ((float) cs.method1414(((double) var10), ((double) arg1.field_1343), ((double) arg2.field_1343), ((double) arg3.field_1343), ((double) arg4.field_1343)));
                float var12 = ((float) cs.method1414(((double) var10), ((double) arg1.field_1342), ((double) arg2.field_1342), ((double) arg3.field_1342), ((double) arg4.field_1342)));
                var8.method_22918(var7, var11, var12, 0.0f).method_39415(arg5.method1680());
                ++var9;
                continue;
            }
            class_286.method_43433(var8.method_60800());
            aZ();
        } catch (Throwable e2) {
            try {
                while (true) {
                    Throwable var13 = e2;
                }
            } catch (Throwable var13) {
            }
        }
    }

  private static float method1738(float arg0, float arg1, float arg2, float arg3, float arg4) { // было: a
        float var5 = 1.0f - arg0;
        float var6 = arg0 * arg0;
        float var7 = var5 * var5;
        return var7 * var5 * arg1 + 3.0f * var7 * arg0 * arg2 + 3.0f * var5 * var6 * arg3 + var6 * arg0 * arg4;
    }

  public static void method1739(class_4587 arg0, float arg1, float arg2, float arg3, float arg4, bp arg5) { // было: a
        arg0.method_22903();
        Matrix4f var6 = arg0.method_23760().method_23761();
        RenderSystem.setShader(class_10142.field_53876);
        aY();
        class_287 var7 = RenderSystem.renderThreadTesselator().method_60827(class_5596.field_27382, class_290.field_1576);
        var7.method_22918(var6, arg1, arg2 + arg4, 0.0f).method_39415(arg5.method1680());
        var7.method_22918(var6, arg1 + arg3, arg2 + arg4, 0.0f).method_39415(arg5.method1680());
        var7.method_22918(var6, arg1 + arg3, arg2, 0.0f).method_39415(arg5.method1680());
        var7.method_22918(var6, arg1, arg2, 0.0f).method_39415(arg5.method1680());
        class_286.method_43433(var7.method_60800());
        aZ();
        arg0.method_22909();
    }

  public static void method1740(class_4587 arg0, float arg1, float arg2, float arg3, float arg4, float arg5, aY arg6, bp arg7) { // было: a
        if (field923 == null) {
            method1742(arg0, arg1, arg2, arg3, arg4, arg6, arg7);
            return;
        } else {
            if (field923.field936 != null) {
                arg0.method_22903();
                Matrix4f var8 = arg0.method_23760().method_23761();
                float var9 = 0.800000011920929f;
                field923.method1767();
                field923.method1770(Decryptor.method1945(XorDecoder.method1946("Ðw\u000ee&R\u001aÞ\u007f(j÷$=\u0007Í/.\u001aèqZn", -1365593770 ^ -33708053))).method_1255(arg3, arg4);
                field923.method1770(Decryptor.method1945(XorDecoder.method1946(" Øð´ò³Ë¼»ÕÔíùæÔç", 1683680164 ^ -796899210))).method_35657(arg6.method1606() * arg5 / 2.0f, arg6.ab() * arg5 / 2.0f, arg6.method1607() * arg5 / 2.0f, arg6.aa() * arg5 / 2.0f);
                field923.method1770(Decryptor.method1945(XorDecoder.method1946("¾\u007fà»\\¯ø\u0007íi´û\u0015å×\u0003", 1579908488 ^ 1623307869))).method_1251(var9);
                field923.method1770(Decryptor.method1945(XorDecoder.method1946("±º\u0011\u007f²º0=\u000bh®£Oc»-H¤³Ua·üW'¢,\"S@¾³'T­¹\u0013/", -1794618551 ^ -2022925123))).method_1251(arg5);
                aY();
                float var10 = -var9 / 2.0f + var9 * 2.0f;
                float var11 = var9 / 2.0f + var9;
                float var12 = arg1 - var10 / 2.0f;
                float var13 = arg2 - var11 / 2.0f;
                float var14 = arg3 + var10;
                float var15 = arg4 + var11;
                class_287 var16 = RenderSystem.renderThreadTesselator().method_60827(class_5596.field_27382, class_290.field_1576);
                var16.method_22918(var8, var12, var13, 0.0f).method_39415(arg7.method1680());
                var16.method_22918(var8, var12, var13 + var15, 0.0f).method_39415(arg7.method1680());
                var16.method_22918(var8, var12 + var14, var13 + var15, 0.0f).method_39415(arg7.method1680());
                var16.method_22918(var8, var12 + var14, var13, 0.0f).method_39415(arg7.method1680());
                class_286.method_43433(var16.method_60800());
                aZ();
                arg0.method_22909();
                return;
            } else {
                method1742(arg0, arg1, arg2, arg3, arg4, arg6, arg7);
                return;
            }
        }
    }

  public static void method1741(class_4587 arg0, float arg1, float arg2, float arg3, float arg4, float arg5, aY arg6, bp arg7) { // было: b
        if (field928 == null) {
            method1742(arg0, arg1, arg2, arg3, arg4, arg6, arg7);
            return;
        } else {
            if (field928.field936 != null) {
                arg0.method_22903();
                Matrix4f var8 = arg0.method_23760().method_23761();
                float var9 = 0.800000011920929f;
                field928.method1767();
                field928.method1770(Decryptor.method1945(XorDecoder.method1946("m¬KÔ<ð4eD«>)54´kø@", 1727895707 ^ 456692858))).method_1255(arg3, arg4);
                field928.method1770(Decryptor.method1945(XorDecoder.method1946("SÛTBh»yh@\\\u001eOÐvOe c\u0015*\u0013", 346848698 ^ 985372059))).method_35657(arg6.method1606(), arg6.ab(), arg6.method1607(), arg6.aa());
                field928.method1770(Decryptor.method1945(XorDecoder.method1946("K$\u0016=\u0012¤5rbn0\u001c��ia|8��Èj", -295687325 ^ -1181386133))).method_1251(var9);
                field928.method1770(Decryptor.method1945(XorDecoder.method1946("{h+\u001bv)\u0007\u0001Ce4&MxIWMN\u0002\u0003Re[S", 1404666365 ^ 1038058436))).method_1251(arg5);
                field928.method1770(Decryptor.method1945(XorDecoder.method1946("@´\u001f¬J\u0006¤\u0015\u0017D\u0015Üm\u001e¨r¦ZØ", 1549618341 ^ -1187333245))).method_1251(0.0f);
                field928.method1770(Decryptor.method1945(XorDecoder.method1946("\u0010¬ñ\u0015E=C\u001e\u001eóCJÁ¬@OýN", -1608289545 ^ -740063536))).method_1251(0.5f);
                aY();
                float var10 = -var9 / 2.0f + var9 * 2.0f;
                float var11 = var9 / 2.0f + var9;
                float var12 = arg1 - var10 / 2.0f;
                float var13 = arg2 - var11 / 2.0f;
                float var14 = arg3 + var10;
                float var15 = arg4 + var11;
                class_287 var16 = RenderSystem.renderThreadTesselator().method_60827(class_5596.field_27382, class_290.field_1576);
                var16.method_22918(var8, var12, var13, 0.0f).method_39415(arg7.method1680());
                var16.method_22918(var8, var12, var13 + var15, 0.0f).method_39415(arg7.method1680());
                var16.method_22918(var8, var12 + var14, var13 + var15, 0.0f).method_39415(arg7.method1680());
                var16.method_22918(var8, var12 + var14, var13, 0.0f).method_39415(arg7.method1680());
                class_286.method_43433(var16.method_60800());
                aZ();
                arg0.method_22909();
                return;
            } else {
                method1742(arg0, arg1, arg2, arg3, arg4, arg6, arg7);
                return;
            }
        }
    }

  public static void method1742(class_4587 arg0, float arg1, float arg2, float arg3, float arg4, aY arg5, bp arg6) { // было: a
        if (field922 == null) {
            method1739(arg0, arg1, arg2, arg3, arg4, arg6);
            return;
        } else {
            if (field922.field936 != null) {
                arg0.method_22903();
                Matrix4f var7 = arg0.method_23760().method_23761();
                float var8 = 0.800000011920929f;
                field922.method1767();
                field922.method1770(Decryptor.method1945(XorDecoder.method1946("\u000bUÔÙW*-Z«Û87Ð+*´_^", -1012851202 ^ -1597770721))).method_1255(arg3, arg4);
                field922.method1770(Decryptor.method1945(XorDecoder.method1946("¿Åµ[¥q¬½\u0007£ÎVÁzùË\n", -526187355 ^ -682233240))).method_35657(arg5.method1606(), arg5.ab(), arg5.method1607(), arg5.aa());
                field922.method1770(Decryptor.method1945(XorDecoder.method1946("±jxöH[¹÷z��ûwn¢ôr\u0012ó$\u0004", 898653974 ^ 210344917))).method_1251(var8);
                aY();
                float var9 = -var8 / 2.0f + var8 * 2.0f;
                float var10 = var8 / 2.0f + var8;
                float var11 = arg1 - var9 / 2.0f;
                float var12 = arg2 - var10 / 2.0f;
                float var13 = arg3 + var9;
                float var14 = arg4 + var10;
                class_287 var15 = RenderSystem.renderThreadTesselator().method_60827(class_5596.field_27382, class_290.field_1576);
                var15.method_22918(var7, var11, var12, 0.0f).method_39415(arg6.method1680());
                var15.method_22918(var7, var11, var12 + var14, 0.0f).method_39415(arg6.method1680());
                var15.method_22918(var7, var11 + var13, var12 + var14, 0.0f).method_39415(arg6.method1680());
                var15.method_22918(var7, var11 + var13, var12, 0.0f).method_39415(arg6.method1680());
                class_286.method_43433(var15.method_60800());
                aZ();
                arg0.method_22909();
                return;
            } else {
                method1739(arg0, arg1, arg2, arg3, arg4, arg6);
                return;
            }
        }
    }

  public static void method1743(class_4587 arg0, float arg1, float arg2, float arg3, float arg4, aY arg5, bp arg6, bp arg7, bp arg8, bp arg9) { // было: a
        arg0.method_22903();
        Matrix4f var10 = arg0.method_23760().method_23761();
        float var11 = 0.800000011920929f;
        field929.method1767();
        field929.method1770(Decryptor.method1945(XorDecoder.method1946("µ\u001eò7íO®H»\u0016Ô8MÁU¨FÒH\u0018¦<", -949949983 ^ -956590535))).method_1255(arg3, arg4);
        field929.method1770(Decryptor.method1945(XorDecoder.method1946("cs£yX\u0013Sp(«%\u007fxtU7×X%7Ý(", 1784282400 ^ 2142879025))).method_35657(arg5.method1606(), arg5.ab(), arg5.method1607(), arg5.aa());
        field929.method1770(Decryptor.method1945(XorDecoder.method1946("£Gð]ÕqÒ~\u0001à%Ø\u007fíK\u0002è7Ðc¾!", 2107692674 ^ 1629742178))).method_1251(var11);
        field929.method1770(Decryptor.method1945(XorDecoder.method1946("\tñ]EÈ;Á]ö$ÐJÀ+ð\u0002Ê\"æ\u0002ÄW", 866020523 ^ -2047546981))).method_35657(((float) arg6.method1695()) / 255.0f, ((float) arg6.method1696()) / 255.0f, ((float) arg6.method1697()) / 255.0f, ((float) arg6.method1698()) / 255.0f);
        field929.method1770(Decryptor.method1945(XorDecoder.method1946("Ñ\u0008c|Ï9a?dF&ò\u0003\u00044ùz\u0007\u001cï��\u000cp", -1422266473 ^ -435634641))).method_35657(((float) arg7.method1695()) / 255.0f, ((float) arg7.method1696()) / 255.0f, ((float) arg7.method1697()) / 255.0f, ((float) arg7.method1698()) / 255.0f);
        field929.method1770(Decryptor.method1945(XorDecoder.method1946("þÛmö¸Ñ\u000fÄ¥`ÐänÐ­NÅ»\u0006ôÈ\u0004²³\u007f·º¶��ÕtÁ@º", 1935086812 ^ -194971881))).method_35657(((float) arg8.method1695()) / 255.0f, ((float) arg8.method1696()) / 255.0f, ((float) arg8.method1697()) / 255.0f, ((float) arg8.method1698()) / 255.0f);
        field929.method1770(Decryptor.method1945(XorDecoder.method1946("±ðEHü@'ÐòSg¬]=·`dÜ\u0014/", 1442656702 ^ 1205138779))).method_35657(((float) arg9.method1695()) / 255.0f, ((float) arg9.method1696()) / 255.0f, ((float) arg9.method1697()) / 255.0f, ((float) arg9.method1698()) / 255.0f);
        aY();
        float var12 = -var11 / 2.0f + var11 * 2.0f;
        float var13 = var11 / 2.0f + var11;
        float var14 = arg1 - var12 / 2.0f;
        float var15 = arg2 - var13 / 2.0f;
        float var16 = arg3 + var12;
        float var17 = arg4 + var13;
        class_287 var18 = RenderSystem.renderThreadTesselator().method_60827(class_5596.field_27382, class_290.field_1576);
        var18.method_22918(var10, var14, var15, 0.0f).method_39415(arg6.method1680());
        var18.method_22918(var10, var14, var15 + var17, 0.0f).method_39415(arg7.method1680());
        var18.method_22918(var10, var14 + var16, var15 + var17, 0.0f).method_39415(arg8.method1680());
        var18.method_22918(var10, var14 + var16, var15, 0.0f).method_39415(arg9.method1680());
        class_286.method_43433(var18.method_60800());
        aZ();
        arg0.method_22909();
    }

  public static void method1744(class_4587 arg0, float arg1, float arg2, float arg3, float arg4, aY arg5, dp arg6) { // было: a
        method1743(arg0, arg1, arg2, arg3, arg4, arg5, arg6.method1661(), arg6.method1662(), arg6.method1664(), arg6.method1663());
    }

  public static void method1745(class_4587 arg0, float arg1, float arg2, float arg3, float arg4, float arg5, aY arg6, bp arg7) { // было: c
        arg0.method_22903();
        Matrix4f var8 = arg0.method_23760().method_23761();
        float var9 = 0.800000011920929f;
        float var10 = 1.0f;
        field926.method1767();
        field926.method1770(Decryptor.method1945(XorDecoder.method1946("@¡ï\u0018ð³èN©ÉgòÜõ]ùÏèx§»", -1001865512 ^ 1708031733))).method_1255(arg3, arg4);
        field926.method1770(Decryptor.method1945(XorDecoder.method1946("ücÇ\u0003¹¼ï8Êàh¶Ê'à·º'êÇ", 1101050062 ^ -1149771712))).method_35657(arg6.method1606(), arg6.ab(), arg6.method1607(), arg6.aa());
        field926.method1770(Decryptor.method1945(XorDecoder.method1946("#ø_ËUÎ}è\u001a¾O³XÀBÝ\u0001½G¡PÜ\u0011·", 601109211 ^ -1443315781))).method_1255(var9, var10);
        field926.method1770(Decryptor.method1945(XorDecoder.method1946(">ñã]¼ÝOóÀ\u0015ðÄ\u0002ÓÕUÆé¬", 2053529327 ^ -340604533))).method_1251(arg5);
        aY();
        float var11 = -var10 / 2.0f + var10 * 2.0f;
        float var12 = var10 / 2.0f + var10;
        float var13 = arg1 - var11 / 2.0f;
        float var14 = arg2 - var12 / 2.0f;
        float var15 = arg3 + var11;
        float var16 = arg4 + var12;
        class_287 var17 = RenderSystem.renderThreadTesselator().method_60827(class_5596.field_27382, class_290.field_1576);
        var17.method_22918(var8, var13, var14, 0.0f).method_39415(arg7.method1680());
        var17.method_22918(var8, var13, var14 + var16, 0.0f).method_39415(arg7.method1680());
        var17.method_22918(var8, var13 + var15, var14 + var16, 0.0f).method_39415(arg7.method1680());
        var17.method_22918(var8, var13 + var15, var14, 0.0f).method_39415(arg7.method1680());
        class_286.method_43433(var17.method_60800());
        aZ();
        arg0.method_22909();
    }

  public static void method1746(class_4587 arg0, float arg1, float arg2, float arg3, float arg4, float arg5, float arg6, bp arg7, bp arg8) { // было: a
        if (field931 == null) {
            method1742(arg0, arg1, arg2, arg3, arg4, aY.method1597(arg6), arg7);
            return;
        } else {
            if (field931.field936 != null) {
                arg0.method_22903();
                Matrix4f var9 = arg0.method_23760().method_23761();
                field931.method1767();
                field931.method1770(Decryptor.method1945(XorDecoder.method1946("d«¥÷<úùj£øCøyó\\­ñü", -1416328243 ^ 1784168900))).method_1255(arg3, arg4);
                field931.method1770(Decryptor.method1945(XorDecoder.method1946("?4ÿ!?×ö#&íüx\tæÚ$5õï\r\u001b", -76389330 ^ 1121261689))).method_1251(arg5);
                field931.method1770(Decryptor.method1945(XorDecoder.method1946("\u0018\u000fÑvA'÷^\u001d��àMr\u0005äjJ\u001c±2e\u0004¾9", 1780607045 ^ 1856159085))).method_35657(((float) arg7.method1695()) / 255.0f, ((float) arg7.method1696()) / 255.0f, ((float) arg7.method1697()) / 255.0f, ((float) arg7.method1698()) / 255.0f);
                field931.method1770(Decryptor.method1945(XorDecoder.method1946("}|T]MÍGeO°T_V®/XY\r6bÄB", -380646995 ^ -1766400861))).method_35657(((float) arg8.method1695()) / 255.0f, ((float) arg8.method1696()) / 255.0f, ((float) arg8.method1697()) / 255.0f, ((float) arg8.method1698()) / 255.0f);
                field931.method1770(Decryptor.method1945(XorDecoder.method1946("R«ïEiËÂoAðç\u0019N ÍHdïd\u0014ï\u0014", 275155685 ^ 969544389))).method_35657(arg6, arg6, arg6, arg6);
                field931.method1770(Decryptor.method1945(XorDecoder.method1946(">w\u001eHA<º\u00071\u000eáEO\u0003\u001c2\u0006óMSPå", -119307621 ^ 546210022))).method_1251(0.800000011920929f);
                aY();
                class_287 var10 = RenderSystem.renderThreadTesselator().method_60827(class_5596.field_27382, class_290.field_1576);
                var10.method_22918(var9, arg1, arg2, 0.0f).method_1336(-1565985051 ^ -1565985254, 412080658 ^ 412080877, 64952606 ^ 64952801, -1826543194 ^ -1826543271);
                var10.method_22918(var9, arg1, arg2 + arg4, 0.0f).method_1336(-1343396543 ^ -1343396418, 759228495 ^ 759228592, -704969923 ^ -704969790, -659077227 ^ -659077270);
                var10.method_22918(var9, arg1 + arg3, arg2 + arg4, 0.0f).method_1336(689441001 ^ 689440790, 711162663 ^ 711162840, 1693254742 ^ 1693254825, -1087708608 ^ -1087708481);
                var10.method_22918(var9, arg1 + arg3, arg2, 0.0f).method_1336(1211058533 ^ 1211058586, -1803900119 ^ -1803899946, -648039295 ^ -648039298, 1775272306 ^ 1775272333);
                class_286.method_43433(var10.method_60800());
                aZ();
                arg0.method_22909();
                return;
            } else {
                method1742(arg0, arg1, arg2, arg3, arg4, aY.method1597(arg6), arg7);
                return;
            }
        }
    }

  public static void method1747(class_4587 arg0, float arg1, float arg2, float arg3, float arg4, float arg5, float arg6, bp arg7, aY arg8) { // было: a
        arg1 = arg1 - 0.30000001192092896f;
        arg2 = arg2 - 0.30000001192092896f;
        arg3 = arg3 + 0.6000000238418579f;
        arg4 = arg4 + 0.6000000238418579f;
        method1748(arg0, arg1, arg2, arg6, arg6, arg5, arg8, arg7, 0.0f);
        method1748(arg0, arg1 + arg3 - arg6, arg2, arg6, arg6, arg5, arg8, arg7, 1.0f);
        method1748(arg0, arg1, arg2 + arg4 - arg6, arg6, arg6, arg5, arg8, arg7, 2.0f);
        method1748(arg0, arg1 + arg3 - arg6, arg2 + arg4 - arg6, arg6, arg6, arg5, arg8, arg7, 3.0f);
    }

  public static void method1748(class_4587 arg0, float arg1, float arg2, float arg3, float arg4, float arg5, aY arg6, bp arg7, float arg8) { // было: a
        arg0.method_22903();
        Matrix4f var9 = arg0.method_23760().method_23761();
        float var10 = 0.800000011920929f;
        float var11 = 1.0f;
        field927.method1767();
        field927.method1770(Decryptor.method1945(XorDecoder.method1946("Ådö\u001f5ª`ËlÐ\u0010â7Å}Ø<Ö`ýb¢\u0014", -1760750078 ^ -1097722966))).method_1255(arg3, arg4);
        field927.method1770(Decryptor.method1945(XorDecoder.method1946("`Iõñ[)ØÛs\u0012ý­|B×üV\rÐ&\r ", 921133527 ^ -1420747835))).method_35657(arg6.method1606(), arg6.ab(), arg6.method1607(), arg6.aa());
        field927.method1770(Decryptor.method1945(XorDecoder.method1946("ç¬b.@\rÞêrV\u007f8ÅézD,R", 1785971084 ^ 90314792))).method_1255(var10, var11);
        field927.method1770(Decryptor.method1945(XorDecoder.method1946("\u0012\u001bqÊ%c89<.¥-y°ýT", -983468010 ^ -1398695586))).method_1251(arg5);
        field927.method1770(Decryptor.method1945(XorDecoder.method1946("J§=¹7³C©ÅB¿ºFá", 1215431670 ^ -1811290105))).method_1251(arg8);
        aY();
        float var12 = -var11 / 2.0f + var11 * 2.0f;
        float var13 = var11 / 2.0f + var11;
        float var14 = arg1 - var12 / 2.0f;
        float var15 = arg2 - var13 / 2.0f;
        float var16 = arg3 + var12;
        float var17 = arg4 + var13;
        class_287 var18 = RenderSystem.renderThreadTesselator().method_60827(class_5596.field_27382, class_290.field_1576);
        var18.method_22918(var9, var14, var15, 0.0f).method_39415(arg7.method1680());
        var18.method_22918(var9, var14, var15 + var17, 0.0f).method_39415(arg7.method1680());
        var18.method_22918(var9, var14 + var16, var15 + var17, 0.0f).method_39415(arg7.method1680());
        var18.method_22918(var9, var14 + var16, var15, 0.0f).method_39415(arg7.method1680());
        class_286.method_43433(var18.method_60800());
        aZ();
        arg0.method_22909();
    }

  public static void method1749(class_4587 arg0, class_2960 arg1, float arg2, float arg3, float arg4, float arg5, bp arg6) { // было: a
        arg0.method_22903();
        Matrix4f var7 = arg0.method_23760().method_23761();
        RenderSystem.setShader(class_10142.field_53880);
        RenderSystem.setShaderTexture(-1874271539 ^ -1874271539, arg1);
        aY();
        class_287 var8 = RenderSystem.renderThreadTesselator().method_60827(class_5596.field_27382, class_290.field_1575);
        var8.method_22918(var7, arg2, arg3, 0.0f).method_22913(0.0f, 0.0f).method_39415(arg6.method1680());
        var8.method_22918(var7, arg2, arg3 + arg5, 0.0f).method_22913(0.0f, 1.0f).method_39415(arg6.method1680());
        var8.method_22918(var7, arg2 + arg4, arg3 + arg5, 0.0f).method_22913(1.0f, 1.0f).method_39415(arg6.method1680());
        var8.method_22918(var7, arg2 + arg4, arg3, 0.0f).method_22913(1.0f, 0.0f).method_39415(arg6.method1680());
        class_286.method_43433(var8.method_60800());
        aZ();
        RenderSystem.setShaderTexture(1936157726 ^ 1936157726, 1469499412 ^ 1469499412);
        arg0.method_22909();
    }

  public static void method1750(class_4587 arg0, class_2960 arg1, float arg2, float arg3, float arg4, float arg5, dp arg6) { // было: a
        arg0.method_22903();
        Matrix4f var7 = arg0.method_23760().method_23761();
        RenderSystem.setShader(class_10142.field_53880);
        RenderSystem.setShaderTexture(1394524036 ^ 1394524036, arg1);
        aY();
        class_287 var8 = RenderSystem.renderThreadTesselator().method_60827(class_5596.field_27382, class_290.field_1575);
        var8.method_22918(var7, arg2, arg3, 0.0f).method_22913(0.0f, 0.0f).method_39415(arg6.method1661().method1680());
        var8.method_22918(var7, arg2, arg3 + arg5, 0.0f).method_22913(0.0f, 1.0f).method_39415(arg6.method1662().method1680());
        var8.method_22918(var7, arg2 + arg4, arg3 + arg5, 0.0f).method_22913(1.0f, 1.0f).method_39415(arg6.method1664().method1680());
        var8.method_22918(var7, arg2 + arg4, arg3, 0.0f).method_22913(1.0f, 0.0f).method_39415(arg6.method1663().method1680());
        class_286.method_43433(var8.method_60800());
        aZ();
        RenderSystem.setShaderTexture(-434694452 ^ -434694452, -287275970 ^ -287275970);
        arg0.method_22909();
    }

  public static void method1751(class_4587 arg0, class_2960 arg1, float arg2, float arg3, float arg4, float arg5, float arg6, float arg7, float arg8, float arg9, bp arg10) { // было: a
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        arg0.method_22903();
        int var11 = arg10.method1680();
        Matrix4f var12 = arg0.method_23760().method_23761();
        float var13 = arg2 + arg4;
        float var14 = arg3 + arg5;
        RenderSystem.setShader(class_10142.field_53880);
        RenderSystem.setShaderTexture(1534990637 ^ 1534990637, arg1);
        class_287 var15 = RenderSystem.renderThreadTesselator().method_60827(class_5596.field_27382, class_290.field_1575);
        var15.method_22918(var12, arg2, arg3, 0.0f).method_22913(arg6, arg8).method_39415(var11);
        var15.method_22918(var12, arg2, var14, 0.0f).method_22913(arg6, arg9).method_39415(var11);
        var15.method_22918(var12, var13, var14, 0.0f).method_22913(arg7, arg9).method_39415(var11);
        var15.method_22918(var12, var13, arg3, 0.0f).method_22913(arg7, arg8).method_39415(var11);
        class_286.method_43433(var15.method_60800());
        aZ();
        RenderSystem.setShaderTexture(222142460 ^ 222142460, 349887794 ^ 349887794);
        arg0.method_22909();
        RenderSystem.disableBlend();
    }

  public static void method1752(class_4587 arg0, ClassA163 arg1, float arg2, float arg3, float arg4, float arg5, bp arg6) { // было: a
        method1751(arg0, arg1.method1653(), arg2, arg3, arg4, arg5, 0.0f, 1.0f, 0.0f, 1.0f, arg6);
    }

  public static void method1753(class_4587 arg0, class_2960 arg1, float arg2, float arg3, float arg4, float arg5, aY arg6) { // было: a
        method1754(arg0, arg1, arg2, arg3, arg4, arg5, arg6, bp.field909);
    }

  public static void method1754(class_4587 arg0, class_2960 arg1, float arg2, float arg3, float arg4, float arg5, aY arg6, bp arg7) { // было: a
        arg0.method_22903();
        Matrix4f var8 = arg0.method_23760().method_23761();
        float var9 = 0.800000011920929f;
        field924.method1767();
        RenderSystem.setShaderTexture(-2056596259 ^ -2056596259, arg1);
        field924.method1770(Decryptor.method1945(XorDecoder.method1946("Ã\u0007\u001edVB\u001bÍ\u000f8käT-\u0006Þ_>\u001bû\u0001Jo", 300858854 ^ 1134156616))).method_1255(arg4, arg5);
        field924.method1770(Decryptor.method1945(XorDecoder.method1946("Òk\u0013Ké\u000b>aÁ0\u001b\u0017Î`1Fä/gj/m\u001a", 59655462 ^ 618536326))).method_35657(arg6.method1606(), arg6.ab(), arg6.method1607(), arg6.aa());
        field924.method1770(Decryptor.method1945(XorDecoder.method1946("×»û\u000f¡Ù,îýëw¬æ\u0019õþãe¤µs", 210954424 ^ 1109009964))).method_1251(var9);
        aY();
        float var10 = -var9 / 2.0f + var9 * 2.0f;
        float var11 = var9 / 2.0f + var9;
        float var12 = arg2 - var10 / 2.0f;
        float var13 = arg3 - var11 / 2.0f;
        float var14 = arg4 + var10;
        float var15 = arg5 + var11;
        class_287 var16 = RenderSystem.renderThreadTesselator().method_60827(class_5596.field_27382, class_290.field_1575);
        var16.method_22918(var8, var12, var13, 0.0f).method_22913(0.0f, 0.0f).method_39415(arg7.method1680());
        var16.method_22918(var8, var12, var13 + var15, 0.0f).method_22913(0.0f, 1.0f).method_39415(arg7.method1680());
        var16.method_22918(var8, var12 + var14, var13 + var15, 0.0f).method_22913(1.0f, 1.0f).method_39415(arg7.method1680());
        var16.method_22918(var8, var12 + var14, var13, 0.0f).method_22913(1.0f, 0.0f).method_39415(arg7.method1680());
        class_286.method_43433(var16.method_60800());
        aZ();
        RenderSystem.setShaderTexture(-356615906 ^ -356615906, 1226361319 ^ 1226361319);
        arg0.method_22909();
    }

  public static void method1755(class_4587 arg0, float arg1, float arg2, float arg3, float arg4, float arg5, aY arg6, bp arg7) { // было: d
        arg0.method_22903();
        Matrix4f var8 = arg0.method_23760().method_23761();
        field922.method1767();
        field922.method1770(Decryptor.method1945(XorDecoder.method1946("+«ÆsúØ¹%£¢É\u000cø·¤6ó¤¹\u0013­ÐÍ", -183504735 ^ 98712295))).method_1255(arg3, arg4);
        field922.method1770(Decryptor.method1945(XorDecoder.method1946("z´ÂnAÔïDiïÊ2f¿àcLð¶O<ð¼?", 2058219788 ^ 2016179716))).method_35657(arg6.method1606() * 3.0f, arg6.ab() * 3.0f, arg6.method1607() * 3.0f, arg6.aa() * 3.0f);
        field922.method1770(Decryptor.method1945(XorDecoder.method1946("yõ#\u000f¤×��@Ôå[\u0002ªè5[×íI\n¶»_", -255644227 ^ -1840919929))).method_1251(arg5);
        aY();
        float var9 = -arg5 / 2.0f + arg5 * 2.0f;
        float var10 = arg5 / 2.0f + arg5;
        float var11 = arg1 - var9 / 2.0f;
        float var12 = arg2 - var10 / 2.0f;
        float var13 = arg3 + var9;
        float var14 = arg4 + var10;
        class_287 var15 = RenderSystem.renderThreadTesselator().method_60827(class_5596.field_27382, class_290.field_1576);
        var15.method_22918(var8, var11, var12, 0.0f).method_39415(arg7.method1680());
        var15.method_22918(var8, var11, var12 + var14, 0.0f).method_39415(arg7.method1680());
        var15.method_22918(var8, var11 + var13, var12 + var14, 0.0f).method_39415(arg7.method1680());
        var15.method_22918(var8, var11 + var13, var12, 0.0f).method_39415(arg7.method1680());
        class_286.method_43433(var15.method_60800());
        aZ();
        arg0.method_22909();
    }

  public static void method1756(class_4587 arg0, float arg1, float arg2, float arg3, float arg4, float arg5, aY arg6, bp arg7) { // было: e
        // (пустое тело)
    }

  public static void method1757(class_4587 arg0, float arg1, float arg2, float arg3, float arg4, float arg5, aY arg6, bp arg7, boolean arg8, boolean arg9) { // было: a
        // (пустое тело)
    }

  public static void method1758(class_4587 arg0, float arg1, float arg2, float arg3, float arg4, int arg5) { // было: a
        ClassA158.method1500(arg0, arg1, arg2, arg3, arg4, arg5, dp.method1657(AngelVisuals.getInstance().getThemeManager().method481().method449(), AngelVisuals.getInstance().getThemeManager().method481().method449(), AngelVisuals.getInstance().getThemeManager().method481().method457(), AngelVisuals.getInstance().getThemeManager().method481().method457()));
    }

  public static void method1759(class_4587 arg0, float arg1, float arg2, float arg3, float arg4, float arg5, aY arg6, bp arg7) { // было: f
        class_276 var8 = class_310.method_1551().method_1522();
        class_6367 var9 = ((class_6367) field933.get());
        if (var9.field_1482 != var8.field_1482) {
            var9.method_1234(var8.field_1482, var8.field_1481);
        } else {
            if (var9.field_1481 != var8.field_1481) {
                var9.method_1234(var8.field_1482, var8.field_1481);
            }
        }
        arg0.method_22903();
        Matrix4f var10 = arg0.method_23760().method_23761();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableCull();
        var9.method_1235(2134937461 ^ 2134937461);
        var8.method_1237(var9.field_1482, var9.field_1481);
        var8.method_1235(2016369992 ^ 2016369992);
        RenderSystem.setShaderTexture(-29466425 ^ -29466425, var9.method_30277());
        field930.method1767();
        field930.method1770(Decryptor.method1945(XorDecoder.method1946("©4dñeÄ\u001b§<¾kg«\u0006´l¸\u001b2Ìo", 774834197 ^ 2094947793))).method_1255(arg3, arg4);
        field930.method1770(Decryptor.method1945(XorDecoder.method1946("sâ´\u0001H+`¹¼]oé\u000cE¦À 5¦ÊP", -791137654 ^ -1120935029))).method_35657(arg6.method1606(), arg6.ab(), arg6.method1607(), arg6.aa());
        field930.method1770(Decryptor.method1945(XorDecoder.method1946("~\u0011DÃ\u0008'fàGWT»\u0005)YÕ\\T\\©\r5\n¿", -285138155 ^ 1832273704))).method_1251(1.0f);
        field930.method1770(Decryptor.method1945(XorDecoder.method1946("k\u0015bkÀK\u001fØ²\\\u000b ÇV<®i*ÐË", 24363295 ^ -140600281))).method_1251(arg5);
        int var11 = mc.method_22683().method_4486();
        int var12 = mc.method_22683().method_4502();
        float var13 = arg1 / ((float) var11);
        float var14 = (((float) var12) - arg2 - arg4) / ((float) var12);
        float var15 = arg3 / ((float) var11);
        float var16 = arg4 / ((float) var12);
        class_287 var17 = RenderSystem.renderThreadTesselator().method_60827(class_5596.field_27382, class_290.field_1576);
        var17.method_22918(var10, arg1, arg2, 0.0f).method_39415(arg7.method1680());
        var17.method_22918(var10, arg1, arg2 + arg4, 0.0f).method_39415(arg7.method1680());
        var17.method_22918(var10, arg1 + arg3, arg2 + arg4, 0.0f).method_39415(arg7.method1680());
        var17.method_22918(var10, arg1 + arg3, arg2, 0.0f).method_39415(arg7.method1680());
        class_286.method_43433(var17.method_60800());
        RenderSystem.setShaderTexture(-2131986178 ^ -2131986178, -55719570 ^ -55719570);
        RenderSystem.enableCull();
        RenderSystem.disableBlend();
        arg0.method_22909();
    }

  public static void method1760(class_4587 arg0, class_287 arg1, double arg2, double arg3, double arg4, double arg5, double arg6, bp arg7) { // было: a
        Matrix4f var13 = arg0.method_23760().method_23761();
        arg1.method_22918(var13, ((float) arg2), ((float) (arg3 + arg6)), ((float) arg4)).method_22913(0.0f, 1.0f).method_39415(arg7.method1680());
        arg1.method_22918(var13, ((float) (arg2 + arg5)), ((float) (arg3 + arg6)), ((float) arg4)).method_22913(1.0f, 1.0f).method_39415(arg7.method1680());
        arg1.method_22918(var13, ((float) (arg2 + arg5)), ((float) arg3), ((float) arg4)).method_22913(1.0f, 0.0f).method_39415(arg7.method1680());
        arg1.method_22918(var13, ((float) arg2), ((float) arg3), ((float) arg4)).method_22913(0.0f, 0.0f).method_39415(arg7.method1680());
    }

  public static void method1761(class_4587 arg0, class_2960 arg1, double arg2, double arg3, double arg4, double arg5, double arg6, bp arg7) { // было: a
        RenderSystem.setShaderTexture(-1442099565 ^ -1442099565, arg1);
        class_287 var13 = RenderSystem.renderThreadTesselator().method_60827(class_5596.field_27382, class_290.field_1575);
        Matrix4f var14 = arg0.method_23760().method_23761();
        var13.method_22918(var14, ((float) arg2), ((float) (arg3 + arg6)), ((float) arg4)).method_22913(0.0f, 1.0f).method_39415(arg7.method1680());
        var13.method_22918(var14, ((float) (arg2 + arg5)), ((float) (arg3 + arg6)), ((float) arg4)).method_22913(1.0f, 1.0f).method_39415(arg7.method1680());
        var13.method_22918(var14, ((float) (arg2 + arg5)), ((float) arg3), ((float) arg4)).method_22913(1.0f, 0.0f).method_39415(arg7.method1680());
        var13.method_22918(var14, ((float) arg2), ((float) arg3), ((float) arg4)).method_22913(0.0f, 0.0f).method_39415(arg7.method1680());
        class_286.method_43433(var13.method_60800());
    }

  public static void method1762(class_4587 arg0, class_2960 arg1, float arg2, float arg3, float arg4, aY arg5, bp arg6) { // было: a
        method1764(arg0, arg1, arg2, arg3, arg4, arg4, arg5, arg6, 0.125f, 0.125f, 0.25f, 0.25f);
    }

  private static void method1763(class_4587 arg0, class_2960 arg1, float arg2, float arg3, float arg4, aY arg5, bp arg6) { // было: b
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        method1764(arg0, arg1, arg2, arg3, arg4, arg4, arg5, arg6, 0.625f, 0.125f, 0.75f, 0.25f);
        RenderSystem.disableBlend();
    }

  public static void method1764(class_4587 arg0, class_2960 arg1, float arg2, float arg3, float arg4, float arg5, aY arg6, bp arg7, float arg8, float arg9, float arg10, float arg11) { // было: a
        arg0.method_22903();
        Matrix4f var12 = arg0.method_23760().method_23761();
        float var13 = 0.800000011920929f;
        field924.method1767();
        RenderSystem.setShaderTexture(-1599091805 ^ -1599091805, arg1);
        field924.method1770(Decryptor.method1945(XorDecoder.method1946("\u0001~HÃP\"7\tXG¼RM*Y^7£\u0007*C", -1645201849 ^ -471378255))).method_1255(arg4, arg5);
        field924.method1770(Decryptor.method1945(XorDecoder.method1946("Äòs2ÿ^\u0018×©{nØùQ?ò¶\u0007\u0013¶\rc", -151030934 ^ -1462783268))).method_35657(arg6.method1606(), arg6.ab(), arg6.method1607(), arg6.aa());
        field924.method1770(Decryptor.method1945(XorDecoder.method1946("ùÇ\u000e$ñ,\u0007À\u001e\\ÿ\u00132Û\u0016Nã@X", -378343383 ^ -1945156973))).method_1251(var13);
        aY();
        float var14 = -var13 / 2.0f + var13 * 2.0f;
        float var15 = var13 / 2.0f + var13;
        float var16 = arg2 - var14 / 2.0f;
        float var17 = arg3 - var15 / 2.0f;
        float var18 = arg4 + var14;
        float var19 = arg5 + var15;
        class_287 var20 = RenderSystem.renderThreadTesselator().method_60827(class_5596.field_27382, class_290.field_1575);
        var20.method_22918(var12, var16, var17, 0.0f).method_22913(arg8, arg9).method_39415(arg7.method1680());
        var20.method_22918(var12, var16, var17 + var19, 0.0f).method_22913(arg8, arg11).method_39415(arg7.method1680());
        var20.method_22918(var12, var16 + var18, var17 + var19, 0.0f).method_22913(arg10, arg11).method_39415(arg7.method1680());
        var20.method_22918(var12, var16 + var18, var17, 0.0f).method_22913(arg10, arg9).method_39415(arg7.method1680());
        class_286.method_43433(var20.method_60800());
        aZ();
        RenderSystem.setShaderTexture(-404198684 ^ -404198684, 940952249 ^ 940952249);
        arg0.method_22909();
    }

  public static void aY() {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
    }

  public static void aZ() {
        RenderSystem.disableBlend();
    }

    @Generated
  private aE() { // было: <init>
        super();
        throw new UnsupportedOperationException(Decryptor.method1945(XorDecoder.method1946("(4B\u0008´\"O\u001f7ô&¼\u0011õ]\u0004ô\u0013«)©1¾&¾\u0011ÿ1ñXP¢\u0010§\u0015³Z\u0008/-\u001d2 ;Uö\u0001¯U\u0002¯\u0012!ò1õ\r«K\u0019Sö\u001d°'÷(û]", -211885373 ^ -1818719481)));
    }

  private static class_6367 method1765() { // было: a
        return new class_6367(557626471 ^ 557626343, 1409056362 ^ 1409055338, 748166126 ^ 748166126);
    }

  private static int ok(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int ol(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int om(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}