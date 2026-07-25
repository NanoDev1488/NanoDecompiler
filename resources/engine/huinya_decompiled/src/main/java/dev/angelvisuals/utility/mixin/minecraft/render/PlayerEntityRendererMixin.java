// исходный (обфусцированный) внутренний класс: dev.angelvisuals.utility.mixin.minecraft.render.PlayerEntityRendererMixin
package dev.angelvisuals.utility.mixin.minecraft.render;

import dev.angelvisuals.a.bQ;
import net.minecraft.class_10055;
import net.minecraft.class_2561;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin
public class PlayerEntityRendererMixin {

  public PlayerEntityRendererMixin() { // было: <init>
        super();
    }

    @Inject
  public void render(class_10055 arg0, class_2561 arg1, class_4587 arg2, class_4597 arg3, int arg4, CallbackInfo arg5) {
        if (bQ.field520.ah()) {
            arg5.cancel();
        }
    }

}