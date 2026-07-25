// исходный (обфусцированный) внутренний класс: dev.angelvisuals.utility.mixin.client.KeyboardMixin
package dev.angelvisuals.utility.mixin.client;

import com.darkmagician6.eventapi.EventManager;
import dev.angelvisuals.a.cY;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin
public class KeyboardMixin {

  public KeyboardMixin() { // было: <init>
        super();
    }

    @Inject
  public void triggerKeyEvent(long arg0, int arg1, int arg2, int arg3, int arg4, CallbackInfo arg5) {
        if (arg1 != -1) {
            EventManager.call(new cY(arg3, arg1));
        }
    }

}