// исходный (обфусцированный) внутренний класс: jnr.ffi.types.ssize_t
package jnr.ffi.types;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import jnr.ffi.annotations.TypeDefinition;

@Retention("RUNTIME")
@Target
@TypeDefinition(alias = "ssize_t")
public @interface ssize_t extends Annotation {

}