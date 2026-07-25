// исходный (обфусцированный) внутренний класс: jnr.ffi.StructLayout.Offset
package jnr.ffi;

public final class StructLayout_Offset extends Number {

    // ---- поля ----
  private final int offset;

  public StructLayout_Offset(int arg0) { // было: <init>
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