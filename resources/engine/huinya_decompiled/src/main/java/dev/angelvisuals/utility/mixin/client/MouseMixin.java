// исходный (обфусцированный) внутренний класс: dev.angelvisuals.utility.mixin.client.MouseMixin
package dev.angelvisuals.utility.mixin.client;

import com.darkmagician6.eventapi.EventManager;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import dev.angelvisuals.a.bF;
import dev.angelvisuals.a.bO;
import dev.angelvisuals.a.bb;
import dev.angelvisuals.a.cF;
import dev.angelvisuals.a.cY;
import net.minecraft.class_1041;
import net.minecraft.class_310;
import net.minecraft.class_312;
import net.minecraft.class_3540;
import net.minecraft.class_437;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin
public class MouseMixin {

    // ---- поля ----
    @Shadow
    @Final
  private class_310 field_1779;
    @Shadow
  private double field_1789;
    @Shadow
  private double field_1787;
    @Shadow
  private class_3540 field_1793;
    @Shadow
  private class_3540 field_1782;
    @Shadow
  private int field_1780;

  public MouseMixin() { // было: <init>
        super();
    }

    @Inject
  private void onMouseButton(long arg0, int arg1, int arg2, int arg3, CallbackInfo arg4) {
        if (arg1 != -1) {
            if (arg0 == cF.field785.method_22683().method_4490()) {
                EventManager.call(new cY(arg2, arg1));
                EventManager.call(new bF(arg1, arg2));
            }
        }
    }

    @Inject
  public void onMouseScrollHook(long arg0, double arg1, double arg2, CallbackInfo arg3) {
        bb var8 = new bb(arg1, arg2);
        EventManager.call(var8);
        if (var8.isCancelled()) {
            arg3.cancel();
        }
    }

    @Redirect
  public boolean onIsCursorLocked(class_312 arg0) {
        return arg0.method_1613() ? 1 : isAnim();
    }

    @WrapWithCondition
  private boolean modifyMouseRotationInput(class_746 arg0, double arg1, double arg2) {
        bO var6 = new bO(((float) arg1), ((float) arg2));
        EventManager.call(var6);
        if (!var6.isCancelled()) {
            arg0.method_5872(((double) var6.av()), ((double) var6.aw()));
            return false;
        } else {
            return false;
        }
    }

    @Unique
  private boolean isAnim() {
        class_437 var1 = class_310.method_1551().field_1755;
        return false;
    }

