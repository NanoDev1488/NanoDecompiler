// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.connections.base.ConnectionMessageHandler
package org.freedesktop.dbus.connections.base;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import org.freedesktop.dbus.annotations.DBusProperties;
import org.freedesktop.dbus.annotations.DBusProperty_Access;
import org.freedesktop.dbus.connections.base.AbstractConnectionBase;
import org.freedesktop.dbus.connections.base.ConnectionMethodInvocation;
import org.freedesktop.dbus.connections.config.ReceivingServiceConfig;
import org.freedesktop.dbus.connections.config.TransportConfig;
import org.freedesktop.dbus.errors.UnknownMethod;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.exceptions.DBusExecutionException;
import org.freedesktop.dbus.interfaces.DBusSigHandler;
import org.freedesktop.dbus.messages.DBusSignal;
import org.freedesktop.dbus.messages.Error;
import org.freedesktop.dbus.messages.ExportedObject;
import org.freedesktop.dbus.messages.Message;
import org.freedesktop.dbus.messages.MessageFactory;
import org.freedesktop.dbus.messages.MethodCall;
import org.freedesktop.dbus.messages.MethodReturn;
import org.freedesktop.dbus.propertyref.PropertyRef;
import org.freedesktop.dbus.types.Variant;
import org.slf4j.Logger;

public abstract class ConnectionMessageHandler extends ConnectionMethodInvocation {

  protected ConnectionMessageHandler(TransportConfig arg0, ReceivingServiceConfig arg1) { // было: <init>
        super(arg0, arg1);
    }

  protected void handleException(Message arg0, DBusExecutionException arg1) {
        try {
            sendMessage(getMessageFactory().createError(arg0, arg1));
        } catch (DBusException var3) {
            getLogger().warn("Exception caught while processing previous error.", var3);
        }
    }

  private void handleMessage(DBusSignal arg0, boolean arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokevirtual  #143 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getLogger:()Lorg/slf4j/Logger;
        //      4: ldc  #26 // 'Handling incoming signal: {}'
        //      6: aload_1
        //      7: invokeinterface  #229 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //     12: new  #58 // java.util.ArrayList
        //     15: dup
        //     16: invokespecial  #123 // java.util.ArrayList.<init>:()V
        //     19: astore_3
        //     20: new  #58 // java.util.ArrayList
        //     23: dup
        //     24: invokespecial  #123 // java.util.ArrayList.<init>:()V
        //     27: astore  4
        //     29: aload_0
        //     30: invokevirtual  #142 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getHandledSignals:()Ljava/util/Map;
        //     33: invokeinterface  #216 // java.util.Map.entrySet:()Ljava/util/Set;, count 1
        //     38: invokeinterface  #226 // java.util.Set.iterator:()Ljava/util/Iterator;, count 1
        //     43: astore  5
        //     45: aload  5
        //     47: invokeinterface  #210 // java.util.Iterator.hasNext:()Z, count 1
        //     52: ifeq  105 (offset +53)
        //     55: aload  5
        //     57: invokeinterface  #211 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //     62: checkcast  #66 // java.util.Map$Entry
        //     65: astore  6
        //     67: aload  6
        //     69: invokeinterface  #222 // java.util.Map$Entry.getKey:()Ljava/lang/Object;, count 1
        //     74: checkcast  #73 // org.freedesktop.dbus.DBusMatchRule
        //     77: aload_1
        //     78: iconst_0
        //     79: invokevirtual  #135 // org.freedesktop.dbus.DBusMatchRule.matches:(Lorg/freedesktop/dbus/messages/DBusSignal;Z)Z
        //     82: ifeq  102 (offset +20)
        //     85: aload_3
        //     86: aload  6
        //     88: invokeinterface  #223 // java.util.Map$Entry.getValue:()Ljava/lang/Object;, count 1
        //     93: checkcast  #60 // java.util.Collection
        //     96: invokeinterface  #212 // java.util.List.addAll:(Ljava/util/Collection;)Z, count 2
        //    101: pop
        //    102: goto  45 (offset -57)
        //    105: aload_0
        //    106: invokevirtual  #141 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getGenericHandledSignals:()Ljava/util/Map;
        //    109: invokeinterface  #216 // java.util.Map.entrySet:()Ljava/util/Set;, count 1
        //    114: invokeinterface  #226 // java.util.Set.iterator:()Ljava/util/Iterator;, count 1
        //    119: astore  5
        //    121: aload  5
        //    123: invokeinterface  #210 // java.util.Iterator.hasNext:()Z, count 1
        //    128: ifeq  182 (offset +54)
        //    131: aload  5
        //    133: invokeinterface  #211 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //    138: checkcast  #66 // java.util.Map$Entry
        //    141: astore  6
        //    143: aload  6
        //    145: invokeinterface  #222 // java.util.Map$Entry.getKey:()Ljava/lang/Object;, count 1
        //    150: checkcast  #73 // org.freedesktop.dbus.DBusMatchRule
        //    153: aload_1
        //    154: iconst_0
        //    155: invokevirtual  #135 // org.freedesktop.dbus.DBusMatchRule.matches:(Lorg/freedesktop/dbus/messages/DBusSignal;Z)Z
        //    158: ifeq  179 (offset +21)
        //    161: aload  4
        //    163: aload  6
        //    165: invokeinterface  #223 // java.util.Map$Entry.getValue:()Ljava/lang/Object;, count 1
        //    170: checkcast  #60 // java.util.Collection
        //    173: invokeinterface  #212 // java.util.List.addAll:(Ljava/util/Collection;)Z, count 2
        //    178: pop
        //    179: goto  121 (offset -58)
        //    182: aload_3
        //    183: invokeinterface  #213 // java.util.List.isEmpty:()Z, count 1
        //    188: ifeq  202 (offset +14)
        //    191: aload  4
        //    193: invokeinterface  #213 // java.util.List.isEmpty:()Z, count 1
        //    198: ifeq  202 (offset +4)
        //    201: return
        //    202: aload_0
        //    203: astore  5
        //    205: aload_3
        //    206: invokeinterface  #214 // java.util.List.iterator:()Ljava/util/Iterator;, count 1
        //    211: astore  6
        //    213: aload  6
        //    215: invokeinterface  #210 // java.util.Iterator.hasNext:()Z, count 1
        //    220: ifeq  289 (offset +69)
        //    223: aload  6
        //    225: invokeinterface  #211 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //    230: checkcast  #93 // org.freedesktop.dbus.interfaces.DBusSigHandler
        //    233: astore  7
        //    235: aload_0
        //    236: invokevirtual  #143 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getLogger:()Lorg/slf4j/Logger;
        //    239: ldc  #7 // 'Adding Runnable for signal {} with handler {}'
        //    241: aload_1
        //    242: aload  7
        //    244: invokeinterface  #234 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V, count 4
        //    249: aload_0
        //    250: aload_1
        //    251: aload  5
        //    253: aload  7
        //    255: invokedynamic  #238 // invokedynamic run:(Lorg/freedesktop/dbus/connections/base/ConnectionMessageHandler;Lorg/freedesktop/dbus/messages/DBusSignal;Lorg/freedesktop/dbus/connections/base/AbstractConnectionBase;Lorg/freedesktop/dbus/interfaces/DBusSigHandler;)Ljava/lang/Runnable;
        //    260: astore  8
        //    262: iload_2
        //    263: ifeq  279 (offset +16)
        //    266: aload_0
        //    267: invokevirtual  #147 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getReceivingService:()Lorg/freedesktop/dbus/connections/base/ReceivingService;
        //    270: aload  8
        //    272: invokevirtual  #178 // org.freedesktop.dbus.connections.base.ReceivingService.execSignalHandler:(Ljava/lang/Runnable;)I
        //    275: pop
        //    276: goto  286 (offset +10)
        //    279: aload  8
        //    281: invokeinterface  #209 // java.lang.Runnable.run:()V, count 1
        //    286: goto  213 (offset -73)
        //    289: aload  4
        //    291: invokeinterface  #214 // java.util.List.iterator:()Ljava/util/Iterator;, count 1
        //    296: astore  6
        //    298: aload  6
        //    300: invokeinterface  #210 // java.util.Iterator.hasNext:()Z, count 1
        //    305: ifeq  371 (offset +66)
        //    308: aload  6
        //    310: invokeinterface  #211 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //    315: checkcast  #93 // org.freedesktop.dbus.interfaces.DBusSigHandler
        //    318: astore  7
        //    320: aload_0
        //    321: invokevirtual  #143 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getLogger:()Lorg/slf4j/Logger;
        //    324: ldc  #7 // 'Adding Runnable for signal {} with handler {}'
        //    326: aload_1
        //    327: aload  7
        //    329: invokeinterface  #234 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V, count 4
        //    334: aload  7
        //    336: aload_1
        //    337: invokedynamic  #239 // invokedynamic run:(Lorg/freedesktop/dbus/interfaces/DBusSigHandler;Lorg/freedesktop/dbus/messages/DBusSignal;)Ljava/lang/Runnable;
        //    342: astore  8
        //    344: iload_2
        //    345: ifeq  361 (offset +16)
        //    348: aload_0
        //    349: invokevirtual  #147 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getReceivingService:()Lorg/freedesktop/dbus/connections/base/ReceivingService;
        //    352: aload  8
        //    354: invokevirtual  #178 // org.freedesktop.dbus.connections.base.ReceivingService.execSignalHandler:(Ljava/lang/Runnable;)I
        //    357: pop
        //    358: goto  368 (offset +10)
        //    361: aload  8
        //    363: invokeinterface  #209 // java.lang.Runnable.run:()V, count 1
        //    368: goto  298 (offset -70)
        //    371: return
    }

