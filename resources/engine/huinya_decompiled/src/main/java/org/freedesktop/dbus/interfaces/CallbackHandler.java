// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.interfaces.CallbackHandler
package org.freedesktop.dbus.interfaces;

import org.freedesktop.dbus.exceptions.DBusExecutionException;

public interface CallbackHandler {

  public abstract void handle(Object arg0);

  public abstract void handleError(DBusExecutionException arg0);

}