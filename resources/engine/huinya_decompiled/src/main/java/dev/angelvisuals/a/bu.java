// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.bU
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.a.ac;
import dev.angelvisuals.a.bR;
import dev.angelvisuals.a.cc;
import dev.angelvisuals.a.dO;
import java.net.URL;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.Intrinsics;
import org.freedesktop.dbus.DBusMap;
import org.jetbrains.annotations.NotNull;

@Metadata
public final class bU implements dO {

    // ---- поля ----
    @NotNull
  private final bR field1014; // было: a
    @NotNull
  private final String rp;
    @NotNull
  private final ac field1015; // было: c
  private static final String rq = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String rr = "// this jar protected by JoinerObfuscator";
  private static final String rs = "// === DO NOT TOUCH ===";
  private static final String rt = "// you are reading machine-generated garbage";
  private static final String ru = "// class hierarchy hashing: ENABLED";
  private static final int kj = 1192653426;
  private static final int kk = -2139179265;
  private static final int kl = -6137387;
  private static final byte[] cq;

    static {
        cq = "^O-Qf%@U/%%[yr}\\3qC<[qc\"OjQ(M|&{;+f80E8\\z+p:.n}7#3H}Ji1}qU\"is}|uGvkLT;A5[Qi[x2[ UWi\",qjAv9?.v.L<-O;lXw\\Te4;4?OL+JQ$-xz5K@x+~o Yazf2@j1(Q=vhoFv\"O_4e{5w9K705%s&Noy{BAA)zg\\wHOV#cper+B/\"a[xK>|$HJ11/h.$\"H}GF:Z>}MiQYBea09#Ra3@pda,I]{aT]SuDXzZrB>?)z7f:{l*LP|gqHR+".getBytes("ISO-8859-1");
    }

  public bU(@NotNull bR arg0, @NotNull String arg1) { // было: <init>
        Intrinsics.checkNotNullParameter(arg0, Decryptor.method1945(XorDecoder.method1946("©¸RµþQ¿«ç~à¬L©¾xäõ6", 805881983 ^ 1002449584)));
        Intrinsics.checkNotNullParameter(arg1, Decryptor.method1945(XorDecoder.method1946("\u0005Ýs\u001aà\u0006®.ÏB\u0010Ê\u0001\u000fÇY§vÔ\nã", 1893923215 ^ -1361740590)));
        super();
        field1014 = arg0;
        rp = arg1;
        field1015 = method1930();
    }

    @NotNull
  public String method1922() { // было: E
        return rp;
    }

    @NotNull
  public ac method1923() { // было: a
        return field1015;
    }

  public void method1924() { // было: U
        field1014.aF();
    }

  public void method1925() { // было: V
        field1014.aG();
    }

  public void method1926() { // было: W
        field1014.aH();
    }

  public void method1927() { // было: X
        field1014.aI();
    }

  public void method1928() { // было: Y
        field1014.aJ();
    }

  public void method1929() { // было: Z
        field1014.aK();
    }

  private final ac method1930() { // было: b
        String __stk1;
        byte[] __stk2;
        DBusMap var1 = ((DBusMap) cc.field1011.method1921(method1922(), Decryptor.method1945(XorDecoder.method1946("Î\u0011þ¯»\u007f©\u001fþ\u001dÔ»c¨i¤", -1141523055 ^ 580994421))));
        boolean var2 = Intrinsics.areEqual(cc.field1011.method1921(method1922(), Decryptor.method1945(XorDecoder.method1946("á^Û¸îeþÔ¦yß¤Aýß¿fÜ¡¡|¤Ñ", -1460085073 ^ 1147270008))), Decryptor.method1945(XorDecoder.method1946("ÊçqÞÑXÜàì\u0014ÃüÝPÿûrÄ\u0002", -607635270 ^ -461508823)));
        long var3 = ((long) (((Number) cc.field1011.method1921(method1922(), Decryptor.method1945(XorDecoder.method1946("ÔçY\u0016æÕL\u0008ÈÃ~\u0012ÊJ ý×+\u0005åÖ%_", -1681189197 ^ -103569649))))).doubleValue()) / ((long) (666988351 ^ 667823487));
        Intrinsics.checkNotNull(var1.get(Decryptor.method1945(XorDecoder.method1946("ÿ\u0006âëU×èHøþ\u000eË\u0003ÅçÑZ", 1070021894 ^ -1633641520))));
        long var5 = Long.parseLong(var1.get(Decryptor.method1945(XorDecoder.method1946("ÿ\u0006âëU×èHøþ\u000eË\u0003ÅçÑZ", 1070021894 ^ -1633641520))).toString()) / ((long) (-1315867579 ^ -1315031547));
        Intrinsics.checkNotNull(var1.get(Decryptor.method1945(XorDecoder.method1946("ÃÆñýéöìÚ®Ëæ»ûÝô³Ëìùà", -975329078 ^ 404292979))), Decryptor.method1945(XorDecoder.method1946("¨ÿ\u001cAÃ+5\u0017vá:s¬ä3s£ð\u0004+óÍ\naÂ5yðÜ1pñä06ò1TýKg¡Þ:F¯õ\u0007Aóö3X¥â\u001eA¨ÄGZò9Tú#4©Ó30ó\u0008AõÐO=", 458443557 ^ 455212258)));
        String var7 = ((String) var1.get(Decryptor.method1945(XorDecoder.method1946("ÃÆñýéöìÚ®Ëæ»ûÝô³Ëìùà", -975329078 ^ 404292979))));
        Object var10 = var1.get(Decryptor.method1945(XorDecoder.method1946("v¼äözõë÷u¸ÝÜj«Àõ.àÞ×(Ò·", -813533001 ^ 1795746728)));
        int var11 = -362141472 ^ -362141472;
        if (!(var10 instanceof String)) {
            Intrinsics.checkNotNull(var10, Decryptor.method1945(XorDecoder.method1946("H¥2ªtF¶/®\u0005´V��S��G½XÆz³\u0012¦u\nÅk\u0003ÄSE¦E:x²+ÁAGªv¨1¼uà4+5§l\u0005¦A½\u0004µM£4X¤\u0004¸U­\u000b½göN", -619281317 ^ -1461877591)));
            __stk1 = CollectionsKt.joinToString$default(((Iterable) ((List) var10)), ((CharSequence) Decryptor.method1945(XorDecoder.method1946("^£t÷D°oq^þY´LÓSµ[Òz%", 1211667793 ^ -249533343))), null, null, 1590497388 ^ 1590497388, null, null, -56774893 ^ -56774867, null);
        } else {
            __stk1 = ((String) var10);
        }
        String var8 = __stk1;
        Object var9 = var1.get(Decryptor.method1945(XorDecoder.method1946("«3*j­p+Aïq&_>\u0014\u0016¨ \u000e��´!|\u0012", 548065518 ^ 267097655)));
        __stk2 = !(var9 instanceof String) ? new byte[1264540491 ^ 1264540491] : TextStreamsKt.readBytes(new URL(((String) var9)));
        var10 = __stk2;
        return new ac(var7, ((String) var8), ((byte[]) var10), var3, var5, var2);
    }

  private static int iT(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int iU(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int iV(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}