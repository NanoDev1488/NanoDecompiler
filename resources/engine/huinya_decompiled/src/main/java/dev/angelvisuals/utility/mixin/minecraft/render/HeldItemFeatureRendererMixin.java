// исходный (обфусцированный) внутренний класс: dev.angelvisuals.utility.mixin.minecraft.render.HeldItemFeatureRendererMixin
package dev.angelvisuals.utility.mixin.minecraft.render;

import dev.angelvisuals.a.ClassA127;
import net.minecraft.class_10426;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin
public class HeldItemFeatureRendererMixin {

  public HeldItemFeatureRendererMixin() { // было: <init>
        super();
    }

    @Inject
  private void onRender(class_4587 arg0, class_4597 arg1, int arg2, class_10426 arg3, float arg4, float arg5, CallbackInfo arg6) {
        if (ClassA127.dN >= 0.0f) {
            arg6.cancel();
        }
    }

}