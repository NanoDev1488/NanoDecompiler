// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.SigType
package jnr.ffi.provider;

import java.lang.reflect.Type;
import java.util.Collection;
import jnr.ffi.NativeType;
import jnr.ffi.mapper.SignatureType;

public abstract class SigType implements SignatureType {

    // ---- поля ----
  private final Class javaType;
  private final Class convertedType;
  private final Collection annotations;
  private final NativeType nativeType;

  public SigType(Class arg0, NativeType arg1, Collection arg2, Class arg3) { // было: <init>
        super();
        javaType = arg0;
        annotations = arg2;
        convertedType = arg3;
        nativeType = arg1;
    }

  public final Class getDeclaredType() {
        return javaType;
    }

  public final Class effectiveJavaType() {
        return convertedType;
    }

  public final Collection annotations() {
        return annotations;
    }

  public final Collection getAnnotations() {
        return annotations;
    }

  public Type getGenericType() {
        return getDeclaredType();
    }

  public final String toString() {
        return String.format("declared: %s, effective: %s, native: %s", new Object[]{getDeclaredType(), effectiveJavaType(), getNativeType()});
    }

  public NativeType getNativeType() {
        return nativeType;
    }

}