// исходный (обфусцированный) внутренний класс: dev.angelvisuals.utility.mixin.client.render.LivingEntityRendererMixin
package dev.angelvisuals.utility.mixin.client.render;

import com.darkmagician6.eventapi.EventManager;
import com.llamalad7.mixinextras.sugar.Local;
import dev.angelvisuals.a.ClassA127;
import dev.angelvisuals.a.ClassA22;
import dev.angelvisuals.a.cF;
import net.minecraft.class_10042;
import net.minecraft.class_1921;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_583;
import net.minecraft.class_922;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin
public abstract class LivingEntityRendererMixin implements cF {

  public LivingEntityRendererMixin() { // было: <init>
        super();
    }

    @Shadow
    @Nullable
  protected abstract class_1921 method_24302(class_10042 arg0, boolean arg1, boolean arg2, boolean arg3);

    @Redirect
  private class_1921 renderHook(class_922 arg0, class_10042 arg1, boolean arg2, boolean arg3, boolean arg4) {
        if (ClassA127.dN < 0.0f) {
            if (!arg3) {
                if (arg1.field_53329 == 0.6000000238418579f) {
                    ClassA22 var6 = new ClassA22(-1);
                    EventManager.call(var6);
                    if (var6.isCancelled()) {
                        arg3 = 1;
                    }
                }
            }
            return method_24302(arg1, arg2, arg3, arg4);
        } else {
            return method_24302(arg1, arg2, true, arg4);
        }
    }

    @Redirect
  private void renderModelHook(class_583 arg0, class_4587 arg1, class_4588 arg2, int arg3, int arg4, int arg5, @Local(ordinal = 0, argsOnly = true) class_10042 arg6) {
        if (ClassA127.dN < 0.0f) {
            ClassA22 var8 = new ClassA22(arg5);
            if (arg6.field_53461) {
                EventManager.call(var8);
            }
            arg0.method_62100(arg1, arg2, arg3, arg4, var8.method238());
            return;
        } else {
            int var8 = ((int) (ClassA127.dN * 255.0f));
            int var9 = var8 << 24 | ClassA127.sJ & 16777215;
            arg0.method_62100(arg1, arg2, 15728880, 1048592, var9);
            return;
        }
    }

}