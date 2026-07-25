// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.ap
package dev.angelvisuals.a;

import dev.angelvisuals.a.ClassA163;
import dev.angelvisuals.a.ClassA40;
import dev.angelvisuals.a.aE;
import dev.angelvisuals.a.aY;
import dev.angelvisuals.a.ar;
import dev.angelvisuals.a.bp;
import dev.angelvisuals.a.cF;
import dev.angelvisuals.a.dp;
import dev.angelvisuals.utility.mixin.accessors.DrawContextAccessor;
import java.util.Objects;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import net.minecraft.class_4587.class_4665;
import net.minecraft.class_4597.class_4598;

public class ap extends class_332 implements cF {

    // ---- поля ----
  private static final String gr = "// === DO NOT TOUCH ===";
  private static final String gs = "// flow obfuscation: ENABLED";
  private static final String gt = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final String gu = "// good luck with the next 9999 classes";
  private static final String gv = "// flow obfuscation: ENABLED";
  private static final int dE = -1259974127;
  private static final int dF = 709930325;
  private static final int dG = 86178640;
  private static final byte[] at;

    static {
        at = "KI ;m1jq&u(^IR}\"=OWS~R0w\"s%)dXb/uTm-g?!Eq jxZhL4o$n !>}(ouEMR:26U9PV(OQ-qRWT}${eUmW1+r`MhdHZ+s<BvQxlOiilVp6]\"bASprlHlx))O_ro!9GW/@/Y^S1^}vp;8f[$>[?H-EHIou^cL7y$ 5'1BB|^yt?wL@/rFRN{RHJ'*\\Nr~B%0Ej%{A-O\"hr$9#un:6|qG%LM~2afj)T*$e}1DKua~0Bx' EpH'M*UQdD\\X&Kig@1F".getBytes("ISO-8859-1");
    }

  public ap(class_4598 arg0) { // было: <init>
        super(mc, arg0);
    }

  public ap(class_332 arg0) { // было: <init>
        super(mc, (((DrawContextAccessor) arg0)).getVertexConsumers());
    }

  public static ap method1637(class_332 arg0) { // было: a
        return new ap(arg0);
    }

  public void method1638(ar arg0, String arg1, float arg2, float arg3, bp arg4) { // было: a
        ClassA40.method391(arg0.method351(), arg1, arg0.method352(), arg4.method1680(), method_51448().method_23760().method_23761(), arg2, arg3, 0.0f);
    }

  public void method1639(ar arg0, String arg1, float arg2, float arg3, dp arg4) { // было: a
        ClassA40.method399(arg0.method351(), arg1, arg0.method352(), arg4, method_51448().method_23760().method_23761(), arg2, arg3, 0.0f);
    }

  public void method1640(ar arg0, class_2561 arg1, float arg2, float arg3) { // было: a
        ClassA40.method394(arg0.method351(), arg1, arg0.method352(), method_51448().method_23760().method_23761(), arg2, arg3, 0.0f);
    }

  public void method1641(ar arg0, class_2561 arg1, float arg2, float arg3, float arg4) { // было: a
        ClassA40.method395(arg0.method351(), arg1, arg0.method352(), method_51448().method_23760().method_23761(), arg2, arg3, 0.0f, ((int) arg4));
    }

  public void method1642(float arg0, float arg1, float arg2, float arg3, float arg4, aY arg5, bp arg6) { // было: a
        aE.method1740(method_51448(), arg0, arg1, arg2, arg3, arg4, arg5, arg6);
    }

  public void method1643(float arg0, float arg1, float arg2, float arg3, aY arg4, bp arg5) { // было: a
        aE.method1742(method_51448(), arg0, arg1, arg2, arg3, arg4, arg5);
    }

  public void method1644(float arg0, float arg1, float arg2, float arg3, aY arg4, dp arg5) { // было: a
        aE.method1744(method_51448(), arg0, arg1, arg2, arg3, arg4, arg5);
    }

  public void method1645(float arg0, float arg1, float arg2, float arg3, bp arg4) { // было: a
        aE.method1739(method_51448(), arg0, arg1, arg2, arg3, arg4);
    }

  public int method1646(class_327 arg0, class_2561 arg1, int arg2, int arg3, int arg4, aY arg5, bp arg6, bp arg7) { // было: a
        int var9 = arg2 - (-659136741 ^ -659136744);
        int var10 = arg3 - (1860933429 ^ 1860933431);
        int var11 = arg4 + (92096746 ^ 92096748);
        Objects.requireNonNull(arg0);
        method1643(((float) var9), ((float) var10), ((float) var11), 13.0f, arg5, arg7);
        return method_51439(arg0, arg1, arg2, arg3, arg6.method1680(), 400034914 ^ 400034915);
    }

  public void method1647(ClassA163 arg0, float arg1, float arg2, float arg3, float arg4, bp arg5) { // было: a
        aE.method1752(method_51448(), arg0, arg1, arg2, arg3, arg4, arg5);
    }

  public void method1648(float arg0, float arg1, float arg2, float arg3, float arg4, float arg5, bp arg6, aY arg7) { // было: a
        arg2 = ((float) Math.round(arg2));
        arg3 = ((float) Math.round(arg3));
        method_44379(((int) Math.ceil(((double) (arg0 - 10.0f)))), ((int) (arg1 - 10.0f)), ((int) (arg0 + arg5)), ((int) (arg1 + arg5)));
        method1649(arg0, arg1, arg2, arg3, arg4, arg7, arg6);
        method_44380();
        method_44379(((int) (arg0 + arg2 - arg5)), ((int) (arg1 - 10.0f)), ((int) (arg0 + arg2 + 10.0f)), ((int) (arg1 + arg5)));
        method1649(arg0, arg1, arg2, arg3, arg4, arg7, arg6);
        method_44380();
        method_44379(((int) (arg0 - 10.0f)), ((int) (arg1 + arg3 - arg5)), ((int) (arg0 + arg5)), ((int) (arg1 + arg3 + 10.0f)));
        method1649(arg0, arg1, arg2, arg3, arg4, arg7, arg6);
        method_44380();
        method_44379(((int) (arg0 + arg2 - arg5)), ((int) (arg1 + arg3 - arg5)), ((int) (arg0 + arg2 + 10.0f)), ((int) (arg1 + arg3 + 10.0f)));
        method1649(arg0, arg1, arg2, arg3, arg4, arg7, arg6);
        method_44380();
    }

  public void method1649(float arg0, float arg1, float arg2, float arg3, float arg4, aY arg5, bp arg6) { // было: b
        aE.method1745(method_51448(), arg0, arg1, arg2, arg3, arg4, arg5, arg6);
    }

  public void method1650(class_2960 arg0, float arg1, float arg2, float arg3, float arg4, bp arg5) { // было: a
        aE.method1749(method_51448(), arg0, arg1, arg2, arg3, arg4, arg5);
    }

  public void method1651() { // было: z
        method_51448().method_22903();
    }

  public void method1652() { // было: A
        method_51448().method_22909();
    }

  private static int df(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int dg(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int dh(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}