// исходный (обфусцированный) внутренний класс: dev.angelvisuals.utility.mixin.client.HandledScreenMixin
package dev.angelvisuals.utility.mixin.client;

import com.darkmagician6.eventapi.EventManager;
import dev.angelvisuals.a.cf;
import net.minecraft.class_1735;
import net.minecraft.class_332;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin
public abstract class HandledScreenMixin {

    // ---- поля ----
    @Shadow
  protected int field_2792;
    @Shadow
  protected int field_2779;
    @Shadow
    @Nullable
  protected class_1735 field_2787;

  public HandledScreenMixin() { // было: <init>
        super();
    }

    @Inject
  public void render(class_332 arg0, int arg1, int arg2, float arg3, CallbackInfo arg4) {
        EventManager.call(new cf(arg0, field_2787, field_2792, field_2779));
    }

}