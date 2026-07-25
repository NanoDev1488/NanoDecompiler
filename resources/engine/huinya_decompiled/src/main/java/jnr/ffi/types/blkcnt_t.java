// исходный (обфусцированный) внутренний класс: jnr.ffi.types.blkcnt_t
package jnr.ffi.types;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import jnr.ffi.annotations.TypeDefinition;

@Retention("RUNTIME")
@Target
@TypeDefinition(alias = "blkcnt_t")
public @interface blkcnt_t extends Annotation {

}