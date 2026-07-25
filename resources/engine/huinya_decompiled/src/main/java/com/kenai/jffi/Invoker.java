// исходный (обфусцированный) внутренний класс: com.kenai.jffi.Invoker
package com.kenai.jffi;

import com.kenai.jffi.CallContext;
import com.kenai.jffi.Foreign;
import com.kenai.jffi.Function;
import com.kenai.jffi.HeapInvocationBuffer;
import com.kenai.jffi.Invoker_Anon1;
import com.kenai.jffi.Invoker_SingletonHolder;
import com.kenai.jffi.ObjectBuffer;
import com.kenai.jffi.ObjectParameterInfo;
import com.kenai.jffi.ObjectParameterInvoker;
import com.kenai.jffi.ObjectParameterStrategy;
import com.kenai.jffi.Type;
import java.math.BigDecimal;

public abstract class Invoker {

    // ---- поля ----
  private final Foreign foreign;
  private final ObjectParameterInvoker objectParameterInvoker;

  public static Invoker getInstance() {
        return Invoker_SingletonHolder.access$200();
    }

  private Invoker() { // было: <init>
        this(Foreign.getInstance(), ObjectParameterInvoker.getInstance());
    }

   Invoker(Foreign arg0, ObjectParameterInvoker arg1) { // было: <init>
        super();
        foreign = arg0;
        objectParameterInvoker = arg1;
    }

  public final ObjectParameterInvoker getObjectParameterInvoker() {
        return objectParameterInvoker;
    }

  public final int invokeI0(CallContext arg0, long arg1) {
        return Foreign.invokeI0(arg0.contextAddress, arg1);
    }

  public final int invokeI1(CallContext arg0, long arg1, int arg2) {
        return Foreign.invokeI1(arg0.contextAddress, arg1, arg2);
    }

  public final int invokeI2(CallContext arg0, long arg1, int arg2, int arg3) {
        return Foreign.invokeI2(arg0.contextAddress, arg1, arg2, arg3);
    }

  public final int invokeI3(CallContext arg0, long arg1, int arg2, int arg3, int arg4) {
        return Foreign.invokeI3(arg0.contextAddress, arg1, arg2, arg3, arg4);
    }

  public final int invokeI4(CallContext arg0, long arg1, int arg2, int arg3, int arg4, int arg5) {
        return Foreign.invokeI4(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5);
    }

  public final int invokeI5(CallContext arg0, long arg1, int arg2, int arg3, int arg4, int arg5, int arg6) {
        return Foreign.invokeI5(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6);
    }

