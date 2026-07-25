// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.cJ
package dev.angelvisuals.a;

import com.darkmagician6.eventapi.EventTarget;
import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.a.aM;
import dev.angelvisuals.a.aZ;
import dev.angelvisuals.a.bA;
import dev.angelvisuals.a.bI;
import dev.angelvisuals.a.bP;
import dev.angelvisuals.a.cK;
import net.minecraft.class_1306;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_7833;

@bI(name = "SwingAnimation", a = "RENDER", I = "Кастомные анимации удара")
public final class cJ extends cK {

    // ---- поля ----
  public static final cJ field567; // было: a
  public final aZ field568; // было: f
  public final aM field569; // было: g
  public bA field570; // было: H
  public bA field571; // было: I
  public final bA field572; // было: J
  private long field573; // было: G
  private static final long field574 = 300L; // было: H
  private static final String xc = "// good luck with the next 9999 classes";
  private static final String xd = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final String xe = "Protected by t.me/JoinerClient";
  private static final String xf = "// nice try. closed source for a reason.";
  private static final String xg = "// good luck with the next 9999 classes";
  private static final int nF = -1195898092;
  private static final int nG = 12966102;
  private static final int nH = 554553463;
  private static final byte[] ds;

    static {
        ds = "ZQTQ\"dr3NpT+bRAwcF #88'pj6%_)}_7u_CV>`=sD<pg%#^9cF.r#SQPr?@j{}$zk$ym^)G2\"YEH6\"%D/m9QpF>]d1EO~WE3hTOJm@rme];g9lsk(6iN?;YGzo-(|FIC]xF6^^L1q~ebS|'.K@<zrQkj-s425FdTrhszBVGsIaiIif?O$''RtY1*AHuzL>yaiF$=|TI]W|n\"mEkLcYNk<jY|p-4]l:T=g&MnHrDPXn^?*.f0j?Jn.U%qvqQ4OM_T".getBytes("ISO-8859-1");
        field567 = new cJ();
    }

  private cJ() { // было: <init>
        super();
        String[] __obj1 = new String[-624980337 ^ -624980347];
        __obj1[-897118234 ^ -897118234] = Decryptor.method1945(XorDecoder.method1946("54Lÿ\t9(þ\u000c qÕ< Tþ\u000c\u001d,À2\u001f&", -605741673 ^ 1996379123));
        __obj1[2064413636 ^ 2064413637] = Decryptor.method1945(XorDecoder.method1946("ý[V@äm\u0013\u0003¤u\u000e\tóiU}\r\u0006[}X\u000c", -1569641228 ^ -1827401665));
        __obj1[-487004062 ^ -487004064] = Decryptor.method1945(XorDecoder.method1946("\t\u0013÷\u0007W8\u0001ç²\u00188â´\u000b$âù\u000e\u0005ìý", 25109272 ^ -1045544583));
        __obj1[-1995202570 ^ -1995202571] = Decryptor.method1945(XorDecoder.method1946("\u0002N!:Om\u0004K°t\"^¨<#.­GXZÂ*", 1183563297 ^ 1366598988));
        __obj1[-1845470564 ^ -1845470568] = Decryptor.method1945(XorDecoder.method1946("\u0019âÇç.óÎ÷\téÎå\u0007òÇà{àÏÒ#Û", 832975770 ^ -1794332202));
        __obj1[-978139830 ^ -978139825] = Decryptor.method1945(XorDecoder.method1946(",ÈYá\u0011Bá[kÑ\u0004 a\u0015Ûy*²5", 1827665042 ^ -941156368));
        __obj1[-121383855 ^ -121383849] = Decryptor.method1945(XorDecoder.method1946("\u0014\u0008L­49dË&\u001a~º<\u0004_©=t}\u0004;6Ã", -661194083 ^ 647870158));
        __obj1[-802800062 ^ -802800059] = Decryptor.method1945(XorDecoder.method1946("ÃCÙàBýà\u000cü²#þõ÷$Õé'ªã", 1022155186 ^ -495210721));
        __obj1[-1492621537 ^ -1492621545] = Decryptor.method1945(XorDecoder.method1946("ªq³Æ\u000c¬êõ\u001a±£¡tÛ¢3¸øª%Ü­", -1182218535 ^ 694780956));
        __obj1[-593988235 ^ -593988228] = Decryptor.method1945(XorDecoder.method1946("\"Õð (Ð¶Ã\u001eÕµÄ\u001dó¡\u0018ç³ZæÿÉ", -1926352271 ^ 2045551391));
        field568 = new aZ(Decryptor.method1945(XorDecoder.method1946("\u001a_áOaÄ\u0019[ä±PS¶%qø\u000bvï½", -127667531 ^ 2024916426)), __obj1);
        field569 = new aM(Decryptor.method1945(XorDecoder.method1946("&ÿ:O ¤\u0003&\u0016å3U\u001b?`9¥'R\u00151xl_O\u00153.\u007f§!'mø1tfù\u0004*", -1132853373 ^ -1424897577)), 1500602041 ^ 1500602041);
        field570 = new bA(Decryptor.method1945(XorDecoder.method1946("X*1åkv\u0016ÈuAK÷]j\röj[\u000beSY", 133234685 ^ -1231753790)), 5.0f, 1.0f, 10.0f, 1.0f, () -> method958());
        field571 = new bA(Decryptor.method1945(XorDecoder.method1946("¨÷BÉþ]Õ¡ókýªÍhÛõwºý<Â»²>®ÉEÌ:ã²ýN÷¡÷z", 362815890 ^ -1532118934)), 7.0f, 0.0f, 10.0f, 1.0f);
        field572 = new bA(Decryptor.method1945(XorDecoder.method1946("\u0006f\u0015h^FE@\u0004_8jVU\u0013SWcDed@A\u000f", -1361312995 ^ -1667226579)), 0.0f, 0.0f, 360.0f, 1.0f, () -> method957());
        field573 = -4853978397651544769L ^ -4853978397651544769L;
    }

