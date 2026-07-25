// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.InvalidRuntime
package jnr.ffi.provider;

import java.nio.ByteOrder;
import jnr.ffi.NativeType;
import jnr.ffi.ObjectReferenceManager;
import jnr.ffi.Runtime;
import jnr.ffi.Type;
import jnr.ffi.TypeAlias;
import jnr.ffi.provider.ClosureManager;
import jnr.ffi.provider.MemoryManager;

class InvalidRuntime extends Runtime {

    // ---- поля ----
  private final String message;
  private final Throwable cause;

   InvalidRuntime(String arg0, Throwable arg1) { // было: <init>
        super();
        message = arg0;
        cause = arg1;
    }

  public Type findType(NativeType arg0) {
        throw newLoadError();
    }

  public Type findType(TypeAlias arg0) {
        throw newLoadError();
    }

  public MemoryManager getMemoryManager() {
        throw newLoadError();
    }

  public ClosureManager getClosureManager() {
        throw newLoadError();
    }

  public ObjectReferenceManager newObjectReferenceManager() {
        throw newLoadError();
    }

  public int getLastError() {
        throw newLoadError();
    }

  public void setLastError(int arg0) {
        throw newLoadError();
    }

  public long addressMask() {
        throw newLoadError();
    }

  public int addressSize() {
        throw newLoadError();
    }

  public int longSize() {
        throw newLoadError();
    }

  public ByteOrder byteOrder() {
        throw newLoadError();
    }

  public boolean isCompatible(Runtime arg0) {
        throw newLoadError();
    }

  private UnsatisfiedLinkError newLoadError() {
        UnsatisfiedLinkError var1 = new UnsatisfiedLinkError(message);
        var1.initCause(cause);
        throw var1;
    }

}