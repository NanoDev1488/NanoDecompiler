// исходный (обфусцированный) внутренний класс: jnr.x86asm.RelocData
package jnr.x86asm;

import jnr.x86asm.RelocData_Type;

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