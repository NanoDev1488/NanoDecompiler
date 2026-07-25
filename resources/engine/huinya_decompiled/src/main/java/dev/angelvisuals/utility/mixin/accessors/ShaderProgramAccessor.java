// исходный (обфусцированный) внутренний класс: dev.angelvisuals.utility.mixin.accessors.ShaderProgramAccessor
package dev.angelvisuals.utility.mixin.accessors;

import java.util.Map;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin
public interface ShaderProgramAccessor {

    @Accessor
  public abstract Map getUniformsByName();

}