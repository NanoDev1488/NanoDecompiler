// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.annotations.IntrospectionDescription
package org.freedesktop.dbus.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import org.freedesktop.dbus.annotations.DBusInterfaceName;

@Retention("RUNTIME")
@DBusInterfaceName("org.freedesktop.DBus.Description")
public @interface IntrospectionDescription extends Annotation {

  public abstract String value();

}