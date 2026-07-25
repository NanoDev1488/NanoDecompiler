// исходный (обфусцированный) внутренний класс: org.intellij.lang.annotations.Language
package org.intellij.lang.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.jetbrains.annotations.NonNls;

@Retention("CLASS")
@Target
public @interface Language extends Annotation {

    @NonNls
  public abstract String value();

    @NonNls
  public abstract String prefix();

    @NonNls
  public abstract String suffix();

}