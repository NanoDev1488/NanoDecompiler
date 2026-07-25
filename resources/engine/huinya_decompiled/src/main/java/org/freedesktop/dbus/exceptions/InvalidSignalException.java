// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.exceptions.InvalidSignalException
package org.freedesktop.dbus.exceptions;

import org.freedesktop.dbus.exceptions.DBusException;

public class InvalidSignalException extends DBusException {

    // ---- поля ----
  private static final long serialVersionUID = 1L;

  public InvalidSignalException(String arg0, Throwable arg1) { // было: <init>
        super(arg0, arg1);
    }

  public InvalidSignalException(String arg0) { // было: <init>
        super(arg0);
    }

  public InvalidSignalException(Class arg0) { // было: <init>
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: aload_1
        //      2: ifnonnull  10 (offset +8)
        //      5: ldc  #2 // 'Null is not a signal'
        //      7: goto  19 (offset +12)
        //     10: aload_1
        //     11: invokevirtual  #11 // java.lang.Class.getName:()Ljava/lang/String;
        //     14: invokedynamic  #16 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;
        //     19: invokespecial  #13 // org.freedesktop.dbus.exceptions.DBusException.<init>:(Ljava/lang/String;)V
        //     22: return
    }

  public InvalidSignalException(Throwable arg0) { // было: <init>
        super(arg0);
    }

}