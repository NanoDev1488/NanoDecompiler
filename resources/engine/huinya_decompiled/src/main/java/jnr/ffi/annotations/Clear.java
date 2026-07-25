// исходный (обфусцированный) внутренний класс: jnr.ffi.annotations.Clear
package jnr.ffi.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention("RUNTIME")
@Target
public @interface Clear extends Annotation {

}