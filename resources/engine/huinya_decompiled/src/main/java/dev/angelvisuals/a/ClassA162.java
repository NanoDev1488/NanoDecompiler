// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.N
package dev.angelvisuals.a;

import dev.angelvisuals.a.cs;
import lombok.Generated;

public class ClassA162 {

    // ---- поля ----
   float field898; // было: R
   float field899; // было: S
   float field900; // было: T
   float field901; // было: U
  private static final String dd = "Protected by t.me/JoinerClient";
  private static final String de = "// Joiner sees you";
  private static final String df = "// Joiner sees you";
  private static final String dg = "// stop. seriously. go play minecraft instead";
  private static final String dh = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final int bz = 2025765572;
  private static final int bA = 1190076618;
  private static final int bB = -399086732;
  private static final byte[] field902; // было: N

    static {
        field902 = ":fwj]`9jjg7$TwG ;IK=tXC1v%jf&PjkA*$r;%D6@[h+U#e?-j['*Ga|B&i?tZVg(~]>p-9l4`29RIv'U\"-}_c\"${h8O:IO&WG_-gO~;c\";?ng>++1FLqn2)k9]TZX6[&C7U2p }wk_5B1Q6Dm3`MHm9Cw7#wZD6LL31%R`I0KpIp[cX_,gx1VW<O^pCP_T^q\"*ADoqi@<h]SAzb)8pvu}%-<$hQ+!;M4duTq7qF0Un/_l|[i~hi:J}\\DmvwA rN".getBytes("ISO-8859-1");
    }

  public boolean method1610(double arg0, double arg1) { // было: a
        return cs.method1417(arg0, arg1, ((double) field898), ((double) field899), ((double) field900), ((double) field901));
    }

    @Generated
  public ClassA162(float arg0, float arg1, float arg2, float arg3) { // было: <init>
        super();
        field898 = arg0;
        field899 = arg1;
        field900 = arg2;
        field901 = arg3;
    }

    @Generated
  public float method1611() { // было: j
        return field898;
    }

    @Generated
  public float method1612() { // было: k
        return field899;
    }

    @Generated
  public float method1613() { // было: l
        return field900;
    }

    @Generated
  public float method1614() { // было: m
        return field901;
    }

    @Generated
  public void method1615(float arg0) { // было: g
        field898 = arg0;
    }

    @Generated
  public void method1616(float arg0) { // было: h
        field899 = arg0;
    }

    @Generated
  public void method1617(float arg0) { // было: i
        field900 = arg0;
    }

    @Generated
  public void method1618(float arg0) { // было: j
        field901 = arg0;
    }

    @Generated
  public boolean equals(Object arg0) {
        if (arg0 != this) {
            if (arg0 instanceof ClassA162) {
                ClassA162 var2 = ((ClassA162) arg0);
                if (var2.method1619(this)) {
                    if (Float.compare(method1611(), var2.method1611()) == 0) {
                        if (Float.compare(method1612(), var2.method1612()) == 0) {
                            if (Float.compare(method1613(), var2.method1613()) == 0) {
                                return Float.compare(method1614(), var2.method1614()) != 0 ? -1323789664 ^ -1323789664 : 1637854347 ^ 1637854346;
                            } else {
                                return -1975296846 ^ -1975296846;
                            }
                        } else {
                            return 1600081847 ^ 1600081847;
                        }
                    } else {
                        return 627283356 ^ 627283356;
                    }
                } else {
                    return -466978722 ^ -466978722;
                }
            } else {
                return 671595582 ^ 671595582;
            }
        } else {
            return -857348669 ^ -857348670;
        }
    }

    @Generated
  protected boolean method1619(Object arg0) { // было: b
        return arg0 instanceof ClassA162;
    }

    @Generated
  public int hashCode() {
        int var1 = -2051510084 ^ -2051510083;
        int var2 = 1095608061 ^ 1095608060;
        var2 = var2 * (129413038 ^ 129413013) + Float.floatToIntBits(method1611());
        var2 = var2 * (1184028935 ^ 1184028988) + Float.floatToIntBits(method1612());
        var2 = var2 * (-1822490377 ^ -1822490420) + Float.floatToIntBits(method1613());
        var2 = var2 * (1818298036 ^ 1818297999) + Float.floatToIntBits(method1614());
        return var2;
    }

    @Generated
  public String toString() {
        float var1 = method1611();
        return "ChangeRect(x=" + var1 + ", y=" + method1612() + ", width=" + method1613() + ", height=" + method1614() + ")";
    }

  private static int bn(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int bo(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int bp(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}