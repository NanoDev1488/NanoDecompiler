// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.IdentityFunctionMapper
package jnr.ffi.provider;

import jnr.ffi.mapper.FunctionMapper;
import jnr.ffi.mapper.FunctionMapper_Context;
import jnr.ffi.provider.IdentityFunctionMapper_SingletonHolder;

public class IdentityFunctionMapper implements FunctionMapper {

  public IdentityFunctionMapper() { // было: <init>
        super();
    }

  public static FunctionMapper getInstance() {
        return IdentityFunctionMapper_SingletonHolder.INSTANCE;
    }

  public String mapFunctionName(String arg0, FunctionMapper_Context arg1) {
        return arg0;
    }

}