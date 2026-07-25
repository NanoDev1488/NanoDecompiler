// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.dK
package dev.angelvisuals.a;

import com.darkmagician6.eventapi.EventTarget;
import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import com.mojang.blaze3d.platform.GlStateManager.class_4534;
import com.mojang.blaze3d.platform.GlStateManager.class_4535;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.angelvisuals.AngelVisuals;
import dev.angelvisuals.a.aZ;
import dev.angelvisuals.a.bA;
import dev.angelvisuals.a.bI;
import dev.angelvisuals.a.bP;
import dev.angelvisuals.a.bp;
import dev.angelvisuals.a.cK;
import dev.angelvisuals.a.ch;
import dev.angelvisuals.a.dD;
import dev.angelvisuals.a.dK_ClassA102;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.class_10142;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_293.class_5596;
import net.minecraft.class_2960;
import net.minecraft.class_304;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_4587.class_4665;
import net.minecraft.class_4588;
import net.minecraft.class_746;
import net.minecraft.class_757;
import net.minecraft.class_7833;
import org.joml.Matrix4f;

@bI(name = "Cubes", a = "RENDER", I = "3D Кубы по миру")
public class dK extends cK {

    // ---- поля ----
  public static dK field412; // было: a
  private static final class_2960 field413; // было: v
  private static final float dP = 12.0f;
  private static final float dQ = 0.18000000715255737f;
  private static final float dR = 0.25f;
  private static final float dS = 1.7000000476837158f;
  private static final float dT = 900.0f;
  private static final byte[][] field414; // было: a
  private static final byte[][] field415; // было: b
  private static final float[] field416; // было: d
  private static final float[] field417; // было: e
  private final aZ field418; // было: l
  private final aZ field419; // было: m
  private final bA ak;
  private final bA al;
  private final bA am;
  private final List field420; // было: T
  private final List field421; // было: U
  private final Random field422; // было: g
  private boolean ax;
  private float dU;
  private float dV;
  private float dW;
  private int sQ;
  private static final String FR = "// number obfuscation: ENABLED (XOR masking)";
  private static final String FS = "// flow obfuscation: ENABLED";
  private static final String FT = "// reverse-engineering this jar is a waste of time, friend";
  private static final String FU = "// nice try. closed source for a reason.";
  private static final String FV = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final int sR = 951100008;
  private static final int sS = 1223925816;
  private static final int sT = -588640711;
  private static final byte[] eX;

