// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.dC
package dev.angelvisuals.a;

import com.darkmagician6.eventapi.EventTarget;
import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.a.aM;
import dev.angelvisuals.a.ap;
import dev.angelvisuals.a.bA;
import dev.angelvisuals.a.bI;
import dev.angelvisuals.a.bp;
import dev.angelvisuals.a.bx;
import dev.angelvisuals.a.cK;
import net.minecraft.class_1041;
import net.minecraft.class_239;
import net.minecraft.class_239.class_240;
import net.minecraft.class_310;
import net.minecraft.class_315;
import net.minecraft.class_5498;
import net.minecraft.class_746;

@bI(name = "Crosshair", a = "RENDER", I = "Кастомный прицел")
public final class dC extends cK {

    // ---- поля ----
  public static final dC field405; // было: a
  private final bA ac;
  private final bA ad;
  private final bA ae;
  private final aM field406; // было: j
  private final aM field407; // было: k
  private final bp field408; // было: o
  private static final String EE = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String EF = "// you are reading machine-generated garbage";
  private static final String EG = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final String EH = "// reverse-engineering this jar is a waste of time, friend";
  private static final String EI = "// stop. seriously. go play minecraft instead";
  private static final int rZ = -778434606;
  private static final int sa = 1253121223;
  private static final int sb = 937164939;
  private static final byte[] eK;

    static {
        eK = "I{82uEY7z@ux]1Avy B5v}e-x'Bvhb[CAii?{\\|YI0NA&#{Sm5S~rD8V(J^PA>zX&sN0DR&l-]V,r!aEo|Ub`mi\"Mu\"0^8Xggo+nI82#.`w)0]ybE \\T?X_F0Wwq0+en|4M+h9j\"@B{r;RxVm6V@u@Q{&9p^z2;f~T]nhqZ4-~x1iQ:a\"b\\!]B@3]Fi(Nw'8:*18{t;IJYxuoup'\"-X;xO88R!S<BMIm:h.}u![fARERy'wr@vm0^c9aSlE%gBc{".getBytes("ISO-8859-1");
        field405 = new dC();
    }

  private dC() { // было: <init>
        super();
        ac = new bA(Decryptor.method1945(XorDecoder.method1946("n\u001aõ\u0016P\u0017¡4E\u0011Mm\u0007é\u000fkM·?(\u0019ÿE", 1448869541 ^ 781811897)), 1.0f, 0.5f, 3.0f, 0.10000000149011612f);
        ad = new bA(Decryptor.method1945(XorDecoder.method1946("ñ®ÅòëÉÛÒý¦ÞæÂÈÆ¢ù­Ý¯¡", -1494347664 ^ 981268213)), 3.0f, 1.0f, 8.0f, 0.5f);
        ae = new bA(Decryptor.method1945(XorDecoder.method1946("\u0002Ò\u0018$½ó\u0018|Õ\u0015\n©.:«40¡c", 832144187 ^ 1862616946)), 2.0f, 0.0f, 5.0f, 0.5f);
        field406 = new aM(Decryptor.method1945(XorDecoder.method1946("Øä×Añß£kÙßP±åùl´ù¤UÌáeÐ¦OÍÙvÎüÝLéæña±ó¬hÔûû\u0014ÄÂÀ\u0010ôÒø\tÙÍÕdúìà`", -800406873 ^ -153192921)), -1488857079 ^ -1488857079);
        field407 = new aM(Decryptor.method1945(XorDecoder.method1946("p\u0003T1I\nm\u0015m\u0001@\u0015T\u0001J%H_Dwr<;7I\u0017su{\\2 +&N9!.vwH[I,.\u0018MqH\\E9I\u0003[;S\u0008I;WTHr", 336717185 ^ 1460897945)), -262234937 ^ -262234937);
        field408 = new bp(-165189955 ^ -165190078, -1138867283 ^ -1138867283, 1278022033 ^ 1278022033, 1477105232 ^ 1477105327);
    }

    @EventTarget
  public void method796(bx arg0) { // было: e
        bp __stk1;
        if (mc.field_1724 != null) {
            if (mc.field_1687 != null) {
                if (mc.field_1690.method_31044() == class_5498.field_26664) {
                    ap var2 = arg0.method318();
                    float var3 = ((float) mc.method_22683().method_4486()) / 2.0f;
                    float var4 = ((float) mc.method_22683().method_4502()) / 2.0f;
                    float var5 = ae.bp();
                    float var6;
                    if (field406.method650()) {
                        var6 = 1.0f - mc.field_1724.method_7261(0.0f);
                        var5 = var5 + 8.0f * var6;
                    }
                    float var6 = ac.bp();
                    float var7 = ad.bp();
                    __stk1 = !field407.method650() ? new bp(1132978668 ^ 1132978451, -204331995 ^ -204331814, -708298641 ^ -708298608, -1386984549 ^ -1386984604) : mc.field_1765 == null ? new bp(1132978668 ^ 1132978451, -204331995 ^ -204331814, -708298641 ^ -708298608, -1386984549 ^ -1386984604) : mc.field_1765.method_17783() != class_240.field_1331 ? new bp(1132978668 ^ 1132978451, -204331995 ^ -204331814, -708298641 ^ -708298608, -1386984549 ^ -1386984604) : field408;
                    bp var8 = __stk1;
                    method797(var2, var3 - var6 / 2.0f, var4 - var5 - var7, var6, var7, ((bp) var8));
                    method797(var2, var3 - var6 / 2.0f, var4 + var5, var6, var7, ((bp) var8));
                    method797(var2, var3 - var5 - var7, var4 - var6 / 2.0f, var7, var6, ((bp) var8));
                    method797(var2, var3 + var5, var4 - var6 / 2.0f, var7, var6, ((bp) var8));
                }
            }
        }
    }

  private void method797(ap arg0, float arg1, float arg2, float arg3, float arg4, bp arg5) { // было: a
        arg0.method1645(arg1, arg2, arg3, arg4, arg5);
    }

  private static int qc(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int qd(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int qe(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}