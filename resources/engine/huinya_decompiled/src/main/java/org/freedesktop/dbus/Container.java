// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.Container
package org.freedesktop.dbus;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.freedesktop.dbus.annotations.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Container {

    // ---- поля ----
  private static final Map TYPE_CACHE;
  private Object[] parameters;

    static {
        TYPE_CACHE = new HashMap();
    }

   Container() { // было: <init>
        super();
        parameters = null;
    }

  private void setup() {
        Field[] var1 = getClass().getDeclaredFields();
        Object[] var2 = new Object[var1.length];
        int var3 = 0;
        Field[] var4 = var1;
        int var5 = var4.length;
        int var6 = 0;
        while (var6 < var5) {
            Object var7 = var4[var6];
            if (var7.isAnnotationPresent(Position.class)) {
                Position var8 = ((Position) var7.getAnnotation(Position.class));
                var7.setAccessible(true);
                try {
                    var2[var8.value()] = var7.get(this);
                } catch (IllegalAccessException var9) {
                    LoggerFactory.getLogger(getClass()).trace("Could not set value", var9);
                }
            } else {
                ++var3;
            }
            ++var6;
            continue;
        }
        parameters = new Object[var2.length - var3];
        System.arraycopy(var2, 0, parameters, 0, parameters.length);
    }

  public final Object[] getParameters() {
        if (null == parameters) {
            setup();
            return parameters;
        } else {
            return parameters;
        }
    }

  public final String toString() {
        StringBuilder var1 = new StringBuilder();
        var1.append(getClass().getName()).append("<");
        if (null == parameters) {
            setup();
        }
        if (0 != parameters.length) {
            var1.append(((String) Arrays.stream(parameters).map(lp0 -> Objects.toString(lp0)).collect(Collectors.joining(", "))));
            return var1.append(">").toString();
        } else {
            return var1.append(">").toString();
        }
    }

  public final boolean equals(Object arg0) {
        if (this != arg0) {
            if (arg0 != null) {
                if (!(arg0 instanceof Container)) {
                    return false;
                } else {
                    Container var2 = ((Container) arg0);
                    if (!getClass().equals(var2.getClass())) {
                        return false;
                    } else {
                        return Arrays.equals(getParameters(), var2.getParameters());
                    }
                }
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

  public int hashCode() {
        int var1 = 31;
        int var2 = 1;
        var2 = 31 * var2 + Arrays.deepHashCode(parameters);
        return var2;
    }

  static void putTypeCache(Type arg0, Type[] arg1) {
        TYPE_CACHE.put(arg0, arg1);
    }

  static Type[] getTypeCache(Type arg0) {
        return ((Type[]) TYPE_CACHE.get(arg0));
    }

}