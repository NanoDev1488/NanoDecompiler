// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.I.a
package dev.angelvisuals.a;

import dev.angelvisuals.a.ClassA2;
import dev.angelvisuals.a.ClassA90;
import dev.angelvisuals.a.aH;
import java.util.List;
import lombok.Generated;

public class ClassA88_ClassA89 {

    // ---- поля ----
  private boolean aj;
  private final String ye;
  private final ClassA2 field338; // было: K
  private static final String yf = "// === DO NOT TOUCH ===";
  private static final String yg = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String yh = "// every class watermarked, every string encrypted, every number xored";
  private static final String yi = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final String yj = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final int om = 2003196980;
  private static final int on = 874624803;
  private static final int oo = -1839373831;
  private static final byte[] dC;

    static {
        dC = "tMhy_tt y,sd6?,8Zkp8ORi:?;zF1vSq^\\T_82ym!P;dphSM|1|('$X|L]FOrdAz6@<=+4jz?6-XTUbQ8}|6/6q40[-78&cJCeXh!-G p#A|i\\;qIce{YAdK;Rzj w_mU+RZ&[u2q86D]VygI`-2~zCHvt&O]*@mv)XmCpn5h#kPX>@,0~wP(Ha+u#E-q(ME*I1Q[u}AGPex-)pb#ar^)!nca\"wFM %am7 DTqc6\"r0R|*Am7[t<Ux)TZ]Oe!axn".getBytes("ISO-8859-1");
    }

  public ClassA88_ClassA89(String arg0, boolean arg1) { // было: <init>
        super();
        field338 = new ClassA2(-7968640331076335788L ^ -7968640331076335698L, aH.field21);
        aj = arg1;
        ye = arg0;
    }

  public ClassA88_ClassA89(ClassA90 arg0, String arg1, boolean arg2) { // было: <init>
        super();
        field338 = new ClassA2(-7107273171657958862L ^ -7107273171657958712L, aH.field21);
        aj = arg2;
        ye = arg1;
        arg0.field339.add(this);
    }

  public static ClassA88_ClassA89 method708(String arg0, boolean arg1) { // было: a
        return new ClassA88_ClassA89(arg0, arg1);
    }

  public static ClassA88_ClassA89 method709(String arg0) { // было: c
        return new ClassA88_ClassA89(arg0, -1464671706 ^ -1464671705);
    }

  public void aE() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: aload_0
        //      2: getfield  #33 // dev.angelvisuals.a.I$a.aj:Z
        //      5: ifne  16 (offset +11)
        //      8: ldc  #7 // 1104041921
        //     10: ldc  #6 // 1104041920
        //     12: ixor
        //     13: goto  21 (offset +8)
        //     16: ldc  #5 // 962135468
        //     18: ldc  #5 // 962135468
        //     20: ixor
        //     21: putfield  #33 // dev.angelvisuals.a.I$a.aj:Z
        //     24: return
    }

    @Generated
  public boolean aj() {
        return aj;
    }

    @Generated
  public String ab() {
        return ye;
    }

    @Generated
  public ClassA2 method710() { // было: k
        return field338;
    }

    @Generated
  public void method711(boolean arg0) { // было: r
        aj = arg0;
    }

  private static int mD(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int mE(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int mF(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}