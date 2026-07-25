// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.bX
package dev.angelvisuals.a;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.a.ClassA84;
import dev.angelvisuals.a.bX_ClassA86;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.class_1792;
import net.minecraft.class_2248;

public class bX extends ClassA84 {

    // ---- поля ----
  private List field331; // было: z
  private static final Gson field332; // было: a
  private static final String wl = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final String wm = "// flow obfuscation: ENABLED";
  private static final String wn = "// number obfuscation: ENABLED (XOR masking)";
  private static final String wo = "// Joiner sees you";
  private static final String wp = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final int nd = -2135542539;
  private static final int ne = 1822280897;
  private static final int nf = -1177607542;
  private static final byte[] dk;

    static {
        dk = "j.phL+Q@}~fS0'XjH1-9G!{$H#8JuAZ8s4#t0I+?]aS1+AtfpOkZBCZ2<U#r}IjHK=9e-#R-=_;T_[LrR!xOY*?I>:$7!;5Q_<{/ot:R8sE&B6boJN`U`UeZlz~O!WK6<RE?R<^Px'yF{fL[ a6&G=w|'H]OOjo990d,.+E6%IQ1vmuIIRkpP*5>Y[#,S342q\"&;Oc.E{Tm(xRCVxY'8NK-4TQ=M[6]6IC QXisllMK,rNRlb=)_7WE6D\"TrXB)/".getBytes("ISO-8859-1");
        field332 = new Gson();
    }

  public void method670(List arg0) { // было: a
        field331 = arg0;
    }

  public bX(String arg0, List arg1) { // было: <init>
        this(arg0, arg1, () -> method683());
    }

  public bX(String arg0, List arg1, Supplier arg2) { // было: <init>
        super(arg0);
        field331 = arg1;
    }

  public List method671() { // было: s
        return field331;
    }

  public void method672(String arg0) { // было: j
        field331.add(arg0);
    }

  public void method673(String arg0) { // было: k
        field331.remove(arg0);
    }

  public boolean method674(String arg0) { // было: j
        return field331.contains(arg0);
    }

  public void method675(class_2248 arg0) { // было: a
        method672(arg0.method_63499().replace(Decryptor.method1945(XorDecoder.method1946("\u00023¿\u0006'¨Â\u0003\rè¿&!¹t\u0012­»|F«ªnDí¿\u0015?è\u0010@£!4°+��å", 1961477714 ^ -1398897733)), Decryptor.method1945(XorDecoder.method1946("ò¿Ú\u001eöÁ\u0007ùþ\"è>ñ¡û;§©o", -674477531 ^ -2057198876))));
    }

  public void method676(class_1792 arg0) { // было: a
        method672(arg0.method_7876().replace(Decryptor.method1945(XorDecoder.method1946("%Á.\u000b\u001bâW\u0003&ñO\u001d;ÿz\u0019��âx6\u0002Á&r", 603370706 ^ 1827480738)), Decryptor.method1945(XorDecoder.method1946("lÅÖhôÏgôªê\u000bæ¼öoÛ¯ó9ÓÄ§", -1220534848 ^ 767135647))));
    }

  public void method677(class_2248 arg0) { // было: b
        method673(arg0.method_63499().replace(Decryptor.method1945(XorDecoder.method1946("PEcÀEAw×\u0014D]iaqþo3BÒm;\u0016Ô|)\u0014iRoNW\u0010õufdáflP", 1592122597 ^ -104669478)), Decryptor.method1945(XorDecoder.method1946("z\u0010.<~!5%q!\n��\u001d3\u001c\u001cy\u000e\u000f\u0019/\u0006dM", 1655035978 ^ 318545667))));
    }

  public void method678(class_1792 arg0) { // было: b
        method673(arg0.method_7876().replace(Decryptor.method1945(XorDecoder.method1946("¥\u001e×g=®o¦.¶q» u=Z\u001eß\u001e", -449309603 ^ -958768211)), Decryptor.method1945(XorDecoder.method1946("\u0016\u0005-G\u001246^\u001d4\t{q&\u001fg\u0015\u001b\u000cbC\u0013g6", -266989459 ^ -78888376))));
    }

  public boolean method679(class_2248 arg0) { // было: b
        return method674(arg0.method_63499().replace(Decryptor.method1945(XorDecoder.method1946("\u0018ÊÁ\rÎÕ\\ËÿÔ!îÓ½'¼à%´´4¦¶Ñ!ÝÍÔ\u0006Ø²¶=éÆ¢.ãòÙ", -1397360775 ^ 1211136526)), Decryptor.method1945(XorDecoder.method1946("LQ@H`YG`¿|+r©`OOºe\u0019GÑ1", -1077421212 ^ -1288970981))));
    }

  public boolean method680(class_1792 arg0) { // было: a
        return method674(arg0.method_7876().replace(Decryptor.method1945(XorDecoder.method1946("°|\u0006±\u0005\u000e\u001d\u0010(\u0014ª*;¨°t\u007f", 1180296708 ^ 68159454)), Decryptor.method1945(XorDecoder.method1946("è\u00101Ù\u000b(Ù4\ræË\"\u0011ö1\u0014ÔþZ@", -1847293550 ^ -326945248))));
    }

  public void aA() {
        field331.clear();
    }

  public void method681(JsonObject arg0) { // было: a
        arg0.add(String.valueOf(aD), field332.toJsonTree(method671()));
    }

  public void method682(JsonObject arg0) { // было: b
        Type var2 = new bX_ClassA86(this).getType();
        JsonElement var3 = arg0.get(String.valueOf(aD));
        if (var3 != null) {
            if (var3.isJsonArray()) {
                List var4 = ((List) field332.fromJson(var3, var2));
                method670(var4);
            }
        }
    }

  private static Boolean method683() { // было: n
        return Boolean.valueOf(1671015367 ^ 1671015366);
    }

  private static int lB(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int lC(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int lD(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}