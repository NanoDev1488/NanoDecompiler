// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.bK
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.a.bZ;
import dev.angelvisuals.a.cF;
import lombok.Generated;
import net.minecraft.class_312;
import net.minecraft.class_5611;

public final class bK implements cF {

    // ---- поля ----
  private static final String pd = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String pe = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final String pf = "// number obfuscation: ENABLED (XOR masking)";
  private static final String pg = "Protected by t.me/JoinerClient";
  private static final String ph = "// you are reading machine-generated garbage";
  private static final int iY = -944135749;
  private static final int iZ = 1024766269;
  private static final int ja = 138735664;
  private static final byte[] bZ;

    static {
        bZ = "\"~Jp#v^p;60sVMU6E8t;yz1qtLFH47pn6}1)%`UB7FW`_>Xdci`\"RpvUT+W#`%I?S,.evsi.+Rdl93^N6E,{;.5~H9e-`RNR.{Kn`W&e?@$-s@qV[;_*J'G6,e@Mw&<Pe~*qjn2r5~~5-dIPa /3RQ??~b$#H3owxoJ)$n;35)Qz'r+C6})/O|fDU$)PckzrP5nMSzPE^w\\UDBg\\<hz0C~<tB/':sI3!`cFT4$h#Hu-R|4\\xLV9EU^q]O@1kf/id".getBytes("ISO-8859-1");
    }

  public static boolean method1665(double arg0, double arg1, double arg2, double arg3, int arg4, int arg5) { // было: a
        return ((double) arg4) < arg0 ? 1564187526 ^ 1564187526 : ((double) arg4) >= arg0 + arg2 ? 1564187526 ^ 1564187526 : ((double) arg5) < arg1 ? 1564187526 ^ 1564187526 : ((double) arg5) >= arg1 + arg3 ? 1564187526 ^ 1564187526 : -1460525712 ^ -1460525711;
    }

  public static boolean method1666(double arg0, double arg1, double arg2, double arg3, bZ arg4) { // было: a
        return method1665(arg0, arg1, arg2, arg3, arg4.method1678(), arg4.method1679());
    }

  public static boolean method1667(double arg0, double arg1, double arg2, double arg3, double arg4, double arg5) { // было: b
        return arg4 < arg0 ? 811406420 ^ 811406420 : arg4 >= arg0 + arg2 ? 811406420 ^ 811406420 : arg5 < arg1 ? 811406420 ^ 811406420 : arg5 >= arg1 + arg3 ? 811406420 ^ 811406420 : 1309475931 ^ 1309475930;
    }

  public static class_5611 method1668(double arg0) { // было: a
        return new class_5611(((float) (mc.field_1729.method_1603() / arg0)), ((float) (mc.field_1729.method_1604() / arg0)));
    }

    @Generated
  private bK() { // было: <init>
        super();
        throw new UnsupportedOperationException(Decryptor.method1945(XorDecoder.method1946("ÆtnþÜ\u001eyÂõ~`ÀCqýµzQÛ´\u0001mÎµOFãèmSìÿM\u0012û°\u0004sãLJßò\u0006jÂÜseçÕAdøág`·]Bß^BØÝ}\u001fû´QFÎEc·A]í¶t\u0016", -1175063727 ^ 333366996)));
    }

  private static int hU(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int hV(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int hW(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}