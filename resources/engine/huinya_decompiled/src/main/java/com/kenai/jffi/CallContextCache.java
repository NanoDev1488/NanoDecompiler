// исходный (обфусцированный) внутренний класс: com.kenai.jffi.CallContextCache
package com.kenai.jffi;

import com.kenai.jffi.CallContext;
import com.kenai.jffi.CallContextCache_Anon1;
import com.kenai.jffi.CallContextCache_SingletonHolder;
import com.kenai.jffi.CallingConvention;
import com.kenai.jffi.Type;
import java.lang.ref.ReferenceQueue;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CallContextCache {

    // ---- поля ----
  private final Map contextCache;
  private final ReferenceQueue contextReferenceQueue;

  public static CallContextCache getInstance() {
        return CallContextCache_SingletonHolder.INSTANCE;
    }

  private CallContextCache() { // было: <init>
        super();
        contextCache = new ConcurrentHashMap();
        contextReferenceQueue = new ReferenceQueue();
    }

  public final CallContext getCallContext(Type arg0, Type[] arg1, CallingConvention arg2) {
        return getCallContext(arg0, arg1, arg2, true, false);
    }

  public final CallContext getCallContext(Type arg0, Type[] arg1, CallingConvention arg2, boolean arg3) {
        return getCallContext(arg0, arg1, arg2, arg3, false);
    }

  public final CallContext getCallContext(Type arg0, int arg1, Type[] arg2, CallingConvention arg3, boolean arg4) {
        return getCallContext(arg0, arg1, arg2, arg3, arg4, false);
    }

  public final CallContext getCallContext(Type arg0, Type[] arg1, CallingConvention arg2, boolean arg3, boolean arg4) {
        return getCallContext(arg0, arg1.length, arg1, arg2, arg3, arg4);
    }

  public final CallContext getCallContext(Type arg0, int arg1, Type[] arg2, CallingConvention arg3, boolean arg4, boolean arg5) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: new  #6 // com.kenai.jffi.CallContextCache$Signature
        //      3: dup
        //      4: aload_1
        //      5: aload_3
        //      6: aload  4
        //      8: iload  5
        //     10: iload  6
        //     12: invokespecial  #23 // com.kenai.jffi.CallContextCache$Signature.<init>:(Lcom/kenai/jffi/Type;[Lcom/kenai/jffi/Type;Lcom/kenai/jffi/CallingConvention;ZZ)V
        //     15: astore  7
        //     17: aload_0
        //     18: getfield  #12 // com.kenai.jffi.CallContextCache.contextCache:Ljava/util/Map;
        //     21: aload  7
        //     23: invokeinterface  #28 // java.util.Map.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //     28: checkcast  #5 // com.kenai.jffi.CallContextCache$CallContextRef
        //     31: astore  8
        //     33: aload  8
        //     35: ifnull  55 (offset +20)
        //     38: aload  8
        //     40: invokevirtual  #22 // com.kenai.jffi.CallContextCache$CallContextRef.get:()Ljava/lang/Object;
        //     43: checkcast  #2 // com.kenai.jffi.CallContext
        //     46: dup
        //     47: astore  9
        //     49: ifnull  55 (offset +6)
        //     52: aload  9
        //     54: areturn
        //     55: aload_0
        //     56: getfield  #13 // com.kenai.jffi.CallContextCache.contextReferenceQueue:Ljava/lang/ref/ReferenceQueue;
        //     59: invokevirtual  #26 // java.lang.ref.ReferenceQueue.poll:()Ljava/lang/ref/Reference;
        //     62: checkcast  #5 // com.kenai.jffi.CallContextCache$CallContextRef
        //     65: dup
        //     66: astore  8
        //     68: ifnull  89 (offset +21)
        //     71: aload_0
        //     72: getfield  #12 // com.kenai.jffi.CallContextCache.contextCache:Ljava/util/Map;
        //     75: aload  8
        //     77: getfield  #14 // com.kenai.jffi.CallContextCache$CallContextRef.signature:Lcom/kenai/jffi/CallContextCache$Signature;
        //     80: invokeinterface  #30 // java.util.Map.remove:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //     85: pop
        //     86: goto  55 (offset -31)
        //     89: new  #2 // com.kenai.jffi.CallContext
        //     92: dup
        //     93: aload_1
        //     94: iload_2
        //     95: aload_3
        //     96: invokevirtual  #16 // [Lcom.kenai.jffi.Type;.clone:()Ljava/lang/Object;
        //     99: checkcast  #1 // [Lcom.kenai.jffi.Type;
        //    102: aload  4
        //    104: iload  5
        //    106: iload  6
        //    108: invokespecial  #17 // com.kenai.jffi.CallContext.<init>:(Lcom/kenai/jffi/Type;I[Lcom/kenai/jffi/Type;Lcom/kenai/jffi/CallingConvention;ZZ)V
        //    111: astore  9
        //    113: aload_0
        //    114: getfield  #12 // com.kenai.jffi.CallContextCache.contextCache:Ljava/util/Map;
        //    117: aload  7
        //    119: new  #5 // com.kenai.jffi.CallContextCache$CallContextRef
        //    122: dup
        //    123: aload  7
        //    125: aload  9
        //    127: aload_0
        //    128: getfield  #13 // com.kenai.jffi.CallContextCache.contextReferenceQueue:Ljava/lang/ref/ReferenceQueue;
        //    131: invokespecial  #21 // com.kenai.jffi.CallContextCache$CallContextRef.<init>:(Lcom/kenai/jffi/CallContextCache$Signature;Lcom/kenai/jffi/CallContext;Ljava/lang/ref/ReferenceQueue;)V
        //    134: invokeinterface  #29 // java.util.Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;, count 3
        //    139: pop
        //    140: aload  9
        //    142: areturn
    }

   CallContextCache(CallContextCache_Anon1 arg0) { // было: <init>
        this();
    }

}