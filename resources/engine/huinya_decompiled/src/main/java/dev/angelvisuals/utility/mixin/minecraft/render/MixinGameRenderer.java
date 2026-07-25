// исходный (обфусцированный) внутренний класс: dev.angelvisuals.utility.mixin.minecraft.render.MixinGameRenderer
package dev.angelvisuals.utility.mixin.minecraft.render;

import com.darkmagician6.eventapi.EventManager;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.angelvisuals.a.ClassA24;
import dev.angelvisuals.a.ag;
import dev.angelvisuals.a.al;
import dev.angelvisuals.a.ap;
import dev.angelvisuals.a.as;
import dev.angelvisuals.a.bZ;
import dev.angelvisuals.a.bx;
import dev.angelvisuals.a.cF;
import dev.angelvisuals.a.dD;
import dev.angelvisuals.a.dE;
import dev.angelvisuals.a.dP;
import dev.angelvisuals.a.dg;
import net.minecraft.class_10366;
import net.minecraft.class_1041;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_3695;
import net.minecraft.class_4587;
import net.minecraft.class_4599;
import net.minecraft.class_9779;
import org.joml.Matrix4f;
import org.joml.Matrix4fStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin
public abstract class MixinGameRenderer {

    // ---- поля ----
    @Shadow
  private float field_4005;
    @Shadow
  private float field_3988;
    @Shadow
  private float field_4004;

  public MixinGameRenderer() { // было: <init>
        super();
    }

    @Shadow
  public abstract float method_32796();

    @Inject
  public void getBasicProjectionMatrixHook(float arg0, CallbackInfoReturnable arg1) {
        ClassA24 var3 = new ClassA24();
        EventManager.call(var3);
        if (var3.isCancelled()) {
            Matrix4f var4 = new Matrix4f();
            if (field_4005 != 1.0f) {
                var4.translate(field_3988, -field_4004, 0.0f);
                var4.scale(field_4005, field_4005, 1.0f);
            }
            var4.perspective(arg0 * 0.01745329238474369f, var3.method295(), 0.05000000074505806f, method_32796());
            arg1.setReturnValue(var4);
        }
    }

    @ModifyExpressionValue
  private int hookGetFov(int arg0) {
        ag var2 = new ag();
        EventManager.call(var2);
        return !var2.isCancelled() ? arg0 : var2.method314();
    }

    @Inject
  private void angelvisuals$removeHurtTilt(class_4587 arg0, float arg1, CallbackInfo arg2) {
        if (dg.field358.ah()) {
            arg2.cancel();
        }
    }

    @Inject
  public void hookWorldRender(class_9779 arg0, CallbackInfo arg1, @Local(ordinal = 2) Matrix4f arg2) {
        class_4587 var4 = new class_4587();
        var4.method_34425(arg2);
        as.method1823(RenderSystem.getProjectionMatrix());
        as.method1824(RenderSystem.getModelViewMatrix());
        as.method1825(arg2);
        dD var5 = new dD(var4, arg0.method_60637(false));
        EventManager.call(var5);
        if (cF.field785.field_1687 != null) {
            al.method1844(var5.method324());
        }
        as.method1804(var5.method324());
    }

    @Inject
  private void renderScreenHook(class_9779 arg0, boolean arg1, CallbackInfo arg2, class_3695 arg3, boolean arg4, int arg5, int arg6, class_1041 arg7, Matrix4f arg8, Matrix4fStack arg9, class_332 arg10) {
        EventManager.call(new dP(bZ.method1677(arg10, arg5, arg6, cF.field785.method_61966().method_60637(false))));
    }

    @Inject
   void renderHudHook(class_9779 arg0, boolean arg1, CallbackInfo arg2) {
        triggerHudRenderEvent(arg0);
    }

