// исходный (обфусцированный) внутренний класс: jnr.ffi.annotations.LongLong
package jnr.ffi.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import jnr.ffi.annotations.TypeDefinition;

@Retention("RUNTIME")
@Target
@TypeDefinition(alias = "int64_t")
public @interface LongLong extends Annotation {

}