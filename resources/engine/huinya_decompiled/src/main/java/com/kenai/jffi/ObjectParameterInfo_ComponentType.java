// исходный (обфусцированный) внутренний класс: com.kenai.jffi.ObjectParameterInfo.ComponentType
package com.kenai.jffi;

public enum ObjectParameterInfo_ComponentType {

    BYTE(16777216),
    SHORT(33554432),
    INT(50331648),
    LONG(67108864),
    FLOAT(83886080),
    DOUBLE(100663296),
    BOOLEAN(117440512),
    CHAR(134217728);

    // ---- поля ----
  final int value;

  private ObjectParameterInfo_ComponentType(int arg2) { // было: <init>
        value = arg2;
    }

}