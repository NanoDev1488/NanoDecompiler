// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.annotations.Position
package org.freedesktop.dbus.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention("RUNTIME")
@Target
public @interface Position extends Annotation {

  public abstract int value();

}