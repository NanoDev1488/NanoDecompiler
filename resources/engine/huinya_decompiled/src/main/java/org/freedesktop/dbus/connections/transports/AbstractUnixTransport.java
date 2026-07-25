// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.connections.transports.AbstractUnixTransport
package org.freedesktop.dbus.connections.transports;

import java.nio.channels.SocketChannel;
import org.freedesktop.dbus.connections.BusAddress;
import org.freedesktop.dbus.connections.config.TransportConfig;
import org.freedesktop.dbus.connections.transports.AbstractTransport;

public abstract class AbstractUnixTransport extends AbstractTransport {

  protected AbstractUnixTransport(BusAddress arg0, TransportConfig arg1) { // было: <init>
        super(arg0, arg1);
    }

  public abstract int getUid(SocketChannel arg0);

}