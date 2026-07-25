// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.InterfaceScanner.VariablesIterator
package jnr.ffi.provider;

import java.lang.reflect.Method;
import java.util.Iterator;
import jnr.ffi.Variable;
import jnr.ffi.provider.InterfaceScanner;
import jnr.ffi.provider.InterfaceScanner_Anon1;
import jnr.ffi.provider.NativeVariable;

final class InterfaceScanner_VariablesIterator implements Iterator {

    // ---- поля ----
  private final Method[] methods;
  private int nextIndex;
  final InterfaceScanner this$0;

  private InterfaceScanner_VariablesIterator(InterfaceScanner arg0, Method[] arg1) { // было: <init>
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
            if (Variable.class == methods[nextIndex].getReturnType()) {
                break;
            }
            nextIndex = nextIndex + 1;
            continue;
        }
        return true;
    }

  public NativeVariable next() {
        nextIndex = nextIndex + 1;
        return new NativeVariable(((Method) methods[nextIndex]));
    }

  public void remove() {
        throw new UnsupportedOperationException();
    }

  public Object next() {
        return next();
    }

   InterfaceScanner_VariablesIterator(InterfaceScanner arg0, Method[] arg1, InterfaceScanner_Anon1 arg2) { // было: <init>
        this(arg0, arg1);
    }

}