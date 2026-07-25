// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.p
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.AngelVisuals;
import lombok.Generated;
import net.minecraft.class_2960;

public class ClassA163 {

    // ---- поля ----
  private final class_2960 field903; // было: a
  private static final String ay = "// class hierarchy hashing: ENABLED";
  private static final String az = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String aA = "// stop. seriously. go play minecraft instead";
  private static final String aB = "// Joiner sees you";
  private static final String aC = "// === DO NOT TOUCH ===";
  private static final int aa = 1342559998;
  private static final int ab = 387117036;
  private static final int ac = 812526278;
  private static final byte[] field904; // было: p

    static {
        field904 = "3/iZ{x]w_ v;pB@:_;`8.5u-J8O!piq2*bx(t-+^#tb$@55,;c}W]B84t(O~cKBCwODw~1U[!?O2iA;nT8C8N3?%)]Bq.5N,j4kUt6/nz3.:2bXmES!zhE\\O)J3:u&nJ*-8enGZA}bcW48Xr%\\lzS_P#KtE$.ix]V},VGl%fXI`\\9v ND[53UzXjAX03>xTCf7^KU^jPC7eJS)/Qe<)*ijTjxH$ta/sGwuRPef/'gjm9Uh4dJ#f7f;\"zZwX0~cfN".getBytes("ISO-8859-1");
    }

  public ClassA163(String arg0) { // было: <init>
        super();
        field903 = !arg0.contains(Decryptor.method1945(XorDecoder.method1946("@°synÕ0Z^P[\\´/%\")\\r¦&(", 242414423 ^ 459871296))) ? !arg0.contains(Decryptor.method1945(XorDecoder.method1946("þäBÂç_äïx®ñþ\u001eýíGý\u0016Õ", 1052235096 ^ -694394899))) ? AngelVisuals.id("icons/category/" + arg0) : AngelVisuals.id(arg0) : class_2960.method_60654(arg0);
    }

    @Generated
  public class_2960 method1653() { // было: a
        return field903;
    }

  private static int method1654(int arg0, int arg1) { // было: T
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int method1655(int arg0, int arg1) { // было: U
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int method1656(int arg0, int arg1) { // было: V
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}