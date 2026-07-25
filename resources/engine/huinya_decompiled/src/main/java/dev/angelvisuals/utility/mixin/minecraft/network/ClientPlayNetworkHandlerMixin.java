// исходный (обфусцированный) внутренний класс: dev.angelvisuals.utility.mixin.minecraft.network.ClientPlayNetworkHandlerMixin
package dev.angelvisuals.utility.mixin.minecraft.network;

import com.darkmagician6.eventapi.EventManager;
import dev.angelvisuals.a.cP;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1542;
import net.minecraft.class_1799;
import net.minecraft.class_2604;
import net.minecraft.class_2775;
import net.minecraft.class_634;
import net.minecraft.class_638;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin
public class ClientPlayNetworkHandlerMixin {

    // ---- поля ----
    @Unique
  private boolean angelvisuals$chatEventReentry;

  public ClientPlayNetworkHandlerMixin() { // было: <init>
        super();
    }

    @Inject
  private void sendChatMessageHook(@NotNull String arg0, CallbackInfo arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #19 // dev.angelvisuals.utility.mixin.minecraft.network.ClientPlayNetworkHandlerMixin.angelvisuals$chatEventReentry:Z
        //      4: ifne  83 (offset +79)
        //      7: new  #6 // dev.angelvisuals.a.aB
        //     10: dup
        //     11: aload_1
        //     12: invokespecial  #24 // dev.angelvisuals.a.aB.<init>:(Ljava/lang/String;)V
        //     15: astore_3
        //     16: aload_3
        //     17: invokestatic  #20 // com.darkmagician6.eventapi.EventManager.call:(Lcom/darkmagician6/eventapi/events/Event;)Lcom/darkmagician6/eventapi/events/Event;
        //     20: pop
        //     21: aload_3
        //     22: invokevirtual  #25 // dev.angelvisuals.a.aB.isCancelled:()Z
        //     25: ifeq  33 (offset +8)
        //     28: aload_2
        //     29: invokevirtual  #43 // org.spongepowered.asm.mixin.injection.callback.CallbackInfo.cancel:()V
        //     32: return
        //     33: aload_3
        //     34: invokevirtual  #26 // dev.angelvisuals.a.aB.r:()Ljava/lang/String;
        //     37: aload_1
        //     38: invokevirtual  #32 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //     41: ifne  83 (offset +42)
        //     44: aload_2
        //     45: invokevirtual  #43 // org.spongepowered.asm.mixin.injection.callback.CallbackInfo.cancel:()V
        //     48: aload_0
        //     49: iconst_1
        //     50: putfield  #19 // dev.angelvisuals.utility.mixin.minecraft.network.ClientPlayNetworkHandlerMixin.angelvisuals$chatEventReentry:Z
        //     53: aload_0
        //     54: checkcast  #16 // net.minecraft.class_634
        //     57: aload_3
        //     58: invokevirtual  #26 // dev.angelvisuals.a.aB.r:()Ljava/lang/String;
        //     61: invokevirtual  #41 // net.minecraft.class_634.method_45729:(Ljava/lang/String;)V
        //     64: aload_0
        //     65: iconst_0
        //     66: putfield  #19 // dev.angelvisuals.utility.mixin.minecraft.network.ClientPlayNetworkHandlerMixin.angelvisuals$chatEventReentry:Z
        //     69: goto  82 (offset +13)
        //     72: astore  4
        //     74: aload_0
        //     75: iconst_0
        //     76: putfield  #19 // dev.angelvisuals.utility.mixin.minecraft.network.ClientPlayNetworkHandlerMixin.angelvisuals$chatEventReentry:Z
        //     79: aload  4
        //     81: athrow
        //     82: return
        //     83: aload_1
        //     84: invokestatic  #23 // dev.angelvisuals.AngelVisuals.getInstance:()Ldev/angelvisuals/AngelVisuals;
        //     87: invokevirtual  #22 // dev.angelvisuals.AngelVisuals.getCommandManager:()Ldev/angelvisuals/a/bT;
        //     90: invokevirtual  #27 // dev.angelvisuals.a.bT.S:()Ljava/lang/String;
        //     93: invokevirtual  #34 // java.lang.String.startsWith:(Ljava/lang/String;)Z
        //     96: ifeq  145 (offset +49)
        //     99: invokestatic  #23 // dev.angelvisuals.AngelVisuals.getInstance:()Ldev/angelvisuals/AngelVisuals;
        //    102: invokevirtual  #22 // dev.angelvisuals.AngelVisuals.getCommandManager:()Ldev/angelvisuals/a/bT;
        //    105: invokevirtual  #28 // dev.angelvisuals.a.bT.a:()Lcom/mojang/brigadier/CommandDispatcher;
        //    108: aload_1
        //    109: invokestatic  #23 // dev.angelvisuals.AngelVisuals.getInstance:()Ldev/angelvisuals/AngelVisuals;
        //    112: invokevirtual  #22 // dev.angelvisuals.AngelVisuals.getCommandManager:()Ldev/angelvisuals/a/bT;
        //    115: invokevirtual  #27 // dev.angelvisuals.a.bT.S:()Ljava/lang/String;
        //    118: invokevirtual  #33 // java.lang.String.length:()I
        //    121: invokevirtual  #35 // java.lang.String.substring:(I)Ljava/lang/String;
        //    124: invokestatic  #23 // dev.angelvisuals.AngelVisuals.getInstance:()Ldev/angelvisuals/AngelVisuals;
        //    127: invokevirtual  #22 // dev.angelvisuals.AngelVisuals.getCommandManager:()Ldev/angelvisuals/a/bT;
        //    130: invokevirtual  #29 // dev.angelvisuals.a.bT.a:()Lnet/minecraft/class_2172;
        //    133: invokevirtual  #21 // com.mojang.brigadier.CommandDispatcher.execute:(Ljava/lang/String;Ljava/lang/Object;)I
        //    136: pop
        //    137: goto  141 (offset +4)
        //    140: astore_3
        //    141: aload_2
        //    142: invokevirtual  #43 // org.spongepowered.asm.mixin.injection.callback.CallbackInfo.cancel:()V
        //    145: return
        //       Exception table:
        //         from 53 to 64 target 72 type any
        //         from 72 to 74 target 72 type any
        //         from 99 to 137 target 140 type com.mojang.brigadier.exceptions.CommandSyntaxException
    }

    @Inject
  private void onSpawn(class_2604 arg0, CallbackInfo arg1) {
        if (arg0.method_11167() == 12345) {
            arg1.cancel();
        }
    }

    @Inject
  private void onPickup(class_2775 arg0, CallbackInfo arg1) {
        class_634 var3 = ((class_634) this);
        if (var3.method_2890() != null) {
            class_1297 var4 = var3.method_2890().method_8469(arg0.method_11912());
            if (var4 instanceof class_1309) {
                class_1309 var5 = ((class_1309) var4);
                class_1297 var6 = var3.method_2890().method_8469(arg0.method_11915());
                if (var6 instanceof class_1542) {
                    class_1542 var7 = ((class_1542) var6);
                    class_1799 var8 = var7.method_6983();
                    EventManager.call(new cP(var5, var8));
                }
            }
        }
    }

}