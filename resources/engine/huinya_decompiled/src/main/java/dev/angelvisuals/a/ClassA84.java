// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.q
package dev.angelvisuals.a;

import com.google.gson.JsonObject;
import dev.angelvisuals.a.ClassA2;
import dev.angelvisuals.a.aH;
import java.util.function.Supplier;
import lombok.Generated;

public abstract class ClassA84 {

    // ---- поля ----
  protected final String aD;
  private final ClassA2 field322; // было: a
  protected Supplier field323; // было: a
  private static final String aE = "// reverse-engineering this jar is a waste of time, friend";
  private static final String aF = "// flow obfuscation: ENABLED";
  private static final String aG = "// class hierarchy hashing: ENABLED";
  private static final String aH = "// number obfuscation: ENABLED (XOR masking)";
  private static final String aI = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final int ad = 1822061460;
  private static final int ae = 330079186;
  private static final int af = 839554622;
  private static final byte[] field324; // было: q

    static {
        field324 = ",z?#9oa z;Iih>XSn:|kM#+Vl(_k/X5?9J)uBKp|jg91@v;b|': 29|U\"k`tLi\"zz?)eVDwl7CwM4wjVl\\Rg.Yl-s=yOh(-'cMO%7vjo3$`{~ZVI7-AwQm$2Rj,E{N_7_'-w&<Eiw}%$D0#\"oIyQzJ@P++_%/%&YR#6V`A~uay!{lYhMk]NE8^?E+46ohX7><A2S!Dg=bTbU]Sp\\F^)&8s !w<i@fm%7N1]Py,>uR2a|uwz?b8$'r|-0X-f'$<{)".getBytes("ISO-8859-1");
    }

  public ClassA84(String arg0) { // было: <init>
        super();
        field322 = new ClassA2(-3936303838056571991L ^ -3936303838056572077L, aH.field21);
        aD = arg0;
        method637(() -> method638());
    }

  public abstract void method631(JsonObject arg0); // было: a

  public abstract void method632(JsonObject arg0); // было: b

  public boolean method633() { // было: m
        return (((Boolean) field323.get())).booleanValue();
    }

    @Generated
  public String method634() { // было: e
        return aD;
    }

    @Generated
  public ClassA2 method635() { // было: a
        return field322;
    }

    @Generated
  public Supplier method636() { // было: a
        return field323;
    }

    @Generated
  public void method637(Supplier arg0) { // было: a
        field323 = arg0;
    }

  private static Boolean method638() { // было: a
        return Boolean.valueOf(73509258 ^ 73509259);
    }

  private static int method639(int arg0, int arg1) { // было: W
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int method640(int arg0, int arg1) { // было: X
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int method641(int arg0, int arg1) { // было: Y
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}