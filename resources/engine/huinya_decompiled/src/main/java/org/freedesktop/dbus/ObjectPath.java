// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.ObjectPath
package org.freedesktop.dbus;

import java.util.Objects;
import org.freedesktop.dbus.DBusPath;

public class ObjectPath extends DBusPath {

    // ---- поля ----
  private String source;

  public ObjectPath(String arg0, String arg1) { // было: <init>
        super(arg1);
        source = arg0;
    }

  public String getSource() {
        return source;
    }

  public void setSource(String arg0) {
        source = arg0;
    }

  public int hashCode() {
        int var1 = 31;
        int var2 = super.hashCode();
        var2 = 31 * var2 + Objects.hash(new Object[]{source});
        return var2;
    }

  public boolean equals(Object arg0) {
        if (this != arg0) {
            if (super.equals(arg0)) {
                if (getClass() == arg0.getClass()) {
                    ObjectPath var2 = ((ObjectPath) arg0);
                    return Objects.equals(source, var2.source);
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

}