    @EventTarget
  private void method955(bP arg0) { // было: b
        field573 = System.currentTimeMillis();
    }

  public boolean ag() {
        if (field569.method650()) {
            return System.currentTimeMillis() - field573 >= (-5073596759639984534L ^ -5073596759639984314L) ? 1279075069 ^ 1279075069 : -1662248984 ^ -1662248983;
        } else {
            return 2124424409 ^ 2124424408;
        }
    }

  public void method956(class_4587 arg0, float arg1, float arg2, class_1306 arg3) { // было: a
        float var5 = ((float) Math.sin(((double) arg1) * 1.5707963267948966 * 2.0));
        float var6 = class_3532.method_15374(class_3532.method_15355(arg1) * 3.1415927410125732f);
        String var7 = field568.method695();
        int var8 = 1212566472 ^ -1212566473;
        switch (var7.hashCode()) {
            case -1814666802:
                if (!var7.equals(Decryptor.method1945(XorDecoder.method1946("ÊØ¸¼Ç¼¹¹ÞåÞÀ¹¹ã¸á²×", -649180442 ^ 868350519)))) {
                    break;
                }
                var8 = -774167235 ^ -774167235;
                break;
            case 2573164:
                if (!var7.equals(Decryptor.method1945(XorDecoder.method1946("\u0014¹¶%\rófMîl\u001aµ\u0018fïæ>f¸i", 986581485 ^ 1850464207)))) {
                    break;
                }
                var8 = -526393012 ^ -526393011;
                break;
            case 79768134:
                if (!var7.equals(Decryptor.method1945(XorDecoder.method1946("éâõ\u0016ç¦æmØðSøÉUëÕ\u0018îô\u001c", 154213186 ^ 680822979)))) {
                    break;
                }
                var8 = -785588951 ^ -785588949;
                break;
            case 2136258:
                if (!var7.equals(Decryptor.method1945(XorDecoder.method1946(">ÎÁ$\u0006ÏÔh8Ëêq\u001eÞò9\u001f®÷BdÚ/", -671549987 ^ -983732596)))) {
                    break;
                }
                var8 = 1556859812 ^ 1556859815;
                break;
            case 987507365:
                if (!var7.equals(Decryptor.method1945(XorDecoder.method1946("ñöÐ¯àÿÀúÿÒáö×úóþå¢È¦®", 1869985287 ^ -51494966)))) {
                    break;
                }
                var8 = -1957406635 ^ -1957406639;
                break;
            case 80998175:
                if (!var7.equals(Decryptor.method1945(XorDecoder.method1946("q\u001cÄULOßU\u0006DöeYtü'H\u000fä.wf¨\"", 1023523301 ^ 580161242)))) {
                    break;
                }
                var8 = -1913901107 ^ -1913901112;
                break;
            case -1911677324:
                if (!var7.equals(Decryptor.method1945(XorDecoder.method1946("Î\u001aÕ7î+ýQü\u0008ç æ\u0016Æ3çfä\u001eÞ)¯Y", -769805107 ^ -1232082364)))) {
                    break;
                }
                var8 = -1203142806 ^ -1203142804;
                break;
            case 2112084:
                if (!var7.equals(Decryptor.method1945(XorDecoder.method1946("��¸\ru#¹)b#÷(IZØ*\u000e4ß\u0001\u0012\\Ü~\u0018", -156754879 ^ -739536593)))) {
                    break;
                }
                var8 = -536715388 ^ -536715389;
                break;
            case 71652:
                if (!var7.equals(Decryptor.method1945(XorDecoder.method1946("UóðK_ö¶(ióµ/jÕ¡uoÁX-Àÿ\"", 1362317604 ^ 1324475965)))) {
                    break;
                }
                var8 = -1523538429 ^ -1523538421;
                break;
            case -599960602:
                if (!var7.equals(Decryptor.method1945(XorDecoder.method1946("é\u0014SÒi\u007f¶\u007f6â\u0011¾NáVmé@è8", 2057974517 ^ 2139034995)))) {
                    break;
                }
                var8 = 1118551513 ^ 1118551504;
            default:
        }
        switch (var8) {
            case 0:
                arg0.method_46416(0.5600000023841858f, -0.5199999809265137f, -0.7200000286102295f);
                float var9 = field570.bp() * 10.0f;
                float var10 = class_3532.method_15374(class_3532.method_15355(arg1) * 3.1415927410125732f);
                arg0.method_22907(class_7833.field_40716.rotationDegrees(45.0f));
                arg0.method_22907(class_7833.field_40716.rotationDegrees(var10 * -var9 / 4.0f));
                float var11 = class_3532.method_15374(class_3532.method_15355(arg1) * 3.1415927410125732f);
                arg0.method_22907(class_7833.field_40718.rotationDegrees(var11 * -(var9 / 4.0f)));
                arg0.method_22907(class_7833.field_40714.rotationDegrees(var11 * -var9));
                arg0.method_22907(class_7833.field_40716.rotationDegrees(-45.0f));
                break;
            case 1:
                arg0.method_46416(0.5600000023841858f, -0.5199999809265137f, -0.7200000286102295f);
                arg0.method_22907(class_7833.field_40716.rotationDegrees(90.0f));
                arg0.method_22907(class_7833.field_40718.rotationDegrees(-30.0f));
                arg0.method_22907(class_7833.field_40714.rotationDegrees(-field572.bp() - field570.bp() * 10.0f * var5));
                break;
            case 2:
                arg0.method_46416(0.5600000023841858f, -0.5199999809265137f, -0.7200000286102295f);
                arg0.method_22907(class_7833.field_40716.rotationDegrees(70.0f));
                arg0.method_22907(class_7833.field_40718.rotationDegrees(-60.0f));
                arg0.method_22907(class_7833.field_40714.rotationDegrees(-field572.bp() - field570.bp() * 10.0f * var5));
                break;
            case 3:
                arg0.method_46416(0.5600000023841858f, -0.5199999809265137f - var5 * field570.bp() / 24.0f, -0.7200000286102295f);
                arg0.method_22907(class_7833.field_40716.rotationDegrees(90.0f));
                arg0.method_22907(class_7833.field_40718.rotationDegrees(-30.0f));
                arg0.method_22907(class_7833.field_40714.rotationDegrees(-90.0f));
                break;
            case 4:
                arg0.method_46416(0.5600000023841858f, -0.5199999809265137f, -0.7200000286102295f);
                var9 = 35.0f;
                arg0.method_22904(0.0, 0.0, -0.3 * ((double) var6));
                arg0.method_22907(class_7833.field_40714.rotationDegrees(var6 * -var9));
                arg0.method_22907(class_7833.field_40718.rotationDegrees(var6 * var9));
                break;
            case 5:
                arg0.method_46416(0.5600000023841858f, -0.5199999809265137f, -0.7200000286102295f);
                arg0.method_22905(1.0f, 1.0f, 1.0f + var5 * field570.bp() / 4.0f);
                arg0.method_46416(0.0f, 0.0f, -0.26499998569488525f);
                arg0.method_22907(class_7833.field_40714.rotationDegrees(-100.0f));
                break;
            case 6:
                arg0.method_46416(0.5600000023841858f, -0.5199999809265137f, -0.7200000286102295f);
                arg0.method_22905(0.800000011920929f, 0.800000011920929f, 0.800000011920929f);
                var9 = var5 * 0.15000000596046448f;
                arg0.method_22904(0.3 - ((double) var9), 0.2 - ((double) arg2) * 0.12, -0.15 - ((double) var5) * 0.13);
                arg0.method_22907(class_7833.field_40716.rotationDegrees(76.0f - 10.0f * var5));
                arg0.method_22907(class_7833.field_40718.rotationDegrees(-16.0f - 8.0f * var5));
                arg0.method_22907(class_7833.field_40714.rotationDegrees(-83.0f - 26.0f * var5));
                break;
            case 7:
                arg0.method_46416(0.5600000023841858f, -0.5199999809265137f, -0.7200000286102295f);
                var9 = class_3532.method_15355(arg1);
                var10 = class_3532.method_15374(var9 * 3.1415927410125732f);
                var11 = class_3532.method_15374(arg1 * 3.1415927410125732f);
                arg0.method_46416(0.4000000059604645f - var10 * 0.20000000298023224f, -0.20000000298023224f + var10 * 0.30000001192092896f, -0.5f - var11 * 0.20000000298023224f);
                arg0.method_22907(class_7833.field_40716.rotationDegrees(91.0f));
                arg0.method_22907(class_7833.field_40718.rotationDegrees(-40.0f + var10 * -100.0f));
                arg0.method_22907(class_7833.field_40714.rotationDegrees(-60.0f));
                break;
            case 8:
                var9 = class_3532.method_15374(arg1 * 3.1415927410125732f);
                arg0.method_46416(0.5600000023841858f - var9 * 0.18000000715255737f, -0.41999998688697815f + var9 * 0.11999999731779099f, -0.5799999833106995f - var9 * 0.18000000715255737f);
                arg0.method_22907(class_7833.field_40716.rotationDegrees(35.0f + var9 * 48.0f));
                arg0.method_22907(class_7833.field_40718.rotationDegrees(-28.0f - var9 * 42.0f));
                arg0.method_22907(class_7833.field_40714.rotationDegrees(-48.0f - var9 * 66.0f));
                arg0.method_22905(1.0f + var9 * 0.09000000357627869f, 1.0f + var9 * 0.09000000357627869f, 1.0f);
                break;
            case 9:
                arg0.method_46416(0.5600000023841858f, -0.5199999809265137f, -0.7200000286102295f);
                var9 = class_3532.method_15374(arg1 * arg1 * 3.1415927410125732f);
                arg0.method_22907(class_7833.field_40716.rotationDegrees(45.0f));
                var10 = class_3532.method_15374(class_3532.method_15355(arg1) * 3.1415927410125732f);
                arg0.method_22907(class_7833.field_40714.rotationDegrees(var9 * -20.0f));
                arg0.method_22907(class_7833.field_40718.rotationDegrees(var10 * -20.0f));
                arg0.method_22907(class_7833.field_40714.rotationDegrees(var10 * -80.0f));
                arg0.method_46416(0.4000000059604645f, 0.20000000298023224f, 0.20000000298023224f);
                arg0.method_46416(-0.5f, 0.07999999821186066f, 0.0f);
                arg0.method_22907(class_7833.field_40716.rotationDegrees(20.0f));
                arg0.method_22907(class_7833.field_40714.rotationDegrees(-80.0f));
                arg0.method_22907(class_7833.field_40716.rotationDegrees(20.0f));
            default:
        }
    }

