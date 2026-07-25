// исходный (обфусцированный) внутренний класс: dev.angelvisuals.utility.mixin.client.HeldItemRendererMixin
package dev.angelvisuals.utility.mixin.client;

import dev.angelvisuals.a.ClassA108;
import dev.angelvisuals.a.cJ;
import dev.angelvisuals.a.cO;
import net.minecraft.class_1268;
import net.minecraft.class_1306;
import net.minecraft.class_1799;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_4597.class_4598;
import net.minecraft.class_742;
import net.minecraft.class_746;
import net.minecraft.class_759;
import net.minecraft.class_9334;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin
public abstract class HeldItemRendererMixin {

    // ---- поля ----
    @Shadow
  private class_1799 field_4047;
    @Shadow
  private float field_4043;
    @Shadow
  private float field_4053;
    @Shadow
  private float field_4051;
    @Shadow
  private float field_4052;
    @Shadow
  private class_1799 field_4048;

  public HeldItemRendererMixin() { // было: <init>
        super();
    }

    @Shadow
  protected abstract void method_3228(class_742 arg0, float arg1, float arg2, class_1268 arg3, float arg4, class_1799 arg5, float arg6, class_4587 arg7, class_4597 arg8, int arg9);

    @Shadow
  protected abstract void method_65816(float arg0, float arg1, class_4587 arg2, int arg3, class_1306 arg4);

    @Inject
  public void injectBeforeRenderCrossBowItem(class_742 arg0, float arg1, float arg2, class_1268 arg3, float arg4, class_1799 arg5, float arg6, class_4587 arg7, class_4597 arg8, int arg9, CallbackInfo arg10) {
        boolean __stk1;
        class_1306 __stk2;
        cO var12 = cO.field593;
        if (var12.ah()) {
            __stk1 = arg3 == class_1268.field_5808;
            int var13 = __stk1;
            __stk2 = var13 == 0 ? arg0.method_6068().method_5928() : arg0.method_6068();
            class_1306 var14 = __stk2;
            var12.method963(arg7, ((class_1306) var14));
        }
    }

    @Inject
  public void injectBeforeRenderItem(class_742 arg0, float arg1, float arg2, class_1268 arg3, float arg4, class_1799 arg5, float arg6, class_4587 arg7, class_4597 arg8, int arg9, CallbackInfo arg10) {
        boolean __stk1;
        class_1306 __stk2;
        cO var12 = cO.field593;
        if (var12.ah()) {
            __stk1 = arg3 == class_1268.field_5808;
            int var13 = __stk1;
            __stk2 = var13 == 0 ? arg0.method_6068().method_5928() : arg0.method_6068();
            class_1306 var14 = __stk2;
            var12.method963(arg7, ((class_1306) var14));
        }
    }

    @Inject
  public void injectAfterMatrixPushHandPosition(class_742 arg0, float arg1, float arg2, class_1268 arg3, float arg4, class_1799 arg5, float arg6, class_4587 arg7, class_4597 arg8, int arg9, CallbackInfo arg10) {
        boolean __stk1;
        class_1306 __stk2;
        class_1306 __stk3;
        cO var12 = cO.field593;
        int var13;
        if (var12.ah()) {
            if (!arg5.method_7960()) {
                if (!arg5.method_57826(class_9334.field_49646)) {
                    __stk1 = arg3 == class_1268.field_5808;
                    var13 = __stk1;
                    __stk2 = var13 == 0 ? arg0.method_6068().method_5928() : arg0.method_6068();
                    class_1306 var14 = __stk2;
                    var12.method964(arg7, ((class_1306) var14));
                }
            }
        }
        __stk3 = arg3 != class_1268.field_5808 ? arg0.method_6068().method_5928() : arg0.method_6068();
        class_1306 var13 = __stk3;
        ClassA108.field456.method835(arg7, arg5, ((class_1306) var13), arg4);
    }

    @Redirect
  public void redirectSwingArmForCustomAnim(class_759 arg0, float arg1, float arg2, class_4587 arg3, int arg4, class_1306 arg5) {
        cJ var7 = cJ.field567;
        if (!var7.ah()) {
            method_65816(arg1, arg2, arg3, arg4, arg5);
        } else {
            if (!var7.ag()) {
                method_65816(arg1, arg2, arg3, arg4, arg5);
            } else {
                if (arg5 != class_1306.field_6183) {
                    method_65816(arg1, arg2, arg3, arg4, arg5);
                } else {
                    var7.method956(arg3, arg1, arg2, arg5);
                }
            }
        }
    }

