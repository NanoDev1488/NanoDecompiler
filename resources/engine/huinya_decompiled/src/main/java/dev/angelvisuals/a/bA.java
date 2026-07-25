// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.ba
package dev.angelvisuals.a;

import net.minecraft.class_241;

public class ba {

    // ---- поля ----
  private final float bt;
  private final float bu;
  private static final String kT = "// every class watermarked, every string encrypted, every number xored";
  private static final String kU = "// good luck with the next 9999 classes";
  private static final String kV = "// every class watermarked, every string encrypted, every number xored";
  private static final String kW = "// every class watermarked, every string encrypted, every number xored";
  private static final String kX = "// flow obfuscation: ENABLED";
  private static final int gn = -1081851008;
  private static final int go = -717433806;
  private static final int gp = -1043988210;
  private static final byte[] bk;

    static {
        bk = "lHr'kqz/Xb$?ck6TDV\"'V;ReNp;X6D\"zL aK|KphnRcTN2D|.UmRp\\$ tTJlHbHB*BzxH9ZVW Ocosg,f'%d1]m@xpWmwbx1N0Oc_j>D0iIP%&yJ\\Fl>+q}(;G~B1iE]gbc 0sM4}qsO)f^%}'IEf`@bdk6#<Kv3p,iIDJ6J|=f3A&97*_+zOolCceKedbs~ot1G$A<+<F{c0Q@\\{Yg3dQ}BeXiz&[R7ZV)Dxjce}kdV};j6K38>gd`mW];?w`xw".getBytes("ISO-8859-1");
    }

  public ba(float arg0, float arg1) { // было: <init>
        super();
        bt = arg0;
        bu = arg1;
    }

  public float ac() {
        return ((float) Math.sqrt(((double) (bt * bt + bu * bu))));
    }

  public float ad() {
        return bt;
    }

  public float ae() {
        return bu;
    }

  public class_241 method1359() { // было: a
        return new class_241(bt, bu);
    }

  public boolean method1360(float arg0) { // было: a
        return method1361(arg0, arg0);
    }

  public boolean method1361(float arg0, float arg1) { // было: a
        return Math.abs(bt) >= arg0 ? 135780418 ^ 135780418 : Math.abs(bu) >= arg1 ? 135780418 ^ 135780418 : 919919240 ^ 919919241;
    }

  private static int fE(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int fF(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int fG(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}