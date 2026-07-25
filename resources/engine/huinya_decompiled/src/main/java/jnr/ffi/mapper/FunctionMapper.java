// исходный (обфусцированный) внутренний класс: jnr.ffi.mapper.FunctionMapper
package jnr.ffi.mapper;

import jnr.ffi.mapper.FunctionMapper_Anon1;
import jnr.ffi.mapper.FunctionMapper_Context;

public interface FunctionMapper {

    // ---- поля ----
  public static final FunctionMapper IDENTITY = new FunctionMapper_Anon1();

  public abstract String mapFunctionName(String arg0, FunctionMapper_Context arg1);

}