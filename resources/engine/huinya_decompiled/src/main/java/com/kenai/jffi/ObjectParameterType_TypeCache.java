// исходный (обфусцированный) внутренний класс: com.kenai.jffi.ObjectParameterType.TypeCache
package com.kenai.jffi;

import com.kenai.jffi.ObjectParameterType;
import com.kenai.jffi.ObjectParameterType_ComponentType;
import java.util.EnumSet;
import java.util.Iterator;

final class ObjectParameterType_TypeCache {

    // ---- поля ----
  static final ObjectParameterType[] arrayTypeCache;
  static final ObjectParameterType[] bufferTypeCache;

    static {
        EnumSet var0 = EnumSet.allOf(ObjectParameterType_ComponentType.class);
        arrayTypeCache = new ObjectParameterType[var0.size()];
        bufferTypeCache = new ObjectParameterType[var0.size()];
        Iterator var1 = var0.iterator();
        while (var1.hasNext()) {
            ObjectParameterType_ComponentType var2 = ((ObjectParameterType_ComponentType) var1.next());
            arrayTypeCache[var2.ordinal()] = new ObjectParameterType(ObjectParameterType.ARRAY, var2);
            bufferTypeCache[var2.ordinal()] = new ObjectParameterType(ObjectParameterType.BUFFER, var2);
            continue;
        }
    }

  private ObjectParameterType_TypeCache() { // было: <init>
        super();
    }

}