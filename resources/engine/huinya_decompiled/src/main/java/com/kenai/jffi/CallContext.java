// исходный (обфусцированный) внутренний класс: com.kenai.jffi.CallContext
package com.kenai.jffi;

import com.kenai.jffi.CallContextCache;
import com.kenai.jffi.CallingConvention;
import com.kenai.jffi.Foreign;
import com.kenai.jffi.Type;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class CallContext {

    // ---- поля ----
  final long contextAddress;
  private final int parameterCount;
  private final int rawParameterSize;
  final Type returnType;
  final Type[] parameterTypes;
  final long[] parameterTypeHandles;
  final int fixedParamCount;
  final int flags;
  volatile int disposed;
  final AtomicIntegerFieldUpdater UPDATER;
  private final Foreign foreign;

  public static CallContext getCallContext(Type arg0, Type[] arg1, CallingConvention arg2, boolean arg3) {
        return CallContextCache.getInstance().getCallContext(arg0, arg1, arg2, arg3);
    }

  public static CallContext getCallContext(Type arg0, int arg1, Type[] arg2, CallingConvention arg3, boolean arg4) {
        return CallContextCache.getInstance().getCallContext(arg0, arg1, arg2, arg3, arg4);
    }

  public static CallContext getCallContext(Type arg0, Type[] arg1, CallingConvention arg2, boolean arg3, boolean arg4) {
        return CallContextCache.getInstance().getCallContext(arg0, arg1, arg2, arg3, arg4);
    }

  public CallContext(Type arg0, Type[] arg1) { // было: <init>
        this(arg0, arg1, CallingConvention.DEFAULT, true);
    }

  public CallContext(Type arg0, Type[] arg1, CallingConvention arg2) { // было: <init>
        this(arg0, arg1, arg2, true);
    }

  public CallContext(Type arg0, Type[] arg1, CallingConvention arg2, boolean arg3) { // было: <init>
        this(arg0, arg1.length, arg1, arg2, arg3, false);
    }

   CallContext(Type arg0, int arg1, Type[] arg2, CallingConvention arg3, boolean arg4, boolean arg5) { // было: <init>
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokespecial  #49 // java.lang.Object.<init>:()V
        //      4: aload_0
        //      5: ldc  #6 // com.kenai.jffi.CallContext
        //      7: ldc  #3 // 'disposed'
        //      9: invokestatic  #61 // java.util.concurrent.atomic.AtomicIntegerFieldUpdater.newUpdater:(Ljava/lang/Class;Ljava/lang/String;)Ljava/util/concurrent/atomic/AtomicIntegerFieldUpdater;
        //     12: putfield  #20 // com.kenai.jffi.CallContext.UPDATER:Ljava/util/concurrent/atomic/AtomicIntegerFieldUpdater;
        //     15: aload_0
        //     16: invokestatic  #42 // com.kenai.jffi.Foreign.getInstance:()Lcom/kenai/jffi/Foreign;
        //     19: putfield  #24 // com.kenai.jffi.CallContext.foreign:Lcom/kenai/jffi/Foreign;
        //     22: iload  5
        //     24: ifne  31 (offset +7)
        //     27: iconst_2
        //     28: goto  32 (offset +4)
        //     31: iconst_0
        //     32: aload  4
        //     34: getstatic  #31 // com.kenai.jffi.CallingConvention.STDCALL:Lcom/kenai/jffi/CallingConvention;
        //     37: if_acmpne  44 (offset +7)
        //     40: iconst_1
        //     41: goto  45 (offset +4)
        //     44: iconst_0
        //     45: ior
        //     46: iload  6
        //     48: ifeq  55 (offset +7)
        //     51: iconst_4
        //     52: goto  56 (offset +4)
        //     55: iconst_0
        //     56: ior
        //     57: istore  7
        //     59: aload_0
        //     60: getfield  #24 // com.kenai.jffi.CallContext.foreign:Lcom/kenai/jffi/Foreign;
        //     63: aload_1
        //     64: invokevirtual  #45 // com.kenai.jffi.Type.handle:()J
        //     67: aload_3
        //     68: invokestatic  #47 // com.kenai.jffi.Type.nativeHandles:([Lcom/kenai/jffi/Type;)[J
        //     71: iload  7
        //     73: iload_2
        //     74: bipush  16
        //     76: ishl
        //     77: ior
        //     78: invokevirtual  #43 // com.kenai.jffi.Foreign.newCallContext:(J[JI)J
        //     81: lstore  8
        //     83: lload  8
        //     85: lconst_0
        //     86: lcmp
        //     87: ifne  100 (offset +13)
        //     90: new  #13 // java.lang.RuntimeException
        //     93: dup
        //     94: ldc  #2 // 'Failed to create native function'
        //     96: invokespecial  #52 // java.lang.RuntimeException.<init>:(Ljava/lang/String;)V
        //     99: athrow
        //    100: aload_0
        //    101: lload  8
        //    103: putfield  #21 // com.kenai.jffi.CallContext.contextAddress:J
        //    106: aload_0
        //    107: aload_1
        //    108: putfield  #29 // com.kenai.jffi.CallContext.returnType:Lcom/kenai/jffi/Type;
        //    111: aload_0
        //    112: aload_3
        //    113: invokevirtual  #33 // [Lcom.kenai.jffi.Type;.clone:()Ljava/lang/Object;
        //    116: checkcast  #5 // [Lcom.kenai.jffi.Type;
        //    119: putfield  #27 // com.kenai.jffi.CallContext.parameterTypes:[Lcom/kenai/jffi/Type;
        //    122: aload_0
        //    123: aload_3
        //    124: arraylength
        //    125: putfield  #25 // com.kenai.jffi.CallContext.parameterCount:I
        //    128: aload_0
        //    129: iload_2
        //    130: putfield  #22 // com.kenai.jffi.CallContext.fixedParamCount:I
        //    133: aload_0
        //    134: aload_0
        //    135: getfield  #24 // com.kenai.jffi.CallContext.foreign:Lcom/kenai/jffi/Foreign;
        //    138: lload  8
        //    140: invokevirtual  #41 // com.kenai.jffi.Foreign.getCallContextRawParameterSize:(J)I
        //    143: putfield  #28 // com.kenai.jffi.CallContext.rawParameterSize:I
        //    146: aload_0
        //    147: aload_3
        //    148: invokestatic  #47 // com.kenai.jffi.Type.nativeHandles:([Lcom/kenai/jffi/Type;)[J
        //    151: putfield  #26 // com.kenai.jffi.CallContext.parameterTypeHandles:[J
        //    154: aload_0
        //    155: iload  7
        //    157: putfield  #23 // com.kenai.jffi.CallContext.flags:I
        //    160: return
    }

  public final int getParameterCount() {
        return parameterCount;
    }

  public final int getRawParameterSize() {
        return rawParameterSize;
    }

  final long getAddress() {
        return contextAddress;
    }

  public final Type getReturnType() {
        return returnType;
    }

  public final Type getParameterType(int arg0) {
        return ((Type) parameterTypes[arg0]);
    }

  public boolean equals(Object arg0) {
        if (this != arg0) {
            if (arg0 == null) {
                return false;
            } else {
                if (getClass() == arg0.getClass()) {
                    CallContext var2 = ((CallContext) arg0);
                    if (flags == var2.flags) {
                        if (parameterCount == var2.parameterCount) {
                            if (rawParameterSize == var2.rawParameterSize) {
                                if (Arrays.equals(parameterTypes, var2.parameterTypes)) {
                                    if (returnType.equals(var2.returnType)) {
                                        return true;
                                    } else {
                                        return false;
                                    }
                                } else {
                                    return false;
                                }
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } else {
            return true;
        }
    }

  public int hashCode() {
        int var1 = parameterCount;
        var1 = 31 * var1 + returnType.hashCode();
        var1 = 31 * var1 + Arrays.hashCode(parameterTypes);
        var1 = 31 * var1 + flags;
        return var1;
    }

    @Deprecated
  public final void dispose() {
        // (пустое тело)
    }

  protected void finalize() {
        try {
            int var1 = UPDATER.getAndSet(this, 1);
            if (var1 != 0) {
            }
            if (contextAddress != 0L) {
                foreign.freeCallContext(contextAddress);
            }
        } catch (Throwable e2) {
            try {
                Throwable var1 = e2;
                Logger.getLogger(getClass().getName()).log(Level.WARNING, new StringBuilder().append("exception when freeing ").append(getClass()).append(": %s").toString(), var1.getLocalizedMessage());
            } catch (Throwable var2) {
                super.finalize();
                throw var2;
            }
        } catch (Throwable var2) {
            super.finalize();
            throw var2;
        }
    }

}