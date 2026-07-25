// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.I
package dev.angelvisuals.a;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.a.ClassA2;
import dev.angelvisuals.a.ClassA84;
import dev.angelvisuals.a.ClassA88_ClassA89;
import dev.angelvisuals.a.aH;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Generated;

public class ClassA90 extends ClassA84 {

    // ---- поля ----
  private final List field339; // было: e
  private final ClassA2 field340; // было: d
  private static final String cE = "// every class watermarked, every string encrypted, every number xored";
  private static final String cF = "// nice try. closed source for a reason.";
  private static final String cG = "// you are reading machine-generated garbage";
  private static final String cH = "// stop. seriously. go play minecraft instead";
  private static final String cI = "// reverse-engineering this jar is a waste of time, friend";
  private static final int bo = -195459865;
  private static final int bp = 1717469340;
  private static final int bq = -1511568425;
  private static final byte[] field341; // было: K

    static {
        field341 = "]A2@Y$chTR6@b\"-pUyy{#Km1_G2aH=Gwo|,.Gv >$S]AHwraa[2x&;1C8s7qg*r0K>!tN4DE5-PB' .+U*E+xmzz-oo7 D-:~Js<Ds-l +.{n-?[2:75Z'w*OT; yrev%b%>-@WFUYm7346(9n4E:Dx$&G:g^Lp(mMRXFz}x$fl<63*WVsUTTXACXM#*o0T:YDzG]LofE0vxZQ_(4tfFp#qWGxqN,-l8hq}vDWtz.7NO&z}!DT66<@b4vhbln|e_".getBytes("ISO-8859-1");
    }

  public ClassA90(String arg0) { // было: <init>
        super(arg0);
        field340 = new ClassA2(-6204144857296408895L ^ -6204144857296409079L, aH.field21);
        field339 = new ArrayList();
    }

  public ClassA90(String arg0, ClassA88_ClassA89[] arg1) { // было: <init>
        super(arg0);
        field340 = new ClassA2(157905522297724289L ^ 157905522297724233L, aH.field21);
        field339 = new ArrayList(Arrays.asList(arg1));
    }

  public ClassA88_ClassA89 method712(String arg0) { // было: a
        return ((ClassA88_ClassA89) field339.stream().filter(lp0 -> method727(arg0, ((ClassA88_ClassA89) lp0))).findFirst().orElse(null));
    }

  public static ClassA90 method713(String arg0, List arg1) { // было: a
        ClassA88_ClassA89[] var2 = ((ClassA88_ClassA89[]) arg1.stream().map(lp0 -> method726(((String) lp0))).toArray(lp0 -> method725(lp0)));
        return new ClassA90(arg0, var2);
    }

  public ClassA88_ClassA89 method714(int arg0) { // было: a
        return ((ClassA88_ClassA89) field339.get(arg0));
    }

  public boolean method715(String arg0) { // было: d
        ClassA88_ClassA89 var2 = method712(arg0);
        return var2 == null ? 844055253 ^ 844055253 : !var2.aj() ? 844055253 ^ 844055253 : -502802740 ^ -502802739;
    }

  public boolean method716(int arg0) { // было: b
        if (arg0 < method721().size()) {
            ClassA88_ClassA89 var2 = method714(arg0);
            return var2 == null ? 1593704504 ^ 1593704504 : !var2.aj() ? 1593704504 ^ 1593704504 : 2130859469 ^ 2130859468;
        } else {
            return 1522553643 ^ 1522553643;
        }
    }

  public List method717() { // было: b
        return ((List) field339.stream().filter(lp0 -> (((ClassA88_ClassA89) lp0)).aj()).collect(Collectors.toList()));
    }

  public List method718() { // было: c
        return ((List) field339.stream().filter(lp0 -> (((ClassA88_ClassA89) lp0)).aj()).map(lp0 -> (((ClassA88_ClassA89) lp0)).ab()).collect(Collectors.toList()));
    }

  public void method719(JsonObject arg0) { // было: a
        StringBuilder var2 = new StringBuilder();
        int var3 = 405857290 ^ 405857290;
        Iterator var4 = method721().iterator();
        while (var4.hasNext()) {
            ClassA88_ClassA89 var5 = ((ClassA88_ClassA89) var4.next());
            if (method712(var5.ab()).aj()) {
                var2.append(var5.ab()).append(Decryptor.method1945(XorDecoder.method1946("õÜØfüÏÏ\u0012Õ¯Ò\u0001§³\u001fÏÍÜ\"òÅl", -784063068 ^ -2131123910)));
            }
            ++var3;
            continue;
        }
        arg0.addProperty(e(), var2.toString());
    }

  public void method720(JsonObject arg0) { // было: b
        method721().forEach(lp0 -> method724(((ClassA88_ClassA89) lp0)));
        String[] var2 = arg0.get(String.valueOf(aD)).getAsString().split(Decryptor.method1945(XorDecoder.method1946("ú3\u001d[ó \n/Ú@\u0017<¨\\H\"À\"\u0019\u001fý*EQ", -1824309276 ^ -12888971)));
        String[] var3 = var2;
        int var4 = var2.length;
        int var5 = -445984963 ^ -445984963;
        while (var5 < var4) {
            Object var6 = var3[var5];
            ClassA88_ClassA89 var7 = method712(((String) var6));
            if (var7 != null) {
                method712(((String) var6)).method711(1238491027 ^ 1238491026);
            }
            ++var5;
            continue;
        }
    }

    @Generated
  public List method721() { // было: d
        return field339;
    }

    @Generated
  public ClassA2 method722() { // было: b
        return field340;
    }

  public void method723(boolean arg0) { // было: e
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #63 // dev.angelvisuals.a.I.d:Ldev/angelvisuals/a/k;
        //      4: iload_1
        //      5: ifeq  14 (offset +9)
        //      8: getstatic  #66 // dev.angelvisuals.a.aH.i:Ldev/angelvisuals/a/aH;
        //     11: goto  17 (offset +6)
        //     14: getstatic  #65 // dev.angelvisuals.a.aH.h:Ldev/angelvisuals/a/aH;
        //     17: invokevirtual  #86 // dev.angelvisuals.a.k.a:(Ldev/angelvisuals/a/aH;)V
        //     20: aload_0
        //     21: getfield  #63 // dev.angelvisuals.a.I.d:Ldev/angelvisuals/a/k;
        //     24: iload_1
        //     25: invokevirtual  #87 // dev.angelvisuals.a.k.a:(Z)V
        //     28: return
    }

  private static void method724(ClassA88_ClassA89 arg0) { // было: a
        arg0.method711(-633346899 ^ -633346899);
    }

  private static ClassA88_ClassA89[] method725(int arg0) { // было: a
        return new ClassA88_ClassA89[arg0];
    }

  private static ClassA88_ClassA89 method726(String arg0) { // было: b
        return new ClassA88_ClassA89(arg0, 1062420330 ^ 1062420331);
    }

  private static boolean method727(String arg0, ClassA88_ClassA89 arg1) { // было: a
        return arg1.ab().equalsIgnoreCase(arg0);
    }

  private static int be(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int bf(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int bg(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}