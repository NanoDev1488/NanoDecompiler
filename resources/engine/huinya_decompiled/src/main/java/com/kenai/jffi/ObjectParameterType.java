// исходный (обфусцированный) внутренний класс: com.kenai.jffi.ObjectParameterType
package com.kenai.jffi;

import com.kenai.jffi.ObjectParameterType_ComponentType;
import com.kenai.jffi.ObjectParameterType_ObjectType;
import com.kenai.jffi.ObjectParameterType_TypeCache;

public final class ObjectParameterType {

    // ---- поля ----
  final int typeInfo;
  static final ObjectParameterType INVALID;
  static final ObjectParameterType NONE;
  public static final ObjectParameterType_ObjectType ARRAY;
  public static final ObjectParameterType_ObjectType BUFFER;
  public static final ObjectParameterType_ComponentType BYTE;
  public static final ObjectParameterType_ComponentType SHORT;
  public static final ObjectParameterType_ComponentType INT;
  public static final ObjectParameterType_ComponentType LONG;
  public static final ObjectParameterType_ComponentType FLOAT;
  public static final ObjectParameterType_ComponentType DOUBLE;
  public static final ObjectParameterType_ComponentType BOOLEAN;
  public static final ObjectParameterType_ComponentType CHAR;

    static {
        INVALID = new ObjectParameterType(0);
        NONE = new ObjectParameterType(0);
        ARRAY = ObjectParameterType_ObjectType.ARRAY;
        BUFFER = ObjectParameterType_ObjectType.BUFFER;
        BYTE = ObjectParameterType_ComponentType.BYTE;
        SHORT = ObjectParameterType_ComponentType.SHORT;
        INT = ObjectParameterType_ComponentType.INT;
        LONG = ObjectParameterType_ComponentType.LONG;
        FLOAT = ObjectParameterType_ComponentType.FLOAT;
        DOUBLE = ObjectParameterType_ComponentType.DOUBLE;
        BOOLEAN = ObjectParameterType_ComponentType.BOOLEAN;
        CHAR = ObjectParameterType_ComponentType.CHAR;
    }

  public static ObjectParameterType create(ObjectParameterType_ObjectType arg0, ObjectParameterType_ComponentType arg1) {
        if (arg0 != ObjectParameterType_ObjectType.ARRAY) {
            if (arg0 != ObjectParameterType_ObjectType.BUFFER) {
                return new ObjectParameterType(arg0.value | arg1.value);
            } else {
                return ((ObjectParameterType) ObjectParameterType_TypeCache.bufferTypeCache[arg1.ordinal()]);
            }
        } else {
            return ((ObjectParameterType) ObjectParameterType_TypeCache.arrayTypeCache[arg1.ordinal()]);
        }
    }

   ObjectParameterType(int arg0) { // было: <init>
        super();
        typeInfo = arg0;
    }

   ObjectParameterType(ObjectParameterType_ObjectType arg0, ObjectParameterType_ComponentType arg1) { // было: <init>
        super();
        typeInfo = arg0.value | arg1.value;
    }

  public boolean equals(Object arg0) {
        return this == arg0 ? 1 : !(arg0 instanceof ObjectParameterType) ? 0 : typeInfo == (((ObjectParameterType) arg0)).typeInfo;
    }

  public int hashCode() {
        return typeInfo;
    }

}