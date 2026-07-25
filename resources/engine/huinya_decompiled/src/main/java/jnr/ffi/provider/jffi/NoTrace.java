// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.NoTrace
package jnr.ffi.provider.jffi;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention("RUNTIME")
@Target
public @interface NoTrace extends Annotation {

}