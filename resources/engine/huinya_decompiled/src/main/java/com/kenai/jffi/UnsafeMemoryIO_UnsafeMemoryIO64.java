// исходный (обфусцированный) внутренний класс: com.kenai.jffi.UnsafeMemoryIO.UnsafeMemoryIO64
package com.kenai.jffi;

import com.kenai.jffi.UnsafeMemoryIO;
import sun.misc.Unsafe;

class UnsafeMemoryIO_UnsafeMemoryIO64 extends UnsafeMemoryIO {

   UnsafeMemoryIO_UnsafeMemoryIO64() { // было: <init>
        super();
    }

  public final long getAddress(long arg0) {
        return unsafe.getLong(arg0);
    }

  public final void putAddress(long arg0, long arg1) {
        unsafe.putLong(arg0, arg1);
    }

}