  protected void handleMessage(Error arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokevirtual  #143 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getLogger:()Lorg/slf4j/Logger;
        //      4: ldc  #23 // 'Handling incoming error: {}'
        //      6: aload_1
        //      7: invokeinterface  #229 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //     12: aconst_null
        //     13: astore_2
        //     14: aload_0
        //     15: invokevirtual  #145 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getPendingCalls:()Ljava/util/Map;
        //     18: ifnonnull  22 (offset +4)
        //     21: return
        //     22: aload_0
        //     23: invokevirtual  #145 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getPendingCalls:()Ljava/util/Map;
        //     26: dup
        //     27: astore_3
        //     28: monitorenter
        //     29: aload_0
        //     30: invokevirtual  #145 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getPendingCalls:()Ljava/util/Map;
        //     33: aload_1
        //     34: invokevirtual  #187 // org.freedesktop.dbus.messages.Error.getReplySerial:()J
        //     37: invokestatic  #111 // java.lang.Long.valueOf:(J)Ljava/lang/Long;
        //     40: invokeinterface  #215 // java.util.Map.containsKey:(Ljava/lang/Object;)Z, count 2
        //     45: ifeq  68 (offset +23)
        //     48: aload_0
        //     49: invokevirtual  #145 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getPendingCalls:()Ljava/util/Map;
        //     52: aload_1
        //     53: invokevirtual  #187 // org.freedesktop.dbus.messages.Error.getReplySerial:()J
        //     56: invokestatic  #111 // java.lang.Long.valueOf:(J)Ljava/lang/Long;
        //     59: invokeinterface  #221 // java.util.Map.remove:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //     64: checkcast  #98 // org.freedesktop.dbus.messages.MethodCall
        //     67: astore_2
        //     68: aload_3
        //     69: monitorexit
        //     70: goto  80 (offset +10)
        //     73: astore  4
        //     75: aload_3
        //     76: monitorexit
        //     77: aload  4
        //     79: athrow
        //     80: aload_2
        //     81: ifnull  158 (offset +77)
        //     84: aload_2
        //     85: aload_1
        //     86: invokevirtual  #200 // org.freedesktop.dbus.messages.MethodCall.setReply:(Lorg/freedesktop/dbus/messages/Message;)V
        //     89: aload_0
        //     90: invokevirtual  #138 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getCallbackManager:()Lorg/freedesktop/dbus/connections/base/PendingCallbackManager;
        //     93: aload_2
        //     94: invokevirtual  #174 // org.freedesktop.dbus.connections.base.PendingCallbackManager.removeCallback:(Lorg/freedesktop/dbus/messages/MethodCall;)Lorg/freedesktop/dbus/interfaces/CallbackHandler;
        //     97: astore_3
        //     98: aload_0
        //     99: invokevirtual  #143 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getLogger:()Lorg/slf4j/Logger;
        //    102: ldc  #39 // '{} = pendingCallbacks.remove({})'
        //    104: aload_3
        //    105: aload_2
        //    106: invokeinterface  #234 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V, count 4
        //    111: aconst_null
        //    112: aload_3
        //    113: if_acmpeq  155 (offset +42)
        //    116: aload_3
        //    117: astore  4
        //    119: aload_0
        //    120: invokevirtual  #143 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getLogger:()Lorg/slf4j/Logger;
        //    123: ldc  #5 // 'Adding Error Runnable with callback handler {}'
        //    125: aload  4
        //    127: invokeinterface  #233 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //    132: new  #81 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler$1
        //    135: dup
        //    136: aload_0
        //    137: aload_1
        //    138: aload  4
        //    140: invokespecial  #167 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler$1.<init>:(Lorg/freedesktop/dbus/connections/base/ConnectionMessageHandler;Lorg/freedesktop/dbus/messages/Error;Lorg/freedesktop/dbus/interfaces/CallbackHandler;)V
        //    143: astore  5
        //    145: aload_0
        //    146: invokevirtual  #147 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getReceivingService:()Lorg/freedesktop/dbus/connections/base/ReceivingService;
        //    149: aload  5
        //    151: invokevirtual  #175 // org.freedesktop.dbus.connections.base.ReceivingService.execErrorHandler:(Ljava/lang/Runnable;)I
        //    154: pop
        //    155: goto  169 (offset +14)
        //    158: aload_0
        //    159: invokevirtual  #146 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getPendingErrorQueue:()Ljava/util/Queue;
        //    162: aload_1
        //    163: invokeinterface  #224 // java.util.Queue.add:(Ljava/lang/Object;)Z, count 2
        //    168: pop
        //    169: return
        //       Exception table:
        //         from 29 to 70 target 73 type any
        //         from 73 to 77 target 73 type any
    }

