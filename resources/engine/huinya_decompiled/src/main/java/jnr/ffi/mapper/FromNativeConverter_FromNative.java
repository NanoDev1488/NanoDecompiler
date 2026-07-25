// исходный (обфусцированный) внутренний класс: jnr.ffi.mapper.FromNativeConverter.FromNative
package jnr.ffi.mapper;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention("RUNTIME")
@Target
public @interface FromNativeConverter_FromNative extends Annotation {

  public abstract Class nativeType();

}