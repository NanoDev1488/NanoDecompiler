// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.ToNativeOp
package jnr.ffi.provider.jffi;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import jnr.ffi.Address;
import jnr.ffi.NativeType;
import jnr.ffi.provider.ToNativeType;
import jnr.ffi.provider.jffi.AsmUtil;
import jnr.ffi.provider.jffi.SkinnyMethodAdapter;
import jnr.ffi.provider.jffi.ToNativeOp_AddressOp;
import jnr.ffi.provider.jffi.ToNativeOp_Float32;
import jnr.ffi.provider.jffi.ToNativeOp_Float64;
import jnr.ffi.provider.jffi.ToNativeOp_Integral;

abstract class ToNativeOp {

    // ---- поля ----
  private final boolean isPrimitive;
  private static final Map operations;

    static {
        IdentityHashMap var0 = new IdentityHashMap();
        Class[] var1 = new Class[]{Byte.TYPE, Character.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Boolean.TYPE};
        int var2 = var1.length;
        int var3 = 0;
        while (var3 < var2) {
            Object var4 = var1[var3];
            var0.put(var4, new ToNativeOp_Integral(((Class) var4)));
            var0.put(AsmUtil.boxedType(((Class) var4)), new ToNativeOp_Integral(AsmUtil.boxedType(((Class) var4))));
            ++var3;
            continue;
        }
        var0.put(Float.TYPE, new ToNativeOp_Float32(Float.TYPE));
        var0.put(Float.class, new ToNativeOp_Float32(Float.class));
        var0.put(Double.TYPE, new ToNativeOp_Float64(Double.TYPE));
        var0.put(Double.class, new ToNativeOp_Float64(Double.class));
        var0.put(Address.class, new ToNativeOp_AddressOp());
        operations = Collections.unmodifiableMap(var0);
    }

  protected ToNativeOp(boolean arg0) { // было: <init>
        super();
        isPrimitive = arg0;
    }

  final boolean isPrimitive() {
        return isPrimitive;
    }

  abstract void emitPrimitive(SkinnyMethodAdapter arg0, Class arg1, NativeType arg2);

  static ToNativeOp get(ToNativeType arg0) {
        ToNativeOp var1 = ((ToNativeOp) operations.get(arg0.effectiveJavaType()));
        if (var1 == null) {
            return null;
        } else {
            return var1;
        }
    }

}