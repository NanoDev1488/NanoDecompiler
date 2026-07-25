// исходный (обфусцированный) внутренний класс: dev.angelvisuals.utility.mixin.accessors.ScreenAccessor
package dev.angelvisuals.utility.mixin.accessors;

import net.minecraft.class_364;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin
public interface ScreenAccessor {

    @Invoker("addDrawableChild")
  public abstract class_364 angelvisuals$addDrawableChild(class_364 arg0);

}