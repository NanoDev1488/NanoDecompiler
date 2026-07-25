// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.cX
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import java.util.Random;
import lombok.Generated;

public class cX {

    // ---- поля ----
  private final int oO;
  private final int oP;
  private final Random field790; // было: e
  private static final String zk = "// flow obfuscation: ENABLED";
  private static final String zl = "// every class watermarked, every string encrypted, every number xored";
  private static final String zm = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final String zn = "// number obfuscation: ENABLED (XOR masking)";
  private static final String zo = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final int oQ = -1275357953;
  private static final int oR = -247737027;
  private static final int oS = 1769101789;
  private static final byte[] dL;

    static {
        dL = "UWXlte'$(,e1{QR@HeoC{ o@M&EBaf`c33m\\%sj[TK!pyGO+G3/mju\"Bl\\nhj],K#msm<5L<6`@{Zl`}`5:)^4FE0/,wb7NP@6@rFb<,*eTP{Sv1dDjhMg$Hj-=uR0/'\".w.}hyQ{xUqxS@L>;m6oOB-\\[boO6;Wj8i@W3sR[8y`x8&K^qp%ox,DQjxH=fYv{<0A;h=8s@/cyn|pEp%J|c*&sC@:%A}>xJ-CQ~-7neF^\\3}Yyq9P:]7?=Xv6ETY8".getBytes("ISO-8859-1");
    }

  public cX(int arg0, int arg1) { // было: <init>
        super();
        field790 = new Random();
        if (arg0 <= arg1) {
            oO = arg0;
            oP = arg1;
            return;
        } else {
            throw new IllegalArgumentException(Decryptor.method1945(XorDecoder.method1946("4²#Îá\u0017¯\u0002;K­ç\u0012·§\u0015¿¦9¾½\" ½J¿+Í°£\u000e×ª4��Ó·³+êI¯¤ªM¿³B.J±\u001c¹îÛ", 840765576 ^ -724637454)));
        }
    }

  public int aj() {
        return oO + field790.nextInt(oP - oO + (-802693184 ^ -802693183));
    }

  public String toString() {
        return oO + ".." + oP;
    }

    @Generated
  public int ak() {
        return oO;
    }

    @Generated
  public int al() {
        return oP;
    }

  private static int ne(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int nf(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int ng(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}