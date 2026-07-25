// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.connections.impl.DirectConnection
package org.freedesktop.dbus.connections.impl;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.freedesktop.dbus.DBusMatchRule;
import org.freedesktop.dbus.RemoteInvocationHandler;
import org.freedesktop.dbus.RemoteObject;
import org.freedesktop.dbus.connections.AbstractConnection;
import org.freedesktop.dbus.connections.BusAddress;
import org.freedesktop.dbus.connections.config.ReceivingServiceConfig;
import org.freedesktop.dbus.connections.config.TransportConfig;
import org.freedesktop.dbus.connections.transports.AbstractTransport;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.DBusSigHandler;
import org.freedesktop.dbus.interfaces.Introspectable;
import org.freedesktop.dbus.utils.AddressBuilder;
import org.freedesktop.dbus.utils.CommonRegexPattern;
import org.freedesktop.dbus.utils.DBusObjects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DirectConnection extends AbstractConnection {

    // ---- поля ----
  private final Logger logger;
  private final String machineId;

   DirectConnection(TransportConfig arg0, ReceivingServiceConfig arg1) { // было: <init>
        super(arg0, arg1);
        logger = LoggerFactory.getLogger(getClass());
        machineId = AddressBuilder.createMachineId();
        if (getAddress().isServer()) {
            return;
        }
        try {
            listen();
        } catch (IOException var3) {
            throw new DBusException(var3);
        }
    }

  public void listen() {
        if (getAddress().isServer()) {
            getTransport().listen();
        }
        super.listen();
    }

   DBusInterface dynamicProxy(String arg0, Class arg1) {
        DBusInterface __stk2;
        try {
            Introspectable var3 = ((Introspectable) getRemoteObject(arg0, Introspectable.class));
            String var4 = var3.Introspect();
            String[] var5 = CommonRegexPattern.PROXY_SPLIT_PATTERN.split(var4);
            List var6 = ((List) Arrays.stream(var5).filter(lp0 -> lambda$dynamicProxy$0(((String) lp0))).map(lp0 -> lambda$dynamicProxy$1(((String) lp0))).collect(Collectors.toList()));
            List var7 = findMatchingTypes(arg1, var6);
            if (!var7.isEmpty()) {
                RemoteObject var8 = new RemoteObject(null, arg0, arg1, false);
                DBusInterface var9 = ((DBusInterface) Proxy.newProxyInstance((((Class) var7.get(0))).getClassLoader(), ((Class[]) var7.toArray(new Class[0])), new RemoteInvocationHandler(this, var8)));
                getImportedObjects().put(var9, var8);
                __stk2 = var9;
            } else {
                throw new DBusException("Could not find an interface to cast to");
            }
        } catch (Exception e1) {
            Throwable var3 = e1;
            logger.debug("Error creating dynamic proxy", var3);
            throw new DBusException(String.format("Failed to create proxy object for %s; reason: %s.", new Object[]{arg0, var3.getMessage()}));
        }
    }

   DBusInterface getExportedObject(String arg0, Class arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aconst_null
        //      1: astore_3
        //      2: aload_0
        //      3: invokevirtual  #77 // org.freedesktop.dbus.connections.impl.DirectConnection.getExportedObjects:()Ljava/util/Map;
        //      6: dup
        //      7: astore  4
        //      9: monitorenter
        //     10: aload_0
        //     11: invokevirtual  #77 // org.freedesktop.dbus.connections.impl.DirectConnection.getExportedObjects:()Ljava/util/Map;
        //     14: aload_1
        //     15: invokeinterface  #104 // java.util.Map.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //     20: checkcast  #39 // org.freedesktop.dbus.messages.ExportedObject
        //     23: astore_3
        //     24: aload  4
        //     26: monitorexit
        //     27: goto  38 (offset +11)
        //     30: astore  5
        //     32: aload  4
        //     34: monitorexit
        //     35: aload  5
        //     37: athrow
        //     38: aconst_null
        //     39: aload_3
        //     40: if_acmpeq  61 (offset +21)
        //     43: aconst_null
        //     44: aload_3
        //     45: invokevirtual  #96 // org.freedesktop.dbus.messages.ExportedObject.getObject:()Ljava/lang/ref/Reference;
        //     48: invokevirtual  #60 // java.lang.ref.Reference.get:()Ljava/lang/Object;
        //     51: if_acmpne  61 (offset +10)
        //     54: aload_0
        //     55: aload_1
        //     56: invokevirtual  #92 // org.freedesktop.dbus.connections.impl.DirectConnection.unExportObject:(Ljava/lang/String;)V
        //     59: aconst_null
        //     60: astore_3
        //     61: aconst_null
        //     62: aload_3
        //     63: if_acmpeq  77 (offset +14)
        //     66: aload_3
        //     67: invokevirtual  #96 // org.freedesktop.dbus.messages.ExportedObject.getObject:()Ljava/lang/ref/Reference;
        //     70: invokevirtual  #60 // java.lang.ref.Reference.get:()Ljava/lang/Object;
        //     73: checkcast  #37 // org.freedesktop.dbus.interfaces.DBusInterface
        //     76: areturn
        //     77: aload_0
        //     78: aload_1
        //     79: aload_2
        //     80: invokevirtual  #73 // org.freedesktop.dbus.connections.impl.DirectConnection.dynamicProxy:(Ljava/lang/String;Ljava/lang/Class;)Lorg/freedesktop/dbus/interfaces/DBusInterface;
        //     83: areturn
        //       Exception table:
        //         from 10 to 27 target 30 type any
        //         from 30 to 35 target 30 type any
    }

  public DBusInterface getRemoteObject(String arg0) {
        if (null != arg0) {
            DBusObjects.requireObjectPath(arg0);
            return dynamicProxy(arg0, null);
        } else {
            throw new DBusException("Invalid object path: null");
        }
    }

  public DBusInterface getRemoteObject(String arg0, Class arg1) {
        if (null != arg0) {
            if (null != arg1) {
                DBusObjects.requireObjectPath(arg0);
                if (DBusInterface.class.isAssignableFrom(arg1)) {
                    if (!arg1.getName().equals(arg1.getSimpleName())) {
                        RemoteObject var3 = new RemoteObject(null, arg0, arg1, false);
                        DBusInterface var4 = ((DBusInterface) Proxy.newProxyInstance(arg1.getClassLoader(), new Class[]{arg1}, new RemoteInvocationHandler(this, var3)));
                        getImportedObjects().put(var4, var3);
                        return var4;
                    } else {
                        throw new DBusException("DBusInterfaces cannot be declared outside a package");
                    }
                } else {
                    throw new ClassCastException("Not A DBus Interface");
                }
            } else {
                throw new ClassCastException("Not A DBus Interface");
            }
        } else {
            throw new DBusException("Invalid object path: null");
        }
    }

  protected void removeSigHandler(DBusMatchRule arg0, DBusSigHandler arg1) {
        Queue var3 = ((Queue) getHandledSignals().get(arg0));
        if (var3 != null) {
            var3.remove(arg1);
            if (var3.isEmpty()) {
                getHandledSignals().remove(arg0);
            }
        }
    }

  protected AutoCloseable addSigHandler(DBusMatchRule arg0, DBusSigHandler arg1) {
        Queue var3 = ((Queue) getHandledSignals().computeIfAbsent(arg0, lp0 -> lambda$addSigHandler$2(((DBusMatchRule) lp0))));
        var3.add(arg1);
        return () -> lambda$addSigHandler$3(arg0, arg1);
    }

  protected void removeGenericSigHandler(DBusMatchRule arg0, DBusSigHandler arg1) {
        Queue var3 = ((Queue) getGenericHandledSignals().get(arg0));
        if (var3 != null) {
            var3.remove(arg1);
            if (var3.isEmpty()) {
                getGenericHandledSignals().remove(arg0);
            }
        }
    }

  protected AutoCloseable addGenericSigHandler(DBusMatchRule arg0, DBusSigHandler arg1) {
        Queue var3 = ((Queue) getGenericHandledSignals().computeIfAbsent(arg0, lp0 -> lambda$addGenericSigHandler$4(((DBusMatchRule) lp0))));
        var3.add(arg1);
        return () -> lambda$addGenericSigHandler$5(arg0, arg1);
    }

  public DBusInterface getExportedObject(String arg0, String arg1, Class arg2) {
        return getExportedObject(arg1, arg2);
    }

  public String getMachineId() {
        return machineId;
    }

  public DBusInterface getExportedObject(String arg0, String arg1) {
        return getExportedObject(arg1, ((Class) null));
    }

  private void lambda$addGenericSigHandler$5(DBusMatchRule arg0, DBusSigHandler arg1) {
        removeGenericSigHandler(arg0, arg1);
    }

  private static Queue lambda$addGenericSigHandler$4(DBusMatchRule arg0) {
        return new ConcurrentLinkedQueue();
    }

  private void lambda$addSigHandler$3(DBusMatchRule arg0, DBusSigHandler arg1) {
        removeSigHandler(arg0, arg1);
    }

  private static Queue lambda$addSigHandler$2(DBusMatchRule arg0) {
        return new ConcurrentLinkedQueue();
    }

  private static String lambda$dynamicProxy$1(String arg0) {
        return CommonRegexPattern.IFACE_PATTERN.matcher(arg0).replaceAll("$1");
    }

  private static boolean lambda$dynamicProxy$0(String arg0) {
        return arg0.startsWith("interface");
    }

}