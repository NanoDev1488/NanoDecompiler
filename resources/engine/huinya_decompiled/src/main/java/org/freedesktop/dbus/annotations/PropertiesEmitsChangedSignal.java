// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.annotations.PropertiesEmitsChangedSignal
package org.freedesktop.dbus.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.annotations.PropertiesEmitsChangedSignal_EmitChangeSignal;

@Target
@Retention("RUNTIME")
@DBusInterfaceName("org.freedesktop.DBus.Property.EmitsChangedSignal")
public @interface PropertiesEmitsChangedSignal extends Annotation {

  public abstract PropertiesEmitsChangedSignal_EmitChangeSignal value();

}