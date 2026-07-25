// исходный (обфусцированный) внутренний класс: jnr.ffi.StructLayout.Field
package jnr.ffi;

import jnr.ffi.StructLayout;

public abstract class StructLayout_Field {

    // ---- поля ----
  private final int offset;
  final StructLayout this$0;

  protected StructLayout_Field(StructLayout arg0, int arg1) { // было: <init>
        super();
        this$0 = arg0;
        offset = arg1;
    }

  public final StructLayout enclosing() {
        return this$0;
    }

  public final long offset() {
        return ((long) (offset + this$0.offset));
    }

}