// исходный (обфусцированный) внутренний класс: dev.angelvisuals.utility.mixin.accessors.InGameHudAccessor
package dev.angelvisuals.utility.mixin.accessors;

import net.minecraft.class_332;
import net.minecraft.class_9779;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin
public interface InGameHudAccessor {

    @Invoker("renderHotbar")
  public abstract void invokeRenderHotbar(class_332 arg0, class_9779 arg1);

    @Invoker("renderStatusBars")
  public abstract void invokeRenderStatusBars(class_332 arg0);

}