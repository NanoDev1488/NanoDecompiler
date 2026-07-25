// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.annotations.PropertiesEmitsChangedSignal.EmitChangeSignal
package org.freedesktop.dbus.annotations;

public enum PropertiesEmitsChangedSignal_EmitChangeSignal {

    TRUE,
    INVALIDATES,
    CONST,
    FALSE;

  private PropertiesEmitsChangedSignal_EmitChangeSignal() { // было: <init>
        // (пустое тело)
    }

  public String toString() {
        return name().toLowerCase();
    }

}