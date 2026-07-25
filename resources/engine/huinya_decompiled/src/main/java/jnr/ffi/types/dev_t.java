// исходный (обфусцированный) внутренний класс: jnr.ffi.types.dev_t
package jnr.ffi.types;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import jnr.ffi.annotations.TypeDefinition;

@Retention("RUNTIME")
@Target
@TypeDefinition(alias = "dev_t")
public @interface dev_t extends Annotation {

}