// исходный (обфусцированный) внутренний класс: com.kenai.jffi.ObjectParameterType.ObjectType
package com.kenai.jffi;

public enum ObjectParameterType_ObjectType {

    ARRAY(268435456),
    BUFFER(536870912);

    // ---- поля ----
  final int value;

  private ObjectParameterType_ObjectType(int arg2) { // было: <init>
        value = arg2;
    }

}