// исходный (обфусцированный) внутренний класс: dev.angelvisuals.utility.mixin.client.PlayerEntityMixin
package dev.angelvisuals.utility.mixin.client;

import dev.angelvisuals.a.bA;
import dev.angelvisuals.a.cJ;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin
public abstract class PlayerEntityMixin {

  public PlayerEntityMixin() { // было: <init>
        super();
    }

    @Inject
  private void getArmSwingAnimationEnd(CallbackInfoReturnable arg0) {
        cJ var2 = cJ.field567;
        if (var2.ah()) {
            arg0.setReturnValue(Integer.valueOf(((int) var2.field571.bp()) * 2));
        }
    }

}