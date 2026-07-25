// исходный (обфусцированный) внутренний класс: com.kenai.jffi.HeapObjectParameterInvoker
package com.kenai.jffi;

import com.kenai.jffi.Foreign;
import com.kenai.jffi.Function;
import com.kenai.jffi.HeapInvocationBuffer_Encoder;
import com.kenai.jffi.ObjectParameterInfo;
import com.kenai.jffi.ObjectParameterInvoker;
import com.kenai.jffi.Type;

final class HeapObjectParameterInvoker extends ObjectParameterInvoker {

    // ---- поля ----
  private final Foreign foreign;

   HeapObjectParameterInvoker(Foreign arg0) { // было: <init>
        super();
        foreign = arg0;
    }

  public final boolean isNative() {
        return false;
    }

  private static int encode(HeapInvocationBuffer_Encoder arg0, byte[] arg1, int arg2, Type arg3, long arg4) {
        if (arg3.size() > 4) {
            return arg0.putLong(arg1, arg2, arg4);
        } else {
            return arg0.putInt(arg1, arg2, ((int) arg4));
        }
    }

  private long invokeO1(Function arg0, byte[] arg1, Object arg2, int arg3, int arg4, ObjectParameterInfo arg5) {
        return arg0.getReturnType().size() != 8 ? ((long) Foreign.invokeArrayO1Int32(arg0.getContextAddress(), arg0.getFunctionAddress(), arg1, arg2, arg5.asObjectInfo(), arg3, arg4)) : Foreign.invokeArrayO1Int64(arg0.getContextAddress(), arg0.getFunctionAddress(), arg1, arg2, arg5.asObjectInfo(), arg3, arg4);
    }

  private long invokeO2(Function arg0, byte[] arg1, Object arg2, int arg3, int arg4, ObjectParameterInfo arg5, Object arg6, int arg7, int arg8, ObjectParameterInfo arg9) {
        return arg0.getReturnType().size() != 8 ? ((long) Foreign.invokeArrayO2Int32(arg0.getContextAddress(), arg0.getFunctionAddress(), arg1, arg2, arg5.asObjectInfo(), arg3, arg4, arg6, arg9.asObjectInfo(), arg7, arg8)) : Foreign.invokeArrayO2Int64(arg0.getContextAddress(), arg0.getFunctionAddress(), arg1, arg2, arg5.asObjectInfo(), arg3, arg4, arg6, arg9.asObjectInfo(), arg7, arg8);
    }

  private long invokeO3(Function arg0, byte[] arg1, Object arg2, int arg3, int arg4, ObjectParameterInfo arg5, Object arg6, int arg7, int arg8, ObjectParameterInfo arg9, Object arg10, int arg11, int arg12, ObjectParameterInfo arg13) {
        int[] var15 = new int[]{arg5.asObjectInfo(), arg3, arg4, arg9.asObjectInfo(), arg7, arg8, arg13.asObjectInfo(), arg11, arg12};
        Object[] __obj2 = new Object[3];
        __obj2[0] = arg2;
        __obj2[1] = arg6;
        __obj2[2] = arg10;
        Object[] var16 = __obj2;
        return arg0.getReturnType().size() != 8 ? ((long) Foreign.invokeArrayWithObjectsInt32(arg0.getContextAddress(), arg0.getFunctionAddress(), arg1, 3, var15, var16)) : Foreign.invokeArrayWithObjectsInt64(arg0.getContextAddress(), arg0.getFunctionAddress(), arg1, 3, var15, var16);
    }

  public long invokeN1O1rN(Function arg0, long arg1, Object arg2, int arg3, int arg4, ObjectParameterInfo arg5) {
        return invokeO1(arg0, new byte[HeapInvocationBuffer_Encoder.getInstance().getBufferSize(arg0.getCallContext())], arg2, arg3, arg4, arg5);
    }

