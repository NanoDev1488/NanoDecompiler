// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.converters.CharSequenceParameterConverter
package jnr.ffi.provider.converters;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import jnr.ffi.annotations.Encoding;
import jnr.ffi.annotations.In;
import jnr.ffi.annotations.NulTerminate;
import jnr.ffi.mapper.MethodParameterContext;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter;
import jnr.ffi.mapper.ToNativeConverter_Cacheable;
import jnr.ffi.mapper.ToNativeConverter_NoContext;
import jnr.ffi.provider.converters.StringUtil;

@ToNativeConverter_NoContext
@ToNativeConverter_Cacheable
public class CharSequenceParameterConverter implements ToNativeConverter {

    // ---- поля ----
  private static final ToNativeConverter DEFAULT;
  private final ThreadLocal localEncoder;
  private final Charset charset;

    static {
        DEFAULT = new CharSequenceParameterConverter(Charset.defaultCharset());
    }

  public static ToNativeConverter getInstance(Charset arg0, ToNativeContext arg1) {
        return !Charset.defaultCharset().equals(arg0) ? new CharSequenceParameterConverter(arg0) : DEFAULT;
    }

  public static ToNativeConverter getInstance(ToNativeContext arg0) {
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
        return getInstance(var1, arg0);
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

  private CharSequenceParameterConverter(Charset arg0) { // было: <init>
        super();
        localEncoder = new ThreadLocal();
        charset = arg0;
    }

  public ByteBuffer toNative(CharSequence arg0, ToNativeContext arg1) {
        ByteBuffer var4;
        CharBuffer var5;
        if (arg0 != null) {
            CharsetEncoder var3 = StringUtil.getEncoder(charset, localEncoder);
            var4 = ByteBuffer.wrap(new byte[((int) (((float) arg0.length()) * var3.averageBytesPerChar())) + 4]);
            var5 = CharBuffer.wrap(arg0);
            var3.reset();
        } else {
            return null;
        }
        ByteBuffer var4;
        while (var5.hasRemaining()) {
            CoderResult var6 = var3.encode(var5, var4, true);
            if (!var6.isUnderflow()) {
                if (!var6.isOverflow()) {
                    StringUtil.throwException(var6);
                } else {
                    var4 = grow(var4);
                }
                continue;
            } else {
                var6 = var3.flush(var4);
                if (!var6.isUnderflow()) {
                    if (!var6.isOverflow()) {
                        StringUtil.throwException(var6);
                    } else {
                        var4 = grow(var4);
                    }
                    continue;
                } else {
                    break;
                }
            }
        }
        ByteBuffer var4;
        if (var4.remaining() <= 4) {
            var4 = grow(var4);
        }
        var4.position(var4.position() + 4);
        var4.flip();
        return var4;
    }

  private static ByteBuffer grow(ByteBuffer arg0) {
        ByteBuffer var1 = ByteBuffer.wrap(new byte[arg0.capacity() * 2]);
        arg0.flip();
        var1.put(arg0);
        return var1;
    }

    @In
    @NulTerminate
  public Class nativeType() {
        return ByteBuffer.class;
    }

  public Object toNative(Object arg0, ToNativeContext arg1) {
        return toNative(((CharSequence) arg0), arg1);
    }

}