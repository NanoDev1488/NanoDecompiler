// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.n
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import dev.angelvisuals.AngelVisuals;
import dev.angelvisuals.a.ClassA145;
import dev.angelvisuals.a.ClassA146;
import dev.angelvisuals.a.ad;
import dev.angelvisuals.a.cM;
import dev.angelvisuals.a.cN;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import ru.nexusguard.protection.annotations.Native;

public class ClassA12 extends cN implements ClassA146 {

    // ---- поля ----
  private final cM field62; // было: a
  private static final String ao = "// === DO NOT TOUCH ===";
  private static final String ap = "// nice try. closed source for a reason.";
  private static final String aq = "// you are reading machine-generated garbage";
  private static final String ar = "// every class watermarked, every string encrypted, every number xored";
  private static final String as = "// Joiner sees you";
  private static final int field63 = -598984039; // было: U
  private static final int field64 = -1517849472; // было: V
  private static final int field65 = 839825805; // было: W
  private static final byte[] field66; // было: n

    static {
        field66 = "^&(n.l) hF=UQg*K?^7k,WNDiq@pBXQvR=,A83{,NZ;DzB95[_;h98)$MoxCPV^Wohoh.:/c3cit-!~<?tjHy?DSJWyL@h(*#2yR|#!Oie<{d,de&8:dkm`#G0\\})M\"pxvOj3,CpG.kXK\\hI-  _eXL#I:qK;betCUt@n8f4NLNl$HV-[ g6=_8@x)I5KnVzStb::$pXv3erDoA=UghCrG5~^M_4.ut!pm8-.`|\\}?)[Xp><LIH*zKGJxEf,Pg<>".getBytes("ISO-8859-1");
    }

  public ClassA12() { // было: <init>
        super(Decryptor.method1945(XorDecoder.method1946("p\u0011ì>?\u0016µ\u001f\u0011A\u0017#\u0004¿?pB?!\u0010ç{", -1535705611 ^ -492008771)));
        field62 = AngelVisuals.getInstance().getRCTRepository();
    }

    @Native
  public void method103(LiteralArgumentBuilder arg0) { // было: a
        arg0.executes(lp0 -> method105(lp0));
        arg0.then(cN.method73(Decryptor.method1945(XorDecoder.method1946(")´ö9xÝ×&\u007f\u001a}ë9!½Â\u001d%h", -1168366991 ^ -268581059)), IntegerArgumentType.integer(-2139601971 ^ -2139601972, 181942468 ^ 181942523)).executes(lp0 -> method104(lp0)));
    }

  private int method104(CommandContext arg0) { // было: e
        ClassA145 var2 = AngelVisuals.getInstance().getServerHandler();
        if (var2.method1382()) {
            if (!var2.method1375()) {
                int var3 = (((Integer) arg0.getArgument(Decryptor.method1945(XorDecoder.method1946("V+n+\u0007BO4��\n\u000f\u0008\u0002\u0016s+^\"Z\u000fZ\u001d\u0004z", -1371405360 ^ -377783837)), Integer.class))).intValue();
                field62.method432(var3);
                return -363132358 ^ -363132357;
            } else {
                ad.method425().method429(Decryptor.method1945(XorDecoder.method1946("`o\\î4\rQë\u001avKð\u000eiSº\u001aX_³ \u0007;", 1422596883 ^ 1391545548)), class_2561.method_43470(" Вы находитесь в режиме " + String.valueOf(class_124.field_1061) + "пвп"));
                return 818376456 ^ 818376457;
            }
        } else {
            ad.method425().method429(Decryptor.method1945(XorDecoder.method1946("&ÁÍ¼\u0007þÆ¾\u001fïí\u0012ïÞYßþ¦(Çï", -1652596504 ^ 1339287171)), class_2561.method_43470(" Не работает на этом " + String.valueOf(class_124.field_1061) + "сервере"));
            return -274706038 ^ -274706037;
        }
    }

  private int method105(CommandContext arg0) { // было: f
        ClassA145 var2 = AngelVisuals.getInstance().getServerHandler();
        if (var2.method1382()) {
            if (!var2.method1375()) {
                field62.method432(var2.method1389());
                return 1597197039 ^ 1597197038;
            } else {
                ad.method425().method429(Decryptor.method1945(XorDecoder.method1946("Ûèå£øÑìÐêìÞÂýÅµÑãâÓ", 2133291843 ^ -778713179)), class_2561.method_43470(" Вы находитесь в режиме " + String.valueOf(class_124.field_1061) + "пвп"));
                return -2124162440 ^ -2124162439;
            }
        } else {
            ad.method425().method429(Decryptor.method1945(XorDecoder.method1946("ôN,ÅÕq'ÇÍ`\u000cîÀ`?êP\u001fßúHp", -20122173 ^ 1434582138)), class_2561.method_43470(" Не работает на этом " + String.valueOf(class_124.field_1061) + "сервере"));
            return -1398701676 ^ -1398701675;
        }
    }

  private static int method106(int arg0, int arg1) { // было: N
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int method107(int arg0, int arg1) { // было: O
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int method108(int arg0, int arg1) { // было: P
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}