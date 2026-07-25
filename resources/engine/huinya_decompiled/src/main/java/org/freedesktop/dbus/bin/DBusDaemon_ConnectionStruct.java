// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.bin.DBusDaemon.ConnectionStruct
package org.freedesktop.dbus.bin;

import org.freedesktop.dbus.connections.transports.TransportConnection;

public class DBusDaemon_ConnectionStruct {

    // ---- поля ----
  private final TransportConnection connection;
  private String unique;

   DBusDaemon_ConnectionStruct(TransportConnection arg0) { // было: <init>
        super();
        connection = arg0;
    }

  public String toString() {
        return null != unique ? unique : ":?-?";
    }

}