// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.ToNativeOp.Integral
package jnr.ffi.provider.jffi;

import jnr.ffi.NativeType;
import jnr.ffi.provider.jffi.AsmUtil;
import jnr.ffi.provider.jffi.NumberUtil;
import jnr.ffi.provider.jffi.SkinnyMethodAdapter;
import jnr.ffi.provider.jffi.ToNativeOp_Primitive;

class ToNativeOp_Integral extends ToNativeOp_Primitive {

   ToNativeOp_Integral(Class arg0) { // было: <init>
        super(arg0);
    }

  public void emitPrimitive(SkinnyMethodAdapter arg0, Class arg1, NativeType arg2) {
        if (!javaType.isPrimitive()) {
            AsmUtil.unboxNumber(arg0, javaType, arg1, arg2);
        } else {
            NumberUtil.convertPrimitive(arg0, javaType, arg1, arg2);
        }
    }

}