  protected void handleMessage(MethodReturn arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokevirtual  #143 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getLogger:()Lorg/slf4j/Logger;
        //      4: ldc  #25 // 'Handling incoming method return: {}'
        //      6: aload_1
        //      7: invokeinterface  #229 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //     12: aconst_null
        //     13: astore_2
        //     14: aconst_null
        //     15: aload_0
        //     16: invokevirtual  #145 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getPendingCalls:()Ljava/util/Map;
        //     19: if_acmpne  23 (offset +4)
        //     22: return
        //     23: aload_0
        //     24: invokevirtual  #145 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getPendingCalls:()Ljava/util/Map;
        //     27: dup
        //     28: astore_3
        //     29: monitorenter
        //     30: aload_0
        //     31: invokevirtual  #145 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getPendingCalls:()Ljava/util/Map;
        //     34: aload_1
        //     35: invokevirtual  #201 // org.freedesktop.dbus.messages.MethodReturn.getReplySerial:()J
        //     38: invokestatic  #111 // java.lang.Long.valueOf:(J)Ljava/lang/Long;
        //     41: invokeinterface  #215 // java.util.Map.containsKey:(Ljava/lang/Object;)Z, count 2
        //     46: ifeq  69 (offset +23)
        //     49: aload_0
        //     50: invokevirtual  #145 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getPendingCalls:()Ljava/util/Map;
        //     53: aload_1
        //     54: invokevirtual  #201 // org.freedesktop.dbus.messages.MethodReturn.getReplySerial:()J
        //     57: invokestatic  #111 // java.lang.Long.valueOf:(J)Ljava/lang/Long;
        //     60: invokeinterface  #221 // java.util.Map.remove:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //     65: checkcast  #98 // org.freedesktop.dbus.messages.MethodCall
        //     68: astore_2
        //     69: aload_3
        //     70: monitorexit
        //     71: goto  81 (offset +10)
        //     74: astore  4
        //     76: aload_3
        //     77: monitorexit
        //     78: aload  4
        //     80: athrow
        //     81: aconst_null
        //     82: aload_2
        //     83: if_acmpeq  199 (offset +116)
        //     86: aload_2
        //     87: aload_1
        //     88: invokevirtual  #200 // org.freedesktop.dbus.messages.MethodCall.setReply:(Lorg/freedesktop/dbus/messages/Message;)V
        //     91: aload_1
        //     92: aload_2
        //     93: invokevirtual  #202 // org.freedesktop.dbus.messages.MethodReturn.setCall:(Lorg/freedesktop/dbus/messages/MethodCall;)V
        //     96: aload_0
        //     97: invokevirtual  #138 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getCallbackManager:()Lorg/freedesktop/dbus/connections/base/PendingCallbackManager;
        //    100: aload_2
        //    101: invokevirtual  #172 // org.freedesktop.dbus.connections.base.PendingCallbackManager.getCallback:(Lorg/freedesktop/dbus/messages/MethodCall;)Lorg/freedesktop/dbus/interfaces/CallbackHandler;
        //    104: astore_3
        //    105: aload_0
        //    106: invokevirtual  #138 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getCallbackManager:()Lorg/freedesktop/dbus/connections/base/PendingCallbackManager;
        //    109: aload_2
        //    110: invokevirtual  #173 // org.freedesktop.dbus.connections.base.PendingCallbackManager.getCallbackReply:(Lorg/freedesktop/dbus/messages/MethodCall;)Lorg/freedesktop/dbus/DBusAsyncReply;
        //    113: astore  4
        //    115: aload_0
        //    116: invokevirtual  #138 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getCallbackManager:()Lorg/freedesktop/dbus/connections/base/PendingCallbackManager;
        //    119: aload_2
        //    120: invokevirtual  #174 // org.freedesktop.dbus.connections.base.PendingCallbackManager.removeCallback:(Lorg/freedesktop/dbus/messages/MethodCall;)Lorg/freedesktop/dbus/interfaces/CallbackHandler;
        //    123: pop
        //    124: aconst_null
        //    125: aload_3
        //    126: if_acmpeq  196 (offset +70)
        //    129: aload_3
        //    130: astore  5
        //    132: aload  4
        //    134: astore  6
        //    136: aload  6
        //    138: ifnonnull  153 (offset +15)
        //    141: aload_0
        //    142: invokevirtual  #143 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getLogger:()Lorg/slf4j/Logger;
        //    145: ldc  #8 // 'Cannot add runnable for method, given method callback was null'
        //    147: invokeinterface  #228 // org.slf4j.Logger.debug:(Ljava/lang/String;)V, count 2
        //    152: return
        //    153: aload_0
        //    154: invokevirtual  #143 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getLogger:()Lorg/slf4j/Logger;
        //    157: ldc  #6 // 'Adding Runnable for method {} with callback handler {}'
        //    159: aload  5
        //    161: aload  6
        //    163: invokevirtual  #134 // org.freedesktop.dbus.DBusAsyncReply.getMethod:()Ljava/lang/reflect/Method;
        //    166: invokeinterface  #234 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V, count 4
        //    171: new  #82 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler$2
        //    174: dup
        //    175: aload_0
        //    176: aload_1
        //    177: aload  6
        //    179: aload  5
        //    181: invokespecial  #168 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler$2.<init>:(Lorg/freedesktop/dbus/connections/base/ConnectionMessageHandler;Lorg/freedesktop/dbus/messages/MethodReturn;Lorg/freedesktop/dbus/DBusAsyncReply;Lorg/freedesktop/dbus/interfaces/CallbackHandler;)V
        //    184: astore  7
        //    186: aload_0
        //    187: invokevirtual  #147 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getReceivingService:()Lorg/freedesktop/dbus/connections/base/ReceivingService;
        //    190: aload  7
        //    192: invokevirtual  #177 // org.freedesktop.dbus.connections.base.ReceivingService.execMethodReturnHandler:(Ljava/lang/Runnable;)I
        //    195: pop
        //    196: goto  236 (offset +40)
        //    199: aload_0
        //    200: aload_0
        //    201: invokevirtual  #144 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getMessageFactory:()Lorg/freedesktop/dbus/messages/MessageFactory;
        //    204: aload_1
        //    205: new  #92 // org.freedesktop.dbus.exceptions.DBusExecutionException
        //    208: dup
        //    209: ldc  #32 // 'Spurious reply. No message with the given serial id was awaiting a reply.'
        //    211: invokespecial  #183 // org.freedesktop.dbus.exceptions.DBusExecutionException.<init>:(Ljava/lang/String;)V
        //    214: invokevirtual  #192 // org.freedesktop.dbus.messages.MessageFactory.createError:(Lorg/freedesktop/dbus/messages/Message;Ljava/lang/Throwable;)Lorg/freedesktop/dbus/messages/Error;
        //    217: invokevirtual  #164 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.sendMessage:(Lorg/freedesktop/dbus/messages/Message;)V
        //    220: goto  236 (offset +16)
        //    223: astore_3
        //    224: aload_0
        //    225: invokevirtual  #143 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getLogger:()Lorg/slf4j/Logger;
        //    228: ldc  #9 // 'Could not send error message'
        //    230: aload_3
        //    231: invokeinterface  #235 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Throwable;)V, count 3
        //    236: return
        //       Exception table:
        //         from 30 to 71 target 74 type any
        //         from 74 to 78 target 74 type any
        //         from 199 to 220 target 223 type org.freedesktop.dbus.exceptions.DBusException
    }

   void handleMessage(Message arg0) {
        if (!(arg0 instanceof DBusSignal)) {
            if (!(arg0 instanceof MethodCall)) {
                if (!(arg0 instanceof MethodReturn)) {
                    if (arg0 instanceof Error) {
                        Error var5 = ((Error) arg0);
                        handleMessage(var5);
                    }
                } else {
                    MethodReturn var4 = ((MethodReturn) arg0);
                    handleMessage(var4);
                }
            } else {
                MethodCall var3 = ((MethodCall) arg0);
                handleMessage(var3);
            }
        } else {
            DBusSignal var2 = ((DBusSignal) arg0);
            handleMessage(var2, true);
        }
    }

