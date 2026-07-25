// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.i
package dev.angelvisuals.a;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.a.aI;
import dev.angelvisuals.a.bD;
import dev.angelvisuals.a.cE;
import dev.angelvisuals.a.cF;
import dev.angelvisuals.a.do;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Stream;
import lombok.Generated;
import net.minecraft.class_2561;
import net.minecraft.class_2761;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_329;
import net.minecraft.class_345;
import net.minecraft.class_3532;
import net.minecraft.class_5321;
import net.minecraft.class_634;
import net.minecraft.class_638;

public class ClassA145 implements cF {

    // ---- поля ----
  private final bD field768; // было: a
  private String field769; // было: O
  private float field770; // было: d
  private long field771; // было: d
  private boolean field772; // было: a
  private int field773; // было: E
  private boolean field774; // было: b
  private static final String field775 = "// good luck with the next 9999 classes"; // было: P
  private static final String field776 = "// you are reading machine-generated garbage"; // было: Q
  private static final String field777 = "// this jar protected by JoinerObfuscator"; // было: R
  private static final String field778 = "// stop. seriously. go play minecraft instead"; // было: S
  private static final String field779 = "Protected by t.me/JoinerClient"; // было: T
  private static final int field780 = 2079201858; // было: F
  private static final int field781 = -1723378315; // было: G
  private static final int field782 = -1091054655; // было: H
  private static final byte[] field783; // было: i

    static {
        field783 = "&&^SB.l0MWQ#S(6=fon*{m/;b,~o0:~~-1@}1OM1dQ(SHS-G]uC#VB.6=CX%aRP/H0=Ckkt<R`:jn{NX)8=. 2I}{AGd C;mC!:$3u7_5O[bN(3Jz#D3Uq9SJ&Ts2$N5rNU%n|}p?Rk&|]g,I`D7nThF@'j\\t|4JoVLGP90%@^ =f,~vb,\\C%3O|ZCjM#JX,%K[Z_0Z'PS%AsV#zc@na\"qo3\";7&I?(mCH6M7Chm}+LAwm2ykl)dx{QXPNI;b_;\"".getBytes("ISO-8859-1");
    }

  public static Instant method1368(String arg0, int arg1) { // было: a
        URL var2 = new URL(arg0);
        HttpURLConnection var3 = ((HttpURLConnection) var2.openConnection());
        var3.setRequestMethod(Decryptor.method1945(XorDecoder.method1946("£eýðLÀÉËRÊå[ÆÈýfÔ©ý\u001c", 1371377878 ^ -6542289)));
        var3.setConnectTimeout(arg1);
        var3.setReadTimeout(arg1);
        var3.setInstanceFollowRedirects(-94397065 ^ -94397066);
        var3.connect();
        String var4 = var3.getHeaderField(Decryptor.method1945(XorDecoder.method1946(")Ó­xuÛ.8ó¶1 Îöp\u000cñ uqÃø'", 1635975229 ^ 2068218492)));
        var3.disconnect();
        if (var4 != null) {
            Instant var5 = ((Instant) DateTimeFormatter.RFC_1123_DATE_TIME.parse(var4, lp0 -> Instant.from(lp0)));
            return var5;
        } else {
            throw new RuntimeException("No Date header from " + arg0);
        }
    }

  public static boolean method1369(String arg0) { // было: a
        int __stk1;
        try {
            Instant var1 = method1368(arg0, -51832458 ^ -51834162);
            ZoneId var2 = ZoneId.of(Decryptor.method1945(XorDecoder.method1946("]÷s£ï\r·ê\u0003·§PÉÞA¹¯á", -1939698524 ^ 1357935761)));
            LocalDate var3 = LocalDateTime.ofInstant(var1, var2).toLocalDate();
            if (var3.getDayOfMonth() == (1600083034 ^ 1600083038)) {
                __stk1 = var3.getMonthValue() != (1528074745 ^ 1528074741) ? -784577019 ^ -784577019 : -1874518353 ^ -1874518354;
            } else {
                if (var3.getDayOfMonth() != (1348347131 ^ 1348347134)) {
                    __stk1 = -784577019 ^ -784577019;
                }
                __stk1 = var3.getMonthValue() != (1528074745 ^ 1528074741) ? -784577019 ^ -784577019 : -1874518353 ^ -1874518354;
            }
        } catch (Exception e1) {
            Throwable var1 = e1;
            return -1237212768 ^ -1237212768;
        }
    }

