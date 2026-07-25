// исходный (обфусцированный) внутренний класс: org.intellij.lang.annotations.Pattern
package org.intellij.lang.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.intellij.lang.annotations.Language;

@Retention("CLASS")
@Target
public @interface Pattern extends Annotation {

    @Language("RegExp")
  public abstract String value();

}