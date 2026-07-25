// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.dr
package dev.angelvisuals.a;

import com.darkmagician6.eventapi.events.Event;
import lombok.Generated;
import net.minecraft.class_1297;

public class dr implements Event {

    // ---- поля ----
  private class_1297 field107; // было: c
  private float dD;
  private static final String Dg = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final String Dh = "// class hierarchy hashing: ENABLED";
  private static final String Di = "// flow obfuscation: ENABLED";
  private static final String Dj = "// good luck with the next 9999 classes";
  private static final String Dk = "// you are reading machine-generated garbage";
  private static final int ri = -1138135746;
  private static final int rj = 1625489558;
  private static final int rk = 1002885662;
  private static final byte[] ey;

    static {
        ey = "j,$K2<zzg*`JF{nyh@=Ui9k$|'@1BN=Jq`H8!^j9c;T%))Uyam~4B}P$ssCgozqpE}Avx]{ZYSUH(]\\gZwyo:L71MT EJ4-gU.MKQV{+^U)]A<gfM3&\"5,XQbspY5z6,@Rjy3p|!~%4}GSOrb94;\\iN5ZgnXu9$ Y:n;N\"lB`Y;u'v$0WE<>E9:O-=OC(UT&nZ=;c-6Z<jry#4`Hcm7n1EZv('C,:fDV:!ZZ<j'xkJM`:eE!3DoI[-;I09pqEcEi".getBytes("ISO-8859-1");
    }

    @Generated
  public class_1297 method273() { // было: c
        return field107;
    }

    @Generated
  public float bo() {
        return dD;
    }

    @Generated
  public void method274(class_1297 arg0) { // было: b
        field107 = arg0;
    }

    @Generated
  public void ae(float arg0) {
        dD = arg0;
    }

    @Generated
  public boolean equals(Object arg0) {
        if (arg0 != this) {
            if (arg0 instanceof dr) {
                dr var2 = ((dr) arg0);
                if (var2.method275(this)) {
                    if (Float.compare(bo(), var2.bo()) == 0) {
                        class_1297 var3 = method273();
                        class_1297 var4 = var2.method273();
                        if (var3 != null) {
                            if (var3.equals(var4)) {
                                return -1948470151 ^ -1948470152;
                            } else {
                                return 73268673 ^ 73268673;
                            }
                        } else {
                            if (var4 == null) {
                                return -1948470151 ^ -1948470152;
                            } else {
                                return -1004506624 ^ -1004506624;
                            }
                        }
                    } else {
                        return 1982883508 ^ 1982883508;
                    }
                } else {
                    return -1604777672 ^ -1604777672;
                }
            } else {
                return -1952790390 ^ -1952790390;
            }
        } else {
            return 604701758 ^ 604701759;
        }
    }

    @Generated
  protected boolean method275(Object arg0) { // было: A
        return arg0 instanceof dr;
    }

    @Generated
  public int hashCode() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: ldc  #20 // 1781311476
        //      2: ldc  #21 // 1781311477
        //      4: ixor
        //      5: istore_1
        //      6: ldc  #8 // -316741319
        //      8: ldc  #7 // -316741320
        //     10: ixor
        //     11: istore_2
        //     12: iload_2
        //     13: ldc  #15 // 1162804880
        //     15: ldc  #16 // 1162804907
        //     17: ixor
        //     18: imul
        //     19: aload_0
        //     20: invokevirtual  #43 // dev.angelvisuals.a.dr.bo:()F
        //     23: invokestatic  #46 // java.lang.Float.floatToIntBits:(F)I
        //     26: iadd
        //     27: istore_2
        //     28: aload_0
        //     29: invokevirtual  #44 // dev.angelvisuals.a.dr.c:()Lnet/minecraft/class_1297;
        //     32: astore_3
        //     33: iload_2
        //     34: ldc  #17 // 1589936540
        //     36: ldc  #18 // 1589936551
        //     38: ixor
        //     39: imul
        //     40: aload_3
        //     41: ifnonnull  52 (offset +11)
        //     44: ldc  #10 // -60348117
        //     46: ldc  #9 // -60348160
        //     48: ixor
        //     49: goto  56 (offset +7)
        //     52: aload_3
        //     53: invokevirtual  #49 // java.lang.Object.hashCode:()I
        //     56: iadd
        //     57: istore_2
        //     58: iload_2
        //     59: ireturn
    }

    @Generated
  public String toString() {
        String var1 = String.valueOf(method273());
        return "EventEntityHitBox(entity=" + var1 + ", size=" + bo() + ")";
    }

    @Generated
  public dr(class_1297 arg0, float arg1) { // было: <init>
        super();
        field107 = arg0;
        dD = arg1;
    }

  private static int ps(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int pt(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int pu(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}