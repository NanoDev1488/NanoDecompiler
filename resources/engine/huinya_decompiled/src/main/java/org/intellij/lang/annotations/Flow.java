// исходный (обфусцированный) внутренний класс: org.intellij.lang.annotations.Flow
package org.intellij.lang.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.jetbrains.annotations.NonNls;

@Documented
@Retention("CLASS")
@Target
public @interface Flow extends Annotation {

    // ---- поля ----
    @NonNls
  public static final String DEFAULT_SOURCE = "The method argument (if parameter was annotated) or this container (if instance method was annotated)";
    @NonNls
  public static final String THIS_SOURCE = "this";
    @NonNls
  public static final String DEFAULT_TARGET = "This container (if the parameter was annotated) or the return value (if instance method was annotated)";
    @NonNls
  public static final String RETURN_METHOD_TARGET = "The return value of this method";
    @NonNls
  public static final String THIS_TARGET = "this";

  public abstract String source();

  public abstract boolean sourceIsContainer();

  public abstract String target();

  public abstract boolean targetIsContainer();

}