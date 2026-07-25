// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.ci.a
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.angelvisuals.AngelVisuals;
import dev.angelvisuals.a.an;
import dev.angelvisuals.a.an_ClassA33;
import dev.angelvisuals.a.an_ClassA35;
import dev.angelvisuals.a.an_ClassA36;
import dev.angelvisuals.a.bM;
import dev.angelvisuals.a.cF;
import dev.angelvisuals.a.ci;
import dev.angelvisuals.a.df;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.minecraft.class_1044;
import net.minecraft.class_1060;
import net.minecraft.class_2960;
import net.minecraft.class_310;

public class ci_ClassA39 {

    // ---- поля ----
  private String sT;
  private class_2960 field185; // было: f
  private class_2960 field186; // было: g
  private static final String sU = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String sV = "// every class watermarked, every string encrypted, every number xored";
  private static final String sW = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final String sX = "Protected by t.me/JoinerClient";
  private static final String sY = "// this jar protected by JoinerObfuscator";
  private static final int ll = -1112291431;
  private static final int lm = 686309516;
  private static final int ln = -1725968274;
  private static final byte[] cF;

    static {
        cF = "~p~2oq*&D*-AI+dk@XuDSPRhjX{k>9Ep^3R5=r2^DQHQ&i(+q529xgL[cpmNCe>B}r5~wvdzw9vP[HeRzoh\"C,-azRibh-#*,~=i}$(3-lhuJe-3Q^f})Rr8^0f,V3zsA8[C \"^hXYQas>A\\^K(,nGe1oTb-6.@j}8XAo4G+;D$+l7mF5PWFo5F`[@);,kZ%@{U(>]TFlCrE76Q(6d=gw]H,I1DGldOuVNtvpvX+nX(9v$Jc`PB|@K*|CniDmpyH".getBytes("ISO-8859-1");
    }

  private ci_ClassA39() { // было: <init>
        super();
        sT = Decryptor.method1945(XorDecoder.method1946("QÊ9;_Ê7eiÏ\u00152HÚF9\\É)E\u000fäC7", 1626021980 ^ 1788191585));
    }

  public ci_ClassA39 method371(String arg0) { // было: a
        sT = arg0;
        return this;
    }

  public ci_ClassA39 method372(String arg0) { // было: b
        field185 = AngelVisuals.id("fonts/msdf/" + arg0 + ".json");
        return this;
    }

  public ci_ClassA39 method373(String arg0) { // было: c
        field186 = AngelVisuals.id("fonts/msdf/" + arg0 + ".png");
        return this;
    }

  public ci method374() { // было: b
        an var1 = ((an) df.method403(field185, an.class));
        class_1044 var2 = cF.field785.method_1531().method_4619(field186);
        if (var1 != null) {
            RenderSystem.recordRenderCall(() -> method378(var2));
            float var3 = var1.method362().bj();
            float var4 = var1.method362().bk();
            Map var5 = ((Map) var1.method364().stream().collect(Collectors.toMap(lp0 -> (((an_ClassA35) lp0)).method353(), lp0 -> method377(var3, var4, ((an_ClassA35) lp0)))));
            HashMap var6 = new HashMap();
            var1.method365().forEach(lp0 -> method375(var6, ((an_ClassA36) lp0)));
            return new ci(sT, var2, var1.method362(), var1.method363(), var5, var6);
        } else {
            throw new RuntimeException("Failed to read font data file: " + field185.toString() + "; Are you sure this is json file? Try to check the correctness of its syntax.");
        }
    }

  private static void method375(Map arg0, an_ClassA36 arg1) { // было: a
        Map var2 = ((Map) arg0.computeIfAbsent(Integer.valueOf(arg1.method356()), lp0 -> method376(((Integer) lp0))));
        var2.put(Integer.valueOf(arg1.method357()), Float.valueOf(arg1.az()));
    }

  private static Map method376(Integer arg0) { // было: a
        return new HashMap();
    }

  private static bM method377(float arg0, float arg1, an_ClassA35 arg2) { // было: a
        return new bM(arg2, arg0, arg1);
    }

  private static void method378(class_1044 arg0) { // было: a
        arg0.method_4527(-2003224606 ^ -2003224605, 150051257 ^ 150051257);
    }

  private static int jM(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int jN(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int jO(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}