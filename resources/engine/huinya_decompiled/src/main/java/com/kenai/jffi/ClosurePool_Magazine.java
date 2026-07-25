// исходный (обфусцированный) внутренний класс: com.kenai.jffi.ClosurePool.Magazine
package com.kenai.jffi;

import com.kenai.jffi.CallContext;
import com.kenai.jffi.ClosurePool;
import com.kenai.jffi.ClosurePool_Magazine_Slot;
import com.kenai.jffi.Foreign;
import com.kenai.jffi.MemoryIO;

final class ClosurePool_Magazine {

    // ---- поля ----
  private static final MemoryIO IO;
  private final Foreign foreign;
  private final CallContext ctx;
  private final long magazine;
  private final ClosurePool_Magazine_Slot[] slots;
  private int next;
  private int freeCount;

    static {
        IO = MemoryIO.getInstance();
    }

   ClosurePool_Magazine(CallContext arg0) { // было: <init>
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokespecial  #30 // java.lang.Object.<init>:()V
        //      4: aload_0
        //      5: invokestatic  #27 // com.kenai.jffi.Foreign.getInstance:()Lcom/kenai/jffi/Foreign;
        //      8: putfield  #12 // com.kenai.jffi.ClosurePool$Magazine.foreign:Lcom/kenai/jffi/Foreign;
        //     11: aload_0
        //     12: aload_1
        //     13: putfield  #11 // com.kenai.jffi.ClosurePool$Magazine.ctx:Lcom/kenai/jffi/CallContext;
        //     16: aload_0
        //     17: aload_0
        //     18: getfield  #12 // com.kenai.jffi.ClosurePool$Magazine.foreign:Lcom/kenai/jffi/Foreign;
        //     21: aload_1
        //     22: invokevirtual  #21 // com.kenai.jffi.CallContext.getAddress:()J
        //     25: getstatic  #19 // com.kenai.jffi.ClosurePool$Proxy.METHOD:Ljava/lang/reflect/Method;
        //     28: iconst_0
        //     29: invokevirtual  #28 // com.kenai.jffi.Foreign.newClosureMagazine:(JLjava/lang/reflect/Method;Z)J
        //     32: putfield  #14 // com.kenai.jffi.ClosurePool$Magazine.magazine:J
        //     35: new  #9 // java.util.ArrayList
        //     38: dup
        //     39: invokespecial  #32 // java.util.ArrayList.<init>:()V
        //     42: astore_2
        //     43: new  #5 // com.kenai.jffi.ClosurePool$Proxy
        //     46: dup
        //     47: aload_1
        //     48: invokespecial  #24 // com.kenai.jffi.ClosurePool$Proxy.<init>:(Lcom/kenai/jffi/CallContext;)V
        //     51: astore  5
        //     53: aload_0
        //     54: getfield  #12 // com.kenai.jffi.ClosurePool$Magazine.foreign:Lcom/kenai/jffi/Foreign;
        //     57: aload_0
        //     58: getfield  #14 // com.kenai.jffi.ClosurePool$Magazine.magazine:J
        //     61: aload  5
        //     63: invokevirtual  #25 // com.kenai.jffi.Foreign.closureMagazineGet:(JLjava/lang/Object;)J
        //     66: dup2
        //     67: lstore_3
        //     68: lconst_0
        //     69: lcmp
        //     70: ifne  76 (offset +6)
        //     73: goto  98 (offset +25)
        //     76: new  #4 // com.kenai.jffi.ClosurePool$Magazine$Slot
        //     79: dup
        //     80: lload_3
        //     81: aload  5
        //     83: invokespecial  #23 // com.kenai.jffi.ClosurePool$Magazine$Slot.<init>:(JLcom/kenai/jffi/ClosurePool$Proxy;)V
        //     86: astore  6
        //     88: aload_2
        //     89: aload  6
        //     91: invokevirtual  #33 // java.util.ArrayList.add:(Ljava/lang/Object;)Z
        //     94: pop
        //     95: goto  43 (offset -52)
        //     98: aload_0
        //     99: aload_2
        //    100: invokevirtual  #34 // java.util.ArrayList.size:()I
        //    103: anewarray  #4 // com.kenai.jffi.ClosurePool$Magazine$Slot
        //    106: putfield  #16 // com.kenai.jffi.ClosurePool$Magazine.slots:[Lcom/kenai/jffi/ClosurePool$Magazine$Slot;
        //    109: aload_2
        //    110: aload_0
        //    111: getfield  #16 // com.kenai.jffi.ClosurePool$Magazine.slots:[Lcom/kenai/jffi/ClosurePool$Magazine$Slot;
        //    114: invokevirtual  #35 // java.util.ArrayList.toArray:([Ljava/lang/Object;)[Ljava/lang/Object;
        //    117: pop
        //    118: aload_0
        //    119: iconst_0
        //    120: putfield  #15 // com.kenai.jffi.ClosurePool$Magazine.next:I
        //    123: aload_0
        //    124: aload_0
        //    125: getfield  #16 // com.kenai.jffi.ClosurePool$Magazine.slots:[Lcom/kenai/jffi/ClosurePool$Magazine$Slot;
        //    128: arraylength
        //    129: putfield  #13 // com.kenai.jffi.ClosurePool$Magazine.freeCount:I
        //    132: return
    }

