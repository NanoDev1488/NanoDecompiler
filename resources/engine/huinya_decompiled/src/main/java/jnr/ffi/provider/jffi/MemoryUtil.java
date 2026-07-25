// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.MemoryUtil
package jnr.ffi.provider.jffi;

import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.provider.BoundedMemoryIO;
import jnr.ffi.provider.jffi.DirectMemoryIO;

public final class MemoryUtil {

  private MemoryUtil() { // было: <init>
        super();
    }

  static Pointer newPointer(Runtime arg0, long arg1) {
        return arg1 == 0L ? null : new DirectMemoryIO(arg0, arg1);
    }

  static Pointer newPointer(Runtime arg0, int arg1) {
        return arg1 == 0 ? null : new DirectMemoryIO(arg0, arg1);
    }

  static Pointer newPointer(Runtime arg0, long arg1, long arg2) {
        return arg1 == 0L ? null : new BoundedMemoryIO(new DirectMemoryIO(arg0, arg1), 0L, arg2);
    }

}