    @Unique
  private void triggerHudRenderEvent(class_9779 arg0) {
        ap var2 = new ap(cF.field785.method_22940().method_23000());
        double var3 = class_310.method_1551().method_22683().method_4495();
        setScaleFactorOutAllMods(((double) dE.field472.bu()));
        RenderSystem.setProjectionMatrix(new Matrix4f().setOrtho(0.0f, ((float) cF.field785.method_22683().method_4486()), ((float) cF.field785.method_22683().method_4502()), 0.0f, 1000.0f, 21000.0f), class_10366.field_54954);
        RenderSystem.disableDepthTest();
        try {
            EventManager.call(new bx(var2, arg0.method_60637(false)));
        } catch (Exception var5) {
        }
        var2.method_51452();
        RenderSystem.enableDepthTest();
        setScaleFactorOutAllMods(var3);
        RenderSystem.setProjectionMatrix(new Matrix4f().setOrtho(0.0f, ((float) cF.field785.method_22683().method_4486()), ((float) cF.field785.method_22683().method_4502()), 0.0f, 1000.0f, 21000.0f), class_10366.field_54954);
    }

    @Unique
  public void setScaleFactorOutAllMods(double arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: getstatic  #39 // dev.angelvisuals.a.cF.c:Lnet/minecraft/class_310;
        //      3: invokevirtual  #86 // net.minecraft.class_310.method_22683:()Lnet/minecraft/class_1041;
        //      6: dload_1
        //      7: putfield  #46 // net.minecraft.class_1041.field_5179:D
        //     10: getstatic  #39 // dev.angelvisuals.a.cF.c:Lnet/minecraft/class_310;
        //     13: invokevirtual  #86 // net.minecraft.class_310.method_22683:()Lnet/minecraft/class_1041;
        //     16: getfield  #48 // net.minecraft.class_1041.field_5181:I
        //     19: i2d
        //     20: dload_1
        //     21: ddiv
        //     22: d2i
        //     23: istore_3
        //     24: getstatic  #39 // dev.angelvisuals.a.cF.c:Lnet/minecraft/class_310;
        //     27: invokevirtual  #86 // net.minecraft.class_310.method_22683:()Lnet/minecraft/class_1041;
        //     30: getstatic  #39 // dev.angelvisuals.a.cF.c:Lnet/minecraft/class_310;
        //     33: invokevirtual  #86 // net.minecraft.class_310.method_22683:()Lnet/minecraft/class_1041;
        //     36: getfield  #48 // net.minecraft.class_1041.field_5181:I
        //     39: i2d
        //     40: dload_1
        //     41: ddiv
        //     42: iload_3
        //     43: i2d
        //     44: dcmpl
        //     45: ifle  54 (offset +9)
        //     48: iload_3
        //     49: iconst_1
        //     50: iadd
        //     51: goto  55 (offset +4)
        //     54: iload_3
        //     55: putfield  #47 // net.minecraft.class_1041.field_5180:I
        //     58: getstatic  #39 // dev.angelvisuals.a.cF.c:Lnet/minecraft/class_310;
        //     61: invokevirtual  #86 // net.minecraft.class_310.method_22683:()Lnet/minecraft/class_1041;
        //     64: getfield  #50 // net.minecraft.class_1041.field_5196:I
        //     67: i2d
        //     68: dload_1
        //     69: ddiv
        //     70: d2i
        //     71: istore  4
        //     73: getstatic  #39 // dev.angelvisuals.a.cF.c:Lnet/minecraft/class_310;
        //     76: invokevirtual  #86 // net.minecraft.class_310.method_22683:()Lnet/minecraft/class_1041;
        //     79: getstatic  #39 // dev.angelvisuals.a.cF.c:Lnet/minecraft/class_310;
        //     82: invokevirtual  #86 // net.minecraft.class_310.method_22683:()Lnet/minecraft/class_1041;
        //     85: getfield  #50 // net.minecraft.class_1041.field_5196:I
        //     88: i2d
        //     89: dload_1
        //     90: ddiv
        //     91: iload  4
        //     93: i2d
        //     94: dcmpl
        //     95: ifle  105 (offset +10)
        //     98: iload  4
        //    100: iconst_1
        //    101: iadd
        //    102: goto  107 (offset +5)
        //    105: iload  4
        //    107: putfield  #49 // net.minecraft.class_1041.field_5194:I
        //    110: return
    }

}