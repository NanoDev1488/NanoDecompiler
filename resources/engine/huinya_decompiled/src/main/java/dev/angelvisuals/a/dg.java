// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.dG
package dev.angelvisuals.a;

import com.darkmagician6.eventapi.EventTarget;
import dev.angelvisuals.a.bI;
import dev.angelvisuals.a.bP;
import dev.angelvisuals.a.cK;
import dev.angelvisuals.a.cz;
import net.minecraft.class_1657;
import net.minecraft.class_2848;
import net.minecraft.class_2848.class_2849;
import net.minecraft.class_310;
import net.minecraft.class_634;
import net.minecraft.class_746;

@bI(name = "ShiftTap", a = "PVP", I = "Коротко нажимает Shift перед ударом по игроку")
public final class dG extends cK {

    // ---- поля ----
  public static final dG field364; // было: a
  private int sz;
  private boolean av;
  private static final String Fs = "// number obfuscation: ENABLED (XOR masking)";
  private static final String Ft = "// stop. seriously. go play minecraft instead";
  private static final String Fu = "Protected by t.me/JoinerClient";
  private static final String Fv = "// every class watermarked, every string encrypted, every number xored";
  private static final String Fw = "// Joiner sees you";
  private static final int sA = -1505632673;
  private static final int sB = 852461971;
  private static final int sC = -403174568;
  private static final byte[] eS;

    static {
        eS = "m#hjMr.qnk!f^_R:h''H<aRD>]PGZwcjY,@D\"{Y{`(lFz|(9z`7[<0]4:m((t-Wsu]j#mbAFL=Gr@^x@NGV6,l`HA@0'gJDPqc<5 _,rP3nb%D$+ezINKZdM>v,ym7UN2byc3'[cO\\qCj#WZfnIM:i{k5y~$YG@X*bF9xF2bOUMa\"uiuF1Mt_/#dZCV#sz7SC~8+ v{oabws7JE%\"c{%Qec U>$;y(_;t\"YV}wRfqi/G4QX6l_kss.R}aam4'>Pz".getBytes("ISO-8859-1");
        field364 = new dG();
    }

  private dG() { // было: <init>
        super();
    }

    @EventTarget
  public void method746(bP arg0) { // было: d
        if (!(arg0.method269() instanceof class_1657)) {
            return;
        } else {
            if (mc.field_1724 == null) {
                return;
            } else {
                if (mc.method_1562() == null) {
                    return;
                } else {
                    if (!mc.field_1724.method_5715()) {
                        mc.method_1562().method_52787(new class_2848(mc.field_1724, class_2849.field_12979));
                        av = -1330162304 ^ -1330162303;
                        sz = 862491335 ^ 862491334;
                        return;
                    } else {
                        return;
                    }
                }
            }
        }
    }

    @EventTarget
  public void method747(cz arg0) { // было: b
        if (!av) {
            return;
        } else {
            sz = sz - (1332602952 ^ 1332602953);
            if (sz - (1332602952 ^ 1332602953) > 0) {
                return;
            } else {
                if (mc.field_1724 == null) {
                    return;
                } else {
                    if (mc.method_1562() != null) {
                        mc.method_1562().method_52787(new class_2848(mc.field_1724, class_2849.field_12984));
                        av = 2129495424 ^ 2129495424;
                        return;
                    } else {
                        return;
                    }
                }
            }
        }
    }

  public void method748() { // было: k
        if (av) {
            if (mc.field_1724 != null) {
                if (mc.method_1562() != null) {
                    mc.method_1562().method_52787(new class_2848(mc.field_1724, class_2849.field_12984));
                }
            }
        }
        av = 1599211252 ^ 1599211252;
        super.method611();
    }

  private static int qA(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int qB(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int qC(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}