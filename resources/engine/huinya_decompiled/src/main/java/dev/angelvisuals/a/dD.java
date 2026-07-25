// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.dd
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.angelvisuals.AngelVisuals;
import dev.angelvisuals.a.aZ;
import dev.angelvisuals.a.bA;
import dev.angelvisuals.a.bI;
import dev.angelvisuals.a.be;
import dev.angelvisuals.a.bl;
import dev.angelvisuals.a.bp;
import dev.angelvisuals.a.cF;
import dev.angelvisuals.a.cK;
import dev.angelvisuals.a.ch;
import net.minecraft.class_10366;
import net.minecraft.class_1041;
import net.minecraft.class_284;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_293.class_5596;
import net.minecraft.class_310;
import net.minecraft.class_315;
import net.minecraft.class_4184;
import net.minecraft.class_7172;
import net.minecraft.class_757;
import org.joml.Matrix4f;
import org.joml.Matrix4fStack;

@bI(name = "CustomSky", a = "RENDER", I = "Красивый кастомный шейдер неба")
public class dd extends cK implements cF {

    // ---- поля ----
  public static final dd field428; // было: a
  public final aZ field429; // было: h
  public final bA field430; // было: V
  public final bA field431; // было: W
  public final bA field432; // было: X
  public final bA field433; // было: Y
  private static final be field434; // было: l
  private static final be field435; // было: m
  private static final be field436; // было: n
  private long field437; // было: L
  private static final String Ad = "// number obfuscation: ENABLED (XOR masking)";
  private static final String Ae = "// number obfuscation: ENABLED (XOR masking)";
  private static final String Af = "Protected by t.me/JoinerClient";
  private static final String Ag = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final String Ah = "// nice try. closed source for a reason.";
  private static final int pt = -1197186727;
  private static final int pu = -1362017307;
  private static final int pv = -705863044;
  private static final byte[] dU;

    static {
        dU = ";mAzlPXwP}uJz2r,OEI_0H|;DRG4F^,uhj_6K:ue>t!zGXgq.kg}N4A,aFEYOefrWGyc-:O,]*D%\\Viai*KvJ{1HKTOO*Nf-Q<6>%`LBjPUdA@n`p>}Re&Cf1@zJu@$6y6 -{et\\^dj>SJqdCEs$[*LotfAq_p-YV:6vL[JRU#O^A=Uom'2T\"X45'ti_U1>J4f;joWbJFYU)@OK:F(\\lPVzbu3=gDZk-S_vmf/`'DH2<q5LDnX=B=/q=^h,~fE<6".getBytes("ISO-8859-1");
        field428 = new dd();
        field434 = new be(AngelVisuals.id(Decryptor.method1945(XorDecoder.method1946("ç8\u00105\u001a¸®$Kµ\u007f\u0019¿;rä®i\u0015", 617211687 ^ 211618039))), class_290.field_1592);
        field435 = new be(AngelVisuals.id(Decryptor.method1945(XorDecoder.method1946("Ó¶x\u0004®£&Mý ]%Ô\\FÁªB^õ­!'á¤a8Ýz\u0001ÑV\u0018¸a\u0002ä@H", 246230457 ^ 2075979566))), class_290.field_1592);
        field436 = new be(AngelVisuals.id(Decryptor.method1945(XorDecoder.method1946("æ«/âðt¬ÁçwÇÿø\u0002Ú²&úá\u0002Áú\u0010 áê\u0018íÉvÜé\u0016ó\u0011¨", 644232910 ^ -1289296540))), class_290.field_1592);
    }

