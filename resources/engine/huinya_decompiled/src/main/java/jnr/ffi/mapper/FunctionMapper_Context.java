// исходный (обфусцированный) внутренний класс: jnr.ffi.mapper.FunctionMapper.Context
package jnr.ffi.mapper;

import java.util.Collection;
import jnr.ffi.Library;

public interface FunctionMapper_Context {

    @Deprecated
  public abstract Library getLibrary();

  public abstract boolean isSymbolPresent(String arg0);

  public abstract Collection getAnnotations();

}