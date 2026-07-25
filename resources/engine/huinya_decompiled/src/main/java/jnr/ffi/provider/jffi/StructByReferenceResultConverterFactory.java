// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.StructByReferenceResultConverterFactory
package jnr.ffi.provider.jffi;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import jnr.ffi.Runtime;
import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.mapper.FromNativeConverter;
import jnr.ffi.provider.converters.StructByReferenceFromNativeConverter;
import jnr.ffi.provider.jffi.AsmClassLoader;
import jnr.ffi.provider.jffi.AsmStructByReferenceFromNativeConverter;

final class StructByReferenceResultConverterFactory {

    // ---- поля ----
  private final Map converters;
  private final AsmClassLoader classLoader;
  private final boolean asmEnabled;

  public StructByReferenceResultConverterFactory(AsmClassLoader arg0, boolean arg1) { // было: <init>
        super();
        converters = new ConcurrentHashMap();
        classLoader = arg0;
        asmEnabled = arg1;
    }

  public final FromNativeConverter get(Class arg0, FromNativeContext arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #11 // jnr.ffi.provider.jffi.StructByReferenceResultConverterFactory.converters:Ljava/util/Map;
        //      4: aload_1
        //      5: invokeinterface  #17 // java.util.Map.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //     10: checkcast  #5 // jnr.ffi.mapper.FromNativeConverter
        //     13: astore_3
        //     14: aload_3
        //     15: ifnonnull  83 (offset +68)
        //     18: aload_0
        //     19: getfield  #11 // jnr.ffi.provider.jffi.StructByReferenceResultConverterFactory.converters:Ljava/util/Map;
        //     22: dup
        //     23: astore  4
        //     25: monitorenter
        //     26: aload_0
        //     27: getfield  #11 // jnr.ffi.provider.jffi.StructByReferenceResultConverterFactory.converters:Ljava/util/Map;
        //     30: aload_1
        //     31: invokeinterface  #17 // java.util.Map.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //     36: checkcast  #5 // jnr.ffi.mapper.FromNativeConverter
        //     39: dup
        //     40: astore_3
        //     41: ifnonnull  69 (offset +28)
        //     44: aload_0
        //     45: getfield  #11 // jnr.ffi.provider.jffi.StructByReferenceResultConverterFactory.converters:Ljava/util/Map;
        //     48: aload_1
        //     49: aload_0
        //     50: aload_2
        //     51: invokeinterface  #19 // jnr.ffi.mapper.FromNativeContext.getRuntime:()Ljnr/ffi/Runtime;, count 1
        //     56: aload_1
        //     57: aload_2
        //     58: invokespecial  #16 // jnr.ffi.provider.jffi.StructByReferenceResultConverterFactory.createConverter:(Ljnr/ffi/Runtime;Ljava/lang/Class;Ljnr/ffi/mapper/FromNativeContext;)Ljnr/ffi/mapper/FromNativeConverter;
        //     61: dup
        //     62: astore_3
        //     63: invokeinterface  #18 // java.util.Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;, count 3
        //     68: pop
        //     69: aload  4
        //     71: monitorexit
        //     72: goto  83 (offset +11)
        //     75: astore  5
        //     77: aload  4
        //     79: monitorexit
        //     80: aload  5
        //     82: athrow
        //     83: aload_3
        //     84: areturn
        //       Exception table:
        //         from 26 to 72 target 75 type any
        //         from 75 to 80 target 75 type any
    }

  private FromNativeConverter createConverter(Runtime arg0, Class arg1, FromNativeContext arg2) {
        return !asmEnabled ? StructByReferenceFromNativeConverter.getInstance(arg1, arg2) : AsmStructByReferenceFromNativeConverter.newStructByReferenceConverter(arg0, arg1, 0, classLoader);
    }

}