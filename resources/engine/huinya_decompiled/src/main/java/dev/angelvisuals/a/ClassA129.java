// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.L
package dev.angelvisuals.a;

import dev.angelvisuals.a.ClassA84;
import dev.angelvisuals.a.ClassA88_ClassA89;
import dev.angelvisuals.a.ClassA90;
import dev.angelvisuals.a.aM;
import dev.angelvisuals.a.aZ;
import dev.angelvisuals.a.aZ_ClassA87;
import dev.angelvisuals.a.ai;
import dev.angelvisuals.a.bA;
import dev.angelvisuals.a.bc;
import dev.angelvisuals.a.cA;
import dev.angelvisuals.a.cK;
import dev.angelvisuals.a.ci;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public final class ClassA129 {

    // ---- поля ----
  public static final float field627 = 18.0f; // было: s
  public static final float field628 = 105.0f; // было: t
  public static final float field629 = 260.0f; // было: u
  public static final float field630 = 115.0f; // было: v
  public static final float field631 = 2.5f; // было: w
  public static final float field632 = 4.0f; // было: x
  public static final float field633 = 19.0f; // было: y
  public static final float field634 = 100.0f; // было: z
  public static final float field635 = 17.5f; // было: A
  public static final float field636 = 3.0f; // было: B
  public static final float field637 = 6.0f; // было: C
  public static final float field638 = 7.0f; // было: D
  public static final float field639 = 98.0f; // было: E
  public static final float field640 = 88.0f; // было: F
  public static final float field641 = 3.0f; // было: G
  public static final float field642 = 3.0f; // было: H
  public static final float field643 = 4.0f; // было: I
  public static final float field644 = 2.0f; // было: J
  public static final float field645 = 79.0f; // было: K
  public static final int bv = 24;
  public static final float field646 = 120.0f; // было: L
  public static final float field647 = 22.0f; // было: M
  public static final float field648 = 8.0f; // было: N
  public static final float field649 = 3.5f; // было: O
  public static final float field650 = 19.0f; // было: P
  public static final float field651 = 8.0f; // было: Q
  private static final String cT = "// stop. seriously. go play minecraft instead";
  private static final String cU = "// stop. seriously. go play minecraft instead";
  private static final String cV = "// === DO NOT TOUCH ===";
  private static final String cW = "// number obfuscation: ENABLED (XOR masking)";
  private static final String cX = "Protected by t.me/JoinerClient";
  private static final int bw = -521322932;
  private static final int bx = -2116789538;
  private static final int by = -459849480;
  private static final byte[] field652; // было: M

    static {
        field652 = "Zt^FjIVYP}y27pBA@8IBX{LRG7\"_ME6qmsfAS_e#!>TNpOGFUcrN<UclShv\\9^[FVjHx*AP&sPW{B(yD,|(O`,:QP:53R&<'/iV*$E0/As(1Ia@lt9|6qsvRk}V,N9[O59U}nY<y8^9>!=b+l9+$;TG/Alwey8h)jwcR;fM;rMmxk`RPW%,a35hh}^q{]dnSbB9&wzm3[T<DUt^ BMs=o}0]j5\\~@4MN!\"cvvh+T%aC49x\"`5\\\"FJ8~\\\"OZC,cY#".getBytes("ISO-8859-1");
    }

  private ClassA129() { // было: <init>
        super();
    }

  public static float method1010(int arg0) { // было: a
        return ((float) (arg0 - (870282825 ^ 870282824))) * 115.0f + 105.0f;
    }

  public static float method1011(float arg0, int arg1) { // было: a
        return arg0 + ((float) arg1) * 115.0f;
    }

  public static float method1012(float arg0) { // было: d
        return arg0 + 18.0f;
    }

  public static float method1013() { // было: i
        return 240.0f;
    }

  public static float method1014(float arg0, int arg1, float arg2) { // было: a
        return arg0 + method1010(arg1) / 2.0f - arg2 / 2.0f;
    }

  public static float method1015(float arg0) { // было: e
        return arg0 + 260.0f + 8.0f;
    }

  public static boolean method1016(cK arg0) { // было: a
        List var1 = arg0.method612();
        if (var1 == null) {
            return 1052753314 ^ 1052753314;
        }
        if (!var1.isEmpty()) {
            Iterator var2 = var1.iterator();
        } else {
            return 1052753314 ^ 1052753314;
        }
        while (true) {
            if (!var2.hasNext()) {
                return 1142880488 ^ 1142880488;
            }
            ClassA84 var3 = ((ClassA84) var2.next());
            if (var3 == null) {
                continue;
            } else {
                if (var3.method633()) {
                    break;
                }
                continue;
            }
        }
        return 485784315 ^ 485784314;
    }

  public static float method1017(aZ arg0) { // было: a
        float var1 = 7.0f;
        float var2 = 11.0f;
        int var3 = -174792537 ^ -174792538;
        Iterator var4 = arg0.method701().iterator();
        while (var4.hasNext()) {
            aZ_ClassA87 var5 = ((aZ_ClassA87) var4.next());
            float var6 = bc.field171.method381(var5.method691(), 6.0f);
            float var7 = var6 + 8.0f;
            if (var1 + var7 > 98.0f) {
                var1 = 7.0f;
                ++var3;
            }
            var1 = var1 + var7 + 3.0f;
            continue;
        }
        return 9.0f + ((float) var3) * var2 + ((float) (var3 - (-691250868 ^ -691250867))) * 3.0f + 2.0f;
    }

  public static float method1018(ClassA90 arg0) { // было: a
        float var1 = 7.0f;
        float var2 = 11.0f;
        int var3 = 917871502 ^ 917871503;
        Iterator var4 = arg0.method721().iterator();
        while (var4.hasNext()) {
            ClassA88_ClassA89 var5 = ((ClassA88_ClassA89) var4.next());
            float var6 = bc.field171.method381(var5.ab(), 6.0f);
            float var7 = var6 + 8.0f;
            if (var1 + var7 > 98.0f) {
                var1 = 7.0f;
                ++var3;
            }
            var1 = var1 + var7 + 3.0f;
            continue;
        }
        return 9.0f + ((float) var3) * var2 + ((float) (var3 - (-1466640336 ^ -1466640335))) * 3.0f + 2.0f;
    }

  public static float method1019(cK arg0) { // было: a
        float var1 = 0.0f;
        List var2 = arg0.method612();
        if (var2 == null) {
            return 0.0f;
        }
        int var3;
        List var5;
        int var6;
        if (!var2.isEmpty()) {
            var3 = -315544916 ^ -315544916;
            float var4 = 2.5f;
            var5 = var2.stream().filter(lp0 -> (((ClassA84) lp0)).method633()).toList();
            var6 = -1841398271 ^ -1841398271;
        } else {
            return 0.0f;
        }
        int var3;
        while (var6 < var5.size()) {
            ClassA84 var7 = ((ClassA84) var5.get(var6));
            var3 = -1158514948 ^ -1158514947;
            float var8 = 0.0f;
            if (var7 instanceof aM) {
                var8 = 12.0f;
            } else {
                if (!(var7 instanceof ai)) {
                    if (var7 instanceof bA) {
                        var8 = 22.0f;
                    } else {
                        if (!(var7 instanceof cA)) {
                            if (!(var7 instanceof aZ)) {
                                if (var7 instanceof ClassA90) {
                                    ClassA90 var10 = ((ClassA90) var7);
                                    var8 = method1018(var10);
                                }
                            } else {
                                aZ var9 = ((aZ) var7);
                                var8 = method1017(var9);
                            }
                        } else {
                            var8 = 22.0f;
                        }
                    }
                } else {
                    var8 = 12.0f;
                }
            }
            var1 = var1 + var8;
            if (var6 < var5.size() - (-896116069 ^ -896116070)) {
                var1 = var1 + var4;
            }
            ++var6;
            continue;
        }
        if (var3 != 0) {
            var1 = var1 + 6.0f;
        }
        return var1;
    }

  public static float method1020(cK arg0, float arg1) { // было: a
        return 19.0f + method1019(arg0) * arg1;
    }

  private static int bk(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int bl(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int bm(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}