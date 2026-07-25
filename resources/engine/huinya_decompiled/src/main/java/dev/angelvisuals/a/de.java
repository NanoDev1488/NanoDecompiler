// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.dE
package dev.angelvisuals.a;

import com.darkmagician6.eventapi.EventTarget;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.AngelVisuals;
import dev.angelvisuals.a.ClassA150_ClassA151;
import dev.angelvisuals.a.ClassA158;
import dev.angelvisuals.a.ClassA64;
import dev.angelvisuals.a.ClassA78;
import dev.angelvisuals.a.ClassA88_ClassA89;
import dev.angelvisuals.a.ClassA90;
import dev.angelvisuals.a.aZ;
import dev.angelvisuals.a.ad;
import dev.angelvisuals.a.ap;
import dev.angelvisuals.a.ay;
import dev.angelvisuals.a.ay_ClassA82;
import dev.angelvisuals.a.az;
import dev.angelvisuals.a.bF;
import dev.angelvisuals.a.bI;
import dev.angelvisuals.a.bK;
import dev.angelvisuals.a.bk;
import dev.angelvisuals.a.bx;
import dev.angelvisuals.a.cC;
import dev.angelvisuals.a.cK;
import dev.angelvisuals.a.cT;
import dev.angelvisuals.a.cs;
import dev.angelvisuals.a.dJ;
import dev.angelvisuals.a.dk;
import dev.angelvisuals.a.dn;
import dev.angelvisuals.a.do;
import dev.angelvisuals.a.dz;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import lombok.Generated;
import net.minecraft.class_1041;
import net.minecraft.class_310;
import net.minecraft.class_408;
import net.minecraft.class_5611;
import org.joml.Vector2f;

@bI(name = "HUD", a = "RENDER", I = "Интерфейс Клиента")
public final class dE extends cK {

    // ---- поля ----
  public static final dE field472; // было: a
  private final aZ field473; // было: j
  private final ClassA90 field474; // было: d
  private final List field475; // было: P
  private final List field476; // было: Q
  private ay field477; // было: a
  private float dK;
  private float dL;
   long field478; // было: N
  private static final String Fd = "// class hierarchy hashing: ENABLED";
  private static final String Fe = "// === DO NOT TOUCH ===";
  private static final String Ff = "// you are reading machine-generated garbage";
  private static final String Fg = "// flow obfuscation: ENABLED";
  private static final String Fh = "// you are reading machine-generated garbage";
  private static final int sq = -729487738;
  private static final int sr = 1969792933;
  private static final int ss = -725623272;
  private static final byte[] eP;

    static {
        eP = "T4bMWgjL+aip[Ec)K:fLJKQ;+hPq4+OARsr7h[)TX\\H:}aV3*e!eg:SK|zQEwIJ.{I?`fDa[9EV\\VhXnG>;Xo^r5(*Dab2V@G~W3lEjfJ&'16~{]g<n\"\\u]uhkdb$YU?rchIM2TH[w(w~!L' 6tO!(L1)'nDb}t`70RGUZ.-46,:QaRZOG?!Av;3 Xv/O8`8J@?+()K^{JECq*]%b pVr/^$ol7v13x#WK>xB9QZ-CCXUeS[2s)l{QSL(pg#WQ,z".getBytes("ISO-8859-1");
        field472 = new dE();
    }

