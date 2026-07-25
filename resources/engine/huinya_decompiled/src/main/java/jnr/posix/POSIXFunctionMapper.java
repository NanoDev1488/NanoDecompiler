// исходный (обфусцированный) внутренний класс: jnr.posix.POSIXFunctionMapper
package jnr.posix;

import jnr.ffi.Library;
import jnr.ffi.mapper.FunctionMapper;
import jnr.ffi.mapper.FunctionMapper_Context;

@Deprecated
final class POSIXFunctionMapper implements FunctionMapper {

    // ---- поля ----
  public static final FunctionMapper INSTANCE;

    static {
        INSTANCE = new POSIXFunctionMapper();
    }

  private POSIXFunctionMapper() { // было: <init>
        super();
    }

  public String mapFunctionName(String arg0, FunctionMapper_Context arg1) {
        if (arg1.getLibrary().getName().equals("msvcrt")) {
            if (arg0.equals("getpid")) {
                arg0 = new StringBuilder().append("_").append(arg0).toString();
            } else {
                if (arg0.equals("chmod")) {
                    arg0 = new StringBuilder().append("_").append(arg0).toString();
                }
            }
        }
        return arg0;
    }

}