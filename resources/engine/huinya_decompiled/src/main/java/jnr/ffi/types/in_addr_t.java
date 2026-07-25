// исходный (обфусцированный) внутренний класс: jnr.ffi.types.in_addr_t
package jnr.ffi.types;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import jnr.ffi.annotations.TypeDefinition;

@Retention("RUNTIME")
@Target
@TypeDefinition(alias = "in_addr_t")
public @interface in_addr_t extends Annotation {

}