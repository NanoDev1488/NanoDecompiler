// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.cb.a
package dev.angelvisuals.a;

import net.minecraft.class_243;

class cb_ClassA109 {

    // ---- поля ----
   class_243 field463; // было: l
   long field464; // было: y
   long field465; // было: z
   long field466; // было: A
   long field467; // было: B
  private static final String se = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final String sf = "// === DO NOT TOUCH ===";
  private static final String sg = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final String sh = "// class hierarchy hashing: ENABLED";
  private static final String si = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final int kI = 739417510;
  private static final int kJ = 569324490;
  private static final int kK = -1403729071;
  private static final byte[] cx;

    static {
        cx = "^).r@`wAzHo/:!pq^lY00~<oOG@]YF3OFPo:}/y3\\}&JZoo]k&r3-a|=T;A;>!aXaOqGj$p;)/Nlr+0ImX@7e;#lvp6&Lxu~Z1I~s() +xYgFw QV.?wuttjG)u|Tb@'sc tYW[|Gk?'0}cYx>l@</ 0t\\(aIXjy/dV&\"q/ (?|F($@md$MWK|Td4J(jDB#*EkDULW,?!Hr!\"Gx[[#Z)S@l[egkrj+9?TWX4fRUJ'$<0hfH\\6Ci^|p.nvOnw/ys2".getBytes("ISO-8859-1");
    }

   cb_ClassA109(class_243 arg0, long arg1, long arg2, long arg3, long arg4) { // было: <init>
        super();
        field463 = arg0;
        field464 = arg1;
        field465 = arg2;
        field466 = arg3;
        field467 = arg4;
    }

   boolean method836() { // было: W
        return System.currentTimeMillis() - field464 < field465 + field466 + field467 ? 336172578 ^ 336172578 : 2033683008 ^ 2033683009;
    }

   float aB() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: invokestatic  #40 // java.lang.System.currentTimeMillis:()J
        //      3: aload_0
        //      4: getfield  #31 // dev.angelvisuals.a.cb$a.y:J
        //      7: lsub
        //      8: lstore_1
        //      9: lload_1
        //     10: aload_0
        //     11: getfield  #32 // dev.angelvisuals.a.cb$a.z:J
        //     14: lcmp
        //     15: ifge  33 (offset +18)
        //     18: lload_1
        //     19: l2f
        //     20: aload_0
        //     21: getfield  #32 // dev.angelvisuals.a.cb$a.z:J
        //     24: l2f
        //     25: fdiv
        //     26: fstore_3
        //     27: aload_0
        //     28: fload_3
        //     29: invokevirtual  #33 // dev.angelvisuals.a.cb$a.k:(F)F
        //     32: freturn
        //     33: lload_1
        //     34: aload_0
        //     35: getfield  #32 // dev.angelvisuals.a.cb$a.z:J
        //     38: aload_0
        //     39: getfield  #27 // dev.angelvisuals.a.cb$a.A:J
        //     42: ladd
        //     43: lcmp
        //     44: ifge  49 (offset +5)
        //     47: fconst_1
        //     48: freturn
        //     49: lload_1
        //     50: aload_0
        //     51: getfield  #32 // dev.angelvisuals.a.cb$a.z:J
        //     54: lsub
        //     55: aload_0
        //     56: getfield  #27 // dev.angelvisuals.a.cb$a.A:J
        //     59: lsub
        //     60: lstore_3
        //     61: fconst_1
        //     62: lload_3
        //     63: l2f
        //     64: aload_0
        //     65: getfield  #28 // dev.angelvisuals.a.cb$a.B:J
        //     68: l2f
        //     69: fdiv
        //     70: invokestatic  #36 // java.lang.Math.min:(FF)F
        //     73: fstore  5
        //     75: fconst_1
        //     76: aload_0
        //     77: fload  5
        //     79: invokevirtual  #34 // dev.angelvisuals.a.cb$a.l:(F)F
        //     82: fsub
        //     83: freturn
    }

   float aC() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: invokestatic  #40 // java.lang.System.currentTimeMillis:()J
        //      3: aload_0
        //      4: getfield  #31 // dev.angelvisuals.a.cb$a.y:J
        //      7: lsub
        //      8: lstore_1
        //      9: lload_1
        //     10: aload_0
        //     11: getfield  #32 // dev.angelvisuals.a.cb$a.z:J
        //     14: lcmp
        //     15: ifge  39 (offset +24)
        //     18: lload_1
        //     19: l2f
        //     20: aload_0
        //     21: getfield  #32 // dev.angelvisuals.a.cb$a.z:J
        //     24: l2f
        //     25: fdiv
        //     26: fstore_3
        //     27: ldc  #8 // 0.5f
        //     29: ldc  #8 // 0.5f
        //     31: aload_0
        //     32: fload_3
        //     33: invokevirtual  #35 // dev.angelvisuals.a.cb$a.m:(F)F
        //     36: fmul
        //     37: fadd
        //     38: freturn
        //     39: lload_1
        //     40: aload_0
        //     41: getfield  #32 // dev.angelvisuals.a.cb$a.z:J
        //     44: aload_0
        //     45: getfield  #27 // dev.angelvisuals.a.cb$a.A:J
        //     48: ladd
        //     49: lcmp
        //     50: ifge  55 (offset +5)
        //     53: fconst_1
        //     54: freturn
        //     55: lload_1
        //     56: aload_0
        //     57: getfield  #32 // dev.angelvisuals.a.cb$a.z:J
        //     60: lsub
        //     61: aload_0
        //     62: getfield  #27 // dev.angelvisuals.a.cb$a.A:J
        //     65: lsub
        //     66: lstore_3
        //     67: fconst_1
        //     68: lload_3
        //     69: l2f
        //     70: aload_0
        //     71: getfield  #28 // dev.angelvisuals.a.cb$a.B:J
        //     74: l2f
        //     75: fdiv
        //     76: invokestatic  #36 // java.lang.Math.min:(FF)F
        //     79: fstore  5
        //     81: fconst_1
        //     82: ldc  #7 // 0.30000001192092896f
        //     84: aload_0
        //     85: fload  5
        //     87: invokevirtual  #34 // dev.angelvisuals.a.cb$a.l:(F)F
        //     90: fmul
        //     91: fsub
        //     92: freturn
    }

  private float method837(float arg0) { // было: k
        return 1.0f - ((float) Math.pow(1.0 - ((double) arg0), 3.0));
    }

  private float method838(float arg0) { // было: l
        return arg0 * arg0 * arg0;
    }

  private float method839(float arg0) { // было: m
        float var2 = 1.7015800476074219f;
        float var3 = var2 + 1.0f;
        return 1.0f + var3 * ((float) Math.pow(((double) arg0) - 1.0, 3.0)) + var2 * ((float) Math.pow(((double) arg0) - 1.0, 2.0));
    }

  private static int jo(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int jp(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int jq(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}