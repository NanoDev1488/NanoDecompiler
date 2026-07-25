// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.aF
package dev.angelvisuals.a;

import dev.angelvisuals.a.ClassA146;
import net.minecraft.class_1041;
import net.minecraft.class_276;
import net.minecraft.class_310;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;

public class aF implements ClassA146 {

    // ---- поля ----
  private static final String iq = "// === DO NOT TOUCH ===";
  private static final String ir = "// flow obfuscation: ENABLED";
  private static final String is = "// reverse-engineering this jar is a waste of time, friend";
  private static final String it = "// this jar protected by JoinerObfuscator";
  private static final String iu = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final int eQ = -1612574683;
  private static final int eR = 1547876695;
  private static final int eS = -2115547437;
  private static final byte[] aN;

    static {
        aN = "VAy[,W/Aye <|d(lj4b`=mi&@ZmI`Mn}Q(RwH 7\"bu={Z^^*=q~>EWNj|oM;K]bqNs-j<O{h74!?Nc3}0kkw/W{r?:d5VHf$oa6-z#I}s`!,,spF\\Fq6EF85wd%s(\\^qtiy W_f%2A`nQ;ZZWw`:C=9Q.-leaR2DT:;6eUURl-Oja{,x4Xldw\"C:YodZKZYv{Dk#(3Qk @'OPLKO<4rO:1f#k,A\\6UMdDIDZR3Mwej$dZ6(/\"F%X;VrMF(U4%H}D".getBytes("ISO-8859-1");
    }

  public aF() { // было: <init>
        super();
    }

  public static void method1554() { // было: K
        class_276 var0 = mc.method_1522();
        if (var0.field_1474 > (-131438361 ^ 131438360)) {
            mc.method_1522().method_1235(243553873 ^ 243553873);
            EXTFramebufferObject.glDeleteRenderbuffersEXT(var0.field_1474);
            int var1 = EXTFramebufferObject.glGenRenderbuffersEXT();
            EXTFramebufferObject.glBindRenderbufferEXT(1320013772 ^ 1319977613, var1);
            EXTFramebufferObject.glRenderbufferStorageEXT(949318735 ^ 949352718, 294663850 ^ 294695507, mc.method_22683().method_4480(), mc.method_22683().method_4507());
            EXTFramebufferObject.glFramebufferRenderbufferEXT(-704847478 ^ -704877366, -1972372950 ^ -1972406518, -1098007978 ^ -1098041577, var1);
            EXTFramebufferObject.glFramebufferRenderbufferEXT(-1051648753 ^ -1051619249, -1860826870 ^ -1860795382, 700410142 ^ 700441695, var1);
            var0.field_1474 = 1025802469 ^ -1025802470;
        }
        GL11.glStencilMask(-444219361 ^ -444219168);
        GL11.glClear(479801595 ^ 479802619);
        GL11.glEnable(-206409794 ^ -206412754);
        GL11.glStencilFunc(46542822 ^ 46542305, 1301504217 ^ 1301504216, 1207628946 ^ 1207628947);
        GL11.glStencilOp(-1650034884 ^ -1650038467, 2051307558 ^ 2051302951, 1685444965 ^ 1685440356);
        GL11.glDisable(-32525283 ^ -32522388);
        GL11.glColorMask(-1445984900 ^ -1445984900, 1131296840 ^ 1131296840, -491969099 ^ -491969099, -1263703405 ^ -1263703405);
    }

  public static void method1555(int arg0) { // было: e
        GL11.glColorMask(-331559865 ^ -331559866, 730468665 ^ 730468664, 932576483 ^ 932576482, 1090760094 ^ 1090760095);
        GL11.glStencilFunc(-1345448219 ^ -1345448729, arg0, 319661236 ^ 319661237);
        GL11.glStencilOp(1009225740 ^ 1009225228, -1532877819 ^ -1532872187, -734625954 ^ -734633634);
    }

  public static void method1556() { // было: L
        GL11.glDisable(294047056 ^ 294045376);
        GL11.glEnable(-1083792461 ^ -1083791166);
    }

  private static int en(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int eo(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int ep(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}