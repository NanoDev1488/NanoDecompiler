// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.bE.a
package dev.angelvisuals.a;

import dev.angelvisuals.a.bE;
import dev.angelvisuals.a.bh;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata
public final class bE_ClassA175 implements bE {

    // ---- поля ----
  static final bE_ClassA175 field1006; // было: a
  private final bE field1007; // было: b
  private static final String ow = "// class hierarchy hashing: ENABLED";
  private static final String ox = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final String oy = "// Joiner sees you";
  private static final String oz = "// class hierarchy hashing: ENABLED";
  private static final String oA = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final int iD = -820558283;
  private static final int iE = -1625386349;
  private static final int iF = -1770646381;
  private static final byte[] bS;

    static {
        bS = "9N+pspq{U\\y7{o_=o1;iS9Xe6J\\CX4V8M_Zkvne'WS<B?W`AP;mZdO*U^u,hYuln(AIQP7^ydbpMf1X[V{+MAxHhZ5m*>*u1nV<w^*A~<|D;5fr=$MP{k&)rCvU[+f8ngPsRdB(2Z`A?Lt@:r3!-<VS*2bkh?cC{M[=s6%$m4q^aO' z[x#\\O(L\\~iy?hAU!\\7n15NP})*5/s\"]=Z@q9njZavamyH`Xs1^:{O6#>3]]rtX$Q9s!m2Y5PHaJP{k$y".getBytes("ISO-8859-1");
        field1006 = new bE_ClassA175();
    }

  private bE_ClassA175() { // было: <init>
        super();
        field1007 = bh.method1918();
    }

    @NotNull
  public List method1916() { // было: m
        return field1007.method1917();
    }

  private static int hC(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int hD(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int hE(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}