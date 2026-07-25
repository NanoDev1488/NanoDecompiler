// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.aV
package dev.angelvisuals.a;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.angelvisuals.a.ClassA84;
import dev.angelvisuals.a.aV_ClassA85;
import dev.angelvisuals.a.bp;
import java.util.function.Supplier;
import lombok.Generated;

public class aV extends ClassA84 {

    // ---- поля ----
  private bp field328; // было: b
  private final aV_ClassA85 field329; // было: a
  private static final String km = "// good luck with the next 9999 classes";
  private static final String kn = "// === DO NOT TOUCH ===";
  private static final String ko = "// flow obfuscation: ENABLED";
  private static final String kp = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String kq = "// number obfuscation: ENABLED (XOR masking)";
  private static final int gb = 221891528;
  private static final int gc = 59068591;
  private static final int gd = -228641053;
  private static final byte[] bg;

    static {
        bg = "N4 a3$)Oz;R|$g?Gsp(f@lX6+f5KLFwxl%sIJ\\PYTD`uq0Pp/Y;H,'B{NUnz)ofsQ:-'?ST>#,?bUkbhLEE6env:P{LLj]h^3}JFQ 8-IRe}F/)<*Q:& MA?^rWa?i{]rWVBiX7FNLl*\">DTtd}j{)v+E4{2 :F*BVdP^BRMut@yu[II]\\$S2rNW OYT.+t;)#`> Zucz5GH_CiWmBhmlT(.|L&q eW93ZAPLv4FtK>qC@:WsC4n+<;(>l+p>\"&}".getBytes("ISO-8859-1");
    }

  public aV(String arg0, bp arg1, Supplier arg2, aV_ClassA85 arg3) { // было: <init>
        this(arg0, arg1, arg3);
        a(arg2);
    }

  public aV(String arg0, bp arg1, aV_ClassA85 arg2) { // было: <init>
        super(arg0);
        if (arg1 != null) {
            field328 = arg1;
            method661(arg1);
            field329 = arg2;
            return;
        } else {
            throw new RuntimeException(arg0 + " color is null");
        }
    }

  public aV(String arg0, bp arg1) { // было: <init>
        this(arg0, arg1, () -> method669(arg1));
    }

  public aV(String arg0, aV_ClassA85 arg1) { // было: <init>
        this(arg0, arg1.IIlllll0lO0ll0lO(), arg1);
    }

  public aV(String arg0, bp arg1, Supplier arg2) { // было: <init>
        this(arg0, arg1, arg2, () -> method668(arg1));
    }

  public int method659() { // было: x
        return field328.method1680();
    }

  public void method660(int arg0) { // было: h
        field328 = new bp(arg0);
    }

  public void method661(bp arg0) { // было: a
        field328 = arg0;
    }

  public void method662() { // было: S
        // (пустое тело)
    }

  public void method663() { // было: T
        field328 = field329.IIlllll0lO0ll0lO();
    }

  public bp method664(float arg0) { // было: a
        return field328.method1687(arg0);
    }

  public void method665(JsonObject arg0) { // было: a
        arg0.addProperty(String.valueOf(aD), Integer.valueOf(method659()));
    }

  public void method666(JsonObject arg0) { // было: b
        method660(arg0.get(String.valueOf(aD)).getAsInt());
    }

    @Generated
  public bp method667() { // было: a
        return field328;
    }

  private static bp method668(bp arg0) { // было: a
        return arg0;
    }

  private static bp method669(bp arg0) { // было: b
        return arg0;
    }

  private static int fs(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int ft(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int fu(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}