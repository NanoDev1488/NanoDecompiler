// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.bd
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.a.ac;
import dev.angelvisuals.a.dO;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata
public final class bd implements dO {

    // ---- поля ----
    @NotNull
  private final ac field1017; // было: b
    @NotNull
  private final String li;
  private final int gw;
  private static final String lj = "// reverse-engineering this jar is a waste of time, friend";
  private static final String lk = "Protected by t.me/JoinerClient";
  private static final String ll = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String lm = "Protected by t.me/JoinerClient";
  private static final String ln = "// stop. seriously. go play minecraft instead";
  private static final int gx = 203229787;
  private static final int gy = 1141505658;
  private static final int gz = 261992489;
  private static final byte[] bn;

    static {
        bn = "c<S7sD;qjCv,!$pC6@\\Km^ Sn3}2^MV:H~jLkKMrmu4G4L4P#DiI;Ev3-qu{]355H}Q~R~>T${k@Z6(eZ>2wu^'4H&&1gxDn!'`J[X^D}X$ ?Iv2wsqhp@E8M=^1z93Snsd7Y@+QHqLD'}WKtPH]es80~M[X~>i1|/lt!9k\\J2L_?idgD\"U>3<\"Eo@aE#\";65|]aVs|zF+p5i$F)Il@6^-5r&Pf[{)U]yVxMG;3$7([x~jQSST8UFU'<1\",80-}H".getBytes("ISO-8859-1");
    }

  public bd(@NotNull ac arg0, @NotNull String arg1, int arg2) { // было: <init>
        Intrinsics.checkNotNullParameter(arg0, Decryptor.method1945(XorDecoder.method1946("*æÖ\u0008Õé/åË\u0001¸ÔZõÈÏ3æÝ", 1910086204 ^ -1854465185)));
        Intrinsics.checkNotNullParameter(arg1, Decryptor.method1945(XorDecoder.method1946("ÞÂãéâ«Ì­ÓÉîÀÄ¶ëó×å¯", -540645235 ^ 1293872725)));
        super();
        field1017 = arg0;
        li = arg1;
        gw = arg2;
    }

    @NotNull
  public ac method1932() { // было: a
        return field1017;
    }

    @NotNull
  public String method1933() { // было: E
        return li;
    }

  public void method1934(); // было: U

  public void method1935(); // было: V

  public void method1936(); // было: W

  public void method1937(); // было: X

  public void method1938(); // было: Y

  public void method1939(); // было: Z

  private static int fN(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int fO(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int fP(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}