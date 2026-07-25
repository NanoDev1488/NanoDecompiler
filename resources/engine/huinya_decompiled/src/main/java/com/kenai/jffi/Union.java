// исходный (обфусцированный) внутренний класс: com.kenai.jffi.Union
package com.kenai.jffi;

import com.kenai.jffi.Aggregate;
import com.kenai.jffi.Foreign;
import com.kenai.jffi.Type;
import java.util.Arrays;

public final class Union extends Aggregate {

    // ---- поля ----
  private final Type[] fields;

  public static Union newUnion(Type[] arg0) {
        return new Union(arg0);
    }

  public Union(Type[] arg0) { // было: <init>
        super(Foreign.getInstance(), Foreign.getInstance().newStruct(Type.nativeHandles(arg0), true));
        fields = ((Type[]) arg0.clone());
    }

  public boolean equals(Object arg0) {
        if (this != arg0) {
            if (arg0 == null) {
                return false;
            } else {
                if (getClass() == arg0.getClass()) {
                    if (super.equals(arg0)) {
                        Union var2 = ((Union) arg0);
                        return Arrays.equals(fields, var2.fields);
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
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokespecial  #12 // com.kenai.jffi.Aggregate.hashCode:()I
        //      4: istore_1
        //      5: bipush  31
        //      7: iload_1
        //      8: imul
        //      9: aload_0
        //     10: getfield  #8 // com.kenai.jffi.Union.fields:[Lcom/kenai/jffi/Type;
        //     13: ifnull  26 (offset +13)
        //     16: aload_0
        //     17: getfield  #8 // com.kenai.jffi.Union.fields:[Lcom/kenai/jffi/Type;
        //     20: invokestatic  #19 // java.util.Arrays.hashCode:([Ljava/lang/Object;)I
        //     23: goto  27 (offset +4)
        //     26: iconst_0
        //     27: iadd
        //     28: istore_1
        //     29: iload_1
        //     30: ireturn
    }

}