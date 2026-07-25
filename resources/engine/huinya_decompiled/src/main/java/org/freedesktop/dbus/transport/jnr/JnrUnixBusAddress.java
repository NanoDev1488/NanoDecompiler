// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.transport.jnr.JnrUnixBusAddress
package org.freedesktop.dbus.transport.jnr;

import java.nio.file.Path;
import java.util.Set;
import org.freedesktop.dbus.connections.BusAddress;
import org.freedesktop.dbus.connections.transports.IFileBasedBusAddress;
import org.freedesktop.dbus.utils.Util;

public class JnrUnixBusAddress extends BusAddress implements IFileBasedBusAddress {

  public JnrUnixBusAddress(BusAddress arg0) { // было: <init>
        super(arg0);
    }

  public boolean hasPath() {
        return hasParameter("path");
    }

  public String getAbstract() {
        return getParameterValue("abstract");
    }

  public boolean isAbstract() {
        return hasParameter("abstract");
    }

  public String getPath() {
        return getParameterValue("path");
    }

  public void updatePermissions(String arg0, String arg1, Set arg2) {
        Util.setFilePermissions(Path.of(getPath(), new String[0]), arg0, arg1, arg2);
    }

}