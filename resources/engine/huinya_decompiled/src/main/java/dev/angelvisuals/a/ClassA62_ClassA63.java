// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.r.e
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
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
import net.minecraft.class_2960;

class ClassA62_ClassA63 extends ClassA54_ClassA55 {

    // ---- поля ----
  final String AS;
  final boolean an;
  private static final String AT = "// === DO NOT TOUCH ===";
  private static final String AU = "// reverse-engineering this jar is a waste of time, friend";
  private static final String AV = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String AW = "// flow obfuscation: ENABLED";
  private static final String AX = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final int pR = 152565243;
  private static final int pS = -718176495;
  private static final int pT = -250765291;
  private static final byte[] ec;

    static {
        ec = "at(5FKd8rh.4%g?AhmoJF}UW'W0&QP2IKm <&ZY|32*N8otnzJG1@?'Kc;B|I*6?tx0R#l-@ ~H&C<Itzp^!s`}_L8.w,qZuyExg'NM (bU^2*{za~BBY$[Vb2(.6AJQsxrS%K]lL)d$]Wq;*J)ijhBs~~}|I:uI\\{LAsW)l}Mjb1I+QK7GRk$iaM|#/,}OUa+<[z@16;{W|?=9_h[>le$~*{uzo^Gnf>m& ?t8O(}hP#eu5G](|]#tID!R+J=XO".getBytes("ISO-8859-1");
    }

   ClassA62_ClassA63(String arg0, boolean arg1) { // было: <init>
        super();
        AS = arg0;
        an = arg1;
    }

   void method520(ap arg0, float arg1, float arg2, ar arg3, bl arg4, float arg5, ClassA64 arg6) { // было: a
        String __stk1;
        if (o == (-2373464392532958179L ^ -2373464392532958179L)) {
            o = System.currentTimeMillis();
        }
        float var8 = 16.0f;
        bp var9 = arg4.method449().method1685(j.method13() * 255.0f);
        bp var10 = bp.field909.method1685(j.method13() * 255.0f);
        String var11 = " " + AS + " потерял тотем";
        float var12 = arg3.method349(var11);
        float var13 = var8 + 4.0f + var12 + 10.0f;
        ar var14 = bc.field181.method383(6.75f);
        __stk1 = !an ? Decryptor.method1945(XorDecoder.method1946("-OOÀAr\u0013ÕTsSÞDY\u0017ÿQE\ràKT\u001f", -95020396 ^ 1114913170)) : Decryptor.method1945(XorDecoder.method1946("®¡pË§P©¢oÃª\u0015¡BÌ£\u001a", 1052103664 ^ -1768808143));
        String var15 = __stk1;
        arg1 = arg1 - (var13 / 2.0f - 1.0f);
        float var16 = j.method13();
        aE.method1759(arg0.method_51448(), arg1 - 1.0f, arg2, var13 - 3.0f, arg5, 15.0f, aY.method1597(5.0f), bp.field909.method1685(var16 * 255.0f));
        aE.method1742(arg0.method_51448(), arg1 - 1.0f, arg2, 17.0f, arg5, aY.method1604(5.0f, 5.0f), new bp(-856403530 ^ -856403530, -1163494292 ^ -1163494292, -796812212 ^ -796812212, var16 * 255.0f));
        aE.method1742(arg0.method_51448(), arg1 + 16.0f, arg2, var13 - 20.0f, arg5, aY.method1605(5.0f, 5.0f), new bp(-1976632854 ^ -1976632854, -1481915092 ^ -1481915092, -476122269 ^ -476122269, var16 * 125.0f));
        float var17 = arg1 - 1.0f + 4.0f;
        float var18 = arg2 + (arg5 - 9.0f) / 2.0f;
        arg0.method1650(class_2960.method_60655(Decryptor.method1945(XorDecoder.method1946("­ë¯\u001d¨ÜAªØ§_î\u001aø°zìÔ\u0014", -794730072 ^ -112657808)), Decryptor.method1945(XorDecoder.method1946("'\týªx+ª¯\u0008\u0003â1\u0004é-\u0006z��¿\u001e4ÿ¯\u0006\u0012s\r¾?\u000f ³\u000c+·¶')ª\u0006)µ\"&ý\u001f(·\u000f#", 1795855417 ^ 692145908))), var17, var18, 9.0f, 9.0f, bp.field909.method1685(var16 * 255.0f));
        float var19 = arg1 + var8 + 8.0f;
        float var20 = arg2 + (arg5 - arg3.method348()) / 2.0f;
        arg0.method1638(arg3, var11, var19 - 4.0f, var20, var10);
    }

  private static int oe(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int of(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int og(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}