  private dE() { // было: <init>
        super();
        String[] __obj1 = new String[-1702441301 ^ -1702441303];
        __obj1[719507969 ^ 719507969] = Decryptor.method1945(XorDecoder.method1946("é @ã¬«C×ïpÁÐ´rñ®RÐ«\u0015", -2103643579 ^ 884214495));
        __obj1[329035006 ^ 329035007] = Decryptor.method1945(XorDecoder.method1946("Ê6»¤\r÷¬ø\u0016ö5ØnÓµÃ0Þ", 866434837 ^ -803411814));
        field473 = new aZ(Decryptor.method1945(XorDecoder.method1946("²,û~èqµ^©\nð%\u000bûy¹\u0019¯/¶\u0002ÿ!", 509287075 ^ 43604345)), __obj1);
        field474 = ClassA90.method713(Decryptor.method1945(XorDecoder.method1946(" ´\n1.\u0011\u001e\u001eí7\u0008,¨&J\u001aæ\u001bJ(æc\n\u0008ôaL\u001d\u001aI:e+\u0001»\u0011?\u0012±%D", -2041852004 ^ -15134505)), List.of(Decryptor.method1945(XorDecoder.method1946("é¤ÆËæ½¡ÒÄ¼É¤¸çâ×Ä±ÇÞÙõúíÔ¢ÅþÐÙ", 1555064951 ^ -1206411036)), Decryptor.method1945(XorDecoder.method1946("¡h¶Ö_¶¥ÿ\u0019ñlÙ��¾ì¬XÎà", 1187279997 ^ -1690870811)), Decryptor.method1945(XorDecoder.method1946("\t½z\u000eÈz\r§ôx(´ä.2¬Ö%1½Ç&\u001cÎß\u0017\u0014Ì\u001djß|\u000bàunÑÿp", -151060870 ^ -1152384989)), Decryptor.method1945(XorDecoder.method1946("VÇð5RÁ8dçsLùç\u000ffùó$UÀ§'{å¥ugýÎttÑ1@í¥sVÍÓ\u007f", -1527297807 ^ -429785133)), Decryptor.method1945(XorDecoder.method1946("íù��<Ã®3\u0003Ò\u000b^ÕÞ Fý\u0015,ã¬.^ÒÚq\u0006Ò¯\u0018Zôþ0\u001aÀò\n\u000cù©\u0018H", -930621311 ^ -1111079882)), Decryptor.method1945(XorDecoder.method1946("MPïjPÌN\u007fë·G{îÆkrâ¿cEÏ", 1412164313 ^ -1500840234)), Decryptor.method1945(XorDecoder.method1946("6t¸(f:¿\u001a\u001ak¶h\u00070º!\u001f\u0010\u0002`3ª5\"58\u001f\u0010\">\u0001´\u0014\u00135©?6:l", 1185753343 ^ 392262575)), Decryptor.method1945(XorDecoder.method1946("%z\u000b¦yK\u0005¦@f%²@['[E~åt#q\\>\rbi'}C\u0013 =\u007f\u0011ìh0{", -1933345706 ^ -892637565))));
        field475 = new ArrayList();
        field476 = new ArrayList();
        field477 = null;
        field478 = -7270680855814029985L ^ -7270680855814029985L;
        field475.add(new dk(Decryptor.method1945(XorDecoder.method1946("\u0018ù*ùTÿ(ñAØ\u001b©tÀ\u000c£\u007f÷\u001ajËcü", -1669771972 ^ 1562892049)), 0.0f, 0.0f, 960.0f, 495.5f, 10.0f, 10.0f, ay_ClassA82.field302, 1082233687 ^ 1082233687));
        field475.add(new ClassA78(Decryptor.method1945(XorDecoder.method1946("nIN7ÁÍhuËÒLV¿\u0010|PqÎÛ", 1765295348 ^ -1882504494)), 0.0f, 0.0f, 960.0f, 495.5f, 119.15234375f, 73.0f, ay_ClassA82.field302, -292105535 ^ -292105535));
        field475.add(new az(Decryptor.method1945(XorDecoder.method1946("©+ÙÀ¥w«§{°d¿¾\u001e(ÏÌ", -1356024315 ^ 1591671240)), 0.0f, 0.0f, 960.0f, 495.5f, 10.0f, 73.0f, ay_ClassA82.field302, -2118988137 ^ -2118988137));
        ClassA64 var1 = new ClassA64(Decryptor.method1945(XorDecoder.method1946("Ó\twOÍ54[ù%SnÎ\u001ctj\"[Eé4<\u0010", -720780784 ^ -133656149)), 0.0f, 0.0f, 960.0f, 495.5f, 0.0f, 50.0f, ay_ClassA82.field306);
        field475.add(var1);
        AngelVisuals.getInstance().getNotifyManager().method426(var1);
        field475.add(new dn(Decryptor.method1945(XorDecoder.method1946("Yx0sq\u000fCb\u001fha^|Qpr}\u000b4Odk8\u0017", -1715718600 ^ -1279718392)), 0.0f, 0.0f, 960.0f, 495.5f, 10.0f, 41.5f, ay_ClassA82.field302));
        field475.add(new cC(Decryptor.method1945(XorDecoder.method1946(" ä ½¦áÍËÃªÂÈ¨", 644766025 ^ -2097443452)), 349.0f, 0.0f, 960.0f, 495.5f, -122.0f, 73.0f, ay_ClassA82.field304, -1678235498 ^ -1678235498));
        field475.add(new bk(Decryptor.method1945(XorDecoder.method1946("î9j\u000eÍZq#Ë^q6/h)ð\u000eI)Û*9\u007f", -1320883878 ^ -213875975)), 166.5f, 128.5f, 960.0f, 495.5f, 0.0f, 31.75f, ay_ClassA82.field306));
        field475.add(new cT(Decryptor.method1945(XorDecoder.method1946("\u00151­=*(\u001f@5\nA\nõ.%&ô\u001f 0ÿp", -889662423 ^ -2026204325)), 0.0f, 0.0f, 960.0f, 495.5f, -10.0f, 10.0f, ay_ClassA82.field304));
        field476.add(new dk(Decryptor.method1945(XorDecoder.method1946("7I>0jM/H\u0004«\u0010O<¬1t@ý7ZHô", -121561715 ^ 833892584)), 0.0f, 0.0f, 960.0f, 495.5f, 5.0f, 5.0f, ay_ClassA82.field302, 403373865 ^ 403373864));
        field476.add(new cC(Decryptor.method1945(XorDecoder.method1946("'\u0015¢<5×¤\u0002A±\u0016\u0013Ó¾\u00085Ó±?'ßÉ", 1906605014 ^ -2059006576)), 0.0f, 0.0f, 960.0f, 495.5f, 5.0f, 30.0f, ay_ClassA82.field302, 1665703063 ^ 1665703062));
        field476.add(new ClassA78(Decryptor.method1945(XorDecoder.method1946("\u0005qF2\u0016xF\u0010\u001c]Ed\u0016W51\rl5#+b=n", -1194723363 ^ -339090798)), 0.0f, 0.0f, 960.0f, 495.5f, 5.0f, 100.0f, ay_ClassA82.field302, 1143182929 ^ 1143182928));
        field476.add(new bk(Decryptor.method1945(XorDecoder.method1946("n¸­òoÉ®R½Ç.®ò[¯­ÑD¯ú", 339921300 ^ -1266337899)), 0.0f, 0.0f, 960.0f, 495.5f, 0.0f, 0.0f, ay_ClassA82.field306, -1806514412 ^ -1806514411));
        field476.add(new az(Decryptor.method1945(XorDecoder.method1946("Kp\u0001T¢R\u001ccq\"Vd=Dh%v \u001cH", -1541947015 ^ -784981929)), 0.0f, 0.0f, 960.0f, 495.5f, -5.0f, 5.0f, ay_ClassA82.field304, -958169025 ^ -958169026));
        field476.add(new cT(Decryptor.method1945(XorDecoder.method1946("®tg\u0011µY\u0010¡Dª\u0017»u×uí\u001e3ÿ\u0010", 870634883 ^ 505844068)), 0.0f, 0.0f, 960.0f, 495.5f, -5.0f, 5.0f, ay_ClassA82.field304));
    }

