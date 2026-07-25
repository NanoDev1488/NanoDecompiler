// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.InterfaceScanner.FunctionsIterator
package jnr.ffi.provider;

import java.lang.reflect.Method;
import java.util.Iterator;
import jnr.ffi.CallingConvention;
import jnr.ffi.Variable;
import jnr.ffi.annotations.StdCall;
import jnr.ffi.provider.InterfaceScanner;
import jnr.ffi.provider.InterfaceScanner_Anon1;
import jnr.ffi.provider.NativeFunction;

final class InterfaceScanner_FunctionsIterator implements Iterator {

    // ---- поля ----
  private final Method[] methods;
  private int nextIndex;
  final InterfaceScanner this$0;

  private InterfaceScanner_FunctionsIterator(InterfaceScanner arg0, Method[] arg1) { // было: <init>
        super();
        this$0 = arg0;
        methods = arg1;
        nextIndex = 0;
    }

  public boolean hasNext() {
        while (true) {
            if (nextIndex >= methods.length) {
                return false;
            }
            if (Variable.class.isAssignableFrom(methods[nextIndex].getReturnType())) {
                nextIndex = nextIndex + 1;
                continue;
            } else {
                if (!InterfaceScanner.access$300(((Method) methods[nextIndex]))) {
                    break;
                }
                nextIndex = nextIndex + 1;
                continue;
            }
        }
        return true;
    }

  public NativeFunction next() {
        CallingConvention __stk1;
        __stk1 = !methods[nextIndex].isAnnotationPresent(StdCall.class) ? InterfaceScanner.access$400(this$0) : CallingConvention.STDCALL;
        CallingConvention var1 = __stk1;
        nextIndex = nextIndex + 1;
        return new NativeFunction(((Method) methods[nextIndex]), ((CallingConvention) var1));
    }

  public void remove() {
        throw new UnsupportedOperationException();
    }

  public Object next() {
        return next();
    }

   InterfaceScanner_FunctionsIterator(InterfaceScanner arg0, Method[] arg1, InterfaceScanner_Anon1 arg2) { // было: <init>
        this(arg0, arg1);
    }

}