// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.u
package dev.angelvisuals.a;

import com.darkmagician6.eventapi.EventTarget;
import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.a.aZ;
import dev.angelvisuals.a.ai;
import dev.angelvisuals.a.bI;
import dev.angelvisuals.a.cK;
import dev.angelvisuals.a.cY;
import net.minecraft.class_1661;
import net.minecraft.class_1713;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2561;
import net.minecraft.class_636;
import net.minecraft.class_746;

@bI(name = "AutoSwap", a = "PVP", I = "Меняет предмет в выбранном слоте по настроенной кнопке")
public final class ClassA94 extends cK {

    // ---- поля ----
  public static final ClassA94 field359; // было: a
  public final aZ field360; // было: a
  public final aZ field361; // было: b
  public final ai field362; // было: a
  private static final String aY = "// number obfuscation: ENABLED (XOR masking)";
  private static final String aZ = "// flow obfuscation: ENABLED";
  private static final String ba = "// number obfuscation: ENABLED (XOR masking)";
  private static final String bb = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String bc = "// Joiner sees you";
  private static final int ap = -1855101169;
  private static final int aq = -736414062;
  private static final int ar = 1688905725;
  private static final byte[] field363; // было: u

    static {
        field363 = "zByTyw,IKu]t )}sjWri={qp@C},,K&'cT[=2Rk@4@?o(tm63PYRhY9Ff#9f}\\;'7y@HB~xmA$M2a=v+z qxt|.SPFj~;{g0R+(+f[\"N[jRZRwru'}<zzg%FQMTG159IvntweuJ$gR&.Y!hzG\"L}UA>Mk5CYVNbL !8iPWS5LFBW*-se10Q8HXqeQrfS1Ja$yHy-O[@taJ042r|PFNkMLt\\-5I/\\}QB-ns\"rCy>-0;bY7K[@bLWZ_+Ze^?q3#,y4".getBytes("ISO-8859-1");
        field359 = new ClassA94();
    }

  private ClassA94() { // было: <init>
        super();
        String[] __obj1 = new String[1090748737 ^ 1090748741];
        __obj1[897238514 ^ 897238514] = Decryptor.method1945(XorDecoder.method1946("×ð!\u0015ï3\u0001é*\u0012åPhËø-7õÊZz", -1525628747 ^ -495485643));
        __obj1[-1320018057 ^ -1320018058] = Decryptor.method1945(XorDecoder.method1946("â\u0012õ\u00055ûqàÕ?¡{", 240783467 ^ 1223280300));
        __obj1[25848640 ^ 25848642] = Decryptor.method1945(XorDecoder.method1946("8¯yÚG¹B«\u0006 É\u001f_ë?¥ký\t@í\u001bÔ\\È\\hÍ\u001d¯wúAÒ\\ä=¥X¡", 132335530 ^ -1678596903));
        __obj1[-1773737210 ^ -1773737211] = Decryptor.method1945(XorDecoder.method1946("c¸²Å^÷Û\u0006µÂJºü[³Ás¶Ú\u0010µûFªøB®Éeºçe", 1968266097 ^ -576087208));
        field360 = new aZ(Decryptor.method1945(XorDecoder.method1946("§jéD«\u0017¬%Ädá\u001a¯0¨*¤\u001a\u0014%ä@", -1688269459 ^ -427401328)), __obj1);
        String[] __obj2 = new String[1875876534 ^ 1875876530];
        __obj2[1927218677 ^ 1927218677] = Decryptor.method1945(XorDecoder.method1946("Æº·¿®±¸½½»åÇÎ­üïÕ", -634609148 ^ 855517660));
        __obj2[-398821095 ^ -398821096] = Decryptor.method1945(XorDecoder.method1946("GÔtMÂc_ÁåSFÎ\u0017[°ºYHñá\u001d", -249130360 ^ -772139903));
        __obj2[-765748766 ^ -765748768] = Decryptor.method1945(XorDecoder.method1946("Df��;p²qz_Ð\u0013c]¯1Cl'u@°7g\u001d¬\u0012 _\u0017af =\u001b¬>Al¨{", -1228326314 ^ -265809831));
        __obj2[-1296111441 ^ -1296111444] = Decryptor.method1945(XorDecoder.method1946("Ûl\u0003\u0005æ#?\u001b¾a?\u0002òn!<ãL\u0002\u0001Ëb&\u001a¨R\u0004;þE\u001b8úY\u001f\tÝ]\u000b'ÝQ&U", 1086107504 ^ 687193057));
        field361 = new aZ(Decryptor.method1945(XorDecoder.method1946("ÓÇ*­\u001fÃë\u001dÈÏè��×+·d", -257961210 ^ -1456462342)), __obj2);
        field362 = new ai(Decryptor.method1945(XorDecoder.method1946("¤'>¡Ó9.ö\u0014düÆ`\u000f­Ë0\u000c¯ò<'²©|<ºë\u0018\u0005¤÷5\u001eòc\u000eºª$`", -3379141 ^ -1566577424)));
    }

