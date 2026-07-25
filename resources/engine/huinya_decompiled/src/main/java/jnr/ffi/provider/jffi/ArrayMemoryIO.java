// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.ArrayMemoryIO
package jnr.ffi.provider.jffi;

import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.provider.AbstractArrayMemoryIO;
import jnr.ffi.provider.jffi.MemoryUtil;

public final class ArrayMemoryIO extends AbstractArrayMemoryIO {

  public ArrayMemoryIO(Runtime arg0, int arg1) { // было: <init>
        super(arg0, arg1);
    }

  public ArrayMemoryIO(Runtime arg0, byte[] arg1, int arg2, int arg3) { // было: <init>
        super(arg0, arg1, arg2, arg3);
    }

  public Pointer getPointer(long arg0) {
        return MemoryUtil.newPointer(getRuntime(), getAddress(arg0));
    }

  public Pointer getPointer(long arg0, long arg1) {
        return MemoryUtil.newPointer(getRuntime(), getAddress(arg0), arg1);
    }

  public void putPointer(long arg0, Pointer arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: lload_1
        //      2: aload_3
        //      3: ifnull  13 (offset +10)
        //      6: aload_3
        //      7: invokevirtual  #5 // jnr.ffi.Pointer.address:()J
        //     10: goto  14 (offset +4)
        //     13: lconst_0
        //     14: invokevirtual  #10 // jnr.ffi.provider.jffi.ArrayMemoryIO.putAddress:(JJ)V
        //     17: return
    }

}