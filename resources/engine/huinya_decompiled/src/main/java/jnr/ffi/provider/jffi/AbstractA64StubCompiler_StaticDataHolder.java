// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.AbstractA64StubCompiler.StaticDataHolder
package jnr.ffi.provider.jffi;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

final class AbstractA64StubCompiler_StaticDataHolder {

    // ---- поля ----
  static final Map PAGES;

    static {
        PAGES = Collections.synchronizedMap(new WeakHashMap());
    }

  private AbstractA64StubCompiler_StaticDataHolder() { // было: <init>
        super();
    }

}