  private List method845() { // было: v
        return !field473.method696(Decryptor.method1945(XorDecoder.method1946("\u001dÙ\u001aXÒ\u0019º\u001bä*¬$Í(â\u0005×\u0008½_öOæ", 1976420796 ^ -1363195694))) ? field476 : field475;
    }

  public boolean at() {
        return 738539218 ^ 738539218;
    }

  public void method846() { // было: j
        field478 = System.currentTimeMillis();
        super.method610();
    }

  public JsonObject method847() { // было: c
        JsonObject var1 = super.method613();
        JsonObject var2 = new JsonObject();
        Iterator var3 = field475.iterator();
        while (var3.hasNext()) {
            ay var4 = ((ay) var3.next());
            var2.add(var4.method596(), var4.method593());
            continue;
        }
        var3 = field476.iterator();
        while (var3.hasNext()) {
            ay var4 = ((ay) var3.next());
            var2.add(var4.method596(), var4.method593());
            continue;
        }
        var1.add(Decryptor.method1945(XorDecoder.method1946("F7\u0002hl4\u001e\u001e\u000f7\u0018Kr\u0019\u0007\u001dMm\u0002c\u0005)t\u0013", -1994174901 ^ -1486221193)), var2);
        return var1;
    }

  public void method848(JsonObject arg0) { // было: e
        super.method614(arg0);
        if (arg0.has(Decryptor.method1945(XorDecoder.method1946(":ït\u0010ìhýsïn¨\u000eÁqþ1µtyñ\u0002ð", -552895244 ^ 305461940)))) {
            if (arg0.get(Decryptor.method1945(XorDecoder.method1946("g­X«M®DÝ.­BS]Þl÷X $³.Ð", -2107772241 ^ 1867396786))).isJsonObject()) {
                JsonObject var2 = arg0.getAsJsonObject(Decryptor.method1945(XorDecoder.method1946("\u0015DYä?GE\\DCÇ!j\\\u001e\u001eYïVZ/", 286632015 ^ -1291352800)));
                Iterator var3 = field475.iterator();
                while (var3.hasNext()) {
                    ay var4 = ((ay) var3.next());
                    String var5 = var4.method596();
                    if (var2.has(var5)) {
                        if (var2.get(var5).isJsonObject()) {
                            var4.method594(var2.getAsJsonObject(var5));
                        }
                    }
                    continue;
                }
                var3 = field476.iterator();
                while (var3.hasNext()) {
                    ay var4 = ((ay) var3.next());
                    String var5 = var4.method596();
                    if (var2.has(var5)) {
                        if (var2.get(var5).isJsonObject()) {
                            var4.method594(var2.getAsJsonObject(var5));
                        }
                    }
                    continue;
                }
            }
        }
    }

