// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.transport.jnr.JnrUnixSocketTransportProvider
package org.freedesktop.dbus.transport.jnr;

import org.freedesktop.dbus.connections.BusAddress;
import org.freedesktop.dbus.connections.config.TransportConfig;
import org.freedesktop.dbus.connections.transports.AbstractTransport;
import org.freedesktop.dbus.spi.transport.ITransportProvider;
import org.freedesktop.dbus.transport.jnr.JnrUnixBusAddress;
import org.freedesktop.dbus.transport.jnr.UnixSocketTransport;

public class JnrUnixSocketTransportProvider implements ITransportProvider {

  public JnrUnixSocketTransportProvider() { // было: <init>
        super();
    }

  public String getTransportName() {
        return "dbus-java-transport-jnr-unixsocket";
    }

  public AbstractTransport createTransport(BusAddress arg0, TransportConfig arg1) {
        Object var3 = null;
        if (!(arg0 instanceof JnrUnixBusAddress)) {
            var3 = new JnrUnixBusAddress(arg0);
        } else {
            JnrUnixBusAddress var4 = ((JnrUnixBusAddress) arg0);
            var3 = var4;
        }
        return new UnixSocketTransport(((JnrUnixBusAddress) var3), arg1);
    }

  public String getSupportedBusType() {
        return "UNIX";
    }

  public String createDynamicSessionAddress(boolean arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: iload_1
        //      1: invokestatic  #13 // org.freedesktop.dbus.utils.Util.isFreeBsd:()Z
        //      4: ifne  13 (offset +9)
        //      7: invokestatic  #14 // org.freedesktop.dbus.utils.Util.isMacOs:()Z
        //     10: ifeq  17 (offset +7)
        //     13: iconst_1
        //     14: goto  18 (offset +4)
        //     17: iconst_0
        //     18: invokestatic  #12 // org.freedesktop.dbus.utils.Util.createDynamicSessionAddress:(ZZ)Ljava/lang/String;
        //     21: areturn
    }

}