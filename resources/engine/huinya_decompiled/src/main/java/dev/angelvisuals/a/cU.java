// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.cu
package dev.angelvisuals.a;

import dev.angelvisuals.a.ClassA139;
import dev.angelvisuals.a.bK;
import dev.angelvisuals.a.bZ;
import dev.angelvisuals.a.cF;
import lombok.Generated;

public abstract class cu implements cF {

    // ---- поля ----
  protected float cw;
  protected float cx;
  protected float cy;
  protected float cz;
  private static final String uw = "// === DO NOT TOUCH ===";
  private static final String ux = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final String uy = "// you are reading machine-generated garbage";
  private static final String uz = "// this jar protected by JoinerObfuscator";
  private static final String uA = "// nice try. closed source for a reason.";
  private static final int mc = -1705280024;
  private static final int md = 213694631;
  private static final int me = -1957816194;
  private static final byte[] cT;

    static {
        cT = "k`\\k\\\\^:Pbn]vl6{5.N{\\L08\\UO\"|5r59vY{94~}j #\"Q/MU1!&x<yO)n@)[3(tgy^/\\1- x,Bo)M5_eTcXfm+z/'zY#5IjG>9/v>gXq)929_Z4qXx1d)}R0VwAWIUT@D}w8y],NyOD2G<9Y3roC,,h6e7mjnjgn>Y;rqyH:!kKQv'9X:!+cCu '_V.K~SKO`v@FmKIN}lI*]Td;Rbk3H_CQVUgpF,t'Lk71St3^#3R#d2/4A-f*!aS=Ct|Ql'_\\".getBytes("ISO-8859-1");
    }

  protected cu(float arg0, float arg1, float arg2, float arg3) { // было: <init>
        super();
        cw = arg0;
        cx = arg1;
        cy = arg2;
        cz = arg3;
    }

  protected cu() { // было: <init>
        this(0.0f, 0.0f, 0.0f, 0.0f);
    }

  public void method1620(bZ arg0) { // было: a
        method1622(arg0);
        method1621(arg0);
    }

  protected abstract void method1621(bZ arg0); // было: b

  public void ar() {
        // (пустое тело)
    }

  public void method1622(bZ arg0) { // было: c
        // (пустое тело)
    }

  public void method1623(double arg0, double arg1, ClassA139 arg2) { // было: d
        // (пустое тело)
    }

  public void method1624(double arg0, double arg1, ClassA139 arg2) { // было: e
        // (пустое тело)
    }

  public void method1625(int arg0, int arg1, int arg2) { // было: a
        // (пустое тело)
    }

  public boolean method1626(char arg0, int arg1) { // было: d
        return 1931032730 ^ 1931032730;
    }

  public void method1627(double arg0, double arg1, double arg2, double arg3) { // было: a
        // (пустое тело)
    }

  public void method1628(float arg0, float arg1) { // было: f
        cw = arg0;
        cx = arg1;
    }

  public void method1629(float arg0, float arg1, float arg2, float arg3) { // было: a
        cw = arg0;
        cx = arg1;
        cy = arg2;
        cz = arg3;
    }

  public boolean method1630(float arg0, float arg1) { // было: b
        return bK.method1667(((double) cw), ((double) cx), ((double) cy), ((double) cz), ((double) arg0), ((double) arg1));
    }

  public boolean method1631(double arg0, double arg1) { // было: d
        return bK.method1667(((double) cw), ((double) cx), ((double) cy), ((double) cz), arg0, arg1);
    }

  public boolean method1632(bZ arg0) { // было: a
        return method1630(((float) arg0.method1678()), ((float) arg0.method1679()));
    }

    @Generated
  public float aH() {
        return cw;
    }

    @Generated
  public float aI() {
        return cx;
    }

    @Generated
  public float aJ() {
        return cy;
    }

    @Generated
  public float aK() {
        return cz;
    }

    @Generated
  public void method1633(float arg0) { // было: L
        cw = arg0;
    }

    @Generated
  public void method1634(float arg0) { // было: M
        cx = arg0;
    }

    @Generated
  public void method1635(float arg0) { // было: N
        cy = arg0;
    }

    @Generated
  public void method1636(float arg0) { // было: O
        cz = arg0;
    }

  private static int kC(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int kD(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int kE(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}