// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.connections.impl.BaseConnectionBuilder
package org.freedesktop.dbus.connections.impl;

import java.nio.ByteOrder;
import org.freedesktop.dbus.connections.AbstractConnection;
import org.freedesktop.dbus.connections.BusAddress;
import org.freedesktop.dbus.connections.IDisconnectCallback;
import org.freedesktop.dbus.connections.config.ReceivingServiceConfig;
import org.freedesktop.dbus.connections.config.ReceivingServiceConfigBuilder;
import org.freedesktop.dbus.connections.config.TransportConfig;
import org.freedesktop.dbus.connections.config.TransportConfigBuilder;

public abstract class BaseConnectionBuilder {

    // ---- поля ----
  private final Class returnType;
  private boolean weakReference;
  private IDisconnectCallback disconnectCallback;
  private final ReceivingServiceConfigBuilder rsConfigBuilder;
  private final TransportConfigBuilder transportConfigBuilder;

  protected BaseConnectionBuilder(Class arg0, BusAddress arg1) { // было: <init>
        super();
        weakReference = false;
        returnType = arg0;
        rsConfigBuilder = new ReceivingServiceConfigBuilder(() -> self());
        transportConfigBuilder = new TransportConfigBuilder(() -> self());
        transportConfigBuilder.withBusAddress(arg1);
    }

   BaseConnectionBuilder self() {
        return ((BaseConnectionBuilder) returnType.cast(this));
    }

  protected ReceivingServiceConfig buildThreadConfig() {
        return rsConfigBuilder.build();
    }

  protected TransportConfig buildTransportConfig() {
        return transportConfigBuilder.build();
    }

  protected boolean isWeakReference() {
        return weakReference;
    }

  protected IDisconnectCallback getDisconnectCallback() {
        return disconnectCallback;
    }

  public ReceivingServiceConfigBuilder receivingThreadConfig() {
        return rsConfigBuilder;
    }

  public TransportConfigBuilder transportConfig() {
        return transportConfigBuilder;
    }

  public BaseConnectionBuilder withWeakReferences(boolean arg0) {
        weakReference = arg0;
        return self();
    }

  public BaseConnectionBuilder withDisconnectCallback(IDisconnectCallback arg0) {
        disconnectCallback = arg0;
        return self();
    }

  public abstract AbstractConnection build();

  public static byte getSystemEndianness() {
        return !ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN) ? 108 : 66;
    }

}