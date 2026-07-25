// исходный (обфусцированный) внутренний класс: jnr.ffi.Struct.Offset
package jnr.ffi;

public final class Struct_Offset extends Number {

    // ---- поля ----
  private final int offset;

  public Struct_Offset(int arg0) { // было: <init>
        super();
        offset = arg0;
    }

  public int intValue() {
        return offset;
    }

  public long longValue() {
        return ((long) offset);
    }

  public float floatValue() {
        return ((float) offset);
    }

  public double doubleValue() {
        return ((double) offset);
    }

}