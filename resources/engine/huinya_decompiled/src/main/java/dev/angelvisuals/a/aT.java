// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.at
package dev.angelvisuals.a;

import dev.angelvisuals.a.ClassA146;
import dev.angelvisuals.a.at_ClassA143;
import it.unimi.dsi.fastutil.objects.Object2DoubleArrayMap;
import it.unimi.dsi.fastutil.objects.Object2DoubleMap;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.class_10185;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1690;
import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2338.class_2339;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_2399;
import net.minecraft.class_243;
import net.minecraft.class_2533;
import net.minecraft.class_2680;
import net.minecraft.class_3481;
import net.minecraft.class_3486;
import net.minecraft.class_3532;
import net.minecraft.class_3610;
import net.minecraft.class_5131;
import net.minecraft.class_5635;
import net.minecraft.class_6862;
import net.minecraft.class_6880;
import net.minecraft.class_746;

public class at implements ClassA146 {

    // ---- поля ----
  public final class_1657 field737; // было: b
  public final at_ClassA143 field738; // было: a
  public class_243 field739; // было: e
  public class_243 field740; // было: f
  public class_238 field741; // было: a
  public float av;
  public float aw;
  public boolean field742; // было: s
  public float ax;
  public int dT;
  public boolean field743; // было: t
  public boolean field744; // было: u
  public boolean field745; // было: v
  public boolean field746; // было: w
  public boolean field747; // было: x
  public boolean field748; // было: y
  public boolean field749; // было: z
  public boolean field750; // было: A
  private final Object2DoubleMap field751; // было: a
  private final HashSet field752; // было: a
  private int dU;
  private boolean field753; // было: B
  private static final double field754 = 0.5; // было: m
  private static final String gR = "// class hierarchy hashing: ENABLED";
  private static final String gS = "// flow obfuscation: ENABLED";
  private static final String gT = "// nice try. closed source for a reason.";
  private static final String gU = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final String gV = "// stop. seriously. go play minecraft instead";
  private static final int dV = -1380677344;
  private static final int dW = 1029704663;
  private static final int dX = 1691334516;
  private static final byte[] ay;

    static {
        ay = "UC8zi\\G3eu+Gnz6_d# &>nZTqn/YX64d?M0ph%v:fY,_fwu/\"S\\O4a@]pkmA_nuIT@w`S6N46KP#unhf'g}EZ2RgoUwsQ()}ct4QNX\"[3/JDx(vsV\\N4*t9>,~s;7Kl0{,pP  `@ ~RqgNZTTsj#jMu?H2nRQEH(maBhA./hm;Wr9;AW%.xEe}F|%09h{Z<feTK+?e'YwXb31<cgH|>a$bXd6.4i/({r-#R_pbu4/Bj3Ennti.4l|ra>_%'.OLNh".getBytes("ISO-8859-1");
    }

  public at(class_1657 arg0, at_ClassA143 arg1, class_243 arg2, class_243 arg3, class_238 arg4, float arg5, float arg6, boolean arg7, float arg8, int arg9, boolean arg10, boolean arg11, boolean arg12, boolean arg13, boolean arg14, boolean arg15, boolean arg16, boolean arg17, Object2DoubleMap arg18, HashSet arg19) { // было: <init>
        super();
        dU = -1023287686 ^ -1023287686;
        field753 = -1209055701 ^ -1209055701;
        field737 = arg0;
        field738 = arg1;
        field739 = arg2;
        field740 = arg3;
        field741 = arg4;
        av = arg5;
        aw = arg6;
        field742 = arg7;
        ax = arg8;
        dT = arg9;
        field743 = arg10;
        field744 = arg11;
        field745 = arg12;
        field746 = arg13;
        field747 = arg14;
        field748 = arg15;
        field749 = arg16;
        field750 = arg17;
        field751 = arg18;
        field752 = arg19;
    }

  public static at method1289(int arg0) { // было: a
        at var1 = method1291(at_ClassA143.method1287(mc.field_1724.field_3913.field_54155));
        int var2 = -1769852046 ^ -1769852046;
        while (var2 < arg0) {
            var1.method1294();
            ++var2;
            continue;
        }
        return var1;
    }

  public static at method1290(class_1657 arg0, int arg1) { // было: a
        at var2 = method1292(arg0, at_ClassA143.method1288(arg0));
        int var3 = 1062636227 ^ 1062636227;
        while (var3 < arg1) {
            var2.method1294();
            ++var3;
            continue;
        }
        return var2;
    }

  public static at method1291(at_ClassA143 arg0) { // было: a
        class_746 var1 = mc.field_1724;
        return new at(var1, arg0, var1.method_19538(), var1.method_18798(), var1.method_5829(), var1.method_36454(), var1.method_36455(), var1.method_5624(), var1.field_6017, var1.field_6228, var1.field_6282, var1.method_6128(), var1.method_24828(), var1.field_5976, var1.field_5992, var1.method_5799(), var1.method_5681(), var1.method_5869(), new Object2DoubleArrayMap(var1.field_5964), new HashSet(var1.field_25599));
    }

  public static at method1292(class_1657 arg0, at_ClassA143 arg1) { // было: a
        return new at(arg0, arg1, arg0.method_19538(), arg0.method_19538().method_1020(new class_243(arg0.field_6014, arg0.field_6036, arg0.field_5969)), arg0.method_5829(), arg0.method_36454(), arg0.method_36455(), arg0.method_5624(), arg0.field_6017, arg0.field_6228, arg0.field_6282, arg0.method_6128(), arg0.method_24828(), arg0.field_5976, arg0.field_5992, arg0.method_5799(), arg0.method_5681(), arg0.method_5869(), new Object2DoubleArrayMap(arg0.field_5964), new HashSet(arg0.field_25599));
    }

  public class_243 method1293() { // было: b
        return field737.method_19538();
    }

