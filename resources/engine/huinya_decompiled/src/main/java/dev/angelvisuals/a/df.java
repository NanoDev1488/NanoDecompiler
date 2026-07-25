// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.dF
package dev.angelvisuals.a;

import com.darkmagician6.eventapi.EventTarget;
import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.a.ak;
import dev.angelvisuals.a.bA;
import dev.angelvisuals.a.bI;
import dev.angelvisuals.a.cK;
import net.minecraft.class_1109;
import net.minecraft.class_1144;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3414;

@bI(name = "ClientSounds", a = "RENDER", I = "Звуки при включении/выключении модулей")
public class dF extends cK {

    // ---- поля ----
  public static final dF field387; // было: a
  private static final float dM = 1.350000023841858f;
  private static final class_3414 field388; // было: a
  private static final class_3414 field389; // было: b
  private final bA ag;
  private final bA ah;
  private static final String Fn = "// Joiner sees you";
  private static final String Fo = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final String Fp = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final String Fq = "// class hierarchy hashing: ENABLED";
  private static final String Fr = "// stop. seriously. go play minecraft instead";
  private static final int sw = 1189180929;
  private static final int sx = 20494726;
  private static final int sy = -1916982045;
  private static final byte[] eR;

    static {
        eR = "6.4\\gjCWl1^e}4F)dN^h?{*5oSWT#!ET'%73`s/~t]F:\\[A$8iL$e`$^vdE{,-_eH=EYo1{e =[6RMkOiu7 [z@`0$HKCfxB'LR{;4(Ar,E^lD@K(|s|srb<#nz3hLmqb/B~m@c}bI#'4So6#PfY}j&BQER$HCmp1&KJ^1}.]WVoCz}j%,1}8)c2pN-C} zu`<w8pR+~wl((c$8KYB-fd6CKV@VlRr/0tVh~CRPoBb5enyOoH#ZY1*!.dTE;sjG4".getBytes("ISO-8859-1");
        field387 = new dF();
        field388 = class_3414.method_47908(class_2960.method_60655(Decryptor.method1945(XorDecoder.method1946("*l\\\u0016\\&?[½%\u000f\u0001\u0004/l]NOíP", 63182605 ^ 1846816624)), Decryptor.method1945(XorDecoder.method1946("zÓNßf¶-Ê^ÝN³iÐ0ÎbÓ\u001eÎhÔGº", 2054571773 ^ -49495054))));
        field389 = class_3414.method_47908(class_2960.method_60655(Decryptor.method1945(XorDecoder.method1946("ÎÏ¹òÿ¨ôÛø÷ë¢·ÖËÏ»ªìÐ", 2062797210 ^ -987868669)), Decryptor.method1945(XorDecoder.method1946("¨\u0018Êa'wî\u007f2²\u0018ýEª0ñg\u0005=", -1319163738 ^ -1310229405))));
    }

  public dF() { // было: <init>
        super();
        ag = new bA(Decryptor.method1945(XorDecoder.method1946("ø´ÁÛÉ¯§¨¼ûá Î¦ùø½Ú×¼ºçæ»¡ùã¹ÊÉ¤Ë", -1396947757 ^ 1517017403)), 1.7999999523162842f, 0.10000000149011612f, 4.0f, 0.10000000149011612f);
        ah = new bA(Decryptor.method1945(XorDecoder.method1946("gµi©MÝ\"±N¬hxÙS¹Eµn\u0005¼'Ö", 244322602 ^ -443876322)), 1.0f, 0.5f, 2.0f, 0.10000000149011612f);
    }

    @EventTarget
  private void method767(ak arg0) { // было: c
        class_3414 __stk1;
        if (mc.field_1724 == null) {
            return;
        } else {
            if (mc.field_1687 != null) {
                if (arg0.method266() != this) {
                    __stk1 = !arg0.method267() ? field389 : field388;
                    class_3414 var2 = __stk1;
                    float var3 = Math.min(4.0f, ag.bp() * 1.350000023841858f);
                    mc.method_1483().method_4873(class_1109.method_4757(((class_3414) var2), ah.bp(), var3));
                    return;
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

  private static int qx(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int qy(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int qz(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}