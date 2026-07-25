// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.LibraryLoader
package jnr.ffi.provider.jffi;

import java.util.Map;
import jnr.ffi.Runtime;
import jnr.ffi.mapper.CompositeTypeMapper;
import jnr.ffi.mapper.SignatureTypeMapper;
import jnr.ffi.provider.jffi.AsmClassLoader;
import jnr.ffi.provider.jffi.NativeLibrary;

public abstract class LibraryLoader {

  public LibraryLoader() { // было: <init>
        super();
    }

  static SignatureTypeMapper getSignatureTypeMapper(Map arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getstatic  #17 // jnr.ffi.LibraryOption.TypeMapper:Ljnr/ffi/LibraryOption;
        //      4: invokeinterface  #28 // java.util.Map.containsKey:(Ljava/lang/Object;)Z, count 2
        //      9: ifeq  72 (offset +63)
        //     12: aload_0
        //     13: getstatic  #17 // jnr.ffi.LibraryOption.TypeMapper:Ljnr/ffi/LibraryOption;
        //     16: invokeinterface  #29 // java.util.Map.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //     21: astore_2
        //     22: aload_2
        //     23: instanceof  #8 // jnr.ffi.mapper.SignatureTypeMapper
        //     26: ifeq  37 (offset +11)
        //     29: aload_2
        //     30: checkcast  #8 // jnr.ffi.mapper.SignatureTypeMapper
        //     33: astore_1
        //     34: goto  69 (offset +35)
        //     37: aload_2
        //     38: instanceof  #10 // jnr.ffi.mapper.TypeMapper
        //     41: ifeq  59 (offset +18)
        //     44: new  #9 // jnr.ffi.mapper.SignatureTypeMapperAdapter
        //     47: dup
        //     48: aload_2
        //     49: checkcast  #10 // jnr.ffi.mapper.TypeMapper
        //     52: invokespecial  #23 // jnr.ffi.mapper.SignatureTypeMapperAdapter.<init>:(Ljnr/ffi/mapper/TypeMapper;)V
        //     55: astore_1
        //     56: goto  69 (offset +13)
        //     59: new  #2 // java.lang.IllegalArgumentException
        //     62: dup
        //     63: ldc  #1 // 'TypeMapper option is not a valid TypeMapper instance'
        //     65: invokespecial  #19 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //     68: athrow
        //     69: goto  80 (offset +11)
        //     72: new  #11 // jnr.ffi.provider.NullTypeMapper
        //     75: dup
        //     76: invokespecial  #24 // jnr.ffi.provider.NullTypeMapper.<init>:()V
        //     79: astore_1
        //     80: aload_1
        //     81: areturn
    }

  static CompositeTypeMapper newCompositeTypeMapper(Runtime arg0, AsmClassLoader arg1, SignatureTypeMapper arg2, CompositeTypeMapper arg3) {
        return new CompositeTypeMapper(new SignatureTypeMapper[]{arg2, new CachingTypeMapper(new InvokerTypeMapper(new NativeClosureManager(arg0, arg3), arg1, NativeLibraryLoader.ASM_ENABLED)), new CachingTypeMapper(new AnnotationTypeMapper())});
    }

  static CompositeTypeMapper newClosureTypeMapper(AsmClassLoader arg0, SignatureTypeMapper arg1) {
        return new CompositeTypeMapper(new SignatureTypeMapper[]{arg1, new CachingTypeMapper(new InvokerTypeMapper(null, arg0, NativeLibraryLoader.ASM_ENABLED)), new CachingTypeMapper(new AnnotationTypeMapper())});
    }

  abstract Object loadLibrary(NativeLibrary arg0, Class arg1, Map arg2, boolean arg3);

}