// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.DBusMap.Entry
package org.freedesktop.dbus;

import java.util.Map.Entry;
import org.freedesktop.dbus.DBusMap;

class DBusMap_Entry implements Comparable, Entry {

    // ---- поля ----
  private final int entryPosition;
  final DBusMap this$0;

   DBusMap_Entry(DBusMap arg0, int arg1) { // было: <init>
        super();
        this$0 = arg0;
        entryPosition = arg1;
    }

  public boolean equals(Object arg0) {
        if (null != arg0) {
            if (arg0 instanceof DBusMap_Entry) {
                return entryPosition == (((DBusMap_Entry) arg0)).entryPosition;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

  public Object getKey() {
        return this$0.entries[entryPosition][0];
    }

  public Object getValue() {
        return this$0.entries[entryPosition][1];
    }

  public int hashCode() {
        return this$0.entries[entryPosition][0].hashCode();
    }

  public Object setValue(Object arg0) {
        throw new UnsupportedOperationException();
    }

  public int compareTo(DBusMap_Entry arg0) {
        return entryPosition - arg0.entryPosition;
    }

  public int compareTo(Object arg0) {
        return compareTo(((DBusMap_Entry) arg0));
    }

}