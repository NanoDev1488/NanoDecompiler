// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.AbstractX86StubCompiler.StaticDataHolder
package jnr.ffi.provider.jffi;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

final class AbstractX86StubCompiler_StaticDataHolder {

    // ---- поля ----
  static final Map PAGES;

    static {
        PAGES = Collections.synchronizedMap(new WeakHashMap());
    }

  private AbstractX86StubCompiler_StaticDataHolder() { // было: <init>
        super();
    }

}