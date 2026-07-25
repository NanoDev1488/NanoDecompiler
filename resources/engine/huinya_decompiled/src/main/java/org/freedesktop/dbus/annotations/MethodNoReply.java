// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.annotations.MethodNoReply
package org.freedesktop.dbus.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.freedesktop.dbus.annotations.DBusInterfaceName;

@Target
@Retention("RUNTIME")
@DBusInterfaceName("org.freedesktop.DBus.Method.NoReply")
public @interface MethodNoReply extends Annotation {

  public abstract boolean value();

}