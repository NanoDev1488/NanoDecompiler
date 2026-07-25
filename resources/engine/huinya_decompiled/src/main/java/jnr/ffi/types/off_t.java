// исходный (обфусцированный) внутренний класс: jnr.ffi.types.off_t
package jnr.ffi.types;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import jnr.ffi.annotations.TypeDefinition;

@Retention("RUNTIME")
@Target
@TypeDefinition(alias = "off_t")
public @interface off_t extends Annotation {

}