// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.DBusPath
package org.freedesktop.dbus;

import java.util.Objects;

public class DBusPath implements Comparable {

    // ---- поля ----
  private String path;

  public DBusPath(String arg0) { // было: <init>
        super();
        setPath(arg0);
    }

  public String getPath() {
        return path;
    }

  public String toString() {
        return getPath();
    }

  public boolean equals(Object arg0) {
        int __stk1;
        if (!(arg0 instanceof DBusPath)) {
            __stk1 = 0;
        } else {
            DBusPath var2 = ((DBusPath) arg0);
            __stk1 = getPath() == null ? 0 : getPath().equals(var2.getPath());
        }
        return __stk1;
    }

  public int hashCode() {
        int var1 = 31;
        int var2 = super.hashCode();
        var2 = 31 * var2 + Objects.hash(new Object[]{path});
        return var2;
    }

  public int compareTo(DBusPath arg0) {
        if (getPath() == null) {
            return 0;
        } else {
            if (arg0 != null) {
                return getPath().compareTo(arg0.getPath());
            } else {
                return 0;
            }
        }
    }

  public void setPath(String arg0) {
        path = arg0;
    }

  public int compareTo(Object arg0) {
        return compareTo(((DBusPath) arg0));
    }

}