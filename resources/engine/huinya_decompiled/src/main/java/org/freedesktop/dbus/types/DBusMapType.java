// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.types.DBusMapType
package org.freedesktop.dbus.types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

public class DBusMapType implements ParameterizedType {

    // ---- поля ----
  private final Type field1019; // было: k
  private final Type field1020; // было: v

  public DBusMapType(Type arg0, Type arg1) { // было: <init>
        super();
        field1019 = arg0;
        field1020 = arg1;
    }

  public Type[] getActualTypeArguments() {
        return new Type[]{field1019, field1020};
    }

  public Type getRawType() {
        return Map.class;
    }

  public Type getOwnerType() {
        return null;
    }

}