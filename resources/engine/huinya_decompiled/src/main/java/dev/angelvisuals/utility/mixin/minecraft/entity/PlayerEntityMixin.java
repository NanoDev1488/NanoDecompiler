// исходный (обфусцированный) внутренний класс: dev.angelvisuals.utility.mixin.minecraft.entity.PlayerEntityMixin
package dev.angelvisuals.utility.mixin.minecraft.entity;

import com.darkmagician6.eventapi.EventManager;
import dev.angelvisuals.a.dM;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin
public class PlayerEntityMixin {

  public PlayerEntityMixin() { // было: <init>
        super();
    }

    @Inject
  public void tickMovement(CallbackInfo arg0) {
        dM var2 = new dM();
        EventManager.call(var2);
    }

}