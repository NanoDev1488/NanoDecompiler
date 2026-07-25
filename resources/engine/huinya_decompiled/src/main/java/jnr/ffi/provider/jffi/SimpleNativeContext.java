// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.SimpleNativeContext
package jnr.ffi.provider.jffi;

import java.util.Collection;
import jnr.ffi.Runtime;
import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.mapper.ToNativeContext;

public class SimpleNativeContext implements FromNativeContext, ToNativeContext {

    // ---- поля ----
  private final Runtime runtime;
  private final Collection annotations;

   SimpleNativeContext(Runtime arg0, Collection arg1) { // было: <init>
        super();
        runtime = arg0;
        annotations = arg1;
    }

  public Collection getAnnotations() {
        return annotations;
    }

  public final Runtime getRuntime() {
        return runtime;
    }

}