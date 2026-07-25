// исходный (обфусцированный) внутренний класс: org.intellij.lang.annotations.RegExp
package org.intellij.lang.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.intellij.lang.annotations.Language;

@Retention("CLASS")
@Target
@Language("RegExp")
public @interface RegExp extends Annotation {

  public abstract String prefix();

  public abstract String suffix();

}