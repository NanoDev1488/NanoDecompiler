// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.converters.StringResultConverter
package jnr.ffi.provider.converters;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import jnr.ffi.Pointer;
import jnr.ffi.annotations.Encoding;
import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.mapper.FromNativeConverter;
import jnr.ffi.mapper.FromNativeConverter_Cacheable;
import jnr.ffi.mapper.FromNativeConverter_NoContext;
import jnr.ffi.mapper.MethodResultContext;
import jnr.ffi.provider.converters.StringUtil;

@FromNativeConverter_NoContext
@FromNativeConverter_Cacheable
public class StringResultConverter implements FromNativeConverter {

    // ---- поля ----
  private static final FromNativeConverter DEFAULT;
  private final ThreadLocal localDecoder;
  private final Charset charset;
  private final int terminatorWidth;

    static {
        DEFAULT = new StringResultConverter(Charset.defaultCharset());
    }

  private StringResultConverter(Charset arg0) { // было: <init>
        super();
        localDecoder = new ThreadLocal();
        charset = arg0;
        terminatorWidth = StringUtil.terminatorWidth(arg0);
    }

  public static FromNativeConverter getInstance(Charset arg0) {
        return !Charset.defaultCharset().equals(arg0) ? new StringResultConverter(arg0) : DEFAULT;
    }

  public static FromNativeConverter getInstance(FromNativeContext arg0) {
        Charset var1 = Charset.defaultCharset();
        Encoding var2;
        if (arg0 instanceof MethodResultContext) {
            var2 = getEncoding(Arrays.asList((((MethodResultContext) arg0)).getMethod().getDeclaringClass().getAnnotations()));
            if (var2 != null) {
                var1 = Charset.forName(var2.value());
            }
        }
        Encoding var2 = getEncoding(arg0.getAnnotations());
        if (var2 != null) {
            var1 = Charset.forName(var2.value());
        }
        return getInstance(var1);
    }

  public String fromNative(Pointer arg0, FromNativeContext arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_1
        //      1: ifnonnull  6 (offset +5)
        //      4: aconst_null
        //      5: areturn
        //      6: iconst_0
        //      7: istore_3
        //      8: iload_3
        //      9: aload_1
        //     10: iload_3
        //     11: i2l
        //     12: iconst_0
        //     13: invokevirtual  #43 // jnr.ffi.Pointer.indexOf:(JB)I
        //     16: iadd
        //     17: istore_3
        //     18: iconst_1
        //     19: istore  4
        //     21: iload  4
        //     23: aload_0
        //     24: getfield  #27 // jnr.ffi.provider.converters.StringResultConverter.terminatorWidth:I
        //     27: if_icmpge  56 (offset +29)
        //     30: aload_1
        //     31: iload_3
        //     32: iload  4
        //     34: iadd
        //     35: i2l
        //     36: invokevirtual  #42 // jnr.ffi.Pointer.getByte:(J)B
        //     39: ifeq  50 (offset +11)
        //     42: iload_3
        //     43: iload  4
        //     45: iadd
        //     46: istore_3
        //     47: goto  8 (offset -39)
        //     50: iinc  4, 1
        //     53: goto  21 (offset -32)
        //     56: iload_3
        //     57: newarray  byte
        //     59: astore  4
        //     61: aload_1
        //     62: lconst_0
        //     63: aload  4
        //     65: iconst_0
        //     66: aload  4
        //     68: arraylength
        //     69: invokevirtual  #41 // jnr.ffi.Pointer.get:(J[BII)V
        //     72: aload_0
        //     73: getfield  #25 // jnr.ffi.provider.converters.StringResultConverter.charset:Ljava/nio/charset/Charset;
        //     76: aload_0
        //     77: getfield  #26 // jnr.ffi.provider.converters.StringResultConverter.localDecoder:Ljava/lang/ThreadLocal;
        //     80: invokestatic  #49 // jnr.ffi.provider.converters.StringUtil.getDecoder:(Ljava/nio/charset/Charset;Ljava/lang/ThreadLocal;)Ljava/nio/charset/CharsetDecoder;
        //     83: invokevirtual  #39 // java.nio.charset.CharsetDecoder.reset:()Ljava/nio/charset/CharsetDecoder;
        //     86: aload  4
        //     88: invokestatic  #33 // java.nio.ByteBuffer.wrap:([B)Ljava/nio/ByteBuffer;
        //     91: invokevirtual  #38 // java.nio.charset.CharsetDecoder.decode:(Ljava/nio/ByteBuffer;)Ljava/nio/CharBuffer;
        //     94: invokevirtual  #34 // java.nio.CharBuffer.toString:()Ljava/lang/String;
        //     97: areturn
        //     98: astore  5
        //    100: new  #3 // java.lang.RuntimeException
        //    103: dup
        //    104: aload  5
        //    106: invokespecial  #30 // java.lang.RuntimeException.<init>:(Ljava/lang/Throwable;)V
        //    109: athrow
        //       Exception table:
        //         from 72 to 97 target 98 type java.nio.charset.CharacterCodingException
    }

  public Class nativeType() {
        return Pointer.class;
    }

  private static Encoding getEncoding(Collection arg0) {
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
        return ((Encoding) var2);
    }

  public Object fromNative(Object arg0, FromNativeContext arg1) {
        return fromNative(((Pointer) arg0), arg1);
    }

}