// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.cZ
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.angelvisuals.AngelVisuals;
import dev.angelvisuals.a.ClassA164;
import dev.angelvisuals.a.am;
import dev.angelvisuals.a.bA;
import dev.angelvisuals.a.be;
import dev.angelvisuals.a.bl;
import dev.angelvisuals.a.bp;
import dev.angelvisuals.a.cF;
import dev.angelvisuals.a.ch;
import dev.angelvisuals.a.dD;
import lombok.Generated;
import net.minecraft.class_10142;
import net.minecraft.class_10366;
import net.minecraft.class_1041;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_284;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_293.class_5596;
import net.minecraft.class_310;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_4587.class_4665;
import net.minecraft.class_4588;
import net.minecraft.class_757;
import org.joml.Matrix4f;
import org.joml.Matrix4fStack;

public class cZ implements cF {

    // ---- поля ----
  private static final cZ field977; // было: a
  private ClassA164 field978; // было: d
  private ClassA164 field979; // было: e
  private ClassA164 field980; // было: f
  private be field981; // было: h
  private be field982; // было: i
  private be field983; // было: j
  private be field984; // было: k
  private static final String zz = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String zA = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final String zB = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String zC = "// class hierarchy hashing: ENABLED";
  private static final String zD = "// Joiner sees you";
  private static final int pb = 2057010895;
  private static final int pc = 266045207;
  private static final int pd = 168079335;
  private static final byte[] dO;

    static {
        dO = ":Fwwn\\,}A_zFMx(q4j~Py^{)&Z7MZbMN)z%G~\"AYpB:<^h,BaYq^Q&WS\\P+M[]7T4Xi4D\\$&a^ :Ov;jQ:YsUKe-4z|=I/$$S^ y=p&5c_9.50Zf0cbX:vWc,/So=`/#+wT|;[&9,Hc*hs%de (T7|Q/]8<es&:8`O\"N%q=2*`{Fl,)Xm$(OK1x{gAZ=P^M2.K3{G^y]h\\<Yd<Dh38K8S3M!H?5$4[Au{?s$%f_|A-f|+3ps:7`R/}BkTj_^(*1V".getBytes("ISO-8859-1");
        field977 = new cZ();
    }

  private cZ() { // было: <init>
        super();
    }

  public static cZ method1845() { // было: a
        return field977;
    }

  public static void aS() {
        field977.field981 = new be(AngelVisuals.id(Decryptor.method1945(XorDecoder.method1946("Ý¾\u001a£¥;Ø\u0004·§ª\u0005\u0001¼ª\u0007½µ*ê«9â¸¢\u001a¨,¢ª^", 508840318 ^ 2109455269))), class_290.field_1575);
        field977.field982 = new be(AngelVisuals.id(Decryptor.method1945(XorDecoder.method1946("?$50?Z.\u00116cS.\u001b^!/ c\u0005+>N'\u000f$A6 4S(#Na'%1b\u0014\u000f\u001da-t", -1736203803 ^ -773346670))), class_290.field_1575);
        field977.field983 = new be(AngelVisuals.id(Decryptor.method1945(XorDecoder.method1946("SûÙ8,üÃ\u0003&½%>âÇ\u000b-ò:2½§\u0001'â¥G2ÞB\u0015¡ .­Õ4=§áO", -402692523 ^ -1788235983))), class_290.field_1575);
        field977.field984 = new be(AngelVisuals.id(Decryptor.method1945(XorDecoder.method1946("N¡PumVhQ¦X\u0012\u0007i\t\u001bZ\u001dX )y{c]­8��\u007fd\n[£+\nc»Rg", -1034521285 ^ -1740260077))), class_290.field_1575);
    }