    static {
        eX = "%7&yk[I>)>^ -427+'+CF{^#PrD9*|.e)=USA\"@W?O%iwiUZ\"[eAgQ.-<XA\"?>au*o]<.n7O6Nh%:+/C2H[9}hy5>Q>{JKLP}N1M))7O5]/5E?QUWE=;~+y6}5e:3.\\4h*G&CbVZQEm@E9RoMe\".7+7wTg0ah>.A<ilG>DU96\\^Hq$u|T!6DoJt  \\4k_G)6\\NMq+vOVcn<GQT*M1Bgq`;f%HM-4G6|Zpye'$K6bm; b2~+B}@s#V1;T*?vb5tvg".getBytes("ISO-8859-1");
        field412 = new dK();
        field413 = class_2960.method_60655(Decryptor.method1945(XorDecoder.method1946(">8Ú#\u0002\u0008ËY+\u000fãZ\u001bUÔ{;8Ø\"Z\u001b³/", 1875690210 ^ 2101522059)), Decryptor.method1945(XorDecoder.method1946("jBÀ!*]ØvWhÜ[y@âvXE*4p/", 546879573 ^ 842055502)));
        byte[] __obj1 = new byte[-1803823438 ^ -1803823436];
        __obj1[-527630436 ^ -527630436] = -2081756651 ^ 2081756650;
        __obj1[-589064756 ^ -589064755] = -1859170723 ^ 1859170722;
        __obj1[2029046903 ^ 2029046901] = -844708920 ^ 844708919;
        __obj1[201026702 ^ 201026701] = -66211710 ^ -66211709;
        __obj1[873613204 ^ 873613200] = 724145678 ^ -724145679;
        __obj1[-391362998 ^ -391362993] = 1813280368 ^ -1813280369;
        byte[][] __obj2 = new byte[356932834 ^ 356932846][];
        __obj2[-1544532227 ^ -1544532227] = __obj1;
        byte[] __obj3 = new byte[-985458034 ^ -985458040];
        __obj3[-1054551498 ^ -1054551498] = 1621545648 ^ 1621545649;
        __obj3[1198982671 ^ 1198982670] = -469860365 ^ 469860364;
        __obj3[139190636 ^ 139190638] = 2052840851 ^ -2052840852;
        __obj3[338558022 ^ 338558021] = -1875253077 ^ -1875253078;
        __obj3[-303941544 ^ -303941540] = -1002101501 ^ 1002101500;
        __obj3[-1682721330 ^ -1682721333] = 2079815799 ^ 2079815798;
        __obj2[351192885 ^ 351192884] = __obj3;
        byte[] __obj4 = new byte[431948733 ^ 431948731];
        __obj4[-1292453386 ^ -1292453386] = -726647957 ^ -726647958;
        __obj4[277267732 ^ 277267733] = 427502393 ^ -427502394;
        __obj4[1901844556 ^ 1901844558] = 531485615 ^ 531485614;
        __obj4[1907313706 ^ 1907313705] = -404674785 ^ 404674784;
        __obj4[651521708 ^ 651521704] = -358369306 ^ 358369305;
        __obj4[-1525182807 ^ -1525182804] = 616679831 ^ 616679830;
        __obj2[643022612 ^ 643022614] = __obj4;
        byte[] __obj5 = new byte[-1831291151 ^ -1831291145];
        __obj5[1865299194 ^ 1865299194] = -46631331 ^ 46631330;
        __obj5[636426064 ^ 636426065] = -1852961037 ^ 1852961036;
        __obj5[1665003042 ^ 1665003040] = 1224532373 ^ 1224532372;
        __obj5[-1423108886 ^ -1423108887] = 831962918 ^ -831962919;
        __obj5[-1321639978 ^ -1321639982] = 721689937 ^ -721689938;
        __obj5[1107623422 ^ 1107623419] = -1365369374 ^ 1365369373;
        __obj2[-1377797008 ^ -1377797005] = __obj5;
        byte[] __obj6 = new byte[1561269270 ^ 1561269264];
        __obj6[-459759948 ^ -459759948] = -30535071 ^ 30535070;
        __obj6[-1573079186 ^ -1573079185] = -331887083 ^ -331887084;
        __obj6[-4077503 ^ -4077501] = -162512169 ^ 162512168;
        __obj6[-130760936 ^ -130760933] = -1414240262 ^ -1414240261;
        __obj6[426055599 ^ 426055595] = 96668628 ^ 96668629;
        __obj6[361743749 ^ 361743744] = -604576713 ^ 604576712;
        __obj2[675389862 ^ 675389858] = __obj6;
        byte[] __obj7 = new byte[-793684343 ^ -793684337];
        __obj7[-54823261 ^ -54823261] = 39371885 ^ 39371884;
        __obj7[-1975040774 ^ -1975040773] = -1991163694 ^ -1991163693;
        __obj7[1105665320 ^ 1105665322] = 1945576925 ^ -1945576926;
        __obj7[-1823936764 ^ -1823936761] = 1581842870 ^ 1581842871;
        __obj7[186230031 ^ 186230027] = 75387165 ^ 75387164;
        __obj7[1823525287 ^ 1823525282] = 1991777580 ^ 1991777581;
        __obj2[1277810143 ^ 1277810138] = __obj7;
        byte[] __obj8 = new byte[91700825 ^ 91700831];
        __obj8[1148666660 ^ 1148666660] = -722670200 ^ -722670199;
        __obj8[108017237 ^ 108017236] = -195074325 ^ -195074326;
        __obj8[-1948873481 ^ -1948873483] = -1054162623 ^ -1054162624;
        __obj8[1681675299 ^ 1681675296] = 2076435170 ^ -2076435171;
        __obj8[30390041 ^ 30390045] = -1618112269 ^ -1618112270;
        __obj8[1716803635 ^ 1716803638] = 1722683480 ^ 1722683481;
        __obj2[1603835264 ^ 1603835270] = __obj8;
        byte[] __obj9 = new byte[-916155985 ^ -916155991];
        __obj9[1014278010 ^ 1014278010] = 1521618204 ^ -1521618205;
        __obj9[296846325 ^ 296846324] = 1479354210 ^ 1479354211;
        __obj9[1160412312 ^ 1160412314] = 1905465868 ^ 1905465869;
        __obj9[928471436 ^ 928471439] = 890504242 ^ -890504243;
        __obj9[1944498563 ^ 1944498567] = -893289322 ^ -893289321;
        __obj9[-362339014 ^ -362339009] = -372915538 ^ 372915537;
        __obj2[1420652829 ^ 1420652826] = __obj9;
        byte[] __obj10 = new byte[156152660 ^ 156152658];
        __obj10[1572503969 ^ 1572503969] = -1345931859 ^ 1345931858;
        __obj10[1923998257 ^ 1923998256] = 201072633 ^ -201072634;
        __obj10[-649291862 ^ -649291864] = 1811985225 ^ -1811985226;
        __obj10[-1000888167 ^ -1000888166] = -1729928854 ^ 1729928853;
        __obj10[876421874 ^ 876421878] = -2144106075 ^ -2144106076;
        __obj10[1456751063 ^ 1456751058] = -385967200 ^ 385967199;
        __obj2[-1662047192 ^ -1662047200] = __obj10;
        byte[] __obj11 = new byte[1924753800 ^ 1924753806];
        __obj11[-780902513 ^ -780902513] = -1992429079 ^ -1992429080;
        __obj11[652176389 ^ 652176388] = -867531890 ^ 867531889;
        __obj11[-140932164 ^ -140932162] = 717571399 ^ -717571400;
        __obj11[-1679880482 ^ -1679880483] = -2060976550 ^ -2060976549;
        __obj11[1111436477 ^ 1111436473] = 357197699 ^ 357197698;
        __obj11[1110296210 ^ 1110296215] = -638361646 ^ 638361645;
        __obj2[-670111899 ^ -670111892] = __obj11;
        byte[] __obj12 = new byte[101373582 ^ 101373576];
        __obj12[-877517600 ^ -877517600] = 1890031779 ^ 1890031778;
        __obj12[784297094 ^ 784297095] = -673922622 ^ 673922621;
        __obj12[482700877 ^ 482700879] = 1067729294 ^ 1067729295;
        __obj12[1643026810 ^ 1643026809] = -1247848918 ^ -1247848917;
        __obj12[334027636 ^ 334027632] = 623494509 ^ 623494508;
        __obj12[1403903641 ^ 1403903644] = -379475434 ^ -379475433;
        __obj2[-812443470 ^ -812443464] = __obj12;
        byte[] __obj13 = new byte[-296836844 ^ -296836846];
        __obj13[778150189 ^ 778150189] = 429556893 ^ -429556894;
        __obj13[2130202136 ^ 2130202137] = 1533280825 ^ -1533280826;
        __obj13[2009225292 ^ 2009225294] = 159044784 ^ 159044785;
        __obj13[650470348 ^ 650470351] = 1159846026 ^ -1159846027;
        __obj13[1854930930 ^ 1854930934] = -1729252399 ^ -1729252400;
        __obj13[334372984 ^ 334372989] = 1385145152 ^ 1385145153;
        __obj2[-1956292844 ^ -1956292833] = __obj13;
        field414 = __obj2;
        byte[] __obj14 = new byte[-1750209415 ^ -1750209413];
        __obj14[-805262788 ^ -805262788] = -1494028953 ^ -1494028953;
        __obj14[1552396671 ^ 1552396670] = 1021963474 ^ 1021963475;
        byte[][] __obj15 = new byte[152389408 ^ 152389416][];
        __obj15[-1378652717 ^ -1378652717] = __obj14;
        byte[] __obj16 = new byte[1396638570 ^ 1396638568];
        __obj16[-1374893082 ^ -1374893082] = -217475821 ^ -217475821;
        __obj16[-225525715 ^ -225525716] = -1937046773 ^ -1937046775;
        __obj15[-1925903245 ^ -1925903246] = __obj16;
        byte[] __obj17 = new byte[-521797023 ^ -521797021];
        __obj17[-1594121869 ^ -1594121869] = 1511977602 ^ 1511977602;
        __obj17[1909824633 ^ 1909824632] = 753628122 ^ 753628121;
        __obj15[-250014891 ^ -250014889] = __obj17;
        byte[] __obj18 = new byte[-538507557 ^ -538507559];
        __obj18[1357946285 ^ 1357946285] = 1835627531 ^ 1835627531;
        __obj18[1080625249 ^ 1080625248] = 947157407 ^ 947157403;
        __obj15[2143364560 ^ 2143364563] = __obj18;
        byte[] __obj19 = new byte[-826046489 ^ -826046491];
        __obj19[1644048894 ^ 1644048894] = -1521908279 ^ -1521908280;
        __obj19[-812121421 ^ -812121422] = 1174238911 ^ 1174238909;
        __obj15[803742904 ^ 803742908] = __obj19;
        byte[] __obj20 = new byte[-592199668 ^ -592199666];
        __obj20[-916450809 ^ -916450809] = -1438941274 ^ -1438941276;
        __obj20[-1859036607 ^ -1859036608] = -1862844871 ^ -1862844870;
        __obj15[-1980145191 ^ -1980145188] = __obj20;
        byte[] __obj21 = new byte[813699069 ^ 813699071];
        __obj21[1310365065 ^ 1310365065] = 566247912 ^ 566247915;
        __obj21[-20325803 ^ -20325804] = 930467665 ^ 930467669;
        __obj15[-656000250 ^ -656000256] = __obj21;
        byte[] __obj22 = new byte[-1778886006 ^ -1778886008];
        __obj22[1104440850 ^ 1104440850] = 1588466797 ^ 1588466793;
        __obj22[-1214992750 ^ -1214992749] = -1938345358 ^ -1938345357;
        __obj15[-1612789899 ^ -1612789902] = __obj22;
        field415 = __obj15;
        float[] __obj23 = new float[1183689922 ^ 1183689921];
        __obj23[-1431758965 ^ -1431758965] = 10.0f;
        __obj23[174389804 ^ 174389805] = 6.0f;
        __obj23[1154907004 ^ 1154907006] = 3.5f;
        field416 = __obj23;
        float[] __obj24 = new float[181889739 ^ 181889736];
        __obj24[-1501661654 ^ -1501661654] = 0.05999999865889549f;
        __obj24[-1326364633 ^ -1326364634] = 0.14000000059604645f;
        __obj24[1781771935 ^ 1781771933] = 0.25f;
        field417 = __obj24;
    }

