// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.handlers.AbstractInterfacesRemovedHandler
package org.freedesktop.dbus.handlers;

import org.freedesktop.dbus.handlers.AbstractSignalHandlerBase;
import org.freedesktop.dbus.interfaces.ObjectManager_InterfacesRemoved;

public abstract class AbstractInterfacesRemovedHandler extends AbstractSignalHandlerBase {

  public AbstractInterfacesRemovedHandler() { // было: <init>
        super();
    }

  public final Class getImplementationClass() {
        return ObjectManager_InterfacesRemoved.class;
    }

}