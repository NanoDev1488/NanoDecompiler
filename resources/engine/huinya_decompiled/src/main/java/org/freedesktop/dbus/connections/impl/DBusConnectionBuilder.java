// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.connections.impl.DBusConnectionBuilder
package org.freedesktop.dbus.connections.impl;

import java.util.List;
import org.freedesktop.dbus.connections.AbstractConnection;
import org.freedesktop.dbus.connections.BusAddress;
import org.freedesktop.dbus.connections.impl.BaseConnectionBuilder;
import org.freedesktop.dbus.connections.impl.DBusConnection;
import org.freedesktop.dbus.connections.impl.DBusConnection_DBusBusType;
import org.freedesktop.dbus.connections.transports.TransportBuilder;
import org.freedesktop.dbus.exceptions.AddressResolvingException;
import org.freedesktop.dbus.utils.AddressBuilder;

public final class DBusConnectionBuilder extends BaseConnectionBuilder {

    // ---- поля ----
  private final String machineId;
  private boolean shared;

  private DBusConnectionBuilder(BusAddress arg0, String arg1) { // было: <init>
        super(DBusConnectionBuilder.class, arg0);
        shared = true;
        machineId = arg1;
    }

  public static DBusConnectionBuilder forSessionBus(String arg0) {
        BusAddress var1 = validateTransportAddress(AddressBuilder.getSessionConnection(arg0));
        return new DBusConnectionBuilder(var1, AddressBuilder.getDbusMachineId(arg0));
    }

  public static DBusConnectionBuilder forSystemBus() {
        BusAddress var0 = validateTransportAddress(AddressBuilder.getSystemConnection());
        return new DBusConnectionBuilder(var0, AddressBuilder.getDbusMachineId(null));
    }

  public static DBusConnectionBuilder forSessionBus() {
        return forSessionBus(null);
    }

  public static DBusConnectionBuilder forType(DBusConnection_DBusBusType arg0) {
        return forType(arg0, null);
    }

  public static DBusConnectionBuilder forType(DBusConnection_DBusBusType arg0, String arg1) {
        if (arg0 != DBusConnection_DBusBusType.SESSION) {
            if (arg0 != DBusConnection_DBusBusType.SYSTEM) {
                throw new IllegalArgumentException("Unknown bus type: " + String.valueOf(arg0));
            } else {
                return forSystemBus();
            }
        } else {
            return forSessionBus(arg1);
        }
    }

  public static DBusConnectionBuilder forAddress(String arg0) {
        return new DBusConnectionBuilder(BusAddress.of(arg0), AddressBuilder.getDbusMachineId(null));
    }

  public static DBusConnectionBuilder forAddress(BusAddress arg0) {
        return new DBusConnectionBuilder(arg0, AddressBuilder.getDbusMachineId(null));
    }