  public void method1846(dD arg0, class_2338 arg1, am arg2) { // было: a
        int var4 = mc.method_22683().method_4489();
        int var5 = mc.method_22683().method_4506();
        if (field978 != null) {
            if (field978.field_1482 != var4) {
                field978.method_1234(var4, var5);
                field979.method_1234(var4, var5);
                field980.method_1234(var4, var5);
            } else {
                if (field978.field_1481 != var5) {
                    field978.method_1234(var4, var5);
                    field979.method_1234(var4, var5);
                    field980.method_1234(var4, var5);
                }
            }
        } else {
            field978 = new ClassA164(var4, var5, -1057962207 ^ -1057962208);
            field979 = new ClassA164(var4, var5, 1717263668 ^ 1717263668);
            field980 = new ClassA164(var4, var5, -857479443 ^ -857479443);
        }
        field978.method_1236(0.0f, 0.0f, 0.0f, 0.0f);
        field978.setup();
        method1847(arg0, arg1);
        field978.stop();
        Matrix4f var6 = RenderSystem.getProjectionMatrix();
        RenderSystem.getModelViewStack().pushMatrix();
        RenderSystem.getModelViewStack().identity();
        RenderSystem.setProjectionMatrix(new Matrix4f(), class_10366.field_54954);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableDepthTest();
        RenderSystem.disableCull();
        bp var7 = AngelVisuals.getInstance().getThemeManager().method481().method449();
        float var8 = ((float) var7.method1695()) / 255.0f;
        float var9 = ((float) var7.method1696()) / 255.0f;
        float var10 = ((float) var7.method1697()) / 255.0f;
        field979.method1724(-921872856 ^ -921872855);
        RenderSystem.setShaderTexture(1645678940 ^ 1645678940, field978.method_30277());
        if (field981 != null) {
            field981.method1767();
            float[] __obj1 = new float[2130085202 ^ 2130085200];
            __obj1[-133492280 ^ -133492280] = 1.0f;
            __obj1[1061016788 ^ 1061016789] = 1.0f;
            method1848(field981, Decryptor.method1945(XorDecoder.method1946("ô��´ªÓ>ôó\u001b¬²Î\u000cöìÎxüÉ\u001e¢¤", -1290182304 ^ 713436891)), __obj1);
            float[] __obj2 = new float[1915934611 ^ 1915934609];
            __obj2[2026787110 ^ 2026787110] = 0.5f / ((float) var4);
            __obj2[-2081988106 ^ -2081988105] = 0.5f / ((float) var5);
            method1848(field981, Decryptor.method1945(XorDecoder.method1946("\u000fjO¶qB\u0013¡\ra\u0017¯tO+¾tb\"òy\u0010G", 103042523 ^ 2080994588)), __obj2);
            float[] __obj3 = new float[-682521357 ^ -682521359];
            __obj3[-1196201641 ^ -1196201641] = ((float) var4);
            __obj3[1751754208 ^ 1751754209] = ((float) var5);
            method1848(field981, Decryptor.method1945(XorDecoder.method1946("1\u0004Ð\u00027%³\u001f0>á;,~Ó\u0005\u00143Ä\u0019R\u0016»q", -1515065043 ^ -382224812)), __obj3);
        }
        aT();
        field979.stop();
        field980.method1724(-1695407326 ^ -1695407325);
        RenderSystem.setShaderTexture(-426924768 ^ -426924768, field979.method_30277());
        if (field981 != null) {
            field981.method1767();
            float[] __obj4 = new float[-16131735 ^ -16131733];
            __obj4[1482666779 ^ 1482666779] = 2.0f;
            __obj4[-2041224994 ^ -2041224993] = 2.0f;
            method1848(field981, Decryptor.method1945(XorDecoder.method1946("4ÇF\u0015\u0013ù\u0006LHÜ^\r\u000eË\u0004S\u000e¿\u000evCÙP\u001b", 1251308441 ^ 1828256738)), __obj4);
            float[] __obj5 = new float[158702696 ^ 158702698];
            __obj5[1420613811 ^ 1420613811] = 0.5f / ((float) var4);
            __obj5[-1635313433 ^ -1635313434] = 0.5f / ((float) var5);
            method1848(field981, Decryptor.method1945(XorDecoder.method1946("\u0019C°g =;7A»?98\u0003(8¸\nd5Êo", -1047613248 ^ -1820736367)), __obj5);
            float[] __obj6 = new float[1654391088 ^ 1654391090];
            __obj6[1451098241 ^ 1451098241] = ((float) var4);
            __obj6[510271238 ^ 510271239] = ((float) var5);
            method1848(field981, Decryptor.method1945(XorDecoder.method1946("±ðL¢·Ñ/¿°Ê}¬O¥ÇX¹Òâ'Ñ", -1295105168 ^ 1591013513)), __obj6);
        }
        aT();
        field980.stop();
        field979.method1724(551309976 ^ 551309977);
        RenderSystem.setShaderTexture(-401781668 ^ -401781668, field980.method_30277());
        if (field982 != null) {
            field982.method1767();
            float[] __obj7 = new float[737283472 ^ 737283474];
            __obj7[-876541738 ^ -876541738] = 2.0f;
            __obj7[-1825542967 ^ -1825542968] = 2.0f;
            method1848(field982, Decryptor.method1945(XorDecoder.method1946("PY\"swgb*,B:kjU`5j!j\u0010'G4}", -1666754121 ^ -592556120)), __obj7);
            float[] __obj8 = new float[1658661113 ^ 1658661115];
            __obj8[1491887975 ^ 1491887975] = 0.5f / ((float) var4);
            __obj8[2047438820 ^ 2047438821] = 0.5f / ((float) var5);
            method1848(field982, Decryptor.method1945(XorDecoder.method1946("¼À@³Âè\u001c¤¾Ë\u0018ªÇå$»ÇÈ-÷ÊºH", -1978606004 ^ -6859378)), __obj8);
            float[] __obj9 = new float[1344583608 ^ 1344583610];
            __obj9[83639452 ^ 83639452] = ((float) var4);
            __obj9[423392814 ^ 423392815] = ((float) var5);
            method1848(field982, Decryptor.method1945(XorDecoder.method1946("É\"·¦Ï\u0003Ô»È\u0018ÔX´¡ì\u0015£½ª0ÜÕ", -799750829 ^ 951428306)), __obj9);
            float[] __obj10 = new float[1816163441 ^ 1816163442];
            __obj10[-399064507 ^ -399064507] = var8;
            __obj10[-1179849736 ^ -1179849735] = var9;
            __obj10[-1764605324 ^ -1764605322] = var10;
            method1848(field982, Decryptor.method1945(XorDecoder.method1946("m4t\u0015:P`%}µb1\"§\u0007\r*|!&À", -1840060591 ^ 1867089789)), __obj10);
        }
        aT();
        field979.stop();
        field980.method1724(455900756 ^ 455900757);
        RenderSystem.setShaderTexture(-1556361151 ^ -1556361151, field979.method_30277());
        if (field982 != null) {
            field982.method1767();
            float[] __obj11 = new float[2000002105 ^ 2000002107];
            __obj11[-1372361786 ^ -1372361786] = 1.0f;
            __obj11[368976981 ^ 368976980] = 1.0f;
            method1848(field982, Decryptor.method1945(XorDecoder.method1946("¢D\u000c\u001fzLFÞ_\u0014\u0007HNY<D|ÕZ\u001a\u0011", 267780481 ^ 600902252)), __obj11);
            float[] __obj12 = new float[1967954636 ^ 1967954638];
            __obj12[-277585089 ^ -277585089] = 0.5f / ((float) var4);
            __obj12[-328674497 ^ -328674498] = 0.5f / ((float) var5);
            method1848(field982, Decryptor.method1945(XorDecoder.method1946("¡Nä´\u0012\u0016á¹*á#ÜìæF", -166311642 ^ -1915886385)), __obj12);
            float[] __obj13 = new float[816426222 ^ 816426220];
            __obj13[1815567988 ^ 1815567988] = ((float) var4);
            __obj13[365049609 ^ 365049608] = ((float) var5);
            method1848(field982, Decryptor.method1945(XorDecoder.method1946("/Íb)ìê\u007f.÷¸[2·e\núyLßâ\u0011", -1691416465 ^ -1208973816)), __obj13);
            float[] __obj14 = new float[-939806073 ^ -939806076];
            __obj14[-274728008 ^ -274728008] = var8;
            __obj14[1797189281 ^ 1797189280] = var9;
            __obj14[2098438541 ^ 2098438543] = var10;
            method1848(field982, Decryptor.method1945(XorDecoder.method1946("XgBã ifãUvKÂWb\u0014Ð2^\u001cüIr\u0010·", -1150168712 ^ 828170849)), __obj14);
        }
        aT();
        field980.stop();
        if (field983 != null) {
            field983.method1767();
            RenderSystem.setShaderTexture(-2085316505 ^ -2085316505, field980.method_30277());
            RenderSystem.setShaderTexture(2104590753 ^ 2104590752, field978.method_30277());
            float[] __obj15 = new float[1107035379 ^ 1107035376];
            __obj15[1842445115 ^ 1842445115] = var8;
            __obj15[-1835206428 ^ -1835206427] = var9;
            __obj15[-1866038392 ^ -1866038390] = var10;
            method1848(field983, Decryptor.method1945(XorDecoder.method1946("|Î ·\u0004À·qß©sËö\u0016÷þ¨mÛòã", -41723755 ^ 592233128)), __obj15);
            bp var11 = AngelVisuals.getInstance().getThemeManager().method481().method457();
            float var12 = ((float) var11.method1695()) / 255.0f;
            float var13 = ((float) var11.method1696()) / 255.0f;
            float var14 = ((float) var11.method1697()) / 255.0f;
            float[] __obj16 = new float[420316598 ^ 420316597];
            __obj16[-1719893874 ^ -1719893874] = var12;
            __obj16[-1245760306 ^ -1245760305] = var13;
            __obj16[-1583855235 ^ -1583855233] = var14;
            method1848(field983, Decryptor.method1945(XorDecoder.method1946("vZ\t¹]:\u000f»V\u001e\"RQ#AQ/\u0017\u000fyá", -1947531732 ^ 1471114772)), __obj16);
            float[] __obj17 = new float[-668123057 ^ -668123058];
            __obj17[-1134290511 ^ -1134290511] = arg2.field381.bp() * 2.0f;
            method1848(field983, Decryptor.method1945(XorDecoder.method1946("��\u0018Å\u0001;?Þ\u001c��[¯\u001eN/øD\u001e\u0003 o\t\n«\u0013", 909218684 ^ 413662726)), __obj17);
            aT();
        }
        if (field984 != null) {
            if (field984.method1768()) {
                field984.method1767();
                RenderSystem.setShaderTexture(-1966186069 ^ -1966186069, field978.method_30277());
                float[] __obj18 = new float[-894722773 ^ -894722774];
                __obj18[1801090798 ^ 1801090798] = ((float) (System.currentTimeMillis() % (2537459889217083311L ^ 2537459889217295855L))) / 1000.0f;
                method1848(field984, Decryptor.method1945(XorDecoder.method1946("\u000cu\u0014\u0012~<è\u0010g\u0006âKH\rÄ\u0017t\u001eñ>Zq", 137694193 ^ -1351010667)), __obj18);
                float[] __obj19 = new float[1769414108 ^ 1769414110];
                __obj19[1779332078 ^ 1779332078] = ((float) var4);
                __obj19[2023172602 ^ 2023172603] = ((float) var5);
                method1848(field984, Decryptor.method1945(XorDecoder.method1946("¨»Ú¡Ãõ áÆêãÈæÓÈÞÞ·¶", 1927300277 ^ -563375010)), __obj19);
                float[] __obj20 = new float[217781443 ^ 217781447];
                __obj20[258508665 ^ 258508665] = var8;
                __obj20[-613647757 ^ -613647758] = var9;
                __obj20[1728892133 ^ 1728892135] = var10;
                __obj20[-1685983117 ^ -1685983120] = ((float) var7.method1698()) / 255.0f;
                method1848(field984, Decryptor.method1945(XorDecoder.method1946(" Åö×­¬´¬¨õ¢¦Ïâø¡Âç£", -1833180343 ^ 207708289)), __obj20);
                float[] __obj21 = new float[-197095796 ^ -197095795];
                __obj21[-1906582714 ^ -1906582714] = arg2.field380.bp();
                method1848(field984, Decryptor.method1945(XorDecoder.method1946("½Å8¨í\u0002ÄÛ&ùð\u000fÔÈGéÞH", 484693396 ^ -1114224007)), __obj21);
                float[] __obj22 = new float[-384494783 ^ -384494784];
                __obj22[1474353663 ^ 1474353663] = arg2.field381.bp();
                method1848(field984, Decryptor.method1945(XorDecoder.method1946("\u0001\u0015¡VT]¯ne\\5x!«A\u0001QTe\u0003ä9", 693819078 ^ 763605747)), __obj22);
                float[] __obj23 = new float[-736803882 ^ -736803881];
                __obj23[-640453266 ^ -640453266] = arg2.field382.bp();
                method1848(field984, Decryptor.method1945(XorDecoder.method1946("w;W.\u0014\u000e\"\u0016?6*li\u0008I{2=\u000575\u000b[i", 1698284999 ^ 828350363)), __obj23);
                float[] __obj24 = new float[-1877445041 ^ -1877445042];
                __obj24[1991234910 ^ 1991234910] = arg2.field383.bp();
                method1848(field984, Decryptor.method1945(XorDecoder.method1946("��¯0a´\u0018\u0001¼¾\u001d^°6^Ê\u0014\u0002®ëO", -835323370 ^ -1125972699)), __obj24);
                float[] __obj25 = new float[-874331014 ^ -874331013];
                __obj25[-330499948 ^ -330499948] = arg2.field378.bp();
                method1848(field984, Decryptor.method1945(XorDecoder.method1946("iOGÌ1C`À,.rî0\u000f\u0006Ï(HGâ\r\u000c\u000b", -945379870 ^ 2140200634)), __obj25);
                float[] __obj26 = new float[353614835 ^ 353614834];
                __obj26[-629066926 ^ -629066926] = arg2.field379.bp();
                method1848(field984, Decryptor.method1945(XorDecoder.method1946("oè\u0001aé\"(åè97ù-,Âß3_áF", 2042784541 ^ 41470746)), __obj26);
                aT();
            }
        }
        RenderSystem.setProjectionMatrix(var6, class_10366.field_54953);
        RenderSystem.getModelViewStack().popMatrix();
        RenderSystem.enableDepthTest();
        RenderSystem.enableCull();
        RenderSystem.disableBlend();
    }

