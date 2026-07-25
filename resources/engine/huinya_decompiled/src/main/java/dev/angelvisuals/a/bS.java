// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.bs
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.a.bo;
import dev.angelvisuals.a.bs_ClassA29;
import java.util.Collection;
import ru.nexusguard.protection.annotations.Native;

public class bs extends bo {

    // ---- поля ----
  private static final String zY = "// good luck with the next 9999 classes";
  private static final String zZ = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final String Aa = "// stop. seriously. go play minecraft instead";
  private static final String Ab = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String Ac = "Protected by t.me/JoinerClient";
  private static final int pq = 1584725767;
  private static final int pr = -500116359;
  private static final int ps = -1436709046;
  private static final byte[] dT;

    static {
        dT = "aoH 6K)VrqHE8>g8?D, I>jsAag&@3*!epM(#h#0wR`!cM67x?%&/0HLV:>\\K`umE$'%6.#WRT{W|`ZY(3n$Z[y*V??[jSO4ivo<CpG>QJ`\"cc5e Aa|JB$O0O||FAc&WYO&5?9F DsY=}?!X>bNULY4~[T&4Crtfu3`(8h-{W)k;CQ-B\">*v~pmzjCJk*}fi?&n7B\")UxVYe0fsC!Yk[A##F|X3_vZEeRv4X.bZuZ}z)KG!xU?!%}@$E{\\1w`=9".getBytes("ISO-8859-1");
    }

  public bs() { // было: <init>
        super(Decryptor.method1945(XorDecoder.method1946("@\u001c×6wzÑ[^\u0017\u001a\\\u0019Þ?siÖ\u0006��XÛR", -2071474696 ^ -345906496)), Decryptor.method1945(XorDecoder.method1946("ðµvoômvûRSDOó«WJ¥£<\u001e", 1514739157 ^ 2034824982)), new bs_ClassA29().getType(), () -> new HashSet());
    }

  public boolean method345(String arg0) { // было: m
        return b().contains(arg0);
    }

    @Native
  public boolean method346(String arg0) { // было: n
        return b().remove(arg0);
    }

  private static int nC(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int nD(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int nE(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}