  private dd() { // было: <init>
        super();
        String[] __obj1 = new String[-1230386562 ^ -1230386563];
        __obj1[-381903705 ^ -381903705] = Decryptor.method1945(XorDecoder.method1946("Õ¡\u0006xÉÁ\u0015mã\u001bBé³\u0015>Ó¢+\u007fØ¥_5", -954713306 ^ -814035043));
        __obj1[596287714 ^ 596287715] = Decryptor.method1945(XorDecoder.method1946("·Ín.®Á.Xñ0Yä¸34ÕÁ\u0018\u000eÞb\\", -364193289 ^ -1961546389));
        __obj1[-147374441 ^ -147374443] = Decryptor.method1945(XorDecoder.method1946("\u000cS] \u0014S\u0014\u0012o^]\u0011mG\u0002:qN<\ntgSu", -1365047617 ^ -422764392));
        field429 = new aZ(Decryptor.method1945(XorDecoder.method1946("T\u0014áù\u0001*æÜW\u0010©\u001e\u0018ò®k:çàE=¥", -1340409951 ^ 683182992)), __obj1);
        field430 = new bA(Decryptor.method1945(XorDecoder.method1946("q÷4Fè(xÞ��s Ý&NÂzc?bßyw¤ð|P¡\u001ekû\nxÏq", -296238629 ^ -1561381382)), 1.0f, 0.10000000149011612f, 5.0f, 0.10000000149011612f);
        field431 = new bA(Decryptor.method1945(XorDecoder.method1946("[æ¹]]ÊZSÆÔyBÇ~yåÓZNË¯\u0006", 41781381 ^ 971977885)), 5.0f, 1.0f, 20.0f, 0.5f);
        field432 = new bA(Decryptor.method1945(XorDecoder.method1946("\u0016\"]V]0]\u001eg\u0016|Vp\">U\u007f\u001aAum;C\u0010i\u0014]mV-#ac%'u\u001d,%\u0017\u0015VP\u001b", -2058377393 ^ -1554332566)), 0.009999999776482582f, 0.0010000000474974513f, 0.05000000074505806f, 0.0010000000474974513f);
        field433 = new bA(Decryptor.method1945(XorDecoder.method1946("öýÿ÷Å¸ÍÕ×ÿÜÞõàÊæÚö×þµæÎäêõ«î÷âæÐóÌ", -1704344988 ^ 1809546179)), 1.0f, 0.30000001192092896f, 1.0f, 0.05000000074505806f);
        field437 = 872755601078944L ^ -872755601078945L;
    }

  public void method821() { // было: j
        field437 = System.currentTimeMillis();
        super.method610();
    }

  public void method822() { // было: k
        field437 = 8686177184755902849L ^ -8686177184755902850L;
        super.method611();
    }

