// исходный (обфусцированный) внутренний класс: jnr.ffi.types.blksize_t
package jnr.ffi.types;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import jnr.ffi.annotations.TypeDefinition;

@Retention("RUNTIME")
@Target
@TypeDefinition(alias = "blksize_t")
public @interface blksize_t extends Annotation {

}