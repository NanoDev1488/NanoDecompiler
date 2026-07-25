// исходный (обфусцированный) внутренний класс: com.kenai.jffi.NativeObjectParameterInvoker
package com.kenai.jffi;

import com.kenai.jffi.CallContext;
import com.kenai.jffi.Foreign;
import com.kenai.jffi.Function;
import com.kenai.jffi.ObjectParameterInfo;
import com.kenai.jffi.ObjectParameterInvoker;

final class NativeObjectParameterInvoker extends ObjectParameterInvoker {

    // ---- поля ----
  private final Foreign foreign;

  public final boolean isNative() {
        return true;
    }

   NativeObjectParameterInvoker(Foreign arg0) { // было: <init>
        super();
        foreign = arg0;
    }

  public final long invokeN1O1rN(Function arg0, long arg1, Object arg2, int arg3, int arg4, ObjectParameterInfo arg5) {
        return Foreign.invokeN1O1(arg0.getContextAddress(), arg0.getFunctionAddress(), arg1, arg2, arg5.asObjectInfo(), arg3, arg4);
    }

  public final long invokeN1O1(CallContext arg0, long arg1, long arg2, Object arg3, int arg4, int arg5, ObjectParameterInfo arg6) {
        return Foreign.invokeN1O1(arg0.getAddress(), arg1, arg2, arg3, arg6.asObjectInfo(), arg4, arg5);
    }

  public final long invokeN2O1rN(Function arg0, long arg1, long arg2, Object arg3, int arg4, int arg5, ObjectParameterInfo arg6) {
        return Foreign.invokeN2O1(arg0.getContextAddress(), arg0.getFunctionAddress(), arg1, arg2, arg3, arg6.asObjectInfo(), arg4, arg5);
    }

  public final long invokeN2O1(CallContext arg0, long arg1, long arg2, long arg3, Object arg4, int arg5, int arg6, ObjectParameterInfo arg7) {
        return Foreign.invokeN2O1(arg0.getAddress(), arg1, arg2, arg3, arg4, arg7.asObjectInfo(), arg5, arg6);
    }

  public final long invokeN2O2rN(Function arg0, long arg1, long arg2, Object arg3, int arg4, int arg5, ObjectParameterInfo arg6, Object arg7, int arg8, int arg9, ObjectParameterInfo arg10) {
        return Foreign.invokeN2O2(arg0.getContextAddress(), arg0.getFunctionAddress(), arg1, arg2, arg3, arg6.asObjectInfo(), arg4, arg5, arg7, arg10.asObjectInfo(), arg8, arg9);
    }

  public final long invokeN3O1rN(Function arg0, long arg1, long arg2, long arg3, Object arg4, int arg5, int arg6, ObjectParameterInfo arg7) {
        return Foreign.invokeN3O1(arg0.getContextAddress(), arg0.getFunctionAddress(), arg1, arg2, arg3, arg4, arg7.asObjectInfo(), arg5, arg6);
    }

  public final long invokeN3O2rN(Function arg0, long arg1, long arg2, long arg3, Object arg4, int arg5, int arg6, ObjectParameterInfo arg7, Object arg8, int arg9, int arg10, ObjectParameterInfo arg11) {
        return Foreign.invokeN3O2(arg0.getContextAddress(), arg0.getFunctionAddress(), arg1, arg2, arg3, arg4, arg7.asObjectInfo(), arg5, arg6, arg8, arg11.asObjectInfo(), arg9, arg10);
    }

  public final long invokeN3O3rN(Function arg0, long arg1, long arg2, long arg3, Object arg4, int arg5, int arg6, ObjectParameterInfo arg7, Object arg8, int arg9, int arg10, ObjectParameterInfo arg11, Object arg12, int arg13, int arg14, ObjectParameterInfo arg15) {
        return Foreign.invokeN3O3(arg0.getContextAddress(), arg0.getFunctionAddress(), arg1, arg2, arg3, arg4, arg7.asObjectInfo(), arg5, arg6, arg8, arg11.asObjectInfo(), arg9, arg10, arg12, arg15.asObjectInfo(), arg13, arg14);
    }

  public final long invokeN4O1rN(Function arg0, long arg1, long arg2, long arg3, long arg4, Object arg5, int arg6, int arg7, ObjectParameterInfo arg8) {
        return Foreign.invokeN4O1(arg0.getContextAddress(), arg0.getFunctionAddress(), arg1, arg2, arg3, arg4, arg5, arg8.asObjectInfo(), arg6, arg7);
    }

