// исходный (обфусцированный) внутренний класс: com.kenai.jffi.HeapInvocationBuffer
package com.kenai.jffi;

import com.kenai.jffi.CallContext;
import com.kenai.jffi.Foreign;
import com.kenai.jffi.Function;
import com.kenai.jffi.HeapInvocationBuffer_Encoder;
import com.kenai.jffi.InvocationBuffer;
import com.kenai.jffi.ObjectBuffer;
import com.kenai.jffi.ObjectParameterInfo;
import com.kenai.jffi.ObjectParameterStrategy;
import com.kenai.jffi.Type;
import java.math.BigDecimal;
import java.nio.Buffer;

public final class HeapInvocationBuffer extends InvocationBuffer {

    // ---- поля ----
  private static final int PARAM_SIZE = 8;
  private final CallContext callContext;
  private final byte[] buffer;
  private ObjectBuffer objectBuffer;
  private int paramOffset;
  private int paramIndex;

  public HeapInvocationBuffer(Function arg0) { // было: <init>
        super();
        paramOffset = 0;
        paramIndex = 0;
        callContext = arg0.getCallContext();
        buffer = new byte[HeapInvocationBuffer_Encoder.getInstance().getBufferSize(callContext)];
    }

  public HeapInvocationBuffer(CallContext arg0) { // было: <init>
        super();
        paramOffset = 0;
        paramIndex = 0;
        callContext = arg0;
        buffer = new byte[HeapInvocationBuffer_Encoder.getInstance().getBufferSize(arg0)];
    }

  public HeapInvocationBuffer(CallContext arg0, int arg1) { // было: <init>
        super();
        paramOffset = 0;
        paramIndex = 0;
        callContext = arg0;
        buffer = new byte[HeapInvocationBuffer_Encoder.getInstance().getBufferSize(arg0)];
        objectBuffer = new ObjectBuffer(arg1);
    }

   byte[] array() {
        return buffer;
    }

   ObjectBuffer objectBuffer() {
        return objectBuffer;
    }

  public final void putByte(int arg0) {
        paramOffset = HeapInvocationBuffer_Encoder.getInstance().putByte(buffer, paramOffset, arg0);
        paramIndex = paramIndex + 1;
    }

  public final void putShort(int arg0) {
        paramOffset = HeapInvocationBuffer_Encoder.getInstance().putShort(buffer, paramOffset, arg0);
        paramIndex = paramIndex + 1;
    }

  public final void putInt(int arg0) {
        paramOffset = HeapInvocationBuffer_Encoder.getInstance().putInt(buffer, paramOffset, arg0);
        paramIndex = paramIndex + 1;
    }

  public final void putLong(long arg0) {
        paramOffset = HeapInvocationBuffer_Encoder.getInstance().putLong(buffer, paramOffset, arg0);
        paramIndex = paramIndex + 1;
    }

  public final void putFloat(float arg0) {
        paramOffset = HeapInvocationBuffer_Encoder.getInstance().putFloat(buffer, paramOffset, arg0);
        paramIndex = paramIndex + 1;
    }

  public final void putDouble(double arg0) {
        paramOffset = HeapInvocationBuffer_Encoder.getInstance().putDouble(buffer, paramOffset, arg0);
        paramIndex = paramIndex + 1;
    }

  public final void putLongDouble(double arg0) {
        byte[] var3 = new byte[Type.LONGDOUBLE.size()];
        Foreign.getInstance().longDoubleFromDouble(arg0, var3, 0, Type.LONGDOUBLE.size());
        getObjectBuffer().putArray(paramIndex, var3, 0, var3.length, 1);
        paramOffset = paramOffset + 8;
        paramIndex = paramIndex + 1;
    }

  public final void putLongDouble(BigDecimal arg0) {
        byte[] var2 = new byte[Type.LONGDOUBLE.size()];
        Foreign.getInstance().longDoubleFromString(arg0.toEngineeringString(), var2, 0, Type.LONGDOUBLE.size());
        getObjectBuffer().putArray(paramIndex, var2, 0, var2.length, 1);
        paramOffset = paramOffset + 8;
        paramIndex = paramIndex + 1;
    }

  public final void putAddress(long arg0) {
        paramOffset = HeapInvocationBuffer_Encoder.getInstance().putAddress(buffer, paramOffset, arg0);
        paramIndex = paramIndex + 1;
    }

  private final ObjectBuffer getObjectBuffer() {
        if (objectBuffer == null) {
            objectBuffer = new ObjectBuffer();
        }
        return objectBuffer;
    }

  public final void putArray(byte[] arg0, int arg1, int arg2, int arg3) {
        paramOffset = HeapInvocationBuffer_Encoder.getInstance().skipAddress(paramOffset);
        paramIndex = paramIndex + 1;
        getObjectBuffer().putArray(paramIndex, arg0, arg1, arg2, arg3);
    }