  public void aV() {
        if (mc.field_1724 == null) {
            return;
        } else {
            if (mc.field_1687 != null) {
                if (field437 < (-7976736174558005615L ^ -7976736174558005615L)) {
                    field437 = System.currentTimeMillis();
                }
                float var1 = ((float) (System.currentTimeMillis() - field437)) / 1000.0f;
                float var2 = ((float) mc.method_22683().method_4489());
                float var3 = ((float) mc.method_22683().method_4506());
                bp var4 = AngelVisuals.getInstance().getThemeManager().method481().method449();
                bp var5 = AngelVisuals.getInstance().getThemeManager().method481().method457();
                float var6 = ((float) var4.method1695()) / 255.0f;
                float var7 = ((float) var4.method1696()) / 255.0f;
                float var8 = ((float) var4.method1697()) / 255.0f;
                float var9 = ((float) var5.method1695()) / 255.0f;
                float var10 = ((float) var5.method1696()) / 255.0f;
                float var11 = ((float) var5.method1697()) / 255.0f;
                be var12;
                if (!field429.method696(Decryptor.method1945(XorDecoder.method1946("£0:©»0sÀ=:Â$e³Þ-[Û\u00044ü", 86668082 ^ -1004325190)))) {
                    var12 = !field429.method696(Decryptor.method1945(XorDecoder.method1946("&\u007fé?s©ý`(·üu\n´Ds«O1åù", -1438656816 ^ 1860669661))) ? field434 : field435;
                } else {
                    var12 = field436;
                }
                if (var12 == null) {
                    return;
                } else {
                    if (var12.method1768()) {
                        var12.method1767();
                        class_4184 var13 = mc.field_1773.method_19418();
                        float var14 = ((float) Math.toRadians(((double) -var13.method_19330())));
                        float var15 = ((float) Math.toRadians(((double) var13.method_19329())));
                        float var16 = ((float) (((Integer) mc.field_1690.method_41808().method_41753())).intValue());
                        if (!field429.method696(Decryptor.method1945(XorDecoder.method1946("\u001eTÑH\u0006Tz}YÑy\u007f@RcI°bf`ß\u001d", 1768130242 ^ 1233233911)))) {
                            float[] __obj8 = new float[-2081890959 ^ -2081890960];
                            __obj8[-338445463 ^ -338445463] = var1;
                            method823(var12, Decryptor.method1945(XorDecoder.method1946("°ÑôÈ´ÃòâñÁõÒÖÐþÊ³üØçôª", -468369192 ^ 1941816190)), __obj8);
                            float[] __obj9 = new float[912452024 ^ 912452026];
                            __obj9[960558881 ^ 960558881] = var2;
                            __obj9[901192 ^ 901193] = var3;
                            method823(var12, Decryptor.method1945(XorDecoder.method1946("\u0003\u0006Þf!<øcg\nÄJ<|ÍOm\u001a¸_\u00164³\u0016", 727645980 ^ 13675592)), __obj9);
                            float[] __obj10 = new float[-1895412511 ^ -1895412510];
                            __obj10[555251745 ^ 555251745] = var6;
                            __obj10[-1687252445 ^ -1687252446] = var7;
                            __obj10[1454507496 ^ 1454507498] = var8;
                            method823(var12, Decryptor.method1945(XorDecoder.method1946("jA°ÂO^²m~gSºg[º\u0018,", -1346028746 ^ -1092566839)), __obj10);
                            float[] __obj11 = new float[-1980456241 ^ -1980456242];
                            __obj11[-624061807 ^ -624061807] = field433.bp();
                            method823(var12, Decryptor.method1945(XorDecoder.method1946("\\z7ü\u0007H\u0014©\u0002v\u000cÐ8C6Ú\u0010U\u0018ë-}}¦", 1345445921 ^ -881734583)), __obj11);
                            float[] __obj12 = new float[473569792 ^ 473569793];
                            __obj12[1360434477 ^ 1360434477] = field430.bp();
                            method823(var12, Decryptor.method1945(XorDecoder.method1946("\nOX\u0013xH½Q]n÷\u001emYHt^+\u0006\u0011", 1527556068 ^ 2000149793)), __obj12);
                            float[] __obj13 = new float[-1463859199 ^ -1463859200];
                            __obj13[2142945882 ^ 2142945882] = field431.bp();
                            method823(var12, Decryptor.method1945(XorDecoder.method1946("TÒ«b[±üM\u0003ñ¹v\u0002´çm\u007fâën\u000fÒî\u0018", 366374878 ^ 805694697)), __obj13);
                            float[] __obj14 = new float[1025561435 ^ 1025561434];
                            __obj14[638142595 ^ 638142595] = field432.bp();
                            method823(var12, Decryptor.method1945(XorDecoder.method1946("óg|Xø@S\u007fÒ$aPÓD\u007f\u007fËgx\u0002äD\u0016\u000e", -149945924 ^ -1002761186)), __obj14);
                            float[] __obj15 = new float[-458271277 ^ -458271279];
                            __obj15[1292102222 ^ 1292102222] = var14;
                            __obj15[-1422232920 ^ -1422232919] = var15;
                            method823(var12, Decryptor.method1945(XorDecoder.method1946("PhVz\u0007T\u0008\u000fsCYXdc\u0007oyD^_a@S\u0003", -1272793443 ^ -1974687831)), __obj15);
                            float[] __obj16 = new float[1254326137 ^ 1254326136];
                            __obj16[34637334 ^ 34637334] = var16;
                            method823(var12, Decryptor.method1945(XorDecoder.method1946("$ÿjn(ÁVuCÉPDaÜ_q\\Áp[{ù8\u001f", 1770289243 ^ 1266808394)), __obj16);
                        } else {
                            float[] __obj1 = new float[-1948651692 ^ -1948651696];
                            __obj1[-1829133853 ^ -1829133853] = var6;
                            __obj1[1491696699 ^ 1491696698] = var7;
                            __obj1[-1863621791 ^ -1863621789] = var8;
                            __obj1[-358317752 ^ -358317749] = field433.bp();
                            method823(var12, Decryptor.method1945(XorDecoder.method1946("ÑdNvÖ\u000eFyòX}\u0013È\u0018C\u0010­\u0012(R×F:\u001c", -542872287 ^ -22852422)), __obj1);
                            float[] __obj2 = new float[-465173554 ^ -465173558];
                            __obj2[-1490970718 ^ -1490970718] = var9;
                            __obj2[-107090260 ^ -107090259] = var10;
                            __obj2[195133044 ^ 195133046] = var11;
                            __obj2[-246372959 ^ -246372958] = field433.bp();
                            method823(var12, Decryptor.method1945(XorDecoder.method1946("-¬Ø\u0006\u007f±§+>ª-3º\u00077Â+gÎQ", 1358043067 ^ 1006752495)), __obj2);
                            float[] __obj3 = new float[1568983318 ^ 1568983316];
                            __obj3[783255026 ^ 783255026] = var2;
                            __obj3[-418592174 ^ -418592173] = var3;
                            method823(var12, Decryptor.method1945(XorDecoder.method1946("Æ\u0007 ëß`Jçü:$ó9*Ñ¿\u007f&ùø\u001a.", 1055083647 ^ -1611659276)), __obj3);
                            float[] __obj4 = new float[-1009113282 ^ -1009113281];
                            __obj4[1101755161 ^ 1101755161] = field431.bp();
                            method823(var12, Decryptor.method1945(XorDecoder.method1946("s¿æO ³¦l¤å\"«ï¹n³ï¤4Å", 1039838855 ^ -973898416)), __obj4);
                            float[] __obj5 = new float[-2051010183 ^ -2051010184];
                            __obj5[1035726544 ^ 1035726544] = var1 * field430.bp();
                            method823(var12, Decryptor.method1945(XorDecoder.method1946("Y²Oåo S\u0001@¾F·\u0017£\u001e¥rå]Ü\u001e", -714039815 ^ -158222550)), __obj5);
                            float[] __obj6 = new float[1593079696 ^ 1593079697];
                            __obj6[1616471697 ^ 1616471697] = var16;
                            method823(var12, Decryptor.method1945(XorDecoder.method1946("Ï]\u0015ç¹7=î<5ßË4BÆ×5\u0003Ý¹YF", 1841299671 ^ 379910226)), __obj6);
                            float[] __obj7 = new float[-1036614554 ^ -1036614556];
                            __obj7[1453095093 ^ 1453095093] = var14;
                            __obj7[-79805449 ^ -79805450] = var15;
                            method823(var12, Decryptor.method1945(XorDecoder.method1946("i\u0011+ì\u001b9\u001aÎ\u0018*!Ú\u00047)ö9\u0011'Î+-W", -1424422386 ^ 326334035)), __obj7);
                        }
                        Matrix4f var17 = new Matrix4f(RenderSystem.getProjectionMatrix());
                        RenderSystem.setProjectionMatrix(new Matrix4f(), class_10366.field_54954);
                        RenderSystem.getModelViewStack().pushMatrix();
                        RenderSystem.getModelViewStack().identity();
                        RenderSystem.enableBlend();
                        RenderSystem.defaultBlendFunc();
                        RenderSystem.enableDepthTest();
                        RenderSystem.depthFunc(-331775674 ^ -331775163);
                        RenderSystem.depthMask(-1822501657 ^ -1822501657);
                        RenderSystem.disableCull();
                        Matrix4f var18 = new Matrix4f();
                        class_287 var19 = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1592);
                        var19.method_22918(var18, -1.0f, -1.0f, 0.0f);
                        var19.method_22918(var18, 1.0f, -1.0f, 0.0f);
                        var19.method_22918(var18, 1.0f, 1.0f, 0.0f);
                        var19.method_22918(var18, -1.0f, 1.0f, 0.0f);
                        class_286.method_43433(var19.method_60800());
                        RenderSystem.getModelViewStack().popMatrix();
                        RenderSystem.depthMask(66767420 ^ 66767421);
                        RenderSystem.enableCull();
                        RenderSystem.disableBlend();
                        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
                        RenderSystem.depthFunc(-624517946 ^ -624517435);
                        RenderSystem.setProjectionMatrix(var17, class_10366.field_54953);
                        return;
                    } else {
                        return;
                    }
                }
            } else {
                return;
            }
        }
    }

  private void method823(be arg0, String arg1, float[] arg2) { // было: c
        class_284 var4 = arg0.method1770(arg1);
        if (var4 != null) {
            if (arg2.length != (56627220 ^ 56627221)) {
                if (arg2.length != (-136019084 ^ -136019082)) {
                    if (arg2.length != (1111975821 ^ 1111975822)) {
                        if (arg2.length == (28170825 ^ 28170829)) {
                            var4.method_35657(arg2[-1291601048 ^ -1291601048], arg2[-1563402140 ^ -1563402139], arg2[-1945144465 ^ -1945144467], arg2[435658932 ^ 435658935]);
                        }
                    } else {
                        var4.method_1249(arg2[1451777047 ^ 1451777047], arg2[1355509030 ^ 1355509031], arg2[1667162889 ^ 1667162891]);
                    }
                } else {
                    var4.method_1255(arg2[1615371868 ^ 1615371868], arg2[-335698670 ^ -335698669]);
                }
            } else {
                var4.method_1251(arg2[2083935481 ^ 2083935481]);
            }
        }
    }

  private static int nF(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int nG(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int nH(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}