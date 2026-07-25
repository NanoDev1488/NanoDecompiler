// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.propertyref.PropertyRef
package org.freedesktop.dbus.propertyref;

import java.lang.reflect.Method;
import java.util.Objects;
import org.freedesktop.dbus.annotations.DBusBoundProperty;
import org.freedesktop.dbus.annotations.DBusProperty;
import org.freedesktop.dbus.annotations.DBusProperty_Access;

public final class PropertyRef {

    // ---- поля ----
  private final String name;
  private final Class type;
  private final DBusProperty_Access access;

  public PropertyRef(String arg0, Class arg1, DBusProperty_Access arg2) { // было: <init>
        super();
        name = arg0;
        type = arg1;
        access = arg2;
    }

  public PropertyRef(DBusProperty arg0) { // было: <init>
        this(arg0.name(), arg0.type(), arg0.access());
    }

  public int hashCode() {
        return Objects.hash(new Object[]{access, name});
    }

  public boolean equals(Object arg0) {
        if (this != arg0) {
            if (arg0 != null) {
                if (getClass() == arg0.getClass()) {
                    PropertyRef var2 = ((PropertyRef) arg0);
                    return access != var2.access ? 0 : Objects.equals(name, var2.name);
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

  public String getName() {
        return name;
    }

  public Class getType() {
        return type;
    }

  public DBusProperty_Access getAccess() {
        return access;
    }

  public static DBusProperty_Access accessForMethod(Method arg0) {
        DBusProperty_Access __stk1;
        DBusBoundProperty var1 = ((DBusBoundProperty) arg0.getAnnotation(DBusBoundProperty.class));
        __stk1 = !arg0.getName().toLowerCase().startsWith("set") ? DBusProperty_Access.READ : DBusProperty_Access.WRITE;
        DBusProperty_Access var2 = __stk1;
        if (var1.access().equals(DBusProperty_Access.READ)) {
            var2 = var1.access();
        } else {
            if (var1.access().equals(DBusProperty_Access.WRITE)) {
                var2 = var1.access();
            }
        }
        return ((Access) var2);
    }

  public static Class typeForMethod(Method arg0) {
        DBusBoundProperty var1 = ((DBusBoundProperty) arg0.getAnnotation(DBusBoundProperty.class));
        Class var2 = var1.type();
        if (var2 == null) {
            if (accessForMethod(arg0) != DBusProperty_Access.READ) {
                return ((Class) arg0.getParameterTypes()[0]);
            } else {
                return arg0.getReturnType();
            }
        } else {
            if (!var2.equals(Void.class)) {
                return var2;
            } else {
                if (accessForMethod(arg0) != DBusProperty_Access.READ) {
                    return ((Class) arg0.getParameterTypes()[0]);
                } else {
                    return arg0.getReturnType();
                }
            }
        }
    }

  public static void checkMethod(Method arg0) {
        DBusProperty_Access var1 = accessForMethod(arg0);
        if (var1 != DBusProperty_Access.READ) {
            if (var1 != DBusProperty_Access.WRITE) {
                return;
            } else {
                if (arg0.getParameterCount() != 1) {
                    throw new IllegalArgumentException("WRITE properties must have exactly 1 parameter, and return void.");
                } else {
                    if (arg0.getReturnType().equals(Void.TYPE)) {
                        return;
                    } else {
                        throw new IllegalArgumentException("WRITE properties must have exactly 1 parameter, and return void.");
                    }
                }
            }
        } else {
            if (arg0.getParameterCount() > 0) {
                throw new IllegalArgumentException("READ properties must have zero parameters, and not return void.");
            } else {
                if (!arg0.getReturnType().equals(Void.TYPE)) {
                    if (var1 != DBusProperty_Access.WRITE) {
                        return;
                    } else {
                        if (arg0.getParameterCount() != 1) {
                            throw new IllegalArgumentException("WRITE properties must have exactly 1 parameter, and return void.");
                        } else {
                            if (arg0.getReturnType().equals(Void.TYPE)) {
                                return;
                            } else {
                                throw new IllegalArgumentException("WRITE properties must have exactly 1 parameter, and return void.");
                            }
                        }
                    }
                } else {
                    throw new IllegalArgumentException("READ properties must have zero parameters, and not return void.");
                }
            }
        }
    }

}