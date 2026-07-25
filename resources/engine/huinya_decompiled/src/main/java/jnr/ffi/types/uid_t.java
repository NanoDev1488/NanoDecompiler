// исходный (обфусцированный) внутренний класс: jnr.ffi.types.uid_t
package jnr.ffi.types;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import jnr.ffi.annotations.TypeDefinition;

@Retention("RUNTIME")
@Target
@TypeDefinition(alias = "uid_t")
public @interface uid_t extends Annotation {

}