// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.m
package dev.angelvisuals.a;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import dev.angelvisuals.a.cF;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
import net.minecraft.class_2172;
import net.minecraft.class_310;
import net.minecraft.class_634;
import net.minecraft.class_640;

public class ClassA15 implements ArgumentType, cF {

    // ---- поля ----
  private static final String aj = "// nice try. closed source for a reason.";
  private static final String ak = "// every class watermarked, every string encrypted, every number xored";
  private static final String al = "// class hierarchy hashing: ENABLED";
  private static final String am = "Protected by t.me/JoinerClient";
  private static final String an = "// this jar protected by JoinerObfuscator";
  private static final int field70 = -772430729; // было: R
  private static final int field71 = -1415416546; // было: S
  private static final int field72 = -366645543; // было: T
  private static final byte[] field73; // было: m

    static {
        field73 = "$zm!0SnmM$2pOq{s^.X&wL!wsu,v,a5sZt TQ2yCVv&Vl+}5Y4*PEKNUewK7[QkhEJVUgX$O-z9b,Dds{?G{&\"xo_s#21;ZGdv; kDCY#D%mFsF&.od1R;pj4XEa05U4{TDcut<tWOtbLsyVW?'~1is)d&p},J~[v3Xi;bYyKP-NH1ao$Rvgg-ED^ENu5#NWl:)=YnsSy*]ELV#%O=7qKso\"z03QEsVz^{k&C^*+Cf`$f*%`xutkvy|8GB3,,9s ".getBytes("ISO-8859-1");
    }

  public ClassA15() { // было: <init>
        super();
    }

  public static ClassA15 method152() { // было: a
        return new ClassA15();
    }

  public String method153(StringReader arg0) { // было: a
        return arg0.readUnquotedString();
    }

  public CompletableFuture method154(CommandContext arg0, SuggestionsBuilder arg1) { // было: a
        return class_2172.method_9264(mc.method_1562().method_2880().stream().map(lp0 -> method157(((class_640) lp0))), arg1);
    }

  public Collection method155() { // было: a
        return List.of();
    }

  public Object method156(StringReader arg0) { // было: a
        return method153(arg0);
    }

  private static String method157(class_640 arg0) { // было: a
        return arg0.method_2966().getName();
    }

  private static int method158(int arg0, int arg1) { // было: K
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int method159(int arg0, int arg1) { // было: L
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int method160(int arg0, int arg1) { // было: M
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}