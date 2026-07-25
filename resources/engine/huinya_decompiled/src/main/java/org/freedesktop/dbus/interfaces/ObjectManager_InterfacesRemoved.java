// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.interfaces.ObjectManager.InterfacesRemoved
package org.freedesktop.dbus.interfaces;

import java.util.List;
import org.freedesktop.dbus.DBusPath;
import org.freedesktop.dbus.messages.DBusSignal;

public class ObjectManager_InterfacesRemoved extends DBusSignal {

    // ---- поля ----
  public final DBusPath signalSource;
  public final String objectPath;
  public final List interfaces;

  public ObjectManager_InterfacesRemoved(String arg0, DBusPath arg1, List arg2) { // было: <init>
        super(arg0, new Object[]{arg1, arg2});
        objectPath = arg0;
        signalSource = arg1;
        interfaces = arg2;
    }

  public DBusPath getSignalSource() {
        return signalSource;
    }

  public String getObjectPath() {
        return objectPath;
    }

  public List getInterfaces() {
        return interfaces;
    }

  public String toString() {
        return getClass().getSimpleName() + "[signalSource=" + String.valueOf(signalSource) + ", objectPath='" + objectPath + "', interfaces=" + String.valueOf(interfaces) + "]";
    }

}