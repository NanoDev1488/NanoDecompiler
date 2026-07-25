// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.DBusMap
package org.freedesktop.dbus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.freedesktop.dbus.DBusMap_Entry;

public class DBusMap implements Map {

    // ---- поля ----
   Object[][] entries;

  public DBusMap(Object[][] arg0) { // было: <init>
        super();
        entries = arg0;
    }

  public void clear() {
        throw new UnsupportedOperationException();
    }

  public boolean containsKey(Object arg0) {
        Object[][] var2 = entries;
        int var3 = var2.length;
        int var4 = 0;
        while (true) {
            if (var4 >= var3) {
                return false;
            }
            Object var5 = var2[var4];
            if (Objects.equals(arg0, var5[0])) {
                break;
            }
            ++var4;
            continue;
        }
        return true;
    }

  public boolean containsValue(Object arg0) {
        Object[][] var2 = entries;
        int var3 = var2.length;
        int var4 = 0;
        while (true) {
            if (var4 >= var3) {
                return false;
            }
            Object var5 = var2[var4];
            if (Objects.equals(arg0, var5[1])) {
                break;
            }
            ++var4;
            continue;
        }
        return true;
    }

  public Set entrySet() {
        LinkedHashSet var1 = new LinkedHashSet();
        int var2 = 0;
        while (var2 < entries.length) {
            var1.add(new DBusMap_Entry(this, var2));
            ++var2;
            continue;
        }
        return var1;
    }

  public Object get(Object arg0) {
        Object[][] var2 = entries;
        int var3 = var2.length;
        int var4 = 0;
        Object var5;
        while (true) {
            if (var4 >= var3) {
                return null;
            }
            var5 = var2[var4];
            if (arg0 == var5[0]) {
                break;
            }
            if (arg0 == null) {
                ++var4;
                continue;
            } else {
                if (arg0.equals(var5[0])) {
                    break;
                }
                ++var4;
                continue;
            }
        }
        return var5[1];
    }

  public boolean isEmpty() {
        return entries.length == 0;
    }

  public Set keySet() {
        LinkedHashSet var1 = new LinkedHashSet();
        Object[][] var2 = entries;
        int var3 = var2.length;
        int var4 = 0;
        while (var4 < var3) {
            Object var5 = var2[var4];
            var1.add(var5[0]);
            ++var4;
            continue;
        }
        return var1;
    }

  public Object put(Object arg0, Object arg1) {
        throw new UnsupportedOperationException();
    }

  public void putAll(Map arg0) {
        throw new UnsupportedOperationException();
    }

  public Object remove(Object arg0) {
        throw new UnsupportedOperationException();
    }

  public int size() {
        return entries.length;
    }

  public Collection values() {
        ArrayList var1 = new ArrayList();
        Object[][] var2 = entries;
        int var3 = var2.length;
        int var4 = 0;
        while (var4 < var3) {
            Object var5 = var2[var4];
            var1.add(var5[1]);
            ++var4;
            continue;
        }
        return var1;
    }

  public int hashCode() {
        return Arrays.deepHashCode(entries);
    }

  public boolean equals(Object arg0) {
        if (null != arg0) {
            if (arg0 instanceof Map) {
                return (((Map) arg0)).entrySet().equals(entrySet());
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

  public String toString() {
        return "{" + ((String) Arrays.stream(entries).map(lp0 -> lambda$toString$0(((Object[]) lp0))).collect(Collectors.joining(","))) + "}";
    }

  private static String lambda$toString$0(Object[] arg0) {
        return String.valueOf(arg0[0]) + " => " + String.valueOf(arg0[1]);
    }

}