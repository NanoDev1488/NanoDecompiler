// исходный (обфусцированный) внутренний класс: com.kenai.jffi.Function
package com.kenai.jffi;

import com.kenai.jffi.CallContext;
import com.kenai.jffi.CallingConvention;
import com.kenai.jffi.Type;

public final class Function {

    // ---- поля ----
  private final CallContext callContext;
  final long functionAddress;
  final long contextAddress;

  public Function(long arg0, Type arg1, Type[] arg2) { // было: <init>
        this(arg0, arg1, arg2, CallingConvention.DEFAULT, true);
    }

  public Function(long arg0, CallContext arg1) { // было: <init>
        super();
        functionAddress = arg0;
        callContext = arg1;
        contextAddress = arg1.getAddress();
    }

  public Function(long arg0, Type arg1, Type[] arg2, CallingConvention arg3) { // было: <init>
        this(arg0, arg1, arg2, arg3, true);
    }

  public Function(long arg0, Type arg1, Type[] arg2, CallingConvention arg3, boolean arg4) { // было: <init>
        super();
        functionAddress = arg0;
        callContext = CallContext.getCallContext(arg1, arg2, arg3, arg4);
        contextAddress = callContext.getAddress();
    }

  public Function(long arg0, Type arg1, int arg2, Type[] arg3, CallingConvention arg4, boolean arg5) { // было: <init>
        super();
        functionAddress = arg0;
        callContext = CallContext.getCallContext(arg1, arg2, arg3, arg4, arg5);
        contextAddress = callContext.getAddress();
    }

  public final int getParameterCount() {
        return callContext.getParameterCount();
    }

  public final int getRawParameterSize() {
        return callContext.getRawParameterSize();
    }

  public final CallContext getCallContext() {
        return callContext;
    }

  final long getContextAddress() {
        return contextAddress;
    }

  public final long getFunctionAddress() {
        return functionAddress;
    }

  public final Type getReturnType() {
        return callContext.getReturnType();
    }

  public final Type getParameterType(int arg0) {
        return callContext.getParameterType(arg0);
    }

    @Deprecated
  public final void dispose() {
        // (пустое тело)
    }

}