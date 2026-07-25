// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.z
package dev.angelvisuals.a;

import com.darkmagician6.eventapi.EventTarget;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.angelvisuals.AngelVisuals;
import dev.angelvisuals.a.ClassA112_ClassA113;
import dev.angelvisuals.a.ClassA114_ClassA115;
import dev.angelvisuals.a.ClassA116_ClassA117;
import dev.angelvisuals.a.bI;
import dev.angelvisuals.a.bP;
import dev.angelvisuals.a.bp;
import dev.angelvisuals.a.cK;
import dev.angelvisuals.a.ch;
import dev.angelvisuals.a.cz;
import dev.angelvisuals.a.dD;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.class_10142;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_243;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_638;
import net.minecraft.class_757;
import org.lwjgl.opengl.GL11;

@bI(name = "KillEffect", a = "RENDER", I = "Визуальные эффекты при убийстве")
public class ClassA118 extends cK {

    // ---- поля ----
  public static final ClassA118 field501; // было: a
  private static final long field502 = 4000L; // было: m
  private final List field503; // было: k
  private final List field504; // было: l
  private final Random field505; // было: a
  private static final String hF = "// number obfuscation: ENABLED (XOR masking)";
  private static final String hG = "// stop. seriously. go play minecraft instead";
  private static final String hH = "Protected by t.me/JoinerClient";
  private static final String hI = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String hJ = "// flow obfuscation: ENABLED";
  private static final int et = 487035232;
  private static final int eu = -1173746722;
  private static final int ev = 2059611917;
  private static final byte[] aG;

    static {
        aG = "Geo+W[G9W 'dUKZ%01LdeadM=>HjZ)hz3#0IKs6%2NQ>w!QiDNaPd>5)2WR8aKm!Td9|+~^=^*AT[FIn#[-(vYRrc1i*]Y:$0N'wRSzgJt,4P>a 3NPW8x?[e1K>Im`}<m\"ze)&k=DgRUe}C@wjcb'l=y||~p]2ksP<5c|%pJ*kn5!$cK}UB1@\\d=LhSlls]5.a?kb8hl({~F[6S4CqR~&XmuDE@lA;+m_M8J>h;X+`9EP~6-|EI<#-t4eF*HTXe".getBytes("ISO-8859-1");
        field501 = new ClassA118();
    }

  private ClassA118() { // было: <init>
        super();
        field503 = new ArrayList();
        field504 = new ArrayList();
        field505 = new Random();
    }

  public void method879() { // было: k
        field503.clear();
        field504.clear();
        super.method611();
    }

    @EventTarget
  private void method880(bP arg0) { // было: a
        if (mc.field_1687 == null) {
            return;
        }
        if (mc.field_1724 == null) {
            return;
        }
        class_1297 var3 = arg0.method269();
        if (!(var3 instanceof class_1309)) {
            return;
        }
        class_1309 var2 = ((class_1309) var3);
        if (var2.method_5805()) {
            var3 = field503.iterator();
        } else {
            return;
        }
        ClassA114_ClassA115 var4;
        while (true) {
            if (!var3.hasNext()) {
                field503.add(new ClassA114_ClassA115(var2, System.currentTimeMillis()));
                return;
            }
            var4 = ((ClassA114_ClassA115) var3.next());
            if (var4.aH == var2.method_5628()) {
                break;
            }
            continue;
        }
        var4.field497 = System.currentTimeMillis();
    }

