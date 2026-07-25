// исходный (обфусцированный) внутренний класс: jnr.ffi.mapper.CompositeFunctionMapper
package jnr.ffi.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import jnr.ffi.mapper.FunctionMapper;
import jnr.ffi.mapper.FunctionMapper_Context;

public final class CompositeFunctionMapper implements FunctionMapper {

    // ---- поля ----
  private final Collection functionMappers;

  public CompositeFunctionMapper(Collection arg0) { // было: <init>
        super();
        functionMappers = Collections.unmodifiableList(new ArrayList(arg0));
    }

  public String mapFunctionName(String arg0, FunctionMapper_Context arg1) {
        Iterator var3 = functionMappers.iterator();
        String var5;
        while (true) {
            if (!var3.hasNext()) {
                return arg0;
            }
            FunctionMapper var4 = ((FunctionMapper) var3.next());
            var5 = var4.mapFunctionName(arg0, arg1);
            if (var5 != arg0) {
                break;
            }
            continue;
        }
        return var5;
    }

}