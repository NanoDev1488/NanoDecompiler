// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.aw
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.a.ClassA146;
import lombok.Generated;
import net.minecraft.class_1294;
import net.minecraft.class_1309;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3532;

public final class aw implements ClassA146 {

    // ---- поля ----
  private static final class_310 field787; // было: a
  private static final float ay = 1.6100000143051147f;
  private static final float az = 1.5f;
  private static final float[] field788; // было: a
  private static final float[] field789; // было: b
  private static final String hl = "// you are reading machine-generated garbage";
  private static final String hm = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String hn = "// Joiner sees you";
  private static final String ho = "// === DO NOT TOUCH ===";
  private static final String hp = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final int eh = -1187544394;
  private static final int ei = 1352425819;
  private static final int ej = -1937018914;
  private static final byte[] aC;

    static {
        aC = "igF:S5Y=_EY2yr1?La} ]Egmb77y7-i`GMPSVA8BsE{8c/prXM6l`xtb|IlFHNC4cLlVonvO[rhKq?xN=Q%!#@JC7iGGJI-ak^PpfV5up(@-*_z_Q$WRJ%Q+'JeH6-'b+zX{wlL:wSGN<,y}He[P+mNT:e~lExGQINrgVLMai2z dhI,^.#Y{n^#ae\"!7#E&TU'E6\\Wu,CJ?.yb}li<?1ey;Q2.Pa}|3ewA(DK(n}T#/3jl\"2dx:>YP3bV$`h<+u".getBytes("ISO-8859-1");
        field787 = class_310.method_1551();
        float[] __obj1 = new float[-782159466 ^ -782159411];
        __obj1[698696336 ^ 698696336] = 1.6100000143051147f;
        __obj1[229763120 ^ 229763121] = 1.6100000143051147f;
        __obj1[770933037 ^ 770933039] = 1.6100000143051147f;
        __obj1[388971946 ^ 388971945] = 1.6100000143051147f;
        __obj1[-1213291806 ^ -1213291802] = 1.6100000143051147f;
        __obj1[-1956657482 ^ -1956657485] = 1.6100000143051147f;
        __obj1[-1134793479 ^ -1134793473] = 1.6200000047683716f;
        __obj1[2067399907 ^ 2067399908] = 1.6200000047683716f;
        __obj1[-1095194121 ^ -1095194113] = 1.6200000047683716f;
        __obj1[116861298 ^ 116861307] = 1.6299999952316284f;
        __obj1[2061303221 ^ 2061303231] = 1.6299999952316284f;
        __obj1[-810908751 ^ -810908742] = 1.6399999856948853f;
        __obj1[1887178202 ^ 1887178198] = 1.649999976158142f;
        __obj1[1444505683 ^ 1444505694] = 1.649999976158142f;
        __obj1[867505632 ^ 867505646] = 1.659999966621399f;
        __obj1[1503630504 ^ 1503630503] = 1.6699999570846558f;
        __obj1[352529384 ^ 352529400] = 1.6799999475479126f;
        __obj1[2011588288 ^ 2011588305] = 1.690000057220459f;
        __obj1[499523144 ^ 499523162] = 1.7000000476837158f;
        __obj1[-1454666010 ^ -1454665995] = 1.7100000381469727f;
        __obj1[875529887 ^ 875529867] = 1.7200000286102295f;
        __obj1[-388733303 ^ -388733284] = 1.7300000190734863f;
        __obj1[-860523305 ^ -860523327] = 1.7300000190734863f;
        __obj1[-13321599 ^ -13321578] = 1.75f;
        __obj1[967544202 ^ 967544210] = 1.7599999904632568f;
        __obj1[774230487 ^ 774230478] = 1.7799999713897705f;
        __obj1[712286368 ^ 712286394] = 1.7899999618530273f;
        __obj1[1692970280 ^ 1692970291] = 1.809999942779541f;
        __obj1[1163475058 ^ 1163475054] = 1.8300000429153442f;
        __obj1[-258134035 ^ -258134032] = 1.850000023841858f;
        __obj1[-1474465077 ^ -1474465067] = 1.8700000047683716f;
        __obj1[736093339 ^ 736093316] = 1.8899999856948853f;
        __obj1[-867028089 ^ -867028057] = 1.909999966621399f;
        __obj1[-614548900 ^ -614548867] = 1.9299999475479126f;
        __obj1[1112437283 ^ 1112437249] = 1.9500000476837158f;
        __obj1[1666200001 ^ 1666200034] = 1.9800000190734863f;
        __obj1[1419159240 ^ 1419159276] = 2.009999990463257f;
        __obj1[-1896725323 ^ -1896725360] = 2.0299999713897705f;
        __obj1[1628846856 ^ 1628846894] = 2.059999942779541f;
        __obj1[176278334 ^ 176278297] = 2.0899999141693115f;
        __obj1[-1412180112 ^ -1412180136] = 2.119999885559082f;
        __obj1[-129013258 ^ -129013281] = 2.1600000858306885f;
        __obj1[-1153255655 ^ -1153255629] = 2.190000057220459f;
        __obj1[1780749480 ^ 1780749443] = 2.2300000190734863f;
        __obj1[-1065604646 ^ -1065604618] = 2.2699999809265137f;
        __obj1[470366405 ^ 470366440] = 2.309999942779541f;
        __obj1[315837150 ^ 315837168] = 2.3499999046325684f;
        __obj1[-2124646274 ^ -2124646319] = 2.309999942779541f;
        __obj1[-1073853769 ^ -1073853817] = 2.2699999809265137f;
        __obj1[894291032 ^ 894291049] = 2.2300000190734863f;
        __obj1[446391685 ^ 446391735] = 2.190000057220459f;
        __obj1[-736436456 ^ -736436437] = 2.1600000858306885f;
        __obj1[1434548918 ^ 1434548866] = 2.119999885559082f;
        __obj1[-356415455 ^ -356415468] = 2.0899999141693115f;
        __obj1[-32173615 ^ -32173593] = 2.059999942779541f;
        __obj1[-52126261 ^ -52126212] = 2.0299999713897705f;
        __obj1[644435043 ^ 644435035] = 2.009999990463257f;
        __obj1[1199893690 ^ 1199893635] = 1.9800000190734863f;
        __obj1[-1573295136 ^ -1573295142] = 1.9500000476837158f;
        __obj1[369093949 ^ 369093894] = 1.9299999475479126f;
        __obj1[-985444556 ^ -985444600] = 1.8899999856948853f;
        __obj1[1211053652 ^ 1211053673] = 1.8700000047683716f;
        __obj1[-1288756418 ^ -1288756480] = 1.850000023841858f;
        __obj1[-1711850493 ^ -1711850436] = 1.8300000429153442f;
        __obj1[-588944000 ^ -588943936] = 1.809999942779541f;
        __obj1[-1049150133 ^ -1049150198] = 1.7899999618530273f;
        __obj1[1023240457 ^ 1023240523] = 1.7799999713897705f;
        __obj1[-1827866477 ^ -1827866416] = 1.7599999904632568f;
        __obj1[-458487743 ^ -458487803] = 1.75f;
        __obj1[-2081995863 ^ -2081995796] = 1.7300000190734863f;
        __obj1[-680266616 ^ -680266546] = 1.7200000286102295f;
        __obj1[2041419854 ^ 2041419785] = 1.7100000381469727f;
        __obj1[540214700 ^ 540214756] = 1.7000000476837158f;
        __obj1[747615111 ^ 747615182] = 1.690000057220459f;
        __obj1[761198570 ^ 761198496] = 1.6799999475479126f;
        __obj1[-1267270895 ^ -1267270822] = 1.6699999570846558f;
        __obj1[1624599607 ^ 1624599675] = 1.659999966621399f;
        __obj1[-1988628844 ^ -1988628775] = 1.649999976158142f;
        __obj1[-685231544 ^ -685231610] = 1.6399999856948853f;
        __obj1[1447657983 ^ 1447657904] = 1.6299999952316284f;
        __obj1[435369707 ^ 435369659] = 1.6299999952316284f;
        __obj1[-926174140 ^ -926174187] = 1.6299999952316284f;
        __obj1[2138342018 ^ 2138342096] = 1.6200000047683716f;
        __obj1[424172120 ^ 424172043] = 1.6200000047683716f;
        __obj1[45446715 ^ 45446767] = 1.6200000047683716f;
        __obj1[1991689803 ^ 1991689758] = 1.6100000143051147f;
        __obj1[392141397 ^ 392141315] = 1.6100000143051147f;
        __obj1[636742407 ^ 636742480] = 1.6100000143051147f;
        __obj1[621084965 ^ 621085053] = 1.6100000143051147f;
        __obj1[784845135 ^ 784845078] = 1.6100000143051147f;
        __obj1[1710076386 ^ 1710076344] = 1.6100000143051147f;
        field788 = __obj1;
        float[] __obj2 = new float[2095211783 ^ 2095211868];
        __obj2[-907530390 ^ -907530390] = 1.6100000143051147f;
        __obj2[-1513190436 ^ -1513190435] = 1.6100000143051147f;
        __obj2[-2056949439 ^ -2056949437] = 1.6100000143051147f;
        __obj2[-1253585057 ^ -1253585060] = 1.6200000047683716f;
        __obj2[-473928921 ^ -473928925] = 1.6200000047683716f;
        __obj2[-1883688838 ^ -1883688833] = 1.6200000047683716f;
        __obj2[1472355984 ^ 1472355990] = 1.6299999952316284f;
        __obj2[-1547688100 ^ -1547688101] = 1.6299999952316284f;
        __obj2[522755313 ^ 522755321] = 1.6399999856948853f;
        __obj2[-717821977 ^ -717821970] = 1.649999976158142f;
        __obj2[-1282244115 ^ -1282244121] = 1.649999976158142f;
        __obj2[-1461491945 ^ -1461491940] = 1.659999966621399f;
        __obj2[-1614696223 ^ -1614696211] = 1.6699999570846558f;
        __obj2[1981691133 ^ 1981691120] = 1.6799999475479126f;
        __obj2[-90693729 ^ -90693743] = 1.690000057220459f;
        __obj2[928285222 ^ 928285225] = 1.7000000476837158f;
        __obj2[524991805 ^ 524991789] = 1.7100000381469727f;
        __obj2[1929374283 ^ 1929374298] = 1.7200000286102295f;
        __obj2[229813285 ^ 229813303] = 1.7300000190734863f;
        __obj2[1115271480 ^ 1115271467] = 1.7300000190734863f;
        __obj2[-281637686 ^ -281637666] = 1.75f;
        __obj2[-1188079509 ^ -1188079490] = 1.7599999904632568f;
        __obj2[-1541382343 ^ -1541382353] = 1.7799999713897705f;
        __obj2[-81944981 ^ -81944964] = 1.7899999618530273f;
        __obj2[-2016898300 ^ -2016898276] = 1.809999942779541f;
        __obj2[-1810145800 ^ -1810145823] = 1.8300000429153442f;
        __obj2[291243782 ^ 291243804] = 1.850000023841858f;
        __obj2[-1692514335 ^ -1692514310] = 1.8700000047683716f;
        __obj2[1742703138 ^ 1742703166] = 1.8899999856948853f;
        __obj2[-866431188 ^ -866431183] = 1.909999966621399f;
        __obj2[-2098133822 ^ -2098133796] = 1.9299999475479126f;
        __obj2[776448513 ^ 776448542] = 1.9500000476837158f;
        __obj2[506741630 ^ 506741598] = 1.9800000190734863f;
        __obj2[-879891884 ^ -879891851] = 2.009999990463257f;
        __obj2[-522612601 ^ -522612571] = 2.0299999713897705f;
        __obj2[-1044019089 ^ -1044019124] = 2.059999942779541f;
        __obj2[1010264627 ^ 1010264599] = 2.0899999141693115f;
        __obj2[-1286092381 ^ -1286092410] = 2.119999885559082f;
        __obj2[1301917004 ^ 1301917034] = 2.1600000858306885f;
        __obj2[1048110667 ^ 1048110700] = 2.190000057220459f;
        __obj2[-2078195528 ^ -2078195568] = 2.2300000190734863f;
        __obj2[-390985836 ^ -390985795] = 2.240000009536743f;
        __obj2[-108302679 ^ -108302717] = 2.2100000381469727f;
        __obj2[1379075987 ^ 1379076024] = 2.2100000381469727f;
        __obj2[-76376171 ^ -76376135] = 2.2100000381469727f;
        __obj2[-1433334080 ^ -1433334035] = 2.2300000190734863f;
        __obj2[-1393069150 ^ -1393069172] = 2.2300000190734863f;
        __obj2[698418936 ^ 698418903] = 2.190000057220459f;
        __obj2[1230407652 ^ 1230407636] = 2.1600000858306885f;
        __obj2[-1627996254 ^ -1627996269] = 2.119999885559082f;
        __obj2[1356775102 ^ 1356775052] = 2.0899999141693115f;
        __obj2[-1513071848 ^ -1513071829] = 2.059999942779541f;
        __obj2[1363717379 ^ 1363717431] = 2.0299999713897705f;
        __obj2[50489470 ^ 50489419] = 2.009999990463257f;
        __obj2[1963542639 ^ 1963542617] = 1.9800000190734863f;
        __obj2[233168721 ^ 233168742] = 1.9500000476837158f;
        __obj2[-1235489417 ^ -1235489457] = 1.9299999475479126f;
        __obj2[1019494591 ^ 1019494534] = 1.8899999856948853f;
        __obj2[-1656817919 ^ -1656817861] = 1.8700000047683716f;
        __obj2[-1255789224 ^ -1255789213] = 1.850000023841858f;
        __obj2[-1900410924 ^ -1900410904] = 1.8300000429153442f;
        __obj2[-943629753 ^ -943629702] = 1.809999942779541f;
        __obj2[633789425 ^ 633789391] = 1.7899999618530273f;
        __obj2[1879532300 ^ 1879532339] = 1.7799999713897705f;
        __obj2[968128746 ^ 968128682] = 1.7599999904632568f;
        __obj2[-495960040 ^ -495959975] = 1.75f;
        __obj2[446879852 ^ 446879790] = 1.7300000190734863f;
        __obj2[502375734 ^ 502375797] = 1.7200000286102295f;
        __obj2[-113021790 ^ -113021722] = 1.7100000381469727f;
        __obj2[-2100973440 ^ -2100973371] = 1.7000000476837158f;
        __obj2[465310984 ^ 465311054] = 1.690000057220459f;
        __obj2[-1311444306 ^ -1311444247] = 1.6799999475479126f;
        __obj2[-1705520678 ^ -1705520750] = 1.6699999570846558f;
        __obj2[493629131 ^ 493629058] = 1.659999966621399f;
        __obj2[-1399395603 ^ -1399395673] = 1.649999976158142f;
        __obj2[-2028987124 ^ -2028987065] = 1.6399999856948853f;
        __obj2[1513209310 ^ 1513209234] = 1.6299999952316284f;
        __obj2[-575121632 ^ -575121555] = 1.6299999952316284f;
        __obj2[1315642455 ^ 1315642393] = 1.6299999952316284f;
        __obj2[193854210 ^ 193854285] = 1.6200000047683716f;
        __obj2[305970281 ^ 305970233] = 1.6200000047683716f;
        __obj2[1729574653 ^ 1729574572] = 1.6200000047683716f;
        __obj2[-639135138 ^ -639135220] = 1.6100000143051147f;
        __obj2[-927258555 ^ -927258602] = 1.6100000143051147f;
        __obj2[201833562 ^ 201833486] = 1.6100000143051147f;
        __obj2[-355269005 ^ -355269082] = 1.6100000143051147f;
        __obj2[2025254799 ^ 2025254873] = 1.6100000143051147f;
        __obj2[474473445 ^ 474473394] = 1.6100000143051147f;
        __obj2[1007068554 ^ 1007068626] = 1.6100000143051147f;
        __obj2[-908192916 ^ -908192971] = 1.6100000143051147f;
        __obj2[-1068717006 ^ -1068716952] = 1.6100000143051147f;
        field789 = __obj2;
    }

