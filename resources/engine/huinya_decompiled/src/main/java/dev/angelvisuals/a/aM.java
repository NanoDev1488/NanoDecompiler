// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.am
package dev.angelvisuals.a;

import com.darkmagician6.eventapi.EventTarget;
import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.angelvisuals.AngelVisuals;
import dev.angelvisuals.a.aZ;
import dev.angelvisuals.a.bA;
import dev.angelvisuals.a.bI;
import dev.angelvisuals.a.bl;
import dev.angelvisuals.a.bp;
import dev.angelvisuals.a.cK;
import dev.angelvisuals.a.cZ;
import dev.angelvisuals.a.ch;
import dev.angelvisuals.a.dD;
import net.minecraft.class_10142;
import net.minecraft.class_2338;
import net.minecraft.class_239;
import net.minecraft.class_239.class_240;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_293.class_5596;
import net.minecraft.class_3965;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_4587.class_4665;
import net.minecraft.class_4588;
import net.minecraft.class_757;
import org.joml.Matrix4f;

@bI(name = "BlockOverlay", a = "RENDER", I = "3D обводка блока в фокусе цветом темы")
public class am extends cK {

    // ---- поля ----
  public static final am field376; // было: a
  public final aZ field377; // было: d
  public final bA field378; // было: s
  public final bA field379; // было: t
  public final bA field380; // было: u
  public final bA field381; // было: v
  public final bA field382; // было: w
  public final bA field383; // было: x
  public final bA field384; // было: y
  private static final String gc = "// class hierarchy hashing: ENABLED";
  private static final String gd = "// === DO NOT TOUCH ===";
  private static final String ge = "// this jar protected by JoinerObfuscator";
  private static final String gf = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final String gg = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final int dv = -1858617774;
  private static final int dw = 331253042;
  private static final int dx = 303318431;
  private static final byte[] aq;

    static {
        aq = "\"*LK|xD}5k\\K1PAm\\2ct`kZj'jtouDh(LXrs`[9I7|xiem\"~-eBx3 ;Z<j X)Ka,9*trH1KfF_ ,^LxJQpNh9waU32YKVZL%*:HK=o'B}pgm:\\JkfA^Dw4~Hz }7 Brx=N(v?(p$bs(_q2!Dc\"g<tdSz<fRAb|FC`],U#HURg}r%/Hf5bn\"%U]R3H|nJs=o@\"~o?A)L=#\\g3iE6SE sd>Uphp$sQ9x){]2\"m\\)mOkt>.eSrjE$$J?8Oq37VM!d1h".getBytes("ISO-8859-1");
        field376 = new am();
    }

  public am() { // было: <init>
        super();
        String[] __obj1 = new String[-909608572 ^ -909608576];
        __obj1[-859213337 ^ -859213337] = Decryptor.method1945(XorDecoder.method1946("\\&Ã}[-Z:6þD\u0002\u001dL(\u0011_\u0008#\u0003", 1849160655 ^ 1351071653));
        __obj1[-1494454327 ^ -1494454328] = Decryptor.method1945(XorDecoder.method1946("Tôf\u0017Sÿ>02ä[.\nÏ-& Ã\"5��ñ(i", -1533907153 ^ -259524275));
        __obj1[-1089632808 ^ -1089632806] = Decryptor.method1945(XorDecoder.method1946("l\u0012{a\u0011\u007fl\u0008\u0008UuaIva_4\u0005Õ", -1816849692 ^ 2072859107));
        __obj1[-731874890 ^ -731874891] = Decryptor.method1945(XorDecoder.method1946("p/DI@!mwc\n]:9<B)\"\u0010Yh{)2,", 1017068041 ^ 764432896));
        field377 = new aZ(Decryptor.method1945(XorDecoder.method1946("\u0001&T\u0018¸\u0002\"úÍK*Ê>\u0008\u0010\u000fñÁ", -1692439527 ^ 1741890173)), __obj1);
        field378 = new bA(Decryptor.method1945(XorDecoder.method1946(" v±©i¤_¸\\¹¢CÂQ¢øxÁýUÇ��§¦P³¤lÉ", -795685082 ^ 615186928)), 1.2000000476837158f, 0.10000000149011612f, 5.0f, 0.10000000149011612f, () -> method765());
        field379 = new bA(Decryptor.method1945(XorDecoder.method1946("\u0001\u0012°$\n¼Ó<\nà@\u001dëÜBhµÕ d³\u00186è0\u0012½Ü8*éàG\u001bì\u0002i¯", 1682600932 ^ -1047384176)), 1.0f, 1.0f, 3.0f, 0.10000000149011612f, () -> method764());
        field380 = new bA(Decryptor.method1945(XorDecoder.method1946("Ú¦Ù!¦ªÀUé]­õ¾bø! óûuâ_ßÖd×öqôÜg¢öÏ-", -1719217986 ^ -1995959728)), 1.2000000476837158f, 0.10000000149011612f, 5.0f, 0.10000000149011612f, () -> method763());
        field381 = new bA(Decryptor.method1945(XorDecoder.method1946("áÙß\u001bÒîì,ôÂÖ3æÆ(ÃÀÅ,Éï?¯éöhÄØÍ\u0016Äìð\rïé2ÒÅd", 612898671 ^ 2100215791)), 1.0f, 0.0f, 5.0f, 0.10000000149011612f, () -> method762());
        field382 = new bA(Decryptor.method1945(XorDecoder.method1946("÷jPÚÉJ~Ô\\'ÙáHOøïU]ýì;\"", -1420916182 ^ -1270345317)), 0.6000000238418579f, 0.0f, 1.0f, 0.009999999776482582f, () -> method761());
        field383 = new bA(Decryptor.method1945(XorDecoder.method1946("\u001eÜ'\u0017ÖÅ\u0004%¬Õ*?ÞÜb\u001dÁÊ<\u000eûö*~öþ\t\u000eïä!wËõ\u0017\u0006Öâ#\u000eñóp", 1160671133 ^ 144504786)), 1.0f, 0.0f, 1.0f, 0.05000000074505806f, () -> method760());
        field384 = new bA(Decryptor.method1945(XorDecoder.method1946("C\u000eäÏ\u0008\u001cä2:ÅÏ%\u000eÌ*6øì8\u0017ú<8äô\u0003\u0001ø6\tìH��@zé", -452899400 ^ 1521287880)), 0.014999999664723873f, 0.0010000000474974513f, 0.05000000074505806f, 0.0010000000474974513f, () -> method759());
    }

