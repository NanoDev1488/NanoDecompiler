// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.converters.BoxedByteArrayParameterConverter
package jnr.ffi.provider.converters;

import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter;
import jnr.ffi.mapper.ToNativeConverter_Cacheable;
import jnr.ffi.mapper.ToNativeConverter_NoContext;
import jnr.ffi.provider.ParameterFlags;
import jnr.ffi.provider.converters.BoxedByteArrayParameterConverter_Out;

@ToNativeConverter_NoContext
@ToNativeConverter_Cacheable
public class BoxedByteArrayParameterConverter implements ToNativeConverter {

    // ---- поля ----
  private static final ToNativeConverter IN;
  private static final ToNativeConverter OUT;
  private static final ToNativeConverter INOUT;
  private final int parameterFlags;

    static {
        IN = new BoxedByteArrayParameterConverter(2);
        OUT = new BoxedByteArrayParameterConverter_Out(1);
        INOUT = new BoxedByteArrayParameterConverter_Out(3);
    }

  public static ToNativeConverter getInstance(ToNativeContext arg0) {
        int var1 = ParameterFlags.parse(arg0.getAnnotations());
        return !ParameterFlags.isOut(var1) ? IN : !ParameterFlags.isIn(var1) ? OUT : INOUT;
    }

   BoxedByteArrayParameterConverter(int arg0) { // было: <init>
        super();
        parameterFlags = arg0;
    }

  public byte[] toNative(Byte[] arg0, ToNativeContext arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_1
        //      1: ifnonnull  6 (offset +5)
        //      4: aconst_null
        //      5: areturn
        //      6: aload_1
        //      7: arraylength
        //      8: newarray  byte
        //     10: astore_3
        //     11: aload_0
        //     12: getfield  #15 // jnr.ffi.provider.converters.BoxedByteArrayParameterConverter.parameterFlags:I
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
        //     45: invokevirtual  #16 // java.lang.Byte.byteValue:()B
        //     48: goto  52 (offset +4)
        //     51: iconst_0
        //     52: bastore
        //     53: iinc  4, 1
        //     56: goto  24 (offset -32)
        //     59: aload_3
        //     60: areturn
    }

  public Class nativeType() {
        return byte[].class;
    }

  public Object toNative(Object arg0, ToNativeContext arg1) {
        return toNative(((Byte[]) arg0), arg1);
    }

}