  private static BusAddress validateTransportAddress(BusAddress arg0) {
        if (!TransportBuilder.getRegisteredBusTypes().isEmpty()) {
            BusAddress var1 = arg0;
            if (TransportBuilder.getRegisteredBusTypes().contains("UNIX")) {
                if (TransportBuilder.getRegisteredBusTypes().contains("TCP")) {
                    return var1;
                } else {
                    if (var1 == null) {
                        return var1;
                    } else {
                        if (!var1.isBusType("TCP")) {
                            return var1;
                        } else {
                            throw new AddressResolvingException("No transports found to handle TCP connections. Please add a TCP transport provider to your classpath");
                        }
                    }
                }
            } else {
                if (var1 == null) {
                    if (TransportBuilder.getRegisteredBusTypes().contains("TCP")) {
                        return var1;
                    } else {
                        if (var1 == null) {
                            return var1;
                        } else {
                            if (!var1.isBusType("TCP")) {
                                return var1;
                            } else {
                                throw new AddressResolvingException("No transports found to handle TCP connections. Please add a TCP transport provider to your classpath");
                            }
                        }
                    }
                } else {
                    if (!var1.isBusType("UNIX")) {
                        if (TransportBuilder.getRegisteredBusTypes().contains("TCP")) {
                            return var1;
                        } else {
                            if (var1 == null) {
                                return var1;
                            } else {
                                if (!var1.isBusType("TCP")) {
                                    return var1;
                                } else {
                                    throw new AddressResolvingException("No transports found to handle TCP connections. Please add a TCP transport provider to your classpath");
                                }
                            }
                        }
                    } else {
                        throw new AddressResolvingException("No transports found to handle UNIX socket connections. Please add a unix-socket transport provider to your classpath");
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("No transports found to connect to DBus. Please add at least one transport provider to your classpath");
        }
    }

  public DBusConnectionBuilder withShared(boolean arg0) {
        shared = arg0;
        return this;
    }

  public DBusConnection build() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokevirtual  #46 // org.freedesktop.dbus.connections.impl.DBusConnectionBuilder.buildThreadConfig:()Lorg/freedesktop/dbus/connections/config/ReceivingServiceConfig;
        //      4: astore_1
        //      5: aload_0
        //      6: invokevirtual  #47 // org.freedesktop.dbus.connections.impl.DBusConnectionBuilder.buildTransportConfig:()Lorg/freedesktop/dbus/connections/config/TransportConfig;
        //      9: astore_2
        //     10: aload_0
        //     11: getfield  #29 // org.freedesktop.dbus.connections.impl.DBusConnectionBuilder.shared:Z
        //     14: ifeq  104 (offset +90)
        //     17: getstatic  #24 // org.freedesktop.dbus.connections.impl.DBusConnection.CONNECTIONS:Ljava/util/concurrent/ConcurrentMap;
        //     20: dup
        //     21: astore  4
        //     23: monitorenter
        //     24: aload_2
        //     25: invokevirtual  #37 // org.freedesktop.dbus.connections.config.TransportConfig.getBusAddress:()Lorg/freedesktop/dbus/connections/BusAddress;
        //     28: invokevirtual  #36 // org.freedesktop.dbus.connections.BusAddress.toString:()Ljava/lang/String;
        //     31: astore  5
        //     33: aload_0
        //     34: aload  5
        //     36: invokevirtual  #52 // org.freedesktop.dbus.connections.impl.DBusConnectionBuilder.getSharedConnection:(Ljava/lang/String;)Lorg/freedesktop/dbus/connections/impl/DBusConnection;
        //     39: astore_3
        //     40: aload_3
        //     41: ifnull  57 (offset +16)
        //     44: aload_3
        //     45: getfield  #25 // org.freedesktop.dbus.connections.impl.DBusConnection.concurrentConnections:Ljava/util/concurrent/atomic/AtomicInteger;
        //     48: invokevirtual  #33 // java.util.concurrent.atomic.AtomicInteger.incrementAndGet:()I
        //     51: pop
        //     52: aload_3
        //     53: aload  4
        //     55: monitorexit
        //     56: areturn
        //     57: new  #18 // org.freedesktop.dbus.connections.impl.DBusConnection
        //     60: dup
        //     61: aload_0
        //     62: getfield  #29 // org.freedesktop.dbus.connections.impl.DBusConnectionBuilder.shared:Z
        //     65: aload_0
        //     66: getfield  #28 // org.freedesktop.dbus.connections.impl.DBusConnectionBuilder.machineId:Ljava/lang/String;
        //     69: aload_2
        //     70: aload_1
        //     71: invokespecial  #39 // org.freedesktop.dbus.connections.impl.DBusConnection.<init>:(ZLjava/lang/String;Lorg/freedesktop/dbus/connections/config/TransportConfig;Lorg/freedesktop/dbus/connections/config/ReceivingServiceConfig;)V
        //     74: astore_3
        //     75: getstatic  #24 // org.freedesktop.dbus.connections.impl.DBusConnection.CONNECTIONS:Ljava/util/concurrent/ConcurrentMap;
        //     78: aload  5
        //     80: aload_3
        //     81: invokeinterface  #63 // java.util.concurrent.ConcurrentMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;, count 3
        //     86: pop
        //     87: aload  4
        //     89: monitorexit
        //     90: goto  101 (offset +11)
        //     93: astore  6
        //     95: aload  4
        //     97: monitorexit
        //     98: aload  6
        //    100: athrow
        //    101: goto  122 (offset +21)
        //    104: new  #18 // org.freedesktop.dbus.connections.impl.DBusConnection
        //    107: dup
        //    108: aload_0
        //    109: getfield  #29 // org.freedesktop.dbus.connections.impl.DBusConnectionBuilder.shared:Z
        //    112: aload_0
        //    113: getfield  #28 // org.freedesktop.dbus.connections.impl.DBusConnectionBuilder.machineId:Ljava/lang/String;
        //    116: aload_2
        //    117: aload_1
        //    118: invokespecial  #39 // org.freedesktop.dbus.connections.impl.DBusConnection.<init>:(ZLjava/lang/String;Lorg/freedesktop/dbus/connections/config/TransportConfig;Lorg/freedesktop/dbus/connections/config/ReceivingServiceConfig;)V
        //    121: astore_3
        //    122: aload_3
        //    123: aload_0
        //    124: invokevirtual  #51 // org.freedesktop.dbus.connections.impl.DBusConnectionBuilder.getDisconnectCallback:()Lorg/freedesktop/dbus/connections/IDisconnectCallback;
        //    127: invokevirtual  #42 // org.freedesktop.dbus.connections.impl.DBusConnection.setDisconnectCallback:(Lorg/freedesktop/dbus/connections/IDisconnectCallback;)V
        //    130: aload_3
        //    131: aload_0
        //    132: invokevirtual  #53 // org.freedesktop.dbus.connections.impl.DBusConnectionBuilder.isWeakReference:()Z
        //    135: invokevirtual  #43 // org.freedesktop.dbus.connections.impl.DBusConnection.setWeakReferences:(Z)V
        //    138: aload_3
        //    139: invokevirtual  #40 // org.freedesktop.dbus.connections.impl.DBusConnection.connectImpl:()V
        //    142: aload_3
        //    143: areturn
        //       Exception table:
        //         from 24 to 56 target 93 type any
        //         from 57 to 90 target 93 type any
        //         from 93 to 98 target 93 type any
    }

  private DBusConnection getSharedConnection(String arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: getstatic  #24 // org.freedesktop.dbus.connections.impl.DBusConnection.CONNECTIONS:Ljava/util/concurrent/ConcurrentMap;
        //      3: dup
        //      4: astore_2
        //      5: monitorenter
        //      6: getstatic  #24 // org.freedesktop.dbus.connections.impl.DBusConnection.CONNECTIONS:Ljava/util/concurrent/ConcurrentMap;
        //      9: aload_1
        //     10: invokeinterface  #62 // java.util.concurrent.ConcurrentMap.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //     15: checkcast  #18 // org.freedesktop.dbus.connections.impl.DBusConnection
        //     18: astore_3
        //     19: aload_3
        //     20: ifnull  48 (offset +28)
        //     23: aload_3
        //     24: invokevirtual  #41 // org.freedesktop.dbus.connections.impl.DBusConnection.isConnected:()Z
        //     27: ifne  44 (offset +17)
        //     30: getstatic  #24 // org.freedesktop.dbus.connections.impl.DBusConnection.CONNECTIONS:Ljava/util/concurrent/ConcurrentMap;
        //     33: aload_1
        //     34: invokeinterface  #64 // java.util.concurrent.ConcurrentMap.remove:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //     39: pop
        //     40: aconst_null
        //     41: aload_2
        //     42: monitorexit
        //     43: areturn
        //     44: aload_3
        //     45: aload_2
        //     46: monitorexit
        //     47: areturn
        //     48: aload_2
        //     49: monitorexit
        //     50: goto  60 (offset +10)
        //     53: astore  4
        //     55: aload_2
        //     56: monitorexit
        //     57: aload  4
        //     59: athrow
        //     60: aconst_null
        //     61: areturn
        //       Exception table:
        //         from 6 to 43 target 53 type any
        //         from 44 to 47 target 53 type any
        //         from 48 to 50 target 53 type any
        //         from 53 to 57 target 53 type any
    }

  public AbstractConnection build() {
        return build();
    }

}