// исходный (обфусцированный) внутренний класс: com.kenai.jffi.ObjectParameterInfo.ObjectType
package com.kenai.jffi;

public enum ObjectParameterInfo_ObjectType {

    ARRAY(268435456),
    BUFFER(536870912);

    // ---- поля ----
  final int value;

  private ObjectParameterInfo_ObjectType(int arg2) { // было: <init>
        value = arg2;
    }

}