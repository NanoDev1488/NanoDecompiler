// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.bN
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.AngelVisuals;
import dev.angelvisuals.a.ClassA129;
import dev.angelvisuals.a.ClassA2;
import dev.angelvisuals.a.ClassA42;
import dev.angelvisuals.a.ClassA84;
import dev.angelvisuals.a.aH;
import dev.angelvisuals.a.aM;
import dev.angelvisuals.a.bA;
import dev.angelvisuals.a.bN_ClassA130;
import dev.angelvisuals.a.cA;
import dev.angelvisuals.a.cG;
import dev.angelvisuals.a.cK;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Stream;
import net.minecraft.class_1041;

public class bN {

    // ---- поля ----
  private bN_ClassA130 field659; // было: a
  private cG field660; // было: a
  private static final Map field661; // было: c
  private final Map field662; // было: d
  private final Map field663; // было: e
  private final Map field664; // было: f
  private final Map field665; // было: g
  private final Map field666; // было: h
  private final Map field667; // было: i
  private final Map field668; // было: j
  private final ClassA2 field669; // было: v
  private final Map field670; // было: k
  private final Map field671; // было: l
  private final Map field672; // было: m
  private final List field673; // было: s
  private float ce;
  private float cf;
  private ClassA84 field674; // было: a
  private cK field675; // было: d
  private float cg;
  private boolean field676; // было: U
  private String pO;
  private int jo;
  private cA field677; // было: a
  private int jp;
  private boolean field678; // было: V
  private bA field679; // было: G
  private static final String pP = "// stop. seriously. go play minecraft instead";
  private static final String pQ = "// this jar protected by JoinerObfuscator";
  private static final String pR = "// reverse-engineering this jar is a waste of time, friend";
  private static final String pS = "// number obfuscation: ENABLED (XOR masking)";
  private static final String pT = "Protected by t.me/JoinerClient";
  private static final int jq = -325526476;
  private static final int jr = 1490052434;
  private static final int js = -91249455;
  private static final byte[] cd;

    static {
        cd = "QI&mGN)]5(l}qpq3iAx`]}f_*}R}^D>T9G9bs.Obb{0 sgSBdE>L*\\viwDS1I)BpGI[cTJX7A4FfY05Cz:h21G%^/tm3_+_Y]|qU8,h*xRG1~# rBLkkF 5-8]OsrN';z{MTk\"3)b_JU @VuhRiYRFm9Dqe(NKoWV.2?bt)V8-G^s?irQL9pEE+Vr{7;zCAYMu|%AVVQ$t8f?|z$vd.6gOu^}Q%>#pP7%F=8xLE8_AE~X^uLmIX/SUc0>GWZ9?Jx".getBytes("ISO-8859-1");
        field661 = new HashMap();
        String var0 = Decryptor.method1945(XorDecoder.method1946("ÿ\rÑôÔ5ðä#öï0òÛõ&ÅÉÝ&ÿûÃ\u0014þïå\u0003ÿõVËÙ\u0008öêÆQþöT¬íØZðüïIñðÝ\u0015øÇZÏÌæ\u0006êûÂ4ÉÚWÛÛó;éÈú8ÙÇï.æ÷\u0011ÏÇì ¬íçM®Ïú\u0003íéÎ[ÒØ��òÍäRÒÔì3æØä*®ÖâSÿÊç\u0006ªÀ8Úß3Äÿå#ÝÚÇ\u0007ÈòÛ\u001b¯×ãQùëÇ[ØÅ\u0005Äí[¯Õ\u0004øß÷\u0014èà6äý\u000eÓå:ÍÕá(¤", -1260524167 ^ 683629508));
        String var1 = Decryptor.method1945(XorDecoder.method1946("DÃD\n¬dèJàGçyáxáeånÃPÜDîvÇqÐ`÷tôXæyîcð^ß^Ã{×cò#õ|¦\"xñ|çyòrþb÷[Rñ]Üc£[ð}¾n×w§[Â\\Äcë}¤då\u0018¦\"ðcÝ'×f¢EìWÖQÿ]â*", 2010483456 ^ 1614854310));
        int var2 = Math.min(var0.length(), var1.length());
        int var3 = -1365781691 ^ -1365781691;
        while (var3 < var2) {
            field661.put(Character.valueOf(var0.charAt(var3)), Character.valueOf(var1.charAt(var3)));
            ++var3;
            continue;
        }
    }

