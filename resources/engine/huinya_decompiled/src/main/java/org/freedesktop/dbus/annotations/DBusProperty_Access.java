// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.annotations.DBusProperty.Access
package org.freedesktop.dbus.annotations;

public enum DBusProperty_Access {

    READ("read"),
    READ_WRITE("readwrite"),
    WRITE("write");

    // ---- поля ----
  private final String accessName;

  private DBusProperty_Access(String arg2) { // было: <init>
        accessName = arg2;
    }

  public String getAccessName() {
        return accessName;
    }

}