  public void method755() { // было: k
        cZ.method1845().aU();
        super.method611();
    }

    @EventTarget
  private void method756(dD arg0) { // было: d
        if (mc.field_1765 == null) {
            return;
        } else {
            if (mc.field_1765.method_17783() == class_240.field_1332) {
                class_3965 var2 = ((class_3965) mc.field_1765);
                class_2338 var3 = var2.method_17777();
                if (field377.method696(Decryptor.method1945(XorDecoder.method1946("\u001cyqj\u0011zui\u001cc\u0002v%\u001ekp9\u001dka/_\u000f?", 883745608 ^ 916376383)))) {
                    method758(arg0, var3);
                } else {
                    if (!field377.method696(Decryptor.method1945(XorDecoder.method1946("\u0001eø1LÆ\u0012³|HcS©xÙ\n\u0013", 1368499672 ^ -239111008)))) {
                        method757(arg0, var3);
                    } else {
                        method758(arg0, var3);
                    }
                }
                return;
            } else {
                return;
            }
        }
    }

  private void method757(dD arg0, class_2338 arg1) { // было: a
        class_4587 var3 = arg0.method324();
        class_243 var4 = mc.field_1773.method_19418().method_19326();
        bl var5 = AngelVisuals.getInstance().getThemeManager().method481();
        int var6 = 1136621394 ^ 1136621542;
        int var7 = var5.method449().method1686(var6).method1680();
        int var8 = var5.method449().method1686(-689105358 ^ -689105382).method1680();
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(-800879221 ^ -800878967, -2027230748 ^ -2027230489);
        RenderSystem.disableCull();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(-120126361 ^ -120126361);
        RenderSystem.setShader(class_10142.field_53876);
        RenderSystem.lineWidth(2.5f);
        class_289 var9 = class_289.method_1348();
        var3.method_22903();
        var3.method_22904(((double) arg1.method_10263()) - var4.field_1352, ((double) arg1.method_10264()) - var4.field_1351, ((double) arg1.method_10260()) - var4.field_1350);
        var3.method_22905(1.0019999742507935f, 1.0019999742507935f, 1.0019999742507935f);
        Matrix4f var10 = var3.method_23760().method_23761();
        class_287 var11 = var9.method_60827(class_5596.field_27382, class_290.field_1576);
        var11.method_22918(var10, 0.0f, 0.0f, 0.0f).method_39415(var8);
        var11.method_22918(var10, 1.0f, 0.0f, 0.0f).method_39415(var8);
        var11.method_22918(var10, 1.0f, 0.0f, 1.0f).method_39415(var8);
        var11.method_22918(var10, 0.0f, 0.0f, 1.0f).method_39415(var8);
        var11.method_22918(var10, 0.0f, 1.0f, 0.0f).method_39415(var8);
        var11.method_22918(var10, 0.0f, 1.0f, 1.0f).method_39415(var8);
        var11.method_22918(var10, 1.0f, 1.0f, 1.0f).method_39415(var8);
        var11.method_22918(var10, 1.0f, 1.0f, 0.0f).method_39415(var8);
        var11.method_22918(var10, 0.0f, 0.0f, 0.0f).method_39415(var8);
        var11.method_22918(var10, 0.0f, 1.0f, 0.0f).method_39415(var8);
        var11.method_22918(var10, 1.0f, 1.0f, 0.0f).method_39415(var8);
        var11.method_22918(var10, 1.0f, 0.0f, 0.0f).method_39415(var8);
        var11.method_22918(var10, 1.0f, 0.0f, 0.0f).method_39415(var8);
        var11.method_22918(var10, 1.0f, 1.0f, 0.0f).method_39415(var8);
        var11.method_22918(var10, 1.0f, 1.0f, 1.0f).method_39415(var8);
        var11.method_22918(var10, 1.0f, 0.0f, 1.0f).method_39415(var8);
        var11.method_22918(var10, 1.0f, 0.0f, 1.0f).method_39415(var8);
        var11.method_22918(var10, 1.0f, 1.0f, 1.0f).method_39415(var8);
        var11.method_22918(var10, 0.0f, 1.0f, 1.0f).method_39415(var8);
        var11.method_22918(var10, 0.0f, 0.0f, 1.0f).method_39415(var8);
        var11.method_22918(var10, 0.0f, 0.0f, 1.0f).method_39415(var8);
        var11.method_22918(var10, 0.0f, 1.0f, 1.0f).method_39415(var8);
        var11.method_22918(var10, 0.0f, 1.0f, 0.0f).method_39415(var8);
        var11.method_22918(var10, 0.0f, 0.0f, 0.0f).method_39415(var8);
        class_286.method_43433(var11.method_60800());
        class_287 var12 = var9.method_60827(class_5596.field_29344, class_290.field_1576);
        var12.method_22918(var10, 0.0f, 0.0f, 0.0f).method_39415(var7);
        var12.method_22918(var10, 1.0f, 0.0f, 0.0f).method_39415(var7);
        var12.method_22918(var10, 1.0f, 0.0f, 0.0f).method_39415(var7);
        var12.method_22918(var10, 1.0f, 0.0f, 1.0f).method_39415(var7);
        var12.method_22918(var10, 1.0f, 0.0f, 1.0f).method_39415(var7);
        var12.method_22918(var10, 0.0f, 0.0f, 1.0f).method_39415(var7);
        var12.method_22918(var10, 0.0f, 0.0f, 1.0f).method_39415(var7);
        var12.method_22918(var10, 0.0f, 0.0f, 0.0f).method_39415(var7);
        var12.method_22918(var10, 0.0f, 1.0f, 0.0f).method_39415(var7);
        var12.method_22918(var10, 1.0f, 1.0f, 0.0f).method_39415(var7);
        var12.method_22918(var10, 1.0f, 1.0f, 0.0f).method_39415(var7);
        var12.method_22918(var10, 1.0f, 1.0f, 1.0f).method_39415(var7);
        var12.method_22918(var10, 1.0f, 1.0f, 1.0f).method_39415(var7);
        var12.method_22918(var10, 0.0f, 1.0f, 1.0f).method_39415(var7);
        var12.method_22918(var10, 0.0f, 1.0f, 1.0f).method_39415(var7);
        var12.method_22918(var10, 0.0f, 1.0f, 0.0f).method_39415(var7);
        var12.method_22918(var10, 0.0f, 0.0f, 0.0f).method_39415(var7);
        var12.method_22918(var10, 0.0f, 1.0f, 0.0f).method_39415(var7);
        var12.method_22918(var10, 1.0f, 0.0f, 0.0f).method_39415(var7);
        var12.method_22918(var10, 1.0f, 1.0f, 0.0f).method_39415(var7);
        var12.method_22918(var10, 1.0f, 0.0f, 1.0f).method_39415(var7);
        var12.method_22918(var10, 1.0f, 1.0f, 1.0f).method_39415(var7);
        var12.method_22918(var10, 0.0f, 0.0f, 1.0f).method_39415(var7);
        var12.method_22918(var10, 0.0f, 1.0f, 1.0f).method_39415(var7);
        class_286.method_43433(var12.method_60800());
        var3.method_22909();
        RenderSystem.lineWidth(1.0f);
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask(-264856817 ^ -264856818);
        RenderSystem.enableCull();
        RenderSystem.disableBlend();
    }

