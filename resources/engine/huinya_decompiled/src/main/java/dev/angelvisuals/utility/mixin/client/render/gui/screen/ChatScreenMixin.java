// исходный (обфусцированный) внутренний класс: dev.angelvisuals.utility.mixin.client.render.gui.screen.ChatScreenMixin
package dev.angelvisuals.utility.mixin.client.render.gui.screen;

import dev.angelvisuals.a.cF;
import net.minecraft.class_2561;
import net.minecraft.class_437;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin
public class ChatScreenMixin extends class_437 implements cF {

  protected ChatScreenMixin(class_2561 arg0) { // было: <init>
        super(arg0);
    }

    @Inject
  private void onSendMessage(String arg0, boolean arg1, CallbackInfo arg2) {
        // (пустое тело)
    }

}