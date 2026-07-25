// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.NativeRuntime.TypeDelegate
package jnr.ffi.provider.jffi;

import jnr.ffi.NativeType;
import jnr.ffi.Type;

final class NativeRuntime_TypeDelegate extends Type {

    // ---- поля ----
  private final com.kenai.jffi.Type type;
  private final NativeType nativeType;

  public NativeRuntime_TypeDelegate(com.kenai.jffi.Type arg0, NativeType arg1) { // было: <init>
        super();
        type = arg0;
        nativeType = arg1;
    }

  public int alignment() {
        return type.alignment();
    }

  public int size() {
        return type.size();
    }

  public NativeType getNativeType() {
        return nativeType;
    }

  public String toString() {
        return type.toString();
    }

}