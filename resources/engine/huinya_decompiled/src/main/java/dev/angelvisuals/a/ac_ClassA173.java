// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.ac.b
package dev.angelvisuals.a;

import dev.angelvisuals.a.ac;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.imageio.ImageIO;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;

@Metadata
final class ac_ClassA173 extends Lambda implements Function0 {

    // ---- поля ----
  final ac field1000; // было: a
  private static final String hb = "// this jar protected by JoinerObfuscator";
  private static final String hc = "// === DO NOT TOUCH ===";
  private static final String hd = "// number obfuscation: ENABLED (XOR masking)";
  private static final String he = "// nice try. closed source for a reason.";
  private static final String hf = "// reverse-engineering this jar is a waste of time, friend";
  private static final int eb = 1799316518;
  private static final int ec = -1841634524;
  private static final int ed = -1529217714;
  private static final byte[] aA;

    static {
        aA = "vSD#]x7'|{/85}^Fk&J|k3_2|>mI~d+'GQy)FIC~Dl?/?aei6^+H/,@0\\F[Mqq9#LnLB{fBFKDOk33GMa>!W}!-IYv`pHJ!ngv[_3Wru6;&<sf4 &`B_89N<Bbra5D}vEiRv0P N61PT@7[[W{VJamj\\ATZ$'l9,%kJ6~@!5P`[h3-zBKH r1,0~8;F'1Xw(NT1ri'IJXo<Eai ,:GK#{$[I;P?DNl15x' Hk5g:>K\\zXUlAVxWzz+I#+6'}P)18".getBytes("ISO-8859-1");
    }

   ac_ClassA173(ac arg0) { // было: <init>
        super(-451466417 ^ -451466417);
        field1000 = arg0;
    }

    @Nullable
  public final BufferedImage method1898() { // было: a
        System.currentTimeMillis();
        BufferedImage var1;
        try {
            var1 = ImageIO.read(((InputStream) new ByteArrayInputStream(field1000.method1902())));
        } catch (Exception var2) {
            var1 = null;
        }
        return var1;
    }

  public Object invoke() {
        return method1898();
    }

  private static int dA(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int dB(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int dC(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}