    @Inject
  private void onUpdateMouse(double arg0, CallbackInfo arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #31 // dev.angelvisuals.utility.mixin.client.MouseMixin.field_1779:Lnet/minecraft/class_310;
        //      4: getfield  #37 // net.minecraft.class_310.field_1724:Lnet/minecraft/class_746;
        //      7: ifnonnull  11 (offset +4)
        //     10: return
        //     11: aload_0
        //     12: getfield  #31 // dev.angelvisuals.utility.mixin.client.MouseMixin.field_1779:Lnet/minecraft/class_310;
        //     15: getfield  #36 // net.minecraft.class_310.field_1690:Lnet/minecraft/class_315;
        //     18: invokevirtual  #65 // net.minecraft.class_315.method_42495:()Lnet/minecraft/class_7172;
        //     21: invokevirtual  #69 // net.minecraft.class_7172.method_41753:()Ljava/lang/Object;
        //     24: checkcast  #11 // java.lang.Double
        //     27: invokevirtual  #55 // java.lang.Double.doubleValue:()D
        //     30: ldc2_w  #26 // 0.6d
        //     33: dmul
        //     34: ldc2_w  #24 // 0.2d
        //     37: dadd
        //     38: dstore  4
        //     40: dload  4
        //     42: dload  4
        //     44: dmul
        //     45: dload  4
        //     47: dmul
        //     48: ldc2_w  #28 // 8.0d
        //     51: dmul
        //     52: dstore  6
        //     54: aload_0
        //     55: getfield  #31 // dev.angelvisuals.utility.mixin.client.MouseMixin.field_1779:Lnet/minecraft/class_310;
        //     58: getfield  #36 // net.minecraft.class_310.field_1690:Lnet/minecraft/class_315;
        //     61: getfield  #39 // net.minecraft.class_315.field_1914:Z
        //     64: ifeq  110 (offset +46)
        //     67: aload_0
        //     68: getfield  #35 // dev.angelvisuals.utility.mixin.client.MouseMixin.field_1793:Lnet/minecraft/class_3540;
        //     71: aload_0
        //     72: getfield  #34 // dev.angelvisuals.utility.mixin.client.MouseMixin.field_1789:D
        //     75: dload  6
        //     77: dmul
        //     78: dload_1
        //     79: dload  6
        //     81: dmul
        //     82: invokevirtual  #67 // net.minecraft.class_3540.method_15429:(DD)D
        //     85: dstore  8
        //     87: aload_0
        //     88: getfield  #32 // dev.angelvisuals.utility.mixin.client.MouseMixin.field_1782:Lnet/minecraft/class_3540;
        //     91: aload_0
        //     92: getfield  #33 // dev.angelvisuals.utility.mixin.client.MouseMixin.field_1787:D
        //     95: dload  6
        //     97: dmul
        //     98: dload_1
        //     99: dload  6
        //    101: dmul
        //    102: invokevirtual  #67 // net.minecraft.class_3540.method_15429:(DD)D
        //    105: dstore  10
        //    107: goto  218 (offset +111)
        //    110: aload_0
        //    111: getfield  #31 // dev.angelvisuals.utility.mixin.client.MouseMixin.field_1779:Lnet/minecraft/class_310;
        //    114: getfield  #36 // net.minecraft.class_310.field_1690:Lnet/minecraft/class_315;
        //    117: invokevirtual  #63 // net.minecraft.class_315.method_31044:()Lnet/minecraft/class_5498;
        //    120: invokevirtual  #68 // net.minecraft.class_5498.method_31034:()Z
        //    123: ifeq  186 (offset +63)
        //    126: aload_0
        //    127: getfield  #31 // dev.angelvisuals.utility.mixin.client.MouseMixin.field_1779:Lnet/minecraft/class_310;
        //    130: getfield  #37 // net.minecraft.class_310.field_1724:Lnet/minecraft/class_746;
        //    133: invokevirtual  #70 // net.minecraft.class_746.method_31550:()Z
        //    136: ifeq  186 (offset +50)
        //    139: aload_0
        //    140: getfield  #35 // dev.angelvisuals.utility.mixin.client.MouseMixin.field_1793:Lnet/minecraft/class_3540;
        //    143: invokevirtual  #66 // net.minecraft.class_3540.method_15428:()V
        //    146: aload_0
        //    147: getfield  #32 // dev.angelvisuals.utility.mixin.client.MouseMixin.field_1782:Lnet/minecraft/class_3540;
        //    150: invokevirtual  #66 // net.minecraft.class_3540.method_15428:()V
        //    153: aload_0
        //    154: getfield  #34 // dev.angelvisuals.utility.mixin.client.MouseMixin.field_1789:D
        //    157: dload  4
        //    159: dmul
        //    160: dload  4
        //    162: dmul
        //    163: dload  4
        //    165: dmul
        //    166: dstore  8
        //    168: aload_0
        //    169: getfield  #33 // dev.angelvisuals.utility.mixin.client.MouseMixin.field_1787:D
        //    172: dload  4
        //    174: dmul
        //    175: dload  4
        //    177: dmul
        //    178: dload  4
        //    180: dmul
        //    181: dstore  10
        //    183: goto  218 (offset +35)
        //    186: aload_0
        //    187: getfield  #35 // dev.angelvisuals.utility.mixin.client.MouseMixin.field_1793:Lnet/minecraft/class_3540;
        //    190: invokevirtual  #66 // net.minecraft.class_3540.method_15428:()V
        //    193: aload_0
        //    194: getfield  #32 // dev.angelvisuals.utility.mixin.client.MouseMixin.field_1782:Lnet/minecraft/class_3540;
        //    197: invokevirtual  #66 // net.minecraft.class_3540.method_15428:()V
        //    200: aload_0
        //    201: getfield  #34 // dev.angelvisuals.utility.mixin.client.MouseMixin.field_1789:D
        //    204: dload  6
        //    206: dmul
        //    207: dstore  8
        //    209: aload_0
        //    210: getfield  #33 // dev.angelvisuals.utility.mixin.client.MouseMixin.field_1787:D
        //    213: dload  6
        //    215: dmul
        //    216: dstore  10
        //    218: aload_0
        //    219: getfield  #31 // dev.angelvisuals.utility.mixin.client.MouseMixin.field_1779:Lnet/minecraft/class_310;
        //    222: getfield  #36 // net.minecraft.class_310.field_1690:Lnet/minecraft/class_315;
        //    225: invokevirtual  #64 // net.minecraft.class_315.method_42438:()Lnet/minecraft/class_7172;
        //    228: invokevirtual  #69 // net.minecraft.class_7172.method_41753:()Ljava/lang/Object;
        //    231: checkcast  #10 // java.lang.Boolean
        //    234: invokevirtual  #54 // java.lang.Boolean.booleanValue:()Z
        //    237: ifeq  244 (offset +7)
        //    240: iconst_m1
        //    241: goto  245 (offset +4)
        //    244: iconst_1
        //    245: istore  12
        //    247: new  #8 // dev.angelvisuals.a.du
        //    250: dup
        //    251: dload  8
        //    253: dload  10
        //    255: iload  12
        //    257: i2d
        //    258: dmul
        //    259: invokespecial  #49 // dev.angelvisuals.a.du.<init>:(DD)V
        //    262: astore  13
        //    264: aload  13
        //    266: invokestatic  #40 // com.darkmagician6.eventapi.EventManager.call:(Lcom/darkmagician6/eventapi/events/Event;)Lcom/darkmagician6/eventapi/events/Event;
        //    269: pop
        //    270: aload  13
        //    272: invokevirtual  #50 // dev.angelvisuals.a.du.isCancelled:()Z
        //    275: ifne  318 (offset +43)
        //    278: aload_0
        //    279: getfield  #31 // dev.angelvisuals.utility.mixin.client.MouseMixin.field_1779:Lnet/minecraft/class_310;
        //    282: invokevirtual  #60 // net.minecraft.class_310.method_1577:()Lnet/minecraft/class_1156;
        //    285: aload  13
        //    287: invokevirtual  #51 // dev.angelvisuals.a.du.l:()D
        //    290: aload  13
        //    292: invokevirtual  #52 // dev.angelvisuals.a.du.m:()D
        //    295: invokevirtual  #58 // net.minecraft.class_1156.method_4908:(DD)V
        //    298: aload_0
        //    299: getfield  #31 // dev.angelvisuals.utility.mixin.client.MouseMixin.field_1779:Lnet/minecraft/class_310;
        //    302: getfield  #37 // net.minecraft.class_310.field_1724:Lnet/minecraft/class_746;
        //    305: aload  13
        //    307: invokevirtual  #51 // dev.angelvisuals.a.du.l:()D
        //    310: aload  13
        //    312: invokevirtual  #52 // dev.angelvisuals.a.du.m:()D
        //    315: invokevirtual  #71 // net.minecraft.class_746.method_5872:(DD)V
        //    318: aload_0
        //    319: dconst_0
        //    320: putfield  #34 // dev.angelvisuals.utility.mixin.client.MouseMixin.field_1789:D
        //    323: aload_0
        //    324: dconst_0
        //    325: putfield  #33 // dev.angelvisuals.utility.mixin.client.MouseMixin.field_1787:D
        //    328: aload_3
        //    329: invokevirtual  #72 // org.spongepowered.asm.mixin.injection.callback.CallbackInfo.cancel:()V
        //    332: goto  337 (offset +5)
        //    335: astore  4
        //    337: return
        //       Exception table:
        //         from 0 to 10 target 335 type java.lang.Exception
        //         from 11 to 332 target 335 type java.lang.Exception
    }

}