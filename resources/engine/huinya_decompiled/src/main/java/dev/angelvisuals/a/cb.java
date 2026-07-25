// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.cB
package dev.angelvisuals.a;

import lombok.Generated;

public class cB {

    // ---- поля ----
  private long field796; // было: F
  private static final String vl = "// class hierarchy hashing: ENABLED";
  private static final String vm = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final String vn = "// flow obfuscation: ENABLED";
  private static final String vo = "// this jar protected by JoinerObfuscator";
  private static final String vp = "// Joiner sees you";
  private static final int mC = 129450313;
  private static final int mD = 457360855;
  private static final int mE = -1416489789;
  private static final byte[] db;

    static {
        db = "kj%| ;$@JK<+GVT&:c:Z:'x&vN%[!QZwxs&OcD#9Pix-5r9eG;mB6jZ{%_@;ev9RcO!7.o51i/`!;d6s%y50>*6.g?L^29eor?aH VbuK\\WwOMtEEW@DQ`V2iWysvxf<=J,KlVUmRgc'\\QP;cUhcty<*A)c_@FWqneg*Tw\"sU_B0QYSenvIFk.9,A_dD[ BfShN_!3@3>u6&E5PVJ}<ni^LV8o/Mr B8VyL(XMc -{  gD&3`ux:GmV#H]Uy)h`g".getBytes("ISO-8859-1");
    }

  public cB() { // было: <init>
        super();
        as();
    }

  public boolean method1443(long arg0) { // было: b
        if (System.currentTimeMillis() - field796 < arg0) {
            return -1068728052 ^ -1068728052;
        } else {
            as();
            return -1010676075 ^ -1010676076;
        }
    }

  public void as() {
        field796 = System.currentTimeMillis();
    }

  public long method1444() { // было: l
        return System.currentTimeMillis() - field796;
    }

    @Generated
  public long method1445() { // было: m
        return field796;
    }

    @Generated
  public void method1446(long arg0) { // было: e
        field796 = arg0;
    }

  private static int la(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int lb(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int lc(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}