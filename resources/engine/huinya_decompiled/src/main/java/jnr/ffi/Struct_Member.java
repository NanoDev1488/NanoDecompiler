// исходный (обфусцированный) внутренний класс: jnr.ffi.Struct.Member
package jnr.ffi;

import jnr.ffi.Pointer;
import jnr.ffi.Struct;

public abstract class Struct_Member {

    // ---- поля ----
  final Struct this$0;

  protected Struct_Member(Struct arg0) { // было: <init>
        super();
        this$0 = arg0;
    }

  abstract Struct struct();

  abstract Pointer getMemory();

  abstract long offset();

}