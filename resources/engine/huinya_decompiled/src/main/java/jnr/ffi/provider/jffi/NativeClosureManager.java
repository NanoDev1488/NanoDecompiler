// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.NativeClosureManager
package jnr.ffi.provider.jffi;

import java.util.IdentityHashMap;
import java.util.Map;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.mapper.CompositeTypeMapper;
import jnr.ffi.mapper.SignatureTypeMapper;
import jnr.ffi.mapper.ToNativeConverter;
import jnr.ffi.provider.ClosureManager;
import jnr.ffi.provider.jffi.AsmClassLoader;
import jnr.ffi.provider.jffi.NativeClosureFactory;
import jnr.ffi.provider.jffi.NativeClosureFactory_ClosureReference;
import jnr.ffi.provider.jffi.NativeClosureManager_ClosureSite;

final class NativeClosureManager implements ClosureManager {

    // ---- поля ----
  private volatile Map factories;
  private volatile Map asmClassLoaders;
  private final Runtime runtime;
  private final SignatureTypeMapper typeMapper;

   NativeClosureManager(Runtime arg0, SignatureTypeMapper arg1) { // было: <init>
        super();
        factories = new IdentityHashMap();
        asmClassLoaders = new IdentityHashMap();
        runtime = arg0;
        typeMapper = new CompositeTypeMapper(new SignatureTypeMapper[]{arg1, new CachingTypeMapper(new ClosureTypeMapper())});
    }

   NativeClosureFactory getClosureFactory(Class arg0) {
        NativeClosureFactory var2 = ((NativeClosureFactory) factories.get(arg0));
        if (var2 == null) {
            AsmClassLoader var3 = ((AsmClassLoader) asmClassLoaders.get(arg0.getClassLoader()));
            if (var3 == null) {
                var3 = new AsmClassLoader(arg0.getClassLoader());
                asmClassLoaders.put(arg0.getClassLoader(), var3);
            }
            return initClosureFactory(arg0, var3);
        } else {
            return var2;
        }
    }

  public Object newClosure(Class arg0, Object arg1) {
        NativeClosureFactory var3 = ((NativeClosureFactory) factories.get(arg0));
        if (var3 == null) {
        }
        return null;
    }

  public final Pointer getClosurePointer(Class arg0, Object arg1) {
        return getClosureFactory(arg0).getClosureReference(arg1).getPointer();
    }

  synchronized NativeClosureFactory initClosureFactory(Class arg0, AsmClassLoader arg1) {
        NativeClosureFactory var3 = ((NativeClosureFactory) factories.get(arg0));
        if (var3 == null) {
            var3 = NativeClosureFactory.newClosureFactory(runtime, arg0, typeMapper, arg1);
            IdentityHashMap var4 = new IdentityHashMap();
            var4.putAll(factories);
            var4.put(arg0, var3);
            factories = var4;
            return var3;
        } else {
            return var3;
        }
    }

   ToNativeConverter newClosureSite(Class arg0) {
        return new NativeClosureManager_ClosureSite(getClosureFactory(arg0), null);
    }

}