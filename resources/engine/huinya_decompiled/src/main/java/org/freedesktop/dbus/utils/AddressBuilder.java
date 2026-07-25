// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.utils.AddressBuilder
package org.freedesktop.dbus.utils;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Stream;
import org.freedesktop.dbus.connections.BusAddress;
import org.freedesktop.dbus.exceptions.AddressResolvingException;
import org.freedesktop.dbus.utils.Hexdump;
import org.freedesktop.dbus.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class AddressBuilder {

  private AddressBuilder() { // было: <init>
        super();
    }

  public static BusAddress getSystemConnection() {
        String var0 = System.getenv("DBUS_SYSTEM_BUS_ADDRESS");
        if (var0 == null) {
            var0 = "unix:path=/var/run/dbus/system_bus_socket";
        }
        return BusAddress.of(var0);
    }

  public static BusAddress getSessionConnection(String arg0) {
        String var1 = System.getProperty("DBUS_SESSION_BUS_ADDRESS");
        if (var1 == null) {
            var1 = !Util.isMacOs() ? System.getenv("DBUS_SESSION_BUS_ADDRESS") : "unix:path=" + System.getenv("DBUS_LAUNCHD_SESSION_BUS_SOCKET");
        }
        if (var1 != null) {
            return BusAddress.of(var1);
        } else {
            String var2 = System.getenv("DISPLAY");
            if (var2 != null) {
                if (var2.charAt(0) != 58) {
                    if (var2.contains(":")) {
                        var2 = var2.substring(var2.indexOf(58));
                    }
                }
                String var3 = getDbusMachineId(arg0);
                String var4 = System.getProperty("user.home");
                File var5 = new File(var4 + "/.dbus/session-bus", var3 + "-" + var2.replaceAll(":([0-9]*)\\..*", "$1"));
                if (var5.exists()) {
                    Properties var6 = Util.readProperties(var5);
                    if (var6 != null) {
                        String var7 = var6.getProperty("DBUS_SESSION_BUS_ADDRESS");
                        if (!Util.isEmpty(var7)) {
                            if (var7.matches("^'[^']+'$")) {
                                var7 = var7.replaceFirst("^'([^']+)'$", "$1");
                            }
                            return BusAddress.of(var7);
                        } else {
                            throw new AddressResolvingException("Cannot Resolve Session Bus Address: No session information found in " + String.valueOf(var5));
                        }
                    } else {
                        throw new AddressResolvingException("Cannot Resolve Session Bus Address: Unable to read " + String.valueOf(var5));
                    }
                } else {
                    throw new AddressResolvingException("Cannot Resolve Session Bus Address: " + String.valueOf(var5) + " not found");
                }
            } else {
                throw new AddressResolvingException("Cannot Resolve Session Bus Address: DISPLAY variable not set");
            }
        }
    }

  public static String getDbusMachineId(String arg0) {
        File var1 = determineMachineIdFile(arg0);
        if (var1 == null) {
            if (Util.isWindows()) {
                return getFakeDbusMachineId();
            } else {
                if (!Util.isMacOs()) {
                    throw new AddressResolvingException("Cannot Resolve Session Bus Address: MachineId file can not be found");
                } else {
                    return getFakeDbusMachineId();
                }
            }
        } else {
            String var2 = Util.readFileToString(var1);
            if (var2.length() <= 0) {
                throw new AddressResolvingException("Cannot Resolve Session Bus Address: MachineId file is empty.");
            } else {
                return var2;
            }
        }
    }

  private static File determineMachineIdFile(String arg0) {
        List var1 = Arrays.asList(new String[]{System.getenv("DBUS_MACHINE_ID_LOCATION"), arg0, "/var/lib/dbus/machine-id", "/usr/local/var/lib/dbus/machine-id", "/etc/machine-id"});
        return ((File) var1.stream().filter(lp0 -> lambda$determineMachineIdFile$0(((String) lp0))).map(lp0 -> new File(((String) lp0))).filter(lp0 -> lambda$determineMachineIdFile$1(((File) lp0))).findFirst().orElse(null));
    }

  private static String getFakeDbusMachineId() {
        return String.format("%s@%s", new Object[]{Util.getCurrentUser(), Util.getHostName()});
    }

  public static String createMachineId() {
        String __stk1;
        try {
            String var0 = Hexdump.toAscii(MessageDigest.getInstance("MD5").digest(InetAddress.getLocalHost().getHostName().getBytes()));
            __stk1 = var0;
        } catch (NoSuchAlgorithmException var1) {
            LoggerFactory.getLogger(AddressBuilder.class).trace("MD5 algorithm not present", var1);
        } catch (UnknownHostException e2) {
            Throwable var1 = e2;
            LoggerFactory.getLogger(AddressBuilder.class).trace("Unable to determine this machines hostname", var1);
        }
    }

  private static boolean lambda$determineMachineIdFile$1(File arg0) {
        return !arg0.exists() ? 0 : arg0.length() > 0L;
    }

  private static boolean lambda$determineMachineIdFile$0(String arg0) {
        return arg0 != null;
    }

}