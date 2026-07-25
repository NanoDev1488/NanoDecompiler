// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.bv
package dev.angelvisuals.a;

import dev.angelvisuals.a.ClassA139;
import dev.angelvisuals.a.bZ;
import dev.angelvisuals.a.cF;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_437;
import ru.nexusguard.protection.annotations.Native;

public abstract class bv extends class_437 implements cF {

    // ---- поля ----
  private static final String nD = "// reverse-engineering this jar is a waste of time, friend";
  private static final String nE = "// this jar protected by JoinerObfuscator";
  private static final String nF = "// number obfuscation: ENABLED (XOR masking)";
  private static final String nG = "Protected by t.me/JoinerClient";
  private static final String nH = "// nice try. closed source for a reason.";
  private static final int if = 186309845;
  private static final int ig = -1688226452;
  private static final int ih = 997565846;
  private static final byte[] bK;

    static {
        bK = "\\K@UJPV}a@fEkc&GDm_*>8.m0Pw|:7`G%T(C8{M87XW`wF!o2SP$L8T-w7)e0`-1fCN(^cHh)~nCFfK{cW&1S^Ki5p_`@}_++CTH@;/DH\"F~0GG.gg:c.0xz?xPa{n_!xwM= H;M\"393R|AR#Y3bIl+.z#b/Wldo'83s++@w!X1+\\ Z.<H>:IJ2ET7~6LEnM#rLQ,BcnM5,@jFPt\\&iJ]Kk('o){E`.Ql+|ccn4Fw.k*3oY3,J,^!]dh2v+sY!ou".getBytes("ISO-8859-1");
    }

  protected bv() { // было: <init>
        super(class_2561.method_43473());
    }

  public abstract void method1203(bZ arg0, float arg1, float arg2); // было: a

    @Native
  public final void method1204(class_332 arg0, int arg1, int arg2, float arg3) { // было: b
        bZ var5 = bZ.method1677(arg0, arg1, arg2, arg3);
        method1203(var5, ((float) arg1), ((float) arg2));
        super.method_25394(arg0, arg1, arg2, arg3);
    }

  public final boolean method1205(double arg0, double arg1, int arg2) { // было: d
        ClassA139 var6 = ClassA139.method1175(arg2);
        method1208(arg0, arg1, var6);
        return super.method_25402(arg0, arg1, arg2);
    }

  public void ah() {
        // (пустое тело)
    }

  public final boolean method1206(double arg0, double arg1, int arg2) { // было: e
        ClassA139 var6 = ClassA139.method1175(arg2);
        method1209(arg0, arg1, var6);
        return super.method_25406(arg0, arg1, arg2);
    }

  public final boolean method1207(double arg0, double arg1, int arg2, double arg3, double arg4) { // было: b
        ClassA139 var10 = ClassA139.method1175(arg2);
        method1210(arg0, arg1, var10, arg3, arg4);
        return super.method_25403(arg0, arg1, arg2, arg3, arg4);
    }

  public void method1208(double arg0, double arg1, ClassA139 arg2) { // было: a
        // (пустое тело)
    }

  public void method1209(double arg0, double arg1, ClassA139 arg2) { // было: b
        // (пустое тело)
    }

  public void method1210(double arg0, double arg1, ClassA139 arg2, double arg3, double arg4) { // было: a
        // (пустое тело)
    }

  private static int he(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int hf(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int hg(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}