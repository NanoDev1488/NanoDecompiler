// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.ToNativeOp.Float64
package jnr.ffi.provider.jffi;

import jnr.ffi.NativeType;
import jnr.ffi.provider.jffi.AsmUtil;
import jnr.ffi.provider.jffi.NumberUtil;
import jnr.ffi.provider.jffi.SkinnyMethodAdapter;
import jnr.ffi.provider.jffi.ToNativeOp_Primitive;

class ToNativeOp_Float64 extends ToNativeOp_Primitive {

   ToNativeOp_Float64(Class arg0) { // было: <init>
        super(arg0);
    }

   void emitPrimitive(SkinnyMethodAdapter arg0, Class arg1, NativeType arg2) {
        if (!javaType.isPrimitive()) {
            AsmUtil.unboxNumber(arg0, javaType, Double.TYPE);
        }
        if (arg1 != Double.TYPE) {
            arg0.invokestatic(Double.class, "doubleToRawLongBits", Long.TYPE, new Class[]{Double.TYPE});
            NumberUtil.narrow(arg0, Long.TYPE, arg1);
        }
    }

}