  private void handleMessage(MethodCall arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokevirtual  #143 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getLogger:()Lorg/slf4j/Logger;
        //      4: ldc  #24 // 'Handling incoming method call: {}'
        //      6: aload_1
        //      7: invokeinterface  #229 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //     12: aconst_null
        //     13: astore_3
        //     14: aconst_null
        //     15: astore  4
        //     17: aconst_null
        //     18: aload_1
        //     19: invokevirtual  #194 // org.freedesktop.dbus.messages.MethodCall.getInterface:()Ljava/lang/String;
        //     22: if_acmpeq  49 (offset +27)
        //     25: aload_1
        //     26: invokevirtual  #194 // org.freedesktop.dbus.messages.MethodCall.getInterface:()Ljava/lang/String;
        //     29: ldc  #38 // 'org.freedesktop.DBus.Peer'
        //     31: invokevirtual  #114 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //     34: ifne  49 (offset +15)
        //     37: aload_1
        //     38: invokevirtual  #194 // org.freedesktop.dbus.messages.MethodCall.getInterface:()Ljava/lang/String;
        //     41: ldc  #37 // 'org.freedesktop.DBus.Introspectable'
        //     43: invokevirtual  #114 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //     46: ifeq  136 (offset +90)
        //     49: aload_0
        //     50: invokevirtual  #139 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getExportedObjects:()Ljava/util/Map;
        //     53: aconst_null
        //     54: invokeinterface  #217 // java.util.Map.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //     59: checkcast  #96 // org.freedesktop.dbus.messages.ExportedObject
        //     62: astore_2
        //     63: aconst_null
        //     64: aload_2
        //     65: if_acmpeq  86 (offset +21)
        //     68: aconst_null
        //     69: aload_2
        //     70: invokevirtual  #189 // org.freedesktop.dbus.messages.ExportedObject.getObject:()Ljava/lang/ref/Reference;
        //     73: invokevirtual  #120 // java.lang.ref.Reference.get:()Ljava/lang/Object;
        //     76: if_acmpne  86 (offset +10)
        //     79: aload_0
        //     80: aconst_null
        //     81: invokevirtual  #166 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.unExportObject:(Ljava/lang/String;)V
        //     84: aconst_null
        //     85: astore_2
        //     86: aload_2
        //     87: ifnull  118 (offset +31)
        //     90: aload_2
        //     91: invokevirtual  #188 // org.freedesktop.dbus.messages.ExportedObject.getMethods:()Ljava/util/Map;
        //     94: new  #75 // org.freedesktop.dbus.MethodTuple
        //     97: dup
        //     98: aload_1
        //     99: invokevirtual  #195 // org.freedesktop.dbus.messages.MethodCall.getName:()Ljava/lang/String;
        //    102: aload_1
        //    103: invokevirtual  #198 // org.freedesktop.dbus.messages.MethodCall.getSig:()Ljava/lang/String;
        //    106: invokespecial  #137 // org.freedesktop.dbus.MethodTuple.<init>:(Ljava/lang/String;Ljava/lang/String;)V
        //    109: invokeinterface  #217 // java.util.Map.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //    114: checkcast  #55 // java.lang.reflect.Method
        //    117: astore_3
        //    118: aload_3
        //    119: ifnull  136 (offset +17)
        //    122: new  #85 // org.freedesktop.dbus.connections.base.GlobalHandler
        //    125: dup
        //    126: aload_0
        //    127: aload_1
        //    128: invokevirtual  #197 // org.freedesktop.dbus.messages.MethodCall.getPath:()Ljava/lang/String;
        //    131: invokespecial  #171 // org.freedesktop.dbus.connections.base.GlobalHandler.<init>:(Lorg/freedesktop/dbus/connections/base/AbstractConnectionBase;Ljava/lang/String;)V
        //    134: astore  4
        //    136: aload  4
        //    138: ifnonnull  561 (offset +423)
        //    141: aload_0
        //    142: invokevirtual  #139 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getExportedObjects:()Ljava/util/Map;
        //    145: aload_1
        //    146: invokevirtual  #197 // org.freedesktop.dbus.messages.MethodCall.getPath:()Ljava/lang/String;
        //    149: invokeinterface  #217 // java.util.Map.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //    154: checkcast  #96 // org.freedesktop.dbus.messages.ExportedObject
        //    157: astore_2
        //    158: aload_0
        //    159: invokevirtual  #143 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getLogger:()Lorg/slf4j/Logger;
        //    162: ldc  #19 // 'Found exported object: {}'
        //    164: aload_2
        //    165: ifnonnull  173 (offset +8)
        //    168: ldc  #4 // '<no object found>'
        //    170: goto  174 (offset +4)
        //    173: aload_2
        //    174: invokeinterface  #229 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //    179: aload_2
        //    180: ifnull  261 (offset +81)
        //    183: aload_2
        //    184: invokevirtual  #189 // org.freedesktop.dbus.messages.ExportedObject.getObject:()Ljava/lang/ref/Reference;
        //    187: invokevirtual  #120 // java.lang.ref.Reference.get:()Ljava/lang/Object;
        //    190: ifnonnull  261 (offset +71)
        //    193: aload_0
        //    194: invokevirtual  #143 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getLogger:()Lorg/slf4j/Logger;
        //    197: ldc  #35 // 'Unexporting {} implicitly (object present: {}, reference present: {})'
        //    199: iconst_3
        //    200: anewarray  #45 // java.lang.Object
        //    203: dup
        //    204: iconst_0
        //    205: aload_1
        //    206: invokevirtual  #197 // org.freedesktop.dbus.messages.MethodCall.getPath:()Ljava/lang/String;
        //    209: aastore
        //    210: dup
        //    211: iconst_1
        //    212: aload_2
        //    213: ifnull  220 (offset +7)
        //    216: iconst_1
        //    217: goto  221 (offset +4)
        //    220: iconst_0
        //    221: invokestatic  #106 // java.lang.Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    224: aastore
        //    225: dup
        //    226: iconst_2
        //    227: aload_2
        //    228: invokevirtual  #189 // org.freedesktop.dbus.messages.ExportedObject.getObject:()Ljava/lang/ref/Reference;
        //    231: invokevirtual  #120 // java.lang.ref.Reference.get:()Ljava/lang/Object;
        //    234: ifnonnull  241 (offset +7)
        //    237: iconst_1
        //    238: goto  242 (offset +4)
        //    241: iconst_0
        //    242: invokestatic  #106 // java.lang.Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    245: aastore
        //    246: invokeinterface  #231 // org.slf4j.Logger.info:(Ljava/lang/String;[Ljava/lang/Object;)V, count 3
        //    251: aload_0
        //    252: aload_1
        //    253: invokevirtual  #197 // org.freedesktop.dbus.messages.MethodCall.getPath:()Ljava/lang/String;
        //    256: invokevirtual  #166 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.unExportObject:(Ljava/lang/String;)V
        //    259: aconst_null
        //    260: astore_2
        //    261: aload_2
        //    262: ifnonnull  298 (offset +36)
        //    265: aload_0
        //    266: invokevirtual  #140 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getFallbackContainer:()Lorg/freedesktop/dbus/connections/base/FallbackContainer;
        //    269: aload_1
        //    270: invokevirtual  #197 // org.freedesktop.dbus.messages.MethodCall.getPath:()Ljava/lang/String;
        //    273: invokevirtual  #170 // org.freedesktop.dbus.connections.base.FallbackContainer.get:(Ljava/lang/String;)Lorg/freedesktop/dbus/messages/ExportedObject;
        //    276: astore_2
        //    277: aload_0
        //    278: invokevirtual  #143 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getLogger:()Lorg/slf4j/Logger;
        //    281: ldc  #20 // 'Found {} in fallback container'
        //    283: aload_2
        //    284: ifnonnull  292 (offset +8)
        //    287: ldc  #36 // 'no'
        //    289: goto  293 (offset +4)
        //    292: aload_2
        //    293: invokeinterface  #229 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //    298: aload_2
        //    299: ifnonnull  346 (offset +47)
        //    302: aload_0
        //    303: invokevirtual  #143 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getLogger:()Lorg/slf4j/Logger;
        //    306: ldc  #28 // 'No object found for method {}'
        //    308: aload_1
        //    309: invokevirtual  #197 // org.freedesktop.dbus.messages.MethodCall.getPath:()Ljava/lang/String;
        //    312: invokeinterface  #229 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //    317: aload_0
        //    318: aload_0
        //    319: invokevirtual  #144 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getMessageFactory:()Lorg/freedesktop/dbus/messages/MessageFactory;
        //    322: aload_1
        //    323: new  #90 // org.freedesktop.dbus.errors.UnknownObject
        //    326: dup
        //    327: aload_1
        //    328: invokevirtual  #197 // org.freedesktop.dbus.messages.MethodCall.getPath:()Ljava/lang/String;
        //    331: invokedynamic  #240 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;
        //    336: invokespecial  #181 // org.freedesktop.dbus.errors.UnknownObject.<init>:(Ljava/lang/String;)V
        //    339: invokevirtual  #192 // org.freedesktop.dbus.messages.MessageFactory.createError:(Lorg/freedesktop/dbus/messages/Message;Ljava/lang/Throwable;)Lorg/freedesktop/dbus/messages/Error;
        //    342: invokevirtual  #164 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.sendMessage:(Lorg/freedesktop/dbus/messages/Message;)V
        //    345: return
        //    346: aload_0
        //    347: invokevirtual  #143 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getLogger:()Lorg/slf4j/Logger;
        //    350: invokeinterface  #232 // org.slf4j.Logger.isTraceEnabled:()Z, count 1
        //    355: ifeq  454 (offset +99)
        //    358: aload_0
        //    359: invokevirtual  #143 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getLogger:()Lorg/slf4j/Logger;
        //    362: ldc  #30 // 'Searching for method {}  with signature {}'
        //    364: aload_1
        //    365: invokevirtual  #195 // org.freedesktop.dbus.messages.MethodCall.getName:()Ljava/lang/String;
        //    368: aload_1
        //    369: invokevirtual  #198 // org.freedesktop.dbus.messages.MethodCall.getSig:()Ljava/lang/String;
        //    372: invokeinterface  #234 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V, count 4
        //    377: aload_0
        //    378: invokevirtual  #143 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getLogger:()Lorg/slf4j/Logger;
        //    381: ldc  #27 // 'List of methods on {}: '
        //    383: aload_2
        //    384: invokeinterface  #233 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //    389: aload_2
        //    390: invokevirtual  #188 // org.freedesktop.dbus.messages.ExportedObject.getMethods:()Ljava/util/Map;
        //    393: invokeinterface  #218 // java.util.Map.keySet:()Ljava/util/Set;, count 1
        //    398: invokeinterface  #226 // java.util.Set.iterator:()Ljava/util/Iterator;, count 1
        //    403: astore  5
        //    405: aload  5
        //    407: invokeinterface  #210 // java.util.Iterator.hasNext:()Z, count 1
        //    412: ifeq  454 (offset +42)
        //    415: aload  5
        //    417: invokeinterface  #211 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //    422: checkcast  #75 // org.freedesktop.dbus.MethodTuple
        //    425: astore  6
        //    427: aload_0
        //    428: invokevirtual  #143 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getLogger:()Lorg/slf4j/Logger;
        //    431: ldc  #3 // '   {} => {}'
        //    433: aload  6
        //    435: aload_2
        //    436: invokevirtual  #188 // org.freedesktop.dbus.messages.ExportedObject.getMethods:()Ljava/util/Map;
        //    439: aload  6
        //    441: invokeinterface  #217 // java.util.Map.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //    446: invokeinterface  #234 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V, count 4
        //    451: goto  405 (offset -46)
        //    454: aload_1
        //    455: invokevirtual  #196 // org.freedesktop.dbus.messages.MethodCall.getParameters:()[Ljava/lang/Object;
        //    458: astore  5
        //    460: aload_0
        //    461: aload_2
        //    462: aload_1
        //    463: aload  5
        //    465: invokevirtual  #148 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.handleDBusBoundProperties:(Lorg/freedesktop/dbus/messages/ExportedObject;Lorg/freedesktop/dbus/messages/MethodCall;[Ljava/lang/Object;)Z
        //    468: ifeq  472 (offset +4)
        //    471: return
        //    472: aload_3
        //    473: ifnonnull  552 (offset +79)
        //    476: aload_2
        //    477: invokevirtual  #188 // org.freedesktop.dbus.messages.ExportedObject.getMethods:()Ljava/util/Map;
        //    480: new  #75 // org.freedesktop.dbus.MethodTuple
        //    483: dup
        //    484: aload_1
        //    485: invokevirtual  #195 // org.freedesktop.dbus.messages.MethodCall.getName:()Ljava/lang/String;
        //    488: aload_1
        //    489: invokevirtual  #198 // org.freedesktop.dbus.messages.MethodCall.getSig:()Ljava/lang/String;
        //    492: invokespecial  #137 // org.freedesktop.dbus.MethodTuple.<init>:(Ljava/lang/String;Ljava/lang/String;)V
        //    495: invokeinterface  #217 // java.util.Map.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //    500: checkcast  #55 // java.lang.reflect.Method
        //    503: astore_3
        //    504: aconst_null
        //    505: aload_3
        //    506: if_acmpne  552 (offset +46)
        //    509: aload_0
        //    510: aload_0
        //    511: invokevirtual  #144 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getMessageFactory:()Lorg/freedesktop/dbus/messages/MessageFactory;
        //    514: aload_1
        //    515: new  #89 // org.freedesktop.dbus.errors.UnknownMethod
        //    518: dup
        //    519: ldc  #33 // "The method `%s.%s' does not exist on this object."
        //    521: iconst_2
        //    522: anewarray  #45 // java.lang.Object
        //    525: dup
        //    526: iconst_0
        //    527: aload_1
        //    528: invokevirtual  #194 // org.freedesktop.dbus.messages.MethodCall.getInterface:()Ljava/lang/String;
        //    531: aastore
        //    532: dup
        //    533: iconst_1
        //    534: aload_1
        //    535: invokevirtual  #195 // org.freedesktop.dbus.messages.MethodCall.getName:()Ljava/lang/String;
        //    538: aastore
        //    539: invokestatic  #115 // java.lang.String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    542: invokespecial  #180 // org.freedesktop.dbus.errors.UnknownMethod.<init>:(Ljava/lang/String;)V
        //    545: invokevirtual  #192 // org.freedesktop.dbus.messages.MessageFactory.createError:(Lorg/freedesktop/dbus/messages/Message;Ljava/lang/Throwable;)Lorg/freedesktop/dbus/messages/Error;
        //    548: invokevirtual  #164 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.sendMessage:(Lorg/freedesktop/dbus/messages/Message;)V
        //    551: return
        //    552: aload_2
        //    553: invokevirtual  #189 // org.freedesktop.dbus.messages.ExportedObject.getObject:()Ljava/lang/ref/Reference;
        //    556: invokevirtual  #120 // java.lang.ref.Reference.get:()Ljava/lang/Object;
        //    559: astore  4
        //    561: aload_3
        //    562: invokestatic  #191 // org.freedesktop.dbus.messages.ExportedObject.isExcluded:(Ljava/lang/reflect/Method;)Z
        //    565: ifeq  611 (offset +46)
        //    568: aload_0
        //    569: aload_0
        //    570: invokevirtual  #144 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getMessageFactory:()Lorg/freedesktop/dbus/messages/MessageFactory;
        //    573: aload_1
        //    574: new  #89 // org.freedesktop.dbus.errors.UnknownMethod
        //    577: dup
        //    578: ldc  #34 // "The method `%s.%s' is not exported."
        //    580: iconst_2
        //    581: anewarray  #45 // java.lang.Object
        //    584: dup
        //    585: iconst_0
        //    586: aload_1
        //    587: invokevirtual  #194 // org.freedesktop.dbus.messages.MethodCall.getInterface:()Ljava/lang/String;
        //    590: aastore
        //    591: dup
        //    592: iconst_1
        //    593: aload_1
        //    594: invokevirtual  #195 // org.freedesktop.dbus.messages.MethodCall.getName:()Ljava/lang/String;
        //    597: aastore
        //    598: invokestatic  #115 // java.lang.String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    601: invokespecial  #180 // org.freedesktop.dbus.errors.UnknownMethod.<init>:(Ljava/lang/String;)V
        //    604: invokevirtual  #192 // org.freedesktop.dbus.messages.MessageFactory.createError:(Lorg/freedesktop/dbus/messages/Message;Ljava/lang/Throwable;)Lorg/freedesktop/dbus/messages/Error;
        //    607: invokevirtual  #164 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.sendMessage:(Lorg/freedesktop/dbus/messages/Message;)V
        //    610: return
        //    611: aload_0
        //    612: aload_1
        //    613: aload_3
        //    614: aload  4
        //    616: invokevirtual  #163 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.queueInvokeMethod:(Lorg/freedesktop/dbus/messages/MethodCall;Ljava/lang/reflect/Method;Ljava/lang/Object;)V
        //    619: return
    }

