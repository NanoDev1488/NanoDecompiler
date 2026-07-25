// исходный (обфусцированный) внутренний класс: com.kenai.jffi.CallContextCache.Signature
package com.kenai.jffi;

import com.kenai.jffi.CallingConvention;
import com.kenai.jffi.Type;

final class CallContextCache_Signature {

    // ---- поля ----
  private final Type returnType;
  private final Type[] parameterTypes;
  private final CallingConvention convention;
  private final boolean saveErrno;
  private final boolean faultProtect;
  private int hashCode;

  public CallContextCache_Signature(Type arg0, Type[] arg1, CallingConvention arg2, boolean arg3, boolean arg4) { // было: <init>
        super();
        hashCode = 0;
        if (arg0 == null) {
            throw new NullPointerException("null return type or parameter types array");
        } else {
            if (arg1 != null) {
                returnType = arg0;
                parameterTypes = arg1;
                convention = arg2;
                saveErrno = arg3;
                faultProtect = arg4;
                return;
            } else {
                throw new NullPointerException("null return type or parameter types array");
            }
        }
    }

  public boolean equals(Object arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_1
        //      1: ifnull  15 (offset +14)
        //      4: aload_0
        //      5: invokevirtual  #20 // java.lang.Object.getClass:()Ljava/lang/Class;
        //      8: aload_1
        //      9: invokevirtual  #20 // java.lang.Object.getClass:()Ljava/lang/Class;
        //     12: if_acmpeq  17 (offset +5)
        //     15: iconst_0
        //     16: ireturn
        //     17: aload_1
        //     18: checkcast  #3 // com.kenai.jffi.CallContextCache$Signature
        //     21: astore_2
        //     22: aload_0
        //     23: getfield  #8 // com.kenai.jffi.CallContextCache$Signature.convention:Lcom/kenai/jffi/CallingConvention;
        //     26: aload_2
        //     27: getfield  #8 // com.kenai.jffi.CallContextCache$Signature.convention:Lcom/kenai/jffi/CallingConvention;
        //     30: if_acmpne  55 (offset +25)
        //     33: aload_0
        //     34: getfield  #13 // com.kenai.jffi.CallContextCache$Signature.saveErrno:Z
        //     37: aload_2
        //     38: getfield  #13 // com.kenai.jffi.CallContextCache$Signature.saveErrno:Z
        //     41: if_icmpne  55 (offset +14)
        //     44: aload_0
        //     45: getfield  #9 // com.kenai.jffi.CallContextCache$Signature.faultProtect:Z
        //     48: aload_2
        //     49: getfield  #9 // com.kenai.jffi.CallContextCache$Signature.faultProtect:Z
        //     52: if_icmpeq  57 (offset +5)
        //     55: iconst_0
        //     56: ireturn
        //     57: aload_0
        //     58: getfield  #12 // com.kenai.jffi.CallContextCache$Signature.returnType:Lcom/kenai/jffi/Type;
        //     61: aload_2
        //     62: getfield  #12 // com.kenai.jffi.CallContextCache$Signature.returnType:Lcom/kenai/jffi/Type;
        //     65: if_acmpeq  84 (offset +19)
        //     68: aload_0
        //     69: getfield  #12 // com.kenai.jffi.CallContextCache$Signature.returnType:Lcom/kenai/jffi/Type;
        //     72: aload_2
        //     73: getfield  #12 // com.kenai.jffi.CallContextCache$Signature.returnType:Lcom/kenai/jffi/Type;
        //     76: invokevirtual  #16 // com.kenai.jffi.Type.equals:(Ljava/lang/Object;)Z
        //     79: ifne  84 (offset +5)
        //     82: iconst_0
        //     83: ireturn
        //     84: aload_0
        //     85: getfield  #11 // com.kenai.jffi.CallContextCache$Signature.parameterTypes:[Lcom/kenai/jffi/Type;
        //     88: arraylength
        //     89: aload_2
        //     90: getfield  #11 // com.kenai.jffi.CallContextCache$Signature.parameterTypes:[Lcom/kenai/jffi/Type;
        //     93: arraylength
        //     94: if_icmpne  160 (offset +66)
        //     97: iconst_0
        //     98: istore_3
        //     99: iload_3
        //    100: aload_0
        //    101: getfield  #11 // com.kenai.jffi.CallContextCache$Signature.parameterTypes:[Lcom/kenai/jffi/Type;
        //    104: arraylength
        //    105: if_icmpge  158 (offset +53)
        //    108: aload_0
        //    109: getfield  #11 // com.kenai.jffi.CallContextCache$Signature.parameterTypes:[Lcom/kenai/jffi/Type;
        //    112: iload_3
        //    113: aaload
        //    114: aload_2
        //    115: getfield  #11 // com.kenai.jffi.CallContextCache$Signature.parameterTypes:[Lcom/kenai/jffi/Type;
        //    118: iload_3
        //    119: aaload
        //    120: if_acmpeq  152 (offset +32)
        //    123: aload_0
        //    124: getfield  #11 // com.kenai.jffi.CallContextCache$Signature.parameterTypes:[Lcom/kenai/jffi/Type;
        //    127: iload_3
        //    128: aaload
        //    129: ifnull  150 (offset +21)
        //    132: aload_0
        //    133: getfield  #11 // com.kenai.jffi.CallContextCache$Signature.parameterTypes:[Lcom/kenai/jffi/Type;
        //    136: iload_3
        //    137: aaload
        //    138: aload_2
        //    139: getfield  #11 // com.kenai.jffi.CallContextCache$Signature.parameterTypes:[Lcom/kenai/jffi/Type;
        //    142: iload_3
        //    143: aaload
        //    144: invokevirtual  #16 // com.kenai.jffi.Type.equals:(Ljava/lang/Object;)Z
        //    147: ifne  152 (offset +5)
        //    150: iconst_0
        //    151: ireturn
        //    152: iinc  3, 1
        //    155: goto  99 (offset -56)
        //    158: iconst_1
        //    159: ireturn
        //    160: iconst_0
        //    161: ireturn
    }

