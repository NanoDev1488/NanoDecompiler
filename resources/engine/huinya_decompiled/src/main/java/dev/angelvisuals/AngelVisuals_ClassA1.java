// исходный (обфусцированный) внутренний класс: dev.angelvisuals.AngelVisuals.a
package dev.angelvisuals;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.AngelVisuals;
import dev.angelvisuals.a.be;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.class_2960;
import net.minecraft.class_3300;

class AngelVisuals_ClassA1 implements SimpleSynchronousResourceReloadListener {

    // ---- поля ----
  private static final String ex = "Protected by t.me/JoinerClient";
  private static final String ey = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String ez = "// Joiner sees you";
  private static final String eA = "// flow obfuscation: ENABLED";
  private static final String eB = "// nice try. closed source for a reason.";
  private static final int cA = -111637214;
  private static final int cB = -624789126;
  private static final int cC = -1851659566;
  private static final byte[] ab;

    static {
        ab = "U:_d 'gd#j+U\"oJ%Q{~~-i%I_g/$szYKi60W85b8Cr3bcMz88F`4#!D$WJwX@PD/e\")yDvMcS?^>7AHBI*fCjH4ktiwOJ1-TLf51d4~eEy@ja,Thcco&TftWoyAXUS$|LFw.T!p+23/'!Mb,j(o65p<j&imQJ>2k)s'\\[znl.Pc;E,\"&(|z'vxVgNSmVvc;H^m<Cx=$\"y:jN)6|v%?M]\\$x{&{V2c:qy:;&:nKy|W<~%\"zpe]rl{*>I^T9'}{,!>".getBytes("ISO-8859-1");
    }

   AngelVisuals_ClassA1(AngelVisuals arg0) { // было: <init>
        super();
    }

  public class_2960 method3() { // было: c
        return AngelVisuals.id(Decryptor.method1945(XorDecoder.method1946("D¿h@#°ON]cKp¹VT}.vtCg&¡0jZÞH\u001dfdNh«IUv9\u0019", 80836039 ^ 550543317)));
    }

  public void method4(class_3300 arg0) { // было: a
        be.aa();
    }

  private static int cd(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int ce(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int cf(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}