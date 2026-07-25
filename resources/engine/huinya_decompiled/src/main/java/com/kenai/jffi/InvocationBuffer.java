// исходный (обфусцированный) внутренний класс: com.kenai.jffi.InvocationBuffer
package com.kenai.jffi;

import java.nio.Buffer;

public abstract class InvocationBuffer {

  public InvocationBuffer() { // было: <init>
        super();
    }

  public abstract void putByte(int arg0);

  public abstract void putShort(int arg0);

  public abstract void putInt(int arg0);

  public abstract void putLong(long arg0);

  public abstract void putFloat(float arg0);

  public abstract void putDouble(double arg0);

  public abstract void putAddress(long arg0);

  public abstract void putArray(byte[] arg0, int arg1, int arg2, int arg3);

  public abstract void putArray(short[] arg0, int arg1, int arg2, int arg3);

  public abstract void putArray(int[] arg0, int arg1, int arg2, int arg3);

  public abstract void putArray(long[] arg0, int arg1, int arg2, int arg3);

  public abstract void putArray(float[] arg0, int arg1, int arg2, int arg3);

  public abstract void putArray(double[] arg0, int arg1, int arg2, int arg3);

  public abstract void putDirectBuffer(Buffer arg0, int arg1, int arg2);

  public abstract void putStruct(byte[] arg0, int arg1);

  public abstract void putStruct(long arg0);

}