  public void method1294() { // было: B
        double __stk1;
        int __stk2;
        dU = dU + (-2025664037 ^ -2025664038);
        field753 = -1402414198 ^ -1402414198;
        if (field739.field_1351 > -70.0) {
            field738.ac();
            method1322();
            method1324();
            method1323();
            if (dT > 0) {
                dT = dT - (1975766944 ^ 1975766945);
            }
            field743 = field738.field735.comp_3163();
            double var1 = field740.field_1352;
            double var3 = field740.field_1351;
            double var5 = field740.field_1350;
            if (Math.abs(field740.field_1352) < 0.003) {
                var1 = 0.0;
            }
            if (Math.abs(field740.field_1351) < 0.003) {
                var3 = 0.0;
            }
            if (Math.abs(field740.field_1350) < 0.003) {
                var5 = 0.0;
            }
            if (field745) {
                field744 = 451536361 ^ 451536361;
            }
            field740 = new class_243(var1, var3, var5);
            double var7;
            int var9;
            if (field743) {
                __stk1 = !method1321() ? method1327(class_3486.field_15517) : method1327(class_3486.field_15518);
                var7 = __stk1;
                __stk2 = !method1320() ? -1083271245 ^ -1083271245 : var7 <= 0.0 ? -1083271245 ^ -1083271245 : 200995260 ^ 200995261;
                var9 = __stk2;
                double var10 = method1319();
                if (var9 == 0) {
                    if (!method1321()) {
                        if (field745) {
                            if (dT == 0) {
                                method1304();
                                dT = 929995637 ^ 929995647;
                            }
                        } else {
                            if (var9 != 0) {
                                if (var7 <= var10) {
                                    if (dT == 0) {
                                        method1304();
                                        dT = 929995637 ^ 929995647;
                                    }
                                }
                            }
                        }
                    } else {
                        if (!field745) {
                            method1317(class_3486.field_15518);
                        } else {
                            if (var7 <= var10) {
                                if (field745) {
                                    if (dT == 0) {
                                        method1304();
                                        dT = 929995637 ^ 929995647;
                                    }
                                } else {
                                    if (var9 != 0) {
                                        if (var7 <= var10) {
                                            if (dT == 0) {
                                                method1304();
                                                dT = 929995637 ^ 929995647;
                                            }
                                        }
                                    }
                                }
                            } else {
                                method1317(class_3486.field_15518);
                            }
                        }
                    }
                } else {
                    if (!field745) {
                        method1317(class_3486.field_15517);
                    } else {
                        if (var7 <= var10) {
                            if (!method1321()) {
                                if (field745) {
                                    if (dT == 0) {
                                        method1304();
                                        dT = 929995637 ^ 929995647;
                                    }
                                } else {
                                    if (var9 != 0) {
                                        if (var7 <= var10) {
                                            if (dT == 0) {
                                                method1304();
                                                dT = 929995637 ^ 929995647;
                                            }
                                        }
                                    }
                                }
                            } else {
                                if (!field745) {
                                    method1317(class_3486.field_15518);
                                } else {
                                    if (var7 <= var10) {
                                        if (field745) {
                                            if (dT == 0) {
                                                method1304();
                                                dT = 929995637 ^ 929995647;
                                            }
                                        } else {
                                            if (var9 != 0) {
                                                if (var7 <= var10) {
                                                    if (dT == 0) {
                                                        method1304();
                                                        dT = 929995637 ^ 929995647;
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        method1317(class_3486.field_15518);
                                    }
                                }
                            }
                        } else {
                            method1317(class_3486.field_15517);
                        }
                    }
                }
            }
            float var7 = field738.bx * 0.9800000190734863f;
            float var8 = field738.bw * 0.9800000190734863f;
            float var9 = 0.0f;
            if (method1332(class_1294.field_5906)) {
                method1303();
            } else {
                if (method1332(class_1294.field_5902)) {
                    method1303();
                }
            }
            method1295(new class_243(((double) var7), ((double) var9), ((double) var8)));
        }
    }

  private void method1295(class_243 arg0) { // было: a
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #263 // dev.angelvisuals.a.at.z:Z
        //      4: ifeq  154 (offset +150)
        //      7: aload_0
        //      8: getfield  #250 // dev.angelvisuals.a.at.b:Lnet/minecraft/class_1657;
        //     11: invokevirtual  #400 // net.minecraft.class_1657.method_5765:()Z
        //     14: ifne  154 (offset +140)
        //     17: aload_0
        //     18: invokevirtual  #349 // dev.angelvisuals.a.at.c:()Lnet/minecraft/class_243;
        //     21: getfield  #295 // net.minecraft.class_243.field_1351:D
        //     24: dstore_2
        //     25: dload_2
        //     26: ldc2_w  #172 // -0.2d
        //     29: dcmpg
        //     30: ifge  39 (offset +9)
        //     33: ldc2_w  #200 // 0.085d
        //     36: goto  42 (offset +6)
        //     39: ldc2_w  #196 // 0.06d
        //     42: dstore  4
        //     44: new  #145 // net.minecraft.class_2338
        //     47: dup
        //     48: aload_0
        //     49: getfield  #253 // dev.angelvisuals.a.at.e:Lnet/minecraft/class_243;
        //     52: getfield  #296 // net.minecraft.class_243.field_1352:D
        //     55: invokestatic  #450 // net.minecraft.class_3532.method_15357:(D)I
        //     58: aload_0
        //     59: getfield  #253 // dev.angelvisuals.a.at.e:Lnet/minecraft/class_243;
        //     62: getfield  #295 // net.minecraft.class_243.field_1351:D
        //     65: dconst_1
        //     66: dadd
        //     67: ldc2_w  #202 // 0.1d
        //     70: dsub
        //     71: invokestatic  #450 // net.minecraft.class_3532.method_15357:(D)I
        //     74: aload_0
        //     75: getfield  #253 // dev.angelvisuals.a.at.e:Lnet/minecraft/class_243;
        //     78: getfield  #294 // net.minecraft.class_243.field_1350:D
        //     81: invokestatic  #450 // net.minecraft.class_3532.method_15357:(D)I
        //     84: invokespecial  #420 // net.minecraft.class_2338.<init>:(III)V
        //     87: astore  6
        //     89: dload_2
        //     90: dconst_0
        //     91: dcmpg
        //     92: ifle  129 (offset +37)
        //     95: aload_0
        //     96: getfield  #242 // dev.angelvisuals.a.at.a:Ldev/angelvisuals/a/at$a;
        //     99: getfield  #265 // dev.angelvisuals.a.at$a.a:Lnet/minecraft/class_10185;
        //    102: invokevirtual  #378 // net.minecraft.class_10185.comp_3163:()Z
        //    105: ifne  129 (offset +24)
        //    108: aload_0
        //    109: getfield  #250 // dev.angelvisuals.a.at.b:Lnet/minecraft/class_1657;
        //    112: invokevirtual  #394 // net.minecraft.class_1657.method_37908:()Lnet/minecraft/class_1937;
        //    115: aload  6
        //    117: invokevirtual  #415 // net.minecraft.class_1937.method_8320:(Lnet/minecraft/class_2338;)Lnet/minecraft/class_2680;
        //    120: invokevirtual  #447 // net.minecraft.class_2680.method_26227:()Lnet/minecraft/class_3610;
        //    123: invokevirtual  #458 // net.minecraft.class_3610.method_15769:()Z
        //    126: ifne  154 (offset +28)
        //    129: aload_0
        //    130: aload_0
        //    131: getfield  #254 // dev.angelvisuals.a.at.f:Lnet/minecraft/class_243;
        //    134: dconst_0
        //    135: dload_2
        //    136: aload_0
        //    137: getfield  #254 // dev.angelvisuals.a.at.f:Lnet/minecraft/class_243;
        //    140: getfield  #295 // net.minecraft.class_243.field_1351:D
        //    143: dsub
        //    144: dload  4
        //    146: dmul
        //    147: dconst_0
        //    148: invokevirtual  #439 // net.minecraft.class_243.method_1031:(DDD)Lnet/minecraft/class_243;
        //    151: putfield  #254 // dev.angelvisuals.a.at.f:Lnet/minecraft/class_243;
        //    154: aload_0
        //    155: getfield  #254 // dev.angelvisuals.a.at.f:Lnet/minecraft/class_243;
        //    158: getfield  #295 // net.minecraft.class_243.field_1351:D
        //    161: dstore_2
        //    162: ldc2_w  #198 // 0.08d
        //    165: dstore  4
        //    167: aload_0
        //    168: getfield  #254 // dev.angelvisuals.a.at.f:Lnet/minecraft/class_243;
        //    171: getfield  #295 // net.minecraft.class_243.field_1351:D
        //    174: dconst_0
        //    175: dcmpg
        //    176: ifgt  187 (offset +11)
        //    179: ldc  #20 // -1058343878
        //    181: ldc  #21 // -1058343877
        //    183: ixor
        //    184: goto  192 (offset +8)
        //    187: ldc  #17 // -1168696093
        //    189: ldc  #17 // -1168696093
        //    191: ixor
        //    192: istore  6
        //    194: aload_0
        //    195: getfield  #254 // dev.angelvisuals.a.at.f:Lnet/minecraft/class_243;
        //    198: getfield  #295 // net.minecraft.class_243.field_1351:D
        //    201: dconst_0
        //    202: dcmpg
        //    203: ifgt  225 (offset +22)
        //    206: aload_0
        //    207: getstatic  #270 // net.minecraft.class_1294.field_5906:Lnet/minecraft/class_6880;
        //    210: invokevirtual  #345 // dev.angelvisuals.a.at.a:(Lnet/minecraft/class_6880;)Z
        //    213: ifeq  225 (offset +12)
        //    216: ldc2_w  #186 // 0.01d
        //    219: dstore  4
        //    221: aload_0
        //    222: invokevirtual  #319 // dev.angelvisuals.a.at.C:()V
        //    225: aload_0
        //    226: invokevirtual  #358 // dev.angelvisuals.a.at.x:()Z
        //    229: ifeq  519 (offset +290)
        //    232: aload_0
        //    233: getfield  #250 // dev.angelvisuals.a.at.b:Lnet/minecraft/class_1657;
        //    236: invokevirtual  #389 // net.minecraft.class_1657.method_29920:()Z
        //    239: ifeq  519 (offset +280)
        //    242: aload_0
        //    243: getfield  #253 // dev.angelvisuals.a.at.e:Lnet/minecraft/class_243;
        //    246: getfield  #295 // net.minecraft.class_243.field_1351:D
        //    249: dstore  7
        //    251: aload_0
        //    252: invokevirtual  #357 // dev.angelvisuals.a.at.w:()Z
        //    255: ifeq  263 (offset +8)
        //    258: ldc  #111 // 0.8999999761581421f
        //    260: goto  265 (offset +5)
        //    263: ldc  #110 // 0.800000011920929f
        //    265: fstore  9
        //    267: ldc  #104 // 0.019999999552965164f
        //    269: fstore  10
        //    271: aload_0
        //    272: getstatic  #304 // net.minecraft.class_5134.field_51578:Lnet/minecraft/class_6880;
        //    275: invokevirtual  #343 // dev.angelvisuals.a.at.a:(Lnet/minecraft/class_6880;)D
        //    278: d2f
        //    279: fstore  9
        //    281: aload_0
        //    282: getfield  #259 // dev.angelvisuals.a.at.v:Z
        //    285: ifne  295 (offset +10)
        //    288: fload  9
        //    290: ldc  #108 // 0.5f
        //    292: fmul
        //    293: fstore  9
        //    295: fload  9
        //    297: fconst_0
        //    298: fcmpl
        //    299: ifle  336 (offset +37)
        //    302: fload  9
        //    304: ldc  #109 // 0.546000063419342f
        //    306: fload  9
        //    308: fsub
        //    309: fload  9
        //    311: fmul
        //    312: ldc  #115 // 3.0f
        //    314: fdiv
        //    315: fadd
        //    316: fstore  9
        //    318: fload  10
        //    320: aload_0
        //    321: invokevirtual  #318 // dev.angelvisuals.a.at.C:()F
        //    324: fload  10
        //    326: fsub
        //    327: fload  9
        //    329: fmul
        //    330: ldc  #115 // 3.0f
        //    332: fdiv
        //    333: fadd
        //    334: fstore  10
        //    336: aload_0
        //    337: getstatic  #268 // net.minecraft.class_1294.field_5900:Lnet/minecraft/class_6880;
        //    340: invokevirtual  #345 // dev.angelvisuals.a.at.a:(Lnet/minecraft/class_6880;)Z
        //    343: ifeq  350 (offset +7)
        //    346: ldc  #113 // 0.9599999785423279f
        //    348: fstore  9
        //    350: aload_0
        //    351: fload  10
        //    353: aload_1
        //    354: invokevirtual  #330 // dev.angelvisuals.a.at.a:(FLnet/minecraft/class_243;)V
        //    357: aload_0
        //    358: aload_0
        //    359: getfield  #254 // dev.angelvisuals.a.at.f:Lnet/minecraft/class_243;
        //    362: invokevirtual  #348 // dev.angelvisuals.a.at.b:(Lnet/minecraft/class_243;)V
        //    365: aload_0
        //    366: getfield  #254 // dev.angelvisuals.a.at.f:Lnet/minecraft/class_243;
        //    369: astore  11
        //    371: aload_0
        //    372: getfield  #260 // dev.angelvisuals.a.at.w:Z
        //    375: ifeq  407 (offset +32)
        //    378: aload_0
        //    379: invokevirtual  #354 // dev.angelvisuals.a.at.t:()Z
        //    382: ifeq  407 (offset +25)
        //    385: new  #150 // net.minecraft.class_243
        //    388: dup
        //    389: aload  11
        //    391: getfield  #296 // net.minecraft.class_243.field_1352:D
        //    394: ldc2_w  #208 // 0.2d
        //    397: aload  11
        //    399: getfield  #294 // net.minecraft.class_243.field_1350:D
        //    402: invokespecial  #433 // net.minecraft.class_243.<init>:(DDD)V
        //    405: astore  11
        //    407: aload_0
        //    408: aload  11
        //    410: fload  9
        //    412: f2d
        //    413: ldc2_w  #222 // 0.8d
        //    416: fload  9
        //    418: f2d
        //    419: invokevirtual  #441 // net.minecraft.class_243.method_18805:(DDD)Lnet/minecraft/class_243;
        //    422: putfield  #254 // dev.angelvisuals.a.at.f:Lnet/minecraft/class_243;
        //    425: aload_0
        //    426: getfield  #250 // dev.angelvisuals.a.at.b:Lnet/minecraft/class_1657;
        //    429: dload  4
        //    431: iload  6
        //    433: aload_0
        //    434: getfield  #254 // dev.angelvisuals.a.at.f:Lnet/minecraft/class_243;
        //    437: invokevirtual  #388 // net.minecraft.class_1657.method_26317:(DZLnet/minecraft/class_243;)Lnet/minecraft/class_243;
        //    440: astore  12
        //    442: aload_0
        //    443: aload  12
        //    445: putfield  #254 // dev.angelvisuals.a.at.f:Lnet/minecraft/class_243;
        //    448: aload_0
        //    449: getfield  #260 // dev.angelvisuals.a.at.w:Z
        //    452: ifeq  516 (offset +64)
        //    455: aload_0
        //    456: aload  12
        //    458: getfield  #296 // net.minecraft.class_243.field_1352:D
        //    461: aload  12
        //    463: getfield  #295 // net.minecraft.class_243.field_1351:D
        //    466: ldc2_w  #218 // 0.6d
        //    469: dadd
        //    470: aload_0
        //    471: getfield  #253 // dev.angelvisuals.a.at.e:Lnet/minecraft/class_243;
        //    474: getfield  #295 // net.minecraft.class_243.field_1351:D
        //    477: dsub
        //    478: dload  7
        //    480: dadd
        //    481: aload  12
        //    483: getfield  #294 // net.minecraft.class_243.field_1350:D
        //    486: invokevirtual  #346 // dev.angelvisuals.a.at.b:(DDD)Z
        //    489: ifeq  516 (offset +27)
        //    492: aload_0
        //    493: new  #150 // net.minecraft.class_243
        //    496: dup
        //    497: aload  12
        //    499: getfield  #296 // net.minecraft.class_243.field_1352:D
        //    502: ldc2_w  #210 // 0.3d
        //    505: aload  12
        //    507: getfield  #294 // net.minecraft.class_243.field_1350:D
        //    510: invokespecial  #433 // net.minecraft.class_243.<init>:(DDD)V
        //    513: putfield  #254 // dev.angelvisuals.a.at.f:Lnet/minecraft/class_243;
        //    516: goto  1369 (offset +853)
        //    519: aload_0
        //    520: invokevirtual  #359 // dev.angelvisuals.a.at.y:()Z
        //    523: ifeq  742 (offset +219)
        //    526: aload_0
        //    527: getfield  #250 // dev.angelvisuals.a.at.b:Lnet/minecraft/class_1657;
        //    530: invokevirtual  #389 // net.minecraft.class_1657.method_29920:()Z
        //    533: ifeq  742 (offset +209)
        //    536: aload_0
        //    537: getfield  #253 // dev.angelvisuals.a.at.e:Lnet/minecraft/class_243;
        //    540: getfield  #295 // net.minecraft.class_243.field_1351:D
        //    543: dstore  7
        //    545: aload_0
        //    546: ldc  #104 // 0.019999999552965164f
        //    548: aload_1
        //    549: invokevirtual  #330 // dev.angelvisuals.a.at.a:(FLnet/minecraft/class_243;)V
        //    552: aload_0
        //    553: aload_0
        //    554: getfield  #254 // dev.angelvisuals.a.at.f:Lnet/minecraft/class_243;
        //    557: invokevirtual  #348 // dev.angelvisuals.a.at.b:(Lnet/minecraft/class_243;)V
        //    560: aload_0
        //    561: getstatic  #303 // net.minecraft.class_3486.field_15518:Lnet/minecraft/class_6862;
        //    564: invokevirtual  #340 // dev.angelvisuals.a.at.a:(Lnet/minecraft/class_6862;)D
        //    567: aload_0
        //    568: invokevirtual  #352 // dev.angelvisuals.a.at.g:()D
        //    571: dcmpg
        //    572: ifgt  617 (offset +45)
        //    575: aload_0
        //    576: aload_0
        //    577: getfield  #254 // dev.angelvisuals.a.at.f:Lnet/minecraft/class_243;
        //    580: ldc2_w  #214 // 0.5d
        //    583: ldc2_w  #222 // 0.8d
        //    586: ldc2_w  #214 // 0.5d
        //    589: invokevirtual  #441 // net.minecraft.class_243.method_18805:(DDD)Lnet/minecraft/class_243;
        //    592: putfield  #254 // dev.angelvisuals.a.at.f:Lnet/minecraft/class_243;
        //    595: aload_0
        //    596: aload_0
        //    597: getfield  #250 // dev.angelvisuals.a.at.b:Lnet/minecraft/class_1657;
        //    600: dload  4
        //    602: iload  6
        //    604: aload_0
        //    605: getfield  #254 // dev.angelvisuals.a.at.f:Lnet/minecraft/class_243;
        //    608: invokevirtual  #388 // net.minecraft.class_1657.method_26317:(DZLnet/minecraft/class_243;)Lnet/minecraft/class_243;
        //    611: putfield  #254 // dev.angelvisuals.a.at.f:Lnet/minecraft/class_243;
        //    614: goto  631 (offset +17)
        //    617: aload_0
        //    618: aload_0
        //    619: getfield  #254 // dev.angelvisuals.a.at.f:Lnet/minecraft/class_243;
        //    622: ldc2_w  #214 // 0.5d
        //    625: invokevirtual  #436 // net.minecraft.class_243.method_1021:(D)Lnet/minecraft/class_243;
        //    628: putfield  #254 // dev.angelvisuals.a.at.f:Lnet/minecraft/class_243;
        //    631: aload_0
        //    632: getfield  #250 // dev.angelvisuals.a.at.b:Lnet/minecraft/class_1657;
        //    635: invokevirtual  #398 // net.minecraft.class_1657.method_5740:()Z
        //    638: ifne  661 (offset +23)
        //    641: aload_0
        //    642: aload_0
        //    643: getfield  #254 // dev.angelvisuals.a.at.f:Lnet/minecraft/class_243;
        //    646: dconst_0
        //    647: dload  4
        //    649: dneg
        //    650: ldc2_w  #236 // 4.0d
        //    653: ddiv
        //    654: dconst_0
        //    655: invokevirtual  #439 // net.minecraft.class_243.method_1031:(DDD)Lnet/minecraft/class_243;
        //    658: putfield  #254 // dev.angelvisuals.a.at.f:Lnet/minecraft/class_243;
        //    661: aload_0
        //    662: getfield  #260 // dev.angelvisuals.a.at.w:Z
        //    665: ifeq  1369 (offset +704)
        //    668: aload_0
        //    669: aload_0
        //    670: getfield  #254 // dev.angelvisuals.a.at.f:Lnet/minecraft/class_243;
        //    673: getfield  #296 // net.minecraft.class_243.field_1352:D
        //    676: aload_0
        //    677: getfield  #254 // dev.angelvisuals.a.at.f:Lnet/minecraft/class_243;
        //    680: getfield  #295 // net.minecraft.class_243.field_1351:D
        //    683: ldc2_w  #218 // 0.6d
        //    686: dadd
        //    687: aload_0
        //    688: getfield  #253 // dev.angelvisuals.a.at.e:Lnet/minecraft/class_243;
        //    691: getfield  #295 // net.minecraft.class_243.field_1351:D
        //    694: dsub
        //    695: dload  7
        //    697: dadd
        //    698: aload_0
        //    699: getfield  #254 // dev.angelvisuals.a.at.f:Lnet/minecraft/class_243;
        //    702: getfield  #294 // net.minecraft.class_243.field_1350:D
        //    705: invokevirtual  #346 // dev.angelvisuals.a.at.b:(DDD)Z
        //    708: ifeq  1369 (offset +661)
        //    711: aload_0
        //    712: new  #150 // net.minecraft.class_243
        //    715: dup
        //    716: aload_0
        //    717: getfield  #254 // dev.angelvisuals.a.at.f:Lnet/minecraft/class_243;
        //    720: getfield  #296 // net.minecraft.class_243.field_1352:D
        //    723: ldc2_w  #210 // 0.3d
        //    726: aload_0
        //    727: getfield  #254 // dev.angelvisuals.a.at.f:Lnet/minecraft/class_243;
        //    730: getfield  #294 // net.minecraft.class_243.field_1350:D
        //    733: invokespecial  #433 // net.minecraft.class_243.<init>:(DDD)V
        //    736: putfield  #254 // dev.angelvisuals.a.at.f:Lnet/minecraft/class_243;
        //    739: goto  1369 (offset +630)
        //    742: aload_0
        //    743: getfield  #258 // dev.angelvisuals.a.at.u:Z
        //    746: ifeq  1100 (offset +354)
        //    749: aload_0
        //    750: getfield  #254 // dev.angelvisuals.a.at.f:Lnet/minecraft/class_243;
        //    753: astore  11
        //    755: aload  11
        //    757: getfield  #295 // net.minecraft.class_243.field_1351:D
        //    760: ldc2_w  #168 // -0.5d
        //    763: dcmpl
        //    764: ifle  772 (offset +8)
        //    767: aload_0
        //    768: fconst_1
        //    769: putfield  #248 // dev.angelvisuals.a.at.ax:F
        //    772: aload_0
        //    773: invokevirtual  #349 // dev.angelvisuals.a.at.c:()Lnet/minecraft/class_243;
        //    776: astore  10
        //    778: aload_0
        //    779: getfield  #247 // dev.angelvisuals.a.at.aw:F
        //    782: ldc  #103 // 0.01745329238474369f
        //    784: fmul
        //    785: fstore  9
        //    787: aload  10
        //    789: getfield  #296 // net.minecraft.class_243.field_1352:D
        //    792: aload  10
        //    794: getfield  #296 // net.minecraft.class_243.field_1352:D
        //    797: dmul
        //    798: aload  10
        //    800: getfield  #294 // net.minecraft.class_243.field_1350:D
        //    803: aload  10
        //    805: getfield  #294 // net.minecraft.class_243.field_1350:D
        //    808: dmul
        //    809: dadd
        //    810: invokestatic  #369 // java.lang.Math.sqrt:(D)D
        //    813: dstore  12
        //    815: aload_0
        //    816: getfield  #254 // dev.angelvisuals.a.at.f:Lnet/minecraft/class_243;
        //    819: invokevirtual  #442 // net.minecraft.class_243.method_37267:()D
        //    822: dstore  14
        //    824: aload  10
        //    826: invokevirtual  #440 // net.minecraft.class_243.method_1033:()D
        //    829: dstore  16
        //    831: fload  9
        //    833: invokestatic  #451 // net.minecraft.class_3532.method_15362:(F)F
        //    836: fstore  18
        //    838: fload  18
        //    840: f2d
        //    841: fload  18
        //    843: f2d
        //    844: dmul
        //    845: dconst_1
        //    846: dload  16
        //    848: ldc2_w  #212 // 0.4d
        //    851: ddiv
        //    852: invokestatic  #368 // java.lang.Math.min:(DD)D
        //    855: dmul
        //    856: d2f
        //    857: fstore  18
        //    859: aload_0
        //    860: getfield  #254 // dev.angelvisuals.a.at.f:Lnet/minecraft/class_243;
        //    863: dconst_0
        //    864: dload  4
        //    866: ldc2_w  #166 // -1.0d
        //    869: fload  18
        //    871: f2d
        //    872: ldc2_w  #220 // 0.75d
        //    875: dmul
        //    876: dadd
        //    877: dmul
        //    878: dconst_0
        //    879: invokevirtual  #439 // net.minecraft.class_243.method_1031:(DDD)Lnet/minecraft/class_243;
        //    882: astore  11
        //    884: aload  11
        //    886: getfield  #295 // net.minecraft.class_243.field_1351:D
        //    889: dconst_0
        //    890: dcmpg
        //    891: ifge  947 (offset +56)
        //    894: dload  12
        //    896: dconst_0
        //    897: dcmpl
        //    898: ifle  947 (offset +49)
        //    901: aload  11
        //    903: getfield  #295 // net.minecraft.class_243.field_1351:D
        //    906: ldc2_w  #176 // -0.1d
        //    909: dmul
        //    910: fload  18
        //    912: f2d
        //    913: dmul
        //    914: dstore  7
        //    916: aload  11
        //    918: aload  10
        //    920: getfield  #296 // net.minecraft.class_243.field_1352:D
        //    923: dload  7
        //    925: dmul
        //    926: dload  12
        //    928: ddiv
        //    929: dload  7
        //    931: aload  10
        //    933: getfield  #294 // net.minecraft.class_243.field_1350:D
        //    936: dload  7
        //    938: dmul
        //    939: dload  12
        //    941: ddiv
        //    942: invokevirtual  #439 // net.minecraft.class_243.method_1031:(DDD)Lnet/minecraft/class_243;
        //    945: astore  11
        //    947: fload  9
        //    949: fconst_0
        //    950: fcmpg
        //    951: ifge  1014 (offset +63)
        //    954: dload  12
        //    956: dconst_0
        //    957: dcmpl
        //    958: ifle  1014 (offset +56)
        //    961: dload  14
        //    963: fload  9
        //    965: invokestatic  #452 // net.minecraft.class_3532.method_15374:(F)F
        //    968: fneg
        //    969: f2d
        //    970: dmul
        //    971: ldc2_w  #192 // 0.04d
        //    974: dmul
        //    975: dstore  7
        //    977: aload  11
        //    979: aload  10
        //    981: getfield  #296 // net.minecraft.class_243.field_1352:D
        //    984: dneg
        //    985: dload  7
        //    987: dmul
        //    988: dload  12
        //    990: ddiv
        //    991: dload  7
        //    993: ldc2_w  #234 // 3.2d
        //    996: dmul
        //    997: aload  10
        //    999: getfield  #294 // net.minecraft.class_243.field_1350:D
        //   1002: dneg
        //   1003: dload  7
        //   1005: dmul
        //   1006: dload  12
        //   1008: ddiv
        //   1009: invokevirtual  #439 // net.minecraft.class_243.method_1031:(DDD)Lnet/minecraft/class_243;
        //   1012: astore  11
        //   1014: dload  12
        //   1016: dconst_0
        //   1017: dcmpl
        //   1018: ifle  1071 (offset +53)
        //   1021: aload  11
        //   1023: aload  10
        //   1025: getfield  #296 // net.minecraft.class_243.field_1352:D
        //   1028: dload  12
        //   1030: ddiv
        //   1031: dload  14
        //   1033: dmul
        //   1034: aload  11
        //   1036: getfield  #296 // net.minecraft.class_243.field_1352:D
        //   1039: dsub
        //   1040: ldc2_w  #202 // 0.1d
        //   1043: dmul
        //   1044: dconst_0
        //   1045: aload  10
        //   1047: getfield  #294 // net.minecraft.class_243.field_1350:D
        //   1050: dload  12
        //   1052: ddiv
        //   1053: dload  14
        //   1055: dmul
        //   1056: aload  11
        //   1058: getfield  #294 // net.minecraft.class_243.field_1350:D
        //   1061: dsub
        //   1062: ldc2_w  #202 // 0.1d
        //   1065: dmul
        //   1066: invokevirtual  #439 // net.minecraft.class_243.method_1031:(DDD)Lnet/minecraft/class_243;
        //   1069: astore  11
        //   1071: aload_0
        //   1072: aload  11
        //   1074: ldc2_w  #228 // 0.99d
        //   1077: ldc2_w  #224 // 0.98d
        //   1080: ldc2_w  #228 // 0.99d
        //   1083: invokevirtual  #441 // net.minecraft.class_243.method_18805:(DDD)Lnet/minecraft/class_243;
        //   1086: putfield  #254 // dev.angelvisuals.a.at.f:Lnet/minecraft/class_243;
        //   1089: aload_0
        //   1090: aload_0
        //   1091: getfield  #254 // dev.angelvisuals.a.at.f:Lnet/minecraft/class_243;
        //   1094: invokevirtual  #348 // dev.angelvisuals.a.at.b:(Lnet/minecraft/class_243;)V
        //   1097: goto  1369 (offset +272)
        //   1100: aload_0
        //   1101: invokevirtual  #328 // dev.angelvisuals.a.at.a:()Lnet/minecraft/class_2338;
        //   1104: astore  11
        //   1106: aload_0
        //   1107: getfield  #250 // dev.angelvisuals.a.at.b:Lnet/minecraft/class_1657;
        //   1110: invokevirtual  #394 // net.minecraft.class_1657.method_37908:()Lnet/minecraft/class_1937;
        //   1113: aload  11
        //   1115: invokevirtual  #415 // net.minecraft.class_1937.method_8320:(Lnet/minecraft/class_2338;)Lnet/minecraft/class_2680;
        //   1118: invokevirtual  #446 // net.minecraft.class_2680.method_26204:()Lnet/minecraft/class_2248;
        //   1121: invokevirtual  #419 // net.minecraft.class_2248.method_9499:()F
        //   1124: fstore  12
        //   1126: aload_0
        //   1127: getfield  #259 // dev.angelvisuals.a.at.v:Z
        //   1130: ifeq  1141 (offset +11)
        //   1133: fload  12
        //   1135: ldc  #112 // 0.9100000262260437f
        //   1137: fmul
        //   1138: goto  1143 (offset +5)
        //   1141: ldc  #112 // 0.9100000262260437f
        //   1143: fstore  9
        //   1145: aload_0
        //   1146: aload_1
        //   1147: fload  12
        //   1149: invokevirtual  #339 // dev.angelvisuals.a.at.a:(Lnet/minecraft/class_243;F)Lnet/minecraft/class_243;
        //   1152: astore  10
        //   1154: aload  10
        //   1156: getfield  #295 // net.minecraft.class_243.field_1351:D
        //   1159: dstore  13
        //   1161: aload_0
        //   1162: getstatic  #269 // net.minecraft.class_1294.field_5902:Lnet/minecraft/class_6880;
        //   1165: invokevirtual  #345 // dev.angelvisuals.a.at.a:(Lnet/minecraft/class_6880;)Z
        //   1168: ifeq  1219 (offset +51)
        //   1171: aload_0
        //   1172: getstatic  #269 // net.minecraft.class_1294.field_5902:Lnet/minecraft/class_6880;
        //   1175: invokevirtual  #344 // dev.angelvisuals.a.at.a:(Lnet/minecraft/class_6880;)Lnet/minecraft/class_1293;
        //   1178: astore  15
        //   1180: aload  15
        //   1182: ifnull  1216 (offset +34)
        //   1185: dload  13
        //   1187: ldc2_w  #194 // 0.05d
        //   1190: aload  15
        //   1192: invokevirtual  #381 // net.minecraft.class_1293.method_5578:()I
        //   1195: ldc  #50 // 205341394
        //   1197: ldc  #51 // 205341395
        //   1199: ixor
        //   1200: iadd
        //   1201: i2d
        //   1202: dmul
        //   1203: aload  10
        //   1205: getfield  #295 // net.minecraft.class_243.field_1351:D
        //   1208: dsub
        //   1209: ldc2_w  #208 // 0.2d
        //   1212: dmul
        //   1213: dadd
        //   1214: dstore  13
        //   1216: goto  1298 (offset +82)
        //   1219: aload_0
        //   1220: getfield  #250 // dev.angelvisuals.a.at.b:Lnet/minecraft/class_1657;
        //   1223: invokevirtual  #394 // net.minecraft.class_1657.method_37908:()Lnet/minecraft/class_1937;
        //   1226: invokevirtual  #417 // net.minecraft.class_1937.method_8608:()Z
        //   1229: ifeq  1281 (offset +52)
        //   1232: aload_0
        //   1233: getfield  #250 // dev.angelvisuals.a.at.b:Lnet/minecraft/class_1657;
        //   1236: invokevirtual  #394 // net.minecraft.class_1657.method_37908:()Lnet/minecraft/class_1937;
        //   1239: aload  11
        //   1241: invokevirtual  #410 // net.minecraft.class_1937.method_22340:(Lnet/minecraft/class_2338;)Z
        //   1244: ifne  1281 (offset +37)
        //   1247: aload_0
        //   1248: getfield  #253 // dev.angelvisuals.a.at.e:Lnet/minecraft/class_243;
        //   1251: getfield  #295 // net.minecraft.class_243.field_1351:D
        //   1254: aload_0
        //   1255: getfield  #250 // dev.angelvisuals.a.at.b:Lnet/minecraft/class_1657;
        //   1258: invokevirtual  #394 // net.minecraft.class_1657.method_37908:()Lnet/minecraft/class_1937;
        //   1261: invokevirtual  #412 // net.minecraft.class_1937.method_31607:()I
        //   1264: i2d
        //   1265: dcmpl
        //   1266: ifle  1275 (offset +9)
        //   1269: ldc2_w  #176 // -0.1d
        //   1272: goto  1276 (offset +4)
        //   1275: dconst_0
        //   1276: dstore  13
        //   1278: goto  1298 (offset +20)
        //   1281: aload_0
        //   1282: getfield  #250 // dev.angelvisuals.a.at.b:Lnet/minecraft/class_1657;
        //   1285: invokevirtual  #398 // net.minecraft.class_1657.method_5740:()Z
        //   1288: ifne  1298 (offset +10)
        //   1291: dload  13
        //   1293: dload  4
        //   1295: dsub
        //   1296: dstore  13
        //   1298: aload_0
        //   1299: getfield  #250 // dev.angelvisuals.a.at.b:Lnet/minecraft/class_1657;
        //   1302: invokevirtual  #391 // net.minecraft.class_1657.method_35053:()Z
        //   1305: ifeq  1334 (offset +29)
        //   1308: aload_0
        //   1309: new  #150 // net.minecraft.class_243
        //   1312: dup
        //   1313: aload  10
        //   1315: getfield  #296 // net.minecraft.class_243.field_1352:D
        //   1318: dload  13
        //   1320: aload  10
        //   1322: getfield  #294 // net.minecraft.class_243.field_1350:D
        //   1325: invokespecial  #433 // net.minecraft.class_243.<init>:(DDD)V
        //   1328: putfield  #254 // dev.angelvisuals.a.at.f:Lnet/minecraft/class_243;
        //   1331: goto  1369 (offset +38)
        //   1334: aload_0
        //   1335: new  #150 // net.minecraft.class_243
        //   1338: dup
        //   1339: aload  10
        //   1341: getfield  #296 // net.minecraft.class_243.field_1352:D
        //   1344: fload  9
        //   1346: f2d
        //   1347: dmul
        //   1348: dload  13
        //   1350: ldc2_w  #226 // 0.9800000190734863d
        //   1353: dmul
        //   1354: aload  10
        //   1356: getfield  #294 // net.minecraft.class_243.field_1350:D
        //   1359: fload  9
        //   1361: f2d
        //   1362: dmul
        //   1363: invokespecial  #433 // net.minecraft.class_243.<init>:(DDD)V
        //   1366: putfield  #254 // dev.angelvisuals.a.at.f:Lnet/minecraft/class_243;
        //   1369: aload_0
        //   1370: getfield  #250 // dev.angelvisuals.a.at.b:Lnet/minecraft/class_1657;
        //   1373: invokevirtual  #390 // net.minecraft.class_1657.method_31549:()Lnet/minecraft/class_1656;
        //   1376: getfield  #272 // net.minecraft.class_1656.field_7479:Z
        //   1379: ifeq  1426 (offset +47)
        //   1382: aload_0
        //   1383: getfield  #250 // dev.angelvisuals.a.at.b:Lnet/minecraft/class_1657;
        //   1386: invokevirtual  #400 // net.minecraft.class_1657.method_5765:()Z
        //   1389: ifne  1426 (offset +37)
        //   1392: aload_0
        //   1393: new  #150 // net.minecraft.class_243
        //   1396: dup
        //   1397: aload_0
        //   1398: getfield  #254 // dev.angelvisuals.a.at.f:Lnet/minecraft/class_243;
        //   1401: getfield  #296 // net.minecraft.class_243.field_1352:D
        //   1404: dload_2
        //   1405: ldc2_w  #218 // 0.6d
        //   1408: dmul
        //   1409: aload_0
        //   1410: getfield  #254 // dev.angelvisuals.a.at.f:Lnet/minecraft/class_243;
        //   1413: getfield  #294 // net.minecraft.class_243.field_1350:D
        //   1416: invokespecial  #433 // net.minecraft.class_243.<init>:(DDD)V
        //   1419: putfield  #254 // dev.angelvisuals.a.at.f:Lnet/minecraft/class_243;
        //   1422: aload_0
        //   1423: invokevirtual  #319 // dev.angelvisuals.a.at.C:()V
        //   1426: return
    }

  private class_243 method1296(class_243 arg0, float arg1) { // было: a
        method1297(method1298(arg1), arg0);
        field740 = method1305(field740);
        method1301(field740);
        class_243 var3 = field740;
        class_2338 var4 = method1336(field739);
        class_2680 var5 = method1337(var4);
        if (field746) {
            if (method1306()) {
                var3 = new class_243(var3.field_1352, 0.2, var3.field_1350);
            } else {
                if (var5 != null) {
                    if (var5.method_27852(class_2246.field_27879)) {
                        if (class_5635.method_32355(field737)) {
                            var3 = new class_243(var3.field_1352, 0.2, var3.field_1350);
                        }
                    }
                }
            }
        } else {
            if (field743) {
                if (method1306()) {
                    var3 = new class_243(var3.field_1352, 0.2, var3.field_1350);
                } else {
                    if (var5 != null) {
                        if (var5.method_27852(class_2246.field_27879)) {
                            if (class_5635.method_32355(field737)) {
                                var3 = new class_243(var3.field_1352, 0.2, var3.field_1350);
                            }
                        }
                    }
                }
            }
        }
        return var3;
    }

  private void method1297(float arg0, class_243 arg1) { // было: a
        class_243 var3 = class_1297.method_18795(arg1, arg0, av);
        field740 = field740.method_1019(var3);
    }

  private float method1298(float arg0) { // было: f
        return !field745 ? method1299() : method1300() * 0.2160000205039978f / (arg0 * arg0 * arg0);
    }

  private float method1299() { // было: B
        float var1 = 0.019999999552965164f;
        return !field738.field735.comp_3165() ? var1 : var1 + 0.006000000052154064f;
    }

  private float method1300() { // было: C
        return 0.10000000149011612f;
    }

  private void method1301(class_243 arg0) { // было: b
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: aload_1
        //      2: invokevirtual  #350 // dev.angelvisuals.a.at.c:(Lnet/minecraft/class_243;)Lnet/minecraft/class_243;
        //      5: astore_2
        //      6: aload_0
        //      7: aload_2
        //      8: invokevirtual  #337 // dev.angelvisuals.a.at.a:(Lnet/minecraft/class_243;)Lnet/minecraft/class_243;
        //     11: astore_3
        //     12: aload_3
        //     13: invokevirtual  #437 // net.minecraft.class_243.method_1027:()D
        //     16: ldc2_w  #178 // 1e-07d
        //     19: dcmpl
        //     20: ifle  53 (offset +33)
        //     23: aload_0
        //     24: aload_0
        //     25: getfield  #253 // dev.angelvisuals.a.at.e:Lnet/minecraft/class_243;
        //     28: aload_3
        //     29: invokevirtual  #434 // net.minecraft.class_243.method_1019:(Lnet/minecraft/class_243;)Lnet/minecraft/class_243;
        //     32: putfield  #253 // dev.angelvisuals.a.at.e:Lnet/minecraft/class_243;
        //     35: aload_0
        //     36: aload_0
        //     37: getfield  #250 // dev.angelvisuals.a.at.b:Lnet/minecraft/class_1657;
        //     40: getfield  #273 // net.minecraft.class_1657.field_18065:Lnet/minecraft/class_4048;
        //     43: aload_0
        //     44: getfield  #253 // dev.angelvisuals.a.at.e:Lnet/minecraft/class_243;
        //     47: invokevirtual  #460 // net.minecraft.class_4048.method_30757:(Lnet/minecraft/class_243;)Lnet/minecraft/class_238;
        //     50: putfield  #245 // dev.angelvisuals.a.at.a:Lnet/minecraft/class_238;
        //     53: aload_1
        //     54: getfield  #296 // net.minecraft.class_243.field_1352:D
        //     57: aload_3
        //     58: getfield  #296 // net.minecraft.class_243.field_1352:D
        //     61: invokestatic  #454 // net.minecraft.class_3532.method_20390:(DD)Z
        //     64: ifne  75 (offset +11)
        //     67: ldc  #23 // -1032650167
        //     69: ldc  #22 // -1032650168
        //     71: ixor
        //     72: goto  80 (offset +8)
        //     75: ldc  #24 // -1023333116
        //     77: ldc  #24 // -1023333116
        //     79: ixor
        //     80: istore  4
        //     82: aload_1
        //     83: getfield  #294 // net.minecraft.class_243.field_1350:D
        //     86: aload_3
        //     87: getfield  #294 // net.minecraft.class_243.field_1350:D
        //     90: invokestatic  #454 // net.minecraft.class_3532.method_20390:(DD)Z
        //     93: ifne  104 (offset +11)
        //     96: ldc  #60 // 800395840
        //     98: ldc  #61 // 800395841
        //    100: ixor
        //    101: goto  109 (offset +8)
        //    104: ldc  #79 // 1377910784
        //    106: ldc  #79 // 1377910784
        //    108: ixor
        //    109: istore  5
        //    111: aload_0
        //    112: iload  4
        //    114: ifne  122 (offset +8)
        //    117: iload  5
        //    119: ifeq  130 (offset +11)
        //    122: ldc  #15 // -1234740653
        //    124: ldc  #14 // -1234740654
        //    126: ixor
        //    127: goto  135 (offset +8)
        //    130: ldc  #37 // -532708328
        //    132: ldc  #37 // -532708328
        //    134: ixor
        //    135: putfield  #260 // dev.angelvisuals.a.at.w:Z
        //    138: aload_0
        //    139: aload_1
        //    140: getfield  #295 // net.minecraft.class_243.field_1351:D
        //    143: aload_3
        //    144: getfield  #295 // net.minecraft.class_243.field_1351:D
        //    147: dcmpl
        //    148: ifeq  159 (offset +11)
        //    151: ldc  #77 // 1212619230
        //    153: ldc  #78 // 1212619231
        //    155: ixor
        //    156: goto  164 (offset +8)
        //    159: ldc  #41 // -267235769
        //    161: ldc  #41 // -267235769
        //    163: ixor
        //    164: putfield  #261 // dev.angelvisuals.a.at.x:Z
        //    167: aload_0
        //    168: aload_0
        //    169: getfield  #261 // dev.angelvisuals.a.at.x:Z
        //    172: ifeq  192 (offset +20)
        //    175: aload_1
        //    176: getfield  #295 // net.minecraft.class_243.field_1351:D
        //    179: dconst_0
        //    180: dcmpg
        //    181: ifge  192 (offset +11)
        //    184: ldc  #33 // -759098734
        //    186: ldc  #34 // -759098733
        //    188: ixor
        //    189: goto  197 (offset +8)
        //    192: ldc  #26 // -988716282
        //    194: ldc  #26 // -988716282
        //    196: ixor
        //    197: putfield  #259 // dev.angelvisuals.a.at.v:Z
        //    200: aload_0
        //    201: invokevirtual  #358 // dev.angelvisuals.a.at.x:()Z
        //    204: ifne  211 (offset +7)
        //    207: aload_0
        //    208: invokevirtual  #323 // dev.angelvisuals.a.at.E:()V
        //    211: aload_0
        //    212: getfield  #259 // dev.angelvisuals.a.at.v:Z
        //    215: ifeq  225 (offset +10)
        //    218: aload_0
        //    219: invokevirtual  #319 // dev.angelvisuals.a.at.C:()V
        //    222: goto  248 (offset +26)
        //    225: aload_1
        //    226: getfield  #295 // net.minecraft.class_243.field_1351:D
        //    229: dconst_0
        //    230: dcmpg
        //    231: ifge  248 (offset +17)
        //    234: aload_0
        //    235: dup
        //    236: getfield  #248 // dev.angelvisuals.a.at.ax:F
        //    239: aload_1
        //    240: getfield  #295 // net.minecraft.class_243.field_1351:D
        //    243: d2f
        //    244: fsub
        //    245: putfield  #248 // dev.angelvisuals.a.at.ax:F
        //    248: aload_0
        //    249: getfield  #254 // dev.angelvisuals.a.at.f:Lnet/minecraft/class_243;
        //    252: astore  6
        //    254: aload_0
        //    255: getfield  #260 // dev.angelvisuals.a.at.w:Z
        //    258: ifne  268 (offset +10)
        //    261: aload_0
        //    262: getfield  #261 // dev.angelvisuals.a.at.x:Z
        //    265: ifeq  323 (offset +58)
        //    268: aload_0
        //    269: new  #150 // net.minecraft.class_243
        //    272: dup
        //    273: iload  4
        //    275: ifeq  282 (offset +7)
        //    278: dconst_0
        //    279: goto  287 (offset +8)
        //    282: aload  6
        //    284: getfield  #296 // net.minecraft.class_243.field_1352:D
        //    287: aload_0
        //    288: getfield  #259 // dev.angelvisuals.a.at.v:Z
        //    291: ifeq  298 (offset +7)
        //    294: dconst_0
        //    295: goto  303 (offset +8)
        //    298: aload  6
        //    300: getfield  #295 // net.minecraft.class_243.field_1351:D
        //    303: iload  5
        //    305: ifeq  312 (offset +7)
        //    308: dconst_0
        //    309: goto  317 (offset +8)
        //    312: aload  6
        //    314: getfield  #294 // net.minecraft.class_243.field_1350:D
        //    317: invokespecial  #433 // net.minecraft.class_243.<init>:(DDD)V
        //    320: putfield  #254 // dev.angelvisuals.a.at.f:Lnet/minecraft/class_243;
        //    323: return
    }

  private class_243 method1302(class_243 arg0) { // было: a
        int __stk1;
        int __stk2;
        int __stk3;
        int __stk4;
        class_238 var2 = new class_238(-0.3, 0.0, -0.3, 0.3, 1.8, 0.3).method_997(field739);
        List var3 = Collections.emptyList();
        class_243 var4;
        if (arg0.method_1027() != 0.0) {
            var4 = class_1297.method_20736(field737, arg0, var2, field737.method_37908(), var3);
        } else {
            var4 = arg0;
        }
        __stk1 = arg0.field_1352 == var4.field_1352 ? -501102843 ^ -501102843 : -1831938046 ^ -1831938045;
        int var5 = __stk1;
        __stk2 = arg0.field_1351 == var4.field_1351 ? -1151237750 ^ -1151237750 : 942978151 ^ 942978150;
        int var6 = __stk2;
        __stk3 = arg0.field_1350 == var4.field_1350 ? 1096674677 ^ 1096674677 : 1519365774 ^ 1519365775;
        int var7 = __stk3;
        __stk4 = field745 ? -898777713 ^ -898777714 : var6 == 0 ? -412004027 ^ -412004027 : arg0.field_1351 >= 0.0 ? -412004027 ^ -412004027 : -898777713 ^ -898777714;
        int var8 = __stk4;
        if (field737.method_49476() <= 0.0f) {
            return var4;
        } else {
            if (var8 == 0) {
                return var4;
            } else {
                if (var5 != 0) {
                    class_243 var9 = class_1297.method_20736(field737, new class_243(arg0.field_1352, ((double) field737.method_49476()), arg0.field_1350), var2, field737.method_37908(), var3);
                    class_243 var10 = class_1297.method_20736(field737, new class_243(0.0, ((double) field737.method_49476()), 0.0), var2.method_1012(arg0.field_1352, 0.0, arg0.field_1350), field737.method_37908(), var3);
                    class_243 var11 = class_1297.method_20736(field737, new class_243(arg0.field_1352, 0.0, arg0.field_1350), var2.method_997(var10), field737.method_37908(), var3).method_1019(var10);
                    if (var10.field_1351 < ((double) field737.method_49476())) {
                        if (var11.method_37268() > var9.method_37268()) {
                            var9 = var11;
                        }
                    }
                    if (var9.method_37268() <= var4.method_37268()) {
                        return var4;
                    } else {
                        return var9.method_1019(class_1297.method_20736(field737, new class_243(0.0, -var9.field_1351 + arg0.field_1351, 0.0), var2.method_997(var9), field737.method_37908(), var3));
                    }
                } else {
                    if (var7 == 0) {
                        return var4;
                    } else {
                        class_243 var9 = class_1297.method_20736(field737, new class_243(arg0.field_1352, ((double) field737.method_49476()), arg0.field_1350), var2, field737.method_37908(), var3);
                        class_243 var10 = class_1297.method_20736(field737, new class_243(0.0, ((double) field737.method_49476()), 0.0), var2.method_1012(arg0.field_1352, 0.0, arg0.field_1350), field737.method_37908(), var3);
                        class_243 var11 = class_1297.method_20736(field737, new class_243(arg0.field_1352, 0.0, arg0.field_1350), var2.method_997(var10), field737.method_37908(), var3).method_1019(var10);
                        if (var10.field_1351 < ((double) field737.method_49476())) {
                            if (var11.method_37268() > var9.method_37268()) {
                                var9 = var11;
                            }
                        }
                        if (var9.method_37268() <= var4.method_37268()) {
                            return var4;
                        } else {
                            return var9.method_1019(class_1297.method_20736(field737, new class_243(0.0, -var9.field_1351 + arg0.field_1351, 0.0), var2.method_997(var9), field737.method_37908(), var3));
                        }
                    }
                }
            }
        }
    }

  private void method1303() { // было: C
        ax = 0.0f;
    }

  public void method1304() { // было: D
        field740 = field740.method_1031(0.0, ((double) method1312()) - field740.field_1351, 0.0);
        if (method1311()) {
            float var1 = ((float) Math.toRadians(((double) av)));
            field740 = field740.method_1031(((double) -class_3532.method_15374(var1)) * 0.2, 0.0, ((double) class_3532.method_15362(var1)) * 0.2);
        }
    }

  private class_243 method1305(class_243 arg0) { // было: b
        if (method1306()) {
            method1303();
            double var2 = class_3532.method_15350(arg0.field_1352, -0.15000000596046448, 0.15000000596046448);
            double var4 = class_3532.method_15350(arg0.field_1350, -0.15000000596046448, 0.15000000596046448);
            double var6 = Math.max(arg0.field_1351, -0.15000000596046448);
            if (var6 < 0.0) {
                if (!method1337(method1336(field739)).method_27852(class_2246.field_16492)) {
                    if (field737.method_21754()) {
                        var6 = 0.0;
                    }
                }
            }
            return new class_243(var2, var6, var4);
        } else {
            return arg0;
        }
    }

  public boolean method1306() { // было: t
        class_2338 var1 = method1336(field739);
        class_2680 var2 = method1337(var1);
        if (!var2.method_26164(class_3481.field_22414)) {
            return !(var2.method_26204() instanceof class_2533) ? 1004246650 ^ 1004246650 : !method1307(var1, var2) ? 1004246650 ^ 1004246650 : -1780341877 ^ -1780341878;
        } else {
            return 2075873446 ^ 2075873447;
        }
    }

  private boolean method1307(class_2338 arg0, class_2680 arg1) { // было: a
        if ((((Boolean) arg1.method_11654(class_2533.field_11631))).booleanValue()) {
            class_2680 var3 = field737.method_37908().method_8320(arg0.method_10074());
            return !var3.method_27852(class_2246.field_9983) ? -302704641 ^ -302704641 : !(((class_2350) var3.method_11654(class_2399.field_11253))).equals(arg1.method_11654(class_2533.field_11177)) ? -302704641 ^ -302704641 : 1705077911 ^ 1705077910;
        } else {
            return 1913581743 ^ 1913581743;
        }
    }

  private class_243 method1308(class_243 arg0) { // было: c
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_1
        //      1: getfield  #295 // net.minecraft.class_243.field_1351:D
        //      4: dconst_0
        //      5: dcmpg
        //      6: ifgt  366 (offset +360)
        //      9: aload_0
        //     10: invokevirtual  #356 // dev.angelvisuals.a.at.v:()Z
        //     13: ifeq  366 (offset +353)
        //     16: aload_1
        //     17: getfield  #296 // net.minecraft.class_243.field_1352:D
        //     20: dstore_2
        //     21: aload_1
        //     22: getfield  #294 // net.minecraft.class_243.field_1350:D
        //     25: dstore  4
        //     27: ldc2_w  #194 // 0.05d
        //     30: dstore  6
        //     32: dload_2
        //     33: dconst_0
        //     34: dcmpl
        //     35: ifeq  107 (offset +72)
        //     38: aload_0
        //     39: getfield  #250 // dev.angelvisuals.a.at.b:Lnet/minecraft/class_1657;
        //     42: invokevirtual  #394 // net.minecraft.class_1657.method_37908:()Lnet/minecraft/class_1937;
        //     45: aload_0
        //     46: getfield  #250 // dev.angelvisuals.a.at.b:Lnet/minecraft/class_1657;
        //     49: aload_0
        //     50: getfield  #245 // dev.angelvisuals.a.at.a:Lnet/minecraft/class_238;
        //     53: dload_2
        //     54: ldc2_w  #168 // -0.5d
        //     57: dconst_0
        //     58: invokevirtual  #431 // net.minecraft.class_238.method_989:(DDD)Lnet/minecraft/class_238;
        //     61: invokevirtual  #416 // net.minecraft.class_1937.method_8587:(Lnet/minecraft/class_1297;Lnet/minecraft/class_238;)Z
        //     64: ifeq  107 (offset +43)
        //     67: dload_2
        //     68: dload  6
        //     70: dcmpg
        //     71: ifge  87 (offset +16)
        //     74: dload_2
        //     75: dload  6
        //     77: dneg
        //     78: dcmpl
        //     79: iflt  87 (offset +8)
        //     82: dconst_0
        //     83: dstore_2
        //     84: goto  107 (offset +23)
        //     87: dload_2
        //     88: dload_2
        //     89: dconst_0
        //     90: dcmpl
        //     91: ifle  100 (offset +9)
        //     94: dload  6
        //     96: dneg
        //     97: goto  102 (offset +5)
        //    100: dload  6
        //    102: dadd
        //    103: dstore_2
        //    104: goto  32 (offset -72)
        //    107: dload  4
        //    109: dconst_0
        //    110: dcmpl
        //    111: ifeq  190 (offset +79)
        //    114: aload_0
        //    115: getfield  #250 // dev.angelvisuals.a.at.b:Lnet/minecraft/class_1657;
        //    118: invokevirtual  #394 // net.minecraft.class_1657.method_37908:()Lnet/minecraft/class_1937;
        //    121: aload_0
        //    122: getfield  #250 // dev.angelvisuals.a.at.b:Lnet/minecraft/class_1657;
        //    125: aload_0
        //    126: getfield  #245 // dev.angelvisuals.a.at.a:Lnet/minecraft/class_238;
        //    129: dconst_0
        //    130: ldc2_w  #168 // -0.5d
        //    133: dload  4
        //    135: invokevirtual  #431 // net.minecraft.class_238.method_989:(DDD)Lnet/minecraft/class_238;
        //    138: invokevirtual  #416 // net.minecraft.class_1937.method_8587:(Lnet/minecraft/class_1297;Lnet/minecraft/class_238;)Z
        //    141: ifeq  190 (offset +49)
        //    144: dload  4
        //    146: dload  6
        //    148: dcmpg
        //    149: ifge  167 (offset +18)
        //    152: dload  4
        //    154: dload  6
        //    156: dneg
        //    157: dcmpl
        //    158: iflt  167 (offset +9)
        //    161: dconst_0
        //    162: dstore  4
        //    164: goto  190 (offset +26)
        //    167: dload  4
        //    169: dload  4
        //    171: dconst_0
        //    172: dcmpl
        //    173: ifle  182 (offset +9)
        //    176: dload  6
        //    178: dneg
        //    179: goto  184 (offset +5)
        //    182: dload  6
        //    184: dadd
        //    185: dstore  4
        //    187: goto  107 (offset -80)
        //    190: dload_2
        //    191: dconst_0
        //    192: dcmpl
        //    193: ifeq  316 (offset +123)
        //    196: dload  4
        //    198: dconst_0
        //    199: dcmpl
        //    200: ifeq  316 (offset +116)
        //    203: aload_0
        //    204: getfield  #250 // dev.angelvisuals.a.at.b:Lnet/minecraft/class_1657;
        //    207: invokevirtual  #394 // net.minecraft.class_1657.method_37908:()Lnet/minecraft/class_1937;
        //    210: aload_0
        //    211: getfield  #250 // dev.angelvisuals.a.at.b:Lnet/minecraft/class_1657;
        //    214: aload_0
        //    215: getfield  #245 // dev.angelvisuals.a.at.a:Lnet/minecraft/class_238;
        //    218: dload_2
        //    219: ldc2_w  #168 // -0.5d
        //    222: dload  4
        //    224: invokevirtual  #431 // net.minecraft.class_238.method_989:(DDD)Lnet/minecraft/class_238;
        //    227: invokevirtual  #416 // net.minecraft.class_1937.method_8587:(Lnet/minecraft/class_1297;Lnet/minecraft/class_238;)Z
        //    230: ifeq  316 (offset +86)
        //    233: dload_2
        //    234: dload  6
        //    236: dcmpg
        //    237: ifge  252 (offset +15)
        //    240: dload_2
        //    241: dload  6
        //    243: dneg
        //    244: dcmpl
        //    245: iflt  252 (offset +7)
        //    248: dconst_0
        //    249: goto  269 (offset +20)
        //    252: dload_2
        //    253: dconst_0
        //    254: dcmpl
        //    255: ifle  265 (offset +10)
        //    258: dload_2
        //    259: dload  6
        //    261: dsub
        //    262: goto  269 (offset +7)
        //    265: dload_2
        //    266: dload  6
        //    268: dadd
        //    269: dstore_2
        //    270: dload  4
        //    272: dload  6
        //    274: dcmpg
        //    275: ifge  293 (offset +18)
        //    278: dload  4
        //    280: dload  6
        //    282: dneg
        //    283: dcmpl
        //    284: iflt  293 (offset +9)
        //    287: dconst_0
        //    288: dstore  4
        //    290: goto  316 (offset +26)
        //    293: dload  4
        //    295: dload  4
        //    297: dconst_0
        //    298: dcmpl
        //    299: ifle  308 (offset +9)
        //    302: dload  6
        //    304: dneg
        //    305: goto  310 (offset +5)
        //    308: dload  6
        //    310: dadd
        //    311: dstore  4
        //    313: goto  190 (offset -123)
        //    316: aload_1
        //    317: getfield  #296 // net.minecraft.class_243.field_1352:D
        //    320: dload_2
        //    321: dcmpl
        //    322: ifne  335 (offset +13)
        //    325: aload_1
        //    326: getfield  #294 // net.minecraft.class_243.field_1350:D
        //    329: dload  4
        //    331: dcmpl
        //    332: ifeq  344 (offset +12)
        //    335: aload_0
        //    336: ldc  #36 // -715052853
        //    338: ldc  #35 // -715052854
        //    340: ixor
        //    341: putfield  #241 // dev.angelvisuals.a.at.B:Z
        //    344: aload_0
        //    345: invokevirtual  #355 // dev.angelvisuals.a.at.u:()Z
        //    348: ifeq  366 (offset +18)
        //    351: new  #150 // net.minecraft.class_243
        //    354: dup
        //    355: dload_2
        //    356: aload_1
        //    357: getfield  #295 // net.minecraft.class_243.field_1351:D
        //    360: dload  4
        //    362: invokespecial  #433 // net.minecraft.class_243.<init>:(DDD)V
        //    365: astore_1
        //    366: aload_1
        //    367: areturn
    }

  protected boolean method1309() { // было: u
        return field738.field735.comp_3164() ? -1992107699 ^ -1992107700 : !field738.field734 ? 521506801 ^ 521506801 : -1992107699 ^ -1992107700;
    }

  private boolean method1310() { // было: v
        return field745 ? -778878550 ^ -778878549 : ((double) ax) >= 0.5 ? 478730770 ^ 478730770 : field737.method_37908().method_8587(field737, field741.method_989(0.0, ((double) ax) - 0.5, 0.0)) ? 478730770 ^ 478730770 : -778878550 ^ -778878549;
    }

  private boolean method1311() { // было: w
        return field742;
    }

  private float method1312() { // было: D
        return 0.41999998688697815f * method1314() + method1313();
    }

  private float method1313() { // было: E
        if (!method1332(class_1294.field_5913)) {
            return 0.0f;
        } else {
            class_1293 var1 = method1333(class_1294.field_5913);
            return 0.10000000149011612f * ((float) (var1.method_5578() + (-59917028 ^ -59917027)));
        }
    }

  private float method1314() { // было: F
        float var1 = 0.0f;
        class_2248 var2 = method1337(method1336(field739)).method_26204();
        if (var2 != null) {
            var1 = var2.method_23350();
        }
        float var3 = 0.0f;
        class_2248 var4 = method1337(method1318()).method_26204();
        if (var4 != null) {
            var3 = var4.method_23350();
        }
        return var1 != 1.0f ? var1 : var3;
    }

  private boolean method1315(double arg0, double arg1, double arg2) { // было: b
        return method1316(field741.method_989(arg0, arg1, arg2));
    }

  private boolean method1316(class_238 arg0) { // было: a
        return !field737.method_37908().method_8587(field737, arg0) ? 282410009 ^ 282410009 : field737.method_37908().method_22345(arg0) ? 282410009 ^ 282410009 : 1393338876 ^ 1393338877;
    }

  private void method1317(class_6862 arg0) { // было: a
        field740 = field740.method_1031(0.0, 0.03999999910593033, 0.0);
    }

  private class_2338 method1318() { // было: a
        return class_2338.method_49637(field739.field_1352, field741.field_1322 - 0.5000001, field739.field_1350);
    }

  private double method1319() { // было: g
        return ((double) field737.method_5751()) >= 0.4 ? 0.4 : 0.0;
    }

  private boolean method1320() { // было: x
        return field748;
    }

  public boolean method1321() { // было: y
        return field751.getDouble(class_3486.field_15518) <= 0.0 ? -1419622567 ^ -1419622567 : 1555492573 ^ 1555492572;
    }

  private void method1322() { // было: E
        if (!(field737.method_5854() instanceof class_1690)) {
            if (!method1328(class_3486.field_15517, 0.014)) {
                field748 = 1458608627 ^ 1458608627;
            } else {
                method1303();
                field748 = 713480537 ^ 713480536;
            }
            return;
        } else {
            class_1690 var1 = ((class_1690) field737.method_5854());
            if (var1.method_5869()) {
                if (!method1328(class_3486.field_15517, 0.014)) {
                    field748 = 1458608627 ^ 1458608627;
                } else {
                    method1303();
                    field748 = 713480537 ^ 713480536;
                }
                return;
            } else {
                field748 = 2141573197 ^ 2141573197;
                return;
            }
        }
    }

  private void method1323() { // было: F
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #263 // dev.angelvisuals.a.at.z:Z
        //      4: ifeq  51 (offset +47)
        //      7: aload_0
        //      8: aload_0
        //      9: invokevirtual  #357 // dev.angelvisuals.a.at.w:()Z
        //     12: ifeq  40 (offset +28)
        //     15: aload_0
        //     16: invokevirtual  #358 // dev.angelvisuals.a.at.x:()Z
        //     19: ifeq  40 (offset +21)
        //     22: aload_0
        //     23: getfield  #250 // dev.angelvisuals.a.at.b:Lnet/minecraft/class_1657;
        //     26: invokevirtual  #400 // net.minecraft.class_1657.method_5765:()Z
        //     29: ifne  40 (offset +11)
        //     32: ldc  #74 // 1083028573
        //     34: ldc  #73 // 1083028572
        //     36: ixor
        //     37: goto  45 (offset +8)
        //     40: ldc  #87 // 1592273327
        //     42: ldc  #87 // 1592273327
        //     44: ixor
        //     45: putfield  #263 // dev.angelvisuals.a.at.z:Z
        //     48: goto  119 (offset +71)
        //     51: aload_0
        //     52: aload_0
        //     53: invokevirtual  #357 // dev.angelvisuals.a.at.w:()Z
        //     56: ifeq  111 (offset +55)
        //     59: aload_0
        //     60: invokevirtual  #360 // dev.angelvisuals.a.at.z:()Z
        //     63: ifeq  111 (offset +48)
        //     66: aload_0
        //     67: getfield  #250 // dev.angelvisuals.a.at.b:Lnet/minecraft/class_1657;
        //     70: invokevirtual  #400 // net.minecraft.class_1657.method_5765:()Z
        //     73: ifne  111 (offset +38)
        //     76: aload_0
        //     77: getfield  #250 // dev.angelvisuals.a.at.b:Lnet/minecraft/class_1657;
        //     80: invokevirtual  #394 // net.minecraft.class_1657.method_37908:()Lnet/minecraft/class_1937;
        //     83: aload_0
        //     84: aload_0
        //     85: getfield  #253 // dev.angelvisuals.a.at.e:Lnet/minecraft/class_243;
        //     88: invokevirtual  #336 // dev.angelvisuals.a.at.a:(Lnet/minecraft/class_243;)Lnet/minecraft/class_2338;
        //     91: invokevirtual  #414 // net.minecraft.class_1937.method_8316:(Lnet/minecraft/class_2338;)Lnet/minecraft/class_3610;
        //     94: getstatic  #302 // net.minecraft.class_3486.field_15517:Lnet/minecraft/class_6862;
        //     97: invokevirtual  #457 // net.minecraft.class_3610.method_15767:(Lnet/minecraft/class_6862;)Z
        //    100: ifeq  111 (offset +11)
        //    103: ldc  #28 // -935527153
        //    105: ldc  #27 // -935527154
        //    107: ixor
        //    108: goto  116 (offset +8)
        //    111: ldc  #46 // 141442014
        //    113: ldc  #46 // 141442014
        //    115: ixor
        //    116: putfield  #263 // dev.angelvisuals.a.at.z:Z
        //    119: return
    }

  private void method1324() { // было: G
        field750 = field752.contains(class_3486.field_15517);
        field752.clear();
        double var1 = method1325() - 0.1111111119389534;
        class_1297 var3 = field737.method_5854();
        if (!(var3 instanceof class_1690)) {
            class_2338 var4 = class_2338.method_49637(field739.field_1352, var1, field739.field_1350);
            class_3610 var5 = field737.method_37908().method_8316(var4);
            double var6 = ((double) (((float) var4.method_10264()) + var5.method_15763(field737.method_37908(), var4)));
            if (var6 > var1) {
                field752.addAll(var5.method_40181().toList());
            }
            return;
        } else {
            class_1690 var4 = ((class_1690) var3);
            if (var4.method_5869()) {
                var4 = class_2338.method_49637(field739.field_1352, var1, field739.field_1350);
                class_3610 var5 = field737.method_37908().method_8316(var4);
                double var6 = ((double) (((float) var4.method_10264()) + var5.method_15763(field737.method_37908(), var4)));
                if (var6 > var1) {
                    field752.addAll(var5.method_40181().toList());
                }
                return;
            } else {
                if (var4.method_5829().field_1325 < var1) {
                    var4 = class_2338.method_49637(field739.field_1352, var1, field739.field_1350);
                    class_3610 var5 = field737.method_37908().method_8316(var4);
                    double var6 = ((double) (((float) var4.method_10264()) + var5.method_15763(field737.method_37908(), var4)));
                    if (var6 > var1) {
                        field752.addAll(var5.method_40181().toList());
                    }
                    return;
                } else {
                    if (var4.method_5829().field_1322 > var1) {
                        var4 = class_2338.method_49637(field739.field_1352, var1, field739.field_1350);
                        class_3610 var5 = field737.method_37908().method_8316(var4);
                        double var6 = ((double) (((float) var4.method_10264()) + var5.method_15763(field737.method_37908(), var4)));
                        if (var6 > var1) {
                            field752.addAll(var5.method_40181().toList());
                        }
                        return;
                    } else {
                        return;
                    }
                }
            }
        }
    }

  private double method1325() { // было: h
        return field739.field_1351 + ((double) field737.method_5751());
    }

  public boolean method1326() { // было: z
        return !field750 ? -1329978551 ^ -1329978551 : !method1320() ? -1329978551 ^ -1329978551 : 670921950 ^ 670921951;
    }

  private double method1327(class_6862 arg0) { // было: a
        return field751.getDouble(arg0);
    }

  private boolean method1328(class_6862 arg0, double arg1) { // было: a
        double var11;
        int var14;
        class_243 var15;
        int var18;
        int var6;
        if (!method1329()) {
            class_238 var4 = field741.method_1011(0.001);
            int var5 = class_3532.method_15357(var4.field_1323);
            var6 = class_3532.method_15384(var4.field_1320);
            int var7 = class_3532.method_15357(var4.field_1322);
            int var8 = class_3532.method_15384(var4.field_1325);
            int var9 = class_3532.method_15357(var4.field_1321);
            int var10 = class_3532.method_15384(var4.field_1324);
            var11 = 0.0;
            int var13 = 1720948395 ^ 1720948394;
            var14 = 1195331689 ^ 1195331689;
            var15 = class_243.field_1353;
            int var16 = 1060393982 ^ 1060393982;
            class_2339 var17 = new class_2339();
            var18 = var5;
        } else {
            return 193303763 ^ 193303763;
        }
        double var11;
        int var14;
        class_243 var15;
        while (var18 < var6) {
            int var19 = var7;
            while (var19 < var8) {
                int var20 = var9;
                while (var20 < var10) {
                    var17.method_10103(var18, var19, var20);
                    class_3610 var21 = field737.method_37908().method_8316(var17);
                    if (var21.method_15767(arg0)) {
                        double var22 = ((double) (((float) var19) + var21.method_15763(field737.method_37908(), var17)));
                        if (var22 >= var4.field_1322) {
                            var14 = 900612538 ^ 900612539;
                            var11 = Math.max(var22 - var4.field_1322, var11);
                            if (var13 != 0) {
                                class_243 var24 = var21.method_15758(field737.method_37908(), var17);
                                if (var11 < 0.4) {
                                    var24 = var24.method_1021(var11);
                                }
                                var15 = var15.method_1019(var24);
                                ++var16;
                            }
                        }
                    }
                    ++var20;
                    continue;
                }
                ++var19;
                continue;
            }
            ++var18;
            continue;
        }
        if (var15.method_1033() > 0.0) {
            class_243 var15;
            if (var16 > 0) {
                var15 = var15.method_1021(1.0 / ((double) var16));
            }
            class_243 var15 = var15.method_1021(arg1);
            if (Math.abs(field740.field_1352) < 0.003) {
                if (Math.abs(field740.field_1350) < 0.003) {
                    if (var15.method_1033() < 0.0045) {
                        var15 = var15.method_1029().method_1021(0.0045);
                    }
                }
            }
            field740 = field740.method_1019(var15);
        }
        field751.put(arg0, var11);
        return var14;
    }

  private boolean method1329() { // было: A
        class_238 var1 = field741.method_1014(1.0);
        int var2 = class_3532.method_15357(var1.field_1323);
        int var3 = class_3532.method_15384(var1.field_1320);
        int var4 = class_3532.method_15357(var1.field_1321);
        int var5 = class_3532.method_15384(var1.field_1324);
        return field737.method_37908().method_33597(var2, var4, var3, var5) ? 939309829 ^ 939309829 : -145688084 ^ -145688083;
    }

  private class_243 method1330() { // было: c
        return method1331(aw, av);
    }

  private class_243 method1331(float arg0, float arg1) { // было: a
        float var3 = ((float) (((double) arg0) * 3.141592653589793 / 180.0));
        float var4 = ((float) (((double) -arg1) * 3.141592653589793 / 180.0));
        float var5 = class_3532.method_15362(var4);
        float var6 = class_3532.method_15374(var4);
        float var7 = class_3532.method_15362(var3);
        float var8 = class_3532.method_15374(var3);
        return new class_243(((double) (var6 * var7)), ((double) -var8), ((double) (var5 * var7)));
    }

  public boolean method1332(class_6880 arg0) { // было: a
        class_1293 var2 = field737.method_6112(arg0);
        return var2 == null ? 1985185588 ^ 1985185588 : var2.method_5584() < dU ? 1985185588 ^ 1985185588 : 2075439636 ^ 2075439637;
    }

  private class_1293 method1333(class_6880 arg0) { // было: a
        class_1293 var2 = field737.method_6112(arg0);
        return var2 == null ? null : var2.method_5584() < dU ? null : var2;
    }

  public double method1334(class_6880 arg0) { // было: a
        return field737.method_6127().method_26852(arg0);
    }

  public at method1335() { // было: a
        return new at(field737, field738, field739, field740, field741, av, aw, field742, ax, dT, field743, field744, field745, field746, field747, field748, field749, field750, new Object2DoubleArrayMap(field751), new HashSet(field752));
    }

  public class_2338 method1336(class_243 arg0) { // было: a
        return new class_2338(class_3532.method_15357(arg0.field_1352), class_3532.method_15357(arg0.field_1351), class_3532.method_15357(arg0.field_1350));
    }

  public class_2680 method1337(class_2338 arg0) { // было: a
        return field737.method_37908().method_8320(arg0);
    }

  public Object method1338() { // было: a
        return method1335();
    }

  private static int du(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int dv(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int dw(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}