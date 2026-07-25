// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.annotations.DBusProperty
package org.freedesktop.dbus.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.freedesktop.dbus.annotations.DBusProperty_Access;

@Target
@Retention("RUNTIME")
@Repeatable("Lorg/freedesktop/dbus/annotations/DBusProperties;")
public @interface DBusProperty extends Annotation {

  public abstract String name();

  public abstract Class type();

  public abstract DBusProperty_Access access();

}