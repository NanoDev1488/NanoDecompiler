// исходный (обфусцированный) внутренний класс: jnr.ffi.types.int32_t
package jnr.ffi.types;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import jnr.ffi.annotations.TypeDefinition;

@Retention("RUNTIME")
@Target
@TypeDefinition(alias = "int32_t")
public @interface int32_t extends Annotation {

}