  private void method758(dD arg0, class_2338 arg1) { // было: b
        cZ.method1845().method1846(arg0, arg1, this);
    }

  private Boolean method759() { // было: f
        return Boolean.valueOf(field377.method696(Decryptor.method1945(XorDecoder.method1946("±ñ©ûÿÅ¢Ô°øâ¯ãÎ´Úº÷ß", 725546355 ^ -1998830661))));
    }

  private Boolean method760() { // было: g
        int __stk1;
        __stk1 = field377.method696(Decryptor.method1945(XorDecoder.method1946("PFJ ]EN#P\\9<i!P:u\"P+c`4u", 1761681066 ^ 554174865))) ? -1330212506 ^ -1330212505 : !field377.method696(Decryptor.method1945(XorDecoder.method1946("£\u001dmÎ\u0013Dð°8t½ê\u000ek®ñ\"pï¨\u001b\u001b«", 1279094805 ^ -635753777))) ? 1467628348 ^ 1467628348 : -1330212506 ^ -1330212505;
        return Boolean.valueOf(__stk1);
    }

  private Boolean method761() { // было: h
        int __stk1;
        __stk1 = field377.method696(Decryptor.method1945(XorDecoder.method1946("Á¡h4Ì¢l7Á»\u001b(øÆr.äÅr?ò\u0016a", -16302619 ^ -1557344945))) ? 1876948564 ^ 1876948565 : !field377.method696(Decryptor.method1945(XorDecoder.method1946("ÅÑÕnõßüPÖôÌ\u001dÂÓ\u000eîÈOÎ×£\u000b", -38149758 ^ -886606018))) ? 1471979859 ^ 1471979859 : 1876948564 ^ 1876948565;
        return Boolean.valueOf(__stk1);
    }

