// исходный (обфусцированный) внутренний класс: jnr.ffi.LibraryLoader$1
package jnr.ffi;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import jnr.ffi.LibraryLoader;

class LibraryLoader_Anon1 implements InvocationHandler {

    // ---- поля ----
  final Throwable val$ex;
  final LibraryLoader this$0;

   LibraryLoader_Anon1(LibraryLoader arg0, Throwable arg1) { // было: <init>
        super();
        this$0 = arg0;
        val$ex = arg1;
    }

  public Object invoke(Object arg0, Method arg1, Object[] arg2) {
        throw val$ex;
    }

}