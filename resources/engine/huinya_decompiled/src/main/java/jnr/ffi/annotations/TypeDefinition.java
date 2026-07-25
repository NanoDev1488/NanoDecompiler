// исходный (обфусцированный) внутренний класс: jnr.ffi.annotations.TypeDefinition
package jnr.ffi.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import jnr.ffi.TypeAlias;

@Retention("RUNTIME")
@Target
public @interface TypeDefinition extends Annotation {

  public abstract TypeAlias alias();

}