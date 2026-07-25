// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.t
package dev.angelvisuals.a;

import com.darkmagician6.eventapi.EventTarget;
import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.a.ClassA22;
import dev.angelvisuals.a.aV;
import dev.angelvisuals.a.bI;
import dev.angelvisuals.a.bp;
import dev.angelvisuals.a.cK;
import lombok.Generated;

@bI(name = "AntiInvis", a = "RENDER", I = "Видно инвизок")
public final class ClassA95 extends cK {

    // ---- поля ----
  public static final ClassA95 field365; // было: a
  private final aV field366; // было: a
  private static final String aT = "// this jar protected by JoinerObfuscator";
  private static final String aU = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final String aV = "// this jar protected by JoinerObfuscator";
  private static final String aW = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final String aX = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final int am = -1260581563;
  private static final int an = 1400085380;
  private static final int ao = 1633372024;
  private static final byte[] field367; // было: t

    static {
        field367 = "|]Hy$SJVZfI1jc`_k4\"('.A'^P6)z4@'w^ki6-kgk$7y['cH0G3>ob\\R* 92S%\"@'$#f`HJT>=l'c_pJ@T&-kX=t%3!r'cmp!mG}#wAB:d0'_WA|'Qj<B\\[L1NY.\"Nvk^i>fe72Mp+ {^FLy G6]j{4\\Vf`\"-C+4s,AQ'b<nI_t~>AB2*a/G2-v7mZD5J@yWT>Ez3+AcNHqQkhoUG0+vFs)}/Ayh\"5s|od'ye\"S}\"pz[|AO:QmGT0*Wicd>clu*h".getBytes("ISO-8859-1");
        field365 = new ClassA95();
    }

  private ClassA95() { // было: <init>
        super();
        field366 = new aV(Decryptor.method1945(XorDecoder.method1946("úúcáÚ9¸ÓxÈßè1ææùeî¦ã5°", 282757665 ^ -1647167308)), bp.field909.method1687(0.5f));
    }

    @EventTarget
  public void method749(ClassA22 arg0) { // было: a
        arg0.method239(field366.method667().method1680());
        arg0.y();
    }

    @Generated
  public aV method750() { // было: a
        return field366;
    }

  private static int af(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int ag(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int ah(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}