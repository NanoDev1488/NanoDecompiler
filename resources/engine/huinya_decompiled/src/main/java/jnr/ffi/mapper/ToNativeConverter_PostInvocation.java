// исходный (обфусцированный) внутренний класс: jnr.ffi.mapper.ToNativeConverter.PostInvocation
package jnr.ffi.mapper;

import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter;

public interface ToNativeConverter_PostInvocation extends ToNativeConverter {

  public abstract void postInvoke(Object arg0, Object arg1, ToNativeContext arg2);

}