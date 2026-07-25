// исходный (обфусцированный) внутренний класс: dev.angelvisuals.utility.mixin.minecraft.entity.LimbAnimatorMixin
package dev.angelvisuals.utility.mixin.minecraft.entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin
public interface LimbAnimatorMixin {

    @Accessor("pos")
    @Mutable
  public abstract void setPos(float arg0);

    @Accessor("speed")
    @Mutable
  public abstract void setSpeedField(float arg0);

    @Accessor("prevSpeed")
    @Mutable
  public abstract void setPrevSpeed(float arg0);

}