// исходный (обфусцированный) внутренний класс: com.kenai.jffi.Array
package com.kenai.jffi;

import com.kenai.jffi.Aggregate;
import com.kenai.jffi.Foreign;
import com.kenai.jffi.Type;

public final class Array extends Aggregate {

    // ---- поля ----
  private final Type elementType;
  private final int length;

  public static Array newArray(Type arg0, int arg1) {
        return new Array(arg0, arg1);
    }

  public Array(Type arg0, int arg1) { // было: <init>
        super(Foreign.getInstance(), Foreign.getInstance().newArray(arg0.handle(), arg1));
        elementType = arg0;
        length = arg1;
    }

  public final Type getElementType() {
        return elementType;
    }

  public final int length() {
        return length;
    }

  public boolean equals(Object arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: aload_1
        //      2: if_acmpne  7 (offset +5)
        //      5: iconst_1
        //      6: ireturn
        //      7: aload_1
        //      8: ifnull  22 (offset +14)
        //     11: aload_0
        //     12: invokevirtual  #17 // java.lang.Object.getClass:()Ljava/lang/Class;
        //     15: aload_1
        //     16: invokevirtual  #17 // java.lang.Object.getClass:()Ljava/lang/Class;
        //     19: if_acmpeq  24 (offset +5)
        //     22: iconst_0
        //     23: ireturn
        //     24: aload_0
        //     25: aload_1
        //     26: invokespecial  #9 // com.kenai.jffi.Aggregate.equals:(Ljava/lang/Object;)Z
        //     29: ifne  34 (offset +5)
        //     32: iconst_0
        //     33: ireturn
        //     34: aload_1
        //     35: checkcast  #2 // com.kenai.jffi.Array
        //     38: astore_2
        //     39: aload_0
        //     40: getfield  #7 // com.kenai.jffi.Array.length:I
        //     43: aload_2
        //     44: getfield  #7 // com.kenai.jffi.Array.length:I
        //     47: if_icmpeq  52 (offset +5)
        //     50: iconst_0
        //     51: ireturn
        //     52: aload_0
        //     53: getfield  #6 // com.kenai.jffi.Array.elementType:Lcom/kenai/jffi/Type;
        //     56: ifnull  76 (offset +20)
        //     59: aload_0
        //     60: getfield  #6 // com.kenai.jffi.Array.elementType:Lcom/kenai/jffi/Type;
        //     63: aload_2
        //     64: getfield  #6 // com.kenai.jffi.Array.elementType:Lcom/kenai/jffi/Type;
        //     67: invokevirtual  #14 // com.kenai.jffi.Type.equals:(Ljava/lang/Object;)Z
        //     70: ifne  85 (offset +15)
        //     73: goto  83 (offset +10)
        //     76: aload_2
        //     77: getfield  #6 // com.kenai.jffi.Array.elementType:Lcom/kenai/jffi/Type;
        //     80: ifnull  85 (offset +5)
        //     83: iconst_0
        //     84: ireturn
        //     85: iconst_1
        //     86: ireturn
    }

  public int hashCode() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokespecial  #10 // com.kenai.jffi.Aggregate.hashCode:()I
        //      4: istore_1
        //      5: bipush  31
        //      7: iload_1
        //      8: imul
        //      9: aload_0
        //     10: getfield  #6 // com.kenai.jffi.Array.elementType:Lcom/kenai/jffi/Type;
        //     13: ifnull  26 (offset +13)
        //     16: aload_0
        //     17: getfield  #6 // com.kenai.jffi.Array.elementType:Lcom/kenai/jffi/Type;
        //     20: invokevirtual  #16 // com.kenai.jffi.Type.hashCode:()I
        //     23: goto  27 (offset +4)
        //     26: iconst_0
        //     27: iadd
        //     28: istore_1
        //     29: bipush  31
        //     31: iload_1
        //     32: imul
        //     33: aload_0
        //     34: getfield  #7 // com.kenai.jffi.Array.length:I
        //     37: iadd
        //     38: istore_1
        //     39: iload_1
        //     40: ireturn
    }

}