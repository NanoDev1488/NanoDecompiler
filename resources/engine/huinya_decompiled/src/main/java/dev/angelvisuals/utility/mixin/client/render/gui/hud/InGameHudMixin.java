// исходный (обфусцированный) внутренний класс: dev.angelvisuals.utility.mixin.client.render.gui.hud.InGameHudMixin
package dev.angelvisuals.utility.mixin.client.render.gui.hud;

import com.darkmagician6.eventapi.EventManager;
import dev.angelvisuals.a.ClassA25;
import dev.angelvisuals.a.ClassA93;
import dev.angelvisuals.a.al;
import dev.angelvisuals.a.ap;
import dev.angelvisuals.a.cF;
import dev.angelvisuals.a.cK;
import dev.angelvisuals.a.dC;
import dev.angelvisuals.a.dE;
import net.minecraft.class_1934;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_4599;
import net.minecraft.class_636;
import net.minecraft.class_9779;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin
public abstract class InGameHudMixin {

  public InGameHudMixin() { // было: <init>
        super();
    }

    @Inject
  private void hideVanillaStatusBars(class_332 arg0, CallbackInfo arg1) {
        if (dE.field472.at()) {
            arg1.cancel();
        }
    }

    @Inject
  private void hideVanillaExpBar(class_332 arg0, int arg1, CallbackInfo arg2) {
        if (dE.field472.at()) {
            arg2.cancel();
        }
    }

    @Inject
  public void onRender(class_332 arg0, class_9779 arg1, CallbackInfo arg2) {
        if (cF.field785.field_1724 != null) {
            al.method1843();
        }
        ap var4 = new ap(cF.field785.method_22940().method_23000());
        EventManager.call(new ClassA25(var4, arg1.method_60637(false)));
    }

    @Inject
  private void angelvisuals$hideFireOverlay(class_332 arg0, class_2960 arg1, float arg2, CallbackInfo arg3) {
        if (ClassA93.field356.ah()) {
            if (arg1.method_12832().contains("fire")) {
                arg3.cancel();
            }
        }
    }

    @Inject
  private void removeVanillaCrosshair(class_332 arg0, class_9779 arg1, CallbackInfo arg2) {
        try {
            dC var4 = dC.field405;
            if (var4.ah()) {
                arg2.cancel();
            }
            return;
        } catch (Exception e1) {
            Throwable var4 = e1;
        }
    }

    @Inject
  private void renderMainHud(class_332 arg0, class_9779 arg1, CallbackInfo arg2) {
        if (cF.field785.field_1761.method_2920() != class_1934.field_9219) {
            dE var4 = dE.field472;
            if (var4.ah()) {
                if (var4.av()) {
                    arg2.cancel();
                }
            }
        }
    }

    @Inject
  private void renderExperienceLevel(class_332 arg0, class_9779 arg1, CallbackInfo arg2) {
        if (cF.field785.field_1761.method_2920() != class_1934.field_9219) {
            dE var4 = dE.field472;
            if (var4.ah()) {
                if (var4.av()) {
                    arg2.cancel();
                }
            }
        }
    }

    @Inject
  private void inject(class_332 arg0, class_9779 arg1, CallbackInfo arg2) {
        dE var4 = dE.field472;
        if (var4.ah()) {
            if (var4.aw()) {
                arg2.cancel();
            }
        }
    }

    @Inject
  private void injectRenderOverlayMessage(class_332 arg0, class_9779 arg1, CallbackInfo arg2) {
        if (cF.field785.field_1761.method_2920() != class_1934.field_9219) {
            dE var4 = dE.field472;
            if (var4.ah()) {
                if (var4.av()) {
                    arg2.cancel();
                }
            }
        }
    }

    @Inject
  private void injectRenderScoreboardSidebar(class_332 arg0, class_9779 arg1, CallbackInfo arg2) {
        dE var4 = dE.field472;
        if (var4.ah()) {
            if (var4.au()) {
                arg2.cancel();
            }
        }
    }

    @ModifyVariable
  private int modifyM(int arg0, class_332 arg1) {
        if (cF.field785.field_1761.method_2920() == class_1934.field_9219) {
            return arg0;
        } else {
            dE var3 = dE.field472;
            if (!var3.ah()) {
                return arg0;
            } else {
                if (!var3.av()) {
                    return arg0;
                } else {
                    return arg1.method_51421() / 2 + 90 + 36;
                }
            }
        }
    }

}