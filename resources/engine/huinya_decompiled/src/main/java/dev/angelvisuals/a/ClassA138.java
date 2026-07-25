// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.O
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.AngelVisuals;
import dev.angelvisuals.a.ClassA136_ClassA137;
import dev.angelvisuals.a.bl;
import dev.angelvisuals.a.bp;
import dev.angelvisuals.a.cF;
import dev.angelvisuals.a.cQ;
import dev.angelvisuals.a.ch;
import java.awt.Color;
import java.util.List;
import lombok.Generated;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_5250;
import net.minecraft.class_746;
import ru.nexusguard.protection.annotations.Native;

public final class ClassA138 implements cF {

    // ---- поля ----
  private static final String GU = "// reverse-engineering this jar is a waste of time, friend";
  private static final String GV = "// class hierarchy hashing: ENABLED";
  private static final String GW = "Protected by t.me/JoinerClient";
  private static final String GX = "// number obfuscation: ENABLED (XOR masking)";
  private static final String GY = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final int tx = 233010934;
  private static final int ty = 41170185;
  private static final int tz = -1694557694;
  private static final byte[] fh;

    static {
        fh = "1Q\\YDQ,yJFm~/cpu}KQU_r.JzD-I0fv9.2VjqjhuzG`?7Wx{lm6V-?\\Ys*V9u$I}p<d?zX^|''{BGoD,g?@gD8Ol1tnUBIHd?|,WMTz=KKzZM^V&v_4YBex$mO8Z4gS#agT7&IaL\"Bd3Uf)t2'jfHq.U7d(5<qnV<B={lKiw8o[qVsx_=!)$w;J<++xL8B!M]\\:fbw)7Pn9S&~a5?- 2vr.]PHF<wky_m(6lX&{Bs+9P~x93t+mVg7pRw}Ifb%#c".getBytes("ISO-8859-1");
    }

    @Native
  public static void method1167(ClassA136_ClassA137 arg0, Object arg1) { // было: a
        class_2561 __stk1;
        class_2561 var2 = method1168(Decryptor.method1945(XorDecoder.method1946("ñ%D\u007f%?\u000f\u0005a\u0001\u0018eN dr74\n", 1435793704 ^ 1654467055)), AngelVisuals.getInstance().getThemeManager().method481().method449(), AngelVisuals.getInstance().getThemeManager().method481().method457());
        __stk1 = !class_2561.method_30163(String.valueOf(arg1)).method_27661().method_36136(class_2583.field_24360).isEmpty() ? ((class_2561) class_2561.method_30163(String.valueOf(arg1)).method_27661().method_36136(class_2583.field_24360).getFirst()) : class_2561.method_30163(Decryptor.method1945(XorDecoder.method1946("hé\u0002fé\u000cÝPì.qù}eê\u0012ý6Çx", -740629574 ^ 1637827518)));
        class_2561 var3 = __stk1;
        mc.field_1724.method_7353(var2.method_27661().method_10852(((class_2561) var3)), -287941166 ^ -287941166);
    }

    @Native
  private static class_2561 method1168(Object arg0, bp arg1, bp arg2) { // было: a
        float __stk1;
        class_5250 var3 = class_2561.method_43473();
        int var4 = String.valueOf(arg0).length();
        int var5 = 2040829688 ^ 2040829688;
        while (var5 < var4) {
            __stk1 = var4 <= (-1811930879 ^ -1811930880) ? 0.0f : ((float) var5) / ((float) (var4 - (-1885707169 ^ -1885707170)));
            float var6 = __stk1;
            bp var7 = cQ.method1716(arg1, arg2, ((Float) var6));
            class_2561 var8 = ((class_2561) class_2561.method_30163(String.valueOf(String.valueOf(arg0).charAt(var5))).method_27661().method_36136(class_2583.field_24360.method_36139(var7.method1680()).method_10982(Boolean.valueOf(-308807704 ^ -308807703))).getFirst());
            var3 = var3.method_27661().method_10852(var8);
            ++var5;
            continue;
        }
        return var3.method_27661().method_10852(((class_2561) class_2561.method_30163(Decryptor.method1945(XorDecoder.method1946("ÖN¯ÙÙ1¬öÎ*ºøú\u0010¾ì JëË\t×", 563902597 ^ -2139748854))).method_36136(class_2583.field_24360.method_10977(class_124.field_1080).method_10982(Boolean.valueOf(-1037584515 ^ -1037584515))).getFirst()));
    }

  public static void method1169(Object arg0) { // было: d
        method1167(ClassA136_ClassA137.field709, arg0);
    }

  public static void method1170(Object arg0) { // было: e
        method1167(ClassA136_ClassA137.field710, arg0);
    }

  public static void method1171(Object arg0) { // было: f
        method1167(ClassA136_ClassA137.field711, arg0);
    }

  private static class_2583 method1172(ClassA136_ClassA137 arg0) { // было: a
        return class_2583.field_24360.method_36139(arg0.method1164().getRGB());
    }

    @Generated
  private ClassA138() { // было: <init>
        super();
        throw new UnsupportedOperationException(Decryptor.method1945(XorDecoder.method1946("EiO_\u0003X³vcAôC^P6gpª7\u001cL¿6Rgkpr|P33\u0019Rë`Qk®q\u001bK³_nDV\\EbzAî4@cî\\Cc©^`>7LgðMXBè4\\|5i7æ", 1254540970 ^ -1848840532)));
    }

  private static int rt(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int ru(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int rv(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}