// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.interfaces.DBus
package org.freedesktop.dbus.interfaces;

import java.util.Map;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.types.UInt32;

@DBusInterfaceName("org.freedesktop.DBus")
public interface DBus extends DBusInterface {

    // ---- поля ----
  public static final int DBUS_NAME_FLAG_ALLOW_REPLACEMENT = 1;
  public static final int DBUS_NAME_FLAG_REPLACE_EXISTING = 2;
  public static final int DBUS_NAME_FLAG_DO_NOT_QUEUE = 4;
  public static final int DBUS_REQUEST_NAME_REPLY_PRIMARY_OWNER = 1;
  public static final int DBUS_REQUEST_NAME_REPLY_IN_QUEUE = 2;
  public static final int DBUS_REQUEST_NAME_REPLY_EXISTS = 3;
  public static final int DBUS_REQUEST_NAME_REPLY_ALREADY_OWNER = 4;
  public static final int DBUS_RELEASE_NAME_REPLY_RELEASED = 1;
  public static final int DBUS_RELEASE_NAME_REPLY_NON_EXISTANT = 2;
  public static final int DBUS_RELEASE_NAME_REPLY_NOT_OWNER = 3;
  public static final int DBUS_START_REPLY_SUCCESS = 1;
  public static final int DBUS_START_REPLY_ALREADY_RUNNING = 2;

  public abstract String Hello();

  public abstract UInt32 RequestName(String arg0, UInt32 arg1);

  public abstract UInt32 ReleaseName(String arg0);

  public abstract String[] ListQueuedOwners(String arg0);

  public abstract String[] ListNames();

  public abstract String[] ListActivatableNames();

  public abstract boolean NameHasOwner(String arg0);

  public abstract UInt32 StartServiceByName(String arg0, UInt32 arg1);

  public abstract void UpdateActivationEnvironment(Map[] arg0);

  public abstract String GetNameOwner(String arg0);

  public abstract UInt32 GetConnectionUnixUser(String arg0);

  public abstract UInt32 GetConnectionUnixProcessID(String arg0);

  public abstract Map GetConnectionCredentials(String arg0);

  public abstract Byte[] GetAdtAuditSessionData(String arg0);

  public abstract Byte[] GetConnectionSELinuxSecurityContext(String arg0);

  public abstract void AddMatch(String arg0);

  public abstract void RemoveMatch(String arg0);

  public abstract String GetId();

}