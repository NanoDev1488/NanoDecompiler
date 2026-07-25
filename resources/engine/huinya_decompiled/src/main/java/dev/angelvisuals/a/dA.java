// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.da
package dev.angelvisuals.a;

import dev.angelvisuals.AngelVisuals;
import dev.angelvisuals.a.bI;
import dev.angelvisuals.a.cK;
import net.minecraft.class_310;

@bI(name = "ClickGUI", a = "RENDER", I = "Меню чита")
public final class da extends cK {

    // ---- поля ----
  public static final da field517; // было: a
  private static final String zE = "// you are reading machine-generated garbage";
  private static final String zF = "Protected by t.me/JoinerClient";
  private static final String zG = "// good luck with the next 9999 classes";
  private static final String zH = "// === DO NOT TOUCH ===";
  private static final String zI = "// flow obfuscation: ENABLED";
  private static final int pe = -482908340;
  private static final int pf = 1038404219;
  private static final int pg = -621385988;
  private static final byte[] dP;

    static {
        dP = "otzV>O%TT(`^:`0bRV3|DNq*]@V<\"rchj:#mI}zi\\>I&Tm/>lo&EeaknQZ@)Q-P+L]>y3JE[:9o~XZ+-dbR}e17<x#Y$B]h_X/}dS?Ocng\\}Rn *5jz9TinnusQX&2:,B{h~v7jmE|%:[&4noDK=52fEI{<2m%5$'b>S5KZP`2d8IWD.\"(fJZz;Q9fk%Qp]@SFSR\\?^|bZ\\G:ot )u1JLu)usJ9TNd9K/Fh\"e=wKi-`EHX1clB.I)/zw(^n409sM".getBytes("ISO-8859-1");
        field517 = new da();
    }

  private da() { // было: <init>
        super();
        method894(-671663900 ^ -671663684);
    }

  public void method892() { // было: j
        if (mc.field_1687 != null) {
            AngelVisuals.getInstance().getMenuScreen().field608 = 1422681481 ^ 1422681481;
            if (mc.field_1755 != AngelVisuals.getInstance().getMenuScreen()) {
                mc.method_1507(AngelVisuals.getInstance().getMenuScreen());
                super.method610();
            }
        } else {
            q(2080571551 ^ 2080571551);
        }
    }

  public void method893() { // было: k
        if (mc.field_1755 == AngelVisuals.getInstance().getMenuScreen()) {
            AngelVisuals.getInstance().getMenuScreen().field608 = -1503692832 ^ -1503692831;
        }
        super.method611();
    }

  public void method894(int arg0) { // было: v
        if (arg0 != (154075596 ^ -154075597)) {
            super.method622(arg0);
        }
    }

  private static int nq(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int nr(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int ns(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}