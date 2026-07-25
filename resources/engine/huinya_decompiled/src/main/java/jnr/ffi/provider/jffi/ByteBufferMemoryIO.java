// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.ByteBufferMemoryIO
package jnr.ffi.provider.jffi;

import com.kenai.jffi.MemoryIO;
import java.nio.ByteBuffer;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.provider.AbstractBufferMemoryIO;
import jnr.ffi.provider.jffi.MemoryUtil;

public class ByteBufferMemoryIO extends AbstractBufferMemoryIO {

  public ByteBufferMemoryIO(Runtime arg0, ByteBuffer arg1) { // было: <init>
        super(arg0, arg1, address(arg1));
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
        //      7: invokevirtual  #11 // jnr.ffi.Pointer.address:()J
        //     10: goto  14 (offset +4)
        //     13: lconst_0
        //     14: invokevirtual  #16 // jnr.ffi.provider.jffi.ByteBufferMemoryIO.putAddress:(JJ)V
        //     17: return
    }

  private static long address(ByteBuffer arg0) {
        if (!arg0.isDirect()) {
            return 0L;
        } else {
            long var1 = MemoryIO.getInstance().getDirectBufferAddress(arg0);
            return var1 == 0L ? 0L : var1 + ((long) arg0.position());
        }
    }

}