// исходный (обфусцированный) внутренний класс: com.kenai.jnr.x86asm.RelocData
package com.kenai.jnr.x86asm;

import com.kenai.jnr.x86asm.RelocData_Type;

@Deprecated
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