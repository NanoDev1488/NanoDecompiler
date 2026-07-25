// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.StrongReference
package org.freedesktop.dbus;

import java.lang.ref.WeakReference;

public class StrongReference extends WeakReference {

    // ---- поля ----
  private Object referant;

  public StrongReference(Object arg0) { // было: <init>
        super(arg0);
        referant = arg0;
    }

  public void clear() {
        referant = null;
    }

  public boolean enqueue() {
        return false;
    }

  public Object get() {
        return referant;
    }

}