// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.spi.transport.ITransportProvider
package org.freedesktop.dbus.spi.transport;

import org.freedesktop.dbus.connections.BusAddress;
import org.freedesktop.dbus.connections.config.TransportConfig;
import org.freedesktop.dbus.connections.transports.AbstractTransport;

public interface ITransportProvider {

  public abstract String getTransportName();

  public abstract AbstractTransport createTransport(BusAddress arg0, TransportConfig arg1);

  public abstract String getSupportedBusType();

  public abstract String createDynamicSessionAddress(boolean arg0);

}