// исходный (обфусцированный) внутренний класс: jnr.ffi.types.socklen_t
package jnr.ffi.types;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import jnr.ffi.annotations.TypeDefinition;

@Retention("RUNTIME")
@Target
@TypeDefinition(alias = "socklen_t")
public @interface socklen_t extends Annotation {

}