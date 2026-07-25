// исходный (обфусцированный) внутренний класс: com.kenai.jffi.HeapInvocationBuffer.Encoder
package com.kenai.jffi;

import com.kenai.jffi.CallContext;
import com.kenai.jffi.HeapInvocationBuffer_Encoder_SingletonHolder;

abstract class HeapInvocationBuffer_Encoder {

   HeapInvocationBuffer_Encoder() { // было: <init>
        super();
    }

  static HeapInvocationBuffer_Encoder getInstance() {
        return HeapInvocationBuffer_Encoder_SingletonHolder.INSTANCE;
    }

  public abstract int getBufferSize(CallContext arg0);

  public abstract int putByte(byte[] arg0, int arg1, int arg2);

  public abstract int putShort(byte[] arg0, int arg1, int arg2);

  public abstract int putInt(byte[] arg0, int arg1, int arg2);

  public abstract int putLong(byte[] arg0, int arg1, long arg2);

  public abstract int putFloat(byte[] arg0, int arg1, float arg2);

  public abstract int putDouble(byte[] arg0, int arg1, double arg2);

  public abstract int putAddress(byte[] arg0, int arg1, long arg2);

  public abstract int skipAddress(int arg0);

}