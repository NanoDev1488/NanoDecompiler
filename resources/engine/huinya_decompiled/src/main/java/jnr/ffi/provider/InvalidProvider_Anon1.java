// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.InvalidProvider$1
package jnr.ffi.provider;

import java.util.Collection;
import java.util.Map;
import jnr.ffi.LibraryLoader;
import jnr.ffi.provider.InvalidProvider;

class InvalidProvider_Anon1 extends LibraryLoader {

    // ---- поля ----
  final InvalidProvider this$0;

   InvalidProvider_Anon1(InvalidProvider arg0, Class arg1) { // было: <init>
        super(arg1);
        this$0 = arg0;
    }

  protected Object loadLibrary(Class arg0, Collection arg1, Collection arg2, Map arg3, boolean arg4) {
        UnsatisfiedLinkError var6 = new UnsatisfiedLinkError(InvalidProvider.access$000(this$0));
        var6.initCause(InvalidProvider.access$100(this$0));
        throw var6;
    }

}