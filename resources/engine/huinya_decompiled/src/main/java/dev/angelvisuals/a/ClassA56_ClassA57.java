// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.r.c
package dev.angelvisuals.a;

import dev.angelvisuals.a.ClassA2;
import dev.angelvisuals.a.ClassA54_ClassA55;
import dev.angelvisuals.a.ClassA64;
import dev.angelvisuals.a.aE;
import dev.angelvisuals.a.aY;
import dev.angelvisuals.a.ap;
import dev.angelvisuals.a.ar;
import dev.angelvisuals.a.bc;
import dev.angelvisuals.a.bl;
import dev.angelvisuals.a.bp;
import dev.angelvisuals.a.ci;

class ClassA56_ClassA57 extends ClassA54_ClassA55 {

    // ---- поля ----
  final String fg;
  final String fh;
  private static final String fi = "// class hierarchy hashing: ENABLED";
  private static final String fj = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final String fk = "// nice try. closed source for a reason.";
  private static final String fl = "// every class watermarked, every string encrypted, every number xored";
  private static final String fm = "// nice try. closed source for a reason.";
  private static final int cV = -1242345090;
  private static final int cW = 2047028005;
  private static final int cX = 586414629;
  private static final byte[] ai;

    static {
        ai = "l&ny~5Y#&Kc7-\\(6x>(FlF[tFpkk]L@ ezi~X4a%S32'l2e5o_8f;lC\"z2D-g)hT_F]\\'~@MAv!}[oBANTZRk`/#7S:V_<9p[_8tqmq\" |K\\p@lzAzal.Zd-Vkrz\"RRm\"'>SSF=>uGk3J.hhINK7x\\!%w/7Fxv[eJ|\"SW~RWi1@Z-Q[47m#xK/L,w94+e$Oa9gp6uA$#<U{!s!h.$K=bh<t/X*D\"IlcJjzfDa/I8_69Dm!G^WFeb;n\\,~b1Emcg}".getBytes("ISO-8859-1");
    }

   ClassA56_ClassA57(String arg0, String arg1) { // было: <init>
        super();
        fg = arg0;
        fh = arg1;
    }

   void method517(ap arg0, float arg1, float arg2, ar arg3, bl arg4, float arg5, ClassA64 arg6) { // было: a
        if (o == (-4871196048094893656L ^ -4871196048094893656L)) {
            o = System.currentTimeMillis();
        }
        float var8 = 16.0f;
        float var9 = j.method13();
        bp var10 = arg4.method449().method1685(var9 * 255.0f);
        bp var11 = bp.field909.method1685(var9 * 255.0f);
        String var12 = " " + fg;
        float var13 = arg3.method349(var12);
        float var14 = var8 + 4.0f + var13 + 10.0f;
        arg1 = arg1 - (var14 / 2.0f - 1.0f);
        aE.method1759(arg0.method_51448(), arg1 - 1.0f, arg2, var14 - 3.0f, arg5, 15.0f, aY.method1597(5.0f), bp.field909.method1685(var9 * 255.0f));
        aE.method1742(arg0.method_51448(), arg1 - 1.0f, arg2, 17.0f, arg5, aY.method1604(5.0f, 5.0f), new bp(846022855 ^ 846022855, 219368206 ^ 219368206, 590497401 ^ 590497401, var9 * 255.0f));
        aE.method1742(arg0.method_51448(), arg1 + 16.0f, arg2, var14 - 20.0f, arg5, aY.method1605(5.0f, 5.0f), new bp(1742688844 ^ 1742688844, 572877735 ^ 572877735, 589133643 ^ 589133643, var9 * 125.0f));
        ar var15 = bc.field181.method383(8.5f);
        float var16 = arg1 - 1.0f + (17.0f - var15.method349(fh)) / 2.0f;
        float var17 = arg2 + (arg5 - var15.method348()) / 2.0f;
        arg0.method1638(var15, fh, var16, var17, var10);
        float var18 = arg1 + var8 + 8.0f;
        float var19 = arg2 + (arg5 - arg3.method348()) / 2.0f;
        arg0.method1638(arg3, var12, var18 - 4.0f, var19, var11);
    }

  private static int cy(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int cz(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int cA(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}