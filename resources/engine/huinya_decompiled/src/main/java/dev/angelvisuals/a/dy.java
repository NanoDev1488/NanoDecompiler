// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.dy
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import java.io.ByteArrayOutputStream;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import lombok.Generated;
import ru.nexusguard.protection.annotations.Native;

public final class dy {

    // ---- поля ----
  private static final String Ek = "// every class watermarked, every string encrypted, every number xored";
  private static final String El = "// === DO NOT TOUCH ===";
  private static final String Em = "// number obfuscation: ENABLED (XOR masking)";
  private static final String En = "// good luck with the next 9999 classes";
  private static final String Eo = "// good luck with the next 9999 classes";
  private static final int rL = -1404235089;
  private static final int rM = -1294166585;
  private static final int rN = -655647540;
  private static final byte[] eG;

    static {
        eG = "Un74]<Bt{[`sdOa6eB5E3g81iweLmS;d9\\!Q3dSgVMZA{SQ]X-e'oQoWStjLq@;}[,^b0awI[Ae4|#m5KD=Q)G&WSzO|PcT?,N@rM|+5*|SBhK0kODPXRMxp^9tE=%wdxutu.^wCX$9$K16x}a8< 8{p*25A4leMPf$P !=q!g>&#P;!O)R5ZY?.~Yu4-QVmR$x/!&oGP=H2*q(g4EiB[/kRZGz2hqh9=_n>&6K+ByB984uu>be(fE$Iu1Q?:eBM".getBytes("ISO-8859-1");
    }

    @Native
  public static byte[] method1132(byte[] arg0, String arg1) { // было: a
        byte[] __stk1;
        try {
            if (arg0.length >= (830404268 ^ 830404236)) {
                byte[] var2 = Arrays.copyOfRange(arg0, 1830798204 ^ 1830798204, 187144797 ^ 187144781);
                byte[] var3 = Arrays.copyOfRange(arg0, -643907455 ^ -643907439, -1111107709 ^ -1111107677);
                byte[] var4 = Arrays.copyOfRange(arg0, -1762384198 ^ -1762384230, arg0.length);
                SecretKeyFactory var5 = SecretKeyFactory.getInstance(Decryptor.method1945(XorDecoder.method1946("a2j\u0001J:S\u001dPST\u0017\\Rb\u000esFQ\u00180Z}\u00075TU=mra<RO4\u0001PRH.JMRi", 85108370 ^ 1359586711)));
                PBEKeySpec var6 = new PBEKeySpec(arg1.toCharArray(), var2, -1586779354 ^ -1586844890, -46280287 ^ -46280415);
                SecretKey var7 = var5.generateSecret(var6);
                SecretKeySpec var8 = new SecretKeySpec(var7.getEncoded(), Decryptor.method1945(XorDecoder.method1946(" É\u001e,ó M®h\u0011¤æ7>Ïñr@£ÐdH", -459352334 ^ -1849207035)));
                Cipher var9 = Cipher.getInstance(Decryptor.method1945(XorDecoder.method1946("oþ\u0012*\u0016ñY#iÆF\"\u0018ÂF11ãN\u0001\ríR\u0018i[d4ô\u0010\u00154ñ\u0017!\u0014ôL\u0007\nùtn", -1801324774 ^ -947896250)));
                var9.init(188571045 ^ 188571047, var8, new IvParameterSpec(var3));
                __stk1 = var9.doFinal(var4);
            } else {
                throw new IllegalArgumentException(Decryptor.method1945(XorDecoder.method1946("��]G\u007fX^\u0019n@¬Dnw»\u000bsG¢U\u0017\u0012", 1799576624 ^ 1147760351)));
            }
        } catch (Exception e1) {
            Throwable var2 = e1;
            return null;
        }
    }

    @Native
  public static byte[] method1133(byte[] arg0, String arg1) { // было: b
        byte[] __stk1;
        try {
            SecureRandom var2 = new SecureRandom();
            byte[] var3 = new byte[1092764333 ^ 1092764349];
            var2.nextBytes(var3);
            SecretKeyFactory var4 = SecretKeyFactory.getInstance(Decryptor.method1945(XorDecoder.method1946("?á³²7Ø¯¨^ß¥¤_é¼KÚªÈWöµÍYÞ\u007fêªB¿³¨_Ã²@ÙÛ", 350688323 ^ -227075394)));
            PBEKeySpec var5 = new PBEKeySpec(arg1.toCharArray(), var3, 2126169495 ^ 2126235031, -1591042725 ^ -1591042597);
            SecretKey var6 = var4.generateSecret(var5);
            SecretKeySpec var7 = new SecretKeySpec(var6.getEncoded(), Decryptor.method1945(XorDecoder.method1946("Â{03ùA\u000eRð\u001cF\u000eÆT\u0019!­C\\_ÁbJW", -1171566836 ^ -799245159)));
            byte[] var8 = new byte[-791226422 ^ -791226406];
            var2.nextBytes(var8);
            IvParameterSpec var9 = new IvParameterSpec(var8);
            Cipher var10 = Cipher.getInstance(Decryptor.method1945(XorDecoder.method1946("#K{ØZD0Ñ%s/ÐTw/Ã}V'óAX;ê%02xAyçxD~ÓXA%õFL\u001d", -130619146 ^ 1501489894)));
            var10.init(-2132958119 ^ -2132958120, var7, var9);
            byte[] var11 = var10.doFinal(arg0);
            ByteArrayOutputStream var12 = new ByteArrayOutputStream();
            var12.write(var3);
            var12.write(var8);
            var12.write(var11);
            __stk1 = var12.toByteArray();
        } catch (Throwable e1) {
            Throwable var2 = e1;
            throw var2;
        }
    }

    @Generated
  private dy() { // было: <init>
        super();
        throw new UnsupportedOperationException(Decryptor.method1945(XorDecoder.method1946("ÓTÉðC«àZìÕ­K k²¡ïW§ ¡|ýiê£(¥êIóö¢p¶çèP«É_À¯^ôZö¢³xöÊ°x±È%¡¿|èÛ«Yð¢¯g£,þ", 252192185 ^ -870711767)));
    }

  private static int pQ(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int pR(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int pS(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}