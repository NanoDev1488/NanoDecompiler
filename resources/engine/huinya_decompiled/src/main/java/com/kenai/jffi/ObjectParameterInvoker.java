// исходный (обфусцированный) внутренний класс: com.kenai.jffi.ObjectParameterInvoker
package com.kenai.jffi;

import com.kenai.jffi.Foreign;
import com.kenai.jffi.Function;
import com.kenai.jffi.HeapObjectParameterInvoker;
import com.kenai.jffi.NativeObjectParameterInvoker;
import com.kenai.jffi.ObjectParameterInfo;
import com.kenai.jffi.ObjectParameterInvoker_SingletonHolder;

public abstract class ObjectParameterInvoker {

  public ObjectParameterInvoker() { // было: <init>
        super();
    }

  public static ObjectParameterInvoker getInstance() {
        return ObjectParameterInvoker_SingletonHolder.INSTANCE;
    }

  static ObjectParameterInvoker newNativeInvoker() {
        return new NativeObjectParameterInvoker(Foreign.getInstance());
    }

  static ObjectParameterInvoker newHeapInvoker() {
        return new HeapObjectParameterInvoker(Foreign.getInstance());
    }

  public abstract boolean isNative();

  public abstract long invokeN1O1rN(Function arg0, long arg1, Object arg2, int arg3, int arg4, ObjectParameterInfo arg5);

  public abstract long invokeN2O1rN(Function arg0, long arg1, long arg2, Object arg3, int arg4, int arg5, ObjectParameterInfo arg6);

  public abstract long invokeN2O2rN(Function arg0, long arg1, long arg2, Object arg3, int arg4, int arg5, ObjectParameterInfo arg6, Object arg7, int arg8, int arg9, ObjectParameterInfo arg10);

  public abstract long invokeN3O1rN(Function arg0, long arg1, long arg2, long arg3, Object arg4, int arg5, int arg6, ObjectParameterInfo arg7);

  public abstract long invokeN3O2rN(Function arg0, long arg1, long arg2, long arg3, Object arg4, int arg5, int arg6, ObjectParameterInfo arg7, Object arg8, int arg9, int arg10, ObjectParameterInfo arg11);

  public abstract long invokeN3O3rN(Function arg0, long arg1, long arg2, long arg3, Object arg4, int arg5, int arg6, ObjectParameterInfo arg7, Object arg8, int arg9, int arg10, ObjectParameterInfo arg11, Object arg12, int arg13, int arg14, ObjectParameterInfo arg15);

  public abstract long invokeN4O1rN(Function arg0, long arg1, long arg2, long arg3, long arg4, Object arg5, int arg6, int arg7, ObjectParameterInfo arg8);

  public abstract long invokeN4O2rN(Function arg0, long arg1, long arg2, long arg3, long arg4, Object arg5, int arg6, int arg7, ObjectParameterInfo arg8, Object arg9, int arg10, int arg11, ObjectParameterInfo arg12);

  public abstract long invokeN4O3rN(Function arg0, long arg1, long arg2, long arg3, long arg4, Object arg5, int arg6, int arg7, ObjectParameterInfo arg8, Object arg9, int arg10, int arg11, ObjectParameterInfo arg12, Object arg13, int arg14, int arg15, ObjectParameterInfo arg16);

  public abstract long invokeN5O1rN(Function arg0, long arg1, long arg2, long arg3, long arg4, long arg5, Object arg6, int arg7, int arg8, ObjectParameterInfo arg9);

  public abstract long invokeN5O2rN(Function arg0, long arg1, long arg2, long arg3, long arg4, long arg5, Object arg6, int arg7, int arg8, ObjectParameterInfo arg9, Object arg10, int arg11, int arg12, ObjectParameterInfo arg13);

  public abstract long invokeN5O3rN(Function arg0, long arg1, long arg2, long arg3, long arg4, long arg5, Object arg6, int arg7, int arg8, ObjectParameterInfo arg9, Object arg10, int arg11, int arg12, ObjectParameterInfo arg13, Object arg14, int arg15, int arg16, ObjectParameterInfo arg17);

  public abstract long invokeN6O1rN(Function arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, Object arg7, int arg8, int arg9, ObjectParameterInfo arg10);

  public abstract long invokeN6O2rN(Function arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, Object arg7, int arg8, int arg9, ObjectParameterInfo arg10, Object arg11, int arg12, int arg13, ObjectParameterInfo arg14);

  public abstract long invokeN6O3rN(Function arg0, long arg1, long arg2, long arg3, long arg4, long arg5, long arg6, Object arg7, int arg8, int arg9, ObjectParameterInfo arg10, Object arg11, int arg12, int arg13, ObjectParameterInfo arg14, Object arg15, int arg16, int arg17, ObjectParameterInfo arg18);

}