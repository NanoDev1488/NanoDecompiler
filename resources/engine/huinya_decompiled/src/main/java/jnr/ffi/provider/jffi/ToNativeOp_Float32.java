// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.ToNativeOp.Float32
package jnr.ffi.provider.jffi;

import jnr.ffi.NativeType;
import jnr.ffi.provider.jffi.AsmUtil;
import jnr.ffi.provider.jffi.NumberUtil;
import jnr.ffi.provider.jffi.SkinnyMethodAdapter;
import jnr.ffi.provider.jffi.ToNativeOp_Primitive;

class ToNativeOp_Float32 extends ToNativeOp_Primitive {

   ToNativeOp_Float32(Class arg0) { // было: <init>
        super(arg0);
    }

   void emitPrimitive(SkinnyMethodAdapter arg0, Class arg1, NativeType arg2) {
        if (!javaType.isPrimitive()) {
            AsmUtil.unboxNumber(arg0, javaType, Float.TYPE);
        }
        if (arg1 != Float.TYPE) {
            arg0.invokestatic(Float.class, "floatToRawIntBits", Integer.TYPE, new Class[]{Float.TYPE});
            NumberUtil.widen(arg0, Integer.TYPE, arg1);
        }
    }

}