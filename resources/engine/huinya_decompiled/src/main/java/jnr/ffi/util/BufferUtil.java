// исходный (обфусцированный) внутренний класс: jnr.ffi.util.BufferUtil
package jnr.ffi.util;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;

public final class BufferUtil {

  private BufferUtil() { // было: <init>
        super();
    }

  public static void putString(ByteBuffer arg0, Charset arg1, String arg2) {
        putCharSequence(arg0, arg1, arg2);
    }

  public static String getString(ByteBuffer arg0, Charset arg1) {
        return getCharSequence(arg0, arg1).toString();
    }

  public static void putCharSequence(ByteBuffer arg0, Charset arg1, CharSequence arg2) {
        putCharSequence(arg0, arg1.newEncoder(), arg2);
    }

  public static void putCharSequence(ByteBuffer arg0, CharsetEncoder arg1, CharSequence arg2) {
        arg1.reset().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE).encode(CharBuffer.wrap(arg2), arg0, true);
        arg1.flush(arg0);
        int var3 = Math.round(arg1.maxBytesPerChar());
        if (var3 != 4) {
            if (var3 != 2) {
                if (var3 == 1) {
                    arg0.put(0);
                }
            } else {
                arg0.putShort(0);
            }
        } else {
            arg0.putInt(0);
        }
    }

  public static CharSequence getCharSequence(ByteBuffer arg0, Charset arg1) {
        ByteBuffer var2 = arg0.slice();
        int var3 = indexOf(var2, 0);
        if (var3 < 0) {
            var3 = var2.limit();
        }
        var2.position(0).limit(var3);
        return arg1.decode(var2);
    }

  public static CharSequence getCharSequence(ByteBuffer arg0, CharsetDecoder arg1) {
        CharBuffer __stk1;
        ByteBuffer var2 = arg0.slice();
        int var3 = indexOf(var2, 0);
        if (var3 < 0) {
            var3 = var2.limit();
        }
        var2.position(0).limit(var3);
        try {
            __stk1 = arg1.reset().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE).decode(var2);
        } catch (CharacterCodingException var4) {
            throw new Error("Illegal character data in native string", var4);
        }
    }

  public static int positionOf(ByteBuffer arg0, byte arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokevirtual  #24 // java.nio.ByteBuffer.hasArray:()Z
        //      4: ifeq  58 (offset +54)
        //      7: aload_0
        //      8: invokevirtual  #20 // java.nio.ByteBuffer.array:()[B
        //     11: astore_2
        //     12: aload_0
        //     13: invokevirtual  #21 // java.nio.ByteBuffer.arrayOffset:()I
        //     16: istore_3
        //     17: aload_0
        //     18: invokevirtual  #25 // java.nio.ByteBuffer.limit:()I
        //     21: istore  4
        //     23: aload_0
        //     24: invokevirtual  #26 // java.nio.ByteBuffer.position:()I
        //     27: istore  5
        //     29: iload  5
        //     31: iload  4
        //     33: if_icmpge  55 (offset +22)
        //     36: aload_2
        //     37: iload_3
        //     38: iload  5
        //     40: iadd
        //     41: baload
        //     42: iload_1
        //     43: if_icmpne  49 (offset +6)
        //     46: iload  5
        //     48: ireturn
        //     49: iinc  5, 1
        //     52: goto  29 (offset -23)
        //     55: goto  90 (offset +35)
        //     58: aload_0
        //     59: invokevirtual  #25 // java.nio.ByteBuffer.limit:()I
        //     62: istore_2
        //     63: aload_0
        //     64: invokevirtual  #26 // java.nio.ByteBuffer.position:()I
        //     67: istore_3
        //     68: iload_3
        //     69: iload_2
        //     70: if_icmpge  90 (offset +20)
        //     73: aload_0
        //     74: iload_3
        //     75: invokevirtual  #23 // java.nio.ByteBuffer.get:(I)B
        //     78: iload_1
        //     79: if_icmpne  84 (offset +5)
        //     82: iload_3
        //     83: ireturn
        //     84: iinc  3, 1
        //     87: goto  68 (offset -19)
        //     90: iconst_m1
        //     91: ireturn
    }

  public static int indexOf(ByteBuffer arg0, byte arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokevirtual  #24 // java.nio.ByteBuffer.hasArray:()Z
        //      4: ifeq  71 (offset +67)
        //      7: aload_0
        //      8: invokevirtual  #20 // java.nio.ByteBuffer.array:()[B
        //     11: astore_2
        //     12: aload_0
        //     13: invokevirtual  #21 // java.nio.ByteBuffer.arrayOffset:()I
        //     16: aload_0
        //     17: invokevirtual  #26 // java.nio.ByteBuffer.position:()I
        //     20: iadd
        //     21: istore_3
        //     22: aload_0
        //     23: invokevirtual  #21 // java.nio.ByteBuffer.arrayOffset:()I
        //     26: aload_0
        //     27: invokevirtual  #25 // java.nio.ByteBuffer.limit:()I
        //     30: iadd
        //     31: istore  4
        //     33: iconst_0
        //     34: istore  5
        //     36: iload  5
        //     38: iload  4
        //     40: if_icmpge  68 (offset +28)
        //     43: iload  5
        //     45: iconst_m1
        //     46: if_icmple  68 (offset +22)
        //     49: aload_2
        //     50: iload_3
        //     51: iload  5
        //     53: iadd
        //     54: baload
        //     55: iload_1
        //     56: if_icmpne  62 (offset +6)
        //     59: iload  5
        //     61: ireturn
        //     62: iinc  5, 1
        //     65: goto  36 (offset -29)
        //     68: goto  105 (offset +37)
        //     71: aload_0
        //     72: invokevirtual  #26 // java.nio.ByteBuffer.position:()I
        //     75: istore_2
        //     76: iconst_0
        //     77: istore_3
        //     78: iload_3
        //     79: aload_0
        //     80: invokevirtual  #25 // java.nio.ByteBuffer.limit:()I
        //     83: if_icmpge  105 (offset +22)
        //     86: aload_0
        //     87: iload_2
        //     88: iload_3
        //     89: iadd
        //     90: invokevirtual  #23 // java.nio.ByteBuffer.get:(I)B
        //     93: iload_1
        //     94: if_icmpne  99 (offset +5)
        //     97: iload_3
        //     98: ireturn
        //     99: iinc  3, 1
        //    102: goto  78 (offset -24)
        //    105: iconst_m1
        //    106: ireturn
    }

  public static int indexOf(ByteBuffer arg0, int arg1, byte arg2) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokevirtual  #24 // java.nio.ByteBuffer.hasArray:()Z
        //      4: ifeq  75 (offset +71)
        //      7: aload_0
        //      8: invokevirtual  #20 // java.nio.ByteBuffer.array:()[B
        //     11: astore_3
        //     12: aload_0
        //     13: invokevirtual  #21 // java.nio.ByteBuffer.arrayOffset:()I
        //     16: aload_0
        //     17: invokevirtual  #26 // java.nio.ByteBuffer.position:()I
        //     20: iadd
        //     21: iload_1
        //     22: iadd
        //     23: istore  4
        //     25: aload_0
        //     26: invokevirtual  #21 // java.nio.ByteBuffer.arrayOffset:()I
        //     29: aload_0
        //     30: invokevirtual  #25 // java.nio.ByteBuffer.limit:()I
        //     33: iadd
        //     34: istore  5
        //     36: iconst_0
        //     37: istore  6
        //     39: iload  6
        //     41: iload  5
        //     43: if_icmpge  72 (offset +29)
        //     46: iload  6
        //     48: iconst_m1
        //     49: if_icmple  72 (offset +23)
        //     52: aload_3
        //     53: iload  4
        //     55: iload  6
        //     57: iadd
        //     58: baload
        //     59: iload_2
        //     60: if_icmpne  66 (offset +6)
        //     63: iload  6
        //     65: ireturn
        //     66: iinc  6, 1
        //     69: goto  39 (offset -30)
        //     72: goto  113 (offset +41)
        //     75: aload_0
        //     76: invokevirtual  #26 // java.nio.ByteBuffer.position:()I
        //     79: istore_3
        //     80: iconst_0
        //     81: istore  4
        //     83: iload  4
        //     85: aload_0
        //     86: invokevirtual  #25 // java.nio.ByteBuffer.limit:()I
        //     89: if_icmpge  113 (offset +24)
        //     92: aload_0
        //     93: iload_3
        //     94: iload  4
        //     96: iadd
        //     97: invokevirtual  #23 // java.nio.ByteBuffer.get:(I)B
        //    100: iload_2
        //    101: if_icmpne  107 (offset +6)
        //    104: iload  4
        //    106: ireturn
        //    107: iinc  4, 1
        //    110: goto  83 (offset -27)
        //    113: iconst_m1
        //    114: ireturn
    }

  public static ByteBuffer slice(ByteBuffer arg0, int arg1) {
        ByteBuffer var2 = arg0.duplicate();
        var2.position(arg1);
        return var2.slice();
    }

  public static ByteBuffer slice(ByteBuffer arg0, int arg1, int arg2) {
        ByteBuffer var3 = arg0.duplicate();
        var3.position(arg1).limit(arg1 + arg2);
        return var3.slice();
    }

}