  public final long invokeN4O2rN(Function arg0, long arg1, long arg2, long arg3, long arg4, Object arg5, int arg6, int arg7, ObjectParameterInfo arg8, Object arg9, int arg10, int arg11, ObjectParameterInfo arg12) {
        return Foreign.invokeN4O2(arg0.getContextAddress(), arg0.getFunctionAddress(), arg1, arg2, arg3, arg4, arg5, arg8.asObjectInfo(), arg6, arg7, arg9, arg12.asObjectInfo(), arg10, arg11);
    }

  public final long invokeN4O3rN(Function arg0, long arg1, long arg2, long arg3, long arg4, Object arg5, int arg6, int arg7, ObjectParameterInfo arg8, Object arg9, int arg10, int arg11, ObjectParameterInfo arg12, Object arg13, int arg14, int arg15, ObjectParameterInfo arg16) {
        return Foreign.invokeN4O3(arg0.getContextAddress(), arg0.getFunctionAddress(), arg1, arg2, arg3, arg4, arg5, arg8.asObjectInfo(), arg6, arg7, arg9, arg12.asObjectInfo(), arg10, arg11, arg13, arg16.asObjectInfo(), arg14, arg15);
    }

  public long invokeN5O1rN(Function arg0, long arg1, long arg2, long arg3, long arg4, long arg5, Object arg6, int arg7, int arg8, ObjectParameterInfo arg9) {
        return Foreign.invokeN5O1(arg0.getContextAddress(), arg0.getFunctionAddress(), arg1, arg2, arg3, arg4, arg5, arg6, arg9.asObjectInfo(), arg7, arg8);
    }

  public long invokeN5O2rN(Function arg0, long arg1, long arg2, long arg3, long arg4, long arg5, Object arg6, int arg7, int arg8, ObjectParameterInfo arg9, Object arg10, int arg11, int arg12, ObjectParameterInfo arg13) {
        return Foreign.invokeN5O2(arg0.getContextAddress(), arg0.getFunctionAddress(), arg1, arg2, arg3, arg4, arg5, arg6, arg9.asObjectInfo(), arg7, arg8, arg10, arg13.asObjectInfo(), arg11, arg12);
    }

  public final long invokeN5O3rN(Function arg0, long arg1, long arg2, long arg3, long arg4, long arg5, Object arg6, int arg7, int arg8, ObjectParameterInfo arg9, Object arg10, int arg11, int arg12, ObjectParameterInfo arg13, Object arg14, int arg15, int arg16, ObjectParameterInfo arg17) {
        return Foreign.invokeN5O3(arg0.getContextAddress(), arg0.getFunctionAddress(), arg1, arg2, arg3, arg4, arg5, arg6, arg9.asObjectInfo(), arg7, arg8, arg10, arg13.asObjectInfo(), arg11, arg12, arg14, arg17.asObjectInfo(), arg15, arg16);
    }

  public long invokeN6O1rN(Function arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, Object arg7, int arg8, int arg9, ObjectParameterInfo arg10) {
        return Foreign.invokeN6O1(arg0.getContextAddress(), arg0.getFunctionAddress(), arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg10.asObjectInfo(), arg8, arg9);
    }

  public long invokeN6O2rN(Function arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, Object arg7, int arg8, int arg9, ObjectParameterInfo arg10, Object arg11, int arg12, int arg13, ObjectParameterInfo arg14) {
        return Foreign.invokeN6O2(arg0.getContextAddress(), arg0.getFunctionAddress(), arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg10.asObjectInfo(), arg8, arg9, arg11, arg14.asObjectInfo(), arg12, arg13);
    }

  public final long invokeN6O3rN(Function arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, Object arg7, int arg8, int arg9, ObjectParameterInfo arg10, Object arg11, int arg12, int arg13, ObjectParameterInfo arg14, Object arg15, int arg16, int arg17, ObjectParameterInfo arg18) {
        return Foreign.invokeN6O3(arg0.getContextAddress(), arg0.getFunctionAddress(), arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg10.asObjectInfo(), arg8, arg9, arg11, arg14.asObjectInfo(), arg12, arg13, arg15, arg18.asObjectInfo(), arg16, arg17);
    }

}