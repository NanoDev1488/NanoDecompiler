// исходный (обфусцированный) внутренний класс: dev.angelvisuals.utility.mixin.accessors.CameraAccessor
package dev.angelvisuals.utility.mixin.accessors;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin
public interface CameraAccessor {

    @Invoker("setRotation")
  public abstract void setCustomRotation(float arg0, float arg1);

    @Invoker("clipToSpace")
  public abstract float setClipToSpace(float arg0);

}