  public long invokeN2O1rN(Function arg0, long arg1, long arg2, Object arg3, int arg4, int arg5, ObjectParameterInfo arg6) {
        HeapInvocationBuffer_Encoder var10 = HeapInvocationBuffer_Encoder.getInstance();
        byte[] var11 = new byte[var10.getBufferSize(arg0.getCallContext())];
        int var12 = 0;
        var12 = encode(var10, var11, var12, arg0.getParameterType(0), arg1);
        encode(var10, var11, var12, arg0.getParameterType(1), arg2);
        return invokeO1(arg0, var11, arg3, arg4, arg5, arg6);
    }

  public long invokeN2O2rN(Function arg0, long arg1, long arg2, Object arg3, int arg4, int arg5, ObjectParameterInfo arg6, Object arg7, int arg8, int arg9, ObjectParameterInfo arg10) {
        return invokeO2(arg0, new byte[HeapInvocationBuffer_Encoder.getInstance().getBufferSize(arg0.getCallContext())], arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
    }

  private static byte[] encodeN3(Function arg0, long arg1, long arg2, long arg3) {
        HeapInvocationBuffer_Encoder var7 = HeapInvocationBuffer_Encoder.getInstance();
        byte[] var8 = new byte[var7.getBufferSize(arg0.getCallContext())];
        int var9 = 0;
        var9 = encode(var7, var8, var9, arg0.getParameterType(0), arg1);
        var9 = encode(var7, var8, var9, arg0.getParameterType(1), arg2);
        encode(var7, var8, var9, arg0.getParameterType(2), arg3);
        return var8;
    }

  public long invokeN3O1rN(Function arg0, long arg1, long arg2, long arg3, Object arg4, int arg5, int arg6, ObjectParameterInfo arg7) {
        return invokeO1(arg0, encodeN3(arg0, arg1, arg2, arg3), arg4, arg5, arg6, arg7);
    }

  public long invokeN3O2rN(Function arg0, long arg1, long arg2, long arg3, Object arg4, int arg5, int arg6, ObjectParameterInfo arg7, Object arg8, int arg9, int arg10, ObjectParameterInfo arg11) {
        return invokeO2(arg0, encodeN3(arg0, arg1, arg2, arg3), arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11);
    }

  public long invokeN3O3rN(Function arg0, long arg1, long arg2, long arg3, Object arg4, int arg5, int arg6, ObjectParameterInfo arg7, Object arg8, int arg9, int arg10, ObjectParameterInfo arg11, Object arg12, int arg13, int arg14, ObjectParameterInfo arg15) {
        return invokeO3(arg0, encodeN3(arg0, arg1, arg2, arg3), arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15);
    }

  private static byte[] encodeN4(Function arg0, long arg1, long arg2, long arg3, long arg4) {
        HeapInvocationBuffer_Encoder var9 = HeapInvocationBuffer_Encoder.getInstance();
        byte[] var10 = new byte[var9.getBufferSize(arg0.getCallContext())];
        int var11 = 0;
        var11 = encode(var9, var10, var11, arg0.getParameterType(0), arg1);
        var11 = encode(var9, var10, var11, arg0.getParameterType(1), arg2);
        var11 = encode(var9, var10, var11, arg0.getParameterType(2), arg3);
        encode(var9, var10, var11, arg0.getParameterType(3), arg4);
        return var10;
    }

  public long invokeN4O1rN(Function arg0, long arg1, long arg2, long arg3, long arg4, Object arg5, int arg6, int arg7, ObjectParameterInfo arg8) {
        return invokeO1(arg0, encodeN4(arg0, arg1, arg2, arg3, arg4), arg5, arg6, arg7, arg8);
    }

  public long invokeN4O2rN(Function arg0, long arg1, long arg2, long arg3, long arg4, Object arg5, int arg6, int arg7, ObjectParameterInfo arg8, Object arg9, int arg10, int arg11, ObjectParameterInfo arg12) {
        return invokeO2(arg0, encodeN4(arg0, arg1, arg2, arg3, arg4), arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12);
    }

  public long invokeN4O3rN(Function arg0, long arg1, long arg2, long arg3, long arg4, Object arg5, int arg6, int arg7, ObjectParameterInfo arg8, Object arg9, int arg10, int arg11, ObjectParameterInfo arg12, Object arg13, int arg14, int arg15, ObjectParameterInfo arg16) {
        return invokeO3(arg0, encodeN4(arg0, arg1, arg2, arg3, arg4), arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16);
    }

  private static byte[] encodeN5(Function arg0, long arg1, long arg2, long arg3, long arg4, long arg5) {
        HeapInvocationBuffer_Encoder var11 = HeapInvocationBuffer_Encoder.getInstance();
        byte[] var12 = new byte[var11.getBufferSize(arg0.getCallContext())];
        int var13 = 0;
        var13 = encode(var11, var12, var13, arg0.getParameterType(0), arg1);
        var13 = encode(var11, var12, var13, arg0.getParameterType(1), arg2);
        var13 = encode(var11, var12, var13, arg0.getParameterType(2), arg3);
        var13 = encode(var11, var12, var13, arg0.getParameterType(3), arg4);
        encode(var11, var12, var13, arg0.getParameterType(4), arg5);
        return var12;
    }

  public long invokeN5O1rN(Function arg0, long arg1, long arg2, long arg3, long arg4, long arg5, Object arg6, int arg7, int arg8, ObjectParameterInfo arg9) {
        return invokeO1(arg0, encodeN5(arg0, arg1, arg2, arg3, arg4, arg5), arg6, arg7, arg8, arg9);
    }

  public long invokeN5O2rN(Function arg0, long arg1, long arg2, long arg3, long arg4, long arg5, Object arg6, int arg7, int arg8, ObjectParameterInfo arg9, Object arg10, int arg11, int arg12, ObjectParameterInfo arg13) {
        return invokeO2(arg0, encodeN5(arg0, arg1, arg2, arg3, arg4, arg5), arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13);
    }

  public long invokeN5O3rN(Function arg0, long arg1, long arg2, long arg3, long arg4, long arg5, Object arg6, int arg7, int arg8, ObjectParameterInfo arg9, Object arg10, int arg11, int arg12, ObjectParameterInfo arg13, Object arg14, int arg15, int arg16, ObjectParameterInfo arg17) {
        return invokeO3(arg0, encodeN5(arg0, arg1, arg2, arg3, arg4, arg5), arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17);
    }

  private static byte[] encodeN6(Function arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6) {
        HeapInvocationBuffer_Encoder var13 = HeapInvocationBuffer_Encoder.getInstance();
        byte[] var14 = new byte[var13.getBufferSize(arg0.getCallContext())];
        int var15 = 0;
        var15 = encode(var13, var14, var15, arg0.getParameterType(0), arg1);
        var15 = encode(var13, var14, var15, arg0.getParameterType(1), arg2);
        var15 = encode(var13, var14, var15, arg0.getParameterType(2), arg3);
        var15 = encode(var13, var14, var15, arg0.getParameterType(3), arg4);
        var15 = encode(var13, var14, var15, arg0.getParameterType(4), arg5);
        encode(var13, var14, var15, arg0.getParameterType(5), arg6);
        return var14;
    }

  public long invokeN6O1rN(Function arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, Object arg7, int arg8, int arg9, ObjectParameterInfo arg10) {
        return invokeO1(arg0, encodeN6(arg0, arg1, arg2, arg3, arg4, arg5, arg6), arg7, arg8, arg9, arg10);
    }

  public long invokeN6O2rN(Function arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, Object arg7, int arg8, int arg9, ObjectParameterInfo arg10, Object arg11, int arg12, int arg13, ObjectParameterInfo arg14) {
        return invokeO2(arg0, encodeN6(arg0, arg1, arg2, arg3, arg4, arg5, arg6), arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14);
    }

  public long invokeN6O3rN(Function arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, Object arg7, int arg8, int arg9, ObjectParameterInfo arg10, Object arg11, int arg12, int arg13, ObjectParameterInfo arg14, Object arg15, int arg16, int arg17, ObjectParameterInfo arg18) {
        return invokeO3(arg0, encodeN6(arg0, arg1, arg2, arg3, arg4, arg5, arg6), arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18);
    }

}