// исходный (обфусцированный) внутренний класс: dev.angelvisuals.utility.mixin.accessors.ItemStackAccessor
package dev.angelvisuals.utility.mixin.accessors;

import net.minecraft.class_9335;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin
public interface ItemStackAccessor {

    @Accessor("components")
  public abstract class_9335 getComponents();

}