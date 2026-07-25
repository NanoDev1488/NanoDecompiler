// исходный (обфусцированный) внутренний класс: com.kenai.jffi.HeapInvocationBuffer.DefaultEncoder
package com.kenai.jffi;

import com.kenai.jffi.CallContext;
import com.kenai.jffi.HeapInvocationBuffer_ArrayIO;
import com.kenai.jffi.HeapInvocationBuffer_Encoder;

final class HeapInvocationBuffer_DefaultEncoder extends HeapInvocationBuffer_Encoder {

    // ---- поля ----
  private final HeapInvocationBuffer_ArrayIO io;

  public HeapInvocationBuffer_DefaultEncoder(HeapInvocationBuffer_ArrayIO arg0) { // было: <init>
        super();
        io = arg0;
    }

  public final int getBufferSize(CallContext arg0) {
        return arg0.getParameterCount() * 8;
    }

  public final int putByte(byte[] arg0, int arg1, int arg2) {
        io.putByte(arg0, arg1, arg2);
        return arg1 + 8;
    }

  public final int putShort(byte[] arg0, int arg1, int arg2) {
        io.putShort(arg0, arg1, arg2);
        return arg1 + 8;
    }

  public final int putInt(byte[] arg0, int arg1, int arg2) {
        io.putInt(arg0, arg1, arg2);
        return arg1 + 8;
    }

  public final int putLong(byte[] arg0, int arg1, long arg2) {
        io.putLong(arg0, arg1, arg2);
        return arg1 + 8;
    }

  public final int putFloat(byte[] arg0, int arg1, float arg2) {
        io.putFloat(arg0, arg1, arg2);
        return arg1 + 8;
    }

  public final int putDouble(byte[] arg0, int arg1, double arg2) {
        io.putDouble(arg0, arg1, arg2);
        return arg1 + 8;
    }

  public final int putAddress(byte[] arg0, int arg1, long arg2) {
        io.putAddress(arg0, arg1, arg2);
        return arg1 + 8;
    }

  public int skipAddress(int arg0) {
        return arg0 + 8;
    }

}