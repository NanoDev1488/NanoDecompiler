// исходный (обфусцированный) внутренний класс: com.kenai.jffi.HeapInvocationBuffer.BE64ArrayIO
package com.kenai.jffi;

import com.kenai.jffi.HeapInvocationBuffer_ArrayIO;
import com.kenai.jffi.HeapInvocationBuffer_BigEndianArrayIO;

final class HeapInvocationBuffer_BE64ArrayIO extends HeapInvocationBuffer_BigEndianArrayIO {

    // ---- поля ----
  static final HeapInvocationBuffer_ArrayIO INSTANCE;

    static {
        INSTANCE = new HeapInvocationBuffer_BE64ArrayIO();
    }

  private HeapInvocationBuffer_BE64ArrayIO() { // было: <init>
        super(null);
    }

  public void putAddress(byte[] arg0, int arg1, long arg2) {
        putLong(arg0, arg1, arg2);
    }

}