  public final int invokeI6(CallContext arg0, long arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7) {
        return Foreign.invokeI6(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
    }

  public final int invokeI0NoErrno(CallContext arg0, long arg1) {
        return Foreign.invokeI0NoErrno(arg0.contextAddress, arg1);
    }

  public final int invokeI1NoErrno(CallContext arg0, long arg1, int arg2) {
        return Foreign.invokeI1NoErrno(arg0.contextAddress, arg1, arg2);
    }

  public final int invokeI2NoErrno(CallContext arg0, long arg1, int arg2, int arg3) {
        return Foreign.invokeI2NoErrno(arg0.contextAddress, arg1, arg2, arg3);
    }

  public final int invokeI3NoErrno(CallContext arg0, long arg1, int arg2, int arg3, int arg4) {
        return Foreign.invokeI3NoErrno(arg0.contextAddress, arg1, arg2, arg3, arg4);
    }

  public final int invokeI4NoErrno(CallContext arg0, long arg1, int arg2, int arg3, int arg4, int arg5) {
        return Foreign.invokeI4NoErrno(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5);
    }

  public final int invokeI5NoErrno(CallContext arg0, long arg1, int arg2, int arg3, int arg4, int arg5, int arg6) {
        return Foreign.invokeI5NoErrno(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6);
    }

  public final int invokeI6NoErrno(CallContext arg0, long arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7) {
        return Foreign.invokeI6NoErrno(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
    }

    @Deprecated
  public final int invokeVrI(Function arg0) {
        return Foreign.invokeI0(arg0.contextAddress, arg0.functionAddress);
    }

    @Deprecated
  public final int invokeNoErrnoVrI(Function arg0) {
        return Foreign.invokeI0NoErrno(arg0.contextAddress, arg0.functionAddress);
    }

    @Deprecated
  public final int invokeIrI(Function arg0, int arg1) {
        return Foreign.invokeI1(arg0.contextAddress, arg0.functionAddress, arg1);
    }

    @Deprecated
  public final int invokeNoErrnoIrI(Function arg0, int arg1) {
        return Foreign.invokeI1NoErrno(arg0.contextAddress, arg0.functionAddress, arg1);
    }

    @Deprecated
  public final int invokeIIrI(Function arg0, int arg1, int arg2) {
        return Foreign.invokeI2(arg0.contextAddress, arg0.functionAddress, arg1, arg2);
    }

    @Deprecated
  public final int invokeNoErrnoIIrI(Function arg0, int arg1, int arg2) {
        return Foreign.invokeI2NoErrno(arg0.contextAddress, arg0.functionAddress, arg1, arg2);
    }

    @Deprecated
  public final int invokeIIIrI(Function arg0, int arg1, int arg2, int arg3) {
        return Foreign.invokeI3(arg0.contextAddress, arg0.functionAddress, arg1, arg2, arg3);
    }

    @Deprecated
  public final int invokeNoErrnoIIIrI(Function arg0, int arg1, int arg2, int arg3) {
        return Foreign.invokeI3NoErrno(arg0.contextAddress, arg0.functionAddress, arg1, arg2, arg3);
    }

    @Deprecated
  public final int invokeIIIIrI(Function arg0, int arg1, int arg2, int arg3, int arg4) {
        return Foreign.invokeI4(arg0.contextAddress, arg0.functionAddress, arg1, arg2, arg3, arg4);
    }

    @Deprecated
  public final int invokeIIIIIrI(Function arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
        return Foreign.invokeI5(arg0.contextAddress, arg0.functionAddress, arg1, arg2, arg3, arg4, arg5);
    }

    @Deprecated
  public final int invokeIIIIIIrI(Function arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6) {
        return Foreign.invokeI6(arg0.contextAddress, arg0.functionAddress, arg1, arg2, arg3, arg4, arg5, arg6);
    }

  public final long invokeL0(CallContext arg0, long arg1) {
        return Foreign.invokeL0(arg0.contextAddress, arg1);
    }

  public final long invokeL1(CallContext arg0, long arg1, long arg2) {
        return Foreign.invokeL1(arg0.contextAddress, arg1, arg2);
    }

  public final long invokeL2(CallContext arg0, long arg1, long arg2, long arg3) {
        return Foreign.invokeL2(arg0.contextAddress, arg1, arg2, arg3);
    }

  public final long invokeL3(CallContext arg0, long arg1, long arg2, long arg3, long arg4) {
        return Foreign.invokeL3(arg0.contextAddress, arg1, arg2, arg3, arg4);
    }

  public final long invokeL4(CallContext arg0, long arg1, long arg2, long arg3, long arg4, long arg5) {
        return Foreign.invokeL4(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5);
    }

  public final long invokeL5(CallContext arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6) {
        return Foreign.invokeL5(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6);
    }

  public final long invokeL6(CallContext arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, long arg7) {
        return Foreign.invokeL6(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
    }

  public final long invokeL0NoErrno(CallContext arg0, long arg1) {
        return Foreign.invokeL0NoErrno(arg0.contextAddress, arg1);
    }

  public final long invokeL1NoErrno(CallContext arg0, long arg1, long arg2) {
        return Foreign.invokeL1NoErrno(arg0.contextAddress, arg1, arg2);
    }

  public final long invokeL2NoErrno(CallContext arg0, long arg1, long arg2, long arg3) {
        return Foreign.invokeL2NoErrno(arg0.contextAddress, arg1, arg2, arg3);
    }

  public final long invokeL3NoErrno(CallContext arg0, long arg1, long arg2, long arg3, long arg4) {
        return Foreign.invokeL3NoErrno(arg0.contextAddress, arg1, arg2, arg3, arg4);
    }

  public final long invokeL4NoErrno(CallContext arg0, long arg1, long arg2, long arg3, long arg4, long arg5) {
        return Foreign.invokeL4NoErrno(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5);
    }

  public final long invokeL5NoErrno(CallContext arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6) {
        return Foreign.invokeL5NoErrno(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6);
    }

  public final long invokeL6NoErrno(CallContext arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, long arg7) {
        return Foreign.invokeL6NoErrno(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
    }

  public final long invokeVrL(Function arg0) {
        return Foreign.invokeL0(arg0.contextAddress, arg0.functionAddress);
    }

  public final long invokeLrL(Function arg0, long arg1) {
        return Foreign.invokeL1(arg0.contextAddress, arg0.functionAddress, arg1);
    }

  public final long invokeLLrL(Function arg0, long arg1, long arg2) {
        return Foreign.invokeL2(arg0.contextAddress, arg0.functionAddress, arg1, arg2);
    }

  public final long invokeLLLrL(Function arg0, long arg1, long arg2, long arg3) {
        return Foreign.invokeL3(arg0.contextAddress, arg0.functionAddress, arg1, arg2, arg3);
    }

  public final long invokeLLLLrL(Function arg0, long arg1, long arg2, long arg3, long arg4) {
        return Foreign.invokeL4(arg0.contextAddress, arg0.functionAddress, arg1, arg2, arg3, arg4);
    }

  public final long invokeLLLLLrL(Function arg0, long arg1, long arg2, long arg3, long arg4, long arg5) {
        return Foreign.invokeL5(arg0.contextAddress, arg0.functionAddress, arg1, arg2, arg3, arg4, arg5);
    }

  public final long invokeLLLLLLrL(Function arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6) {
        return Foreign.invokeL6(arg0.contextAddress, arg0.functionAddress, arg1, arg2, arg3, arg4, arg5, arg6);
    }

  public final long invokeN0(CallContext arg0, long arg1) {
        return Foreign.invokeN0(arg0.contextAddress, arg1);
    }

  public final long invokeN1(CallContext arg0, long arg1, long arg2) {
        return Foreign.invokeN1(arg0.contextAddress, arg1, arg2);
    }

  public final long invokeN2(CallContext arg0, long arg1, long arg2, long arg3) {
        return Foreign.invokeN2(arg0.contextAddress, arg1, arg2, arg3);
    }

  public final long invokeN3(CallContext arg0, long arg1, long arg2, long arg3, long arg4) {
        return Foreign.invokeN3(arg0.contextAddress, arg1, arg2, arg3, arg4);
    }

  public final long invokeN4(CallContext arg0, long arg1, long arg2, long arg3, long arg4, long arg5) {
        return Foreign.invokeN4(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5);
    }

  public final long invokeN5(CallContext arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6) {
        return Foreign.invokeN5(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6);
    }

  public final long invokeN6(CallContext arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, long arg7) {
        return Foreign.invokeN6(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
    }

  public final long invokeVrN(Function arg0) {
        return Foreign.invokeN0(arg0.contextAddress, arg0.functionAddress);
    }

  public final long invokeNrN(Function arg0, long arg1) {
        return Foreign.invokeN1(arg0.contextAddress, arg0.functionAddress, arg1);
    }

  public final long invokeNNrN(Function arg0, long arg1, long arg2) {
        return Foreign.invokeN2(arg0.contextAddress, arg0.functionAddress, arg1, arg2);
    }

  public final long invokeNNNrN(Function arg0, long arg1, long arg2, long arg3) {
        return Foreign.invokeN3(arg0.contextAddress, arg0.functionAddress, arg1, arg2, arg3);
    }

  public final long invokeNNNNrN(Function arg0, long arg1, long arg2, long arg3, long arg4) {
        return Foreign.invokeN4(arg0.contextAddress, arg0.functionAddress, arg1, arg2, arg3, arg4);
    }

  public final long invokeNNNNNrN(Function arg0, long arg1, long arg2, long arg3, long arg4, long arg5) {
        return Foreign.invokeN5(arg0.contextAddress, arg0.functionAddress, arg1, arg2, arg3, arg4, arg5);
    }

  public final long invokeNNNNNNrN(Function arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6) {
        return Foreign.invokeN6(arg0.contextAddress, arg0.functionAddress, arg1, arg2, arg3, arg4, arg5, arg6);
    }

    @Deprecated
  public final long invokeNNO1rN(Function arg0, long arg1, long arg2, Object arg3, int arg4, int arg5, ObjectParameterInfo arg6) {
        return Foreign.invokeN2O1(arg0.contextAddress, arg0.functionAddress, arg1, arg2, arg3, arg6.asObjectInfo(), arg4, arg5);
    }

    @Deprecated
  public final long invokeNNO2rN(Function arg0, long arg1, long arg2, Object arg3, int arg4, int arg5, ObjectParameterInfo arg6, Object arg7, int arg8, int arg9, ObjectParameterInfo arg10) {
        return Foreign.invokeN2O2(arg0.contextAddress, arg0.functionAddress, arg1, arg2, arg3, arg6.asObjectInfo(), arg4, arg5, arg7, arg10.asObjectInfo(), arg8, arg9);
    }

    @Deprecated
  public final long invokeNNNO1rN(Function arg0, long arg1, long arg2, long arg3, Object arg4, int arg5, int arg6, ObjectParameterInfo arg7) {
        return Foreign.invokeN3O1(arg0.contextAddress, arg0.functionAddress, arg1, arg2, arg3, arg4, arg7.asObjectInfo(), arg5, arg6);
    }

    @Deprecated
  public final long invokeNNNO2rN(Function arg0, long arg1, long arg2, long arg3, Object arg4, int arg5, int arg6, ObjectParameterInfo arg7, Object arg8, int arg9, int arg10, ObjectParameterInfo arg11) {
        return Foreign.invokeN3O2(arg0.contextAddress, arg0.functionAddress, arg1, arg2, arg3, arg4, arg7.asObjectInfo(), arg5, arg6, arg8, arg11.asObjectInfo(), arg9, arg10);
    }

  private static RuntimeException newObjectCountError(int arg0) {
        return new RuntimeException(new StringBuilder().append("invalid object count: ").append(arg0).toString());
    }

  private static RuntimeException newInsufficientObjectCountError(int arg0) {
        return new RuntimeException(new StringBuilder().append("invalid object count: ").append(arg0).toString());
    }

  private static RuntimeException newHeapObjectCountError(int arg0) {
        return new RuntimeException(new StringBuilder().append("insufficient number of heap objects supplied (").append(arg0).append(" required)").toString());
    }

  public final long invokeN1O1(CallContext arg0, long arg1, long arg2, Object arg3, ObjectParameterStrategy arg4, ObjectParameterInfo arg5) {
        return Foreign.invokeN1O1(arg0.contextAddress, arg1, arg2, arg4.object(arg3), arg4.objectInfo(arg5), arg4.offset(arg3), arg4.length(arg3));
    }

  public final long invokeN2O1(CallContext arg0, long arg1, long arg2, long arg3, Object arg4, ObjectParameterStrategy arg5, ObjectParameterInfo arg6) {
        return Foreign.invokeN2O1(arg0.contextAddress, arg1, arg2, arg3, arg5.object(arg4), arg5.objectInfo(arg6), arg5.offset(arg4), arg5.length(arg4));
    }

  public final long invokeN2O2(CallContext arg0, long arg1, long arg2, long arg3, Object arg4, ObjectParameterStrategy arg5, ObjectParameterInfo arg6, Object arg7, ObjectParameterStrategy arg8, ObjectParameterInfo arg9) {
        return Foreign.invokeN2O2(arg0.contextAddress, arg1, arg2, arg3, arg5.object(arg4), arg5.objectInfo(arg6), arg5.offset(arg4), arg5.length(arg4), arg8.object(arg7), arg8.objectInfo(arg9), arg8.offset(arg7), arg8.length(arg7));
    }

  public final long invokeN3O1(CallContext arg0, long arg1, long arg2, long arg3, long arg4, Object arg5, ObjectParameterStrategy arg6, ObjectParameterInfo arg7) {
        return Foreign.invokeN3O1(arg0.contextAddress, arg1, arg2, arg3, arg4, arg6.object(arg5), arg6.objectInfo(arg7), arg6.offset(arg5), arg6.length(arg5));
    }

  public final long invokeN3O2(CallContext arg0, long arg1, long arg2, long arg3, long arg4, Object arg5, ObjectParameterStrategy arg6, ObjectParameterInfo arg7, Object arg8, ObjectParameterStrategy arg9, ObjectParameterInfo arg10) {
        return Foreign.invokeN3O2(arg0.contextAddress, arg1, arg2, arg3, arg4, arg6.object(arg5), arg6.objectInfo(arg7), arg6.offset(arg5), arg6.length(arg5), arg9.object(arg8), arg9.objectInfo(arg10), arg9.offset(arg8), arg9.length(arg8));
    }

  public final long invokeN3O3(CallContext arg0, long arg1, long arg2, long arg3, long arg4, Object arg5, ObjectParameterStrategy arg6, ObjectParameterInfo arg7, Object arg8, ObjectParameterStrategy arg9, ObjectParameterInfo arg10, Object arg11, ObjectParameterStrategy arg12, ObjectParameterInfo arg13) {
        return Foreign.invokeN3O3(arg0.contextAddress, arg1, arg2, arg3, arg4, arg6.object(arg5), arg6.objectInfo(arg7), arg6.offset(arg5), arg6.length(arg5), arg9.object(arg8), arg9.objectInfo(arg10), arg9.offset(arg8), arg9.length(arg8), arg12.object(arg11), arg12.objectInfo(arg13), arg12.offset(arg11), arg12.length(arg11));
    }

  public final long invokeN4O1(CallContext arg0, long arg1, long arg2, long arg3, long arg4, long arg5, Object arg6, ObjectParameterStrategy arg7, ObjectParameterInfo arg8) {
        return Foreign.invokeN4O1(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg7.object(arg6), arg7.objectInfo(arg8), arg7.offset(arg6), arg7.length(arg6));
    }

  public final long invokeN4O2(CallContext arg0, long arg1, long arg2, long arg3, long arg4, long arg5, Object arg6, ObjectParameterStrategy arg7, ObjectParameterInfo arg8, Object arg9, ObjectParameterStrategy arg10, ObjectParameterInfo arg11) {
        return Foreign.invokeN4O2(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg7.object(arg6), arg7.objectInfo(arg8), arg7.offset(arg6), arg7.length(arg6), arg10.object(arg9), arg10.objectInfo(arg11), arg10.offset(arg9), arg10.length(arg9));
    }

  public final long invokeN4O3(CallContext arg0, long arg1, long arg2, long arg3, long arg4, long arg5, Object arg6, ObjectParameterStrategy arg7, ObjectParameterInfo arg8, Object arg9, ObjectParameterStrategy arg10, ObjectParameterInfo arg11, Object arg12, ObjectParameterStrategy arg13, ObjectParameterInfo arg14) {
        return Foreign.invokeN4O3(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg7.object(arg6), arg7.objectInfo(arg8), arg7.offset(arg6), arg7.length(arg6), arg10.object(arg9), arg10.objectInfo(arg11), arg10.offset(arg9), arg10.length(arg9), arg13.object(arg12), arg13.objectInfo(arg14), arg13.offset(arg12), arg13.length(arg12));
    }

  public final long invokeN5O1(CallContext arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, Object arg7, ObjectParameterStrategy arg8, ObjectParameterInfo arg9) {
        return Foreign.invokeN5O1(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg8.object(arg7), arg8.objectInfo(arg9), arg8.offset(arg7), arg8.length(arg7));
    }

  public final long invokeN5O2(CallContext arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, Object arg7, ObjectParameterStrategy arg8, ObjectParameterInfo arg9, Object arg10, ObjectParameterStrategy arg11, ObjectParameterInfo arg12) {
        return Foreign.invokeN5O2(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg8.object(arg7), arg8.objectInfo(arg9), arg8.offset(arg7), arg8.length(arg7), arg11.object(arg10), arg11.objectInfo(arg12), arg11.offset(arg10), arg11.length(arg10));
    }

  public final long invokeN5O3(CallContext arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, Object arg7, ObjectParameterStrategy arg8, ObjectParameterInfo arg9, Object arg10, ObjectParameterStrategy arg11, ObjectParameterInfo arg12, Object arg13, ObjectParameterStrategy arg14, ObjectParameterInfo arg15) {
        return Foreign.invokeN5O3(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg8.object(arg7), arg8.objectInfo(arg9), arg8.offset(arg7), arg8.length(arg7), arg11.object(arg10), arg11.objectInfo(arg12), arg11.offset(arg10), arg11.length(arg10), arg14.object(arg13), arg14.objectInfo(arg15), arg14.offset(arg13), arg14.length(arg13));
    }

  public final long invokeN6O1(CallContext arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, long arg7, Object arg8, ObjectParameterStrategy arg9, ObjectParameterInfo arg10) {
        return Foreign.invokeN6O1(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg9.object(arg8), arg9.objectInfo(arg10), arg9.offset(arg8), arg9.length(arg8));
    }

  public final long invokeN6O2(CallContext arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, long arg7, Object arg8, ObjectParameterStrategy arg9, ObjectParameterInfo arg10, Object arg11, ObjectParameterStrategy arg12, ObjectParameterInfo arg13) {
        return Foreign.invokeN6O2(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg9.object(arg8), arg9.objectInfo(arg10), arg9.offset(arg8), arg9.length(arg8), arg12.object(arg11), arg12.objectInfo(arg13), arg12.offset(arg11), arg12.length(arg11));
    }

  public final long invokeN6O3(CallContext arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, long arg7, Object arg8, ObjectParameterStrategy arg9, ObjectParameterInfo arg10, Object arg11, ObjectParameterStrategy arg12, ObjectParameterInfo arg13, Object arg14, ObjectParameterStrategy arg15, ObjectParameterInfo arg16) {
        return Foreign.invokeN6O3(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg9.object(arg8), arg9.objectInfo(arg10), arg9.offset(arg8), arg9.length(arg8), arg12.object(arg11), arg12.objectInfo(arg13), arg12.offset(arg11), arg12.length(arg11), arg15.object(arg14), arg15.objectInfo(arg16), arg15.offset(arg14), arg15.length(arg14));
    }

  public final long invokeN1(CallContext arg0, long arg1, long arg2, int arg3, Object arg4, ObjectParameterStrategy arg5, ObjectParameterInfo arg6) {
        if (arg3 != 0) {
            if (arg3 != 1) {
                throw newObjectCountError(arg3);
            } else {
                return Foreign.invokeN1O1(arg0.contextAddress, arg1, arg2, arg5.object(arg4), arg5.objectInfo(arg6), arg5.offset(arg4), arg5.length(arg4));
            }
        } else {
            return Foreign.invokeN1(arg0.contextAddress, arg1, arg2);
        }
    }

  public final long invokeN2(CallContext arg0, long arg1, long arg2, long arg3, int arg4, Object arg5, ObjectParameterStrategy arg6, ObjectParameterInfo arg7) {
        if (arg4 != 0) {
            if (arg4 != 1) {
                throw newObjectCountError(arg4);
            } else {
                return Foreign.invokeN2O1(arg0.contextAddress, arg1, arg2, arg3, arg6.object(arg5), arg6.objectInfo(arg7), arg6.offset(arg5), arg6.length(arg5));
            }
        } else {
            return Foreign.invokeN2(arg0.contextAddress, arg1, arg2, arg3);
        }
    }

  public final long invokeN2(CallContext arg0, long arg1, long arg2, long arg3, int arg4, Object arg5, ObjectParameterStrategy arg6, ObjectParameterInfo arg7, Object arg8, ObjectParameterStrategy arg9, ObjectParameterInfo arg10) {
        if (arg4 != 0) {
            if (arg4 != 1) {
                if (arg4 != 2) {
                    throw newObjectCountError(arg4);
                } else {
                    return Foreign.invokeN2O2(arg0.contextAddress, arg1, arg2, arg3, arg6.object(arg5), arg6.objectInfo(arg7), arg6.offset(arg5), arg6.length(arg5), arg9.object(arg8), arg9.objectInfo(arg10), arg9.offset(arg8), arg9.length(arg8));
                }
            } else {
                if (arg6.isDirect()) {
                    if (!arg9.isDirect()) {
                        arg5 = arg8;
                        arg6 = arg9;
                        arg7 = arg10;
                    }
                }
                return Foreign.invokeN2O1(arg0.contextAddress, arg1, arg2, arg3, arg6.object(arg5), arg6.objectInfo(arg7), arg6.offset(arg5), arg6.length(arg5));
            }
        } else {
            return Foreign.invokeN2(arg0.contextAddress, arg1, arg2, arg3);
        }
    }

  public final long invokeN3(CallContext arg0, long arg1, long arg2, long arg3, long arg4, int arg5, Object arg6, ObjectParameterStrategy arg7, ObjectParameterInfo arg8) {
        if (arg5 != 0) {
            if (arg5 != 1) {
                throw newObjectCountError(arg5);
            } else {
                if (!arg7.isDirect()) {
                    return Foreign.invokeN3O1(arg0.contextAddress, arg1, arg2, arg3, arg4, arg7.object(arg6), arg7.objectInfo(arg8), arg7.offset(arg6), arg7.length(arg6));
                } else {
                    throw newInsufficientObjectCountError(arg5);
                }
            }
        } else {
            return Foreign.invokeN3(arg0.contextAddress, arg1, arg2, arg3, arg4);
        }
    }

  public final long invokeN3(CallContext arg0, long arg1, long arg2, long arg3, long arg4, int arg5, Object arg6, ObjectParameterStrategy arg7, ObjectParameterInfo arg8, Object arg9, ObjectParameterStrategy arg10, ObjectParameterInfo arg11) {
        if (arg5 != 0) {
            if (arg5 != 1) {
                if (arg5 != 2) {
                    throw newObjectCountError(arg5);
                } else {
                    return Foreign.invokeN3O2(arg0.contextAddress, arg1, arg2, arg3, arg4, arg7.object(arg6), arg7.objectInfo(arg8), arg7.offset(arg6), arg7.length(arg6), arg10.object(arg9), arg10.objectInfo(arg11), arg10.offset(arg9), arg10.length(arg9));
                }
            } else {
                if (arg7.isDirect()) {
                    if (!arg10.isDirect()) {
                        arg6 = arg9;
                        arg7 = arg10;
                        arg8 = arg11;
                    }
                }
                return Foreign.invokeN3O1(arg0.contextAddress, arg1, arg2, arg3, arg4, arg7.object(arg6), arg7.objectInfo(arg8), arg7.offset(arg6), arg7.length(arg6));
            }
        } else {
            return Foreign.invokeN3(arg0.contextAddress, arg1, arg2, arg3, arg4);
        }
    }

  public final long invokeN3(CallContext arg0, long arg1, long arg2, long arg3, long arg4, int arg5, Object arg6, ObjectParameterStrategy arg7, ObjectParameterInfo arg8, Object arg9, ObjectParameterStrategy arg10, ObjectParameterInfo arg11, Object arg12, ObjectParameterStrategy arg13, ObjectParameterInfo arg14) {
        if (arg5 != 0) {
            if (arg5 >= 3) {
                return Foreign.invokeN3O3(arg0.contextAddress, arg1, arg2, arg3, arg4, arg7.object(arg6), arg7.objectInfo(arg8), arg7.offset(arg6), arg7.length(arg6), arg10.object(arg9), arg10.objectInfo(arg11), arg10.offset(arg9), arg10.length(arg9), arg13.object(arg12), arg13.objectInfo(arg14), arg13.offset(arg12), arg13.length(arg12));
            } else {
                if (arg7.isDirect()) {
                    if (arg10.isDirect()) {
                        arg6 = arg12;
                        arg7 = arg13;
                        arg8 = arg14;
                        int var20 = 4;
                    } else {
                        arg6 = arg9;
                        arg7 = arg10;
                        arg8 = arg11;
                        int var20 = 3;
                    }
                } else {
                    int var20 = 2;
                }
                if (arg5 != 1) {
                    if (arg5 != 2) {
                        throw newObjectCountError(arg5);
                    } else {
                        if (var20 > 2) {
                            if (var20 <= 3) {
                                arg9 = arg12;
                                arg10 = arg13;
                                arg11 = arg14;
                            }
                        } else {
                            if (arg10.isDirect()) {
                                if (var20 <= 3) {
                                    arg9 = arg12;
                                    arg10 = arg13;
                                    arg11 = arg14;
                                }
                            }
                        }
                        return Foreign.invokeN3O2(arg0.contextAddress, arg1, arg2, arg3, arg4, arg7.object(arg6), arg7.objectInfo(arg8), arg7.offset(arg6), arg7.length(arg6), arg10.object(arg9), arg10.objectInfo(arg11), arg10.offset(arg9), arg10.length(arg9));
                    }
                } else {
                    return Foreign.invokeN3O1(arg0.contextAddress, arg1, arg2, arg3, arg4, arg7.object(arg6), arg7.objectInfo(arg8), arg7.offset(arg6), arg7.length(arg6));
                }
            }
        } else {
            return Foreign.invokeN3(arg0.contextAddress, arg1, arg2, arg3, arg4);
        }
    }

  public final long invokeN4(CallContext arg0, long arg1, long arg2, long arg3, long arg4, long arg5, int arg6, Object arg7, ObjectParameterStrategy arg8, ObjectParameterInfo arg9) {
        if (arg6 != 0) {
            if (arg6 != 1) {
                throw newObjectCountError(arg6);
            } else {
                return Foreign.invokeN4O1(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg8.object(arg7), arg8.objectInfo(arg9), arg8.offset(arg7), arg8.length(arg7));
            }
        } else {
            return Foreign.invokeN4(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5);
        }
    }

  public final long invokeN4(CallContext arg0, long arg1, long arg2, long arg3, long arg4, long arg5, int arg6, Object arg7, ObjectParameterStrategy arg8, ObjectParameterInfo arg9, Object arg10, ObjectParameterStrategy arg11, ObjectParameterInfo arg12) {
        if (arg6 != 0) {
            if (arg6 != 1) {
                if (arg6 != 2) {
                    throw newObjectCountError(arg6);
                } else {
                    return Foreign.invokeN4O2(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg8.object(arg7), arg8.objectInfo(arg9), arg8.offset(arg7), arg8.length(arg7), arg11.object(arg10), arg11.objectInfo(arg12), arg11.offset(arg10), arg11.length(arg10));
                }
            } else {
                if (arg8.isDirect()) {
                    if (!arg11.isDirect()) {
                        arg7 = arg10;
                        arg8 = arg11;
                        arg9 = arg12;
                    }
                }
                return Foreign.invokeN4O1(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg8.object(arg7), arg8.objectInfo(arg9), arg8.offset(arg7), arg8.length(arg7));
            }
        } else {
            return Foreign.invokeN4(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5);
        }
    }

  public final long invokeN4(CallContext arg0, long arg1, long arg2, long arg3, long arg4, long arg5, int arg6, Object arg7, ObjectParameterStrategy arg8, ObjectParameterInfo arg9, Object arg10, ObjectParameterStrategy arg11, ObjectParameterInfo arg12, Object arg13, ObjectParameterStrategy arg14, ObjectParameterInfo arg15) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: iload  12
        //      2: ifne  22 (offset +20)
        //      5: aload_1
        //      6: getfield  #22 // com.kenai.jffi.CallContext.contextAddress:J
        //      9: lload_2
        //     10: lload  4
        //     12: lload  6
        //     14: lload  8
        //     16: lload  10
        //     18: invokestatic  #82 // com.kenai.jffi.Foreign.invokeN4:(JJJJJJ)J
        //     21: lreturn
        //     22: iconst_1
        //     23: istore  22
        //     25: iload  22
        //     27: tableswitch  default->115, 1->52, 2->66, 3->92
        //     52: iinc  22, 1
        //     55: aload  14
        //     57: invokevirtual  #126 // com.kenai.jffi.ObjectParameterStrategy.isDirect:()Z
        //     60: ifne  66 (offset +6)
        //     63: goto  115 (offset +52)
        //     66: iinc  22, 1
        //     69: aload  17
        //     71: invokevirtual  #126 // com.kenai.jffi.ObjectParameterStrategy.isDirect:()Z
        //     74: ifne  92 (offset +18)
        //     77: aload  16
        //     79: astore  13
        //     81: aload  17
        //     83: astore  14
        //     85: aload  18
        //     87: astore  15
        //     89: goto  115 (offset +26)
        //     92: iinc  22, 1
        //     95: aload  20
        //     97: invokevirtual  #126 // com.kenai.jffi.ObjectParameterStrategy.isDirect:()Z
        //    100: ifne  115 (offset +15)
        //    103: aload  19
        //    105: astore  13
        //    107: aload  20
        //    109: astore  14
        //    111: aload  21
        //    113: astore  15
        //    115: iload  12
        //    117: iconst_1
        //    118: if_icmpne  166 (offset +48)
        //    121: aload_1
        //    122: getfield  #22 // com.kenai.jffi.CallContext.contextAddress:J
        //    125: lload_2
        //    126: lload  4
        //    128: lload  6
        //    130: lload  8
        //    132: lload  10
        //    134: aload  14
        //    136: aload  13
        //    138: invokevirtual  #128 // com.kenai.jffi.ObjectParameterStrategy.object:(Ljava/lang/Object;)Ljava/lang/Object;
        //    141: aload  14
        //    143: aload  15
        //    145: invokevirtual  #129 // com.kenai.jffi.ObjectParameterStrategy.objectInfo:(Lcom/kenai/jffi/ObjectParameterInfo;)I
        //    148: aload  14
        //    150: aload  13
        //    152: invokevirtual  #130 // com.kenai.jffi.ObjectParameterStrategy.offset:(Ljava/lang/Object;)I
        //    155: aload  14
        //    157: aload  13
        //    159: invokevirtual  #127 // com.kenai.jffi.ObjectParameterStrategy.length:(Ljava/lang/Object;)I
        //    162: invokestatic  #83 // com.kenai.jffi.Foreign.invokeN4O1:(JJJJJJLjava/lang/Object;III)J
        //    165: lreturn
        //    166: iload  22
        //    168: lookupswitch  default->233, 2->196, 3->210
        //    196: iinc  22, 1
        //    199: aload  17
        //    201: invokevirtual  #126 // com.kenai.jffi.ObjectParameterStrategy.isDirect:()Z
        //    204: ifne  210 (offset +6)
        //    207: goto  233 (offset +26)
        //    210: iinc  22, 1
        //    213: aload  20
        //    215: invokevirtual  #126 // com.kenai.jffi.ObjectParameterStrategy.isDirect:()Z
        //    218: ifne  233 (offset +15)
        //    221: aload  19
        //    223: astore  16
        //    225: aload  20
        //    227: astore  17
        //    229: aload  21
        //    231: astore  18
        //    233: iload  12
        //    235: iconst_2
        //    236: if_icmpne  312 (offset +76)
        //    239: aload_1
        //    240: getfield  #22 // com.kenai.jffi.CallContext.contextAddress:J
        //    243: lload_2
        //    244: lload  4
        //    246: lload  6
        //    248: lload  8
        //    250: lload  10
        //    252: aload  14
        //    254: aload  13
        //    256: invokevirtual  #128 // com.kenai.jffi.ObjectParameterStrategy.object:(Ljava/lang/Object;)Ljava/lang/Object;
        //    259: aload  14
        //    261: aload  15
        //    263: invokevirtual  #129 // com.kenai.jffi.ObjectParameterStrategy.objectInfo:(Lcom/kenai/jffi/ObjectParameterInfo;)I
        //    266: aload  14
        //    268: aload  13
        //    270: invokevirtual  #130 // com.kenai.jffi.ObjectParameterStrategy.offset:(Ljava/lang/Object;)I
        //    273: aload  14
        //    275: aload  13
        //    277: invokevirtual  #127 // com.kenai.jffi.ObjectParameterStrategy.length:(Ljava/lang/Object;)I
        //    280: aload  17
        //    282: aload  16
        //    284: invokevirtual  #128 // com.kenai.jffi.ObjectParameterStrategy.object:(Ljava/lang/Object;)Ljava/lang/Object;
        //    287: aload  17
        //    289: aload  18
        //    291: invokevirtual  #129 // com.kenai.jffi.ObjectParameterStrategy.objectInfo:(Lcom/kenai/jffi/ObjectParameterInfo;)I
        //    294: aload  17
        //    296: aload  16
        //    298: invokevirtual  #130 // com.kenai.jffi.ObjectParameterStrategy.offset:(Ljava/lang/Object;)I
        //    301: aload  17
        //    303: aload  16
        //    305: invokevirtual  #127 // com.kenai.jffi.ObjectParameterStrategy.length:(Ljava/lang/Object;)I
        //    308: invokestatic  #84 // com.kenai.jffi.Foreign.invokeN4O2:(JJJJJJLjava/lang/Object;IIILjava/lang/Object;III)J
        //    311: lreturn
        //    312: iload  22
        //    314: lookupswitch  default->343, 3->332
        //    332: aload  20
        //    334: invokevirtual  #126 // com.kenai.jffi.ObjectParameterStrategy.isDirect:()Z
        //    337: ifne  343 (offset +6)
        //    340: goto  349 (offset +9)
        //    343: iload  12
        //    345: invokestatic  #118 // com.kenai.jffi.Invoker.newInsufficientObjectCountError:(I)Ljava/lang/RuntimeException;
        //    348: athrow
        //    349: iload  12
        //    351: iconst_3
        //    352: if_icmpne  456 (offset +104)
        //    355: aload_1
        //    356: getfield  #22 // com.kenai.jffi.CallContext.contextAddress:J
        //    359: lload_2
        //    360: lload  4
        //    362: lload  6
        //    364: lload  8
        //    366: lload  10
        //    368: aload  14
        //    370: aload  13
        //    372: invokevirtual  #128 // com.kenai.jffi.ObjectParameterStrategy.object:(Ljava/lang/Object;)Ljava/lang/Object;
        //    375: aload  14
        //    377: aload  15
        //    379: invokevirtual  #129 // com.kenai.jffi.ObjectParameterStrategy.objectInfo:(Lcom/kenai/jffi/ObjectParameterInfo;)I
        //    382: aload  14
        //    384: aload  13
        //    386: invokevirtual  #130 // com.kenai.jffi.ObjectParameterStrategy.offset:(Ljava/lang/Object;)I
        //    389: aload  14
        //    391: aload  13
        //    393: invokevirtual  #127 // com.kenai.jffi.ObjectParameterStrategy.length:(Ljava/lang/Object;)I
        //    396: aload  17
        //    398: aload  16
        //    400: invokevirtual  #128 // com.kenai.jffi.ObjectParameterStrategy.object:(Ljava/lang/Object;)Ljava/lang/Object;
        //    403: aload  17
        //    405: aload  18
        //    407: invokevirtual  #129 // com.kenai.jffi.ObjectParameterStrategy.objectInfo:(Lcom/kenai/jffi/ObjectParameterInfo;)I
        //    410: aload  17
        //    412: aload  16
        //    414: invokevirtual  #130 // com.kenai.jffi.ObjectParameterStrategy.offset:(Ljava/lang/Object;)I
        //    417: aload  17
        //    419: aload  16
        //    421: invokevirtual  #127 // com.kenai.jffi.ObjectParameterStrategy.length:(Ljava/lang/Object;)I
        //    424: aload  20
        //    426: aload  19
        //    428: invokevirtual  #128 // com.kenai.jffi.ObjectParameterStrategy.object:(Ljava/lang/Object;)Ljava/lang/Object;
        //    431: aload  20
        //    433: aload  21
        //    435: invokevirtual  #129 // com.kenai.jffi.ObjectParameterStrategy.objectInfo:(Lcom/kenai/jffi/ObjectParameterInfo;)I
        //    438: aload  20
        //    440: aload  19
        //    442: invokevirtual  #130 // com.kenai.jffi.ObjectParameterStrategy.offset:(Ljava/lang/Object;)I
        //    445: aload  20
        //    447: aload  19
        //    449: invokevirtual  #127 // com.kenai.jffi.ObjectParameterStrategy.length:(Ljava/lang/Object;)I
        //    452: invokestatic  #85 // com.kenai.jffi.Foreign.invokeN4O3:(JJJJJJLjava/lang/Object;IIILjava/lang/Object;IIILjava/lang/Object;III)J
        //    455: lreturn
        //    456: iload  12
        //    458: invokestatic  #119 // com.kenai.jffi.Invoker.newObjectCountError:(I)Ljava/lang/RuntimeException;
        //    461: athrow
    }

  public final long invokeN4(CallContext arg0, long arg1, long arg2, long arg3, long arg4, long arg5, int arg6, Object arg7, ObjectParameterStrategy arg8, ObjectParameterInfo arg9, Object arg10, ObjectParameterStrategy arg11, ObjectParameterInfo arg12, Object arg13, ObjectParameterStrategy arg14, ObjectParameterInfo arg15, Object arg16, ObjectParameterStrategy arg17, ObjectParameterInfo arg18) {
        if (arg6 != 0) {
            int var25 = 1;
            switch (var25) {
                case 1:
                    ++var25;
                    if (!arg8.isDirect()) {
                        break;
                    }
                case 2:
                    ++var25;
                    if (!arg11.isDirect()) {
                        arg7 = arg10;
                        arg8 = arg11;
                        arg9 = arg12;
                        break;
                    }
                case 3:
                    ++var25;
                    if (!arg14.isDirect()) {
                        arg7 = arg13;
                        arg8 = arg14;
                        arg9 = arg15;
                        break;
                    }
                case 4:
                    ++var25;
                    if (arg17.isDirect()) {
                        break;
                    }
                    arg7 = arg16;
                    arg8 = arg17;
                    arg9 = arg18;
                default:
            }
        } else {
            return Foreign.invokeN4(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5);
        }
        if (arg6 != 1) {
            switch (var25) {
                case 2:
                    ++var25;
                    if (!arg11.isDirect()) {
                        break;
                    }
                case 3:
                    ++var25;
                    if (!arg14.isDirect()) {
                        arg10 = arg13;
                        arg11 = arg14;
                        arg12 = arg15;
                        break;
                    }
                case 4:
                    ++var25;
                    if (arg17.isDirect()) {
                        break;
                    }
                    arg10 = arg16;
                    arg11 = arg17;
                    arg12 = arg18;
                default:
            }
        } else {
            return Foreign.invokeN4O1(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg8.object(arg7), arg8.objectInfo(arg9), arg8.offset(arg7), arg8.length(arg7));
        }
        if (arg6 != 2) {
            switch (var25) {
                case 3:
                    ++var25;
                    if (!arg14.isDirect()) {
                        break;
                    }
                case 4:
                    ++var25;
                    if (arg17.isDirect()) {
                        break;
                    }
                    arg13 = arg16;
                    arg14 = arg17;
                    arg15 = arg18;
                default:
            }
        } else {
            return Foreign.invokeN4O2(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg8.object(arg7), arg8.objectInfo(arg9), arg8.offset(arg7), arg8.length(arg7), arg11.object(arg10), arg11.objectInfo(arg12), arg11.offset(arg10), arg11.length(arg10));
        }
        if (arg6 != 3) {
            if (var25 != 4) {
                throw newInsufficientObjectCountError(arg6);
            } else {
                if (!arg17.isDirect()) {
                    if (arg6 != 4) {
                        throw newObjectCountError(arg6);
                    } else {
                        return Foreign.invokeN4O4(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg8.object(arg7), arg8.objectInfo(arg9), arg8.offset(arg7), arg8.length(arg7), arg11.object(arg10), arg11.objectInfo(arg12), arg11.offset(arg10), arg11.length(arg10), arg14.object(arg13), arg14.objectInfo(arg15), arg14.offset(arg13), arg14.length(arg13), arg17.object(arg16), arg17.objectInfo(arg18), arg17.offset(arg16), arg17.length(arg16));
                    }
                } else {
                    throw newInsufficientObjectCountError(arg6);
                }
            }
        } else {
            return Foreign.invokeN4O3(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg8.object(arg7), arg8.objectInfo(arg9), arg8.offset(arg7), arg8.length(arg7), arg11.object(arg10), arg11.objectInfo(arg12), arg11.offset(arg10), arg11.length(arg10), arg14.object(arg13), arg14.objectInfo(arg15), arg14.offset(arg13), arg14.length(arg13));
        }
    }

  public final long invokeN5(CallContext arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, int arg7, Object arg8, ObjectParameterStrategy arg9, ObjectParameterInfo arg10) {
        if (arg7 != 0) {
            if (arg7 != 1) {
                throw newObjectCountError(arg7);
            } else {
                return Foreign.invokeN5O1(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg9.object(arg8), arg9.objectInfo(arg10), arg9.offset(arg8), arg9.length(arg8));
            }
        } else {
            return Foreign.invokeN5(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6);
        }
    }

  public final long invokeN5(CallContext arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, int arg7, Object arg8, ObjectParameterStrategy arg9, ObjectParameterInfo arg10, Object arg11, ObjectParameterStrategy arg12, ObjectParameterInfo arg13) {
        if (arg7 != 0) {
            if (arg7 != 1) {
                if (arg7 != 2) {
                    throw newObjectCountError(arg7);
                } else {
                    return Foreign.invokeN5O2(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg9.object(arg8), arg9.objectInfo(arg10), arg9.offset(arg8), arg9.length(arg8), arg12.object(arg11), arg12.objectInfo(arg13), arg12.offset(arg11), arg12.length(arg11));
                }
            } else {
                if (arg9.isDirect()) {
                    arg8 = arg11;
                    arg9 = arg12;
                    arg10 = arg13;
                }
                return Foreign.invokeN5O1(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg9.object(arg8), arg9.objectInfo(arg10), arg9.offset(arg8), arg9.length(arg8));
            }
        } else {
            return Foreign.invokeN5(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6);
        }
    }

  public final long invokeN5(CallContext arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, int arg7, Object arg8, ObjectParameterStrategy arg9, ObjectParameterInfo arg10, Object arg11, ObjectParameterStrategy arg12, ObjectParameterInfo arg13, Object arg14, ObjectParameterStrategy arg15, ObjectParameterInfo arg16) {
        if (arg7 != 0) {
            int var24 = 1;
            switch (var24) {
                case 1:
                    ++var24;
                    if (!arg9.isDirect()) {
                        break;
                    }
                case 2:
                    ++var24;
                    if (!arg12.isDirect()) {
                        arg8 = arg11;
                        arg9 = arg12;
                        arg10 = arg13;
                        break;
                    }
                case 3:
                    ++var24;
                    if (arg15.isDirect()) {
                        break;
                    }
                    arg8 = arg14;
                    arg9 = arg15;
                    arg10 = arg16;
                default:
            }
        } else {
            return Foreign.invokeN5(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6);
        }
        if (arg7 != 1) {
            switch (var24) {
                case 2:
                    ++var24;
                    if (!arg12.isDirect()) {
                        break;
                    }
                case 3:
                    ++var24;
                    if (arg15.isDirect()) {
                        break;
                    }
                    arg11 = arg14;
                    arg12 = arg15;
                    arg13 = arg16;
                default:
            }
        } else {
            return Foreign.invokeN5O1(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg9.object(arg8), arg9.objectInfo(arg10), arg9.offset(arg8), arg9.length(arg8));
        }
        if (arg7 != 2) {
            switch (var24) {
                case 3:
                    if (arg15.isDirect()) {
                        break;
                    } else {
                        break;
                    }
                default:
            }
        } else {
            return Foreign.invokeN5O2(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg9.object(arg8), arg9.objectInfo(arg10), arg9.offset(arg8), arg9.length(arg8), arg12.object(arg11), arg12.objectInfo(arg13), arg12.offset(arg11), arg12.length(arg11));
        }
        if (arg7 != 3) {
            throw newObjectCountError(arg7);
        } else {
            return Foreign.invokeN5O3(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg9.object(arg8), arg9.objectInfo(arg10), arg9.offset(arg8), arg9.length(arg8), arg12.object(arg11), arg12.objectInfo(arg13), arg12.offset(arg11), arg12.length(arg11), arg15.object(arg14), arg15.objectInfo(arg16), arg15.offset(arg14), arg15.length(arg14));
        }
    }

  public final long invokeN5(CallContext arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, int arg7, Object arg8, ObjectParameterStrategy arg9, ObjectParameterInfo arg10, Object arg11, ObjectParameterStrategy arg12, ObjectParameterInfo arg13, Object arg14, ObjectParameterStrategy arg15, ObjectParameterInfo arg16, Object arg17, ObjectParameterStrategy arg18, ObjectParameterInfo arg19) {
        if (arg7 != 0) {
            int var27 = 1;
            switch (var27) {
                case 1:
                    ++var27;
                    if (!arg9.isDirect()) {
                        break;
                    }
                case 2:
                    ++var27;
                    if (!arg12.isDirect()) {
                        arg8 = arg11;
                        arg9 = arg12;
                        arg10 = arg13;
                        break;
                    }
                case 3:
                    ++var27;
                    if (!arg15.isDirect()) {
                        arg8 = arg14;
                        arg9 = arg15;
                        arg10 = arg16;
                        break;
                    }
                case 4:
                    ++var27;
                    if (arg18.isDirect()) {
                        break;
                    }
                    arg8 = arg17;
                    arg9 = arg18;
                    arg10 = arg19;
                default:
            }
        } else {
            return Foreign.invokeN5(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6);
        }
        if (arg7 != 1) {
            switch (var27) {
                case 2:
                    ++var27;
                    if (!arg12.isDirect()) {
                        break;
                    }
                case 3:
                    ++var27;
                    if (!arg15.isDirect()) {
                        arg11 = arg14;
                        arg12 = arg15;
                        arg13 = arg16;
                        break;
                    }
                case 4:
                    ++var27;
                    if (arg18.isDirect()) {
                        break;
                    }
                    arg11 = arg17;
                    arg12 = arg18;
                    arg13 = arg19;
                default:
            }
        } else {
            return Foreign.invokeN5O1(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg9.object(arg8), arg9.objectInfo(arg10), arg9.offset(arg8), arg9.length(arg8));
        }
        if (arg7 != 2) {
            switch (var27) {
                case 3:
                    if (!arg15.isDirect()) {
                        break;
                    }
                case 4:
                    if (arg18.isDirect()) {
                        break;
                    }
                    arg14 = arg17;
                    arg15 = arg18;
                    arg16 = arg19;
                default:
            }
        } else {
            return Foreign.invokeN5O2(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg9.object(arg8), arg9.objectInfo(arg10), arg9.offset(arg8), arg9.length(arg8), arg12.object(arg11), arg12.objectInfo(arg13), arg12.offset(arg11), arg12.length(arg11));
        }
        if (arg7 != 3) {
            if (arg7 != 4) {
                throw newObjectCountError(arg7);
            } else {
                return Foreign.invokeN5O4(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg9.object(arg8), arg9.objectInfo(arg10), arg9.offset(arg8), arg9.length(arg8), arg12.object(arg11), arg12.objectInfo(arg13), arg12.offset(arg11), arg12.length(arg11), arg15.object(arg14), arg15.objectInfo(arg16), arg15.offset(arg14), arg15.length(arg14), arg18.object(arg17), arg18.objectInfo(arg19), arg18.offset(arg17), arg18.length(arg17));
            }
        } else {
            return Foreign.invokeN5O3(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg9.object(arg8), arg9.objectInfo(arg10), arg9.offset(arg8), arg9.length(arg8), arg12.object(arg11), arg12.objectInfo(arg13), arg12.offset(arg11), arg12.length(arg11), arg15.object(arg14), arg15.objectInfo(arg16), arg15.offset(arg14), arg15.length(arg14));
        }
    }

  public final long invokeN5(CallContext arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, int arg7, Object arg8, ObjectParameterStrategy arg9, ObjectParameterInfo arg10, Object arg11, ObjectParameterStrategy arg12, ObjectParameterInfo arg13, Object arg14, ObjectParameterStrategy arg15, ObjectParameterInfo arg16, Object arg17, ObjectParameterStrategy arg18, ObjectParameterInfo arg19, Object arg20, ObjectParameterStrategy arg21, ObjectParameterInfo arg22) {
        if (arg7 != 0) {
            int var30 = 1;
            switch (var30) {
                case 1:
                    ++var30;
                    if (!arg9.isDirect()) {
                        break;
                    }
                case 2:
                    ++var30;
                    if (!arg12.isDirect()) {
                        arg8 = arg11;
                        arg9 = arg12;
                        arg10 = arg13;
                        break;
                    }
                case 3:
                    ++var30;
                    if (!arg15.isDirect()) {
                        arg8 = arg14;
                        arg9 = arg15;
                        arg10 = arg16;
                        break;
                    }
                case 4:
                    ++var30;
                    if (!arg18.isDirect()) {
                        arg8 = arg17;
                        arg9 = arg18;
                        arg10 = arg19;
                        break;
                    }
                case 5:
                    ++var30;
                    if (arg21.isDirect()) {
                        break;
                    }
                    arg8 = arg20;
                    arg9 = arg21;
                    arg10 = arg22;
                default:
            }
        } else {
            return Foreign.invokeN5(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6);
        }
        if (arg7 != 1) {
            switch (var30) {
                case 2:
                    ++var30;
                    if (!arg12.isDirect()) {
                        break;
                    }
                case 3:
                    ++var30;
                    if (!arg15.isDirect()) {
                        arg11 = arg14;
                        arg12 = arg15;
                        arg13 = arg16;
                        break;
                    }
                case 4:
                    ++var30;
                    if (!arg18.isDirect()) {
                        arg11 = arg17;
                        arg12 = arg18;
                        arg13 = arg19;
                        break;
                    }
                case 5:
                    ++var30;
                    if (arg21.isDirect()) {
                        break;
                    }
                    arg11 = arg20;
                    arg12 = arg21;
                    arg13 = arg22;
                default:
            }
        } else {
            return Foreign.invokeN5O1(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg9.object(arg8), arg9.objectInfo(arg10), arg9.offset(arg8), arg9.length(arg8));
        }
        if (arg7 != 2) {
            switch (var30) {
                case 3:
                    ++var30;
                    if (!arg15.isDirect()) {
                        break;
                    }
                case 4:
                    ++var30;
                    if (!arg18.isDirect()) {
                        arg14 = arg17;
                        arg15 = arg18;
                        arg16 = arg19;
                        break;
                    }
                case 5:
                    ++var30;
                    if (arg21.isDirect()) {
                        break;
                    }
                    arg14 = arg20;
                    arg15 = arg21;
                    arg16 = arg22;
                default:
            }
        } else {
            return Foreign.invokeN5O2(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg9.object(arg8), arg9.objectInfo(arg10), arg9.offset(arg8), arg9.length(arg8), arg12.object(arg11), arg12.objectInfo(arg13), arg12.offset(arg11), arg12.length(arg11));
        }
        if (arg7 != 3) {
            switch (var30) {
                case 4:
                    if (!arg18.isDirect()) {
                        break;
                    }
                case 5:
                    if (arg21.isDirect()) {
                        break;
                    }
                    arg17 = arg20;
                    arg18 = arg21;
                    arg19 = arg22;
                default:
            }
        } else {
            return Foreign.invokeN5O3(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg9.object(arg8), arg9.objectInfo(arg10), arg9.offset(arg8), arg9.length(arg8), arg12.object(arg11), arg12.objectInfo(arg13), arg12.offset(arg11), arg12.length(arg11), arg15.object(arg14), arg15.objectInfo(arg16), arg15.offset(arg14), arg15.length(arg14));
        }
        if (arg7 != 4) {
            if (arg7 != 5) {
                throw newObjectCountError(arg7);
            } else {
                return Foreign.invokeN5O5(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg9.object(arg8), arg9.objectInfo(arg10), arg9.offset(arg8), arg9.length(arg8), arg12.object(arg11), arg12.objectInfo(arg13), arg12.offset(arg11), arg12.length(arg11), arg15.object(arg14), arg15.objectInfo(arg16), arg15.offset(arg14), arg15.length(arg14), arg18.object(arg17), arg18.objectInfo(arg19), arg18.offset(arg17), arg18.length(arg17), arg21.object(arg20), arg21.objectInfo(arg22), arg21.offset(arg20), arg21.length(arg20));
            }
        } else {
            return Foreign.invokeN5O4(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg9.object(arg8), arg9.objectInfo(arg10), arg9.offset(arg8), arg9.length(arg8), arg12.object(arg11), arg12.objectInfo(arg13), arg12.offset(arg11), arg12.length(arg11), arg15.object(arg14), arg15.objectInfo(arg16), arg15.offset(arg14), arg15.length(arg14), arg18.object(arg17), arg18.objectInfo(arg19), arg18.offset(arg17), arg18.length(arg17));
        }
    }

  public final long invokeN6(CallContext arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, long arg7, int arg8, Object arg9, ObjectParameterStrategy arg10, ObjectParameterInfo arg11) {
        if (arg8 != 0) {
            if (arg8 != 1) {
                throw newObjectCountError(arg8);
            } else {
                return Foreign.invokeN6O1(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg10.object(arg9), arg10.objectInfo(arg11), arg10.offset(arg9), arg10.length(arg9));
            }
        } else {
            return Foreign.invokeN6(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
        }
    }

  public final long invokeN6(CallContext arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, long arg7, int arg8, Object arg9, ObjectParameterStrategy arg10, ObjectParameterInfo arg11, Object arg12, ObjectParameterStrategy arg13, ObjectParameterInfo arg14) {
        if (arg8 != 0) {
            if (arg8 != 1) {
                if (arg8 != 2) {
                    throw newObjectCountError(arg8);
                } else {
                    return Foreign.invokeN6O2(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg10.object(arg9), arg10.objectInfo(arg11), arg10.offset(arg9), arg10.length(arg9), arg13.object(arg12), arg13.objectInfo(arg14), arg13.offset(arg12), arg13.length(arg12));
                }
            } else {
                if (arg10.isDirect()) {
                    arg9 = arg12;
                    arg10 = arg13;
                    arg11 = arg14;
                }
                return Foreign.invokeN6O1(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg10.object(arg9), arg10.objectInfo(arg11), arg10.offset(arg9), arg10.length(arg9));
            }
        } else {
            return Foreign.invokeN6(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
        }
    }

  public final long invokeN6(CallContext arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, long arg7, int arg8, Object arg9, ObjectParameterStrategy arg10, ObjectParameterInfo arg11, Object arg12, ObjectParameterStrategy arg13, ObjectParameterInfo arg14, Object arg15, ObjectParameterStrategy arg16, ObjectParameterInfo arg17) {
        if (arg8 != 0) {
            int var26 = 1;
            switch (var26) {
                case 1:
                    ++var26;
                    if (!arg10.isDirect()) {
                        break;
                    }
                case 2:
                    ++var26;
                    if (!arg13.isDirect()) {
                        arg9 = arg12;
                        arg10 = arg13;
                        arg11 = arg14;
                        break;
                    }
                case 3:
                    ++var26;
                    if (arg16.isDirect()) {
                        break;
                    }
                    arg9 = arg15;
                    arg10 = arg16;
                    arg11 = arg17;
                default:
            }
        } else {
            return Foreign.invokeN6(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
        }
        if (arg8 != 1) {
            switch (var26) {
                case 2:
                    ++var26;
                    if (!arg13.isDirect()) {
                        break;
                    }
                case 3:
                    ++var26;
                    if (arg16.isDirect()) {
                        break;
                    }
                    arg12 = arg15;
                    arg13 = arg16;
                    arg14 = arg17;
                default:
            }
        } else {
            return Foreign.invokeN6O1(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg10.object(arg9), arg10.objectInfo(arg11), arg10.offset(arg9), arg10.length(arg9));
        }
        if (arg8 != 2) {
            switch (var26) {
                case 3:
                    if (arg16.isDirect()) {
                        break;
                    } else {
                        break;
                    }
                default:
            }
        } else {
            return Foreign.invokeN6O2(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg10.object(arg9), arg10.objectInfo(arg11), arg10.offset(arg9), arg10.length(arg9), arg13.object(arg12), arg13.objectInfo(arg14), arg13.offset(arg12), arg13.length(arg12));
        }
        if (arg8 != 3) {
            throw newObjectCountError(arg8);
        } else {
            return Foreign.invokeN6O3(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg10.object(arg9), arg10.objectInfo(arg11), arg10.offset(arg9), arg10.length(arg9), arg13.object(arg12), arg13.objectInfo(arg14), arg13.offset(arg12), arg13.length(arg12), arg16.object(arg15), arg16.objectInfo(arg17), arg16.offset(arg15), arg16.length(arg15));
        }
    }

  public final long invokeN6(CallContext arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, long arg7, int arg8, Object arg9, ObjectParameterStrategy arg10, ObjectParameterInfo arg11, Object arg12, ObjectParameterStrategy arg13, ObjectParameterInfo arg14, Object arg15, ObjectParameterStrategy arg16, ObjectParameterInfo arg17, Object arg18, ObjectParameterStrategy arg19, ObjectParameterInfo arg20) {
        if (arg8 != 0) {
            int var29 = 1;
            switch (var29) {
                case 1:
                    ++var29;
                    if (!arg10.isDirect()) {
                        break;
                    }
                case 2:
                    ++var29;
                    if (!arg13.isDirect()) {
                        arg9 = arg12;
                        arg10 = arg13;
                        arg11 = arg14;
                        break;
                    }
                case 3:
                    ++var29;
                    if (!arg16.isDirect()) {
                        arg9 = arg15;
                        arg10 = arg16;
                        arg11 = arg17;
                        break;
                    }
                case 4:
                    ++var29;
                    if (arg19.isDirect()) {
                        break;
                    }
                    arg9 = arg18;
                    arg10 = arg19;
                    arg11 = arg20;
                default:
            }
        } else {
            return Foreign.invokeN6(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
        }
        if (arg8 != 1) {
            switch (var29) {
                case 2:
                    ++var29;
                    if (!arg13.isDirect()) {
                        break;
                    }
                case 3:
                    ++var29;
                    if (!arg16.isDirect()) {
                        arg12 = arg15;
                        arg13 = arg16;
                        arg14 = arg17;
                        break;
                    }
                case 4:
                    ++var29;
                    if (arg19.isDirect()) {
                        break;
                    }
                    arg12 = arg18;
                    arg13 = arg19;
                    arg14 = arg20;
                default:
            }
        } else {
            return Foreign.invokeN6O1(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg10.object(arg9), arg10.objectInfo(arg11), arg10.offset(arg9), arg10.length(arg9));
        }
        if (arg8 != 2) {
            switch (var29) {
                case 3:
                    if (!arg16.isDirect()) {
                        break;
                    }
                case 4:
                    if (arg19.isDirect()) {
                        break;
                    }
                    arg15 = arg18;
                    arg16 = arg19;
                    arg17 = arg20;
                default:
            }
        } else {
            return Foreign.invokeN6O2(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg10.object(arg9), arg10.objectInfo(arg11), arg10.offset(arg9), arg10.length(arg9), arg13.object(arg12), arg13.objectInfo(arg14), arg13.offset(arg12), arg13.length(arg12));
        }
        if (arg8 != 3) {
            if (arg8 != 4) {
                throw newObjectCountError(arg8);
            } else {
                return Foreign.invokeN6O4(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg10.object(arg9), arg10.objectInfo(arg11), arg10.offset(arg9), arg10.length(arg9), arg13.object(arg12), arg13.objectInfo(arg14), arg13.offset(arg12), arg13.length(arg12), arg16.object(arg15), arg16.objectInfo(arg17), arg16.offset(arg15), arg16.length(arg15), arg19.object(arg18), arg19.objectInfo(arg20), arg19.offset(arg18), arg19.length(arg18));
            }
        } else {
            return Foreign.invokeN6O3(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg10.object(arg9), arg10.objectInfo(arg11), arg10.offset(arg9), arg10.length(arg9), arg13.object(arg12), arg13.objectInfo(arg14), arg13.offset(arg12), arg13.length(arg12), arg16.object(arg15), arg16.objectInfo(arg17), arg16.offset(arg15), arg16.length(arg15));
        }
    }

  public final long invokeN6(CallContext arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, long arg7, int arg8, Object arg9, ObjectParameterStrategy arg10, ObjectParameterInfo arg11, Object arg12, ObjectParameterStrategy arg13, ObjectParameterInfo arg14, Object arg15, ObjectParameterStrategy arg16, ObjectParameterInfo arg17, Object arg18, ObjectParameterStrategy arg19, ObjectParameterInfo arg20, Object arg21, ObjectParameterStrategy arg22, ObjectParameterInfo arg23) {
        if (arg8 != 0) {
            int var32 = 1;
            switch (var32) {
                case 1:
                    ++var32;
                    if (!arg10.isDirect()) {
                        break;
                    }
                case 2:
                    ++var32;
                    if (!arg13.isDirect()) {
                        arg9 = arg12;
                        arg10 = arg13;
                        arg11 = arg14;
                        break;
                    }
                case 3:
                    ++var32;
                    if (!arg16.isDirect()) {
                        arg9 = arg15;
                        arg10 = arg16;
                        arg11 = arg17;
                        break;
                    }
                case 4:
                    ++var32;
                    if (!arg19.isDirect()) {
                        arg9 = arg18;
                        arg10 = arg19;
                        arg11 = arg20;
                        break;
                    }
                case 5:
                    ++var32;
                    if (arg22.isDirect()) {
                        break;
                    }
                    arg9 = arg21;
                    arg10 = arg22;
                    arg11 = arg23;
                default:
            }
        } else {
            return Foreign.invokeN6(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
        }
        if (arg8 != 1) {
            switch (var32) {
                case 2:
                    ++var32;
                    if (!arg13.isDirect()) {
                        break;
                    }
                case 3:
                    ++var32;
                    if (!arg16.isDirect()) {
                        arg12 = arg15;
                        arg13 = arg16;
                        arg14 = arg17;
                        break;
                    }
                case 4:
                    ++var32;
                    if (!arg19.isDirect()) {
                        arg12 = arg18;
                        arg13 = arg19;
                        arg14 = arg20;
                        break;
                    }
                case 5:
                    ++var32;
                    if (arg22.isDirect()) {
                        break;
                    }
                    arg12 = arg21;
                    arg13 = arg22;
                    arg14 = arg23;
                default:
            }
        } else {
            return Foreign.invokeN6O1(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg10.object(arg9), arg10.objectInfo(arg11), arg10.offset(arg9), arg10.length(arg9));
        }
        if (arg8 != 2) {
            switch (var32) {
                case 3:
                    ++var32;
                    if (!arg16.isDirect()) {
                        break;
                    }
                case 4:
                    ++var32;
                    if (!arg19.isDirect()) {
                        arg15 = arg18;
                        arg16 = arg19;
                        arg17 = arg20;
                        break;
                    }
                case 5:
                    ++var32;
                    if (arg22.isDirect()) {
                        break;
                    }
                    arg15 = arg21;
                    arg16 = arg22;
                    arg17 = arg23;
                default:
            }
        } else {
            return Foreign.invokeN6O2(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg10.object(arg9), arg10.objectInfo(arg11), arg10.offset(arg9), arg10.length(arg9), arg13.object(arg12), arg13.objectInfo(arg14), arg13.offset(arg12), arg13.length(arg12));
        }
        if (arg8 != 3) {
            switch (var32) {
                case 4:
                    if (!arg19.isDirect()) {
                        break;
                    }
                case 5:
                    if (arg22.isDirect()) {
                        break;
                    }
                    arg18 = arg21;
                    arg19 = arg22;
                    arg20 = arg23;
                default:
            }
        } else {
            return Foreign.invokeN6O3(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg10.object(arg9), arg10.objectInfo(arg11), arg10.offset(arg9), arg10.length(arg9), arg13.object(arg12), arg13.objectInfo(arg14), arg13.offset(arg12), arg13.length(arg12), arg16.object(arg15), arg16.objectInfo(arg17), arg16.offset(arg15), arg16.length(arg15));
        }
        if (arg8 != 4) {
            if (arg8 != 5) {
                throw newObjectCountError(arg8);
            } else {
                return Foreign.invokeN6O5(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg10.object(arg9), arg10.objectInfo(arg11), arg10.offset(arg9), arg10.length(arg9), arg13.object(arg12), arg13.objectInfo(arg14), arg13.offset(arg12), arg13.length(arg12), arg16.object(arg15), arg16.objectInfo(arg17), arg16.offset(arg15), arg16.length(arg15), arg19.object(arg18), arg19.objectInfo(arg20), arg19.offset(arg18), arg19.length(arg18), arg22.object(arg21), arg22.objectInfo(arg23), arg22.offset(arg21), arg22.length(arg21));
            }
        } else {
            return Foreign.invokeN6O4(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg10.object(arg9), arg10.objectInfo(arg11), arg10.offset(arg9), arg10.length(arg9), arg13.object(arg12), arg13.objectInfo(arg14), arg13.offset(arg12), arg13.length(arg12), arg16.object(arg15), arg16.objectInfo(arg17), arg16.offset(arg15), arg16.length(arg15), arg19.object(arg18), arg19.objectInfo(arg20), arg19.offset(arg18), arg19.length(arg18));
        }
    }

  public final long invokeN6(CallContext arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, long arg7, int arg8, Object arg9, ObjectParameterStrategy arg10, ObjectParameterInfo arg11, Object arg12, ObjectParameterStrategy arg13, ObjectParameterInfo arg14, Object arg15, ObjectParameterStrategy arg16, ObjectParameterInfo arg17, Object arg18, ObjectParameterStrategy arg19, ObjectParameterInfo arg20, Object arg21, ObjectParameterStrategy arg22, ObjectParameterInfo arg23, Object arg24, ObjectParameterStrategy arg25, ObjectParameterInfo arg26) {
        if (arg8 != 0) {
            int var35 = 1;
            switch (var35) {
                case 1:
                    ++var35;
                    if (!arg10.isDirect()) {
                        break;
                    }
                case 2:
                    ++var35;
                    if (!arg13.isDirect()) {
                        arg9 = arg12;
                        arg10 = arg13;
                        arg11 = arg14;
                        break;
                    }
                case 3:
                    ++var35;
                    if (!arg16.isDirect()) {
                        arg9 = arg15;
                        arg10 = arg16;
                        arg11 = arg17;
                        break;
                    }
                case 4:
                    ++var35;
                    if (!arg19.isDirect()) {
                        arg9 = arg18;
                        arg10 = arg19;
                        arg11 = arg20;
                        break;
                    }
                case 5:
                    ++var35;
                    if (!arg22.isDirect()) {
                        arg9 = arg21;
                        arg10 = arg22;
                        arg11 = arg23;
                        break;
                    }
                case 6:
                    ++var35;
                    if (arg25.isDirect()) {
                        break;
                    }
                    arg9 = arg24;
                    arg10 = arg25;
                    arg11 = arg26;
                default:
            }
        } else {
            return Foreign.invokeN6(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
        }
        if (arg8 != 1) {
            switch (var35) {
                case 2:
                    ++var35;
                    if (!arg13.isDirect()) {
                        break;
                    }
                case 3:
                    ++var35;
                    if (!arg16.isDirect()) {
                        arg12 = arg15;
                        arg13 = arg16;
                        arg14 = arg17;
                        break;
                    }
                case 4:
                    ++var35;
                    if (!arg19.isDirect()) {
                        arg12 = arg18;
                        arg13 = arg19;
                        arg14 = arg20;
                        break;
                    }
                case 5:
                    ++var35;
                    if (!arg22.isDirect()) {
                        arg12 = arg21;
                        arg13 = arg22;
                        arg14 = arg23;
                        break;
                    }
                case 6:
                    ++var35;
                    if (arg25.isDirect()) {
                        break;
                    }
                    arg12 = arg24;
                    arg13 = arg25;
                    arg14 = arg26;
                default:
            }
        } else {
            return Foreign.invokeN6O1(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg10.object(arg9), arg10.objectInfo(arg11), arg10.offset(arg9), arg10.length(arg9));
        }
        if (arg8 != 2) {
            switch (var35) {
                case 3:
                    ++var35;
                    if (!arg16.isDirect()) {
                        break;
                    }
                case 4:
                    ++var35;
                    if (!arg19.isDirect()) {
                        arg15 = arg18;
                        arg16 = arg19;
                        arg17 = arg20;
                        break;
                    }
                case 5:
                    ++var35;
                    if (!arg22.isDirect()) {
                        arg15 = arg21;
                        arg16 = arg22;
                        arg17 = arg23;
                        break;
                    }
                case 6:
                    ++var35;
                    if (arg25.isDirect()) {
                        break;
                    }
                    arg15 = arg24;
                    arg16 = arg25;
                    arg17 = arg26;
                default:
            }
        } else {
            return Foreign.invokeN6O2(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg10.object(arg9), arg10.objectInfo(arg11), arg10.offset(arg9), arg10.length(arg9), arg13.object(arg12), arg13.objectInfo(arg14), arg13.offset(arg12), arg13.length(arg12));
        }
        if (arg8 != 3) {
            switch (var35) {
                case 4:
                    ++var35;
                    if (!arg19.isDirect()) {
                        break;
                    }
                case 5:
                    ++var35;
                    if (!arg22.isDirect()) {
                        arg18 = arg21;
                        arg19 = arg22;
                        arg20 = arg23;
                        break;
                    }
                case 6:
                    ++var35;
                    if (arg25.isDirect()) {
                        break;
                    }
                    arg18 = arg24;
                    arg19 = arg25;
                    arg20 = arg26;
                default:
            }
        } else {
            return Foreign.invokeN6O3(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg10.object(arg9), arg10.objectInfo(arg11), arg10.offset(arg9), arg10.length(arg9), arg13.object(arg12), arg13.objectInfo(arg14), arg13.offset(arg12), arg13.length(arg12), arg16.object(arg15), arg16.objectInfo(arg17), arg16.offset(arg15), arg16.length(arg15));
        }
        if (arg8 != 4) {
            switch (var35) {
                case 5:
                    if (!arg22.isDirect()) {
                        break;
                    }
                case 6:
                    if (arg25.isDirect()) {
                        break;
                    }
                    arg21 = arg24;
                    arg22 = arg25;
                    arg23 = arg26;
                default:
            }
        } else {
            return Foreign.invokeN6O4(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg10.object(arg9), arg10.objectInfo(arg11), arg10.offset(arg9), arg10.length(arg9), arg13.object(arg12), arg13.objectInfo(arg14), arg13.offset(arg12), arg13.length(arg12), arg16.object(arg15), arg16.objectInfo(arg17), arg16.offset(arg15), arg16.length(arg15), arg19.object(arg18), arg19.objectInfo(arg20), arg19.offset(arg18), arg19.length(arg18));
        }
        if (arg8 != 5) {
            if (arg8 != 6) {
                throw newObjectCountError(arg8);
            } else {
                return Foreign.invokeN6O6(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg10.object(arg9), arg10.objectInfo(arg11), arg10.offset(arg9), arg10.length(arg9), arg13.object(arg12), arg13.objectInfo(arg14), arg13.offset(arg12), arg13.length(arg12), arg16.object(arg15), arg16.objectInfo(arg17), arg16.offset(arg15), arg16.length(arg15), arg19.object(arg18), arg19.objectInfo(arg20), arg19.offset(arg18), arg19.length(arg18), arg22.object(arg21), arg22.objectInfo(arg23), arg22.offset(arg21), arg22.length(arg21), arg25.object(arg24), arg25.objectInfo(arg26), arg25.offset(arg24), arg25.length(arg24));
            }
        } else {
            return Foreign.invokeN6O5(arg0.contextAddress, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg10.object(arg9), arg10.objectInfo(arg11), arg10.offset(arg9), arg10.length(arg9), arg13.object(arg12), arg13.objectInfo(arg14), arg13.offset(arg12), arg13.length(arg12), arg16.object(arg15), arg16.objectInfo(arg17), arg16.offset(arg15), arg16.length(arg15), arg19.object(arg18), arg19.objectInfo(arg20), arg19.offset(arg18), arg19.length(arg18), arg22.object(arg21), arg22.objectInfo(arg23), arg22.offset(arg21), arg22.length(arg21));
        }
    }

  public long invokeAddress(Function arg0, HeapInvocationBuffer arg1) {
        return invokeAddress(arg0.getCallContext(), arg0.getFunctionAddress(), arg1);
    }

  public abstract long invokeAddress(CallContext arg0, long arg1, HeapInvocationBuffer arg2);

  public final int invokeInt(Function arg0, HeapInvocationBuffer arg1) {
        return invokeInt(arg0.getCallContext(), arg0.getFunctionAddress(), arg1);
    }

  public final int invokeInt(CallContext arg0, long arg1, HeapInvocationBuffer arg2) {
        ObjectBuffer var5 = arg2.objectBuffer();
        return var5 == null ? Foreign.invokeArrayReturnInt(arg0.contextAddress, arg1, arg2.array()) : invokeArrayWithObjectsInt32(arg0.contextAddress, arg1, arg2, var5);
    }

  public final long invokeLong(Function arg0, HeapInvocationBuffer arg1) {
        return invokeLong(arg0.getCallContext(), arg0.getFunctionAddress(), arg1);
    }

  public final long invokeLong(CallContext arg0, long arg1, HeapInvocationBuffer arg2) {
        ObjectBuffer var5 = arg2.objectBuffer();
        return var5 == null ? Foreign.invokeArrayReturnLong(arg0.contextAddress, arg1, arg2.array()) : invokeArrayWithObjectsInt64(arg0.contextAddress, arg1, arg2, var5);
    }

  public final float invokeFloat(Function arg0, HeapInvocationBuffer arg1) {
        return invokeFloat(arg0.getCallContext(), arg0.getFunctionAddress(), arg1);
    }

  public final float invokeFloat(CallContext arg0, long arg1, HeapInvocationBuffer arg2) {
        ObjectBuffer var5 = arg2.objectBuffer();
        return var5 == null ? Foreign.invokeArrayReturnFloat(arg0.contextAddress, arg1, arg2.array()) : Foreign.invokeArrayWithObjectsFloat(arg0.contextAddress, arg1, arg2.array(), var5.objectCount(), var5.info(), var5.objects());
    }

  public final double invokeDouble(Function arg0, HeapInvocationBuffer arg1) {
        return invokeDouble(arg0.getCallContext(), arg0.getFunctionAddress(), arg1);
    }

  public final double invokeDouble(CallContext arg0, long arg1, HeapInvocationBuffer arg2) {
        ObjectBuffer var5 = arg2.objectBuffer();
        return var5 == null ? Foreign.invokeArrayReturnDouble(arg0.contextAddress, arg1, arg2.array()) : Foreign.invokeArrayWithObjectsDouble(arg0.contextAddress, arg1, arg2.array(), var5.objectCount(), var5.info(), var5.objects());
    }

  public final BigDecimal invokeBigDecimal(Function arg0, HeapInvocationBuffer arg1) {
        return invokeBigDecimal(arg0.getCallContext(), arg0.getFunctionAddress(), arg1);
    }

  public final BigDecimal invokeBigDecimal(CallContext arg0, long arg1, HeapInvocationBuffer arg2) {
        byte[] var5 = invokeStruct(arg0, arg1, arg2);
        return new BigDecimal(foreign.longDoubleToString(var5, 0, var5.length));
    }

  public final byte[] invokeStruct(Function arg0, HeapInvocationBuffer arg1) {
        return invokeStruct(arg0.getCallContext(), arg0.getFunctionAddress(), arg1);
    }

  public final byte[] invokeStruct(CallContext arg0, long arg1, HeapInvocationBuffer arg2) {
        byte[] var5 = new byte[arg0.getReturnType().size()];
        invokeStruct(arg0, arg1, arg2, var5, 0);
        return var5;
    }

  public final void invokeStruct(Function arg0, HeapInvocationBuffer arg1, byte[] arg2, int arg3) {
        invokeStruct(arg0.getCallContext(), arg0.getFunctionAddress(), arg1, arg2, arg3);
    }

  public final void invokeStruct(CallContext arg0, long arg1, HeapInvocationBuffer arg2, byte[] arg3, int arg4) {
        ObjectBuffer var7 = arg2.objectBuffer();
        if (var7 == null) {
            Foreign.invokeArrayReturnStruct(arg0.contextAddress, arg1, arg2.array(), arg3, arg4);
        } else {
            Foreign.invokeArrayWithObjectsReturnStruct(arg0.contextAddress, arg1, arg2.array(), var7.objectCount(), var7.info(), var7.objects(), arg3, arg4);
        }
    }

  public final Object invokeObject(Function arg0, HeapInvocationBuffer arg1) {
        ObjectBuffer var3 = arg1.objectBuffer();
        return Foreign.invokeArrayWithObjectsReturnObject(arg0.contextAddress, arg0.functionAddress, arg1.array(), var3.objectCount(), var3.info(), var3.objects());
    }

  public final void invoke(Function arg0, long arg1, long[] arg2) {
        Foreign.invokePointerParameterArray(arg0.contextAddress, arg0.functionAddress, arg1, arg2);
    }

  public final void invoke(CallContext arg0, long arg1, long arg2, long[] arg3) {
        Foreign.invokePointerParameterArray(arg0.contextAddress, arg1, arg2, arg3);
    }

  private int invokeArrayWithObjectsInt32(long arg0, long arg1, HeapInvocationBuffer arg2, ObjectBuffer arg3) {
        Object[] var7 = arg3.objects();
        int[] var8 = arg3.info();
        int var9 = arg3.objectCount();
        switch (var9) {
            case 1:
                return Foreign.invokeArrayO1Int32(arg0, arg1, arg2.array(), var7[0], var8[0], var8[1], var8[2]);
            case 2:
                return Foreign.invokeArrayO2Int32(arg0, arg1, arg2.array(), var7[0], var8[0], var8[1], var8[2], var7[1], var8[3], var8[4], var8[5]);
            default:
                return Foreign.invokeArrayWithObjectsInt32(arg0, arg1, arg2.array(), var9, var8, var7);
        }
    }

  private long invokeArrayWithObjectsInt64(long arg0, long arg1, HeapInvocationBuffer arg2, ObjectBuffer arg3) {
        Object[] var7 = arg3.objects();
        int[] var8 = arg3.info();
        int var9 = arg3.objectCount();
        switch (var9) {
            case 1:
                return Foreign.invokeArrayO1Int64(arg0, arg1, arg2.array(), var7[0], var8[0], var8[1], var8[2]);
            case 2:
                return Foreign.invokeArrayO2Int64(arg0, arg1, arg2.array(), var7[0], var8[0], var8[1], var8[2], var7[1], var8[3], var8[4], var8[5]);
            default:
                return Foreign.invokeArrayWithObjectsInt64(arg0, arg1, arg2.array(), var9, var8, var7);
        }
    }

   Invoker(Invoker_Anon1 arg0) { // было: <init>
        this();
    }

}