  private void method849(ay arg0) { // было: a
        // (пустое тело)
    }

    @EventTarget
  public void method850(bx arg0) { // было: f
        if (!(mc.field_1755 instanceof class_408)) {
            if (field477 != null) {
                field477.method589();
                field477 = null;
            }
        }
        ap var2 = arg0.method318();
        float var3 = ((float) mc.method_22683().method_4480()) / bu();
        float var4 = ((float) mc.method_22683().method_4507()) / bu();
        if (!mc.field_1690.field_1842) {
            List var5 = method845();
            Iterator var6 = var5.iterator();
            while (var6.hasNext()) {
                ay var7 = ((ay) var6.next());
                if (method851(var7)) {
                    try {
                        var7.method579(var2);
                    } catch (Exception var8) {
                    }
                    if (field477 != var7) {
                        if (System.currentTimeMillis() - field478 < (7376982316955469425L ^ 7376982316955473401L)) {
                            var7.method587(var3, var4);
                        }
                    }
                }
                continue;
            }
        }
        if (mc.field_1755 instanceof class_408) {
            if (field477 != null) {
                class_5611 var5 = bK.method1668(((double) bu()));
                double var6 = ((double) var5.method_32118());
                double var8 = ((double) var5.method_32119());
                field477.method582(var2, ((float) var6) - dK, ((float) var8) - dL, this, var3, var4);
            }
        }
    }

