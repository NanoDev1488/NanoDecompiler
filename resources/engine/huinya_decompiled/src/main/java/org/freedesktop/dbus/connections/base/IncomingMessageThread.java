// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.connections.base.IncomingMessageThread
package org.freedesktop.dbus.connections.base;

import java.io.IOException;
import java.util.Objects;
import org.freedesktop.dbus.connections.BusAddress;
import org.freedesktop.dbus.connections.base.ConnectionMessageHandler;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.interfaces.FatalException;
import org.freedesktop.dbus.messages.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IncomingMessageThread extends Thread {

    // ---- поля ----
  private final Logger logger;
  private volatile boolean terminate;
  private final ConnectionMessageHandler connection;

  public IncomingMessageThread(ConnectionMessageHandler arg0, BusAddress arg1) { // было: <init>
        super();
        logger = LoggerFactory.getLogger(getClass());
        connection = ((ConnectionMessageHandler) Objects.requireNonNull(arg0));
        setName("DBusConnection [listener=" + arg1.isListeningSocket() + "]");
        setDaemon(true);
    }

  public void terminate() {
        terminate = true;
        interrupt();
    }

  public void run() {
        while (true) {
            Object var1;
            if (terminate) {
                return;
            } else {
                var1 = null;
            }
            try {
                Message var1 = connection.readIncoming();
                if (var1 != null) {
                    logger.trace("Read message from {}: {}", connection.getTransport(), var1);
                    connection.handleMessage(((Message) var1));
                }
            } catch (DBusException var2) {
                if (var2 instanceof FatalException) {
                    break;
                }
                if (!terminate) {
                    logger.error("Exception in connection thread", var2);
                }
                continue;
            }
        }
        if (!terminate) {
            logger.error("FatalException in connection thread", var2);
            if (connection.isConnected()) {
                terminate = true;
                Throwable var4 = var2.getCause();
                if (!(var4 instanceof IOException)) {
                    connection.internalDisconnect(null);
                } else {
                    IOException var3 = ((IOException) var4);
                    connection.internalDisconnect(var3);
                }
            }
            return;
        } else {
            return;
        }
    }

}