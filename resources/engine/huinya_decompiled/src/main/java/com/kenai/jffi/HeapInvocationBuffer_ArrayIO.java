// исходный (обфусцированный) внутренний класс: com.kenai.jffi.HeapInvocationBuffer.ArrayIO
package com.kenai.jffi;

import com.kenai.jffi.HeapInvocationBuffer_Anon1;
import com.kenai.jffi.HeapInvocationBuffer_ArrayIO_SingletonHolder;
import com.kenai.jffi.HeapInvocationBuffer_BE32ArrayIO;
import com.kenai.jffi.HeapInvocationBuffer_BE64ArrayIO;
import com.kenai.jffi.HeapInvocationBuffer_InvalidArrayIO;
import com.kenai.jffi.HeapInvocationBuffer_LE32ArrayIO;
import com.kenai.jffi.HeapInvocationBuffer_LE64ArrayIO;

abstract class HeapInvocationBuffer_ArrayIO {

  private HeapInvocationBuffer_ArrayIO() { // было: <init>
        super();
    }

  static HeapInvocationBuffer_ArrayIO getInstance() {
        return HeapInvocationBuffer_ArrayIO_SingletonHolder.access$000();
    }

  static HeapInvocationBuffer_ArrayIO getBE32IO() {
        return HeapInvocationBuffer_BE32ArrayIO.INSTANCE;
    }

  static HeapInvocationBuffer_ArrayIO getLE32IO() {
        return HeapInvocationBuffer_LE32ArrayIO.INSTANCE;
    }

  static HeapInvocationBuffer_ArrayIO getLE64IO() {
        return HeapInvocationBuffer_LE64ArrayIO.INSTANCE;
    }

  static HeapInvocationBuffer_ArrayIO getBE64IO() {
        return HeapInvocationBuffer_BE64ArrayIO.INSTANCE;
    }

  static HeapInvocationBuffer_ArrayIO newInvalidArrayIO(Throwable arg0) {
        return new HeapInvocationBuffer_InvalidArrayIO(arg0);
    }

  public abstract void putByte(byte[] arg0, int arg1, int arg2);

  public abstract void putShort(byte[] arg0, int arg1, int arg2);

  public abstract void putInt(byte[] arg0, int arg1, int arg2);

  public abstract void putLong(byte[] arg0, int arg1, long arg2);

  public final void putFloat(byte[] arg0, int arg1, float arg2) {
        putInt(arg0, arg1, Float.floatToRawIntBits(arg2));
    }

  public final void putDouble(byte[] arg0, int arg1, double arg2) {
        putLong(arg0, arg1, Double.doubleToRawLongBits(arg2));
    }

  public abstract void putAddress(byte[] arg0, int arg1, long arg2);

   HeapInvocationBuffer_ArrayIO(HeapInvocationBuffer_Anon1 arg0) { // было: <init>
        this();
    }

}