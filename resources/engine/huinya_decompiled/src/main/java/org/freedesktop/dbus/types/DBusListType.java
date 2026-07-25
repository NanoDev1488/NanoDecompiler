// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.types.DBusListType
package org.freedesktop.dbus.types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class DBusListType implements ParameterizedType {

    // ---- поля ----
  private final Type field1018; // было: v

  public DBusListType(Type arg0) { // было: <init>
        super();
        field1018 = arg0;
    }

  public Type[] getActualTypeArguments() {
        return new Type[]{field1018};
    }

  public Type getRawType() {
        return List.class;
    }

  public Type getOwnerType() {
        return null;
    }

}