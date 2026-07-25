// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.NoX86
package jnr.ffi.provider.jffi;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention("RUNTIME")
@Target
public @interface NoX86 extends Annotation {

}