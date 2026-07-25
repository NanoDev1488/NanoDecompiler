// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.ToNativeOp.Delegate
package jnr.ffi.provider.jffi;

import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.provider.jffi.AsmUtil;
import jnr.ffi.provider.jffi.SkinnyMethodAdapter;
import jnr.ffi.provider.jffi.ToNativeOp;
import jnr.ffi.provider.jffi.ToNativeOp_Primitive;

class ToNativeOp_Delegate extends ToNativeOp_Primitive {

    // ---- поля ----
  static final ToNativeOp INSTANCE;

    static {
        INSTANCE = new ToNativeOp_Delegate();
    }

   ToNativeOp_Delegate() { // было: <init>
        super(Pointer.class);
    }

   void emitPrimitive(SkinnyMethodAdapter arg0, Class arg1, NativeType arg2) {
        AsmUtil.unboxPointer(arg0, arg1);
    }

}