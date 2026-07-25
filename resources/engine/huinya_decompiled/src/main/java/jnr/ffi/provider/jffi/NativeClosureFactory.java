// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.NativeClosureFactory
package jnr.ffi.provider.jffi;

import com.kenai.jffi.CallContext;
import com.kenai.jffi.ClosureMagazine;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import jnr.ffi.Runtime;
import jnr.ffi.annotations.Delegate;
import jnr.ffi.mapper.SignatureTypeMapper;
import jnr.ffi.provider.FromNativeType;
import jnr.ffi.provider.jffi.AsmClassLoader;
import jnr.ffi.provider.jffi.ClosureUtil;
import jnr.ffi.provider.jffi.InvokerUtil;
import jnr.ffi.provider.jffi.NativeClosureFactory_ClosureReference;
import jnr.ffi.provider.jffi.NativeClosurePointer;
import jnr.ffi.provider.jffi.NativeClosureProxy;
import jnr.ffi.provider.jffi.NativeClosureProxy_Factory;

public final class NativeClosureFactory {

    // ---- поля ----
  private final Runtime runtime;
  private final ConcurrentMap closures;
  private final CallContext callContext;
  private final NativeClosureProxy_Factory closureProxyFactory;
  private final ConcurrentLinkedQueue freeQueue;
  private ClosureMagazine currentMagazine;

  protected NativeClosureFactory(Runtime arg0, CallContext arg1, NativeClosureProxy_Factory arg2) { // было: <init>
        super();
        closures = new ConcurrentHashMap();
        freeQueue = new ConcurrentLinkedQueue();
        runtime = arg0;
        closureProxyFactory = arg2;
        callContext = arg1;
    }

  static NativeClosureFactory newClosureFactory(Runtime arg0, Class arg1, SignatureTypeMapper arg2, AsmClassLoader arg3) {
        Object var4 = null;
        Method[] var5 = arg1.getMethods();
        int var6 = var5.length;
        int var7 = 0;
        while (var7 < var6) {
            Object var8 = var5[var7];
            if (!var8.isAnnotationPresent(Delegate.class)) {
                ++var7;
                continue;
            } else {
                if (!Modifier.isPublic(var8.getModifiers())) {
                    ++var7;
                    continue;
                } else {
                    if (Modifier.isStatic(var8.getModifiers())) {
                        ++var7;
                        continue;
                    } else {
                        var4 = var8;
                        break;
                    }
                }
            }
        }
        if (var4 != null) {
            var5 = var4.getParameterTypes();
            var6 = new FromNativeType[var5.length];
            var7 = 0;
        } else {
            throw new NoSuchMethodError(new StringBuilder().append("no public non-static delegate method defined in ").append(arg1.getName()).toString());
        }
        while (var7 < var5.length) {
            var6[var7] = ClosureUtil.getParameterType(arg0, ((Method) var4), var7, arg2);
            ++var7;
            continue;
        }
        var7 = ClosureUtil.getResultType(arg0, ((Method) var4), arg2);
        return new NativeClosureFactory(arg0, InvokerUtil.getCallContext(var7, var6, InvokerUtil.getNativeCallingConvention(((Method) var4)), false), NativeClosureProxy.newProxyFactory(arg0, ((Method) var4), var7, var6, arg3));
    }

