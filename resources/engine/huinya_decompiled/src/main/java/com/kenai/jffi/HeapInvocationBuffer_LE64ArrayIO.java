// исходный (обфусцированный) внутренний класс: com.kenai.jffi.HeapInvocationBuffer.LE64ArrayIO
package com.kenai.jffi;

import com.kenai.jffi.HeapInvocationBuffer_ArrayIO;
import com.kenai.jffi.HeapInvocationBuffer_LittleEndianArrayIO;

final class HeapInvocationBuffer_LE64ArrayIO extends HeapInvocationBuffer_LittleEndianArrayIO {

    // ---- поля ----
  static final HeapInvocationBuffer_ArrayIO INSTANCE;

    static {
        INSTANCE = new HeapInvocationBuffer_LE64ArrayIO();
    }

  private HeapInvocationBuffer_LE64ArrayIO() { // было: <init>
        super(null);
    }

  public final void putAddress(byte[] arg0, int arg1, long arg2) {
        putLong(arg0, arg1, arg2);
    }

}