    @Overwrite
  public void method_22976(float arg0, class_4587 arg1, class_4598 arg2, class_746 arg3, int arg4) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload  4
        //      2: fload_1
        //      3: invokevirtual  #62 // net.minecraft.class_746.method_6055:(F)F
        //      6: fstore  6
        //      8: aload  4
        //     10: getfield  #39 // net.minecraft.class_746.field_6266:Lnet/minecraft/class_1268;
        //     13: getstatic  #36 // net.minecraft.class_1268.field_5808:Lnet/minecraft/class_1268;
        //     16: invokestatic  #43 // com.google.common.base.MoreObjects.firstNonNull:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //     19: checkcast  #12 // net.minecraft.class_1268
        //     22: astore  7
        //     24: aload  4
        //     26: fload_1
        //     27: invokevirtual  #63 // net.minecraft.class_746.method_61414:(F)F
        //     30: fstore  8
        //     32: aload  4
        //     34: invokestatic  #64 // net.minecraft.class_759.method_33303:(Lnet/minecraft/class_746;)Lnet/minecraft/class_759$class_5773;
        //     37: astore  9
        //     39: getstatic  #25 // dev.angelvisuals.a.ae.a:Ldev/angelvisuals/a/ae;
        //     42: invokevirtual  #44 // dev.angelvisuals.a.ae.ah:()Z
        //     45: ifne  57 (offset +12)
        //     48: getstatic  #29 // dev.angelvisuals.a.w.a:Ldev/angelvisuals/a/w;
        //     51: invokevirtual  #52 // dev.angelvisuals.a.w.ah:()Z
        //     54: ifeq  61 (offset +7)
        //     57: iconst_1
        //     58: goto  62 (offset +4)
        //     61: iconst_0
        //     62: istore  10
        //     64: iload  10
        //     66: ifeq  73 (offset +7)
        //     69: iconst_1
        //     70: putstatic  #26 // dev.angelvisuals.a.bu.ac:Z
        //     73: aload  9
        //     75: getfield  #40 // net.minecraft.class_759$class_5773.field_28387:Z
        //     78: ifeq  137 (offset +59)
        //     81: aload  7
        //     83: getstatic  #36 // net.minecraft.class_1268.field_5808:Lnet/minecraft/class_1268;
        //     86: if_acmpne  94 (offset +8)
        //     89: fload  6
        //     91: goto  95 (offset +4)
        //     94: fconst_0
        //     95: fstore  11
        //     97: fconst_1
        //     98: fload_1
        //     99: aload_0
        //    100: getfield  #35 // dev.angelvisuals.utility.mixin.client.HeldItemRendererMixin.field_4053:F
        //    103: aload_0
        //    104: getfield  #30 // dev.angelvisuals.utility.mixin.client.HeldItemRendererMixin.field_4043:F
        //    107: invokestatic  #59 // net.minecraft.class_3532.method_16439:(FFF)F
        //    110: fsub
        //    111: fstore  12
        //    113: aload_0
        //    114: aload  4
        //    116: fload_1
        //    117: fload  8
        //    119: getstatic  #36 // net.minecraft.class_1268.field_5808:Lnet/minecraft/class_1268;
        //    122: fload  11
        //    124: aload_0
        //    125: getfield  #31 // dev.angelvisuals.utility.mixin.client.HeldItemRendererMixin.field_4047:Lnet/minecraft/class_1799;
        //    128: fload  12
        //    130: aload_2
        //    131: aload_3
        //    132: iload  5
        //    134: invokevirtual  #53 // dev.angelvisuals.utility.mixin.client.HeldItemRendererMixin.method_3228:(Lnet/minecraft/class_742;FFLnet/minecraft/class_1268;FLnet/minecraft/class_1799;FLnet/minecraft/class_4587;Lnet/minecraft/class_4597;I)V
        //    137: aload  9
        //    139: getfield  #41 // net.minecraft.class_759$class_5773.field_28388:Z
        //    142: ifeq  201 (offset +59)
        //    145: aload  7
        //    147: getstatic  #37 // net.minecraft.class_1268.field_5810:Lnet/minecraft/class_1268;
        //    150: if_acmpne  158 (offset +8)
        //    153: fload  6
        //    155: goto  159 (offset +4)
        //    158: fconst_0
        //    159: fstore  11
        //    161: fconst_1
        //    162: fload_1
        //    163: aload_0
        //    164: getfield  #33 // dev.angelvisuals.utility.mixin.client.HeldItemRendererMixin.field_4051:F
        //    167: aload_0
        //    168: getfield  #34 // dev.angelvisuals.utility.mixin.client.HeldItemRendererMixin.field_4052:F
        //    171: invokestatic  #59 // net.minecraft.class_3532.method_16439:(FFF)F
        //    174: fsub
        //    175: fstore  12
        //    177: aload_0
        //    178: aload  4
        //    180: fload_1
        //    181: fload  8
        //    183: getstatic  #37 // net.minecraft.class_1268.field_5810:Lnet/minecraft/class_1268;
        //    186: fload  11
        //    188: aload_0
        //    189: getfield  #32 // dev.angelvisuals.utility.mixin.client.HeldItemRendererMixin.field_4048:Lnet/minecraft/class_1799;
        //    192: fload  12
        //    194: aload_2
        //    195: aload_3
        //    196: iload  5
        //    198: invokevirtual  #53 // dev.angelvisuals.utility.mixin.client.HeldItemRendererMixin.method_3228:(Lnet/minecraft/class_742;FFLnet/minecraft/class_1268;FLnet/minecraft/class_1799;FLnet/minecraft/class_4587;Lnet/minecraft/class_4597;I)V
        //    201: aload_3
        //    202: invokevirtual  #60 // net.minecraft.class_4597$class_4598.method_22993:()V
        //    205: iload  10
        //    207: ifeq  231 (offset +24)
        //    210: iconst_0
        //    211: putstatic  #26 // dev.angelvisuals.a.bu.ac:Z
        //    214: goto  231 (offset +17)
        //    217: astore  13
        //    219: iload  10
        //    221: ifeq  228 (offset +7)
        //    224: iconst_0
        //    225: putstatic  #26 // dev.angelvisuals.a.bu.ac:Z
        //    228: aload  13
        //    230: athrow
        //    231: return
        //       Exception table:
        //         from 73 to 205 target 217 type any
        //         from 217 to 219 target 217 type any
    }

}