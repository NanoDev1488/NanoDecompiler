// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.connections.AbstractConnection
package org.freedesktop.dbus.connections;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.freedesktop.dbus.DBusAsyncReply;
import org.freedesktop.dbus.DBusMatchRule;
import org.freedesktop.dbus.RemoteObject;
import org.freedesktop.dbus.connections.BusAddress;
import org.freedesktop.dbus.connections.base.ConnectionMessageHandler;
import org.freedesktop.dbus.connections.base.FallbackContainer;
import org.freedesktop.dbus.connections.base.IncomingMessageThread;
import org.freedesktop.dbus.connections.base.PendingCallbackManager;
import org.freedesktop.dbus.connections.config.ReceivingServiceConfig;
import org.freedesktop.dbus.connections.config.TransportConfig;
import org.freedesktop.dbus.connections.transports.AbstractTransport;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.exceptions.InvalidSignalException;
import org.freedesktop.dbus.interfaces.CallbackHandler;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.DBusSigHandler;
import org.freedesktop.dbus.messages.DBusSignal;
import org.freedesktop.dbus.messages.ExportedObject;
import org.freedesktop.dbus.messages.MethodCall;
import org.freedesktop.dbus.utils.DBusObjects;
import org.slf4j.Logger;

public abstract class AbstractConnection extends ConnectionMessageHandler {

    // ---- поля ----
  public static final boolean FLOAT_SUPPORT;
  public static final Pattern DOLLAR_PATTERN;
  public static final int MAX_ARRAY_LENGTH = 67108864;
  public static final int MAX_NAME_LENGTH = 255;
  private boolean weakreferences;

    static {
        boolean __stk1;
        __stk1 = null != System.getenv("DBUS_JAVA_FLOATS");
        FLOAT_SUPPORT = __stk1;
        DOLLAR_PATTERN = Pattern.compile("[$]");
    }

  protected AbstractConnection(TransportConfig arg0, ReceivingServiceConfig arg1) { // было: <init>
        super(arg0, arg1);
        weakreferences = false;
    }

  protected IncomingMessageThread createReaderThread(BusAddress arg0) {
        return new IncomingMessageThread(this, arg0);
    }

  protected abstract void removeSigHandler(DBusMatchRule arg0, DBusSigHandler arg1);

  protected abstract AutoCloseable addSigHandler(DBusMatchRule arg0, DBusSigHandler arg1);

  protected abstract void removeGenericSigHandler(DBusMatchRule arg0, DBusSigHandler arg1);

  protected abstract AutoCloseable addGenericSigHandler(DBusMatchRule arg0, DBusSigHandler arg1);

  protected List findMatchingTypes(Class arg0, List arg1) {
        ArrayList var3 = new ArrayList();
        if (arg0 != null) {
            var3.add(arg0);
        } else {
            Iterator var4 = arg1.iterator();
            while (var4.hasNext()) {
                String var5 = ((String) var4.next());
                getLogger().debug("Trying interface {}", var5);
                int var6 = 0;
                while (var6 >= 0) {
                    try {
                        Class var7 = Class.forName(var5);
                        if (!var3.contains(var7)) {
                            var3.add(var7);
                        }
                    } catch (Exception e1) {
                        Throwable var7 = e1;
                        getLogger().trace("No class found for {}", var5, var7);
                        var6 = var5.lastIndexOf(46);
                        var7 = var5.toCharArray();
                        if (var6 >= 0) {
                            var7[var6] = 36;
                            var5 = String.valueOf(var7);
                        }
                    }
                }
                continue;
            }
        }
        return var3;
    }

  public void setWeakReferences(boolean arg0) {
        weakreferences = arg0;
    }

