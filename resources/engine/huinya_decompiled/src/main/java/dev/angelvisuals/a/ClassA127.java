// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.C
package dev.angelvisuals.a;

import com.darkmagician6.eventapi.EventTarget;
import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.AngelVisuals;
import dev.angelvisuals.a.ClassA125_ClassA126;
import dev.angelvisuals.a.aI;
import dev.angelvisuals.a.aM;
import dev.angelvisuals.a.bA;
import dev.angelvisuals.a.bI;
import dev.angelvisuals.a.bl;
import dev.angelvisuals.a.bp;
import dev.angelvisuals.a.cK;
import dev.angelvisuals.a.ch;
import dev.angelvisuals.a.dD;
import dev.angelvisuals.utility.mixin.minecraft.entity.LimbAnimatorMixin;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.class_1657;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_4597.class_4598;
import net.minecraft.class_4599;
import net.minecraft.class_757;
import net.minecraft.class_898;

@bI(name = "TotemPop", a = "RENDER", I = "Показывает призрачную копию игрока при срабатывании тотема")
public class ClassA127 extends cK {

    // ---- поля ----
  public static final ClassA127 field590; // было: a
  public static float dN;
  public static float dO;
  public static int sJ;
  private final bA ai;
  private final bA aj;
  private final aM field591; // было: m
  private final List field592; // было: S
  private static final String FH = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final String FI = "// nice try. closed source for a reason.";
  private static final String FJ = "// class hierarchy hashing: ENABLED";
  private static final String FK = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final String FL = "// flow obfuscation: ENABLED";
  private static final int sK = -1104707907;
  private static final int sL = -169055305;
  private static final int sM = 1114118353;
  private static final byte[] eV;

    static {
        eV = "_6.#P+O+JbBqz#+7CY=)!YNi2oWJ3r$6]YBJ~b8-uhURxsa.=%rpD>N^8Gr5)MUYU<jWC1-[ yODhmw}QEOe&H9-@fznIa^AeVm{wy^m).]6lV4Qm\\>iWoXv/\\6j~&cUUEs0`mkaz?7 Xs/`tw$\"<P4f{eWb#fq_6RKI9;2ie0vxt6_|@OP5d6gK@|URI`|}JI.&!<)d#(i?e/lYZ[H)TCBla\\W:4-9v%_ne&Hwd{(t5I'':d460k_Q;41BgN6xD".getBytes("ISO-8859-1");
        field590 = new ClassA127();
        dN = -1.0f;
        dO = -1.0f;
        sJ = 1496951027 ^ 1506170636;
    }

  private ClassA127() { // было: <init>
        super();
        ai = new bA(Decryptor.method1945(XorDecoder.method1946("ç»+¦ýÿ;´ÿþ_àýÝ7 Ã,÷ç*µ¥ú\"Õã8¾¬Ç)ÝÚ>¼Õý/ï", 616922039 ^ -156504285)), 700.0f, 250.0f, 2000.0f, 50.0f);
        aj = new bA(Decryptor.method1945(XorDecoder.method1946("Ð\u001dÀÃ~ëÚéRÖÌ\u001eýÿ\u0018»ðiÈ", -226253842 ^ 121277268)), 2.4000000953674316f, 0.0f, 6.0f, 0.10000000149011612f);
        field591 = new aM(Decryptor.method1945(XorDecoder.method1946("\u001e¶ÜSÌïò!Â×ñPããíRè¾ô\u0015Õ¨", 1524342804 ^ -129096592)), 978799159 ^ 978799158);
        field592 = new ArrayList();
    }

