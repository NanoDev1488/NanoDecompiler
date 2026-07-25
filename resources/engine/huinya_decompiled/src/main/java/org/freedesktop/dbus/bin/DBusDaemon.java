// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.bin.DBusDaemon
package org.freedesktop.dbus.bin;

import java.io.Closeable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.freedesktop.dbus.bin.DBusDaemon_ConnectionStruct;
import org.freedesktop.dbus.bin.DBusDaemon_DBusDaemonReaderThread;
import org.freedesktop.dbus.bin.DBusDaemon_DBusDaemonSenderThread;
import org.freedesktop.dbus.bin.DBusDaemon_DBusServer;
import org.freedesktop.dbus.bin.DBusDaemon_Pair;
import org.freedesktop.dbus.connections.transports.AbstractTransport;
import org.freedesktop.dbus.connections.transports.TransportBuilder_SaslAuthMode;
import org.freedesktop.dbus.connections.transports.TransportConnection;
import org.freedesktop.dbus.interfaces.Introspectable;
import org.freedesktop.dbus.messages.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBusDaemon extends Thread implements Closeable {

    // ---- поля ----
  public static final int QUEUE_POLL_WAIT = 500;
  private static final String DBUS_BUSPATH = "/org/freedesktop/DBus";
  private static final String DBUS_BUSNAME = "org.freedesktop.DBus";
  private static final Logger LOGGER;
  private final Map conns;
  private final Map names;
  private final BlockingDeque outqueue;
  private final BlockingDeque inqueue;
  private final List sigrecips;
  private final DBusDaemon_DBusServer dbusServer;
  private final DBusDaemon_DBusDaemonSenderThread sender;
  private final AtomicBoolean run;
  private final AtomicInteger nextUnique;
  private final AbstractTransport transport;

    static {
        LOGGER = LoggerFactory.getLogger(DBusDaemon.class);
    }

  public DBusDaemon(AbstractTransport arg0) { // было: <init>
        super();
        conns = new ConcurrentHashMap();
        names = Collections.synchronizedMap(new HashMap());
        outqueue = new LinkedBlockingDeque();
        inqueue = new LinkedBlockingDeque();
        sigrecips = new ArrayList();
        dbusServer = new DBusDaemon_DBusServer(this);
        sender = new DBusDaemon_DBusDaemonSenderThread(this);
        run = new AtomicBoolean(false);
        nextUnique = new AtomicInteger(0);
        setName(getClass().getSimpleName() + "-Thread");
        transport = arg0;
        names.put("org.freedesktop.DBus", null);
    }

  private void send(DBusDaemon_ConnectionStruct arg0, Message arg1) {
        send(arg0, arg1, false);
    }

  private void send(DBusDaemon_ConnectionStruct arg0, Message arg1, boolean arg2) {
        if (arg0 != null) {
            LOGGER.trace("Queuing message {} for {}", arg1, arg0.unique);
            if (!arg2) {
                outqueue.addLast(new DBusDaemon_Pair(arg1, new WeakReference(arg0)));
            } else {
                outqueue.addFirst(new DBusDaemon_Pair(arg1, new WeakReference(arg0)));
            }
        } else {
            LOGGER.trace("Queuing message {} for all connections", arg1);
            Iterator var4 = conns.keySet().iterator();
            while (var4.hasNext()) {
                DBusDaemon_ConnectionStruct var5 = ((DBusDaemon_ConnectionStruct) var4.next());
                if (var5.connection == null) {
                    LOGGER.debug("Ignoring broadcast message for disconnected connection {}: {}", var5.connection, arg1);
                } else {
                    if (var5.connection.getChannel() == null) {
                        LOGGER.debug("Ignoring broadcast message for disconnected connection {}: {}", var5.connection, arg1);
                    } else {
                        if (var5.connection.getChannel().isConnected()) {
                            if (!arg2) {
                                outqueue.addLast(new DBusDaemon_Pair(arg1, new WeakReference(var5)));
                            } else {
                                outqueue.addFirst(new DBusDaemon_Pair(arg1, new WeakReference(var5)));
                            }
                        } else {
                            LOGGER.debug("Ignoring broadcast message for disconnected connection {}: {}", var5.connection, arg1);
                        }
                    }
                }
                continue;
            }
        }
    }

  public void run() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #126 // org.freedesktop.dbus.bin.DBusDaemon.run:Ljava/util/concurrent/atomic/AtomicBoolean;
        //      4: iconst_1
        //      5: invokevirtual  #168 // java.util.concurrent.atomic.AtomicBoolean.set:(Z)V
        //      8: aload_0
        //      9: getfield  #127 // org.freedesktop.dbus.bin.DBusDaemon.sender:Lorg/freedesktop/dbus/bin/DBusDaemon$DBusDaemonSenderThread;
        //     12: invokevirtual  #189 // org.freedesktop.dbus.bin.DBusDaemon$DBusDaemonSenderThread.start:()V
        //     15: aload_0
        //     16: invokevirtual  #172 // org.freedesktop.dbus.bin.DBusDaemon.isRunning:()Z
        //     19: ifeq  467 (offset +448)
        //     22: aload_0
        //     23: getfield  #122 // org.freedesktop.dbus.bin.DBusDaemon.inqueue:Ljava/util/concurrent/BlockingDeque;
        //     26: invokeinterface  #230 // java.util.concurrent.BlockingDeque.take:()Ljava/lang/Object;, count 1
        //     31: checkcast  #101 // org.freedesktop.dbus.bin.DBusDaemon$Pair
        //     34: astore_1
        //     35: aload_1
        //     36: getfield  #133 // org.freedesktop.dbus.bin.DBusDaemon$Pair.second:Ljava/lang/Object;
        //     39: checkcast  #77 // java.lang.ref.WeakReference
        //     42: invokevirtual  #155 // java.lang.ref.WeakReference.get:()Ljava/lang/Object;
        //     45: checkcast  #97 // org.freedesktop.dbus.bin.DBusDaemon$ConnectionStruct
        //     48: astore_2
        //     49: aload_2
        //     50: ifnull  427 (offset +377)
        //     53: aload_1
        //     54: getfield  #132 // org.freedesktop.dbus.bin.DBusDaemon$Pair.first:Ljava/lang/Object;
        //     57: checkcast  #113 // org.freedesktop.dbus.messages.Message
        //     60: astore_3
        //     61: ldc  #24 // '<inqueue> Got message {} from {}'
        //     63: aload_3
        //     64: aload_2
        //     65: getfield  #131 // org.freedesktop.dbus.bin.DBusDaemon$ConnectionStruct.unique:Ljava/lang/String;
        //     68: invokestatic  #176 // org.freedesktop.dbus.bin.DBusDaemon.logMessage:(Ljava/lang/String;Lorg/freedesktop/dbus/messages/Message;Ljava/lang/String;)V
        //     71: aload_2
        //     72: getfield  #130 // org.freedesktop.dbus.bin.DBusDaemon$ConnectionStruct.connection:Lorg/freedesktop/dbus/connections/transports/TransportConnection;
        //     75: invokevirtual  #205 // org.freedesktop.dbus.connections.transports.TransportConnection.getMessageFactory:()Lorg/freedesktop/dbus/messages/MessageFactory;
        //     78: astore  4
        //     80: aconst_null
        //     81: aload_2
        //     82: getfield  #131 // org.freedesktop.dbus.bin.DBusDaemon$ConnectionStruct.unique:Ljava/lang/String;
        //     85: if_acmpne  152 (offset +67)
        //     88: aload_3
        //     89: instanceof  #115 // org.freedesktop.dbus.messages.MethodCall
        //     92: ifeq  119 (offset +27)
        //     95: ldc  #54 // 'org.freedesktop.DBus'
        //     97: aload_3
        //     98: invokevirtual  #207 // org.freedesktop.dbus.messages.Message.getDestination:()Ljava/lang/String;
        //    101: invokevirtual  #144 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //    104: ifeq  119 (offset +15)
        //    107: ldc  #32 // 'Hello'
        //    109: aload_3
        //    110: invokevirtual  #209 // org.freedesktop.dbus.messages.Message.getName:()Ljava/lang/String;
        //    113: invokevirtual  #144 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //    116: ifne  152 (offset +36)
        //    119: aload_0
        //    120: aload_2
        //    121: aload  4
        //    123: ldc  #54 // 'org.freedesktop.DBus'
        //    125: aconst_null
        //    126: ldc  #55 // 'org.freedesktop.DBus.Error.AccessDenied'
        //    128: aload_3
        //    129: invokevirtual  #210 // org.freedesktop.dbus.messages.Message.getSerial:()J
        //    132: ldc  #58 // 's'
        //    134: iconst_1
        //    135: anewarray  #68 // java.lang.Object
        //    138: dup
        //    139: iconst_0
        //    140: ldc  #53 // 'You must send a Hello message'
        //    142: aastore
        //    143: invokevirtual  #212 // org.freedesktop.dbus.messages.MessageFactory.createError:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;[Ljava/lang/Object;)Lorg/freedesktop/dbus/messages/Error;
        //    146: invokevirtual  #179 // org.freedesktop.dbus.bin.DBusDaemon.send:(Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;Lorg/freedesktop/dbus/messages/Message;)V
        //    149: goto  427 (offset +278)
        //    152: aconst_null
        //    153: aload_2
        //    154: getfield  #131 // org.freedesktop.dbus.bin.DBusDaemon$ConnectionStruct.unique:Ljava/lang/String;
        //    157: if_acmpeq  182 (offset +25)
        //    160: aload_3
        //    161: aload_2
        //    162: getfield  #131 // org.freedesktop.dbus.bin.DBusDaemon$ConnectionStruct.unique:Ljava/lang/String;
        //    165: invokevirtual  #211 // org.freedesktop.dbus.messages.Message.setSource:(Ljava/lang/String;)V
        //    168: getstatic  #119 // org.freedesktop.dbus.bin.DBusDaemon.LOGGER:Lorg/slf4j/Logger;
        //    171: ldc  #51 // 'Updated source to {}'
        //    173: aload_2
        //    174: getfield  #131 // org.freedesktop.dbus.bin.DBusDaemon$ConnectionStruct.unique:Ljava/lang/String;
        //    177: invokeinterface  #239 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //    182: goto  229 (offset +47)
        //    185: astore  5
        //    187: getstatic  #119 // org.freedesktop.dbus.bin.DBusDaemon.LOGGER:Lorg/slf4j/Logger;
        //    190: ldc  #30 // 'Error setting source'
        //    192: aload  5
        //    194: invokeinterface  #236 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Throwable;)V, count 3
        //    199: aload_0
        //    200: aload_2
        //    201: aload  4
        //    203: ldc  #54 // 'org.freedesktop.DBus'
        //    205: aconst_null
        //    206: ldc  #56 // 'org.freedesktop.DBus.Error.GeneralError'
        //    208: aload_3
        //    209: invokevirtual  #210 // org.freedesktop.dbus.messages.Message.getSerial:()J
        //    212: ldc  #58 // 's'
        //    214: iconst_1
        //    215: anewarray  #68 // java.lang.Object
        //    218: dup
        //    219: iconst_0
        //    220: ldc  #42 // 'Sending message failed'
        //    222: aastore
        //    223: invokevirtual  #212 // org.freedesktop.dbus.messages.MessageFactory.createError:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;[Ljava/lang/Object;)Lorg/freedesktop/dbus/messages/Error;
        //    226: invokevirtual  #179 // org.freedesktop.dbus.bin.DBusDaemon.send:(Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;Lorg/freedesktop/dbus/messages/Message;)V
        //    229: ldc  #54 // 'org.freedesktop.DBus'
        //    231: aload_3
        //    232: invokevirtual  #207 // org.freedesktop.dbus.messages.Message.getDestination:()Ljava/lang/String;
        //    235: invokevirtual  #144 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //    238: ifeq  259 (offset +21)
        //    241: aload_0
        //    242: getfield  #121 // org.freedesktop.dbus.bin.DBusDaemon.dbusServer:Lorg/freedesktop/dbus/bin/DBusDaemon$DBusServer;
        //    245: aload_2
        //    246: aload_1
        //    247: getfield  #132 // org.freedesktop.dbus.bin.DBusDaemon$Pair.first:Ljava/lang/Object;
        //    250: checkcast  #113 // org.freedesktop.dbus.messages.Message
        //    253: invokevirtual  #192 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.handleMessage:(Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;Lorg/freedesktop/dbus/messages/Message;)V
        //    256: goto  427 (offset +171)
        //    259: aload_3
        //    260: instanceof  #112 // org.freedesktop.dbus.messages.DBusSignal
        //    263: ifeq  349 (offset +86)
        //    266: aload_0
        //    267: getfield  #128 // org.freedesktop.dbus.bin.DBusDaemon.sigrecips:Ljava/util/List;
        //    270: dup
        //    271: astore  6
        //    273: monitorenter
        //    274: new  #79 // java.util.ArrayList
        //    277: dup
        //    278: aload_0
        //    279: getfield  #128 // org.freedesktop.dbus.bin.DBusDaemon.sigrecips:Ljava/util/List;
        //    282: invokespecial  #158 // java.util.ArrayList.<init>:(Ljava/util/Collection;)V
        //    285: astore  5
        //    287: aload  6
        //    289: monitorexit
        //    290: goto  301 (offset +11)
        //    293: astore  7
        //    295: aload  6
        //    297: monitorexit
        //    298: aload  7
        //    300: athrow
        //    301: aload  5
        //    303: astore  6
        //    305: aload  6
        //    307: invokeinterface  #217 // java.util.List.iterator:()Ljava/util/Iterator;, count 1
        //    312: astore  7
        //    314: aload  7
        //    316: invokeinterface  #214 // java.util.Iterator.hasNext:()Z, count 1
        //    321: ifeq  346 (offset +25)
        //    324: aload  7
        //    326: invokeinterface  #215 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //    331: checkcast  #97 // org.freedesktop.dbus.bin.DBusDaemon$ConnectionStruct
        //    334: astore  8
        //    336: aload_0
        //    337: aload  8
        //    339: aload_3
        //    340: invokevirtual  #179 // org.freedesktop.dbus.bin.DBusDaemon.send:(Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;Lorg/freedesktop/dbus/messages/Message;)V
        //    343: goto  314 (offset -29)
        //    346: goto  427 (offset +81)
        //    349: aload_0
        //    350: getfield  #123 // org.freedesktop.dbus.bin.DBusDaemon.names:Ljava/util/Map;
        //    353: aload_3
        //    354: invokevirtual  #207 // org.freedesktop.dbus.messages.Message.getDestination:()Ljava/lang/String;
        //    357: invokeinterface  #220 // java.util.Map.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //    362: checkcast  #97 // org.freedesktop.dbus.bin.DBusDaemon$ConnectionStruct
        //    365: astore  5
        //    367: aconst_null
        //    368: aload  5
        //    370: if_acmpne  420 (offset +50)
        //    373: aload_0
        //    374: aload_2
        //    375: aload  4
        //    377: ldc  #54 // 'org.freedesktop.DBus'
        //    379: aconst_null
        //    380: ldc  #57 // 'org.freedesktop.DBus.Error.ServiceUnknown'
        //    382: aload_3
        //    383: invokevirtual  #210 // org.freedesktop.dbus.messages.Message.getSerial:()J
        //    386: ldc  #58 // 's'
        //    388: iconst_1
        //    389: anewarray  #68 // java.lang.Object
        //    392: dup
        //    393: iconst_0
        //    394: ldc  #48 // "The name `%s' does not exist"
        //    396: iconst_1
        //    397: anewarray  #68 // java.lang.Object
        //    400: dup
        //    401: iconst_0
        //    402: aload_3
        //    403: invokevirtual  #207 // org.freedesktop.dbus.messages.Message.getDestination:()Ljava/lang/String;
        //    406: aastore
        //    407: invokestatic  #145 // java.lang.String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    410: aastore
        //    411: invokevirtual  #212 // org.freedesktop.dbus.messages.MessageFactory.createError:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;[Ljava/lang/Object;)Lorg/freedesktop/dbus/messages/Error;
        //    414: invokevirtual  #179 // org.freedesktop.dbus.bin.DBusDaemon.send:(Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;Lorg/freedesktop/dbus/messages/Message;)V
        //    417: goto  427 (offset +10)
        //    420: aload_0
        //    421: aload  5
        //    423: aload_3
        //    424: invokevirtual  #179 // org.freedesktop.dbus.bin.DBusDaemon.send:(Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;Lorg/freedesktop/dbus/messages/Message;)V
        //    427: goto  15 (offset -412)
        //    430: astore_1
        //    431: getstatic  #119 // org.freedesktop.dbus.bin.DBusDaemon.LOGGER:Lorg/slf4j/Logger;
        //    434: ldc  #29 // 'Error processing connection'
        //    436: aload_1
        //    437: invokeinterface  #236 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Throwable;)V, count 3
        //    442: goto  15 (offset -427)
        //    445: astore_1
        //    446: getstatic  #119 // org.freedesktop.dbus.bin.DBusDaemon.LOGGER:Lorg/slf4j/Logger;
        //    449: ldc  #34 // 'Interrupted'
        //    451: invokeinterface  #233 // org.slf4j.Logger.debug:(Ljava/lang/String;)V, count 2
        //    456: aload_0
        //    457: invokevirtual  #170 // org.freedesktop.dbus.bin.DBusDaemon.close:()V
        //    460: aload_0
        //    461: invokevirtual  #171 // org.freedesktop.dbus.bin.DBusDaemon.interrupt:()V
        //    464: goto  15 (offset -449)
        //    467: return
        //       Exception table:
        //         from 152 to 182 target 185 type org.freedesktop.dbus.exceptions.DBusException
        //         from 274 to 290 target 293 type any
        //         from 293 to 298 target 293 type any
        //         from 22 to 427 target 430 type org.freedesktop.dbus.exceptions.DBusException
        //         from 22 to 427 target 445 type java.lang.InterruptedException
    }

  private static void logMessage(String arg0, Message arg1, String arg2) {
        Message var3 = arg1;
        if (arg1 != null) {
            if (Introspectable.class.getName().equals(arg1.getInterface())) {
                if (!LOGGER.isTraceEnabled()) {
                    var3 = "<Introspection data only visible in loglevel trace>";
                }
            }
        }
        if (!LOGGER.isTraceEnabled()) {
            LOGGER.debug(arg0, arg1, arg2);
        } else {
            LOGGER.trace(arg0, var3, arg2);
        }
    }

  public synchronized boolean isRunning() {
        return run.get();
    }

  public void close() {
        run.set(false);
        if (!conns.isEmpty()) {
            HashSet var1 = new HashSet(conns.keySet());
            Iterator var2 = var1.iterator();
            while (var2.hasNext()) {
                DBusDaemon_ConnectionStruct var3 = ((DBusDaemon_ConnectionStruct) var2.next());
                removeConnection(var3);
                continue;
            }
        }
        sender.terminate();
        if (transport != null) {
            LOGGER.debug("Terminating transport {}", transport);
            try {
                transport.close();
            } catch (IOException e1) {
                Throwable var1 = e1;
                LOGGER.debug("Error closing transport", var1);
            }
        }
        interrupt();
    }

  private void removeConnection(DBusDaemon_ConnectionStruct arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #120 // org.freedesktop.dbus.bin.DBusDaemon.conns:Ljava/util/Map;
        //      4: aload_1
        //      5: invokeinterface  #224 // java.util.Map.remove:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //     10: checkcast  #98 // org.freedesktop.dbus.bin.DBusDaemon$DBusDaemonReaderThread
        //     13: astore_2
        //     14: aload_2
        //     15: ifnull  76 (offset +61)
        //     18: getstatic  #119 // org.freedesktop.dbus.bin.DBusDaemon.LOGGER:Lorg/slf4j/Logger;
        //     21: ldc  #46 // 'Terminating reader thread for {}'
        //     23: aload_1
        //     24: invokeinterface  #234 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //     29: aload_2
        //     30: invokevirtual  #187 // org.freedesktop.dbus.bin.DBusDaemon$DBusDaemonReaderThread.terminate:()V
        //     33: aload_1
        //     34: getfield  #130 // org.freedesktop.dbus.bin.DBusDaemon$ConnectionStruct.connection:Lorg/freedesktop/dbus/connections/transports/TransportConnection;
        //     37: ifnull  61 (offset +24)
        //     40: aload_1
        //     41: getfield  #130 // org.freedesktop.dbus.bin.DBusDaemon$ConnectionStruct.connection:Lorg/freedesktop/dbus/connections/transports/TransportConnection;
        //     44: invokevirtual  #203 // org.freedesktop.dbus.connections.transports.TransportConnection.close:()V
        //     47: getstatic  #119 // org.freedesktop.dbus.bin.DBusDaemon.LOGGER:Lorg/slf4j/Logger;
        //     50: ldc  #45 // 'Terminated connection {}'
        //     52: aload_1
        //     53: getfield  #130 // org.freedesktop.dbus.bin.DBusDaemon$ConnectionStruct.connection:Lorg/freedesktop/dbus/connections/transports/TransportConnection;
        //     56: invokeinterface  #234 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //     61: goto  76 (offset +15)
        //     64: astore_3
        //     65: getstatic  #119 // org.freedesktop.dbus.bin.DBusDaemon.LOGGER:Lorg/slf4j/Logger;
        //     68: ldc  #31 // 'Error while closing socketchannel'
        //     70: aload_3
        //     71: invokeinterface  #236 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Throwable;)V, count 3
        //     76: getstatic  #119 // org.freedesktop.dbus.bin.DBusDaemon.LOGGER:Lorg/slf4j/Logger;
        //     79: ldc  #41 // 'Removing signal destination {}'
        //     81: aload_1
        //     82: invokeinterface  #234 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //     87: aload_0
        //     88: getfield  #128 // org.freedesktop.dbus.bin.DBusDaemon.sigrecips:Ljava/util/List;
        //     91: dup
        //     92: astore_3
        //     93: monitorenter
        //     94: aload_0
        //     95: getfield  #128 // org.freedesktop.dbus.bin.DBusDaemon.sigrecips:Ljava/util/List;
        //     98: aload_1
        //     99: invokedynamic  #242 // invokedynamic test:(Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;)Ljava/util/function/Predicate;
        //    104: invokeinterface  #218 // java.util.List.removeIf:(Ljava/util/function/Predicate;)Z, count 2
        //    109: ifeq  123 (offset +14)
        //    112: getstatic  #119 // org.freedesktop.dbus.bin.DBusDaemon.LOGGER:Lorg/slf4j/Logger;
        //    115: ldc  #39 // 'Removed one or more signal destinations for {}'
        //    117: aload_1
        //    118: invokeinterface  #234 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //    123: aload_3
        //    124: monitorexit
        //    125: goto  135 (offset +10)
        //    128: astore  4
        //    130: aload_3
        //    131: monitorexit
        //    132: aload  4
        //    134: athrow
        //    135: getstatic  #119 // org.freedesktop.dbus.bin.DBusDaemon.LOGGER:Lorg/slf4j/Logger;
        //    138: ldc  #40 // 'Removing name registration for {}'
        //    140: aload_1
        //    141: invokeinterface  #234 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //    146: aload_0
        //    147: getfield  #123 // org.freedesktop.dbus.bin.DBusDaemon.names:Ljava/util/Map;
        //    150: dup
        //    151: astore_3
        //    152: monitorenter
        //    153: new  #79 // java.util.ArrayList
        //    156: dup
        //    157: invokespecial  #157 // java.util.ArrayList.<init>:()V
        //    160: astore  4
        //    162: aload_0
        //    163: getfield  #123 // org.freedesktop.dbus.bin.DBusDaemon.names:Ljava/util/Map;
        //    166: invokeinterface  #219 // java.util.Map.entrySet:()Ljava/util/Set;, count 1
        //    171: invokeinterface  #227 // java.util.Set.iterator:()Ljava/util/Iterator;, count 1
        //    176: astore  5
        //    178: aload  5
        //    180: invokeinterface  #214 // java.util.Iterator.hasNext:()Z, count 1
        //    185: ifeq  232 (offset +47)
        //    188: aload  5
        //    190: invokeinterface  #215 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //    195: checkcast  #87 // java.util.Map$Entry
        //    198: astore  6
        //    200: aload  6
        //    202: invokeinterface  #226 // java.util.Map$Entry.getValue:()Ljava/lang/Object;, count 1
        //    207: aload_1
        //    208: if_acmpne  229 (offset +21)
        //    211: aload  4
        //    213: aload  6
        //    215: invokeinterface  #225 // java.util.Map$Entry.getKey:()Ljava/lang/Object;, count 1
        //    220: checkcast  #69 // java.lang.String
        //    223: invokeinterface  #216 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //    228: pop
        //    229: goto  178 (offset -51)
        //    232: aload  4
        //    234: invokeinterface  #217 // java.util.List.iterator:()Ljava/util/Iterator;, count 1
        //    239: astore  5
        //    241: aload  5
        //    243: invokeinterface  #214 // java.util.Iterator.hasNext:()Z, count 1
        //    248: ifeq  317 (offset +69)
        //    251: aload  5
        //    253: invokeinterface  #215 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //    258: checkcast  #69 // java.lang.String
        //    261: astore  6
        //    263: aload_0
        //    264: getfield  #123 // org.freedesktop.dbus.bin.DBusDaemon.names:Ljava/util/Map;
        //    267: aload  6
        //    269: invokeinterface  #224 // java.util.Map.remove:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //    274: pop
        //    275: aload_0
        //    276: aconst_null
        //    277: new  #110 // org.freedesktop.dbus.interfaces.DBus$NameOwnerChanged
        //    280: dup
        //    281: ldc  #22 // '/org/freedesktop/DBus'
        //    283: aload  6
        //    285: aload_1
        //    286: getfield  #131 // org.freedesktop.dbus.bin.DBusDaemon$ConnectionStruct.unique:Ljava/lang/String;
        //    289: ldc  #2 // ''
        //    291: invokespecial  #206 // org.freedesktop.dbus.interfaces.DBus$NameOwnerChanged.<init>:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    294: invokevirtual  #179 // org.freedesktop.dbus.bin.DBusDaemon.send:(Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;Lorg/freedesktop/dbus/messages/Message;)V
        //    297: goto  314 (offset +17)
        //    300: astore  7
        //    302: getstatic  #119 // org.freedesktop.dbus.bin.DBusDaemon.LOGGER:Lorg/slf4j/Logger;
        //    305: ldc  #50 // 'Unable to change owner'
        //    307: aload  7
        //    309: invokeinterface  #236 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Throwable;)V, count 3
        //    314: goto  241 (offset -73)
        //    317: aload_3
        //    318: monitorexit
        //    319: goto  329 (offset +10)
        //    322: astore  8
        //    324: aload_3
        //    325: monitorexit
        //    326: aload  8
        //    328: athrow
        //    329: return
        //       Exception table:
        //         from 33 to 61 target 64 type java.io.IOException
        //         from 94 to 125 target 128 type any
        //         from 128 to 132 target 128 type any
        //         from 275 to 297 target 300 type org.freedesktop.dbus.exceptions.DBusException
        //         from 153 to 319 target 322 type any
        //         from 322 to 326 target 322 type any
    }

   void addSock(TransportConnection arg0) {
        LOGGER.debug("New Client");
        DBusDaemon_ConnectionStruct var2 = new DBusDaemon_ConnectionStruct(arg0);
        DBusDaemon_DBusDaemonReaderThread var3 = new DBusDaemon_DBusDaemonReaderThread(this, var2);
        conns.put(var2, var3);
        var3.start();
    }

  public static void syntax() {
        System.out.println("Syntax: DBusDaemon [--version] [-v] [--help] [-h] [--listen address] [-l address] [--print-address] [-r] [--pidfile file] [-p file] [--addressfile file] [--auth-mode AUTH_ANONYMOUS|AUTH_COOKIE|AUTH_EXTERNAL] [-m AUTH_ANONYMOUS|AUTH_COOKIE|AUTH_EXTERNAL][-a file] [--unix] [-u] [--tcp] [-t] ");
        System.exit(1);
    }

  public static void version() {
        System.out.println("D-Bus Java Version: " + System.getProperty("Version"));
        System.exit(1);
    }

  public static void saveFile(String arg0, String arg1) {
        PrintWriter var2 = new PrintWriter(new FileOutputStream(arg1));
        try {
            var2.println(arg0);
        } catch (Throwable var3) {
        }
    }

  public static void main(String[] arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aconst_null
        //      1: astore_1
        //      2: aconst_null
        //      3: astore_2
        //      4: aconst_null
        //      5: astore_3
        //      6: aconst_null
        //      7: astore  4
        //      9: iconst_0
        //     10: istore  5
        //     12: iconst_1
        //     13: istore  6
        //     15: iconst_0
        //     16: istore  7
        //     18: iconst_0
        //     19: istore  8
        //     21: iload  8
        //     23: aload_0
        //     24: arraylength
        //     25: if_icmpge  334 (offset +309)
        //     28: ldc  #6 // '--help'
        //     30: aload_0
        //     31: iload  8
        //     33: aaload
        //     34: invokevirtual  #144 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //     37: ifne  52 (offset +15)
        //     40: ldc  #14 // '-h'
        //     42: aload_0
        //     43: iload  8
        //     45: aaload
        //     46: invokevirtual  #144 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //     49: ifeq  58 (offset +9)
        //     52: invokestatic  #182 // org.freedesktop.dbus.bin.DBusDaemon.syntax:()V
        //     55: goto  328 (offset +273)
        //     58: ldc  #12 // '--version'
        //     60: aload_0
        //     61: iload  8
        //     63: aaload
        //     64: invokevirtual  #144 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //     67: ifne  82 (offset +15)
        //     70: ldc  #21 // '-v'
        //     72: aload_0
        //     73: iload  8
        //     75: aaload
        //     76: invokevirtual  #144 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //     79: ifeq  88 (offset +9)
        //     82: invokestatic  #183 // org.freedesktop.dbus.bin.DBusDaemon.version:()V
        //     85: goto  328 (offset +243)
        //     88: ldc  #7 // '--listen'
        //     90: aload_0
        //     91: iload  8
        //     93: aaload
        //     94: invokevirtual  #144 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //     97: ifne  112 (offset +15)
        //    100: ldc  #15 // '-l'
        //    102: aload_0
        //    103: iload  8
        //    105: aaload
        //    106: invokevirtual  #144 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //    109: ifeq  123 (offset +14)
        //    112: aload_0
        //    113: iinc  8, 1
        //    116: iload  8
        //    118: aaload
        //    119: astore_1
        //    120: goto  328 (offset +208)
        //    123: ldc  #8 // '--pidfile'
        //    125: aload_0
        //    126: iload  8
        //    128: aaload
        //    129: invokevirtual  #144 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //    132: ifne  147 (offset +15)
        //    135: ldc  #17 // '-p'
        //    137: aload_0
        //    138: iload  8
        //    140: aaload
        //    141: invokevirtual  #144 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //    144: ifeq  158 (offset +14)
        //    147: aload_0
        //    148: iinc  8, 1
        //    151: iload  8
        //    153: aaload
        //    154: astore_2
        //    155: goto  328 (offset +173)
        //    158: ldc  #4 // '--addressfile'
        //    160: aload_0
        //    161: iload  8
        //    163: aaload
        //    164: invokevirtual  #144 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //    167: ifne  182 (offset +15)
        //    170: ldc  #13 // '-a'
        //    172: aload_0
        //    173: iload  8
        //    175: aaload
        //    176: invokevirtual  #144 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //    179: ifeq  193 (offset +14)
        //    182: aload_0
        //    183: iinc  8, 1
        //    186: iload  8
        //    188: aaload
        //    189: astore_3
        //    190: goto  328 (offset +138)
        //    193: ldc  #9 // '--print-address'
        //    195: aload_0
        //    196: iload  8
        //    198: aaload
        //    199: invokevirtual  #144 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //    202: ifne  217 (offset +15)
        //    205: ldc  #18 // '-r'
        //    207: aload_0
        //    208: iload  8
        //    210: aaload
        //    211: invokevirtual  #144 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //    214: ifeq  223 (offset +9)
        //    217: iconst_1
        //    218: istore  5
        //    220: goto  328 (offset +108)
        //    223: ldc  #11 // '--unix'
        //    225: aload_0
        //    226: iload  8
        //    228: aaload
        //    229: invokevirtual  #144 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //    232: ifne  247 (offset +15)
        //    235: ldc  #20 // '-u'
        //    237: aload_0
        //    238: iload  8
        //    240: aaload
        //    241: invokevirtual  #144 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //    244: ifeq  256 (offset +12)
        //    247: iconst_1
        //    248: istore  6
        //    250: iconst_0
        //    251: istore  7
        //    253: goto  328 (offset +75)
        //    256: ldc  #10 // '--tcp'
        //    258: aload_0
        //    259: iload  8
        //    261: aaload
        //    262: invokevirtual  #144 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //    265: ifne  280 (offset +15)
        //    268: ldc  #19 // '-t'
        //    270: aload_0
        //    271: iload  8
        //    273: aaload
        //    274: invokevirtual  #144 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //    277: ifeq  289 (offset +12)
        //    280: iconst_1
        //    281: istore  7
        //    283: iconst_0
        //    284: istore  6
        //    286: goto  328 (offset +42)
        //    289: ldc  #5 // '--auth-mode'
        //    291: aload_0
        //    292: iload  8
        //    294: aaload
        //    295: invokevirtual  #144 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //    298: ifne  313 (offset +15)
        //    301: ldc  #16 // '-m'
        //    303: aload_0
        //    304: iload  8
        //    306: aaload
        //    307: invokevirtual  #144 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //    310: ifeq  325 (offset +15)
        //    313: aload_0
        //    314: iinc  8, 1
        //    317: iload  8
        //    319: aaload
        //    320: astore  4
        //    322: goto  328 (offset +6)
        //    325: invokestatic  #182 // org.freedesktop.dbus.bin.DBusDaemon.syntax:()V
        //    328: iinc  8, 1
        //    331: goto  21 (offset -310)
        //    334: goto  342 (offset +8)
        //    337: astore  8
        //    339: invokestatic  #182 // org.freedesktop.dbus.bin.DBusDaemon.syntax:()V
        //    342: aconst_null
        //    343: aload_1
        //    344: if_acmpne  362 (offset +18)
        //    347: iload  6
        //    349: ifeq  362 (offset +13)
        //    352: ldc  #49 // 'UNIX'
        //    354: iconst_1
        //    355: invokestatic  #200 // org.freedesktop.dbus.connections.transports.TransportBuilder.createDynamicSession:(Ljava/lang/String;Z)Ljava/lang/String;
        //    358: astore_1
        //    359: goto  379 (offset +20)
        //    362: aconst_null
        //    363: aload_1
        //    364: if_acmpne  379 (offset +15)
        //    367: iload  7
        //    369: ifeq  379 (offset +10)
        //    372: ldc  #44 // 'TCP'
        //    374: iconst_1
        //    375: invokestatic  #200 // org.freedesktop.dbus.connections.transports.TransportBuilder.createDynamicSession:(Ljava/lang/String;Z)Ljava/lang/String;
        //    378: astore_1
        //    379: aload_1
        //    380: invokestatic  #198 // org.freedesktop.dbus.connections.BusAddress.of:(Ljava/lang/String;)Lorg/freedesktop/dbus/connections/BusAddress;
        //    383: astore  8
        //    385: iload  5
        //    387: ifeq  397 (offset +10)
        //    390: getstatic  #118 // java.lang.System.out:Ljava/io/PrintStream;
        //    393: aload_1
        //    394: invokevirtual  #135 // java.io.PrintStream.println:(Ljava/lang/String;)V
        //    397: aconst_null
        //    398: astore  9
        //    400: aload  4
        //    402: ifnull  447 (offset +45)
        //    405: aload  4
        //    407: astore  10
        //    409: invokestatic  #202 // org.freedesktop.dbus.connections.transports.TransportBuilder$SaslAuthMode.values:()[Lorg/freedesktop/dbus/connections/transports/TransportBuilder$SaslAuthMode;
        //    412: invokestatic  #159 // java.util.Arrays.stream:([Ljava/lang/Object;)Ljava/util/stream/Stream;
        //    415: aload  10
        //    417: invokedynamic  #244 // invokedynamic test:(Ljava/lang/String;)Ljava/util/function/Predicate;
        //    422: invokeinterface  #231 // java.util.stream.Stream.filter:(Ljava/util/function/Predicate;)Ljava/util/stream/Stream;, count 2
        //    427: invokeinterface  #232 // java.util.stream.Stream.findFirst:()Ljava/util/Optional;, count 1
        //    432: aload  10
        //    434: invokedynamic  #245 // invokedynamic get:(Ljava/lang/String;)Ljava/util/function/Supplier;
        //    439: invokevirtual  #163 // java.util.Optional.orElseThrow:(Ljava/util/function/Supplier;)Ljava/lang/Object;
        //    442: checkcast  #106 // org.freedesktop.dbus.connections.transports.TransportBuilder$SaslAuthMode
        //    445: astore  9
        //    447: aconst_null
        //    448: aload_3
        //    449: if_acmpeq  457 (offset +8)
        //    452: aload_1
        //    453: aload_3
        //    454: invokestatic  #178 // org.freedesktop.dbus.bin.DBusDaemon.saveFile:(Ljava/lang/String;Ljava/lang/String;)V
        //    457: aconst_null
        //    458: aload_2
        //    459: if_acmpeq  471 (offset +12)
        //    462: ldc  #36 // 'Pid'
        //    464: invokestatic  #149 // java.lang.System.getProperty:(Ljava/lang/String;)Ljava/lang/String;
        //    467: aload_2
        //    468: invokestatic  #178 // org.freedesktop.dbus.bin.DBusDaemon.saveFile:(Ljava/lang/String;Ljava/lang/String;)V
        //    471: getstatic  #119 // org.freedesktop.dbus.bin.DBusDaemon.LOGGER:Lorg/slf4j/Logger;
        //    474: ldc  #26 // 'Binding to {}'
        //    476: aload_1
        //    477: invokeinterface  #237 // org.slf4j.Logger.info:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //    482: new  #102 // org.freedesktop.dbus.bin.EmbeddedDBusDaemon
        //    485: dup
        //    486: aload  8
        //    488: invokespecial  #194 // org.freedesktop.dbus.bin.EmbeddedDBusDaemon.<init>:(Lorg/freedesktop/dbus/connections/BusAddress;)V
        //    491: astore  10
        //    493: aload  10
        //    495: aload  9
        //    497: invokevirtual  #196 // org.freedesktop.dbus.bin.EmbeddedDBusDaemon.setSaslAuthMode:(Lorg/freedesktop/dbus/connections/transports/TransportBuilder$SaslAuthMode;)V
        //    500: aload  10
        //    502: invokevirtual  #197 // org.freedesktop.dbus.bin.EmbeddedDBusDaemon.startInForeground:()V
        //    505: aload  10
        //    507: invokevirtual  #195 // org.freedesktop.dbus.bin.EmbeddedDBusDaemon.close:()V
        //    510: goto  535 (offset +25)
        //    513: astore  11
        //    515: aload  10
        //    517: invokevirtual  #195 // org.freedesktop.dbus.bin.EmbeddedDBusDaemon.close:()V
        //    520: goto  532 (offset +12)
        //    523: astore  12
        //    525: aload  11
        //    527: aload  12
        //    529: invokevirtual  #151 // java.lang.Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //    532: aload  11
        //    534: athrow
        //    535: return
        //       Exception table:
        //         from 18 to 334 target 337 type java.lang.ArrayIndexOutOfBoundsException
        //         from 493 to 505 target 513 type java.lang.Throwable
        //         from 515 to 520 target 523 type java.lang.Throwable
    }

  private static IllegalArgumentException lambda$main$2(String arg0) {
        return new IllegalArgumentException("Auth mode '" + arg0 + "' unsupported");
    }

  private static boolean lambda$main$1(String arg0, TransportBuilder_SaslAuthMode arg1) {
        return arg1.name().toLowerCase().matches(arg0.toLowerCase());
    }

  private static boolean lambda$removeConnection$0(DBusDaemon_ConnectionStruct arg0, DBusDaemon_ConnectionStruct arg1) {
        return arg1.equals(arg0);
    }

}