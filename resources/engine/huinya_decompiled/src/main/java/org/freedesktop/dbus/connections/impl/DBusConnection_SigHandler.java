// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.connections.impl.DBusConnection.SigHandler
package org.freedesktop.dbus.connections.impl;

import org.freedesktop.dbus.connections.impl.DBusConnection;
import org.freedesktop.dbus.interfaces.DBusSigHandler;
import org.freedesktop.dbus.messages.DBusSignal;

final class DBusConnection_SigHandler implements DBusSigHandler {

    // ---- поля ----
  final DBusConnection this$0;

  private DBusConnection_SigHandler(DBusConnection arg0) { // было: <init>
        super();
        this$0 = arg0;
    }

  public void handle(DBusSignal arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_1
        //      1: instanceof  #6 // org.freedesktop.dbus.interfaces.DBus$NameAcquired
        //      4: ifeq  51 (offset +47)
        //      7: aload_1
        //      8: checkcast  #6 // org.freedesktop.dbus.interfaces.DBus$NameAcquired
        //     11: astore_2
        //     12: aload_0
        //     13: getfield  #9 // org.freedesktop.dbus.connections.impl.DBusConnection$SigHandler.this$0:Lorg/freedesktop/dbus/connections/impl/DBusConnection;
        //     16: getfield  #8 // org.freedesktop.dbus.connections.impl.DBusConnection.busnames:Ljava/util/List;
        //     19: dup
        //     20: astore_3
        //     21: monitorenter
        //     22: aload_0
        //     23: getfield  #9 // org.freedesktop.dbus.connections.impl.DBusConnection$SigHandler.this$0:Lorg/freedesktop/dbus/connections/impl/DBusConnection;
        //     26: getfield  #8 // org.freedesktop.dbus.connections.impl.DBusConnection.busnames:Ljava/util/List;
        //     29: aload_2
        //     30: getfield  #10 // org.freedesktop.dbus.interfaces.DBus$NameAcquired.name:Ljava/lang/String;
        //     33: invokeinterface  #12 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //     38: pop
        //     39: aload_3
        //     40: monitorexit
        //     41: goto  51 (offset +10)
        //     44: astore  4
        //     46: aload_3
        //     47: monitorexit
        //     48: aload  4
        //     50: athrow
        //     51: return
        //       Exception table:
        //         from 22 to 41 target 44 type any
        //         from 44 to 48 target 44 type any
    }

}