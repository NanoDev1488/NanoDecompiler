// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.annotations.MethodError
package org.freedesktop.dbus.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.freedesktop.dbus.annotations.DBusInterfaceName;

@Target
@Retention("RUNTIME")
@DBusInterfaceName("org.freedesktop.DBus.Method.Error")
public @interface MethodError extends Annotation {

  public abstract String value();

}