// исходный (обфусцированный) внутренний класс: jnr.ffi.util.ref.FinalizableReferenceQueue
package jnr.ffi.util.ref;

import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import jnr.ffi.util.ref.FinalizableReferenceQueue_FinalizerLoader;

public class FinalizableReferenceQueue {

    // ---- поля ----
  private static final Logger logger;
  private static final String FINALIZER_CLASS_NAME = "jnr.ffi.util.ref.internal.Finalizer";
  private static final Method startFinalizer;
  private static final Map finalizerQueues;
  final ReferenceQueue queue;
  final boolean threadStarted;

    static {
        logger = Logger.getLogger(FinalizableReferenceQueue.class.getName());
        Class var0 = loadFinalizer(new FinalizableReferenceQueue_FinalizerLoader[]{new FinalizableReferenceQueue_SystemLoader(), new FinalizableReferenceQueue_DecoupledLoader(), new FinalizableReferenceQueue_DirectLoader()});
        startFinalizer = getStartFinalizer(var0);
        finalizerQueues = Collections.synchronizedMap(new WeakHashMap());
    }

  public FinalizableReferenceQueue() { // было: <init>
        super();
        int var2 = 0;
        try {
            ReferenceQueue var1 = ((ReferenceQueue) startFinalizer.invoke(null, new Object[]{FinalizableReference.class, this}));
            var2 = 1;
        } catch (IllegalAccessException var3) {
            throw new AssertionError(var3);
        } catch (Throwable e2) {
            Throwable var3 = e2;
            logger.log(Level.INFO, "Failed to start reference finalizer thread. Reference cleanup will only occur when new references are created.", var3);
            ReferenceQueue var1 = new ReferenceQueue();
        }
    }

   void cleanUp() {
        if (!threadStarted) {
            pollReferenceQueue();
        }
    }

  private void pollReferenceQueue() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #32 // jnr.ffi.util.ref.FinalizableReferenceQueue.queue:Ljava/lang/ref/ReferenceQueue;
        //      4: invokevirtual  #42 // java.lang.ref.ReferenceQueue.poll:()Ljava/lang/ref/Reference;
        //      7: dup
        //      8: astore_1
        //      9: ifnull  44 (offset +35)
        //     12: aload_1
        //     13: invokevirtual  #40 // java.lang.ref.Reference.clear:()V
        //     16: aload_1
        //     17: checkcast  #21 // jnr.ffi.util.ref.FinalizableReference
        //     20: invokeinterface  #58 // jnr.ffi.util.ref.FinalizableReference.finalizeReferent:()V, count 1
        //     25: goto  0 (offset -25)
        //     28: astore_2
        //     29: getstatic  #31 // jnr.ffi.util.ref.FinalizableReferenceQueue.logger:Ljava/util/logging/Logger;
        //     32: getstatic  #29 // java.util.logging.Level.SEVERE:Ljava/util/logging/Level;
        //     35: ldc  #1 // 'Error cleaning up after reference.'
        //     37: aload_2
        //     38: invokevirtual  #47 // java.util.logging.Logger.log:(Ljava/util/logging/Level;Ljava/lang/String;Ljava/lang/Throwable;)V
        //     41: goto  0 (offset -41)
        //     44: return
        //       Exception table:
        //         from 16 to 25 target 28 type java.lang.Throwable
    }

  private static Class loadFinalizer(FinalizableReferenceQueue_FinalizerLoader[] arg0) {
        FinalizableReferenceQueue_FinalizerLoader[] var1 = arg0;
        int var2 = var1.length;
        int var3 = 0;
        Class var5;
        while (true) {
            if (var3 >= var2) {
                throw new AssertionError();
            }
            Object var4 = var1[var3];
            var5 = var4.loadFinalizer();
            if (var5 != null) {
                break;
            }
            ++var3;
            continue;
        }
        return var5;
    }

  static Method getStartFinalizer(Class arg0) {
        Method __stk2;
        try {
            __stk2 = arg0.getMethod("startFinalizer", new Class[]{Class.class, Object.class});
        } catch (NoSuchMethodException var1) {
            throw new AssertionError(var1);
        }
    }

  public static void cleanUpAll() {
        try {
            Object[] var0 = finalizerQueues.keySet().toArray();
            int var1 = var0.length;
            int var2 = 0;
            while (var2 < var1) {
                Object var3 = var0[var2];
                (((FinalizableReferenceQueue) var3)).cleanUp();
                ++var2;
                continue;
            }
        } catch (Throwable e1) {
            Throwable var0 = e1;
        }
    }

  static Logger access$000() {
        return logger;
    }

}