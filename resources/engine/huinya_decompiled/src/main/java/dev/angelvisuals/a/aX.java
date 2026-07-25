// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.ax
package dev.angelvisuals.a;

import dev.angelvisuals.a.cs;

public final class ax extends Record {

    // ---- поля ----
  private final float aA;
  private final float aB;
  private final float aC;
  private final float aD;
  private static final String hq = "// every class watermarked, every string encrypted, every number xored";
  private static final String hr = "// stop. seriously. go play minecraft instead";
  private static final String hs = "// === DO NOT TOUCH ===";
  private static final String ht = "Protected by t.me/JoinerClient";
  private static final String hu = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final int ek = -502146457;
  private static final int el = -1922409627;
  private static final int em = -601471135;
  private static final byte[] aD;

    static {
        aD = "-YG`o?BF,[=@z4huDx`&HR<mAb*~:7FZl+AgF@L@f,i6[cW5`*=fR(`FX&P`Sm*X1=(ZEI@Gc<\"= FoEMd49qf`sp\\lD@qg&,tJ]ZSVm$G%t7{~FK{mh#k11i}~A<@7iggwVzjhzcsFW2LzFM}oR*([Ec-{FeMTs&Z;BQ{HXp|1#'Q5qXZ8y!uz:jT0QE;Mbj^ @eU(X5sw\"|]/cuu7c|uNmWqD7#}?KY~a:@Z[I&$>F5e<Rd+~mEvtU kew?+QL".getBytes("ISO-8859-1");
    }

  public ax(float arg0, float arg1, float arg2, float arg3) { // было: <init>
        super();
        aA = arg0;
        aB = arg1;
        aC = arg2;
        aD = arg3;
    }

  public boolean method1669(double arg0, double arg1) { // было: b
        return cs.method1417(arg0, arg1, ((double) aA), ((double) aB), ((double) aC), ((double) aD));
    }

  public float method1670() { // было: r
        return aA;
    }

  public float method1671() { // было: s
        return aB;
    }

  public float method1672() { // было: t
        return aC;
    }

  public float method1673() { // было: u
        return aD;
    }

  public final String method1674() { // было: q
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokedynamic  #30 // invokedynamic toString:(Ldev/angelvisuals/a/ax;)Ljava/lang/String;
        //      6: areturn
    }

  public final int method1675() { // было: q
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokedynamic  #29 // invokedynamic hashCode:(Ldev/angelvisuals/a/ax;)I
        //      6: ireturn
    }

  public final boolean method1676(Object arg0) { // было: f
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: aload_1
        //      2: invokedynamic  #28 // invokedynamic equals:(Ldev/angelvisuals/a/ax;Ljava/lang/Object;)Z
        //      7: ireturn
    }

  private static int dJ(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int dK(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int dL(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}