  public static class_243 method1398(class_1309 arg0) { // было: a
        int __stk1;
        double __stk2;
        float var1 = method1399(arg0);
        class_243 var2 = arg0.method_5720();
        class_243 var3 = class_243.method_1030(arg0.method_36455(), arg0.method_36454()).method_1021(((double) var1));
        float var4 = arg0.method_36455() * 0.01745329238474369f;
        double var5 = Math.sqrt(var2.field_1352 * var2.field_1352 + var2.field_1350 * var2.field_1350);
        double var7 = var3.method_37267();
        __stk1 = arg0.method_18798().field_1351 > 0.0 ? 220208641 ^ 220208641 : -284932550 ^ -284932549;
        int var9 = __stk1;
        __stk2 = var9 == 0 ? arg0.method_56989() : !arg0.method_6059(class_1294.field_5906) ? arg0.method_56989() : Math.min(arg0.method_56989(), 0.01);
        double var10 = __stk2;
        double var12 = class_3532.method_33723(Math.cos(((double) var4)));
        var3 = var3.method_1031(0.0, var10 * (-1.0 + var12 * 0.75), 0.0);
        if (var3.field_1351 < 0.0) {
            if (var5 > 0.0) {
                double var14 = var3.field_1351 * -0.1 * var12;
                var3 = var3.method_1031(var2.field_1352 * var14 / var5, var14, var2.field_1350 * var14 / var5);
            }
        }
        if (var4 < 0.0f) {
            if (var5 > 0.0) {
                double var14 = var7 * ((double) -class_3532.method_15374(var4)) * 0.04;
                var3 = var3.method_1031(-var2.field_1352 * var14 / var5, var14 * 3.2, -var2.field_1350 * var14 / var5);
            }
        }
        if (var5 > 0.0) {
            var3 = var3.method_1031((var2.field_1352 / var5 * var7 - var3.field_1352) * 0.1, 0.0, (var2.field_1350 / var5 * var7 - var3.field_1350) * 0.1);
        }
        double var16 = var3.method_1033();
        return new class_243(var16, var16, var16).method_18805(0.99, 0.98, 0.99);
    }

