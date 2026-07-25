// исходный (обфусцированный) внутренний класс: dev.angelvisuals.utility.mixin.client.ChatInputSuggestorMixin
package dev.angelvisuals.utility.mixin.client;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.StringReader;
import dev.angelvisuals.AngelVisuals;
import dev.angelvisuals.a.bT;
import java.util.concurrent.CompletableFuture;
import net.minecraft.class_342;
import net.minecraft.class_4717.class_464;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin
public abstract class ChatInputSuggestorMixin {

    // ---- поля ----
    @Final
    @Shadow
   class_342 field_21599;
    @Shadow
   boolean field_21614;
    @Shadow
  private ParseResults field_21610;
    @Shadow
  private CompletableFuture field_21611;
    @Shadow
  private class_464 field_21612;

  public ChatInputSuggestorMixin() { // было: <init>
        super();
    }

    @Shadow
  protected abstract void method_23937();

    @Inject
  public void refreshHook(CallbackInfo arg0, String arg1, StringReader arg2) {
        if (arg2.canRead(AngelVisuals.getInstance().getCommandManager().method67().length())) {
            if (arg2.getString().startsWith(AngelVisuals.getInstance().getCommandManager().method67(), arg2.getCursor())) {
                arg2.setCursor(arg2.getCursor() + 1);
                if (field_21610 == null) {
                    field_21610 = AngelVisuals.getInstance().getCommandManager().method68().parse(arg2, AngelVisuals.getInstance().getCommandManager().method69());
                }
                int var4 = field_21599.method_1881();
                if (var4 >= 1) {
                    if (field_21612 == null) {
                        field_21611 = AngelVisuals.getInstance().getCommandManager().method68().getCompletionSuggestions(field_21610, var4);
                        field_21611.thenRun(() -> lambda$refreshHook$0());
                    } else {
                        if (!field_21614) {
                            field_21611 = AngelVisuals.getInstance().getCommandManager().method68().getCompletionSuggestions(field_21610, var4);
                            field_21611.thenRun(() -> lambda$refreshHook$0());
                        }
                    }
                }
                arg0.cancel();
            }
        }
    }

  private void lambda$refreshHook$0() {
        if (field_21611.isDone()) {
            method_23937();
        }
    }

}