// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.annotations.DeprecatedOnDBus
package org.freedesktop.dbus.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import org.freedesktop.dbus.annotations.DBusInterfaceName;

@Retention("RUNTIME")
@DBusInterfaceName("org.freedesktop.DBus.Deprecated")
public @interface DeprecatedOnDBus extends Annotation {

  public abstract boolean value();

}