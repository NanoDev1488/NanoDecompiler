// исходный (обфусцированный) внутренний класс: dev.angelvisuals.utility.mixin.client.WorldRendererMixin
package dev.angelvisuals.utility.mixin.client;

import com.darkmagician6.eventapi.EventManager;
import dev.angelvisuals.a.bg;
import dev.angelvisuals.a.dd;
import net.minecraft.class_243;
import net.minecraft.class_4063;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_9909;
import net.minecraft.class_9916;
import net.minecraft.class_9958;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin
public class WorldRendererMixin {

  public WorldRendererMixin() { // было: <init>
        super();
    }

    @Redirect
  private void onSetRenderer(class_9916 arg0, Runnable arg1) {
        if (!dd.field428.ah()) {
            arg0.method_61929(arg1);
        } else {
            arg0.method_61929(() -> lambda$onSetRenderer$0());
        }
    }

    @Inject
  private void onRenderSky(class_9909 arg0, class_4184 arg1, float arg2, class_9958 arg3, CallbackInfo arg4) {
        EventManager.call(new bg(new class_4587(), new Matrix4f(), arg2));
    }

    @Inject
  private void onRenderClouds(class_9909 arg0, Matrix4f arg1, Matrix4f arg2, class_4063 arg3, class_243 arg4, float arg5, int arg6, float arg7, CallbackInfo arg8) {
        if (dd.field428.ah()) {
            arg8.cancel();
        }
    }

  private static void lambda$onSetRenderer$0() {
        dd.field428.aV();
    }

}