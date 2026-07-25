// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.Z
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandExceptionType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ClassA13 implements ArgumentType {

    // ---- поля ----
  private static final String es = "// this jar protected by JoinerObfuscator";
  private static final String et = "// stop. seriously. go play minecraft instead";
  private static final String eu = "// reverse-engineering this jar is a waste of time, friend";
  private static final String ev = "// this jar protected by JoinerObfuscator";
  private static final String ew = "// Joiner sees you";
  private static final int cx = 725969054;
  private static final int cy = 247886260;
  private static final int cz = -269031155;
  private static final byte[] aa;

    static {
        aa = "y6jPnLWtWuwOjebEmoCcgiO8eqOCX&!`k<L]f{#\\AT#mR'{zZaFP7i6]45MTH,8j]zMj}P3R'PV*r^\"NU=f@oDGk4BO9*$}QFuw s7eO\"(@I)[DLg{QD45#0zbsa5zZm*e7dKA+a,N3uvWv3V*-g64A'r (_X=Oh*&X=e9vC v\"QhR{d G:8Y_y\",HQ4;#)P`&/o?\\{N=_n,0t&ZzJP)!nleN]>MSS`aKAP5E!_~bq@@:]bx:$rC,yp3Bs!uT`\\!".getBytes("ISO-8859-1");
    }

  public ClassA13() { // было: <init>
        super();
    }

  public static ClassA13 method124() { // было: a
        return new ClassA13();
    }

  public Double method125(StringReader arg0) { // было: a
        Double __stk1;
        try {
            __stk1 = Double.valueOf(Double.parseDouble(arg0.readString()));
        } catch (NumberFormatException var2) {
            throw new CommandSyntaxException(((CommandExceptionType) null), () -> method129());
        }
    }

  public CompletableFuture method126(CommandContext arg0, SuggestionsBuilder arg1) { // было: a
        return Suggestions.empty();
    }

  public Collection method127() { // было: a
        return List.of();
    }

  public Object method128(StringReader arg0) { // было: a
        return method125(arg0);
    }

  private static String method129() { // было: k
        return Decryptor.method1945(XorDecoder.method1946("\u0008©ä×#è×Ð.ÏË±\u0010õþüxóíã<ÇîÍ}Åè\u0007ÍØ8ÿöã\u0012÷ïÙ\u0001éåÊ\"éýà\u001b©Èâ\u0008ÕëÈ\u0004ìòÛ.¥ÔÀ0ß©{ÏÎÛ��Þö8ØÈ.äÚÊ\u0005Ì§", 993006982 ^ -1584625460));
    }

  private static int ca(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int cb(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int cc(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}