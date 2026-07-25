// исходный (обфусцированный) внутренний класс: com.kenai.jffi.MemoryIO.NativeImpl64
package com.kenai.jffi;

import com.kenai.jffi.Foreign;
import com.kenai.jffi.MemoryIO_Anon1;
import com.kenai.jffi.MemoryIO_NativeImpl;

final class MemoryIO_NativeImpl64 extends MemoryIO_NativeImpl {

  private MemoryIO_NativeImpl64() { // было: <init>
        super(null);
    }

  public final long getAddress(long arg0) {
        return Foreign.getLong(arg0);
    }

  public final void putAddress(long arg0, long arg1) {
        Foreign.putLong(arg0, arg1);
    }

   MemoryIO_NativeImpl64(MemoryIO_Anon1 arg0) { // было: <init>
        this();
    }

}