// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.ReflectionLibraryLoader
package jnr.ffi.provider.jffi;

import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import jnr.ffi.CallingConvention;
import jnr.ffi.mapper.SignatureTypeMapper;
import jnr.ffi.provider.InterfaceScanner;
import jnr.ffi.provider.NativeFunction;
import jnr.ffi.provider.NativeInvocationHandler;
import jnr.ffi.provider.NativeVariable;
import jnr.ffi.provider.jffi.InvokerUtil;
import jnr.ffi.provider.jffi.LibraryLoader;
import jnr.ffi.provider.jffi.NativeLibrary;
import jnr.ffi.provider.jffi.ReflectionLibraryLoader_LazyLoader;

class ReflectionLibraryLoader extends LibraryLoader {

   ReflectionLibraryLoader() { // было: <init>
        super();
    }

   Object loadLibrary(NativeLibrary arg0, Class arg1, Map arg2, boolean arg3) {
        ReflectionLibraryLoader_LazyLoader var5 = new ReflectionLibraryLoader_LazyLoader(arg0, arg1, arg2, null);
        if (arg3) {
            SignatureTypeMapper var6 = getSignatureTypeMapper(arg2);
            CallingConvention var7 = InvokerUtil.getCallingConvention(arg1, arg2);
            InterfaceScanner var8 = new InterfaceScanner(arg1, var6, var7);
            Iterator var9 = var8.functions().iterator();
            while (var9.hasNext()) {
                NativeFunction var10 = ((NativeFunction) var9.next());
                var5.get(var10.getMethod());
                continue;
            }
            var9 = var8.variables().iterator();
            while (var9.hasNext()) {
                NativeVariable var10 = ((NativeVariable) var9.next());
                var5.get(var10.getMethod());
                continue;
            }
        }
        return arg1.cast(Proxy.newProxyInstance(arg1.getClassLoader(), new Class[]{arg1, LoadedLibrary.class}, new NativeInvocationHandler(var5)));
    }

}