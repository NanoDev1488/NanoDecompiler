// исходный (обфусцированный) внутренний класс: jnr.posix.SimpleFunctionMapper.Builder
package jnr.posix;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import jnr.posix.SimpleFunctionMapper;

public class SimpleFunctionMapper_Builder {

    // ---- поля ----
  private final Map functionNameMap;

  public SimpleFunctionMapper_Builder() { // было: <init>
        super();
        functionNameMap = Collections.synchronizedMap(new HashMap());
    }

  public SimpleFunctionMapper_Builder map(String arg0, String arg1) {
        functionNameMap.put(arg0, arg1);
        return this;
    }

  public SimpleFunctionMapper build() {
        return new SimpleFunctionMapper(functionNameMap, null);
    }

}