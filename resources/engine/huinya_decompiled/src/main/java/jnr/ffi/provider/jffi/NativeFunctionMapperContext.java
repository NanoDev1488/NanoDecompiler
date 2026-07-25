// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.NativeFunctionMapperContext
package jnr.ffi.provider.jffi;

import java.util.Collection;
import jnr.ffi.Library;
import jnr.ffi.mapper.FunctionMapper_Context;
import jnr.ffi.provider.jffi.NativeLibrary;

public final class NativeFunctionMapperContext implements FunctionMapper_Context {

    // ---- поля ----
  private final NativeLibrary library;
  private final Collection annotations;

  public NativeFunctionMapperContext(NativeLibrary arg0, Collection arg1) { // было: <init>
        super();
        library = arg0;
        annotations = arg1;
    }

  public Library getLibrary() {
        return null;
    }

  public boolean isSymbolPresent(String arg0) {
        return library.getSymbolAddress(arg0) != 0L;
    }

  public Collection getAnnotations() {
        return annotations;
    }

}