// исходный (обфусцированный) внутренний класс: dev.angelvisuals.utility.mixin.client.sound.SoundSystemMixin
package dev.angelvisuals.utility.mixin.client.sound;

import net.minecraft.class_1113;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin
public abstract class SoundSystemMixin {

  public SoundSystemMixin() { // было: <init>
        super();
    }

    @Inject
  private void onPlay(class_1113 arg0, CallbackInfo arg1) {
        // (пустое тело)
    }

}