    @EventTarget
  public void method742(cY arg0) { // было: b
        if (arg0.am() != (-1436982491 ^ -1436982492)) {
            return;
        } else {
            if (arg0.an() != field362.method688()) {
                return;
            } else {
                if (field362.method688() != (-111571372 ^ 111571371)) {
                    method743();
                    return;
                } else {
                    return;
                }
            }
        }
    }

  private void method743() { // было: e
        int __stk1;
        if (mc.field_1724 == null) {
            return;
        } else {
            if (mc.field_1761 != null) {
                int var1 = mc.field_1724.method_31548().field_7545;
                class_1799 var2 = mc.field_1724.method_31548().method_5438(var1);
                if (method745(var2, field360.method695())) {
                    int var3 = method744(field361.method695());
                    if (var3 >= 0) {
                        int var4 = (-1983534173 ^ -1983534201) + var1;
                        __stk1 = var3 >= (-1560490032 ^ -1560490023) ? var3 : (-888359550 ^ -888359514) + var3;
                        int var5 = __stk1;
                        mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, ((Integer) var5), 451488514 ^ 451488514, class_1713.field_7790, mc.field_1724);
                        mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, var4, 1031273674 ^ 1031273674, class_1713.field_7790, mc.field_1724);
                        mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, ((Integer) var5), -1888454902 ^ -1888454902, class_1713.field_7790, mc.field_1724);
                        return;
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

  private int method744(String arg0) { // было: a
        int var2 = -1421259389 ^ -1421259389;
        while (true) {
            if (var2 >= mc.field_1724.method_31548().method_5439()) {
                return 1631951889 ^ -1631951890;
            }
            if (var2 == mc.field_1724.method_31548().field_7545) {
                ++var2;
                continue;
            } else {
                if (method745(mc.field_1724.method_31548().method_5438(var2), arg0)) {
                    break;
                }
                ++var2;
                continue;
            }
        }
        return var2;
    }

  private boolean method745(class_1799 arg0, String arg1) { // было: a
        boolean __stk1;
        int var4;
        if (!arg0.method_7960()) {
            String var3 = arg1;
            var4 = -846082847 ^ 846082846;
            switch (var3.hashCode()) {
                case 1010520205:
                    if (!var3.equals(Decryptor.method1945(XorDecoder.method1946("×¬ùÃªþÐ¦ýàªõ¶ºê¸", -1115476419 ^ 945087486)))) {
                        break;
                    }
                    var4 = -1926769782 ^ -1926769782;
                    break;
                case 368602458:
                    if (!var3.equals(Decryptor.method1945(XorDecoder.method1946("Ã��\u000c\u0013¼\u00167bý9U��ä;*\"Ä\n\u001e4ò&5$à{)\u0001§9\u001d\u0004æ��\u00023º})-Æ\n-h", -1624831500 ^ -901560964)))) {
                        break;
                    }
                    var4 = 1350876313 ^ 1350876312;
                    break;
                case -1452819997:
                    if (!var3.equals(Decryptor.method1945(XorDecoder.method1946("=N§G��\u0001YXC@\u0014L~\u0005n¦C-@XNp y\u0018g¿z\u001c{»K;\u007f¯e;s\u0017", -1181175829 ^ -1821009508)))) {
                        break;
                    }
                    var4 = 1187229099 ^ 1187229097;
                    break;
                case 1009763266:
                    if (!var3.equals(Decryptor.method1945(XorDecoder.method1946("qJ\u0002_{\\\u0015Hi_jxpP\u001b<m.5r~on6", -1506914845 ^ -1384294948)))) {
                        break;
                    }
                    var4 = -1284438317 ^ -1284438320;
                default:
            }
        } else {
            return 396241253 ^ 396241253;
        }
        switch (var4) {
            case 0:
                __stk1 = arg0.method_31574(class_1802.field_8288);
                break;
            case 1:
                if (arg0.method_31574(class_1802.field_8463)) {
                    __stk1 = -1016266745 ^ -1016266746;
                    break;
                } else {
                    if (!arg0.method_31574(class_1802.field_8367)) {
                        __stk1 = -2078625563 ^ -2078625563;
                        break;
                    } else {
                        __stk1 = -1016266745 ^ -1016266746;
                        break;
                    }
                }
            case 2:
                __stk1 = arg0.method_31574(class_1802.field_8575);
                break;
            case 3:
                if (arg0.method_7964().getString().toLowerCase().contains(Decryptor.method1945(XorDecoder.method1946("ÀÈp²NÏI¢êJ´Åê\u0011·Ø\u001bá", -2099126415 ^ 1590118023)))) {
                    __stk1 = -713073102 ^ -713073101;
                    break;
                } else {
                    if (!arg0.method_7964().getString().toLowerCase().contains(Decryptor.method1945(XorDecoder.method1946("\u0015ïÍTêúHÃÂ­OÚ°Rù§\u001c", 1001859216 ^ -1869165017)))) {
                        __stk1 = -164331017 ^ -164331017;
                        break;
                    } else {
                        __stk1 = -713073102 ^ -713073101;
                        break;
                    }
                }
            default:
                __stk1 = 1931822446 ^ 1931822446;
        }
        return __stk1;
    }

  private static int ai(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int aj(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int ak(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}