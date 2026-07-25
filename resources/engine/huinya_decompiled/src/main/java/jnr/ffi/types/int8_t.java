// исходный (обфусцированный) внутренний класс: jnr.ffi.types.int8_t
package jnr.ffi.types;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import jnr.ffi.annotations.TypeDefinition;

@Retention("RUNTIME")
@Target
@TypeDefinition(alias = "int8_t")
public @interface int8_t extends Annotation {

}