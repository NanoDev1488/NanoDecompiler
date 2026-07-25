// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.annotations.DBusIgnore
package org.freedesktop.dbus.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target
@Retention("RUNTIME")
public @interface DBusIgnore extends Annotation {

}