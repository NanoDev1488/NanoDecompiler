// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.interfaces.ObjectManager.InterfacesAdded
package org.freedesktop.dbus.interfaces;

import java.util.Map;
import org.freedesktop.dbus.DBusPath;
import org.freedesktop.dbus.messages.DBusSignal;

public class ObjectManager_InterfacesAdded extends DBusSignal {

    // ---- поля ----
  public final DBusPath signalSource;
  public final String objectPath;
  public final Map interfaces;

  public ObjectManager_InterfacesAdded(String arg0, DBusPath arg1, Map arg2) { // было: <init>
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

  public Map getInterfaces() {
        return interfaces;
    }

  public String toString() {
        return getClass().getSimpleName() + "[signalSource=" + String.valueOf(signalSource) + ", objectPath='" + objectPath + "', interfaces=" + String.valueOf(interfaces) + "]";
    }

}