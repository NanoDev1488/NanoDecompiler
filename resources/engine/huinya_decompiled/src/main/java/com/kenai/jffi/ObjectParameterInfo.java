// исходный (обфусцированный) внутренний класс: com.kenai.jffi.ObjectParameterInfo
package com.kenai.jffi;

import com.kenai.jffi.ObjectBuffer;
import com.kenai.jffi.ObjectParameterInfo_ComponentType;
import com.kenai.jffi.ObjectParameterInfo_ObjectType;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class ObjectParameterInfo {

    // ---- поля ----
  private static final ConcurrentMap CACHE;
  private final int parameterIndex;
  private final int ioflags;
  private final int objectInfo;
  public static final int IN = 1;
  public static final int OUT = 2;
  public static final int PINNED = 8;
  public static final int NULTERMINATE = 4;
  public static final int CLEAR = 16;
  public static final ObjectParameterInfo_ObjectType ARRAY;
  public static final ObjectParameterInfo_ObjectType BUFFER;
  public static final ObjectParameterInfo_ComponentType BYTE;
  public static final ObjectParameterInfo_ComponentType SHORT;
  public static final ObjectParameterInfo_ComponentType INT;
  public static final ObjectParameterInfo_ComponentType LONG;
  public static final ObjectParameterInfo_ComponentType FLOAT;
  public static final ObjectParameterInfo_ComponentType DOUBLE;
  public static final ObjectParameterInfo_ComponentType BOOLEAN;
  public static final ObjectParameterInfo_ComponentType CHAR;

    static {
        CACHE = new ConcurrentHashMap();
        ARRAY = ObjectParameterInfo_ObjectType.ARRAY;
        BUFFER = ObjectParameterInfo_ObjectType.BUFFER;
        BYTE = ObjectParameterInfo_ComponentType.BYTE;
        SHORT = ObjectParameterInfo_ComponentType.SHORT;
        INT = ObjectParameterInfo_ComponentType.INT;
        LONG = ObjectParameterInfo_ComponentType.LONG;
        FLOAT = ObjectParameterInfo_ComponentType.FLOAT;
        DOUBLE = ObjectParameterInfo_ComponentType.DOUBLE;
        BOOLEAN = ObjectParameterInfo_ComponentType.BOOLEAN;
        CHAR = ObjectParameterInfo_ComponentType.CHAR;
    }

  public static ObjectParameterInfo create(int arg0, ObjectParameterInfo_ObjectType arg1, ObjectParameterInfo_ComponentType arg2, int arg3) {
        return getCachedInfo(ObjectBuffer.makeObjectFlags(arg3, arg1.value | arg2.value, arg0));
    }

  public static ObjectParameterInfo create(int arg0, int arg1) {
        return getCachedInfo(ObjectBuffer.makeObjectFlags(arg1, 0, arg0));
    }

  private static ObjectParameterInfo getCachedInfo(int arg0) {
        ObjectParameterInfo var1 = ((ObjectParameterInfo) CACHE.get(Integer.valueOf(arg0)));
        if (var1 == null) {
            var1 = new ObjectParameterInfo(arg0);
            ObjectParameterInfo var2 = ((ObjectParameterInfo) CACHE.putIfAbsent(Integer.valueOf(arg0), var1));
            return var2 == null ? var1 : var2;
        } else {
            return var1;
        }
    }

  private ObjectParameterInfo(int arg0) { // было: <init>
        super();
        objectInfo = arg0;
        ioflags = arg0 & 255;
        parameterIndex = (arg0 & 16711680) >> 16;
    }

  final int asObjectInfo() {
        return objectInfo;
    }

  final int ioflags() {
        return ioflags;
    }

  public final int getParameterIndex() {
        return parameterIndex;
    }

  public boolean equals(Object arg0) {
        if (this != arg0) {
            if (arg0 == null) {
                return false;
            } else {
                if (getClass() == arg0.getClass()) {
                    ObjectParameterInfo var2 = ((ObjectParameterInfo) arg0);
                    return objectInfo == var2.objectInfo;
                } else {
                    return false;
                }
            }
        } else {
            return true;
        }
    }

  public int hashCode() {
        return 31 * objectInfo;
    }

}