// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.types.DBusStructType
package org.freedesktop.dbus.types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import org.freedesktop.dbus.Struct;

public class DBusStructType implements ParameterizedType {

    // ---- поля ----
  private final Type[] contents;

  public DBusStructType(Type[] arg0) { // было: <init>
        super();
        contents = arg0;
    }

  public Type[] getActualTypeArguments() {
        return contents;
    }

  public Type getRawType() {
        return Struct.class;
    }

  public Type getOwnerType() {
        return null;
    }

}