  public void exportObject(String arg0, DBusInterface arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aconst_null
        //      1: aload_1
        //      2: if_acmpeq  12 (offset +10)
        //      5: aload_1
        //      6: invokevirtual  #63 // java.lang.String.isEmpty:()Z
        //      9: ifeq  22 (offset +13)
        //     12: new  #46 // org.freedesktop.dbus.exceptions.DBusException
        //     15: dup
        //     16: ldc  #8 // 'Must Specify an Object Path'
        //     18: invokespecial  #101 // org.freedesktop.dbus.exceptions.DBusException.<init>:(Ljava/lang/String;)V
        //     21: athrow
        //     22: aload_1
        //     23: invokestatic  #107 // org.freedesktop.dbus.utils.DBusObjects.requireObjectPath:(Ljava/lang/String;)Ljava/lang/String;
        //     26: pop
        //     27: aload_0
        //     28: invokevirtual  #84 // org.freedesktop.dbus.connections.AbstractConnection.getExportedObjects:()Ljava/util/Map;
        //     31: dup
        //     32: astore_3
        //     33: monitorenter
        //     34: aconst_null
        //     35: aload_0
        //     36: invokevirtual  #84 // org.freedesktop.dbus.connections.AbstractConnection.getExportedObjects:()Ljava/util/Map;
        //     39: aload_1
        //     40: invokeinterface  #113 // java.util.Map.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //     45: if_acmpeq  58 (offset +13)
        //     48: new  #46 // org.freedesktop.dbus.exceptions.DBusException
        //     51: dup
        //     52: ldc  #11 // 'Object already exported'
        //     54: invokespecial  #101 // org.freedesktop.dbus.exceptions.DBusException.<init>:(Ljava/lang/String;)V
        //     57: athrow
        //     58: new  #51 // org.freedesktop.dbus.messages.ExportedObject
        //     61: dup
        //     62: aload_2
        //     63: aload_0
        //     64: getfield  #57 // org.freedesktop.dbus.connections.AbstractConnection.weakreferences:Z
        //     67: invokespecial  #104 // org.freedesktop.dbus.messages.ExportedObject.<init>:(Lorg/freedesktop/dbus/interfaces/DBusInterface;Z)V
        //     70: astore  4
        //     72: aload_0
        //     73: invokevirtual  #84 // org.freedesktop.dbus.connections.AbstractConnection.getExportedObjects:()Ljava/util/Map;
        //     76: aload_1
        //     77: aload  4
        //     79: invokeinterface  #114 // java.util.Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;, count 3
        //     84: pop
        //     85: aload_0
        //     86: invokevirtual  #89 // org.freedesktop.dbus.connections.AbstractConnection.getObjectTree:()Lorg/freedesktop/dbus/messages/ObjectTree;
        //     89: dup
        //     90: astore  5
        //     92: monitorenter
        //     93: aload_0
        //     94: invokevirtual  #89 // org.freedesktop.dbus.connections.AbstractConnection.getObjectTree:()Lorg/freedesktop/dbus/messages/ObjectTree;
        //     97: aload_1
        //     98: aload  4
        //    100: aload  4
        //    102: invokevirtual  #105 // org.freedesktop.dbus.messages.ExportedObject.getIntrospectiondata:()Ljava/lang/String;
        //    105: invokevirtual  #106 // org.freedesktop.dbus.messages.ObjectTree.add:(Ljava/lang/String;Lorg/freedesktop/dbus/messages/ExportedObject;Ljava/lang/String;)V
        //    108: aload  5
        //    110: monitorexit
        //    111: goto  122 (offset +11)
        //    114: astore  6
        //    116: aload  5
        //    118: monitorexit
        //    119: aload  6
        //    121: athrow
        //    122: aload_3
        //    123: monitorexit
        //    124: goto  134 (offset +10)
        //    127: astore  7
        //    129: aload_3
        //    130: monitorexit
        //    131: aload  7
        //    133: athrow
        //    134: return
        //       Exception table:
        //         from 93 to 111 target 114 type any
        //         from 114 to 119 target 114 type any
        //         from 34 to 124 target 127 type any
        //         from 127 to 131 target 127 type any
    }

  public void exportObject(DBusInterface arg0) {
        Objects.requireNonNull(arg0, "object must not be null");
        exportObject(arg0.getObjectPath(), arg0);
    }

  public void addFallback(String arg0, DBusInterface arg1) {
        DBusObjects.requireObjectPath(arg0);
        ExportedObject var3 = new ExportedObject(arg1, weakreferences);
        getFallbackContainer().add(arg0, var3);
    }

  public void removeFallback(String arg0) {
        getFallbackContainer().remove(arg0);
    }

  public void removeSigHandler(Class arg0, DBusSigHandler arg1) {
        assertSignal(arg0);
        removeSigHandler(new DBusMatchRule(arg0), arg1);
    }