  private boolean method851(ay arg0) { // было: a
        String var2 = arg0.method596().replace(Decryptor.method1945(XorDecoder.method1946("\\rðîY}ìÏ\u0019IÔÜ)x¹\u001cEö¾\u0002]¶", 2017878647 ^ -201818597)), Decryptor.method1945(XorDecoder.method1946("A;vÄE\nmÝJ\nRø&\u0018DäB%Wá\u0014-<µ", 158192790 ^ -2123581212)));
        List var3 = List.of(Decryptor.method1945(XorDecoder.method1946("Î]IÁD\u0007íõbKðî]\u001dÄ\u001emãH\u0018ÅàuQÑþizÌÝbbÐó[JÄÙb_", 109390598 ^ -1364387150)), Decryptor.method1945(XorDecoder.method1946("±-\u0003Æ¡-1ïç\u0011\u001fá\u000c\u001eÉþ%x¼¦Ut", -2054103167 ^ -856158711)), Decryptor.method1945(XorDecoder.method1946("~´¿ÐyíÐz®ÑÒ_½ÁE¥óF´âkÇú½cé·\u001dúÖ|Åß\u0019ØÚÚ", 357012711 ^ -221486135)), Decryptor.method1945(XorDecoder.method1946("¬·'¨ð\u0016ô0Ø¶0¤$¯°prÞ\u0019ßõ\u0006ºrØ¬½\u0004Ô", -1186365111 ^ 1342719633)), Decryptor.method1945(XorDecoder.method1946("\u000e\u0002ó\u0016 UÀ)`)øt6%Óll\u0006æ\u0006��WÝt1!,1Tëp\u0017\u0005Ã0#\tù&\u001aRëb", 1307253684 ^ 307854560)), Decryptor.method1945(XorDecoder.method1946("¯¹¹¸¬­¥Ü¥¬èÕ", -208637969 ^ 457544194)), Decryptor.method1945(XorDecoder.method1946("ýq$õ­?#ÇÑn*µÌ5&üÔ\u0015\u0012ß«66èé0\u0013åÔ\u0015\u0005ÿõ\u0004(ÉØ05âý?\u0019±", 105039225 ^ -1978491934)), Decryptor.method1945(XorDecoder.method1946("G\u0004JocX{acaVAwakCBzu\u001a U\u0013\u0015^}\u000eiECYCU\\swe\u001cOu)I��\u001f", -787049737 ^ -215047705)));
        List var4 = List.of(Decryptor.method1945(XorDecoder.method1946("{³wo7µug\"F?\u0017Q5\u001c½G\u0013\t>j", -1556374891 ^ -197637413)), Decryptor.method1945(XorDecoder.method1946("wr6Wæ3tq¤9kUi\u0006\t­h0I <b", 82508886 ^ 1542138729)), Decryptor.method1945(XorDecoder.method1946("ÍÑRÁÑ£5æÝ¸\u0019àÂ·��Ú¸\u0017æÇ^", 635348718 ^ 1176784711)), Decryptor.method1945(XorDecoder.method1946("³Ø\u0013»­äP¯ô7®Í\u0010ðó?±åXä", 946567270 ^ -519112003)), Decryptor.method1945(XorDecoder.method1946("ñ5ÉnÙBº\u007f·%CÔ\u001coÕFÍRÌ&Á\n", 1872746329 ^ 1482924225)), Decryptor.method1945(XorDecoder.method1946("/o2åiê\u0016ö\u0002À\u0008ê\u000c%ê\rÃ\u0001÷g", 826406100 ^ -1642376554)), Decryptor.method1945(XorDecoder.method1946("o!k¾\u000c:F¸\u0008:Såy#LX\u0002L¨|r\u001a", -1069244420 ^ -418667988)), Decryptor.method1945(XorDecoder.method1946("È_®·ÑrÝÌhÜó\u0007½¸ß\u0006½É\rã", 643120366 ^ -127565311)));
        int var5 = var4.indexOf(var2);
        if (var5 == (1076503678 ^ -1076503679)) {
            return -889133743 ^ -889133744;
        } else {
            if (var5 >= field474.method721().size()) {
                return -889133743 ^ -889133744;
            } else {
                return (((ClassA88_ClassA89) field474.method721().get(var5))).aj();
            }
        }
    }

    @EventTarget
  public void method852(bF arg0) { // было: a
        if (mc.field_1755 instanceof class_408) {
            class_5611 var2 = bK.method1668(((double) bu()));
            double var3 = ((double) var2.method_32118());
            double var5 = ((double) var2.method_32119());
            if (arg0.method252() != (-7499797 ^ -7499798)) {
                if (arg0.method252() == 0) {
                    if (field477 != null) {
                        field477.method589();
                        field477 = null;
                    }
                }
            } else {
                if (arg0.method251() != 0) {
                    if (arg0.method252() == 0) {
                        if (field477 != null) {
                            field477.method589();
                            field477 = null;
                        }
                    }
                } else {
                    ArrayList var7 = new ArrayList(method845());
                    Collections.reverse(var7);
                    Iterator var8 = var7.iterator();
                    while (var8.hasNext()) {
                        ay var9 = ((ay) var8.next());
                        if (!method851(var9)) {
                            continue;
                        } else {
                            if (!var9.method580(var3, var5)) {
                                continue;
                            } else {
                                field477 = var9;
                                dK = ((float) var3) - var9.method597();
                                dL = ((float) var5) - var9.method598();
                                break;
                            }
                        }
                    }
                }
            }
        } else {
            if (field477 != null) {
                field477.method589();
                field477 = null;
            }
        }
    }

  public float bu() {
        return 2.0f;
    }

