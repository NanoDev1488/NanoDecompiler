// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.ToNativeOp.Primitive
package jnr.ffi.provider.jffi;

import jnr.ffi.provider.jffi.ToNativeOp;

abstract class ToNativeOp_Primitive extends ToNativeOp {

    // ---- поля ----
  protected final Class javaType;

  protected ToNativeOp_Primitive(Class arg0) { // было: <init>
        super(true);
        javaType = arg0;
    }

}