  public void removeSigHandler(Class arg0, DBusInterface arg1, DBusSigHandler arg2) {
        assertSignal(arg0);
        String var4 = (((RemoteObject) getImportedObjects().get(arg1))).getObjectPath();
        DBusObjects.requireObjectPath(var4);
        removeSigHandler(new DBusMatchRule(arg0, null, var4), arg2);
    }

  public AutoCloseable addSigHandler(Class arg0, DBusSigHandler arg1) {
        assertSignal(arg0);
        return addSigHandler(new DBusMatchRule(arg0), arg1);
    }

  public AutoCloseable addSigHandler(Class arg0, DBusInterface arg1, DBusSigHandler arg2) {
        assertSignal(arg0);
        RemoteObject var4 = ((RemoteObject) getImportedObjects().get(arg1));
        if (var4 != null) {
            String var5 = var4.getObjectPath();
            DBusObjects.requireObjectPath(var5);
            return addSigHandler(new DBusMatchRule(arg0, null, var5), arg2);
        } else {
            throw new DBusException("Not an object exported or imported by this connection");
        }
    }

  private void assertSignal(Class arg0) {
        if (DBusSignal.class.isAssignableFrom(arg0)) {
            return;
        } else {
            throw new InvalidSignalException(arg0);
        }
    }

  protected void addSigHandlerWithoutMatch(Class arg0, DBusSigHandler arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: new  #37 // org.freedesktop.dbus.DBusMatchRule
        //      3: dup
        //      4: aload_1
        //      5: invokespecial  #74 // org.freedesktop.dbus.DBusMatchRule.<init>:(Ljava/lang/Class;)V
        //      8: astore_3
        //      9: aload_0
        //     10: invokevirtual  #86 // org.freedesktop.dbus.connections.AbstractConnection.getHandledSignals:()Ljava/util/Map;
        //     13: dup
        //     14: astore  4
        //     16: monitorenter
        //     17: aload_0
        //     18: invokevirtual  #86 // org.freedesktop.dbus.connections.AbstractConnection.getHandledSignals:()Ljava/util/Map;
        //     21: aload_3
        //     22: invokeinterface  #113 // java.util.Map.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //     27: checkcast  #31 // java.util.Queue
        //     30: astore  5
        //     32: aconst_null
        //     33: aload  5
        //     35: if_acmpne  72 (offset +37)
        //     38: new  #33 // java.util.concurrent.ConcurrentLinkedQueue
        //     41: dup
        //     42: invokespecial  #72 // java.util.concurrent.ConcurrentLinkedQueue.<init>:()V
        //     45: astore  5
        //     47: aload  5
        //     49: aload_2
        //     50: invokeinterface  #115 // java.util.Queue.add:(Ljava/lang/Object;)Z, count 2
        //     55: pop
        //     56: aload_0
        //     57: invokevirtual  #86 // org.freedesktop.dbus.connections.AbstractConnection.getHandledSignals:()Ljava/util/Map;
        //     60: aload_3
        //     61: aload  5
        //     63: invokeinterface  #114 // java.util.Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;, count 3
        //     68: pop
        //     69: goto  81 (offset +12)
        //     72: aload  5
        //     74: aload_2
        //     75: invokeinterface  #115 // java.util.Queue.add:(Ljava/lang/Object;)Z, count 2
        //     80: pop
        //     81: aload  4
        //     83: monitorexit
        //     84: goto  95 (offset +11)
        //     87: astore  6
        //     89: aload  4
        //     91: monitorexit
        //     92: aload  6
        //     94: athrow
        //     95: return
        //       Exception table:
        //         from 17 to 84 target 87 type any
        //         from 87 to 92 target 87 type any
    }

