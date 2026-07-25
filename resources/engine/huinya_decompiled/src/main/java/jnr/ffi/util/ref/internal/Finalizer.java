// исходный (обфусцированный) внутренний класс: jnr.ffi.util.ref.internal.Finalizer
package jnr.ffi.util.ref.internal;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Finalizer implements Runnable {

    // ---- поля ----
  private static final Logger logger;
  private static final String FINALIZABLE_REFERENCE = "jnr.ffi.util.ref.FinalizableReference";
  private Thread thread;
  private final WeakReference finalizableReferenceClassReference;
  private final PhantomReference frqReference;
  private final ReferenceQueue queue;
  private static final Field inheritableThreadLocals;
  private static final Constructor inheritableThreadlocalsConstructor;

    static {
        logger = Logger.getLogger(Finalizer.class.getName());
        Object var0 = null;
        try {
            var0 = getInheritableThreadLocalsConstructor();
        } catch (Throwable var1) {
        }
        Throwable var1 = null;
        if (var0 == null) {
            try {
                var1 = getInheritableThreadLocalsField();
            } catch (Throwable var2) {
            }
        }
        inheritableThreadLocals = var1;
        inheritableThreadlocalsConstructor = ((Constructor) var0);
        if (var1 == null) {
            if (var0 == null) {
                logger.log(Level.INFO, "Couldn't access Thread.inheritableThreadLocals or appropriate constructor. Reference finalizer threads will inherit thread local values.");
            }
        }
    }

  public static ReferenceQueue startFinalizer(Class arg0, Object arg1) {
        if (arg0.getName().equals("jnr.ffi.util.ref.FinalizableReference")) {
            Finalizer var2 = new Finalizer(arg0, arg1);
            var2.start();
            return var2.queue;
        } else {
            throw new IllegalArgumentException("Expected jnr.ffi.util.ref.FinalizableReference.");
        }
    }

  private Finalizer(Class arg0, Object arg1) { // было: <init>
        super();
        queue = new ReferenceQueue();
        finalizableReferenceClassReference = new WeakReference(arg0);
        frqReference = new PhantomReference(arg1, queue);
    }

  public void start() {
        if (inheritableThreadlocalsConstructor != null) {
            try {
                thread = ((Thread) inheritableThreadlocalsConstructor.newInstance(new Object[]{Thread.currentThread().getThreadGroup(), this, Finalizer.class.getName(), Integer.valueOf(0), Boolean.valueOf(false)}));
            } catch (Throwable var1) {
                logger.log(Level.INFO, "Failed to disable thread local values inherited by reference finalizer thread.", var1);
            }
        }
        if (thread == null) {
            thread = new Thread(this, Finalizer.class.getName());
            if (inheritableThreadLocals != null) {
                try {
                    inheritableThreadLocals.set(thread, null);
                } catch (Throwable e2) {
                    Throwable var1 = e2;
                    logger.log(Level.INFO, "Failed to clear thread local values inherited by reference finalizer thread.", var1);
                }
            }
        }
        thread.setDaemon(true);
        thread.setPriority(10);
        thread.setContextClassLoader(null);
        thread.start();
    }

  public void run() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: aload_0
        //      2: getfield  #42 // jnr.ffi.util.ref.internal.Finalizer.queue:Ljava/lang/ref/ReferenceQueue;
        //      5: invokevirtual  #65 // java.lang.ref.ReferenceQueue.remove:()Ljava/lang/ref/Reference;
        //      8: invokespecial  #76 // jnr.ffi.util.ref.internal.Finalizer.cleanUp:(Ljava/lang/ref/Reference;)Z
        //     11: ifne  17 (offset +6)
        //     14: goto  24 (offset +10)
        //     17: goto  0 (offset -17)
        //     20: astore_1
        //     21: goto  0 (offset -21)
        //     24: return
        //       Exception table:
        //         from 0 to 14 target 20 type java.lang.InterruptedException
    }

  private boolean cleanUp(Reference arg0) {
        Method var2 = getFinalizeReferentMethod();
        if (var2 == null) {
            return false;
        }
        while (true) {
            arg0.clear();
            if (arg0 == frqReference) {
                break;
            }
            try {
                var2.invoke(arg0, new Object[0]);
            } catch (Throwable var3) {
                logger.log(Level.SEVERE, "Error cleaning up after reference.", var3);
            }
            arg0 = queue.poll();
            if (arg0 != null) {
                continue;
            }
            return true;
        }
        return false;
    }

  private Method getFinalizeReferentMethod() {
        Method __stk1;
        Class var1 = ((Class) finalizableReferenceClassReference.get());
        if (var1 == null) {
            return null;
        }
        try {
            __stk1 = var1.getMethod("finalizeReferent", new Class[0]);
        } catch (NoSuchMethodException var2) {
            throw new AssertionError(var2);
        }
    }

  public static Field getInheritableThreadLocalsField() {
        Field __stk1;
        try {
            Field var0 = Thread.class.getDeclaredField("inheritableThreadLocals");
            var0.setAccessible(true);
            __stk1 = var0;
        } catch (Throwable e1) {
            Throwable var0 = e1;
            return null;
        }
    }

  public static Constructor getInheritableThreadLocalsConstructor() {
        Constructor __stk2;
        try {
            __stk2 = Thread.class.getConstructor(new Class[]{ThreadGroup.class, Runnable.class, String.class, Long.TYPE, Boolean.TYPE});
        } catch (Throwable var0) {
            return null;
        }
    }

}