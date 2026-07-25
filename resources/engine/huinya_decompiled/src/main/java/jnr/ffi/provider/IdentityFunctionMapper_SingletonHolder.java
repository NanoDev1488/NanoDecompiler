// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.IdentityFunctionMapper.SingletonHolder
package jnr.ffi.provider;

import jnr.ffi.mapper.FunctionMapper;
import jnr.ffi.provider.IdentityFunctionMapper;

final class IdentityFunctionMapper_SingletonHolder {

    // ---- поля ----
  public static final FunctionMapper INSTANCE;

    static {
        INSTANCE = new IdentityFunctionMapper();
    }

  private IdentityFunctionMapper_SingletonHolder() { // было: <init>
        super();
    }

}