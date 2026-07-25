// исходный (обфусцированный) внутренний класс: com.kenai.jffi.DirectClosureBuffer
package com.kenai.jffi;

import com.kenai.jffi.CallContext;
import com.kenai.jffi.Closure_Buffer;
import com.kenai.jffi.DirectClosureBuffer_NativeWordIO;
import com.kenai.jffi.MemoryIO;
import com.kenai.jffi.Platform;
import com.kenai.jffi.Type;

final class DirectClosureBuffer implements Closure_Buffer {

    // ---- поля ----
  private static final MemoryIO IO;
  private static final DirectClosureBuffer_NativeWordIO WordIO;
  private static final long PARAM_SIZE;
  private final long retval;
  private final long parameters;
  private final CallContext callContext;

    static {
        IO = MemoryIO.getInstance();
        WordIO = DirectClosureBuffer_NativeWordIO.getInstance();
        PARAM_SIZE = ((long) (Platform.getPlatform().addressSize() / 8));
    }

  public DirectClosureBuffer(CallContext arg0, long arg1, long arg2) { // было: <init>
        super();
        callContext = arg0;
        retval = arg1;
        parameters = arg2;
    }

  public final byte getByte(int arg0) {
        return IO.getByte(IO.getAddress(parameters + ((long) arg0) * PARAM_SIZE));
    }

  public final short getShort(int arg0) {
        return IO.getShort(IO.getAddress(parameters + ((long) arg0) * PARAM_SIZE));
    }

  public final int getInt(int arg0) {
        return IO.getInt(IO.getAddress(parameters + ((long) arg0) * PARAM_SIZE));
    }

  public final long getLong(int arg0) {
        return IO.getLong(IO.getAddress(parameters + ((long) arg0) * PARAM_SIZE));
    }

  public final float getFloat(int arg0) {
        return IO.getFloat(IO.getAddress(parameters + ((long) arg0) * PARAM_SIZE));
    }

  public final double getDouble(int arg0) {
        return IO.getDouble(IO.getAddress(parameters + ((long) arg0) * PARAM_SIZE));
    }

  public final long getAddress(int arg0) {
        return IO.getAddress(IO.getAddress(parameters + ((long) arg0) * PARAM_SIZE));
    }

  public final long getStruct(int arg0) {
        return IO.getAddress(parameters + ((long) arg0) * PARAM_SIZE);
    }

  public final void setByteReturn(byte arg0) {
        WordIO.put(retval, arg0);
    }

  public final void setShortReturn(short arg0) {
        WordIO.put(retval, arg0);
    }

  public final void setIntReturn(int arg0) {
        WordIO.put(retval, arg0);
    }

  public final void setLongReturn(long arg0) {
        IO.putLong(retval, arg0);
    }

  public final void setFloatReturn(float arg0) {
        IO.putFloat(retval, arg0);
    }

  public final void setDoubleReturn(double arg0) {
        IO.putDouble(retval, arg0);
    }

  public final void setAddressReturn(long arg0) {
        IO.putAddress(retval, arg0);
    }

  public void setStructReturn(long arg0) {
        IO.copyMemory(arg0, retval, ((long) callContext.getReturnType().size()));
    }

  public void setStructReturn(byte[] arg0, int arg1) {
        IO.putByteArray(retval, arg0, arg1, callContext.getReturnType().size());
    }

}