// исходный (обфусцированный) внутренний класс: com.kenai.jffi.HeapInvocationBuffer.InvalidArrayIO
package com.kenai.jffi;

import com.kenai.jffi.HeapInvocationBuffer_ArrayIO;

final class HeapInvocationBuffer_InvalidArrayIO extends HeapInvocationBuffer_ArrayIO {

    // ---- поля ----
  private final Throwable error;

   HeapInvocationBuffer_InvalidArrayIO(Throwable arg0) { // было: <init>
        super(null);
        error = arg0;
    }

  private RuntimeException ex() {
        RuntimeException var1 = new RuntimeException("could not determine native data encoding");
        var1.initCause(error);
        return var1;
    }

  public void putByte(byte[] arg0, int arg1, int arg2) {
        throw ex();
    }

  public void putShort(byte[] arg0, int arg1, int arg2) {
        throw ex();
    }

  public void putInt(byte[] arg0, int arg1, int arg2) {
        throw ex();
    }

  public void putLong(byte[] arg0, int arg1, long arg2) {
        throw ex();
    }

  public void putAddress(byte[] arg0, int arg1, long arg2) {
        throw ex();
    }

}