  private final int calculateHashCode() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: bipush  7
        //      2: istore_1
        //      3: bipush  53
        //      5: iload_1
        //      6: imul
        //      7: aload_0
        //      8: getfield  #12 // com.kenai.jffi.CallContextCache$Signature.returnType:Lcom/kenai/jffi/Type;
        //     11: ifnull  24 (offset +13)
        //     14: aload_0
        //     15: getfield  #12 // com.kenai.jffi.CallContextCache$Signature.returnType:Lcom/kenai/jffi/Type;
        //     18: invokevirtual  #17 // com.kenai.jffi.Type.hashCode:()I
        //     21: goto  25 (offset +4)
        //     24: iconst_0
        //     25: iadd
        //     26: istore_1
        //     27: iconst_1
        //     28: istore_2
        //     29: iconst_0
        //     30: istore_3
        //     31: iload_3
        //     32: aload_0
        //     33: getfield  #11 // com.kenai.jffi.CallContextCache$Signature.parameterTypes:[Lcom/kenai/jffi/Type;
        //     36: arraylength
        //     37: if_icmpge  61 (offset +24)
        //     40: bipush  31
        //     42: iload_2
        //     43: imul
        //     44: aload_0
        //     45: getfield  #11 // com.kenai.jffi.CallContextCache$Signature.parameterTypes:[Lcom/kenai/jffi/Type;
        //     48: iload_3
        //     49: aaload
        //     50: invokevirtual  #17 // com.kenai.jffi.Type.hashCode:()I
        //     53: iadd
        //     54: istore_2
        //     55: iinc  3, 1
        //     58: goto  31 (offset -27)
        //     61: bipush  53
        //     63: iload_1
        //     64: imul
        //     65: iload_2
        //     66: iadd
        //     67: istore_1
        //     68: bipush  53
        //     70: iload_1
        //     71: imul
        //     72: aload_0
        //     73: getfield  #8 // com.kenai.jffi.CallContextCache$Signature.convention:Lcom/kenai/jffi/CallingConvention;
        //     76: invokevirtual  #15 // com.kenai.jffi.CallingConvention.hashCode:()I
        //     79: iadd
        //     80: istore_1
        //     81: bipush  53
        //     83: iload_1
        //     84: imul
        //     85: aload_0
        //     86: getfield  #13 // com.kenai.jffi.CallContextCache$Signature.saveErrno:Z
        //     89: ifeq  96 (offset +7)
        //     92: iconst_1
        //     93: goto  97 (offset +4)
        //     96: iconst_0
        //     97: iadd
        //     98: istore_1
        //     99: bipush  53
        //    101: iload_1
        //    102: imul
        //    103: aload_0
        //    104: getfield  #9 // com.kenai.jffi.CallContextCache$Signature.faultProtect:Z
        //    107: ifeq  114 (offset +7)
        //    110: iconst_1
        //    111: goto  115 (offset +4)
        //    114: iconst_0
        //    115: iadd
        //    116: istore_1
        //    117: iload_1
        //    118: ireturn
    }

  public int hashCode() {
        int __stk1;
        if (hashCode == 0) {
            hashCode = calculateHashCode();
            __stk1 = calculateHashCode();
        } else {
            __stk1 = hashCode;
        }
        return __stk1;
    }

}