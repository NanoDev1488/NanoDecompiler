// исходный (обфусцированный) внутренний класс: dev.angelvisuals.utility.mixin.accessors.HandledScreenAccessor
package dev.angelvisuals.utility.mixin.accessors;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin
public interface HandledScreenAccessor {

    @Accessor("x")
  public abstract int getX();

    @Accessor("y")
  public abstract int getY();

}