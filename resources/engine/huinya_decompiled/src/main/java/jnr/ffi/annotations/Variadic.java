// исходный (обфусцированный) внутренний класс: jnr.ffi.annotations.Variadic
package jnr.ffi.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;

@Retention("RUNTIME")
public @interface Variadic extends Annotation {

  public abstract int fixedCount();

}