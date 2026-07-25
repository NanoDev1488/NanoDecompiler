// исходный (обфусцированный) внутренний класс: com.kenai.jffi.HeapInvocationBuffer.LE32ArrayIO
package com.kenai.jffi;

import com.kenai.jffi.HeapInvocationBuffer_ArrayIO;
import com.kenai.jffi.HeapInvocationBuffer_LittleEndianArrayIO;

final class HeapInvocationBuffer_LE32ArrayIO extends HeapInvocationBuffer_LittleEndianArrayIO {

    // ---- поля ----
  static final HeapInvocationBuffer_ArrayIO INSTANCE;

    static {
        INSTANCE = new HeapInvocationBuffer_LE32ArrayIO();
    }

  private HeapInvocationBuffer_LE32ArrayIO() { // было: <init>
        super(null);
    }

  public final void putAddress(byte[] arg0, int arg1, long arg2) {
        arg0[arg1] = ((byte) ((int) arg2));
        arg0[arg1 + 1] = ((byte) ((int) (arg2 >> 8)));
        arg0[arg1 + 2] = ((byte) ((int) (arg2 >> 16)));
        arg0[arg1 + 3] = ((byte) ((int) (arg2 >> 24)));
    }

}