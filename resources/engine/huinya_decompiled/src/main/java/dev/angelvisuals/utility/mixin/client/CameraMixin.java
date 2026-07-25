// исходный (обфусцированный) внутренний класс: dev.angelvisuals.utility.mixin.client.CameraMixin
package dev.angelvisuals.utility.mixin.client;

import com.darkmagician6.eventapi.EventManager;
import dev.angelvisuals.a.aP;
import dev.angelvisuals.a.dQ;
import dev.angelvisuals.utility.mixin.accessors.CameraAccessor;
import net.minecraft.class_1297;
import net.minecraft.class_1922;
import net.minecraft.class_2338.class_2339;
import net.minecraft.class_243;
import net.minecraft.class_4184;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin
public abstract class CameraMixin {

    // ---- поля ----
    @Shadow
  private class_243 field_18712;
    @Shadow
    @Final
  private class_2339 field_18713;
    @Shadow
  private float field_18718;
    @Shadow
  private float field_18717;

  public CameraMixin() { // было: <init>
        super();
    }

    @Shadow
  protected abstract void method_19325(float arg0, float arg1);

    @Shadow
  protected abstract void method_19324(float arg0, float arg1, float arg2);

    @Shadow
  protected abstract float method_19318(float arg0);

    @Inject
  private void updateHook(class_1922 arg0, class_1297 arg1, boolean arg2, boolean arg3, float arg4, CallbackInfo arg5) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: new  #6 // dev.angelvisuals.a.aU
        //      3: dup
        //      4: iconst_0
        //      5: ldc  #2 // 4.0f
        //      7: new  #7 // dev.angelvisuals.a.cH
        //     10: dup
        //     11: aload_0
        //     12: getfield  #22 // dev.angelvisuals.utility.mixin.client.CameraMixin.field_18718:F
        //     15: aload_0
        //     16: getfield  #21 // dev.angelvisuals.utility.mixin.client.CameraMixin.field_18717:F
        //     19: invokespecial  #34 // dev.angelvisuals.a.cH.<init>:(FF)V
        //     22: invokespecial  #30 // dev.angelvisuals.a.aU.<init>:(ZFLdev/angelvisuals/a/cH;)V
        //     25: astore  7
        //     27: aload  7
        //     29: invokestatic  #26 // com.darkmagician6.eventapi.EventManager.call:(Lcom/darkmagician6/eventapi/events/Event;)Lcom/darkmagician6/eventapi/events/Event;
        //     32: pop
        //     33: new  #7 // dev.angelvisuals.a.cH
        //     36: dup
        //     37: aload_0
        //     38: getfield  #22 // dev.angelvisuals.utility.mixin.client.CameraMixin.field_18718:F
        //     41: aload_0
        //     42: getfield  #21 // dev.angelvisuals.utility.mixin.client.CameraMixin.field_18717:F
        //     45: invokespecial  #34 // dev.angelvisuals.a.cH.<init>:(FF)V
        //     48: astore  8
        //     50: aload  7
        //     52: invokevirtual  #33 // dev.angelvisuals.a.aU.isCancelled:()Z
        //     55: ifeq  144 (offset +89)
        //     58: aload_2
        //     59: instanceof  #15 // net.minecraft.class_746
        //     62: ifeq  144 (offset +82)
        //     65: aload_2
        //     66: checkcast  #15 // net.minecraft.class_746
        //     69: astore  9
        //     71: aload  9
        //     73: invokevirtual  #44 // net.minecraft.class_746.method_6113:()Z
        //     76: ifne  144 (offset +68)
        //     79: iload_3
        //     80: ifeq  144 (offset +64)
        //     83: aload  8
        //     85: invokevirtual  #36 // dev.angelvisuals.a.cH.aY:()F
        //     88: fstore  10
        //     90: aload  8
        //     92: invokevirtual  #35 // dev.angelvisuals.a.cH.aX:()F
        //     95: fstore  11
        //     97: aload  7
        //     99: invokevirtual  #32 // dev.angelvisuals.a.aU.X:()F
        //    102: fstore  12
        //    104: aload_0
        //    105: fload  11
        //    107: fload  10
        //    109: invokevirtual  #41 // dev.angelvisuals.utility.mixin.client.CameraMixin.method_19325:(FF)V
        //    112: aload_0
        //    113: aload  7
        //    115: invokevirtual  #31 // dev.angelvisuals.a.aU.D:()Z
        //    118: ifeq  127 (offset +9)
        //    121: fload  12
        //    123: fneg
        //    124: goto  134 (offset +10)
        //    127: aload_0
        //    128: fload  12
        //    130: invokevirtual  #39 // dev.angelvisuals.utility.mixin.client.CameraMixin.method_19318:(F)F
        //    133: fneg
        //    134: fconst_0
        //    135: fconst_0
        //    136: invokevirtual  #40 // dev.angelvisuals.utility.mixin.client.CameraMixin.method_19324:(FFF)V
        //    139: aload  6
        //    141: invokevirtual  #45 // org.spongepowered.asm.mixin.injection.callback.CallbackInfo.cancel:()V
        //    144: return
    }

    @Inject
  private void posHook(class_243 arg0, CallbackInfo arg1) {
        dQ var3 = new dQ(arg0);
        EventManager.call(var3);
        arg0 = var3.method306();
        field_18712 = arg0;
        field_18713.method_10102(arg0.field_1352, arg0.field_1351, arg0.field_1350);
        arg1.cancel();
    }

    @Redirect
  private void redirectSetRotation(class_4184 arg0, float arg1, float arg2, class_1922 arg3, class_1297 arg4, boolean arg5, boolean arg6, float arg7) {
        aP var9 = new aP(arg1, arg2, arg7);
        EventManager.call(var9);
        float var10 = var9.method289();
        float var11 = var9.method290();
        if (arg5) {
            if (arg6) {
                var10 = var10 + 180.0f;
                var11 = -var11;
            }
        }
        (((CameraAccessor) arg0)).setCustomRotation(var10, var11);
    }

}