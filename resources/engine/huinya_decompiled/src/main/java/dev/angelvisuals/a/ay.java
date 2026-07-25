// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.aY
package dev.angelvisuals.a;

import org.jetbrains.annotations.NotNull;

public final class aY extends Record {

    // ---- поля ----
  private final float bp;
  private final float bq;
  private final float br;
  private final float bs;
  public static final aY field897; // было: b
  private static final String kE = "// good luck with the next 9999 classes";
  private static final String kF = "// number obfuscation: ENABLED (XOR masking)";
  private static final String kG = "// reverse-engineering this jar is a waste of time, friend";
  private static final String kH = "// flow obfuscation: ENABLED";
  private static final String kI = "// this jar protected by JoinerObfuscator";
  private static final int gh = -218002550;
  private static final int gi = -1058095892;
  private static final int gj = -1462232924;
  private static final byte[] bi;

    static {
        bi = "[N_X$O?Z!?0aWAlCk?]qY(C=HjdO`'C8Ph+6qNyaVAswNsrOwu'V0I_=Ch snoA;L|%d6XE_hDjrFrH\\[4}E5p1`t@23<c%M0NE-dacP6K`7\"%S/D*Pw|y||]@S6_)nNd,`&ahZ;+~SmcgMf@,01DSOxwat58[kG>&^:r4.w'..zGy:bxpwXW%AYzmbPR#%-}qyF[n1#$?21Z`nq0* {w0lAm0;z$dBz$;vkVJ-P7)jb-#R9!Zw8p'7 JMI=*.Ru".getBytes("ISO-8859-1");
        field897 = new aY(0.0f, 0.0f, 0.0f, 0.0f);
    }

  public aY(float arg0, float arg1, float arg2, float arg3) { // было: <init>
        super();
        bp = arg0;
        bq = arg1;
        br = arg2;
        bs = arg3;
    }

  public static aY method1597(float arg0) { // было: a
        return new aY(arg0, arg0, arg0, arg0);
    }

  public static aY method1598(float arg0) { // было: b
        return new aY(arg0, 0.0f, 0.0f, 0.0f);
    }

  public static aY method1599(float arg0) { // было: c
        return new aY(0.0f, arg0, 0.0f, 0.0f);
    }

  public static aY method1600(float arg0) { // было: d
        return new aY(0.0f, 0.0f, arg0, 0.0f);
    }

  public static aY method1601(float arg0) { // было: e
        return new aY(0.0f, 0.0f, 0.0f, arg0);
    }

  public static aY method1602(float arg0, float arg1) { // было: a
        return new aY(arg0, arg1, 0.0f, 0.0f);
    }

  public static aY method1603(float arg0, float arg1) { // было: b
        return new aY(0.0f, 0.0f, arg1, arg0);
    }

  public static aY method1604(float arg0, float arg1) { // было: c
        return new aY(arg0, 0.0f, 0.0f, arg1);
    }

  public static aY method1605(float arg0, float arg1) { // было: d
        return new aY(0.0f, arg0, arg1, 0.0f);
    }

    @NotNull
  public String toString() {
        return "BorderRadius{topLeftRadius=" + bp + ", topRightRadius=" + bq + ", bottomRightRadius=" + br + ", bottomLeftRadius=" + bs + "}";
    }

  public float method1606() { // было: Y
        return bp;
    }

  public float method1607() { // было: Z
        return bq;
    }

  public float aa() {
        return br;
    }

  public float ab() {
        return bs;
    }

  public final int method1608() { // было: z
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokedynamic  #33 // invokedynamic hashCode:(Ldev/angelvisuals/a/aY;)I
        //      6: ireturn
    }

  public final boolean method1609(Object arg0) { // было: k
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: aload_1
        //      2: invokedynamic  #32 // invokedynamic equals:(Ldev/angelvisuals/a/aY;Ljava/lang/Object;)Z
        //      7: ireturn
    }

  private static int fy(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int fz(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int fA(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}