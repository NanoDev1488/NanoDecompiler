// исходный (обфусцированный) внутренний класс: dev.angelvisuals.utility.mixin.client.LightmapTextureManagerMixin
package dev.angelvisuals.utility.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.angelvisuals.a.ce;
import org.spongepowered.asm.mixin.Mixin;

@Mixin
public class LightmapTextureManagerMixin {

  public LightmapTextureManagerMixin() { // было: <init>
        super();
    }

    @ModifyExpressionValue
  private Object injectXRayFullBright(Object arg0) {
        ce var2 = ce.field455;
        return !var2.ah() ? arg0 : Double.valueOf(Math.max((((Double) arg0)).doubleValue(), 10.0));
    }

}