// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.connections.impl.DBusConnection
package org.freedesktop.dbus.connections.impl;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.freedesktop.dbus.DBusMatchRule;
import org.freedesktop.dbus.RemoteInvocationHandler;
import org.freedesktop.dbus.RemoteObject;
import org.freedesktop.dbus.connections.AbstractConnection;
import org.freedesktop.dbus.connections.IDisconnectCallback;
import org.freedesktop.dbus.connections.config.ReceivingServiceConfig;
import org.freedesktop.dbus.connections.config.TransportConfig;
import org.freedesktop.dbus.connections.impl.DBusConnection_Anon1;
import org.freedesktop.dbus.connections.impl.DBusConnection_Anon2;
import org.freedesktop.dbus.connections.impl.DBusConnection_Anon3;
import org.freedesktop.dbus.connections.impl.DBusConnection_Anon4;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.exceptions.DBusExecutionException;
import org.freedesktop.dbus.exceptions.InvalidBusNameException;
import org.freedesktop.dbus.exceptions.NotConnected;
import org.freedesktop.dbus.interfaces.DBus;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.DBusSigHandler;
import org.freedesktop.dbus.interfaces.Introspectable;
import org.freedesktop.dbus.messages.DBusSignal;
import org.freedesktop.dbus.types.UInt32;
import org.freedesktop.dbus.utils.CommonRegexPattern;
import org.freedesktop.dbus.utils.DBusObjects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DBusConnection extends AbstractConnection {

    // ---- поля ----
  static final ConcurrentMap CONNECTIONS;
  private final Logger logger;
  private final List busnames;
  private final String machineId;
  private DBus dbus;
  private boolean registered;
  final AtomicInteger concurrentConnections;
  private final boolean shared;

    static {
        CONNECTIONS = new ConcurrentHashMap();
    }

   DBusConnection(boolean arg0, String arg1, TransportConfig arg2, ReceivingServiceConfig arg3) { // было: <init>
        super(arg2, arg3);
        logger = LoggerFactory.getLogger(getClass());
        concurrentConnections = new AtomicInteger(1);
        busnames = new ArrayList();
        machineId = arg1;
        shared = arg0;
    }

  private AtomicInteger getConcurrentConnections() {
        return concurrentConnections;
    }

   void connectImpl() {
        try {
            listen();
        } catch (IOException var1) {
            throw new DBusException(var1);
        }
    }

  public void register() {
        if (!registered) {
            dbus = ((DBus) getRemoteObject("org.freedesktop.DBus", "/org/freedesktop/DBus", DBus.class));
        } else {
            return;
        }
        try {
            busnames.add(dbus.Hello());
            registered = true;
        } catch (DBusExecutionException var1) {
            logger.debug("Error while doing 'Hello' handshake", var1);
            throw new DBusException(var1.getMessage(), var1);
        }
    }

  public DBusInterface dynamicProxy(String arg0, String arg1, Class arg2) {
        DBusInterface __stk2;
        logger.debug("Introspecting {} on {} for dynamic proxy creation", arg1, arg0);
        try {
            Introspectable var4 = ((Introspectable) getRemoteObject(arg0, arg1, Introspectable.class));
            String var5 = var4.Introspect();
            logger.trace("Got introspection data: {}", var5);
            String[] var6 = CommonRegexPattern.PROXY_SPLIT_PATTERN.split(var5);
            List var7 = ((List) Arrays.stream(var6).filter(lp0 -> lambda$dynamicProxy$0(((String) lp0))).map(lp0 -> lambda$dynamicProxy$1(((String) lp0))).map(lp0 -> lambda$dynamicProxy$2(((String) lp0))).collect(Collectors.toList()));
            List var8 = findMatchingTypes(arg2, var7);
            if (!var8.isEmpty()) {
                RemoteObject var9 = new RemoteObject(arg0, arg1, arg2, false);
                DBusInterface var10 = ((DBusInterface) Proxy.newProxyInstance((((Class) var8.get(0))).getClassLoader(), ((Class[]) var8.toArray(lp0 -> lambda$dynamicProxy$3(lp0))), new RemoteInvocationHandler(this, var9)));
                getImportedObjects().put(var10, var9);
                __stk2 = var10;
            } else {
                var8.add(DBusInterface.class);
                RemoteObject var9 = new RemoteObject(arg0, arg1, arg2, false);
                DBusInterface var10 = ((DBusInterface) Proxy.newProxyInstance((((Class) var8.get(0))).getClassLoader(), ((Class[]) var8.toArray(lp0 -> lambda$dynamicProxy$3(lp0))), new RemoteInvocationHandler(this, var9)));
                getImportedObjects().put(var10, var9);
                __stk2 = var10;
            }
        } catch (Exception e1) {
            Throwable var4 = e1;
            logger.debug("Cannot create proxy object", var4);
            throw new DBusException(String.format("Failed to create proxy object for %s exported by %s. Reason: %s", new Object[]{arg1, arg0, var4.getMessage()}));
        }
    }

  public DBusInterface getExportedObject(String arg0, String arg1, Class arg2) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokevirtual  #158 // org.freedesktop.dbus.connections.impl.DBusConnection.getExportedObjects:()Ljava/util/Map;
        //      4: dup
        //      5: astore  5
        //      7: monitorenter
        //      8: aload_0
        //      9: invokevirtual  #158 // org.freedesktop.dbus.connections.impl.DBusConnection.getExportedObjects:()Ljava/util/Map;
        //     12: aload_2
        //     13: invokeinterface  #217 // java.util.Map.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //     18: checkcast  #87 // org.freedesktop.dbus.messages.ExportedObject
        //     21: astore  4
        //     23: aload  5
        //     25: monitorexit
        //     26: goto  37 (offset +11)
        //     29: astore  6
        //     31: aload  5
        //     33: monitorexit
        //     34: aload  6
        //     36: athrow
        //     37: aconst_null
        //     38: aload  4
        //     40: if_acmpeq  62 (offset +22)
        //     43: aload  4
        //     45: invokevirtual  #196 // org.freedesktop.dbus.messages.ExportedObject.getObject:()Ljava/lang/ref/Reference;
        //     48: invokevirtual  #119 // java.lang.ref.Reference.get:()Ljava/lang/Object;
        //     51: ifnonnull  62 (offset +11)
        //     54: aload_0
        //     55: aload_2
        //     56: invokevirtual  #183 // org.freedesktop.dbus.connections.impl.DBusConnection.unExportObject:(Ljava/lang/String;)V
        //     59: aconst_null
        //     60: astore  4
        //     62: aconst_null
        //     63: aload  4
        //     65: if_acmpeq  80 (offset +15)
        //     68: aload  4
        //     70: invokevirtual  #196 // org.freedesktop.dbus.messages.ExportedObject.getObject:()Ljava/lang/ref/Reference;
        //     73: invokevirtual  #119 // java.lang.ref.Reference.get:()Ljava/lang/Object;
        //     76: checkcast  #84 // org.freedesktop.dbus.interfaces.DBusInterface
        //     79: areturn
        //     80: aconst_null
        //     81: aload_1
        //     82: if_acmpne  95 (offset +13)
        //     85: new  #78 // org.freedesktop.dbus.exceptions.DBusException
        //     88: dup
        //     89: ldc  #24 // 'Not an object exported by this connection and no remote specified'
        //     91: invokespecial  #191 // org.freedesktop.dbus.exceptions.DBusException.<init>:(Ljava/lang/String;)V
        //     94: athrow
        //     95: aload_0
        //     96: aload_1
        //     97: aload_2
        //     98: aload_3
        //     99: invokevirtual  #152 // org.freedesktop.dbus.connections.impl.DBusConnection.dynamicProxy:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Class;)Lorg/freedesktop/dbus/interfaces/DBusInterface;
        //    102: areturn
        //       Exception table:
        //         from 8 to 26 target 29 type any
        //         from 29 to 34 target 29 type any
    }

  public DBusInterface getExportedObject(String arg0, String arg1) {
        return getExportedObject(arg0, arg1, null);
    }

  public void releaseBusName(String arg0) {
        DBusObjects.requireBusName(arg0);
        try {
            dbus.ReleaseName(arg0);
        } catch (DBusExecutionException var2) {
            logger.debug("Failed to release bus name", var2);
            throw new DBusException(var2.getMessage());
        }
    }

  public void requestBusName(String arg0) {
        DBusObjects.requireBusName(arg0);
        try {
            UInt32 var2 = dbus.RequestName(arg0, new UInt32(6L));
        } catch (DBusExecutionException var3) {
            logger.debug("Failed to request bus name", var3);
            throw new DBusException(var3);
        }
    }

  public String getUniqueName() {
        return ((String) busnames.get(0));
    }

  public String[] getNames() {
        TreeSet var1 = new TreeSet();
        var1.addAll(busnames);
        return ((String[]) var1.toArray(lp0 -> lambda$getNames$4(lp0)));
    }

  public DBusInterface getPeerRemoteObject(String arg0, String arg1, Class arg2) {
        return getPeerRemoteObject(arg0, arg1, arg2, true);
    }

  public DBusInterface getPeerRemoteObject(String arg0, String arg1) {
        DBusObjects.requireBusNameOrConnectionId(arg0);
        String var3 = dbus.GetNameOwner(arg0);
        return dynamicProxy(var3, arg1, null);
    }

  public DBusInterface getRemoteObject(String arg0, String arg1) {
        DBusObjects.requireBusNameOrConnectionId(arg0);
        DBusObjects.requireObjectPath(arg1);
        return dynamicProxy(arg0, arg1, null);
    }

  public DBusInterface getPeerRemoteObject(String arg0, String arg1, Class arg2, boolean arg3) {
        if (null != arg0) {
            DBusObjects.requireBusNameOrConnectionId(arg0);
            String var5 = dbus.GetNameOwner(arg0);
            return getRemoteObject(var5, arg1, arg2, arg3);
        } else {
            throw new InvalidBusNameException();
        }
    }

  public DBusInterface getRemoteObject(String arg0, String arg1, Class arg2) {
        return getRemoteObject(arg0, arg1, arg2, true);
    }

  public DBusInterface getRemoteObject(String arg0, String arg1, Class arg2, boolean arg3) {
        if (arg2 != null) {
            DBusObjects.requireBusNameOrConnectionId(arg0);
            DBusObjects.requireObjectPath(arg1);
            if (DBusInterface.class.isAssignableFrom(arg2)) {
                if (!arg2.getName().equals(arg2.getSimpleName())) {
                    RemoteObject var5 = new RemoteObject(arg0, arg1, arg2, arg3);
                    DBusInterface var6 = ((DBusInterface) Proxy.newProxyInstance(arg2.getClassLoader(), new Class[]{arg2}, new RemoteInvocationHandler(this, var5)));
                    getImportedObjects().put(var6, var5);
                    return var6;
                } else {
                    throw new DBusException("DBusInterfaces cannot be declared outside a package");
                }
            } else {
                throw new ClassCastException("Not A DBus Interface");
            }
        } else {
            throw new ClassCastException("Not A DBus Interface");
        }
    }

  public void removeSigHandler(Class arg0, String arg1, DBusSigHandler arg2) {
        validateSignal(arg0, arg1);
        removeSigHandler(new DBusMatchRule(arg0, arg1, null), arg2);
    }

  public void removeSigHandler(Class arg0, String arg1, DBusInterface arg2, DBusSigHandler arg3) {
        validateSignal(arg0, arg1);
        String var5 = (((RemoteObject) getImportedObjects().get(arg2))).getObjectPath();
        DBusObjects.requireObjectPath(var5);
        removeSigHandler(new DBusMatchRule(arg0, arg1, var5), arg3);
    }

  protected void removeSigHandler(DBusMatchRule arg0, DBusSigHandler arg1) {
        Queue var3 = ((Queue) getHandledSignals().get(arg0));
        if (null == var3) {
            return;
        }
        var3.remove(arg1);
        if (!var3.isEmpty()) {
            return;
        } else {
            getHandledSignals().remove(arg0);
        }
        try {
            dbus.RemoveMatch(arg0.toString());
        } catch (NotConnected var4) {
            logger.debug("No connection.", var4);
        } catch (DBusExecutionException e2) {
            Throwable var4 = e2;
            logger.debug("Error removing signal", var4);
            throw new DBusException(var4);
        }
    }

  public AutoCloseable addSigHandler(Class arg0, String arg1, DBusSigHandler arg2) {
        validateSignal(arg0, arg1);
        addSigHandler(new DBusMatchRule(arg0, arg1, null), arg2);
        return new DBusConnection_Anon1(this, arg0, arg1, arg2);
    }

  public AutoCloseable addSigHandler(Class arg0, String arg1, DBusInterface arg2, DBusSigHandler arg3) {
        validateSignal(arg0, arg1);
        String var5 = (((RemoteObject) getImportedObjects().get(arg2))).getObjectPath();
        DBusObjects.requireObjectPath(var5);
        addSigHandler(new DBusMatchRule(arg0, arg1, var5), arg3);
        return new DBusConnection_Anon2(this, arg0, arg1, arg2, arg3);
    }

  private void validateSignal(Class arg0, String arg1) {
        if (DBusSignal.class.isAssignableFrom(arg0)) {
            DBusObjects.requireNotBusName(arg1, "Cannot watch for signals based on well known bus name as source. Only unique names supported");
            DBusObjects.requireConnectionId(arg1);
            return;
        } else {
            throw new ClassCastException("Not A DBus Signal");
        }
    }

  public AutoCloseable addSigHandler(DBusMatchRule arg0, DBusSigHandler arg1) {
        Objects.requireNonNull(arg0, "Match rule cannot be null");
        Objects.requireNonNull(arg1, "Handler cannot be null");
        AtomicBoolean var3 = new AtomicBoolean(false);
        Queue var4 = ((Queue) getHandledSignals().computeIfAbsent(arg0, lp0 -> lambda$addSigHandler$5(var3, ((DBusMatchRule) lp0))));
        var4.add(arg1);
        if (!var3.get()) {
            return new DBusConnection_Anon3(this, arg0, arg1);
        }
        try {
            dbus.AddMatch(arg0.toString());
        } catch (DBusExecutionException var5) {
            logger.debug("Cannot add match rule: " + arg0.toString(), var5);
            throw new DBusException("Cannot add match rule.", var5);
        }
    }

  public synchronized void disconnect() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokevirtual  #167 // org.freedesktop.dbus.connections.impl.DBusConnection.isConnected:()Z
        //      4: ifne  8 (offset +4)
        //      7: return
        //      8: aload_0
        //      9: getfield  #102 // org.freedesktop.dbus.connections.impl.DBusConnection.shared:Z
        //     12: ifeq  138 (offset +126)
        //     15: getstatic  #95 // org.freedesktop.dbus.connections.impl.DBusConnection.CONNECTIONS:Ljava/util/concurrent/ConcurrentMap;
        //     18: dup
        //     19: astore_1
        //     20: monitorenter
        //     21: getstatic  #95 // org.freedesktop.dbus.connections.impl.DBusConnection.CONNECTIONS:Ljava/util/concurrent/ConcurrentMap;
        //     24: aload_0
        //     25: invokevirtual  #154 // org.freedesktop.dbus.connections.impl.DBusConnection.getAddress:()Lorg/freedesktop/dbus/connections/BusAddress;
        //     28: invokevirtual  #147 // org.freedesktop.dbus.connections.BusAddress.toString:()Ljava/lang/String;
        //     31: invokeinterface  #227 // java.util.concurrent.ConcurrentMap.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //     36: checkcast  #70 // org.freedesktop.dbus.connections.impl.DBusConnection
        //     39: astore_2
        //     40: aload_2
        //     41: ifnull  125 (offset +84)
        //     44: aload_2
        //     45: invokevirtual  #155 // org.freedesktop.dbus.connections.impl.DBusConnection.getConcurrentConnections:()Ljava/util/concurrent/atomic/AtomicInteger;
        //     48: invokevirtual  #134 // java.util.concurrent.atomic.AtomicInteger.get:()I
        //     51: iconst_1
        //     52: if_icmpgt  78 (offset +26)
        //     55: getstatic  #95 // org.freedesktop.dbus.connections.impl.DBusConnection.CONNECTIONS:Ljava/util/concurrent/ConcurrentMap;
        //     58: aload_0
        //     59: invokevirtual  #154 // org.freedesktop.dbus.connections.impl.DBusConnection.getAddress:()Lorg/freedesktop/dbus/connections/BusAddress;
        //     62: invokevirtual  #147 // org.freedesktop.dbus.connections.BusAddress.toString:()Ljava/lang/String;
        //     65: invokeinterface  #228 // java.util.concurrent.ConcurrentMap.remove:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //     70: pop
        //     71: aload_0
        //     72: invokespecial  #145 // org.freedesktop.dbus.connections.AbstractConnection.disconnect:()V
        //     75: goto  125 (offset +50)
        //     78: aload_0
        //     79: getfield  #99 // org.freedesktop.dbus.connections.impl.DBusConnection.logger:Lorg/slf4j/Logger;
        //     82: ldc  #25 // 'Still {} connections left, decreasing connection counter'
        //     84: aload_2
        //     85: invokevirtual  #155 // org.freedesktop.dbus.connections.impl.DBusConnection.getConcurrentConnections:()Ljava/util/concurrent/atomic/AtomicInteger;
        //     88: invokevirtual  #134 // java.util.concurrent.atomic.AtomicInteger.get:()I
        //     91: iconst_1
        //     92: isub
        //     93: invokestatic  #112 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //     96: invokeinterface  #240 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //    101: aload_0
        //    102: invokevirtual  #156 // org.freedesktop.dbus.connections.impl.DBusConnection.getDisconnectCallback:()Lorg/freedesktop/dbus/connections/IDisconnectCallback;
        //    105: invokestatic  #125 // java.util.Optional.ofNullable:(Ljava/lang/Object;)Ljava/util/Optional;
        //    108: aload_2
        //    109: invokedynamic  #252 // invokedynamic accept:(Lorg/freedesktop/dbus/connections/impl/DBusConnection;)Ljava/util/function/Consumer;
        //    114: invokevirtual  #124 // java.util.Optional.ifPresent:(Ljava/util/function/Consumer;)V
        //    117: aload_2
        //    118: invokevirtual  #155 // org.freedesktop.dbus.connections.impl.DBusConnection.getConcurrentConnections:()Ljava/util/concurrent/atomic/AtomicInteger;
        //    121: invokevirtual  #133 // java.util.concurrent.atomic.AtomicInteger.decrementAndGet:()I
        //    124: pop
        //    125: aload_1
        //    126: monitorexit
        //    127: goto  135 (offset +8)
        //    130: astore_3
        //    131: aload_1
        //    132: monitorexit
        //    133: aload_3
        //    134: athrow
        //    135: goto  151 (offset +16)
        //    138: aload_0
        //    139: invokedynamic  #253 // invokedynamic perform:(Lorg/freedesktop/dbus/connections/impl/DBusConnection;)Lorg/freedesktop/dbus/connections/IDisconnectAction;
        //    144: astore_1
        //    145: aload_0
        //    146: aload_1
        //    147: aconst_null
        //    148: invokespecial  #146 // org.freedesktop.dbus.connections.AbstractConnection.disconnect:(Lorg/freedesktop/dbus/connections/IDisconnectAction;Lorg/freedesktop/dbus/connections/IDisconnectAction;)V
        //    151: return
        //       Exception table:
        //         from 21 to 127 target 130 type any
        //         from 130 to 133 target 130 type any
    }

  public void close() {
        disconnect();
    }

  public String getMachineId() {
        return machineId;
    }

  public void removeGenericSigHandler(DBusMatchRule arg0, DBusSigHandler arg1) {
        Queue var3 = ((Queue) getGenericHandledSignals().get(arg0));
        if (null == var3) {
            return;
        }
        var3.remove(arg1);
        if (!var3.isEmpty()) {
            return;
        } else {
            getGenericHandledSignals().remove(arg0);
        }
        try {
            dbus.RemoveMatch(arg0.toString());
        } catch (NotConnected var4) {
            logger.debug("No connection.", var4);
        } catch (DBusExecutionException e2) {
            Throwable var4 = e2;
            logger.debug("Error removing generic signal", var4);
            throw new DBusException(var4);
        }
    }

  public AutoCloseable addGenericSigHandler(DBusMatchRule arg0, DBusSigHandler arg1) {
        AtomicBoolean var3 = new AtomicBoolean(false);
        Queue var4 = ((Queue) getGenericHandledSignals().computeIfAbsent(arg0, lp0 -> lambda$addGenericSigHandler$10(var3, ((DBusMatchRule) lp0))));
        var4.add(arg1);
        if (!var3.get()) {
            return new DBusConnection_Anon4(this, arg0, arg1);
        }
        try {
            dbus.AddMatch(arg0.toString());
        } catch (DBusExecutionException var5) {
            logger.debug("Error adding signal handler", var5);
            throw new DBusException(var5.getMessage());
        }
    }

  private static Queue lambda$addGenericSigHandler$10(AtomicBoolean arg0, DBusMatchRule arg1) {
        ConcurrentLinkedQueue var2 = new ConcurrentLinkedQueue();
        arg0.set(true);
        return var2;
    }

  private void lambda$disconnect$9() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #96 // org.freedesktop.dbus.connections.impl.DBusConnection.busnames:Ljava/util/List;
        //      4: dup
        //      5: astore_1
        //      6: monitorenter
        //      7: aload_0
        //      8: getfield  #96 // org.freedesktop.dbus.connections.impl.DBusConnection.busnames:Ljava/util/List;
        //     11: invokeinterface  #214 // java.util.List.stream:()Ljava/util/stream/Stream;, count 1
        //     16: invokedynamic  #255 // invokedynamic test:()Ljava/util/function/Predicate;
        //     21: invokeinterface  #230 // java.util.stream.Stream.filter:(Ljava/util/function/Predicate;)Ljava/util/stream/Stream;, count 2
        //     26: invokestatic  #138 // java.util.stream.Collectors.toList:()Ljava/util/stream/Collector;
        //     29: invokeinterface  #229 // java.util.stream.Stream.collect:(Ljava/util/stream/Collector;)Ljava/lang/Object;, count 2
        //     34: checkcast  #47 // java.util.List
        //     37: astore_2
        //     38: aload_2
        //     39: aload_0
        //     40: invokedynamic  #256 // invokedynamic accept:(Lorg/freedesktop/dbus/connections/impl/DBusConnection;)Ljava/util/function/Consumer;
        //     45: invokeinterface  #209 // java.util.List.forEach:(Ljava/util/function/Consumer;)V, count 2
        //     50: aload_1
        //     51: monitorexit
        //     52: goto  60 (offset +8)
        //     55: astore_3
        //     56: aload_1
        //     57: monitorexit
        //     58: aload_3
        //     59: athrow
        //     60: aload_0
        //     61: invokevirtual  #158 // org.freedesktop.dbus.connections.impl.DBusConnection.getExportedObjects:()Ljava/util/Map;
        //     64: astore_1
        //     65: aload_1
        //     66: dup
        //     67: astore_2
        //     68: monitorenter
        //     69: aload_1
        //     70: invokeinterface  #218 // java.util.Map.keySet:()Ljava/util/Set;, count 1
        //     75: invokeinterface  #225 // java.util.Set.stream:()Ljava/util/stream/Stream;, count 1
        //     80: invokedynamic  #257 // invokedynamic test:()Ljava/util/function/Predicate;
        //     85: invokeinterface  #230 // java.util.stream.Stream.filter:(Ljava/util/function/Predicate;)Ljava/util/stream/Stream;, count 2
        //     90: invokestatic  #138 // java.util.stream.Collectors.toList:()Ljava/util/stream/Collector;
        //     93: invokeinterface  #229 // java.util.stream.Stream.collect:(Ljava/util/stream/Collector;)Ljava/lang/Object;, count 2
        //     98: checkcast  #47 // java.util.List
        //    101: astore_3
        //    102: aload_3
        //    103: invokeinterface  #212 // java.util.List.iterator:()Ljava/util/Iterator;, count 1
        //    108: astore  4
        //    110: aload  4
        //    112: invokeinterface  #206 // java.util.Iterator.hasNext:()Z, count 1
        //    117: ifeq  141 (offset +24)
        //    120: aload  4
        //    122: invokeinterface  #207 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //    127: checkcast  #37 // java.lang.String
        //    130: astore  5
        //    132: aload_0
        //    133: aload  5
        //    135: invokevirtual  #183 // org.freedesktop.dbus.connections.impl.DBusConnection.unExportObject:(Ljava/lang/String;)V
        //    138: goto  110 (offset -28)
        //    141: aload_2
        //    142: monitorexit
        //    143: goto  153 (offset +10)
        //    146: astore  6
        //    148: aload_2
        //    149: monitorexit
        //    150: aload  6
        //    152: athrow
        //    153: return
        //       Exception table:
        //         from 7 to 52 target 55 type any
        //         from 55 to 58 target 55 type any
        //         from 69 to 143 target 146 type any
        //         from 146 to 150 target 146 type any
    }

  private static boolean lambda$disconnect$8(String arg0) {
        return arg0 != null;
    }

  private void lambda$disconnect$7(String arg0) {
        try {
            releaseBusName(arg0);
        } catch (DBusException var2) {
            logger.error("Error while releasing busName '" + arg0 + "'.", var2);
        }
    }

  private static void lambda$disconnect$6(DBusConnection arg0, IDisconnectCallback arg1) {
        arg1.requestedDisconnect(Integer.valueOf(arg0.getConcurrentConnections().get()));
    }

  private static Queue lambda$addSigHandler$5(AtomicBoolean arg0, DBusMatchRule arg1) {
        ConcurrentLinkedQueue var2 = new ConcurrentLinkedQueue();
        arg0.set(true);
        return var2;
    }

  private static String[] lambda$getNames$4(int arg0) {
        return new String[arg0];
    }

  private static Class[] lambda$dynamicProxy$3(int arg0) {
        return new Class[arg0];
    }

  private static String lambda$dynamicProxy$2(String arg0) {
        if (!arg0.startsWith("org.freedesktop.DBus.")) {
            return arg0;
        } else {
            return CommonRegexPattern.DBUS_IFACE_PATTERN.matcher(arg0).replaceAll("$1");
        }
    }

  private static String lambda$dynamicProxy$1(String arg0) {
        return CommonRegexPattern.IFACE_PATTERN.matcher(arg0).replaceAll("$1");
    }

  private static boolean lambda$dynamicProxy$0(String arg0) {
        return arg0.startsWith("interface");
    }

}