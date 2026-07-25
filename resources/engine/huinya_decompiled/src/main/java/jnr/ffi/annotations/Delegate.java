// исходный (обфусцированный) внутренний класс: jnr.ffi.annotations.Delegate
package jnr.ffi.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import jnr.ffi.CallingConvention;

@Retention("RUNTIME")
@Target
public @interface Delegate extends Annotation {

  public abstract CallingConvention convention();

}