// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.ToNativeOp.AddressOp
package jnr.ffi.provider.jffi;

import jnr.ffi.Address;
import jnr.ffi.NativeType;
import jnr.ffi.provider.jffi.AsmRuntime;
import jnr.ffi.provider.jffi.NumberUtil;
import jnr.ffi.provider.jffi.SkinnyMethodAdapter;
import jnr.ffi.provider.jffi.ToNativeOp_Primitive;

class ToNativeOp_AddressOp extends ToNativeOp_Primitive {

   ToNativeOp_AddressOp() { // было: <init>
        super(Address.class);
    }

   void emitPrimitive(SkinnyMethodAdapter arg0, Class arg1, NativeType arg2) {
        if (Long.TYPE != arg1) {
            arg0.invokestatic(AsmRuntime.class, "intValue", Integer.TYPE, new Class[]{Address.class});
            NumberUtil.narrow(arg0, Integer.TYPE, arg1);
        } else {
            arg0.invokestatic(AsmRuntime.class, "longValue", Long.TYPE, new Class[]{Address.class});
        }
    }

}