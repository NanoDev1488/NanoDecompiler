// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.annotations.DBusBoundProperty
package org.freedesktop.dbus.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.freedesktop.dbus.annotations.DBusProperty_Access;

@Target
@Retention("RUNTIME")
public @interface DBusBoundProperty extends Annotation {

  public abstract String name();

  public abstract Class type();

  public abstract DBusProperty_Access access();

}