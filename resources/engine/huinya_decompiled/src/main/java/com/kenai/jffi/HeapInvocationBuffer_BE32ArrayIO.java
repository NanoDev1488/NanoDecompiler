// исходный (обфусцированный) внутренний класс: com.kenai.jffi.HeapInvocationBuffer.BE32ArrayIO
package com.kenai.jffi;

import com.kenai.jffi.HeapInvocationBuffer_ArrayIO;
import com.kenai.jffi.HeapInvocationBuffer_BigEndianArrayIO;

final class HeapInvocationBuffer_BE32ArrayIO extends HeapInvocationBuffer_BigEndianArrayIO {

    // ---- поля ----
  static final HeapInvocationBuffer_ArrayIO INSTANCE;

    static {
        INSTANCE = new HeapInvocationBuffer_BE32ArrayIO();
    }

  private HeapInvocationBuffer_BE32ArrayIO() { // было: <init>
        super(null);
    }

  public void putAddress(byte[] arg0, int arg1, long arg2) {
        arg0[arg1 + 0] = ((byte) ((int) (arg2 >> 24)));
        arg0[arg1 + 1] = ((byte) ((int) (arg2 >> 16)));
        arg0[arg1 + 2] = ((byte) ((int) (arg2 >> 8)));
        arg0[arg1 + 3] = ((byte) ((int) arg2));
    }

}