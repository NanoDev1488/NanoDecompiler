// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.k
package dev.angelvisuals.a;

import dev.angelvisuals.a.aH;
import lombok.Generated;

public class ClassA2 {

    // ---- поля ----
  private long field1; // было: e
  private float field2; // было: f
  private aH field3; // было: a
  private long field4; // было: f
  private float field5; // было: g
  private float field6; // было: h
  private boolean field7; // было: c
  private boolean field8; // было: d
  private static final String field9 = "// string encryption: ENABLED (AES-128/ECB + XOR)"; // было: Z
  private static final String aa = "// nice try. closed source for a reason.";
  private static final String ab = "// number obfuscation: ENABLED (XOR masking)";
  private static final String ac = "// Joiner sees you";
  private static final String ad = "// reverse-engineering this jar is a waste of time, friend";
  private static final int field10 = -530826856; // было: L
  private static final int field11 = 1108568731; // было: M
  private static final int field12 = -1087775993; // было: N
  private static final byte[] field13; // было: k

    static {
        field13 = "=Z7&]o4>uDmdM@T_+S@\"!o\\843XWX$nGI@xu%27qGR^?PS?*NaQF2%:bmm}H76@u@'}'yL2-7&6RAR[I1yF0Va|=)i*k@p7,:L[tUk@5q9BG:V?+jQ!|}A]Q`=mX[`uxM`-;#<rsgKh>a]B?li6e2?~O7S,?j8!>o_jvv2;RZ%P08RZ\\oLzy0ruuczcKP5v|!qal7\\zn4G$+OJvEAU[!sNnPp9U8LagH5*/)Za~@p5t*M^z4+NKh_l<aHR?&IsKY".getBytes("ISO-8859-1");
    }

  public ClassA2(long arg0, float arg1, aH arg2) { // было: <init>
        super();
        field1 = arg0;
        field3 = arg2;
        field2 = arg1;
        field5 = arg1;
        field6 = arg1;
        field7 = -436484707 ^ -436484708;
    }

  public ClassA2(long arg0, aH arg1) { // было: <init>
        this(arg0, 0.0f, arg1);
    }

  public void method5(boolean arg0) { // было: a
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: iload_1
        //      2: ifeq  9 (offset +7)
        //      5: fconst_1
        //      6: goto  10 (offset +4)
        //      9: fconst_0
        //     10: invokevirtual  #36 // dev.angelvisuals.a.k.a:(F)F
        //     13: pop
        //     14: return
    }

  public float method6(float arg0) { // было: a
        long var2 = System.currentTimeMillis();
        if (arg0 != field6) {
            field5 = field2;
            field6 = arg0;
            field4 = var2;
            field7 = -1108886739 ^ -1108886739;
        }
        long var4 = var2 - field4;
        if (var4 < field1) {
            float var6 = ((float) var4) / ((float) field1);
            float var7 = field3.IIlIll1IO0Illl(var6, 0.0f, 1.0f, 1.0f);
            field2 = field5 + (field6 - field5) * var7;
            return field2;
        } else {
            field2 = field6;
            field7 = 974617005 ^ 974617004;
            return field2;
        }
    }

  public void method7(float arg0) { // было: b
        field2 = arg0;
        field5 = arg0;
        field6 = arg0;
        field7 = -1476954488 ^ -1476954487;
    }

  public void method8(float arg0) { // было: c
        field2 = arg0;
        field5 = arg0;
        field6 = arg0;
        field7 = 1128576921 ^ 1128576920;
    }

  public void method9() { // было: d
        method8(0.0f);
    }

  public void method10(float arg0) { // было: d
        if (arg0 != field6) {
            field5 = field2;
            field6 = arg0;
            field4 = System.currentTimeMillis();
            field7 = -2134683447 ^ -2134683447;
        }
    }

  public float method11() { // было: d
        return method6(field6);
    }

    @Generated
  public long method12() { // было: b
        return field1;
    }

    @Generated
  public float method13() { // было: e
        return field2;
    }

    @Generated
  public aH method14() { // было: a
        return field3;
    }

    @Generated
  public long method15() { // было: c
        return field4;
    }

    @Generated
  public float method16() { // было: f
        return field5;
    }

    @Generated
  public float method17() { // было: g
        return field6;
    }

    @Generated
  public boolean method18() { // было: k
        return field7;
    }

    @Generated
  public boolean method19() { // было: l
        return field8;
    }

    @Generated
  public void method20(long arg0) { // было: a
        field1 = arg0;
    }

    @Generated
  public void method21(aH arg0) { // было: a
        field3 = arg0;
    }

    @Generated
  public void method22(long arg0) { // было: b
        field4 = arg0;
    }

    @Generated
  public void method23(float arg0) { // было: e
        field5 = arg0;
    }

    @Generated
  public void method24(float arg0) { // было: f
        field6 = arg0;
    }

    @Generated
  public void method25(boolean arg0) { // было: b
        field7 = arg0;
    }

    @Generated
  public void method26(boolean arg0) { // было: c
        field8 = arg0;
    }

  private static int method27(int arg0, int arg1) { // было: E
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int method28(int arg0, int arg1) { // было: F
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int method29(int arg0, int arg1) { // было: G
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}