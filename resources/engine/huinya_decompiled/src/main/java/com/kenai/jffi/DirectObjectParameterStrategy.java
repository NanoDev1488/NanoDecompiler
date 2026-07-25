// исходный (обфусцированный) внутренний класс: com.kenai.jffi.DirectObjectParameterStrategy
package com.kenai.jffi;

import com.kenai.jffi.ObjectParameterStrategy;
import com.kenai.jffi.ObjectParameterType;

public abstract class DirectObjectParameterStrategy extends ObjectParameterStrategy {

  public DirectObjectParameterStrategy(boolean arg0, ObjectParameterType arg1) { // было: <init>
        super(arg0, arg1);
    }

  public abstract long getAddress(Object arg0);

  public final Object object(Object arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: new  #9 // java.lang.RuntimeException
        //      3: dup
        //      4: new  #10 // java.lang.StringBuilder
        //      7: dup
        //      8: invokespecial  #14 // java.lang.StringBuilder.<init>:()V
        //     11: ldc  #2 // 'direct object '
        //     13: invokevirtual  #16 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     16: aload_1
        //     17: ifnull  27 (offset +10)
        //     20: aload_1
        //     21: invokevirtual  #12 // java.lang.Object.getClass:()Ljava/lang/Class;
        //     24: goto  29 (offset +5)
        //     27: ldc  #5 // 'null'
        //     29: invokevirtual  #15 // java.lang.StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //     32: ldc  #1 // ' has no array'
        //     34: invokevirtual  #16 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     37: invokevirtual  #17 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //     40: invokespecial  #13 // java.lang.RuntimeException.<init>:(Ljava/lang/String;)V
        //     43: athrow
    }

  public final int offset(Object arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: new  #9 // java.lang.RuntimeException
        //      3: dup
        //      4: new  #10 // java.lang.StringBuilder
        //      7: dup
        //      8: invokespecial  #14 // java.lang.StringBuilder.<init>:()V
        //     11: ldc  #2 // 'direct object '
        //     13: invokevirtual  #16 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     16: aload_1
        //     17: ifnull  27 (offset +10)
        //     20: aload_1
        //     21: invokevirtual  #12 // java.lang.Object.getClass:()Ljava/lang/Class;
        //     24: goto  29 (offset +5)
        //     27: ldc  #5 // 'null'
        //     29: invokevirtual  #15 // java.lang.StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //     32: ldc  #4 // 'has no offset'
        //     34: invokevirtual  #16 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     37: invokevirtual  #17 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //     40: invokespecial  #13 // java.lang.RuntimeException.<init>:(Ljava/lang/String;)V
        //     43: athrow
    }

  public final int length(Object arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: new  #9 // java.lang.RuntimeException
        //      3: dup
        //      4: new  #10 // java.lang.StringBuilder
        //      7: dup
        //      8: invokespecial  #14 // java.lang.StringBuilder.<init>:()V
        //     11: ldc  #2 // 'direct object '
        //     13: invokevirtual  #16 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     16: aload_1
        //     17: ifnull  27 (offset +10)
        //     20: aload_1
        //     21: invokevirtual  #12 // java.lang.Object.getClass:()Ljava/lang/Class;
        //     24: goto  29 (offset +5)
        //     27: ldc  #5 // 'null'
        //     29: invokevirtual  #15 // java.lang.StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //     32: ldc  #3 // 'has no length'
        //     34: invokevirtual  #16 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     37: invokevirtual  #17 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //     40: invokespecial  #13 // java.lang.RuntimeException.<init>:(Ljava/lang/String;)V
        //     43: athrow
    }

}