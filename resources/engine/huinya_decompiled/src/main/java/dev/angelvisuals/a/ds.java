// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.ds
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.a.ClassA2;
import dev.angelvisuals.a.aH;
import dev.angelvisuals.a.bp;
import dev.angelvisuals.a.dp;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;

public class ds {

    // ---- поля ----
  private List field47; // было: M
  private final List field48; // было: N
  private int rl;
  private final ClassA2 field49; // было: Q
  private static final int rm = 0;
  private static final int rn = 1;
  private static final int ro = 2;
  private static final int rp = 3;
  private static final String Dl = "// you are reading machine-generated garbage";
  private static final String Dm = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final String Dn = "// flow obfuscation: ENABLED";
  private static final String Do = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final String Dp = "// this jar protected by JoinerObfuscator";
  private static final int rq = -137263689;
  private static final int rr = 472465632;
  private static final int rs = 1148901214;
  private static final byte[] ez;

    static {
        ez = "^3W`DQ^n@2q{Ff2/=+Se-.qqB=TPvb.}4|adT$A=X@F]:=%D:Oy~0,!*wGh1Ni=aa+sIbtKb`$O/&/Vv>\"ohd%Y]|}jKk;b0J?*3w0EW.L|Ci#_|_(<=/[AN#w|a9Fu\"v?>K4!K?X't?%aY\\5a+A7oi!Z{2C{^u`)9CGmK(]T(ni`bYo=L['amHs)rz^ft]ApL\"84<Cde-mU/+`UszQM1*#16?P}t7nZIi|NXa*CQOLU-yYy_kIIPyuAECcHa#@w".getBytes("ISO-8859-1");
    }

  public ds(List arg0, long arg1) { // было: <init>
        super();
        rl = -2033667635 ^ -2033667635;
        if (arg0.size() == (1086194992 ^ 1086194996)) {
            field47 = new ArrayList(arg0);
            field48 = new ArrayList(arg0);
            field49 = new ClassA2(arg1, aH.field16);
            return;
        } else {
            throw new IllegalArgumentException(Decryptor.method1945(XorDecoder.method1946("H³+åf´��í1µ\u001dþoë~üeÆ%äTÒ��ãiÍ\rÀKÌ\u0016ËN´;ÁUÖ<ù5à5çEÌyÒU°\u0018úwð\u000bá1³#ËKÅ(áw®\u0002ä6Ó\u001f¼L÷\u0015ÓHÂ|úOÆ\u0001ÇVær·", 1903563050 ^ -80113619)));
        }
    }

  public void ba() {
        float var1 = field49.method13();
        field49.method20(-2591932112956541591L ^ -2591932112956541311L);
        bp var2 = (((bp) field47.get(6162590 ^ 6162590))).method1687(0.0f);
        if (rl == 0) {
            field48.set(-2031676386 ^ -2031676386, (((bp) field47.get(-1282123406 ^ -1282123406))).method1688(var2, var1));
            field48.set(-960377788 ^ -960377787, var2.method1688(((bp) field47.get(-721947763 ^ -721947764)), var1));
        }
        if (rl == (-987683309 ^ -987683310)) {
            field48.set(-2137143336 ^ -2137143334, (((bp) field47.get(1373078510 ^ 1373078508))).method1688(var2, var1));
            field48.set(-90052030 ^ -90052030, var2.method1688(((bp) field47.get(-1651739185 ^ -1651739185)), var1));
        }
        if (rl == (203748336 ^ 203748338)) {
            field48.set(1176149748 ^ 1176149751, (((bp) field47.get(-412323253 ^ -412323256))).method1688(var2, var1));
            field48.set(-1254658390 ^ -1254658392, var2.method1688(((bp) field47.get(625048190 ^ 625048188)), var1));
        }
        if (rl == (1401021737 ^ 1401021738)) {
            field48.set(110877296 ^ 110877297, (((bp) field47.get(355408181 ^ 355408180))).method1688(var2, var1));
            field48.set(2067618137 ^ 2067618138, var2.method1688(((bp) field47.get(1640972120 ^ 1640972123)), var1));
        }
        if (field49.method13() == 1.0f) {
            field49.method9();
            rl = rl + (834772761 ^ 834772760);
            if (rl >= field48.size()) {
                rl = -250354624 ^ -250354624;
            }
        }
        field49.method6(1.0f);
    }

  public dp method64() { // было: b
        return dp.method1657(((bp) field48.get(-1335249034 ^ -1335249034)), ((bp) field48.get(-1091589235 ^ -1091589236)), ((bp) field48.get(-11448657 ^ -11448659)), ((bp) field48.get(58558725 ^ 58558726)));
    }

    @Generated
  public List method65() { // было: u
        return field47;
    }

  private static int pv(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int pw(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int px(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}