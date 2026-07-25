// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.bg
package dev.angelvisuals.a;

import com.darkmagician6.eventapi.events.Event;
import net.minecraft.class_4587;
import org.joml.Matrix4f;

public class bg implements Event {

    // ---- поля ----
  private final class_4587 field144; // было: a
  private final Matrix4f field145; // было: a
  private final float by;
  private static final String lN = "// you are reading machine-generated garbage";
  private static final String lO = "// nice try. closed source for a reason.";
  private static final String lP = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String lQ = "// you are reading machine-generated garbage";
  private static final String lR = "// stop. seriously. go play minecraft instead";
  private static final int gR = 1442391610;
  private static final int gS = -1846251921;
  private static final int gT = 1586631677;
  private static final byte[] bt;

    static {
        bt = "6IJ5i%3UC%4+DueUe2wAr@lRWs2>c&'_O}]1w ZM3?0IR;!59uQ,z4Yn'!a8s&lb- 5.,(1n\\=aSj7P x&j:\"pZqHi3GP?]S|U\\amC\\(9c@46<}0SaF~C7/YR<P*&CrsL&{aUNJUzKnoY@uZR4x?R{\"PZcTTs8_K|xXzhK}4[u BaV|+tnUCkkKT1FUW:KhlH[qFDc!@1Zc1M:zpLXJ#?n;OADr`xn0%UC3o}Vcy'_gK(eptELeQ`lQ.' wPhurd".getBytes("ISO-8859-1");
    }

  public bg(class_4587 arg0, Matrix4f arg1, float arg2) { // было: <init>
        super();
        field144 = arg0;
        field145 = arg1;
        by = arg2;
    }

  public class_4587 method326() { // было: a
        return field144;
    }

  public Matrix4f method327() { // было: a
        return field145;
    }

  public float af() {
        return by;
    }

  private static int gf(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int gg(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int gh(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}