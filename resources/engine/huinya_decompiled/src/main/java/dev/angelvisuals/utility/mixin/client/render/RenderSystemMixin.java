// исходный (обфусцированный) внутренний класс: dev.angelvisuals.utility.mixin.client.render.RenderSystemMixin
package dev.angelvisuals.utility.mixin.client.render;

import dev.angelvisuals.a.bu;
import net.minecraft.class_10156;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin
public class RenderSystemMixin {

  public RenderSystemMixin() { // было: <init>
        super();
    }

    @ModifyVariable
  private static class_10156 modifyShaderKey(class_10156 arg0) {
        if (!bu.ac) {
            return arg0;
        } else {
            class_10156 var1 = bu.method1857().method1860(arg0);
            if (var1 == null) {
                return arg0;
            } else {
                return var1;
            }
        }
    }

}