  private Boolean method762() { // было: i
        int __stk1;
        __stk1 = field377.method696(Decryptor.method1945(XorDecoder.method1946("h\u0011D¦e\u0012@¥h\u000b7ºQv^¼Mu^­[7:ó", 1875197426 ^ -1581089295))) ? -1514740689 ^ -1514740690 : !field377.method696(Decryptor.method1945(XorDecoder.method1946("\u0001J¦1c\u0012¨SÕHLÆS²W\n<Ã", 246716418 ^ -256552326))) ? -1858554423 ^ -1858554423 : -1514740689 ^ -1514740690;
        return Boolean.valueOf(__stk1);
    }

  private Boolean method763() { // было: j
        int __stk1;
        __stk1 = field377.method696(Decryptor.method1945(XorDecoder.method1946("ð»\u00086ý¸\u000c5ð¡{*ÉÜ\u0012,Õß\u0012=Ãvc", 865688998 ^ 1842526013))) ? -226395812 ^ -226395811 : !field377.method696(Decryptor.method1945(XorDecoder.method1946("*þMt\u001aðdJ9ÛT\u0007cíK\u0014xÁPU!ø;\u0011", -1778285212 ^ -1173930953))) ? -1371842100 ^ -1371842100 : -226395812 ^ -226395811;
        return Boolean.valueOf(__stk1);
    }

  private Boolean method764() { // было: k
        int __stk1;
        __stk1 = field377.method696(Decryptor.method1945(XorDecoder.method1946("\u001aÚÈ\u0017ÙÌ\u001aÀ»#½Ò?¾Ò)ü¶Í", -1580195725 ^ 1364962050))) ? 1697364635 ^ 1697364634 : !field377.method696(Decryptor.method1945(XorDecoder.method1946("Ê%M¯Ä\u000csï<>ÖÙ#-Íõ8lÌS(", -29857679 ^ -346626665))) ? 1367021589 ^ 1367021589 : 1697364635 ^ 1697364634;
        return Boolean.valueOf(__stk1);
    }

  private Boolean method765() { // было: l
        int __stk1;
        __stk1 = field377.method696(Decryptor.method1945(XorDecoder.method1946("ã��}\tî\u0003y\nã\u001a\u000e\u0015Úgg\u0013Ædg\u0002Ð&\u0003\\", -643587612 ^ -1197608852))) ? 1170924634 ^ 1170924635 : !field377.method696(Decryptor.method1945(XorDecoder.method1946("ñõ®±ÁûâÐ·Â¸æ¨Ñ£Ê³úóØÔ", -61973125 ^ 363589619))) ? -98563251 ^ -98563251 : 1170924634 ^ 1170924635;
        return Boolean.valueOf(__stk1);
    }

  private static int cW(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int cX(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int cY(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}