// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.bh
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.a.bE;
import dev.angelvisuals.a.cL;
import dev.angelvisuals.a.cc;
import dev.angelvisuals.a.dj;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata
public final class bh {

    // ---- поля ----
    @NotNull
  private static final bE field1009; // было: a
  private static final String lX = "// stop. seriously. go play minecraft instead";
  private static final String lY = "// every class watermarked, every string encrypted, every number xored";
  private static final String lZ = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final String ma = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final String mb = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final int gX = -349731278;
  private static final int gY = 105080779;
  private static final int gZ = 1198162968;
  private static final byte[] bv;

    static {
        bE __stk1;
        bv = "cS8~gD`T[vg'c*aQBJ2XqB#}%*AjT|-$rVh7#vB8-{%gSZ\"oo4M`MOKpiv}7;m_*`{+?f\\{&LQFth)eUoGvbE$]~;,}Spv]G-`XXn{I[4D}IF4!F\".Llf`,PwBlHNk918N6tNA@BoW_>7LL:XCFQWS|DW|lCBHw])%E3&@9Wbzc|\"YBy5f9\\EZ_8DE{510J/)NK!s]BtK/K]Bd)tFR=[R7Fi$f`\"rH\\PZ,=7|AQ&|O|+y?0HyIvL%($ery_JSdI,".getBytes("ISO-8859-1");
        Intrinsics.checkNotNullExpressionValue(System.getProperty(Decryptor.method1945(XorDecoder.method1946("ZèH¦ÒB¹ðµ}ü\u001càKñ§", -1166829661 ^ 549436230))), Decryptor.method1945(XorDecoder.method1946("{ïxípö\u0014ªeðróX\nÊfØKçCÏ\u0010ìv\u0012ªcëi¯Dî\u0016Í\u007fßbÙlÕV¢", 1407553847 ^ -859559422)));
        Intrinsics.checkNotNullExpressionValue(System.getProperty(Decryptor.method1945(XorDecoder.method1946("ZèH¦ÒB¹ðµ}ü\u001càKñ§", -1166829661 ^ 549436230))).toLowerCase(Locale.ROOT), Decryptor.method1945(XorDecoder.method1946("BSìt!\u000eÌ\r\u001aKîT$lô\r\u0013O÷e;_³L7\u0010±\n\"kÊ\u000f\u0005nµm>_Áy-Uõ\u0002", -1663979687 ^ -1554803667)));
        if (!StringsKt.startsWith$default(System.getProperty(Decryptor.method1945(XorDecoder.method1946("ZèH¦ÒB¹ðµ}ü\u001càKñ§", -1166829661 ^ 549436230))).toLowerCase(Locale.ROOT), Decryptor.method1945(XorDecoder.method1946("Ã¬ÅysÌ\tþµyÎ\u001cèËw", -174277443 ^ -1083540718)), -507671270 ^ -507671270, -641595936 ^ -641595934, null)) {
            Intrinsics.checkNotNullExpressionValue(System.getProperty(Decryptor.method1945(XorDecoder.method1946("ý M7ò2~\rú8a/È\u0007Z#þf_?ù1)x", -62721653 ^ -1185503469))), Decryptor.method1945(XorDecoder.method1946("AÏJ£Ë_Åbç½«\\µüy¢§Lý¥ËYÞÎ~¡¬E²Õ¸V¸áÃ", 1918223134 ^ -1933372143)));
            Intrinsics.checkNotNullExpressionValue(System.getProperty(Decryptor.method1945(XorDecoder.method1946("ý M7ò2~\rú8a/È\u0007Z#þf_?ù1)x", -62721653 ^ -1185503469))).toLowerCase(Locale.ROOT), Decryptor.method1945(XorDecoder.method1946("¤p\u000eÇÔPwür.Â¶hwõk\u001fÝ/6ÑÊ-pÄ±Vuã´)\u0017Ø]\u0003Ëix", -206561644 ^ -1230045434)));
            __stk1 = !Intrinsics.areEqual(System.getProperty(Decryptor.method1945(XorDecoder.method1946("ý M7ò2~\rú8a/È\u0007Z#þf_?ù1)x", -62721653 ^ -1185503469))).toLowerCase(Locale.ROOT), Decryptor.method1945(XorDecoder.method1946("\u000cíáËsÑôX·ûètíÏM£Ç×Oµ", -1875183351 ^ 884089398))) ? ((bE) cL.field1010) : ((bE) cc.field1011);
        } else {
            __stk1 = ((bE) dj.field1016);
        }
        field1009 = __stk1;
    }

    @NotNull
  public static final bE method1918() { // было: a
        return field1009;
    }

  private static int gl(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int gm(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int gn(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}