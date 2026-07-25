// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.bi.a
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.a.bL;
import dev.angelvisuals.a.bi;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class bi_ClassA21 {

    // ---- поля ----
  private final bi field92; // было: a
  private static final String mc = "// nice try. closed source for a reason.";
  private static final String md = "Protected by t.me/JoinerClient";
  private static final String me = "// every class watermarked, every string encrypted, every number xored";
  private static final String mf = "// this jar protected by JoinerObfuscator";
  private static final String mg = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final int ha = -1118513746;
  private static final int hb = 1225272864;
  private static final int hc = -1032215055;
  private static final byte[] bw;

    static {
        bw = "(0gRFq\\TfyVA?hX]!a(4j5.ZVA\"CW^kZQcb*9uH)JN}:lm\\pETHC4y(V+\"-raNl?Po#JA`TYwDkZj&1Nrs@Us0>{CdkQpbXj`:f3 *H5HORW){Ki(Honv?}Prf4h2\\QB!Hj8@zMBRe`h,0l`qENF9+9}%8RIiR<fVen'crhPC402fu{CUCs='[z@@}wmg1zA&7,`=28L(Y\\%RaP.qc]{{9q#h(J$E`bJ'A[LZD<R>/|\"RrNv'$eeq\\[uz?swIO/-".getBytes("ISO-8859-1");
    }

  public bi_ClassA21() { // было: <init>
        super();
        field92 = new bi();
    }

  public bi_ClassA21 method215(String arg0) { // было: a
        return method221(arg0, Decryptor.method1945(XorDecoder.method1946("\r÷M<ìT<Óqè.Åm\u0013ÖhÚ\u001b½<", -1676413796 ^ -1651266016)));
    }

  public bi_ClassA21 method216(String arg0) { // было: b
        if (arg0 != null) {
            if (!arg0.isEmpty()) {
                field92.pn = arg0.substring(-658161704 ^ -658161704, Math.min(arg0.length(), -614912871 ^ -614912999));
            }
        }
        return this;
    }

  public bi_ClassA21 method217(String arg0, String arg1) { // было: a
        field92.pi = arg0;
        field92.pj = arg1;
        return this;
    }

  public bi_ClassA21 method218(String arg0) { // было: c
        if (arg0 != null) {
            if (!arg0.isEmpty()) {
                field92.ps = arg0.substring(-1161663013 ^ -1161663013, Math.min(arg0.length(), -582467737 ^ -582467609));
            }
        }
        return this;
    }

  public bi_ClassA21 method219(boolean arg0) { // было: a
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #69 // dev.angelvisuals.a.bi$a.a:Ldev/angelvisuals/a/bi;
        //      4: getfield  #64 // dev.angelvisuals.a.bi.pu:Ljava/lang/String;
        //      7: ifnull  23 (offset +16)
        //     10: aload_0
        //     11: getfield  #69 // dev.angelvisuals.a.bi$a.a:Ldev/angelvisuals/a/bi;
        //     14: getfield  #64 // dev.angelvisuals.a.bi.pu:Ljava/lang/String;
        //     17: invokevirtual  #82 // java.lang.String.isEmpty:()Z
        //     20: ifne  70 (offset +50)
        //     23: aload_0
        //     24: getfield  #69 // dev.angelvisuals.a.bi$a.a:Ldev/angelvisuals/a/bi;
        //     27: getfield  #66 // dev.angelvisuals.a.bi.pw:Ljava/lang/String;
        //     30: ifnull  46 (offset +16)
        //     33: aload_0
        //     34: getfield  #69 // dev.angelvisuals.a.bi$a.a:Ldev/angelvisuals/a/bi;
        //     37: getfield  #66 // dev.angelvisuals.a.bi.pw:Ljava/lang/String;
        //     40: invokevirtual  #82 // java.lang.String.isEmpty:()Z
        //     43: ifne  70 (offset +27)
        //     46: aload_0
        //     47: getfield  #69 // dev.angelvisuals.a.bi$a.a:Ldev/angelvisuals/a/bi;
        //     50: iload_1
        //     51: ifeq  62 (offset +11)
        //     54: ldc  #12 // -910263515
        //     56: ldc  #11 // -910263516
        //     58: ixor
        //     59: goto  67 (offset +8)
        //     62: ldc  #31 // 1609943265
        //     64: ldc  #31 // 1609943265
        //     66: ixor
        //     67: putfield  #53 // dev.angelvisuals.a.bi.jb:I
        //     70: aload_0
        //     71: areturn
    }

  public bi_ClassA21 method220(bL arg0) { // было: a
        return method222(Collections.singletonList(arg0));
    }

  public bi_ClassA21 method221(String arg0, String arg1) { // было: b
        field92.pq = arg0;
        field92.pk = arg1;
        return this;
    }

  public bi_ClassA21 method222(List arg0) { // было: a
        if (arg0 != null) {
            if (!arg0.isEmpty()) {
                int var2 = Math.min(arg0.size(), -1907625630 ^ -1907625632);
                field92.pu = (((bL) arg0.get(-188387557 ^ -188387557))).method236();
                field92.pt = (((bL) arg0.get(484634510 ^ 484634510))).method235();
                if (var2 == (-1418057776 ^ -1418057774)) {
                    field92.pw = (((bL) arg0.get(454858519 ^ 454858518))).method236();
                    field92.pv = (((bL) arg0.get(557094239 ^ 557094238))).method235();
                }
            }
        }
        return this;
    }

  public bi_ClassA21 method223(OffsetDateTime arg0) { // было: a
        field92.field93 = arg0.toEpochSecond();
        return this;
    }

  public bi_ClassA21 method224(String arg0, String arg1, String arg2) { // было: a
        if (field92.pu == null) {
            if (field92.pw == null) {
                field92.pr = arg0;
                field92.po = arg1;
                field92.pp = arg2;
            } else {
                if (!field92.pw.isEmpty()) {
                    field92.pr = arg0;
                    field92.po = arg1;
                    field92.pp = arg2;
                }
            }
        } else {
            if (!field92.pu.isEmpty()) {
                if (field92.pw == null) {
                    field92.pr = arg0;
                    field92.po = arg1;
                    field92.pp = arg2;
                } else {
                    if (!field92.pw.isEmpty()) {
                        field92.pr = arg0;
                        field92.po = arg1;
                        field92.pp = arg2;
                    }
                }
            }
        }
        return this;
    }

  public bi_ClassA21 method225(bL arg0, bL arg1) { // было: a
        bL[] __obj1 = new bL[1408344554 ^ 1408344552];
        __obj1[-319556096 ^ -319556096] = arg0;
        __obj1[-885053620 ^ -885053619] = arg1;
        method222(Arrays.asList(__obj1));
        return this;
    }

  public bi_ClassA21 method226(long arg0) { // было: a
        field92.field93 = arg0;
        return this;
    }

  public bi_ClassA21 method227(String arg0, String arg1) { // было: c
        if (field92.pu == null) {
            if (field92.pw == null) {
                field92.po = arg0;
                field92.pp = arg1;
            } else {
                if (!field92.pw.isEmpty()) {
                    field92.po = arg0;
                    field92.pp = arg1;
                }
            }
        } else {
            if (!field92.pu.isEmpty()) {
                if (field92.pw == null) {
                    field92.po = arg0;
                    field92.pp = arg1;
                } else {
                    if (!field92.pw.isEmpty()) {
                        field92.po = arg0;
                        field92.pp = arg1;
                    }
                }
            }
        }
        return this;
    }

  public bi_ClassA21 method228(long arg0) { // было: b
        field92.field94 = arg0;
        return this;
    }

  public bi_ClassA21 method229(OffsetDateTime arg0) { // было: b
        field92.field94 = arg0.toEpochSecond();
        return this;
    }

  public bi_ClassA21 method230(String arg0) { // было: d
        return method217(arg0, Decryptor.method1945(XorDecoder.method1946("\"oó\n&^è\u0013)^×6ELÁ*!qÒ/wy¹{", -1835810286 ^ -736626173)));
    }

  public bi method231() { // было: a
        return field92;
    }

  private static int go(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int gp(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int gq(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}