  private static float method1399(class_1309 arg0) { // было: a
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokevirtual  #564 // net.minecraft.class_1309.method_36454:()F
        //      4: invokestatic  #578 // net.minecraft.class_3532.method_15393:(F)F
        //      7: invokestatic  #552 // java.lang.Math.abs:(F)F
        //     10: fstore_1
        //     11: fload_1
        //     12: invokestatic  #549 // dev.angelvisuals.a.aw.g:(F)F
        //     15: fstore_2
        //     16: aload_0
        //     17: invokevirtual  #565 // net.minecraft.class_1309.method_36455:()F
        //     20: invokestatic  #550 // dev.angelvisuals.a.aw.h:(F)F
        //     23: invokestatic  #552 // java.lang.Math.abs:(F)F
        //     26: fstore_3
        //     27: fload_3
        //     28: ldc_w  #490 // 70.0f
        //     31: fcmpl
        //     32: iflt  47 (offset +15)
        //     35: fload_3
        //     36: ldc_w  #492 // 90.0f
        //     39: fcmpg
        //     40: ifgt  47 (offset +7)
        //     43: ldc_w  #429 // 1.6150000095367432f
        //     46: freturn
        //     47: getstatic  #538 // dev.angelvisuals.a.aw.a:[F
        //     50: fload_2
        //     51: f2d
        //     52: invokestatic  #553 // java.lang.Math.ceil:(D)D
        //     55: d2i
        //     56: ldc  #191 // -99924279
        //     58: ldc  #190 // -99924333
        //     60: ixor
        //     61: invokestatic  #558 // java.lang.Math.min:(II)I
        //     64: faload
        //     65: fstore  4
        //     67: fload_3
        //     68: f2d
        //     69: invokestatic  #553 // java.lang.Math.ceil:(D)D
        //     72: d2i
        //     73: getstatic  #540 // dev.angelvisuals.a.aw.b:[F
        //     76: arraylength
        //     77: ldc  #248 // 457619704
        //     79: ldc  #249 // 457619705
        //     81: ixor
        //     82: isub
        //     83: invokestatic  #558 // java.lang.Math.min:(II)I
        //     86: istore  5
        //     88: getstatic  #540 // dev.angelvisuals.a.aw.b:[F
        //     91: iload  5
        //     93: faload
        //     94: fstore  6
        //     96: fload_3
        //     97: ldc_w  #491 // 75.0f
        //    100: fcmpl
        //    101: iflt  109 (offset +8)
        //    104: fload  6
        //    106: goto  116 (offset +10)
        //    109: fload  4
        //    111: fload  6
        //    113: invokestatic  #555 // java.lang.Math.max:(FF)F
        //    116: fstore  7
        //    118: fload  7
        //    120: fload_3
        //    121: ldc_w  #491 // 75.0f
        //    124: fcmpl
        //    125: iflt  134 (offset +9)
        //    128: ldc_w  #427 // 1.5f
        //    131: goto  137 (offset +6)
        //    134: ldc_w  #428 // 1.6100000143051147f
        //    137: invokestatic  #555 // java.lang.Math.max:(FF)F
        //    140: freturn
    }

