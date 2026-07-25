// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.connections.IDisconnectCallback
package org.freedesktop.dbus.connections;

import java.io.IOException;

public interface IDisconnectCallback {

  public void disconnectOnError(IOException arg0) {
        // (пустое тело)
    }

  public void requestedDisconnect(Integer arg0) {
        // (пустое тело)
    }

  public void clientDisconnect() {
        // (пустое тело)
    }

  public void exceptionOnTerminate(IOException arg0) {
        // (пустое тело)
    }

}