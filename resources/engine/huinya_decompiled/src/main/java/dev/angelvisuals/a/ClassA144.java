// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.a
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.a.cF;
import dev.angelvisuals.a.cH;
import lombok.Generated;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import net.minecraft.class_746;

public final class ClassA144 implements cF {

    // ---- поля ----
  private static final String field759 = "// number obfuscation: ENABLED (XOR masking)"; // было: a
  private static final String field760 = "// reverse-engineering this jar is a waste of time, friend"; // было: b
  private static final String field761 = "// this jar protected by JoinerObfuscator"; // было: c
  private static final String field762 = "// === DO NOT TOUCH ==="; // было: d
  private static final String field763 = "// string encryption: ENABLED (AES-128/ECB + XOR)"; // было: e
  private static final int field764 = 99016686; // было: a
  private static final int field765 = -154648597; // было: b
  private static final int field766 = -1853453012; // было: c
  private static final byte[] field767; // было: a

    static {
        field767 = "|rBLCi,~n?dlnnD83>Bk-#1$tM)' 8p7 '7~t]CjHD~<_i (=1b\"&kp@aBd<~=\"BL#_:r{#cpFh;/R[AraI_2wQJ*4;w2q%rBMJ.]yX)%{(\\x&~`+E$[44L?VF#U 39XKz&{\\z<tfUA'5mvzS0wEc-9rohXxC`Q,tThP8,wThMG5!mg}Xjk$Q!`.m)Cgl)1Vu9\"NxQo\"st_i-4VI=:_\\[DE7}l8XpyFj\"2&pM\"6b ~]8b3e>3p;'ojtu)_ec 4_S".getBytes("ISO-8859-1");
    }

  public static cH method1362() { // было: a
        return new cH(mc.field_1724.method_36454(), mc.field_1724.method_36455());
    }

  public static cH method1363(class_243 arg0) { // было: a
        return new cH(((float) class_3532.method_15338(Math.toDegrees(Math.atan2(arg0.field_1350, arg0.field_1352)) - 90.0)), ((float) class_3532.method_15338(Math.toDegrees(-Math.atan2(arg0.field_1351, Math.hypot(arg0.field_1352, arg0.field_1350))))));
    }

  public static cH method1364(class_243 arg0) { // было: b
        return method1363(arg0.method_1020(mc.field_1724.method_33571()));
    }

    @Generated
  private ClassA144() { // было: <init>
        super();
        throw new UnsupportedOperationException(Decryptor.method1945(XorDecoder.method1946("¢[±¸1¦´Q¿ó¤l®ÑU­Ð.²¸Ñ`BbÍÔ+¬ìc©)µ´¸\\º±n»H¿éÓré»q®¹RÀÐ~÷ªj¼ïÓnÒ[Éá", -1837294372 ^ 1317602877)));
    }

  private static int method1365(int arg0, int arg1) { // было: a
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int method1366(int arg0, int arg1) { // было: b
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int method1367(int arg0, int arg1) { // было: c
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}