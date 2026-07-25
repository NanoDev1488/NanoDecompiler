// исходный (обфусцированный) внутренний класс: com.kenai.jffi.Type.TypeInfo
package com.kenai.jffi;

final class Type_TypeInfo {

    // ---- поля ----
  final int type;
  final int size;
  final int alignment;
  final long handle;

   Type_TypeInfo(long arg0, int arg1, int arg2, int arg3) { // было: <init>
        super();
        handle = arg0;
        type = arg1;
        size = arg2;
        alignment = arg3;
    }

}