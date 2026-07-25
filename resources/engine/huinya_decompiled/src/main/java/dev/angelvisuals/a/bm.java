// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.bM
package dev.angelvisuals.a;

import dev.angelvisuals.a.an_ClassA34;
import dev.angelvisuals.a.an_ClassA35;
import dev.angelvisuals.a.bp;
import dev.angelvisuals.a.dp;
import net.minecraft.class_4588;
import org.joml.Matrix4f;

public final class bM {

    // ---- поля ----
  private final int jk;
  private final float bW;
  private final float bX;
  private final float bY;
  private final float bZ;
  private final float ca;
  private final float cb;
  private final float cc;
  private final float cd;
  private static final String pJ = "// number obfuscation: ENABLED (XOR masking)";
  private static final String pK = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String pL = "// Joiner sees you";
  private static final String pM = "// this jar protected by JoinerObfuscator";
  private static final String pN = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final int jl = -345448308;
  private static final int jm = 1137073184;
  private static final int jn = -2129895693;
  private static final byte[] cc;

    static {
        cc = ".#Q\\+Laom8!om<n_Km(,d:9co;ac|$nPuK6tn@V*,;{|l>En~3A[WzS04~nRfN'H,5Z~!/(=t>x{y~,=$$;\"H(d`n26EGXn*i+&F$?.CO}.Xf\"X6jYA{$)\"\\h#-EkzFWJf^zc `sC;xG/J1j-t8e}JWM8UyjoEGZfH<8tu0)kBUz?eQpD?4Yt'( &C6'U/y.ZJyiB&UTZ>kOo}Sh+:K%VT??kX#-uzi^,\"P2_?sv=Y$0wT_jt6eTY\\5S;TC5#|cj".getBytes("ISO-8859-1");
    }

  public bM(an_ClassA35 arg0, float arg1, float arg2) { // было: <init>
        super();
        jk = arg0.method353();
        ca = arg0.ax();
        an_ClassA34 var4 = arg0.method355();
        if (var4 == null) {
            bZ = 0.0f;
            bY = 0.0f;
            bX = 0.0f;
            bW = 0.0f;
        } else {
            bW = var4.aS() / arg1;
            bX = var4.aU() / arg1;
            bY = 1.0f - var4.aT() / arg2;
            bZ = 1.0f - var4.aV() / arg2;
        }
        an_ClassA34 var5 = arg0.method354();
        if (var5 == null) {
            cb = 0.0f;
            cd = 0.0f;
            cc = 0.0f;
        } else {
            cc = var5.aU() - var5.aS();
            cd = var5.aT() - var5.aV();
            cb = var5.aT();
        }
    }

  public float method387(Matrix4f arg0, class_4588 arg1, float arg2, float arg3, float arg4, float arg5, int arg6) { // было: a
        arg4 = arg4 - cb * arg2;
        float var8 = cc * arg2;
        float var9 = cd * arg2;
        arg1.method_22918(arg0, arg3, arg4, arg5).method_22913(bW, bY).method_39415(arg6);
        arg1.method_22918(arg0, arg3, arg4 + var9, arg5).method_22913(bW, bZ).method_39415(arg6);
        arg1.method_22918(arg0, arg3 + var8, arg4 + var9, arg5).method_22913(bX, bZ).method_39415(arg6);
        arg1.method_22918(arg0, arg3 + var8, arg4, arg5).method_22913(bX, bY).method_39415(arg6);
        return ca * arg2;
    }

  public float method388(Matrix4f arg0, class_4588 arg1, float arg2, float arg3, float arg4, float arg5, dp arg6) { // было: a
        arg4 = arg4 - cb * arg2;
        float var8 = cc * arg2;
        float var9 = cd * arg2;
        arg1.method_22918(arg0, arg3, arg4, arg5).method_22913(bW, bY).method_39415(arg6.method1661().method1680());
        arg1.method_22918(arg0, arg3, arg4 + var9, arg5).method_22913(bW, bZ).method_39415(arg6.method1662().method1680());
        arg1.method_22918(arg0, arg3 + var8, arg4 + var9, arg5).method_22913(bX, bZ).method_39415(arg6.method1664().method1680());
        arg1.method_22918(arg0, arg3 + var8, arg4, arg5).method_22913(bX, bY).method_39415(arg6.method1663().method1680());
        return ca * arg2;
    }

  public float method389(float arg0) { // было: j
        return ca * arg0;
    }

  public int method390() { // было: N
        return jk;
    }

  private static int id(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int ie(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int if(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}