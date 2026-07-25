// исходный (обфусцированный) внутренний класс: jnr.ffi.mapper.FunctionMapper.Builder
package jnr.ffi.mapper;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import jnr.ffi.mapper.FunctionMapper;
import jnr.ffi.mapper.SimpleFunctionMapper;

public final class FunctionMapper_Builder {

    // ---- поля ----
  private final Map functionNameMap;

  public FunctionMapper_Builder() { // было: <init>
        super();
        functionNameMap = Collections.synchronizedMap(new HashMap());
    }

  public FunctionMapper_Builder map(String arg0, String arg1) {
        functionNameMap.put(arg0, arg1);
        return this;
    }

  public FunctionMapper build() {
        return new SimpleFunctionMapper(functionNameMap);
    }

}