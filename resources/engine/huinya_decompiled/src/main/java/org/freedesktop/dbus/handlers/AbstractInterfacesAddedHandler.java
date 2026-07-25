// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.handlers.AbstractInterfacesAddedHandler
package org.freedesktop.dbus.handlers;

import org.freedesktop.dbus.handlers.AbstractSignalHandlerBase;
import org.freedesktop.dbus.interfaces.ObjectManager_InterfacesAdded;

public abstract class AbstractInterfacesAddedHandler extends AbstractSignalHandlerBase {

  public AbstractInterfacesAddedHandler() { // было: <init>
        super();
    }

  public final Class getImplementationClass() {
        return ObjectManager_InterfacesAdded.class;
    }

}