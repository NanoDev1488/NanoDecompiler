// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.cn
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.AngelVisuals;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_4185;
import net.minecraft.class_4185.class_7840;
import net.minecraft.class_437;
import net.minecraft.class_442;

public final class cn extends class_437 {

    // ---- поля ----
  private static final class_2960 field680; // было: h
  private static final String tB = "// flow obfuscation: ENABLED";
  private static final String tC = "// number obfuscation: ENABLED (XOR masking)";
  private static final String tD = "Protected by t.me/JoinerClient";
  private static final String tE = "// good luck with the next 9999 classes";
  private static final String tF = "// you are reading machine-generated garbage";
  private static final int lA = -48592812;
  private static final int lB = -1866525968;
  private static final int lC = 2064245589;
  private static final byte[] cK;

    static {
        cK = "+A23j1]]B!?~42)qmhNI<^O)@L!vn]nA_/hWKIG{kDv;!8a[$pps 50>eh^[I7O4Q[@t~ A!?(JClve3M~i9pxoHnD}]|A7-QE9pZVN9Q^ORuy&47\"4!d\\AiB\\/\".=@WXorZ>ruLUvc.LR0%-Po[CUiA,~+<8$AbgLZo%/D-T}R>RCA!c:y;apM!o0\\^l8Jvm$c]nqbaX[kJjLKA]];3--t/%Fk_Q/O_] gKNqs8=+^k93bYcH[?Tv!hG1_#A.Br".getBytes("ISO-8859-1");
        field680 = AngelVisuals.id(Decryptor.method1945(XorDecoder.method1946("\"ób\u001f£­s1þèd\u0019¢ºod³îx\u0010õr,¦^3ú²Y:= y%©6", 1228778987 ^ 1122199485)));
    }

  public cn() { // было: <init>
        super(class_2561.method_43470(Decryptor.method1945(XorDecoder.method1946("'âÆbõÞnÖ|ºæßLÓ¤Gð÷9÷Ó¼;dõïJòzõñÔ", 1505530940 ^ -1340582348))));
    }

  protected void method1089() { // было: o
        int var1 = field_22789 / (-1777976354 ^ -1777976356) - (-1088781253 ^ -1088781217);
        method_37063(class_4185.method_46430(class_2561.method_43470(Decryptor.method1945(XorDecoder.method1946("á\u001a¦ÀåmÖ´4¢×!­õÊ\u001aØÀÊ\u0014×", 884447892 ^ -1940009197))), lp0 -> method1092(lp0)).method_46434(var1, field_22790 - (185605353 ^ 185605289), 1247183385 ^ 1247183569, 1775155757 ^ 1775155769).method_46431());
        method_37063(class_4185.method_46430(class_2561.method_43470(Decryptor.method1945(XorDecoder.method1946("\u000fõÏ¥\u001cøôë\u000eåÈª\u000e´ãX­õ®\u0006èý", 1610152798 ^ -1621334990))), lp0 -> method1091(lp0)).method_46434(var1, field_22790 - (13209285 ^ 13209315), 1603809068 ^ 1603809252, -1691700761 ^ -1691700749).method_46431());
    }

  public void method1090(class_332 arg0, int arg1, int arg2, float arg3) { // было: a
        arg0.method_25294(1535489826 ^ 1535489826, -362503060 ^ -362503060, field_22789, field_22790, -2002683321 ^ 2004965487);
        int var5 = Math.min(field_22789, ((int) (((float) field_22790) * 1.7799999713897705f)));
        int var6 = (field_22789 - var5) / (2139608915 ^ 2139608913);
        arg0.method_25302(lp0 -> class_1921.method_62277(((class_2960) lp0)), field680, var6, 777101191 ^ 777101191, 0.0f, 0.0f, var5, field_22790, var5, field_22790, var5, field_22790);
        arg0.method_25294(-277760789 ^ -277760789, 1770348375 ^ 1770348375, field_22789, field_22790, -1247409358 ^ 984620078);
        arg0.method_25300(field_22793, Decryptor.method1945(XorDecoder.method1946("ãJÞ)Íoê(Ñ`\u001cÉc6ã:ü(èld", 2033123750 ^ 547097088)), field_22789 / (121435556 ^ 121435558), -798097904 ^ -798097864, -1143770511 ^ 1144757148);
        arg0.method_25300(field_22793, Decryptor.method1945(XorDecoder.method1946("\u007f¬;'6!DdE\u00030£\u0016%r 2=6£IM", -1741609860 ^ -398080391)), field_22789 / (18205515 ^ 18205513), -52482597 ^ -52482591, -265658139 ^ 260229968);
        super.method_25394(arg0, arg1, arg2, arg3);
    }

  private static void method1091(class_4185 arg0) { // было: a
        class_310.method_1551().method_1592();
    }

  private static void method1092(class_4185 arg0) { // было: b
        class_310.method_1551().method_1507(new class_442());
    }

  private static int kb(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int kc(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int kd(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}