// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.interfaces.Properties
package org.freedesktop.dbus.interfaces;

import java.util.Map;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;

@DBusInterfaceName("org.freedesktop.DBus.Properties")
public interface Properties extends DBusInterface {

  public abstract Object Get(String arg0, String arg1);

  public abstract void Set(String arg0, String arg1, Object arg2);

  public abstract Map GetAll(String arg0);

}