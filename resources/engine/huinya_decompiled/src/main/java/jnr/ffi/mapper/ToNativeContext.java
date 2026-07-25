// исходный (обфусцированный) внутренний класс: jnr.ffi.mapper.ToNativeContext
package jnr.ffi.mapper;

import java.util.Collection;
import jnr.ffi.Runtime;

public interface ToNativeContext {

  public abstract Collection getAnnotations();

  public abstract Runtime getRuntime();

}