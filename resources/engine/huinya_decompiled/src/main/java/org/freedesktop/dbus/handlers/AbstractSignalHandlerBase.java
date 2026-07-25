// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.handlers.AbstractSignalHandlerBase
package org.freedesktop.dbus.handlers;

import org.freedesktop.dbus.interfaces.DBusSigHandler;

public abstract class AbstractSignalHandlerBase implements DBusSigHandler {

  public AbstractSignalHandlerBase() { // было: <init>
        super();
    }

  public abstract Class getImplementationClass();

}