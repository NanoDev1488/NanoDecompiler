// исходный (обфусцированный) внутренний класс: dev.angelvisuals.utility.mixin.client.BackGroundRendererMixin
package dev.angelvisuals.utility.mixin.client;

import com.darkmagician6.eventapi.EventManager;
import dev.angelvisuals.a.aN;
import dev.angelvisuals.a.cQ;
import net.minecraft.class_1297;
import net.minecraft.class_4184;
import net.minecraft.class_638;
import net.minecraft.class_6854;
import net.minecraft.class_758.class_4596;
import net.minecraft.class_9958;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin
public class BackGroundRendererMixin {

  public BackGroundRendererMixin() { // было: <init>
        super();
    }

    @Inject
  private static void onGetFogModifier(class_1297 arg0, float arg1, CallbackInfoReturnable arg2) {
        // (пустое тело)
    }

    @Inject
  private static void getFogColorHook(class_4184 arg0, float arg1, class_638 arg2, int arg3, float arg4, CallbackInfoReturnable arg5) {
        aN var6 = new aN();
        EventManager.call(var6);
        if (var6.isCancelled()) {
            int var7 = var6.method309();
            float var8 = ((float) (var7 >> 24 & 255)) / 255.0f;
            if (var8 == 0.0f) {
                var8 = 1.0f;
            }
            arg5.setReturnValue(new Vector4f(cQ.method1703(var7), cQ.method1704(var7), cQ.method1705(var7), var8));
        }
    }

    @Inject
  private static void modifyFog(class_4184 arg0, class_4596 arg1, Vector4f arg2, float arg3, boolean arg4, float arg5, CallbackInfoReturnable arg6) {
        aN var7 = new aN();
        EventManager.call(var7);
        if (var7.isCancelled()) {
            int var8 = var7.method309();
            float var9 = ((float) (var8 >> 24 & 255)) / 255.0f;
            if (var9 <= 0.0f) {
                var9 = 1.0f;
            }
            arg6.setReturnValue(new class_9958(var7.method308() * var7.method312(), var7.method308(), class_6854.field_36351, ((float) (var8 >> 16 & 255)) / 255.0f, ((float) (var8 >> 8 & 255)) / 255.0f, ((float) (var8 & 255)) / 255.0f, var9));
        }
    }

}