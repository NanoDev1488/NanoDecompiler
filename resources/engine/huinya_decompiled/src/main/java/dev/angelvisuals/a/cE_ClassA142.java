// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.cE.a
package dev.angelvisuals.a;

import net.minecraft.class_3675.class_307;

class cE_ClassA142 {

    // ---- поля ----
  static final int[] field728; // было: a
  private static final String BC = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final String BD = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String BE = "// flow obfuscation: ENABLED";
  private static final String BF = "// you are reading machine-generated garbage";
  private static final String BG = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final int qo = -74309254;
  private static final int qp = 1352548887;
  private static final int qq = 849412112;
  private static final byte[] ej;

    static {
        ej = "ODTsz`(&3M*#%+#~6~WR\\!n_fn6yAT8(Ws^2n4-HDT4t5;|lV;nXRI6oE26j/l+a8%CaAB'Ub{hmgxl2J:d;Ae:l1Gl!. @3q)&uhs3W~a^lQKo1t^YYzspE+G^d.WpC#E=$~c5^;fK%[Je`b?{@z-`,>o<9+!]Jv45w%bA:Da2+Q$f;6/%O7d8~3}3u\\CbcGdyn:3G?*vQThHj_@DU'LA<7]0:-,JsBl9^e;T+Vxrj8hSDR)c5AtF>\"me)(=@)W".getBytes("ISO-8859-1");
        field728 = new int[class_307.values().length];
        try {
            field728[class_307.field_1668.ordinal()] = -768994355 ^ -768994356;
        } catch (NoSuchFieldError var0) {
        }
        try {
            field728[class_307.field_1672.ordinal()] = 381121731 ^ 381121729;
        } catch (NoSuchFieldError e2) {
            Throwable var0 = e2;
        }
    }

  private static int oz(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int oA(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int oB(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}