// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.cA
package dev.angelvisuals.a;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.angelvisuals.a.ClassA84;
import java.util.function.Supplier;
import lombok.Generated;

public class cA extends ClassA84 {

    // ---- поля ----
  private String vf;
  private final int my;
  private static final String vg = "// flow obfuscation: ENABLED";
  private static final String vh = "// === DO NOT TOUCH ===";
  private static final String vi = "// === DO NOT TOUCH ===";
  private static final String vj = "// this jar protected by JoinerObfuscator";
  private static final String vk = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final int mz = 737029549;
  private static final int mA = -1971164530;
  private static final int mB = 1213966638;
  private static final byte[] da;

    static {
        da = "g`&WW]d\\JqH5JEu%RVeY[EVCVkwt.[)dm>RNslHtiMHj7Y)fVWp2<3hQ t!@C(r]y5yG)jNVAZC%&=<,7O;<\"d`xWYT)M^B2hhO|[=f]$o&6Tlpz%-|^`.83>+s*REAMXtZNBTQ`dflY$G[_1GUhR8B^I*V#(ndlI!mK\"`_^(c=x-,Y61yv7\\\\QoSxA$ty}T>,$IB`xyo#QkS[ZfKdis32d:ElL-<^3f oy/5Rd*>ULM{@szX;d%%^SMAO#b>nu#".getBytes("ISO-8859-1");
    }

  public cA(String arg0, String arg1) { // было: <init>
        this(arg0, arg1, 871157746 ^ 871157714, () -> method737());
    }

  public cA(String arg0, String arg1, Supplier arg2) { // было: <init>
        this(arg0, arg1, -2033752318 ^ -2033752286, arg2);
    }

  public cA(String arg0, String arg1, int arg2, Supplier arg3) { // было: <init>
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: aload_1
        //      2: invokespecial  #49 // dev.angelvisuals.a.q.<init>:(Ljava/lang/String;)V
        //      5: aload_0
        //      6: aload_2
        //      7: ifnonnull  26 (offset +19)
        //     10: ldc  #15 // ' ÈY\x1c$ùB\x05+ù} Gëk<#Öx9uÞ\x13m'
        //     12: ldc  #6 // 763208472
        //     14: ldc  #14 // 2102606859
        //     16: ixor
        //     17: invokestatic  #44 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //     20: invokestatic  #43 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //     23: goto  27 (offset +4)
        //     26: aload_2
        //     27: putfield  #37 // dev.angelvisuals.a.cA.vf:Ljava/lang/String;
        //     30: aload_0
        //     31: iload_3
        //     32: putfield  #36 // dev.angelvisuals.a.cA.my:I
        //     35: aload_0
        //     36: aload  4
        //     38: invokevirtual  #46 // dev.angelvisuals.a.cA.a:(Ljava/util/function/Supplier;)V
        //     41: return
    }

  public void method733(String arg0) { // было: i
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_1
        //      1: ifnonnull  22 (offset +21)
        //      4: aload_0
        //      5: ldc  #22 // '|\x19Â7x(Ù.w(æ\x0b\x1b:ð\x17\x7f\x07ã\x12)\x0f\x88F'
        //      7: ldc  #10 // 1589904882
        //      9: ldc  #4 // 628181949
        //     11: ixor
        //     12: invokestatic  #44 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //     15: invokestatic  #43 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //     18: putfield  #37 // dev.angelvisuals.a.cA.vf:Ljava/lang/String;
        //     21: return
        //     22: aload_0
        //     23: aload_1
        //     24: invokevirtual  #52 // java.lang.String.length:()I
        //     27: aload_0
        //     28: getfield  #36 // dev.angelvisuals.a.cA.my:I
        //     31: if_icmple  50 (offset +19)
        //     34: aload_1
        //     35: ldc  #13 // 1930327978
        //     37: ldc  #13 // 1930327978
        //     39: ixor
        //     40: aload_0
        //     41: getfield  #36 // dev.angelvisuals.a.cA.my:I
        //     44: invokevirtual  #53 // java.lang.String.substring:(II)Ljava/lang/String;
        //     47: goto  51 (offset +4)
        //     50: aload_1
        //     51: putfield  #37 // dev.angelvisuals.a.cA.vf:Ljava/lang/String;
        //     54: return
    }

  public void method734(JsonObject arg0) { // было: a
        arg0.addProperty(aD, vf);
    }

  public void method735(JsonObject arg0) { // было: b
        if (arg0.has(aD)) {
            if (!arg0.get(aD).isJsonNull()) {
                method733(arg0.get(aD).getAsString());
            }
        }
    }

    @Generated
  public String method736() { // было: V
        return vf;
    }

    @Generated
  public int ad() {
        return my;
    }

  private static Boolean method737() { // было: m
        return Boolean.valueOf(1820239534 ^ 1820239535);
    }

  private static int kX(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int kY(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int kZ(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}