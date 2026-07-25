// исходный (обфусцированный) внутренний класс: dev.angelvisuals.utility.mixin.client.render.gui.hud.GameOverlayRendererMixin
package dev.angelvisuals.utility.mixin.client.render.gui.hud;

import dev.angelvisuals.a.bY;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin
public class GameOverlayRendererMixin {

  public GameOverlayRendererMixin() { // было: <init>
        super();
    }

    @Inject
  private static void removeFireOverlay(class_4587 arg0, class_4597 arg1, CallbackInfo arg2) {
        if (bY.field528.method902()) {
            arg2.cancel();
        }
    }

}