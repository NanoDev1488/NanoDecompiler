// исходный (обфусцированный) внутренний класс: dev.angelvisuals.utility.mixin.client.TitleScreenMixin
package dev.angelvisuals.utility.mixin.client;

import dev.angelvisuals.a.cn;
import dev.angelvisuals.utility.mixin.accessors.ScreenAccessor;
import net.minecraft.class_1041;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_4185;
import net.minecraft.class_4185.class_7840;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin
public abstract class TitleScreenMixin {

  public TitleScreenMixin() { // было: <init>
        super();
    }

    @Inject
  private void angelvisuals$addMenuButton(CallbackInfo arg0) {
        class_310 var2 = class_310.method_1551();
        int var3 = var2.method_22683().method_4486();
        int var4 = var2.method_22683().method_4502();
        (((ScreenAccessor) this)).angelvisuals$addDrawableChild(class_4185.method_46430(class_2561.method_43470("AngelVisuals Menu"), lp0 -> lambda$angelvisuals$addMenuButton$0(var2, lp0)).method_46434(var3 / 2 - 100, var4 / 4 + 72, 200, 20).method_46431());
    }

  private static void lambda$angelvisuals$addMenuButton$0(class_310 arg0, class_4185 arg1) {
        arg0.method_1507(new cn());
    }

}