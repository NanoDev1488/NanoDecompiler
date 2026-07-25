// исходный (обфусцированный) внутренний класс: jnr.ffi.types.uintptr_t
package jnr.ffi.types;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import jnr.ffi.annotations.TypeDefinition;

@Retention("RUNTIME")
@Target
@TypeDefinition(alias = "uintptr_t")
public @interface uintptr_t extends Annotation {

}