  private void method1847(dD arg0, class_2338 arg1) { // было: c
        class_4587 var3 = arg0.method324();
        class_243 var4 = mc.field_1773.method_19418().method_19326();
        bp var5 = AngelVisuals.getInstance().getThemeManager().method481().method449();
        int var6 = var5.method1686(1236358123 ^ 1236357908).method1680();
        int var7 = var5.method1686(-1442253406 ^ -1442253370).method1680();
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(1194223938 ^ 1194224192, 1542629052 ^ 1542628799);
        RenderSystem.disableCull();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(-371531060 ^ -371531060);
        RenderSystem.setShader(class_10142.field_53876);
        RenderSystem.lineWidth(3.0f);
        class_289 var8 = class_289.method_1348();
        var3.method_22903();
        var3.method_22904(((double) arg1.method_10263()) - var4.field_1352, ((double) arg1.method_10264()) - var4.field_1351, ((double) arg1.method_10260()) - var4.field_1350);
        var3.method_22905(1.0019999742507935f, 1.0019999742507935f, 1.0019999742507935f);
        Matrix4f var9 = var3.method_23760().method_23761();
        class_287 var10 = var8.method_60827(class_5596.field_27382, class_290.field_1576);
        var10.method_22918(var9, 0.0f, 0.0f, 0.0f).method_39415(var7);
        var10.method_22918(var9, 1.0f, 0.0f, 0.0f).method_39415(var7);
        var10.method_22918(var9, 1.0f, 0.0f, 1.0f).method_39415(var7);
        var10.method_22918(var9, 0.0f, 0.0f, 1.0f).method_39415(var7);
        var10.method_22918(var9, 0.0f, 1.0f, 0.0f).method_39415(var7);
        var10.method_22918(var9, 0.0f, 1.0f, 1.0f).method_39415(var7);
        var10.method_22918(var9, 1.0f, 1.0f, 1.0f).method_39415(var7);
        var10.method_22918(var9, 1.0f, 1.0f, 0.0f).method_39415(var7);
        var10.method_22918(var9, 0.0f, 0.0f, 0.0f).method_39415(var7);
        var10.method_22918(var9, 0.0f, 1.0f, 0.0f).method_39415(var7);
        var10.method_22918(var9, 1.0f, 1.0f, 0.0f).method_39415(var7);
        var10.method_22918(var9, 1.0f, 0.0f, 0.0f).method_39415(var7);
        var10.method_22918(var9, 1.0f, 0.0f, 0.0f).method_39415(var7);
        var10.method_22918(var9, 1.0f, 1.0f, 0.0f).method_39415(var7);
        var10.method_22918(var9, 1.0f, 1.0f, 1.0f).method_39415(var7);
        var10.method_22918(var9, 1.0f, 0.0f, 1.0f).method_39415(var7);
        var10.method_22918(var9, 1.0f, 0.0f, 1.0f).method_39415(var7);
        var10.method_22918(var9, 1.0f, 1.0f, 1.0f).method_39415(var7);
        var10.method_22918(var9, 0.0f, 1.0f, 1.0f).method_39415(var7);
        var10.method_22918(var9, 0.0f, 0.0f, 1.0f).method_39415(var7);
        var10.method_22918(var9, 0.0f, 0.0f, 1.0f).method_39415(var7);
        var10.method_22918(var9, 0.0f, 1.0f, 1.0f).method_39415(var7);
        var10.method_22918(var9, 0.0f, 1.0f, 0.0f).method_39415(var7);
        var10.method_22918(var9, 0.0f, 0.0f, 0.0f).method_39415(var7);
        class_286.method_43433(var10.method_60800());
        class_287 var11 = var8.method_60827(class_5596.field_29344, class_290.field_1576);
        var11.method_22918(var9, 0.0f, 0.0f, 0.0f).method_39415(var6);
        var11.method_22918(var9, 1.0f, 0.0f, 0.0f).method_39415(var6);
        var11.method_22918(var9, 1.0f, 0.0f, 0.0f).method_39415(var6);
        var11.method_22918(var9, 1.0f, 0.0f, 1.0f).method_39415(var6);
        var11.method_22918(var9, 1.0f, 0.0f, 1.0f).method_39415(var6);
        var11.method_22918(var9, 0.0f, 0.0f, 1.0f).method_39415(var6);
        var11.method_22918(var9, 0.0f, 0.0f, 1.0f).method_39415(var6);
        var11.method_22918(var9, 0.0f, 0.0f, 0.0f).method_39415(var6);
        var11.method_22918(var9, 0.0f, 1.0f, 0.0f).method_39415(var6);
        var11.method_22918(var9, 1.0f, 1.0f, 0.0f).method_39415(var6);
        var11.method_22918(var9, 1.0f, 1.0f, 0.0f).method_39415(var6);
        var11.method_22918(var9, 1.0f, 1.0f, 1.0f).method_39415(var6);
        var11.method_22918(var9, 1.0f, 1.0f, 1.0f).method_39415(var6);
        var11.method_22918(var9, 0.0f, 1.0f, 1.0f).method_39415(var6);
        var11.method_22918(var9, 0.0f, 1.0f, 1.0f).method_39415(var6);
        var11.method_22918(var9, 0.0f, 1.0f, 0.0f).method_39415(var6);
        var11.method_22918(var9, 0.0f, 0.0f, 0.0f).method_39415(var6);
        var11.method_22918(var9, 0.0f, 1.0f, 0.0f).method_39415(var6);
        var11.method_22918(var9, 1.0f, 0.0f, 0.0f).method_39415(var6);
        var11.method_22918(var9, 1.0f, 1.0f, 0.0f).method_39415(var6);
        var11.method_22918(var9, 1.0f, 0.0f, 1.0f).method_39415(var6);
        var11.method_22918(var9, 1.0f, 1.0f, 1.0f).method_39415(var6);
        var11.method_22918(var9, 0.0f, 0.0f, 1.0f).method_39415(var6);
        var11.method_22918(var9, 0.0f, 1.0f, 1.0f).method_39415(var6);
        class_286.method_43433(var11.method_60800());
        var3.method_22909();
        RenderSystem.lineWidth(1.0f);
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask(-448055213 ^ -448055214);
        RenderSystem.enableCull();
    }