    @EventTarget
  private void method881(cz arg0) { // было: a
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: getstatic  #61 // dev.angelvisuals.a.z.mc:Lnet/minecraft/class_310;
        //      3: getfield  #69 // net.minecraft.class_310.field_1687:Lnet/minecraft/class_638;
        //      6: ifnonnull  10 (offset +4)
        //      9: return
        //     10: invokestatic  #99 // java.lang.System.currentTimeMillis:()J
        //     13: lstore_2
        //     14: aload_0
        //     15: getfield  #59 // dev.angelvisuals.a.z.k:Ljava/util/List;
        //     18: invokeinterface  #122 // java.util.List.iterator:()Ljava/util/Iterator;, count 1
        //     23: astore  4
        //     25: aload  4
        //     27: invokeinterface  #116 // java.util.Iterator.hasNext:()Z, count 1
        //     32: ifeq  145 (offset +113)
        //     35: aload  4
        //     37: invokeinterface  #117 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //     42: checkcast  #27 // dev.angelvisuals.a.z$a
        //     45: astore  5
        //     47: aload  5
        //     49: getfield  #62 // dev.angelvisuals.a.z$a.a:Lnet/minecraft/class_1309;
        //     52: astore  6
        //     54: aload  6
        //     56: ifnull  67 (offset +11)
        //     59: aload  6
        //     61: invokevirtual  #105 // net.minecraft.class_1309.method_31481:()Z
        //     64: ifeq  77 (offset +13)
        //     67: aload  4
        //     69: invokeinterface  #118 // java.util.Iterator.remove:()V, count 1
        //     74: goto  25 (offset -49)
        //     77: aload  6
        //     79: invokevirtual  #107 // net.minecraft.class_1309.method_5805:()Z
        //     82: ifne  117 (offset +35)
        //     85: aload_0
        //     86: aload  6
        //     88: invokevirtual  #104 // net.minecraft.class_1309.method_19538:()Lnet/minecraft/class_243;
        //     91: dconst_0
        //     92: aload  6
        //     94: invokevirtual  #103 // net.minecraft.class_1309.method_17682:()F
        //     97: fconst_2
        //     98: fdiv
        //     99: f2d
        //    100: dconst_0
        //    101: invokevirtual  #108 // net.minecraft.class_243.method_1031:(DDD)Lnet/minecraft/class_243;
        //    104: invokevirtual  #92 // dev.angelvisuals.a.z.c:(Lnet/minecraft/class_243;)V
        //    107: aload  4
        //    109: invokeinterface  #118 // java.util.Iterator.remove:()V, count 1
        //    114: goto  25 (offset -89)
        //    117: lload_2
        //    118: aload  5
        //    120: getfield  #64 // dev.angelvisuals.a.z$a.h:J
        //    123: lsub
        //    124: ldc2_w  #54 // 8516251645233416476L
        //    127: ldc2_w  #52 // 8516251645233415868L
        //    130: lxor
        //    131: lcmp
        //    132: ifle  142 (offset +10)
        //    135: aload  4
        //    137: invokeinterface  #118 // java.util.Iterator.remove:()V, count 1
        //    142: goto  25 (offset -117)
        //    145: aload_0
        //    146: getfield  #60 // dev.angelvisuals.a.z.l:Ljava/util/List;
        //    149: invokedynamic  #124 // invokedynamic test:()Ljava/util/function/Predicate;
        //    154: invokeinterface  #123 // java.util.List.removeIf:(Ljava/util/function/Predicate;)Z, count 2
        //    159: pop
        //    160: aload_0
        //    161: getfield  #60 // dev.angelvisuals.a.z.l:Ljava/util/List;
        //    164: invokeinterface  #122 // java.util.List.iterator:()Ljava/util/Iterator;, count 1
        //    169: astore  5
        //    171: aload  5
        //    173: invokeinterface  #116 // java.util.Iterator.hasNext:()Z, count 1
        //    178: ifeq  201 (offset +23)
        //    181: aload  5
        //    183: invokeinterface  #117 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //    188: checkcast  #28 // dev.angelvisuals.a.z$b
        //    191: astore  6
        //    193: aload  6
        //    195: invokevirtual  #96 // dev.angelvisuals.a.z$b.am:()V
        //    198: goto  171 (offset -27)
        //    201: return
    }

    @EventTarget
  private void method882(dD arg0) { // было: e
        if (field504.isEmpty()) {
            return;
        }
        class_4587 var2;
        Iterator var6;
        if (mc.field_1724 != null) {
            var2 = arg0.method324();
            class_243 var3 = mc.field_1773.method_19418().method_19326();
            float var4 = arg0.bt();
            var2.method_22903();
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.disableCull();
            RenderSystem.enableDepthTest();
            RenderSystem.depthMask(1430410776 ^ 1430410776);
            GL11.glEnable(-962895931 ^ -962898715);
            bp var5 = AngelVisuals.getInstance().getThemeManager().method480(941120979 ^ 941120979);
            RenderSystem.setShader(class_10142.field_53864);
            var6 = field504.iterator();
        } else {
            return;
        }
        while (var6.hasNext()) {
            ClassA116_ClassA117 var7 = ((ClassA116_ClassA117) var6.next());
            var7.method877(var2, var3, var5, var4);
            continue;
        }
        GL11.glDisable(708021023 ^ 708018239);
        RenderSystem.depthMask(1983771224 ^ 1983771225);
        RenderSystem.disableBlend();
        RenderSystem.enableCull();
        RenderSystem.lineWidth(1.0f);
        var2.method_22909();
    }

  private void method883(class_243 arg0) { // было: c
        if (mc.field_1687 != null) {
            mc.field_1687.method_8486(arg0.field_1352, arg0.field_1351, arg0.field_1350, class_3417.field_14865, class_3419.field_15252, 1.0f, 1.0f, 1329974671 ^ 1329974671);
        }
        field504.add(new ClassA112_ClassA113(arg0));
    }

  private static int dS(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int dT(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int dU(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}