  private static float method1400(float arg0) { // было: g
        float __stk1;
        __stk1 = arg0 <= 180.0f ? arg0 : 360.0f - arg0;
        float var1 = __stk1;
        return var1 <= 90.0f ? var1 : 180.0f - var1;
    }

  private static float method1401(float arg0) { // было: h
        return Math.max(-90.0f, Math.min(90.0f, arg0));
    }

  public static class_243 method1402(class_1309 arg0, float arg1) { // было: b
        float var2 = Math.abs((arg0.method_36454() - 360.0f) % 360.0f);
        float var3 = arg0.method_36455();
        float var4 = Math.abs(var3);
        float var5 = arg1;
        float var6 = 0.0f;
        if (var4 < 30.0f) {
            if (var4 < 25.0f) {
                if (var4 >= 20.0f) {
                    if (var4 <= 60.0f) {
                        var6 = 0.05000000074505806f;
                    }
                }
            } else {
                if (var4 > 55.0f) {
                    if (var4 >= 20.0f) {
                        if (var4 <= 60.0f) {
                            var6 = 0.05000000074505806f;
                        }
                    }
                } else {
                    var6 = 0.10000000149011612f;
                }
            }
        } else {
            if (var4 > 50.0f) {
                if (var4 < 25.0f) {
                    if (var4 >= 20.0f) {
                        if (var4 <= 60.0f) {
                            var6 = 0.05000000074505806f;
                        }
                    }
                } else {
                    if (var4 > 55.0f) {
                        if (var4 >= 20.0f) {
                            if (var4 <= 60.0f) {
                                var6 = 0.05000000074505806f;
                            }
                        }
                    } else {
                        var6 = 0.10000000149011612f;
                    }
                }
            } else {
                var6 = 0.15000000596046448f;
            }
        }
        float var7 = var5 + var6;
        float[] __obj1 = new float[-852996421 ^ -852996417];
        __obj1[-2072898791 ^ -2072898791] = 45.0f;
        __obj1[-1380469678 ^ -1380469677] = 135.0f;
        __obj1[1344229654 ^ 1344229652] = 225.0f;
        __obj1[31053307 ^ 31053304] = 315.0f;
        float[] var8 = __obj1;
        float var9 = 9999.0f;
        float[] var10 = var8;
        int var11 = var10.length;
        int var12 = -585749943 ^ -585749943;
        while (var12 < var11) {
            float var13 = var10[var12];
            float var14 = Math.abs(var2 - var13);
            if (var14 < var9) {
                var9 = var14;
            }
            ++var12;
            continue;
        }
        if (var9 >= 15.0f) {
            if (var9 < 25.0f) {
                var7 = var7 + 0.05000000074505806f;
            }
        } else {
            var7 = var7 + 0.10000000149011612f;
        }
        var7 = Math.min(var7, 2.799999952316284f);
        return new class_243(((double) var7), ((double) var7), ((double) var7));
    }