  public bN() { // было: <init>
        super();
        field659 = bN_ClassA130.field657;
        field660 = cG.field313;
        field662 = new IdentityHashMap();
        field663 = new IdentityHashMap();
        field664 = new IdentityHashMap();
        field665 = new HashMap();
        field666 = new HashMap();
        field667 = new HashMap();
        field668 = new HashMap();
        field669 = new ClassA2(-4653068026313443429L ^ -4653068026313443513L, aH.field21);
        field670 = new EnumMap(cG.class);
        field671 = new EnumMap(cG.class);
        field672 = new EnumMap(cG.class);
        field673 = new ArrayList();
        pO = Decryptor.method1945(XorDecoder.method1946("pI³txª{x¹\u0017j¯sW¼%_×Â", 203319305 ^ -202082742));
        jo = -1201880315 ^ -1201880315;
        jp = 1854145597 ^ 1854145597;
        al();
    }

  public void al() {
        field673.clear();
        field673.addAll(AngelVisuals.getInstance().getModuleManager().method420());
        cG[] var1 = cG.method605();
        int var2 = var1.length;
        int var3 = 1866632611 ^ 1866632611;
        while (var3 < var2) {
            Object var4 = var1[var3];
            field672.put(var4, field673.stream().filter(lp0 -> method1086(((cG) var4), ((cK) lp0))).toList());
            field670.putIfAbsent(var4, Float.valueOf(0.0f));
            field671.putIfAbsent(var4, new ClassA2(-3555442561014276724L ^ -3555442561014276960L, aH.field21));
            ++var3;
            continue;
        }
    }

  public void method1037(class_1041 arg0, int arg1) { // было: a
        float __stk1;
        __stk1 = field659 != bN_ClassA130.field656 ? 250.0f : ClassA129.method1010(arg1);
        float var3 = __stk1;
        ce = ((float) arg0.method_4486()) / 2.0f - var3 / 2.0f;
        cf = ((float) arg0.method_4502()) / 2.0f - 130.0f;
    }

  public bN_ClassA130 method1038() { // было: a
        return field659;
    }

  public void method1039(bN_ClassA130 arg0) { // было: a
        field659 = arg0;
    }

  public cG method1040() { // было: b
        return field660;
    }

  public void method1041(cG arg0) { // было: a
        field660 = arg0;
    }

  public float ao() {
        return ce;
    }

  public float ap() {
        return cf;
    }

  public float aq() {
        return cg;
    }

  public void method1042(float arg0) { // было: A
        cg = arg0;
    }

  public List method1043(cG arg0) { // было: a
        List var2 = ((List) field672.getOrDefault(arg0, List.of()));
        if (!pO.isBlank()) {
            String var3 = pO.toLowerCase(Locale.ROOT);
            return var2.stream().filter(lp0 -> method1085(var3, ((cK) lp0))).toList();
        } else {
            return var2;
        }
    }

  public List method1044() { // было: n
        return field673;
    }

  public String method1045(String arg0) { // было: b
        StringBuilder var2 = new StringBuilder();
        char[] var3 = arg0.toCharArray();
        int var4 = var3.length;
        int var5 = -1019365792 ^ -1019365792;
        while (var5 < var4) {
            char var6 = var3[var5];
            var2.append(field661.getOrDefault(Character.valueOf(var6), Character.valueOf(var6)));
            ++var5;
            continue;
        }
        return var2.toString();
    }

  public float method1046(bA arg0) { // было: a
        float var2 = arg0.br() - arg0.bq();
        return (arg0.bp() - arg0.bq()) / var2;
    }

  public float method1047(bA arg0, float arg1, double arg2) { // было: a
        float var5 = arg0.br() - arg0.bq();
        float var6 = ((float) arg2) - arg1;
        float var7 = Math.max(0.0f, Math.min(1.0f, var6 / 88.0f));
        float var8 = arg0.bq() + var5 * var7;
        float var9 = arg0.bs();
        var8 = ((float) Math.round(var8 / var9)) * var9;
        return Math.max(arg0.bq(), Math.min(arg0.br(), var8));
    }

