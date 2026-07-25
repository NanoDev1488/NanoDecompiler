// исходный (обфусцированный) внутренний класс: jnr.ffi.annotations.Pinned
package jnr.ffi.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention("RUNTIME")
@Target
public @interface Pinned extends Annotation {

}