// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.AbstractAsmLibraryInterface
package jnr.ffi.provider.jffi;

import com.kenai.jffi.Invoker;
import jnr.ffi.Runtime;
import jnr.ffi.provider.LoadedLibrary;
import jnr.ffi.provider.jffi.NativeLibrary;

public abstract class AbstractAsmLibraryInterface implements LoadedLibrary {

    // ---- поля ----
  public static final Invoker ffi;
  protected final Runtime runtime;
  protected final NativeLibrary library;

    static {
        ffi = Invoker.getInstance();
    }

  public AbstractAsmLibraryInterface(Runtime arg0, NativeLibrary arg1) { // было: <init>
        super();
        runtime = arg0;
        library = arg1;
    }

  public final Runtime getRuntime() {
        return runtime;
    }

  final NativeLibrary getLibrary() {
        return library;
    }

}