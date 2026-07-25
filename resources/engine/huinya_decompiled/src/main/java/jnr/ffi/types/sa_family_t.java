// исходный (обфусцированный) внутренний класс: jnr.ffi.types.sa_family_t
package jnr.ffi.types;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import jnr.ffi.annotations.TypeDefinition;

@Retention("RUNTIME")
@Target
@TypeDefinition(alias = "sa_family_t")
public @interface sa_family_t extends Annotation {

}