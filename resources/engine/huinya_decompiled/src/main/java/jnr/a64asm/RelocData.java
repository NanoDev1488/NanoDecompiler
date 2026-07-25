// исходный (обфусцированный) внутренний класс: jnr.a64asm.RelocData
package jnr.a64asm;

import jnr.a64asm.RelocData_Type;

class RelocData {

    // ---- поля ----
  final RelocData_Type type;
  final int size;
  final int offset;
  final long destination;

  public RelocData(RelocData_Type arg0, int arg1, int arg2, long arg3) { // было: <init>
        super();
        type = arg0;
        size = arg1;
        offset = arg2;
        destination = arg3;
    }

}