  public Vector2f method853(float arg0, float arg1) { // было: a
        float var3 = 3.4028234663852886e+38f;
        float var4 = 3.4028234663852886e+38f;
        float var5 = 0.0f;
        Vector2f var6 = new Vector2f(-1.0f, -1.0f);
        Iterator var7 = method845().iterator();
        while (var7.hasNext()) {
            ay var12 = ((ay) var7.next());
            if (!var12.equals(field477)) {
                float var8 = var12.method597();
                float var9 = var12.method598();
                float var10 = var12.method597() + var12.method599();
                float var11 = var12.method598() + var12.method600();
                float var13 = var12.method597() + var12.method599() / 2.0f;
                float var14 = var12.method598() + var12.method600() / 2.0f;
                float var15 = method854(var8, var10, var13, arg0);
                float var16 = method854(var9, var11, var14, arg1);
                float var17 = cs.method1420(var15, arg0);
                float var18 = cs.method1420(var16, arg1);
                if (var17 < var3) {
                    var3 = var17;
                    if (var17 < var5) {
                        var6.x = var15;
                    }
                }
                if (var18 < var4) {
                    var4 = var18;
                    if (var18 < var5) {
                        var6.y = var16;
                    }
                }
            }
            continue;
        }
        if (var6.x == -1.0f) {
            float var12 = ((float) mc.method_22683().method_4486()) / 2.0f;
            float var13 = ((float) mc.method_22683().method_4502()) / 2.0f;
            float var8 = method854(var12, var12, var12, arg0);
            float var9 = method854(var13, var13, var13, arg1);
            float var10 = cs.method1420(var8, arg0);
            float var11 = cs.method1420(var9, arg1);
            if (var10 < var3) {
                if (var10 < var5) {
                    var6.x = var8;
                }
            }
            if (var11 < var4) {
                if (var11 < var5) {
                    var6.y = var9;
                }
            }
        } else {
            if (var6.y == -1.0f) {
                float var12 = ((float) mc.method_22683().method_4486()) / 2.0f;
                float var13 = ((float) mc.method_22683().method_4502()) / 2.0f;
                float var8 = method854(var12, var12, var12, arg0);
                float var9 = method854(var13, var13, var13, arg1);
                float var10 = cs.method1420(var8, arg0);
                float var11 = cs.method1420(var9, arg1);
                if (var10 < var3) {
                    if (var10 < var5) {
                        var6.x = var8;
                    }
                }
                if (var11 < var4) {
                    if (var11 < var5) {
                        var6.y = var9;
                    }
                }
            }
        }
        return var6;
    }

  public float method854(float arg0, float arg1, float arg2, float arg3) { // было: G
        float var5 = arg0;
        if (cs.method1420(arg1, arg3) < cs.method1420(arg0, arg3)) {
            var5 = arg1;
        }
        if (cs.method1420(arg2, arg3) < cs.method1420(var5, arg3)) {
            var5 = arg2;
        }
        return var5;
    }

  public boolean au() {
        return -1835107587 ^ -1835107587;
    }

  public boolean av() {
        return 1166740179 ^ 1166740179;
    }

  public boolean aw() {
        return -1102191693 ^ -1102191693;
    }

    @EventTarget
  public void method855(dz arg0) { // было: a
        float var2 = ((float) mc.method_22683().method_4480()) / bu();
        float var3 = ((float) mc.method_22683().method_4507()) / bu();
        Iterator var4 = method845().iterator();
        while (var4.hasNext()) {
            ay var5 = ((ay) var4.next());
            var5.method587(var2, var3);
            continue;
        }
    }

    @EventTarget
  public void method856(do arg0) { // было: g
        if (ClassA158.field870.size() > (-2116734589 ^ -2116734957)) {
            ClassA158.field870.values().removeIf(lp0 -> method859(((ClassA150_ClassA151) lp0)));
        }
        Iterator var2 = method845().iterator();
        while (var2.hasNext()) {
            ay var3 = ((ay) var2.next());
            var3.method577();
            continue;
        }
    }

    @EventTarget
  public void method857(dJ arg0) { // было: a
        if (arg0.method255() instanceof class_408) {
            field478 = System.currentTimeMillis();
        }
    }

    @Generated
  public ay method858() { // было: a
        return field477;
    }

  public int av() {
        return 1329765585 ^ 1329765595;
    }

  private static boolean method859(ClassA150_ClassA151 arg0) { // было: a
        if (!arg0.aB()) {
            return 1392416602 ^ 1392416602;
        } else {
            arg0.bi();
            return -118288794 ^ -118288793;
        }
    }

  private static int qr(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int qs(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int qt(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}