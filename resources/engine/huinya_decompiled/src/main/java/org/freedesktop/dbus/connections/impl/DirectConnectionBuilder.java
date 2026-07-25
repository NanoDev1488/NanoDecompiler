// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.connections.impl.DirectConnectionBuilder
package org.freedesktop.dbus.connections.impl;

import org.freedesktop.dbus.connections.AbstractConnection;
import org.freedesktop.dbus.connections.BusAddress;
import org.freedesktop.dbus.connections.config.ReceivingServiceConfig;
import org.freedesktop.dbus.connections.config.TransportConfig;
import org.freedesktop.dbus.connections.impl.BaseConnectionBuilder;
import org.freedesktop.dbus.connections.impl.DirectConnection;

public final class DirectConnectionBuilder extends BaseConnectionBuilder {

  private DirectConnectionBuilder(BusAddress arg0) { // было: <init>
        super(DirectConnectionBuilder.class, arg0);
    }

  public static DirectConnectionBuilder forAddress(String arg0) {
        return new DirectConnectionBuilder(BusAddress.of(arg0));
    }

  public DirectConnection build() {
        ReceivingServiceConfig var1 = buildThreadConfig();
        TransportConfig var2 = buildTransportConfig();
        DirectConnection var3 = new DirectConnection(var2, var1);
        var3.setDisconnectCallback(getDisconnectCallback());
        var3.setWeakReferences(isWeakReference());
        return var3;
    }

  public AbstractConnection build() {
        return build();
    }

}