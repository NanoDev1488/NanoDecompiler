// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.utils.NameableThreadFactory
package org.freedesktop.dbus.utils;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NameableThreadFactory implements ThreadFactory {

    // ---- поля ----
  private static final AtomicInteger POOL_NUMBER;
  private final ThreadGroup group;
  private final AtomicInteger threadNumber;
  private final String namePrefix;
  private final int threadPriority;
  private final boolean daemonizeThreads;

    static {
        POOL_NUMBER = new AtomicInteger(1);
    }

  public NameableThreadFactory(String arg0, boolean arg1) { // было: <init>
        this(arg0, arg1, 5);
    }

  public NameableThreadFactory(String arg0, boolean arg1, int arg2) { // было: <init>
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokespecial  #18 // java.lang.Object.<init>:()V
        //      4: aload_0
        //      5: new  #9 // java.util.concurrent.atomic.AtomicInteger
        //      8: dup
        //      9: iconst_1
        //     10: invokespecial  #25 // java.util.concurrent.atomic.AtomicInteger.<init>:(I)V
        //     13: putfield  #16 // org.freedesktop.dbus.utils.NameableThreadFactory.threadNumber:Ljava/util/concurrent/atomic/AtomicInteger;
        //     16: aload_0
        //     17: invokestatic  #20 // java.lang.Thread.currentThread:()Ljava/lang/Thread;
        //     20: invokevirtual  #21 // java.lang.Thread.getThreadGroup:()Ljava/lang/ThreadGroup;
        //     23: putfield  #14 // org.freedesktop.dbus.utils.NameableThreadFactory.group:Ljava/lang/ThreadGroup;
        //     26: aload_0
        //     27: aload_1
        //     28: invokestatic  #28 // org.freedesktop.dbus.utils.Util.isBlank:(Ljava/lang/String;)Z
        //     31: ifeq  48 (offset +17)
        //     34: getstatic  #12 // org.freedesktop.dbus.utils.NameableThreadFactory.POOL_NUMBER:Ljava/util/concurrent/atomic/AtomicInteger;
        //     37: invokevirtual  #26 // java.util.concurrent.atomic.AtomicInteger.getAndIncrement:()I
        //     40: invokedynamic  #29 // invokedynamic makeConcatWithConstants:(I)Ljava/lang/String;
        //     45: goto  49 (offset +4)
        //     48: aload_1
        //     49: putfield  #15 // org.freedesktop.dbus.utils.NameableThreadFactory.namePrefix:Ljava/lang/String;
        //     52: aload_0
        //     53: iload_2
        //     54: putfield  #13 // org.freedesktop.dbus.utils.NameableThreadFactory.daemonizeThreads:Z
        //     57: aload_0
        //     58: iload_3
        //     59: putfield  #17 // org.freedesktop.dbus.utils.NameableThreadFactory.threadPriority:I
        //     62: return
    }

  public Thread newThread(Runnable arg0) {
        Thread var2 = new Thread(group, arg0, namePrefix + threadNumber.getAndIncrement(), 0L);
        var2.setDaemon(daemonizeThreads);
        var2.setPriority(threadPriority);
        return var2;
    }

}