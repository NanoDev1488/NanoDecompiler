// исходный (обфусцированный) внутренний класс: com.kenai.jffi.Struct
package com.kenai.jffi;

import com.kenai.jffi.Aggregate;
import com.kenai.jffi.Foreign;
import com.kenai.jffi.Struct_StructReference;
import com.kenai.jffi.Type;
import java.lang.ref.ReferenceQueue;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class Struct extends Aggregate {

    // ---- поля ----
  private static final Map structCache;
  private static final ReferenceQueue structReferenceQueue;
  private final Type[] fields;

    static {
        structCache = new ConcurrentHashMap();
        structReferenceQueue = new ReferenceQueue();
    }

  public static Struct newStruct(Type[] arg0) {
        Struct __stk1;
        List var1 = Arrays.asList(arg0);
        Struct_StructReference var2 = ((Struct_StructReference) structCache.get(var1));
        __stk1 = var2 == null ? null : ((Struct) var2.get());
        Struct var3 = __stk1;
        if (var3 == null) {
            while (true) {
                var2 = ((Struct_StructReference) structReferenceQueue.poll());
                if (var2 == null) {
                    break;
                }
                structCache.remove(var2.fieldsList);
                continue;
            }
        } else {
            return ((Struct) var3);
        }
        var3 = new Struct(Foreign.getInstance(), arg0);
        structCache.put(var1, new Struct_StructReference(((Struct) var3), structReferenceQueue, var1, null));
        return ((Struct) var3);
    }

  private Struct(Foreign arg0, Type[] arg1) { // было: <init>
        super(arg0, arg0.newStruct(Type.nativeHandles(arg1), false));
        fields = ((Type[]) arg1.clone());
    }

    @Deprecated
  public Struct(Type[] arg0) { // было: <init>
        super(Foreign.getInstance(), Foreign.getInstance().newStruct(Type.nativeHandles(arg0), false));
        fields = ((Type[]) arg0.clone());
    }

  public boolean equals(Object arg0) {
        if (this != arg0) {
            if (arg0 == null) {
                return false;
            } else {
                if (getClass() == arg0.getClass()) {
                    if (super.equals(arg0)) {
                        return Arrays.equals(fields, (((Struct) arg0)).fields);
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } else {
            return true;
        }
    }

  public int hashCode() {
        int var1 = super.hashCode();
        var1 = 31 * var1 + Arrays.hashCode(fields);
        return var1;
    }

}