// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.connections.base.GlobalHandler
package org.freedesktop.dbus.connections.base;

import org.freedesktop.dbus.connections.base.AbstractConnectionBase;
import org.freedesktop.dbus.connections.base.FallbackContainer;
import org.freedesktop.dbus.errors.UnknownObject;
import org.freedesktop.dbus.interfaces.Introspectable;
import org.freedesktop.dbus.interfaces.Peer;
import org.freedesktop.dbus.messages.ExportedObject;
import org.freedesktop.dbus.messages.ObjectTree;

public class GlobalHandler implements Introspectable, Peer {

    // ---- поля ----
  private final AbstractConnectionBase connection;
  private final String objectpath;

   GlobalHandler(AbstractConnectionBase arg0) { // было: <init>
        super();
        connection = arg0;
        objectpath = null;
    }

   GlobalHandler(AbstractConnectionBase arg0, String arg1) { // было: <init>
        super();
        connection = arg0;
        objectpath = arg1;
    }

  public boolean isRemote() {
        return false;
    }

  public void Ping() {
        // (пустое тело)
    }

  public String Introspect() {
        String var1 = connection.getObjectTree().Introspect(objectpath);
        if (null == var1) {
            ExportedObject var2 = connection.getFallbackContainer().get(objectpath);
            if (null != var2) {
                var1 = var2.getIntrospectiondata();
            }
        }
        if (null != var1) {
            return "<!DOCTYPE node PUBLIC \"-//freedesktop//DTD D-BUS Object Introspection 1.0//EN\" \"http://www.freedesktop.org/standards/dbus/1.0/introspect.dtd\">\n" + var1;
        } else {
            throw new UnknownObject("Introspecting on non-existant object");
        }
    }

  public String getObjectPath() {
        return objectpath;
    }

  public String GetMachineId() {
        return connection.getMachineId();
    }

}