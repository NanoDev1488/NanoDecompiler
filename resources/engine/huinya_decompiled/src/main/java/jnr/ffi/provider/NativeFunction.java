// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.NativeFunction
package jnr.ffi.provider;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import jnr.ffi.CallingConvention;
import jnr.ffi.annotations.IgnoreError;
import jnr.ffi.annotations.SaveError;

public final class NativeFunction {

    // ---- поля ----
  private final Method method;
  private final Collection annotations;
  private final boolean saveError;
  private final boolean ignoreError;
  private final CallingConvention callingConvention;

  public NativeFunction(Method arg0, CallingConvention arg1) { // было: <init>
        super();
        method = arg0;
        annotations = Collections.unmodifiableCollection(Arrays.asList(arg0.getAnnotations()));
        saveError = hasSaveError(arg0);
        ignoreError = hasIgnoreError(arg0);
        callingConvention = arg1;
    }

  public Collection annotations() {
        return annotations;
    }

  public CallingConvention convention() {
        return callingConvention;
    }

  public String name() {
        return method.getName();
    }

  public boolean isErrnoRequired() {
        return !ignoreError ? 1 : saveError;
    }

  public boolean hasSaveError() {
        return saveError;
    }

  public boolean hasIgnoreError() {
        return ignoreError;
    }

  public Method getMethod() {
        return method;
    }

  public static boolean hasSaveError(Method arg0) {
        return arg0.getAnnotation(SaveError.class) != null;
    }

  public static boolean hasIgnoreError(Method arg0) {
        return arg0.getAnnotation(IgnoreError.class) != null;
    }

}