// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.connections.base.AbstractConnectionBase
package org.freedesktop.dbus.connections.base;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.lang.ref.Reference;
import java.nio.channels.ClosedByInterruptException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import org.freedesktop.dbus.DBusCallInfo;
import org.freedesktop.dbus.RemoteObject;
import org.freedesktop.dbus.connections.BusAddress;
import org.freedesktop.dbus.connections.IDisconnectAction;
import org.freedesktop.dbus.connections.IDisconnectCallback;
import org.freedesktop.dbus.connections.base.FallbackContainer;
import org.freedesktop.dbus.connections.base.GlobalHandler;
import org.freedesktop.dbus.connections.base.IncomingMessageThread;
import org.freedesktop.dbus.connections.base.PendingCallbackManager;
import org.freedesktop.dbus.connections.base.ReceivingService;
import org.freedesktop.dbus.connections.config.ReceivingServiceConfig;
import org.freedesktop.dbus.connections.config.TransportConfig;
import org.freedesktop.dbus.connections.transports.AbstractTransport;
import org.freedesktop.dbus.connections.transports.TransportBuilder;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.exceptions.DBusExecutionException;
import org.freedesktop.dbus.exceptions.FatalDBusException;
import org.freedesktop.dbus.exceptions.NotConnected;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.messages.Error;
import org.freedesktop.dbus.messages.ExportedObject;
import org.freedesktop.dbus.messages.Message;
import org.freedesktop.dbus.messages.MessageFactory;
import org.freedesktop.dbus.messages.MethodCall;
import org.freedesktop.dbus.messages.ObjectTree;
import org.freedesktop.dbus.utils.NameableThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractConnectionBase implements Closeable {

    // ---- поля ----
  private static final Map INFOMAP;
  private final Logger logger;
  private final ObjectTree objectTree;
  private final Map exportedObjects;
  private final Map importedObjects;
  private final PendingCallbackManager callbackManager;
  private final FallbackContainer fallbackContainer;
  private final ExecutorService senderService;
  private final ReceivingService receivingService;
  private final IncomingMessageThread readerThread;
  private final Map handledSignals;
  private final Map genericHandledSignals;
  private final Map pendingCalls;
  private final Queue pendingErrorQueue;
  private final BusAddress busAddress;
  private final MessageFactory messageFactory;
  private AbstractTransport transport;
  private Optional disconnectCallback;
  private volatile boolean disconnecting;

    static {
        INFOMAP = new ConcurrentHashMap();
    }

  protected AbstractConnectionBase(TransportConfig arg0, ReceivingServiceConfig arg1) { // было: <init>
        super();
        logger = LoggerFactory.getLogger(getClass());
        exportedObjects = Collections.synchronizedMap(new HashMap());
        importedObjects = new ConcurrentHashMap();
        getExportedObjects().put(null, new ExportedObject(new GlobalHandler(this), false));
        disconnectCallback = Optional.ofNullable(null);
        disconnecting = false;
        handledSignals = new ConcurrentHashMap();
        genericHandledSignals = new ConcurrentHashMap();
        pendingCalls = Collections.synchronizedMap(new LinkedHashMap());
        callbackManager = new PendingCallbackManager();
        pendingErrorQueue = new ConcurrentLinkedQueue();
        TransportBuilder var3 = TransportBuilder.create(arg0);
        busAddress = var3.getAddress();
        String var4 = "DBus Sender Thread-";
        String var5 = "";
        if (logger.isDebugEnabled()) {
            var4 = "DBus Sender Thread: " + busAddress.isListeningSocket() + ", ";
            var5 = "RcvSvc: " + busAddress.isListeningSocket() + " ";
        }
        receivingService = new ReceivingService(var5, arg1);
        senderService = Executors.newFixedThreadPool(1, new NameableThreadFactory(var4, true));
        objectTree = new ObjectTree();
        fallbackContainer = new FallbackContainer();
        readerThread = ((IncomingMessageThread) Objects.requireNonNull(createReaderThread(busAddress), "Reader thread required"));
        try {
            transport = var3.build();
            messageFactory = ((MessageFactory) Optional.ofNullable(transport).map(lp0 -> (((AbstractTransport) lp0)).getMessageFactory()).orElseThrow());
        } catch (IOException var6) {
            logger.debug("Error creating transport", var6);
            if (var6 instanceof IOException) {
                IOException var7 = ((IOException) var6);
                internalDisconnect(var7);
            }
        }
    }

  public abstract DBusInterface getExportedObject(String arg0, String arg1);

  public abstract DBusInterface getExportedObject(String arg0, String arg1, Class arg2);

  protected abstract IncomingMessageThread createReaderThread(BusAddress arg0);

  public abstract String getMachineId();

   Message readIncoming() {
        if (isConnected()) {
            Object var1 = null;
        } else {
            return null;
        }
        try {
            Message var1 = getTransport().readMessage();
        } catch (IOException var2) {
            if (var2 instanceof EOFException) {
                disconnectCallback.ifPresent(lp0 -> lambda$readIncoming$0(((IDisconnectCallback) lp0)));
                if (disconnecting) {
                    return null;
                } else {
                    if (!getBusAddress().isListeningSocket()) {
                        if (!isConnected()) {
                            return ((Message) var1);
                        } else {
                            throw new FatalDBusException(var2);
                        }
                    } else {
                        return null;
                    }
                }
            } else {
                if (!(var2 instanceof ClosedByInterruptException)) {
                    if (!isConnected()) {
                        return ((Message) var1);
                    } else {
                        throw new FatalDBusException(var2);
                    }
                } else {
                    disconnectCallback.ifPresent(lp0 -> lambda$readIncoming$0(((IDisconnectCallback) lp0)));
                    if (disconnecting) {
                        return null;
                    } else {
                        if (!getBusAddress().isListeningSocket()) {
                            if (!isConnected()) {
                                return ((Message) var1);
                            } else {
                                throw new FatalDBusException(var2);
                            }
                        } else {
                            return null;
                        }
                    }
                }
            }
        }
    }

  protected final synchronized void internalDisconnect(IOException arg0) {
        IOException __stk1;
        if (isConnected()) {
            disconnecting = true;
            getLogger().debug("Disconnecting Abstract Connection");
            disconnectCallback.ifPresent(lp0 -> lambda$internalDisconnect$2(arg0, ((IDisconnectCallback) lp0)));
            readerThread.terminate();
            receivingService.shutdown(10, TimeUnit.SECONDS);
            getLogger().debug("Notifying {} method call(s) to stop waiting for replies", Integer.valueOf(getPendingCalls().size()));
            __stk1 = arg0 != null ? arg0 : new IOException("Disconnecting");
        } else {
            getLogger().debug("Ignoring disconnect, already disconnected");
            return;
        }
        IOException var2 = __stk1;
        Iterator var3 = getPendingCalls().values().iterator();
        while (var3.hasNext()) {
            MethodCall var4 = ((MethodCall) var3.next());
            try {
                var4.setReply(getMessageFactory().createError(var4, ((Throwable) var2)));
            } catch (DBusException var5) {
                getLogger().debug("Cannot set method reply to error", var5);
            }
            continue;
        }
        getLogger().debug("Shutting down SenderService");
        var3 = senderService.shutdownNow();
        if (arg0 != null) {
            if (!var3.isEmpty()) {
                getLogger().debug("Will not send {} messages due to connection closed by IOException", Integer.valueOf(var3.size()));
            }
        } else {
            Iterator var4 = var3.iterator();
            while (var4.hasNext()) {
                Runnable var5 = ((Runnable) var4.next());
                var5.run();
                continue;
            }
        }
        try {
            if (transport != null) {
                transport.close();
                transport = null;
            }
            receivingService.shutdownNow();
            disconnecting = false;
            return;
        } catch (IOException e2) {
            Throwable var4 = e2;
            getLogger().debug("Exception while disconnecting transport.", var4);
        }
        receivingService.shutdownNow();
        disconnecting = false;
    }

  protected synchronized void disconnect(IDisconnectAction arg0, IDisconnectAction arg1) {
        if (arg0 != null) {
            arg0.perform();
        }
        internalDisconnect(null);
        if (arg1 != null) {
            arg1.perform();
        }
    }

  public synchronized void disconnect() {
        getLogger().debug("Disconnect called");
        internalDisconnect(null);
    }

  protected synchronized Map getExportedObjects() {
        return exportedObjects;
    }

  protected Logger getLogger() {
        return logger;
    }

  protected FallbackContainer getFallbackContainer() {
        return fallbackContainer;
    }

  public BusAddress getAddress() {
        return busAddress;
    }

  public boolean isConnected() {
        return transport == null ? 0 : transport.isConnected();
    }

  protected AbstractTransport getTransport() {
        return transport;
    }

  public void sendMessage(Message arg0) {
        if (isConnected()) {
            Runnable var2 = () -> lambda$sendMessage$3(arg0);
            senderService.execute(var2);
            return;
        } else {
            throw new NotConnected("Cannot send message: Not connected");
        }
    }

  private void sendMessageInternally(Message arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokevirtual  #155 // org.freedesktop.dbus.connections.base.AbstractConnectionBase.isConnected:()Z
        //      4: ifne  17 (offset +13)
        //      7: new  #80 // org.freedesktop.dbus.exceptions.NotConnected
        //     10: dup
        //     11: ldc  #9 // 'Disconnected'
        //     13: invokespecial  #186 // org.freedesktop.dbus.exceptions.NotConnected.<init>:(Ljava/lang/String;)V
        //     16: athrow
        //     17: aload_1
        //     18: instanceof  #82 // org.freedesktop.dbus.messages.DBusSignal
        //     21: ifeq  52 (offset +31)
        //     24: aload_1
        //     25: checkcast  #82 // org.freedesktop.dbus.messages.DBusSignal
        //     28: astore_2
        //     29: aload_1
        //     30: invokevirtual  #191 // org.freedesktop.dbus.messages.Message.getEndianess:()B
        //     33: ifne  47 (offset +14)
        //     36: aload_1
        //     37: aload_0
        //     38: invokevirtual  #149 // org.freedesktop.dbus.connections.base.AbstractConnectionBase.getMessageFactory:()Lorg/freedesktop/dbus/messages/MessageFactory;
        //     41: invokevirtual  #196 // org.freedesktop.dbus.messages.MessageFactory.getEndianess:()B
        //     44: invokevirtual  #194 // org.freedesktop.dbus.messages.Message.updateEndianess:(B)V
        //     47: aload_2
        //     48: aload_0
        //     49: invokevirtual  #187 // org.freedesktop.dbus.messages.DBusSignal.appendbody:(Lorg/freedesktop/dbus/connections/base/AbstractConnectionBase;)V
        //     52: aload_1
        //     53: instanceof  #87 // org.freedesktop.dbus.messages.MethodCall
        //     56: ifeq  119 (offset +63)
        //     59: aload_1
        //     60: checkcast  #87 // org.freedesktop.dbus.messages.MethodCall
        //     63: astore_2
        //     64: iconst_0
        //     65: aload_1
        //     66: invokevirtual  #192 // org.freedesktop.dbus.messages.Message.getFlags:()I
        //     69: iconst_1
        //     70: iand
        //     71: if_icmpne  119 (offset +48)
        //     74: aconst_null
        //     75: aload_0
        //     76: invokevirtual  #151 // org.freedesktop.dbus.connections.base.AbstractConnectionBase.getPendingCalls:()Ljava/util/Map;
        //     79: if_acmpeq  119 (offset +40)
        //     82: aload_0
        //     83: invokevirtual  #151 // org.freedesktop.dbus.connections.base.AbstractConnectionBase.getPendingCalls:()Ljava/util/Map;
        //     86: dup
        //     87: astore_3
        //     88: monitorenter
        //     89: aload_0
        //     90: invokevirtual  #151 // org.freedesktop.dbus.connections.base.AbstractConnectionBase.getPendingCalls:()Ljava/util/Map;
        //     93: aload_1
        //     94: invokevirtual  #193 // org.freedesktop.dbus.messages.Message.getSerial:()J
        //     97: invokestatic  #117 // java.lang.Long.valueOf:(J)Ljava/lang/Long;
        //    100: aload_2
        //    101: invokeinterface  #211 // java.util.Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;, count 3
        //    106: pop
        //    107: aload_3
        //    108: monitorexit
        //    109: goto  119 (offset +10)
        //    112: astore  4
        //    114: aload_3
        //    115: monitorexit
        //    116: aload  4
        //    118: athrow
        //    119: aload_0
        //    120: invokevirtual  #148 // org.freedesktop.dbus.connections.base.AbstractConnectionBase.getLogger:()Lorg/slf4j/Logger;
        //    123: ldc  #27 // 'Writing message to connection {}: {}'
        //    125: aload_0
        //    126: invokevirtual  #153 // org.freedesktop.dbus.connections.base.AbstractConnectionBase.getTransport:()Lorg/freedesktop/dbus/connections/transports/AbstractTransport;
        //    129: aload_1
        //    130: invokeinterface  #233 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V, count 4
        //    135: aload_0
        //    136: invokevirtual  #153 // org.freedesktop.dbus.connections.base.AbstractConnectionBase.getTransport:()Lorg/freedesktop/dbus/connections/transports/AbstractTransport;
        //    139: aload_1
        //    140: invokevirtual  #178 // org.freedesktop.dbus.connections.transports.AbstractTransport.writeMessage:(Lorg/freedesktop/dbus/messages/Message;)V
        //    143: goto  357 (offset +214)
        //    146: astore_2
        //    147: aload_0
        //    148: invokevirtual  #148 // org.freedesktop.dbus.connections.base.AbstractConnectionBase.getLogger:()Lorg/slf4j/Logger;
        //    151: ldc  #15 // 'Exception while sending message.'
        //    153: aload_2
        //    154: invokeinterface  #234 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Throwable;)V, count 3
        //    159: aload_1
        //    160: instanceof  #87 // org.freedesktop.dbus.messages.MethodCall
        //    163: ifeq  212 (offset +49)
        //    166: aload_1
        //    167: checkcast  #87 // org.freedesktop.dbus.messages.MethodCall
        //    170: astore_3
        //    171: aload_2
        //    172: instanceof  #78 // org.freedesktop.dbus.exceptions.DBusExecutionException
        //    175: ifeq  212 (offset +37)
        //    178: aload_3
        //    179: aload_0
        //    180: invokevirtual  #149 // org.freedesktop.dbus.connections.base.AbstractConnectionBase.getMessageFactory:()Lorg/freedesktop/dbus/messages/MessageFactory;
        //    183: aload_1
        //    184: aload_2
        //    185: invokevirtual  #195 // org.freedesktop.dbus.messages.MessageFactory.createError:(Lorg/freedesktop/dbus/messages/Message;Ljava/lang/Throwable;)Lorg/freedesktop/dbus/messages/Error;
        //    188: invokevirtual  #197 // org.freedesktop.dbus.messages.MethodCall.setReply:(Lorg/freedesktop/dbus/messages/Message;)V
        //    191: goto  328 (offset +137)
        //    194: astore  5
        //    196: aload_0
        //    197: invokevirtual  #148 // org.freedesktop.dbus.connections.base.AbstractConnectionBase.getLogger:()Lorg/slf4j/Logger;
        //    200: ldc  #5 // 'Could not set message reply'
        //    202: aload  5
        //    204: invokeinterface  #234 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Throwable;)V, count 3
        //    209: goto  328 (offset +119)
        //    212: aload_1
        //    213: instanceof  #87 // org.freedesktop.dbus.messages.MethodCall
        //    216: ifeq  287 (offset +71)
        //    219: aload_1
        //    220: checkcast  #87 // org.freedesktop.dbus.messages.MethodCall
        //    223: astore  4
        //    225: aload_0
        //    226: invokevirtual  #148 // org.freedesktop.dbus.connections.base.AbstractConnectionBase.getLogger:()Lorg/slf4j/Logger;
        //    229: ldc  #24 // 'Setting reply to {} as an error'
        //    231: aload_1
        //    232: invokeinterface  #231 // org.slf4j.Logger.info:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //    237: aload  4
        //    239: aload_0
        //    240: invokevirtual  #149 // org.freedesktop.dbus.connections.base.AbstractConnectionBase.getMessageFactory:()Lorg/freedesktop/dbus/messages/MessageFactory;
        //    243: aload_1
        //    244: new  #78 // org.freedesktop.dbus.exceptions.DBusExecutionException
        //    247: dup
        //    248: aload_2
        //    249: invokevirtual  #115 // java.lang.Exception.getMessage:()Ljava/lang/String;
        //    252: invokedynamic  #242 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;
        //    257: invokespecial  #184 // org.freedesktop.dbus.exceptions.DBusExecutionException.<init>:(Ljava/lang/String;)V
        //    260: invokevirtual  #195 // org.freedesktop.dbus.messages.MessageFactory.createError:(Lorg/freedesktop/dbus/messages/Message;Ljava/lang/Throwable;)Lorg/freedesktop/dbus/messages/Error;
        //    263: invokevirtual  #197 // org.freedesktop.dbus.messages.MethodCall.setReply:(Lorg/freedesktop/dbus/messages/Message;)V
        //    266: goto  328 (offset +62)
        //    269: astore  5
        //    271: aload_0
        //    272: invokevirtual  #148 // org.freedesktop.dbus.connections.base.AbstractConnectionBase.getLogger:()Lorg/slf4j/Logger;
        //    275: ldc  #5 // 'Could not set message reply'
        //    277: aload  5
        //    279: invokeinterface  #234 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Throwable;)V, count 3
        //    284: goto  328 (offset +44)
        //    287: aload_1
        //    288: instanceof  #88 // org.freedesktop.dbus.messages.MethodReturn
        //    291: ifeq  328 (offset +37)
        //    294: aload_0
        //    295: invokevirtual  #153 // org.freedesktop.dbus.connections.base.AbstractConnectionBase.getTransport:()Lorg/freedesktop/dbus/connections/transports/AbstractTransport;
        //    298: aload_0
        //    299: invokevirtual  #149 // org.freedesktop.dbus.connections.base.AbstractConnectionBase.getMessageFactory:()Lorg/freedesktop/dbus/messages/MessageFactory;
        //    302: aload_1
        //    303: aload_2
        //    304: invokevirtual  #195 // org.freedesktop.dbus.messages.MessageFactory.createError:(Lorg/freedesktop/dbus/messages/Message;Ljava/lang/Throwable;)Lorg/freedesktop/dbus/messages/Error;
        //    307: invokevirtual  #178 // org.freedesktop.dbus.connections.transports.AbstractTransport.writeMessage:(Lorg/freedesktop/dbus/messages/Message;)V
        //    310: goto  328 (offset +18)
        //    313: astore  5
        //    315: aload_0
        //    316: invokevirtual  #148 // org.freedesktop.dbus.connections.base.AbstractConnectionBase.getLogger:()Lorg/slf4j/Logger;
        //    319: ldc  #13 // 'Error writing method return to transport'
        //    321: aload  5
        //    323: invokeinterface  #230 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Throwable;)V, count 3
        //    328: aload_2
        //    329: instanceof  #30 // java.io.IOException
        //    332: ifeq  357 (offset +25)
        //    335: aload_2
        //    336: checkcast  #30 // java.io.IOException
        //    339: astore_3
        //    340: aload_0
        //    341: invokevirtual  #148 // org.freedesktop.dbus.connections.base.AbstractConnectionBase.getLogger:()Lorg/slf4j/Logger;
        //    344: ldc  #17 // 'Fatal IOException while sending message, disconnecting'
        //    346: aload_2
        //    347: invokeinterface  #230 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Throwable;)V, count 3
        //    352: aload_0
        //    353: aload_3
        //    354: invokevirtual  #154 // org.freedesktop.dbus.connections.base.AbstractConnectionBase.internalDisconnect:(Ljava/io/IOException;)V
        //    357: return
        //       Exception table:
        //         from 89 to 109 target 112 type any
        //         from 112 to 116 target 112 type any
        //         from 0 to 143 target 146 type java.lang.Exception
        //         from 178 to 191 target 194 type org.freedesktop.dbus.exceptions.DBusException
        //         from 225 to 266 target 269 type org.freedesktop.dbus.exceptions.DBusException
        //         from 294 to 310 target 313 type java.io.IOException
        //         from 294 to 310 target 313 type org.freedesktop.dbus.exceptions.DBusException
    }

  public String getExportedObject(DBusInterface arg0) {
        Optional var2 = getExportedObjects().entrySet().stream().filter(lp0 -> lambda$getExportedObject$4(arg0, ((Entry) lp0))).findFirst();
        if (!var2.isPresent()) {
            RemoteObject var3 = ((RemoteObject) getImportedObjects().get(arg0));
            if (var3 == null) {
                throw new DBusException("Not an object exported or imported by this connection");
            } else {
                String var4 = var3.getObjectPath();
                if (var4 == null) {
                    throw new DBusException("Not an object exported or imported by this connection");
                } else {
                    return var4;
                }
            }
        } else {
            return ((String) (((Entry) var2.get())).getKey());
        }
    }

  public DBusExecutionException getError() {
        Error var1 = ((Error) getPendingErrorQueue().poll());
        if (var1 == null) {
            return null;
        } else {
            return var1.getException();
        }
    }

  public boolean connect() {
        if (getTransport().isConnected()) {
            return false;
        } else {
            if (!getTransport().isListening()) {
                return getTransport().connect() != null;
            } else {
                return getTransport().listen() != null;
            }
        }
    }

  public TransportConfig getTransportConfig() {
        return getTransport().getTransportConfig();
    }

  protected void listen() {
        readerThread.start();
    }

  public MessageFactory getMessageFactory() {
        return messageFactory;
    }

  protected Queue getPendingErrorQueue() {
        return pendingErrorQueue;
    }

  protected Map getHandledSignals() {
        return handledSignals;
    }

  protected Map getGenericHandledSignals() {
        return genericHandledSignals;
    }

  protected Map getPendingCalls() {
        return pendingCalls;
    }

  protected Map getImportedObjects() {
        return importedObjects;
    }

  public ObjectTree getObjectTree() {
        return objectTree;
    }

  protected PendingCallbackManager getCallbackManager() {
        return callbackManager;
    }

  protected ReceivingService getReceivingService() {
        return receivingService;
    }

  protected BusAddress getBusAddress() {
        return busAddress;
    }

  protected Map getInfoMap() {
        return INFOMAP;
    }

  public void unExportObject(String arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokevirtual  #146 // org.freedesktop.dbus.connections.base.AbstractConnectionBase.getExportedObjects:()Ljava/util/Map;
        //      4: dup
        //      5: astore_2
        //      6: monitorenter
        //      7: aload_0
        //      8: invokevirtual  #146 // org.freedesktop.dbus.connections.base.AbstractConnectionBase.getExportedObjects:()Ljava/util/Map;
        //     11: aload_1
        //     12: invokeinterface  #212 // java.util.Map.remove:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //     17: pop
        //     18: aload_0
        //     19: invokevirtual  #150 // org.freedesktop.dbus.connections.base.AbstractConnectionBase.getObjectTree:()Lorg/freedesktop/dbus/messages/ObjectTree;
        //     22: aload_1
        //     23: invokevirtual  #199 // org.freedesktop.dbus.messages.ObjectTree.remove:(Ljava/lang/String;)V
        //     26: aload_2
        //     27: monitorexit
        //     28: goto  36 (offset +8)
        //     31: astore_3
        //     32: aload_2
        //     33: monitorexit
        //     34: aload_3
        //     35: athrow
        //     36: return
        //       Exception table:
        //         from 7 to 28 target 31 type any
        //         from 31 to 34 target 31 type any
    }

  public static DBusCallInfo getCallInfo() {
        return ((DBusCallInfo) INFOMAP.get(Thread.currentThread()));
    }

  public IDisconnectCallback getDisconnectCallback() {
        return ((IDisconnectCallback) disconnectCallback.orElse(null));
    }

  public void setDisconnectCallback(IDisconnectCallback arg0) {
        disconnectCallback = Optional.ofNullable(arg0);
    }

  public void close() {
        disconnect();
    }

  public String toString() {
        return getClass().getSimpleName() + "[address=" + String.valueOf(busAddress) + "]";
    }

  private static boolean lambda$getExportedObject$4(DBusInterface arg0, Entry arg1) {
        return arg0.equals((((ExportedObject) arg1.getValue())).getObject().get());
    }

  private void lambda$sendMessage$3(Message arg0) {
        sendMessageInternally(arg0);
    }

  private static void lambda$internalDisconnect$2(IOException arg0, IDisconnectCallback arg1) {
        Objects.requireNonNull(arg1);
        Optional.ofNullable(arg0).ifPresentOrElse(lp0 -> arg1.disconnectOnError(((IOException) lp0)), () -> lambda$internalDisconnect$1(arg1));
    }

  private static void lambda$internalDisconnect$1(IDisconnectCallback arg0) {
        arg0.requestedDisconnect(null);
    }

  private static void lambda$readIncoming$0(IDisconnectCallback arg0) {
        arg0.clientDisconnect();
    }

}