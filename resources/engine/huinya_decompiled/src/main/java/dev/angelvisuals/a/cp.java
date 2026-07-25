// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.cP
package dev.angelvisuals.a;

import com.darkmagician6.eventapi.events.Event;
import lombok.Generated;
import net.minecraft.class_1309;
import net.minecraft.class_1799;

public class cP implements Event {

    // ---- поля ----
  private final class_1309 field112; // было: c
  private final class_1799 field113; // было: b
  private static final String xI = "// === DO NOT TOUCH ===";
  private static final String xJ = "// Joiner sees you";
  private static final String xK = "// you are reading machine-generated garbage";
  private static final String xL = "// every class watermarked, every string encrypted, every number xored";
  private static final String xM = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final int nZ = -1057773147;
  private static final int oa = -837805410;
  private static final int ob = -779937055;
  private static final byte[] dy;

    static {
        dy = ":%e()E:GN8Wsi6!Y`:g=3Di^rVa`zrvd\\2LaNep)/Io~3?tU?Z96@3H:y RuLPD}3:uZ*$ic_[?knn`\\.yeKbR[SlV3GiA|1:1brn4(SSlpr%7$lt+`DlE4\\+(LCuQ6/i\"Ya%RF#GB*k!M?K3[r*9xw&@bJ7IQ#\"N>W?F~RaND{xj@>wQ_f|[OU1]*G$1sa3e)HvlJ8P>%0}NY'[uB\\YTT\"5:Kfi/5qb,]DmSt0! 7a~+FNQ/n#LqB,GM8BO=0LA".getBytes("ISO-8859-1");
    }

    @Generated
  public cP(class_1309 arg0, class_1799 arg1) { // было: <init>
        super();
        field112 = arg0;
        field113 = arg1;
    }

    @Generated
  public class_1309 method286() { // было: a
        return field112;
    }

    @Generated
  public class_1799 method287() { // было: b
        return field113;
    }

    @Generated
  public boolean equals(Object arg0) {
        if (arg0 != this) {
            if (arg0 instanceof cP) {
                cP var2 = ((cP) arg0);
                if (var2.method288(this)) {
                    class_1309 var3 = method286();
                    class_1309 var4 = var2.method286();
                    if (var3 != null) {
                        if (var3.equals(var4)) {
                            class_1799 var5 = method287();
                            class_1799 var6 = var2.method287();
                            if (var5 != null) {
                                if (var5.equals(var6)) {
                                    return 819583441 ^ 819583440;
                                } else {
                                    return -626045543 ^ -626045543;
                                }
                            } else {
                                if (var6 == null) {
                                    return 819583441 ^ 819583440;
                                } else {
                                    return 1815978306 ^ 1815978306;
                                }
                            }
                        } else {
                            return 549230421 ^ 549230421;
                        }
                    } else {
                        if (var4 == null) {
                            class_1799 var5 = method287();
                            class_1799 var6 = var2.method287();
                            if (var5 != null) {
                                if (var5.equals(var6)) {
                                    return 819583441 ^ 819583440;
                                } else {
                                    return -626045543 ^ -626045543;
                                }
                            } else {
                                if (var6 == null) {
                                    return 819583441 ^ 819583440;
                                } else {
                                    return 1815978306 ^ 1815978306;
                                }
                            }
                        } else {
                            return 2128457449 ^ 2128457449;
                        }
                    }
                } else {
                    return -763695843 ^ -763695843;
                }
            } else {
                return -113149331 ^ -113149331;
            }
        } else {
            return 681638079 ^ 681638078;
        }
    }

    @Generated
  protected boolean method288(Object arg0) { // было: w
        return arg0 instanceof cP;
    }

    @Generated
  public int hashCode() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: ldc  #11 // -509260237
        //      2: ldc  #10 // -509260238
        //      4: ixor
        //      5: istore_1
        //      6: ldc  #8 // -647047501
        //      8: ldc  #7 // -647047502
        //     10: ixor
        //     11: istore_2
        //     12: aload_0
        //     13: invokevirtual  #44 // dev.angelvisuals.a.cP.a:()Lnet/minecraft/class_1309;
        //     16: astore_3
        //     17: iload_2
        //     18: ldc  #1 // -1476236195
        //     20: ldc  #2 // -1476236186
        //     22: ixor
        //     23: imul
        //     24: aload_3
        //     25: ifnonnull  36 (offset +11)
        //     28: ldc  #12 // -330415078
        //     30: ldc  #13 // -330415055
        //     32: ixor
        //     33: goto  40 (offset +7)
        //     36: aload_3
        //     37: invokevirtual  #49 // java.lang.Object.hashCode:()I
        //     40: iadd
        //     41: istore_2
        //     42: aload_0
        //     43: invokevirtual  #45 // dev.angelvisuals.a.cP.b:()Lnet/minecraft/class_1799;
        //     46: astore  4
        //     48: iload_2
        //     49: ldc  #17 // 233310080
        //     51: ldc  #18 // 233310139
        //     53: ixor
        //     54: imul
        //     55: aload  4
        //     57: ifnonnull  68 (offset +11)
        //     60: ldc  #15 // -116468951
        //     62: ldc  #14 // -116468990
        //     64: ixor
        //     65: goto  73 (offset +8)
        //     68: aload  4
        //     70: invokevirtual  #49 // java.lang.Object.hashCode:()I
        //     73: iadd
        //     74: istore_2
        //     75: iload_2
        //     76: ireturn
    }

    @Generated
  public String toString() {
        String var1 = String.valueOf(method286());
        return "EventPickupItem(entity=" + var1 + ", itemStack=" + String.valueOf(method287()) + ")";
    }

  private static int mr(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int ms(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int mt(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}