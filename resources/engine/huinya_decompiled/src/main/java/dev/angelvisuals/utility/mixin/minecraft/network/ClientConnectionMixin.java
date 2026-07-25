// исходный (обфусцированный) внутренний класс: dev.angelvisuals.utility.mixin.minecraft.network.ClientConnectionMixin
package dev.angelvisuals.utility.mixin.minecraft.network;

import com.darkmagician6.eventapi.EventManager;
import dev.angelvisuals.a.ClassA140;
import dev.angelvisuals.a.aI;
import dev.angelvisuals.a.aI_ClassA27;
import dev.angelvisuals.a.cF;
import java.util.List;
import net.minecraft.class_2547;
import net.minecraft.class_2596;
import net.minecraft.class_310;
import net.minecraft.class_634;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin
public class ClientConnectionMixin implements cF {

    // ---- поля ----
    @Unique
  private static boolean stackOverflowFix;

  public ClientConnectionMixin() { // было: <init>
        super();
    }

    @Inject
  private static void triggerReceivePacketEvent(class_2596 arg0, class_2547 arg1, CallbackInfo arg2) {
        aI var3 = new aI(aI_ClassA27.field149, arg0);
        EventManager.call(var3);
        if (!var3.isCancelled()) {
            if (var3.method335() != arg0) {
                arg2.cancel();
                var3.method335().method_65081(arg1);
            }
        } else {
            arg2.cancel();
        }
    }

    @Inject
  public void triggerSendPacketEvent(class_2596 arg0, CallbackInfo arg1) {
        if (!ClassA140.method1181().contains(arg0)) {
            if (!stackOverflowFix) {
                aI var3 = new aI(aI_ClassA27.field148, arg0);
                EventManager.call(var3);
                if (var3.isCancelled()) {
                    arg1.cancel();
                }
                class_2596 var4 = var3.method335();
                if (var4 != arg0) {
                    arg1.cancel();
                    stackOverflowFix = true;
                    mc.method_1562().method_52787(var4);
                    stackOverflowFix = false;
                }
            }
        } else {
            ClassA140.method1181().remove(arg0);
        }
    }

}