// исходный (обфусцированный) внутренний класс: jnr.posix.HANDLE$1
package jnr.posix;

import jnr.ffi.Pointer;
import jnr.ffi.mapper.DataConverter;
import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.mapper.ToNativeContext;
import jnr.posix.HANDLE;

class HANDLE_Anon1 implements DataConverter {

   HANDLE_Anon1() { // было: <init>
        super();
    }

  public Pointer toNative(HANDLE arg0, ToNativeContext arg1) {
        return arg0 == null ? null : HANDLE.access$000(arg0);
    }

  public HANDLE fromNative(Pointer arg0, FromNativeContext arg1) {
        return arg0 == null ? null : new HANDLE(arg0);
    }

  public Class nativeType() {
        return Pointer.class;
    }

  public Object toNative(Object arg0, ToNativeContext arg1) {
        return toNative(((HANDLE) arg0), arg1);
    }

  public Object fromNative(Object arg0, FromNativeContext arg1) {
        return fromNative(((Pointer) arg0), arg1);
    }

}