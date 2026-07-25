// исходный (обфусцированный) внутренний класс: jnr.posix.util.ExecIt.StreamPumper
package jnr.posix.util;

import java.io.InputStream;
import java.io.OutputStream;

class ExecIt_StreamPumper extends Thread {

    // ---- поля ----
  private InputStream in;
  private OutputStream out;
  private boolean onlyIfAvailable;
  private volatile boolean quit;
  private final Object waitLock;

   ExecIt_StreamPumper(InputStream arg0, OutputStream arg1, boolean arg2) { // было: <init>
        super();
        waitLock = new Object();
        in = arg0;
        out = arg1;
        onlyIfAvailable = arg2;
    }

  public void run() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: sipush  1024
        //      3: newarray  byte
        //      5: astore_1
        //      6: iconst_0
        //      7: istore_3
        //      8: aload_0
        //      9: getfield  #14 // jnr.posix.util.ExecIt$StreamPumper.quit:Z
        //     12: ifne  103 (offset +91)
        //     15: aload_0
        //     16: getfield  #12 // jnr.posix.util.ExecIt$StreamPumper.onlyIfAvailable:Z
        //     19: ifeq  73 (offset +54)
        //     22: iload_3
        //     23: ifne  73 (offset +50)
        //     26: aload_0
        //     27: getfield  #11 // jnr.posix.util.ExecIt$StreamPumper.in:Ljava/io/InputStream;
        //     30: invokevirtual  #16 // java.io.InputStream.available:()I
        //     33: ifne  71 (offset +38)
        //     36: aload_0
        //     37: getfield  #15 // jnr.posix.util.ExecIt$StreamPumper.waitLock:Ljava/lang/Object;
        //     40: dup
        //     41: astore  4
        //     43: monitorenter
        //     44: aload_0
        //     45: getfield  #15 // jnr.posix.util.ExecIt$StreamPumper.waitLock:Ljava/lang/Object;
        //     48: ldc2_w  #9 // 10L
        //     51: invokevirtual  #22 // java.lang.Object.wait:(J)V
        //     54: aload  4
        //     56: monitorexit
        //     57: goto  68 (offset +11)
        //     60: astore  5
        //     62: aload  4
        //     64: monitorexit
        //     65: aload  5
        //     67: athrow
        //     68: goto  8 (offset -60)
        //     71: iconst_1
        //     72: istore_3
        //     73: aload_0
        //     74: getfield  #11 // jnr.posix.util.ExecIt$StreamPumper.in:Ljava/io/InputStream;
        //     77: aload_1
        //     78: invokevirtual  #17 // java.io.InputStream.read:([B)I
        //     81: dup
        //     82: istore_2
        //     83: iconst_m1
        //     84: if_icmpne  90 (offset +6)
        //     87: goto  103 (offset +16)
        //     90: aload_0
        //     91: getfield  #13 // jnr.posix.util.ExecIt$StreamPumper.out:Ljava/io/OutputStream;
        //     94: aload_1
        //     95: iconst_0
        //     96: iload_2
        //     97: invokevirtual  #19 // java.io.OutputStream.write:([BII)V
        //    100: goto  8 (offset -92)
        //    103: aload_0
        //    104: getfield  #12 // jnr.posix.util.ExecIt$StreamPumper.onlyIfAvailable:Z
        //    107: ifeq  173 (offset +66)
        //    110: aload_0
        //    111: getfield  #13 // jnr.posix.util.ExecIt$StreamPumper.out:Ljava/io/OutputStream;
        //    114: invokevirtual  #18 // java.io.OutputStream.close:()V
        //    117: goto  173 (offset +56)
        //    120: astore  4
        //    122: goto  173 (offset +51)
        //    125: astore  4
        //    127: aload_0
        //    128: getfield  #12 // jnr.posix.util.ExecIt$StreamPumper.onlyIfAvailable:Z
        //    131: ifeq  173 (offset +42)
        //    134: aload_0
        //    135: getfield  #13 // jnr.posix.util.ExecIt$StreamPumper.out:Ljava/io/OutputStream;
        //    138: invokevirtual  #18 // java.io.OutputStream.close:()V
        //    141: goto  173 (offset +32)
        //    144: astore  4
        //    146: goto  173 (offset +27)
        //    149: astore  6
        //    151: aload_0
        //    152: getfield  #12 // jnr.posix.util.ExecIt$StreamPumper.onlyIfAvailable:Z
        //    155: ifeq  170 (offset +15)
        //    158: aload_0
        //    159: getfield  #13 // jnr.posix.util.ExecIt$StreamPumper.out:Ljava/io/OutputStream;
        //    162: invokevirtual  #18 // java.io.OutputStream.close:()V
        //    165: goto  170 (offset +5)
        //    168: astore  7
        //    170: aload  6
        //    172: athrow
        //    173: return
        //       Exception table:
        //         from 44 to 57 target 60 type any
        //         from 60 to 65 target 60 type any
        //         from 110 to 117 target 120 type java.io.IOException
        //         from 8 to 103 target 125 type java.lang.Exception
        //         from 134 to 141 target 144 type java.io.IOException
        //         from 8 to 103 target 149 type any
        //         from 125 to 127 target 149 type any
        //         from 158 to 165 target 168 type java.io.IOException
        //         from 149 to 151 target 149 type any
    }

  public void quit() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: iconst_1
        //      2: putfield  #14 // jnr.posix.util.ExecIt$StreamPumper.quit:Z
        //      5: aload_0
        //      6: getfield  #15 // jnr.posix.util.ExecIt$StreamPumper.waitLock:Ljava/lang/Object;
        //      9: dup
        //     10: astore_1
        //     11: monitorenter
        //     12: aload_0
        //     13: getfield  #15 // jnr.posix.util.ExecIt$StreamPumper.waitLock:Ljava/lang/Object;
        //     16: invokevirtual  #21 // java.lang.Object.notify:()V
        //     19: aload_1
        //     20: monitorexit
        //     21: goto  29 (offset +8)
        //     24: astore_2
        //     25: aload_1
        //     26: monitorexit
        //     27: aload_2
        //     28: athrow
        //     29: return
        //       Exception table:
        //         from 12 to 21 target 24 type any
        //         from 24 to 27 target 24 type any
    }

}