  public ClassA145() { // было: <init>
        super();
        field768 = new bD();
        field769 = Decryptor.method1945(XorDecoder.method1946("ÛæE é\u0016´Ë\u0002Îï°\u0016¥õîC¼×MÊ", -76010858 ^ 201913614));
        field770 = 20.0f;
        try {
            EventManager.register(this);
        } catch (Exception var1) {
        }
    }

    @EventTarget
  public void method1370(do arg0) { // было: a
        field773 = method1374();
        field769 = method1373();
        field774 = method1377();
        if (method1376()) {
            field768.ai();
        }
    }

    @EventTarget
  public void method1371(aI arg0) { // было: a
        if (arg0.method335() instanceof class_2761) {
            long var2 = System.nanoTime();
            float var4 = 20.0f;
            float var5 = var4 * 1000000000.0f / ((float) (var2 - field771));
            field770 = class_3532.method_15363(var5, 0.0f, var4);
            field771 = var2;
        }
    }

    @EventTarget
  public void method1372(aI arg0) { // было: b
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_1
        //      1: invokevirtual  #248 // dev.angelvisuals.a.aI.b:()Lnet/minecraft/class_2596;
        //      4: astore_2
        //      5: aload_2
        //      6: instanceof  #208 // net.minecraft.class_2848
        //      9: ifeq  96 (offset +87)
        //     12: aload_2
        //     13: checkcast  #208 // net.minecraft.class_2848
        //     16: astore_3
        //     17: aload_3
        //     18: invokevirtual  #297 // net.minecraft.class_2848.method_12365:()Lnet/minecraft/class_2848$class_2849;
        //     21: getstatic  #238 // net.minecraft.class_2848$class_2849.field_12981:Lnet/minecraft/class_2848$class_2849;
        //     24: invokevirtual  #298 // net.minecraft.class_2848$class_2849.equals:(Ljava/lang/Object;)Z
        //     27: ifeq  50 (offset +23)
        //     30: aload_1
        //     31: aload_0
        //     32: getfield  #231 // dev.angelvisuals.a.i.a:Z
        //     35: invokevirtual  #249 // dev.angelvisuals.a.aI.setCancelled:(Z)V
        //     38: aload_0
        //     39: ldc  #105 // 1455338448
        //     41: ldc  #106 // 1455338449
        //     43: ixor
        //     44: putfield  #231 // dev.angelvisuals.a.i.a:Z
        //     47: goto  96 (offset +49)
        //     50: aload_3
        //     51: invokevirtual  #297 // net.minecraft.class_2848.method_12365:()Lnet/minecraft/class_2848$class_2849;
        //     54: getstatic  #239 // net.minecraft.class_2848$class_2849.field_12985:Lnet/minecraft/class_2848$class_2849;
        //     57: invokevirtual  #298 // net.minecraft.class_2848$class_2849.equals:(Ljava/lang/Object;)Z
        //     60: ifeq  96 (offset +36)
        //     63: aload_1
        //     64: aload_0
        //     65: getfield  #231 // dev.angelvisuals.a.i.a:Z
        //     68: ifne  79 (offset +11)
        //     71: ldc  #35 // -1089141919
        //     73: ldc  #34 // -1089141920
        //     75: ixor
        //     76: goto  84 (offset +8)
        //     79: ldc  #15 // -1845902344
        //     81: ldc  #15 // -1845902344
        //     83: ixor
        //     84: invokevirtual  #249 // dev.angelvisuals.a.aI.setCancelled:(Z)V
        //     87: aload_0
        //     88: ldc  #55 // -424081570
        //     90: ldc  #55 // -424081570
        //     92: ixor
        //     93: putfield  #231 // dev.angelvisuals.a.i.a:Z
        //     96: return
    }