  public final void putArray(short[] arg0, int arg1, int arg2, int arg3) {
        paramOffset = HeapInvocationBuffer_Encoder.getInstance().skipAddress(paramOffset);
        paramIndex = paramIndex + 1;
        getObjectBuffer().putArray(paramIndex, arg0, arg1, arg2, arg3);
    }

  public final void putArray(int[] arg0, int arg1, int arg2, int arg3) {
        paramOffset = HeapInvocationBuffer_Encoder.getInstance().skipAddress(paramOffset);
        paramIndex = paramIndex + 1;
        getObjectBuffer().putArray(paramIndex, arg0, arg1, arg2, arg3);
    }

  public final void putArray(long[] arg0, int arg1, int arg2, int arg3) {
        paramOffset = HeapInvocationBuffer_Encoder.getInstance().skipAddress(paramOffset);
        paramIndex = paramIndex + 1;
        getObjectBuffer().putArray(paramIndex, arg0, arg1, arg2, arg3);
    }

  public final void putArray(float[] arg0, int arg1, int arg2, int arg3) {
        paramOffset = HeapInvocationBuffer_Encoder.getInstance().skipAddress(paramOffset);
        paramIndex = paramIndex + 1;
        getObjectBuffer().putArray(paramIndex, arg0, arg1, arg2, arg3);
    }

  public final void putArray(double[] arg0, int arg1, int arg2, int arg3) {
        paramOffset = HeapInvocationBuffer_Encoder.getInstance().skipAddress(paramOffset);
        paramIndex = paramIndex + 1;
        getObjectBuffer().putArray(paramIndex, arg0, arg1, arg2, arg3);
    }

  public final void putDirectBuffer(Buffer arg0, int arg1, int arg2) {
        paramOffset = HeapInvocationBuffer_Encoder.getInstance().skipAddress(paramOffset);
        paramIndex = paramIndex + 1;
        getObjectBuffer().putDirectBuffer(paramIndex, arg0, arg1, arg2);
    }

  public final void putStruct(byte[] arg0, int arg1) {
        Type var3 = callContext.getParameterType(paramIndex);
        paramOffset = HeapInvocationBuffer_Encoder.getInstance().skipAddress(paramOffset);
        getObjectBuffer().putArray(paramIndex, arg0, arg1, var3.size(), 1);
        paramIndex = paramIndex + 1;
    }

  public final void putStruct(long arg0) {
        Type var3 = callContext.getParameterType(paramIndex);
        paramOffset = HeapInvocationBuffer_Encoder.getInstance().putAddress(buffer, paramOffset, arg0);
        paramIndex = paramIndex + 1;
    }

  public final void putObject(Object arg0, ObjectParameterStrategy arg1, ObjectParameterInfo arg2) {
        if (!arg1.isDirect()) {
            paramOffset = HeapInvocationBuffer_Encoder.getInstance().skipAddress(paramOffset);
            getObjectBuffer().putObject(arg1.object(arg0), arg1.offset(arg0), arg1.length(arg0), ObjectBuffer.makeObjectFlags(arg2.ioflags(), arg1.typeInfo, paramIndex));
        } else {
            paramOffset = HeapInvocationBuffer_Encoder.getInstance().putAddress(buffer, paramOffset, arg1.address(arg0));
        }
        paramIndex = paramIndex + 1;
    }

  public final void putObject(Object arg0, ObjectParameterStrategy arg1, int arg2) {
        if (!arg1.isDirect()) {
            paramOffset = HeapInvocationBuffer_Encoder.getInstance().skipAddress(paramOffset);
            getObjectBuffer().putObject(arg1.object(arg0), arg1.offset(arg0), arg1.length(arg0), ObjectBuffer.makeObjectFlags(arg2, arg1.typeInfo, paramIndex));
        } else {
            paramOffset = HeapInvocationBuffer_Encoder.getInstance().putAddress(buffer, paramOffset, arg1.address(arg0));
        }
        paramIndex = paramIndex + 1;
    }

  public final void putJNIEnvironment() {
        paramOffset = HeapInvocationBuffer_Encoder.getInstance().putAddress(buffer, paramOffset, 0L);
        paramIndex = paramIndex + 1;
        getObjectBuffer().putJNI(paramIndex, null, 16777216);
    }

  public final void putJNIObject(Object arg0) {
        paramOffset = HeapInvocationBuffer_Encoder.getInstance().putAddress(buffer, paramOffset, 0L);
        paramIndex = paramIndex + 1;
        getObjectBuffer().putJNI(paramIndex, arg0, 33554432);
    }

}