// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.converters.StringUtil
package jnr.ffi.provider.converters;

import java.lang.annotation.Annotation;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import jnr.ffi.annotations.Encoding;
import jnr.ffi.mapper.MethodParameterContext;
import jnr.ffi.mapper.ToNativeContext;

final class StringUtil {

    // ---- поля ----
  private static final Charset UTF8;
  private static final Charset USASCII;
  private static final Charset ISO8859_1;
  private static final Charset UTF16;
  private static final Charset UTF16LE;
  private static final Charset UTF16BE;

    static {
        UTF8 = Charset.forName("UTF-8");
        USASCII = Charset.forName("US-ASCII");
        ISO8859_1 = Charset.forName("ISO-8859-1");
        UTF16 = Charset.forName("UTF-16");
        UTF16LE = Charset.forName("UTF-16LE");
        UTF16BE = Charset.forName("UTF-16BE");
    }

  private StringUtil() { // было: <init>
        super();
    }

  static CharsetEncoder getEncoder(Charset arg0, ThreadLocal arg1) {
        CharsetEncoder __stk1;
        Reference var2 = ((Reference) arg1.get());
        if (var2 == null) {
            __stk1 = initEncoder(arg0, arg1);
        } else {
            CharsetEncoder var3 = ((CharsetEncoder) var2.get());
            __stk1 = var3 == null ? initEncoder(arg0, arg1) : var3.charset() != arg0 ? initEncoder(arg0, arg1) : var3;
        }
        return __stk1;
    }

  static CharsetDecoder getDecoder(Charset arg0, ThreadLocal arg1) {
        CharsetDecoder __stk1;
        Reference var2 = ((Reference) arg1.get());
        if (var2 == null) {
            __stk1 = initDecoder(arg0, arg1);
        } else {
            CharsetDecoder var3 = ((CharsetDecoder) var2.get());
            __stk1 = var3 == null ? initDecoder(arg0, arg1) : var3.charset() != arg0 ? initDecoder(arg0, arg1) : var3;
        }
        return __stk1;
    }

  private static CharsetEncoder initEncoder(Charset arg0, ThreadLocal arg1) {
        CharsetEncoder var2 = arg0.newEncoder();
        var2.onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
        arg1.set(new SoftReference(var2));
        return var2;
    }

  private static CharsetDecoder initDecoder(Charset arg0, ThreadLocal arg1) {
        CharsetDecoder var2 = arg0.newDecoder();
        var2.onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
        arg1.set(new SoftReference(var2));
        return var2;
    }

  static Charset getCharset(ToNativeContext arg0) {
        Charset var1 = Charset.defaultCharset();
        Charset var2;
        if (arg0 instanceof MethodParameterContext) {
            var2 = getEncodingCharset(Arrays.asList((((MethodParameterContext) arg0)).getMethod().getDeclaringClass().getAnnotations()));
            if (var2 != null) {
                var1 = var2;
            }
            var2 = getEncodingCharset(Arrays.asList((((MethodParameterContext) arg0)).getMethod().getAnnotations()));
            if (var2 != null) {
                var1 = var2;
            }
        }
        Charset var2 = getEncodingCharset(arg0.getAnnotations());
        if (var2 != null) {
            var1 = var2;
        }
        return var1;
    }

  private static Charset getEncodingCharset(Collection arg0) {
        Iterator var1 = arg0.iterator();
        Annotation var2;
        while (true) {
            if (!var1.hasNext()) {
                return null;
            }
            var2 = ((Annotation) var1.next());
            if (var2 instanceof Encoding) {
                break;
            }
            continue;
        }
        return Charset.forName((((Encoding) var2)).value());
    }

  static void throwException(CoderResult arg0) {
        try {
            arg0.throwException();
        } catch (RuntimeException var1) {
            throw var1;
        } catch (CharacterCodingException e2) {
            Throwable var1 = e2;
            throw new RuntimeException(var1);
        }
    }

  static int terminatorWidth(Charset arg0) {
        if (arg0.equals(UTF8)) {
            return 1;
        } else {
            if (arg0.equals(USASCII)) {
                return 1;
            } else {
                if (!arg0.equals(ISO8859_1)) {
                    if (arg0.equals(UTF16)) {
                        return 2;
                    } else {
                        if (arg0.equals(UTF16LE)) {
                            return 2;
                        } else {
                            if (!arg0.equals(UTF16BE)) {
                                return 4;
                            } else {
                                return 2;
                            }
                        }
                    }
                } else {
                    return 1;
                }
            }
        }
    }

  static int stringLength(ByteBuffer arg0, int arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokevirtual  #48 // java.nio.ByteBuffer.hasArray:()Z
        //      4: ifeq  75 (offset +71)
        //      7: aload_0
        //      8: invokevirtual  #45 // java.nio.ByteBuffer.array:()[B
        //     11: astore_2
        //     12: aload_0
        //     13: invokevirtual  #46 // java.nio.ByteBuffer.arrayOffset:()I
        //     16: aload_0
        //     17: invokevirtual  #49 // java.nio.ByteBuffer.limit:()I
        //     20: iadd
        //     21: istore_3
        //     22: iconst_0
        //     23: istore  4
        //     25: aload_0
        //     26: invokevirtual  #46 // java.nio.ByteBuffer.arrayOffset:()I
        //     29: aload_0
        //     30: invokevirtual  #50 // java.nio.ByteBuffer.position:()I
        //     33: iadd
        //     34: istore  5
        //     36: iload  5
        //     38: iload_3
        //     39: if_icmpge  72 (offset +33)
        //     42: aload_2
        //     43: iload  5
        //     45: iinc  5, 1
        //     48: baload
        //     49: ifne  58 (offset +9)
        //     52: iinc  4, 1
        //     55: goto  61 (offset +6)
        //     58: iconst_0
        //     59: istore  4
        //     61: iload  4
        //     63: iload_1
        //     64: if_icmpne  36 (offset -28)
        //     67: iload  5
        //     69: iload_1
        //     70: isub
        //     71: ireturn
        //     72: goto  129 (offset +57)
        //     75: aload_0
        //     76: invokevirtual  #50 // java.nio.ByteBuffer.position:()I
        //     79: istore_2
        //     80: aload_0
        //     81: invokevirtual  #49 // java.nio.ByteBuffer.limit:()I
        //     84: istore_3
        //     85: iconst_0
        //     86: istore  4
        //     88: iload_2
        //     89: istore  5
        //     91: iload  5
        //     93: iload_3
        //     94: if_icmpge  129 (offset +35)
        //     97: aload_0
        //     98: iload  5
        //    100: iinc  5, 1
        //    103: invokevirtual  #47 // java.nio.ByteBuffer.get:(I)B
        //    106: ifne  115 (offset +9)
        //    109: iinc  4, 1
        //    112: goto  118 (offset +6)
        //    115: iconst_0
        //    116: istore  4
        //    118: iload  4
        //    120: iload_1
        //    121: if_icmpne  91 (offset -30)
        //    124: iload  5
        //    126: iload_1
        //    127: isub
        //    128: ireturn
        //    129: iconst_m1
        //    130: ireturn
    }

}