  private dK() { // было: <init>
        super();
        String[] __obj1 = new String[-2065655562 ^ -2065655564];
        __obj1[-973352549 ^ -973352549] = Decryptor.method1945(XorDecoder.method1946("Àvðö\u0013õøp¿§Só¼­kùáøQå", -1419533263 ^ 1943765668));
        __obj1[1816316423 ^ 1816316422] = Decryptor.method1945(XorDecoder.method1946("±ò:E°õP^ñÑM\u0004òó4XüÓ\u0017ré_\n", 1902960460 ^ 1175339401));
        field418 = new aZ(Decryptor.method1945(XorDecoder.method1946("âô·£ÜòÍ¸ýÞ«íÌÄãõæóÏ×ÍÇÂí¶ÂåèÉ ÞÙ½´ÍÓÏ", -529238653 ^ 311085079)), __obj1);
        String[] __obj2 = new String[-1593720635 ^ -1593720633];
        __obj2[-183791016 ^ -183791016] = Decryptor.method1945(XorDecoder.method1946("\nzMïMBS)R-öJi|\u0011K@ß\tR%", 505087227 ^ -1207756928));
        __obj2[2074933281 ^ 2074933280] = Decryptor.method1945(XorDecoder.method1946("íñf½ï®\u0004ºÈr·\u0001Ü~¦\u0008¡öqà³\u0006£Íuúÿtøÿ\r", -1781660194 ^ -1526157548));
        field419 = new aZ(Decryptor.method1945(XorDecoder.method1946("©ö\u0010¢mÖ¦®kÖ·H¶â¢\u0015¶ù\u001b", 65726729 ^ 623786222)), __obj2);
        ak = new bA(Decryptor.method1945(XorDecoder.method1946("\u0006ôSÅ\u0006ù\u0004Ô\u0012é\u007fè\u0013õM¤\u001eÇ\u0017ýLôqý\u001dê\n©\u0019ÈMØ\u001d×\u000eÿ^ÌiÚ\u0002Ì_­", -711765889 ^ 1169157386)), 30.0f, 5.0f, 100.0f, 1.0f);
        al = new bA(Decryptor.method1945(XorDecoder.method1946("¼Ñ¬,º¿ß+´ñÁ\u0008¥¼Ò\u000fÒÆ+©üºw", 1709061742 ^ 794410385)), 1.0f, 0.10000000149011612f, 3.0f, 0.10000000149011612f);
        am = new bA(Decryptor.method1945(XorDecoder.method1946("¼ÿ\u0017\"ö\u0008>µû>\u0016¾Å=0ý\"l®õi)¯ºkoºÁ\u0010jÄo\u0008¦õ\u001b\u001cµÿ/g", 1131382889 ^ 423041413)), 1.0f, 0.10000000149011612f, 5.0f, 0.10000000149011612f);
        field420 = new ArrayList();
        field421 = new ArrayList();
        field422 = new Random();
        sQ = -1072450225 ^ -1072450225;
    }

  public void method798() { // было: j
        super.method610();
        field420.clear();
    }

  public void method799() { // было: k
        super.method611();
        field420.clear();
    }

    @EventTarget
  public void method800(dD arg0) { // было: n
        if (mc.field_1724 == null) {
            return;
        } else {
            if (mc.field_1687 != null) {
                boolean var2 = mc.field_1690.field_1886.method_1434();
                if (var2) {
                    if (!ax) {
                        method802(mc.field_1773.method_19418());
                    }
                }
                ax = var2;
                sQ = sQ + (1072161440 ^ 1072161441);
                if (sQ % (1731003898 ^ 1731003896) == 0) {
                    bf();
                }
                method803(arg0);
                return;
            } else {
                return;
            }
        }
    }

