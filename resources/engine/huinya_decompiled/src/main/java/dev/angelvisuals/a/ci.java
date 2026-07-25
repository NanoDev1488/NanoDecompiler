// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.cI
package dev.angelvisuals.a;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import dev.angelvisuals.a.ClassA146;
import dev.angelvisuals.a.aP;
import dev.angelvisuals.a.du;
import lombok.Generated;
import net.minecraft.class_3532;

public class cI implements ClassA146 {

    // ---- поля ----
  private static boolean ag;
  private static float cT;
  private static float cU;
  private static final String wX = "// every class watermarked, every string encrypted, every number xored";
  private static final String wY = "// === DO NOT TOUCH ===";
  private static final String wZ = "// flow obfuscation: ENABLED";
  private static final String xa = "// this jar protected by JoinerObfuscator";
  private static final String xb = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final int nC = -1897364627;
  private static final int nD = -406109250;
  private static final int nE = -1366542981;
  private static final byte[] dr;

    static {
        dr = "#}X;#4X@Nin#k4-R@jV0sPzQ~Lh)|?6;ujOjX@%0.&P,Pn\\!NfJ|Kq-*i3o5t9imr|nRGubH}w\\bQ$))RHIFYC\\PaIjvt+z(bXVtL%b]oyqkbzA4>HcwJ'0&6E)A{\\=w5S; +g_R;+%T]Z[xy0 jj:<w\"))y'Q*bIf+O|z\\Ks9?2rza~yifR*!@R7NUUFR'h|qei-G*LC)#;>t=1Ta(c|\"[yRX2&!(0DSAOX@&9y?L:/Y= 0xbf@]_7l*,D;RvfM".getBytes("ISO-8859-1");
    }

  public cI() { // было: <init>
        super();
        EventManager.register(this);
    }

    @EventTarget
  public void method1093(du arg0) { // было: a
        if (ag) {
            method1095(arg0.method276(), arg0.method277());
            arg0.y();
        }
    }

    @EventTarget
  public void method1094(aP arg0) { // было: a
        if (!ag) {
            cT = arg0.method289();
            cU = arg0.method290();
        } else {
            arg0.method292(cT);
            arg0.method293(cU);
        }
    }

  private void method1095(double arg0, double arg1) { // было: a
        cU = class_3532.method_15363(((float) (((double) cU) + arg1 * 0.15)), -90.0f, 90.0f);
        cT = ((float) (((double) cT) + arg0 * 0.15));
    }

    @Generated
  public static void method1096(boolean arg0) { // было: o
        ag = arg0;
    }

    @Generated
  public static float ba() {
        return cT;
    }

    @Generated
  public static float bb() {
        return cU;
    }

    @Generated
  public static void method1097(float arg0) { // было: X
        cT = arg0;
    }

    @Generated
  public static void method1098(float arg0) { // было: Y
        cU = arg0;
    }

  private static int lW(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int lX(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int lY(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}