  public void callWithCallback(DBusInterface arg0, String arg1, CallbackHandler arg2, Object[] arg3) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokevirtual  #88 // org.freedesktop.dbus.connections.AbstractConnection.getLogger:()Lorg/slf4j/Logger;
        //      4: ldc  #14 // 'callWithCallback({}, {}, {})'
        //      6: iconst_3
        //      7: anewarray  #19 // java.lang.Object
        //     10: dup
        //     11: iconst_0
        //     12: aload_1
        //     13: aastore
        //     14: dup
        //     15: iconst_1
        //     16: aload_2
        //     17: aastore
        //     18: dup
        //     19: iconst_2
        //     20: aload_3
        //     21: aastore
        //     22: invokeinterface  #124 // org.slf4j.Logger.trace:(Ljava/lang/String;[Ljava/lang/Object;)V, count 3
        //     27: aload  4
        //     29: invokestatic  #81 // org.freedesktop.dbus.connections.AbstractConnection.createTypesArray:([Ljava/lang/Object;)[Ljava/lang/Class;
        //     32: astore  5
        //     34: aload_0
        //     35: invokevirtual  #87 // org.freedesktop.dbus.connections.AbstractConnection.getImportedObjects:()Ljava/util/Map;
        //     38: aload_1
        //     39: invokeinterface  #113 // java.util.Map.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //     44: checkcast  #39 // org.freedesktop.dbus.RemoteObject
        //     47: astore  6
        //     49: aconst_null
        //     50: aload  6
        //     52: invokevirtual  #77 // org.freedesktop.dbus.RemoteObject.getInterface:()Ljava/lang/Class;
        //     55: if_acmpne  75 (offset +20)
        //     58: aload_1
        //     59: invokeinterface  #119 // org.freedesktop.dbus.interfaces.DBusInterface.getClass:()Ljava/lang/Class;, count 1
        //     64: aload_2
        //     65: aload  5
        //     67: invokevirtual  #59 // java.lang.Class.getMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //     70: astore  7
        //     72: goto  88 (offset +16)
        //     75: aload  6
        //     77: invokevirtual  #77 // org.freedesktop.dbus.RemoteObject.getInterface:()Ljava/lang/Class;
        //     80: aload_2
        //     81: aload  5
        //     83: invokevirtual  #59 // java.lang.Class.getMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //     86: astore  7
        //     88: aload  6
        //     90: aload  7
        //     92: aload_0
        //     93: iconst_2
        //     94: aload_3
        //     95: aload  4
        //     97: invokestatic  #76 // org.freedesktop.dbus.RemoteInvocationHandler.executeRemoteMethod:(Lorg/freedesktop/dbus/RemoteObject;Ljava/lang/reflect/Method;Lorg/freedesktop/dbus/connections/AbstractConnection;ILorg/freedesktop/dbus/interfaces/CallbackHandler;[Ljava/lang/Object;)Ljava/lang/Object;
        //    100: pop
        //    101: goto  150 (offset +49)
        //    104: astore  7
        //    106: aload_0
        //    107: invokevirtual  #88 // org.freedesktop.dbus.connections.AbstractConnection.getLogger:()Lorg/slf4j/Logger;
        //    110: ldc  #5 // 'Error calling callback'
        //    112: aload  7
        //    114: invokeinterface  #122 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Throwable;)V, count 3
        //    119: aload  7
        //    121: athrow
        //    122: astore  7
        //    124: aload_0
        //    125: invokevirtual  #88 // org.freedesktop.dbus.connections.AbstractConnection.getLogger:()Lorg/slf4j/Logger;
        //    128: ldc  #6 // 'Failed to call callback'
        //    130: aload  7
        //    132: invokeinterface  #122 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Throwable;)V, count 3
        //    137: new  #47 // org.freedesktop.dbus.exceptions.DBusExecutionException
        //    140: dup
        //    141: aload  7
        //    143: invokevirtual  #61 // java.lang.Exception.getMessage:()Ljava/lang/String;
        //    146: invokespecial  #102 // org.freedesktop.dbus.exceptions.DBusExecutionException.<init>:(Ljava/lang/String;)V
        //    149: athrow
        //    150: return
        //       Exception table:
        //         from 49 to 101 target 104 type org.freedesktop.dbus.exceptions.DBusExecutionException
        //         from 49 to 101 target 122 type java.lang.Exception
    }

  public DBusAsyncReply callMethodAsync(DBusInterface arg0, String arg1, Object[] arg2) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_3
        //      1: invokestatic  #81 // org.freedesktop.dbus.connections.AbstractConnection.createTypesArray:([Ljava/lang/Object;)[Ljava/lang/Class;
        //      4: astore  4
        //      6: aload_0
        //      7: invokevirtual  #87 // org.freedesktop.dbus.connections.AbstractConnection.getImportedObjects:()Ljava/util/Map;
        //     10: aload_1
        //     11: invokeinterface  #113 // java.util.Map.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //     16: checkcast  #39 // org.freedesktop.dbus.RemoteObject
        //     19: astore  5
        //     21: aconst_null
        //     22: aload  5
        //     24: invokevirtual  #77 // org.freedesktop.dbus.RemoteObject.getInterface:()Ljava/lang/Class;
        //     27: if_acmpne  47 (offset +20)
        //     30: aload_1
        //     31: invokeinterface  #119 // org.freedesktop.dbus.interfaces.DBusInterface.getClass:()Ljava/lang/Class;, count 1
        //     36: aload_2
        //     37: aload  4
        //     39: invokevirtual  #59 // java.lang.Class.getMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //     42: astore  6
        //     44: goto  60 (offset +16)
        //     47: aload  5
        //     49: invokevirtual  #77 // org.freedesktop.dbus.RemoteObject.getInterface:()Ljava/lang/Class;
        //     52: aload_2
        //     53: aload  4
        //     55: invokevirtual  #59 // java.lang.Class.getMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //     58: astore  6
        //     60: aload  5
        //     62: aload  6
        //     64: aload_0
        //     65: iconst_1
        //     66: aconst_null
        //     67: aload_3
        //     68: invokestatic  #76 // org.freedesktop.dbus.RemoteInvocationHandler.executeRemoteMethod:(Lorg/freedesktop/dbus/RemoteObject;Ljava/lang/reflect/Method;Lorg/freedesktop/dbus/connections/AbstractConnection;ILorg/freedesktop/dbus/interfaces/CallbackHandler;[Ljava/lang/Object;)Ljava/lang/Object;
        //     71: checkcast  #36 // org.freedesktop.dbus.DBusAsyncReply
        //     74: areturn
        //     75: astore  6
        //     77: aload_0
        //     78: invokevirtual  #88 // org.freedesktop.dbus.connections.AbstractConnection.getLogger:()Lorg/slf4j/Logger;
        //     81: ldc  #4 // 'Error calling async method'
        //     83: aload  6
        //     85: invokeinterface  #122 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Throwable;)V, count 3
        //     90: aload  6
        //     92: athrow
        //     93: astore  6
        //     95: aload_0
        //     96: invokevirtual  #88 // org.freedesktop.dbus.connections.AbstractConnection.getLogger:()Lorg/slf4j/Logger;
        //     99: ldc  #7 // 'Failed to execute async method'
        //    101: aload  6
        //    103: invokeinterface  #122 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Throwable;)V, count 3
        //    108: new  #47 // org.freedesktop.dbus.exceptions.DBusExecutionException
        //    111: dup
        //    112: aload  6
        //    114: invokevirtual  #61 // java.lang.Exception.getMessage:()Ljava/lang/String;
        //    117: invokespecial  #102 // org.freedesktop.dbus.exceptions.DBusExecutionException.<init>:(Ljava/lang/String;)V
        //    120: athrow
        //       Exception table:
        //         from 21 to 74 target 75 type org.freedesktop.dbus.exceptions.DBusExecutionException
        //         from 21 to 74 target 93 type java.lang.Exception
    }

  private static Class[] createTypesArray(Object[] arg0) {
        if (arg0 != null) {
            return ((Class[]) Arrays.stream(arg0).filter(lp0 -> lambda$createTypesArray$0(lp0)).map(lp0 -> lambda$createTypesArray$1(lp0)).toArray(lp0 -> lambda$createTypesArray$2(lp0)));
        } else {
            return null;
        }
    }

  public void queueCallback(MethodCall arg0, Method arg1, CallbackHandler arg2) {
        getCallbackManager().queueCallback(arg0, arg1, arg2, this);
    }

  public boolean isFileDescriptorSupported() {
        return getTransport().isFileDescriptorSupported();
    }

  private static Class[] lambda$createTypesArray$2(int arg0) {
        return new Class[arg0];
    }

  private static Class lambda$createTypesArray$1(Object arg0) {
        if (!List.class.isAssignableFrom(arg0.getClass())) {
            if (!Map.class.isAssignableFrom(arg0.getClass())) {
                if (!Set.class.isAssignableFrom(arg0.getClass())) {
                    return arg0.getClass();
                } else {
                    return Set.class;
                }
            } else {
                return Map.class;
            }
        } else {
            return List.class;
        }
    }

  private static boolean lambda$createTypesArray$0(Object arg0) {
        return arg0 != null;
    }

}