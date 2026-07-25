// исходный (обфусцированный) внутренний класс: com.kenai.jffi.HeapInvocationBuffer.ArrayIO.SingletonHolder
package com.kenai.jffi;

import com.kenai.jffi.HeapInvocationBuffer_ArrayIO;

final class HeapInvocationBuffer_ArrayIO_SingletonHolder {

    // ---- поля ----
  private static final HeapInvocationBuffer_ArrayIO DEFAULT;

    static {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: invokestatic  #19 // com.kenai.jffi.Platform.getPlatform:()Lcom/kenai/jffi/Platform;
        //      3: invokevirtual  #18 // com.kenai.jffi.Platform.addressSize:()I
        //      6: lookupswitch  default->82, 32->32, 64->57
        //     32: invokestatic  #27 // java.nio.ByteOrder.nativeOrder:()Ljava/nio/ByteOrder;
        //     35: getstatic  #12 // java.nio.ByteOrder.BIG_ENDIAN:Ljava/nio/ByteOrder;
        //     38: invokevirtual  #22 // java.lang.Object.equals:(Ljava/lang/Object;)Z
        //     41: ifeq  50 (offset +9)
        //     44: invokestatic  #13 // com.kenai.jffi.HeapInvocationBuffer$ArrayIO.getBE32IO:()Lcom/kenai/jffi/HeapInvocationBuffer$ArrayIO;
        //     47: goto  53 (offset +6)
        //     50: invokestatic  #15 // com.kenai.jffi.HeapInvocationBuffer$ArrayIO.getLE32IO:()Lcom/kenai/jffi/HeapInvocationBuffer$ArrayIO;
        //     53: astore_0
        //     54: goto  114 (offset +60)
        //     57: invokestatic  #27 // java.nio.ByteOrder.nativeOrder:()Ljava/nio/ByteOrder;
        //     60: getstatic  #12 // java.nio.ByteOrder.BIG_ENDIAN:Ljava/nio/ByteOrder;
        //     63: invokevirtual  #22 // java.lang.Object.equals:(Ljava/lang/Object;)Z
        //     66: ifeq  75 (offset +9)
        //     69: invokestatic  #14 // com.kenai.jffi.HeapInvocationBuffer$ArrayIO.getBE64IO:()Lcom/kenai/jffi/HeapInvocationBuffer$ArrayIO;
        //     72: goto  78 (offset +6)
        //     75: invokestatic  #16 // com.kenai.jffi.HeapInvocationBuffer$ArrayIO.getLE64IO:()Lcom/kenai/jffi/HeapInvocationBuffer$ArrayIO;
        //     78: astore_0
        //     79: goto  114 (offset +35)
        //     82: new  #6 // java.lang.IllegalArgumentException
        //     85: dup
        //     86: new  #8 // java.lang.StringBuilder
        //     89: dup
        //     90: invokespecial  #23 // java.lang.StringBuilder.<init>:()V
        //     93: ldc  #1 // 'unsupported address size: '
        //     95: invokevirtual  #25 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     98: invokestatic  #19 // com.kenai.jffi.Platform.getPlatform:()Lcom/kenai/jffi/Platform;
        //    101: invokevirtual  #18 // com.kenai.jffi.Platform.addressSize:()I
        //    104: invokevirtual  #24 // java.lang.StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    107: invokevirtual  #26 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    110: invokespecial  #20 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    113: athrow
        //    114: goto  123 (offset +9)
        //    117: astore_1
        //    118: aload_1
        //    119: invokestatic  #17 // com.kenai.jffi.HeapInvocationBuffer$ArrayIO.newInvalidArrayIO:(Ljava/lang/Throwable;)Lcom/kenai/jffi/HeapInvocationBuffer$ArrayIO;
        //    122: astore_0
        //    123: aload_0
        //    124: putstatic  #11 // com.kenai.jffi.HeapInvocationBuffer$ArrayIO$SingletonHolder.DEFAULT:Lcom/kenai/jffi/HeapInvocationBuffer$ArrayIO;
        //    127: return
        //       Exception table:
        //         from 0 to 114 target 117 type java.lang.Throwable
    }

  private HeapInvocationBuffer_ArrayIO_SingletonHolder() { // было: <init>
        super();
    }

  static HeapInvocationBuffer_ArrayIO access$000() {
        return DEFAULT;
    }

}