  public static class_243 method1403(class_1309 arg0) { // было: b
        float var1 = Math.abs((arg0.method_36454() - 360.0f) % 360.0f);
        float var2 = arg0.method_36455();
        float var3 = Math.abs(var2);
        float var4 = 2.5f;
        float var5 = 2.299999952316284f;
        if (var3 < 35.0f) {
            if (var3 >= 30.0f) {
                if (var3 <= 55.0f) {
                    var4 = 2.5999999046325684f;
                    var5 = 2.4000000953674316f;
                }
            }
        } else {
            if (var3 > 50.0f) {
                if (var3 >= 30.0f) {
                    if (var3 <= 55.0f) {
                        var4 = 2.5999999046325684f;
                        var5 = 2.4000000953674316f;
                    }
                }
            } else {
                var4 = 2.700000047683716f;
                var5 = 2.5f;
            }
        }
        float[] __obj1 = new float[497484483 ^ 497484487];
        __obj1[439794855 ^ 439794855] = 45.0f;
        __obj1[692004656 ^ 692004657] = 135.0f;
        __obj1[-1187488126 ^ -1187488128] = 225.0f;
        __obj1[-1827371577 ^ -1827371580] = 315.0f;
        float[] var6 = __obj1;
        float var7 = 9999.0f;
        float[] var8 = var6;
        int var9 = var8.length;
        int var10 = 738973838 ^ 738973838;
        while (var10 < var9) {
            float var11 = var8[var10];
            float var12 = Math.abs(var1 - var11);
            if (var12 < var7) {
                var7 = var12;
            }
            ++var10;
            continue;
        }
        if (var7 < 20.0f) {
            var4 = var4 + 0.15000000596046448f;
        }
        return new class_243(((double) var4), ((double) var5), ((double) var4));
    }

