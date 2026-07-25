// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.cR
package dev.angelvisuals.a;

import com.darkmagician6.eventapi.EventTarget;
import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.AngelVisuals;
import dev.angelvisuals.a.aN;
import dev.angelvisuals.a.aZ;
import dev.angelvisuals.a.bA;
import dev.angelvisuals.a.bI;
import dev.angelvisuals.a.bl;
import dev.angelvisuals.a.bp;
import dev.angelvisuals.a.cK;
import dev.angelvisuals.a.ch;
import java.awt.Color;

@bI(name = "CustomFog", a = "RENDER", I = "Шейдерный кастомный туман: цвет темы или радужный")
public final class cR extends cK {

    // ---- поля ----
  public static final cR field423; // было: a
  public final aZ field424; // было: g
  public final bA field425; // было: S
  public final bA field426; // было: T
  public final bA field427; // было: U
  private static final String yp = "Protected by t.me/JoinerClient";
  private static final String yq = "// === DO NOT TOUCH ===";
  private static final String yr = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final String ys = "// number obfuscation: ENABLED (XOR masking)";
  private static final String yt = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final int oq = 548419900;
  private static final int or = 403171875;
  private static final int os = -1699043549;
  private static final byte[] dD;

    static {
        dD = "AXg<SuBl<XNUijzwkK)Jc9/D#-uD_h4AD>xnOv'-oJLE'_H!WFw5NQhC!^n,\\QL1.+b6cB,uvJ@I]V0{8gBIA{a.0MrU>m}j1v^N}'p`Ehr + >eI,6(*k~yEmn:tCaa..V&i&@d./j_<'Ti6P$;b\"iWbx=5pR6CSyyr=4ExeO:*alL$p; D}9=9R,6]m045n9\\@*pkQ*HFtTwKp-.@aE`CH@!B+!i!rn3OrV<RpwijSQZl@K2&a]C&xZ,N(d<I0".getBytes("ISO-8859-1");
        field423 = new cR();
    }

  private cR() { // было: <init>
        super();
        String[] __obj1 = new String[-1840129108 ^ -1840129106];
        __obj1[2048819026 ^ 2048819026] = Decryptor.method1945(XorDecoder.method1946("ªÔÅx íó\"ÜçÃ\u0008*Ûëã\u0007ã.Ü×Ýb¾×ï}ÚØqÆïë\u0019ØÝt", 1183525936 ^ 253858525));
        __obj1[-373313562 ^ -373313561] = Decryptor.method1945(XorDecoder.method1946("²øÒýÛùàÌÀÄååÿþ¯ôÉÚäÿ¥ÞãËáÿæìä×ÕôÄßÀë", -2103487975 ^ 1411965828));
        field424 = new aZ(Decryptor.method1945(XorDecoder.method1946("\u0004vy´QH~\u0007r\u000cäNzjã;X\u007f­\u0015_\u0007è", -877794191 ^ 513274384)), __obj1);
        field425 = new bA(Decryptor.method1945(XorDecoder.method1946("\r\u0015+\u000b!7k$?\u0011*-\r-H70\u0012V\u000c#(4\u0005u,p\u0002$\u001c*\u0003\u000e\u0002/\u0001#1P>=2hs", 800026548 ^ 1639188979)), 120.0f, 10.0f, 500.0f, 5.0f);
        field426 = new bA(Decryptor.method1945(XorDecoder.method1946("u½Èè}ýÝ®Z½ÈbÙ¶y½Îê]ó°", 712553293 ^ -1481195627)), 0.20000000298023224f, 0.0f, 0.949999988079071f, 0.05000000074505806f);
        field427 = new bA(Decryptor.method1945(XorDecoder.method1946("\u0011úä\u001aÞý\u001cÞç\u0016¢Á¶CÿFÕð¶Eø5â½L¾ó=£ä¿5õì", -1663717293 ^ 1298226471)), 0.6499999761581421f, 0.0f, 1.0f, 0.05000000074505806f);
    }

    @EventTarget
  public void method818(aN arg0) { // было: a
        Color __stk1;
        if (ah()) {
            __stk1 = !field424.method696(Decryptor.method1945(XorDecoder.method1946("ùÑt~Ð&cébf®ÖX,¿à|\u001c¯Ö &³\"`ÈYe§Í&\u0007üR\u0013öfh", -1452994388 ^ -59471750))) ? method819() : method820();
            Color var2 = __stk1;
            arg0.method310(field425.bp());
            arg0.method313(field426.bp());
            arg0.method311(var2.getRGB());
            arg0.setCancelled(-229264254 ^ -229264253);
            return;
        } else {
            return;
        }
    }

  private Color method819() { // было: b
        bp var1 = AngelVisuals.getInstance().getThemeManager().method481().method449();
        float[] var2 = Color.RGBtoHSB(var1.method1695(), var1.method1696(), var1.method1697(), null);
        return Color.getHSBColor(var2[-1815769638 ^ -1815769638], var2[-278788970 ^ -278788969] * field427.bp(), Math.min(1.0f, var2[-1041124018 ^ -1041124020] + 0.11999999731779099f));
    }

  private Color method820() { // было: c
        float var1 = ((float) (System.currentTimeMillis() % (-1840225465149869449L ^ -1840225465149870793L))) / 8000.0f;
        return Color.getHSBColor(var1, Math.max(0.15000000596046448f, field427.bp()), 1.0f);
    }

  private static int mG(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int mH(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int mI(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}