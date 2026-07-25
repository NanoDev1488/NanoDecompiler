// исходный (обфусцированный) внутренний класс: jnr.ffi.annotations.IgnoreError
package jnr.ffi.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;

@Retention("RUNTIME")
public @interface IgnoreError extends Annotation {

}