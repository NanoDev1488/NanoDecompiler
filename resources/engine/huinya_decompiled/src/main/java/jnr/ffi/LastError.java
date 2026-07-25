// исходный (обфусцированный) внутренний класс: jnr.ffi.LastError
package jnr.ffi;

import jnr.ffi.Runtime;

public final class LastError {

  private LastError() { // было: <init>
        super();
    }

  public static int getLastError(Runtime arg0) {
        return arg0.getLastError();
    }

  public static void setLastError(Runtime arg0, int arg1) {
        arg0.setLastError(arg1);
    }

}