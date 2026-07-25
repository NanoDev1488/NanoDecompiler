// исходный (обфусцированный) внутренний класс: jnr.ffi.types.key_t
package jnr.ffi.types;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import jnr.ffi.annotations.TypeDefinition;

@Retention("RUNTIME")
@Target
@TypeDefinition(alias = "key_t")
public @interface key_t extends Annotation {

}