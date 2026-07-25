// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.annotations.DBusInterfaceName
package org.freedesktop.dbus.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention("RUNTIME")
@Target
public @interface DBusInterfaceName extends Annotation {

  public abstract String value();

}