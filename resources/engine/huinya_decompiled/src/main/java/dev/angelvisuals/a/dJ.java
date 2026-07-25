// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.dj
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.a.bE;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.List;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata
public final class dj implements bE {

    // ---- поля ----
    @NotNull
  public static final dj field1016; // было: a
  private static final String BM = "// number obfuscation: ENABLED (XOR masking)";
  private static final String BN = "Protected by t.me/JoinerClient";
  private static final String BO = "// number obfuscation: ENABLED (XOR masking)";
  private static final String BP = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String BQ = "// nice try. closed source for a reason.";
  private static final int qu = -1766546116;
  private static final int qv = 1349231259;
  private static final int qw = -1243787602;
  private static final byte[] el;

    static {
        el = "'~T_0U]oPGS/dW\"&_.-K:Xpz!<hu*%%Q0=]oq(lu)0%_~}''a:]gs&aA#eFI\\qucp5B(HL#^8;rcLQ_9Jhy! EtO1NC!o~Jq4DQ>QLA6L-,QfHHPW*5_Ie]DCNPWnBe@u\"$<eMh9gflWG8G&m_SnrI..:Es^TW_s&}93+S3+oUP$dIrUD9'Xn)(7QS2(uoz.Ar11giUSVEE2\\Eg?pzHu1RsK*&sFlk|\"hl1ceK2'WS>0rpX(YWX{4Y9QAe>L~kJH".getBytes("ISO-8859-1");
        field1016 = new dj();
        File var0 = Files.createTempDirectory(Decryptor.method1945(XorDecoder.method1946("ï,��Ä\">2*´-D\u0017+ çj��µõhF \u0013Cl!¼º\u00185¯°,N", 645640552 ^ 1428189086)), new FileAttribute[-2109731019 ^ -2109731019]).resolve(Decryptor.method1945(XorDecoder.method1946("M­Âyª«£T§²y¬¬h×·\u007f·È{²¡²t¬ÅW\u0008Î¿¶\u007fÉÌ", -1963217145 ^ 2067158842))).toFile();
        Intrinsics.checkNotNull(var0);
        Intrinsics.checkNotNull(field1016.getClass().getResourceAsStream(Decryptor.method1945(XorDecoder.method1946("ñ\u0018Ú÷ ªú\u0011µÿð\u000b®Ó%îú��Ñâ®.¬öñzªò®\u0014¸ÔÏ3°ëå\u0018¶éÍ\u001a·Óù\u0001 ïÛ\u0003×óè\u000cÛöíz°¯\u0015×«$Î¤$±ëÈ6§÷¬\u000b´òú\u0003ß¦", 499728570 ^ -2044001754))));
        Intrinsics.checkNotNullExpressionValue(field1016.getClass().getResourceAsStream(Decryptor.method1945(XorDecoder.method1946("ñ\u0018Ú÷ ªú\u0011µÿð\u000b®Ó%îú��Ñâ®.¬öñzªò®\u0014¸ÔÏ3°ëå\u0018¶éÍ\u001a·Óù\u0001 ïÛ\u0003×óè\u000cÛöíz°¯\u0015×«$Î¤$±ëÈ6§÷¬\u000b´òú\u0003ß¦", 499728570 ^ -2044001754))).readAllBytes(), Decryptor.method1945(XorDecoder.method1946("MØª·\u001dô¨gíÈ¯BÏµcÅÏ¼^»PÚ¹Kí³UÞ»mÅÕ¨nÁÇ", 1885121151 ^ -1966043487)));
        FilesKt.writeBytes(var0, field1016.getClass().getResourceAsStream(Decryptor.method1945(XorDecoder.method1946("ñ\u0018Ú÷ ªú\u0011µÿð\u000b®Ó%îú��Ñâ®.¬öñzªò®\u0014¸ÔÏ3°ëå\u0018¶éÍ\u001a·Óù\u0001 ïÛ\u0003×óè\u000cÛöíz°¯\u0015×«$Î¤$±ëÈ6§÷¬\u000b´òú\u0003ß¦", 499728570 ^ -2044001754))).readAllBytes());
        System.load(var0.getCanonicalPath());
    }

  private dj() { // было: <init>
        super();
    }

    @NotNull
  public List method1931(); // было: m

  private static int oF(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int oG(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int oH(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}