// исходный (обфусцированный) внутренний класс: dev.angelvisuals.utility.mixin.client.render.WindowMixin
package dev.angelvisuals.utility.mixin.client.render;

import com.darkmagician6.eventapi.EventManager;
import dev.angelvisuals.a.dz;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin
public class WindowMixin {

  public WindowMixin() { // было: <init>
        super();
    }

    @Inject
  private void onWindowSizeChanged(long arg0, int arg1, int arg2, CallbackInfo arg3) {
        EventManager.call(new dz());
    }

}