  private void expunge(NativeClosureFactory_ClosureReference arg0, Integer arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_1
        //      1: getfield  #33 // jnr.ffi.provider.jffi.NativeClosureFactory$ClosureReference.next:Ljnr/ffi/provider/jffi/NativeClosureFactory$ClosureReference;
        //      4: ifnonnull  22 (offset +18)
        //      7: aload_0
        //      8: getfield  #29 // jnr.ffi.provider.jffi.NativeClosureFactory.closures:Ljava/util/concurrent/ConcurrentMap;
        //     11: aload_2
        //     12: aload_1
        //     13: invokeinterface  #75 // java.util.concurrent.ConcurrentMap.remove:(Ljava/lang/Object;Ljava/lang/Object;)Z, count 3
        //     18: ifeq  22 (offset +4)
        //     21: return
        //     22: aload_0
        //     23: getfield  #29 // jnr.ffi.provider.jffi.NativeClosureFactory.closures:Ljava/util/concurrent/ConcurrentMap;
        //     26: dup
        //     27: astore_3
        //     28: monitorenter
        //     29: aload_0
        //     30: getfield  #29 // jnr.ffi.provider.jffi.NativeClosureFactory.closures:Ljava/util/concurrent/ConcurrentMap;
        //     33: aload_2
        //     34: invokeinterface  #73 // java.util.concurrent.ConcurrentMap.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //     39: checkcast  #23 // jnr.ffi.provider.jffi.NativeClosureFactory$ClosureReference
        //     42: astore  4
        //     44: aload  4
        //     46: astore  5
        //     48: aload  4
        //     50: ifnull  138 (offset +88)
        //     53: aload  4
        //     55: aload_1
        //     56: if_acmpne  124 (offset +68)
        //     59: aload  5
        //     61: aload  4
        //     63: if_acmpeq  79 (offset +16)
        //     66: aload  5
        //     68: aload  4
        //     70: getfield  #33 // jnr.ffi.provider.jffi.NativeClosureFactory$ClosureReference.next:Ljnr/ffi/provider/jffi/NativeClosureFactory$ClosureReference;
        //     73: putfield  #33 // jnr.ffi.provider.jffi.NativeClosureFactory$ClosureReference.next:Ljnr/ffi/provider/jffi/NativeClosureFactory$ClosureReference;
        //     76: goto  138 (offset +62)
        //     79: aload  4
        //     81: getfield  #33 // jnr.ffi.provider.jffi.NativeClosureFactory$ClosureReference.next:Ljnr/ffi/provider/jffi/NativeClosureFactory$ClosureReference;
        //     84: ifnull  108 (offset +24)
        //     87: aload_0
        //     88: getfield  #29 // jnr.ffi.provider.jffi.NativeClosureFactory.closures:Ljava/util/concurrent/ConcurrentMap;
        //     91: aload_2
        //     92: aload  4
        //     94: aload  4
        //     96: getfield  #33 // jnr.ffi.provider.jffi.NativeClosureFactory$ClosureReference.next:Ljnr/ffi/provider/jffi/NativeClosureFactory$ClosureReference;
        //     99: invokeinterface  #76 // java.util.concurrent.ConcurrentMap.replace:(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Z, count 4
        //    104: pop
        //    105: goto  138 (offset +33)
        //    108: aload_0
        //    109: getfield  #29 // jnr.ffi.provider.jffi.NativeClosureFactory.closures:Ljava/util/concurrent/ConcurrentMap;
        //    112: aload_2
        //    113: aload  4
        //    115: invokeinterface  #75 // java.util.concurrent.ConcurrentMap.remove:(Ljava/lang/Object;Ljava/lang/Object;)Z, count 3
        //    120: pop
        //    121: goto  138 (offset +17)
        //    124: aload  4
        //    126: astore  5
        //    128: aload  4
        //    130: getfield  #33 // jnr.ffi.provider.jffi.NativeClosureFactory$ClosureReference.next:Ljnr/ffi/provider/jffi/NativeClosureFactory$ClosureReference;
        //    133: astore  4
        //    135: goto  48 (offset -87)
        //    138: aload_3
        //    139: monitorexit
        //    140: goto  150 (offset +10)
        //    143: astore  6
        //    145: aload_3
        //    146: monitorexit
        //    147: aload  6
        //    149: athrow
        //    150: return
        //       Exception table:
        //         from 29 to 140 target 143 type any
        //         from 143 to 147 target 143 type any
    }

  private void recycle(NativeClosurePointer arg0) {
        freeQueue.add(arg0);
    }

