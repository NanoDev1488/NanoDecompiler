// исходный (обфусцированный) внутренний класс: com.kenai.jffi.HeapInvocationBuffer.LittleEndianArrayIO
package com.kenai.jffi;

import com.kenai.jffi.HeapInvocationBuffer_Anon1;
import com.kenai.jffi.HeapInvocationBuffer_ArrayIO;

abstract class HeapInvocationBuffer_LittleEndianArrayIO extends HeapInvocationBuffer_ArrayIO {

  private HeapInvocationBuffer_LittleEndianArrayIO() { // было: <init>
        super(null);
    }

  public final void putByte(byte[] arg0, int arg1, int arg2) {
        arg0[arg1] = ((byte) arg2);
    }

  public final void putShort(byte[] arg0, int arg1, int arg2) {
        arg0[arg1] = ((byte) arg2);
        arg0[arg1 + 1] = ((byte) (arg2 >> 8));
    }

  public final void putInt(byte[] arg0, int arg1, int arg2) {
        arg0[arg1] = ((byte) arg2);
        arg0[arg1 + 1] = ((byte) (arg2 >> 8));
        arg0[arg1 + 2] = ((byte) (arg2 >> 16));
        arg0[arg1 + 3] = ((byte) (arg2 >> 24));
    }

  public final void putLong(byte[] arg0, int arg1, long arg2) {
        arg0[arg1] = ((byte) ((int) arg2));
        arg0[arg1 + 1] = ((byte) ((int) (arg2 >> 8)));
        arg0[arg1 + 2] = ((byte) ((int) (arg2 >> 16)));
        arg0[arg1 + 3] = ((byte) ((int) (arg2 >> 24)));
        arg0[arg1 + 4] = ((byte) ((int) (arg2 >> 32)));
        arg0[arg1 + 5] = ((byte) ((int) (arg2 >> 40)));
        arg0[arg1 + 6] = ((byte) ((int) (arg2 >> 48)));
        arg0[arg1 + 7] = ((byte) ((int) (arg2 >> 56)));
    }

   HeapInvocationBuffer_LittleEndianArrayIO(HeapInvocationBuffer_Anon1 arg0) { // было: <init>
        this();
    }

}