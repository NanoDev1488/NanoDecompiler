// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.NativeClosureManager.ClosureSite
package jnr.ffi.provider.jffi;

import jnr.ffi.Pointer;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter;
import jnr.ffi.mapper.ToNativeConverter_NoContext;
import jnr.ffi.provider.jffi.ClosureFromNativeConverter_AbstractClosurePointer;
import jnr.ffi.provider.jffi.NativeClosureFactory;
import jnr.ffi.provider.jffi.NativeClosureFactory_ClosureReference;
import jnr.ffi.provider.jffi.NativeClosureManager_Anon1;

@ToNativeConverter_NoContext
public final class NativeClosureManager_ClosureSite implements ToNativeConverter {

    // ---- поля ----
  private final NativeClosureFactory factory;
  private NativeClosureFactory_ClosureReference closureReference;

  private NativeClosureManager_ClosureSite(NativeClosureFactory arg0) { // было: <init>
        super();
        closureReference = null;
        factory = arg0;
    }

  public Pointer toNative(Object arg0, ToNativeContext arg1) {
        if (arg0 != null) {
            if (!(arg0 instanceof ClosureFromNativeConverter_AbstractClosurePointer)) {
                NativeClosureFactory_ClosureReference var3 = closureReference;
                if (var3 == null) {
                    var3 = factory.getClosureReference(arg0);
                    if (closureReference == null) {
                        closureReference = var3;
                    } else {
                        if (closureReference.get() == null) {
                            closureReference = var3;
                        }
                    }
                    return var3.getPointer();
                } else {
                    if (var3.getCallable() != arg0) {
                        var3 = factory.getClosureReference(arg0);
                        if (closureReference == null) {
                            closureReference = var3;
                        } else {
                            if (closureReference.get() == null) {
                                closureReference = var3;
                            }
                        }
                        return var3.getPointer();
                    } else {
                        return var3.getPointer();
                    }
                }
            } else {
                return ((ClosureFromNativeConverter_AbstractClosurePointer) arg0);
            }
        } else {
            return null;
        }
    }

  public Class nativeType() {
        return Pointer.class;
    }

  public Object toNative(Object arg0, ToNativeContext arg1) {
        return toNative(arg0, arg1);
    }

   NativeClosureManager_ClosureSite(NativeClosureFactory arg0, NativeClosureManager_Anon1 arg1) { // было: <init>
        this(arg0);
    }

}