   NativeClosurePointer allocateClosurePointer() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #31 // jnr.ffi.provider.jffi.NativeClosureFactory.freeQueue:Ljava/util/concurrent/ConcurrentLinkedQueue;
        //      4: invokevirtual  #56 // java.util.concurrent.ConcurrentLinkedQueue.poll:()Ljava/lang/Object;
        //      7: checkcast  #24 // jnr.ffi.provider.jffi.NativeClosurePointer
        //     10: astore_1
        //     11: aload_1
        //     12: ifnull  17 (offset +5)
        //     15: aload_1
        //     16: areturn
        //     17: aload_0
        //     18: getfield  #28 // jnr.ffi.provider.jffi.NativeClosureFactory.closureProxyFactory:Ljnr/ffi/provider/jffi/NativeClosureProxy$Factory;
        //     21: invokevirtual  #72 // jnr.ffi.provider.jffi.NativeClosureProxy$Factory.newClosureProxy:()Ljnr/ffi/provider/jffi/NativeClosureProxy;
        //     24: astore_2
        //     25: aconst_null
        //     26: astore_3
        //     27: aload_0
        //     28: dup
        //     29: astore  4
        //     31: monitorenter
        //     32: aload_0
        //     33: getfield  #30 // jnr.ffi.provider.jffi.NativeClosureFactory.currentMagazine:Lcom/kenai/jffi/ClosureMagazine;
        //     36: ifnull  52 (offset +16)
        //     39: aload_0
        //     40: getfield  #30 // jnr.ffi.provider.jffi.NativeClosureFactory.currentMagazine:Lcom/kenai/jffi/ClosureMagazine;
        //     43: aload_2
        //     44: invokevirtual  #36 // com.kenai.jffi.ClosureMagazine.allocate:(Ljava/lang/Object;)Lcom/kenai/jffi/Closure$Handle;
        //     47: dup
        //     48: astore_3
        //     49: ifnonnull  73 (offset +24)
        //     52: aload_0
        //     53: invokestatic  #37 // com.kenai.jffi.ClosureManager.getInstance:()Lcom/kenai/jffi/ClosureManager;
        //     56: aload_0
        //     57: getfield  #27 // jnr.ffi.provider.jffi.NativeClosureFactory.callContext:Lcom/kenai/jffi/CallContext;
        //     60: aload_0
        //     61: getfield  #28 // jnr.ffi.provider.jffi.NativeClosureFactory.closureProxyFactory:Ljnr/ffi/provider/jffi/NativeClosureProxy$Factory;
        //     64: invokevirtual  #71 // jnr.ffi.provider.jffi.NativeClosureProxy$Factory.getInvokeMethod:()Ljava/lang/reflect/Method;
        //     67: invokevirtual  #38 // com.kenai.jffi.ClosureManager.newClosureMagazine:(Lcom/kenai/jffi/CallContext;Ljava/lang/reflect/Method;)Lcom/kenai/jffi/ClosureMagazine;
        //     70: putfield  #30 // jnr.ffi.provider.jffi.NativeClosureFactory.currentMagazine:Lcom/kenai/jffi/ClosureMagazine;
        //     73: aload_3
        //     74: ifnull  32 (offset -42)
        //     77: aload  4
        //     79: monitorexit
        //     80: goto  91 (offset +11)
        //     83: astore  5
        //     85: aload  4
        //     87: monitorexit
        //     88: aload  5
        //     90: athrow
        //     91: new  #24 // jnr.ffi.provider.jffi.NativeClosurePointer
        //     94: dup
        //     95: aload_0
        //     96: getfield  #32 // jnr.ffi.provider.jffi.NativeClosureFactory.runtime:Ljnr/ffi/Runtime;
        //     99: aload_3
        //    100: aload_2
        //    101: invokespecial  #69 // jnr.ffi.provider.jffi.NativeClosurePointer.<init>:(Ljnr/ffi/Runtime;Lcom/kenai/jffi/Closure$Handle;Ljnr/ffi/provider/jffi/NativeClosureProxy;)V
        //    104: areturn
        //       Exception table:
        //         from 32 to 80 target 83 type any
        //         from 83 to 88 target 83 type any
    }

   NativeClosurePointer newClosure(Object arg0, Integer arg1) {
        return NativeClosureFactory_ClosureReference.access$200(newClosureReference(arg0, arg1));
    }

   NativeClosureFactory_ClosureReference newClosureReference(Object arg0, Integer arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokevirtual  #62 // jnr.ffi.provider.jffi.NativeClosureFactory.allocateClosurePointer:()Ljnr/ffi/provider/jffi/NativeClosurePointer;
        //      4: astore_3
        //      5: new  #23 // jnr.ffi.provider.jffi.NativeClosureFactory$ClosureReference
        //      8: dup
        //      9: aload_0
        //     10: aload_1
        //     11: aload_2
        //     12: aload_0
        //     13: aload_3
        //     14: aconst_null
        //     15: invokespecial  #66 // jnr.ffi.provider.jffi.NativeClosureFactory$ClosureReference.<init>:(Ljnr/ffi/provider/jffi/NativeClosureFactory;Ljava/lang/Object;Ljava/lang/Integer;Ljnr/ffi/provider/jffi/NativeClosureFactory;Ljnr/ffi/provider/jffi/NativeClosurePointer;Ljnr/ffi/provider/jffi/NativeClosureFactory$1;)V
        //     18: astore  4
        //     20: aload_3
        //     21: getfield  #34 // jnr.ffi.provider.jffi.NativeClosurePointer.proxy:Ljnr/ffi/provider/jffi/NativeClosureProxy;
        //     24: aload  4
        //     26: putfield  #35 // jnr.ffi.provider.jffi.NativeClosureProxy.closureReference:Ljava/lang/ref/Reference;
        //     29: aload_0
        //     30: getfield  #29 // jnr.ffi.provider.jffi.NativeClosureFactory.closures:Ljava/util/concurrent/ConcurrentMap;
        //     33: aload_2
        //     34: aload  4
        //     36: invokeinterface  #74 // java.util.concurrent.ConcurrentMap.putIfAbsent:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;, count 3
        //     41: ifnonnull  47 (offset +6)
        //     44: aload  4
        //     46: areturn
        //     47: aload_0
        //     48: getfield  #29 // jnr.ffi.provider.jffi.NativeClosureFactory.closures:Ljava/util/concurrent/ConcurrentMap;
        //     51: dup
        //     52: astore  5
        //     54: monitorenter
        //     55: aload  4
        //     57: aload_0
        //     58: getfield  #29 // jnr.ffi.provider.jffi.NativeClosureFactory.closures:Ljava/util/concurrent/ConcurrentMap;
        //     61: aload_2
        //     62: invokeinterface  #73 // java.util.concurrent.ConcurrentMap.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //     67: checkcast  #23 // jnr.ffi.provider.jffi.NativeClosureFactory$ClosureReference
        //     70: putfield  #33 // jnr.ffi.provider.jffi.NativeClosureFactory$ClosureReference.next:Ljnr/ffi/provider/jffi/NativeClosureFactory$ClosureReference;
        //     73: aload  4
        //     75: getfield  #33 // jnr.ffi.provider.jffi.NativeClosureFactory$ClosureReference.next:Ljnr/ffi/provider/jffi/NativeClosureFactory$ClosureReference;
        //     78: ifnonnull  99 (offset +21)
        //     81: aload_0
        //     82: getfield  #29 // jnr.ffi.provider.jffi.NativeClosureFactory.closures:Ljava/util/concurrent/ConcurrentMap;
        //     85: aload_2
        //     86: aload  4
        //     88: invokeinterface  #74 // java.util.concurrent.ConcurrentMap.putIfAbsent:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;, count 3
        //     93: ifnonnull  99 (offset +6)
        //     96: goto  119 (offset +23)
        //     99: aload_0
        //    100: getfield  #29 // jnr.ffi.provider.jffi.NativeClosureFactory.closures:Ljava/util/concurrent/ConcurrentMap;
        //    103: aload_2
        //    104: aload  4
        //    106: getfield  #33 // jnr.ffi.provider.jffi.NativeClosureFactory$ClosureReference.next:Ljnr/ffi/provider/jffi/NativeClosureFactory$ClosureReference;
        //    109: aload  4
        //    111: invokeinterface  #76 // java.util.concurrent.ConcurrentMap.replace:(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Z, count 4
        //    116: ifeq  55 (offset -61)
        //    119: aload  5
        //    121: monitorexit
        //    122: goto  133 (offset +11)
        //    125: astore  6
        //    127: aload  5
        //    129: monitorexit
        //    130: aload  6
        //    132: athrow
        //    133: aload  4
        //    135: areturn
        //       Exception table:
        //         from 55 to 122 target 125 type any
        //         from 125 to 130 target 125 type any
    }

   NativeClosureFactory_ClosureReference getClosureReference(Object arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_1
        //      1: invokestatic  #47 // java.lang.System.identityHashCode:(Ljava/lang/Object;)I
        //      4: invokestatic  #41 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //      7: astore_2
        //      8: aload_0
        //      9: getfield  #29 // jnr.ffi.provider.jffi.NativeClosureFactory.closures:Ljava/util/concurrent/ConcurrentMap;
        //     12: aload_2
        //     13: invokeinterface  #73 // java.util.concurrent.ConcurrentMap.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //     18: checkcast  #23 // jnr.ffi.provider.jffi.NativeClosureFactory$ClosureReference
        //     21: astore_3
        //     22: aload_3
        //     23: ifnull  80 (offset +57)
        //     26: aload_3
        //     27: invokevirtual  #68 // jnr.ffi.provider.jffi.NativeClosureFactory$ClosureReference.getCallable:()Ljava/lang/Object;
        //     30: aload_1
        //     31: if_acmpne  36 (offset +5)
        //     34: aload_3
        //     35: areturn
        //     36: aload_0
        //     37: getfield  #29 // jnr.ffi.provider.jffi.NativeClosureFactory.closures:Ljava/util/concurrent/ConcurrentMap;
        //     40: dup
        //     41: astore  4
        //     43: monitorenter
        //     44: aload_3
        //     45: getfield  #33 // jnr.ffi.provider.jffi.NativeClosureFactory$ClosureReference.next:Ljnr/ffi/provider/jffi/NativeClosureFactory$ClosureReference;
        //     48: dup
        //     49: astore_3
        //     50: ifnull  66 (offset +16)
        //     53: aload_3
        //     54: invokevirtual  #68 // jnr.ffi.provider.jffi.NativeClosureFactory$ClosureReference.getCallable:()Ljava/lang/Object;
        //     57: aload_1
        //     58: if_acmpne  44 (offset -14)
        //     61: aload_3
        //     62: aload  4
        //     64: monitorexit
        //     65: areturn
        //     66: aload  4
        //     68: monitorexit
        //     69: goto  80 (offset +11)
        //     72: astore  5
        //     74: aload  4
        //     76: monitorexit
        //     77: aload  5
        //     79: athrow
        //     80: aload_0
        //     81: aload_1
        //     82: aload_2
        //     83: invokevirtual  #64 // jnr.ffi.provider.jffi.NativeClosureFactory.newClosureReference:(Ljava/lang/Object;Ljava/lang/Integer;)Ljnr/ffi/provider/jffi/NativeClosureFactory$ClosureReference;
        //     86: areturn
        //       Exception table:
        //         from 44 to 65 target 72 type any
        //         from 66 to 69 target 72 type any
        //         from 72 to 77 target 72 type any
    }

  static void access$000(NativeClosureFactory arg0, NativeClosureFactory_ClosureReference arg1, Integer arg2) {
        arg0.expunge(arg1, arg2);
    }

  static void access$100(NativeClosureFactory arg0, NativeClosurePointer arg1) {
        arg0.recycle(arg1);
    }

}