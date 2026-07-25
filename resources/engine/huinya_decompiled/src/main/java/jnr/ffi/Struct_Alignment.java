// исходный (обфусцированный) внутренний класс: jnr.ffi.Struct.Alignment
package jnr.ffi;

public final class Struct_Alignment extends Number {

    // ---- поля ----
  private final int alignment;

  public Struct_Alignment(int arg0) { // было: <init>
        super();
        alignment = arg0;
    }

  public int intValue() {
        return alignment;
    }

  public long longValue() {
        return ((long) alignment);
    }

  public float floatValue() {
        return ((float) alignment);
    }

  public double doubleValue() {
        return ((double) alignment);
    }

}