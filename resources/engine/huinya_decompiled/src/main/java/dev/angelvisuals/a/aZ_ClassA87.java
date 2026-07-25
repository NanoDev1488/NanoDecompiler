// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.aZ.a
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.a.ClassA2;
import dev.angelvisuals.a.aH;
import dev.angelvisuals.a.aZ;
import java.util.List;
import java.util.Objects;
import lombok.Generated;

public class aZ_ClassA87 {

    // ---- поля ----
  private final aZ field333; // было: e
  private final String vF;
  private final String vG;
  private final ClassA2 field334; // было: A
  private static final String vH = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final String vI = "// this jar protected by JoinerObfuscator";
  private static final String vJ = "Protected by t.me/JoinerClient";
  private static final String vK = "Protected by t.me/JoinerClient";
  private static final String vL = "// flow obfuscation: ENABLED";
  private static final int mO = 1777513343;
  private static final int mP = 549510420;
  private static final int mQ = -1916700897;
  private static final byte[] df;

    static {
        df = "GgBlFWfHijGonBS=d/1J|)Vz)E~Hvt XB-@H#~I4g.4\"_sbHT:cI^~2qMj/@<3, '6.ob^;0ztCR5qJ$IA:.b!Yhsjfb4jJ)#Y!Q PBf_.w(.r)X)/Wt$HU:Qx[qa\\snxVYNNN@]($\"]2XEnV#D9%BNgO3^ci;pxEd6tE[L]>)][;:I$Hh|,k;-E`:@Ae1zsoJYwyMwzSFO(;)VJbv[N7^0BJgX*oX@^x<smw L)/Ru)z)L',eu8)FrilPam_`,q".getBytes("ISO-8859-1");
    }

  public aZ_ClassA87(aZ arg0, String arg1) { // было: <init>
        super();
        field334 = new ClassA2(6844645855816708246L ^ 6844645855816708204L, aH.field21);
        field333 = arg0;
        vF = arg1;
        vG = Decryptor.method1945(XorDecoder.method1946("w>s®'|®ª\u0002\u0010¼¼\u001et¯\u001b\"ÄO", 1752957994 ^ 444976238));
        if (arg0.field335.isEmpty()) {
            method689();
        }
        arg0.field335.add(this);
    }

  public aZ_ClassA87(aZ arg0, String arg1, String arg2) { // было: <init>
        super();
        field334 = new ClassA2(6902819241238109149L ^ 6902819241238108967L, aH.field21);
        field333 = arg0;
        vF = arg1;
        vG = arg2;
        if (arg0.field335.isEmpty()) {
            method689();
        }
        arg0.field335.add(this);
    }

  public aZ_ClassA87 method689() { // было: c
        field333.method703(this);
        return this;
    }

  public boolean ab() {
        return field333.method702() != this ? -310475500 ^ -310475500 : 8731984 ^ 8731985;
    }

  public String toString() {
        return vF;
    }

  public boolean equals(Object arg0) {
        if (arg0 != this) {
            if (arg0 == null) {
                return 2133263153 ^ 2133263153;
            } else {
                if (arg0.getClass() != getClass()) {
                    return 2133263153 ^ 2133263153;
                } else {
                    aZ_ClassA87 var2 = ((aZ_ClassA87) arg0);
                    return !Objects.equals(field333, var2.field333) ? 665963890 ^ 665963890 : !Objects.equals(vF, var2.vF) ? 665963890 ^ 665963890 : !Objects.equals(vG, var2.vG) ? 665963890 ^ 665963890 : -502674931 ^ -502674932;
                }
            }
        } else {
            return 1371942214 ^ 1371942215;
        }
    }

  public int hashCode() {
        Object[] __obj1 = new Object[428408511 ^ 428408508];
        __obj1[-854738192 ^ -854738192] = field333;
        __obj1[476560946 ^ 476560947] = vF;
        __obj1[-1076434243 ^ -1076434241] = vG;
        return Objects.hash(__obj1);
    }

    @Generated
  public aZ method690() { // было: a
        return field333;
    }

    @Generated
  public String method691() { // было: W
        return vF;
    }

    @Generated
  public String method692() { // было: X
        return vG;
    }

    @Generated
  public ClassA2 method693() { // было: h
        return field334;
    }

  private static int lm(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int ln(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int lo(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}