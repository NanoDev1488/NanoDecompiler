// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.aH.b
package dev.angelvisuals.a;

import dev.angelvisuals.a.aH_ClassA7;
import dev.angelvisuals.a.cs;

public class aH_ClassA10 extends aH_ClassA7 {

    // ---- поля ----
  private static final String lS = "// nice try. closed source for a reason.";
  private static final String lT = "// number obfuscation: ENABLED (XOR masking)";
  private static final String lU = "// flow obfuscation: ENABLED";
  private static final String lV = "Protected by t.me/JoinerClient";
  private static final String lW = "// nice try. closed source for a reason.";
  private static final int gU = -757619852;
  private static final int gV = 601001184;
  private static final int gW = 1663632033;
  private static final byte[] bu;

    static {
        bu = "GYMxUJy[5dZO?PU<m@[,*eI#<:`9G#''d =0S/ \"UX%EQN[IHco.S.jP r~pEG^m<*w'<m2.T,pE\\KQ -CQYzVyX-BW-sC'vk6Kn;du1gjYQ`iUvr\\h{<%`AiN%0gku\\=t54%gK]:efdsCt\"#E_byh@emsxsZODY)ycT6P:eUG){@JiY!y@?'(<vA5JHN4Q&697CDC\\*k\",y-/EdP~(\"&Eu5~b$2{/8'GVn>S\"JGHrv~\\IG\\oyh.C5uFcB#2jgB>".getBytes("ISO-8859-1");
    }

  public aH_ClassA10(float arg0, float arg1) { // было: <init>
        super(arg0, arg1);
    }

  public aH_ClassA10() { // было: <init>
        super();
    }

  public float method36(float arg0, float arg1, float arg2, float arg3) { // было: b
        float var5 = bc();
        float var6 = bd();
        if (arg0 != 0.0f) {
            arg0 = arg0 / arg3;
            if (arg0 != 1.0f) {
                if (var6 == 0.0f) {
                    var6 = arg3 * 0.30000001192092896f;
                }
                float var7 = 0.0f;
                if (var5 >= Math.abs(arg2)) {
                    var7 = var6 / 6.2831854820251465f * ((float) Math.asin(((double) (arg2 / var5))));
                } else {
                    var5 = arg2;
                    var7 = var6 / 4.0f;
                }
                return var5 * ((float) Math.pow(2.0, ((double) (-10.0f * arg0)))) * ((float) cs.method1411(((double) (arg0 * arg3 - var7)) * 6.283185307179586 / ((double) var6))) + arg2 + arg1;
            } else {
                return arg1 + arg2;
            }
        } else {
            return arg1;
        }
    }

  private static int gi(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int gj(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int gk(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}