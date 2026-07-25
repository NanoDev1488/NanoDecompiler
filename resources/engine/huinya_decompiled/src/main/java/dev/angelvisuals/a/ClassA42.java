// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.e
package dev.angelvisuals.a;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import dev.angelvisuals.a.ClassA101;
import dev.angelvisuals.a.ClassA107;
import dev.angelvisuals.a.ClassA108;
import dev.angelvisuals.a.ClassA111;
import dev.angelvisuals.a.ClassA118;
import dev.angelvisuals.a.ClassA124;
import dev.angelvisuals.a.ClassA127;
import dev.angelvisuals.a.ClassA92;
import dev.angelvisuals.a.ClassA93;
import dev.angelvisuals.a.ClassA94;
import dev.angelvisuals.a.ClassA95;
import dev.angelvisuals.a.ClassA96;
import dev.angelvisuals.a.ae;
import dev.angelvisuals.a.am;
import dev.angelvisuals.a.au;
import dev.angelvisuals.a.bY;
import dev.angelvisuals.a.bt;
import dev.angelvisuals.a.bx;
import dev.angelvisuals.a.bz;
import dev.angelvisuals.a.cF;
import dev.angelvisuals.a.cJ;
import dev.angelvisuals.a.cK;
import dev.angelvisuals.a.cO;
import dev.angelvisuals.a.cR;
import dev.angelvisuals.a.cY;
import dev.angelvisuals.a.cb;
import dev.angelvisuals.a.ce;
import dev.angelvisuals.a.cw;
import dev.angelvisuals.a.dB;
import dev.angelvisuals.a.dC;
import dev.angelvisuals.a.dE;
import dev.angelvisuals.a.dF;
import dev.angelvisuals.a.dG;
import dev.angelvisuals.a.dK;
import dev.angelvisuals.a.da;
import dev.angelvisuals.a.dd;
import dev.angelvisuals.a.dg;
import dev.angelvisuals.a.dt;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public final class ClassA42 implements cF {

    // ---- поля ----
  private final List field196; // было: a
  private long field197; // было: a
  private int field198; // было: m
  private static final String field199 = "// you are reading machine-generated garbage"; // было: u
  private static final String field200 = "// reverse-engineering this jar is a waste of time, friend"; // было: v
  private static final String field201 = "// you are reading machine-generated garbage"; // было: w
  private static final String field202 = "// class hierarchy hashing: ENABLED"; // было: x
  private static final String field203 = "// === DO NOT TOUCH ==="; // было: y
  private static final int field204 = 559994898; // было: n
  private static final int field205 = 1016096260; // было: o
  private static final int field206 = 1462319390; // было: p
  private static final byte[] field207; // было: e

    static {
        field207 = "b*-Tc4@W&Y@U88~]yXikrnhwoY:2<SkA2yi.j'.d1jGesBUZ5|&f&p;@94wJ(q+(>XXtOc8Jr~4u+~OJ{c~&\"\"TuC-=HHLM_46vU@w[Z7*3[(}\"J(Z:o%Dmg:k.%!H2Y$(AI7'-V#9DO72W@\\YdY22*:Y?=Kf~?b{25zibLqp4P29-?7K[:2yr-aJc<u>TTn\"pa*2[+\\!W|8`}mNOvem/>D8: Q;wPuGKk<M'&\"N{b$+{/=D-keIW[1O'8>RFgoN".getBytes("ISO-8859-1");
    }

  public ClassA42() { // было: <init>
        super();
        field196 = new ArrayList();
        field198 = -95690749 ^ 95690748;
        method412();
        method413();
        method414();
        EventManager.register(this);
    }

  private void method412() { // было: a
        method415(dE.field472);
        method415(ClassA95.field365);
        method415(bY.field528);
        method415(ClassA124.field555);
        method415(cJ.field567);
        method415(dC.field405);
        method415(cO.field593);
        method415(bt.field564);
        method415(ClassA107.field451);
        method415(ClassA111.field481);
        method415(ClassA101.field390);
        method415(ce.field455);
        method415(da.field517);
        method415(cw.field536);
        method415(ClassA118.field501);
        method415(am.field376);
        method415(ClassA127.field590);
        method415(bz.field510);
        method415(au.field368);
        method415(dK.field412);
        method415(cb.field468);
        method415(new dB());
        method415(dF.field387);
        method415(ClassA96.field373);
        method415(ae.field556);
        method415(ClassA108.field456);
        method415(dd.field428);
        method415(cR.field423);
        method415(dt.field575);
    }

  private void method413() { // было: b
        method415(dG.field364);
        method415(ClassA94.field359);
    }

  private void method414() { // было: c
        method415(ClassA92.field344);
        method415(dg.field358);
        method415(ClassA93.field356);
    }

  private void method415(cK arg0) { // было: a
        field196.add(arg0);
    }

  public cK method416(String arg0) { // было: a
        return ((cK) field196.stream().filter(lp0 -> method421(arg0, ((cK) lp0))).findFirst().orElse(null));
    }

  public Set method417() { // было: a
        HashSet var1 = new HashSet();
        Iterator var2 = field196.iterator();
        while (var2.hasNext()) {
            cK var3 = ((cK) var2.next());
            if (var3.ah()) {
                var1.add(var3);
            }
            continue;
        }
        return var1;
    }

    @EventTarget
  public void method418(cY arg0) { // было: a
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: getstatic  #112 // dev.angelvisuals.a.e.mc:Lnet/minecraft/class_310;
        //      3: getfield  #120 // net.minecraft.class_310.field_1755:Lnet/minecraft/class_437;
        //      6: ifnonnull  21 (offset +15)
        //      9: aload_1
        //     10: invokevirtual  #138 // dev.angelvisuals.a.cY.am:()I
        //     13: ldc  #5 // 318064906
        //     15: ldc  #6 // 318064907
        //     17: ixor
        //     18: if_icmpeq  22 (offset +4)
        //     21: return
        //     22: aload_1
        //     23: invokevirtual  #139 // dev.angelvisuals.a.cY.an:()I
        //     26: istore_2
        //     27: invokestatic  #150 // java.lang.System.currentTimeMillis:()J
        //     30: lstore_3
        //     31: iload_2
        //     32: aload_0
        //     33: getfield  #111 // dev.angelvisuals.a.e.m:I
        //     36: if_icmpne  57 (offset +21)
        //     39: lload_3
        //     40: aload_0
        //     41: getfield  #108 // dev.angelvisuals.a.e.a:J
        //     44: lsub
        //     45: ldc2_w  #78 // -7017378732874989226L
        //     48: ldc2_w  #80 // -7017378732874989154L
        //     51: lxor
        //     52: lcmp
        //     53: ifge  57 (offset +4)
        //     56: return
        //     57: aload_0
        //     58: iload_2
        //     59: putfield  #111 // dev.angelvisuals.a.e.m:I
        //     62: aload_0
        //     63: lload_3
        //     64: putfield  #108 // dev.angelvisuals.a.e.a:J
        //     67: aload_0
        //     68: getfield  #109 // dev.angelvisuals.a.e.a:Ljava/util/List;
        //     71: invokeinterface  #158 // java.util.List.iterator:()Ljava/util/Iterator;, count 1
        //     76: astore  5
        //     78: aload  5
        //     80: invokeinterface  #155 // java.util.Iterator.hasNext:()Z, count 1
        //     85: ifeq  201 (offset +116)
        //     88: aload  5
        //     90: invokeinterface  #156 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //     95: checkcast  #37 // dev.angelvisuals.a.cK
        //     98: astore  6
        //    100: aload  6
        //    102: invokevirtual  #134 // dev.angelvisuals.a.cK.ai:()I
        //    105: iload_2
        //    106: if_icmpne  123 (offset +17)
        //    109: iload_2
        //    110: ldc  #10 // 1415108146
        //    112: ldc  #1 // -1415108147
        //    114: ixor
        //    115: if_icmpeq  123 (offset +8)
        //    118: aload  6
        //    120: invokevirtual  #132 // dev.angelvisuals.a.cK.aD:()V
        //    123: aload  6
        //    125: invokevirtual  #137 // dev.angelvisuals.a.cK.t:()Ljava/util/List;
        //    128: invokeinterface  #158 // java.util.List.iterator:()Ljava/util/Iterator;, count 1
        //    133: astore  7
        //    135: aload  7
        //    137: invokeinterface  #155 // java.util.Iterator.hasNext:()Z, count 1
        //    142: ifeq  198 (offset +56)
        //    145: aload  7
        //    147: invokeinterface  #156 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //    152: checkcast  #58 // dev.angelvisuals.a.q
        //    155: astore  8
        //    157: aload  8
        //    159: instanceof  #26 // dev.angelvisuals.a.aM
        //    162: ifeq  195 (offset +33)
        //    165: aload  8
        //    167: checkcast  #26 // dev.angelvisuals.a.aM
        //    170: astore  9
        //    172: aload  9
        //    174: invokevirtual  #128 // dev.angelvisuals.a.aM.t:()I
        //    177: iload_2
        //    178: if_icmpne  195 (offset +17)
        //    181: iload_2
        //    182: ldc  #2 // -1121065947
        //    184: ldc  #9 // 1121065946
        //    186: ixor
        //    187: if_icmpeq  195 (offset +8)
        //    190: aload  9
        //    192: invokevirtual  #126 // dev.angelvisuals.a.aM.R:()V
        //    195: goto  135 (offset -60)
        //    198: goto  78 (offset -120)
        //    201: return
    }

    @EventTarget
  public void method419(bx arg0) { // было: a
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #109 // dev.angelvisuals.a.e.a:Ljava/util/List;
        //      4: invokeinterface  #158 // java.util.List.iterator:()Ljava/util/Iterator;, count 1
        //      9: astore_2
        //     10: aload_2
        //     11: invokeinterface  #155 // java.util.Iterator.hasNext:()Z, count 1
        //     16: ifeq  243 (offset +227)
        //     19: aload_2
        //     20: invokeinterface  #156 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //     25: checkcast  #37 // dev.angelvisuals.a.cK
        //     28: astore_3
        //     29: aload_3
        //     30: invokevirtual  #136 // dev.angelvisuals.a.cK.i:()Ldev/angelvisuals/a/k;
        //     33: aload_3
        //     34: invokevirtual  #133 // dev.angelvisuals.a.cK.ah:()Z
        //     37: invokevirtual  #146 // dev.angelvisuals.a.k.a:(Z)V
        //     40: aload_3
        //     41: invokevirtual  #137 // dev.angelvisuals.a.cK.t:()Ljava/util/List;
        //     44: invokeinterface  #158 // java.util.List.iterator:()Ljava/util/Iterator;, count 1
        //     49: astore  4
        //     51: aload  4
        //     53: invokeinterface  #155 // java.util.Iterator.hasNext:()Z, count 1
        //     58: ifeq  240 (offset +182)
        //     61: aload  4
        //     63: invokeinterface  #156 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //     68: checkcast  #58 // dev.angelvisuals.a.q
        //     71: astore  5
        //     73: aload  5
        //     75: instanceof  #26 // dev.angelvisuals.a.aM
        //     78: ifeq  104 (offset +26)
        //     81: aload  5
        //     83: checkcast  #26 // dev.angelvisuals.a.aM
        //     86: astore  6
        //     88: aload  6
        //     90: invokevirtual  #127 // dev.angelvisuals.a.aM.c:()Ldev/angelvisuals/a/k;
        //     93: aload  6
        //     95: invokevirtual  #125 // dev.angelvisuals.a.aM.C:()Z
        //     98: invokevirtual  #146 // dev.angelvisuals.a.k.a:(Z)V
        //    101: goto  237 (offset +136)
        //    104: aload  5
        //    106: instanceof  #27 // dev.angelvisuals.a.aZ
        //    109: ifeq  172 (offset +63)
        //    112: aload  5
        //    114: checkcast  #27 // dev.angelvisuals.a.aZ
        //    117: astore  7
        //    119: aload  7
        //    121: invokevirtual  #129 // dev.angelvisuals.a.aZ.j:()Ljava/util/List;
        //    124: invokeinterface  #158 // java.util.List.iterator:()Ljava/util/Iterator;, count 1
        //    129: astore  9
        //    131: aload  9
        //    133: invokeinterface  #155 // java.util.Iterator.hasNext:()Z, count 1
        //    138: ifeq  169 (offset +31)
        //    141: aload  9
        //    143: invokeinterface  #156 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //    148: checkcast  #28 // dev.angelvisuals.a.aZ$a
        //    151: astore  10
        //    153: aload  10
        //    155: invokevirtual  #131 // dev.angelvisuals.a.aZ$a.h:()Ldev/angelvisuals/a/k;
        //    158: aload  10
        //    160: invokevirtual  #130 // dev.angelvisuals.a.aZ$a.ab:()Z
        //    163: invokevirtual  #146 // dev.angelvisuals.a.k.a:(Z)V
        //    166: goto  131 (offset -35)
        //    169: goto  237 (offset +68)
        //    172: aload  5
        //    174: instanceof  #21 // dev.angelvisuals.a.I
        //    177: ifeq  237 (offset +60)
        //    180: aload  5
        //    182: checkcast  #21 // dev.angelvisuals.a.I
        //    185: astore  8
        //    187: aload  8
        //    189: invokevirtual  #122 // dev.angelvisuals.a.I.d:()Ljava/util/List;
        //    192: invokeinterface  #158 // java.util.List.iterator:()Ljava/util/Iterator;, count 1
        //    197: astore  9
        //    199: aload  9
        //    201: invokeinterface  #155 // java.util.Iterator.hasNext:()Z, count 1
        //    206: ifeq  237 (offset +31)
        //    209: aload  9
        //    211: invokeinterface  #156 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //    216: checkcast  #22 // dev.angelvisuals.a.I$a
        //    219: astore  10
        //    221: aload  10
        //    223: invokevirtual  #124 // dev.angelvisuals.a.I$a.k:()Ldev/angelvisuals/a/k;
        //    226: aload  10
        //    228: invokevirtual  #123 // dev.angelvisuals.a.I$a.aj:()Z
        //    231: invokevirtual  #146 // dev.angelvisuals.a.k.a:(Z)V
        //    234: goto  199 (offset -35)
        //    237: goto  51 (offset -186)
        //    240: goto  10 (offset -230)
        //    243: return
    }

  public List method420() { // было: a
        return field196;
    }

  private static boolean method421(String arg0, cK arg1) { // было: a
        return arg1.getName().equalsIgnoreCase(arg0);
    }

  private static int method422(int arg0, int arg1) { // было: m
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int method423(int arg0, int arg1) { // было: n
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int method424(int arg0, int arg1) { // было: o
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}