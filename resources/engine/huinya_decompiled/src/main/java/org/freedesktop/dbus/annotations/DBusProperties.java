// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.annotations.DBusProperties
package org.freedesktop.dbus.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.freedesktop.dbus.annotations.DBusProperty;

@Target
@Retention("RUNTIME")
public @interface DBusProperties extends Annotation {

  public abstract DBusProperty[] value();

}