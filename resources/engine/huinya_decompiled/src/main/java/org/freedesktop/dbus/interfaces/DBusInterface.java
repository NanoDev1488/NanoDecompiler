// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.interfaces.DBusInterface
package org.freedesktop.dbus.interfaces;

public interface DBusInterface {

  public boolean isRemote() {
        return false;
    }

  public abstract String getObjectPath();

}