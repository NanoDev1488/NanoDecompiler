// исходный (обфусцированный) внутренний класс: jnr.ffi.types.clock_t
package jnr.ffi.types;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import jnr.ffi.annotations.TypeDefinition;

@Retention("RUNTIME")
@Target
@TypeDefinition(alias = "clock_t")
public @interface clock_t extends Annotation {

}