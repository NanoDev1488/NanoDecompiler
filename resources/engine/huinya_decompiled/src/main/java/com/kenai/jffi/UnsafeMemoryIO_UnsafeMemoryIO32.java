// исходный (обфусцированный) внутренний класс: com.kenai.jffi.UnsafeMemoryIO.UnsafeMemoryIO32
package com.kenai.jffi;

import com.kenai.jffi.UnsafeMemoryIO;
import sun.misc.Unsafe;

class UnsafeMemoryIO_UnsafeMemoryIO32 extends UnsafeMemoryIO {

   UnsafeMemoryIO_UnsafeMemoryIO32() { // было: <init>
        super();
    }

  public final long getAddress(long arg0) {
        return ((long) unsafe.getInt(arg0)) & ADDRESS_MASK;
    }

  public final void putAddress(long arg0, long arg1) {
        unsafe.putInt(arg0, ((int) arg1));
    }

}