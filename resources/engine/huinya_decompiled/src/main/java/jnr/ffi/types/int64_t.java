// исходный (обфусцированный) внутренний класс: jnr.ffi.types.int64_t
package jnr.ffi.types;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import jnr.ffi.annotations.TypeDefinition;

@Retention("RUNTIME")
@Target
@TypeDefinition(alias = "int64_t")
public @interface int64_t extends Annotation {

}