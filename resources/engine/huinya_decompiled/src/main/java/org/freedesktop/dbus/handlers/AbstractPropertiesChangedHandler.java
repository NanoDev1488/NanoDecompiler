// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.handlers.AbstractPropertiesChangedHandler
package org.freedesktop.dbus.handlers;

import org.freedesktop.dbus.handlers.AbstractSignalHandlerBase;
import org.freedesktop.dbus.interfaces.Properties_PropertiesChanged;

public abstract class AbstractPropertiesChangedHandler extends AbstractSignalHandlerBase {

  public AbstractPropertiesChangedHandler() { // было: <init>
        super();
    }

  public final Class getImplementationClass() {
        return Properties_PropertiesChanged.class;
    }

}