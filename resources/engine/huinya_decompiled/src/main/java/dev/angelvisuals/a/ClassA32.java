// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.H
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.a.ClassA30_ClassA31;
import dev.angelvisuals.a.bo;
import java.util.Collection;

public class ClassA32 extends bo {

    // ---- поля ----
  private static final String uL = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final String uM = "// every class watermarked, every string encrypted, every number xored";
  private static final String uN = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String uO = "// === DO NOT TOUCH ===";
  private static final String uP = "// every class watermarked, every string encrypted, every number xored";
  private static final int mm = 1099451210;
  private static final int mn = -1684481524;
  private static final int mo = -1002527093;
  private static final byte[] cW;

    static {
        cW = "dMatb(P2Y95#-g4~:%,.XQb\"\"(!t)QY'Kf2>${6yMa^I:|^xHxVUZPBZi[*x_:elW7 SHc;XE1]l1_e`#u\\J'iR v]X,'l+;7\".%T`auv\\IV}}b@\"?V!$X#sflB_6c.MCa~a&a%ieEB*_#j'1 T8wSiUhq&|HR3.B`*P.--u&k~cT W 5xbFB1<3a wUg'Ys@+%Fu[(6E7kG:;_RI3^]rjF(<)&(mKnv*ld:w353f)*~+QpQI}??<k2vlj4UAhk/".getBytes("ISO-8859-1");
    }

  public ClassA32() { // было: <init>
        super(Decryptor.method1945(XorDecoder.method1946("\u0019Gì\\\u0005ï,A§Ý\u000bd£Ë\tGË\u0018\u000cÎ", 1166686637 ^ -1229236718)), Decryptor.method1945(XorDecoder.method1946("Gõ¢vî»vÑèdÇYÔÚQ¿Ó", -1978800598 ^ 1687156374)), new ClassA30_ClassA31().getType(), () -> new HashSet());
    }

  public boolean method347(String arg0) { // было: i
        return b().contains(arg0);
    }

  private static int kL(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int kM(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int kN(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}