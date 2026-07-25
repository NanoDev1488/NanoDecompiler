// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.connections.shared.IThreadPoolRetryHandler
package org.freedesktop.dbus.connections.shared;

import org.freedesktop.dbus.connections.shared.ExecutorNames;

@FunctionalInterface
public interface IThreadPoolRetryHandler {

  public abstract boolean handle(ExecutorNames arg0, Exception arg1);

}