   ClosurePool_Magazine_Slot get() {
        Object var1;
        while (true) {
            if (freeCount <= 0) {
                return null;
            }
            if (next >= slots.length) {
                return null;
            }
            next = next + 1;
            var1 = slots[next];
            if (var1.autorelease) {
                break;
            }
            continue;
        }
        freeCount = freeCount - 1;
        return ((Slot) var1);
    }

   boolean isFull() {
        return slots.length == freeCount;
    }

   boolean isEmpty() {
        return freeCount < 1;
    }

   void recycle() {
        int var1 = 0;
        while (var1 < slots.length) {
            Object var2 = slots[var1];
            if (var2.autorelease) {
                freeCount = freeCount + 1;
                var2.proxy.closure = ClosurePool.access$000();
            }
            ++var1;
            continue;
        }
        next = 0;
    }

  protected void finalize() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: iconst_1
        //      1: istore_1
        //      2: iconst_0
        //      3: istore_2
        //      4: iload_2
        //      5: aload_0
        //      6: getfield  #16 // com.kenai.jffi.ClosurePool$Magazine.slots:[Lcom/kenai/jffi/ClosurePool$Magazine$Slot;
        //      9: arraylength
        //     10: if_icmpge  36 (offset +26)
        //     13: aload_0
        //     14: getfield  #16 // com.kenai.jffi.ClosurePool$Magazine.slots:[Lcom/kenai/jffi/ClosurePool$Magazine$Slot;
        //     17: iload_2
        //     18: aaload
        //     19: getfield  #17 // com.kenai.jffi.ClosurePool$Magazine$Slot.autorelease:Z
        //     22: ifne  30 (offset +8)
        //     25: iconst_0
        //     26: istore_1
        //     27: goto  36 (offset +9)
        //     30: iinc  2, 1
        //     33: goto  4 (offset -29)
        //     36: aload_0
        //     37: getfield  #14 // com.kenai.jffi.ClosurePool$Magazine.magazine:J
        //     40: lconst_0
        //     41: lcmp
        //     42: ifeq  60 (offset +18)
        //     45: iload_1
        //     46: ifeq  60 (offset +14)
        //     49: aload_0
        //     50: getfield  #12 // com.kenai.jffi.ClosurePool$Magazine.foreign:Lcom/kenai/jffi/Foreign;
        //     53: aload_0
        //     54: getfield  #14 // com.kenai.jffi.ClosurePool$Magazine.magazine:J
        //     57: invokevirtual  #26 // com.kenai.jffi.Foreign.freeClosureMagazine:(J)V
        //     60: aload_0
        //     61: invokespecial  #31 // java.lang.Object.finalize:()V
        //     64: goto  74 (offset +10)
        //     67: astore_3
        //     68: aload_0
        //     69: invokespecial  #31 // java.lang.Object.finalize:()V
        //     72: aload_3
        //     73: athrow
        //     74: return
        //       Exception table:
        //         from 0 to 60 target 67 type any
    }

  static MemoryIO access$100() {
        return IO;
    }

}