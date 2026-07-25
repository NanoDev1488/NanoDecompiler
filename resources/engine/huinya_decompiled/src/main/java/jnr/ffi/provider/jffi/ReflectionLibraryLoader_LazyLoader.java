// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.ReflectionLibraryLoader.LazyLoader
package jnr.ffi.provider.jffi;

import java.lang.reflect.Method;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import jnr.ffi.CallingConvention;
import jnr.ffi.Runtime;
import jnr.ffi.Variable;
import jnr.ffi.mapper.FunctionMapper;
import jnr.ffi.mapper.SignatureTypeMapper;
import jnr.ffi.provider.Invoker;
import jnr.ffi.provider.jffi.AsmClassLoader;
import jnr.ffi.provider.jffi.DefaultInvokerFactory;
import jnr.ffi.provider.jffi.NativeFunctionMapperContext;
import jnr.ffi.provider.jffi.NativeLibrary;
import jnr.ffi.provider.jffi.NativeRuntime;
import jnr.ffi.provider.jffi.ReflectionLibraryLoader_Anon1;
import jnr.ffi.provider.jffi.ReflectionLibraryLoader_FunctionNotFoundInvoker;
import jnr.ffi.provider.jffi.ReflectionLibraryLoader_GetRuntimeInvoker;
import jnr.ffi.provider.jffi.ReflectionLibraryLoader_LazyLoader_VariableAcccessorInvoker;
import jnr.ffi.provider.jffi.ReflectionVariableAccessorGenerator;
import jnr.ffi.util.Annotations;

final class ReflectionLibraryLoader_LazyLoader extends AbstractMap {

    // ---- поля ----
  private final DefaultInvokerFactory invokerFactory;
  private final Runtime runtime;
  private final AsmClassLoader classLoader;
  private final SignatureTypeMapper typeMapper;
  private final FunctionMapper functionMapper;
  private final CallingConvention libraryCallingConvention;
  private final boolean libraryIsSynchronized;
  private final NativeLibrary library;
  private final Class interfaceClass;
  private final Map libraryOptions;

