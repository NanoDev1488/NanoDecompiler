// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.types.Variant
package org.freedesktop.dbus.types;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.freedesktop.dbus.Marshalling;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.utils.DBusObjects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Variant {

    // ---- поля ----
  private final Logger logger;
  private final Object value;
  private final Type type;
  private final String sig;

  public Variant(Object arg0) { // было: <init>
        super();
        logger = LoggerFactory.getLogger(getClass());
        DBusObjects.requireNotNull(arg0, () -> lambda$new$0());
        type = arg0.getClass();
        try {
            String[] var2 = Marshalling.getDBusType(arg0.getClass(), true);
            if (var2.length == 1) {
                sig = ((String) var2[0]);
            } else {
                throw new IllegalArgumentException("Can't wrap a multi-valued type in a Variant: " + String.valueOf(type));
            }
        } catch (DBusException e1) {
            Throwable var2 = e1;
            logger.debug("Cannot create variant", var2);
            throw new IllegalArgumentException(String.format("Can't wrap %s in an unqualified Variant (%s).", new Object[]{arg0.getClass(), var2.getMessage()}));
        }
    }

  public Variant(Object arg0, Type arg1) { // было: <init>
        super();
        logger = LoggerFactory.getLogger(getClass());
        DBusObjects.requireNotNull(arg0, () -> lambda$new$1());
        type = arg1;
        try {
            String[] var3 = Marshalling.getDBusType(arg1);
            if (var3.length == 1) {
                sig = ((String) var3[0]);
            } else {
                throw new IllegalArgumentException("Can't wrap a multi-valued type in a Variant: " + String.valueOf(arg1));
            }
        } catch (DBusException e1) {
            Throwable var3 = e1;
            logger.debug("Cannot create variant", var3);
            throw new IllegalArgumentException(String.format("Can't wrap %s in an unqualified Variant (%s).", new Object[]{arg1, var3.getMessage()}));
        }
    }

  public Variant(Object arg0, String arg1) { // было: <init>
        super();
        logger = LoggerFactory.getLogger(getClass());
        DBusObjects.requireNotNull(arg0, () -> lambda$new$2());
        sig = arg1;
        try {
            ArrayList var3 = new ArrayList();
            Marshalling.getJavaType(arg1, var3, 1);
            if (var3.size() == 1) {
                type = ((Type) var3.get(0));
            } else {
                throw new IllegalArgumentException("Can't wrap multiple or no types in a Variant: " + arg1);
            }
        } catch (DBusException e1) {
            Throwable var3 = e1;
            logger.debug("Cannot create variant", var3);
            throw new IllegalArgumentException(String.format("Can''t wrap %s in an unqualified Variant (%s).", new Object[]{arg1, var3.getMessage()}));
        }
    }

  public Object getValue() {
        return value;
    }

  public Type getType() {
        return type;
    }

  public String getSig() {
        return sig;
    }

  public String toString() {
        return "[" + String.valueOf(value) + "]";
    }

  public int hashCode() {
        return Objects.hash(new Object[]{value});
    }

  public boolean equals(Object arg0) {
        if (this != arg0) {
            if (arg0 instanceof Variant) {
                Variant var2 = ((Variant) arg0);
                return Objects.equals(value, var2.value);
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

  private static IllegalArgumentException lambda$new$2() {
        return new IllegalArgumentException("Can't wrap Null in a Variant");
    }

  private static IllegalArgumentException lambda$new$1() {
        return new IllegalArgumentException("Can't wrap Null in a Variant");
    }

  private static IllegalArgumentException lambda$new$0() {
        return new IllegalArgumentException("Can't wrap Null in a Variant");
    }

}