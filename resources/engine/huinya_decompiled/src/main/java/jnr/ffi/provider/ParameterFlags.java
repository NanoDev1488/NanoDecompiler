// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.ParameterFlags
package jnr.ffi.provider;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Iterator;

public final class ParameterFlags {

    // ---- поля ----
  public static final int OUT = 1;
  public static final int IN = 2;
  public static final int PINNED = 4;
  public static final int NULTERMINATE = 8;
  public static final int TRANSIENT = 16;
  public static final int DIRECT = 32;

  private ParameterFlags() { // было: <init>
        super();
    }

  public static int parse(Annotation arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: iconst_0
        //      1: istore_1
        //      2: iload_1
        //      3: aload_0
        //      4: instanceof  #14 // jnr.ffi.annotations.Out
        //      7: ifeq  14 (offset +7)
        //     10: iconst_1
        //     11: goto  15 (offset +4)
        //     14: iconst_0
        //     15: ior
        //     16: istore_1
        //     17: iload_1
        //     18: aload_0
        //     19: instanceof  #12 // jnr.ffi.annotations.In
        //     22: ifeq  29 (offset +7)
        //     25: iconst_2
        //     26: goto  30 (offset +4)
        //     29: iconst_0
        //     30: ior
        //     31: istore_1
        //     32: iload_1
        //     33: aload_0
        //     34: instanceof  #16 // jnr.ffi.annotations.Transient
        //     37: ifeq  45 (offset +8)
        //     40: bipush  16
        //     42: goto  46 (offset +4)
        //     45: iconst_0
        //     46: ior
        //     47: istore_1
        //     48: iload_1
        //     49: aload_0
        //     50: instanceof  #11 // jnr.ffi.annotations.Direct
        //     53: ifeq  61 (offset +8)
        //     56: bipush  32
        //     58: goto  62 (offset +4)
        //     61: iconst_0
        //     62: ior
        //     63: istore_1
        //     64: iload_1
        //     65: aload_0
        //     66: instanceof  #15 // jnr.ffi.annotations.Pinned
        //     69: ifeq  76 (offset +7)
        //     72: iconst_4
        //     73: goto  77 (offset +4)
        //     76: iconst_0
        //     77: ior
        //     78: istore_1
        //     79: iload_1
        //     80: aload_0
        //     81: instanceof  #13 // jnr.ffi.annotations.NulTerminate
        //     84: ifeq  92 (offset +8)
        //     87: bipush  8
        //     89: goto  93 (offset +4)
        //     92: iconst_0
        //     93: ior
        //     94: istore_1
        //     95: iload_1
        //     96: ireturn
    }

  public static int parse(Annotation[] arg0) {
        int var1 = 0;
        Annotation[] var2 = arg0;
        int var3 = var2.length;
        int var4 = 0;
        while (var4 < var3) {
            Object var5 = var2[var4];
            var1 = var1 | parse(((Annotation) var5));
            ++var4;
            continue;
        }
        return var1;
    }

  public static int parse(Collection arg0) {
        int var1 = 0;
        Iterator var2 = arg0.iterator();
        while (var2.hasNext()) {
            Annotation var3 = ((Annotation) var2.next());
            var1 = var1 | parse(var3);
            continue;
        }
        return var1;
    }

  public static boolean isFlag(Annotation arg0) {
        return parse(arg0) != 0;
    }

  public static boolean isPinned(int arg0) {
        return (arg0 & 4) != 0;
    }

  public static boolean isTransient(int arg0) {
        return (arg0 & 16) != 0;
    }

  public static boolean isDirect(int arg0) {
        return (arg0 & 32) != 0;
    }

  public static boolean isNulTerminate(int arg0) {
        return (arg0 & 8) != 0;
    }

  public static boolean isOut(int arg0) {
        return (arg0 & 3) != 2;
    }

  public static boolean isIn(int arg0) {
        return (arg0 & 3) != 1;
    }

}