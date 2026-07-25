// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.bin.DBusDaemon.DBusDaemonSenderThread
package org.freedesktop.dbus.bin;

import java.util.concurrent.atomic.AtomicBoolean;
import org.freedesktop.dbus.bin.DBusDaemon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBusDaemon_DBusDaemonSenderThread extends Thread {

    // ---- поля ----
  private final Logger logger;
  private final AtomicBoolean running;
  final DBusDaemon this$0;

  public DBusDaemon_DBusDaemonSenderThread(DBusDaemon arg0) { // было: <init>
        super();
        this$0 = arg0;
        logger = LoggerFactory.getLogger(getClass());
        running = new AtomicBoolean(false);
        setName(getClass().getSimpleName().replace('$', '-'));
    }

  public void run() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #31 // org.freedesktop.dbus.bin.DBusDaemon$DBusDaemonSenderThread.logger:Lorg/slf4j/Logger;
        //      4: ldc  #3 // '>>>> Sender thread started <<<<'
        //      6: invokeinterface  #56 // org.slf4j.Logger.debug:(Ljava/lang/String;)V, count 2
        //     11: aload_0
        //     12: getfield  #32 // org.freedesktop.dbus.bin.DBusDaemon$DBusDaemonSenderThread.running:Ljava/util/concurrent/atomic/AtomicBoolean;
        //     15: iconst_1
        //     16: invokevirtual  #46 // java.util.concurrent.atomic.AtomicBoolean.set:(Z)V
        //     19: aload_0
        //     20: getfield  #33 // org.freedesktop.dbus.bin.DBusDaemon$DBusDaemonSenderThread.this$0:Lorg/freedesktop/dbus/bin/DBusDaemon;
        //     23: invokevirtual  #47 // org.freedesktop.dbus.bin.DBusDaemon.isRunning:()Z
        //     26: ifeq  232 (offset +206)
        //     29: aload_0
        //     30: getfield  #32 // org.freedesktop.dbus.bin.DBusDaemon$DBusDaemonSenderThread.running:Ljava/util/concurrent/atomic/AtomicBoolean;
        //     33: invokevirtual  #45 // java.util.concurrent.atomic.AtomicBoolean.get:()Z
        //     36: ifeq  232 (offset +196)
        //     39: aload_0
        //     40: getfield  #31 // org.freedesktop.dbus.bin.DBusDaemon$DBusDaemonSenderThread.logger:Lorg/slf4j/Logger;
        //     43: ldc  #4 // 'Acquiring lock on outqueue and blocking for data'
        //     45: invokeinterface  #60 // org.slf4j.Logger.trace:(Ljava/lang/String;)V, count 2
        //     50: aload_0
        //     51: getfield  #33 // org.freedesktop.dbus.bin.DBusDaemon$DBusDaemonSenderThread.this$0:Lorg/freedesktop/dbus/bin/DBusDaemon;
        //     54: getfield  #28 // org.freedesktop.dbus.bin.DBusDaemon.outqueue:Ljava/util/concurrent/BlockingDeque;
        //     57: invokeinterface  #54 // java.util.concurrent.BlockingDeque.take:()Ljava/lang/Object;, count 1
        //     62: checkcast  #22 // org.freedesktop.dbus.bin.DBusDaemon$Pair
        //     65: astore_1
        //     66: aload_1
        //     67: ifnull  207 (offset +140)
        //     70: aload_1
        //     71: getfield  #35 // org.freedesktop.dbus.bin.DBusDaemon$Pair.second:Ljava/lang/Object;
        //     74: checkcast  #15 // java.lang.ref.WeakReference
        //     77: invokevirtual  #42 // java.lang.ref.WeakReference.get:()Ljava/lang/Object;
        //     80: checkcast  #20 // org.freedesktop.dbus.bin.DBusDaemon$ConnectionStruct
        //     83: astore_2
        //     84: aload_2
        //     85: ifnull  192 (offset +107)
        //     88: aload_2
        //     89: getfield  #29 // org.freedesktop.dbus.bin.DBusDaemon$ConnectionStruct.connection:Lorg/freedesktop/dbus/connections/transports/TransportConnection;
        //     92: invokevirtual  #51 // org.freedesktop.dbus.connections.transports.TransportConnection.getChannel:()Ljava/nio/channels/SocketChannel;
        //     95: invokevirtual  #43 // java.nio.channels.SocketChannel.isConnected:()Z
        //     98: ifeq  166 (offset +68)
        //    101: aload_0
        //    102: getfield  #31 // org.freedesktop.dbus.bin.DBusDaemon$DBusDaemonSenderThread.logger:Lorg/slf4j/Logger;
        //    105: ldc  #1 // '<outqueue> Got message {} for {}'
        //    107: aload_1
        //    108: getfield  #34 // org.freedesktop.dbus.bin.DBusDaemon$Pair.first:Ljava/lang/Object;
        //    111: aload_2
        //    112: getfield  #30 // org.freedesktop.dbus.bin.DBusDaemon$ConnectionStruct.unique:Ljava/lang/String;
        //    115: invokeinterface  #57 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V, count 4
        //    120: aload_2
        //    121: getfield  #29 // org.freedesktop.dbus.bin.DBusDaemon$ConnectionStruct.connection:Lorg/freedesktop/dbus/connections/transports/TransportConnection;
        //    124: invokevirtual  #52 // org.freedesktop.dbus.connections.transports.TransportConnection.getWriter:()Lorg/freedesktop/dbus/spi/message/IMessageWriter;
        //    127: aload_1
        //    128: getfield  #34 // org.freedesktop.dbus.bin.DBusDaemon$Pair.first:Ljava/lang/Object;
        //    131: checkcast  #24 // org.freedesktop.dbus.messages.Message
        //    134: invokeinterface  #55 // org.freedesktop.dbus.spi.message.IMessageWriter.writeMessage:(Lorg/freedesktop/dbus/messages/Message;)V, count 2
        //    139: goto  207 (offset +68)
        //    142: astore_3
        //    143: aload_0
        //    144: getfield  #31 // org.freedesktop.dbus.bin.DBusDaemon$DBusDaemonSenderThread.logger:Lorg/slf4j/Logger;
        //    147: ldc  #7 // 'Disconnecting client due to previous exception'
        //    149: aload_3
        //    150: invokeinterface  #58 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Throwable;)V, count 3
        //    155: aload_0
        //    156: getfield  #33 // org.freedesktop.dbus.bin.DBusDaemon$DBusDaemonSenderThread.this$0:Lorg/freedesktop/dbus/bin/DBusDaemon;
        //    159: aload_2
        //    160: invokevirtual  #48 // org.freedesktop.dbus.bin.DBusDaemon.removeConnection:(Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;)V
        //    163: goto  207 (offset +44)
        //    166: aload_0
        //    167: getfield  #31 // org.freedesktop.dbus.bin.DBusDaemon$DBusDaemonSenderThread.logger:Lorg/slf4j/Logger;
        //    170: ldc  #5 // 'Connection to {} broken'
        //    172: aload_2
        //    173: getfield  #29 // org.freedesktop.dbus.bin.DBusDaemon$ConnectionStruct.connection:Lorg/freedesktop/dbus/connections/transports/TransportConnection;
        //    176: invokeinterface  #61 // org.slf4j.Logger.warn:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //    181: aload_0
        //    182: getfield  #33 // org.freedesktop.dbus.bin.DBusDaemon$DBusDaemonSenderThread.this$0:Lorg/freedesktop/dbus/bin/DBusDaemon;
        //    185: aload_2
        //    186: invokevirtual  #48 // org.freedesktop.dbus.bin.DBusDaemon.removeConnection:(Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;)V
        //    189: goto  207 (offset +18)
        //    192: aload_0
        //    193: getfield  #31 // org.freedesktop.dbus.bin.DBusDaemon$DBusDaemonSenderThread.logger:Lorg/slf4j/Logger;
        //    196: ldc  #6 // 'Discarding {} connection reaped'
        //    198: aload_1
        //    199: getfield  #34 // org.freedesktop.dbus.bin.DBusDaemon$Pair.first:Ljava/lang/Object;
        //    202: invokeinterface  #59 // org.slf4j.Logger.info:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //    207: goto  19 (offset -188)
        //    210: astore_1
        //    211: aload_0
        //    212: getfield  #31 // org.freedesktop.dbus.bin.DBusDaemon$DBusDaemonSenderThread.logger:Lorg/slf4j/Logger;
        //    215: ldc  #8 // 'Got interrupted'
        //    217: aload_1
        //    218: invokeinterface  #58 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Throwable;)V, count 3
        //    223: invokestatic  #40 // java.lang.Thread.currentThread:()Ljava/lang/Thread;
        //    226: invokevirtual  #41 // java.lang.Thread.interrupt:()V
        //    229: goto  19 (offset -210)
        //    232: aload_0
        //    233: getfield  #31 // org.freedesktop.dbus.bin.DBusDaemon$DBusDaemonSenderThread.logger:Lorg/slf4j/Logger;
        //    236: ldc  #2 // '>>>> Sender Thread terminated <<<<'
        //    238: invokeinterface  #56 // org.slf4j.Logger.debug:(Ljava/lang/String;)V, count 2
        //    243: return
        //       Exception table:
        //         from 120 to 139 target 142 type java.io.IOException
        //         from 50 to 207 target 210 type java.lang.InterruptedException
    }

  public synchronized void terminate() {
        running.set(false);
        interrupt();
    }

}