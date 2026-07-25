// исходный (обфусцированный) внутренний класс: dev.angelvisuals.utility.mixin.accessors.DrawContextAccessor
package dev.angelvisuals.utility.mixin.accessors;

import net.minecraft.class_1799;
import net.minecraft.class_4597.class_4598;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin
public interface DrawContextAccessor {

    @Accessor("vertexConsumers")
  public abstract class_4598 getVertexConsumers();

    @Invoker("drawItemBar")
  public abstract void callDrawItemBar(class_1799 arg0, int arg1, int arg2);

    @Invoker("drawCooldownProgress")
  public abstract void callDrawCooldownProgress(class_1799 arg0, int arg1, int arg2);

}