  private String method1373() { // было: a
        if (cE.ac()) {
            return Decryptor.method1945(XorDecoder.method1946("±\u0014ôÊGÁ¡üS»ÆGÐ\u0012É½è\u001c¿", -455019446 ^ 1723948472));
        } else {
            if (mc.method_1562() == null) {
                return Decryptor.method1945(XorDecoder.method1946("±\u0014ôÊGÁ¡üS»ÆGÐ\u0012É½è\u001c¿", -455019446 ^ 1723948472));
            } else {
                if (mc.method_1562().method_45734() == null) {
                    return Decryptor.method1945(XorDecoder.method1946("±\u0014ôÊGÁ¡üS»ÆGÐ\u0012É½è\u001c¿", -455019446 ^ 1723948472));
                } else {
                    if (mc.method_1562().method_52790() == null) {
                        return Decryptor.method1945(XorDecoder.method1946("±\u0014ôÊGÁ¡üS»ÆGÐ\u0012É½è\u001c¿", -455019446 ^ 1723948472));
                    } else {
                        String var1 = mc.method_1562().method_45734().field_3761.toLowerCase();
                        String var2 = mc.method_1562().method_52790().toLowerCase();
                        if (!var2.contains(Decryptor.method1945(XorDecoder.method1946("\u0003\u001f?\u0004(à\u0006R\u000f\"Y2½#F\u0010z|\u000còh", -618182647 ^ -1897392838)))) {
                            if (var1.contains(Decryptor.method1945(XorDecoder.method1946("\u0003[¾+\u0013\r¥;$\u0010ºZ7\u000e½\u0008\u0003!·Dp2òL", -1212172975 ^ -965683696)))) {
                                return Decryptor.method1945(XorDecoder.method1946("#ÌÂ]úêw­Û¢d¢r©÷wÇ°", -38545401 ^ 1883883733));
                            } else {
                                if (var1.contains(Decryptor.method1945(XorDecoder.method1946("3{½m\u0007G;/X®8\u0015G³1>:§)'PÂ`", 1804330943 ^ 913635008)))) {
                                    return Decryptor.method1945(XorDecoder.method1946("#ÌÂ]úêw­Û¢d¢r©÷wÇ°", -38545401 ^ 1883883733));
                                } else {
                                    if (var1.contains(Decryptor.method1945(XorDecoder.method1946("{p)ôtR=Ë;/9ø\u0006]\u0005Í*s+\u001ahN", 662871720 ^ -2014215702)))) {
                                        return Decryptor.method1945(XorDecoder.method1946("#ÌÂ]úêw­Û¢d¢r©÷wÇ°", -38545401 ^ 1883883733));
                                    } else {
                                        if (var1.contains(Decryptor.method1945(XorDecoder.method1946("Àåã[óº{ÐÃ÷\u007fÁ½ø]ÐÙõ\u0011ãÉ\u0015", -1382816032 ^ -2061013386)))) {
                                            return Decryptor.method1945(XorDecoder.method1946("#ÌÂ]úêw­Û¢d¢r©÷wÇ°", -38545401 ^ 1883883733));
                                        } else {
                                            if (var2.contains(Decryptor.method1945(XorDecoder.method1946("¢ú\u0016§¦ð\u000bþ\u0003¦úÃ\n¤î\u0014­ãÌXô", 107214792 ^ -821670824)))) {
                                                return Decryptor.method1945(XorDecoder.method1946("1EßÃ��x\u0004\u0018¸Ý@{ÛãAYAHÑ", 853523148 ^ -2076994116));
                                            } else {
                                                if (var2.contains(Decryptor.method1945(XorDecoder.method1946("JÁQýmö\u0019×WÄ\u001fõqÕ\u0018±m÷,éOÃT¹", -782352194 ^ 1429717635)))) {
                                                    return Decryptor.method1945(XorDecoder.method1946("1EßÃ��x\u0004\u0018¸Ý@{ÛãAYAHÑ", 853523148 ^ -2076994116));
                                                } else {
                                                    if (var2.contains(Decryptor.method1945(XorDecoder.method1946("¨\u000bþß Æ¾výÛ\u0010³ÂÜkÕÃ3¹Í", -587341483 ^ 746169790)))) {
                                                        return Decryptor.method1945(XorDecoder.method1946("1EßÃ��x\u0004\u0018¸Ý@{ÛãAYAHÑ", 853523148 ^ -2076994116));
                                                    } else {
                                                        if (!var1.contains(Decryptor.method1945(XorDecoder.method1946("C°Å´W«ä¬WµZµÎ©u¼ê\u000eÂ", -1021398748 ^ 1009600904)))) {
                                                            return !var1.contains(Decryptor.method1945(XorDecoder.method1946("GÑ\u001e\u000b}û_$B\u0007P_\u0004\u0013dÇ!TbÑI]", -766082432 ^ -1306381166))) ? Decryptor.method1945(XorDecoder.method1946("\u0001Û9èzÔjÝ\u0011·~§5jÌ/Ó?Õ\r£1£", 134661131 ^ -1777719735)) : Decryptor.method1945(XorDecoder.method1946("ÿ,VÄ3\u0006­x\u0004©ò+5å|\u0006Å\u001bZû", -476623061 ^ 636487103));
                                                        } else {
                                                            return Decryptor.method1945(XorDecoder.method1946("z��\u0002÷gW<vW��>n2GK\u0017[nhü", 189371346 ^ -904086306));
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            return Decryptor.method1945(XorDecoder.method1946("E)\\m\t»B[\u000crE\u000c¦*z\rwx:í'", -1588234848 ^ -1148903774));
                        }
                    }
                }
            }
        }
    }

  private int method1374() { // было: a
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: getstatic  #236 // dev.angelvisuals.a.i.mc:Lnet/minecraft/class_310;
        //      3: getfield  #240 // net.minecraft.class_310.field_1687:Lnet/minecraft/class_638;
        //      6: invokevirtual  #309 // net.minecraft.class_638.method_8428:()Lnet/minecraft/class_269;
        //      9: astore_1
        //     10: aload_1
        //     11: getstatic  #244 // net.minecraft.class_8646.field_45157:Lnet/minecraft/class_8646;
        //     14: invokevirtual  #296 // net.minecraft.class_269.method_1189:(Lnet/minecraft/class_8646;)Lnet/minecraft/class_266;
        //     17: astore_2
        //     18: aload_0
        //     19: getfield  #229 // dev.angelvisuals.a.i.O:Ljava/lang/String;
        //     22: astore_3
        //     23: ldc  #46 // -718957300
        //     25: ldc  #87 // 718957299
        //     27: ixor
        //     28: istore  4
        //     30: aload_3
        //     31: invokevirtual  #269 // java.lang.String.hashCode:()I
        //     34: lookupswitch  default->117, -495240450->60, 1154553036->90
        //     60: aload_3
        //     61: ldc  #130 // '\x07~R¯6C\x08ì2#5±v@V\x8fwb\x1bêws\\ç'
        //     63: ldc  #51 // -585084266
        //     65: ldc  #70 // 121718480
        //     67: ixor
        //     68: invokestatic  #247 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //     71: invokestatic  #246 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //     74: invokevirtual  #267 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //     77: ifeq  117 (offset +40)
        //     80: ldc  #63 // -75225386
        //     82: ldc  #64 // -75225385
        //     84: ixor
        //     85: istore  4
        //     87: goto  117 (offset +30)
        //     90: aload_3
        //     91: ldc  #150 // 'X\x14ÎÔp4õÊF1ÍúX1è¢g0Êÿe\x07£¯'
        //     93: ldc  #94 // 1038980376
        //     95: ldc  #24 // -1351367929
        //     97: ixor
        //     98: invokestatic  #247 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    101: invokestatic  #246 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    104: invokevirtual  #267 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //    107: ifeq  117 (offset +10)
        //    110: ldc  #110 // 1561901515
        //    112: ldc  #110 // 1561901515
        //    114: ixor
        //    115: istore  4
        //    117: iload  4
        //    119: lookupswitch  default->320, 0->144, 1->201
        //    144: aload_2
        //    145: ifnull  320 (offset +175)
        //    148: aload_2
        //    149: invokevirtual  #292 // net.minecraft.class_266.method_1114:()Lnet/minecraft/class_2561;
        //    152: invokeinterface  #320 // net.minecraft.class_2561.getString:()Ljava/lang/String;, count 1
        //    157: ldc  #151 // "Y\x04Y\x07]\x0bH\x11P\x04X\x06{\x02C7A}~;t\x1e'^"
        //    159: ldc  #77 // 251895171
        //    161: ldc  #120 // 1813631666
        //    163: ixor
        //    164: invokestatic  #247 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    167: invokestatic  #246 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    170: invokevirtual  #271 // java.lang.String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //    173: astore  5
        //    175: aload  5
        //    177: arraylength
        //    178: ldc  #11 // -1874980440
        //    180: ldc  #12 // -1874980439
        //    182: ixor
        //    183: if_icmple  198 (offset +15)
        //    186: aload  5
        //    188: ldc  #113 // 1591845049
        //    190: ldc  #112 // 1591845048
        //    192: ixor
        //    193: aaload
        //    194: invokestatic  #263 // java.lang.Integer.parseInt:(Ljava/lang/String;)I
        //    197: ireturn
        //    198: goto  320 (offset +122)
        //    201: aload_1
        //    202: aload_2
        //    203: invokevirtual  #295 // net.minecraft.class_269.method_1184:(Lnet/minecraft/class_266;)Ljava/util/Collection;
        //    206: invokeinterface  #313 // java.util.Collection.iterator:()Ljava/util/Iterator;, count 1
        //    211: astore  5
        //    213: aload  5
        //    215: invokeinterface  #315 // java.util.Iterator.hasNext:()Z, count 1
        //    220: ifeq  320 (offset +100)
        //    223: aload  5
        //    225: invokeinterface  #316 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //    230: checkcast  #222 // net.minecraft.class_9011
        //    233: astore  6
        //    235: aload_1
        //    236: aload  6
        //    238: invokevirtual  #310 // net.minecraft.class_9011.comp_2127:()Ljava/lang/String;
        //    241: invokevirtual  #294 // net.minecraft.class_269.method_1164:(Ljava/lang/String;)Lnet/minecraft/class_268;
        //    244: aload  6
        //    246: invokevirtual  #311 // net.minecraft.class_9011.method_55387:()Lnet/minecraft/class_2561;
        //    249: invokestatic  #293 // net.minecraft.class_268.method_1142:(Lnet/minecraft/class_270;Lnet/minecraft/class_2561;)Lnet/minecraft/class_5250;
        //    252: invokevirtual  #304 // net.minecraft.class_5250.getString:()Ljava/lang/String;
        //    255: astore  7
        //    257: aload  7
        //    259: invokevirtual  #270 // java.lang.String.isEmpty:()Z
        //    262: ifne  317 (offset +55)
        //    265: aload  7
        //    267: ldc  #153 // 'f¢8{5ì\x0csNìQ!O¤��/VÆQ\\MÅG%'
        //    269: ldc  #21 // -1545623134
        //    271: ldc  #31 // -1146798683
        //    273: ixor
        //    274: invokestatic  #247 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    277: invokestatic  #246 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    280: ldc  #169 // 'Ð\\Ñ\x88°,\x9f\x8cç;\x9e¡ç\x0eç®¸\x07áÝñ\r\x9bÔ'
        //    282: ldc  #41 // -832663454
        //    284: ldc  #85 // 670628578
        //    286: ixor
        //    287: invokestatic  #247 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    290: invokestatic  #246 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    293: invokestatic  #312 // org.apache.commons.lang3.StringUtils.substringBetween:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    296: astore  8
        //    298: aload  8
        //    300: ifnull  317 (offset +17)
        //    303: aload  8
        //    305: invokevirtual  #270 // java.lang.String.isEmpty:()Z
        //    308: ifne  317 (offset +9)
        //    311: aload  8
        //    313: invokestatic  #263 // java.lang.Integer.parseInt:(Ljava/lang/String;)I
        //    316: ireturn
        //    317: goto  213 (offset -104)
        //    320: ldc  #38 // -989262342
        //    322: ldc  #92 // 989262341
        //    324: ixor
        //    325: ireturn
    }

  public boolean method1375() { // было: a
        return field768.method1448(-3954194769280320937L ^ -3954194769280320851L) ? 568876725 ^ 568876725 : 1040612160 ^ 1040612161;
    }

  private boolean method1376() { // было: b
        return mc.field_1705.method_1740().field_2060.values().stream().map(lp0 -> method1394(((class_345) lp0))).anyMatch(lp0 -> method1393(((String) lp0)));
    }

  private boolean method1377() { // было: c
        return mc.field_1705.method_1740().field_2060.values().stream().map(lp0 -> method1392(((class_345) lp0))).anyMatch(lp0 -> method1391(((String) lp0)));
    }

  public String method1378() { // было: b
        return mc.field_1687.method_27983().method_29177().method_12832();
    }

  public boolean method1379() { // было: d
        return field769.equals(Decryptor.method1945(XorDecoder.method1946("v\u0002\u0002êx|ZÒ\u0008Vcó@ENtSgßeV\t", 699983828 ^ -1936927004))) ? -2114762956 ^ -2114762955 : field769.equals(Decryptor.method1945(XorDecoder.method1946("\u0016\u000b=\t§\u0004$\u000c\u000c\u0004\u000fá\u000b8w?,7§zK", -2067613919 ^ -226138269))) ? -2114762956 ^ -2114762955 : !field769.equals(Decryptor.method1945(XorDecoder.method1946("\u000cHç$´sù\u0012±KÉ\u000c±n3°LÌ1%", 503766665 ^ -1088550718))) ? -565526539 ^ -565526539 : -2114762956 ^ -2114762955;
    }

  public boolean method1380() { // было: e
        return field769.equals(Decryptor.method1945(XorDecoder.method1946("é^nbÁ~U|÷{mLé{H\u0014ÖzjIÔM\u0003\u0019", 2034617673 ^ 1568395751)));
    }

  public boolean method1381() { // было: f
        return field769.equals(Decryptor.method1945(XorDecoder.method1946("¦+³ñ4ãáô\u007fáÏ«,Ðí¼{ãá\u001c¿", 1343113900 ^ -259258527)));
    }

  public boolean method1382() { // было: g
        return field769.equals(Decryptor.method1945(XorDecoder.method1946("x\u0019Þ!I$bMD¹?\t'Ú\u0001\u0008\u0005d\u0008\u0014Ði", 1502599086 ^ 224568471)));
    }

  public boolean method1383() { // было: h
        return field769.equals(Decryptor.method1945(XorDecoder.method1946("jfH\u0019\u0011i\u001b,z\n\u000fV^0\u001b=DnN$f\u001e@R", -147016610 ^ -1740510345)));
    }

    @Generated
  public bD method1384() { // было: a
        return field768;
    }

    @Generated
  public String method1385() { // было: d
        return field769;
    }

    @Generated
  public float method1386() { // было: b
        return field770;
    }

    @Generated
  public long method1387() { // было: a
        return field771;
    }

    @Generated
  public boolean method1388() { // было: i
        return field772;
    }

    @Generated
  public int method1389() { // было: b
        return field773;
    }

    @Generated
  public boolean method1390() { // было: j
        return field774;
    }

  private static boolean method1391(String arg0) { // было: b
        return arg0.contains(Decryptor.method1945(XorDecoder.method1946("<}\u0019Á-v=?<!À\"n]ß\nm\u001e¸<2S", -390787450 ^ -2034608778))) ? arg0.contains(Decryptor.method1945(XorDecoder.method1946("1z*\u0011{\u0005«\tk-¬\u0013y-\u0015L3®\u0013\\B÷", 332585438 ^ -642919258))) ? 465429557 ^ 465429556 : !arg0.contains(Decryptor.method1945(XorDecoder.method1946("EÌTÏCÐ\u0003è:\u000eä#Ê\u0017Ømù\u0014D_", 760148994 ^ 1341383086))) ? 123631998 ^ 123631998 : 465429557 ^ 465429556 : !arg0.contains(Decryptor.method1945(XorDecoder.method1946("Öbá¯uªÅ{ èY²pä édëç", 1121794286 ^ -1744098477))) ? 123631998 ^ 123631998 : arg0.contains(Decryptor.method1945(XorDecoder.method1946("1z*\u0011{\u0005«\tk-¬\u0013y-\u0015L3®\u0013\\B÷", 332585438 ^ -642919258))) ? 465429557 ^ 465429556 : !arg0.contains(Decryptor.method1945(XorDecoder.method1946("EÌTÏCÐ\u0003è:\u000eä#Ê\u0017Ømù\u0014D_", 760148994 ^ 1341383086))) ? 123631998 ^ 123631998 : 465429557 ^ 465429556;
    }

  private static String method1392(class_345 arg0) { // было: a
        return arg0.method_5414().getString().toLowerCase();
    }

  private static boolean method1393(String arg0) { // было: c
        return arg0.contains(Decryptor.method1945(XorDecoder.method1946("\u0017\u0011eW\u001aA\u0003P]V\u0002!I³\u0001b.^/", -1124015832 ^ -1352465586))) ? -1295832790 ^ -1295832789 : !arg0.contains(Decryptor.method1945(XorDecoder.method1946("U7+Í\u0011 MÈF.jâk\u000cLÐ\u000c%.Âj1!", 1614175488 ^ -668329154))) ? 128342827 ^ 128342827 : -1295832790 ^ -1295832789;
    }

  private static String method1394(class_345 arg0) { // было: b
        return arg0.method_5414().getString().toLowerCase();
    }

  private static int method1395(int arg0, int arg1) { // было: y
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int method1396(int arg0, int arg1) { // было: z
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int method1397(int arg0, int arg1) { // было: A
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}