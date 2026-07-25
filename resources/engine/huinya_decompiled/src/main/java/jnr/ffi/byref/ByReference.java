// исходный (обфусцированный) внутренний класс: jnr.ffi.byref.ByReference
package jnr.ffi.byref;

import jnr.ffi.Pointer;
import jnr.ffi.Runtime;

public interface ByReference {

  public abstract int nativeSize(Runtime arg0);

  public abstract void toNative(Runtime arg0, Pointer arg1, long arg2);

  public abstract void fromNative(Runtime arg0, Pointer arg1, long arg2);

  public abstract Object getValue();

}