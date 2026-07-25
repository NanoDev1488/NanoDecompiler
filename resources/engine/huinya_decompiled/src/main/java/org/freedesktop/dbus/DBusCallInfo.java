// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.DBusCallInfo
package org.freedesktop.dbus;

import org.freedesktop.dbus.messages.Message;

public class DBusCallInfo {

    // ---- поля ----
  public static final int NO_REPLY = 1;
  public static final int ASYNC = 256;
  private final String source;
  private final String destination;
  private final String objectpath;
  private final String iface;
  private final String method;
  private final int flags;

  public DBusCallInfo(Message arg0) { // было: <init>
        super();
        source = arg0.getSource();
        destination = arg0.getDestination();
        objectpath = arg0.getPath();
        iface = arg0.getInterface();
        method = arg0.getName();
        flags = arg0.getFlags();
    }

  public String getSource() {
        return source;
    }

  public String getDestination() {
        return destination;
    }

  public String getObjectPath() {
        return objectpath;
    }

  public String getInterface() {
        return iface;
    }

  public String getMethod() {
        return method;
    }

  public int getFlags() {
        return flags;
    }

}