  public float method1048(cG arg0) { // было: a
        ClassA2 var2 = ((ClassA2) field671.computeIfAbsent(arg0, lp0 -> method1084(((cG) lp0))));
        var2.method6((((Float) field670.getOrDefault(arg0, Float.valueOf(0.0f)))).floatValue());
        return var2.method13();
    }

  public void method1049(cG arg0, float arg1) { // было: a
        float var3 = method1051(arg0);
        float var4 = Math.min(0.0f, arg1 - var3);
        float var5 = (((Float) field670.getOrDefault(arg0, Float.valueOf(0.0f)))).floatValue();
        if (var5 < var4) {
            field670.put(arg0, Float.valueOf(Math.max(var4, Math.min(0.0f, var5))));
        } else {
            if (var5 > 0.0f) {
                field670.put(arg0, Float.valueOf(Math.max(var4, Math.min(0.0f, var5))));
            }
        }
    }

  public void method1050(cG arg0, double arg1, float arg2) { // было: a
        float var5 = method1051(arg0);
        float var6 = Math.min(0.0f, arg2 - var5);
        float var7 = (((Float) field670.getOrDefault(arg0, Float.valueOf(0.0f)))).floatValue();
        float var8 = var7 + ((float) (arg1 * 20.0));
        field670.put(arg0, Float.valueOf(Math.max(var6, Math.min(0.0f, var8))));
    }

  public float method1051(cG arg0) { // было: b
        float var2 = 0.0f;
        Iterator var3 = method1043(arg0).iterator();
        while (var3.hasNext()) {
            cK var4 = ((cK) var3.next());
            var2 = var2 + 4.0f + ClassA129.method1020(var4, method1052(var4));
            continue;
        }
        return var2;
    }

  public float method1052(cK arg0) { // было: b
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #125 // dev.angelvisuals.a.bN.d:Ljava/util/Map;
        //      4: aload_1
        //      5: invokedynamic  #220 // invokedynamic apply:()Ljava/util/function/Function;
        //     10: invokeinterface  #211 // java.util.Map.computeIfAbsent:(Ljava/lang/Object;Ljava/util/function/Function;)Ljava/lang/Object;, count 3
        //     15: checkcast  #52 // dev.angelvisuals.a.k
        //     18: astore_2
        //     19: aload_2
        //     20: aload_0
        //     21: aload_1
        //     22: invokevirtual  #163 // dev.angelvisuals.a.bN.b:(Ldev/angelvisuals/a/cK;)Z
        //     25: ifeq  32 (offset +7)
        //     28: fconst_1
        //     29: goto  33 (offset +4)
        //     32: fconst_0
        //     33: invokevirtual  #174 // dev.angelvisuals.a.k.a:(F)F
        //     36: pop
        //     37: aload_2
        //     38: invokevirtual  #176 // dev.angelvisuals.a.k.e:()F
        //     41: freturn
    }

  public boolean method1053(cK arg0) { // было: b
        return (((Boolean) field663.getOrDefault(arg0, Boolean.valueOf(-1089449396 ^ -1089449396)))).booleanValue();
    }

  public void method1054(cK arg0) { // было: b
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #126 // dev.angelvisuals.a.bN.e:Ljava/util/Map;
        //      4: aload_1
        //      5: aload_0
        //      6: aload_1
        //      7: invokevirtual  #163 // dev.angelvisuals.a.bN.b:(Ldev/angelvisuals/a/cK;)Z
        //     10: ifne  21 (offset +11)
        //     13: ldc  #20 // 1647132003
        //     15: ldc  #19 // 1647132002
        //     17: ixor
        //     18: goto  26 (offset +8)
        //     21: ldc  #14 // 232853363
        //     23: ldc  #14 // 232853363
        //     25: ixor
        //     26: invokestatic  #178 // java.lang.Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //     29: invokeinterface  #213 // java.util.Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;, count 3
        //     34: pop
        //     35: return
    }

  public ClassA2 method1055(aM arg0) { // было: a
        return ((ClassA2) field665.computeIfAbsent(arg0, lp0 -> method1082(((aM) lp0))));
    }

  public ClassA2 method1056(aM arg0) { // было: b
        return ((ClassA2) field666.computeIfAbsent(arg0, lp0 -> method1081(((aM) lp0))));
    }