  public static class_243 method1404(class_1309 arg0, float arg1, float arg2, float arg3) { // было: a
        float var4 = Math.abs(arg1);
        float var5 = arg3;
        float var6 = arg3;
        if (var4 < 38.0f) {
            if (var4 < 30.0f) {
                if (var4 < 25.0f) {
                    var5 = arg3 - 0.10000000149011612f;
                    var6 = arg3 - 0.15000000596046448f;
                } else {
                    if (var4 > 65.0f) {
                        var5 = arg3 - 0.10000000149011612f;
                        var6 = arg3 - 0.15000000596046448f;
                    } else {
                        var6 = arg3 - 0.05000000074505806f;
                    }
                }
            } else {
                if (var4 > 60.0f) {
                    if (var4 < 25.0f) {
                        var5 = arg3 - 0.10000000149011612f;
                        var6 = arg3 - 0.15000000596046448f;
                    } else {
                        if (var4 > 65.0f) {
                            var5 = arg3 - 0.10000000149011612f;
                            var6 = arg3 - 0.15000000596046448f;
                        } else {
                            var6 = arg3 - 0.05000000074505806f;
                        }
                    }
                } else {
                    var5 = Math.min(arg3 + 0.10000000149011612f, 2.5999999046325684f);
                    var6 = Math.min(arg3 + 0.10000000149011612f, 2.4000000953674316f);
                }
            }
        } else {
            if (var4 > 52.0f) {
                if (var4 < 30.0f) {
                    if (var4 < 25.0f) {
                        var5 = arg3 - 0.10000000149011612f;
                        var6 = arg3 - 0.15000000596046448f;
                    } else {
                        if (var4 > 65.0f) {
                            var5 = arg3 - 0.10000000149011612f;
                            var6 = arg3 - 0.15000000596046448f;
                        } else {
                            var6 = arg3 - 0.05000000074505806f;
                        }
                    }
                } else {
                    if (var4 > 60.0f) {
                        if (var4 < 25.0f) {
                            var5 = arg3 - 0.10000000149011612f;
                            var6 = arg3 - 0.15000000596046448f;
                        } else {
                            if (var4 > 65.0f) {
                                var5 = arg3 - 0.10000000149011612f;
                                var6 = arg3 - 0.15000000596046448f;
                            } else {
                                var6 = arg3 - 0.05000000074505806f;
                            }
                        }
                    } else {
                        var5 = Math.min(arg3 + 0.10000000149011612f, 2.5999999046325684f);
                        var6 = Math.min(arg3 + 0.10000000149011612f, 2.4000000953674316f);
                    }
                }
            } else {
                var5 = Math.min(arg3 + 0.20000000298023224f, 2.700000047683716f);
                var6 = Math.min(arg3 + 0.15000000596046448f, 2.5f);
            }
        }
        return new class_243(((double) var5), ((double) var6), ((double) var5));
    }

