// исходный (обфусцированный) внутренний класс: dev.angelvisuals.utility.mixin.client.ClientWorldMixin
package dev.angelvisuals.utility.mixin.client;

import com.darkmagician6.eventapi.EventManager;
import dev.angelvisuals.a.ct;
import net.minecraft.class_1297;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin
public class ClientWorldMixin {

  public ClientWorldMixin() { // было: <init>
        super();
    }

    @Inject
  public void injectAddEntity(class_1297 arg0, CallbackInfo arg1) {
        ct var3 = new ct(arg0);
        EventManager.call(var3);
    }

}