  private ReflectionLibraryLoader_LazyLoader(NativeLibrary arg0, Class arg1, Map arg2) { // было: <init>
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokespecial  #52 // java.util.AbstractMap.<init>:()V
        //      4: aload_0
        //      5: invokestatic  #63 // jnr.ffi.provider.jffi.NativeRuntime.getInstance:()Ljnr/ffi/provider/jffi/NativeRuntime;
        //      8: putfield  #42 // jnr.ffi.provider.jffi.ReflectionLibraryLoader$LazyLoader.runtime:Ljnr/ffi/Runtime;
        //     11: aload_0
        //     12: new  #18 // jnr.ffi.provider.jffi.AsmClassLoader
        //     15: dup
        //     16: invokespecial  #54 // jnr.ffi.provider.jffi.AsmClassLoader.<init>:()V
        //     19: putfield  #34 // jnr.ffi.provider.jffi.ReflectionLibraryLoader$LazyLoader.classLoader:Ljnr/ffi/provider/jffi/AsmClassLoader;
        //     22: aload_0
        //     23: aload_1
        //     24: putfield  #38 // jnr.ffi.provider.jffi.ReflectionLibraryLoader$LazyLoader.library:Ljnr/ffi/provider/jffi/NativeLibrary;
        //     27: aload_0
        //     28: aload_2
        //     29: putfield  #36 // jnr.ffi.provider.jffi.ReflectionLibraryLoader$LazyLoader.interfaceClass:Ljava/lang/Class;
        //     32: aload_0
        //     33: aload_3
        //     34: putfield  #41 // jnr.ffi.provider.jffi.ReflectionLibraryLoader$LazyLoader.libraryOptions:Ljava/util/Map;
        //     37: aload_0
        //     38: aload_3
        //     39: getstatic  #33 // jnr.ffi.LibraryOption.FunctionMapper:Ljnr/ffi/LibraryOption;
        //     42: invokeinterface  #72 // java.util.Map.containsKey:(Ljava/lang/Object;)Z, count 2
        //     47: ifeq  65 (offset +18)
        //     50: aload_3
        //     51: getstatic  #33 // jnr.ffi.LibraryOption.FunctionMapper:Ljnr/ffi/LibraryOption;
        //     54: invokeinterface  #73 // java.util.Map.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //     59: checkcast  #15 // jnr.ffi.mapper.FunctionMapper
        //     62: goto  68 (offset +6)
        //     65: invokestatic  #53 // jnr.ffi.provider.IdentityFunctionMapper.getInstance:()Ljnr/ffi/mapper/FunctionMapper;
        //     68: putfield  #35 // jnr.ffi.provider.jffi.ReflectionLibraryLoader$LazyLoader.functionMapper:Ljnr/ffi/mapper/FunctionMapper;
        //     71: aload_3
        //     72: invokestatic  #58 // jnr.ffi.provider.jffi.LibraryLoader.getSignatureTypeMapper:(Ljava/util/Map;)Ljnr/ffi/mapper/SignatureTypeMapper;
        //     75: astore  4
        //     77: aload_0
        //     78: getfield  #34 // jnr.ffi.provider.jffi.ReflectionLibraryLoader$LazyLoader.classLoader:Ljnr/ffi/provider/jffi/AsmClassLoader;
        //     81: aload  4
        //     83: invokestatic  #59 // jnr.ffi.provider.jffi.LibraryLoader.newClosureTypeMapper:(Ljnr/ffi/provider/jffi/AsmClassLoader;Ljnr/ffi/mapper/SignatureTypeMapper;)Ljnr/ffi/mapper/CompositeTypeMapper;
        //     86: astore  5
        //     88: aload_0
        //     89: aload_0
        //     90: getfield  #42 // jnr.ffi.provider.jffi.ReflectionLibraryLoader$LazyLoader.runtime:Ljnr/ffi/Runtime;
        //     93: aload_0
        //     94: getfield  #34 // jnr.ffi.provider.jffi.ReflectionLibraryLoader$LazyLoader.classLoader:Ljnr/ffi/provider/jffi/AsmClassLoader;
        //     97: aload  4
        //     99: aload  5
        //    101: invokestatic  #60 // jnr.ffi.provider.jffi.LibraryLoader.newCompositeTypeMapper:(Ljnr/ffi/Runtime;Ljnr/ffi/provider/jffi/AsmClassLoader;Ljnr/ffi/mapper/SignatureTypeMapper;Ljnr/ffi/mapper/CompositeTypeMapper;)Ljnr/ffi/mapper/CompositeTypeMapper;
        //    104: putfield  #43 // jnr.ffi.provider.jffi.ReflectionLibraryLoader$LazyLoader.typeMapper:Ljnr/ffi/mapper/SignatureTypeMapper;
        //    107: aload_0
        //    108: aload_2
        //    109: aload_3
        //    110: invokestatic  #57 // jnr.ffi.provider.jffi.InvokerUtil.getCallingConvention:(Ljava/lang/Class;Ljava/util/Map;)Ljnr/ffi/CallingConvention;
        //    113: putfield  #39 // jnr.ffi.provider.jffi.ReflectionLibraryLoader$LazyLoader.libraryCallingConvention:Ljnr/ffi/CallingConvention;
        //    116: aload_0
        //    117: aload_2
        //    118: ldc  #14 // jnr.ffi.annotations.Synchronized
        //    120: invokevirtual  #44 // java.lang.Class.isAnnotationPresent:(Ljava/lang/Class;)Z
        //    123: putfield  #40 // jnr.ffi.provider.jffi.ReflectionLibraryLoader$LazyLoader.libraryIsSynchronized:Z
        //    126: aload_0
        //    127: new  #19 // jnr.ffi.provider.jffi.DefaultInvokerFactory
        //    130: dup
        //    131: aload_0
        //    132: getfield  #42 // jnr.ffi.provider.jffi.ReflectionLibraryLoader$LazyLoader.runtime:Ljnr/ffi/Runtime;
        //    135: aload_1
        //    136: aload_0
        //    137: getfield  #43 // jnr.ffi.provider.jffi.ReflectionLibraryLoader$LazyLoader.typeMapper:Ljnr/ffi/mapper/SignatureTypeMapper;
        //    140: aload_0
        //    141: getfield  #35 // jnr.ffi.provider.jffi.ReflectionLibraryLoader$LazyLoader.functionMapper:Ljnr/ffi/mapper/FunctionMapper;
        //    144: aload_0
        //    145: getfield  #39 // jnr.ffi.provider.jffi.ReflectionLibraryLoader$LazyLoader.libraryCallingConvention:Ljnr/ffi/CallingConvention;
        //    148: aload_3
        //    149: aload_0
        //    150: getfield  #40 // jnr.ffi.provider.jffi.ReflectionLibraryLoader$LazyLoader.libraryIsSynchronized:Z
        //    153: invokespecial  #55 // jnr.ffi.provider.jffi.DefaultInvokerFactory.<init>:(Ljnr/ffi/Runtime;Ljnr/ffi/provider/jffi/NativeLibrary;Ljnr/ffi/mapper/SignatureTypeMapper;Ljnr/ffi/mapper/FunctionMapper;Ljnr/ffi/CallingConvention;Ljava/util/Map;Z)V
        //    156: putfield  #37 // jnr.ffi.provider.jffi.ReflectionLibraryLoader$LazyLoader.invokerFactory:Ljnr/ffi/provider/jffi/DefaultInvokerFactory;
        //    159: return
    }

  public Set entrySet() {
        throw new UnsupportedOperationException("not implemented");
    }

  public synchronized Invoker get(Object arg0) {
        if (arg0 instanceof Method) {
            Method var2 = ((Method) arg0);
            if (!Variable.class.isAssignableFrom(var2.getReturnType())) {
                if (!var2.getName().equals("getRuntime")) {
                    return invokerFactory.createInvoker(var2);
                } else {
                    if (!var2.getReturnType().isAssignableFrom(NativeRuntime.class)) {
                        return invokerFactory.createInvoker(var2);
                    } else {
                        return new ReflectionLibraryLoader_GetRuntimeInvoker(runtime, null);
                    }
                }
            } else {
                return getVariableAccessor(var2);
            }
        } else {
            throw new IllegalArgumentException("key not instance of Method");
        }
    }

  private Invoker getVariableAccessor(Method arg0) {
        Collection var2 = Annotations.sortedAnnotationCollection(arg0.getAnnotations());
        String var3 = functionMapper.mapFunctionName(arg0.getName(), new NativeFunctionMapperContext(library, var2));
        long var4 = library.getSymbolAddress(var3);
        if (var4 != 0L) {
            Variable var6 = ReflectionVariableAccessorGenerator.createVariableAccessor(runtime, arg0, var4, typeMapper, var2);
            return new ReflectionLibraryLoader_LazyLoader_VariableAcccessorInvoker(var6, null);
        } else {
            return new ReflectionLibraryLoader_FunctionNotFoundInvoker(arg0, var3, null);
        }
    }

  public Object get(Object arg0) {
        return get(arg0);
    }

   ReflectionLibraryLoader_LazyLoader(NativeLibrary arg0, Class arg1, Map arg2, ReflectionLibraryLoader_Anon1 arg3) { // было: <init>
        this(arg0, arg1, arg2);
    }

}