    @EventTarget
  public void method801(bP arg0) { // было: e
        if (mc.field_1773 != null) {
            if (mc.field_1773.method_19418() != null) {
                method802(mc.field_1773.method_19418());
            }
        }
    }

  private void method802(class_4184 arg0) { // было: a
        if (field420.isEmpty()) {
            return;
        }
        Object var11;
        int var14;
        int var15;
        if (arg0 != null) {
            class_243 var2 = arg0.method_19326();
            float var3 = ((float) Math.toRadians(((double) arg0.method_19330())));
            float var4 = ((float) Math.toRadians(((double) arg0.method_19329())));
            double var5 = ((double) (-class_3532.method_15374(var3) * class_3532.method_15362(var4)));
            double var7 = ((double) -class_3532.method_15374(var4));
            double var9 = ((double) (class_3532.method_15362(var3) * class_3532.method_15362(var4)));
            var11 = null;
            double var12 = 1.7976931348623157e+308;
            var14 = -78721581 ^ -78721581;
            var15 = field420.size();
        } else {
            return;
        }
        dK_ClassA102 var11;
        while (var14 < var15) {
            dK_ClassA102 var16 = ((dK_ClassA102) field420.get(var14));
            double var17 = var16.field409 - var2.field_1352;
            double var19 = var16.field410 - var2.field_1351;
            double var21 = var16.field411 - var2.field_1350;
            double var23 = var17 * var5 + var19 * var7 + var21 * var9;
            if (var23 >= 0.0) {
                if (var23 <= 128.0) {
                    double var25 = var2.field_1352 + var5 * var23;
                    double var27 = var2.field_1351 + var7 * var23;
                    double var29 = var2.field_1350 + var9 * var23;
                    double var31 = var16.field409 - var25;
                    double var33 = var16.field410 - var27;
                    double var35 = var16.field411 - var29;
                    double var37 = var31 * var31 + var33 * var33 + var35 * var35;
                    if (var37 <= 1.32) {
                        if (var23 < var12) {
                            double var12 = var23;
                            var11 = var16;
                        }
                    }
                }
            }
            ++var14;
            continue;
        }
        if (var11 != null) {
            double var14 = 0.08 * ((double) am.bp());
            var11.dX = ((float) (((double) var11.dX) + var5 * var14));
            var11.dY = ((float) (((double) var11.dY) + var7 * var14 + 0.02));
            var11.dZ = ((float) (((double) var11.dZ) + var9 * var14));
        }
    }

  private void bf() {
        int var1 = ((int) ak.bp());
        int var2 = field420.size();
        int var4;
        if (var2 >= var1) {
            if (var2 > var1) {
                field420.subList(var1, var2).clear();
            }
        } else {
            int var3 = Math.min(var1 - var2, 1491916947 ^ 1491916950);
            var4 = 600707182 ^ 600707182;
            while (var4 < var3) {
                field420.add(method816());
                ++var4;
                continue;
            }
        }
        float var3 = 0.25f * am.bp();
        float var4 = 12.0f;
        boolean var5 = field418.method696(Decryptor.method1945(XorDecoder.method1946("<E­¸=BÇ£|fÚù\u007fD£¥qd\u001a^È÷", -1560365524 ^ 1745592164)));
        class_243 var6 = mc.field_1724.method_19538();
        double var7 = ((double) (var4 * var4)) * 6.25;
        int var9 = field420.size() - (424495110 ^ 424495111);
        while (var9 >= 0) {
            dK_ClassA102 var10 = ((dK_ClassA102) field420.get(var9));
            if (!var5) {
                var10.field409 = var10.field409 + ((double) (var10.dX * var3));
                var10.field410 = var10.field410 + ((double) (var10.dY * var3));
                var10.field411 = var10.field411 + ((double) (var10.dZ * var3));
                var10.ea = var10.ea + var10.ed * var3;
                var10.eb = var10.eb + var10.ee * var3;
                var10.ec = var10.ec + var10.ef * var3;
                var10.dX = var10.dX * 0.9950000047683716f;
                var10.dY = var10.dY * 0.9950000047683716f;
                var10.dZ = var10.dZ * 0.9950000047683716f;
            } else {
                var10.eg = var10.eg + 0.05999999865889549f * var3;
                var10.field409 = var10.field409 + ((double) (var10.dX * var3)) + Math.sin(((double) (var10.eg + var10.eh))) * 0.002400000113993883 * ((double) var3);
                var10.field410 = var10.field410 + ((double) (var10.dY * var3));
                var10.field411 = var10.field411 + ((double) (var10.dZ * var3)) + Math.cos(((double) (var10.eg * 0.800000011920929f + var10.eh))) * 0.0020000000949949026 * ((double) var3);
                var10.dY = Math.max(var10.dY - 7.999999797903001e-05f * var3, -0.03200000151991844f);
                var10.ea = var10.ea + var10.ed * 0.20000000298023224f * var3;
                var10.eb = var10.eb + var10.ee * 0.20000000298023224f * var3;
                var10.ec = var10.ec + var10.ef * 0.20000000298023224f * var3;
            }
            var10.tA = var10.tA - (-1489489043 ^ -1489489044);
            double var11 = var10.field409 - var6.field_1352;
            double var13 = var10.field410 - var6.field_1351;
            double var15 = var10.field411 - var6.field_1350;
            double var17 = var11 * var11 + var13 * var13 + var15 * var15;
            if (var10.tA <= 0) {
                field420.remove(var9);
                field420.add(method816());
            } else {
                if (var17 > var7) {
                    field420.remove(var9);
                    field420.add(method816());
                } else {
                    if (var5) {
                        if (var10.field410 < var6.field_1351 - 2.5) {
                            field420.remove(var9);
                            field420.add(method816());
                        }
                    }
                }
            }
            --var9;
            continue;
        }
    }

