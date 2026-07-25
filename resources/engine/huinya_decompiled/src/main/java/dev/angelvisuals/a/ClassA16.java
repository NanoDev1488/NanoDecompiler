// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.B
package dev.angelvisuals.a;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.AngelVisuals;
import dev.angelvisuals.a.ClassA42;
import dev.angelvisuals.a.bl;
import dev.angelvisuals.a.cK;
import dev.angelvisuals.a.ch;
import dev.angelvisuals.a.cq;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import lombok.Generated;

public class ClassA16 {

    // ---- поля ----
  private final String bN;
  private final File field74; // было: a
  private static final String bO = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String bP = "// every class watermarked, every string encrypted, every number xored";
  private static final String bQ = "// flow obfuscation: ENABLED";
  private static final String bR = "// flow obfuscation: ENABLED";
  private static final String bS = "// === DO NOT TOUCH ===";
  private static final int aO = 1649538710;
  private static final int aP = 2026674510;
  private static final int aQ = -1291351820;
  private static final byte[] field75; // было: C

    static {
        field75 = "MW'6&QeF~3I4j_<WMrs{<h^YBeuJPWZYN/Xn]q:k%$'hf']!'W.=S0[ND= *4x@Um>fv+\\nCn=NL5HoYuz)a]fl}`Fp8awSjs42f(2`&|1J!&'@P YSFnK| g4\"qOfgOB@F{8oz|f)mOS)&)kAdz.!Ko3>NE66K}B%pC:%\\By~vkc!9lCbR8o%&H|<:F6Tt]*vkC58*?q,?)p'qIs~qfOD`Ep^qvN(YWJ>ix nj$y&r*@XSf?D`Gqm6*<Y?<:\"6#".getBytes("ISO-8859-1");
    }

  public ClassA16(String arg0) { // было: <init>
        super();
        bN = arg0;
        field74 = new File(cq.field76, arg0 + "." + Decryptor.method1945(XorDecoder.method1946("Ä³Ðc¥³«\u0013°õ\u001d ñRª¶ðnº¡ \u0016", -337997652 ^ -1069066658)).toLowerCase());
        if (!field74.exists()) {
            try {
                field74.createNewFile();
            } catch (IOException var2) {
            }
        }
    }

  public JsonObject method161() { // было: a
        JsonObject __stk1;
        try {
            JsonObject var1 = new JsonObject();
            JsonObject var2 = new JsonObject();
            Iterator var3 = AngelVisuals.getInstance().getModuleManager().method420().iterator();
            cK var4;
            while (var3.hasNext()) {
                var4 = ((cK) var3.next());
                var2.add(var4.getName(), var4.method613());
                continue;
            }
            var1.add(Decryptor.method1945(XorDecoder.method1946("\u00125zª\u0017$\u0005\n\u001e\u007f \u0019d\u0003gQ\"j\u000f", 293725996 ^ 601265361)), var2);
            ch var4 = AngelVisuals.getInstance().getThemeManager();
            JsonObject var5 = new JsonObject();
            var5.addProperty(Decryptor.method1945(XorDecoder.method1946("E_¹\u0016hMw¹ME±\u0012Q:cøÃ", -687286905 ^ 701360123)), var4.method481().method463());
            var1.add(Decryptor.method1945(XorDecoder.method1946("\u000f0eþ2\u001bbâ\u0010\u001dF°08a°\u001e=A·\u00034\u0017è", -332289005 ^ 958136181)), var5);
            __stk1 = var1;
        } catch (Exception e1) {
            Throwable var1 = e1;
            return null;
        }
    }

  public void method162(JsonObject arg0) { // было: c
        if (arg0.has(Decryptor.method1945(XorDecoder.method1946("Ò8êîï\u0013íòÍ\u0015É í0î Ã5Î§Þ<ø", 968799956 ^ -65278609)))) {
            JsonObject var2 = arg0.getAsJsonObject(Decryptor.method1945(XorDecoder.method1946("\t|\u0006¡\"{\u001a$_H£\u0001xH\u0004XO\r\u000e\u0010", -810584109 ^ -493085402)));
            if (var2.has(Decryptor.method1945(XorDecoder.method1946("S\u0019«è��.¿ß[1¼è[\u0003¡à\u0004\u0017µÚ,%Å", 1875535437 ^ -1070460377)))) {
                String var3 = var2.get(Decryptor.method1945(XorDecoder.method1946("ik6:¡\u007f\u0001a¾|6aa>>u\u0004\u0016ª\u0005L", -540318158 ^ -1359766174))).getAsString();
                Iterator var4 = AngelVisuals.getInstance().getThemeManager().method482().iterator();
                while (var4.hasNext()) {
                    bl var5 = ((bl) var4.next());
                    if (!var5.method463().equalsIgnoreCase(var3)) {
                        continue;
                    } else {
                        AngelVisuals.getInstance().getThemeManager().method484(var5);
                        break;
                    }
                }
            }
        }
        if (arg0.has(Decryptor.method1945(XorDecoder.method1946("Î·]kñ²L\u0014Ç¯vnÜquÃ¦\u000f@Ó\u0002\u001e", -965086145 ^ -448388455)))) {
            try {
                JsonObject var2 = arg0.getAsJsonObject(Decryptor.method1945(XorDecoder.method1946("Ñ;$î*[Ø\u0010!Ã¢\u0017:Üi\u000fÌ dQ", -1791542833 ^ -110180234)));
                Iterator var3 = AngelVisuals.getInstance().getModuleManager().method420().iterator();
                while (var3.hasNext()) {
                    cK var4 = ((cK) var3.next());
                    var4.method614(var2.getAsJsonObject(var4.getName()));
                    continue;
                }
            } catch (Exception e1) {
                Throwable var3 = e1;
            }
        }
    }

    @Generated
  public String method163() { // было: g
        return bN;
    }

    @Generated
  public File method164() { // было: a
        return field74;
    }

  private static int aG(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int aH(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int aI(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}