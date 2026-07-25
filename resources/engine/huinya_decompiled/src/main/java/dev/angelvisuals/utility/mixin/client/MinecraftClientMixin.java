// исходный (обфусцированный) внутренний класс: dev.angelvisuals.utility.mixin.client.MinecraftClientMixin
package dev.angelvisuals.utility.mixin.client;

import com.darkmagician6.eventapi.EventManager;
import dev.angelvisuals.AngelVisuals;
import dev.angelvisuals.a.bP;
import dev.angelvisuals.a.cz;
import dev.angelvisuals.a.dJ;
import dev.angelvisuals.a.dl;
import net.minecraft.class_1297;
import net.minecraft.class_156;
import net.minecraft.class_310;
import net.minecraft.class_437;
import net.minecraft.class_542;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin
public abstract class MinecraftClientMixin {

    // ---- поля ----
    @Unique
  private long lastHookTime;
    @Unique
  private int accumulatedCalls;

  public MinecraftClientMixin() { // было: <init>
        super();
        lastHookTime = class_156.method_648();
        accumulatedCalls = 0;
    }

    @Inject
  public void init(class_542 arg0, CallbackInfo arg1) {
        AngelVisuals.getInstance().init();
    }

    @ModifyVariable
  private class_437 mixin$modifySetScreenArg(class_437 arg0) {
        dJ var2 = new dJ(arg0);
        EventManager.call(var2);
        return var2.method255();
    }

    @Inject
  private void render(boolean arg0, CallbackInfo arg1) {
        long var3 = class_156.method_648();
        long var5 = var3 - lastHookTime;
        accumulatedCalls = accumulatedCalls + ((int) (var5 / 4166666L));
        lastHookTime = lastHookTime + ((long) accumulatedCalls) * 4166666L;
        accumulatedCalls = Math.min(accumulatedCalls, 240);
        while (accumulatedCalls > 0) {
            EventManager.call(new dl());
            accumulatedCalls = accumulatedCalls - 1;
            continue;
        }
    }

    @Inject
  private void angelvisuals$beforeAttack(CallbackInfoReturnable arg0) {
        class_1297 var2 = class_310.method_1551().field_1692;
        if (var2 != null) {
            EventManager.call(new bP(var2));
        }
    }

    @Inject
  public void tick(CallbackInfo arg0) {
        cz var2 = new cz();
        EventManager.call(var2);
    }

}