    @EventTarget
  private void method961(aI arg0) { // было: e
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_1
        //      1: invokevirtual  #109 // dev.angelvisuals.a.aI.am:()Z
        //      4: ifeq  25 (offset +21)
        //      7: getstatic  #76 // dev.angelvisuals.a.C.mc:Lnet/minecraft/class_310;
        //     10: getfield  #100 // net.minecraft.class_310.field_1687:Lnet/minecraft/class_638;
        //     13: ifnull  25 (offset +12)
        //     16: getstatic  #76 // dev.angelvisuals.a.C.mc:Lnet/minecraft/class_310;
        //     19: getfield  #101 // net.minecraft.class_310.field_1724:Lnet/minecraft/class_746;
        //     22: ifnonnull  26 (offset +4)
        //     25: return
        //     26: aload_1
        //     27: invokevirtual  #110 // dev.angelvisuals.a.aI.b:()Lnet/minecraft/class_2596;
        //     30: astore_3
        //     31: aload_3
        //     32: instanceof  #60 // net.minecraft.class_2663
        //     35: ifeq  46 (offset +11)
        //     38: aload_3
        //     39: checkcast  #60 // net.minecraft.class_2663
        //     42: astore_2
        //     43: goto  47 (offset +4)
        //     46: return
        //     47: aload_2
        //     48: invokevirtual  #130 // net.minecraft.class_2663.method_11470:()B
        //     51: ldc  #2 // -2119970627
        //     53: ldc  #1 // -2119970658
        //     55: ixor
        //     56: if_icmpeq  60 (offset +4)
        //     59: return
        //     60: aload_2
        //     61: getstatic  #76 // dev.angelvisuals.a.C.mc:Lnet/minecraft/class_310;
        //     64: getfield  #100 // net.minecraft.class_310.field_1687:Lnet/minecraft/class_638;
        //     67: invokevirtual  #129 // net.minecraft.class_2663.method_11469:(Lnet/minecraft/class_1937;)Lnet/minecraft/class_1297;
        //     70: astore  4
        //     72: aload  4
        //     74: instanceof  #58 // net.minecraft.class_1657
        //     77: ifeq  89 (offset +12)
        //     80: aload  4
        //     82: checkcast  #58 // net.minecraft.class_1657
        //     85: astore_3
        //     86: goto  90 (offset +4)
        //     89: return
        //     90: aload_0
        //     91: getfield  #75 // dev.angelvisuals.a.C.m:Ldev/angelvisuals/a/aM;
        //     94: invokevirtual  #112 // dev.angelvisuals.a.aM.C:()Z
        //     97: ifne  111 (offset +14)
        //    100: aload_3
        //    101: getstatic  #76 // dev.angelvisuals.a.C.mc:Lnet/minecraft/class_310;
        //    104: getfield  #101 // net.minecraft.class_310.field_1724:Lnet/minecraft/class_746;
        //    107: if_acmpne  111 (offset +4)
        //    110: return
        //    111: aload_0
        //    112: getfield  #68 // dev.angelvisuals.a.C.S:Ljava/util/List;
        //    115: new  #43 // dev.angelvisuals.a.C$a
        //    118: dup
        //    119: aload_3
        //    120: aload_3
        //    121: invokevirtual  #124 // net.minecraft.class_1657.method_23317:()D
        //    124: aload_3
        //    125: invokevirtual  #125 // net.minecraft.class_1657.method_23318:()D
        //    128: aload_3
        //    129: invokevirtual  #126 // net.minecraft.class_1657.method_23321:()D
        //    132: invokestatic  #122 // java.lang.System.currentTimeMillis:()J
        //    135: invokespecial  #108 // dev.angelvisuals.a.C$a.<init>:(Lnet/minecraft/class_1657;DDDJ)V
        //    138: invokeinterface  #143 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //    143: pop
        //    144: return
    }

    @EventTarget
  private void method962(dD arg0) { // было: m
        if (mc.field_1687 == null) {
            return;
        }
        if (!field592.isEmpty()) {
            long var2 = System.currentTimeMillis();
            long var4 = ((long) ai.bp());
            float var6 = arg0.bt();
            int var7 = AngelVisuals.getInstance().getThemeManager().method481().method449().method1680() & (-718507058 ^ -707556303);
            class_4587 var8 = arg0.method324();
            class_243 var9 = mc.field_1773.method_19418().method_19326();
            class_898 var10 = mc.method_1561();
            class_4598 var11 = mc.method_22940().method_23000();
            Iterator var12 = field592.iterator();
        } else {
            return;
        }
        while (true) {
            if (!var12.hasNext()) {
                return;
            }
            ClassA125_ClassA126 var13 = ((ClassA125_ClassA126) var12.next());
            float var14 = ((float) (var2 - var13.field583)) / ((float) var4);
            if (var14 < 1.0f) {
                float var15 = 0.699999988079071f * (1.0f - var14);
                dN = var15;
                dO = var14;
                sJ = var7;
                try {
                    float var16 = var13.field579.field_6283;
                    float var17 = var13.field579.field_6241;
                    float var18 = var13.field579.method_36455();
                    float var19 = var13.field579.field_6220;
                    float var20 = var13.field579.field_6259;
                    float var21 = var13.field579.field_6004;
                    float var22 = var13.field579.field_6251;
                    float var23 = var13.field579.field_6229;
                    boolean var24 = var13.field579.field_6252;
                    var13.field579.field_6283 = var13.field584;
                    var13.field579.field_6241 = var13.field585;
                    var13.field579.field_6220 = var13.field584;
                    var13.field579.field_6259 = var13.field585;
                    var13.field579.field_6004 = var13.field586;
                    var13.field579.method_36457(var13.field586);
                    LimbAnimatorMixin var25 = ((LimbAnimatorMixin) var13.field579.field_42108);
                    var25.setPos(var13.field587);
                    var25.setSpeedField(var13.field588);
                    var13.field579.field_6251 = 0.0f;
                    var13.field579.field_6229 = 0.0f;
                    var13.field579.field_6252 = -1727121324 ^ -1727121324;
                    var10.method_62424(var13.field579, var13.field580 - var9.field_1352, var13.field581 + ((double) (aj.bp() * var14)) - var9.field_1351, var13.field582 - var9.field_1350, var6, var8, var11, 1734208834 ^ 1739451826);
                    var11.method_22993();
                    var13.field579.field_6283 = var16;
                    var13.field579.field_6241 = var17;
                    var13.field579.field_6220 = var19;
                    var13.field579.field_6259 = var20;
                    var13.field579.field_6004 = var21;
                    var13.field579.method_36457(var18);
                    var13.field579.field_6251 = var22;
                    var13.field579.field_6229 = var23;
                    var13.field579.field_6252 = var24;
                } catch (Throwable e1) {
                }
            } else {
                var12.remove();
                continue;
            }
        }
        Throwable var26;
        try {
            while (true) {
                var26 = __caught__;
            }
        } catch (Throwable var26) {
        }
        dN = -1.0f;
        dO = -1.0f;
        throw var26;
    }

  private static int qJ(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int qK(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int qL(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}