// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.bin.DBusDaemon.DBusDaemonReaderThread
package org.freedesktop.dbus.bin;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import org.freedesktop.dbus.bin.DBusDaemon;
import org.freedesktop.dbus.bin.DBusDaemon_ConnectionStruct;
import org.freedesktop.dbus.bin.DBusDaemon_Pair;
import org.freedesktop.dbus.connections.transports.TransportConnection;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.interfaces.FatalException;
import org.freedesktop.dbus.messages.Message;
import org.freedesktop.dbus.spi.message.IMessageReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBusDaemon_DBusDaemonReaderThread extends Thread {

    // ---- поля ----
  private final Logger logger;
  private DBusDaemon_ConnectionStruct conn;
  private final WeakReference weakconn;
  private final AtomicBoolean running;
  final DBusDaemon this$0;

  public DBusDaemon_DBusDaemonReaderThread(DBusDaemon arg0, DBusDaemon_ConnectionStruct arg1) { // было: <init>
        super();
        this$0 = arg0;
        logger = LoggerFactory.getLogger(getClass());
        running = new AtomicBoolean(false);
        conn = arg1;
        weakconn = new WeakReference(arg1);
        setName(getClass().getSimpleName());
    }

  public void terminate() {
        running.set(false);
    }

  public void run() {
        logger.debug(">>>> Reader Thread started <<<<");
        running.set(true);
        while (this$0.isRunning()) {
            if (!running.get()) {
                break;
            }
            Object var1 = null;
            try {
                var1 = conn.connection.getReader().readMessage();
            } catch (IOException var2) {
                DBusDaemon.LOGGER.debug("Error reading message", var2);
                this$0.removeConnection(conn);
            } catch (DBusException e2) {
                Throwable var2 = e2;
                DBusDaemon.LOGGER.debug("", var2);
                if (var2 instanceof FatalException) {
                    this$0.removeConnection(conn);
                }
            }
            if (null != var1) {
                DBusDaemon.logMessage("Read {} from {}", ((Message) var1), conn.unique);
                this$0.inqueue.add(new DBusDaemon_Pair(var1, weakconn));
            }
            continue;
        }
        conn = null;
        logger.debug(">>>> Reader Thread terminated <<<<");
    }

}