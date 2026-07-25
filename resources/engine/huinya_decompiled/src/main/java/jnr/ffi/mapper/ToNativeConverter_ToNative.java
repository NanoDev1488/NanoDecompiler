// исходный (обфусцированный) внутренний класс: jnr.ffi.mapper.ToNativeConverter.ToNative
package jnr.ffi.mapper;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention("RUNTIME")
@Target
public @interface ToNativeConverter_ToNative extends Annotation {

  public abstract Class nativeType();

}