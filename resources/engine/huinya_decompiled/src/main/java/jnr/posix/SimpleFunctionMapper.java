// исходный (обфусцированный) внутренний класс: jnr.posix.SimpleFunctionMapper
package jnr.posix;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import jnr.ffi.mapper.FunctionMapper;
import jnr.ffi.mapper.FunctionMapper_Context;
import jnr.posix.SimpleFunctionMapper_Anon1;

public class SimpleFunctionMapper implements FunctionMapper {

    // ---- поля ----
  private final Map functionNameMap;

  private SimpleFunctionMapper(Map arg0) { // было: <init>
        super();
        functionNameMap = Collections.unmodifiableMap(new HashMap(arg0));
    }

  public String mapFunctionName(String arg0, FunctionMapper_Context arg1) {
        String var3 = ((String) functionNameMap.get(arg0));
        return var3 == null ? arg0 : var3;
    }

   SimpleFunctionMapper(Map arg0, SimpleFunctionMapper_Anon1 arg1) { // было: <init>
        this(arg0);
    }

}