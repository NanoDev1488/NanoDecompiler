// исходный (обфусцированный) внутренний класс: com.kenai.jffi.Closure.Buffer
package com.kenai.jffi;

public interface Closure_Buffer {

  public abstract byte getByte(int arg0);

  public abstract short getShort(int arg0);

  public abstract int getInt(int arg0);

  public abstract long getLong(int arg0);

  public abstract float getFloat(int arg0);

  public abstract double getDouble(int arg0);

  public abstract long getAddress(int arg0);

  public abstract long getStruct(int arg0);

  public abstract void setByteReturn(byte arg0);

  public abstract void setShortReturn(short arg0);

  public abstract void setIntReturn(int arg0);

  public abstract void setLongReturn(long arg0);

  public abstract void setFloatReturn(float arg0);

  public abstract void setDoubleReturn(double arg0);

  public abstract void setAddressReturn(long arg0);

  public abstract void setStructReturn(long arg0);

  public abstract void setStructReturn(byte[] arg0, int arg1);

}