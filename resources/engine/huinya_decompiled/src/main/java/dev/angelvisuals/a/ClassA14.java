// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.U
package dev.angelvisuals.a;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import dev.angelvisuals.AngelVisuals;
import dev.angelvisuals.a.bH;
import dev.angelvisuals.a.by;
import dev.angelvisuals.a.cd;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.minecraft.class_2172;

public class ClassA14 implements ArgumentType {

    // ---- поля ----
  private static final String dT = "// Joiner sees you";
  private static final String dU = "// every class watermarked, every string encrypted, every number xored";
  private static final String dV = "// reverse-engineering this jar is a waste of time, friend";
  private static final String dW = "// class hierarchy hashing: ENABLED";
  private static final String dX = "// nice try. closed source for a reason.";
  private static final int cg = 1616265810;
  private static final int ch = 1013567115;
  private static final int ci = -1664199997;
  private static final byte[] field68; // было: V

    static {
        field68 = "[*nXND3RtXgL~a(z27*etdFpXvAGZ:Su#FroH'{/F6/T'SB,,tdrNEe#,?_/*i-IK)|xEyhf#$2y?M*3:\"'2gJ!CO;9^a1b+>B\"BulA(j:~kBQviEG}qz[>/:/2(Gx$UjKw`\"o|$_DBiywG*k?x|i~mX6#RC]Gxx @AUVVdgV|SUTA#tKv=zX.#fXQBc6H9yS=Tq#YcEL^`ybp>?DOD_wtg3_O]7LNFmr-+,4KV:I7L?gU`fuyC]@_y8cVM/b%\"8".getBytes("ISO-8859-1");
    }

  public ClassA14() { // было: <init>
        super();
    }

  public static ClassA14 method141() { // было: a
        return new ClassA14();
    }

  public String method142(StringReader arg0) { // было: a
        return arg0.readString();
    }

  public CompletableFuture method143(CommandContext arg0, SuggestionsBuilder arg1) { // было: a
        ArrayList var3 = new ArrayList();
        Iterator var4 = AngelVisuals.getInstance().getMacroManager().b().iterator();
        while (var4.hasNext()) {
            bH var5 = ((bH) var4.next());
            var3.add(cd.method1469(var5.method406()));
            continue;
        }
        return class_2172.method_9265(var3, arg1);
    }

  public Collection method144() { // было: a
        return List.of();
    }

  public Object method145(StringReader arg0) { // было: a
        return method142(arg0);
    }

  private static int bL(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int bM(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int bN(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}