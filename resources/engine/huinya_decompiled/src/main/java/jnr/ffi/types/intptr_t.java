// исходный (обфусцированный) внутренний класс: jnr.ffi.types.intptr_t
package jnr.ffi.types;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import jnr.ffi.annotations.TypeDefinition;

@Retention("RUNTIME")
@Target
@TypeDefinition(alias = "intptr_t")
public @interface intptr_t extends Annotation {

}