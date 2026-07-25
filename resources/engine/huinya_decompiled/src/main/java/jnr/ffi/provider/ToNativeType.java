// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.ToNativeType
package jnr.ffi.provider;

import java.util.Collection;
import jnr.ffi.NativeType;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter;
import jnr.ffi.mapper.ToNativeType;
import jnr.ffi.provider.SigType;

public class ToNativeType extends SigType implements ToNativeType {

    // ---- поля ----
  private final ToNativeConverter toNativeConverter;
  private final ToNativeContext toNativeContext;

  public ToNativeType(Class arg0, NativeType arg1, Collection arg2, ToNativeConverter arg3, ToNativeContext arg4) { // было: <init>
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: aload_1
        //      2: aload_2
        //      3: aload_3
        //      4: aload  4
        //      6: ifnull  19 (offset +13)
        //      9: aload  4
        //     11: invokeinterface  #8 // jnr.ffi.mapper.ToNativeConverter.nativeType:()Ljava/lang/Class;, count 1
        //     16: goto  20 (offset +4)
        //     19: aload_1
        //     20: invokespecial  #7 // jnr.ffi.provider.SigType.<init>:(Ljava/lang/Class;Ljnr/ffi/NativeType;Ljava/util/Collection;Ljava/lang/Class;)V
        //     23: aload_0
        //     24: aload  4
        //     26: putfield  #6 // jnr.ffi.provider.ToNativeType.toNativeConverter:Ljnr/ffi/mapper/ToNativeConverter;
        //     29: aload_0
        //     30: aload  5
        //     32: putfield  #5 // jnr.ffi.provider.ToNativeType.toNativeContext:Ljnr/ffi/mapper/ToNativeContext;
        //     35: return
    }

  public final ToNativeConverter getToNativeConverter() {
        return toNativeConverter;
    }

  public ToNativeContext getToNativeContext() {
        return toNativeContext;
    }

}