  private void method803(dD arg0) { // было: o
        int var16;
        int var17;
        if (mc.field_1724 != null) {
            class_4587 var2 = arg0.method324();
            class_243 var3 = mc.field_1773.method_19418().method_19326();
            class_4184 var4 = mc.field_1773.method_19418();
            float var5 = 0.18000000715255737f * al.bp();
            float var6 = 1.7000000476837158f;
            int var7 = AngelVisuals.getInstance().getThemeManager().method480(1343996180 ^ 1343996180).method1680();
            dU = ((float) (var7 >> (1900354853 ^ 1900354869) & (-1753353354 ^ -1753353335))) / 255.0f;
            dV = ((float) (var7 >> (165567262 ^ 165567254) & (-437286472 ^ -437286585))) / 255.0f;
            dW = ((float) (var7 & (-1576874472 ^ -1576874265))) / 255.0f;
            field421.clear();
            float var8 = ((float) Math.toRadians(((double) var4.method_19330())));
            float var9 = ((float) Math.toRadians(((double) var4.method_19329())));
            double var10 = ((double) (-class_3532.method_15374(var8) * class_3532.method_15362(var9)));
            double var12 = ((double) -class_3532.method_15374(var9));
            double var14 = ((double) (class_3532.method_15362(var8) * class_3532.method_15362(var9)));
            var16 = 1328834702 ^ 1328834702;
            var17 = field420.size();
        } else {
            return;
        }
        while (var16 < var17) {
            dK_ClassA102 var18 = ((dK_ClassA102) field420.get(var16));
            double var19 = var18.field409 - var3.field_1352;
            double var21 = var18.field410 - var3.field_1351;
            double var23 = var18.field411 - var3.field_1350;
            double var25 = var19 * var19 + var21 * var21 + var23 * var23;
            if (var25 <= 900.0) {
                if (var19 * var10 + var21 * var12 + var23 * var14 >= -1.0) {
                    var18.ei = method805(var18);
                    if (var18.ei >= 0.009999999776482582f) {
                        field421.add(var18);
                    }
                }
            }
            ++var16;
            continue;
        }
        if (!field421.isEmpty()) {
            RenderSystem.enableBlend();
            RenderSystem.disableCull();
            RenderSystem.disableDepthTest();
            RenderSystem.depthMask(-1238940123 ^ -1238940123);
            RenderSystem.blendFuncSeparate(class_4535.SRC_ALPHA, class_4534.ONE, class_4535.ZERO, class_4534.ONE);
            RenderSystem.setShader(class_10142.field_53880);
            RenderSystem.setShaderTexture(1792699606 ^ 1792699606, field413);
            method804(var2, var4, var3, var5, var6);
            RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE_MINUS_SRC_ALPHA);
            RenderSystem.setShader(class_10142.field_53876);
            boolean var16 = field419.method696(Decryptor.method1945(XorDecoder.method1946("\nr5MJJ)Zë,JaºJ\u0011C\u0005\tZãA", 587989427 ^ 1607604936)));
            boolean var17 = field419.method696(Decryptor.method1945(XorDecoder.method1946("ì' îxå§É_aàÝF»Yé¼ áeç¾ÌVçþVåþQì", -1439536790 ^ 2065875645)));
            if (var16 != 0) {
                method806(var2, var3, var5);
            }
            if (var17 != 0) {
                method807(var2, var3, var5);
            }
            RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE);
            if (var16 == 0) {
                if (var17 != 0) {
                    method811(var2, var3, var5);
                }
            } else {
                method810(var2, var3, var5);
            }
            RenderSystem.depthMask(-1048278955 ^ -1048278956);
            RenderSystem.enableCull();
            RenderSystem.defaultBlendFunc();
            RenderSystem.disableBlend();
            return;
        } else {
            return;
        }
    }

  private void method804(class_4587 arg0, class_4184 arg1, class_243 arg2, float arg3, float arg4) { // было: a
        class_287 var6 = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
        int var7 = -1523855382 ^ -1523855382;
        int var8 = field421.size();
        while (var7 < var8) {
            dK_ClassA102 var9 = ((dK_ClassA102) field421.get(var7));
            float var10 = var9.ei;
            arg0.method_22903();
            arg0.method_46416(((float) (var9.field409 - arg2.field_1352)), ((float) (var9.field410 - arg2.field_1351)), ((float) (var9.field411 - arg2.field_1350)));
            arg0.method_22907(class_7833.field_40716.rotationDegrees(-arg1.method_19330()));
            arg0.method_22907(class_7833.field_40714.rotationDegrees(arg1.method_19329()));
            Matrix4f var11 = arg0.method_23760().method_23761();
            int var12 = -1066577935 ^ -1066577935;
            while (var12 < (1399602711 ^ 1399602708)) {
                float var13 = arg3 * field416[var12] * arg4;
                float var14 = var10 * field417[var12] * arg4;
                float var15 = var13 * 0.5f;
                var6.method_22918(var11, -var15, var15, 0.0f).method_22913(0.0f, 1.0f).method_22915(dU, dV, dW, var14);
                var6.method_22918(var11, var15, var15, 0.0f).method_22913(1.0f, 1.0f).method_22915(dU, dV, dW, var14);
                var6.method_22918(var11, var15, -var15, 0.0f).method_22913(1.0f, 0.0f).method_22915(dU, dV, dW, var14);
                var6.method_22918(var11, -var15, -var15, 0.0f).method_22913(0.0f, 0.0f).method_22915(dU, dV, dW, var14);
                ++var12;
                continue;
            }
            arg0.method_22909();
            ++var7;
            continue;
        }
        class_286.method_43433(var6.method_60800());
    }

  private float method805(dK_ClassA102 arg0) { // было: a
        float var2 = class_3532.method_15363(((float) arg0.tA) / ((float) arg0.tB), 0.0f, 1.0f);
        float var3 = Math.min(1.0f, ((float) (arg0.tB - arg0.tA)) / 20.0f);
        return var2 * var3;
    }

  private void method806(class_4587 arg0, class_243 arg1, float arg2) { // было: a
        class_287 var4;
        int var5;
        int var6;
        if (az()) {
            var4 = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1576);
            var5 = -1465714797 ^ -1465714797;
            var6 = field421.size();
        } else {
            return;
        }
        while (var5 < var6) {
            dK_ClassA102 var7 = ((dK_ClassA102) field421.get(var5));
            float var8 = var7.ei * 0.4000000059604645f;
            if (var8 >= 0.009999999776482582f) {
                arg0.method_22903();
                arg0.method_46416(((float) (var7.field409 - arg1.field_1352)), ((float) (var7.field410 - arg1.field_1351)), ((float) (var7.field411 - arg1.field_1350)));
                arg0.method_22907(class_7833.field_40714.rotationDegrees(var7.ea));
                arg0.method_22907(class_7833.field_40716.rotationDegrees(var7.eb));
                arg0.method_22907(class_7833.field_40718.rotationDegrees(var7.ec));
                method808(var4, arg0.method_23760().method_23761(), arg2, var8);
                arg0.method_22909();
            }
            ++var5;
            continue;
        }
        class_286.method_43433(var4.method_60800());
    }

  private void method807(class_4587 arg0, class_243 arg1, float arg2) { // было: b
        class_287 var4;
        int var5;
        int var6;
        if (az()) {
            var4 = class_289.method_1348().method_60827(class_5596.field_27379, class_290.field_1576);
            var5 = 2134161551 ^ 2134161551;
            var6 = field421.size();
        } else {
            return;
        }
        while (var5 < var6) {
            dK_ClassA102 var7 = ((dK_ClassA102) field421.get(var5));
            float var8 = var7.ei * 0.4000000059604645f;
            if (var8 >= 0.009999999776482582f) {
                arg0.method_22903();
                arg0.method_46416(((float) (var7.field409 - arg1.field_1352)), ((float) (var7.field410 - arg1.field_1351)), ((float) (var7.field411 - arg1.field_1350)));
                arg0.method_22907(class_7833.field_40714.rotationDegrees(var7.ea));
                arg0.method_22907(class_7833.field_40716.rotationDegrees(var7.eb));
                arg0.method_22907(class_7833.field_40718.rotationDegrees(var7.ec));
                method809(var4, arg0.method_23760().method_23761(), arg2, var8);
                arg0.method_22909();
            }
            ++var5;
            continue;
        }
        class_286.method_43433(var4.method_60800());
    }

  private boolean az() {
        int var1 = -1746197645 ^ -1746197645;
        int var2 = field421.size();
        while (true) {
            if (var1 >= var2) {
                return 2135494243 ^ 2135494243;
            }
            if ((((dK_ClassA102) field421.get(var1))).ei >= 0.02500000037252903f) {
                break;
            }
            ++var1;
            continue;
        }
        return 1179270668 ^ 1179270669;
    }

  private void method808(class_287 arg0, Matrix4f arg1, float arg2, float arg3) { // было: a
        arg0.method_22918(arg1, -arg2, -arg2, arg2).method_22915(dU, dV, dW, arg3);
        arg0.method_22918(arg1, arg2, -arg2, arg2).method_22915(dU, dV, dW, arg3);
        arg0.method_22918(arg1, arg2, arg2, arg2).method_22915(dU, dV, dW, arg3);
        arg0.method_22918(arg1, -arg2, arg2, arg2).method_22915(dU, dV, dW, arg3);
        arg0.method_22918(arg1, arg2, -arg2, -arg2).method_22915(dU, dV, dW, arg3);
        arg0.method_22918(arg1, -arg2, -arg2, -arg2).method_22915(dU, dV, dW, arg3);
        arg0.method_22918(arg1, -arg2, arg2, -arg2).method_22915(dU, dV, dW, arg3);
        arg0.method_22918(arg1, arg2, arg2, -arg2).method_22915(dU, dV, dW, arg3);
        arg0.method_22918(arg1, -arg2, arg2, arg2).method_22915(dU, dV, dW, arg3);
        arg0.method_22918(arg1, arg2, arg2, arg2).method_22915(dU, dV, dW, arg3);
        arg0.method_22918(arg1, arg2, arg2, -arg2).method_22915(dU, dV, dW, arg3);
        arg0.method_22918(arg1, -arg2, arg2, -arg2).method_22915(dU, dV, dW, arg3);
        arg0.method_22918(arg1, -arg2, -arg2, -arg2).method_22915(dU, dV, dW, arg3);
        arg0.method_22918(arg1, arg2, -arg2, -arg2).method_22915(dU, dV, dW, arg3);
        arg0.method_22918(arg1, arg2, -arg2, arg2).method_22915(dU, dV, dW, arg3);
        arg0.method_22918(arg1, -arg2, -arg2, arg2).method_22915(dU, dV, dW, arg3);
        arg0.method_22918(arg1, arg2, -arg2, arg2).method_22915(dU, dV, dW, arg3);
        arg0.method_22918(arg1, arg2, -arg2, -arg2).method_22915(dU, dV, dW, arg3);
        arg0.method_22918(arg1, arg2, arg2, -arg2).method_22915(dU, dV, dW, arg3);
        arg0.method_22918(arg1, arg2, arg2, arg2).method_22915(dU, dV, dW, arg3);
        arg0.method_22918(arg1, -arg2, -arg2, -arg2).method_22915(dU, dV, dW, arg3);
        arg0.method_22918(arg1, -arg2, -arg2, arg2).method_22915(dU, dV, dW, arg3);
        arg0.method_22918(arg1, -arg2, arg2, arg2).method_22915(dU, dV, dW, arg3);
        arg0.method_22918(arg1, -arg2, arg2, -arg2).method_22915(dU, dV, dW, arg3);
    }

  private void method809(class_287 arg0, Matrix4f arg1, float arg2, float arg3) { // было: b
        float var5 = arg2;
        float var6 = -arg2;
        float var7 = arg2 * 0.8659999966621399f;
        arg0.method_22918(arg1, 0.0f, var5, 0.0f).method_22915(dU, dV, dW, arg3);
        arg0.method_22918(arg1, -var7, var6, var7).method_22915(dU, dV, dW, arg3);
        arg0.method_22918(arg1, var7, var6, var7).method_22915(dU, dV, dW, arg3);
        arg0.method_22918(arg1, 0.0f, var5, 0.0f).method_22915(dU, dV, dW, arg3);
        arg0.method_22918(arg1, var7, var6, var7).method_22915(dU, dV, dW, arg3);
        arg0.method_22918(arg1, var7, var6, -var7).method_22915(dU, dV, dW, arg3);
        arg0.method_22918(arg1, 0.0f, var5, 0.0f).method_22915(dU, dV, dW, arg3);
        arg0.method_22918(arg1, var7, var6, -var7).method_22915(dU, dV, dW, arg3);
        arg0.method_22918(arg1, -var7, var6, -var7).method_22915(dU, dV, dW, arg3);
        arg0.method_22918(arg1, 0.0f, var5, 0.0f).method_22915(dU, dV, dW, arg3);
        arg0.method_22918(arg1, -var7, var6, -var7).method_22915(dU, dV, dW, arg3);
        arg0.method_22918(arg1, -var7, var6, var7).method_22915(dU, dV, dW, arg3);
        arg0.method_22918(arg1, -var7, var6, var7).method_22915(dU, dV, dW, arg3);
        arg0.method_22918(arg1, var7, var6, var7).method_22915(dU, dV, dW, arg3);
        arg0.method_22918(arg1, var7, var6, -var7).method_22915(dU, dV, dW, arg3);
        arg0.method_22918(arg1, -var7, var6, var7).method_22915(dU, dV, dW, arg3);
        arg0.method_22918(arg1, var7, var6, -var7).method_22915(dU, dV, dW, arg3);
        arg0.method_22918(arg1, -var7, var6, -var7).method_22915(dU, dV, dW, arg3);
    }

  private void method810(class_4587 arg0, class_243 arg1, float arg2) { // было: c
        class_287 var4 = class_289.method_1348().method_60827(class_5596.field_29344, class_290.field_1576);
        int var5 = -1296096140 ^ -1296096140;
        int var6 = -1169404721 ^ -1169404721;
        int var7 = field421.size();
        while (var6 < var7) {
            dK_ClassA102 var8 = ((dK_ClassA102) field421.get(var6));
            float var9 = var8.ei;
            arg0.method_22903();
            arg0.method_46416(((float) (var8.field409 - arg1.field_1352)), ((float) (var8.field410 - arg1.field_1351)), ((float) (var8.field411 - arg1.field_1350)));
            arg0.method_22907(class_7833.field_40714.rotationDegrees(var8.ea));
            arg0.method_22907(class_7833.field_40716.rotationDegrees(var8.eb));
            arg0.method_22907(class_7833.field_40718.rotationDegrees(var8.ec));
            var5 = var5 + method812(var4, arg0.method_23760().method_23761(), arg2, var9);
            arg0.method_22909();
            ++var6;
            continue;
        }
        if (var5 > 0) {
            class_286.method_43433(var4.method_60800());
        }
    }

  private void method811(class_4587 arg0, class_243 arg1, float arg2) { // было: d
        class_287 var4 = class_289.method_1348().method_60827(class_5596.field_29344, class_290.field_1576);
        int var5 = -287825156 ^ -287825156;
        int var6 = -1761042588 ^ -1761042588;
        int var7 = field421.size();
        while (var6 < var7) {
            dK_ClassA102 var8 = ((dK_ClassA102) field421.get(var6));
            float var9 = var8.ei;
            arg0.method_22903();
            arg0.method_46416(((float) (var8.field409 - arg1.field_1352)), ((float) (var8.field410 - arg1.field_1351)), ((float) (var8.field411 - arg1.field_1350)));
            arg0.method_22907(class_7833.field_40714.rotationDegrees(var8.ea));
            arg0.method_22907(class_7833.field_40716.rotationDegrees(var8.eb));
            arg0.method_22907(class_7833.field_40718.rotationDegrees(var8.ec));
            var5 = var5 + method813(var4, arg0.method_23760().method_23761(), arg2, var9);
            arg0.method_22909();
            ++var6;
            continue;
        }
        if (var5 > 0) {
            class_286.method_43433(var4.method_60800());
        }
    }

  private int method812(class_287 arg0, Matrix4f arg1, float arg2, float arg3) { // было: a
        float __stk1;
        int __stk2;
        int var5 = method817(Math.min(1.0f, dU * 1.5f), Math.min(1.0f, dV * 1.5f), Math.min(1.0f, dW * 1.5f), arg3);
        float var6 = arg2 * 0.30000001192092896f;
        float var7 = arg2 * 0.25f;
        int var8 = 1316910830 ^ 1316910830;
        byte[][] var9 = field414;
        int var10 = var9.length;
        int var11 = -1293814356 ^ -1293814356;
        while (var11 < var10) {
            Object var12 = var9[var11];
            float var13 = ((float) var12[1174180282 ^ 1174180282]) * arg2;
            float var14 = ((float) var12[740273709 ^ 740273708]) * arg2;
            float var15 = ((float) var12[269108032 ^ 269108034]) * arg2;
            float var16 = ((float) var12[-729878336 ^ -729878333]) * arg2;
            float var17 = ((float) var12[-5435005 ^ -5435001]) * arg2;
            float var18 = ((float) var12[558727516 ^ 558727513]) * arg2;
            float var19 = var16 - var13;
            float var20 = var17 - var14;
            float var21 = var18 - var15;
            float var22 = class_3532.method_15355(var19 * var19 + var20 * var20 + var21 * var21);
            if (var22 >= 0.0010000000474974513f) {
                float var23 = var19 / var22;
                float var24 = var20 / var22;
                float var25 = var21 / var22;
                float var26 = 0.0f;
                int var27 = -834341086 ^ -834341085;
                while (var26 < var22) {
                    __stk1 = var27 == 0 ? var7 : var6;
                    float var28 = __stk1;
                    float var29 = Math.min(var26 + var28, var22);
                    if (var27 != 0) {
                        arg0.method_22918(arg1, var13 + var23 * var26, var14 + var24 * var26, var15 + var25 * var26).method_39415(var5);
                        arg0.method_22918(arg1, var13 + var23 * var29, var14 + var24 * var29, var15 + var25 * var29).method_39415(var5);
                        ++var8;
                    }
                    var26 = var29;
                    __stk2 = var27 != 0 ? -214752212 ^ -214752212 : 1846602780 ^ 1846602781;
                    var27 = __stk2;
                    continue;
                }
            }
            ++var11;
            continue;
        }
        return var8;
    }

  private int method813(class_287 arg0, Matrix4f arg1, float arg2, float arg3) { // было: b
        float __stk1;
        float __stk2;
        float __stk3;
        int __stk4;
        int var5 = method817(Math.min(1.0f, dU * 1.5f), Math.min(1.0f, dV * 1.5f), Math.min(1.0f, dW * 1.5f), arg3);
        float var6 = arg2 * 0.30000001192092896f;
        float var7 = arg2 * 0.25f;
        int var8 = -232898071 ^ -232898071;
        float var9 = arg2;
        float var10 = -arg2;
        float var11 = arg2 * 0.8659999966621399f;
        byte[][] var12 = field415;
        int var13 = var12.length;
        int var14 = 1310992761 ^ 1310992761;
        while (var14 < var13) {
            Object var15 = var12[var14];
            float var16 = method814(var15[-279047552 ^ -279047552], var11);
            __stk1 = var15[-1914160032 ^ -1914160032] != 0 ? var10 : var9;
            float var17 = __stk1;
            float var18 = method815(var15[1216396232 ^ 1216396232], var11);
            float var19 = method814(var15[1592299874 ^ 1592299875], var11);
            __stk2 = var15[1685508135 ^ 1685508134] != 0 ? var10 : var9;
            float var20 = __stk2;
            float var21 = method815(var15[122697325 ^ 122697324], var11);
            float var22 = var19 - var16;
            float var23 = var20 - var17;
            float var24 = var21 - var18;
            float var25 = class_3532.method_15355(var22 * var22 + var23 * var23 + var24 * var24);
            if (var25 >= 0.0010000000474974513f) {
                float var26 = var22 / var25;
                float var27 = var23 / var25;
                float var28 = var24 / var25;
                float var29 = 0.0f;
                int var30 = 1671369643 ^ 1671369642;
                while (var29 < var25) {
                    __stk3 = var30 == 0 ? var7 : var6;
                    float var31 = __stk3;
                    float var32 = Math.min(var29 + var31, var25);
                    if (var30 != 0) {
                        arg0.method_22918(arg1, var16 + var26 * var29, var17 + var27 * var29, var18 + var28 * var29).method_39415(var5);
                        arg0.method_22918(arg1, var16 + var26 * var32, var17 + var27 * var32, var18 + var28 * var32).method_39415(var5);
                        ++var8;
                    }
                    var29 = var32;
                    __stk4 = var30 != 0 ? 1530048176 ^ 1530048176 : -818943618 ^ -818943617;
                    var30 = __stk4;
                    continue;
                }
            }
            ++var14;
            continue;
        }
        return var8;
    }

  private float method814(int arg0, float arg1) { // было: a
        float __stk1;
        switch (arg0) {
            case 1:
            case 4:
                __stk1 = -arg1;
                break;
            case 2:
            case 3:
                __stk1 = arg1;
                break;
            default:
                __stk1 = 0.0f;
        }
        return __stk1;
    }

  private float method815(int arg0, float arg1) { // было: b
        float __stk1;
        switch (arg0) {
            case 1:
            case 2:
                __stk1 = arg1;
                break;
            case 3:
            case 4:
                __stk1 = -arg1;
                break;
            default:
                __stk1 = 0.0f;
        }
        return __stk1;
    }

  private dK_ClassA102 method816() { // было: a
        int __stk1;
        double __stk2;
        float var1 = 12.0f;
        boolean var2 = field418.method696(Decryptor.method1945(XorDecoder.method1946("õ3\\Qô46Jµ\u0010+\u0010¶2RL¸\u0012qfÓ(9\u001e", -2100008816 ^ -1580189935)));
        __stk1 = !var2 ? (-1341489666 ^ -1341490086) + field422.nextInt(515511565 ^ 515511465) : (606897950 ^ 606897690) + field422.nextInt(36389812 ^ 36389736);
        int var3 = __stk1;
        double var4 = mc.field_1724.method_23317() + (field422.nextDouble() * 2.0 - 1.0) * ((double) var1);
        __stk2 = !var2 ? mc.field_1724.method_23318() + 2.0 + field422.nextDouble() * ((double) var1) * 0.8 : mc.field_1724.method_23318() + 4.0 + field422.nextDouble() * ((double) var1) * 0.55;
        double var6 = __stk2;
        double var8 = mc.field_1724.method_23321() + (field422.nextDouble() * 2.0 - 1.0) * ((double) var1);
        float var10 = am.bp();
        float var11;
        float var12;
        float var13;
        if (!var2) {
            float var14 = field422.nextFloat() * 360.0f;
            float var15 = (0.009999999776482582f + field422.nextFloat() * 0.019999999552965164f) * var10;
            var11 = -class_3532.method_15374(((float) Math.toRadians(((double) var14)))) * var15;
            var13 = class_3532.method_15362(((float) Math.toRadians(((double) var14)))) * var15;
            var12 = (field422.nextFloat() - 0.5f) * 0.009999999776482582f * var10;
        } else {
            var11 = (field422.nextFloat() - 0.5f) * 0.00800000037997961f * var10;
            var12 = (-0.012000000104308128f - field422.nextFloat() * 0.012000000104308128f) * var10;
            var13 = (field422.nextFloat() - 0.5f) * 0.00800000037997961f * var10;
        }
        return new dK_ClassA102(var4, ((Double) var6), var8, var11, var12, var13, field422.nextFloat() * 360.0f, field422.nextFloat() * 360.0f, field422.nextFloat() * 360.0f, (field422.nextFloat() - 0.5f) * 1.5f, (field422.nextFloat() - 0.5f) * 1.5f, (field422.nextFloat() - 0.5f) * 1.5f, ((Integer) var3), ((float) (field422.nextDouble() * 3.141592653589793 * 2.0)), field422.nextFloat() * 10.0f);
    }

  private static int method817(float arg0, float arg1, float arg2, float arg3) { // было: a
        return ((int) (arg3 * 255.0f)) << (-622361653 ^ -622361645) | ((int) (arg0 * 255.0f)) << (-1209495664 ^ -1209495680) | ((int) (arg1 * 255.0f)) << (101790310 ^ 101790318) | ((int) (arg2 * 255.0f));
    }

  private static int qP(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int qQ(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int qR(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}