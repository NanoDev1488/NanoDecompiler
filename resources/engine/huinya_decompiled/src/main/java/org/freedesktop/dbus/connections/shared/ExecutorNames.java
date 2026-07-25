// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.connections.shared.ExecutorNames
package org.freedesktop.dbus.connections.shared;

public enum ExecutorNames {

    SIGNAL("SignalExecutor"),
    ERROR("ErrorExecutor"),
    METHODCALL("MethodCallExecutor"),
    METHODRETURN("MethodReturnExecutor");

    // ---- поля ----
  private final String description;

  private ExecutorNames(String arg2) { // было: <init>
        description = arg2;
    }

  public String getDescription() {
        return description;
    }

  public String toString() {
        return description;
    }

}