  private Boolean method957() { // было: o
        int __stk1;
        __stk1 = field568.method696(Decryptor.method1945(XorDecoder.method1946("\"jà2;\\¥q{D¸{,Xã\u000fP<°)PLî~", 535649076 ^ 1547589152))) ? -2120687645 ^ -2120687646 : !field568.method696(Decryptor.method1945(XorDecoder.method1946("¾¥¡°É¶ÚÃä¯¦Æâ¼ºÆ¯¹È«", -954113965 ^ 1372888197))) ? 1348903907 ^ 1348903907 : -2120687645 ^ -2120687646;
        return Boolean.valueOf(__stk1);
    }

  private Boolean method958() { // было: p
        int __stk1;
        __stk1 = field568.method696(Decryptor.method1945(XorDecoder.method1946("¬tUú\tJÖó\u001fW§qlç¤6^Ä¬ :", -1713248780 ^ 904307255))) ? 2085686898 ^ 2085686898 : field568.method696(Decryptor.method1945(XorDecoder.method1946("\u0011@ß»1q÷Ý#Rí¬9LÌ¿8<î\u0001s¥Õ", -591173254 ^ 878406956))) ? 2085686898 ^ 2085686898 : field568.method696(Decryptor.method1945(XorDecoder.method1946("ùéXÚè»OÚ¦ºd£¸#Í?¥ì5", 776010912 ^ 647045175))) ? 2085686898 ^ 2085686898 : 647496571 ^ 647496570;
        return Boolean.valueOf(__stk1);
    }

  private static int lZ(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int ma(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int mb(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}