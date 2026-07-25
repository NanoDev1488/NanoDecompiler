// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.connections.base.ReceivingService
package org.freedesktop.dbus.connections.base;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.freedesktop.dbus.connections.config.ReceivingServiceConfig;
import org.freedesktop.dbus.connections.config.ReceivingServiceConfigBuilder;
import org.freedesktop.dbus.connections.shared.ExecutorNames;
import org.freedesktop.dbus.connections.shared.IThreadPoolRetryHandler;
import org.freedesktop.dbus.utils.NameableThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReceivingService {

    // ---- поля ----
  static final int MAX_RETRIES = 50;
  private final Logger logger;
  private boolean closed;
  private final Map executors;
  private final IThreadPoolRetryHandler retryHandler;

   ReceivingService(String arg0, ReceivingServiceConfig arg1) { // было: <init>
        String __stk1;
        super();
        logger = LoggerFactory.getLogger(getClass());
        closed = false;
        executors = new ConcurrentHashMap();
        __stk1 = arg0 != null ? arg0 : "";
        String var3 = __stk1;
        ReceivingServiceConfig var4 = ((ReceivingServiceConfig) Optional.ofNullable(arg1).orElse(ReceivingServiceConfigBuilder.getDefaultConfig()));
        executors.put(ExecutorNames.SIGNAL, Executors.newFixedThreadPool(var4.getSignalThreadPoolSize(), new NameableThreadFactory(var3 + "DBus-Signal-Receiver-", true, var4.getSignalThreadPriority())));
        executors.put(ExecutorNames.ERROR, Executors.newFixedThreadPool(var4.getErrorThreadPoolSize(), new NameableThreadFactory(var3 + "DBus-Error-Receiver-", true, var4.getErrorThreadPriority())));
        executors.put(ExecutorNames.METHODCALL, Executors.newFixedThreadPool(var4.getMethodCallThreadPoolSize(), new NameableThreadFactory(var3 + "DBus-MethodCall-Receiver-", true, var4.getMethodCallThreadPriority())));
        executors.put(ExecutorNames.METHODRETURN, Executors.newFixedThreadPool(var4.getMethodReturnThreadPoolSize(), new NameableThreadFactory(var3 + "DBus-MethodReturn-Receiver-", true, var4.getMethodReturnThreadPriority())));
        retryHandler = var4.getRetryHandler();
    }

   int execSignalHandler(Runnable arg0) {
        return execOrFail(ExecutorNames.SIGNAL, arg0);
    }

   int execErrorHandler(Runnable arg0) {
        return execOrFail(ExecutorNames.ERROR, arg0);
    }

   int execMethodCallHandler(Runnable arg0) {
        return execOrFail(ExecutorNames.METHODCALL, arg0);
    }

   int execMethodReturnHandler(Runnable arg0) {
        return execOrFail(ExecutorNames.METHODRETURN, arg0);
    }

   int execOrFail(ExecutorNames arg0, Runnable arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_2
        //      1: ifnull  8 (offset +7)
        //      4: aload_1
        //      5: ifnonnull  10 (offset +5)
        //      8: iconst_m1
        //      9: ireturn
        //     10: iconst_0
        //     11: istore_3
        //     12: iload_3
        //     13: bipush  50
        //     15: if_icmpge  187 (offset +172)
        //     18: aload_0
        //     19: aload_1
        //     20: invokevirtual  #63 // org.freedesktop.dbus.connections.base.ReceivingService.getExecutor:(Lorg/freedesktop/dbus/connections/shared/ExecutorNames;)Ljava/util/concurrent/ExecutorService;
        //     23: astore  4
        //     25: aload  4
        //     27: ifnonnull  47 (offset +20)
        //     30: new  #38 // org.freedesktop.dbus.exceptions.IllegalThreadPoolStateException
        //     33: dup
        //     34: aload_1
        //     35: invokestatic  #54 // java.lang.String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //     38: invokedynamic  #100 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;
        //     43: invokespecial  #74 // org.freedesktop.dbus.exceptions.IllegalThreadPoolStateException.<init>:(Ljava/lang/String;)V
        //     46: athrow
        //     47: aload_0
        //     48: getfield  #42 // org.freedesktop.dbus.connections.base.ReceivingService.closed:Z
        //     51: ifne  74 (offset +23)
        //     54: aload  4
        //     56: invokeinterface  #87 // java.util.concurrent.ExecutorService.isShutdown:()Z, count 1
        //     61: ifne  74 (offset +13)
        //     64: aload  4
        //     66: invokeinterface  #88 // java.util.concurrent.ExecutorService.isTerminated:()Z, count 1
        //     71: ifeq  84 (offset +13)
        //     74: new  #38 // org.freedesktop.dbus.exceptions.IllegalThreadPoolStateException
        //     77: dup
        //     78: ldc  #13 // 'Receiving service already closed'
        //     80: invokespecial  #74 // org.freedesktop.dbus.exceptions.IllegalThreadPoolStateException.<init>:(Ljava/lang/String;)V
        //     83: athrow
        //     84: aload  4
        //     86: aload_2
        //     87: invokeinterface  #86 // java.util.concurrent.ExecutorService.execute:(Ljava/lang/Runnable;)V, count 2
        //     92: goto  187 (offset +95)
        //     95: astore  4
        //     97: aload  4
        //     99: athrow
        //    100: astore  4
        //    102: aload_0
        //    103: getfield  #45 // org.freedesktop.dbus.connections.base.ReceivingService.retryHandler:Lorg/freedesktop/dbus/connections/shared/IThreadPoolRetryHandler;
        //    106: ifnonnull  126 (offset +20)
        //    109: aload_0
        //    110: getfield  #44 // org.freedesktop.dbus.connections.base.ReceivingService.logger:Lorg/slf4j/Logger;
        //    113: ldc  #8 // 'Could not handle runnable for executor {}, runnable will be dropped'
        //    115: aload_1
        //    116: aload  4
        //    118: invokeinterface  #94 // org.slf4j.Logger.error:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V, count 4
        //    123: goto  187 (offset +64)
        //    126: iinc  3, 1
        //    129: aload_0
        //    130: getfield  #45 // org.freedesktop.dbus.connections.base.ReceivingService.retryHandler:Lorg/freedesktop/dbus/connections/shared/IThreadPoolRetryHandler;
        //    133: aload_1
        //    134: aload  4
        //    136: invokeinterface  #91 // org.freedesktop.dbus.connections.shared.IThreadPoolRetryHandler.handle:(Lorg/freedesktop/dbus/connections/shared/ExecutorNames;Ljava/lang/Exception;)Z, count 3
        //    141: ifne  184 (offset +43)
        //    144: aload_0
        //    145: getfield  #44 // org.freedesktop.dbus.connections.base.ReceivingService.logger:Lorg/slf4j/Logger;
        //    148: ldc  #10 // 'Ignoring unhandled runnable for executor {} due to {}, dropped by retry handler after {} retries'
        //    150: iconst_3
        //    151: anewarray  #19 // java.lang.Object
        //    154: dup
        //    155: iconst_0
        //    156: aload_1
        //    157: aastore
        //    158: dup
        //    159: iconst_1
        //    160: aload  4
        //    162: invokevirtual  #53 // java.lang.Object.getClass:()Ljava/lang/Class;
        //    165: invokevirtual  #50 // java.lang.Class.getName:()Ljava/lang/String;
        //    168: aastore
        //    169: dup
        //    170: iconst_2
        //    171: iload_3
        //    172: invokestatic  #51 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //    175: aastore
        //    176: invokeinterface  #95 // org.slf4j.Logger.trace:(Ljava/lang/String;[Ljava/lang/Object;)V, count 3
        //    181: goto  187 (offset +6)
        //    184: goto  12 (offset -172)
        //    187: iload_3
        //    188: bipush  50
        //    190: if_icmplt  209 (offset +19)
        //    193: aload_0
        //    194: getfield  #44 // org.freedesktop.dbus.connections.base.ReceivingService.logger:Lorg/slf4j/Logger;
        //    197: ldc  #7 // 'Could not handle runnable for executor {} after {} retries, runnable will be dropped'
        //    199: aload_1
        //    200: iload_3
        //    201: invokestatic  #51 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //    204: invokeinterface  #94 // org.slf4j.Logger.error:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V, count 4
        //    209: iload_3
        //    210: ireturn
        //       Exception table:
        //         from 18 to 92 target 95 type org.freedesktop.dbus.exceptions.IllegalThreadPoolStateException
        //         from 18 to 92 target 100 type java.lang.Exception
    }

   ExecutorService getExecutor(ExecutorNames arg0) {
        return ((ExecutorService) executors.get(arg0));
    }

  public synchronized void shutdown(int arg0, TimeUnit arg1) {
        Iterator var3 = executors.entrySet().iterator();
        while (var3.hasNext()) {
            Entry var4 = ((Entry) var3.next());
            logger.debug("Shutting down executor: {}", var4.getKey());
            (((ExecutorService) var4.getValue())).shutdown();
            continue;
        }
        var3 = executors.entrySet().iterator();
        while (var3.hasNext()) {
            Entry var4 = ((Entry) var3.next());
            try {
                (((ExecutorService) var4.getValue())).awaitTermination(((long) arg0), arg1);
            } catch (InterruptedException var5) {
                logger.debug("Interrupted while waiting for termination of executor");
                Thread.currentThread().interrupt();
            }
            continue;
        }
        closed = true;
    }

  public synchronized void shutdownNow() {
        Iterator var1 = executors.entrySet().iterator();
        while (var1.hasNext()) {
            Entry var2 = ((Entry) var1.next());
            if (!(((ExecutorService) var2.getValue())).isTerminated()) {
                logger.debug("Forcefully stopping {}", var2.getKey());
                (((ExecutorService) var2.getValue())).shutdownNow();
            }
            continue;
        }
        closed = true;
    }

}