  private boolean handleDBusBoundProperties(ExportedObject arg0, MethodCall arg1, Object[] arg2) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_3
        //      1: arraylength
        //      2: iconst_2
        //      3: if_icmpne  109 (offset +106)
        //      6: aload_3
        //      7: iconst_0
        //      8: aaload
        //      9: instanceof  #48 // java.lang.String
        //     12: ifeq  109 (offset +97)
        //     15: aload_3
        //     16: iconst_1
        //     17: aaload
        //     18: instanceof  #48 // java.lang.String
        //     21: ifeq  109 (offset +88)
        //     24: aload_2
        //     25: invokevirtual  #195 // org.freedesktop.dbus.messages.MethodCall.getName:()Ljava/lang/String;
        //     28: ldc  #21 // 'Get'
        //     30: invokevirtual  #114 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //     33: ifeq  109 (offset +76)
        //     36: new  #100 // org.freedesktop.dbus.propertyref.PropertyRef
        //     39: dup
        //     40: aload_3
        //     41: iconst_1
        //     42: aaload
        //     43: checkcast  #48 // java.lang.String
        //     46: aconst_null
        //     47: getstatic  #104 // org.freedesktop.dbus.annotations.DBusProperty$Access.READ:Lorg/freedesktop/dbus/annotations/DBusProperty$Access;
        //     50: invokespecial  #203 // org.freedesktop.dbus.propertyref.PropertyRef.<init>:(Ljava/lang/String;Ljava/lang/Class;Lorg/freedesktop/dbus/annotations/DBusProperty$Access;)V
        //     53: astore  4
        //     55: aload_1
        //     56: invokevirtual  #190 // org.freedesktop.dbus.messages.ExportedObject.getPropertyMethods:()Ljava/util/Map;
        //     59: aload  4
        //     61: invokeinterface  #217 // java.util.Map.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //     66: checkcast  #55 // java.lang.reflect.Method
        //     69: astore  5
        //     71: aload  5
        //     73: ifnull  106 (offset +33)
        //     76: aload_1
        //     77: invokevirtual  #189 // org.freedesktop.dbus.messages.ExportedObject.getObject:()Ljava/lang/ref/Reference;
        //     80: invokevirtual  #120 // java.lang.ref.Reference.get:()Ljava/lang/Object;
        //     83: astore  6
        //     85: aload_0
        //     86: invokevirtual  #147 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getReceivingService:()Lorg/freedesktop/dbus/connections/base/ReceivingService;
        //     89: aload_0
        //     90: aload_2
        //     91: aload  5
        //     93: aload  6
        //     95: invokedynamic  #241 // invokedynamic run:(Lorg/freedesktop/dbus/connections/base/ConnectionMessageHandler;Lorg/freedesktop/dbus/messages/MethodCall;Ljava/lang/reflect/Method;Ljava/lang/Object;)Ljava/lang/Runnable;
        //    100: invokevirtual  #176 // org.freedesktop.dbus.connections.base.ReceivingService.execMethodCallHandler:(Ljava/lang/Runnable;)I
        //    103: pop
        //    104: iconst_1
        //    105: ireturn
        //    106: goto  516 (offset +410)
        //    109: aload_3
        //    110: arraylength
        //    111: iconst_3
        //    112: if_icmpne  263 (offset +151)
        //    115: aload_3
        //    116: iconst_0
        //    117: aaload
        //    118: instanceof  #48 // java.lang.String
        //    121: ifeq  263 (offset +142)
        //    124: aload_3
        //    125: iconst_1
        //    126: aaload
        //    127: instanceof  #48 // java.lang.String
        //    130: ifeq  263 (offset +133)
        //    133: aload_2
        //    134: invokevirtual  #195 // org.freedesktop.dbus.messages.MethodCall.getName:()Ljava/lang/String;
        //    137: ldc  #31 // 'Set'
        //    139: invokevirtual  #114 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //    142: ifeq  263 (offset +121)
        //    145: new  #100 // org.freedesktop.dbus.propertyref.PropertyRef
        //    148: dup
        //    149: aload_3
        //    150: iconst_1
        //    151: aaload
        //    152: checkcast  #48 // java.lang.String
        //    155: aconst_null
        //    156: getstatic  #105 // org.freedesktop.dbus.annotations.DBusProperty$Access.WRITE:Lorg/freedesktop/dbus/annotations/DBusProperty$Access;
        //    159: invokespecial  #203 // org.freedesktop.dbus.propertyref.PropertyRef.<init>:(Ljava/lang/String;Ljava/lang/Class;Lorg/freedesktop/dbus/annotations/DBusProperty$Access;)V
        //    162: astore  4
        //    164: aload_1
        //    165: invokevirtual  #190 // org.freedesktop.dbus.messages.ExportedObject.getPropertyMethods:()Ljava/util/Map;
        //    168: aload  4
        //    170: invokeinterface  #217 // java.util.Map.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //    175: checkcast  #55 // java.lang.reflect.Method
        //    178: astore  5
        //    180: aload  5
        //    182: ifnull  260 (offset +78)
        //    185: aload_1
        //    186: invokevirtual  #189 // org.freedesktop.dbus.messages.ExportedObject.getObject:()Ljava/lang/ref/Reference;
        //    189: invokevirtual  #120 // java.lang.ref.Reference.get:()Ljava/lang/Object;
        //    192: astore  6
        //    194: aload  5
        //    196: invokestatic  #206 // org.freedesktop.dbus.propertyref.PropertyRef.typeForMethod:(Ljava/lang/reflect/Method;)Ljava/lang/Class;
        //    199: astore  7
        //    201: new  #71 // java.util.concurrent.atomic.AtomicBoolean
        //    204: dup
        //    205: iconst_0
        //    206: invokespecial  #131 // java.util.concurrent.atomic.AtomicBoolean.<init>:(Z)V
        //    209: astore  8
        //    211: aload_3
        //    212: iconst_2
        //    213: aaload
        //    214: invokestatic  #129 // java.util.Optional.ofNullable:(Ljava/lang/Object;)Ljava/util/Optional;
        //    217: aload  8
        //    219: invokedynamic  #242 // invokedynamic apply:(Ljava/util/concurrent/atomic/AtomicBoolean;)Ljava/util/function/Function;
        //    224: invokevirtual  #128 // java.util.Optional.map:(Ljava/util/function/Function;)Ljava/util/Optional;
        //    227: aconst_null
        //    228: invokevirtual  #130 // java.util.Optional.orElse:(Ljava/lang/Object;)Ljava/lang/Object;
        //    231: astore  9
        //    233: aload_0
        //    234: invokevirtual  #147 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getReceivingService:()Lorg/freedesktop/dbus/connections/base/ReceivingService;
        //    237: aload_0
        //    238: aload  9
        //    240: aload  5
        //    242: aload  8
        //    244: aload_2
        //    245: aload  7
        //    247: aload  6
        //    249: invokedynamic  #243 // invokedynamic run:(Lorg/freedesktop/dbus/connections/base/ConnectionMessageHandler;Ljava/lang/Object;Ljava/lang/reflect/Method;Ljava/util/concurrent/atomic/AtomicBoolean;Lorg/freedesktop/dbus/messages/MethodCall;Ljava/lang/Class;Ljava/lang/Object;)Ljava/lang/Runnable;
        //    254: invokevirtual  #176 // org.freedesktop.dbus.connections.base.ReceivingService.execMethodCallHandler:(Ljava/lang/Runnable;)I
        //    257: pop
        //    258: iconst_1
        //    259: ireturn
        //    260: goto  516 (offset +256)
        //    263: aload_3
        //    264: arraylength
        //    265: iconst_1
        //    266: if_icmpne  516 (offset +250)
        //    269: aload_3
        //    270: iconst_0
        //    271: aaload
        //    272: instanceof  #48 // java.lang.String
        //    275: ifeq  516 (offset +241)
        //    278: aload_2
        //    279: invokevirtual  #195 // org.freedesktop.dbus.messages.MethodCall.getName:()Ljava/lang/String;
        //    282: ldc  #22 // 'GetAll'
        //    284: invokevirtual  #114 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //    287: ifeq  516 (offset +229)
        //    290: aload_1
        //    291: invokevirtual  #190 // org.freedesktop.dbus.messages.ExportedObject.getPropertyMethods:()Ljava/util/Map;
        //    294: invokeinterface  #216 // java.util.Map.entrySet:()Ljava/util/Set;, count 1
        //    299: astore  4
        //    301: aload  4
        //    303: invokeinterface  #225 // java.util.Set.isEmpty:()Z, count 1
        //    308: ifne  516 (offset +208)
        //    311: aload_1
        //    312: invokevirtual  #189 // org.freedesktop.dbus.messages.ExportedObject.getObject:()Ljava/lang/ref/Reference;
        //    315: invokevirtual  #120 // java.lang.ref.Reference.get:()Ljava/lang/Object;
        //    318: astore  5
        //    320: aconst_null
        //    321: astore  6
        //    323: aload  5
        //    325: instanceof  #76 // org.freedesktop.dbus.annotations.DBusProperties
        //    328: ifeq  410 (offset +82)
        //    331: aload_1
        //    332: invokevirtual  #188 // org.freedesktop.dbus.messages.ExportedObject.getMethods:()Ljava/util/Map;
        //    335: new  #75 // org.freedesktop.dbus.MethodTuple
        //    338: dup
        //    339: aload_2
        //    340: invokevirtual  #195 // org.freedesktop.dbus.messages.MethodCall.getName:()Ljava/lang/String;
        //    343: aload_2
        //    344: invokevirtual  #198 // org.freedesktop.dbus.messages.MethodCall.getSig:()Ljava/lang/String;
        //    347: invokespecial  #137 // org.freedesktop.dbus.MethodTuple.<init>:(Ljava/lang/String;Ljava/lang/String;)V
        //    350: invokeinterface  #217 // java.util.Map.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //    355: checkcast  #55 // java.lang.reflect.Method
        //    358: astore  6
        //    360: aconst_null
        //    361: aload  6
        //    363: if_acmpne  489 (offset +126)
        //    366: aload_0
        //    367: aload_0
        //    368: invokevirtual  #144 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getMessageFactory:()Lorg/freedesktop/dbus/messages/MessageFactory;
        //    371: aload_2
        //    372: new  #89 // org.freedesktop.dbus.errors.UnknownMethod
        //    375: dup
        //    376: ldc  #33 // "The method `%s.%s' does not exist on this object."
        //    378: iconst_2
        //    379: anewarray  #45 // java.lang.Object
        //    382: dup
        //    383: iconst_0
        //    384: aload_2
        //    385: invokevirtual  #194 // org.freedesktop.dbus.messages.MethodCall.getInterface:()Ljava/lang/String;
        //    388: aastore
        //    389: dup
        //    390: iconst_1
        //    391: aload_2
        //    392: invokevirtual  #195 // org.freedesktop.dbus.messages.MethodCall.getName:()Ljava/lang/String;
        //    395: aastore
        //    396: invokestatic  #115 // java.lang.String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    399: invokespecial  #180 // org.freedesktop.dbus.errors.UnknownMethod.<init>:(Ljava/lang/String;)V
        //    402: invokevirtual  #192 // org.freedesktop.dbus.messages.MessageFactory.createError:(Lorg/freedesktop/dbus/messages/Message;Ljava/lang/Throwable;)Lorg/freedesktop/dbus/messages/Error;
        //    405: invokevirtual  #164 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.sendMessage:(Lorg/freedesktop/dbus/messages/Message;)V
        //    408: iconst_1
        //    409: ireturn
        //    410: ldc  #68 // java.util.Properties
        //    412: ldc  #22 // 'GetAll'
        //    414: iconst_1
        //    415: anewarray  #41 // java.lang.Class
        //    418: dup
        //    419: iconst_0
        //    420: ldc  #48 // java.lang.String
        //    422: aastore
        //    423: invokevirtual  #107 // java.lang.Class.getDeclaredMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //    426: astore  6
        //    428: goto  489 (offset +61)
        //    431: astore  7
        //    433: aload_0
        //    434: invokevirtual  #143 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getLogger:()Lorg/slf4j/Logger;
        //    437: ldc  #29 // 'Properties GetAll failed'
        //    439: aload  7
        //    441: invokeinterface  #230 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Throwable;)V, count 3
        //    446: aload_0
        //    447: aload_2
        //    448: new  #92 // org.freedesktop.dbus.exceptions.DBusExecutionException
        //    451: dup
        //    452: ldc  #10 // 'Error Executing Method %s.%s: %s'
        //    454: iconst_3
        //    455: anewarray  #45 // java.lang.Object
        //    458: dup
        //    459: iconst_0
        //    460: aload_2
        //    461: invokevirtual  #194 // org.freedesktop.dbus.messages.MethodCall.getInterface:()Ljava/lang/String;
        //    464: aastore
        //    465: dup
        //    466: iconst_1
        //    467: aload_2
        //    468: invokevirtual  #195 // org.freedesktop.dbus.messages.MethodCall.getName:()Ljava/lang/String;
        //    471: aastore
        //    472: dup
        //    473: iconst_2
        //    474: aload  7
        //    476: invokevirtual  #110 // java.lang.Exception.getMessage:()Ljava/lang/String;
        //    479: aastore
        //    480: invokestatic  #115 // java.lang.String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    483: invokespecial  #183 // org.freedesktop.dbus.exceptions.DBusExecutionException.<init>:(Ljava/lang/String;)V
        //    486: invokevirtual  #149 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.handleException:(Lorg/freedesktop/dbus/messages/Message;Lorg/freedesktop/dbus/exceptions/DBusExecutionException;)V
        //    489: aload  6
        //    491: astore  7
        //    493: aload_0
        //    494: invokevirtual  #147 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getReceivingService:()Lorg/freedesktop/dbus/connections/base/ReceivingService;
        //    497: aload_0
        //    498: aload  4
        //    500: aload_2
        //    501: aload  5
        //    503: aload  7
        //    505: invokedynamic  #244 // invokedynamic run:(Lorg/freedesktop/dbus/connections/base/ConnectionMessageHandler;Ljava/util/Set;Lorg/freedesktop/dbus/messages/MethodCall;Ljava/lang/Object;Ljava/lang/reflect/Method;)Ljava/lang/Runnable;
        //    510: invokevirtual  #176 // org.freedesktop.dbus.connections.base.ReceivingService.execMethodCallHandler:(Ljava/lang/Runnable;)I
        //    513: pop
        //    514: iconst_1
        //    515: ireturn
        //    516: iconst_0
        //    517: ireturn
        //       Exception table:
        //         from 410 to 428 target 431 type java.lang.NoSuchMethodException
        //         from 410 to 428 target 431 type java.lang.SecurityException
    }

  private void lambda$handleDBusBoundProperties$5(Set arg0, MethodCall arg1, Object arg2, Method arg3) {
        HashMap var5 = new HashMap();
        Iterator var6 = arg0.iterator();
        Object var9;
        while (true) {
            if (!var6.hasNext()) {
                if (arg2 instanceof DBusProperties) {
                    var5.putAll(((Map) setupAndInvoke(arg1, arg3, arg2, true)));
                }
                try {
                    invokedMethodReply(arg1, arg3, var5);
                } catch (DBusExecutionException e1) {
                    var6 = e1;
                    getLogger().debug("Error invoking method call", var6);
                    handleException(arg1, var6);
                } catch (Throwable e2) {
                    var6 = e2;
                    getLogger().debug("Failed to invoke method call", var6);
                    handleException(arg1, new DBusExecutionException(String.format("Error Executing Method %s.%s: %s", new Object[]{arg1.getInterface(), arg1.getName(), var6.getMessage()})));
                }
                return;
            } else {
                Entry var7 = ((Entry) var6.next());
                Method var8 = ((Method) var7.getValue());
                if ((((PropertyRef) var7.getKey())).getAccess() != DBusProperty_Access.READ) {
                    continue;
                } else {
                    try {
                        arg1.setArgs(new Object[0]);
                        var9 = invokeMethod(arg1, var8, arg2);
                        var5.put((((PropertyRef) var7.getKey())).getName(), var9);
                    } catch (Throwable e3) {
                    }
                }
            }
        }
        Throwable var9 = __caught__;
        getLogger().debug("", ((Throwable) var9));
        handleException(arg1, new UnknownMethod("Failure in de-serializing message: " + String.valueOf(var9)));
    }

  private void lambda$handleDBusBoundProperties$4(Object arg0, Method arg1, AtomicBoolean arg2, MethodCall arg3, Class arg4, Object arg5) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_1
        //      1: astore  7
        //      3: aload_2
        //      4: invokevirtual  #121 // java.lang.reflect.Method.getParameters:()[Ljava/lang/reflect/Parameter;
        //      7: astore  8
        //      9: aload  8
        //     11: arraylength
        //     12: iconst_1
        //     13: if_icmpeq  32 (offset +19)
        //     16: new  #88 // org.freedesktop.dbus.errors.InvalidMethodArgument
        //     19: dup
        //     20: aload  8
        //     22: arraylength
        //     23: invokedynamic  #246 // invokedynamic makeConcatWithConstants:(I)Ljava/lang/String;
        //     28: invokespecial  #179 // org.freedesktop.dbus.errors.InvalidMethodArgument.<init>:(Ljava/lang/String;)V
        //     31: athrow
        //     32: ldc  #60 // java.util.Collection
        //     34: aload  8
        //     36: iconst_0
        //     37: aaload
        //     38: invokevirtual  #122 // java.lang.reflect.Parameter.getType:()Ljava/lang/Class;
        //     41: invokevirtual  #109 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //     44: ifeq  122 (offset +78)
        //     47: aload_3
        //     48: invokevirtual  #132 // java.util.concurrent.atomic.AtomicBoolean.get:()Z
        //     51: ifeq  122 (offset +71)
        //     54: aload  7
        //     56: ifnull  122 (offset +66)
        //     59: aload  7
        //     61: invokevirtual  #113 // java.lang.Object.getClass:()Ljava/lang/Class;
        //     64: invokevirtual  #108 // java.lang.Class.isArray:()Z
        //     67: ifeq  122 (offset +55)
        //     70: ldc  #70 // java.util.Set
        //     72: aload  8
        //     74: iconst_0
        //     75: aaload
        //     76: invokevirtual  #122 // java.lang.reflect.Parameter.getType:()Ljava/lang/Class;
        //     79: invokevirtual  #109 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //     82: ifeq  105 (offset +23)
        //     85: new  #63 // java.util.LinkedHashSet
        //     88: dup
        //     89: aload  7
        //     91: invokestatic  #208 // org.freedesktop.dbus.utils.Util.toObjectArray:(Ljava/lang/Object;)[Ljava/lang/Object;
        //     94: invokestatic  #125 // java.util.Arrays.asList:([Ljava/lang/Object;)Ljava/util/List;
        //     97: invokespecial  #127 // java.util.LinkedHashSet.<init>:(Ljava/util/Collection;)V
        //    100: astore  7
        //    102: goto  122 (offset +20)
        //    105: new  #58 // java.util.ArrayList
        //    108: dup
        //    109: aload  7
        //    111: invokestatic  #208 // org.freedesktop.dbus.utils.Util.toObjectArray:(Ljava/lang/Object;)[Ljava/lang/Object;
        //    114: invokestatic  #125 // java.util.Arrays.asList:([Ljava/lang/Object;)Ljava/util/List;
        //    117: invokespecial  #124 // java.util.ArrayList.<init>:(Ljava/util/Collection;)V
        //    120: astore  7
        //    122: aload  4
        //    124: iconst_1
        //    125: anewarray  #45 // java.lang.Object
        //    128: dup
        //    129: iconst_0
        //    130: aload  7
        //    132: aastore
        //    133: iconst_1
        //    134: anewarray  #57 // java.lang.reflect.Type
        //    137: dup
        //    138: iconst_0
        //    139: aload  5
        //    141: aastore
        //    142: aload_0
        //    143: invokestatic  #136 // org.freedesktop.dbus.Marshalling.deSerializeParameters:([Ljava/lang/Object;[Ljava/lang/reflect/Type;Lorg/freedesktop/dbus/connections/base/AbstractConnectionBase;)[Ljava/lang/Object;
        //    146: invokevirtual  #199 // org.freedesktop.dbus.messages.MethodCall.setArgs:([Ljava/lang/Object;)V
        //    149: aload_0
        //    150: aload  4
        //    152: aload_2
        //    153: aload  6
        //    155: iconst_1
        //    156: aload  4
        //    158: invokevirtual  #193 // org.freedesktop.dbus.messages.MethodCall.getFlags:()I
        //    161: iconst_1
        //    162: iand
        //    163: if_icmpne  170 (offset +7)
        //    166: iconst_1
        //    167: goto  171 (offset +4)
        //    170: iconst_0
        //    171: invokevirtual  #155 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.invokeMethodAndReply:(Lorg/freedesktop/dbus/messages/MethodCall;Ljava/lang/reflect/Method;Ljava/lang/Object;Z)Ljava/lang/Object;
        //    174: pop
        //    175: goto  217 (offset +42)
        //    178: astore  7
        //    180: aload_0
        //    181: invokevirtual  #143 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getLogger:()Lorg/slf4j/Logger;
        //    184: ldc  #17 // 'Failed to invoke method call on Properties'
        //    186: aload  7
        //    188: invokeinterface  #230 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Throwable;)V, count 3
        //    193: aload_0
        //    194: aload  4
        //    196: new  #89 // org.freedesktop.dbus.errors.UnknownMethod
        //    199: dup
        //    200: aload  7
        //    202: invokestatic  #116 // java.lang.String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //    205: invokedynamic  #245 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;
        //    210: invokespecial  #180 // org.freedesktop.dbus.errors.UnknownMethod.<init>:(Ljava/lang/String;)V
        //    213: invokevirtual  #149 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.handleException:(Lorg/freedesktop/dbus/messages/Message;Lorg/freedesktop/dbus/exceptions/DBusExecutionException;)V
        //    216: return
        //    217: return
        //       Exception table:
        //         from 0 to 175 target 178 type java.lang.Exception
    }

  private static Object lambda$handleDBusBoundProperties$3(AtomicBoolean arg0, Object arg1) {
        if (!(arg1 instanceof Variant)) {
            return arg1;
        } else {
            Variant var2 = ((Variant) arg1);
            arg0.set(true);
            return var2.getValue();
        }
    }

  private void lambda$handleDBusBoundProperties$2(MethodCall arg0, Method arg1, Object arg2) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_1
        //      1: iconst_0
        //      2: anewarray  #45 // java.lang.Object
        //      5: invokevirtual  #199 // org.freedesktop.dbus.messages.MethodCall.setArgs:([Ljava/lang/Object;)V
        //      8: aload_0
        //      9: aload_1
        //     10: aload_2
        //     11: aload_3
        //     12: iconst_1
        //     13: aload_1
        //     14: invokevirtual  #193 // org.freedesktop.dbus.messages.MethodCall.getFlags:()I
        //     17: iconst_1
        //     18: iand
        //     19: if_icmpne  26 (offset +7)
        //     22: iconst_1
        //     23: goto  27 (offset +4)
        //     26: iconst_0
        //     27: invokevirtual  #155 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.invokeMethodAndReply:(Lorg/freedesktop/dbus/messages/MethodCall;Ljava/lang/reflect/Method;Ljava/lang/Object;Z)Ljava/lang/Object;
        //     30: pop
        //     31: return
    }

  private static void lambda$handleMessage$1(DBusSigHandler arg0, DBusSignal arg1) {
        arg0.handle(arg1);
    }

  private void lambda$handleMessage$0(DBusSignal arg0, AbstractConnectionBase arg1, DBusSigHandler arg2) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_1
        //      1: invokevirtual  #113 // java.lang.Object.getClass:()Ljava/lang/Class;
        //      4: ldc  #94 // org.freedesktop.dbus.messages.DBusSignal
        //      6: invokevirtual  #112 // java.lang.Object.equals:(Ljava/lang/Object;)Z
        //      9: ifeq  22 (offset +13)
        //     12: aload_1
        //     13: aload_2
        //     14: invokevirtual  #184 // org.freedesktop.dbus.messages.DBusSignal.createReal:(Lorg/freedesktop/dbus/connections/base/AbstractConnectionBase;)Lorg/freedesktop/dbus/messages/DBusSignal;
        //     17: astore  4
        //     19: goto  25 (offset +6)
        //     22: aload_1
        //     23: astore  4
        //     25: aload  4
        //     27: ifnonnull  31 (offset +4)
        //     30: return
        //     31: aload_3
        //     32: aload  4
        //     34: invokeinterface  #227 // org.freedesktop.dbus.interfaces.DBusSigHandler.handle:(Lorg/freedesktop/dbus/messages/DBusSignal;)V, count 2
        //     39: goto  102 (offset +63)
        //     42: astore  4
        //     44: aload_0
        //     45: invokevirtual  #143 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.getLogger:()Lorg/slf4j/Logger;
        //     48: ldc  #14 // "Exception while running signal handler '{}' for signal '{}':"
        //     50: iconst_3
        //     51: anewarray  #45 // java.lang.Object
        //     54: dup
        //     55: iconst_0
        //     56: aload_3
        //     57: aastore
        //     58: dup
        //     59: iconst_1
        //     60: aload_1
        //     61: aastore
        //     62: dup
        //     63: iconst_2
        //     64: aload  4
        //     66: aastore
        //     67: invokeinterface  #237 // org.slf4j.Logger.warn:(Ljava/lang/String;[Ljava/lang/Object;)V, count 3
        //     72: aload_0
        //     73: aload_1
        //     74: new  #92 // org.freedesktop.dbus.exceptions.DBusExecutionException
        //     77: dup
        //     78: aload_1
        //     79: invokevirtual  #185 // org.freedesktop.dbus.messages.DBusSignal.getInterface:()Ljava/lang/String;
        //     82: aload_1
        //     83: invokevirtual  #186 // org.freedesktop.dbus.messages.DBusSignal.getName:()Ljava/lang/String;
        //     86: aload  4
        //     88: invokevirtual  #182 // org.freedesktop.dbus.exceptions.DBusException.getMessage:()Ljava/lang/String;
        //     91: invokedynamic  #247 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //     96: invokespecial  #183 // org.freedesktop.dbus.exceptions.DBusExecutionException.<init>:(Ljava/lang/String;)V
        //     99: invokevirtual  #149 // org.freedesktop.dbus.connections.base.ConnectionMessageHandler.handleException:(Lorg/freedesktop/dbus/messages/Message;Lorg/freedesktop/dbus/exceptions/DBusExecutionException;)V
        //    102: return
        //       Exception table:
        //         from 0 to 30 target 42 type org.freedesktop.dbus.exceptions.DBusException
        //         from 31 to 39 target 42 type org.freedesktop.dbus.exceptions.DBusException
    }

}