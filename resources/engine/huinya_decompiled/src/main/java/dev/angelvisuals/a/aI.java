// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.ai
package dev.angelvisuals.a;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.a.ClassA84;
import dev.angelvisuals.a.cd;
import java.util.function.Supplier;
import lombok.Generated;

public class ai extends ClassA84 {

    // ---- поля ----
  private String fC;
  private int di;
  private static final String fD = "// stop. seriously. go play minecraft instead";
  private static final String fE = "// you are reading machine-generated garbage";
  private static final String fF = "// number obfuscation: ENABLED (XOR masking)";
  private static final String fG = "Protected by t.me/JoinerClient";
  private static final String fH = "// this jar protected by JoinerObfuscator";
  private static final int dj = 1236026959;
  private static final int dk = -76310908;
  private static final int dl = 787524879;
  private static final byte[] am;

    static {
        am = "UQY/Pu#r;~@dd+VB^<(f7,c39d-)zUg/%r[%$89oL~3@LR$HbU|F(R]3/:i%MI7kHs&~2b+XOqHfN'v{ouzxjf,40`x(8CRJ]iqHyi9?`T4|peF W{raU=,%};33'9J\\ga~Fuf,7Q#^)D^Cju|f(7B~Qw)FxO=*+HZpb|V 9_PL<2!9eEo9'1{kF\"_vFV8=mM x4E?Yw7wTNLM^5Y0zR38udV}l{=k_D/ItY1qL15c`%T^98UO?t]03I=|f0,2um".getBytes("ISO-8859-1");
    }

  public void method684(int arg0) { // было: d
        di = arg0;
        fC = cd.method1469(arg0);
    }

  public ai(String arg0, Supplier arg1) { // было: <init>
        super(arg0);
        a(arg1);
        di = -1339022133 ^ 1339022132;
        fC = cd.method1469(di);
    }

  public ai(String arg0, int arg1, Supplier arg2) { // было: <init>
        super(arg0);
        a(arg2);
        di = arg1;
        fC = cd.method1469(arg1);
    }

  public ai(String arg0, int arg1) { // было: <init>
        super(arg0);
        di = arg1;
        fC = cd.method1469(arg1);
    }

  public ai(String arg0) { // было: <init>
        super(arg0);
        di = 1467915116 ^ -1467915117;
        fC = Decryptor.method1945(XorDecoder.method1946(">zÔ\u000faÍ\u000f^èé\u001dHô [ñÛ(0¥", -621770361 ^ 1123934266));
    }

  public void method685(JsonObject arg0) { // было: a
        arg0.addProperty(String.valueOf(aD), Integer.valueOf(method688()));
    }

  public void method686(JsonObject arg0) { // было: b
        method684(arg0.get(String.valueOf(aD)).getAsInt());
    }

    @Generated
  public String method687() { // было: n
        return fC;
    }

    @Generated
  public int method688() { // было: o
        return di;
    }

  private static int cK(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int cL(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int cM(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}