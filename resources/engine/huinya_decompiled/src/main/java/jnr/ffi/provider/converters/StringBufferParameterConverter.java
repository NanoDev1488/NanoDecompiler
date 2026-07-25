// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.converters.StringBufferParameterConverter
package jnr.ffi.provider.converters;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter;
import jnr.ffi.mapper.ToNativeConverter_Cacheable;
import jnr.ffi.mapper.ToNativeConverter_NoContext;
import jnr.ffi.mapper.ToNativeConverter_PostInvocation;
import jnr.ffi.provider.ParameterFlags;
import jnr.ffi.util.BufferUtil;

@ToNativeConverter_NoContext
@ToNativeConverter_Cacheable
public class StringBufferParameterConverter implements ToNativeConverter, ToNativeConverter_PostInvocation {

    // ---- поля ----
  private final Charset charset;
  private final int parameterFlags;

  private StringBufferParameterConverter(Charset arg0, int arg1) { // было: <init>
        super();
        charset = arg0;
        parameterFlags = arg1;
    }

  public Class nativeType() {
        return ByteBuffer.class;
    }

  public static StringBufferParameterConverter getInstance(int arg0, ToNativeContext arg1) {
        return new StringBufferParameterConverter(Charset.defaultCharset(), arg0);
    }

  public static StringBufferParameterConverter getInstance(Charset arg0, int arg1, ToNativeContext arg2) {
        return new StringBufferParameterConverter(arg0, arg1);
    }

  public ByteBuffer toNative(StringBuffer arg0, ToNativeContext arg1) {
        ByteBuffer __stk1;
        if (arg0 != null) {
            __stk1 = !ParameterFlags.isIn(parameterFlags) ? ByteBuffer.allocate(arg0.capacity() + 1) : charset.encode(CharBuffer.wrap(arg0));
            ByteBuffer var3 = __stk1;
            if (!ParameterFlags.isOut(parameterFlags)) {
                if (var3.hasArray()) {
                    return ((ByteBuffer) var3);
                } else {
                    byte[] var4 = new byte[arg0.capacity() + 1];
                    var3.get(var4, 0, var3.remaining());
                    return ByteBuffer.wrap(var4);
                }
            } else {
                if (var3.capacity() < arg0.capacity() + 1) {
                    byte[] var4 = new byte[arg0.capacity() + 1];
                    var3.get(var4, 0, var3.remaining());
                    return ByteBuffer.wrap(var4);
                } else {
                    if (var3.hasArray()) {
                        return ((ByteBuffer) var3);
                    } else {
                        byte[] var4 = new byte[arg0.capacity() + 1];
                        var3.get(var4, 0, var3.remaining());
                        return ByteBuffer.wrap(var4);
                    }
                }
            }
        } else {
            return null;
        }
    }

  public void postInvoke(StringBuffer arg0, ByteBuffer arg1, ToNativeContext arg2) {
        if (ParameterFlags.isOut(parameterFlags)) {
            if (arg0 != null) {
                if (arg1 != null) {
                    arg1.limit(arg1.capacity());
                    arg0.delete(0, arg0.length()).append(BufferUtil.getCharSequence(arg1, charset));
                }
            }
        }
    }

  public Object toNative(Object arg0, ToNativeContext arg1) {
        return toNative(((StringBuffer) arg0), arg1);
    }

  public void postInvoke(Object arg0, Object arg1, ToNativeContext arg2) {
        postInvoke(((StringBuffer) arg0), ((ByteBuffer) arg1), arg2);
    }

}