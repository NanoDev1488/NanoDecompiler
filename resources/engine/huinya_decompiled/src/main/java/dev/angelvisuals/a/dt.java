// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.dt
package dev.angelvisuals.a;

import com.darkmagician6.eventapi.EventTarget;
import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.AngelVisuals;
import dev.angelvisuals.a.aZ;
import dev.angelvisuals.a.as;
import dev.angelvisuals.a.bA;
import dev.angelvisuals.a.bI;
import dev.angelvisuals.a.bl;
import dev.angelvisuals.a.bp;
import dev.angelvisuals.a.cK;
import dev.angelvisuals.a.ch;
import dev.angelvisuals.a.dD;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_757;
import net.minecraft.class_7833;
import net.minecraft.class_9779;
import org.joml.Vector4i;

@bI(name = "TargetESP", a = "RENDER", I = "Подсветка игрока под прицелом")
public final class dt extends cK {

    // ---- поля ----
  public static final dt field575; // было: a
  private static final class_2960 field576; // было: l
  public final aZ field577; // было: i
  public final bA field578; // было: Z
  public final bA aa;
  private static final String Dq = "// Joiner sees you";
  private static final String Dr = "// flow obfuscation: ENABLED";
  private static final String Ds = "// good luck with the next 9999 classes";
  private static final String Dt = "// good luck with the next 9999 classes";
  private static final String Du = "// every class watermarked, every string encrypted, every number xored";
  private static final int rt = -638032428;
  private static final int ru = 1911780977;
  private static final int rv = -1053908748;
  private static final byte[] eA;

    static {
        eA = "%o'$E)v:f1*X^eM*M:P37o~kFjLSvTe.eCR:jv=@z[>X,t.HfN|+5iysw)s%yI5`0<b%3eLEq,F/{[bnZ:+iu~*a+ii\",e.)ah6ALes]Gw.{7GS'OCih2s`<K14WKt,,K]*YX~?T=O>;3YStgqkh5BG'\"^\\x#k(\"wwAy f]}-\\{M-nJ!Yu\"9F) S8_=^_KJw/Uv@wIq2uQ^T0$8%l7r|{09M%gm6E=f{X_'?-Z2<1@%ehdIO@=X[~;=^3n@\"ya7H".getBytes("ISO-8859-1");
        field575 = new dt();
        field576 = AngelVisuals.id(Decryptor.method1945(XorDecoder.method1946("¸ì%/¨û\t8£å**¤Î9\tÓY\u0012åüX!ë\u0001Uà^.úZ\u000f¯Ì\u0019I½Ä\u001b_", -1586394905 ^ -1021501139)));
    }

  private dt() { // было: <init>
        super();
        String[] __obj1 = new String[307922692 ^ 307922694];
        __obj1[-946316682 ^ -946316682] = Decryptor.method1945(XorDecoder.method1946("¯áËÇ¶ý¸ðÐÝ»ð÷«ÅåÞÙ·", -332799830 ^ 1604544614));
        __obj1[-1366825430 ^ -1366825429] = Decryptor.method1945(XorDecoder.method1946("fn¨C\u0017[¿\u007fjWsRWÊCXL­R\u0016NØ&", 1577763403 ^ 1173343593));
        field577 = new aZ(Decryptor.method1945(XorDecoder.method1946("÷O®þ¢q©ÛôKÛ®½C½©Èa¨çæfÐ¢", 1686118224 ^ -76728126)), __obj1);
        field578 = new bA(Decryptor.method1945(XorDecoder.method1946("¿[z\u000fX(!¼GcºÀ\u0019SùQ)", 17774688 ^ 358861741)), 1.5f, 0.5f, 5.0f, 0.25f);
        aa = new bA(Decryptor.method1945(XorDecoder.method1946("¶«»æ°¨É²§Áö¦ó·ÆÍ¾ÀìÀ´Æ¶Ñ¶ðÂ§Ð¸­ü¾ô¬ªÌ", -1075657667 ^ 1316753816)), 1.649999976158142f, 0.5f, 3.0f, 0.05000000074505806f, () -> method960());
    }

    @EventTarget
  public void method959(dD arg0) { // было: j
        class_1297 var3 = mc.field_1692;
        if (!(var3 instanceof class_1657)) {
            return;
        } else {
            class_1657 var2 = ((class_1657) var3);
            if (var2 == mc.field_1724) {
                return;
            } else {
                if (!var2.method_31481()) {
                    var3 = AngelVisuals.getInstance().getThemeManager().method481().method449().method1680() & (-1068603749 ^ -1062102684);
                    if (field577.method696(Decryptor.method1945(XorDecoder.method1946("XH@Z)}WfTqhjlq\"ZfjEK(h0?", -1357726782 ^ -1390439714)))) {
                        class_243 var4 = mc.field_1773.method_19418().method_19326();
                        class_243 var5 = var2.method_30950(mc.method_61966().method_60637(-1661257052 ^ -1661257052)).method_1031(0.0, ((double) var2.method_17682()) * 0.55, 0.0).method_1020(var4);
                        float var6 = aa.bp();
                        float var7 = 0.8799999952316284f + 0.11999999731779099f * ((float) Math.sin(((double) System.currentTimeMillis()) / 260.0));
                        arg0.method324().method_22903();
                        arg0.method324().method_22904(var5.field_1352, var5.field_1351, var5.field_1350);
                        arg0.method324().method_22907(mc.field_1773.method_19418().method_23767());
                        arg0.method324().method_22907(class_7833.field_40718.rotationDegrees(((float) (((double) System.currentTimeMillis()) / 12.0 % 360.0))));
                        int var8 = ((int) (220.0f * var7)) << (-1534075232 ^ -1534075208) | -1824714528 ^ -1815941345;
                        as.method1821(arg0.method324().method_23760(), field576, -var6 * 0.5f, -var6 * 0.5f, var6, var6, new Vector4i(var8, var8, var8, var8), -451966957 ^ -451966957);
                        arg0.method324().method_22909();
                        return;
                    } else {
                        as.method1810(var2.method_5829().method_1014(0.04), -957189890 ^ -1594724098 | var3, field578.bp(), -1699026435 ^ -1699026436, 1718035890 ^ 1718035891, 1335735663 ^ 1335735663);
                        return;
                    }
                } else {
                    return;
                }
            }
        }
    }

  private Boolean method960() { // было: q
        return Boolean.valueOf(field577.method696(Decryptor.method1945(XorDecoder.method1946("\u0015\tÜd¨\u001eà\u0019¤!ì!¤kÜ+¿\u000cÍe½y¹", 946869150 ^ -1137381425))));
    }

  private static int py(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int pz(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int pA(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}