  public ClassA2 method1057(bA arg0) { // было: a
        return ((ClassA2) field667.computeIfAbsent(arg0, lp0 -> method1080(((bA) lp0))));
    }

  public ClassA2 method1058(String arg0, boolean arg1) { // было: a
        return ((ClassA2) field668.computeIfAbsent(arg0, lp0 -> method1079(((String) lp0))));
    }

  public ClassA2 method1059(cK arg0) { // было: a
        return ((ClassA2) field664.computeIfAbsent(arg0, lp0 -> method1078(((cK) lp0))));
    }

  public ClassA84 method1060() { // было: a
        return field674;
    }

  public void method1061(ClassA84 arg0) { // было: a
        field674 = arg0;
    }

  public cK method1062() { // было: b
        return field675;
    }

  public void method1063(cK arg0) { // было: c
        field675 = arg0;
    }

  public boolean method1064() { // было: K
        return field676;
    }

  public void method1065(boolean arg0) { // было: i
        field676 = arg0;
    }

  public String method1066() { // было: P
        return pO;
    }

  public void method1067(String arg0) { // было: e
        pO = arg0;
    }

  public int method1068() { // было: O
        return jo;
    }

  public void method1069(int arg0) { // было: n
        jo = arg0;
    }

  public cA method1070() { // было: a
        return field677;
    }

  public void method1071(cA arg0) { // было: a
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: aload_1
        //      2: putfield  #116 // dev.angelvisuals.a.bN.a:Ldev/angelvisuals/a/cA;
        //      5: aload_0
        //      6: aload_1
        //      7: ifnonnull  18 (offset +11)
        //     10: ldc  #16 // 1142491745
        //     12: ldc  #16 // 1142491745
        //     14: ixor
        //     15: goto  25 (offset +10)
        //     18: aload_1
        //     19: invokevirtual  #168 // dev.angelvisuals.a.cA.V:()Ljava/lang/String;
        //     22: invokevirtual  #191 // java.lang.String.length:()I
        //     25: putfield  #133 // dev.angelvisuals.a.bN.jp:I
        //     28: return
    }

  public int method1072() { // было: P
        return jp;
    }

  public void method1073(int arg0) { // было: o
        jp = arg0;
    }

  public boolean method1074() { // было: L
        return field678;
    }

  public void method1075(boolean arg0) { // было: j
        field678 = arg0;
    }

  public boolean method1076(bA arg0) { // было: a
        return field679 != arg0 ? -441647166 ^ -441647166 : 2107602693 ^ 2107602692;
    }

  public void method1077(bA arg0) { // было: a
        field679 = arg0;
    }

  public float ar() {
        field669.method5(field678);
        return field669.method13();
    }

  private static ClassA2 method1078(cK arg0) { // было: b
        return new ClassA2(3551188170793980515L ^ 3551188170793980587L, aH.field21);
    }

  private static ClassA2 method1079(String arg0) { // было: a
        return new ClassA2(-1845331333147295241L ^ -1845331333147295425L, aH.field21);
    }

  private static ClassA2 method1080(bA arg0) { // было: b
        return new ClassA2(1357768299839804569L ^ 1357768299839804853L, aH.field21);
    }

  private static ClassA2 method1081(aM arg0) { // было: c
        return new ClassA2(-2795039572487171365L ^ -2795039572487171565L, aH.field21);
    }

  private static ClassA2 method1082(aM arg0) { // было: d
        return new ClassA2(-8253691981738313377L ^ -8253691981738313321L, aH.field21);
    }

  private static ClassA2 method1083(cK arg0) { // было: c
        return new ClassA2(-5249626393527957277L ^ -5249626393527957479L, aH.field21);
    }

  private static ClassA2 method1084(cG arg0) { // было: a
        return new ClassA2(-6079552841019223014L ^ -6079552841019222816L, aH.field21);
    }

  private static boolean method1085(String arg0, cK arg1) { // было: b
        return arg1.getName().toLowerCase(Locale.ROOT).contains(arg0);
    }

  private static boolean method1086(cG arg0, cK arg1) { // было: a
        return arg1.method617() != arg0 ? -213979706 ^ -213979706 : -821841240 ^ -821841239;
    }

  private static int ig(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int ih(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int ii(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}