  public static class_243 method1405(class_1309 arg0) { // было: c
        return method1408(arg0, 42.0f);
    }

  public static class_243 method1406(class_1309 arg0) { // было: d
        return method1408(arg0, 39.0f);
    }

  public static class_243 method1407(class_1309 arg0) { // было: e
        return method1408(arg0, 33.20000076293945f);
    }

  public static class_243 method1408(class_1309 arg0, float arg1) { // было: c
        int __stk2;
        double __stk3;
        float var2 = arg1 / 20.0f;
        float var3 = Math.abs((arg0.method_36454() - 360.0f) % 360.0f);
        float var4 = arg0.method_36455();
        float var5 = Math.min(var2 * 0.699999988079071f, 1.6699999570846558f);
        float[] __obj1 = new float[-1606062964 ^ -1606062968];
        __obj1[2033545142 ^ 2033545142] = 45.0f;
        __obj1[-1856427047 ^ -1856427048] = 135.0f;
        __obj1[-1261927086 ^ -1261927088] = 225.0f;
        __obj1[631349714 ^ 631349713] = 315.0f;
        float[] var6 = __obj1;
        float var7 = 9999.0f;
        float[] var8 = var6;
        int var9 = var8.length;
        int var10 = 1164649979 ^ 1164649979;
        float var11;
        float var12;
        while (var10 < var9) {
            var11 = var8[var10];
            var12 = Math.abs(var3 - var11);
            if (var12 < var7) {
                var7 = var12;
            }
            ++var10;
            continue;
        }
        var8 = 1.0f - var7 / 45.0f;
        var8 = Math.max(0.0f, Math.min(1.0f, var8));
        var9 = method1410(var4);
        var10 = var8 * var9;
        float var11 = var5 + (var2 - var5) * var10;
        class_243 var12 = arg0.method_5720();
        class_243 var13 = class_243.method_1030(var4, arg0.method_36454()).method_1021(((double) var11));
        float var14 = var4 * 0.01745329238474369f;
        double var15 = Math.sqrt(var12.field_1352 * var12.field_1352 + var12.field_1350 * var12.field_1350);
        double var17 = var13.method_37267();
        __stk2 = arg0.method_18798().field_1351 > 0.0 ? -1060375988 ^ -1060375988 : 1313605079 ^ 1313605078;
        int var19 = __stk2;
        __stk3 = var19 == 0 ? arg0.method_56989() : !arg0.method_6059(class_1294.field_5906) ? arg0.method_56989() : Math.min(arg0.method_56989(), 0.01);
        double var20 = __stk3;
        double var22 = class_3532.method_33723(Math.cos(((double) var14)));
        var13 = var13.method_1031(0.0, var20 * (-1.0 + var22 * 0.75), 0.0);
        if (var13.field_1351 < 0.0) {
            if (var15 > 0.0) {
                double var24 = var13.field_1351 * -0.1 * var22;
                var13 = var13.method_1031(var12.field_1352 * var24 / var15, var24, var12.field_1350 * var24 / var15);
            }
        }
        if (var14 < 0.0f) {
            if (var15 > 0.0) {
                double var24 = var17 * ((double) -class_3532.method_15374(var14)) * 0.04;
                var13 = var13.method_1031(-var12.field_1352 * var24 / var15, var24 * 3.2, -var12.field_1350 * var24 / var15);
            }
        }
        if (var15 > 0.0) {
            var13 = var13.method_1031((var12.field_1352 / var15 * var17 - var13.field_1352) * 0.1, 0.0, (var12.field_1350 / var15 * var17 - var13.field_1350) * 0.1);
        }
        double var26 = var13.method_1033();
        return new class_243(var26, var26, var26).method_18805(0.99, 0.98, 0.99);
    }

