// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.annotations.DBusMemberName
package org.freedesktop.dbus.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention("RUNTIME")
@Target
public @interface DBusMemberName extends Annotation {

  public abstract String value();

}