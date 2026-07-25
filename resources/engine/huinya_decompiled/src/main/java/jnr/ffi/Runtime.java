// исходный (обфусцированный) внутренний класс: jnr.ffi.Runtime
package jnr.ffi;

import java.nio.ByteOrder;
import java.util.List;
import jnr.ffi.NativeType;
import jnr.ffi.ObjectReferenceManager;
import jnr.ffi.Runtime_SingletonHolder;
import jnr.ffi.Type;
import jnr.ffi.TypeAlias;
import jnr.ffi.provider.ClosureManager;
import jnr.ffi.provider.LoadedLibrary;
import jnr.ffi.provider.MemoryManager;
import jnr.ffi.provider.jffi.NativeRuntime;

public abstract class Runtime {

  public Runtime() { // было: <init>
        super();
    }

  public static Runtime getSystemRuntime() {
        return Runtime_SingletonHolder.SYSTEM_RUNTIME;
    }

  public static Runtime getRuntime(Object arg0) {
        return (((LoadedLibrary) arg0)).getRuntime();
    }

  public static List getLoadedLibraries() {
        return NativeRuntime.getLoadedLibraries();
    }

  public abstract Type findType(NativeType arg0);

  public abstract Type findType(TypeAlias arg0);

  public abstract MemoryManager getMemoryManager();

  public abstract ClosureManager getClosureManager();

  public abstract ObjectReferenceManager newObjectReferenceManager();

  public abstract int getLastError();

  public abstract void setLastError(int arg0);

  public abstract long addressMask();

  public abstract int addressSize();

  public abstract int longSize();

  public abstract ByteOrder byteOrder();

  public abstract boolean isCompatible(Runtime arg0);

}