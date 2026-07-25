// исходный (обфусцированный) внутренний класс: dev.angelvisuals.utility.mixin.client.ScoreboarMixin
package dev.angelvisuals.utility.mixin.client;

import net.minecraft.class_268;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin
public abstract class ScoreboarMixin {

  public ScoreboarMixin() { // было: <init>
        super();
    }

    @Shadow
    @Nullable
  public abstract class_268 method_1164(String arg0);

    @Inject
  public void remove(String arg0, class_268 arg1, CallbackInfo arg2) {
        if (method_1164(arg0) != arg1) {
            arg2.cancel();
        }
    }

}