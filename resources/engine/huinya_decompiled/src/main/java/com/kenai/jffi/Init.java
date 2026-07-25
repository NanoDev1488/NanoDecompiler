// исходный (обфусцированный) внутренний класс: com.kenai.jffi.Init
package com.kenai.jffi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

final class Init {

    // ---- поля ----
  private static volatile boolean loaded;
  static final String stubLoaderClassName;

    static {
        loaded = false;
        stubLoaderClassName = new StringBuilder().append(Init.class.getPackage().getName()).append(".internal.StubLoader").toString();
    }

  private Init() { // было: <init>
        super();
    }

  static void load() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: getstatic  #25 // com.kenai.jffi.Init.loaded:Z
        //      3: ifeq  7 (offset +4)
        //      6: return
        //      7: new  #21 // java.util.ArrayList
        //     10: dup
        //     11: invokespecial  #51 // java.util.ArrayList.<init>:()V
        //     14: astore_0
        //     15: invokestatic  #27 // com.kenai.jffi.Init.getClassLoaders:()Ljava/util/List;
        //     18: astore_1
        //     19: aload_1
        //     20: invokeinterface  #58 // java.util.List.iterator:()Ljava/util/Iterator;, count 1
        //     25: astore_2
        //     26: aload_2
        //     27: invokeinterface  #53 // java.util.Iterator.hasNext:()Z, count 1
        //     32: ifeq  206 (offset +174)
        //     35: aload_2
        //     36: invokeinterface  #54 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //     41: checkcast  #9 // java.lang.ClassLoader
        //     44: astore_3
        //     45: getstatic  #26 // com.kenai.jffi.Init.stubLoaderClassName:Ljava/lang/String;
        //     48: iconst_1
        //     49: aload_3
        //     50: invokestatic  #34 // java.lang.Class.forName:(Ljava/lang/String;ZLjava/lang/ClassLoader;)Ljava/lang/Class;
        //     53: astore  4
        //     55: aload  4
        //     57: ldc  #3 // 'isLoaded'
        //     59: iconst_0
        //     60: anewarray  #8 // java.lang.Class
        //     63: invokevirtual  #36 // java.lang.Class.getDeclaredMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //     66: astore  5
        //     68: getstatic  #25 // com.kenai.jffi.Init.loaded:Z
        //     71: ldc  #7 // java.lang.Boolean
        //     73: aload  5
        //     75: aload  4
        //     77: iconst_0
        //     78: anewarray  #12 // java.lang.Object
        //     81: invokevirtual  #50 // java.lang.reflect.Method.invoke:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //     84: invokevirtual  #33 // java.lang.Class.cast:(Ljava/lang/Object;)Ljava/lang/Object;
        //     87: checkcast  #7 // java.lang.Boolean
        //     90: invokevirtual  #32 // java.lang.Boolean.booleanValue:()Z
        //     93: ior
        //     94: putstatic  #25 // com.kenai.jffi.Init.loaded:Z
        //     97: getstatic  #25 // com.kenai.jffi.Init.loaded:Z
        //    100: ifne  136 (offset +36)
        //    103: aload  4
        //    105: ldc  #2 // 'getFailureCause'
        //    107: iconst_0
        //    108: anewarray  #8 // java.lang.Class
        //    111: invokevirtual  #36 // java.lang.Class.getDeclaredMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //    114: astore  6
        //    116: ldc  #17 // java.lang.Throwable
        //    118: aload  6
        //    120: aload  4
        //    122: iconst_0
        //    123: anewarray  #12 // java.lang.Object
        //    126: invokevirtual  #50 // java.lang.reflect.Method.invoke:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //    129: invokevirtual  #33 // java.lang.Class.cast:(Ljava/lang/Object;)Ljava/lang/Object;
        //    132: checkcast  #17 // java.lang.Throwable
        //    135: athrow
        //    136: goto  203 (offset +67)
        //    139: astore  4
        //    141: aload_0
        //    142: aload  4
        //    144: invokeinterface  #56 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //    149: pop
        //    150: goto  203 (offset +53)
        //    153: astore  4
        //    155: aload_0
        //    156: aload  4
        //    158: invokeinterface  #56 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //    163: pop
        //    164: goto  203 (offset +39)
        //    167: astore  4
        //    169: aload_0
        //    170: aload  4
        //    172: invokeinterface  #56 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //    177: pop
        //    178: goto  203 (offset +25)
        //    181: astore  4
        //    183: aload  4
        //    185: instanceof  #18 // java.lang.UnsatisfiedLinkError
        //    188: ifeq  197 (offset +9)
        //    191: aload  4
        //    193: checkcast  #18 // java.lang.UnsatisfiedLinkError
        //    196: athrow
        //    197: aload  4
        //    199: invokestatic  #28 // com.kenai.jffi.Init.newLoadError:(Ljava/lang/Throwable;)Ljava/lang/UnsatisfiedLinkError;
        //    202: athrow
        //    203: goto  26 (offset -177)
        //    206: getstatic  #25 // com.kenai.jffi.Init.loaded:Z
        //    209: ifne  289 (offset +80)
        //    212: aload_0
        //    213: invokeinterface  #57 // java.util.List.isEmpty:()Z, count 1
        //    218: ifne  289 (offset +71)
        //    221: new  #6 // java.io.StringWriter
        //    224: dup
        //    225: invokespecial  #30 // java.io.StringWriter.<init>:()V
        //    228: astore_2
        //    229: new  #5 // java.io.PrintWriter
        //    232: dup
        //    233: aload_2
        //    234: invokespecial  #29 // java.io.PrintWriter.<init>:(Ljava/io/Writer;)V
        //    237: astore_3
        //    238: aload_0
        //    239: invokeinterface  #58 // java.util.List.iterator:()Ljava/util/Iterator;, count 1
        //    244: astore  4
        //    246: aload  4
        //    248: invokeinterface  #53 // java.util.Iterator.hasNext:()Z, count 1
        //    253: ifeq  277 (offset +24)
        //    256: aload  4
        //    258: invokeinterface  #54 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //    263: checkcast  #17 // java.lang.Throwable
        //    266: astore  5
        //    268: aload  5
        //    270: aload_3
        //    271: invokevirtual  #47 // java.lang.Throwable.printStackTrace:(Ljava/io/PrintWriter;)V
        //    274: goto  246 (offset -28)
        //    277: new  #18 // java.lang.UnsatisfiedLinkError
        //    280: dup
        //    281: aload_2
        //    282: invokevirtual  #31 // java.io.StringWriter.toString:()Ljava/lang/String;
        //    285: invokespecial  #48 // java.lang.UnsatisfiedLinkError.<init>:(Ljava/lang/String;)V
        //    288: athrow
        //    289: return
        //       Exception table:
        //         from 45 to 136 target 139 type java.lang.IllegalAccessException
        //         from 45 to 136 target 153 type java.lang.reflect.InvocationTargetException
        //         from 45 to 136 target 167 type java.lang.ClassNotFoundException
        //         from 45 to 136 target 181 type java.lang.Throwable
    }

  private static List getClassLoaders() {
        ArrayList var0 = new ArrayList();
        try {
            var0.add(ClassLoader.getSystemClassLoader());
        } catch (SecurityException var1) {
        }
        try {
            var0.add(Thread.currentThread().getContextClassLoader());
        } catch (SecurityException e2) {
            Throwable var1 = e2;
        }
        var0.add(Init.class.getClassLoader());
        int var1 = 0;
        Iterator var2 = var0.iterator();
        while (var2.hasNext()) {
            if (var2.next() != null) {
                continue;
            }
            if (++var1 <= 1) {
                continue;
            }
            var2.remove();
            continue;
        }
        return Collections.unmodifiableList(var0);
    }

  private static UnsatisfiedLinkError newLoadError(Throwable arg0) {
        UnsatisfiedLinkError var1 = new UnsatisfiedLinkError(arg0.getLocalizedMessage());
        var1.initCause(arg0);
        return var1;
    }

}