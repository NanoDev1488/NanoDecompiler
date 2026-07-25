// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.Util
package jnr.ffi.provider.jffi;

final class Util {

   Util() { // было: <init>
        super();
    }

  static boolean getBooleanProperty(String arg0, boolean arg1) {
        boolean __stk1;
        try {
            __stk1 = Boolean.valueOf(System.getProperty(arg0, Boolean.valueOf(arg1).toString())).booleanValue();
        } catch (SecurityException var2) {
            return arg1;
        }
    }

}