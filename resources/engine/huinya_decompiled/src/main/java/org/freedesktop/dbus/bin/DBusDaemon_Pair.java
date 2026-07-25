// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.bin.DBusDaemon.Pair
package org.freedesktop.dbus.bin;

import java.util.Objects;

class DBusDaemon_Pair {

    // ---- поля ----
  private final Object first;
  private final Object second;

   DBusDaemon_Pair(Object arg0, Object arg1) { // было: <init>
        super();
        first = arg0;
        second = arg1;
    }

  public int hashCode() {
        return Objects.hash(new Object[]{first, second});
    }

  public boolean equals(Object arg0) {
        if (this != arg0) {
            if (arg0 instanceof DBusDaemon_Pair) {
                DBusDaemon_Pair var2 = ((DBusDaemon_Pair) arg0);
                return !Objects.equals(first, var2.first) ? 0 : Objects.equals(second, var2.second);
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

}