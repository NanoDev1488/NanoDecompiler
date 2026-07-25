// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.converters.StringBuilderParameterConverter
package jnr.ffi.provider.converters;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter;
import jnr.ffi.mapper.ToNativeConverter_Cacheable;
import jnr.ffi.mapper.ToNativeConverter_NoContext;
import jnr.ffi.mapper.ToNativeConverter_PostInvocation;
import jnr.ffi.provider.ParameterFlags;
import jnr.ffi.provider.converters.StringUtil;

@ToNativeConverter_NoContext
@ToNativeConverter_Cacheable
public class StringBuilderParameterConverter implements ToNativeConverter, ToNativeConverter_PostInvocation {

    // ---- поля ----
  private final ThreadLocal localEncoder;
  private final ThreadLocal localDecoder;
  private final Charset charset;
  private final int parameterFlags;
  private final int terminatorWidth;

  private StringBuilderParameterConverter(Charset arg0, int arg1) { // было: <init>
        super();
        localEncoder = new ThreadLocal();
        localDecoder = new ThreadLocal();
        charset = arg0;
        parameterFlags = arg1;
        terminatorWidth = StringUtil.terminatorWidth(arg0);
    }

  public Class nativeType() {
        return ByteBuffer.class;
    }

  public static StringBuilderParameterConverter getInstance(int arg0, ToNativeContext arg1) {
        return new StringBuilderParameterConverter(StringUtil.getCharset(arg1), arg0);
    }

  public static StringBuilderParameterConverter getInstance(Charset arg0, int arg1, ToNativeContext arg2) {
        return new StringBuilderParameterConverter(arg0, arg1);
    }

  public ByteBuffer toNative(StringBuilder arg0, ToNativeContext arg1) {
        if (arg0 != null) {
            CharsetEncoder var3 = StringUtil.getEncoder(charset, localEncoder);
            ByteBuffer var4 = ByteBuffer.wrap(new byte[arg0.capacity() * ((int) Math.ceil(((double) var3.maxBytesPerChar()))) + 4]);
            if (ParameterFlags.isIn(parameterFlags)) {
                var4.mark();
                var3.reset();
                CoderResult var5 = var3.encode(CharBuffer.wrap(arg0), var4, true);
                if (var5.isUnderflow()) {
                    var5 = var3.flush(var4);
                }
                if (var5.isError()) {
                    StringUtil.throwException(var5);
                }
                var4.reset();
            }
            return var4;
        } else {
            return null;
        }
    }

  public void postInvoke(StringBuilder arg0, ByteBuffer arg1, ToNativeContext arg2) {
        if (!ParameterFlags.isOut(parameterFlags)) {
            return;
        }
        if (arg0 == null) {
            return;
        }
        if (arg1 == null) {
            return;
        } else {
            arg1.limit(StringUtil.stringLength(arg1, terminatorWidth));
        }
        try {
            arg0.delete(0, arg0.length()).append(StringUtil.getDecoder(charset, localDecoder).reset().decode(arg1));
        } catch (CharacterCodingException var4) {
            throw new RuntimeException(var4);
        }
    }

  public Object toNative(Object arg0, ToNativeContext arg1) {
        return toNative(((StringBuilder) arg0), arg1);
    }

  public void postInvoke(Object arg0, Object arg1, ToNativeContext arg2) {
        postInvoke(((StringBuilder) arg0), ((ByteBuffer) arg1), arg2);
    }

}