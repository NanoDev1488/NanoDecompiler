// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.interfaces.Properties.PropertiesChanged
package org.freedesktop.dbus.interfaces;

import java.util.List;
import java.util.Map;
import org.freedesktop.dbus.messages.DBusSignal;

public class Properties_PropertiesChanged extends DBusSignal {

    // ---- поля ----
  private final Map propertiesChanged;
  private final List propertiesRemoved;
  private final String interfaceName;

  public Properties_PropertiesChanged(String arg0, String arg1, Map arg2, List arg3) { // было: <init>
        super(arg0, new Object[]{arg1, arg2, arg3});
        propertiesChanged = arg2;
        propertiesRemoved = arg3;
        interfaceName = arg1;
    }

  public String getInterfaceName() {
        return interfaceName;
    }

  public Map getPropertiesChanged() {
        return propertiesChanged;
    }

  public List getPropertiesRemoved() {
        return propertiesRemoved;
    }

  public String toString() {
        return getClass().getSimpleName() + "[propertiesChanged=" + String.valueOf(propertiesChanged) + ", propertiesRemoved=" + String.valueOf(propertiesRemoved) + ", interfaceName='" + interfaceName + "']";
    }

}