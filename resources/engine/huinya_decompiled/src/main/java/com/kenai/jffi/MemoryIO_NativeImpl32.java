// исходный (обфусцированный) внутренний класс: com.kenai.jffi.MemoryIO.NativeImpl32
package com.kenai.jffi;

import com.kenai.jffi.Foreign;
import com.kenai.jffi.MemoryIO_Anon1;
import com.kenai.jffi.MemoryIO_NativeImpl;

final class MemoryIO_NativeImpl32 extends MemoryIO_NativeImpl {

  private MemoryIO_NativeImpl32() { // было: <init>
        super(null);
    }

  public final long getAddress(long arg0) {
        return ((long) Foreign.getInt(arg0)) & ADDRESS_MASK;
    }

  public final void putAddress(long arg0, long arg1) {
        Foreign.putInt(arg0, ((int) arg1));
    }

   MemoryIO_NativeImpl32(MemoryIO_Anon1 arg0) { // было: <init>
        this();
    }

}