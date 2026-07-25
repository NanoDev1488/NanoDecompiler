// исходный (обфусцированный) внутренний класс: jnr.ffi.util.EnumMapper.StaticDataHolder
package jnr.ffi.util;

import java.util.Collections;
import java.util.Map;

final class EnumMapper_StaticDataHolder {

    // ---- поля ----
  private static volatile Map MAPPERS;

    static {
        MAPPERS = Collections.emptyMap();
    }

  private EnumMapper_StaticDataHolder() { // было: <init>
        super();
    }

  static Map access$000() {
        return MAPPERS;
    }

  static Map access$002(Map arg0) {
        MAPPERS = arg0;
        return arg0;
    }

}