  private void aT() {
        class_287 var1 = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
        Matrix4f var2 = new Matrix4f();
        var1.method_22918(var2, -1.0f, -1.0f, 0.0f).method_22913(0.0f, 0.0f).method_39415(1253345105 ^ -1253345106);
        var1.method_22918(var2, -1.0f, 1.0f, 0.0f).method_22913(0.0f, 1.0f).method_39415(-2101552599 ^ 2101552598);
        var1.method_22918(var2, 1.0f, 1.0f, 0.0f).method_22913(1.0f, 1.0f).method_39415(506053577 ^ -506053578);
        var1.method_22918(var2, 1.0f, -1.0f, 0.0f).method_22913(1.0f, 0.0f).method_39415(1174934976 ^ -1174934977);
        class_286.method_43433(var1.method_60800());
    }

  private void method1848(be arg0, String arg1, float[] arg2) { // было: b
        class_284 var4 = arg0.method1770(arg1);
        if (var4 != null) {
            if (arg2.length != (633208298 ^ 633208299)) {
                if (arg2.length != (-1410072412 ^ -1410072410)) {
                    if (arg2.length != (1941996330 ^ 1941996329)) {
                        if (arg2.length == (998348481 ^ 998348485)) {
                            var4.method_35657(arg2[-663382940 ^ -663382940], arg2[1560129389 ^ 1560129388], arg2[1533361156 ^ 1533361158], arg2[1282313718 ^ 1282313717]);
                        }
                    } else {
                        var4.method_1249(arg2[46343669 ^ 46343669], arg2[606903216 ^ 606903217], arg2[1554865694 ^ 1554865692]);
                    }
                } else {
                    var4.method_1255(arg2[1202948909 ^ 1202948909], arg2[-1466235314 ^ -1466235313]);
                }
            } else {
                var4.method_1251(arg2[-1589047272 ^ -1589047272]);
            }
        }
    }

  public void aU() {
        if (field978 != null) {
            field978.method_1238();
            field978 = null;
        }
        if (field979 != null) {
            field979.method_1238();
            field979 = null;
        }
        if (field980 != null) {
            field980.method_1238();
            field980 = null;
        }
    }

    @Generated
  public ClassA164 method1849() { // было: e
        return field978;
    }

    @Generated
  public ClassA164 method1850() { // было: f
        return field979;
    }

    @Generated
  public ClassA164 method1851() { // было: g
        return field980;
    }

    @Generated
  public be method1852() { // было: h
        return field981;
    }

    @Generated
  public be method1853() { // было: i
        return field982;
    }

    @Generated
  public be method1854() { // было: j
        return field983;
    }

    @Generated
  public be method1855() { // было: k
        return field984;
    }

  private static int nn(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int no(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int np(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}