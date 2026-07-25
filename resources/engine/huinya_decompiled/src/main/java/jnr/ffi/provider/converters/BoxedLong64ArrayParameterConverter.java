// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.converters.BoxedLong64ArrayParameterConverter
package jnr.ffi.provider.converters;

import jnr.ffi.annotations.LongLong;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter;
import jnr.ffi.mapper.ToNativeConverter_Cacheable;
import jnr.ffi.mapper.ToNativeConverter_NoContext;
import jnr.ffi.provider.ParameterFlags;
import jnr.ffi.provider.converters.BoxedLong64ArrayParameterConverter_Out;

@ToNativeConverter_NoContext
@ToNativeConverter_Cacheable
public class BoxedLong64ArrayParameterConverter implements ToNativeConverter {

    // ---- поля ----
  private static final ToNativeConverter IN;
  private static final ToNativeConverter OUT;
  private static final ToNativeConverter INOUT;
  private final int parameterFlags;

    static {
        IN = new BoxedLong64ArrayParameterConverter(2);
        OUT = new BoxedLong64ArrayParameterConverter_Out(1);
        INOUT = new BoxedLong64ArrayParameterConverter_Out(3);
    }

  public static ToNativeConverter getInstance(ToNativeContext arg0) {
        int var1 = ParameterFlags.parse(arg0.getAnnotations());
        return !ParameterFlags.isOut(var1) ? IN : !ParameterFlags.isIn(var1) ? OUT : INOUT;
    }

  public BoxedLong64ArrayParameterConverter(int arg0) { // было: <init>
        super();
        parameterFlags = arg0;
    }

  public long[] toNative(Long[] arg0, ToNativeContext arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_1
        //      1: ifnonnull  6 (offset +5)
        //      4: aconst_null
        //      5: areturn
        //      6: aload_1
        //      7: arraylength
        //      8: newarray  long
        //     10: astore_3
        //     11: aload_0
        //     12: getfield  #15 // jnr.ffi.provider.converters.BoxedLong64ArrayParameterConverter.parameterFlags:I
        //     15: invokestatic  #18 // jnr.ffi.provider.ParameterFlags.isIn:(I)Z
        //     18: ifeq  59 (offset +41)
        //     21: iconst_0
        //     22: istore  4
        //     24: iload  4
        //     26: aload_1
        //     27: arraylength
        //     28: if_icmpge  59 (offset +31)
        //     31: aload_3
        //     32: iload  4
        //     34: aload_1
        //     35: iload  4
        //     37: aaload
        //     38: ifnull  51 (offset +13)
        //     41: aload_1
        //     42: iload  4
        //     44: aaload
        //     45: invokevirtual  #16 // java.lang.Long.longValue:()J
        //     48: goto  52 (offset +4)
        //     51: lconst_0
        //     52: lastore
        //     53: iinc  4, 1
        //     56: goto  24 (offset -32)
        //     59: aload_3
        //     60: areturn
    }

    @LongLong
  public Class nativeType() {
        return long[].class;
    }

  public Object toNative(Object arg0, ToNativeContext arg1) {
        return toNative(((Long[]) arg0), arg1);
    }

}