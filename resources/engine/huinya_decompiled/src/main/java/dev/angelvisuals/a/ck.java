// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.cK
package dev.angelvisuals.a;

import com.darkmagician6.eventapi.EventManager;
import com.google.gson.JsonObject;
import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.a.ClassA146;
import dev.angelvisuals.a.ClassA2;
import dev.angelvisuals.a.ClassA84;
import dev.angelvisuals.a.aH;
import dev.angelvisuals.a.ak;
import dev.angelvisuals.a.bI;
import dev.angelvisuals.a.cG;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public class cK implements ClassA146, Comparable {

    // ---- поля ----
  protected bI field318; // было: a
  private String xh;
  private final cG field319; // было: g
  private volatile boolean ah;
  private int nI;
  private ClassA2 field320; // было: G
  private ClassA2 field321; // было: H
  private static final String xi = "// number obfuscation: ENABLED (XOR masking)";
  private static final String xj = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final String xk = "Protected by t.me/JoinerClient";
  private static final String xl = "// class hierarchy hashing: ENABLED";
  private static final String xm = "// === DO NOT TOUCH ===";
  private static final int nJ = -1285345808;
  private static final int nK = -2089558227;
  private static final int nL = 337343929;
  private static final byte[] dt;

    static {
        dt = "1!8r]v.F<_Zl-1G|xH0OqS+g#S>+NIRdlj~(;,ugMko{p^]}:=9V0%6nmK]H@aEBl#m| GzZel?zlIe<'2dB&z[}0j>1EfIz>KX%S(&aKl=}</<H*-,cV5n5{u,n)VYy^]\"`Mhg@Or@?eo.pI^BOX-rc][@Fx56gMZ,JJ1)$'^`WclEZy{7DnM)J<\")6LIEzf01^CV u?>~}M?&_6hL[n1,8d?71p?N[;^0nY[;#A+!ULY9G+]G:6DEs.BueWC$o".getBytes("ISO-8859-1");
    }

  protected cK() { // было: <init>
        super();
        field318 = ((bI) getClass().getAnnotation(bI.class));
        field320 = new ClassA2(-2851822233551099170L ^ -2851822233551099356L, aH.field21);
        field321 = new ClassA2(-531338741847415271L ^ -531338741847415069L, aH.field21);
        xh = field318.name();
        field319 = field318.method629();
        ah = -1713186257 ^ -1713186257;
        nI = 1606296404 ^ -1606296405;
    }

  public void method609(boolean arg0) { // было: p
        if (!arg0) {
            method611();
        } else {
            method610();
        }
        ah = arg0;
    }

  public void aD() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: aload_0
        //      2: getfield  #133 // dev.angelvisuals.a.cK.ah:Z
        //      5: ifne  16 (offset +11)
        //      8: ldc  #27 // -653428555
        //     10: ldc  #26 // -653428556
        //     12: ixor
        //     13: goto  21 (offset +8)
        //     16: ldc  #68 // 1951633535
        //     18: ldc  #68 // 1951633535
        //     20: ixor
        //     21: putfield  #133 // dev.angelvisuals.a.cK.ah:Z
        //     24: aload_0
        //     25: getfield  #133 // dev.angelvisuals.a.cK.ah:Z
        //     28: ifeq  38 (offset +10)
        //     31: aload_0
        //     32: invokevirtual  #164 // dev.angelvisuals.a.cK.j:()V
        //     35: goto  42 (offset +7)
        //     38: aload_0
        //     39: invokevirtual  #165 // dev.angelvisuals.a.cK.k:()V
        //     42: return
    }

  public void method610() { // было: j
        EventManager.register(this);
        EventManager.call(new ak(this, ah));
    }

  public void method611() { // было: k
        EventManager.unregister(this);
        EventManager.call(new ak(this, ah));
    }

  public List method612() { // было: t
        return ((List) Arrays.stream(getClass().getDeclaredFields()).map(lp0 -> method628(((Field) lp0))).filter(lp0 -> method627(lp0)).map(lp0 -> method626(lp0)).collect(Collectors.toList()));
    }

  public JsonObject method613() { // было: c
        JsonObject var1 = new JsonObject();
        var1.addProperty(Decryptor.method1945(XorDecoder.method1946("\tâ¯\r\u0011ÿ(VN��19ßÅ\u0003\u0005ÑÊG", 250213166 ^ 1948140879)), Boolean.valueOf(ah));
        var1.addProperty(Decryptor.method1945(XorDecoder.method1946("ëx\u0011ØÀk\u0018ÂûO\u0013ìt$M\u0007ÂæEk", 682424736 ^ -1627727094)), Integer.valueOf(nI));
        JsonObject var2 = new JsonObject();
        Iterator var3 = method612().iterator();
        while (var3.hasNext()) {
            ClassA84 var4 = ((ClassA84) var3.next());
            var4.method631(var2);
            continue;
        }
        var1.add(Decryptor.method1945(XorDecoder.method1946("\u0005:`òx5[üPiAýB{Sùs&YÍI\u0011%", -1996261166 ^ 1025190115)), var2);
        return var1;
    }

  public void method614(JsonObject arg0) { // было: e
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_1
        //      1: ifnull  197 (offset +196)
        //      4: aload_1
        //      5: ldc  #90 // 'ÿà\x8fìçý¾É\xa0\x87£¯ö\x85°ÐÏÝåâóÓê¦'
        //      7: ldc  #15 // -1441697644
        //      9: ldc  #52 // 835109891
        //     11: ixor
        //     12: invokestatic  #151 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //     15: invokestatic  #150 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //     18: invokevirtual  #149 // com.google.gson.JsonObject.has:(Ljava/lang/String;)Z
        //     21: ifeq  75 (offset +54)
        //     24: aload_1
        //     25: ldc  #82 // ':û\x91É"æ\xa0ìe\x9c½\x8a3\x9e®õ\nÆûÇ6Èô\x83'
        //     27: ldc  #25 // -768485213
        //     29: ldc  #63 // 1828222961
        //     31: ixor
        //     32: invokestatic  #151 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //     35: invokestatic  #150 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //     38: invokevirtual  #147 // com.google.gson.JsonObject.get:(Ljava/lang/String;)Lcom/google/gson/JsonElement;
        //     41: invokevirtual  #141 // com.google.gson.JsonElement.getAsBoolean:()Z
        //     44: istore_2
        //     45: iload_2
        //     46: ifeq  60 (offset +14)
        //     49: aload_0
        //     50: invokevirtual  #156 // dev.angelvisuals.a.cK.ah:()Z
        //     53: ifne  60 (offset +7)
        //     56: aload_0
        //     57: invokevirtual  #155 // dev.angelvisuals.a.cK.aD:()V
        //     60: iload_2
        //     61: ifne  75 (offset +14)
        //     64: aload_0
        //     65: invokevirtual  #156 // dev.angelvisuals.a.cK.ah:()Z
        //     68: ifeq  75 (offset +7)
        //     71: aload_0
        //     72: invokevirtual  #155 // dev.angelvisuals.a.cK.aD:()V
        //     75: aload_1
        //     76: ldc  #86 // 'U\x8d\x14!~\x9e\x1d;Eº\x16{R\x81!x$¸\x02;X°nr'
        //     78: ldc  #55 // 970548349
        //     80: ldc  #70 // 1988801385
        //     82: ixor
        //     83: invokestatic  #151 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //     86: invokestatic  #150 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //     89: invokevirtual  #149 // com.google.gson.JsonObject.has:(Ljava/lang/String;)Z
        //     92: ifeq  119 (offset +27)
        //     95: aload_0
        //     96: aload_1
        //     97: ldc  #87 // 'c%\x1c\x11H6\x15\x0bs\x12\x1eKd))H\x12\x10\n\x0bn\x18fB'
        //     99: ldc  #38 // 272703100
        //    101: ldc  #64 // 1863999838
        //    103: ixor
        //    104: invokestatic  #151 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    107: invokestatic  #150 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    110: invokevirtual  #147 // com.google.gson.JsonObject.get:(Ljava/lang/String;)Lcom/google/gson/JsonElement;
        //    113: invokevirtual  #142 // com.google.gson.JsonElement.getAsInt:()I
        //    116: putfield  #136 // dev.angelvisuals.a.cK.nI:I
        //    119: aload_0
        //    120: invokevirtual  #166 // dev.angelvisuals.a.cK.t:()Ljava/util/List;
        //    123: invokeinterface  #194 // java.util.List.iterator:()Ljava/util/Iterator;, count 1
        //    128: astore_2
        //    129: aload_2
        //    130: invokeinterface  #192 // java.util.Iterator.hasNext:()Z, count 1
        //    135: ifeq  197 (offset +62)
        //    138: aload_2
        //    139: invokeinterface  #193 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //    144: checkcast  #102 // dev.angelvisuals.a.q
        //    147: astore_3
        //    148: aload_3
        //    149: invokevirtual  #172 // dev.angelvisuals.a.q.e:()Ljava/lang/String;
        //    152: astore  4
        //    154: aload_1
        //    155: ldc  #88 // '\x96$teë+OkÃwUjÑeGnà8MZÚ\x0f1\x1e'
        //    157: ldc  #44 // 556810526
        //    159: ldc  #34 // 37490620
        //    161: ixor
        //    162: invokestatic  #151 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    165: invokestatic  #150 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    168: invokevirtual  #148 // com.google.gson.JsonObject.getAsJsonObject:(Ljava/lang/String;)Lcom/google/gson/JsonObject;
        //    171: astore  5
        //    173: aload  5
        //    175: ifnull  194 (offset +19)
        //    178: aload  5
        //    180: aload  4
        //    182: invokevirtual  #149 // com.google.gson.JsonObject.has:(Ljava/lang/String;)Z
        //    185: ifeq  194 (offset +9)
        //    188: aload_3
        //    189: aload  5
        //    191: invokevirtual  #171 // dev.angelvisuals.a.q.b:(Lcom/google/gson/JsonObject;)V
        //    194: goto  129 (offset -65)
        //    197: goto  201 (offset +4)
        //    200: astore_2
        //    201: return
        //       Exception table:
        //         from 0 to 197 target 200 type java.lang.Exception
    }

  public int method615(@NotNull cK arg0) { // было: a
        return arg0.getName().compareTo(xh);
    }

    @Generated
  public bI method616() { // было: a
        return field318;
    }

    @Generated
  public String getName() {
        return xh;
    }

    @Generated
  public cG method617() { // было: d
        return field319;
    }

    @Generated
  public boolean ah() {
        return ah;
    }

    @Generated
  public int ai() {
        return nI;
    }

    @Generated
  public ClassA2 method618() { // было: i
        return field320;
    }

    @Generated
  public ClassA2 method619() { // было: j
        return field321;
    }

    @Generated
  public void method620(bI arg0) { // было: a
        field318 = arg0;
    }

    @Generated
  public void setName(String arg0) {
        xh = arg0;
    }

    @Generated
  public void method621(boolean arg0) { // было: q
        ah = arg0;
    }

    @Generated
  public void method622(int arg0) { // было: v
        nI = arg0;
    }

    @Generated
  public void method623(ClassA2 arg0) { // было: d
        field320 = arg0;
    }

    @Generated
  public void method624(ClassA2 arg0) { // было: e
        field321 = arg0;
    }

    @Generated
  public boolean equals(Object arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_1
        //      1: aload_0
        //      2: if_acmpne  11 (offset +9)
        //      5: ldc  #42 // 349891606
        //      7: ldc  #43 // 349891607
        //      9: ixor
        //     10: ireturn
        //     11: aload_1
        //     12: instanceof  #100 // dev.angelvisuals.a.cK
        //     15: ifne  24 (offset +9)
        //     18: ldc  #33 // -151909536
        //     20: ldc  #33 // -151909536
        //     22: ixor
        //     23: ireturn
        //     24: aload_1
        //     25: checkcast  #100 // dev.angelvisuals.a.cK
        //     28: astore_2
        //     29: aload_2
        //     30: aload_0
        //     31: invokevirtual  #167 // dev.angelvisuals.a.cK.u:(Ljava/lang/Object;)Z
        //     34: ifne  43 (offset +9)
        //     37: ldc  #35 // 199470160
        //     39: ldc  #35 // 199470160
        //     41: ixor
        //     42: ireturn
        //     43: aload_0
        //     44: invokevirtual  #156 // dev.angelvisuals.a.cK.ah:()Z
        //     47: aload_2
        //     48: invokevirtual  #156 // dev.angelvisuals.a.cK.ah:()Z
        //     51: if_icmpeq  60 (offset +9)
        //     54: ldc  #30 // -391978817
        //     56: ldc  #30 // -391978817
        //     58: ixor
        //     59: ireturn
        //     60: aload_0
        //     61: invokevirtual  #157 // dev.angelvisuals.a.cK.ai:()I
        //     64: aload_2
        //     65: invokevirtual  #157 // dev.angelvisuals.a.cK.ai:()I
        //     68: if_icmpeq  77 (offset +9)
        //     71: ldc  #22 // -857117381
        //     73: ldc  #22 // -857117381
        //     75: ixor
        //     76: ireturn
        //     77: aload_0
        //     78: invokevirtual  #153 // dev.angelvisuals.a.cK.a:()Ldev/angelvisuals/a/bI;
        //     81: astore_3
        //     82: aload_2
        //     83: invokevirtual  #153 // dev.angelvisuals.a.cK.a:()Ldev/angelvisuals/a/bI;
        //     86: astore  4
        //     88: aload_3
        //     89: ifnonnull  100 (offset +11)
        //     92: aload  4
        //     94: ifnonnull  112 (offset +18)
        //     97: goto  118 (offset +21)
        //    100: aload_3
        //    101: aload  4
        //    103: invokevirtual  #178 // java.lang.Object.equals:(Ljava/lang/Object;)Z
        //    106: ifeq  112 (offset +6)
        //    109: goto  118 (offset +9)
        //    112: ldc  #61 // 1388764145
        //    114: ldc  #61 // 1388764145
        //    116: ixor
        //    117: ireturn
        //    118: aload_0
        //    119: invokevirtual  #161 // dev.angelvisuals.a.cK.getName:()Ljava/lang/String;
        //    122: astore_3
        //    123: aload_2
        //    124: invokevirtual  #161 // dev.angelvisuals.a.cK.getName:()Ljava/lang/String;
        //    127: astore  4
        //    129: aload_3
        //    130: ifnonnull  144 (offset +14)
        //    133: aload  4
        //    135: ifnull  159 (offset +24)
        //    138: ldc  #74 // 2139216473
        //    140: ldc  #74 // 2139216473
        //    142: ixor
        //    143: ireturn
        //    144: aload_3
        //    145: aload  4
        //    147: invokevirtual  #178 // java.lang.Object.equals:(Ljava/lang/Object;)Z
        //    150: ifne  159 (offset +9)
        //    153: ldc  #36 // 203678198
        //    155: ldc  #36 // 203678198
        //    157: ixor
        //    158: ireturn
        //    159: aload_0
        //    160: invokevirtual  #160 // dev.angelvisuals.a.cK.d:()Ldev/angelvisuals/a/cG;
        //    163: astore  5
        //    165: aload_2
        //    166: invokevirtual  #160 // dev.angelvisuals.a.cK.d:()Ldev/angelvisuals/a/cG;
        //    169: astore  6
        //    171: aload  5
        //    173: ifnonnull  184 (offset +11)
        //    176: aload  6
        //    178: ifnonnull  197 (offset +19)
        //    181: goto  203 (offset +22)
        //    184: aload  5
        //    186: aload  6
        //    188: invokevirtual  #178 // java.lang.Object.equals:(Ljava/lang/Object;)Z
        //    191: ifeq  197 (offset +6)
        //    194: goto  203 (offset +9)
        //    197: ldc  #71 // 2046774235
        //    199: ldc  #71 // 2046774235
        //    201: ixor
        //    202: ireturn
        //    203: aload_0
        //    204: invokevirtual  #162 // dev.angelvisuals.a.cK.i:()Ldev/angelvisuals/a/k;
        //    207: astore  5
        //    209: aload_2
        //    210: invokevirtual  #162 // dev.angelvisuals.a.cK.i:()Ldev/angelvisuals/a/k;
        //    213: astore  6
        //    215: aload  5
        //    217: ifnonnull  228 (offset +11)
        //    220: aload  6
        //    222: ifnonnull  241 (offset +19)
        //    225: goto  247 (offset +22)
        //    228: aload  5
        //    230: aload  6
        //    232: invokevirtual  #178 // java.lang.Object.equals:(Ljava/lang/Object;)Z
        //    235: ifeq  241 (offset +6)
        //    238: goto  247 (offset +9)
        //    241: ldc  #21 // -956565320
        //    243: ldc  #21 // -956565320
        //    245: ixor
        //    246: ireturn
        //    247: aload_0
        //    248: invokevirtual  #163 // dev.angelvisuals.a.cK.j:()Ldev/angelvisuals/a/k;
        //    251: astore  5
        //    253: aload_2
        //    254: invokevirtual  #163 // dev.angelvisuals.a.cK.j:()Ldev/angelvisuals/a/k;
        //    257: astore  6
        //    259: aload  5
        //    261: ifnonnull  275 (offset +14)
        //    264: aload  6
        //    266: ifnull  291 (offset +25)
        //    269: ldc  #69 // 1955548083
        //    271: ldc  #69 // 1955548083
        //    273: ixor
        //    274: ireturn
        //    275: aload  5
        //    277: aload  6
        //    279: invokevirtual  #178 // java.lang.Object.equals:(Ljava/lang/Object;)Z
        //    282: ifne  291 (offset +9)
        //    285: ldc  #11 // -1749059461
        //    287: ldc  #11 // -1749059461
        //    289: ixor
        //    290: ireturn
        //    291: ldc  #8 // -1806842567
        //    293: ldc  #7 // -1806842568
        //    295: ixor
        //    296: ireturn
    }

    @Generated
  protected boolean method625(Object arg0) { // было: u
        return arg0 instanceof cK;
    }

    @Generated
  public int hashCode() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: ldc  #54 // 859657825
        //      2: ldc  #53 // 859657824
        //      4: ixor
        //      5: istore_1
        //      6: ldc  #45 // 633087892
        //      8: ldc  #46 // 633087893
        //     10: ixor
        //     11: istore_2
        //     12: iload_2
        //     13: ldc  #23 // -832098029
        //     15: ldc  #24 // -832098008
        //     17: ixor
        //     18: imul
        //     19: aload_0
        //     20: invokevirtual  #156 // dev.angelvisuals.a.cK.ah:()Z
        //     23: ifeq  34 (offset +11)
        //     26: ldc  #31 // -351100138
        //     28: ldc  #32 // -351100071
        //     30: ixor
        //     31: goto  39 (offset +8)
        //     34: ldc  #72 // 2116404280
        //     36: ldc  #73 // 2116404313
        //     38: ixor
        //     39: iadd
        //     40: istore_2
        //     41: iload_2
        //     42: ldc  #10 // -1753864736
        //     44: ldc  #9 // -1753864741
        //     46: ixor
        //     47: imul
        //     48: aload_0
        //     49: invokevirtual  #157 // dev.angelvisuals.a.cK.ai:()I
        //     52: iadd
        //     53: istore_2
        //     54: aload_0
        //     55: invokevirtual  #153 // dev.angelvisuals.a.cK.a:()Ldev/angelvisuals/a/bI;
        //     58: astore_3
        //     59: iload_2
        //     60: ldc  #65 // 1908929365
        //     62: ldc  #66 // 1908929390
        //     64: ixor
        //     65: imul
        //     66: aload_3
        //     67: ifnonnull  78 (offset +11)
        //     70: ldc  #20 // -976594563
        //     72: ldc  #19 // -976594602
        //     74: ixor
        //     75: goto  82 (offset +7)
        //     78: aload_3
        //     79: invokevirtual  #180 // java.lang.Object.hashCode:()I
        //     82: iadd
        //     83: istore_2
        //     84: aload_0
        //     85: invokevirtual  #161 // dev.angelvisuals.a.cK.getName:()Ljava/lang/String;
        //     88: astore  4
        //     90: iload_2
        //     91: ldc  #18 // -1012265476
        //     93: ldc  #17 // -1012265529
        //     95: ixor
        //     96: imul
        //     97: aload  4
        //     99: ifnonnull  110 (offset +11)
        //    102: ldc  #5 // -1810837811
        //    104: ldc  #6 // -1810837786
        //    106: ixor
        //    107: goto  115 (offset +8)
        //    110: aload  4
        //    112: invokevirtual  #180 // java.lang.Object.hashCode:()I
        //    115: iadd
        //    116: istore_2
        //    117: aload_0
        //    118: invokevirtual  #160 // dev.angelvisuals.a.cK.d:()Ldev/angelvisuals/a/cG;
        //    121: astore  5
        //    123: iload_2
        //    124: ldc  #28 // -419164834
        //    126: ldc  #29 // -419164827
        //    128: ixor
        //    129: imul
        //    130: aload  5
        //    132: ifnonnull  143 (offset +11)
        //    135: ldc  #49 // 682696697
        //    137: ldc  #48 // 682696658
        //    139: ixor
        //    140: goto  148 (offset +8)
        //    143: aload  5
        //    145: invokevirtual  #180 // java.lang.Object.hashCode:()I
        //    148: iadd
        //    149: istore_2
        //    150: aload_0
        //    151: invokevirtual  #162 // dev.angelvisuals.a.cK.i:()Ldev/angelvisuals/a/k;
        //    154: astore  6
        //    156: iload_2
        //    157: ldc  #57 // 1096856966
        //    159: ldc  #58 // 1096857021
        //    161: ixor
        //    162: imul
        //    163: aload  6
        //    165: ifnonnull  176 (offset +11)
        //    168: ldc  #60 // 1364678123
        //    170: ldc  #59 // 1364678080
        //    172: ixor
        //    173: goto  181 (offset +8)
        //    176: aload  6
        //    178: invokevirtual  #180 // java.lang.Object.hashCode:()I
        //    181: iadd
        //    182: istore_2
        //    183: aload_0
        //    184: invokevirtual  #163 // dev.angelvisuals.a.cK.j:()Ldev/angelvisuals/a/k;
        //    187: astore  7
        //    189: iload_2
        //    190: ldc  #39 // 284781854
        //    192: ldc  #40 // 284781861
        //    194: ixor
        //    195: imul
        //    196: aload  7
        //    198: ifnonnull  209 (offset +11)
        //    201: ldc  #4 // -1919232541
        //    203: ldc  #3 // -1919232568
        //    205: ixor
        //    206: goto  214 (offset +8)
        //    209: aload  7
        //    211: invokevirtual  #180 // java.lang.Object.hashCode:()I
        //    214: iadd
        //    215: istore_2
        //    216: iload_2
        //    217: ireturn
    }

    @Generated
  public String toString() {
        String var1 = String.valueOf(method616());
        return "Module(info=" + var1 + ", name=" + getName() + ", category=" + String.valueOf(method617()) + ", enabled=" + ah() + ", keyCode=" + ai() + ", animation=" + String.valueOf(method618()) + ", descAnimation=" + String.valueOf(method619()) + ")";
    }

  public int compareTo(@NotNull Object arg0) {
        return method615(((cK) arg0));
    }

  private static ClassA84 method626(Object arg0) { // было: b
        return ((ClassA84) arg0);
    }

  private static boolean method627(Object arg0) { // было: v
        return arg0 instanceof ClassA84;
    }

  private Object method628(Field arg0) { // было: b
        Object __stk1;
        try {
            arg0.setAccessible(722256555 ^ 722256554);
            __stk1 = arg0.get(this);
        } catch (IllegalAccessException var2) {
            return null;
        }
    }

  private static int mc(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int md(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int me(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}