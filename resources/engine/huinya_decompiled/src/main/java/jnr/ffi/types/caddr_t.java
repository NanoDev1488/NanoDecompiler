// исходный (обфусцированный) внутренний класс: jnr.ffi.types.caddr_t
package jnr.ffi.types;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import jnr.ffi.annotations.TypeDefinition;

@Retention("RUNTIME")
@Target
@TypeDefinition(alias = "caddr_t")
public @interface caddr_t extends Annotation {

}