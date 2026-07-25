// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.FFIProvider.SystemProviderSingletonHolder
package jnr.ffi.provider;

import jnr.ffi.provider.FFIProvider;

final class FFIProvider_SystemProviderSingletonHolder {

    // ---- поля ----
  private static final FFIProvider INSTANCE;

    static {
        INSTANCE = getInstance();
    }

  private FFIProvider_SystemProviderSingletonHolder() { // было: <init>
        super();
    }

  static FFIProvider getInstance() {
        String __stk1;
        FFIProvider __stk2;
        String var0 = System.getProperty("jnr.ffi.provider");
        if (var0 == null) {
            Package var1 = FFIProvider.class.getPackage();
            __stk1 = var1 == null ? "jnr.ffi.provider" : var1.getName() == null ? "jnr.ffi.provider" : var1.getName();
            String var2 = __stk1;
            var0 = new StringBuilder().append(((String) var2)).append(".jffi.Provider").toString();
        }
        try {
            __stk2 = ((FFIProvider) Class.forName(var0).newInstance());
        } catch (Throwable e1) {
            Throwable var1 = e1;
            return FFIProvider.access$100(new StringBuilder().append("could not load FFI provider ").append(var0).toString(), var1);
        }
    }

  static FFIProvider access$000() {
        return INSTANCE;
    }

}