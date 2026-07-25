// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.aZ
package dev.angelvisuals.a;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.a.ClassA2;
import dev.angelvisuals.a.ClassA84;
import dev.angelvisuals.a.aH;
import dev.angelvisuals.a.aZ_ClassA87;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;
import lombok.Generated;

public class aZ extends ClassA84 {

    // ---- поля ----
  private final List field335; // было: n
  private aZ_ClassA87 field336; // было: a
  private final ClassA2 field337; // было: k
  private static final String kJ = "Protected by t.me/JoinerClient";
  private static final String kK = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final String kL = "// good luck with the next 9999 classes";
  private static final String kM = "// nice try. closed source for a reason.";
  private static final String kN = "// class hierarchy hashing: ENABLED";
  private static final int gk = -6552154;
  private static final int gl = -678607940;
  private static final int gm = -1166329134;
  private static final byte[] bj;

    static {
        bj = "+qS+WN;D*9j4CHeK.R^dXzbCA,1<<4a!\"z.7!7pEA9CW,Hxq2Q;p5'%-\\4HsN`.k~!9p\"a.lD6cF}TE;I~cj),qXf,jgW39ORQDrWQ:CY(j,4?ajQ{Fo0C12K@B<[#4_-WN68@@1xR%0JmqC,!tc9~f{+,A&g< ,Sw6Dg%Dm?5\"iJ2`0Cp5s+_)Age`&(Gv*o'tv3ZPe`Q7bUQ}v9S#=]%IQR4n.=)Ak%6#O9rK5RYq6kI1fgM$U\\2`JMzO\"*T#L".getBytes("ISO-8859-1");
    }

  public aZ(String arg0, String[] arg1) { // было: <init>
        super(arg0);
        field335 = new ArrayList();
        field337 = new ClassA2(8104691664983550834L ^ 8104691664983550906L, aH.field21);
        String[] var3 = arg1;
        int var4 = arg1.length;
        int var5 = -1256620319 ^ -1256620319;
        while (var5 < var4) {
            Object var6 = var3[var5];
            if (!var6.isEmpty()) {
                new aZ_ClassA87(this, ((String) var6));
            }
            ++var5;
            continue;
        }
        if (!field335.isEmpty()) {
            field336 = ((aZ_ClassA87) field335.getFirst());
        }
    }

  public aZ(String arg0, Supplier arg1, String[] arg2) { // было: <init>
        super(arg0);
        field335 = new ArrayList();
        field337 = new ClassA2(-6580516034131244015L ^ -6580516034131243815L, aH.field21);
        String[] var4 = arg2;
        int var5 = arg2.length;
        int var6 = -1006315209 ^ -1006315209;
        while (var6 < var5) {
            Object var7 = var4[var6];
            if (!var7.isEmpty()) {
                new aZ_ClassA87(this, ((String) var7));
            }
            ++var6;
            continue;
        }
        if (!field335.isEmpty()) {
            field336 = ((aZ_ClassA87) field335.getFirst());
        }
        a(arg1);
    }

  public void method694(String arg0) { // было: b
        field335.stream().filter(lp0 -> method707(arg0, ((aZ_ClassA87) lp0))).findFirst().ifPresent(lp0 -> method706(((aZ_ClassA87) lp0)));
    }

  public String method695() { // было: D
        return field336 == null ? Decryptor.method1945(XorDecoder.method1946("ËW~Ï¬LgÀ¬sB¬¾e^Èv[\u001d\u000f", -904284930 ^ -130449914)) : field336.method691();
    }

  public boolean method696(String arg0) { // было: e
        return field336 == null ? -1261096725 ^ -1261096725 : !field336.method691().equals(arg0) ? -1261096725 ^ -1261096725 : 1007745512 ^ 1007745513;
    }

  public boolean method697(aZ_ClassA87 arg0) { // было: a
        return field336 != arg0 ? 859363325 ^ 859363325 : -1445733500 ^ -1445733499;
    }

  public aZ_ClassA87 method698() { // было: a
        List var1 = field335.stream().filter(lp0 -> (((aZ_ClassA87) lp0)).ab()).toList();
        return var1.isEmpty() ? null : ((aZ_ClassA87) var1.get(new Random().nextInt(var1.size())));
    }

  public void method699(JsonObject arg0) { // было: a
        arg0.addProperty(String.valueOf(aD), method695());
    }

  public void method700(JsonObject arg0) { // было: b
        method694(arg0.get(String.valueOf(aD)).getAsString());
    }

    @Generated
  public List method701() { // было: j
        return field335;
    }

    @Generated
  public aZ_ClassA87 method702() { // было: b
        return field336;
    }

    @Generated
  public void method703(aZ_ClassA87 arg0) { // было: a
        field336 = arg0;
    }

  public void method704(boolean arg0) { // было: h
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #52 // dev.angelvisuals.a.aZ.k:Ldev/angelvisuals/a/k;
        //      4: iload_1
        //      5: ifeq  12 (offset +7)
        //      8: fconst_1
        //      9: goto  13 (offset +4)
        //     12: fconst_0
        //     13: invokevirtual  #68 // dev.angelvisuals.a.k.a:(F)F
        //     16: pop
        //     17: return
    }

    @Generated
  public ClassA2 method705() { // было: d
        return field337;
    }

  private void method706(aZ_ClassA87 arg0) { // было: b
        field336 = arg0;
    }

  private static boolean method707(String arg0, aZ_ClassA87 arg1) { // было: a
        return arg1.method691().equals(arg0);
    }

  private static int fB(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int fC(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int fD(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}