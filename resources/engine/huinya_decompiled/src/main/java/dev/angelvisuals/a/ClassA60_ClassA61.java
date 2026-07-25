// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.r.a
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
import net.minecraft.class_2561;

class ClassA60_ClassA61 extends ClassA54_ClassA55 {

    // ---- поля ----
  final String bd;
  final class_2561 field253; // было: a
  private static final String be = "// stop. seriously. go play minecraft instead";
  private static final String bf = "// every class watermarked, every string encrypted, every number xored";
  private static final String bg = "// flow obfuscation: ENABLED";
  private static final String bh = "// you are reading machine-generated garbage";
  private static final String bi = "// you are reading machine-generated garbage";
  private static final int as = 967158002;
  private static final int at = 1602315282;
  private static final int au = -2065898447;
  private static final byte[] field254; // было: v

    static {
        field254 = "C:$;vP-Q-{b*Cp*P<(F|z_qMiGHIhV<l.1k4Ht6pFn'|ptEs/G{`=Qv,J7k@mO~I7uO:%^]e7c%BlP<*p8L?`.&\":01-`[AT]Dwj@F}c2>I8$..$/6:(Bg5~5[!(cz-8mGCgF\\He=(SGtT *mWb;\"-e/x}3#*'kfAr`*h\\}c(-z32y!(g)-!Z}'mMdg7,*bU6u4XmbS1E_w8_@vv#d[5^H9Hb&vu\\;3\"d|_l&J5)lGgjze.`z^K=g4i*h(w\"(MEz".getBytes("ISO-8859-1");
    }

   ClassA60_ClassA61(String arg0, class_2561 arg1) { // было: <init>
        super();
        bd = arg0;
        field253 = arg1;
    }

   void method519(ap arg0, float arg1, float arg2, ar arg3, bl arg4, float arg5, ClassA64 arg6) { // было: a
        if (o == (2953246402266488477L ^ 2953246402266488477L)) {
            o = System.currentTimeMillis();
        }
        float var8 = 16.0f;
        float var9 = j.method13();
        bp var10 = arg4.method449().method1685(var9 * 255.0f);
        bp var11 = bp.field909.method1685(var9 * 255.0f);
        String var12 = " " + field253.getString();
        float var13 = arg3.method349(var12);
        float var14 = var8 + 4.0f + var13 + 10.0f;
        arg1 = arg1 - (var14 / 2.0f - 1.0f);
        aE.method1759(arg0.method_51448(), arg1 - 1.0f, arg2, var14 - 3.0f, arg5, 15.0f, aY.method1597(5.0f), bp.field909.method1685(var9 * 255.0f));
        aE.method1742(arg0.method_51448(), arg1 - 1.0f, arg2, 17.0f, arg5, aY.method1604(5.0f, 5.0f), new bp(1069741702 ^ 1069741702, 14825604 ^ 14825604, -1578544344 ^ -1578544344, var9 * 255.0f));
        aE.method1742(arg0.method_51448(), arg1 + 16.0f, arg2, var14 - 20.0f, arg5, aY.method1605(5.0f, 5.0f), new bp(301115642 ^ 301115642, 708450850 ^ 708450850, 1966512780 ^ 1966512780, var9 * 125.0f));
        ar var15 = bc.field175.method383(8.5f);
        float var16 = arg1 - 1.0f + (17.0f - var15.method349(bd)) / 2.0f;
        float var17 = arg2 + (arg5 - var15.method348()) / 2.0f;
        arg0.method1638(var15, bd, var16, var17, var10);
        float var18 = arg1 + var8 + 8.0f;
        float var19 = arg2 + (arg5 - arg3.method348()) / 2.0f;
        arg0.method1638(arg3, var12, var18 - 4.0f, var19, var11);
    }

  private static int al(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int am(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int an(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}