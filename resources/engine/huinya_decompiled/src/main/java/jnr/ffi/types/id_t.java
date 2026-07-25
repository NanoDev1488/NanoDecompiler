// исходный (обфусцированный) внутренний класс: jnr.ffi.types.id_t
package jnr.ffi.types;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import jnr.ffi.annotations.TypeDefinition;

@Retention("RUNTIME")
@Target
@TypeDefinition(alias = "id_t")
public @interface id_t extends Annotation {

}