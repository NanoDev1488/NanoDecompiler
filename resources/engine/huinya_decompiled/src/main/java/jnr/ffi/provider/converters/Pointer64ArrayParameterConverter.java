// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.converters.Pointer64ArrayParameterConverter
package jnr.ffi.provider.converters;

import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.annotations.LongLong;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter;
import jnr.ffi.mapper.ToNativeConverter_Cacheable;
import jnr.ffi.mapper.ToNativeConverter_NoContext;
import jnr.ffi.provider.ParameterFlags;
import jnr.ffi.provider.converters.Pointer64ArrayParameterConverter_Out;

@ToNativeConverter_NoContext
@ToNativeConverter_Cacheable
public class Pointer64ArrayParameterConverter implements ToNativeConverter {

    // ---- поля ----
  protected final Runtime runtime;
  protected final int parameterFlags;

  public static ToNativeConverter getInstance(ToNativeContext arg0) {
        int var1 = ParameterFlags.parse(arg0.getAnnotations());
        return ParameterFlags.isOut(var1) ? new Pointer64ArrayParameterConverter_Out(arg0.getRuntime(), var1) : new Pointer64ArrayParameterConverter(arg0.getRuntime(), var1);
    }

   Pointer64ArrayParameterConverter(Runtime arg0, int arg1) { // было: <init>
        super();
        runtime = arg0;
        parameterFlags = arg1;
    }

    @LongLong
  public Class nativeType() {
        return long[].class;
    }

  public long[] toNative(Pointer[] arg0, ToNativeContext arg1) {
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
        //     12: getfield  #15 // jnr.ffi.provider.converters.Pointer64ArrayParameterConverter.parameterFlags:I
        //     15: invokestatic  #25 // jnr.ffi.provider.ParameterFlags.isIn:(I)Z
        //     18: ifeq  104 (offset +86)
        //     21: iconst_0
        //     22: istore  4
        //     24: iload  4
        //     26: aload_1
        //     27: arraylength
        //     28: if_icmpge  104 (offset +76)
        //     31: aload_1
        //     32: iload  4
        //     34: aaload
        //     35: ifnull  76 (offset +41)
        //     38: aload_1
        //     39: iload  4
        //     41: aaload
        //     42: invokevirtual  #24 // jnr.ffi.Pointer.isDirect:()Z
        //     45: ifne  76 (offset +31)
        //     48: new  #4 // java.lang.IllegalArgumentException
        //     51: dup
        //     52: new  #6 // java.lang.StringBuilder
        //     55: dup
        //     56: invokespecial  #19 // java.lang.StringBuilder.<init>:()V
        //     59: ldc  #1 // 'invalid pointer in array at index '
        //     61: invokevirtual  #21 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     64: iload  4
        //     66: invokevirtual  #20 // java.lang.StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //     69: invokevirtual  #22 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //     72: invokespecial  #17 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //     75: athrow
        //     76: aload_3
        //     77: iload  4
        //     79: aload_1
        //     80: iload  4
        //     82: aaload
        //     83: ifnull  96 (offset +13)
        //     86: aload_1
        //     87: iload  4
        //     89: aaload
        //     90: invokevirtual  #23 // jnr.ffi.Pointer.address:()J
        //     93: goto  97 (offset +4)
        //     96: lconst_0
        //     97: lastore
        //     98: iinc  4, 1
        //    101: goto  24 (offset -77)
        //    104: aload_3
        //    105: areturn
    }

  public Object toNative(Object arg0, ToNativeContext arg1) {
        return toNative(((Pointer[]) arg0), arg1);
    }

}