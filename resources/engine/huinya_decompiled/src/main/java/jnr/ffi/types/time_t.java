// исходный (обфусцированный) внутренний класс: jnr.ffi.types.time_t
package jnr.ffi.types;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import jnr.ffi.annotations.TypeDefinition;

@Retention("RUNTIME")
@Target
@TypeDefinition(alias = "time_t")
public @interface time_t extends Annotation {

}