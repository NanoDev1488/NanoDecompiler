// исходный (обфусцированный) внутренний класс: jnr.ffi.mapper.FromNativeContext
package jnr.ffi.mapper;

import java.util.Collection;
import jnr.ffi.Runtime;

public interface FromNativeContext {

  public abstract Collection getAnnotations();

  public abstract Runtime getRuntime();

}