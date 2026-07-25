// исходный (обфусцированный) внутренний класс: jnr.ffi.mapper.SimpleFunctionMapper
package jnr.ffi.mapper;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import jnr.ffi.mapper.FunctionMapper;
import jnr.ffi.mapper.FunctionMapper_Context;

class SimpleFunctionMapper implements FunctionMapper {

    // ---- поля ----
  private final Map functionNameMap;

   SimpleFunctionMapper(Map arg0) { // было: <init>
        super();
        functionNameMap = Collections.unmodifiableMap(new HashMap(arg0));
    }

  public String mapFunctionName(String arg0, FunctionMapper_Context arg1) {
        String var3 = ((String) functionNameMap.get(arg0));
        return var3 == null ? arg0 : var3;
    }

}