  public static class_243 method1409(class_1309 arg0, float arg1) { // было: d
        float var2 = arg1 / 20.0f;
        return new class_243(((double) var2), ((double) var2), ((double) var2)).method_18805(0.99, 0.98, 0.99);
    }

  private static float method1410(float arg0) { // было: i
        float var1 = Math.abs(arg0);
        if (var1 > 5.0f) {
            if (var1 > 15.0f) {
                if (var1 > 25.0f) {
                    if (var1 > 35.0f) {
                        if (var1 > 45.0f) {
                            if (var1 > 55.0f) {
                                if (var1 > 65.0f) {
                                    if (var1 > 75.0f) {
                                        return 0.25f;
                                    } else {
                                        return 0.3499999940395355f;
                                    }
                                } else {
                                    return 0.44999998807907104f;
                                }
                            } else {
                                return 0.550000011920929f;
                            }
                        } else {
                            return 0.6499999761581421f;
                        }
                    } else {
                        return 0.75f;
                    }
                } else {
                    return 0.8500000238418579f;
                }
            } else {
                return 0.949999988079071f;
            }
        } else {
            return 1.0f;
        }
    }

    @Generated
  private aw() { // было: <init>
        super();
        throw new UnsupportedOperationException(Decryptor.method1945(XorDecoder.method1946("ýI^±¸÷GöÊVøóv¨ùJ½øÆa¥ät²Ä5ýTé®Åm¬¿M±úBÈC¬îGìúÔeì×e«ô8ùØaòÌDêúÈzûý1ä", 2034232690 ^ -1607249478)));
    }

  private static int dG(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int dH(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int dI(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}